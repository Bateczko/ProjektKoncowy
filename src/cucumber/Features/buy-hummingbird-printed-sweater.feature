Feature: Buy Hummingbird Printed Sweater

  Scenario:
    Given User is logged in to PrestaShop
    When  User goes to Hummingbird Printed Sweater site
    Then  User checks discount

    When  User choices size M
    And   User choices quantity 5
    And   User adds product to cart
    Then  User has 5 products in the cart

    When  User goes to proceed to checkout
    And   User selects the address
    And   User goes to shipping method
    And   User choices the method of delivery
    And   User goes to payment
    And   User choices payment
    And   User clicks the order with an obligation to pay
    Then  User sees ORDER IS CONFIRMED

    When  User takes screenShot and saves price
    And   User goes to Order history and details page
    Then  User checks if the order is on the list with Awaiting check payment status and the same amount
