package com.ravindra.product_management.Controller;

import com.ravindra.product_management.DTO.UserDto;
import com.ravindra.product_management.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/users")
public class UserController {

       private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

      @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto)
    {
        return ResponseEntity.ok(userService.createUser(userDto));


    }
      @GetMapping
       public ResponseEntity<List<UserDto>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUser());

    }
     @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id)
    {
        return  ResponseEntity.ok(userService.getUserById(id));

    }
     public ResponseEntity<String>deleteUser(Long id){
        return ResponseEntity.ok("user deleted successfully");
     }




}
