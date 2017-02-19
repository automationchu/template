package utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class WebDriverUtils {
	
	public static WebDriver driver;
	public WebDriverWait wait;
	public Actions actions;
	public Select select;
	private static int timeout = 20;
		
	public WebDriverUtils(){
		driver = DriverUtils.getDriver();
		
	}
	
	public void navigateToURL(String uRL){
		try{
			driver.navigate().to(uRL);
		}catch(Exception e){
			System.out.println("FAILURE : URL DID NOT LOAD "+uRL);
		}
	}
	
	public void navigateBackTo(){
		try{
			driver.navigate().back();
		}catch (Throwable t){
			System.out.println("FAILURE : COULD NOT NAVIGATE BACK TO PREVIOUS PAGE");
		}
	}
	
	public WebElement getElement(By selector){
		try{
			return driver.findElement(selector);
		}catch(Exception e){
			//System.out.println(String.format("WebElement [%s] does not exist, selector"));
		}
		return null;
	}
	
	public String getElementText(By selector){
		try{
			waitUntilElementIsDisplayedOnScreen(selector);
			return StringUtils.trim(driver.findElement(selector).getText());
		}catch(Exception e){
			System.out.println(String.format("Element does not exist : %s", selector));
		}
			return null;
	}
	
	public void waitUntilElementIsDisplayedOnScreen(By selector){
		try{
			wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
		}catch(Exception e){
			throw new NoSuchElementException(String.format("No such element found: %s", selector));
		}
	}
	
	public void click(By selector){
		WebElement element = getElement(selector);
		waitForElementToBeClickable(selector);
		try{
			element.click();
		}catch (Exception e){
			throw new NoSuchElementException(String.format("Element is not clickable: [%s]", selector));
		}
	}
	
	public void waitForElementToBeClickable(By selector) {
        try {
            wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.elementToBeClickable(selector));
        } catch (Exception e) {
            System.out.println(("The following element is not clickable: " + selector));
        }
    }
	
	public void waitForElementToBeVisible(By selector){
		try{
			wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.presenceOfElementLocated(selector));
		}catch (Exception e) {
            throw new NoSuchElementException(String.format("The following element was not visible: %s", selector));
        }
	}
	
	public List<WebElement> getElements(By selector){
		waitForElementToBeVisible(selector);
			try{
				return driver.findElements(selector);
			}catch (Exception e){
				throw new NoSuchElementException(String.format("The following element did not display %s ", selector.toString()));
	 		}
	}
	
	public String getPageTitle() {
        try {
            return driver.getTitle();
        } catch (Exception e) {
        	throw new NoSuchElementException(String.format("Current page title is: %s", driver.getTitle()));
        }
    }
	
	public void sendKeys(By selector, String value) {
        WebElement element = getElement(selector);
        clearField(element);
        try {
            element.sendKeys(value);
        } catch (Exception e) {
            throw new NoSuchElementException(String.format("Error in sending [%s] to the following element: [%s]", value, selector.toString()));
        }
    }
	
	public void clearField(WebElement element) {
        try {
            element.clear();
            waitForElementTextToBeEmpty(element);
        } catch (Exception e) {
            System.out.print(String.format("The following element could not be cleared: [%s]", element.getText()));
        }
    }
	
	public void waitForElementTextToBeEmpty(WebElement element) {
        String text;
        try {
            text = element.getText();
            int maxRetries = 10;
            int retry = 0;
            while ((text.length() >= 1) || (retry < maxRetries)) {
                retry++;
                text = element.getText();
            }
        } catch (Exception e) {
            System.out.print(String.format("The following element could not be cleared: [%s]", element.getText()));
        }

    }
		
	
}
