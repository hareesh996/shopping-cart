package com.mindtree.services.user;


import com.mindtree.core.exception.BusinessException;
import com.mindtree.dao.user.UserDao;
import com.mindtree.entities.user.User;
import com.mindtree.web.dto.user.Action;
import com.mindtree.web.dto.user.UserDto;
import com.mindtree.web.mappers.UserDtoMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserDtoMapper userDtoMapper;
	private final UserDao userDao;
	
	UserServiceImpl(UserDtoMapper userDtoMapper, UserDao userDao){
		this.userDtoMapper = userDtoMapper;
		this.userDao = userDao;
	}
	
	@Override
	public UserDto getUserDetailsByUserName(String userName) {
		return this.userDao.findByUserName(userName).map((t) -> this.userDtoMapper.dtoToEntity(t) )
			.orElseThrow(() -> new BusinessException("user.detailsNtFound", "User Details are not found for the userId ["+ userName +"]"));
	}

	@Override
	public UserDto addOrUpdateUserDetails(UserDto userDto, Action actionType) {
		if(actionType == Action.ADD) {
			User updatedUserDetails = this.userDao.save(this.userDtoMapper.entityToDto(userDto));
			return this.userDtoMapper.dtoToEntity(updatedUserDetails);
		}
		User user = this.userDao.findByUserName(userDto.getUserName())
		.orElseThrow( () -> new BusinessException("user.userNtFound","User details are not found"));
		this.userDtoMapper.updateUserEntity(userDto, user);
		user = this.userDao.save(user);
		return this.userDtoMapper.dtoToEntity(user);
	}

}
