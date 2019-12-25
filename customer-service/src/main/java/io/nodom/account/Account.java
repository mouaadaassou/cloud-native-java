package io.nodom.account;

import io.nodom.BaseEntity;
import io.nodom.address.Address;
import io.nodom.creditcard.CreditCard;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account extends BaseEntity {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String accountNumber;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<CreditCard> creditCards = new HashSet<>();


  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<Address> addresses = new HashSet<>();
}
