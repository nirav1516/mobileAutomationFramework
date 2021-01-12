package mypractice.AppiumFramework;

import org.testng.annotations.DataProvider;

public class TestData {
	@DataProvider(name = "inputData")
	public Object getData() {
		Object[][] data = new Object[2][2];
		
		for (int i=0;i<data.length;i++) {
			for(int j=0;j<data[0].length;j++) {
				data[i][j] = (j%2==0)? "username"+i:"password"+i;
			}
		}
		return data;
		
	}

}
