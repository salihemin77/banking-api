package com.example.banking_api.controller;

import com.example.banking_api.dto.UserDTO;
import com.example.banking_api.entity.User;
import com.example.banking_api.mapper.UserMapper;
import com.example.banking_api.service.UserService;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class UserController {
    private UserService userService;
    private UserMapper userMapper;

    public UserController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }
@GetMapping("/users")
    public List<UserDTO> getUsers(){
        return userService.findAll().stream().map(userMapper::toDTO).collect(toList());
}
@GetMapping("/users/{id}")
    public UserDTO getUser(@PathVariable Integer id){
      User user=userService.findById(id);
      return  userMapper.toDTO(user);
}
@PostMapping("users")
    public UserDTO createUser(@Valid @RequestBody User user){
        User saveuser=userService.save(user);
        return  userMapper.toDTO(saveuser);
}
@DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Integer id){
        userService.deleteById(id);
}

}
