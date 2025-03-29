Feature: Login

  Scenario: Successful login with valid data
    Given user navigates to "homePage"
    When user types "Admin" into the "Username Field"
    When user types "admin123" into the "Password Field"
    Then user clicks on the button with text "Login"
    And user should see the text "My Actions" appear

  Scenario: The Username field should not be case-sensitive
    Given user navigates to "homePage"
    When user types "AdMiN" into the "Username Field"
    When user types "admin123" into the "Password Field"
    Then user clicks on the button with text "Login"
    And user should see the text "My Actions" appear

  Scenario Outline: Login fails with invalid input combinations
    Given user navigates to "homePage"
    When user types "<username>" into the "Username Field"
    When user types "<password>" into the "Password Field"
    Then user clicks on the button with text "Login"
    And user should see the text "Invalid credentials" appear

    Examples:
      | username       | password     |
      | Admin          | Admin123     |
      | user           | user         |
      | admin123       | Admin        |
      | Admin          | ' OR '1'='1  |
