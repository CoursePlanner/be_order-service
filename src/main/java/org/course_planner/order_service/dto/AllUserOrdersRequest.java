package org.course_planner.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllUserOrdersRequest {
    private String userId;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private Page pagination;
}
