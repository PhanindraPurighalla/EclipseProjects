package com.aarnasolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aarnasolutions.assemblers.UserAssembler;
import com.aarnasolutions.domain.User;
import com.aarnasolutions.service.UserService;
import com.aarnasolutions.vo.CreateUserVO;
import com.aarnasolutions.vo.UserVO;
import com.aarnasolutions.vo.UpdateUserVO;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserAssembler userAssembler;

    /**
	 * @return the userAssembler
	 */
	public UserAssembler getUserAssembler() {
		return userAssembler;
	}

	/**
	 * @param userAssembler the userAssembler to set
	 */
	public void setUserAssembler(UserAssembler userAssembler) {
		this.userAssembler = userAssembler;
	}

	@Autowired
    private UserService userService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserVO getUser(@PathVariable("id") Long id) {
        return userAssembler.toUserVO(userService.getUserById(id));
    }
    
    @RequestMapping(value = "/mobileNo/{mobileNo}", method = RequestMethod.GET)
    public UserVO getUser(@PathVariable("mobileNo") String mobileNo) {
        return userAssembler.toUserVO(userService.getUserByMobileNo(mobileNo));
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public UserVO createUser(@RequestBody CreateUserVO userVO) {
        //convert to User
        User user = userAssembler.toUser(userVO);
        //save User
        User savedUser = userService.createUser(user);
        //convert to UserVO
        return userAssembler.toUserVO(savedUser);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public UserVO updateUser(@RequestBody UpdateUserVO updateUserVO) {
        //convert to User
        User user = userAssembler.toUser(updateUserVO);
        //update User
        userService.updateUser(user);
        //convert to UserVO
        return userAssembler.toUserVO(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }
}