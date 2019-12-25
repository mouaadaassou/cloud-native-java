package io.nodom.invoice;

import io.nodom.address.Address;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InvoiceRepository extends MongoRepository<Invoice, String> {

  Optional<Invoice> findByBillingAddress(Address billingAddress);
}
