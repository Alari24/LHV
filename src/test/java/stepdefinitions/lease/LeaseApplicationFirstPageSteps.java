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

    @Given("^user is on LHV lease application page for (.+)$")
    public void userHasOpenedBrowserOnTheDesiredPage(String browserType) {
        String desiredPage = "https://www.lhv.ee/et/liising/taotlus";
        client.openDesiredWebPage(browserType, desiredPage);
    }

    @When("^user clicks on (.+) questions first radio button$")
    public void userClicksOnFirstQuestionsFirstRadioButton(String question) {
        client.waitUntilIframeIsVisible();
        if (question.equals("first")) {
            firstRadioButton = "account_type-P";
            secondRadioButton = "account_type-C";
        } else {
            firstRadioButton = "lease_type-HP";
            secondRadioButton = "lease_type-FL";
        }
        client.clickDesiredElementById(firstRadioButton);
    }

    @When("^user clicks on desired questions second radio button$")
    public void userClicksOnFirstQuestionsSecondRadioButton() {
        client.clickDesiredElementById(secondRadioButton);
    }

    @Then("^desired questions first radio button is checked$")
    public void firstQuestionsFirstRadioButtonIsChecked() {
        client.checkIfElementIsSelected(firstRadioButton);
    }

    @Then("^desired questions first radio button is unchecked$")
    public void firstQuestionsFirstRadioButtonIsUnchecked() {
        client.checkIfElementIsNotSelected(firstRadioButton);
    }

    @And("^desired questions second radio button is checked$")
    public void firstQuestionsSecondRadioButtonIsChecked() {
        client.checkIfElementIsSelected(secondRadioButton);
    }

    @After
    public void userClosesOpenBrowser() {
        client.closeBrowser();
    }
}