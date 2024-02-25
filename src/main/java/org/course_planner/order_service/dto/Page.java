package org.course_planner.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page {
    private Integer page;
    private Integer size;
    private Long totalRecords;
    private Long totalPages;
    private Sort.Direction direction;

    public Page(Integer page, Integer size, Sort.Direction direction) {
        this.page = page;
        this.size = size;
        this.direction = direction;
    }

    public Page(org.springframework.data.domain.Page<?> pageable) {
        this.page = pageable.getNumber();
        this.size = pageable.getSize();
        this.totalPages = (long) pageable.getTotalPages();
        this.totalRecords = pageable.getTotalElements();
    }
}
