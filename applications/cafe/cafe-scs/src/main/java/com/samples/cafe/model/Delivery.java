package com.samples.cafe.model;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Delivery implements Serializable {

  private static final long serialVersionUID = 1L;

  private List<Drink> deliveredDrinks;

  @Override
  public String toString() {

    return "-- " + deliveredDrinks.stream().map(
            s -> s.getShots() + " " + s.getDrinkType() + " " +
                (s.isIced() ? "cold" : "hot") + " (#" + s.getOrderNumber() + ")")
        .collect(Collectors.joining(", ")) + " --";
  }
}