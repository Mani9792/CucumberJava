package steps;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	@Before
	public void setDriver()
	{
		String browser = System.getProperty("browser")!=null?System.getProperty("browser"):"chrome";
		switch (browser.toLowerCase()) 
		{
        case "firfox":
        	FirefoxOptions fo = new FirefoxOptions();
			fo.addArguments("--remote-allow-origins=*"); //using this to get rid of Web socket issues in Chrome version > 111..
			fo.addArguments("--ignore-ssl-errors=yes");
			fo.addArguments("--ignore-certificate-errors");		
			DriverManager.setDriver(new FirefoxDriver(fo));
        case "edge":
        	EdgeOptions eo = new EdgeOptions();
			eo.addArguments("--remote-allow-origins=*"); //using this to get rid of Web socket issues in Chrome version > 111..
			eo.addArguments("--ignore-ssl-errors=yes");
			eo.addArguments("--ignore-certificate-errors");
			WebDriverManager.edgedriver().setup();
			DriverManager.setDriver(new EdgeDriver(eo));
		default:	
			ChromeOptions co = new ChromeOptions();
			co.addArguments("--remote-allow-origins=*"); //using this to get rid of Web socket issues in Chrome version > 111..
			co.addArguments("--ignore-ssl-errors=yes");
			co.addArguments("--ignore-certificate-errors");
			WebDriverManager.chromedriver().setup();
			if(browser.contains("headless"))
			{
				co.addArguments("headless");
			}
			DriverManager.setDriver(new ChromeDriver(co));
			//To set size
			Dimension d = new Dimension(1440,900);
			System.out.println("Dimension : "+d);
			DriverManager.getDriver().manage().window().setSize(d);
		}
		
		DriverManager.getDriver().manage().timeouts().implicitlyWait(100,TimeUnit.SECONDS);
		
		DriverManager.getDriver().manage().window().maximize();
	}
	
	@After
	public void tearDriver(Scenario scenario)
	{
		if(scenario.isFailed())
		{
			final byte[] scr = ((TakesScreenshot)DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
			scenario.attach(scr,"image/png",scenario.getName());
		}
		DriverManager.quitDriver();
	}

}
