package com.myeongmy.book.springboot.web;

import com.myeongmy.book.springboot.service.posts.PostsService;
import com.myeongmy.book.springboot.web.dto.PostsResponseDto;
import com.myeongmy.book.springboot.web.dto.PostsSaveRequestDto;
import com.myeongmy.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor //생성자를 통해 빈(Bean) 객체를 주입받음(lombok 어노테이션) : final 키워드 붙은 모든 필드를 인자값으로 하는 생성자 생성
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){

        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);

        return id;
    }
}
