package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AdminPage {
	
	WebDriver driver;
	
	
	public AdminPage(WebDriver driver) {
		
		this.driver = driver;
	}
	
	
	By adminLink = By.xpath("//b[text()='Admin']");
	By welcomeLink = By.xpath("//div[@id='branding']//a[2]");
	By logoutLink = By.xpath("//a[contains(text(),'Logout')]");
	
	
	public void verifyAfterLogin() {
		
		Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
	}
	
	
	public void clickOnAdminLink() {
		
		driver.findElement(adminLink).click();
		Assert.assertTrue(driver.getCurrentUrl().contains("admin"));
	}
	
	
	
	

}
