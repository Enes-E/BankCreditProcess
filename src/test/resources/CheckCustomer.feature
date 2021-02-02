Feature: Check if the customers name is registered

  Scenario: Check if the customers name is registered
    Given The customer 'Max' 'Heermann' gives his firstname and lastname.
    And The firstname and the lastname of 'Max' 'Heermann' is registered
    When Customer Data Collection is done
    Then The customers character is evaluated