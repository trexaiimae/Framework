package FrameworkPractice;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class CartPage extends WaitingPage{

	WebDriver driver;
	
	@FindBy (css=".cartSection h3")
	List <WebElement> CartProdList;
	
	@FindBy (css=".totalRow button")
	WebElement PlaceOrder;
	
	
	
	
public CartPage(WebDriver driver)
{
	super (driver);
	this.driver= (driver);
	PageFactory.initElements((driver), this);
	
}

public Boolean checkAddedProduct(String productName)
{
	Boolean match= CartProdList.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
	return match;

}

public CheckoutPage submit()
{
	PlaceOrder.click();
	
	CheckoutPage checkoutpage = new CheckoutPage (driver);
	return checkoutpage;
}

}
//check if the added product is really added in cart
//		List <WebElement> cartProducts= driver.findElements(By.cssSelector(".cartSection h3"));
//		Boolean match= cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));// iterate in the list of products in list and get the name and compare to the added name product
//		Assert.assertTrue(match);// apply validation
//		driver.findElement(By.cssSelector(".totalRow button")).click();

