package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/java/features"},
		glue = {"steps"},
		tags = "@regression",
		plugin = {"pretty","json:target/json-report/cucumber.json"},  //Report Generation
		dryRun = false    // just the dry run to find any feature without any step definitions no actual execution	   
		)
public class RegressionTestRunner {

}
