package io.nodom;

import static org.assertj.core.api.Assertions.assertThat;

import io.nodom.address.Address;
import io.nodom.invoice.Invoice;
import io.nodom.invoice.InvoiceRepository;
import io.nodom.order.LineItem;
import io.nodom.order.Order;
import io.nodom.order.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataMongoTest
class OrderServiceApplicationTests {


  @Autowired
  private InvoiceRepository invoiceRepository;

  @Autowired
  private OrderRepository orderRepository;

  @BeforeEach
  @AfterEach
  public void reset() {
    this.orderRepository.deleteAll();
    this.invoiceRepository.deleteAll();
  }

  @Test
  public void orderTest() {
    // Given:
    Address shippingAddress = new Address("1600 Pennsylvania Ave NW", null, "DC",
        "Washington", "United States", 20500);
    Order order = new Order("12345", shippingAddress);
    order.addLineItem(new LineItem("Best. Cloud. Ever. (T-Shirt, Men's Large)",
        "SKU-24642", 1, 21.99, .06));
    order.addLineItem(new LineItem("Like a BOSH (T-Shirt, Women's Medium)",
        "SKU-34563", 3, 14.99, .06));
    order.addLineItem(new LineItem(
        "We're gonna need a bigger VM (T-Shirt, Women's Small)", "SKU-12464", 4,
        13.99, .06));
    order.addLineItem(new LineItem("cf push awesome (Hoodie, Men's Medium)",
        "SKU-64233", 2, 21.99, .06));
    order = orderRepository.save(order);

    // Then:
    assertThat(order.getOrderId()).isNotNull();
    assertThat(order.getItemLineList().size()).isEqualTo(4);

    Address billingAddress = new Address("875 Howard St", null, "CA",
        "San Francisco", "United States", 94103);
    String accountNumber = "918273465";

    Invoice invoice = new Invoice(accountNumber, billingAddress);
    invoice.addOrder(order);
    invoice = invoiceRepository.save(invoice);
    assertThat(invoice.getOrderList().size()).isEqualTo(1);
  }
}
