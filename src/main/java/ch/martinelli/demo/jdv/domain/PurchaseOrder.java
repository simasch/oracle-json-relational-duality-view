package ch.martinelli.demo.jdv.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of = {"_id"})
@ToString
public class PurchaseOrder {

    private String _id;
    private LocalDateTime orderDate;
    private Customer customer;
    private List<OrderItem> items;
}