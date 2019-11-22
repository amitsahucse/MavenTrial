//package com.bosch.cloudia.helper;
//
//import org.junit.rules.TestWatcher;
//import org.junit.runner.Description;
//import org.openqa.selenium.WebDriver;
//
//public class TestWatcherRule extends TestWatcher {
//
////	WebDriver driver;
////	String fileName;
////	ScreenshotHelper screenshotHelper;
////	
////	public TestWatcherRule( WebDriver driver, String fileName) {
////		super();
////		this.driver = driver;
//////		this.fileName = fileName;
//////		
////	}
//
////	/**
////	 *  This method gets invoked if the test fails for any reason.
////	 */
////	@Override
////	protected void failed(Throwable e, Description description) {
////		/**
////		 *  Print out the error message:
////		 */
////		System.out.println(fileName +  "Testcase failed.");
////		
////		screenshotHelper = new ScreenshotHelper();
////		screenshotHelper.takeScreenshot(driver, fileName);
////		System.out.println("the Screenshot is taken");
////	}
////	
////	
//}
