Feature: Add new user address

  Scenario Outline: Change user address
    Given User is logged in to shop
    When  User goes to Address page
    And   User inputs alias <alias>, address <address>, postcode <postcode>, city <city>, phone <phone>
    And   User selects country
    And   User saves new address
    Then  User see Address successfully added! alias <alias>, address <address>, postcode <postcode>, city <city>, phone <phone> and country is correct.
    And   User deletes address, and removes the address and logs out of the account

    Examples:
      |alias    |address                         |postcode     |city     |phone        |
      |UK       |2 Marsham Street, Wellesley Road|SW1P 4DF     |London   |666-666-666  |
