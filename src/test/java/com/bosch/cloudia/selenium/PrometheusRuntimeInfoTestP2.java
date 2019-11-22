/*
 * PrometheusRuntimeInfoTestP2.java
 *
 * Copyright (c) 2019 Robert Bosch GmbH. All Rights Reserved.
 */
package com.bosch.cloudia.selenium;

import java.util.Properties;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.bosch.cloudia.helper.OpenChromeBrowser;
import com.bosch.cloudia.helper.ReadProperties;

/**
 * This testcase opens Prometheus and checks for the Configuration Reload result
 * in Status -> RuntimeInformation section.
 *
 * @author Amit Kumar Sahu (amit-kumar.sahu@lhind.dlh.de)
 */
public class PrometheusRuntimeInfoTestP2 {
	private String url;
	private Properties properties;

	@Test
	public void prometheusRuntimeInfoTest() {

		/* Step 1: Initialization: Set the Prometheus url. */
		initializeProperties("prometheus.cloud.url");

		/* Step 2: Verify configuration reload was successful.*/
		verifyTableValues();

	}

	/*
	 * This method initializes the url for different prometheus urls passed.
	 */
	private void initializeProperties(String newUrl) {
		properties = new ReadProperties().getFile();
		url = properties.getProperty(newUrl);
	}

	/*
	 * This method clicks on status and then Runtime & Build Information, and
	 * verifies the Configuration reload was successful.
	 */
	private void verifyTableValues() {
		WebDriver driver = OpenChromeBrowser.openURL(url);

		/* Step 2a: Click on Status and then click on Runtime & Build Information. */
		driver.findElement(By.linkText("Status")).click();
		driver.findElement(By.linkText("Runtime & Build Information")).click();

		/*
		 * Step 2b: Locate the Runtime Information table and verify the value for
		 * Configuration Reload is Successful.
		 */
		WebElement table = driver.findElement(By.xpath("//h2[@id=\"runtime\"]/following-sibling::table[1]"));
		WebElement cell1 = table.findElement(By.xpath("//tr[3]/th"));
		Assertions.assertEquals("Configuration reload", cell1.getText());
		WebElement cell2 = table.findElement(By.xpath("//tr[3]/td"));
		Assertions.assertEquals("Successful", cell2.getText());

		driver.close();
	}

}
