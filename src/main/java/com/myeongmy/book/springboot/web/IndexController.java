package com.myeongmy.book.springboot.web;

import com.myeongmy.book.springboot.service.posts.PostsService;
import com.myeongmy.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("posts", postsService.findAllDesc());

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){

        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto responseDto = postsService.findById(id);
        model.addAttribute("post", responseDto);

        return "posts-update";
    }
}

/*
Model: 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다.
 */