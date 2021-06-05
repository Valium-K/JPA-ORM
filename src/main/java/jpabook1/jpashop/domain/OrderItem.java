package jpabook1.jpashop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class OrderItem extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    /**
     *     더이상 외래키 값으로 직접 연관관계를 맺지 않는다.
     *     객체 자체를 연관관계로 맺는다.
     *     @Column(name = "ORDER_ID")
     *     private Long orderId;
     *
     *     @Column(name = "ITEM_ID")
     *     private Long itemId;
     */

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    // 단방향으로 설계했다
    // 상품 자체로
    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
    public OrderStatus getStatus() {
        return status;
    }
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
