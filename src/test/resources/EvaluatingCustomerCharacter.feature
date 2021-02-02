Feature: Decline Customer Character

  Scenario: Declining the Customer, since he does not pass the character check
    Given The customer 'Manfred' 'Stein' gives his firstname and lastname as registration.
    When Customer gets character evaluated
    Then The customers character is getting rejected