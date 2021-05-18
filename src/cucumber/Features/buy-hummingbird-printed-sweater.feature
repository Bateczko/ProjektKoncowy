Feature: Buy Hummingbird Printed Sweater

  Scenario:
    Given User is logged in to PrestaShop
    When  User goes to Hummingbird Printed Sweater site
    And   User checks discount
    And   User choices size M
    And   User choices quantity 5
    And   User adds product to cart
    And   User goes to proceed to checkout
    And   User selects the address
    And   User goes to shipping method
    And   User choices the method of delivery
    And   User goes to payment
    And   User choices payment
    And   User clicks the order with an obligation to pay
    Then  User sees ORDER IS CONFIRMED, and takes screenshot
    And   User goes to Order history and details page
    And   User checks if the order is on the list with Awaiting check payment status and the same amount