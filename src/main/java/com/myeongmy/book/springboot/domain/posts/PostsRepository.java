package com.myeongmy.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    @Query(value = "SELECT p FROM Posts p ORDER BY p.id DESC")   //Spring Data JPA에서 기본적으로 제공하지 않는 메소드는 @Query 어노테이션을 통해 작성
    List<Posts> findAllDesc();
}
