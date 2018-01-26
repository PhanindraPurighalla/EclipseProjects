package cabip001;

import java.io.BufferedReader;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import utils.KenanDatabaseTester;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {"com.cucumber.listener.ExtentCucumberFormatter:target/automation/batch-cucumber-report.html"},
    features = {"src/test/resources/cucumber/cabip001.feature"}, 
    glue = {})
public class BatchCucumberTester {

  protected static BufferedReader in;

  protected static KenanDatabaseTester databaseTester;

  @BeforeClass
  public static void connectToDB() throws Throwable {
    databaseTester = new KenanDatabaseTester();
    databaseTester.fetchConnectionDetailsForCustomer1Database();
  }

  @AfterClass
  public static void teardown() {
    // Reporter.loadXMLConfig(new File("src/test/resources/extent-config.xml"));
    databaseTester.rollbackAndCloseDatabaseConnections();
    Reporter.setTestRunnerOutput("Disconnected from database instances at: " + new Date());
    
    Reporter.setSystemInfo("user", System.getProperty("user.name"));
    Reporter.setSystemInfo("os", "MacOS");
  }

}
