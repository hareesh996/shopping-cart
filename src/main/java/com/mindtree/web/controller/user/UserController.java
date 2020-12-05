package com.mindtree.web.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.services.user.UserService;
import com.mindtree.web.dto.Response;
import com.mindtree.web.dto.user.Action;
import com.mindtree.web.dto.user.UserDto;

@RestController("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/{userId}")
	public UserDto getUserDetails(@PathVariable("userId")Long userId){
		return this.userService.getUserDetails(userId);
	}
	
	@PutMapping("/add")
	public ResponseEntity<Response<UserDto>> addUser(@RequestBody UserDto userDto) {
		UserDto createdUser = userService.addOrUpdateUserDetails(userDto, Action.ADD);
		return Response.<UserDto>builder().ok(createdUser);
	}
	
	
	@PostMapping("/update")
	public UserDto updateUser(@RequestBody UserDto userDto) {
		return userService.addOrUpdateUserDetails(userDto, Action.UPDATE);
	}
	
}
