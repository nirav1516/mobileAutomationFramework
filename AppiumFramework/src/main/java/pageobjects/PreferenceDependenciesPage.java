package pageobjects;

import java.util.List;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class PreferenceDependenciesPage {
	
	public PreferenceDependenciesPage(AndroidDriver<AndroidElement> driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "android:id/checkbox")
	public AndroidElement wifiCheckBox;
	
	@AndroidFindBy(xpath = "(//android.widget.RelativeLayout)[2]")
	public AndroidElement wifiSettingsMenu;
	
	@AndroidFindBy(className = "android.widget.EditText")
	public AndroidElement wifiTextBox;
	
	@AndroidFindBy(className = "android.widget.Button")
	public List<AndroidElement> settingButtons;
	
}
