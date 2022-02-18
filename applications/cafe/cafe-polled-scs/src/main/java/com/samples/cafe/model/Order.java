package com.samples.cafe.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Order implements Serializable {

  private int reference;

  private final List<OrderItem> orderItems = new ArrayList<>();

  public Order(Integer ref) {

    reference = ref;
  }

  public void addItem(final DrinkType type, int shots, boolean isIced) {

    orderItems.add(new OrderItem(type, shots, isIced, this.reference));
  }

  @Override
  public String toString() {

    return "Order { #" + reference + " items:" + orderItems.size() + " }";
  }
}