package com.fypgradingsystem.jury.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fypgradingsystem.jury.messaging.Message;
import com.fypgradingsystem.jury.messaging.QueueSender;
import com.fypgradingsystem.jury.model.Jury;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.Json;
import java.util.List;

@Service
public class JuryRepository {

  @Autowired
  JuryCrudRepository crudRepository;

  @Autowired
  QueueSender queueSender;

  public Uni<Jury> createJury(Jury jury) {
    return crudRepository.persist(jury).onItem().transform(persistedJury -> {
      var message = Message
          .builder()
          .type("assign-jury")
          .queue("teams")
          .order(Json.encode(jury))
          .build();
      queueSender.send(message);
      return persistedJury;
    });
  }

  public Uni<List<Jury>> getAllJuries() {
    return Uni.createFrom().future(crudRepository.findAll().collectList().toFuture());
  }
}
