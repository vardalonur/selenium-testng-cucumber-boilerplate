package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.BasePage;

import java.util.List;

public class PredefinedSteps {

    private BasePage basePage;

    public PredefinedSteps() {
        basePage = new BasePage();
    }

    @When("user refreshes the page")
    public void userRefreshesThePage() {
        basePage.refreshPage();
    }

    @When("user navigates back")
    public void userNavigatesBack() {
        basePage.navigateBack();
    }

    @When("user navigates forward")
    public void userNavigatesForward() {
        basePage.navigateForward();
    }

    @Given("user navigates to {string}")
    public void userNavigatesTo(String url) {
        basePage.navigateTo(url);
    }

    @When("user opens a new tab")
    public void userOpensANewTab() {
        basePage.openNewTab();
    }

    @When("user switches to tab {int}")
    public void userSwitchesToTab(int tabIndex) {
        basePage.switchToTab(tabIndex);
    }

    @When("user closes the current tab and switches back")
    public void userClosesTheCurrentTabAndSwitchesBack() {
        basePage.closeCurrentTabAndSwitch();
    }

    @When("user clicks on {string}")
    public void userClicksOn(String elementName) {
        basePage.click(elementName);

    }

    @When("user clicks on the element with text {string}")
    public void userClicksOnElementWithText(String text) throws InterruptedException {
        basePage.clickElementContainingText(text);
    }

    @When("user clicks on the button with text {string}")
    public void userClicksOnButtonWithText(String text) {
        basePage.clickElementContainingText(text);
    }

    @When("user clicks on the link with text {string}")
    public void userClicksOnLinkWithText(String text) {
        basePage.clickElementContainingText(text);
    }

    @When("user types {string} into the {string}")
    public void userTypesInto(String text, String elementName) {
        basePage.type(elementName, text);
    }

    @When("user clears the text from the {string}")
    public void userClearsTheTextFrom(String elementName) {
        basePage.clear(elementName);
    }

    @Then("user should see text {string} in the {string}")
    public void userShouldSeeTextIn(String expectedText, String elementName) {
        String actualText = basePage.getText(elementName);
        Assert.assertEquals(actualText, expectedText, "Text does not match!");
    }


    @Then("user should see the {string} is displayed")
    public void userShouldSeeIsDisplayed(String elementName) {
        Assert.assertTrue(basePage.isDisplayed(elementName), "Element is not displayed!");
    }

    @Then("user should see the {string} is enabled")
    public void userShouldSeeIsEnabled(String elementName) {
        Assert.assertTrue(basePage.isEnabled(elementName), "Element is not enabled!");
    }

    @When("user selects {string} from the {string} dropdown")
    public void userSelectsFromDropdown(String optionText, String elementName) {
        basePage.selectByVisibleText(elementName, optionText);
    }

    @When("user selects the option with value {string} from the {string} dropdown")
    public void userSelectsOptionWithValueFromDropdown(String value, String elementName) {
        basePage.selectByValue(elementName, value);
    }

    @When("user checks the {string}")
    public void userChecksThe(String elementName) {
        basePage.setSelection(elementName, true);
    }

    @When("user unchecks the {string}")
    public void userUnchecksThe(String elementName) {
        basePage.setSelection(elementName, false);
    }

    @When("user hovers over the {string}")
    public void userHoversOver(String elementName) {
        basePage.hoverOver(elementName);
    }

    @When("user drags {string} and drops it on {string}")
    public void userDragsAndDropsItOn(String sourceElementName, String targetElementName) {
        basePage.dragAndDrop(sourceElementName, targetElementName);
    }

    @When("user right-clicks on the {string}")
    public void userRightClicksOn(String elementName) {
        basePage.rightClick(elementName);
    }

    @When("user double-clicks on the {string}")
    public void userDoubleClicksOn(String elementName) {
        basePage.doubleClick(elementName);
    }

    @When("user scrolls to the {string}")
    public void userScrollsTo(String elementName) {
        basePage.scrollToElement(elementName);
    }

    @When("user uploads the file {string} to the {string}")
    public void userUploadsFileTo(String filePath, String elementName) {
        basePage.uploadFile(elementName, filePath);
    }

    @Then("The uploaded file is verified with the name {string}")
    public void theUploadedFileIsVerifiedWithTheName(String expectedFileName) {
        String actualFileName = basePage.getUploadedFileName(expectedFileName);
        //Assert.assertTrue(actualFileName.contains(expectedFileName), "Uploaded file name does not contain the expected value!");
        Assert.assertTrue(actualFileName.trim().startsWith(expectedFileName.trim()),
                "Uploaded file name does not start with the expected value!");
    }

