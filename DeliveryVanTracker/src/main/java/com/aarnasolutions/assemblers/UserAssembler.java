package com.aarnasolutions.assemblers;

import org.springframework.stereotype.Component;

import com.aarnasolutions.domain.User;
import com.aarnasolutions.vo.CreateUserVO;
import com.aarnasolutions.vo.UpdateUserVO;
import com.aarnasolutions.vo.UserVO;

@Component
public class UserAssembler {
	
	
	/**
     * CreateUserVO to User.
     *
     * @param createUserVO
     * @return
     */
    public User toUser(CreateUserVO createUserVO) {
    		
    		User user = new User();
        user.setUserName(createUserVO.getUserName());
        user.setMobileNo(createUserVO.getMobileNo());
        user.setUserTypeId(createUserVO.getUserTypeId());
        return user;
    }


    /**
     * User to UserVO.
     *
     * @param user
     * @return
     */
    public UserVO toUserVO(User user) {
    	
    		if (user == null) {
    			return null;
    		}
    		
    		UserVO userVO = new UserVO();
        userVO.setUserId(user.getId());
        userVO.setUserName(user.getUserName());
        userVO.setMobileNo(user.getMobileNo());
        userVO.setUserTypeId(user.getUserTypeId());
        userVO.setLastLocationLat(user.getLastLocationLat());
        userVO.setLastLocationLong(user.getLastLocationLong());
        userVO.setLastLocationLocality(user.getLastLocationLocality());
        userVO.setLastLocationCity(user.getLastLocationCity());
        userVO.setLastLocationCountry(user.getLastLocationCountry());
        userVO.setLastLocationTime(user.getLastLocationTime());
        return userVO;
    }

    /**
     * UpdateUserVO to User.
     *
     * @param updateUserVO
     * @return
     */
    public User toUser(UpdateUserVO updateUserVO) {
    	
    		User user = new User();
        user.setId(updateUserVO.getUserId());
        user.setUserName(updateUserVO.getUserName());
        user.setUserTypeId(updateUserVO.getUserTypeId());
        user.setMobileNo(updateUserVO.getMobileNo());
        user.setLastLocationLat(updateUserVO.getLastLocationLat());
        user.setLastLocationLong(updateUserVO.getLastLocationLong());
        user.setLastLocationLocality(updateUserVO.getLastLocationLocality());
        user.setLastLocationCity(updateUserVO.getLastLocationCity());
        user.setLastLocationCountry(updateUserVO.getLastLocationCountry());
        user.setLastLocationTime(updateUserVO.getLastLocationTime());
        return user;
    }
}