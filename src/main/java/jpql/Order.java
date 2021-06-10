package jpql;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "ORDER_JPQL")
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    private int orderAmount;
}
