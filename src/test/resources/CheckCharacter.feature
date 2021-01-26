Feature: Check if the customers character is ok

  Scenario: Investigate the claim and type in the customer data to check if the customer exists
    Given I have the firstname and the lastname
    And I check the yes checkbox
    When I confirm my entries
    Then It should be checked if the customers character is ok