package ch.martinelli.demo.jdv.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of = {"_id"})
@ToString
public class Product {

    private Long _id;
    private String name;
    private double price;
}
