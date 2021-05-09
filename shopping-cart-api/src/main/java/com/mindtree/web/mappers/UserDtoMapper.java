package com.mindtree.web.mappers;

import com.mindtree.entities.user.User;
import com.mindtree.web.dto.user.UserDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface UserDtoMapper {

    UserDto dtoToEntity(User user);

    User entityToDto(UserDto userDto);

    /**
     * Update the UserDto with User.
     *
     * @param userDto
     * @param userEntity
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserEntity(UserDto userDto, @MappingTarget User userEntity);

}
