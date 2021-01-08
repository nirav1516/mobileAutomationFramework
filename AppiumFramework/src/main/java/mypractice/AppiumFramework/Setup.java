package mypractice.AppiumFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class Setup {
	public static AndroidDriver<AndroidElement> capabilities(String appName, String deviceName) throws IOException {
		
		// TODO Auto-generated method stub
		File srcPath = new File("src");
		String nameOfPropertieFile = "global.properties";
		String pathToPropertiesFile = System.getProperty("user.dir") +"\\src\\main\\java\\mypractice\\AppiumFramework\\"+nameOfPropertieFile;
		FileInputStream propertiFile = new FileInputStream(pathToPropertiesFile);
		
		Properties prop = new Properties();
		prop.load(propertiFile);
		
		
		File apkPath = new File(srcPath,prop.getProperty(appName));
		String serverPath = "http://127.0.0.1:4723/wd/hub";
		URL serverURL = new URL(serverPath);
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, prop.getProperty(deviceName));
		cap.setCapability(MobileCapabilityType.APP, apkPath.getAbsolutePath());
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, Integer.parseInt(prop.getProperty("APPIUM_TIMEOUT")));
		AndroidDriver<AndroidElement> driver = new AndroidDriver<>(serverURL,cap);
		return driver;
	}
	
	public static void pritAwesome() {
		System.out.println("Awesome");
	}

}
