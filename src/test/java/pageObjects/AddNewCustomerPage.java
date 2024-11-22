package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddNewCustomerPage 
{
	private WebDriver driver;
	
	public AddNewCustomerPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getPageTitle()
	{
		return driver.getTitle();
	}
	
	By customersMenu = By.xpath("(//*[contains(text(),'Customers')])[1]");
	public void clickOnCustomersMenu()
	{
		driver.findElement(customersMenu).click();
	}
	
	By customersMenuItem = By.xpath("(//p[contains(text(),' Customers')])[2]");
	public void clickOnCustomersMenuItem()
	{
		driver.findElement(customersMenuItem).click();
	}
	
	By addNewButton = By.xpath("//*[contains(@href,'Create')]");
	public void clickOnAddNewButton()
	{
		driver.findElement(addNewButton).click();
	}
	
	By emailField = By.cssSelector("[type='email']");
	public void setEmail(String email)
	{
		driver.findElement(emailField).sendKeys(email);
	}
	
	By passwordField = By.name("Password");
	public void setPassword(String password)
	{
		driver.findElement(passwordField).sendKeys(password);
	}
	
	By firstNameField = By.id("FirstName");
	public void setFirstName(String firstName)
	{
		driver.findElement(firstNameField).sendKeys(firstName);
	}
	
	By lastNameField = By.id("LastName");
	public void setLastName(String lastName) 
	{
		driver.findElement(lastNameField).sendKeys(lastName);
	}
	
	By maleGender = By.id("Gender_Male");
	By femaleGender = By.id("Gender_Female");
	public void setGender(String gender)
	{
		if(gender.equalsIgnoreCase("male"))
			driver.findElement(maleGender).click();
		else if(gender.equalsIgnoreCase("female"))
			driver.findElement(femaleGender).click();
		else
			driver.findElement(maleGender).click();	// default male
	}
	
	By dateOfBirth = By.id("DateOfBirth");
	public void setDateOfBirth(String dob)
	{
		driver.findElement(dateOfBirth).sendKeys(dob);
	}
	
	By companyName = By.id("Company");
	public void setCompanyName(String companyName)
	{
		driver.findElement(this.companyName).sendKeys(companyName);
	}
	
	By removeChoice = By.className("select2-selection__choice__remove");
	By customerRoleField = By.xpath("(//input[@class='select2-search__field'])[2]");
	
	By administratorRole = By.xpath("//li[text()='Administrators']"); 
	By forumModeratorsRole = By.xpath("//li[text()='Forum Moderators']");
	By regigsteredRole = By.xpath("//li[text()='Registered']");
	By guestRole = By.xpath("//li[text()='Guests']");
	By vendorRole = By.xpath("//li[text()='Vendors']");
	
	public void setCustomerRole(String role)
	{
		WebElement roleName = null;
		
		if(!role.equalsIgnoreCase("Guests"))
		{
			driver.findElement(customerRoleField).click();
		}
		if(role.equalsIgnoreCase("Guests"))
		{
			driver.findElement(removeChoice).click();
			roleName = driver.findElement(guestRole);
		}
		else if(role.contains("Administrators"))
		{
			 roleName = driver.findElement(administratorRole);
		}
		else if(role.contains("Forum Moderators"))
		{
			 roleName = driver.findElement(forumModeratorsRole);
		}
		else if(role.contains("Registered"))
		{
			 roleName = driver.findElement(regigsteredRole);
		}
		else
		{
			roleName = driver.findElement(vendorRole); 
		}
		roleName.click();
		driver.findElement(customerRoleField).click();
	}
	
	By mgrVendor = By.id("VendorId");
	public void setManagerOfVendor(String vendorValue)
	{
		Select vendorDropdown = new Select(driver.findElement(mgrVendor));
		vendorDropdown.selectByVisibleText(vendorValue);
	}
	
	By adminComment = By.cssSelector("#AdminComment");
	public void setAdminComment(String comment)
	{
		driver.findElement(adminComment).sendKeys(comment);
	}
	
	By saveButton = By.cssSelector("[name='save']");
	public void clickOnSaveButton()
	{
		driver.findElement(saveButton).click();
	}

}
