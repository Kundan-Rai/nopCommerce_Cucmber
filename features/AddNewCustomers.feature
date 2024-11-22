Feature: Customers

	Background: Below Steps are common to each scenario
							i.e., Steps common in every scenario are written under Background
		
		Given User launches chrome browser
		When User opens URL "https://admin-demo.nopcommerce.com/login"
		And User enters email as "admin@yourstore.com" and password as "admin"
		And User clicks on login button
		Then User can see the dashboard
		When User clicks on Customers menu
		And User clicks on Customers menu item

	# tags
	@regression 
	Scenario: Add a new customer
		
		And User clicks on Add new button
		Then User can see the Add a new customer page
		When User enters Customer info
		And User clicks on Save button
		Then User can see the confirmation message "The new customer has been added successfully."
		And User closes the browser
	
	# tags	
	@sanity
	Scenario: Search Customer by Email id
		
		Then User can see the Customers page
		When User enters customer email
		And User clicks on Search button
		Then User should found email in the search table
		And User closes the browser
	
	# tags
	@regression @sanity
	Scenario: Search Customer by Name
		
		Then User can see the Customers page
		When User enters customer first name
		And User enters customer last name
		And User clicks on Search button
		Then User should found name in the search table
		And User closes the browser