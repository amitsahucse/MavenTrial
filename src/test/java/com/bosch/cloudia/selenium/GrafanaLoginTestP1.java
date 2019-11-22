/**
 * GrafanaLoginTestP1.java
 *
 * Copyright (c) 2019 Robert Bosch GmbH. All Rights Reserved.
 */

package com.bosch.cloudia.selenium;

import java.util.Properties;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.bosch.cloudia.helper.OpenChromeBrowser;
import com.bosch.cloudia.helper.ReadProperties;

/**
 * This testcase logs in to Grafana and checks for the text Home Dashboard.
 * @author Amit Kumar Sahu (amit-kumar.sahu@lhind.dlh.de) 
 */

public class GrafanaLoginTestP1 {
	
	String url,user,password;
	
	@Test
	public void grafanaLoginMethod() {

		initializeLoginDetails();
		
		WebDriver driver = OpenChromeBrowser.openURL(url);
		driver.findElement(By.name("user")).sendKeys(user);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.tagName("button")).click();
		String buttonText = driver.findElement(By.xpath("//div[contains(@class, 'dashboard-header')]")).getText();
		Assertions.assertEquals("Home Dashboard", buttonText);

		driver.close();
	}

	/*
	 * This method reads Url, Username and Password for Grafana from properties
	 * file.
	 */
	private void initializeLoginDetails()
	{
		Properties properties = new ReadProperties().getFile();
		 url = properties.getProperty("grafana.url");
		 user = properties.getProperty("grafana.username");
		 password = properties.getProperty("grafana.password");

	}
}
