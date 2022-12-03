package com.fypgradingsystem.jury.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fypgradingsystem.jury.model.Jury;
import com.fypgradingsystem.jury.repositories.JuryRepository;
import io.vertx.core.json.Json;

@Component
public class Receiver {
  @Autowired
  JuryRepository juryRepository;

  @RabbitListener(queues = "${queue.name}")
  public void receiveMessage(String message) {
    var messageEntity = Json.decodeValue(message, Message.class);
    switch (messageEntity.getType()) {
      case "create-jury":
        juryRepository.createJury(Json.decodeValue(messageEntity.getOrder(), Jury.class)).await().indefinitely();
        break;
      default:
        break;
    }
  }
}