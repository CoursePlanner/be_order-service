package org.course_planner.order_service.repository;

import org.course_planner.order_service.entity.OrderDetailsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderDetailsEntity, Long> {
    Page<OrderDetailsEntity> findAllByUserId(String userId, Pageable pageable);

    Optional<OrderDetailsEntity> findByIdAndUserId(Long orderId, String userId);
}
