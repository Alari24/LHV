package client;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class BrowserClient {
    private WebDriver activeDriver;

    //Browser activities
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
                throw new IllegalArgumentException("No such browser as " + browserType + " available");
        }
        activeDriver.manage().window().maximize();
        activeDriver.get(desiredPage);
    }

    public void closeBrowser() {
        activeDriver.quit();
    }

    //Iframe switch
    public void switchToDesiredFrame(String element) {
        activeDriver.switchTo().frame(element);
    }

    public void switchToDefaultContent() {
        activeDriver.switchTo().defaultContent();
    }

    //Different element clickables
    public void clickDesiredElementById(String elementId) {
        WebDriverWait wait = new WebDriverWait(activeDriver, 5);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id(elementId)));
        element.click();
    }

    public void clickDesiredElementByXpath(String elementXpath) {
        WebDriverWait wait = new WebDriverWait(activeDriver, 5);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath)));
        if (element.isEnabled() && element.isDisplayed()) {
            element.click();
        }
    }

    //Different element checks
    public void checkIfRadioButtonIsSelected(String elementId) {
        if (!activeDriver.findElement(By.id(elementId)).isSelected()) {
            throw new RuntimeException("Radio button is not selected");
        }
    }

    public void checkIfRadioButtonIsNotSelectedAnymore(String elementId) {
        if (activeDriver.findElement(By.id(elementId)).isSelected()) {
            throw new RuntimeException("Radio button is still selected");
        }
    }

    public void checkIfTooltipIsOpen(String tooltipId) {
        if (!activeDriver.findElement(By.id(tooltipId)).getCssValue("display").equals("block")) {
            throw new RuntimeException("Tooltip is not open");
        }
    }

    public void checkIfTooltipIsClosed(String tooltipId) {
        if (activeDriver.findElement(By.id(tooltipId)).getCssValue("display").equals("block")) {
            throw new RuntimeException("Tooltip is not closed");
        }
    }

    public void checkDropdownValues(String dropdownId, String type) {
        Select dropdown = new Select(activeDriver.findElement(By.id(dropdownId)));
        switch (type) {
            case "year":
                String[] expectedYears = {"0", "1", "2", "3", "4", "5", "6"};
                for (int i = 0; i < expectedYears.length; i++) {
                    if (!dropdown.getOptions().get(i).getText().equals(expectedYears[i])) {
                        throw new RuntimeException("Values in dropdown for year are incorrect");
                    }
                }
                break;
            case "month":
                String[] expectedMonths = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
                for (int i = 0; i < expectedMonths.length; i++) {
                    if (!dropdown.getOptions().get(i).getText().equals(expectedMonths[i])) {
                        throw new RuntimeException("Values in dropdown for month are incorrect");
                    }
                }
                break;
            default:
                throw new IllegalArgumentException("No such type as " + type + " available");
        }
    }

    public void checkIfCorrectErrorIsDisplayed(String elementClass, String language, String errorType) {
        WebElement element = activeDriver.findElement(By.className(elementClass));
        String desiredErrorMessage;
        switch (language) {
            case "et":
                switch (errorType) {
                    case "general":
                        desiredErrorMessage = "Vigased andmed, palun kontrolli märgitud väljasid.";
                        break;
                    case "downpayment":
                        desiredErrorMessage = "Vigased andmed, palun kontrolli märgitud väljasid.\n" +
                                "Sissemakse protsent ja summa ei ole vastavuses";
                        break;
                    case "residual":
                        desiredErrorMessage = "Vigased andmed, palun kontrolli märgitud väljasid.\n" +
                                "Jääkväärtuse protsent ja summa ei ole vastavuses";
                        break;
                    case "all":
                        desiredErrorMessage = "Vigased andmed, palun kontrolli märgitud väljasid.\n" +
                                "Sissemakse protsent ja summa ei ole vastavuses\n" +
                                "Jääkväärtuse protsent ja summa ei ole vastavuses";
                        break;
                    default:
                        throw new IllegalArgumentException("No such error type as " + errorType + " available");
                }
                break;
            case "en":
                switch (errorType) {
                    case "general":
                        desiredErrorMessage = "Invalid parameters - please check the marked fields.";
                        break;
                    case "downpayment":
                        desiredErrorMessage = "Invalid parameters - please check the marked fields.\n" +
                                "Non-compliance between downpayment percentage and amount";
                        break;
                    case "residual":
                        desiredErrorMessage = "Invalid parameters - please check the marked fields.\n" +
                                "Non-compliance between residual value percentage and amount";
                        break;
                    case "all":
                        desiredErrorMessage = "Invalid parameters - please check the marked fields.\n" +
                                "Non-compliance between downpayment percentage and amount\n" +
                                "Non-compliance between residual value percentage and amount";
                        break;
                    default:
                        throw new IllegalArgumentException("No such error type as " + errorType + " available");
                }
                break;
            case "ru":
                switch (errorType) {
                    case "general":
                        desiredErrorMessage = "Неверные данные, пожалуйста, проверьте отмеченные поля.";
                        break;
                    case "downpayment":
                        desiredErrorMessage = "Неверные данные, пожалуйста, проверьте отмеченные поля.\n" +
                                "Процент взноса не соответствует сумме";
                        break;
                    case "residual":
                        desiredErrorMessage = "Неверные данные, пожалуйста, проверьте отмеченные поля.\n" +
                                "Процент остаточной стоимости не соответствует сумме";
                        break;
                    case "all":
                        desiredErrorMessage = "Неверные данные, пожалуйста, проверьте отмеченные поля.\n" +
                                "Процент взноса не соответствует сумме\n" +
                                "Процент остаточной стоимости не соответствует сумме";
                        break;
                    default:
                        throw new IllegalArgumentException("No such error type as " + errorType + " available");
                }
                break;
            default:
                throw new IllegalArgumentException("No such language as " + language + " available");
        }
        if (!element.getText().equals(desiredErrorMessage)) {
            throw new RuntimeException("Displayed error message is incorrect.\nActual message: " + element.getText() + "\nDesired message: " + desiredErrorMessage);
        }
    }

    public void checkIfElementIsNotPresent(String elementId) {
        if (activeDriver.findElements(By.id(elementId)).size() > 0) {
            throw new RuntimeException("Element is found");
        }
    }

    public void checkIfElementIsPresent(String elementId) {
        if (activeDriver.findElements(By.id(elementId)).size() < 0) {
            throw new RuntimeException("Element is not found");
        }
    }

    public void checkIfElementHasDesiredClass(String elementXpath, String desiredClass) {
        WebElement element = activeDriver.findElement(By.xpath(elementXpath));
        if (!element.getAttribute("class").equals(desiredClass)) {
            throw new RuntimeException("Element is not marked");
        }
    }

    public void checkCalculationCorrectness(String percentageField, String valueField, String type) {
        WebElement price = activeDriver.findElement(By.id("origin-price"));
        WebElement percentage = activeDriver.findElement(By.id(percentageField));
        WebElement value = activeDriver.findElement(By.id(valueField));
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.00", symbols);
        String calculation;
        switch (type) {
            case "value":
                calculation = String.valueOf(df.format(Double.parseDouble(percentage.getAttribute("value")) * Double.parseDouble(price.getAttribute("value")) / 100));
                if (!calculation.equals(value.getAttribute("value"))) {
                    throw new RuntimeException("Calculation incorrect. Desired value: " + calculation + "\nActual value: " + value.getAttribute("value"));
                }
                break;
            case "percentage":
                calculation = String.valueOf(df.format(Double.parseDouble(value.getAttribute("value")) * 100 / Double.parseDouble(price.getAttribute("value"))));
                if (!calculation.equals(percentage.getAttribute("value"))) {
                    throw new RuntimeException("Calculation incorrect. Desired value: " + calculation + "\nActual value: " + percentage.getAttribute("value"));
                }
                break;
            default:
                throw new IllegalArgumentException("No such type as " + type);
        }
    }

    public void checkGrammar(String textLocationXpath, String desiredText) {
        WebElement label = activeDriver.findElement(By.xpath(textLocationXpath));
        if (!label.getText().equals(desiredText)) {
            throw new RuntimeException("Text does not match desired text");
        }
    }

    public void checkIfElementIsHidden(String elementId) {
        WebElement element = activeDriver.findElement(By.id(elementId));
        if (!element.getAttribute("class").equals("hidden")) {
            throw new RuntimeException("Element is still visible");
        }
    }

    //Selecting and filling values
    public void fillFieldWithValue(String fieldId, String value) {
        WebElement element = activeDriver.findElement(By.id(fieldId));
        element.clear();
        element.sendKeys(value);
    }

    public void selectValueFromDropdown(String dropDownId, String value) {
        Select element = new Select(activeDriver.findElement(By.id(dropDownId)));
        element.selectByValue(value);
    }

    //Waiting until workable element is visible
    public void waitUntilElementIsVisible(String element) {
        WebDriverWait wait = new WebDriverWait(activeDriver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(element)));
    }
}