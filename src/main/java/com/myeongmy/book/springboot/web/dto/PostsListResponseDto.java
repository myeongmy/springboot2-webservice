package com.myeongmy.book.springboot.web.dto;

import com.myeongmy.book.springboot.domain.posts.Posts;
import lombok.Getter;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String author;
    private String content;
    private LocalDateTime modifiedDate;

    public PostsListResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.content = entity.getContent();
        this.modifiedDate = entity.getModifiedTime();
    }
}
