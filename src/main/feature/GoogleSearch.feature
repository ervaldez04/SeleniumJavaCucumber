Feature: Google Search

  Scenario: Should be able to refresh page
    Given User is in Google site
    When User refreshes page
    Then Page will be redirected to Google site

  Scenario: Should be able to search Test Automation
    Given User is in Google site
    When User inputs "Test Automation"
    And User hit enter
    Then Page will be redirected to "Test Automation" Google Search
    And User closes browser

  @Failed
  Scenario: Should be able to fail test
    Given User is in Google site
    When User inputs "Test Automation"
    And User hit enter
    Then Page will be redirected to "Test Automations" Google Search
    And User closes browser

  Scenario: Should be able to search Cucumber
    Given User is in Google site
    When User inputs "Cucumber"
    And User hit enter
    Then Page will be redirected to "Cucumber" Google Search
    And User closes browser

