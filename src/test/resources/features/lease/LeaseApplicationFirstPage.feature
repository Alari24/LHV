@Full
Feature: Lease application first page

  Scenario Outline: Check if radio buttons act correctly
    Given user is on LHV lease application page for <browser> and <language>
    When user clicks on <whichQuestion> questions first radio button
    Then desired questions first radio button is checked
    When user clicks on desired questions second radio button
    Then desired questions first radio button is unchecked
    And desired questions second radio button is checked
    Examples:
      | browser  | whichQuestion | language |
      | firefox  | first         | et       |
      | firefox  | first         | en       |
      | firefox  | first         | ru       |
      | firefox  | second        | et       |
      | firefox  | second        | en       |
      | firefox  | second        | ru       |
      | chrome   | first         | et       |
      | chrome   | first         | en       |
      | chrome   | first         | ru       |
      | chrome   | second        | et       |
      | chrome   | second        | en       |
      | chrome   | second        | ru       |
      | explorer | first         | et       |
      | explorer | first         | en       |
      | explorer | first         | ru       |
      | explorer | second        | et       |
      | explorer | second        | en       |
      | explorer | second        | ru       |

  Scenario Outline: Check if help text is present for second question
    Given user is on LHV lease application page for <browser> and <language>
    When user clicks on help text button next to second question
    Then help text is displayed for the user
    When user clicks on 'X' to close help text
    Then help text box is visible no more
    When user clicks on help text button next to second question
    Then help text is displayed for the user
    When user clicks away to close help text
    Then help text box is visible no more
    Examples:
      | browser  | language |
      | firefox  | et       |
      | firefox  | en       |
      | firefox  | ru       |
      | chrome   | et       |
      | chrome   | en       |
      | chrome   | ru       |
      | explorer | et       |
      | explorer | en       |
      | explorer | ru       |

  Scenario Outline: Check if value fields accept only numbers
    Given user is on LHV lease application page for <browser> and <language>
    When user enters string values to all value fields
    And user selects 1 for lease period year
    And user clicks Next
    Then general message will appear
    And broken question labels are marked in red
    And next page is not displayed
    When user enters numerical values to all value fields
    And user clicks Next
    Then next page is displayed
    Examples:
      | browser  | language |
      | firefox  | et       |
      | firefox  | en       |
      | firefox  | ru       |
      | chrome   | et       |
      | chrome   | en       |
      | chrome   | ru       |
      | explorer | et       |
      | explorer | en       |
      | explorer | ru       |

  Scenario Outline: Check if value fields accept numbers correctly
    Given user is on LHV lease application page for <browser> and <language>
    When user enters numerical values to all value fields
    And user selects 1 for lease period year
    And user enters 1000000000000 into vehicle price field
    And user clicks Next
    Then general message will appear
    And vehicle price label is marked in red
    When user enters 1000 into vehicle price field
    And user enters 110 as percentage for downpayment
    And user clicks Next
    Then downpayment message will appear
    And downpayment label is marked in red
    When user enters 100 as percentage for downpayment
    And user clicks Next
    Then general message will appear
    And downpayment label is marked in red
    When user enters 1100 into downpayment field
    And user clicks Next
    Then downpayment message will appear
    And downpayment label is marked in red
    When user enters 50 into downpayment field
    And user enters 110 as percentage for residual
    And user clicks Next
    Then residual message will appear
    And residual value label is marked in red
    When user enters 100 as percentage for residual
    And user clicks Next
    Then general message will appear
    And residual value label is marked in red
    When user enters 1100 into residual value field
    And user clicks Next
    Then residual message will appear
    And residual value label is marked in red
    When user enters 50 into residual value field
    And user clicks Next
    Then next page is displayed
    Examples:
      | browser  | language |
      | firefox  | et       |
      | firefox  | en       |
      | firefox  | ru       |
      | chrome   | et       |
      | chrome   | en       |
      | chrome   | ru       |
      | explorer | et       |
      | explorer | en       |
      | explorer | ru       |

  Scenario Outline: Check if automatic calculations are correct
    Given user is on LHV lease application page for <browser> and <language>
    When user enters 1000 into vehicle price field
    And user enters 10 as percentage for downpayment
    Then correct value is displayed in downpayment value field
    When user enters 200 into downpayment field
    Then correct value is displayed in downpayment percentage field
    When user enters 10 as percentage for residual
    Then correct value is displayed in residual value value field
    When user enters 200 into residual value field
    Then correct value is displayed in residual value percentage field
    Examples:
      | browser | language |
      | firefox  | et       |
      | firefox  | en       |
      | firefox  | ru       |
      | chrome   | et       |
      | chrome   | en       |
      | chrome   | ru       |
      | explorer | et       |
      | explorer | en       |
      | explorer | ru       |