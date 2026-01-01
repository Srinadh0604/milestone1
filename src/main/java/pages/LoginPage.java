package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	  WebDriver driver;
	  

	    private By usernameField = By.name("username");
	    private By passwordField = By.name("password");
	    private By loginButton = By.xpath("//button[@type='submit']");
	    private By errorMessage = By.xpath("//p[contains(@class,'alert-contenttext')]");
	
	
	   
	    public LoginPage(WebDriver driver) {
	         this.driver = driver;
	         
	    }

	    public void enterUsername(String username) {
	        driver.findElement(usernameField).sendKeys(username);
	        
	       }
	      
	    
	    public void enterPassword(String password) {
	        driver.findElement(passwordField).sendKeys(password);
        	}
	
       	public void clickLogin() {
	        driver.findElement(loginButton).click();
	      }
       	
	     public String getErrorMessage() {
	        return driver.findElement(errorMessage).getText();
        	}
	     
	     
	     public boolean isPasswordMasked() {
	        return driver.findElement(passwordField)
	     .getAttribute("type")
	.     equals("password");
	}
}
