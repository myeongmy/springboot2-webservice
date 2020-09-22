package com.myeongmy.book.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}

/*
email을 통해 이미 생성된 사용자인지 처음 가입하는 사용자인지 판단하기 위한 메소드
Optional 클래스: 값이 있으면 객체 리턴, 없으면 false
 */