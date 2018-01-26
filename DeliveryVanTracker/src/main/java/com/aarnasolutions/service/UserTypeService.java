package com.aarnasolutions.service;

import com.aarnasolutions.domain.UserType;

public interface UserTypeService {

	UserType getUserTypeById(Long id);
	
	UserType getUserTypeByUserTypeCode(String userTypeCode);
	
	UserType getUserTypeByUserTypeDesc(String userTypeDesc);
	
	UserType createUserType(UserType userType);
	
    void deleteUserType(Long id);
    
}
