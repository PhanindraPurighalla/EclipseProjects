package com.aarnasolutions.assemblers;

import org.springframework.stereotype.Component;

import com.aarnasolutions.domain.UserType;
import com.aarnasolutions.vo.CreateUserTypeVO;
import com.aarnasolutions.vo.UserTypeVO;

@Component
public class UserTypeAssembler {
	
	/**
     * CreateUserTypeVO to UserType.
     *
     * @param createUserTypeVO
     * @return
     */
    public UserType toUserType(CreateUserTypeVO createUserTypeVO) {
    		
    		UserType userType = new UserType();
    		userType.setUserTypeCode(createUserTypeVO.getUserTypeCode());
    		userType.setUserTypeDesc(createUserTypeVO.getUserTypeDesc());
        return userType;
    }


    /**
     * UserType to UserTypeVO.
     *
     * @param userType
     * @return
     */
    public UserTypeVO toUserTypeVO(UserType userType) {
    	
    		if (userType == null) {
    			return null;
    		}
    		
    		UserTypeVO userTypeVO = new UserTypeVO();
        userTypeVO.setUserTypeId(userType.getId());
        userTypeVO.setUserTypeCode(userType.getUserTypeCode());
        userTypeVO.setUserTypeDesc(userType.getUserTypeDesc());
        return userTypeVO;
    }
    
}