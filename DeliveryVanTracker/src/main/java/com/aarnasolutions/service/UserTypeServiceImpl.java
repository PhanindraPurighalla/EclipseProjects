package com.aarnasolutions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aarnasolutions.domain.UserType;
import com.aarnasolutions.repo.UserTypeRepository;

@Service
public class UserTypeServiceImpl implements UserTypeService {
	
	@Autowired
	private UserTypeRepository userTypeRepository;

	@Override
	public UserType getUserTypeById(Long id) {
		return userTypeRepository.findOne(id);
	}
	
	@Override
	public UserType getUserTypeByUserTypeCode(String userTypeCode) {
		return userTypeRepository.findByUserTypeCode(userTypeCode).get(0);
	}
	
	@Override
	public UserType getUserTypeByUserTypeDesc(String userTypeDesc) {
		return userTypeRepository.findByUserTypeDesc(userTypeDesc).get(0);
	}
	
	@Override
	public UserType createUserType(UserType userType) {
		return userTypeRepository.save(userType);
	}

	@Override
	public void deleteUserType(Long id) {
		UserType userType = getUserTypeById(id);
		if (userType != null) {
			userTypeRepository.delete(id);
		}
	}


}
