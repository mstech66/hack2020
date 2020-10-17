@Cart @Rest
Feature: Cart

  Scenario Outline: Add a product to cart
    Given User wants to add a product to the cart
    When User provides the <productid> and <quantity>
    Then User gets the updated cart with new <productid>
    Examples:
      | productid | quantity |
      | 1         | 1        |

  Scenario Outline: Get specific item from the cart
    Given User wants to get specific item from cart
    When User provides the <cartItemId> for cartItem
    Then User gets the specific <cartItem>
    Examples:
      | cartItemId | cartItem       |
      | 184        | "Redmi Note 8" |

  Scenario: All Cart Items should be displayed
    Given User wants to get all products from cart
    When User navigates to cart url
    Then User gets the cart items
      | Redmi Note 8 |
#
  Scenario Outline: Get specific cart item which is not present in cart
    Given User wants to get specific item from cart
    When User provides the invalid <cartItemId>
    Then User should get the <statusCode>
    Examples:
      | cartItemId | statusCode |
      | 95         | 404        |

  Scenario Outline: Update Cart Item Quantity
    Given User wants to update cartItem <quantity>
    When User provides the <cartItemId>
    Then User gets updated cartItem <quantity>
    Examples:
      | cartItemId | quantity |
      | 184        | 1        |
#
  Scenario Outline: Update Cart Item Quantity which does not exist
    Given User wants to update cartItem <quantity>
    When User provides the <cartItemId>
    Then User should get the <statusCode>
    Examples:
      | cartItemId | quantity | statusCode |
      | 900        | 1        | 404        |

  Scenario Outline: Update Cart Item Quantity with negative value
    Given User wants to update cartItem <quantity>
    When User provides the <cartItemId>
    Then User should get the <statusCode>
    Examples:
      | cartItemId | quantity | statusCode |
      | 92         | -2       | 400        |
#
  Scenario Outline: Update Cart Item Quantity with more than available quantity
    Given User wants to update cartItem <quantity>
    When User provides the <cartItemId>
    Then User should get the <statusCode>
    Examples:
      | cartItemId | quantity | statusCode |
      | 184        | 200000   | 400        |
#
  Scenario Outline: Add a product to cart with negative quantity
    Given User wants to add a product to the cart
    When User provides the <productid> and <quantity>
    Then User should get the <statusCode>
    Examples:
      | productid | quantity | statusCode |
      | 3         | -1       | 400        |
#
  Scenario Outline: Adding a product which does not exist to cart
    Given User wants to add a product to the cart
    When User provides the <productid> and <quantity>
    Then User should get the <statusCode>
    Examples:
      | productid | quantity | statusCode |
      | 1200      | 2        | 404        |
#
  Scenario Outline: Adding a product more than available quantity
    Given User wants to add a product to the cart
    When User provides the <productid> and <quantity>
    Then User should get the <statusCode>
    Examples:
      | productid | quantity | statusCode |
      | 2         | 100      | 400        |

  Scenario Outline: Delete a Cart Item
    Given User wants to delete a cartItem
    When User provides the <cartItemId> to be deleted
    Then User gets the updated cart with deleted <cartItemId>
    Examples:
      | cartItemId |
      | 184        |

  Scenario: Requesting all cart items when there are no cart items
    Given User wants to get all products from cart
    When User navigates to cart url
    Then User gets the statusCode
      | 404 |
    And User should get the message
      | No Products Available In Cart |

  Scenario Outline: Delete a Cart Item which is not present
    Given User wants to delete a cartItem
    When User provides the <cartItemId> to be deleted
    Then User should get the <statusCode>
    Examples:
      | cartItemId | statusCode |
      | 92         | 404        |