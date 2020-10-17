@Product @Rest
Feature: Product

  Scenario Outline: Get products by valid subcategoryId
    Given User wants to get all products for a subcategory
    When User navigates to product url by <subcategoryId>
    Then User retrieves the list of <products>
    Examples:
      | subcategoryId | products                 |
      | 3             | "Redmi Note 8,iPhone XR" |
      | 5             | "Denim Jeans"            |

  Scenario Outline: Get products by invalid subcategoryId
    Given User wants to get all products for a subcategory
    When User navigates to product url by <subcategoryId>
    Then User gets the <statusCode> for invalid product operation
    And <errorMessage> is received while requesting products
    Examples:
      | subcategoryId | statusCode | errorMessage             |
      | 4             | 404        | "No Products Available " |

  @Admin
  Scenario: Get all products
    Given User wants to get all products
    When User navigates to product url
    Then User retrieves the list of <products>
      | Redmi Note 8 |
      | iPhone XR    |

  @Admin
  Scenario Outline: Adding a product
    Given User specifies the <subcategory>
    When User provides the <name> <quantity> and <price>
    Then User gets the <statuscode> in response

    Examples:
      | subcategory | name      | quantity | price | statuscode |
      | "Biscuits"  | "Parle-G" | 10       | 10.0  | 201        |

  @Admin
  Scenario Outline: Adding an already existing product
    Given User specifies the <subcategory>
    When User provides the <name> <quantity> and <price>
    Then User gets the <statuscode> in response

    Examples:
      | subcategory | name      | quantity | price | statuscode |
      | "Biscuits"  | "Parle-G" | 10       | 10.0  | 409        |

  @Admin
  Scenario Outline: Adding a product with negative quantity
    Given User specifies the <subcategory>
    When User provides the <name> <quantity> and <price>
    Then User gets the <statuscode> in response

    Examples:
      | subcategory | name            | quantity | price | statuscode |
      | "Biscuits"  | "Hide and Seek" | -50      | 30.0  | 400        |

  @Admin
  Scenario Outline: Adding a product with negative price
    Given User specifies the <subcategory>
    When User provides the <name> <quantity> and <price>
    Then User gets the <statuscode> in response

    Examples:
      | subcategory | name         | quantity | price  | statuscode |
      | "Biscuits"  | "Krack Jack" | 10       | -10.00 | 400        |

  @Admin
  Scenario Outline: Adding a product to subcategory which doesn't exist
    Given User specifies the <subcategory> which doesn't exist
    When User provides the <name> <quantity> and <price>
    Then User gets the <statuscode> in response

    Examples:
      | subcategory | name      | quantity | price | statuscode |
      | "Snacks"    | "Parle-G" | 10       | 10.0  | 404        |

  @Admin
  Scenario Outline: Update a product
    Given User specifies the <subcategory> and <product>
    When User provides the <name> <quantity> and <price> to update
    Then User gets the <statuscode> in response

    Examples:
      | subcategory | product   | name     | quantity | price | statuscode |
      | "Biscuits"  | "Parle-G" | "Monaco" | 10       | 10.0  | 200        |

  @Admin
  Scenario Outline: Update a product which doesn't exist
    Given User specifies the <subcategory> and <product> which doesn't exist
    When User provides the <name> <quantity> and <price> to update
    Then User gets the <statuscode> in response

    Examples:
      | subcategory | product         | name     | quantity | price | statuscode |
      | "Biscuits"  | "Hakuna Matata" | "Monaco" | 10       | 10.0  | 404        |

  @Admin
  Scenario Outline: Update a product with negative quantity
    Given User specifies the <subcategory> and <product>
    When User provides the <name> <quantity> and <price> to update
    Then User gets the <statuscode> in response

    Examples:
      | subcategory | product  | name     | quantity | price | statuscode |
      | "Biscuits"  | "Monaco" | "Monaco" | -10      | 10.0  | 400        |

  @Admin
  Scenario Outline: Update a product with negative price
    Given User specifies the <subcategory> and <product>
    When User provides the <name> <quantity> and <price> to update
    Then User gets the <statuscode> in response

    Examples:
      | subcategory | product  | name     | quantity | price | statuscode |
      | "Biscuits"  | "Monaco" | "Monaco" | 10       | -10.0 | 400        |

  @Admin
  Scenario Outline: Update a product's subcategory
    Given User specifies the <subcategory> and <product>
    When User provides the details <product> <quantity> <price> and <newsubcategory>
    Then User gets the <statuscode> in response

    Examples:
      | subcategory | newsubcategory | product  | quantity | price | statuscode |
      | "Biscuits"  | "Cookies"      | "Monaco" | 10       | 10.0  | 200        |

  @Admin
  Scenario Outline: Update a product's subcategory which doesn't exist
    Given User specifies the <subcategory> and <product>
    When User provides the details <product> <quantity> <price> and <newsubcategory> which doesn't exist
    Then User gets the <statuscode> in response

    Examples:
      | subcategory | newsubcategory | product  | quantity | price | statuscode |
      | "Biscuits"  | "Daily"        | "Monaco" | 10       | 10.0  | 404        |

  @Admin
  Scenario: Delete a product
    Given User provides the product name
      | Monaco |
    When User deletes the product
    Then User gets the statuscode as 200

  @Admin
  Scenario: Delete a product that doesn't exist
    Given User provides the product name that doesn't exist
      | Monaco |
    When User deletes the product
    Then User gets the statuscode as 404