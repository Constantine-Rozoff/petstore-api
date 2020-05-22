package model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Order {
    private String id;
    private long petId;
    private int quantity;
    private long shipDate;
    private OrderStatus status;
    private boolean complete;
}
