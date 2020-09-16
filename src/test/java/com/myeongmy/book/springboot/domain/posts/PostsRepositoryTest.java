package com.myeongmy.book.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest    //자동적으로 h2 데이터베이스 실행
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;
    
    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }
    
    @Test
    public void 게시글저장_불러오기(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";
        
        postsRepository.save(Posts.builder()
        .title(title)
        .content(content)
        .author("kp6069@naver.com")
        .build());
        
        //when
        List<Posts> postsList = postsRepository.findAll();
        
        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록(){
        //given
        LocalDateTime now = LocalDateTime.of(2020,1,1,0,0, 0);
        postsRepository.save(Posts.builder()
        .title("title")
        .content("content")
        .author("author")
        .build());

        //when
        List<Posts> postsList = postsRepository.findAll();
        System.out.println(">>>>>>> createdDate="+postsList.get(0).getCreatedTime()+", modifiedDate="+postsList.get(0).getModifiedTime());

        assertThat(postsList.get(0).getCreatedTime()).isAfter(now);
        assertThat(postsList.get(0).getModifiedTime()).isAfter(now);
    }
}

/*
@After: JUnit에서 단위 테스트가 끝날 때마다 수행되는 메소드 지정. 메모리 삭제를 위함.
@PostsRepository.save : 테이블 posts에 insert/update 쿼리 수행(테이블에 id 값이 있다면 update, 없다면 insert)
@PostsRepository.findAll: 테이블 posts에 있는 모든 데이터를 조회해오는 메소드
 */
