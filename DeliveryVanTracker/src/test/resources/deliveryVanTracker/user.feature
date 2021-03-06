Feature: User
	Scenario Outline: User is created
		Given that usertypes adminUserType and driverUserType exist
		| admin_user_type  | A |
		| driver_user_type | D |
		And that username <username>, usertype <user_type_id> and mobileNo <mobile_no> are provided
		When the delivery van tracker is searched for mobileNo <mobile_no>
		Then a user record with mobileNo <mobile_no> is created in the database for username <username>
		Examples:
		| username  | user_type_id | mobile_no  |
		| phanindra | 16           | 9949488758 |
		| kameswari | 15           | 9849488848 |