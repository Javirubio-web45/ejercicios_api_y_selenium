Feature: Sauce Shopping Automation
  Scenario: Testing the authentication
    Given I go to the Website
    When I specify my credentials and click on login
    Then I can log into website

  Scenario: Testing locked user
    Given I go to the Website
    When I specify my credentials with locked user and click on login
    Then I can see an error message

  Scenario: Testing adding and removing products
    Given I go to the Website
    When I specify my credentials and click on login
    And I add three products to cart
    And I remove one product
    And I go to cart page
    Then I can see two products present

  Scenario: Testing go to product details form cart page
    Given I go to the Website
    When I specify my credentials and click on login
    And I add one product to cart
    And I go to cart page
    And I click on product name
    Then I can see product details

  Scenario: Testing product checkout
    Given I go to the Website
    When I specify my credentials and click on login
    And I add one product to cart
    And I go to cart page
    And I click on checkout button
    And I fill all the fields
    And I click on continue button
    Then Total price to pay is correct
    And I click on finish button
    And I can see the thanks to order message
