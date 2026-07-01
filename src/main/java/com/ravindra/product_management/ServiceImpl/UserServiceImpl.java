package com.ravindra.product_management.ServiceImpl;

import com.ravindra.product_management.DTO.UserDto;
import com.ravindra.product_management.Entity.User;
import com.ravindra.product_management.Repository.UserRepository;
import com.ravindra.product_management.Service.UserService;
import com.ravindra.product_management.exception.EmailAlredyExistException;
import com.ravindra.product_management.exception.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {


      private final PasswordEncoder passwordEncoder;
      private final UserRepository userRepository;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {

       if(userRepository.existsByEmail(userDto.getEmail())){
               throw new  EmailAlredyExistException ("Email already exist");
           }
            User user = new User();
            user.setFullName(userDto.getFullName());
            user.setEmail(userDto.getEmail());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setRole(userDto.getRole());
            User saved = userRepository.save(user);
            userDto.setUserId(saved.getUserId());
            return userDto;
    }

    @Override
    public List<UserDto> getAllUser() {
        return userRepository.findAll()
                .stream().map(user -> {
                     UserDto dto = new UserDto();
                             dto.setUserId(user.getUserId());
                             dto.setFullName(user.getFullName());
                             dto.setEmail(user.getEmail());
                             return dto;
                })
                               .toList();

    }

    @Override
    public UserDto getUserById(Long id) {

        User user =  userRepository.findById(id)
                 .orElseThrow(() -> new ResourceNotFoundException("user not found"));

        UserDto dto = new UserDto();
        dto.setUserId(user.getUserId());
        dto.setFullName(user.getFullName());
        dto.setEmail(user.getEmail());
        return dto;
    }

    @Override
    public void deleteUser(Long id) {

           User user =  userRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("user Not found"));
                    userRepository.delete(user);

    }
}
