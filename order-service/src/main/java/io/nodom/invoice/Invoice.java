package io.nodom.invoice;


import io.nodom.address.Address;
import io.nodom.data.BaseEntity;
import io.nodom.order.Order;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.Assert;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Invoice extends BaseEntity {

  @Id
  private String invoiceId;

  private String accountId;

  private List<Order> orderList = new ArrayList<>();

  private Address billingAddress;

  private InvoiceStatus invoiceStatus;

  public Invoice(String accountId, Address billingAddress) {
    this.accountId = accountId;
    this.billingAddress = billingAddress;
  }

  public void addOrder(Order order) {
    Assert.notNull(orderList, "the order item is NULL");
    this.orderList.add(order);
  }
}
