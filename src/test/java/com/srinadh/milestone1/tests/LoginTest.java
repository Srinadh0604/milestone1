package com.srinadh.milestone1.tests;

	import org.testng.Assert;
	import org.testng.annotations.Test;
	import com.srinadh.milestone1.base.BaseTest;

import dataproviders.LoginDataProvider;
import pages.LoginPage;
	
	
	public class LoginTest extends BaseTest {
		
	   @Test(priority = 1,dataProvider ="validLoginData",  dataProviderClass = LoginDataProvider.class )   
	   public void verifyValidLogin(String username, String password) {
		   try {
		     System.out.println("Valid Login Test Started");
		   LoginPage loginPage = new LoginPage(driver);
		  	loginPage.enterUsername(username);
		  	loginPage.enterPassword(password);
		    loginPage.clickLogin();
		    String currentUrl = driver.getCurrentUrl();
		    Assert.assertTrue(currentUrl.contains("dashboard"), "User is not redirected to dashboard");
		    
		    }catch (Exception e) {     
		   System.out.println("Exception occurred: " + e.getMessage());
	        Assert.fail("Test failed due to exception");
	    }
	   }
	   
	   @Test(priority = 2,  dataProvider = "invalidLoginData", dataProviderClass = LoginDataProvider.class)
	   public void verifyInvalidLogin(String username, String password) {
		   try {
		   System.out.println(">>> Starting verifyInValidLogin test");
		   
		   LoginPage loginPage = new LoginPage(driver);
		   loginPage.enterUsername(username);
		   loginPage.enterPassword(password);
		   loginPage.clickLogin();
		   String errorMsg = loginPage.getErrorMessage();
		   System.out.println("Actual error message: " + errorMsg);
		   Assert.assertTrue(errorMsg.contains("Invalid"), "Error message not displayed for invalid login");
		   System.out.println("<<< verifyInValidLogin test completed");
		   }catch (Exception e) {     
			   System.out.println("Exception occurred: " + e.getMessage());
		        Assert.fail("Test failed due to exception");
		    }
		   }
	   
			@Test(priority = 3, dataProvider = "invalidLoginData", dataProviderClass = LoginDataProvider.class, groups = "valid")
			public void verifyPasswordMasking(String username, String password) {
				try {
					LoginPage loginPage = new LoginPage(driver);
					loginPage.enterPassword(password);
					boolean isMasked = loginPage.isPasswordMasked();
					System.out.println("Is password masked? " + isMasked);
					Assert.assertTrue(isMasked, "Password is not masked");
					System.out.println("<<< verifyPasswordMasking test completed");
				} catch (Exception e) {
					System.out.println("Exception occurred: " + e.getMessage());
					Assert.fail("Test failed due to exception");
				}
			}
	   
	    // : Login Button Enabled Validation
	    @Test(priority = 4)
	    public void loginButtonEnabledTest() {
	    	try {
	        LoginPage login = new LoginPage(driver);

	        Assert.assertTrue(
	                login.isLoginButtonEnabled(),
	                "Login button should be enabled on page load"
	        );
	        System.out.println(" Passed: Login button is enabled");
	    	}catch (Exception e) {     
	 		   System.out.println("Exception occurred: " + e.getMessage());
		        Assert.fail("Test failed due to exception");
		    }
	    }
	   
	    @Test(priority = 5)
		public void emptyUsernameAndPasswordTest() {
	    	try {
			System.out.println(" Empty Username and Password Test Started");

			LoginPage login = new LoginPage(driver);
			login.clickLogin();

			Assert.assertTrue(login.isRequiredFieldMessageDisplayed(),
					"Required field validation message should be displayed");
			
			   Assert.assertTrue(
				        login.isPasswordRequiredMessageDisplayed(),
				        "Password Required message should be displayed"
				    );

			   System.out.println(" Passed: Required validation messages displayed for empty fields");
	    	}catch (Exception e) {     
	 		   System.out.println("Exception occurred: " + e.getMessage());
		        Assert.fail("Test failed due to exception");
		    }

		}
	    
		@Test(priority = 6, dataProvider = "invalidLoginData", dataProviderClass = LoginDataProvider.class)
		public void emptyUsernameValidPasswordTest(String username, String password) {
			try {
				System.out.println(" Empty Username with Valid Password Test Started");

				LoginPage login = new LoginPage(driver);
				login.enterPassword(password);
				login.clickLogin();

				Assert.assertTrue(login.isUsernameRequiredMessageDisplayed(),
						"username mandatory validation NOT working");
				System.out.println(" Passed: Username mandatory validation working");
			} catch (Exception e) {
				System.out.println("Exception occurred: " + e.getMessage());
				Assert.fail("Test failed due to exception");
			}
		}

	    @Test(priority = 7, dataProvider = "validLoginData", dataProviderClass = LoginDataProvider.class)
	    public void validUsernameEmptyPasswordTest(String username, String password) {
            try {
	        System.out.println(" Valid Username with Empty Password Test Started");

	        LoginPage login = new LoginPage(driver);
	        login.enterUsername(username);
	        login.clickLogin();

	        Assert.assertTrue(login.isPasswordRequiredMessageDisplayed(),"Password mandatory validation NOT working");
	        System.out.println(" Passed: Password mandatory validation working");
	    }catch (Exception e) {     
			   System.out.println("Exception occurred: " + e.getMessage());
		        Assert.fail("Test failed due to exception");
		    }
	    }

	    @Test(priority = 8, dataProvider = "invalidLoginData", dataProviderClass = LoginDataProvider.class)
	    public void passwordClearedAfterRefreshTest(String username, String password) {
            try{
	        System.out.println("Password Clear After Refresh Test Started");

	        LoginPage login = new LoginPage(driver);

	        login.enterPassword(password);
	        driver.navigate().refresh();

	        Assert.assertEquals(login.getPasswordFieldValue(), "");
	        System.out.println(" Passed: Password cleared after refresh");
	    }catch (Exception e) {     
			   System.out.println("Exception occurred: " + e.getMessage());
		        Assert.fail("Test failed due to exception");
		    }
	    }

	    @Test(priority = 9)
	    public void loginPageTitleTest() {
	    	try {
	        System.out.println(" Login Page Title Test Started");

	        String title = driver.getTitle();
	        Assert.assertTrue(title.contains("OrangeHRM"));

	        System.out.println(" Passed: Login page title verified");
	    }catch (Exception e) {     
			   System.out.println("Exception occurred: " + e.getMessage());
		        Assert.fail("Test failed due to exception");
		    }
	    }
	    
	    @Test(priority = 10 ,dataProvider = "invalidLoginData", dataProviderClass = LoginDataProvider.class )
	    public void invalidUsernameValidPasswordTest(String username, String password) {
	    	try {
	        System.out.println(" Invalid Username with Valid Password Test Started");

	        LoginPage login = new LoginPage(driver);

	        login.enterUsername(username);
	        login.enterPassword(password);
	        login.clickLogin();

	        Assert.assertTrue(login.getErrorMessage().contains("Invalid credentials"));
	        System.out.println(" Passed: Invalid username handled correctly");
	    }catch (Exception e) {     
			   System.out.println("Exception occurred: " + e.getMessage());
		        Assert.fail("Test failed due to exception");
		    }
	    }
	    	
	}

