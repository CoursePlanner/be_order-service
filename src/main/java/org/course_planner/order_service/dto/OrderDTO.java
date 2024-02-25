package org.course_planner.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.course_planner.order_service.entity.OrderDetailsEntity;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long orderId;
    private Long productId;
    private String productName;
    private Double productPrice;
    private Integer quantity;
    private LocalDateTime purchasedOn;
    private OrderStatus orderStatus;
    private Long paymentId;
    private String userId;

    public OrderDTO(OrderDetailsEntity entity) {
        this.orderId = entity.getId();
        this.productId = entity.getProductId();
        this.productName = entity.getProductName();
        this.productPrice = entity.getProductPrice();
        this.quantity = entity.getQuantity();
        this.purchasedOn = entity.getPurchasedOn();
        this.orderStatus = entity.getOrderStatus() != null
                ? OrderStatus.valueOf(entity.getOrderStatus()) : OrderStatus.PAYMENT_PENDING;
        this.paymentId = entity.getPaymentId();
        this.userId = entity.getUserId();
    }
}
