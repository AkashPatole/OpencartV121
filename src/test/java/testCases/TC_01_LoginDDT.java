package testCases;

import org.testng.Assert;

import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*Data is valid - login success - logout
Data is valid -- login failed-- test fail

Data is invalid -- login success - logout
Data is invalid -- login failed -- test pass 
*/

public class TC_01_LoginDDT extends BaseClass{
 
	@Test(dataProvider = "LoginData",dataProviderClass=DataProviders.class,groups = {"sanity","master"})//dataProviderClass=DataProviders.class is required cuz dataprovider class is in diff pakage
	public void verify_loginDDT(String email,String pwd,String exp)
	{
		try {
		HomePage hp=new HomePage(driver);
		hp.clickAccount();
		hp.clickLogin();
		
		LoginPage lp=new LoginPage(driver);
		lp.enterEmail(email);
		lp.enterPassword(pwd);
		lp.clickLogin();
		
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage=macc.isMyAccountPageExists();
		//Assert.assertEquals(targetPage, true,"Lofin Failed");
		//Assert.assertTrue(targetPage);
		
		/*Data is valid - login success - logout
		Data is valid -- login failed-- test fail*/
		 if(exp.equalsIgnoreCase("valid"))
		 {   
			 if(targetPage == true)
			 {   
				 macc.clickLogout();
				 macc.contiueBtn();
				 Assert.assertTrue(true);
			 }
			 else
			 {
				 Assert.assertTrue(false);
			 }
		 }
		 
		 
	  /* Data is invalid -- login success - logout
		 Data is invalid -- login failed -- test pass 
		 */
		 if(exp.equalsIgnoreCase("Invalid"))
		 {   
			 if(targetPage == true)
			 {   
				 macc.clickLogout();
				 macc.contiueBtn();
				 Assert.assertTrue(false);
			 }
			 else
			 {
				 Assert.assertTrue(true);
			 }
		 }
		 
		 
		}//try End
		catch(Exception e)
		{
			Assert.fail();
		}
	}
}
