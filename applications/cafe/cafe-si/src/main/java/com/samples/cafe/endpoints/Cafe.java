package com.samples.cafe.endpoints;

import com.samples.cafe.model.Order;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface Cafe {

  @Gateway(requestChannel = "orders")
  void placeOrder(Order order);

}