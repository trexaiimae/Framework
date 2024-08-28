package StepDefination;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Components.baseTest;
import FrameworkPractice.AddProductCatalogue;
import FrameworkPractice.AssertMessage;
import FrameworkPractice.CartPage;
import FrameworkPractice.CheckoutPage;
import FrameworkPractice.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDef extends baseTest {
		
	public LoginPage loginpage;
	public AddProductCatalogue addproduct;
	public AssertMessage confirmationmessage;
	
	@Given ("I landed on Ecommerce Page")
	public void i_landed_on_ecommerce_page() throws IOException
	{
		loginpage = launchapp();
		  throw new io.cucumber.java.PendingException();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password)
	{
		
		AddProductCatalogue addproduct= loginpage.doLogin(username,password);
		throw new io.cucumber.java.PendingException();
	}
	
	   
	@When ("^I add product (.+) to  cart$")
	public void i_add_poduct_to_cart(String productName) throws InterruptedException
	{
		   
		   List <WebElement> products = addproduct.productList();
		   addproduct.addToCart(productName);
		   throw new io.cucumber.java.PendingException();
	}
	
	@When("^Checkout (.+) and sbuimt the order$")
	public void checkout_and_submit_the_order(String productName) throws InterruptedException
	{
		
		CartPage cart = addproduct.addToCart(productName);
		Boolean match = cart.checkAddedProduct(productName);
		Assert.assertTrue(match);
		
		CheckoutPage checkoutpage =cart.submit();
		checkoutpage.placeOrder("India");
		AssertMessage confirmationmessage = checkoutpage.purchase();
		 throw new io.cucumber.java.PendingException();
	}
	

	 @Then ("{string} message is displayed on ConfirmationPage")
	 public void message_displayed_on_confirmationPage(String string)
	 {
		 
		String confirm = confirmationmessage.verifyMessage();
		Assert.assertTrue(confirm.equalsIgnoreCase(string));// assert the text ignoring case
		throw new io.cucumber.java.PendingException();
	 }
	
	
}


