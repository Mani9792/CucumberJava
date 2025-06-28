@smoke
Feature: Sign in to sauce labs page

Scenario Outline: User login with valid "<username>" and "<password>"

Given user enters sign in page
When User enter sheetname "<SheetName>" and row number <RowNumber>
And User clicks on login button
#Then User navigates to home page

Examples:
|SheetName|RowNumber|
|SignIn|0|
