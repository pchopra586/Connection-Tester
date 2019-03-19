Feature: Launch Connection Tester Website
  @testConnection
  Scenario: Launch Connection Tester Website
    Given I want to launch the Connection Tester website
    When I want to verify the headline of the page
    Then I want to select the production environment
    And I want to click on run checker button
    And I want to read the summary after the run
    And I want to send email if there is any server failure


