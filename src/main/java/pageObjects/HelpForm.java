package pageObjects;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class HelpForm extends Form {
    private final IButton sendToBottomButton = AqualityServices.getElementFactory().getButton(By.cssSelector("button.button--solid.button--blue.help-form__send-to-bottom-button"), "Send To Bottom Button");
    private final ITextBox hiddenHelpForm = AqualityServices.getElementFactory().getTextBox(By.xpath("//div[contains(@class, 'is-hidden')]"), "Hidden Help Form");

    public HelpForm() {
        super(By.className("help-form"), "Help Form");
    }

    public boolean isHelpFormDisplayed() {
        return state().waitForDisplayed();
    }

    public void clickSendToBottomButton(){
        sendToBottomButton.click();
    }

    public boolean isHelpFormHidden(){
        final int hiddenFormHeight = 10;
        AqualityServices.getConditionalWait().waitFor(()-> hiddenHelpForm.getElement().getSize().getHeight() == hiddenFormHeight);
        return hiddenHelpForm.getElement().getSize().height == hiddenFormHeight;
    }
}