    @When("user waits for the {string} to be visible")
    public void userWaitsForToBeVisible(String elementName) {
        basePage.waitForVisibility(elementName);
    }

    @When("user waits for the {string} to be clickable")
    public void userWaitsForToBeClickable(String elementName) {
        basePage.waitForClickability(elementName);
    }

    @When("user waits for the {string} to be invisible")
    public void userWaitsForToBeInvisible(String elementName) {
        basePage.waitForInvisibility(elementName);
    }

    @When("user waits for the text {string} to be present in the {string}")
    public void userWaitsForTextToBePresentIn(String text, String elementName) {
        basePage.waitForText(elementName, text);
    }

    @When("user executes the JavaScript {string}")
    public void userExecutesJavaScript(String script) {
        basePage.executeJavaScript(script);
    }

    @When("user accepts the alert")
    public void userAcceptsAlert() {
        basePage.acceptAlert();
    }

    @When("user dismisses the alert")
    public void userDismissesAlert() {
        basePage.dismissAlert();
    }

    @Then("user should see the alert text as {string}")
    public void userShouldSeeAlertTextAs(String expectedText) {
        String actualText = basePage.getAlertText();
        Assert.assertEquals(actualText, expectedText, "Alert text does not match!");
    }

    @Then("user should see the current URL as {string}")
    public void userShouldSeeCurrentURLAs(String expectedURL) {
        String actualURL = basePage.getCurrentURL();
        Assert.assertEquals(actualURL, expectedURL, "URL does not match!");
    }

    @Then("user should see the page title as {string}")
    public void userShouldSeePageTitleAs(String expectedTitle) {
        String actualTitle = basePage.getPageTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Page title does not match!");
    }

    @When("user switches to the frame {string}")
    public void userSwitchesToFrame(String iFrameName) {
        basePage.switchToFrame(iFrameName);
    }

    @When("user switches to the default content")
    public void userSwitchesToDefaultContent() {
        basePage.switchToDefaultContent();
    }

    @When("user takes a screenshot")
    public void userTakesAScreenshot() {
        byte[] screenshot = basePage.takeScreenshot();
        // You can save the screenshot to a file if necessary
    }

    @Then("user should see {int} {string} displayed on the page")
    public void userShouldSeeDisplayedOnThePage(int expectedCount, String elementName) {
        int actualVisibleCount = basePage.getVisibleElementCount(elementName);

        Assert.assertEquals(actualVisibleCount, expectedCount,
                "Visible element count does not match the expected value!");
    }

    @Then("user closes and reopens the browser")
    public void user_closes_and_reopens_the_browser() {
        basePage.resetDriver();
    }

    @Then("user should display the following options on {string}")
    public void userShouldDisplayTheFollowingOptionsOn(String sectionName, DataTable dataTable) {
        List<String> expectedTexts = dataTable.asList();
        Assert.assertTrue(basePage.verifyDisplayedSections(sectionName, expectedTexts),
                "All sections should be displayed and match expected texts");
    }

    @Then("user should see the text {string} appear")
    public void userShouldSeeTheTextAppear(String expectedText) {
        String actualText = basePage.getTextDynamic(expectedText);
        Assert.assertEquals(actualText, expectedText,
                "Expected message: " + expectedText + " but found: " + actualText);
    }

    @Given("browser focuses on the {string} modal")
    public void browserFocusesOnTheModal(String modalName) {
        basePage.focusOnModal(modalName);
    }

    @Given("user expands the uploaded image")
    public void userExpandsTheUploadedImage() {
        basePage.cropImageWithMouse();
    }

    @Given("browser moves focus out of the modal content")
    public void browserMovesFocusOutOfTheModalContent() {
        basePage.resetZIndexAndFocusOutFromModal();
    }

    @Then("user {string} the checkbox labeled {string}")
    public void userTheCheckboxLabeled(String status, String labelText) {
        basePage.setSelectionWithText(status, labelText);
    }


    @Then("all properties should be displayed in the same size")
    public void allPropertiesShouldBeDisplayedInTheSameSize() {

    }

    @And("all {string} should be displayed in the same size")
    public void allShouldBeDisplayedInTheSameSize(String elementName) {
        Assert.assertTrue(basePage.areElementsSameSize(elementName), "Elements are not displayed in the same size!");
    }

}