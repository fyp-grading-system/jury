package com.fypgradingsystem.jury.repositories;

import com.fypgradingsystem.jury.model.Jury;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import io.smallrye.mutiny.Uni;
import java.util.List;

public interface JuryCrudRepository extends ReactiveMongoRepository<Jury, String> {

  @Query("{ 'supervisorId': ?0 }")
  Uni<List<Jury>> getBySupervisorId(String id);

  @Query("{ 'id' : ?0 }")
  Uni<Jury> getById(String id);

  default Uni<Jury> persist(Jury jury) {
    return Uni.createFrom().future(save(jury).toFuture());
  }
}
