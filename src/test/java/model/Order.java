package model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Order {
    private String id;
    private int petId;
    private int quantity;
    private long shipDate;
    private Status status;
    private boolean complete;
}
