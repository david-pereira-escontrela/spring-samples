package com.samples.cafe;

import com.samples.cafe.handlers.Barista;
import com.samples.cafe.model.Drink;
import com.samples.cafe.model.OrderItem;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.support.ErrorMessage;

@Configuration
public class AppConfiguration {

  private static final Logger LOGGER = LoggerFactory.getLogger(AppConfiguration.class);

  @Autowired
  private Barista barista;

  @Autowired
  StreamBridge streamBridge;

  @ServiceActivator(inputChannel = "errorChannel")
  public void notifyOrdersError(ErrorMessage errorMessage) {

    Exception exception = (Exception) errorMessage.getPayload();
    LOGGER.error("Order error on [errorChannel]: {} ", exception.getCause().toString());
  }

  @ServiceActivator(inputChannel = "orders.orders-api.errors")
  public void notifyOrdersApiError(ErrorMessage errorMessage) {

    /*
    Message<?> failedMessage =  ((MessagingException) errorMessage.getPayload())
        .getFailedMessage();
    */
    Exception exception = (Exception) errorMessage.getPayload();
    LOGGER.error("Order error on [orders-api.errors]: {} ", exception.getCause().toString());
  }

  @Bean
  public Consumer<OrderItem> hotDrinkRouter() {

    return value -> {

      LOGGER.info(" [hotDrinkRouter] #{} : {}", value.getOrderReference(), value.getDrinkType());
      final Drink drink = barista.prepareHotDrink(value);
      streamBridge.send("preparedDrinks-out-0", drink);
    };
  }

  @Bean
  public Consumer<OrderItem> coldDrinkRouter() {

    return value -> {

      LOGGER.info(" [coldDrinkRouter] #{}: {}", value.getOrderReference(), value.getDrinkType());
      final Drink drink = barista.prepareColdDrink(value);
      streamBridge.send("preparedDrinks-out-0", drink);
    };
  }
}