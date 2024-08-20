package FrameworkPractice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitingPage {
	
	WebDriver driver;


public WaitingPage(WebDriver driver)
{
	
	this.driver= driver;
	
}
 public void waitForelementToAppear(By findBy)
 {
	 
	 WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
	 wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));// wait until all products are displayed
	 
 }
 
 public void waitForWebElementToAppear(WebElement findBy)
 {
	 
	 WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
	 wait.until(ExpectedConditions.visibilityOf(findBy));// wait until all products are displayed
	 
 }
 
 public void waitElementtodisappear(WebElement element) throws InterruptedException
 {
	 Thread.sleep(2000);
 }
}

//WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));// wait until all products are displayed