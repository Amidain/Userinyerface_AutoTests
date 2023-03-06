package pageObjects;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MainPageForm extends Form {

    private final IButton startButton = AqualityServices.getElementFactory().getButton(By.className("start__link"), "Start Button");

    public MainPageForm() {
        super(By.className("start__button"), "Main Page");
    }

    public  boolean isMainPageOpen(){
        return state().waitForDisplayed();
    }

    public void clickStartButton (){
        startButton.clickAndWait();
    }
}

