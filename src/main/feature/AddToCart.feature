Feature: Add to Cart functionality

  Scenario: Should be able to add an item
    Given User is login to site
    When User add item in cart
    Then Item is displayed in minicart
    And User remove item in minicart