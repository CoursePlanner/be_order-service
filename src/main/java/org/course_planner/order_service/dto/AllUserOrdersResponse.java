package org.course_planner.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllUserOrdersResponse {
    private List<OrderDTO> orders;
    private Page pagination;
}
