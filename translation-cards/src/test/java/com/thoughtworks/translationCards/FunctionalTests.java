package com.thoughtworks.translationCards;

import com.thoughtworks.translationCards.page.*;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.junit.Assert.assertEquals;

/**
 * Created by nliao on 8/23/16.
 */
public class FunctionalTests {

    public AndroidDriver driver;

    @Before
    public void setUp() throws Exception {
        File appDir = new File("/Users/nliao/Desktop/");
        File app = new File(appDir, "app-debug.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("device", "emulator-5556");

        capabilities.setCapability("deviceName", "test_device");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "4.4");

        capabilities.setCapability("app", app.getAbsolutePath());
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void createDeck() {
        String str_assert = "Select conversation";

        MyDecks mdp = new MyDecks(driver);
            System.out.println(driver.currentActivity());
        mdp.clickMakeDeckButton();

        NewDeck dmp = new NewDeck(driver);
        dmp.clickGetStartedButton();

        DeckTitle dtp = new DeckTitle(driver);
        dtp.setDeckTitle("TEST DECK");
        dtp.clickNext();

        SourceLanguage slp = new SourceLanguage(driver);
        slp.clickNext();

        DestinationLanguage dlp = new DestinationLanguage(driver);
        dlp.clickAddLanguage();
        dlp.setLanguage("Arabic");
        dlp.clickNext();

        AuthorName anp = new AuthorName(driver);
        anp.setOrganization("Thoughtworks");
        anp.wait(3000);
        anp.clickNext();

        ReviewSave rsp = new ReviewSave(driver);
        rsp.clickSave();

        mdp.clickDeckNameButton();

        NewCard ncp = new NewCard(driver);
        ncp.clickAddNewCardButton();

        CardMaker cmp = new CardMaker(driver);
        cmp.clickGetStartedButton();

        WriteYourPhrase wyp = new WriteYourPhrase(driver);
        wyp.setYourPhrase("Hello");
        wyp.clickNext();

        Translation tp = new Translation(driver);
        tp.setYourTranslation("Marhabaan");
        tp.clickNext();

        RecordYourPhrase ryp = new RecordYourPhrase(driver);
        ryp.clickRecord();
        ryp.clickGotIt();
        ryp.clickAllow();
        ryp.clickRecord();
        ryp.clickRecord();
        ryp.clickNext();

        ReviewSaveCard rsc = new ReviewSaveCard(driver);
        rsc.clickSave();

        ncp.clickBackButton();

        mdp.clickDeckOptions();
        mdp.clickSharePopUp();
        mdp.clickConfirmSharePopUp();
        assertEquals(str_assert,mdp.getConfirmationMessage());
    }
}

