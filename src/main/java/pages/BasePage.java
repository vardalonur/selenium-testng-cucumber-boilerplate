package pages;

import config.ConfigReader;
import utils.DriverManager;
import utils.WaitUtils;
import utils.LogUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WaitUtils wait;
    protected Actions actions;


    public BasePage() {
        initializeDriver();
    }

    private void initializeDriver() {
        this.driver = DriverManager.getInstance().getDriver();
        this.wait = new WaitUtils(driver);
        this.actions = new Actions(driver);
    }

    public void resetDriver() {
        DriverManager.getInstance().removeDriver();
        DriverManager.getInstance().setDriver();
        initializeDriver();
        LogUtils.info("Browser closed and reopened");
    }

    // Click on any element
    public void click(String elementName) {
        By locator = ElementMapper.getLocator(elementName);
        try {
            WebElement element = wait.waitForElementClickable(locator);
            // Check if the element is visible in the current viewport
            if (!isElementInViewport(element)) {
                // If not visible scroll with JS
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest', behavior: 'instant'});", element);
                LogUtils.info("Scrolled to element: " + elementName);
            }
            if (element.isDisplayed() && element.isEnabled()) {
                element.click();
                LogUtils.info("Clicked on element: " + elementName);
            } else {
                throw new ElementNotInteractableException("Element found but not interactable: " + elementName);
            }
        } catch (TimeoutException e) {
            throw new NoSuchElementException("No clickable element found: " + elementName);
        }

    }

    // Click on an element (button or link) containing text
    public void clickElementContainingText(String text) {
        By locator = By.xpath("(//button | //a)[normalize-space(text()) = '" + text + "' or contains(normalize-space(.), '" + text + "')]");

        try {
            WebElement element = wait.waitForElementClickable(locator); // Explicit wait ile elementi bekle
            // Check if the element is visible in the current viewport
            if (!isElementInViewport(element)) {
                // If not visible scroll with JS
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest', behavior: 'instant'});", element);
                LogUtils.info("Scrolled to element containing text: " + text);

            }
            if (element.isDisplayed() && element.isEnabled()) {
                element.click();
                LogUtils.info("Clicked on element containing text: " + text);
            } else {
                throw new ElementNotInteractableException("Element found but not interactable: " + text);
            }
        } catch (TimeoutException e) {
            throw new NoSuchElementException("No clickable element found with text: " + text);
        }
    }

    // Type text into any input field
    public void type(String elementName, String text) {
        By locator = ElementMapper.getLocator(elementName);
        WebElement element = wait.waitForElementVisible(locator);
        // Check if the element is visible in the current viewport
        if (!isElementInViewport(element)) {
            // If not visible scroll with JS
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            LogUtils.info("Scrolled to element containing text: " + text);
        }
        element.clear();
        element.sendKeys(text);
        LogUtils.info("Typed into element: " + elementName + " with text: " + text);
    }

    // Clear text from any input field
    public void clear(String elementName) {
        By locator = ElementMapper.getLocator(elementName);
        WebElement element = wait.waitForElementVisible(locator);
        element.clear();
        LogUtils.info("Cleared text from element: " + elementName);
    }

    // Get the text of any element
    public String getText(String elementName) {
        try {
            By locator = ElementMapper.getLocator(elementName);
            WebElement element = wait.waitForElementVisible(locator);
            String text = element.getText();
            LogUtils.info("Got text from element: " + elementName + " - Text: " + text);
            return text;
        } catch (NoSuchElementException | TimeoutException e) {
            LogUtils.info("Element not found for display check: " + elementName);
            return "Element not found for display check:" + elementName;
        }
    }

    // Extracts visible text from an element dynamically
    public String getTextDynamic(String expectedText) {
        By locator = By.xpath("//*[contains(normalize-space(.), '" + expectedText + "') and not(descendant::*[contains(normalize-space(.), '" + expectedText + "')])]");

        WebElement messageElement = wait.waitForElementVisible(locator);

        String actualText = messageElement.getText().trim();
        LogUtils.info("Found visible text: " + actualText);

        return actualText;
    }

    // Check if an element is displayed
    public boolean isDisplayed(String elementName) {
        try {
            By locator = ElementMapper.getLocator(elementName);
            WebElement element = wait.waitForElementVisible(locator);
            boolean displayed = element.isDisplayed();
            LogUtils.info("Element displayed: " + elementName + " - " + displayed);
            return displayed;
        } catch (NoSuchElementException | TimeoutException e) {
            LogUtils.info("Element not found for display check: " + elementName);
            return false;
        }
    }

    // Check if an element is enabled
    public boolean isEnabled(String elementName) {
        By locator = ElementMapper.getLocator(elementName);
        boolean enabled = driver.findElement(locator).isEnabled();
        LogUtils.info("Element enabled: " + elementName + " - " + enabled);
        return enabled;
    }

    // Select an option from a dropdown by visible text
    public void selectByVisibleText(String elementName, String optionText) {
        By locator = ElementMapper.getLocator(elementName);
        WebElement dropdownElement = wait.waitForElementVisible(locator);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(optionText);
        LogUtils.info("Selected option '" + optionText + "' from dropdown: " + elementName);
    }

    // Select an option from a dropdown by value
    public void selectByValue(String elementName, String value) {
        By locator = ElementMapper.getLocator(elementName);
        WebElement dropdownElement = wait.waitForElementVisible(locator);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByValue(value);
        LogUtils.info("Selected option with value '" + value + "' from dropdown: " + elementName);
    }

    // Select or deselect a checkbox or radio button
    public void setSelection(String elementName, boolean selected) {
        By locator = ElementMapper.getLocator(elementName);
        WebElement element = wait.waitForElementClickable(locator);
        if (element.isSelected() != selected) {
            element.click();
            LogUtils.info((selected ? "Selected" : "Deselected") + " element: " + elementName);
        } else {
            LogUtils.info("Element already in desired state: " + elementName + " - Selected: " + selected);
        }
    }

    // Select or deselect a checkbox or radio button with text
    public void setSelectionWithText(String state, String labelText) {
        By locator = By.xpath(
                "//label[contains(normalize-space(.), '" + labelText + "')]//input[@type='checkbox'] | " +
                        "//label[contains(normalize-space(.), '" + labelText + "')]/..//input[@type='checkbox'] | " +
                        "//input[@type='checkbox'][@id=//label[contains(normalize-space(.), '" + labelText + "')]/@for]"
        );

        boolean shouldBeChecks = state.equalsIgnoreCase("checks");

        try {
            WebElement checkbox = wait.waitForElementClickable(locator);
            boolean isChecked = checkbox.isSelected();

            if (shouldBeChecks != isChecked) {
                try {
                    checkbox.click();
                } catch (ElementClickInterceptedException e) {
                    // If normal clicking is blocked, use JSExecutor to click
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("arguments[0].click();", checkbox);
                }
                LogUtils.info("Checkbox toggled: " + labelText + " -> " + state);
            } else {
                LogUtils.info("Checkbox state is already correct: " + labelText);
            }
        } catch (NoSuchElementException | TimeoutException e) {
            LogUtils.error("Checkbox not found: " + labelText);
        }
    }

    // Hover over an element
    public void hoverOver(String elementName) {
        By locator = ElementMapper.getLocator(elementName);
        WebElement element = wait.waitForElementVisible(locator);
        actions.moveToElement(element).perform();
        LogUtils.info("Hovered over element: " + elementName);
    }

    // Drag an element to a target element
    public void dragAndDrop(String sourceElementName, String targetElementName) {
        By sourceLocator = ElementMapper.getLocator(sourceElementName);
        By targetLocator = ElementMapper.getLocator(targetElementName);
        WebElement sourceElement = wait.waitForElementVisible(sourceLocator);
        WebElement targetElement = wait.waitForElementVisible(targetLocator);
        actions.dragAndDrop(sourceElement, targetElement).perform();
        LogUtils.info("Dragged element: " + sourceElementName + " to: " + targetElementName);
    }

    // Right-click on an element
    public void rightClick(String elementName) {
        By locator = ElementMapper.getLocator(elementName);
        WebElement element = wait.waitForElementClickable(locator);
        actions.contextClick(element).perform();
        LogUtils.info("Right-clicked on element: " + elementName);
    }

    // Double-click on an element
    public void doubleClick(String elementName) {
        By locator = ElementMapper.getLocator(elementName);
        WebElement element = wait.waitForElementClickable(locator);
        actions.doubleClick(element).perform();
        LogUtils.info("Double-clicked on element: " + elementName);
    }

    // Scroll to an element
    public void scrollToElement(String elementName) {
        By locator = ElementMapper.getLocator(elementName);
        WebElement element = wait.waitForElementPresent(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        LogUtils.info("Scrolled to element: " + elementName);
    }

    // Upload a file
    public void uploadFile(String elementName, String filePath) {
        By locator = ElementMapper.getLocator(elementName);
        WebElement fileInput = wait.waitForElementPresent(locator);

        // Convert relative path to absolute path
        File file = new File(filePath);
        String absolutePath = file.getAbsolutePath();

        // Check if the file exists
        if (!file.exists()) {
            throw new RuntimeException("File not found at path: " + absolutePath);
        }

        fileInput.sendKeys(absolutePath);
        LogUtils.info("Uploaded file to element: " + elementName + " - File path: " + absolutePath);
    }

    //Gets the name of the upladed file from the URL
    public String getUploadedFileName(String elementName) {
        By locator = ElementMapper.getLocator(elementName);
        WebElement uploadedFileElement = wait.waitForElementVisible(locator);

        String fileName = uploadedFileElement.getAttribute("src");
        System.out.println(fileName);
        LogUtils.info("Retrieved uploaded file name: " + fileName);

        // Gets the name from the URL
        String fileNameFromUrl = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.lastIndexOf("."));
        System.out.println(fileNameFromUrl);

        return fileNameFromUrl;
    }

    //Image cropping
    public void cropImageWithMouse() {
        By cropperPointNE = By.cssSelector("*.point-ne"); // Dragging point
        By cropperPointSW = By.cssSelector("*.point-sw"); // Dragging point
        WebElement cropperNE = wait.waitForElementVisible(cropperPointNE);
        WebElement cropperSW = wait.waitForElementVisible(cropperPointSW);

        Actions actions = new Actions(driver);
        actions.clickAndHold(cropperSW)
                .moveByOffset(-50, 50) // Drag 50px to left, 50px to up
                .release()
                .perform();
        actions.clickAndHold(cropperNE)
                .moveByOffset(50, -50) // Drag 50px to right, 50px to down
                .release()
                .perform();
        LogUtils.info("Cropped image using mouse drag.");
    }



    // Wait for element to be visible
    public void waitForVisibility(String elementName) {
        By locator = ElementMapper.getLocator(elementName);
        wait.waitForElementVisible(locator);
        LogUtils.info("Element is visible: " + elementName);
    }

    // Wait for element to be clickable
    public void waitForClickability(String elementName) {
        By locator = ElementMapper.getLocator(elementName);
        wait.waitForElementClickable(locator);
        LogUtils.info("Element is clickable: " + elementName);
    }

    // Wait for element to be invisible
    public void waitForInvisibility(String elementName) {
        By locator = ElementMapper.getLocator(elementName);
        wait.waitForElementInvisible(locator);
        LogUtils.info("Element is invisible: " + elementName);
    }

    // Wait for text to be present in a located element
    public void waitForText(String elementName, String text) {
        By locator = ElementMapper.getLocator(elementName);
        wait.waitForTextToBePresentInElement(locator, text);
        LogUtils.info("Text '" + text + "' is present in element: " + elementName);
    }

    // Execute JavaScript command
    public Object executeJavaScript(String script, Object... args) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        Object result = jsExecutor.executeScript(script, args);
        LogUtils.info("Executed JavaScript command");
        return result;
    }

    // Handle alerts
    public void acceptAlert() {
        wait.waitForAlertPresent();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        LogUtils.info("Accepted alert");
    }

    public void dismissAlert() {
        wait.waitForAlertPresent();
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
        LogUtils.info("Dismissed alert");
    }

    public String getAlertText() {
        wait.waitForAlertPresent();
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        LogUtils.info("Got alert text: " + text);
        return text;
    }

    // Refresh the page
    public void refreshPage() {
        driver.navigate().refresh();
        LogUtils.info("Page refreshed");
    }

    // Navigate back in browser history
    public void navigateBack() {
        driver.navigate().back();
        LogUtils.info("Navigated back to previous page");
    }

    // Navigate forward in browser history
    public void navigateForward() {
        driver.navigate().forward();
        LogUtils.info("Navigated forward");
    }

    // Navigate to a URL
    public void navigateTo(String url) {
        driver.get(ConfigReader.getProperty(url));
        LogUtils.info("Navigated to URL: " + url);
    }

    // Get the current URL
    public String getCurrentURL() {
        String url = driver.getCurrentUrl();
        LogUtils.info("Current URL: " + url);
        return url;
    }

    // Get the page title
    public String getPageTitle() {
        String title = driver.getTitle();
        LogUtils.info("Page title: " + title);
        return title;
    }

    // Open a new tab and switch to it
    public void openNewTab() {
        ((JavascriptExecutor) driver).executeScript("window.open()");
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));
        LogUtils.info("Opened a new tab and switched to it");
    }

    // Switch to a specific tab by index
    public void switchToTab(int tabIndex) {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        if (tabIndex < tabs.size()) {
            driver.switchTo().window(tabs.get(tabIndex));
            LogUtils.info("Switched to tab: " + tabIndex);
        } else {
            LogUtils.error("Invalid tab index: " + tabIndex);
        }
    }

    // Close the current tab and switch to the previous one
    public void closeCurrentTabAndSwitch() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        int currentTabIndex = tabs.indexOf(driver.getWindowHandle());
        driver.close();
        if (currentTabIndex > 0) {
            driver.switchTo().window(tabs.get(currentTabIndex - 1));
            LogUtils.info("Closed current tab and switched to the previous one");
        } else {
            LogUtils.warn("No previous tab to switch to");
        }
    }

    // Switch to a frame

    public void switchToFrame(String iFrameName) {
        By locator = ElementMapper.getLocator(iFrameName);
        WebElement frameElement = wait.waitForElementVisible(locator);
        driver.switchTo().frame(frameElement);
        LogUtils.info("Switched to frame: " + locator);
    }

    // Switch back to the default content
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
        LogUtils.info("Switched back to default content");
    }

    // Take a screenshot
    public byte[] takeScreenshot() {
        TakesScreenshot ts = (TakesScreenshot) driver;
        byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
        LogUtils.info("Took a screenshot");
        return screenshot;
    }

    public boolean verifyDisplayedSections(String sectionName, List<String> expectedTexts) {
        try {
            // Get all elements in the specified section
            By sectionLocator = ElementMapper.getLocator(sectionName);
            List<WebElement> elements = driver.findElements(sectionLocator);

            // Check each element's visibility and text
            for (int i = 0; i < elements.size(); i++) {
                WebElement element = elements.get(i);
                String expectedText = expectedTexts.get(i);

                if (!element.isDisplayed()) {
                    return false;
                }

                String actualText = element.getText().trim();
                if (!actualText.contains(expectedText)) {
                    LogUtils.info("Text mismatch. Expected: '" + expectedText + "', Actual: '" + actualText + "'");
                    return false;
                }
            }

            LogUtils.info("All sections are displayed and texts match in " + sectionName);
            return true;

        } catch (Exception e) {
            LogUtils.info("Error verifying sections: " + e.getMessage());
            return false;
        }
    }

    // Elementin görünür alanda olup olmadığını kontrol eden yardımcı metot
    private boolean isElementInViewport(WebElement element) {
        return (Boolean)((JavascriptExecutor) driver).executeScript(
                "var elem = arguments[0],                 " +
                        "  box = elem.getBoundingClientRect(),    " +
                        "  height = window.innerHeight || document.documentElement.clientHeight, " +
                        "  width = window.innerWidth || document.documentElement.clientWidth;    " +
                        "return (                                   " +
                        "  box.top >= 0 &&                          " +
                        "  box.left >= 0 &&                         " +
                        "  box.bottom <= height &&                  " +
                        "  box.right <= width                       " +
                        ");", element);
    }

    // Focus driver on modal window
    public void focusOnModal(String elementName) {
        By modalLocator = ElementMapper.getLocator(elementName);
        // 1. Modal'ın Açılmasını Bekle
        WebElement modal = wait.waitForElementVisible(modalLocator);

        // 2. Modal'ın Önde Olduğunu ve Görünür Olduğunu Kontrol Et
        if (!modal.isDisplayed()) {
            throw new RuntimeException("Modal is not displayed!");
        }

        // 3. Modal'ın Z-index Değerini Yükselt (Görünürlüğünü Garantile)
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.zIndex='9999';", modal);

        // 4. Modal’a Focus Ayarla (Driver’ın Odağını Buraya Getir)
        ((JavascriptExecutor) driver).executeScript("arguments[0].focus();", modal);

        LogUtils.info("Driver focus set on modal: " + modalLocator);
    }

    public void resetZIndexAndFocusOutFromModal() {
        // z-index reset with JS
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zIndex = 'auto';");
        wait.waitForElementInvisible(By.cssSelector(".modal.show"));
        LogUtils.info("Reset z-index and focused out from modal.");
    }


    public int getVisibleElementCount(String elementName) {
        By locator = ElementMapper.getLocator(elementName);
        List<WebElement> elements = driver.findElements(locator);

        // Görünür olan elementleri filtrele
        long visibleCount = elements.stream().filter(WebElement::isDisplayed).count();

        LogUtils.info("Found " + elements.size() + " elements with name: " + elementName +
                " | Visible: " + visibleCount);

        return (int) visibleCount;
    }

    public boolean areElementsSameSize(String elementName) {
        By locator = ElementMapper.getLocator(elementName);
        List<WebElement> elements = driver.findElements(locator);
        if (elements.isEmpty()) return false;

        Dimension firstSize = elements.get(0).getSize();
        return elements.stream().allMatch(item -> item.getSize().equals(firstSize));
    }

}