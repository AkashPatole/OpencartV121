package testCases;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegisterAccountPage;
import testBase.BaseClass;

public class TC_00_RegistrationTest extends BaseClass {
       
	
	@Test(groups = {"regression","master"})
	public void verfiy_RegistrationTest()
	{  
		logger.info("******Starting TC_00_RegistrationTest *****");
		try {
		HomePage hp=new HomePage(super.driver);
		
		hp.clickAccount();
		
		logger.info("***Clicked on MyAccount Link*****");
		hp.clickRegister();
		logger.info("***Clicked on Register Link*****");
		
		RegisterAccountPage rp=new RegisterAccountPage(driver);
		logger.info("***Providing customer details*****");
		rp.setFirstName(randomAlphabate().toUpperCase());
		rp.setLastName(randomAlphabate().toUpperCase());
		rp.setEmail(randomAlphabate().toLowerCase()+"@gmail.com");
		rp.setTelephone(randomNumeric());
		String pwd=randomAlphaNumeric();
		rp.setPassword(pwd);
		rp.confirmPwd(pwd);
		rp.agreement();
		rp.continueBtn();
		assertEquals(rp.confirmMessage(), "Your Account Has Been Created!");
		}
		catch(Exception e)
		{
			logger.error("Test Failed..");
			logger.debug("Debug logs..");
			Assert.fail();
		}
		logger.info("******Finished TC_00_RegistrationTest *****");
	}
}
