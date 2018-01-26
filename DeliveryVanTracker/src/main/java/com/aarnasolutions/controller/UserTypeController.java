package com.aarnasolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aarnasolutions.assemblers.UserAssembler;
import com.aarnasolutions.assemblers.UserTypeAssembler;
import com.aarnasolutions.domain.User;
import com.aarnasolutions.domain.UserType;
import com.aarnasolutions.service.UserService;
import com.aarnasolutions.service.UserTypeService;
import com.aarnasolutions.vo.CreateUserTypeVO;
import com.aarnasolutions.vo.CreateUserVO;
import com.aarnasolutions.vo.UpdateUserVO;
import com.aarnasolutions.vo.UserTypeVO;
import com.aarnasolutions.vo.UserVO;

@RestController
@RequestMapping("/userType")
public class UserTypeController {

    @Autowired
    private UserTypeAssembler userTypeAssembler;

    /**
	 * @return the userTypeAssembler
	 */
	public UserTypeAssembler getUserTypeAssembler() {
		return userTypeAssembler;
	}

	/**
	 * @param userTypeAssembler the userTypeAssembler to set
	 */
	public void setUserTypeAssembler(UserTypeAssembler userTypeAssembler) {
		this.userTypeAssembler = userTypeAssembler;
	}

	@Autowired
    private UserTypeService userTypeService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserTypeVO getUserType(@PathVariable("id") Long id) {
        return userTypeAssembler.toUserTypeVO(userTypeService.getUserTypeById(id));
    }
    
    @RequestMapping(value = "/userTypeCode/{userTypeCode}", method = RequestMethod.GET)
    public UserTypeVO getUserTypeByUserTypeCode(@PathVariable("userTypeCode") String userTypeCode) {
        return userTypeAssembler.toUserTypeVO(userTypeService.getUserTypeByUserTypeCode(userTypeCode));
    }
    
    @RequestMapping(value = "/userTypeCode/{userTypeDesc}", method = RequestMethod.GET)
    public UserTypeVO getUserTypeByUserTypeDesc(@PathVariable("userTypeDesc") String userTypeDesc) {
        return userTypeAssembler.toUserTypeVO(userTypeService.getUserTypeByUserTypeDesc(userTypeDesc));
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public UserTypeVO createUserType(@RequestBody CreateUserTypeVO userTypeVO) {
        //convert to UserType
        UserType userType = userTypeAssembler.toUserType(userTypeVO);
        //save UserType
        UserType savedUserType = userTypeService.createUserType(userType);
        //convert to UserTypeVO
        return userTypeAssembler.toUserTypeVO(savedUserType);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        userTypeService.deleteUserType(id);
    }
}