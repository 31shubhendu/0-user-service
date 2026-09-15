package com.example.service;

import java.util.List;

import com.example.dto.UserDTO;

public interface UserService {
	UserDTO save(UserDTO userDTO);
	
	//Write by YourSelf
	UserDTO findBy(Long userId);
	List<UserDTO> findAll();
	UserDTO update(Long userId, UserDTO userDto);
	UserDTO findByUserName(String userName);
}
