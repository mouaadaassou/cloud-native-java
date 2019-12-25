package io.nodom.data;


import java.time.LocalDate;
import lombok.Data;

@Data
public class BaseEntity {

  private LocalDate createdAt;
  private LocalDate updatedAt;
}
