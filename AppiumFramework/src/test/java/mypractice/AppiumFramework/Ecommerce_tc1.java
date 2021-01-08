package mypractice.AppiumFramework;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Ecommerce_tc1 extends Setup{

	@Test
	public void testFormFill() throws IOException {
		// TODO Auto-generated method stub
		
		AndroidDriver<AndroidElement> driver = capabilities("ECOMMERCE_TEST_APP","PIXEL_3A_8");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
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
		Assert.assertTrue(true);
		driver.quit();
		
	}

}
