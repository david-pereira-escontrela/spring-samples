package com.samples.cafe.handlers;

import com.samples.cafe.model.Delivery;
import java.util.function.Consumer;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("deliveryDlqConsumer")
@AllArgsConstructor
public class DeliveryDlqConsumer implements Consumer<Delivery> {

  private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryDlqConsumer.class);

  @Override
  public void accept(Delivery delivery) {

    LOGGER.info("[DeliveryDlqConsumer] delivery received {}.", delivery);
  }
}