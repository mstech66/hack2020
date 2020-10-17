@Orders @Rest
Feature: Orders

  Scenario: Placing Order
    Given User wants to place order
    When User navigates to place order url
    Then Order has been placed

  Scenario: Placing Order when there are no items in cart
    Given User wants to place order
    When User navigates to place order url
    Then User should get the statusCode
      | 404 |

  Scenario: Getting all orders
    Given User wants to get all orders
    When User navigates to orders url
    Then User gets all the orders
      | iPhone XR |