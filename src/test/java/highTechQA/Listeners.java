package highTechQA;

import org.testng.ITest;
import org.testng.ITestListener;

public class Listeners implements ITestListener{
	//This is for capturing failed or passed Test cases with name
	//Call this class at xml file after <suite> tag using <listener> tag
	
	public void onTestFailure(ITest result)
	{
		System.out.println("I failed" +result.getTestName());
	}
	
}

