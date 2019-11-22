/**
 * PrometheusStatusTestCloudP1.java
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
 * This testcase opens Prometheus cloud url and checks for the Target Status
 * should not be unhealthy.
 *
 * @author Amit Kumar Sahu (amit-kumar.sahu@lhind.dlh.de)
 * @author Tobias Mahncke (tobias.mahncke@lhind.dlh.de)
 */
public class PrometheusStatusTestCloudP1 {
	private String url;
	private Properties properties;

	@Test
	public void prometheusStatusTest() {

		/**
		 * Step 1 and 2: Checks the prometheus-cloud-url and verifies no unhealthy
		 * targets are present.
		 */
		initializeProperties("prometheus.cloud.url");
		verifyTargetsAreHealthy();

	}

	/**
	 * This method initializes the url for different prometheus urls passed.
	 */
	private void initializeProperties(String newUrl) {
		properties = new ReadProperties().getFile();
		url = properties.getProperty(newUrl);
	}

	/**
	 * This method clicks on status and then Targets, and verifies no unhealthy
	 * targets are present.
	 */
	private void verifyTargetsAreHealthy() {
		WebDriver driver = OpenChromeBrowser.openURL(url);

		/* Step: Click on Status and then click on Targets. */
		driver.findElement(By.linkText("Status")).click();
		driver.findElement(By.linkText("Targets")).click();

		/* Step 2: Click on Unhealthy and verify no table appears there. */
		driver.findElement(By.xpath("//div[contains(@id,'showTargets')]//label[2]")).click();
		String style = driver.findElement(By.xpath("//div[contains(@class,'table-container')]")).getAttribute("style");
		Assertions.assertEquals("display: none;", style);

		driver.close();
	}

}
