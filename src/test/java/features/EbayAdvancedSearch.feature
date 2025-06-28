@regression
Feature: Ebay Advanced search page

Scenario: Ebay logo on advanced search page

Given I am on Ebay Advanced search page
When I click on Ebay logo
Then I navigate to Ebay home page

Scenario: Ebay logo on advanced search page

Given I am on Ebay Advanced search page
When I advance search an item
|keyword|exclude|min|max|
|iphone 11|refurbished|300|900|