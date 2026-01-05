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
	   
	    @Test(priority = 5)
		public void emptyUsernameAndPasswordTest() {

			System.out.println(" Empty Username and Password Test Started");

			LoginPage login = new LoginPage(driver);
			login.clickLogin();

			Assert.assertTrue(login.isRequiredFieldMessageDisplayed(),
					"Required field validation message should be displayed");
			
			   Assert.assertTrue(
				        login.isPasswordRequiredMessageDisplayed(),
				        "Password Required message should be displayed"
				    );

			   System.out.println("TC-03 Passed: Required validation messages displayed for empty fields");

		}
	    
	    @Test(priority = 6)
	    public void emptyUsernameValidPasswordTest() {

	        System.out.println(" Empty Username with Valid Password Test Started");

	        LoginPage login = new LoginPage(driver);
	        login.enterPassword("admin123");
	        login.clickLogin();

	        Assert.assertTrue(login.isUsernameRequiredMessageDisplayed());
	        System.out.println(" Passed: Username mandatory validation working");
	        		
	    }

	    @Test(priority = 7)
	    public void validUsernameEmptyPasswordTest() {

	        System.out.println("TC-05: Valid Username with Empty Password Test Started");

	        LoginPage login = new LoginPage(driver);
	        login.enterUsername("Admin");
	        login.clickLogin();

	        Assert.assertTrue(login.isPasswordRequiredMessageDisplayed());
	        System.out.println(" Passed: Password mandatory validation working");
	    }

	    @Test(priority = 8)
	    public void passwordClearedAfterRefreshTest() {

	        System.out.println("Password Clear After Refresh Test Started");

	        LoginPage login = new LoginPage(driver);

	        login.enterPassword("admin123");
	        driver.navigate().refresh();

	        Assert.assertEquals(login.getPasswordFieldValue(), "");
	        System.out.println(" Passed: Password cleared after refresh");
	    }

	    @Test(priority = 9)
	    public void loginPageTitleTest() {

	        System.out.println(" Login Page Title Test Started");

	        String title = driver.getTitle();
	        Assert.assertTrue(title.contains("OrangeHRM"));

	        System.out.println(" Passed: Login page title verified");
	    }

	}

