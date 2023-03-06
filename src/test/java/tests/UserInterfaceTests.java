package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import tests.BaseTest;

public class UserInterfaceTests extends BaseTest {

    @Test
    void registrationStepsTest() {
        MainPageForm mainPage = new MainPageForm();
        LoginFirstStepForm loginFirstStepForm = new LoginFirstStepForm();
        LoginSecondStepForm loginSecondStepForm = new LoginSecondStepForm();
        LoginThirdStepForm loginThirdStepForm = new LoginThirdStepForm();

        Assert.assertTrue(mainPage.isMainPageOpen(), "Main Page has not been displayed!");
        mainPage.clickStartButton();
        Assert.assertTrue(loginFirstStepForm.isLoginFormDisplayed(), "Login Form has not been displayed");
        loginFirstStepForm.clearAndFillLoginForm();
        loginFirstStepForm.clickDropDownButton();
        loginFirstStepForm.clickTermsAndConditionsCheckBox();
        Assert.assertFalse(loginFirstStepForm.isTermsAndConditionsCheckBoxChecked(), "Box remains checked!");
        loginFirstStepForm.clickNextButton();
        Assert.assertTrue(loginSecondStepForm.isLoginSecondStepFormDisplayed(), "Second step login form has not been displayed!");
        loginSecondStepForm.clickUploadImageButtonAndUploadImage();
        loginSecondStepForm.chooseThreeInterests();
        loginSecondStepForm.clickNextButton();
        Assert.assertTrue(loginThirdStepForm.isLoginThirdStepFormDisplayed(), "Third step login form has not been displayed");
    }

    @Test
    void hideHelpFormTest(){
        MainPageForm mainPage = new MainPageForm();
        HelpForm helpForm = new HelpForm();

        Assert.assertTrue(mainPage.isMainPageOpen(), "Main Page has not been displayed!");
        mainPage.clickStartButton();
        Assert.assertTrue(helpForm.isHelpFormDisplayed(), "Help Form has not been displayed!");
        helpForm.clickSendToBottomButton();
        Assert.assertTrue(helpForm.isHelpFormHidden(), "Help Form is not hidden!");
    }

    @Test
    void acceptCookiesTest(){
        MainPageForm mainPage = new MainPageForm();
        CookiesForm cookiesForm = new CookiesForm();

        Assert.assertTrue(mainPage.isMainPageOpen(), "Main Page has not been displayed!");
        mainPage.clickStartButton();
        Assert.assertTrue(cookiesForm.isCookiesFormDisplayed(), "Cookies form has not been displayed!");
        cookiesForm.clickAcceptCookiesButton();
        Assert.assertFalse(cookiesForm.isCookiesFormDisplayed(), "Cookies form has not been accepted, thus it is still displayed!");
    }

    @Test
    void timerTest(){
        MainPageForm mainPage = new MainPageForm();
        LoginFirstStepForm loginForm = new LoginFirstStepForm();

        Assert.assertTrue(mainPage.isMainPageOpen(), "Main Page has not been displayed!");
        mainPage.clickStartButton();
        Assert.assertEquals(loginForm.getTimerValue(), "00:00:00", "Timer does not start at 00:00:00");
    }
}

