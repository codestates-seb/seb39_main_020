package com.codestates.BocamDogam.course.entity;

import lombok.Getter;

public enum CourseCategory {
    // 드롭다운으로 표시하여 선택할 수 있도록
    FRONT_END("프론트엔드"),
    BACK_END("백엔드"),
    DEV_OPS("데브옵스"),
    APPLICATION_DEVELOPMENT("앱 개발"),
    BLOCKCHAIN("블록체인"),
    DATA_ANALYTICS("데이터 분석"),
    ARTIFICIAL_INTELLIGENCE("인공지능"),
    INFO_SECURITY("정보보안"),
    DATABASE_ENGINEER("데이터베이스 엔지니어"),
    PRODUCT_MANAGEMENT("프로덕트 매니저"),
    MARKETING("마케팅"),
    INTERNET_OF_THINGS("사물인터넷"),
    ECT("기타");

    @Getter
    private final String category;

    CourseCategory(String category) {
        this.category = category;
    }
}
