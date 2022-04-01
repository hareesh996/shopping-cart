package com.mindtree.web.controller.user;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.services.user.UserService;
import com.mindtree.web.dto.Response;
import com.mindtree.web.dto.user.Action;
import com.mindtree.web.dto.user.UserDto;

import lombok.extern.slf4j.Slf4j;

@RestController()
@RequestMapping("users")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<UserDto>> getUserDetails(@PathVariable("userName") String userName) {
        UserDto userDto = this.userService.getUserDetailsByUserName(userName);
        if(log.isInfoEnabled()) {
        	log.info("We found the user details for the provided username [{}]", userName);
        }
        return Response.<UserDto>builder().ok(userDto);
    }

    @PutMapping(path = "add-user", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<UserDto>> addUser(@RequestBody UserDto userDto) {
        UserDto createdUser = userService.addOrUpdateUserDetails(userDto, Action.ADD);
        if(log.isInfoEnabled()) {
        	log.info("Successfully User [{}] is added to the system", userDto);
        }
        return Response.<UserDto>builder().ok(createdUser);
    }

    @PostMapping(path = "update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<UserDto>> updateUser(@RequestBody UserDto userDto) {
        UserDto updatedDto = userService.addOrUpdateUserDetails(userDto, Action.UPDATE);
        if(log.isInfoEnabled()) {
        	log.info("User [{}] is updated as per the request [{}]", userDto.getUserId(), userDto);
        }
        return Response.<UserDto>builder().ok(updatedDto);
    }

}
