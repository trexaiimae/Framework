package Components;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import FrameworkPractice.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class baseTest {
	
	public  WebDriver driver;
	public  LoginPage loginpage;
	
	public WebDriver baseTest() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "\\src\\main\\java\\FrameworkPractice\\resources\\GlobalData.properties"); //convert the file to input stream
		prop.load(fis);
		String browserName = System.getProperty("browser")!=null? System.getProperty("browser") : prop.getProperty("browser");
		prop.getProperty("browser");
		
		if (browserName.contains("chrome"))
		{
		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
			
			if(browserName.contains("headless")) 
			{
				options.addArguments("headless");
			}
		
		driver = new ChromeDriver(options);
		driver.manage().window().setSize(new Dimension(1440,900));//help you run the headless chrome to max scrcee size
		
		}
		
		else if (browserName.contains("firefox"))
		{
			FirefoxOptions options = new FirefoxOptions();
			 WebDriverManager.firefoxdriver().setup();
				 
				if(browserName.contains("headless")) 
				{
					options.addArguments("--headless");
					
				}
				
		        driver = new FirefoxDriver(options);
		        driver.manage().window().setSize(new Dimension(1440,900));
		}
		
		else if (browserName.contains("edge"))
		{
			EdgeOptions options = new EdgeOptions();
			 WebDriverManager.edgedriver().setup();
			
				 if(browserName.contains("headless")) 
				{
					options.addArguments("headless");
				}
				
			
		        driver = new EdgeDriver(options);
		        driver.manage().window().setSize(new Dimension(1440,900));
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		return driver;
		
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
		{
		
		//read json to string
	String jsoncontent=	FileUtils.readFileToString(new File (filePath),StandardCharsets.UTF_8);
		
	//Convert String to Hasmap using Jackson Databind
	ObjectMapper mapper = new ObjectMapper ();
	List<HashMap<String, String>> data = mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String,String>>>(){
	});
	return data;
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException // create screenshot to failedtest
	{
		
		TakesScreenshot ts =  (TakesScreenshot) driver;
		File source= ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"\\reports\\" + testCaseName+ ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"\\reports\\" + testCaseName+ ".png";
		
		
	}
	
	
	@BeforeMethod(alwaysRun=true)
	public LoginPage launchapp() throws IOException
	{
		
		 driver = baseTest();
		 loginpage = new LoginPage (driver);
		 loginpage.getLink();
		 return loginpage;
		 
	} 
	
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.quit();
		
	}

}
