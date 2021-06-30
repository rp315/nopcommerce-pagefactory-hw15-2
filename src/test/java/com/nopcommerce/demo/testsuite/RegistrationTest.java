package com.nopcommerce.demo.testsuite;

import com.nopcommerce.demo.customListeners.CustomListener;
import com.nopcommerce.demo.pages.HomePage;
import com.nopcommerce.demo.pages.LoginPage;
import com.nopcommerce.demo.pages.RegisterPage;
import com.nopcommerce.demo.testBase.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Listeners(CustomListener.class)
public class RegistrationTest extends TestBase {

    HomePage homePage;
    LoginPage loginPage;
    SoftAssert softAssert;
    RegisterPage registerPage;

    @BeforeMethod(groups = {"smoke","sanity","regression"})
    public void setUp(){

        homePage = new HomePage();
        loginPage = new LoginPage();
        softAssert = new SoftAssert();
        registerPage = new RegisterPage();
    }

    @Test
    public void verifyUserAbleToNavigateToRegisterPageSuccessfully(){
        homePage.clickOnRegisterTab();

        String expectedPageNavigationText = "Register";
        String actualPageNavigationText = registerPage.getTextRegisterText();
        softAssert.assertEquals(expectedPageNavigationText,actualPageNavigationText);

        softAssert.assertAll();

    }

    @Test(groups = {"smoke","regression"})
    public void verifyUserShouldNotAbleToRegisterLeavingMandatoryFieldEmpty() throws InterruptedException {

        homePage.clickOnRegisterTab();

        Thread.sleep(1000);
        registerPage.enterFirstName("");
        Thread.sleep(1000);
        registerPage.enterLastName("Patel");
        Thread.sleep(1000);
        registerPage.enterEmail("samir","rst@gmail.com");
        Thread.sleep(1000);
        registerPage.enterPassword("wert1234");
        Thread.sleep(1000);
        registerPage.enterConfirmPassword("wert1234");
        Thread.sleep(1000);
        registerPage.clickOnRegisterButton();

        String expectedErrorMessage = "First name is required.";
        String actualErrorMessage = registerPage.getErrorMessage();
        softAssert.assertEquals(expectedErrorMessage,actualErrorMessage);

        softAssert.assertAll();
    }

    @Test(groups = {"sanity","regression"})
    public void verifyUserShouldAbleToRegisterWithValidInputInAllMandatoryFields() throws InterruptedException {

        homePage.clickOnRegisterTab();

        Thread.sleep(1000);
        registerPage.enterFirstName("Amit");
        registerPage.enterLastName("Patel");
        Thread.sleep(1000);
        registerPage.enterEmail("Amit","cvbn8@gmail.com");
        registerPage.enterPassword("uiop987");
        Thread.sleep(1000);
        registerPage.enterConfirmPassword("uiop987");
        Thread.sleep(1000);
        registerPage.clickOnRegisterButton();

        String expectedRegistrationText = "Your registration completed";
        String actualRegistrationText = registerPage.getRegistrationCompleteText();
        softAssert.assertEquals(expectedRegistrationText,actualRegistrationText);

        softAssert.assertAll();

    }


}
