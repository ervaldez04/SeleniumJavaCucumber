Feature: Bank site should be working
  Scenario: Should be able to login via Customer
    Given User navigates to Bank site
    When User clicks Customer Login
    And User logins using "Hermoine Granger"
    Then User will be redirected to "Hermoine Granger" Homepage

  Scenario: Should be able to add customer
    Given User navigates to Bank site
    When User clicks Add Customer
    And User inputs required fields "abc", "def", "123"
    Then Details is be added successfully "Customer added successfully with customer id"

  Scenario: Should be able to open account
    Given User navigates to Bank site
    And User creates new customer "abcd", "def", "123"
    When User clicks Open Account
    And User inputs account details "abcd", "def", "Dollar"
    Then Details is be added successfully "Account created successfully with account Number"

#  Scenario: Should be able to delete customer
#    Given User navigates to Bank site
#    And User creates new customer "aaa", "def", "123"
#    When User clicks Customer
#    And User deletes "aaa"
#    Then Customer will be deleted

  Scenario: Should be able to deposit amount
    Given User navigates to Bank site
    And User logins via Customer "Hermoine Granger"
    When User selects Deposit
    And User enters deposit of "100"
    Then Transaction Message of "Deposit Successful" is Displayed

  Scenario: Should be able to withdraw amount less than balance
    Given User navigates to Bank site
    And User logins via Customer "Hermoine Granger"
    When User selects Withdrawl
    And User enters withdrawal amount of "100"
    Then Transaction Message of "Transaction successful" is Displayed

  Scenario: Should not be able to withdraw amount more than balance
    Given User navigates to Bank site
    And User logins via Customer "Hermoine Granger"
    When User selects Withdrawl
    And User enters withdrawal amount of "10000"
    Then Transaction Message of "Transaction Failed. You can not withdraw amount more than the balance." is Displayed

  Scenario: Should be able to logout
    Given User navigates to Bank site
    And User logins via Customer "Hermoine Granger"
    When User clicks Logout
    Then User is redirected to login page




