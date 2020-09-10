package com.myeongmy.book.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter   //선언된 모든 필드의 getter 메소드를 선언해준다.
@RequiredArgsConstructor   //선언된 모든 final 필드가 포함된 생성자를 선언해준다.
public class HelloResponseDto {
    private final String name;
    private final int amount;
}
