package testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import pages.AdminPage;
import pages.BaseClass;
import pages.LoginPage;

public class TestAdminPage extends BaseClass {
	
	
	LoginPage loginPage;
	AdminPage adminPage;
	
		
	@Test(priority = 1)
	public void verifyAfterLoginTest() {
		
		logger = report.createTest("verifyAfterLoginTest");
		
		loginPage = PageFactory.initElements(driver, LoginPage.class);
		adminPage = PageFactory.initElements(driver, AdminPage.class);
		
		loginPage.loginToApplication("Admin", "admin123");
		adminPage.verifyAfterLogin();	
		
	}
	
	
	@Test(priority = 2)
	public void clickOnAdminLinkTest() {
		
		logger = report.createTest("clickOnAdminLinkTest");
		
		adminPage.clickOnAdminLink();
		loginPage.clickLogoutFromApplication();
	}
	
	
	@Test(priority = 3)
	public void verifyLogoutTest() {
		
		logger = report.createTest("verifyLogoutTest");
		loginPage.verifyLogout();
	}
	
	
	

}
