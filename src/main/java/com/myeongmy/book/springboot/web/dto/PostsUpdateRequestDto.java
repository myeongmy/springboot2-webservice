package com.myeongmy.book.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private String title;
    private String content;

    @Builder
    PostsUpdateRequestDto(String title, String content){
        this.title = title;
        this.content = content;
    }
}
