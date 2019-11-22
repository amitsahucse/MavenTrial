/**
 * KeycloakLandingPageTestP1.java
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bosch.cloudia.helper.OpenChromeBrowser;
import com.bosch.cloudia.helper.ReadProperties;

/**
 * This testcase goes to Keycloak and checks for the Keycolak welcome page and
 * appearance of login page for administration console and Bosch Login Page.
 * 
 * @author Amit Kumar Sahu (amit-kumar.sahu@lhind.dlh.de)
 */

public class KeycloakLandingPageTestP1 {

	String url, user, password;

	@Test
	public void grafanaLoginMethod() {

		/**
		 * Step 1: Initialize the url, username and password and open Keycloak url in
		 * browser.
		 */
		initializeLoginDetails();
		WebDriver driver = OpenChromeBrowser.openURL(url);

		/**
		 * Step 2: Verify Welcome page is displayed.
		 */
		String welcomeText = driver.findElement(By.className("welcome-header")).getText();
		Assertions.assertEquals(welcomeText, "Welcome to Keycloak");

		/**
		 * Step 3: Verify Administration Console link exists and click on it.
		 */
		String adminConsole = driver.findElement(By.linkText("Administration Console")).getAttribute("href");
		Assertions.assertEquals("https://localhost/auth/admin/", adminConsole);
		driver.findElement(By.linkText("Administration Console")).click();

		/**
		 * Step 4: Verify the login page for admin. Verify username and password fields
		 * exist and are type input.
		 */
		WebElement username = driver.findElement(By.id("username"));
		Assertions.assertEquals("input", username.getTagName());
		String usernameText = username.findElement(By.xpath("//input[@id='username']/preceding-sibling::label"))
				.getText();
		Assertions.assertEquals("Username or email", usernameText);

		WebElement password = driver.findElement(By.id("password"));
		Assertions.assertEquals("input", password.getTagName());
		String passwordText = password.findElement(By.xpath("//input[@id='password']/preceding-sibling::label"))
				.getText();
		Assertions.assertEquals("Password", passwordText);

		/**
		 * Step 5: Check login link exists.
		 */
		WebElement loginLink = driver.findElement(By.id("kc-login"));
		Assertions.assertEquals(loginLink.getAttribute("value"), "Log In");
		Assertions.assertEquals(loginLink.getAttribute("type"), "submit");

		/**
		 * Step 6: Check the Bosch Azure AD button link exists and click on it.
		 */
		WebElement boschLink = driver.findElement(By.id("zocial-boschaad"));
		String boschLinkText = boschLink.getText();
		Assertions.assertEquals("Bosch Azure AD", boschLinkText);
		boschLink.click();

		/**
		 * Step 7: Check on bosch login page, that input type email address textbox
		 * exists and has the default email as user@bosch.com
		 */
		WebElement emailTextBox = driver.findElement(By.id("i0116"));
		Assertions.assertEquals("email", emailTextBox.getAttribute("type"));
		Assertions.assertEquals("user@bosch.com", emailTextBox.getAttribute("placeholder"));

		
		/**
		 * Step 8: Verify the weiter button exists. 
		 */
		WebElement nextButton = driver.findElement(By.id("idSIButton9"));
		Assertions.assertEquals("submit", nextButton.getAttribute("type"));
		Assertions.assertEquals("Weiter", nextButton.getAttribute("value"));
		
		/**
		 * Step 9: Verify the login header exists.
		 */

		WebElement loginHeader = driver.findElement(By.id("loginHeader"));
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.textToBe((By.xpath("//div[@id='loginHeader']/div")), "Anmelden"));
		Assertions.assertEquals("Anmelden", loginHeader.getText());
		
		driver.close();
	}

	/**
	 * This method reads Url, Username and Password for Keycloak from properties
	 * file.
	 */
	private void initializeLoginDetails() {
		Properties properties = new ReadProperties().getFile();
		url = properties.getProperty("keycloak.url");
		user = properties.getProperty("keycloak.username");
		password = properties.getProperty("keycloak.password");

	}

}
