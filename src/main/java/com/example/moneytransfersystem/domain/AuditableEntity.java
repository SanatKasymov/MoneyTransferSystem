package com.example.moneytransfersystem.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

@Data
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class AuditableEntity {

    @CreatedDate
    @Column(nullable = false)
    protected Instant createdAt = Instant.now();

    @LastModifiedDate
    @Column(nullable = false)
    protected Instant updatedAt = Instant.now();

}
