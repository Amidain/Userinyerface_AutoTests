package pageObjects;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class LoginThirdStepForm extends Form {

    public LoginThirdStepForm() {
        super(By.className("personal-details"), "Login Third Step Form");
    }

    public boolean isLoginThirdStepFormDisplayed(){
        return state().waitForDisplayed();
    }
}

