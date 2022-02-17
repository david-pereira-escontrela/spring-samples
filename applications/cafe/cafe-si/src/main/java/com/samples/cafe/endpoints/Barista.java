package com.samples.cafe.endpoints;

import static org.slf4j.LoggerFactory.getLogger;

import com.samples.cafe.model.Drink;
import com.samples.cafe.model.OrderItem;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

@Component
public class Barista {

  private static final Logger LOGGER = getLogger(Barista.class);

  private static long DELAY = 5000;

  @ServiceActivator(inputChannel = "hotDrinks", outputChannel = "preparedDrinks", poller = @Poller(fixedDelay = "1000"))
  public Drink prepareHotDrink(final OrderItem orderItem) {

    delay();

    var hotDrink = new Drink(orderItem.isIced(), orderItem.getShots(), orderItem.getDrinkType(), orderItem.getOrderReference());
    LOGGER.info("[ServiceActivator][hotDrinks->preparedDrinks] preparing {}", hotDrink);
    return hotDrink;
  }

  @ServiceActivator(inputChannel = "coldDrinks", outputChannel = "preparedDrinks", poller = @Poller(fixedDelay = "1000"))
  public Drink prepareColdDrink(final OrderItem orderItem) {

    delay();

    var coldDrink = new Drink(orderItem.isIced(), orderItem.getShots(), orderItem.getDrinkType(), orderItem.getOrderReference());
    LOGGER.info("[ServiceActivator][coldDrinks->preparedDrinks] preparing {}", coldDrink);
    return coldDrink;
  }

  private void delay() {
    try {

      Thread.sleep(DELAY);

    } catch (InterruptedException e) {

      Thread.currentThread().interrupt();
    }
  }
}