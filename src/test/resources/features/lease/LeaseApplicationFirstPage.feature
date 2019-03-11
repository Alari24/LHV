@Test
Feature: Lease application first page

  Scenario Outline: Check if radio buttons act correctly
    Given user is on LHV lease application page for <browser>
    When user clicks on <whichQuestion> questions first radio button
    Then desired questions first radio button is checked
    When user clicks on desired questions second radio button
    Then desired questions first radio button is unchecked
    And desired questions second radio button is checked
    Examples:
      | browser  | whichQuestion |
      | firefox  | first         |
      | firefox  | second        |
      | chrome   | first         |
      | chrome   | second        |
      | explorer | first         |
      | explorer | second        |