package tokenManagementSystem;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import org.springframework.beans.factory.annotation.Autowired;

import com.aarnasolutions.controller.CustomerController;
import com.aarnasolutions.vo.CreateCustomerVO;
import com.aarnasolutions.vo.CustomerVO;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CustomerSteps {

	@Autowired
	CustomerController customerController = new CustomerController();
	
	CustomerVO customerFromDatabase;
	
	@Given("^that (.*), (.*), (.*), (.*), (.*) and (.*) are provided$")
	public void addNewCustomer(String firstName, String lastName, String emailId, String dateOfBirth,
			String pinCode, String contactNo) throws Throwable {
		
		CreateCustomerVO createCustomerVO = new CreateCustomerVO();
		createCustomerVO.setFirstName(firstName);
		createCustomerVO.setLastName(lastName);
		createCustomerVO.setEmailId(emailId);
		createCustomerVO.setDateOfBirth(dateOfBirth);
		createCustomerVO.setPinCode(pinCode);
		createCustomerVO.setContactNo(contactNo);
		
		customerController.createCustomer(createCustomerVO);
		
	}

	@When("^the tokenManagementSystem is searched for contactNo (.*)$")
	public void findCustomer(final Long contactNo) throws Throwable {
		customerFromDatabase = customerController.getCustomer(contactNo);
	}
	
	@Then("^a customer record with contactNo (.*) is created in the database for (.*)$")
	public void customerCreated(final long contactNo, String fullName) throws Throwable {
		
		assertThat(customerFromDatabase.getFullName(), equalTo(fullName));
	}

}
