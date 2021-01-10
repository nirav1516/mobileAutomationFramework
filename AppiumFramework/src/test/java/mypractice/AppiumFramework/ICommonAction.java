package mypractice.AppiumFramework;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public interface ICommonAction {
	@BeforeClass
	public void setPreRequisite() throws IOException, InterruptedException;
	
	@AfterClass
	public void terminateProcesses() throws IOException, InterruptedException;

}
