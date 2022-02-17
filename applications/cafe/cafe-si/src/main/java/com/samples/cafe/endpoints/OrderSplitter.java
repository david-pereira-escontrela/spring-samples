package com.samples.cafe.endpoints;

import com.samples.cafe.model.Order;
import com.samples.cafe.model.OrderItem;
import java.util.List;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Splitter;

@MessageEndpoint
public class OrderSplitter {

  @Splitter(inputChannel = "orders", outputChannel = "drinks")
  public List<OrderItem> split(final Order order) {

    return order.getOrderItems();
  }
}
