package com.fypgradingsystem.jury;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fypgradingsystem.jury.repositories.JuryRepository;
import com.fypgradingsystem.jury.model.Jury;
import io.smallrye.mutiny.Uni;
import java.util.List;

@RestController
public class JuryResource {
  
  @Autowired
  JuryRepository juryRepository;

  @GetMapping("/juries")
  public Uni<List<Jury>> getAllJuries() {
    return juryRepository.getAllJuries();
  }
}
