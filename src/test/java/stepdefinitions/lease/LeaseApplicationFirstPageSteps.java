package stepdefinitions.lease;

import client.BrowserClient;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LeaseApplicationFirstPageSteps {
    private BrowserClient client = new BrowserClient();
    private String firstRadioButton;
    private String secondRadioButton;
    private String iframeName = "iframe";
    private String language;
    private String dropDownValue;

    @Given("^user is on LHV lease application page for (.+) and (.+)$")
    public void userHasOpenedBrowserOnTheDesiredPage(String browserType, String language) {
        String desiredPage;
        this.language = language;
        switch (language) {
            case "et":
                desiredPage = "https://www.lhv.ee/et/liising/taotlus";
                break;
            case "ru":
                desiredPage = "https://www.lhv.ee/ru/lizing/hodatajstvo";
                break;
            case "en":
                desiredPage = "https://www.lhv.ee/en/leasing/application";
                break;
            default:
                throw new IllegalArgumentException("No such language as " + language + " available");
        }
        client.openDesiredWebPage(browserType, desiredPage);
    }

    @When("^user clicks on (.+) questions first radio button$")
    public void userClicksOnFirstQuestionsFirstRadioButton(String question) {
        client.waitUntilElementIsVisible(iframeName);
        client.switchToDesiredFrame("iframe");
        if (question.equals("first")) {
            firstRadioButton = "account_type-P";
            secondRadioButton = "account_type-C";
        } else {
            firstRadioButton = "lease_type-HP";
            secondRadioButton = "lease_type-FL";
        }
        client.clickDesiredElementById(firstRadioButton);
        client.switchToDefaultContent();
    }

    @When("^user clicks on desired questions second radio button$")
    public void userClicksOnFirstQuestionsSecondRadioButton() {
        client.switchToDesiredFrame(iframeName);
        client.clickDesiredElementById(secondRadioButton);
        client.switchToDefaultContent();
    }

    @When("user clicks on help text button next to second question")
    public void userClicksOnHelpTextButtonNextToSecondQuestion() {
        client.switchToDesiredFrame(iframeName);
        client.clickDesiredElementByXpath("//*[@id=\"form1\"]/table/tbody[1]/tr[2]/td[2]/a");
        client.switchToDefaultContent();
    }

    @When("user clicks on 'X' to close help text")
    public void userClicksOnToCloseHelpText() {
        client.switchToDesiredFrame(iframeName);
        client.clickDesiredElementByXpath("//*[@id=\"tooltip01\"]/div[1]/p/a");
        client.switchToDefaultContent();
    }

    @When("user clicks away to close help text")
    public void userClicksAwayToCloseHelpText() {
        client.clickDesiredElementById("content");
    }

    @When("user enters string values to all value fields")
    public void userEntersStringValuesToAllValueFields() {
        client.switchToDesiredFrame(iframeName);
        client.fillFieldWithValue("origin-price", "asd");
        client.fillFieldWithValue("initial_percentage", "asd");
        client.fillFieldWithValue("initial", "asd");
        client.fillFieldWithValue("reminder_percentage", "asd");
        client.fillFieldWithValue("reminder", "asd");
        client.switchToDefaultContent();
    }

    @When("user enters numerical values to all value fields")
    public void userEntersNumericalValuesToAllValueFields() {
        client.switchToDesiredFrame(iframeName);
        client.fillFieldWithValue("origin-price", "50");
        client.fillFieldWithValue("initial_percentage", "50");
        client.fillFieldWithValue("reminder_percentage", "50");
        client.switchToDefaultContent();
    }

    @When("user checks dropdown for lease period (.+)")
    public void userOpenDropdownForLeasePeriodYears(String period) {
        client.switchToDesiredFrame(iframeName);
        String elementId;
        switch (period) {
            case "year":
                elementId = "duration_years";
                break;
            case "month":
                elementId = "duration_months";
                break;
            default:
                throw new IllegalArgumentException("No such period as " + period);
        }
        client.checkDropdownValues(elementId, period);
        client.switchToDefaultContent();
    }

    @Then("^desired questions first radio button is checked$")
    public void firstQuestionsFirstRadioButtonIsChecked() {
        client.switchToDesiredFrame(iframeName);
        client.checkIfRadioButtonIsSelected(firstRadioButton);
        client.switchToDefaultContent();
    }

    @Then("^desired questions first radio button is unchecked$")
    public void firstQuestionsFirstRadioButtonIsUnchecked() {
        client.switchToDesiredFrame(iframeName);
        client.checkIfRadioButtonIsNotSelectedAnymore(firstRadioButton);
        client.switchToDefaultContent();
    }

    @Then("help text is displayed for the user")
    public void helpTextIsDisplayedForTheUser() {
        client.switchToDesiredFrame(iframeName);
        client.checkIfTooltipIsOpen("tooltip01");
        client.switchToDefaultContent();
    }

    @Then("help text box is visible no more")
    public void helpTextBoxIsVisibleNoMore() {
        client.switchToDesiredFrame(iframeName);
        client.checkIfTooltipIsClosed("tooltip01");
        client.switchToDefaultContent();
    }

    @Then("(.+) message will appear")
    public void errorMessageWillAppear(String errorType) {
        client.switchToDesiredFrame(iframeName);
        client.waitUntilElementIsVisible("msg-container");
        client.checkIfCorrectErrorIsDisplayed("message", language, errorType);
        client.switchToDefaultContent();
    }

    @Then("next page is displayed")
    public void nextPageIsDisplayed() {
        client.switchToDesiredFrame(iframeName);
        client.checkIfElementIsPresent("vehicle_condition-N");
        client.checkIfElementIsPresent("seller_type-C");
        client.switchToDefaultContent();
    }

    @Then("correct value is displayed in (.+) (.+) field")
    public void correctValueIsDisplayedInDownpaymentField(String field, String type) {
        client.switchToDesiredFrame(iframeName);
        String percentageField;
        String valueField;
        switch (field) {
            case "downpayment":
                percentageField = "initial_percentage";
                valueField = "initial";
                break;
            case "residual value":
                percentageField = "reminder_percentage";
                valueField = "reminder";
                break;
            default:
                throw new IllegalArgumentException("No such option as " + field);
        }
        client.checkCalculationCorrectness(percentageField, valueField, type);
        client.switchToDefaultContent();
    }

    @Then("label for lease period (.+) is correct for (.+)")
    public void labelForLeasePeriodYearIsCorrect(String period, String multitude) {
        client.switchToDesiredFrame(iframeName);
        String element;
        String desiredText;
        switch (period) {
            case "year":
                element = "//*[@id=\"form1\"]/table/tbody[3]/tr[1]/td/span/span[2]";
                switch (language) {
                    case "et":
                        desiredText = "aasta";
                        if (multitude.equals("multiple")) {
                            desiredText = "aastat";
                        }
                        break;
                    case "en":
                        desiredText = "year";
                        if (multitude.equals("multiple")) {
                            desiredText = "years";
                        }
                        break;
                    case "ru":
                        desiredText = "год";
                        if (multitude.equals("multiple")) {
                            if (dropDownValue.equals("0") || dropDownValue.equals("5") || dropDownValue.equals("6")) {
                                desiredText = "лет";
                            } else {
                                desiredText = "года";
                            }
                        }
                        break;
                    default:
                        throw new IllegalArgumentException("No such language as " + language + " available");
                }
                break;
            case "month":
                element = "//*[@id=\"period-months\"]/td/span/span[2]";
                switch (language) {
                    case "et":
                        desiredText = "kuu";
                        if (multitude.equals("multiple")) {
                            desiredText = "kuud";
                        }
                        break;
                    case "en":
                        desiredText = "month";
                        if (multitude.equals("multiple")) {
                            desiredText = "months";
                        }
                        break;
                    case "ru":
                        desiredText = "месяц";
                        if (multitude.equals("multiple")) {
                            if (dropDownValue.equals("2") || dropDownValue.equals("3") || dropDownValue.equals("4")) {
                                desiredText = "месяца";
                            } else {
                                desiredText = "месяцев";
                            }
                        }
                        break;
                    default:
                        throw new IllegalArgumentException("No such language as " + language + " available");
                }
                break;
            default:
                throw new IllegalArgumentException("No such period as " + period);
        }
        client.checkGrammar(element, desiredText);
        client.switchToDefaultContent();
    }

    @And("^desired questions second radio button is checked$")
    public void firstQuestionsSecondRadioButtonIsChecked() {
        client.switchToDesiredFrame(iframeName);
        client.checkIfRadioButtonIsSelected(secondRadioButton);
        client.switchToDefaultContent();
    }

    @And("user clicks Next")
    public void userClicksNext() {
        client.switchToDesiredFrame(iframeName);
        client.clickDesiredElementByXpath("//*[@id=\"form1\"]/div[2]/button");
        client.switchToDefaultContent();
    }

    @And("user selects (.+) for lease period (.+)")
    public void selectsValueForLeasePeriod(String value, String period) {
        client.switchToDesiredFrame(iframeName);
        dropDownValue = value;
        switch (period) {
            case "year":
                period = "duration_years";
                break;
            case "month":
                period = "duration_months";
                break;
            default:
                throw new IllegalArgumentException("No such period as " + period);
        }
        client.selectValueFromDropdown(period, value);
        client.switchToDefaultContent();
    }

    @And("next page is not displayed")
    public void nextPageIsNotDisplayed() {
        client.switchToDesiredFrame(iframeName);
        client.checkIfElementIsNotPresent("vehicle_condition-N");
        client.checkIfElementIsNotPresent("seller_type-C");
        client.switchToDefaultContent();
    }

    @And("broken question labels are marked in red")
    public void brokenFieldLabelsAreMarkedInRed() {
        client.switchToDesiredFrame(iframeName);
        client.checkIfElementHasDesiredClass("//*[@id=\"form1\"]/table/tbody[1]/tr[3]/th/label", "req error");
        client.checkIfElementHasDesiredClass("//*[@id=\"form1\"]/table/tbody[2]/tr[1]/th/label", "req error");
        client.checkIfElementHasDesiredClass("//*[@id=\"form1\"]/table/tbody[4]/tr[1]/th/label", "req error");
        client.switchToDefaultContent();
    }

    @And("user enters (.+) into (.+) field")
    public void userEntersValueIntoField(String value, String field) {
        client.switchToDesiredFrame(iframeName);
        switch (field) {
            case "vehicle price":
                field = "origin-price";
                break;
            case "downpayment":
                field = "initial";
                break;
            case "residual value":
                field = "reminder";
                break;
            default:
                throw new IllegalArgumentException("No such field as " + field);
        }
        client.fillFieldWithValue(field, value);
        client.switchToDefaultContent();
    }

    @And("user enters (.+) as percentage for (.+)")
    public void userEntersValueAsPercentageFor(String value, String field) {
        client.switchToDesiredFrame(iframeName);
        switch (field) {
            case "downpayment":
                field = "initial_percentage";
                break;
            case "residual":
                field = "reminder_percentage";
                break;
            default:
                throw new IllegalArgumentException("No such field as " + field);
        }

        client.fillFieldWithValue(field, value);
        client.switchToDefaultContent();
    }

    @And("(.+) label is marked in red")
    public void LabelIsMarkedInRed(String whatLabel) {
        client.switchToDesiredFrame(iframeName);
        String path;
        switch (whatLabel) {
            case "vehicle price":
                path = "//*[@id=\"form1\"]/table/tbody[1]/tr[3]/th/label";
                break;
            case "downpayment":
                path = "//*[@id=\"form1\"]/table/tbody[2]/tr[1]/th/label";
                break;
            case "residual value":
                path = "//*[@id=\"form1\"]/table/tbody[4]/tr[1]/th/label";
                break;
            default:
                throw new IllegalArgumentException("No such label as " + whatLabel);
        }
        client.checkIfElementHasDesiredClass(path, "req error");
        client.switchToDefaultContent();
    }

    @And("months dropdown is not visible")
    public void monthsDropdownIsNotVisible() {
        client.switchToDesiredFrame(iframeName);
        client.checkIfElementIsHidden("period-months");
        client.switchToDefaultContent();
    }

    @After
    public void userClosesOpenBrowser() {
        client.closeBrowser();
    }
}