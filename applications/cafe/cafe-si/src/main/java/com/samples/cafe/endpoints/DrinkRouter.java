package com.samples.cafe.endpoints;

import com.samples.cafe.model.OrderItem;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Router;

@MessageEndpoint
public class DrinkRouter {

  @Router(inputChannel = "drinks", defaultOutputChannel = "coldDrinks")
  public String resolveOrderItemChannel(final OrderItem orderItem) {

    return (orderItem.isIced()) ? "coldDrinks" : "hotDrinks";
  }
}