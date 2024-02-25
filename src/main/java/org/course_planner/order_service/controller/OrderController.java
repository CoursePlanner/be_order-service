package org.course_planner.order_service.controller;

import lombok.RequiredArgsConstructor;
import org.course_planner.order_service.dto.AllUserOrdersRequest;
import org.course_planner.order_service.dto.AllUserOrdersResponse;
import org.course_planner.order_service.dto.OrderDTO;
import org.course_planner.order_service.dto.Page;
import org.course_planner.order_service.service.OrderService;
import org.course_planner.utils.exceptions.OrderException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDTO> loadOrderById(@RequestHeader(value = "x-order-id", required = false) String orderId,
                                                  @RequestHeader(value = "x-user-id", required = false) String userId) {
        if (orderId == null || orderId.trim().equalsIgnoreCase("")) {
            throw new OrderException("Order ID is a required header!", HttpStatus.BAD_REQUEST);
        }
        if (userId == null || userId.trim().equalsIgnoreCase("")) {
            throw new OrderException("User ID is a required header!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(orderService.loadOrderById(Long.parseLong(orderId), userId), HttpStatus.OK);
    }

    @PostMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllUserOrdersResponse> loadUserOrders(@RequestBody AllUserOrdersRequest request) {
        if (request.getPagination() != null) {
            if (request.getPagination().getDirection() == null) {
                request.getPagination().setDirection(Sort.Direction.DESC);
            }

            if (request.getPagination().getPage() == null) {
                request.getPagination().setPage(0);
            }

            if (request.getPagination().getSize() == null) {
                request.getPagination().setSize(10);
            } else if (request.getPagination().getSize() < 1 || request.getPagination().getSize() > 10) {
                throw new OrderException("Unsupported pagination size!", HttpStatus.BAD_REQUEST);
            }

            if (request.getPagination().getDirection() == null) {
                request.getPagination().setDirection(Sort.Direction.DESC);
            }
        } else {
            request.setPagination(new Page(0, 10, Sort.Direction.DESC));
        }
        return new ResponseEntity<>(orderService.retrieveAllOrders(request), HttpStatus.OK);
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDTO> placeOrder(@RequestBody OrderDTO request) {
        return new ResponseEntity<>(orderService.placeOrder(request), HttpStatus.OK);
    }
}
