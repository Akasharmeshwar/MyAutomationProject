package framework_core;



import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	public static WebDriver create(String browser, Properties env) {
		
		switch (browser.toLowerCase()) {
		
		case "chrome":
			
			WebDriverManager.chromedriver().setup();
			
			ChromeOptions c = new ChromeOptions();
			
			c.addArguments("--start-maximized");
			return new ChromeDriver(c);
		
		case "firefox":
			
			WebDriverManager.firefoxdriver().setup();
			
			FirefoxOptions f = new FirefoxOptions();
			
			return new FirefoxDriver(f);
			
		case "edge":
			
			WebDriverManager.edgedriver().setup();
			
			EdgeOptions e = new EdgeOptions();
			
			return new EdgeDriver(e);
			
			
		default:
			
			throw new IllegalArgumentException("Unsupported browser: " + browser);
			
		}
	}
}