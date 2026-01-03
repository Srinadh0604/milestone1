package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	  WebDriver driver;
	  

	    private By usernameField = By.name("username");
	    private By passwordField = By.name("password");
	    private By loginButton = By.xpath("//button[@type='submit']");
	    private By errorMessage = By.cssSelector("p.oxd-alert-content-text");

	
	
	   
	    public LoginPage(WebDriver driver) {
	         this.driver = driver;
	         
	    }

	    public void enterUsername(String username) {
	    	System.out.println("Entering username: " + username);
	        driver.findElement(usernameField).sendKeys(username);
	        
	       }
	      
	    
	    public void enterPassword(String password) {
	    	System.out.println("Entering password");
	        driver.findElement(passwordField).sendKeys(password);
        	}
	
       	public void clickLogin() {
       	 System.out.println("Clicking Login button");
	        driver.findElement(loginButton).click();
	      }
       	
	     public String getErrorMessage() {
	    	 System.out.println("Fetching error message");
	        return driver.findElement(errorMessage).getText();
        	}
	     
	     
	     public boolean isPasswordMasked() {
	    	    System.out.println("Checking if password is masked");
	    	 String type = driver
	    	            .findElement(passwordField)
	    	            .getAttribute("type");

	    	 System.out.println("Password input type attribute: " + type);
	    	 
	    	    return type.equals("password");
	   
	}
	     
	     public boolean isLoginButtonEnabled() {
	    	    return driver.findElement(loginButton).isEnabled();
	    	}
}
