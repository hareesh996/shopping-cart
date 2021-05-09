package com.mindtree.web.dto.user;

import lombok.Data;

@Data
public class UserDto {
    private Long userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String middleName;

}
