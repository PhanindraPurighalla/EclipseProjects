/**
 * 
 */
package deliveryVanTracker;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * @author phanindrapurighalla
 *
 */
@RunWith(Cucumber.class)
@CucumberOptions(
	    plugin = {"com.cucumber.listener.ExtentCucumberFormatter:target/automation/cucumber-report.html"},
	    features = {"src/test/resources/deliveryVanTracker/processRequest.feature"}, 
	    glue = {})
public class CreateUserTest {	

}
