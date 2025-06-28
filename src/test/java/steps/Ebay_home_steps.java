package steps;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

public class Ebay_home_steps {
	WebDriver driver;
	private static String dataSheet="./src/test/java/resources/TestDataSheet.xlsx";
	public Ebay_home_steps(BaseClass base)
	{
		this.driver = base.getDriver();
	}
	@Given("I am on Ebay home page")
	public void i_am_on_Ebay_home_page()
	{
		driver.get("https://www.ebay.com");
		System.out.println("I am on Ebay home page");
	}
	@When("I click on advanced link")
	public void i_click_on_advanced_link()
	{
		driver.findElement(By.xpath("//a[contains(text(),'Advanced')]")).click();
		System.out.println("I click on advanced link");
	}
	@Then("I navigate to Advanced search page")
	public void i_navigate_to_advances_search_page()
	{
		String title = driver.getTitle();
		Assert.assertEquals("Advanced Search | eBay", title);
		System.out.println("I navigate to Advanced search page");
	}
	@Given("I am on ebay home page")
	public void i_am_on_ebay_home_page() {
		driver.get("https://www.ebay.com");
		System.out.println("I am on Ebay home page");
	}
	@When("I search for products and validate altleast {int} search items present")
	public void i_search_for_products(Integer int1) throws InterruptedException {
		DataFromXcel ex = new DataFromXcel();
		
		List<Map<String, String>> data = ex.getExcelDataOneRow(dataSheet,"TESTCASE_1");
		for(int i=0;i<data.size();i++)
		{
		String products = data.get(i).get("PRODUCTS");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@name='_nkw']")).clear();
		driver.findElement(By.xpath("//input[@name='_nkw']")).sendKeys(products);	
		driver.findElement(By.xpath("//button[@id='gh-search-btn']")).click();
		Thread.sleep(2000);
		String itemCount = driver.findElement(By.xpath("//h1[@class='srp-controls__count-heading']/span[1]")).getText().trim();
        String itc = itemCount.replace(",", "");
	    int count = Integer.parseInt(itc); 
	    
        if(count <= int1)
        {
        	System.out.println("less than 1000 results");
        }
        else
        {
        	System.out.println("more than 1000 results");
        }
		}
	}
	@When("^I search for \"(.*)\"$")
	public void i_search_for(String string) {
		driver.findElement(By.xpath("//input[@name='_nkw']")).sendKeys(string);	
		driver.findElement(By.xpath("//button[@id='gh-search-btn']")).click();
	}
	@Then("I validate altleast {int} search items present")
	public void i_validate_altleast_search_items_present(Integer int1) {
	    String itemCount = driver.findElement(By.xpath("//h1[@class='srp-controls__count-heading']/span[1]")).getText().trim();
        String itc = itemCount.replace(",", "");
	    int count = Integer.parseInt(itc); 
        if(count <= int1)
        {
        	System.out.println("less than 1000 results");
        }
	}
}