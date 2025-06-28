package steps;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.kaiser.cucumberProject.ExcelReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SignInSauceSteps {
	
	WebDriver driver;
	String path = "./src/test/java/resources/SiginDetails.xlsx";

	public SignInSauceSteps(BaseClass base)
	{
		this.driver = base.getDriver();
	}
	
	@Given("user enters sign in page")
	public void user_enters_sign_in_page() {
		driver.get("https://www.saucedemo.com");		
	}
	@When("User enter sheetname {string} and row number {int}")
	public void user_enter_sheetname_and_row_number(String sheetName, Integer row) {
		ExcelReader ex = new ExcelReader();
		List<Map<String,String>> map = ExcelReader.getExcelDataOneRow(path, sheetName);
		System.out.println(map.get(row).get("Username"));
		System.out.println(map.get(row).get("Password"));
		driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys(map.get(row).get("Username"));
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(map.get(row).get("Password"));
	}
	@When("User clicks on login button")
	public void user_clicks_on_login_button() {
		driver.findElement(By.xpath("//input[@id='login-button']")).click();
	}
	@Then("User navigates to home page")
	public void user_navigates_to_home_page() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
}
