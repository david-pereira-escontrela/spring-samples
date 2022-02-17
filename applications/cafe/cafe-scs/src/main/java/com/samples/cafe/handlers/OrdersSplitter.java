package com.samples.cafe.handlers;

import com.samples.cafe.model.Order;
import com.samples.cafe.model.OrderItem;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component("ordersSplitter")
@AllArgsConstructor
public class OrdersSplitter implements Function<Order, List<Message<OrderItem>>> {

  private static final Logger LOGGER = LoggerFactory.getLogger(OrdersSplitter.class);

  @Override
  public List<Message<OrderItem>> apply(Order order) {

    List<Message<OrderItem>> list = new ArrayList<>();
    Message<OrderItem> orderItem1 =
        MessageBuilder.withPayload(order.getOrderItems().get(0))
            .setHeader("type", order.getOrderItems().get(0).isIced() ? "cold" : "hot")
            .build();
    Message<OrderItem> orderItem2 =
        MessageBuilder.withPayload(order.getOrderItems().get(1))
            .setHeader("type", order.getOrderItems().get(0).isIced() ? "cold" : "hot")
            .build();

    list.add(orderItem1);
    list.add(orderItem2);

    LOGGER.info(" [OrdersSplitter] splitting order #{}", order.getReference());
    return list;
  }
}