package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	
	public HomePage(WebDriver driver) {
		super(driver);
		
	}
	

	@FindBy(xpath ="//a[@title='My Account']" )
	WebElement accoountbtn;
	
	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement registerbtn;
	
	@FindBy(xpath = "//a[normalize-space()='Login']")
	WebElement loginBtn;
	
	public void clickAccount()
	{
		accoountbtn.click();
	}
	
	public void clickRegister()
	{
		registerbtn.click();
	}
	
	public void clickLogin()
	{
		loginBtn.click();
	}

	
}
