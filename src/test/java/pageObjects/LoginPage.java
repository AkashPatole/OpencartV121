package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	
	@FindBy(xpath ="//input[@id='input-email']" )
	WebElement enterEmail;
	
	@FindBy(xpath ="//input[@id='input-password']" )
	WebElement enterPwd;
	
	@FindBy(xpath ="//input[@value='Login']" )
	WebElement loginBtn;
	
	public void enterEmail(String mail)
	{
		enterEmail.sendKeys(mail);
	}
	
	public void enterPassword(String pwd)
	{
		enterPwd.sendKeys(pwd);
	}
     
	public void clickLogin()
	{
		loginBtn.click();
	}
     
     
}
