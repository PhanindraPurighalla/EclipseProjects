Feature: Create Customer
	Scenario: Customer is created
		Given that first_name, last_name, email_id, date_of_birth, pin_code and contact_no are provided
		When the tokenManagementSystem is searched for contactNo contact_no
		Then a customer record with contactNo contact_no is created in the database for first_name+last_name