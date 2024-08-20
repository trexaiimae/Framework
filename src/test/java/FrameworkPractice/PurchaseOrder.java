package FrameworkPractice;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import java.nio.charset.StandardCharsets;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Components.baseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class PurchaseOrder extends baseTest {
	
	
		@Test(dataProvider = "getData", groups= {"Purchase"})
		public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {
		
		AddProductCatalogue addproduct= loginpage.doLogin(input.get("email"),input.get("password"));
		
		//addproduct
		List <WebElement> products = addproduct.productList();
		CartPage cart = addproduct.addToCart(input.get("productName"));
		Boolean match = cart.checkAddedProduct(input.get("productName"));
		Assert.assertTrue(match);
		
		CheckoutPage checkoutpage =cart.submit();
		checkoutpage.placeOrder("India");
		AssertMessage confirmationmessage = checkoutpage.purchase();
		
		String confirm = confirmationmessage.verifyMessage();
		Assert.assertTrue(confirm.equalsIgnoreCase("THANKYOU FOR THE ORDER."));// assert the text ignoring case

	}

	//to verify the ZARA COAT 3 is displayed in orders page
	@Test(dataProvider = "getData", dependsOnMethods= {"submitOrder"}) // this will not run unless submit method is already run
	public void OrderHistory(HashMap<String, String> input)
	{
		AddProductCatalogue addproduct= loginpage.doLogin(input.get("email"),input.get("password"));
		OrderPage orderpage = addproduct.goToOrdersPage();
		Assert.assertTrue(orderpage.checkOrderisDisplayedHistory(input.get("productName")));
		
	}
	
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	
	{
		
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\Data\\PurchaseOrder.json");
		 return new Object [][] {{data.get(0)}, {data.get(1)},  {data.get(2)}};
		
	}

}














