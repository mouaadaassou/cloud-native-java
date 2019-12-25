package io.nodom.order;

import io.nodom.data.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LineItem extends BaseEntity {

  private String name;
  private String productId;
  private Integer quantity;
  private Double price;
  private Double tax;
}
