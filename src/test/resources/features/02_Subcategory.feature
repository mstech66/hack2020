@Subcategory @Rest
Feature: Subcategory

  Scenario Outline: Get subcategory by valid categoryId
    Given User wants to get all subcategories for a category
    When User navigates to subcategory url by <categoryId>
    Then User gets the list of <subcategories>
    Examples:
      | categoryId | subcategories     |
      | 1          | "Mobiles,Laptops" |
      | 2          | "Men,Women"       |

  Scenario Outline: Get subcategory by invalid categoryId
    Given User wants to get all subcategories for a category
    When User navigates to subcategory url by <categoryId>
    Then User gets the <statusCode> for invalid operation
    And <errorMessage> is received
    Examples:
      | categoryId | statusCode | errorMessage                      |
      | 3          | 404        | "No Category Available With Id:3" |

  @Admin
  Scenario: Adding a subcategory to category
    Given User specifies category for subcategory
      | Groceries |
    When User provides the subcategory
      | Biscuits |
    Then User gets the status code
      | 201 |

  @Admin
  Scenario: Adding an already existing subcategory to category
    Given User specifies category for subcategory
      | Groceries |
    When User provides the subcategory
      | Biscuits |
    Then User gets the status code
      | 409 |

  @Admin
  Scenario: Adding a subcategory to category which doesn't exist
    Given User specifies category for subcategory which doesn't exist
      | Sports |
    When User provides the subcategory
      | Cricket |
    Then User gets the status code
      | 404 |