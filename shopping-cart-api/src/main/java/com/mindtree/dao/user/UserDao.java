package com.mindtree.dao.user;

import com.mindtree.entities.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserDao extends CrudRepository<User, Long> {
	
	/**
	 * Retrieve the user details based on the user name.
	 * @param userName
	 * @return
	 */
	Optional<User> findByUserName(String userName);

}
