Feature: 
  		A simple BDD framework that uses PageObject Modelling

  Scenario: Check that a registered user has the ability to change their last name in
    their profile section

    Given the new user is registered to the www.brighttalk.com website
      | username          | password          |
      | user111@gmail.com | user111@gmail.com |
    When user selects the Edit Profile on the right top of the page
    And changes the "Last name" on the Profile page
    And re-logins to the site
      | username          | password          |
      | user111@gmail.com | user111@gmail.com |
    Then the new "Last name" is shown on the left side of the Knowledge feed page.

  Scenario: Delete the registered user from BrightTalk and check that the user is unable to log
    back into the platform

    Given that the registered user goes to the BrightTalk HomePage
    And that the user is registered on the BrightTalk website
      | username         | password         |
      | oneone@gmail.com | oneone@gmail.com |
    And the user deletes said self from the user profile page
    And login to Bright Talk platform
      | username         | password         |
      | oneone@gmail.com | oneone@gmail.com |
    Then the user can not login to the BrightTalk platform
