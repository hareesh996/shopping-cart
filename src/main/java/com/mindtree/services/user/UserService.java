package com.mindtree.services.user;

import com.mindtree.web.dto.user.Action;
import com.mindtree.web.dto.user.UserDto;

public interface UserService {
	
	/**
	 * Get the logged in user details for the application.
	 * @return
	 */
	public UserDto getUserDetails(Long userId);
	
	/**
	 * Update insert the User details based on the action type
	 * @param userDto
	 * @return
	 */
	public UserDto addOrUpdateUserDetails(UserDto userDto, Action actionType);

}
