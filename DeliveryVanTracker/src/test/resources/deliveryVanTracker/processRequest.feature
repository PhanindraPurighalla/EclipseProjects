Feature: Parameterized Input XML
	Scenario: User is greeted based on input XML
		Given parameterized input XML exists
		| input_xml  | ParameterizedInput.xml |
		And CSV file exists
	    | csv_file   | create_user.csv        |
	    And CSV file is valid
		When createUser API is invoked
		Then response XML is generated
		