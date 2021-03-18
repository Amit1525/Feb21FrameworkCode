package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utilities.Helper;

public class UserPage {
	
	WebDriver driver;
	
	
	public UserPage(WebDriver driver) {
		
		this.driver = driver;
	}
	
	
	By adminTab = By.xpath("//b[text()='Admin']");
	By addButton = By.xpath("//Input[@value='Add']");
	By empName = By.xpath("//label[text()='Employee Name']//following::input[1]");
	By username = By.xpath("//label[text()='Username']//following::input[1]");
	By password = By.xpath("//label[text()='Password']//following::input[1]");
	By confirmPassword = By.xpath("//label[text()='Confirm Password']//following::input[1]");
	By saveBtn = By.xpath(" //input[@value='Save']");
	By successMessage = By.xpath("//*[contains(text(),'Successfully Saved')]");
	
	
	public void addUser() {
		
		Helper.waitForWebElementAndClick(driver, adminTab, "Click on Admin Tab");
		Helper.waitForWebElementAndClick(driver, addButton, "Click on Add Button");
		Helper.waitForWebElementAndType(driver, empName, "John Smith", "Type Employee Name");
		Helper.waitForWebElementAndType(driver, username, "John123", "Emp Name");
		Helper.waitForWebElementAndType(driver, password, "Admin1234", "Type password");
		Helper.waitForWebElementAndType(driver, confirmPassword, "Admin1234", "Type password again");
		Helper.waitForWebElementAndClick(driver, saveBtn, "Click on Save button"); 
		Helper.verifyPartialMessages(driver, successMessage, "saved", "verify success message after adding new user");
 
	}

}
