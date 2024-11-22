package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(//features = "features/AddNewCustomers.feature",
				 features = "features",
				 glue = "stepDefinitions",
//				 dryRun = true,	// To check all steps are defined in the step definition file/class or not before real execution
				 dryRun = false,
				 monochrome = true,	// For clean output
//				 tags = "@sanity",	// Will execute only sanity scenarios
				 tags = "@sanity or @regression",	// Will execute scenarios which are tagged as @sanity or @regression
//				 tags = "@sanity and @regression",	// Will execute scenarios which are tagged as @sanity and @regression both
//				 tags = "@smoke",	// No scenario will run
				 plugin = {"pretty",
						   "html:cucumber-output/cucumber-report.html"}
				 )
public class TestRunner extends AbstractTestNGCucumberTests{

}
