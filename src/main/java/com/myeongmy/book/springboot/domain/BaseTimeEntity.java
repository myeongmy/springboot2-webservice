package com.myeongmy.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {
    @CreatedDate
    private LocalDateTime createdTime;
    @LastModifiedDate
    private LocalDateTime modifiedTime;

}

/*
@MappedSuperClass : 해당 클래스를 상속할 경우 필드들을 컬럼으로 인식하도록 한다.
@EntityListeners : 해당 클래스에 auditing 기능을 포함시킨다.
@CreatedDate : 자동으로 엔티티가 생성된 시간이 저장된다.
@ModifiedDate: 자동으로 엔티티가 수정된 시간이 저장된다.
 */