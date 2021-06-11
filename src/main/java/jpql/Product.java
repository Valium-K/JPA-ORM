package jpql;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "PRODUCT_JPQL")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue
    @Column(name = "PRODUCT_ID")
    private Long id;
    private String name;
    private int price;
    private int stockAmount;
}
