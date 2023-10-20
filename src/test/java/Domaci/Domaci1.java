package Domaci;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Domaci1 {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.navigate().to("https://www.youtube.com/");

        WebElement searchBox = driver.findElement(By.xpath("/html/body/ytd-app/div[1]/div/ytd-masthead/div[4]/div[2]/ytd-searchbox/form/div[1]/div[1]/input"));
        searchBox.sendKeys("Kuca poso");

        WebElement searchButton = driver.findElement(By.id("search-icon-legacy"));
        searchButton.click();

        Thread.sleep(3000);
        WebElement pesmaLink = driver.findElement(By.xpath("//a[@title='Ekrem Jevric Kuca Poso']"));
        pesmaLink.click();
    }
}
