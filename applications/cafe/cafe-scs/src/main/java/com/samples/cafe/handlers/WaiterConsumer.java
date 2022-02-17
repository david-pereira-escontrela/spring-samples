package com.samples.cafe.handlers;

import com.samples.cafe.model.Delivery;
import java.util.function.Consumer;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("waiterConsumer")
@AllArgsConstructor
public class WaiterConsumer implements Consumer<Delivery> {

  private static final Logger LOGGER = LoggerFactory.getLogger(WaiterConsumer.class);

  @Override
  public void accept(Delivery delivery) {

    LOGGER.info("[WaiterConsumer] delivery received {}.", delivery);
  }
}