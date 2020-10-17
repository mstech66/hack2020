@Category @Rest
Feature: Category

  Scenario: All Categories should be displayed
    Given User wants to get all categories
    When User navigates to category url
    Then User gets the <categories>
      | Electronics |
      | Fashion     |
      | Groceries   |

#  Scenario: Requesting all categories when there are no categories available
#    Given User wants to get all categories
#    When User navigates to category url
#    Then User gets the status code in response
#      | 404 |

  @Admin
  Scenario: Adding a new Category
    Given User wants to add a category
    When User provides the category name
      | Stationary |
    Then User gets the status code in response
      | 201 |

  @Admin
  Scenario: Adding a category which already exists
    Given User wants to add a category
    When User provides the category name
      | Stationary |
    Then User gets the status code in response
      | 409 |

  @Admin
  Scenario: Deleting a category
    Given User wants to delete a category
    When User provides the category name to be deleted
      | Stationary |
    Then User gets the status code in response
      | 200 |

  @Admin
  Scenario: Deleting a category that does not exists
    Given User wants to delete a category
    When User provides the deleted category
    Then User gets the status code in response
      | 404 |

  @Admin
  Scenario: Updating a category
    Given User wants to update a category
      | Stationary |
    When User provides the category name to be updated
      | Stationary Items |
    Then User gets the status code in response
      | 200 |

  @Admin
  Scenario: Updating a category which does not exist
    Given User wants to update a category that doesn't exist
    When User provides the category name to be updated
      | Toys |
    Then User gets the status code in response
      | 404 |