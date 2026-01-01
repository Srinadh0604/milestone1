package com.srinadh.milestone1.tests;

	import org.testng.Assert;
	import org.testng.annotations.Test;
	import base.BaseTest;
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
		   LoginPage loginPage = new LoginPage(driver);
		   loginPage.enterUsername("InvalidUser");
		   loginPage.enterPassword("InvalidPass");
		   loginPage.clickLogin();
		   String errorMsg = loginPage.getErrorMessage();
		   Assert.assertTrue(errorMsg.contains("Invalid"), "Error message not displayed for invalid login");
		   }
	   
	   @Test(priority = 3)
	   public void verifyPasswordMasking() {
		   LoginPage loginPage = new LoginPage(driver);
		   boolean isMasked = loginPage.isPasswordMasked();
		   Assert.assertTrue(isMasked, "Password is not masked");
		   }
	   
	   
	}

