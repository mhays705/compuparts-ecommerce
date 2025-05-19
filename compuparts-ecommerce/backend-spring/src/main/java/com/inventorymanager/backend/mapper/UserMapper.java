package com.inventorymanager.backend.mapper;

import com.inventorymanager.backend.dto.user.CreateUserRequest;
import com.inventorymanager.backend.dto.user.UserResponse;
import com.inventorymanager.backend.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {



	User toEntity(CreateUserRequest createUserRequest);

	UserResponse toDTO(User user);

	List<UserResponse> toDTOList(List<User> users);
}
