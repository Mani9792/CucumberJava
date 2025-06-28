@regression
Feature: Ebay Home Page scenarios

Scenario: Advanced search link

	Given I am on Ebay home page
	When I click on advanced link
	Then I navigate to Advanced search page	

Scenario Outline: Search Items Count

	Given I am on ebay home page
	When I search for "<products>"
	Then I validate altleast 10000000 search items present
	
	Examples:
	|products|
	|Iphone 11|
	|Toy Cars|

Scenario: Search Items Count

	Given I am on ebay home page
	When I search for products and validate altleast 100 search items present