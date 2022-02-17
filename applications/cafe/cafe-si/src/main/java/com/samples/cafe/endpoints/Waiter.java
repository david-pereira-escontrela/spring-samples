package com.samples.cafe.endpoints;

import com.samples.cafe.model.Delivery;
import com.samples.cafe.model.Drink;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.Aggregator;
import org.springframework.integration.annotation.CorrelationStrategy;
import org.springframework.integration.annotation.MessageEndpoint;

@MessageEndpoint
public class Waiter {

  private static final Logger LOGGER = LoggerFactory.getLogger(Waiter.class);

  @Aggregator(inputChannel = "preparedDrinks", outputChannel = "deliveries")
  public Delivery prepareDelivery(final List<Drink> drinks) {

    LOGGER.info("[Waiter][preparedDrinks->deliveries] serving {} drinks.", drinks.size());
    return new Delivery(drinks);
  }

  @CorrelationStrategy
  public int correlateByOrderNumber(final Drink drink) {

    return drink.getOrderNumber();
  }

}