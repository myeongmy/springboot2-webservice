package com.myeongmy.book.springboot.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor   //private final 붙은 필드를 포함한 생성자 자동 생성
public enum Role {

    USER("ROLE_USER", "일반 사용자"),
    GUEST("ROLE_GUEST", "손님");

    private final String key;
    private final String title;
}
