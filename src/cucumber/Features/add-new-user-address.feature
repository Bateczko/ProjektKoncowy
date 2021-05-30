Feature: Add new user address

  Scenario Outline: Change user address
    Given User is logged in to shop
    And   User went to Address page
    When  User inputs alias <alias>, address <address>, postcode <postcode>, city <city>, phone <phone>
    And   User selects country
    And   User saves new address
    Then  Alias <alias>, address <address>, postcode <postcode>, city <city>, phone <phone> and country is correct.

    When  User removes the address
    Then  User has one address

    Examples:
      |alias    |address                         |postcode     |city     |phone        |
      |UK       |2 Marsham Street, Wellesley Road|SW1P 4DF     |London   |666-666-666  |
