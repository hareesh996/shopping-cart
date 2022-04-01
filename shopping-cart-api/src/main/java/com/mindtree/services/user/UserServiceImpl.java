package com.mindtree.services.user;


import com.mindtree.core.exception.BusinessException;
import com.mindtree.dao.user.CartRespository;
import com.mindtree.dao.user.UserRepository;
import com.mindtree.entities.user.Cart;
import com.mindtree.entities.user.User;
import com.mindtree.web.dto.user.Action;
import com.mindtree.web.dto.user.UserDto;
import com.mindtree.web.mappers.UserDtoMapper;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserDtoMapper userDtoMapper;
    private final UserRepository userRepository;
    private final CartRespository cartRespository;

    UserServiceImpl(UserDtoMapper userDtoMapper, UserRepository userRepository, CartRespository cartRespository) {
        this.userDtoMapper = userDtoMapper;
        this.userRepository = userRepository;
        this.cartRespository = cartRespository;
    }

    @Override
    public UserDto getUserDetailsByUserName(String userName) {
        return this.userRepository.findByUserName(userName).map((t) -> this.userDtoMapper.dtoToEntity(t))
                .orElseThrow(() -> {
                	log.error("User Details are not found for the userId [{}]", userName);
                	return new BusinessException("user.detailsNtFound", HttpStatus.NOT_FOUND,  "User Details are not found for the userId [" + userName + "]");
                });
    }

    @Transactional()
    @Override
    public UserDto addOrUpdateUserDetails(UserDto userDto, Action actionType) {
        if (actionType == Action.ADD) {
            return createUser(userDto);
        }
        User user = this.userRepository.findByUserName(userDto.getUserName())
                .orElseThrow(() -> {
                	log.error("User details are not found for the user [{}]", userDto.getUserName());
                	return new BusinessException("user.userNtFound", HttpStatus.BAD_REQUEST, "User details are not found");
                });
        this.userDtoMapper.updateUserEntity(userDto, user);
        user = this.userRepository.save(user);
        return this.userDtoMapper.dtoToEntity(user);
    }

    private UserDto createUser(UserDto userDto) {
        User user = this.userDtoMapper.entityToDto(userDto);
        User updatedUserDetails = this.userRepository.save(user);
        Cart cart = new Cart();
        cart.setUser(user);
        this.cartRespository.save(cart);
        return this.userDtoMapper.dtoToEntity(updatedUserDetails);
    }

}
