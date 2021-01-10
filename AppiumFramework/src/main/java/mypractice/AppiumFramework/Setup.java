package mypractice.AppiumFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.net.PortProber;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class Setup {
	
	public static AppiumDriverLocalService service;
	public static Properties prop;
	public static AndroidDriver<AndroidElement> driver;
	
	public void startService() throws IOException {
		//APPIUM_PORT
		setProperties();
		
		if(!checkIfServiceRunning(Integer.parseInt(prop.getProperty("APPIUM_PORT")))) {
			System.out.println("Appium  NOT running, trying to start...");
			service = AppiumDriverLocalService.buildDefaultService();
			service.start();
		}else {
			System.out.println("Appium already running. Not starting again.");
		}
		
		
	}
	
	public void killAllProcess() throws IOException, InterruptedException {
		Runtime.getRuntime().exec("taskkill /f /im node.exe");
		Thread.sleep(3000);
	}
	
	public void stopService() {
		
		if(service!=null && service.isRunning()) {
			System.out.println("Setup.stopService()-------------------------> Sercice Running, Trying to Stop");
			service.stop();
		}else {
			System.out.println("Setup.stopService()-------------------------> Service Already stopped.");
		}
	}
	
	
	
	public static AndroidDriver<AndroidElement> capabilities(String appName, String deviceName) throws IOException, InterruptedException {
		System.out.println("Setup.capabilities()");
		//System.err.println("Erorrrrrrrrrrrrrrrrrrrr");
		// TODO Auto-generated method stub
		File srcPath = new File("src");
		
		File apkPath = new File(srcPath,prop.getProperty(appName));
		String serverPath = "http://127.0.0.1:4723/wd/hub";
		URL serverURL = new URL(serverPath);
		System.out.println("Chcking if device is an emulator:...............>> "+deviceName.toLowerCase().contains("emulator"));
		if(deviceName.toLowerCase().contains("emulator"))
			startEmulator(prop.getProperty(deviceName));
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, prop.getProperty(deviceName));
		cap.setCapability(MobileCapabilityType.APP, apkPath.getAbsolutePath());
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, Integer.parseInt(prop.getProperty("APPIUM_TIMEOUT")));
		driver = new AndroidDriver<>(serverURL,cap);
		return driver;
	}
	
	public static void pritAwesome() {
		System.out.println("Awesome");
	}
	
	//Check port prober implementation
	public boolean checkIfServiceRunning(int port) {
		boolean isServiceRunning = false;
	    ServerSocket socket;
	    try {
	      socket = new ServerSocket(port);
	     // socket.setReuseAddress(true);
	     // socket.bind(new InetSocketAddress("localhost", port));
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
	
	public static void startEmulator(String emulatorName) throws IOException, InterruptedException {
		String nameOfBatchFile = "startEmulator.bat";
		//D:\AppiumLearnings\mobileAutomationFramework\AppiumFramework\src\main\java\\utilities\resources\startEmulator.bat
		String pathToBatchFile = System.getProperty("user.dir") +"\\src\\main\\java\\utilities\\resources\\"+nameOfBatchFile;
		if (!emulatorName.isBlank() && !emulatorName.isEmpty())
			pathToBatchFile = pathToBatchFile +" "+emulatorName;
		Runtime.getRuntime().exec(pathToBatchFile);
		Thread.sleep(8000);
	}
	
	public static String getScreenShot(String testCaseName) throws IOException {
		
		  
		File ssFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		StringBuilder sB = new StringBuilder();
		sB.append("./test-output/screenshots/");
		sB.append(testCaseName);
		sB.append("_");
		sB.append(System.currentTimeMillis());
		sB.append(".png");
		
		String finalFilePath = sB.toString();
		File outputFile = new File(finalFilePath);
		System.out.println("Setup.getScreenShot()---------------------------->>>");
		System.out.println(outputFile.getAbsolutePath());
		FileUtils.copyFile(ssFile, outputFile);
		return outputFile.getAbsolutePath();
	}
	
	
	

}
