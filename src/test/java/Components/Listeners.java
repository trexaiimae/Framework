package Components;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import FrameworkPractice.resources.ExtentReporterNG;

public class Listeners extends baseTest implements ITestListener{

	ExtentReports extent= ExtentReporterNG.getReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> safeThread = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = extent.createTest(result.getMethod().getMethodName());// grab the name of the test case every iteration
		safeThread.set(test);// assign unique thread id to every data in iteration
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		safeThread.get().log(Status.PASS, "Test Passed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
	
		safeThread.get().fail(result.getThrowable()); // get why it fail and which method or thread ID
		
//		 baseTest base = (baseTest) result.getInstance();
//		 WebDriver driver = base.driver; // get the driver object from the baseTest instance
//		    
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
		 
		
		//Screenshot and attach to the report
		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		safeThread.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}

}
