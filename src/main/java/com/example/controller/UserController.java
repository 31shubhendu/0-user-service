package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.UserDTO;
import com.example.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public UserDTO createUser(@Valid @RequestBody UserDTO userDto) {
		return userService.save(userDto);
	}
	
	@GetMapping("/{userId}")
	public UserDTO fetchUser(@PathVariable Long userId) {
		return userService.findBy(userId);
	}
	
	@GetMapping
	public List<UserDTO> fetchAllUsers() {
	    return userService.findAll();
	}
	
	@PutMapping("/{userId}")
    public UserDTO updateUser(@PathVariable Long userId, @RequestBody UserDTO userDto) {
        return userService.update(userId, userDto);
    }
	
	@GetMapping("/username/{userName}")
	public UserDTO fetchUserByUserName(@PathVariable String userName) {
	    return userService.findByUserName(userName);
	}
}
