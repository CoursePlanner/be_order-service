package org.course_planner.order_service.service;

import org.course_planner.order_service.dto.AllUserOrdersRequest;
import org.course_planner.order_service.dto.AllUserOrdersResponse;
import org.course_planner.order_service.dto.OrderDTO;

public interface OrderService {
    OrderDTO placeOrder(OrderDTO request);

    void updateOrder(OrderDTO request);

    AllUserOrdersResponse retrieveAllOrders(AllUserOrdersRequest request);

    OrderDTO loadOrderById(Long orderId, String userId);
}
