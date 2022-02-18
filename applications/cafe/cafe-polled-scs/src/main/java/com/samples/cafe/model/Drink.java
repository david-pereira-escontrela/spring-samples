package com.samples.cafe.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Drink implements Serializable {

  private static final long serialVersionUID = 1L;

  private boolean iced;

  private int shots;

  private DrinkType drinkType;

  private int orderNumber;

  @Override
  public String toString() {

    return "Drink { # " + orderNumber + " " + drinkType + " " + (iced ? "cold" : "hot") + " }";
  }
}