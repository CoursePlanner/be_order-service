package org.course_planner.order_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.course_planner.order_service.dto.OrderDTO;
import org.course_planner.order_service.dto.OrderStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_details_entity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsEntity {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;
    @Column(name = "product_id", nullable = false, updatable = false)
    private Long productId;
    @Column(name = "product_name", nullable = false, updatable = false)
    private String productName;
    @Column(name = "product_price", nullable = false, updatable = false)
    private Double productPrice;
    @Column(name = "product_qty", nullable = false, updatable = false)
    private Integer quantity;
    @Column(name = "order_placed_on", nullable = false, updatable = false)
    private LocalDateTime purchasedOn;
    @Column(name = "order_status")
    private String orderStatus;
    @Column(name = "payment_id")
    private Long paymentId;
    @Column(name = "user_id", nullable = false, updatable = false)
    private String userId;

    public OrderDetailsEntity(OrderDTO orderDTO) {
        this.id = orderDTO.getOrderId();
        this.productId = orderDTO.getProductId();
        this.productName = orderDTO.getProductName();
        this.productPrice = orderDTO.getProductPrice();
        this.quantity = orderDTO.getQuantity();
        this.purchasedOn = orderDTO.getPurchasedOn();
        this.orderStatus = orderDTO.getOrderStatus() != null
                ? orderDTO.getOrderStatus().name() : OrderStatus.PAYMENT_PENDING.name();
        this.paymentId = orderDTO.getPaymentId();
        this.userId = orderDTO.getUserId();
    }
}
