package com.mindtree.entities;

import com.mindtree.entities.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

@Data
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass()
@NoArgsConstructor
@SuperBuilder
public abstract class BaseEntity {

    // TODO: We will uncomment below details once the security implementation is added.
//    @CreatedBy
//    User createdUserd;
//
//    @LastModifiedBy
//    User modifiedUser;

    @CreatedDate
    LocalDateTime createdDate;

    @LastModifiedDate
    LocalDateTime modifiedDate;

}
