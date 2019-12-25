package io.nodom.address;

import io.nodom.data.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Address extends BaseEntity {

  private String street1;
  private String street2;
  private String state;
  private String city;
  private String country;
  private Integer zipCode;
  private AddressType addressType;

  public Address(String street1, String street2, String state, String city,
      String country, Integer zipCode) {
    this.street1 = street1;
    this.street2 = street2;
    this.state = state;
    this.city = city;
    this.country = country;
    this.zipCode = zipCode;
  }
}
