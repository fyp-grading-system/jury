package com.fypgradingsystem.jury.model;

import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Jury {
  @Id
  private String id;
  private String name;
  private String email;
  private String teamUUID;
}
