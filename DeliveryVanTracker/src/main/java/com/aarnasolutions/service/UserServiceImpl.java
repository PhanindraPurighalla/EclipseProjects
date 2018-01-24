package com.aarnasolutions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aarnasolutions.domain.User;
import com.aarnasolutions.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User getUserById(Long id) {
		return userRepository.findOne(id);
	}
	
	@Override
	public User getUserByMobileNo(String mobileNo) {
		return userRepository.findByMobileNo(mobileNo).get(0);
	}
	
	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(Long id) {
		User user = getUserById(id);
		if (user != null) {
			userRepository.delete(id);
		}
	}


}
