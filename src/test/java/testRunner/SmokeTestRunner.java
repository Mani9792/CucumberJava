package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/java/features"},
		glue = {"steps"},
		tags = "@smoke",
		plugin = {"pretty","html:target/cucumber-reports/index"},  //Report Generation
		monochrome = true,
		dryRun = false    // just the dry run to find any feature without any step definitions no actual execution	   
		//name = {"logo"} "html:target/cucumber-reports"
		//Json report json:target/json-report/cucumber.json
		)
public class SmokeTestRunner {

}
