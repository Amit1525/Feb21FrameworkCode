package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import factory.BrowserFactory;
import pages.BaseClass;
import pages.LoginPage;

public class TestLoginPage extends BaseClass {

	LoginPage loginPage;

	
	@Test(priority = 1)
	public void verifyTitleTest() {

		logger = report.createTest("verifyTitleTest");
		
		loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.verifyBeforeLogin();
	}
	

	@Test(priority = 2)
	public void verifySocialLinksTest() {

		logger = report.createTest("verifySocialLinksTest");
		
		loginPage.verifyLinkedInLink();
		loginPage.verifyYouTubeLink();
	}
	

	@Test(priority = 3)
	public void loginTest() {

		logger = report.createTest("loginTest");
		loginPage.loginToApplication("Admin", "admin123");

	}
	
	
	@Test(priority = 4)
	public void logoutTest() {
		
		logger = report.createTest("logoutTest");
		loginPage.clickLogoutFromApplication();
	}
	
	
	@Test(priority = 5)
	public void verifyLogoutTest() {
		
		logger = report.createTest("verifyLogoutTest");
		loginPage.verifyLogout();
	}
	
	
	
	
	
	
	

}
