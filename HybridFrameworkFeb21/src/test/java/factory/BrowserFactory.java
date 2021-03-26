package factory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {

	public WebDriver startBrowser(String browserName, String url) {
		
		System.out.println("LOG:INFO : Starting session on "+browserName + " and Launching application "+ url);

		WebDriver driver = null; // It is our responsibility to initialize local variable.
		String path = System.getProperty("user.dir") + "/Drivers";

		if (browserName.equalsIgnoreCase("chrome")) {
			//System.setProperty("webdriver.chrome.driver", path + "/chromedriver.exe");
			//System.setProperty("webdriver.chrome.silentOutput", "true");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {
			//System.setProperty("webdriver.gecko.driver", path + "/geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("edge")) {
			//System.setProperty("webdriver.edge.driver", path + "/msedgedriver.exe");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		} else if (browserName.equalsIgnoreCase("ChromeHeadless")) {
			//System.setProperty("webdriver.chrome.driver", path + "/chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			ChromeOptions opt = new ChromeOptions();
			opt.setHeadless(true);
			driver = new ChromeDriver(opt);

		} else if (browserName.equalsIgnoreCase("FirefoxHeadless")) {
			//System.setProperty("webdriver.gecko.driver", path + "/geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions opt = new FirefoxOptions();
			opt.setHeadless(true);
			driver = new FirefoxDriver(opt);

		} else {
			System.out.println("Browser not supported. PLease use Chrome/Firefox or Edge Browser");
		}

		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);

		System.out.println("LOG:INFO : Browser and Application are up ad running");
		
		return driver;
	
	}
	
	
}
