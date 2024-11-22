package stepDefinitions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.AddNewCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class NopCommerceSteps extends BaseClass
{
	@Before	// Will execute before each scenario
	public void setUp() throws IOException
	{
		// Read properties file
		File file = new File("./resources/config.properties");
		FileInputStream fis = new FileInputStream(file);
		
		// Load properties file to properties object
		prop = new Properties();
		prop.load(fis);		
		
		// Access the properties file value
		String browser = prop.getProperty("browser");
		
		if(browser.equalsIgnoreCase("chrome"))
		{
			chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--start-maximized");
//			chromeOptions.addArguments("--headless=new");
			chromeOptions.setExperimentalOption("debuggerAddress", "localhost:9999");	// For running local chrome profile
			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(chromeOptions);
		}
		else if(browser.equalsIgnoreCase("edge"))
		{
			edgeOptions = new EdgeOptions();
			edgeOptions.addArguments("--start-maximized");
		
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver(edgeOptions);
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			firefoxOptions = new FirefoxOptions();
			firefoxOptions.addArguments("--start-maximized");
			
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else
		{
			System.err.println("Invalid browser");
			return;
		}
	}
	
	@Given("User launches chrome browser")
	public void user_launches_chrome_browser()
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		loginPageObj = new LoginPage(driver);
	}
	
	@When("User opens URL {string}")
	public void user_opens_url(String URL)
	{
		driver.get(URL);
	}

	@And("User enters email as {string} and password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password)
	{
		loginPageObj.setEmail(email);
		loginPageObj.setPassword(password);
	}

	@And("User clicks on login button")
	public void user_clicks_on_login_button()
	{
		loginPageObj.clickLogin();
	}

	@Then("Page title should be {string}")
	public void page_title_should_be(String ExpectedTitle)
	{
		if(driver.getPageSource().contains("Login was unsuccessful."))
		{
//			driver.close();
			Assert.fail("Login is unsuccessful");
		}
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, ExpectedTitle, "Title validation failed");
	}

	@When("User clicks on the logout link")
	public void user_clicks_on_the_logout_link()
	{
		loginPageObj.clickLogout();
	}
	
	@And("User closes the browser")
	public void User_closes_the_browser()
	{
//		driver.close();
	}
	
	// Add new customer page
	
	@Then("User can see the dashboard")
	public void user_can_see_the_dashboard()
	{
		String expectedTitle = "Dashboard / nopCommerce administration";
		addNewCustPageObj = new AddNewCustomerPage(driver);
		String actualTitle = addNewCustPageObj.getPageTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
	}

	@When("User clicks on Customers menu")
	public void user_clicks_on_customers_menu()
	{
		addNewCustPageObj.clickOnCustomersMenu();
	}

	@And("User clicks on Customers menu item")
	public void user_clicks_on_customers_menu_item()
	{
		addNewCustPageObj.clickOnCustomersMenuItem();
	}

	@And("User clicks on Add new button")
	public void user_clicks_on_add_new_button()
	{
		addNewCustPageObj.clickOnAddNewButton();
	}

	@Then("User can see the Add a new customer page")
	public void user_can_see_the_add_a_new_customer_page()
	{
	    String expectedTitle = "Add a new customer / nopCommerce administration";
	    String actualTitle = addNewCustPageObj.getPageTitle();
	    Assert.assertEquals(actualTitle, expectedTitle);
	}

	@When("User enters Customer info")
	public void user_enters_customer_info() throws InterruptedException
	{
		String randomEmail = randomString()+"@gmail.com";
		addNewCustPageObj.setEmail(randomEmail);
		addNewCustPageObj.setPassword("test123");
		addNewCustPageObj.setFirstName("Kundan");
		addNewCustPageObj.setLastName("Rai");
		addNewCustPageObj.setGender("male");
		addNewCustPageObj.setDateOfBirth("10-12-1990");
		addNewCustPageObj.setCompanyName("BusyQA");
		
		// Customer roles
		// Registered - default
		// The customer cannot be in both "Guests" and "Registered"
		// Add the customer to "Guestes" or "Registered" customer role
		addNewCustPageObj.setCustomerRole("Guests");
//		addNewCustPageObj.setCustomerRole("Vendors");
		addNewCustPageObj.setAdminComment("This is for testing...");
		Thread.sleep(5000);	}

	@And("User clicks on Save button")
	public void user_clicks_on_save_button()
	{
		addNewCustPageObj.clickOnSaveButton();
	}

	@Then("User can see the confirmation message {string}")
	public void user_can_see_the_confirmation_message(String confMessage)
	{
	    WebElement body = driver.findElement(By.tagName("body"));
	    Assert.assertTrue(body.getText().contains("The new customer has been added successfully"));
	}
	
	// Steps for Scenario: Search Customer by email
	
	@Then("User can see the Customers page")
	public void user_can_see_the_customers_page()
	{
		String expectedTitle = "Customers / nopCommerce administration";
		String actualTitle = addNewCustPageObj.getPageTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
	}
	@When("User enters customer email")
	public void user_enters_customer_email()
	{
		searchCustomerPageObj = new SearchCustomerPage(driver);
		searchCustomerPageObj.setEmail("victoria_victoria@nopCommerce.com");
	}
	@And("User clicks on Search button")
	public void user_clicks_on_search_button()
	{
		searchCustomerPageObj.clickOnSearchButton();
	}
	@Then("User should found email in the search table")
	public void user_should_found_email_in_the_search_table()
	{
		boolean status = searchCustomerPageObj.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
		Assert.assertTrue(status);
	}
	
	// Steps for Scenario: Search customer by first name and last name
	
	@When("User enters customer first name")
	public void user_enters_customer_first_name()
	{
		searchCustomerPageObj = new SearchCustomerPage(driver);
		searchCustomerPageObj.setFirstName("Brenda");
	}
	@When("User enters customer last name")
	public void user_enters_customer_last_name()
	{
		searchCustomerPageObj.setLasttName("Lindgren");
	}
	@Then("User should found name in the search table")
	public void user_should_found_name_in_the_search_table()
	{
		boolean status = searchCustomerPageObj.searchCustomerByName("Brenda", "Lindgren");
		Assert.assertTrue(status);
	}

}
