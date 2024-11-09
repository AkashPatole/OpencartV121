package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterAccountPage extends BasePage {

	public RegisterAccountPage(WebDriver driver) {
		super(driver);

	}

	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement firstName;

	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement lastName;

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement emailid;

	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement telephone;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement password;

	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement confirmPwd;

	@FindBy(xpath = "//input[@name='agree']")
	WebElement agree;

	@FindBy(xpath = "//input[@value='Continue']")
	WebElement continuebtn;
	
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement messageComfirmation;
   
	
	public void setFirstName(String fname)
	{
		firstName.sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		lastName.sendKeys(lname);
	}
	public void setEmail(String email)
	{
		emailid.sendKeys(email);
	}
	public void setTelephone(String no)
	{
		telephone.sendKeys(no);
	}
	public void setPassword(String pwd)
	{
		password.sendKeys(pwd);
	}

	public void confirmPwd(String cpwd) {
		confirmPwd.sendKeys(cpwd);
	}
	
	public void agreement()
	{
		agree.click();
	}
	
	public void continueBtn()
	{
		continuebtn.click();;
	}
	
	public String confirmMessage()
	{
		try {
			return messageComfirmation.getText();
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}
