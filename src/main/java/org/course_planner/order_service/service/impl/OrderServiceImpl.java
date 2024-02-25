package org.course_planner.order_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.course_planner.order_service.dto.*;
import org.course_planner.order_service.entity.OrderDetailsEntity;
import org.course_planner.order_service.repository.OrderRepository;
import org.course_planner.order_service.service.OrderService;
import org.course_planner.utils.exceptions.OrderException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public OrderDTO placeOrder(OrderDTO request) {
        OrderDetailsEntity entity = new OrderDetailsEntity(request);
        entity.setId(null);
        entity.setOrderStatus(OrderStatus.CREATED.name());
        entity.setPurchasedOn(LocalDateTime.now());
        return new OrderDTO(orderRepository.save(entity));
    }

    @Override
    public void updateOrder(OrderDTO request) {
        boolean isUpdated = false;
        OrderDetailsEntity entity = orderRepository.findById(request.getOrderId()).orElse(null);
        if (entity != null) {
            if (request.getOrderStatus() != null && !entity.getOrderStatus().equalsIgnoreCase(request.getOrderStatus().name())) {
                entity.setOrderStatus(request.getOrderStatus().name());
                isUpdated = true;
            }

            if (request.getPaymentId() != null && request.getPaymentId() > 0) {
                entity.setPaymentId(request.getPaymentId());
                isUpdated = true;
            }

            if (isUpdated) {
                orderRepository.save(entity);
            }
        }
    }

    @Override
    public AllUserOrdersResponse retrieveAllOrders(AllUserOrdersRequest request) {
        AllUserOrdersResponse response = new AllUserOrdersResponse(new LinkedList<>(), null);
        Pageable pageable = PageRequest.of(request.getPagination().getPage(), request.getPagination().getSize(),
                Sort.by(request.getPagination().getDirection(), "purchasedOn"));
        org.springframework.data.domain.Page<OrderDetailsEntity> userOrders = orderRepository.findAllByUserId(request.getUserId(), pageable);
        response.getOrders().addAll(userOrders.get().map(OrderDTO::new).toList());
        response.setPagination(new Page(userOrders));
        return response;
    }

    @Override
    public OrderDTO loadOrderById(Long orderId, String userId) {
        Optional<OrderDetailsEntity> entity = orderRepository.findByIdAndUserId(orderId, userId);
        if (entity.isEmpty()) {
            throw new OrderException("Order does not exist!", HttpStatus.BAD_REQUEST);
        }
        return new OrderDTO(entity.get());
    }
}
