#language: en
#project: demoblaze
Feature: Signup API Tests


  @HP
  Scenario Outline: Create a new user in signup
    When I send the user details
      | username   | password   |
      | <username> | <password> |
    Then I validate the response content for the signup
      | codeResponse | error |
      | 200          | NA    |

    Examples:
      | username | password    |
      | newuser  | password123 |

  @Error
  Scenario Outline: Attempt to create an existing user
    When I send the user details
      | username   | password   |
      | <username> | <password> |
    Then I validate the response content for the signup
      | codeResponse | error                    |
      | 200          | This user already exist. |

    Examples:
      | username    | password    |
      | juliotester | password123 |
