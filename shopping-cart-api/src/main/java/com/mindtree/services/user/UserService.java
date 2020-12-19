package com.mindtree.services.user;

import com.mindtree.web.dto.user.Action;
import com.mindtree.web.dto.user.UserDto;

public interface UserService {

	/**
     * Get the the user details based on the logged in username
	 * @param userName
     * @return
     */
	public UserDto getUserDetailsByUserName(String userName);
	
	/**
	 * Update insert the User details based on the action type
	 * @param userDto
	 * @return
	 */
	public UserDto addOrUpdateUserDetails(UserDto userDto, Action actionType);

}
