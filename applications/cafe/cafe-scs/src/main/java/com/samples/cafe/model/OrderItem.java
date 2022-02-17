package com.samples.cafe.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderItem implements Serializable {

  private DrinkType drinkType;

  private int shots = 1;

  private boolean iced = false;

  private int orderReference;

  @Override
  public String toString() {

    return "OrderItem { #" + orderReference + " " + (iced ? "iced" : "not iced") + " " + drinkType + " }";
  }
}