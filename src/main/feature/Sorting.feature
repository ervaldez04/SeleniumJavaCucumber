Feature: Sort Functionality

  @Failed
  Scenario: Test will fail
    Given User is login to site
    When User select sort via "az"
    Then Items will be sorted in descending order

  Scenario: Should be able to sort in ascending order
    Given User is login to site
    When User select sort via "az"
    Then Items will be sorted in ascending order

  Scenario: Should be able to sort in descending order
    Given User is login to site
    When User select sort via "za"
    Then Items will be sorted in descending order
