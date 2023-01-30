Feature: Login Functionality

  @SmokeTest @RegresstionTest
  Scenario: Should be able to login successfully
    Given User navigate to Swag Labs site
    When User inputs "standard_user" and "secret_sauce"
    Then User will be able to go to dashboard page
    And User closes window

  @RegressionTest
  Scenario: Should not be able to login using locked user
    Given User navigate to Swag Labs site
    When User inputs "locked_out_user" and "secret_sauce"
    Then Page will display "Epic sadface: Sorry, this user has been locked out."
    And User closes window

  @RegressionTest
  Scenario: Should not be able to login using invalid username
    Given User navigate to Swag Labs site
    When User inputs "invalid_user" and "secret_sauce"
    Then Page will display "Epic sadface: Username and password do not match any user in this service"
    And User closes window

  @RegressionTest
  Scenario: Should not be able to login using invalid password
    Given User navigate to Swag Labs site
    When User inputs "standard_user" and "secret_saucee"
    Then Page will display "Epic sadface: Username and password do not match any user in this service"
    And User closes window

  @Failed
  Scenario: Should fail test
    Given User navigate to Swag Labs site
    When User inputs "standard_user" and "secret_saucee"
    Then User will be able to go to dashboard page
    And User closes window