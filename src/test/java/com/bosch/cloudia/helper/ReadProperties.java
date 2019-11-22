/*
 * ReadProperties.java
 *
 * Copyright (c) 2019 Robert Bosch GmbH. All Rights Reserved.
 */
package com.bosch.cloudia.helper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * The code for reading data from desired environment properties file. It takes
 * input from command line eg. mvn test -DTestEnvironment=dev This variable
 * TestEnvironment is then used to generate the properties file name to read the
 * data from.
 * 
 * @author Amit Kumar Sahu (amit-kumar.sahu@lhind.dlh.de)
 */


public class ReadProperties {

	public Properties getFile() {

		/* Generate the name of properties file to read values from. */
		String configFileName = "application.properties";

		/* Create new Properties file with the generated name. */
		Properties properties = generatePropertiesFile(configFileName);

		/*
		 * Return the desired properties file depending on the input variable
		 * TestEnvironment.
		 */
		return properties;
	}

	/*
	 * This method creates a new Properties file with the given name in
	 * configFileName input parameter.
	 */
	private Properties generatePropertiesFile(String configFileName) {
		Properties properties = new Properties();
		try {
			/* Read from the properties file, the name of which was generated above. */
			properties.load(ReadProperties.class.getClassLoader().getResourceAsStream(configFileName));
		} catch (FileNotFoundException exception) {
			System.out.println("Environment properties file not available.");
		} catch (IOException ioException) {
			System.out.println("Environment properties file - IO Exception.");
		}

		String url = properties.getProperty("google.url");
		System.out.println("The google url from properties file is : " + url);

		return properties;
	}

}
