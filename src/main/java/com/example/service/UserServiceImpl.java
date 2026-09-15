package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.CredentialDTO;
import com.example.dto.UserDTO;
import com.example.exception.UserNotFoundException;
import com.example.models.Credential;
import com.example.models.User;
import com.example.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDTO save(UserDTO userDTO) {
		CredentialDTO credentialDTO=userDTO.getCredentialDTO();
				
		User user = new User();
		BeanUtils.copyProperties(userDTO, user);
		
		Credential credential = new Credential(); 
		BeanUtils.copyProperties(credentialDTO, credential);
		
		
		//set bidirectional relationship
		
		credential.setUser(user);
		user.setCredential(credential);
		// TODO Auto-generated method stub
		
		User dbUser =userRepository.save(user);
		Credential dbCredential= dbUser.getCredential();
		
		UserDTO userResponse = mapToDto(dbUser, dbCredential);
		return userResponse;
	}

	private UserDTO mapToDto(User dbUser, Credential dbCredential) {
		UserDTO userResponse = new UserDTO();
		BeanUtils.copyProperties(dbUser, userResponse);
		
		CredentialDTO credentialResponse= new CredentialDTO();
		BeanUtils.copyProperties(dbCredential, credentialResponse);
		
		userResponse.setCredentialDTO(credentialResponse);
		return userResponse;
	}

	@Override
	public UserDTO findBy(Long userId) {
		// TODO Auto-generated method stub
		Optional<User> optional =userRepository.findById(userId);
		if(optional.isPresent()) {
			User dbUser=optional.get();
			Credential dbCredential=dbUser.getCredential();
			return mapToDto(dbUser, dbCredential);
		} else {
			throw new UserNotFoundException("User not exist in DB");
		}
		
	}

	@Override
	public List<UserDTO> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll()
				.stream()
				.map(obj->mapToDto(obj, obj.getCredential()))
				.toList();
	
	}

	@Override
	public UserDTO update(Long userId, UserDTO userDto) {
		// TODO Auto-generated method stub
		Optional<User> optional = userRepository.findById(userId);
	    if(optional.isPresent()) {
	        User dbUser = optional.get();
	        BeanUtils.copyProperties(userDto, dbUser, "userId", "credentialDTO");
	        Credential dbCredential = dbUser.getCredential();
	        if(dbCredential != null && userDto.getCredentialDTO() != null) {
	            BeanUtils.copyProperties(
	                    userDto.getCredentialDTO(),
	                    dbCredential,
	                    "credentilId", "user"
	            );
	        }
	        User updatedUser = userRepository.save(dbUser);
	        return mapToDto(updatedUser, updatedUser.getCredential());
	    } else {
	        throw new UserNotFoundException("User not exist in DB");
	    }
	}

	@Override
	public UserDTO findByUserName(String userName) {
		Optional<User> optional = userRepository.findByCredentialUsername(Long.valueOf(userName));

	    if(optional.isPresent()) {
	        User dbUser = optional.get();
	        Credential dbCredential = dbUser.getCredential();
	        return mapToDto(dbUser, dbCredential);
	    } else {
	        throw new UserNotFoundException("User not exist in DB");
	    }
	}
	

}
