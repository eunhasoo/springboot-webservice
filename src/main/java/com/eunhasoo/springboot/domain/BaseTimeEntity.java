package com.eunhasoo.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    // Entity가 생성될 때 시간 자동저장
    @CreatedDate
    private LocalDateTime createdDate;

    // Entity가 수정될 때 시간 자동저장
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
