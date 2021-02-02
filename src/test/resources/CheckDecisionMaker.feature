Feature: Check if the correct decision maker is selected.

  Scenario: Customer Data is collected and the decision maker has to be selected.
    Given The customer 'Max' 'Heermann' wants a loan of 5000.0 €.
    When The customers loan purpose of buying a 'car' with the 5000.0 € is collected.
    Then The decision maker has to be 'Manfred Mueller'.