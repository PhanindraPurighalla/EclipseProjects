package deliveryVanTracker;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;

import com.aarnasolutions.app.Application;
import com.aarnasolutions.controller.UserController;
import com.aarnasolutions.controller.UserTypeController;
import com.aarnasolutions.vo.CreateUserTypeVO;
import com.aarnasolutions.vo.CreateUserVO;
import com.aarnasolutions.vo.UserVO;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@ContextConfiguration(classes = Application.class, loader = SpringBootContextLoader.class)
public class UserSteps {

	@Autowired
	UserController userController;
	
	@Autowired
	UserTypeController userTypeController;
	
	/**
	 * @return the userController
	 */
	public UserController getUserController() {
		return userController;
	}

	/**
	 * @param userController the userController to set
	 */
	public void setUserController(UserController userController) {
		this.userController = userController;
	}

	/**
	 * @return the userTypeController
	 */
	public UserTypeController getUserTypeController() {
		return userTypeController;
	}

	/**
	 * @param userTypeController the userTypeController to set
	 */
	public void setUserTypeController(UserTypeController userTypeController) {
		this.userTypeController = userTypeController;
	}

	UserVO userFromDatabase;
	
	@Given("^that usertypes adminUserType and driverUserType exist$")
	public void userTypesExist(Map<String, String> tableData) throws Throwable {
		
		CreateUserTypeVO createAdminUserTypeVO = new CreateUserTypeVO();
		createAdminUserTypeVO.setUserTypeCode(tableData.get("admin_user_type"));
		createAdminUserTypeVO.setUserTypeDesc("Admin User");
		
		userTypeController.createUserType(createAdminUserTypeVO);
		
		CreateUserTypeVO createDriverUserTypeVO = new CreateUserTypeVO();
		createDriverUserTypeVO.setUserTypeCode(tableData.get("driver_user_type"));
		createDriverUserTypeVO.setUserTypeDesc("Driver");
		
		userTypeController.createUserType(createDriverUserTypeVO);
		
	}
	
	@And("^that username (.*), usertype (.*) and mobileNo (.*) are provided$")
	public void addNewUser(String userName, String userTypeId, String mobileNo) throws Throwable {
		
		CreateUserVO createUserVO = new CreateUserVO();
		createUserVO.setUserName(userName);
		createUserVO.setUserTypeId(userTypeId);
		createUserVO.setMobileNo(mobileNo);
		
		userController.createUser(createUserVO);
		
	}

	@When("^the delivery van tracker is searched for mobileNo (.*)$")
	public void findUser(final String mobileNo) throws Throwable {
		userFromDatabase = userController.getUser(mobileNo);
	}
	
	@Then("^a user record with mobileNo (.*) is created in the database for username (.*)$")
	public void userCreated(final String mobileNo, String userName) throws Throwable {
		
		assertThat(userFromDatabase.getUserName(), equalTo(userName));
	}

}
