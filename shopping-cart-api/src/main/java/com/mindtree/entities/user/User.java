package com.mindtree.entities.user;

import com.mindtree.entities.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "app_user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(generator = "user_id_seq")
    // TODO: This should be moved to the DB.
    @SequenceGenerator(initialValue = 000001, name = "user_id_seq", sequenceName = "user_id_generator")
    private Long userId;

    private String userName;
    private String firstName;
    private String lastName;
    private String middleName;

}
