package pageObjects;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class CookiesForm extends Form {
    private final IButton acceptCookiesButton = AqualityServices.getElementFactory().getButton(By.cssSelector("button.button--solid.button--transparent"), "Accept Cookies Button");

    public CookiesForm() {
        super(By.className("cookies"), "Cookies Form");
    }

    public boolean isCookiesFormDisplayed(){
        return state().waitForDisplayed();
    }

    public void clickAcceptCookiesButton(){
        acceptCookiesButton.click();
    }
}
