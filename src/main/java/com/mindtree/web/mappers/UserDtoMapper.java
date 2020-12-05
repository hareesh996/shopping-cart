package com.mindtree.web.mappers;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.mindtree.entities.user.User;
import com.mindtree.web.dto.user.UserDto;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserDtoMapper {
		
	UserDto dtoToEntity(User user);

	User entityToDto(UserDto userDto);
	
	/**
	 * Update the UserDto with User.
	 * @param userDto
	 * @param userEntity
	 */
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateUserEntity(UserDto userDto, @MappingTarget User userEntity);
	
}
