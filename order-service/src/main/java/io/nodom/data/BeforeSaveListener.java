package io.nodom.data;

import java.time.LocalDate;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class BeforeSaveListener extends AbstractMongoEventListener<BaseEntity> {

  @Override
  public void onBeforeSave(BeforeSaveEvent<BaseEntity> event) {
    LocalDate now = LocalDate.now();
    if (Objects.isNull(event.getSource().getCreatedAt())) {
      event.getSource().setCreatedAt(now);
    }
    event.getSource().setUpdatedAt(now);
    super.onBeforeSave(event);
  }
}
