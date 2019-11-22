/*
 * ScreenshotHelper.java
 *
 * Copyright (c) 2019 Robert Bosch GmbH. All Rights Reserved.
 */
package com.bosch.cloudia.helper;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotHelper {
	
	
	public void takeScreenshot(WebDriver driver, String fileName)
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		System.out.println("Reached Screenshothelper.");
		File source = ts.getScreenshotAs(OutputType.FILE);
		
		DateFormat dateFormat = new SimpleDateFormat("-yyyy-MM-dd_HH-mm-ss");
		Date date = new Date();
		String dateAndTime = dateFormat.format(date);
		
		String newFileName = fileName + dateAndTime + ".png";
		String filePath = "./screenshots/"+fileName + "/" + newFileName;
		
		try {
			FileUtils.copyFile(source, new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("the Screenshot is taken");
	}

}
