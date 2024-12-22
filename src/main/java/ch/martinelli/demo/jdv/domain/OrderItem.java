package ch.martinelli.demo.jdv.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of = {"_id"})
@ToString
public class OrderItem {

    private Long _id;
    private int quantity;
    private Product product;
}