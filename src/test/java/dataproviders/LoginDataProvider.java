package dataproviders;

import org.testng.annotations.DataProvider;

public class LoginDataProvider {

    @DataProvider(name = "invalidLoginData")
    public static Object[][] invalidLoginData() {

        return new Object[][] {
            { "Admin", "wrongpass" },
            { "WrongAdmin", "admin123" },
            { "WrongAdmin", "wrongpass" }
        };
    }
    
    @DataProvider(name = "validLoginData")
    public static Object[][] validLoginData() {

        return new Object[][] {
            { "Admin","admin123" }
        };
    }
    
}
