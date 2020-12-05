package com.mindtree.dao.user;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.mindtree.entities.user.User;

public interface UserDao extends CrudRepository<User, Long> {
	
	/**
	 * Retrieve the user details based on the user name.
	 * @param userName
	 * @return
	 */
	Optional<User> findByUserName(String userName);

}
