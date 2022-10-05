package com.codestates.BocamDogam.course.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

public enum ApplyStatus {
    APPLY_ACTIVE("모집 중"),
    APPLY_DEADLINE("마감 임박"),
    APPLY_END("모집 종료"),
    APPLY_SCHEDULED("모집 예정");

    @Getter
    private String status;

    ApplyStatus(String status) {
        this.status = status;
    }
}
