package FrameworkPractice;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AddProductCatalogue extends WaitingPage{

	WebDriver driver;
	
	@FindBy (css=".col-lg-4")
	List <WebElement> products;
	
	@FindBy (css=".ng-animating")
	WebElement spinner;
	
	@FindBy (css="[routerlink*='cart']")
	WebElement goCart;
	
	@FindBy (css="[routerlink*='myorders']")
	WebElement goToOrder;
	
	

	By productsLoc = By.cssSelector(".col-lg-4");
	By addButton = By.cssSelector(".card-body button:last-of-type");
	By toastmessage = By.cssSelector("#toast-container");
	By submitButton= By.cssSelector("[routerlink*=\"cart\"]");
	
	
	
	
	
public AddProductCatalogue(WebDriver driver)
{
	super (driver);
	this.driver= (driver);
	PageFactory.initElements((driver), this);
	
}

public List <WebElement> productList ()
{
	waitForelementToAppear(productsLoc);
	return products;
	
}


public CartPage addToCart(String productName) throws InterruptedException
{
	WebElement prod= products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
	prod.findElement(addButton).click();
	waitForelementToAppear(toastmessage);
	waitElementtodisappear(spinner);
	Thread.sleep(1000);
	goCart.click();
	
	CartPage cart = new CartPage(driver);
	return cart;

}

public OrderPage goToOrdersPage()

{
	goToOrder.click();
	OrderPage orderpage = new OrderPage(driver);
	return orderpage;
}
}


