package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utilities.Helper;

public class LoginPage {
	
	WebDriver driver;
	
	
	public LoginPage(WebDriver driver) {
		
		this.driver = driver;
		
	}
	
	
	By forgotPasswordLink = By.xpath("//a[text()='Forgot your password?']");
	By usernameId = By.id("txtUsername");
	By passwordId = By.id("txtPassword");
	By loginBtn = By.xpath("//input[@value='LOGIN']");
	By linkedinIcon = By.xpath("//img[contains(@alt,'LinkedIn ')]");
	By youTubeIcon = By.xpath("//img[contains(@alt,'youtube')]");
	By welcomeLink = By.xpath("//div[@id='branding']//a[2]");
	By logoutLink = By.xpath("//a[contains(text(),'Logout')]");
	
	
	public void verifyBeforeLogin() {
		
		Assert.assertTrue(driver.getTitle().contains("OrangeHRM"));
	}
	
	public void clickOnForgotPasswordLink() {
		
		driver.findElement(forgotPasswordLink).isDisplayed();
		
	}
	
	public void loginToApplication(String username, String password) {
		
	/*	driver.findElement(usernameId).sendKeys(username);
		driver.findElement(passwordId).sendKeys(password);
		driver.findElement(loginBtn).click();  */
		
		
		Helper.waitForWebElementAndType(driver, usernameId,username, "Type username" );
		Helper.waitForWebElementAndType(driver, passwordId, password, "Type password");
		Helper.waitForWebElementAndClick(driver, loginBtn, "Click on button");
		
	}
	
	public void verifyLinkedInLink() {
		
		Assert.assertTrue(driver.findElement(linkedinIcon).isDisplayed());
	}
	
	public void verifyYouTubeLink() {
		
		Assert.assertTrue(driver.findElement(youTubeIcon).isDisplayed());
	}
	
	
	public void clickLogoutFromApplication() {
		
		new WebDriverWait(driver, 12).until(ExpectedConditions.visibilityOf(driver.findElement(welcomeLink))).click();
		new WebDriverWait(driver, 12).until(ExpectedConditions.visibilityOf(driver.findElement(logoutLink))).click();
		
	}
	
	
	public void verifyLogout() {
		
		new WebDriverWait(driver, 12).until(ExpectedConditions.urlContains("login"));
		//Assert.assertTrue(driver.getCurrentUrl().contains("login"));
	}
	
	
	

}
