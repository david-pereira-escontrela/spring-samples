package com.samples.cafe.handlers;

import com.samples.cafe.model.DrinkType;
import com.samples.cafe.model.Order;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("ordersSupplier")
@AllArgsConstructor
public class OrdersSupplier implements Supplier<Order> {

  private static final Logger LOGGER = LoggerFactory.getLogger(OrdersSupplier.class);

  private static final AtomicInteger orderCounter = new AtomicInteger();

  public Order get() {

    Order order = new Order(orderCounter.incrementAndGet());
    order.addItem(DrinkType.LATTE, 1, true);
    order.addItem(DrinkType.MOCHA, 2, false);

    LOGGER.info("[OrdersSupplier] sending order #{}", order.getReference());
    return order;
  }
}