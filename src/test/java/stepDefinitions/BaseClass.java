package stepDefinitions;

import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import pageObjects.AddNewCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class BaseClass
{
	WebDriver driver;
	LoginPage loginPageObj;
	AddNewCustomerPage addNewCustPageObj;
	SearchCustomerPage searchCustomerPageObj;
	Properties prop;
	ChromeOptions chromeOptions;
	EdgeOptions edgeOptions;
	FirefoxOptions firefoxOptions;
	
	// Created for generating random string for unique email
	public static String randomString()
	{
		String randString = RandomStringUtils.randomAlphabetic(5);
		return randString;
	}
}
