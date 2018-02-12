package deliveryVanTracker;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;

import com.aarnasolutions.app.Application;
import com.cucumber.listener.Reporter;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

@SuppressWarnings("deprecation")
@ContextConfiguration(classes = Application.class, loader = SpringBootContextLoader.class)
public class ParameterizedInputSteps {

	String parameterizedInputXml = null;
	String inputCsvFile = null;

	File inputXml = null;
	File csvFile = null;

	Map<String, String> parameterNameNodeElementMapping = new HashMap<String, String>();
	Map<String, List<String>> parameterNameTestValueMapping = new HashMap<String, List<String>>();

	int numberOfTests = 0;

	String fileAsString = null;

	Map<Integer, Response> testResponses = new HashMap<Integer, Response>();

	@Given("^parameterized input XML exists$")
	public void parameterizedInputXmlExists(Map<String, String> tableData) throws Throwable {

		parameterizedInputXml = "src/test/resources/deliveryVanTracker/" + tableData.get("input_xml");

		inputXml = new File(parameterizedInputXml);

		Assert.assertTrue("Parameterized input XML does not exist!", inputXml.exists());

	}

	@And("^CSV file exists$")
	public void csvFileExists(Map<String, String> tableData) throws Throwable {

		inputCsvFile = "src/test/resources/deliveryVanTracker/" + tableData.get("csv_file");

		csvFile = new File(inputCsvFile);

		Assert.assertTrue("CSV file with test data does not exist!", csvFile.exists());

	}

	@And("^CSV file is valid$")
	public void csvFileIsValid() throws Throwable {
		BufferedReader reader = new BufferedReader(new FileReader(inputCsvFile));

		String read_line = null;
		while ((read_line = reader.readLine()) != null) {
			String[] fields = read_line.split("\\:");
			parameterNameNodeElementMapping.put(fields[0], fields[1]);

			String[] testValues = fields[2].split(",");
			List<String> testParameterList = new ArrayList<String>();
			for (int i = 0; i < testValues.length; i++) {
				testParameterList.add(i, testValues[i]);
			}
			parameterNameTestValueMapping.put(fields[0], testParameterList);
		}
		reader.close();

		for (String parameter : parameterNameTestValueMapping.keySet()) {
			int currNumberOfTests = parameterNameTestValueMapping.get(parameter).size();
			if (numberOfTests == 0) {
				numberOfTests = currNumberOfTests;
			}
			Assert.assertTrue(
					"All parameters not bound in CSV file. Parameter " + parameter + " has " + currNumberOfTests
							+ " test values when " + numberOfTests + " values were expected!",
					(numberOfTests == 0 || currNumberOfTests == numberOfTests));
		}
	}

	@When("^createUser API is invoked$")
	public void invokeCreateUserAPI() throws Throwable {

		// init array with file length
		byte[] bytesArray = new byte[(int) inputXml.length()];

		FileInputStream fis = new FileInputStream(inputXml);
		fis.read(bytesArray); // read file into bytes[]
		fis.close();

		fileAsString = new String(bytesArray);
		fileAsString = fileAsString.replaceAll("[^\\x20-\\x7e\\x0A]", "");

		for (int testNumber = 0; testNumber < numberOfTests; testNumber++) {
			Response response = null;
			RestAssured.baseURI ="http://localhost:8080";
			RequestSpecification request = RestAssured.given();
			
			// Add a header stating the Request body is a JSON
			request.header("Content-Type", "application/json");
			
			JSONObject requestParams = new JSONObject();
			for (String parameter : parameterNameTestValueMapping.keySet()) {
				requestParams.put(parameterNameNodeElementMapping.get(parameter),
						parameterNameTestValueMapping.get(parameter).get(testNumber));

			}
			
			// Add the Json to the body of the request
			request.body(requestParams.toString());
			
			Reporter.addStepLog("CreateUser API invoked with the below body: " + requestParams.toString());
			response = request.post("/user");
			testResponses.put(testNumber, response);			

		}

	}

	@Then("^response XML is generated$")
	public void responseXMLIsGenerated() throws Throwable {

		for (Integer testNumber : testResponses.keySet()) {
			Reporter.addStepLog("Response from CreateUser API being verified: "
					+ testResponses.get(testNumber).getBody().asString());
			assertThat("API call has not been successful for test number: " + testNumber,
					testResponses.get(testNumber).getStatusCode(), equalTo(200));
			XmlPath xmlPath = testResponses.get(testNumber).getBody().xmlPath();
			assertThat("UserType should be 21", xmlPath.getInt("CreateUserVO.userTypeId"), equalTo(21));
		}

	}

}
