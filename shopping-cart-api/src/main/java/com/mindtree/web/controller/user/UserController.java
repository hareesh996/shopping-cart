package com.mindtree.web.controller.user;

import com.mindtree.services.user.UserService;
import com.mindtree.web.dto.Response;
import com.mindtree.web.dto.user.Action;
import com.mindtree.web.dto.user.CartDto;
import com.mindtree.web.dto.user.CartRequest;
import com.mindtree.web.dto.user.UserDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<UserDto>> getUserDetails(@PathVariable("userName") String userName) {
        UserDto userDto = this.userService.getUserDetailsByUserName(userName);
        return Response.<UserDto>builder().ok(userDto);
    }

    @PutMapping(path = "add-user", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<UserDto>> addUser(@RequestBody UserDto userDto) {
        UserDto createdUser = userService.addOrUpdateUserDetails(userDto, Action.ADD);
        return Response.<UserDto>builder().ok(createdUser);
    }

    @PostMapping(path = "update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<UserDto>> updateUser(@RequestBody UserDto userDto) {
        UserDto updatedDto = userService.addOrUpdateUserDetails(userDto, Action.UPDATE);
        return Response.<UserDto>builder().ok(updatedDto);
    }

}
