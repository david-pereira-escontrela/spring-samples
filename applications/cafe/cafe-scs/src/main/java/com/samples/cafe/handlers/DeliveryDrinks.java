package com.samples.cafe.handlers;

import com.samples.cafe.model.Delivery;
import com.samples.cafe.model.Drink;
import java.util.List;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("deliveryDrinks")
public class DeliveryDrinks implements Function<List<Drink>, Delivery> {

  private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryDrinks.class);

  @Override
  public Delivery apply(List<Drink> drinks) {

    LOGGER.info("[DeliveryDrinks] preparing drinks {}", drinks.size());
    return new Delivery(drinks);
  }
}