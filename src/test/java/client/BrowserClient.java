package client;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public class BrowserClient {
    private WebDriver activeDriver;

    public void openDesiredWebPage(String browserType, String desiredPage) {
        switch (browserType) {
            case ("chrome"):
                activeDriver = new ChromeDriver();
                break;
            case ("firefox"):
                activeDriver = new FirefoxDriver();
                break;
            case ("explorer"):
                activeDriver = new InternetExplorerDriver();
                break;
            default:
                throw new RuntimeException("No such option as " + browserType + " available");
        }
        activeDriver.manage().window().maximize();
        activeDriver.get(desiredPage);
    }

    public void closeBrowser() {
        activeDriver.close();
    }

    public void clickDesiredElementById(String elementId) {
        activeDriver.switchTo().frame("iframe");
        WebElement element = activeDriver.findElement(By.id(elementId));
        element.click();
        activeDriver.switchTo().defaultContent();
    }

    public void checkIfElementIsSelected(String elementId) {
        activeDriver.switchTo().frame("iframe");
        if (!activeDriver.findElement(By.id(elementId)).isSelected()) {
            throw new RuntimeException("Radio button is not selected");
        }
        activeDriver.switchTo().defaultContent();
    }

    public void checkIfElementIsNotSelected(String elementId) {
        activeDriver.switchTo().frame("iframe");
        if (activeDriver.findElement(By.id(elementId)).isSelected()) {
            throw new RuntimeException("Radio button is still selected");
        }
        activeDriver.switchTo().defaultContent();
    }

    public void waitUntilIframeIsVisible() {
        await().atMost(5, TimeUnit.SECONDS)
                .with()
                .pollInterval(100, TimeUnit.MILLISECONDS).untilAsserted(() -> {
            activeDriver.findElement(By.id("iframe"));
        });
    }
}