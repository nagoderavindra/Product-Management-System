package com.ravindra.product_management.Service;

import com.ravindra.product_management.DTO.UserDto;

import java.util.List;

public interface UserService {

       UserDto createUser(UserDto userDto);
       List<UserDto> getAllUser();
       UserDto getUserById(Long id);
        void deleteUser(Long id);

}
