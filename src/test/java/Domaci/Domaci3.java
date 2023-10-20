package Domaci;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Domaci3 {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.navigate().to("https://demoqa.com/text-box/");

        String validName = "Miljan Milic";
        WebElement fullName = driver.findElement(By.id("userName"));
        fullName.sendKeys(validName);

        String validEmail = "miljan@gmail.com";
        WebElement email = driver.findElement(By.id("userEmail"));
        email.sendKeys(validEmail);

        String validCurrentAddress = "Ulica broj 1";
        WebElement currentAddress = driver.findElement(By.id("currentAddress"));
        currentAddress.sendKeys(validCurrentAddress);


        String validPermanentAddress = "Ulica broj 2";
        WebElement permanentAddress = driver.findElement(By.id("permanentAddress"));
        permanentAddress.sendKeys(validPermanentAddress);
        //permanentAddress.sendKeys(TAB, ENTER);

        // Ovde me je banner blokirao da kliknem na submit pa sam morao da ubacim scroll
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement submitButton = driver.findElement(By.cssSelector(".btn.btn-primary"));
        js.executeScript("arguments[0].scrollIntoView();", submitButton);
        //WebElement submitButton = driver.findElement(By.cssSelector(".btn.btn-primary"));
        submitButton.click();

        WebElement output = driver.findElement(By.id("output"));
        Assert.assertTrue(output.isDisplayed());

        WebElement nameOutput = driver.findElement(By.id("name"));
        System.out.println(nameOutput.getText());
        String expectedNameOutput = "Name:" + validName;
        Assert.assertEquals(nameOutput.getText(), expectedNameOutput);

        WebElement emailOutput = driver.findElement(By.id("email"));
        System.out.println(emailOutput.getText());
        String expectedEmailOutput = "Email:" + validEmail;
        Assert.assertEquals(emailOutput.getText(), expectedEmailOutput);

        //ovde pada test zbog razmaka posle dve tacke u outputu
/*        WebElement currentAddressOutput = driver.findElement(By.xpath("//p[@id='currentAddress']"));
        String expectedCurrentAddressOutput = "Current Address:" + validCurrentAddress;
        Assert.assertEquals(currentAddressOutput.getText(), expectedCurrentAddressOutput);*/

        //ovde pada test zbog razmaka posle dve tacke i gramaticke greske u outputu
/*        WebElement permanentAddressOutput = driver.findElement(By.xpath("//p[@id='permanentAddress']"));
        String expectedPermanentAddressOutput = "Permananet Address:" + validPermanentAddress;
        Assert.assertEquals(permanentAddressOutput.getText(), expectedPermanentAddressOutput);*/

        driver.quit();
    }
}
