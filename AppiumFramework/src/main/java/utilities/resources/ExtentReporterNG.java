package utilities.resources;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
 
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

 
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

 
public class ExtentReporterNG implements IReporter {
    private ExtentReports extent;
    ExtentSparkReporter htmlReporter;
 
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
    	
       htmlReporter = new ExtentSparkReporter("./test-output/reports/htmlreport.html");
       extent = new ExtentReports();
        
        extent.attachReporter(htmlReporter);
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();
 
            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();
 
                buildTestNodes(context.getPassedTests(), Status.PASS);
                buildTestNodes(context.getFailedTests(), Status.FAIL);
                buildTestNodes(context.getSkippedTests(), Status.SKIP);
            }
        }
 
        extent.flush();
        
    }
 
    private void buildTestNodes(IResultMap tests, Status status) {
        ExtentTest test;
 
        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                test = extent.createTest(result.getName());
 
                /*test.getTest(). = getTime(result.getStartMillis());
                test.getTest().endedTime = getTime(result.getEndMillis());*/
 
                for (String group : result.getMethod().getGroups())
                    test.assignCategory(group);
 
                String message = "Test " + status.toString().toLowerCase() + "ed at time in millis"+result.getEndMillis();
 
                if (result.getThrowable() != null)
                    message = result.getThrowable().getMessage();
                
                String ssPath = (String) result.getAttribute("ssPath");
                if(ssPath!=null && !ssPath.isBlank()) {
                	System.out.println("Screnshot presetttttttttttttttttttttttttttttttttttttttttttttt");
                	test.addScreenCaptureFromPath(ssPath);
                }
 
                test.log(status, message);
 
          //      extent.endTest(test);
            }
        }
    }
 
    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();        
    }
}