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

public class CheckoutPage extends WaitingPage{

	WebDriver driver;
	
	@FindBy (css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy (xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;
	
	@FindBy (css=".action__submit ")
	WebElement placeOrder;
	
	By result = By.cssSelector(".ta-results");
	
	
	
	
	
public CheckoutPage(WebDriver driver)
{
	super (driver);
	this.driver= (driver);
	PageFactory.initElements((driver), this);
	
}

public void placeOrder (String countryName)
{
	Actions a = new Actions(driver);
	a.sendKeys(country, countryName).build().perform();
	waitForelementToAppear(result);
	selectCountry.click();
	
}

public AssertMessage purchase()
{
	placeOrder.click();
	
	AssertMessage confirmationmessage = new AssertMessage (driver);
	
	return confirmationmessage;
}


}


//Actions a = new Actions(driver);
//a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();

////wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results"))); // wait until option container is showing

//driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
//driver.findElement(By.cssSelector(".action__submit ")).click();  //click on place order


