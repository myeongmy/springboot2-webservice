package com.myeongmy.book.springboot.domain.posts;

import com.myeongmy.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    private String author;

    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}

/*
Posts 클래스는 Entity 클래스라고 불린다. 테이블과 매칭되는 클래스
JPA 관련 어노테이션
@Entity: 테이블과 링크될 클래스임을 알려줌.
@Id: 해당 테이블의 pk 필드를 나타낸다.
@GeneratedValue: pk 생성 규칙을 나타낸다.(springboot 2.0 이상에서는 generatedtype identity 속성을 추가해야 autoincrement가 된다)
@Column: 이 어노테이션은 굳이 사용하지 않아도 모두 필드가 column으로 선언된다. 단지, 기본값 외에 다른 값을 이용하고 싶은 경우 사용

lombok 관련 어노테이션
@Getter: 모든 클래스의 getter 메소드 생성
@NoArgsConstructor: 기본 생성자 생성
@Builder: 해당 클래스의 빌더 패턴 생성.
 */
