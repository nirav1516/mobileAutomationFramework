package utilities.gestures;

import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Scrollutility {
	AndroidDriver driver;
	public Scrollutility(AndroidDriver driver) {
		this.driver = driver;
	}
	
	public WebElement scrollToText(String dest_Text) {
		return driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+dest_Text+"\"));");
	}
}
