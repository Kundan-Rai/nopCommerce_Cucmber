package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage
{
	private WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	// email field
	@FindBy(css = "[type='email']")
	private WebElement emailField;
	
	public void setEmail(String email)
	{
		this.emailField.clear();
		emailField.sendKeys(email);
	}
	
	// password field
	@FindBy(id = "Password")
	private WebElement passwordField;
	
	public void setPassword(String password)
	{
		this.passwordField.clear();
		this.passwordField.sendKeys(password);
	}
	
	// login button
	@FindBy(xpath = "//button[text()='Log in']")
	private WebElement loginButton;
	
	public void clickLogin()
	{
		this.loginButton.click();
	}
	
	// logout butonn
	@FindBy(linkText = "Logout")
	private WebElement logoutLink;
	
	public void clickLogout()
	{
		logoutLink.click();
	}
}
