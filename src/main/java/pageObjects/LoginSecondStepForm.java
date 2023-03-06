package pageObjects;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import aquality.selenium.elements.CheckBox;
import aquality.selenium.elements.Element;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class LoginSecondStepForm extends Form {
    private final IButton uploadImageButton = AqualityServices.getElementFactory().getButton(By.className("avatar-and-interests__upload-button"), "Upload Image Button");
    private final ITextBox checkBoxContainer = AqualityServices.getElementFactory().getTextBox(By.className("avatar-and-interests__interests-list"), "Check Box List");
    private final IButton nextButton = AqualityServices.getElementFactory().getButton(By.xpath("//button[contains(@class,'button--stroked')]"),"Next Button");

    public LoginSecondStepForm() {
        super(By.className("avatar-and-interests__form"), "Login Second Step Form");
    }

    public boolean isLoginSecondStepFormDisplayed(){
        return state().waitForDisplayed();
    }

    public void clickUploadImageButtonAndUploadImage() {
        try{
            Robot robot = new Robot();

            ISettingsFile testDataReader = new JsonSettingsFile("test-data.json");
            uploadImageButton.click();

            StringSelection path = new StringSelection(testDataReader.getValue("/image-path").toString());
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(path,null);

            robot.delay(1000);

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);

            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e){
            System.out.println("Robot class was unable to paste data!");
        }
    }

    public void chooseThreeInterests () {
        final int MAX_INTERESTS_REQUIRED = 3;
        int rndInt;
        List<CheckBox> checkBoxes = checkBoxContainer.findChildElements(By.className("avatar-and-interests__interests-list"), ElementType.CHECKBOX);

        CheckBox unselect = checkBoxes.stream().filter(box -> box.getElement().getText().equals("Unselect all"))
                .findAny()
                .get()
                .findChildElement(By.className("checkbox__box"), ElementType.CHECKBOX);
        unselect.getJsActions().scrollIntoView();
        unselect.click();

        List<String> checkBoxValues = checkBoxes.stream().map(Element::getText).collect(Collectors.toList());
        checkBoxValues.remove("Unselect all");
        checkBoxValues.remove("Select all");


        int counter = 0;
        while (counter < MAX_INTERESTS_REQUIRED) {
            rndInt = new Random().nextInt(checkBoxValues.size());
            String temp = checkBoxValues.get(rndInt);
            checkBoxes.stream().filter(checkBox -> checkBox.getText().equals(temp))
                    .findAny()
                    .get()
                    .findChildElement(By.className("checkbox__box"), ElementType.CHECKBOX)
                    .click();
            checkBoxValues.remove(temp);
            counter++;
        }
    }

    public void clickNextButton(){
        nextButton.click();
    }
}
