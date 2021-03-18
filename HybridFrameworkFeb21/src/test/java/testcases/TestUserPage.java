package testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import factory.DataProviderFactory;
import pages.BaseClass;
import pages.LoginPage;
import pages.UserPage;

public class TestUserPage extends BaseClass{
	
	UserPage userPage;
	LoginPage loginPage;
	
	
	@Test
	public void createUser() {
		
		//System.out.println("Thread ID "+ Thread.currentThread().getId());
		
		logger = report.createTest("Create User Test");
		
		loginPage = PageFactory.initElements(driver, LoginPage.class);
		userPage = PageFactory.initElements(driver, UserPage.class);
		
		loginPage.loginToApplication(DataProviderFactory.getExcel().getCellData("Admin", 0, 0), DataProviderFactory.getExcel().getCellData("Admin", 0, 1));
		userPage.addUser();
		
		
	}
	
	
	
	

}
