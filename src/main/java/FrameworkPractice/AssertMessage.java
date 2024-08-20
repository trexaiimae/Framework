package FrameworkPractice;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class AssertMessage extends WaitingPage{

	WebDriver driver;
	
	@FindBy (css=".hero-primary")
	WebElement purhcasemessage;

	
	
public AssertMessage(WebDriver driver)
{
	super (driver);
	this.driver= (driver);
	PageFactory.initElements((driver), this);
	
}
public String verifyMessage ()
{
	return purhcasemessage.getText();

}
}

//String confirmMessage= driver.findElement(By.cssSelector(".hero-primary")).getText();
//Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));// assert the text ignoring case

