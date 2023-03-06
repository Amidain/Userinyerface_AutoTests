package pageObjects;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.TextBox;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utils.LoginDataGenerator;

import java.util.List;
import java.util.Random;

public class LoginFirstStepForm extends Form {
    private final ITextBox loginFormTextBox = AqualityServices.getElementFactory().getTextBox(By.className("login-form"), "Choose Password TextBox");
    private final IButton dropDownButton = AqualityServices.getElementFactory().getButton(By.xpath("//div[contains(@class, 'dropdown--gray')]"), "Drop Down Button");
    private final ICheckBox termsAndConditionsCheckbox = AqualityServices.getElementFactory().getCheckBox(By.className("checkbox__box"), "Terms And Conditions CheckBox");
    private final IButton nextButton = AqualityServices.getElementFactory().getButton(By.xpath("//a[contains(@class,'button--secondary')]"), "Next Button");
    private final ITextBox timerTextBox = AqualityServices.getElementFactory().getTextBox(By.xpath("//div[contains(@class, 'timer--white')]"), "Timer Box");

    public LoginFirstStepForm() {
        super(By.className("login-form"), "Login Form");
    }

    public boolean isLoginFormDisplayed(){
        return state().waitForDisplayed();
    }

    public void clickDropDownButton() {
        dropDownButton.click();
        List<TextBox> mailSuffixes = AqualityServices.getElementFactory().findChildElements(dropDownButton, By.className("dropdown__list-item"), ElementType.TEXTBOX);
        int rndDomain = new Random().nextInt(mailSuffixes.size());
        mailSuffixes.get(rndDomain).click();
    }

    public void clearAndFillLoginForm (){
        String email = LoginDataGenerator.generateEmailPrefix();
        String password = LoginDataGenerator.generateCustomPassword(email);
        String mailBox = LoginDataGenerator.generateMailBox();

        final int PASSWORD_BOX = 0;
        final int EMAIL_BOX = 1;
        final int DOMAIN_BOX = 2;

        List<TextBox> textBoxes = loginFormTextBox.findChildElements(By.cssSelector("input.input--blue.input--gray"), ElementType.TEXTBOX);
        textBoxes.get(PASSWORD_BOX).clearAndType(password);
        textBoxes.get(EMAIL_BOX).clearAndType(email);
        textBoxes.get(DOMAIN_BOX).clearAndType(mailBox);
    }

    public void clickTermsAndConditionsCheckBox (){
        termsAndConditionsCheckbox.check();
    }

    public boolean isTermsAndConditionsCheckBoxChecked(){
        return termsAndConditionsCheckbox.isChecked();
    }

    public void clickNextButton(){
        nextButton.click();
    }

    public String getTimerValue(){
        return timerTextBox.getText();
    }
}

