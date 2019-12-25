package io.nodom;

import static org.assertj.core.api.Assertions.assertThat;

import io.nodom.account.Account;
import io.nodom.address.Address;
import io.nodom.address.AddressType;
import io.nodom.creditcard.CreditCard;
import io.nodom.creditcard.CreditCardType;
import io.nodom.customer.Customer;
import io.nodom.customer.CustomerRepository;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
class CustomerServiceApplicationTests {


  @Autowired
  private CustomerRepository customerRepository;

  @Test
  public void customerTest() {
    // Given:
    CreditCard creditCard = CreditCard.builder().number("DE54123693218745")
        .creditCardType(
            CreditCardType.MASTERCARD).build();
    Set<CreditCard> creditCards = new HashSet<>();
    creditCards.add(creditCard);

    Address address = Address.builder().street1("NeufeldStr")
        .city("Munich")
        .zipCode(452369)
        .state("Bavaria")
        .county("Germany")
        .addressType(AddressType.SHIPPING)
        .build();
    Set<Address> addresses = new HashSet<>();
    addresses.add(address);

    Account account = Account.builder().accountNumber("125CEM8XX2")
        .addresses(addresses)
        .creditCards(creditCards)
        .build();

    Customer customer = Customer.builder().account(account)
        .firstName("andrew")
        .lastName("boot")
        .email("andrewbot.tech@gmail.com")
        .account(account)
        .build();

    // When:
    Customer savedCustomer = this.customerRepository.save(customer);
    Customer foundedCustomer =
        this.customerRepository.findById(savedCustomer.getId()).orElseThrow(() ->
            new RuntimeException("Customer NotUser With Id : " + savedCustomer.getId()));

    // then:
    assertThat(foundedCustomer).isNotNull();
    assertThat(foundedCustomer.getAccount()).isNotNull();
    assertThat(foundedCustomer.getAccount().getAddresses()).isNotNull();
    assertThat(foundedCustomer.getAccount().getCreditCards()).isNotNull();

    this.customerRepository
        .findByEmailContaining(savedCustomer.getEmail())
        .orElseThrow(() -> new RuntimeException(
            "No Customer with The Given Email: " + savedCustomer.getEmail()));
  }
}
