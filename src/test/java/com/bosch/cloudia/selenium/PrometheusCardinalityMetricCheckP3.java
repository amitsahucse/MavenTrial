/**
 * PrometheusCardinalityMetricCheckP3.java
 *
 * Copyright (c) 2019 Robert Bosch GmbH. All Rights Reserved.
 */
package com.bosch.cloudia.selenium;

import java.util.ArrayList;
import java.util.Properties;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.bosch.cloudia.helper.OpenChromeBrowser;
import com.bosch.cloudia.helper.ReadProperties;

/**
 * This testcase opens Prometheus and checks for the Highest Cardinality Metric
 * Names table's values to be in descending order.
 *
 * @author Amit Kumar Sahu (amit-kumar.sahu@lhind.dlh.de)
 */
public class PrometheusCardinalityMetricCheckP3 {
	private String url;
	private Properties properties;

	@Test
	public void prometheusRuntimeInfoTest() {

		/** Step 1: Initialization: Set the Prometheus url. */
		initializeProperties("prometheus.cloud.url");

		/** Step 2: Verify the Highest Cardinality Metric Names table has all integer values in descending order. */
		verifyTableValues();

	}

	/**
	 * This method initializes the url for different prometheus urls passed.
	 */
	private void initializeProperties(String newUrl) {
		properties = new ReadProperties().getFile();
		url = properties.getProperty(newUrl);
	}

	/**
	 * This method clicks on status and then Runtime & Build Information, and
	 * verifies the Highest Cardinality Metric Names Table has all integer values in
	 * descending order.
	 */
	private void verifyTableValues() {
		WebDriver driver = OpenChromeBrowser.openURL(url);

		/** Step 2a: Click on Status and then click on Runtime & Build Information. */
		driver.findElement(By.linkText("Status")).click();
		driver.findElement(By.linkText("Runtime & Build Information")).click();

		/**
		 * Step 2b: Locate the Highest Cardinality Metric Names table and verify the
		 * values are in descending order.
		 */

		WebElement cell1;
		boolean sorted = true;
		ArrayList<Integer> intList = new ArrayList<Integer>();
		/**
		 * Get the number of Rows in the table.
		 */
		int numberOfRows = (driver.findElements(By.xpath(
				"//h3[@id=\"headstatus\" and text()=\"Highest Cardinality Metric Names\"]/following-sibling::table[1]//tr")))
						.size();
		System.out.println("The size is " + numberOfRows);

		/**
		 * Store the numeric values of the table in an Arraylist and check if it's in descending.
		 */
		for (int i = 2; i <= numberOfRows; i++) {
			cell1 = driver.findElement(By.xpath(
					"//h3[@id=\"headstatus\" and text()=\"Highest Cardinality Metric Names\"]/following-sibling::table[1]//tr["
							+ i + "]/td[2]"));
			intList.add(Integer.parseInt(cell1.getText()));
		}
		for (int i = 0; i < intList.size() - 1; i++) {
			if (intList.get(i) >= intList.get(i + 1)) {
				System.out.println(intList.get(i) + " >= " + intList.get(i + 1));
				sorted = sorted && true;
			} else {
				sorted = sorted && false;
			}
		}
		Assertions.assertTrue(sorted);
		driver.close();
	}
}
