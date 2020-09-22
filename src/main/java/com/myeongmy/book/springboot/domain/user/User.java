package com.myeongmy.book.springboot.domain.user;

import com.myeongmy.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role){
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture){
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}

/*
Enum 클래스 : 클래스처럼 보이게 하는 상수
서로 관련 있는 상수들을 모아 심볼릭한 명칭의 집합으로 정의한 것
@Enumerated(EnumType.STRING) : JPA로 데이터베이스로 저장할 때 Enum 값을 어떤 형태로 저장할지 결정
기본값이 int로 된 숫자인데, 숫자로 저장되면 데이터베이스로 확인할 때 그 값이 무슨 코드를 의미하는지 알 수가 없기 때문에, 문자열로 지정

 */
