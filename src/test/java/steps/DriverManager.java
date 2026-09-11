package steps;

import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Set driver for the current thread
    public static void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }

    // Get driver for the current thread
    public static WebDriver getDriver() {
        return driver.get();
    }
    
    public static void quitDriver() {
    	if(driver.get()!= null)
    	{
	        driver.get().quit();
	        driver.remove();
    	}
    }
}
