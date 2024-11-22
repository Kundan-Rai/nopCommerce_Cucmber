Feature: Login

	# tags
	@sanity
  Scenario: Successful Login With Valid Credentials
    Given User launches chrome browser
    When User opens URL "https://admin-demo.nopcommerce.com/login"
    And User enters email as "admin@yourstore.com" and password as "admin"
    And User clicks on login button
    Then Page title should be "Dashboard / nopCommerce administration"
    When User clicks on the logout link
    Then Page title should be "Your store. Login"
    And User closes the browser

	# tags
	@regression
  Scenario Outline: Login Data Driven
    Given User launches chrome browser
    When User opens URL "https://admin-demo.nopcommerce.com/login"
    And User enters email as "<email>" and password as "<password>"
    And User clicks on login button
    Then Page title should be "Dashboard / nopCommerce administration"
    When User clicks on the logout link
    Then Page title should be "Your store. Login"
    And User closes the browser

    Examples: 
      | email                | password |
      | admin@yourstore.com  | admin    |
      | admin1@yourstore.com | admin123 |
