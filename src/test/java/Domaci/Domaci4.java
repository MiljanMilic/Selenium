package Domaci;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Domaci4 {
    WebDriver driver;
    String URL = "https://practicetestautomation.com/";

    @BeforeMethod
    public void setUpPage() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.navigate().to(URL);
        WebElement practiceButton = driver.findElement(By.id("menu-item-20"));
        practiceButton.click();
        WebElement testLoginPage = driver.findElement(By.linkText("Test Login Page"));
        testLoginPage.click();

    }

    @Test
    public void positiveLogin() {
        String validUsername = "student";
        String validPassword = "Password123";
        String expectedMsg = "Congratulations " + validUsername + ". You successfully logged in!";

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        usernameField.clear();
        usernameField.sendKeys(validUsername);
        passwordField.clear();
        passwordField.sendKeys(validPassword);
        submitButton.click();

        WebElement actualMsg = driver.findElement(By.className("has-text-align-center"));
        WebElement loginConfirmationMsg = driver.findElement(By.className("post-title"));
        WebElement logoutButton = driver.findElement(By.linkText("Log out"));

        Assert.assertEquals(actualMsg.getText(), expectedMsg);
        Assert.assertTrue(loginConfirmationMsg.isDisplayed());
        Assert.assertEquals(driver.getCurrentUrl(), "https://practicetestautomation.com/logged-in-successfully/");
        Assert.assertTrue(logoutButton.isDisplayed());

    }

    @Test(priority = 1)
    public void negativeUsername(){
        String invalidUsername = "incorrectUser";
        String validPassword = "Password123";
        String expectedErrorMsg = "Your username is invalid!";

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        usernameField.clear();
        usernameField.sendKeys(invalidUsername);
        passwordField.clear();
        passwordField.sendKeys(validPassword);
        submitButton.click();

        WebElement errorMsg = driver.findElement(By.id("error"));

        Assert.assertTrue(errorMsg.isDisplayed());
        Assert.assertEquals(errorMsg.getText(), expectedErrorMsg);


    }

    @Test(priority = 2)
    public void negativePassword(){
        String validUsername = "student";
        String invalidPassword = "incorrectPassword";
        String expectedErrorMsg = "Your password is invalid!";

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        usernameField.clear();
        usernameField.sendKeys(validUsername);
        passwordField.clear();
        passwordField.sendKeys(invalidPassword);
        submitButton.click();

        WebElement errorMsg = driver.findElement(By.id("error"));

        Assert.assertTrue(errorMsg.isDisplayed());
        Assert.assertEquals(errorMsg.getText(), expectedErrorMsg);


    }

    @Test(priority = 3)
    public void emptyUsername(){
        String validPassword = "Password123";
        String expectedErrorMsg = "Your username is invalid!";

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        usernameField.clear();
        passwordField.clear();
        passwordField.sendKeys(validPassword);
        submitButton.click();

        WebElement errorMsg = driver.findElement(By.id("error"));

        Assert.assertTrue(errorMsg.isDisplayed());
        Assert.assertEquals(errorMsg.getText(), expectedErrorMsg);


    }

    @Test(priority = 4)
    public void emptyPassword(){
        String validUsername = "student";
        String expectedErrorMsg = "Your password is invalid!";

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        usernameField.clear();
        usernameField.sendKeys(validUsername);
        passwordField.clear();
        submitButton.click();

        WebElement errorMsg = driver.findElement(By.id("error"));

        Assert.assertTrue(errorMsg.isDisplayed());
        Assert.assertEquals(errorMsg.getText(), expectedErrorMsg);


    }

    @Test(priority = 5)
    public void emptyUsernameAndPassword(){

        String expectedErrorMsg = "Your username is invalid!";

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        usernameField.clear();
        passwordField.clear();
        submitButton.click();

        WebElement errorMsg = driver.findElement(By.id("error"));

        Assert.assertTrue(errorMsg.isDisplayed());
        Assert.assertEquals(errorMsg.getText(), expectedErrorMsg);


    }

    @Test(priority = 6)
    public void loggingOut(){

        positiveLogin();

        WebElement logoutButton = driver.findElement(By.linkText("Log out"));
        logoutButton.click();
        WebElement assertText = driver.findElement(By.xpath("//*[@id='login']/h2[contains(text(),'Test login')]"));

        Assert.assertEquals(driver.getCurrentUrl(), "https://practicetestautomation.com/practice-test-login/");
        Assert.assertTrue(assertText.isDisplayed());
    }

    @AfterMethod
    public void shutDown(){
        driver.quit();
    }
}
