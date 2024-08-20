package FrameworkPractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage extends WaitingPage {
	
	WebDriver driver ;
	
	@FindBy (id="userEmail")
	WebElement userEmail;
	
	@FindBy (id="userPassword")
	WebElement userPassword;
	
	@FindBy (id="login")
	WebElement loginbutton;
	
	@FindBy (css="div[aria-label=\"Incorrect email or password.\"]")
	WebElement errorMessage;
	
	
	public LoginPage(WebDriver driver)
	{
		super (driver);
		this.driver= (driver);
		PageFactory.initElements((driver), this);
	}
	
	public String getErrorMessage ()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
		
	}

	public void getLink()
	{
		
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public AddProductCatalogue doLogin(String Email, String password)
	{
		userEmail.sendKeys(Email);
		userPassword.sendKeys(password);
		loginbutton.click();
		
		AddProductCatalogue addproduct= new AddProductCatalogue(driver);
		return addproduct;
	}
	
	
}

