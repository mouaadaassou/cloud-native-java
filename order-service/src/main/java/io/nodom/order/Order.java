package io.nodom.order;


import io.nodom.address.Address;
import io.nodom.data.BaseEntity;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.aggregation.BooleanOperators.Or;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.Assert;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order extends BaseEntity {


  @Id
  private String orderId;

  private String accountNumber;

  private OrderStatus orderStatus;

  private List<LineItem> itemLineList = new ArrayList<>();

  private Address shippingAddress;

  public Order(String accountNumber, Address shippingAddress) {
    this.accountNumber = accountNumber;
    this.shippingAddress = shippingAddress;
  }

  public void addLineItem(LineItem itemLine) {
    Assert.notNull(itemLine, "the item line should not be NULL");
    this.itemLineList.add(itemLine);
  }
}
