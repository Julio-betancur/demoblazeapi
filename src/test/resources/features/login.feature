#language: en
#project: demoblaze
Feature: Login API Tests

  @HP
  Scenario Outline: Correct username and password in login
    When I send the user credentials
      | username   | password   |
      | <username> | <password> |
    Then I validate the response content for the login
      | codeResponse | token   | error |
      | 200          | <token> | NA    |

    Examples:
      | username    | password   | token                   |
      | juliotester | 1234567890 | anVsaW90ZXN0ZXIxNzE3Njg |

  @Error
  Scenario Outline: Incorrect username or password in login
    When I send the user credentials
      | username   | password   |
      | <username> | <password> |
    Then I validate the response content for the login
      | codeResponse | error   |
      | 200          | <error> |

    Examples:
      | username     | password   | error                |
      | juliotester1 | 1234567890 | User does not exist. |
      | juliotester  | abcd123    | Wrong password.      |
