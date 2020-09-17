package com.myeongmy.book.springboot.service.posts;

import com.myeongmy.book.springboot.domain.posts.PostsRepository;
import com.myeongmy.book.springboot.web.dto.PostsListResponseDto;
import com.myeongmy.book.springboot.web.dto.PostsResponseDto;
import com.myeongmy.book.springboot.web.dto.PostsSaveRequestDto;
import com.myeongmy.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.myeongmy.book.springboot.domain.posts.Posts;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public PostsResponseDto findById(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        return new PostsResponseDto(posts);
    }

    @Transactional
    public Long delete(Long id){
        postsRepository.deleteById(id);

        return id;
    }

    @Transactional(readOnly = true)    //조회속도 개선을 위함
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
        .map(PostsListResponseDto::new)
        .collect(Collectors.toList());
    }
}
