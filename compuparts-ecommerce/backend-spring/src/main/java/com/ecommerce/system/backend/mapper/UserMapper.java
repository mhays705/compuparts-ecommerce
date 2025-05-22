package com.ecommerce.system.backend.mapper;

import com.ecommerce.system.backend.dto.user.CreateUserRequest;
import com.ecommerce.system.backend.dto.user.UserResponse;
import com.ecommerce.system.backend.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {



	User toEntity(CreateUserRequest createUserRequest);

	UserResponse toDTO(User user);

	List<UserResponse> toDTOList(List<User> users);
}
