package com.aarnasolutions.service;

import com.aarnasolutions.domain.User;

public interface UserService {

	User getUserById(Long id);
	
	User getUserByMobileNo(String mobileNo);
	
	User createUser(User user);

    User updateUser(User user);

    void deleteUser(Long id);
    
}
