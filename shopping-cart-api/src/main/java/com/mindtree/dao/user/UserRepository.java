package com.mindtree.dao.user;

import com.mindtree.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Retrieve the user details based on the user name.
     *
     * @param userName
     * @return
     */
    Optional<User> findByUserName(String userName);

}
