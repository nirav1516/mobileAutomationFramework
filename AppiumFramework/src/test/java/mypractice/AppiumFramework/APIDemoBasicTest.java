package mypractice.AppiumFramework;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import junit.framework.Assert;
import pageobjects.HomePage;
import pageobjects.PreferenceDependenciesPage;
import pageobjects.PreferencePage;

public class APIDemoBasicTest extends Setup{
	public static AndroidDriver<AndroidElement> driver;
	static HomePage hP;
	static PreferencePage pP;
	static PreferenceDependenciesPage pDP;
	
	
	
	
	@BeforeMethod
	public void setPreRequisite() throws IOException, InterruptedException {
		System.err.println("APIDEMOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
		killAllProcess();
		startService();
		driver = capabilities("DEMO_API_TEST_APP","EMULATOR_PIXEL_3A_8");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		hP = new HomePage(driver);
		pP = new PreferencePage(driver);
		pDP = new PreferenceDependenciesPage(driver);
		
	}

	@Test(groups = {"smoke","healthCheck"},dataProvider = "inputData", dataProviderClass = TestData.class)
	public void testBasicApiDemo(String userName,String passWord) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		
		AndroidElement e;
		hP.clickPreferenceMenu();
		
		
		e=  pP.DependeciesMenu;
		e.click();
		
		e = pDP.wifiCheckBox;
		e.click();
		
		e=  pDP.wifiSettingsMenu;//driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]"));//driver.findElementByXPath("(//android.widget.RelativeLayout)[2]");
		e.click();
		
		e = pDP.wifiTextBox;//driver.findElement(By.className("android.widget.EditText"));
		e.setValue(userName);
		 
		//e= driver.findElement(By.id("android:id/button1"));
		
		List<AndroidElement> eL = pDP.settingButtons;//driver.findElementsByClassName("android.widget.Button");
		e = eL.get(1);
		e.click();
		
		
	}
	
	@AfterClass
	public void terminateProcesses() throws IOException, InterruptedException{
		stopService();
	}
	
	

}
