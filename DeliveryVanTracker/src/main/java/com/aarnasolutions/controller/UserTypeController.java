package com.aarnasolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aarnasolutions.assemblers.UserTypeAssembler;
import com.aarnasolutions.domain.UserType;
import com.aarnasolutions.service.UserTypeService;
import com.aarnasolutions.vo.CreateUserTypeVO;
import com.aarnasolutions.vo.UserTypeVO;

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
    public @ResponseBody UserTypeVO getUserType(@PathVariable("id") Long id) {
        return userTypeAssembler.toUserTypeVO(userTypeService.getUserTypeById(id));
    }
    
    @RequestMapping(value = "/userTypeCode/{userTypeCode}", method = RequestMethod.GET)
    public @ResponseBody UserTypeVO getUserTypeByUserTypeCode(@PathVariable("userTypeCode") String userTypeCode) {
        return userTypeAssembler.toUserTypeVO(userTypeService.getUserTypeByUserTypeCode(userTypeCode));
    }
    
    @RequestMapping(value = "/userTypeCode/{userTypeDesc}", method = RequestMethod.GET)
    public @ResponseBody UserTypeVO getUserTypeByUserTypeDesc(@PathVariable("userTypeDesc") String userTypeDesc) {
        return userTypeAssembler.toUserTypeVO(userTypeService.getUserTypeByUserTypeDesc(userTypeDesc));
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
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