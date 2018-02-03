package com.aarnasolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
	 * @param userAssembler
	 *            the userAssembler to set
	 */
	public void setUserAssembler(UserAssembler userAssembler) {
		this.userAssembler = userAssembler;
	}

	@Autowired
	private UserService userService;

	/**
	 * @return the userService
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * @param userService
	 *            the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public UserVO getUser(@PathVariable("id") Long id) {
		return userAssembler.toUserVO(userService.getUserById(id));
	}

	@RequestMapping(value = "/mobileNo/{mobileNo}", method = RequestMethod.GET, consumes = {"application/xml", "application/json"}, produces = {"application/xml", "application/json"})
	public UserVO getUser(@PathVariable("mobileNo") String mobileNo) {
		return userAssembler.toUserVO(userService.getUserByMobileNo(mobileNo));
	}

	@RequestMapping(method = RequestMethod.POST, consumes = {"application/xml", "application/json"}, produces = {"application/xml", "application/json"})
	public @ResponseBody UserVO createUser(@RequestBody CreateUserVO userVO) {
		// convert to User
		User user = userAssembler.toUser(userVO);
		// save User
		User savedUser = userService.createUser(user);
		// convert to UserVO
		return userAssembler.toUserVO(userService.getUserById(savedUser.getId()));
	}

	@RequestMapping(value = "/{mobileNo}", method = RequestMethod.PUT, consumes = {"application/xml", "application/json"}, produces = {"application/xml", "application/json"})
	public ResponseEntity<UserVO> updateUser(@PathVariable("mobileNo") String mobileNo,
			@RequestBody UpdateUserVO updateUserVO) {

		User currentUser = userService.getUserByMobileNo(mobileNo);
		if (currentUser == null) {
			System.out.println("User with mobileNo " + mobileNo + " not found");
			return new ResponseEntity<UserVO>(HttpStatus.NOT_FOUND);
		}
		
		currentUser.setLastLocationCity(updateUserVO.getLastLocationCity());
		currentUser.setLastLocationLocality(updateUserVO.getLastLocationLocality());
		currentUser.setLastLocationCountry(updateUserVO.getLastLocationCountry());
		currentUser.setMobileNo(updateUserVO.getMobileNo());
		userService.updateUser(currentUser);
		return new ResponseEntity<UserVO>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id) {
		userService.deleteUser(id);
	}
}