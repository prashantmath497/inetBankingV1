package com.inetbanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {

	Properties prop;

	public ReadConfig() {

		File file = new File("./Configurations/config.properties");
		try {
			FileInputStream fis = new FileInputStream(file);
			prop = new Properties();
			prop.load(fis);

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public String getAppilcationUrl() {
		String url = prop.getProperty("baseURL");
		return url;
	}

	public String getUserName() {
		String userName = prop.getProperty("userName");
		return userName;
	}

	public String getPassword() {
		String pass = prop.getProperty("password");
		return pass;
	}

	public String getChromeDriverPath() {
		String chromePath = prop.getProperty("chromePath");
		return chromePath;
	}

	public String getIePath() {
		String iepath = prop.getProperty("iPath");
		return iepath;
	}

	public String getFireFoxPath() {
		String geco = prop.getProperty("firefoxPath");
		return geco;
	}

}
