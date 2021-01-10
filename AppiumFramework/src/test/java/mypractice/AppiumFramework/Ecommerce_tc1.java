package mypractice.AppiumFramework;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageobjects.HomePage;
import pageobjects.PreferenceDependenciesPage;
import pageobjects.PreferencePage;

public class Ecommerce_tc1 extends Setup{
	public static AndroidDriver<AndroidElement> driver;
	
	@BeforeClass
	public void setPreRequisite() throws IOException, InterruptedException {
		System.err.println("Ecome1111111111111111111111111111111111");
		killAllProcess();
		startService();
		driver = capabilities("ECOMMERCE_TEST_APP","EMULATOR_PIXEL_3A_8");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}

	@Test(groups= {"healthCheck"})
	public void testFormFill() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		AndroidElement ae = driver.findElementById("com.androidsample.generalstore:id/nameField");
		ae.sendKeys("nirav");
		driver.hideKeyboard();
		
		ae = driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']"));
		ae.click();
		
		ae = driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry"));
		ae.click();
		
		ae = driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Barbados\"));");
		//new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"));
		//driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + containedText + "\").instance(0))")); 
		ae.click();
		
		ae = driver.findElementByXPath("//android.widget.Button[@text=\"Let's  Shop\"]");
		ae.click();
		
		//driver.quit();
		
		
	}
	
	@AfterClass
	public void terminateProcesses() throws IOException, InterruptedException{
		stopService();
	}

}
