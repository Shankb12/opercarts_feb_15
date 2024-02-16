package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass{
 
	@Test(groups={"Regression", "Master"})
	public void test_account_reg() {
		try {
			logger.info("****************starting TC_001_AccountRegistrationTest******************");
		logger.debug("***Debugg****");
			HomePage hp= new HomePage(driver);
		hp.clickMyAccount();
		logger.info("clicked on MyAccount Link");
		hp.clickRegister();
		logger.info("clicked on Register link");
		AccountRegistrationPage ar= new AccountRegistrationPage(driver);
		logger.info("Providing Customer details.....");
		ar.setFirstname(randomString());
		ar.setLastname(randomString());
		ar.setEmail(randomAlphaNumeric()+"@gmail.com");
		ar.setTelephone(randomNumeric());
		String pwd=randomAlphaNumeric();
		ar.setPassword(pwd);
		ar.setConfirmPassword(pwd);
		ar.checkPolicy(); 
		ar.clkContinue();
		logger.info("Clicked on Continue button...");
		String s= ar.getConfirmationMsg();
		if(s.equals("Your Account Has Been Created!")) {
			logger.info("tedt passed");
			Assert.assertTrue(true);
		}
		else {
			logger.error("test failed");
			Assert.assertTrue(false);
		
		}
		
	}
	
	catch(Exception e) {
		Assert.fail();
	}
		logger.info("****************Finished TC_001_AccountRegistrationTest******************");
	}
	
}
 