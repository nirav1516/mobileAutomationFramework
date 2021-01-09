package mypractice.AppiumFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.net.PortProber;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class Setup {
	
	public static AppiumDriverLocalService service;
	public static Properties prop;
	
	public void startService() throws IOException {
		//APPIUM_PORT
		setProperties();
		
		if(!checkIfServiceRunning(Integer.parseInt(prop.getProperty("APPIUM_PORT")))) {
			System.out.println("Appium  NOT running, trying to start...");
			service = AppiumDriverLocalService.buildDefaultService();
			service.start();
		}
		System.out.println("Appium already running. Not starting again.");
		
	}
	
	public void stopService() {
		
		if(service!=null)service.stop();
	}
	
	
	
	public static AndroidDriver<AndroidElement> capabilities(String appName, String deviceName) throws IOException, InterruptedException {
		System.out.println("Setup.capabilities()");
		System.err.println("Erorrrrrrrrrrrrrrrrrrrr");
		// TODO Auto-generated method stub
		File srcPath = new File("src");
		
		File apkPath = new File(srcPath,prop.getProperty(appName));
		String serverPath = "http://127.0.0.1:4723/wd/hub";
		URL serverURL = new URL(serverPath);
		System.out.println("Chcking if device is an emulator:...............>> "+deviceName.toLowerCase().contains("emulator"));
		
		
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
	
	public boolean checkIfServiceRunning(int port) {
		boolean isServiceRunning = false;
	    ServerSocket socket;
	    try {
	      socket = new ServerSocket();
	      socket.setReuseAddress(true);
	      socket.bind(new InetSocketAddress("localhost", port));
	      socket.close();
	     
	    } catch (IOException e) {
	      isServiceRunning = true;
	    }
	    return isServiceRunning;
	}
	
	private static void setProperties() throws IOException {
		String nameOfPropertieFile = "global.properties";
		String pathToPropertiesFile = System.getProperty("user.dir") +"\\src\\main\\java\\mypractice\\AppiumFramework\\"+nameOfPropertieFile;
		FileInputStream propertiFile = new FileInputStream(pathToPropertiesFile);
		
		prop = new Properties();
		prop.load(propertiFile);
	}
	


}
