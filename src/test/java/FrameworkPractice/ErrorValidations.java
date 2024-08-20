package FrameworkPractice;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import Components.Retry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Components.baseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidations extends baseTest {
		
		@Test (groups= {"ErrorHandling"},retryAnalyzer=Retry.class)// tag this test to ErrorHandling Group name
		public void ErrorValidation() throws InterruptedException, IOException {
		loginpage.doLogin("trexaiihurtado@gmai.com", "Admin@12334");
		Assert.assertEquals("Incorrect email or passwordsss.", loginpage.getErrorMessage());

}
		@Test(groups= {"ErrorHandling"})
		public void producterror() throws InterruptedException, IOException {
			String productName= "ZARA COAT 3";// initialize product name globally
			AddProductCatalogue addproduct= loginpage.doLogin("trexaiihurtado@gmai.com", "Admin@123");
			List <WebElement> products = addproduct.productList();
			CartPage cart = addproduct.addToCart(productName);
			
			Boolean match = cart.checkAddedProduct("ZARA COAT 3");
			Assert.assertTrue(match);
			
	
		
	}

}
