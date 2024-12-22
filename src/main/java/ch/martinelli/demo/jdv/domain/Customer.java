package ch.martinelli.demo.jdv.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of = {"_id"})
@ToString
public class Customer {

    private Long _id;
    private String firstName;
    private String lastName;
    private String street;
    private String postalCode;
    private String city;
}
