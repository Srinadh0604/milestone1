package com.srinadh.milestone1.tests;

	import org.testng.Assert;
	import org.testng.annotations.Test;
	import com.srinadh.milestone1.base.BaseTest;
	import pages.LoginPage;
	
	
	public class LoginTest extends BaseTest {
		
	   @Test(priority = 1)
	   
	   public void verifyValidLogin() {
		   LoginPage loginPage = new LoginPage(driver);
		  	loginPage.enterUsername("Admin");
		  	loginPage.enterPassword("admin123");
		    loginPage.clickLogin();
		    String currentUrl = driver.getCurrentUrl();
		    Assert.assertTrue(currentUrl.contains("dashboard"), "User is not redirected to dashboard");
		    }
	   
	   @Test(priority = 2)
	   public void verifyInvalidLogin() {
		   System.out.println(">>> Starting verifyValidLogin test");
		   
		   LoginPage loginPage = new LoginPage(driver);
		   loginPage.enterUsername("InvalidUser");
		   loginPage.enterPassword("InvalidPass");
		   loginPage.clickLogin();
		   String errorMsg = loginPage.getErrorMessage();
		   System.out.println("Actual error message: " + errorMsg);
		   Assert.assertTrue(errorMsg.contains("Invalid"), "Error message not displayed for invalid login");
		   System.out.println("<<< verifyValidLogin test completed");
		   }
	   
	   @Test(priority = 3)
	   public void verifyPasswordMasking() {
		   LoginPage loginPage = new LoginPage(driver);
		   loginPage.enterPassword("dummy");
		   boolean isMasked = loginPage.isPasswordMasked();
		   System.out.println("Is password masked? " + isMasked);
		   Assert.assertTrue(isMasked, "Password is not masked");
		   System.out.println("<<< verifyPasswordMasking test completed");

		   }
	   
	    // : Login Button Enabled Validation
	    @Test(priority = 4)
	    public void loginButtonEnabledTest() {
	        LoginPage login = new LoginPage(driver);

	        Assert.assertTrue(
	                login.isLoginButtonEnabled(),
	                "Login button should be enabled on page load"
	        );
	        System.out.println(" Passed: Login button is enabled");
	    }
	   
	}

