package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
//import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class Helper {

	
	public void gitMethod() {
		
		System.out.println("GitHUb");
	}
	
	//JUST WAIT METHOD using THREAD CLASS//**********************************************************
	public static void jutWait(int timeInSeconds, String stepInfo) {
		
		try {
			Thread.sleep(1000 * timeInSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Reporter.log("LOG INFO: " + stepInfo, true);
	}
	
	// ACTION CLASS METHODS****************************************************************************
	public static void waitForWebElementAndPerformDragNDrop(WebDriver driver, By srcLocator, By destLocator,String stepInfo) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(srcLocator));
		WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(destLocator));
		Actions act = new Actions(driver);
		act.dragAndDrop(element1, element2).perform();// Perform method is a must when using Actions class.
		Reporter.log("LOG INFO: " + stepInfo, true);

	}

	public static void waitForWebElementAndPerformDragNDropInSteps(WebDriver driver, By srcLocator, By destLocator,int waitTimeInSeconds, String stepInfo) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(srcLocator));
		WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(destLocator));
		Actions act = new Actions(driver);
		// act.clickAndHold(element1).pause(waitTimeInSeconds).moveToElement(element2).pause(waitTimeInSeconds).release(element2).build().perform();
		act.clickAndHold(element1).pause(Duration.ofSeconds(waitTimeInSeconds)).moveToElement(element2)
				.pause(Duration.ofSeconds(waitTimeInSeconds)).build().perform();
		
		Reporter.log("LOG INFO: " + stepInfo, true);

	}

	public static void waitForWebElementAndPerformDragNDropWithXY(WebDriver driver, By srcLocator, int XCordinates,int YCordinates, String stepInfo) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(srcLocator));
		Actions act = new Actions(driver);
		act.dragAndDropBy(element1, XCordinates, YCordinates).perform();// Perform method is a must when using Actions
																		// class.
		
		Reporter.log("LOG INFO: " + stepInfo, true);
	}

	public static void waitForWebElementAndMouseHoverToElementAndClick(WebDriver driver, By srcLocator, By destLocator,int waitTimeInSeconds, String stepInfo) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(srcLocator));
		WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(destLocator));
		Actions act = new Actions(driver);
		act.moveToElement(element1).pause(Duration.ofSeconds(waitTimeInSeconds)).click(element2).build().perform();
		
		Reporter.log("LOG INFO: " + stepInfo, true);
	}

	public static void waitForWebElementAndJustMouseHoverToElement(WebDriver driver, By srcLocator,int waitTimeInSeconds, String stepInfo) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(srcLocator));
		Actions act = new Actions(driver);
		act.moveToElement(element1).pause(Duration.ofSeconds(waitTimeInSeconds)).build().perform();
	}
	
		
	//*****************************JAVASCRIPT METHODS//*************************************************
		
	//HIGHLIGHTING WEBELEMENT with JS
	public static void highlightElement(WebDriver driver, WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
		try {
			Thread.sleep(300);

		} catch (InterruptedException e) {

			System.out.println(e.getMessage());
		}

		js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid white');", element);
	}

	// WAIT FOR WEBELEMENT AND CLICK USING JS - METHOD
	public static WebElement waitForWebElementAndClickUsingJS(WebDriver driver, By locator, String stepInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 15);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		highlightElement(driver, element);
		// element.click();

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(locator));
		Reporter.log("LOG:INFO-JavaScript Click Operation" + stepInfo, true);
		return element;

	}

	// WAIT FOR WEBELEMENT AND TYPE USING JS - METHOD
	public static WebElement waitForWebElementAndTypeUsingJS(WebDriver driver, By locator, String data,
			String stepInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 15);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		highlightElement(driver, element);
		// element.sendKeys(data);

		((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1];", driver.findElement(locator),
				data);
		Reporter.log("LOG:INFO-" + stepInfo, true);
		return element;
	}
	
	//**************************************************************************************

	// AUTO-SUGGESTION METHOD
	// Below method can be used for calendar, auto-suggestion, webtables and any
	// other scenario where multiple options are present
	public static void waitForAutoSuggestionAndSelect(WebDriver driver, By locator, String text, String stepInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 20);
		List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		for (WebElement ele : elements) {
			if (ele.getText().contains(text)) {
				// highLightWebElement(driver, ele);
				ele.click();
				break;
			}
		}
		Reporter.log("LOG INFO: " + stepInfo, true);
	}

	//********************************WAIT FOR WEB-ELEMENT METHODS**********************************************************
	public static WebElement waitForWebElement(WebDriver driver, By locator, String stepInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 15);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		highlightElement(driver, element);
		return element;

	}

	public static WebElement waitWithTimeForWebElement(WebDriver driver, By locator, int time, String stepInfo) {

		WebDriverWait wait = new WebDriverWait(driver, time);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		highlightElement(driver, element);
		return element;
	}

	// WAIT FOR WEBELEMENT AND CLICK - METHOD
	public static WebElement waitForWebElementAndClick(WebDriver driver, By locator, String stepInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 15);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		highlightElement(driver, element);
		element.click();

		Reporter.log("LOG:INFO-" + stepInfo, true);
		return element;
	}

	// WAIT FOR WEBELEMENT AND TYPE - METHOD
	public static WebElement waitForWebElementAndType(WebDriver driver, By locator, String data, String stepInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 15);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		highlightElement(driver, element);
		element.sendKeys(data);

		Reporter.log("LOG:INFO-" + stepInfo, true);
		return element;
	}
	
	
	public static WebElement waitForWebElementAndClearInputField(WebDriver driver, By locator, String stepInfo) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		highlightElement(driver, element);
		element.clear();
		Reporter.log("LOG INFO: " + stepInfo, true);
		return element;
	}

	//******************************** ALERTS METHODDS**********************************************
	public static void waitForAlertAndAccept(WebDriver driver, String stepInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.alertIsPresent()).accept();
		Reporter.log("LOG:INFO-" + stepInfo, true);

	}

	public static void waitForAlertAndDismiss(WebDriver driver, String stepInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.alertIsPresent()).dismiss();
		Reporter.log("LOG:INFO-" + stepInfo, true);
	}

	public static void waitForAlertAndVerifyText(WebDriver driver, String expectedAlertMessage, String stepInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 15);
		Assert.assertEquals(wait.until(ExpectedConditions.alertIsPresent()).getText(), expectedAlertMessage);
		Reporter.log("LOG:INFO-" + stepInfo, true);
	}

	public static void waitForAlertAndVerifyTextPartially(WebDriver driver, String expectedPartialAlertMessage,
			String stepInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 15);
		Assert.assertTrue(
				wait.until(ExpectedConditions.alertIsPresent()).getText().contains(expectedPartialAlertMessage));
		Reporter.log("LOG:INFO-" + stepInfo, true);

	}
	
	//*************************** METHODS FOR SELECTING DROPDOWN VALUES**********************************
	public static void waitForWebElementAndSelectValues(WebDriver driver, By locator, String visibleText,
			String stepInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 15);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		highlightElement(driver, element);

		Select sel = new Select(element);
		sel.selectByVisibleText(visibleText);
		Reporter.log("LOG:INFO-" + stepInfo, true);

	}

	public static void waitForWebElementAndSelectWithValue(WebDriver driver, By locator, String value,
			String stepInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 15);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		highlightElement(driver, element);

		Select sel = new Select(element);
		sel.selectByValue(value);
		Reporter.log("LOG:INFO-" + stepInfo, true);

	}

	public static void waitForWebElementAndSelectIndex(WebDriver driver, By locator, int index, String stepInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 15);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		highlightElement(driver, element);

		Select sel = new Select(element);
		sel.selectByIndex(index);
		Reporter.log("LOG:INFO-" + stepInfo, true);

	}

	//*************************** VERIFY TEXTS METHODS**************************************************************
	public static void verifyCompleteMessages(WebDriver driver, By locator, String text, String stepInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 15);
		Assert.assertTrue(
				wait.until(ExpectedConditions.elementToBeClickable(locator)).getText().equalsIgnoreCase(text));
		Reporter.log("LOG:INFO-" + stepInfo, true);
	}

	public static void verifyPartialMessages(WebDriver driver, By locator, String partialText, String stepInfo) {

		WebDriverWait wait = new WebDriverWait(driver, 15);
		Assert.assertTrue(wait.until(ExpectedConditions.elementToBeClickable(locator)).getText().contains(partialText));
		Reporter.log("LOG:INFO-" + stepInfo, true);
	}
	
	//*********************************************************************************************************

	public static void wait(int time) {

		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static WebElement waitForWebElement(WebDriver driver, String xpath, int time) {

		WebElement element = null;

		for (int i = 0; i < time; i++) {

			try {
				element = driver.findElement(By.xpath(xpath));
				break;

			} catch (Exception e) {
				try {
					Thread.sleep(1000);
					System.out.println("Waiting for webElement to appear ");
				} catch (InterruptedException e1) {

				}
			}
		}

		return element;

	}

	public static String getCurrentDateTime() {

		/*
		 * 
		 * SimpleDateFormat myDateFormat = new SimpleDateFormat("HH_mm_ss_dd_MM-yyyy");
		 * Date d = new Date(); String date = myDateFormat.format(d); return date;
		 * 
		 **/

		return new SimpleDateFormat("HH_mm_ss_dd-MMM-yyyy").format(new Date());
	}
	
	//********************************CAPTURE SCREENSHOT METHOD**********************************************
	public static String captureScreenshot(WebDriver driver) {

		String path = System.getProperty("user.dir") + "/Screenshots/" + Helper.getCurrentDateTime() + ".png";

		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(path);

		try {
			FileHandler.copy(src, dest);
		} catch (IOException e) {
			System.out.println("Got exception here>> " + e.getMessage());
		}

		return path;

	}

}
