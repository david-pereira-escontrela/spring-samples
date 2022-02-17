package com.samples.cafe;

import static org.slf4j.LoggerFactory.getLogger;

import com.samples.cafe.endpoints.Cafe;
import com.samples.cafe.model.Delivery;
import com.samples.cafe.model.DrinkType;
import com.samples.cafe.model.Order;
import com.samples.cafe.model.OrderItem;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class AppConfiguration {

  private static final Logger LOGGER = getLogger(AppConfiguration.class);

  @Autowired
  private Cafe cafeGateway;

  private final AtomicInteger orderCounter = new AtomicInteger();

  @Scheduled(fixedRate = 50000, initialDelay = 5000)
  public void produceCoffeeOrderScheduler() {

    LOGGER.info("[Scheduled] new cafe order # {}", orderCounter.incrementAndGet());

    Order order = new Order(orderCounter.get());
    order.addItem(DrinkType.LATTE, 1, true);
    order.addItem(DrinkType.MOCHA, 2, false);

    cafeGateway.placeOrder(order);
  }

  @ServiceActivator(inputChannel = "orders")
  public void notifyOrders(Message<Order> message) {

    LOGGER.info("[ServiceActivator][->orders] {}", message.getPayload());
  }

  @ServiceActivator(inputChannel = "drinks")
  public void notifyDrinks(Message<OrderItem> drink) {

    LOGGER.info("[ServiceActivator][->drinks] {}", drink.getPayload());
  }

  @ServiceActivator(inputChannel = "deliveries")
  public void notifyDelivery(Delivery deliveries) {

    LOGGER.info(" [ServiceActivator][->deliveries] {}", deliveries);
  }

/*
  @Bean
  public IntegrationFlow myFlow() {
    return IntegrationFlows
        .from(this.fileReadingMessageSource(), c -> c.poller(Pollers.fixedDelay(1000)))
        .channel(this.inputChannel())
        .trigger(System.out::println)
        .channel(this.outputChannel()).get();
  }
*/
}