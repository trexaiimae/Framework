package FrameworkPractice;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class OrderPage extends WaitingPage{

	WebDriver driver;
	
	@FindBy (css="tr td:nth-child(3)")
	List <WebElement> OrderList;
	
	
	
	
public OrderPage(WebDriver driver)
{
	super (driver);
	this.driver= (driver);
	PageFactory.initElements((driver), this);
	
}

public Boolean checkOrderisDisplayedHistory(String productName)
{
	Boolean match= OrderList.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
	return match;

}



}
//check if the added product is really added in cart
//		List <WebElement> cartProducts= driver.findElements(By.cssSelector(".cartSection h3"));
//		Boolean match= cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));// iterate in the list of products in list and get the name and compare to the added name product
//		Assert.assertTrue(match);// apply validation
//		driver.findElement(By.cssSelector(".totalRow button")).click();

