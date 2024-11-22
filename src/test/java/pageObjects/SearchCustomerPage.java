package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitHelper;

public class SearchCustomerPage
{
	private WebDriver driver;
	private WaitHelper waitHelper;
	
	public SearchCustomerPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		waitHelper = new WaitHelper(driver);
	}
	
	@FindBy(how = How.ID, using = "SearchEmail")
	WebElement emailField;
	
	public void setEmail(String email)
	{
		waitHelper.waitForElement(emailField, 10);
		emailField.clear();
		emailField.sendKeys(email);
	}
	
	@FindBy(how = How.CSS, using = "#SearchFirstName")
	WebElement firstNameField;
	
	public void setFirstName(String firstName)
	{
		waitHelper.waitForElement(firstNameField, 10);
		firstNameField.clear();
		firstNameField.sendKeys(firstName);
	}
	
	@FindBy(how = How.NAME, using = "SearchLastName")
	WebElement lastNameField;
	
	public void setLasttName(String lastName)
	{
		waitHelper.waitForElement(lastNameField, 10);
		lastNameField.clear();
		lastNameField.sendKeys(lastName);
	}
	
	@FindBy(how = How.XPATH, using = "//button/i[@class='fas fa-search']")
	WebElement searchButton;
	
	public void clickOnSearchButton()
	{
		searchButton.click();
	}
	
	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']")
	WebElement table;
	
	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']/tbody/tr")
	List<WebElement> tableRows;
	
	public int getNoOfRows()
	{
		return tableRows.size();
	}
	
	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']/tbody/tr[1]/td")
	List<WebElement> tableColumns;
	
	public int getNoOfColumns()
	{
		return tableColumns.size();
	}
	
	// Here we are checking/validating, the email id we searched is present in the table or not
	public boolean searchCustomerByEmail(String email)
	{
		waitHelper.waitForElement(table, 10);
		
		int rows = getNoOfRows();
		
		for(int i = 1; i <= rows; i++)
		{
			String emailId = driver.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr["+i+"]/td[2]")).getText();
			if(emailId.equals(email))
			{
				return true;
			}
		}
		return false;
	}
	
	// Validation code based on first name and last name
	public boolean searchCustomerByName(String firstName, String lastName)
	{
		for(int i = 1; i <= getNoOfRows(); i++)
		{
			String[] actualName = driver.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr["+i+"]/td[3]")).getText().split(" ");
			
			if(actualName[0].equals(firstName) && actualName[1].equals(lastName))
				return true;
		}
		return false;
	}
}
