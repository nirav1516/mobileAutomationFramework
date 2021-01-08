package pageobjects;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage {

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Preference']")
	public AndroidElement PrefernceMenu;
	
	
	
	public HomePage(AndroidDriver<AndroidElement> driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public void clickPreferenceMenu() {
		PrefernceMenu.click();
	}
}
