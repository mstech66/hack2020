Feature: Shopping

  Scenario: User should be able to place order of single product
    When User selects the category and chooses the subcategory and adds the product with quantity to the cart
      | category    | subcategory | product      | quantity |
      | Electronics | Mobiles     | Redmi Note 8 | 1        |
    And User checkout the cart
    Then Cart should be empty
    And Order should be placed

#  Scenario: User should be able to place order of multiple products
#    When User selects the category and chooses the subcategory and adds the product with quantity to the cart
#      | category    | subcategory | product     | quantity |
#      | Electronics | Mobiles     | iPhone XR   | 1        |
#      | Fashion     | Men         | Denim Jeans | 1        |
#    And User checkout the cart
#    Then Cart should be empty
#    And Order should be placed