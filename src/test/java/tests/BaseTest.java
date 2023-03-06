package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    private ISettingsFile configReader = new JsonSettingsFile("config.json");
    private final String URL = configReader.getValue("/URL").toString();

    @BeforeMethod
    protected void beforeMethod() {
        AqualityServices.getBrowser().goTo(URL);
        AqualityServices.getBrowser().waitForPageToLoad();
        AqualityServices.getBrowser().maximize();
    }

    @AfterMethod
    protected void afterTest() {
        if (AqualityServices.isBrowserStarted()) {
            AqualityServices.getBrowser().quit();
        }
    }
}
