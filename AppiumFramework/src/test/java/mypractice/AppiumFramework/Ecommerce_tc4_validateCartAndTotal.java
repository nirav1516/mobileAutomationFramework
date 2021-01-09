package mypractice.AppiumFramework;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.Locator;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageobjects.Ecommerce_FormPage;

public class Ecommerce_tc4_validateCartAndTotal extends Setup{
	HashSet<String> shoeNames = new HashSet<>();
	float totalAmount = 0;

	@Test
	public void testValidationCart() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		startService();
		AndroidDriver<AndroidElement> driver = capabilities("ECOMMERCE_TEST_APP","EMULATOR_PIXEL_3A_8");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Ecommerce_FormPage eFP = new Ecommerce_FormPage(driver);
		
		eFP.enterName("Nirav");
	
		AndroidElement ae = eFP.genderFemaleCB;
		ae.click();
		
		ae = eFP.countryDD;
		ae.click();
		
		ae = eFP.scrollToCountry("Aruba");
		//new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"));
		//driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + containedText + "\").instance(0))")); 
		ae.click();
		
		ae = eFP.submitBtn;
		ae.click();
		
		String shoesText = "Converse All Star";
		String secondShoeText = "Air Jordan 4 Retro";
		Ecommerce_tc4_validateCartAndTotal ev = new Ecommerce_tc4_validateCartAndTotal();
		ev.addToCart(ae,driver,shoesText);
		System.out.println("Total so far: "+ev.totalAmount);
		ev.addToCart(ae,driver,secondShoeText);
		System.out.println("Total so far: "+ev.totalAmount);
		ev.validateItemsInCart(ae, driver);
		ev.validateTotalAmountInCart(ae, driver);
		stopService();
		
		
	}
	
	public void addToCart(AndroidElement ae,AndroidDriver<AndroidElement> driver,String shoesText) {
		ae = driver
                .findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()"
                + ".resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView("
                + "new UiSelector().text(\""+ shoesText +"\"));");
		
		//Find parent of this shoeText
		AndroidElement parentElement = driver.findElement(By.xpath("//*[@text='"+ shoesText +"']/parent::android.widget.LinearLayout[1]"));
		
		//below commented part is another approch to get parent, first get list and then get the fisrt parent
		//List<MobileElement> lae = ae.findElements(By.xpath("./parent::*"));
		
		//System.out.println("the size: " +lae.size());
		//ae = lae.get(0);
		
		//once parent is found, search within parent to find Add to cart 
		System.out.println(ae.getAttribute("class"));
		ae = (AndroidElement) parentElement.findElement(By.xpath("//*[@text='ADD TO CART']"));
		System.out.println(ae.getAttribute("class"));
		ae.click();
		
		ae = (AndroidElement) parentElement.findElement(By.xpath("//*[@resource-id='com.androidsample.generalstore:id/productPrice']"));
		String sPrice = ae.getText().substring(1);
		float fPrice = Float.parseFloat(sPrice);
		totalAmount+=fPrice;
		shoeNames.add(shoesText);
		
		/*
		//com.androidsample.generalstore:id/appbar_btn_cart
		ae = driver.findElementById("com.androidsample.generalstore:id/appbar_btn_cart");
		ae.click();
		//Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver,10,1000);
		ae = (AndroidElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='"+ shoesText +"']") ));
		//ae = (AndroidElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression))
		//ae = driver.findElement(By.xpath("//android.widget.TextView[@text='"+ shoesText +"']"));
		Assert.assertEquals(shoesText, ae.getText());
		System.out.println(ae.getText());
		
		ae = driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_back"));
		ae.click();*/
	}
	
	public void validateItemsInCart(AndroidElement ae, AndroidDriver<AndroidElement> driver) {
		navigateToCart(ae, driver);
		
		//List<AndroidElement> lae = driver.findElementsById("com.androidsample.generalstore:id/productName");
		//List<WebElement> lae = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("com.androidsample.generalstore:id/productName")));
		List<AndroidElement> lae = driver.findElements(By.id("com.androidsample.generalstore:id/productName"));
		for(int i=0;i<lae.size();i++) {
			System.out.println(lae.get(i).getText());
			Assert.assertTrue(shoeNames.contains(lae.get(i).getText()));
		}
		
	}
	
	public boolean isElementPresent(AndroidDriver<AndroidElement> driver, By locator) {
		try {
			AndroidElement ae = driver.findElement(locator);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	public void navigateToCart(AndroidElement ae, AndroidDriver<AndroidElement> driver) {
		By cartBy = By.xpath("//android.widget.TextView[@text='Cart']");
		if(!isElementPresent(driver, cartBy)) {
			ae = driver.findElementById("com.androidsample.generalstore:id/appbar_btn_cart");
			ae.click();
		}
		WebDriverWait wait = new WebDriverWait(driver,20,1000);
		
		//List<AndroidElement> lae = driver.findElementsById("com.androidsample.generalstore:id/productName");
		ae = (AndroidElement) wait.until(ExpectedConditions.visibilityOfElementLocated(cartBy));
	}
	
	public void validateTotalAmountInCart(AndroidElement ae, AndroidDriver<AndroidElement> driver) {
		//com.androidsample.generalstore:id/totalAmountLbl
		navigateToCart(ae, driver);
		ae = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl"));
		String sPrice = ae.getText().substring(2);
		System.out.println("cartTotal: "+sPrice);
		float fPrice = Float.parseFloat(sPrice);
		Assert.assertEquals(fPrice, totalAmount,0.00);
		
		
	}

}
