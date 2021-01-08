package pageobjects;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Ecommerce_FormPage {
	

	public Ecommerce_FormPage(AndroidDriver<AndroidElement> driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	final String nameFieldId = "com.androidsample.generalstore:id/nameField";
	final String genderFemaleCBxPath = "//android.widget.RadioButton[@text='Female']";
	final String countryDDId = "com.androidsample.generalstore:id/spinnerCountry";
	final String countryScrollTo = "new UiScrollable(new UiSelector()).scrollIntoView(text(\"Aruba\"));";
	final String submitBtnXpath = "//android.widget.Button[@text=\"Let's  Shop\"]";
	
	
	@AndroidFindBy(id = nameFieldId)
	public AndroidElement nameFieldTB;
	
	@AndroidFindBy(xpath = genderFemaleCBxPath)
	public AndroidElement genderFemaleCB;
	
	@AndroidFindBy(id = countryDDId)
	public AndroidElement countryDD;
	
	@AndroidFindBy(uiAutomator = countryScrollTo)
	public AndroidElement countryToPick;
	
	@AndroidFindBy(xpath = submitBtnXpath)
	public AndroidElement submitBtn;

	public void enterName(AndroidDriver<AndroidElement> driver,String name) {
		// TODO Auto-generated method stub
		System.out.println("Trying to get name field");
		AndroidElement ae = nameFieldTB;
		System.out.println("Entrying the received name into the name field");
		ae.sendKeys(name);
		System.out.println("Hiding keyboard");
		driver.hideKeyboard();
		
	}
	
	
}
