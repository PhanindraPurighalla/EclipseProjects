package deliveryVanTracker;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;

import com.aarnasolutions.app.Application;
import com.aarnasolutions.controller.UserController;
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

	UserVO userFromDatabase;
	
	@Given("^that usertypes adminUserType (.*) and driverUsertype (.*) exist$")
	public void userTypesExist(String adminUserType, String driverUserType) throws Throwable {
		
		CreateUserTypeVO createAdminUserTypeVO = new CreateUserTypeVO();
		createAdminUserTypeVO.setUserTypeCode("A");
		createAdminUserTypeVO.setUserTypeDesc("Admin User");
		
		userController.createUser(createUserVO);
		
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
