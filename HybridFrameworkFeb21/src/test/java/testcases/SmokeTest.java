package testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import dataprovider.ConfigDataProvider;
import factory.DataProviderFactory;
import pages.AdminPage;
import pages.BaseClass;
import pages.LoginPage;
import utilities.Helper;


public class SmokeTest extends BaseClass{  
	
	LoginPage loginPage;
	AdminPage adminPage;
	
	
	@Test
	public void validLogin() {
		
		logger = report.createTest("Smoke Scenario");
		
		loginPage = PageFactory.initElements(driver, LoginPage.class);
		adminPage = PageFactory.initElements(driver, AdminPage.class);
		
		loginPage.verifyBeforeLogin();
		
		
		loginPage.loginToApplication(DataProviderFactory.getExcel().getCellData("Admin", 0, 0), DataProviderFactory.getExcel().getCellData("Admin", 0, 1));
		adminPage.verifyAfterLogin();
		
		//System.out.println(Helper.getCurrentDateTime());
		//ConfigDataProvider config = new ConfigDataProvider();
		//System.out.println(config.getBrowser());
	}
	

}
