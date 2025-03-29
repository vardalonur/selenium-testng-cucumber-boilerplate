Feature: Predefined Steps


#####################################################################
#### BASIC BROWSER & URL METHODS
#####################################################################
  Scenario: User navigates to a URL
    * user navigates to "homeURL"

  Scenario: User refreshes the page
    * user refreshes the page

  Scenario: User navigates back
    * user navigates back

  Scenario: User navigates forward
    * user navigates forward

  Scenario: User verifies the current URL
    * user should see the current URL as "https://example.com/dashboard"

  Scenario: User verifies the page title
    * user should see the page title as "Dashboard"

  Scenario: User opens a new tab
    * user opens a new tab

  Scenario: User switches to tab by index
    * user switches to tab 1

  Scenario: User closes the current tab and switches back
    * user closes the current tab and switches back

#####################################################################
#### CLICK METHODS
#####################################################################
  Scenario: User clicks on an element
    * user clicks on "loginButton"

  #Dynamic
  Scenario: User clicks on a button with specific text
    * user clicks on the button with text "Login"

  #Dynamic
  Scenario: User clicks on a link with specific text
    * user clicks on the link with text "Forgot Password"

  #Dynamic
  Scenario: User clicks on an element with specific text
    * user clicks on the element with text "Settings"


#####################################################################
#### TEXT ACTIONS / getText(), sendKeys(), clear()
#####################################################################
  Scenario: User verifies text in an element
    * user should see text "Welcome" in the "welcomeMessage"

  #Dynamic
  Scenario: User sees a text on the screen
    * user should see the text "Hello!" appear

  Scenario: User types text into an input field
    * user types "example text" into the "usernameField"

  Scenario: User clears text from an input field
    * user clears the text from the "usernameField"


#######################################################################
#### VISIBILITY & READINESS TESTS / isDisplayed(), isEnabled() size()
#######################################################################
  Scenario: User verifies an element is displayed
    * user should see the "logo" is displayed

  Scenario: User verifies an element is enabled
    * user should see the "submitButton" is enabled

  Scenario:
    * user should display the following options on "Header"
      | Item |
      | Item |
      | Item |
      | Item |
      | Item |
      | Item |
      | Item |

  Scenario: User verifies the number of visible elements
    * user should see 15 "listItem" displayed on the page


#####################################################################
#### DROPDOWN MENU STEPS
#####################################################################
  Scenario: User selects an option from a dropdown
    * user selects "Option 1" from the "dropdownMenu" dropdown

  Scenario: User selects an option by value from a dropdown
    * user selects the option with value "value1" from the "dropdownMenu" dropdown


#####################################################################
#### CHECKBOX STEPS
#####################################################################
  Scenario: User checks a checkbox
    * user checks the "rememberMeCheckbox"

  Scenario: User unchecks a checkbox
    * user unchecks the "rememberMeCheckbox"

  #Dynamic
  Scenario: User checks/unchecks a checkbox with text
    Then user "checks" the checkbox labeled "Remember Me"


#####################################################################
#### ACTION CLASS METHODS
#####################################################################

  Scenario: User hovers over an element
    * user hovers over the "profileIcon"

  Scenario: User drags and drops an element
    * user drags "sourceElement" and drops it on "targetElement"

  Scenario: User right-clicks on an element
    * user right-clicks on the "contextMenu"

  Scenario: User double-clicks on an element
    * user double-clicks on the "editField"

  #Scroll with JS
  Scenario: User scrolls to an element
    * user scrolls to the "footer"



#####################################################################
#### FILE UPLOAD
#####################################################################
  Scenario: User uploads a file
    * user uploads the file "path/to/file.txt" to the "fileInputWebElement"

  Scenario: User verifies that a file is uploaded
    * The uploaded file is verified with the name "abc.txt"


#####################################################################
#### WAIT METHODS / Visible, clickable, invisible, present
#####################################################################
  Scenario: User waits for an element to be visible
    * user waits for the "loadingSpinner" to be visible

  Scenario: User waits for an element to be clickable
    * user waits for the "submitButton" to be clickable

  Scenario: User waits for an element to be invisible
    * user waits for the "loadingSpinner" to be invisible

  Scenario: User waits for text to be present in an element
    * user waits for the text "Loaded" to be present in the "statusMessage"


#####################################################################
#### JS EXECUTIONS / Alert Actions, Basic JS Actions
#####################################################################
  Scenario: User accepts an alert
    * user accepts the alert

  Scenario: User dismisses an alert
    * user dismisses the alert

  Scenario: User verifies alert text
    * user should see the alert text as "Are you sure?"

  #Scroll with JS
  Scenario: User scrolls to an element
    * user scrolls to the "footer"

  #Exceptional #Not BDD compliant
  Scenario: User executes JavaScript
    * user executes the JavaScript "console.log('Hello World')"


#####################################################################
#### IFRAME & MODAL WINDOWS
#####################################################################
  Scenario: User switches to a frame
    * user switches to the frame "frameNameOrId"

  Scenario: User switches back to the default content
    * user switches to the default content

  Scenario:
    * browser focuses on the "modal content" modal

  Scenario:
    * browser moves focus out of the modal content


#####################################################################
#### OTHERS
#####################################################################
  Scenario: User takes a screenshot
    * user takes a screenshot

  Scenario:
    * user expands the uploaded image

    * all "element cards" should be displayed in the same size

  Scenario:
    * user closes and reopens the browser