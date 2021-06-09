package jpabook1.jpashop.test;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
// @Inheritance(strategy = InheritanceType.JOINED)
// @DiscriminatorColumn
@Entity
public class Car {

    @Id @GeneratedValue
    private Long id;
    private String name;
    private int price;
}
