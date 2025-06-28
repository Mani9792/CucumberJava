package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

public class Ebay_AdvancedSearch_steps {
	WebDriver driver;
	public void setDriver()
	{
		ChromeOptions co = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(co);
	}
	@Given("I am on Ebay Advanced search page")
	public void i_am_on_ebay_advanced_search_page() {
		setDriver();
		driver.get("https://www.ebay.com/sch/ebayadvsearch");
		System.out.println("I am on Ebay home page");
	}
	@When("I click on Ebay logo")
	public void i_click_on_ebay_logo() {
		driver.findElement(By.xpath("//a[@class='gh-logo']")).click();
		System.out.println("I clicked on ebay logo");
	}
	@Then("I navigate to Ebay home page")
	public void i_navigate_to_ebay_home_page() {
		String currUrl = driver.getCurrentUrl();
		Assert.assertEquals("https://www.ebay.com/", currUrl);
		driver.quit();
	}
	
	@When("I advance search an item")
	public void i_advance_search_an_item(DataTable dataTable) throws InterruptedException {
	    driver.findElement(By.xpath("//input[@id='_nkw']")).sendKeys(dataTable.cell(1, 0));
	    driver.findElement(By.xpath("//input[@id='_ex_kw']")).sendKeys(dataTable.cell(1, 1));
	    driver.findElement(By.xpath("//input[@name='_udlo']")).sendKeys(dataTable.cell(1, 2));
	    driver.findElement(By.xpath("//input[@name='_udhi']")).sendKeys(dataTable.cell(1, 3));
	    driver.findElement(By.xpath("(//*[text()='Search'])[2]")).click();  
	    Thread.sleep(5000);
	    driver.quit();
	}

}
