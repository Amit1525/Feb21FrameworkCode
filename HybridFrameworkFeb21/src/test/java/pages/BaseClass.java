/*
 * @Author Amit Mahale
 * 
 */

package pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import dataprovider.ConfigDataProvider;
import factory.BrowserFactory;
import factory.DataProviderFactory;
import utilities.Helper;

public class BaseClass {

	public WebDriver driver;
	public static ExtentReports report;
	public ExtentTest logger;
	String reportPath;

	@BeforeSuite
	public void setupReport() {

		System.out.println("LOG:INFO : Setting  up Report");

		String path = System.getProperty("user.dir");
		reportPath = path + "/Reports/Report" + Helper.getCurrentDateTime() + ".html";

		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportPath);
		htmlReporter.config().setReportName("Automation Report");

		report = new ExtentReports();
		report.attachReporter(htmlReporter);

		System.out.println("LOG:INFO : Report is set");
	}

	//@Parameters({"browser","appURL"})
	@BeforeClass
	//public void startBrowser(String browser, String url) {
	public void startBrowser() {	
		System.out.println("LOG:INFO : Starting the browser session");

		//ConfigDataProvider config = new ConfigDataProvider();
		driver = new BrowserFactory().startBrowser(DataProviderFactory.getConfig().getBrowser(), DataProviderFactory.getConfig().getURL());
		//driver = new BrowserFactory().startBrowser(browser, url);
		//System.out.println("Driver value "+ driver);
		
	}

	@AfterMethod
	public void addResultToReport(ITestResult result) {

		if (result.getStatus() == ITestResult.SUCCESS) {
			System.out.println("LOG:INFO : Test Result is Success");
			logger.pass("Test Passed");

		} else if (result.getStatus() == ITestResult.FAILURE) {
			System.out.println("LOG:INFO : Test Result is Failure");

			try {
				logger.fail("Test Failed " + result.getThrowable().getMessage(),
						MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
			} catch (IOException e) {

				System.out.println(e.getMessage());
			}

		} else {
			System.out.println("LOG:INFO : Test Result is Skipped");

			try {
				logger.skip("Test Skipped",
						MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
			} catch (IOException e) {

				System.out.println(e.getMessage());
			}
		}

		report.flush();
		System.out.println("LOG:INFO : Adding Test Result to report");
	}

	@AfterClass
	public void closeBrowser() {

		//System.out.println("Driver value before closing the session "+ driver);
		driver.quit();
		//driver.get(reportPath);
		System.out.println("LOG:INFO : Closing the browser session");
	}

}
