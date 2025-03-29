package pages;

import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.Map;

public class ElementMapper {
    private static final Map<String, By> elementMap = new HashMap<>();

    static {
        // Header Elements

        // Login Page Elements
        elementMap.put("username field", By.xpath("//input[@name='username']"));
        elementMap.put("password field", By.xpath("//input[@name='password']"));


        // Dashboard Elements
        elementMap.put("dashboard side bar menu", By.xpath("//div[@class='d-navigation']//li"));
        elementMap.put("welcome message", By.id("welcomeMsg"));
        elementMap.put("profile picture", By.id("profilePic"));
        elementMap.put("settings icon", By.id("settingsIcon"));

        //Dashboard/Settings Elements
        elementMap.put("'verified' message at the bottom of the email form", By.xpath("//*[@id='setting-form']//*[text()='Verified']"));
        elementMap.put("profile photo in the settings menu", By.xpath("//*[@id='account-avatar']/div/div"));
        elementMap.put("change avatar title", By.xpath("//*[@id='avatar-modal']//H4[contains(normalize-space(.),'Change')]"));
        elementMap.put("new image label", By.xpath("//div/label[contains(normalize-space(.),'New image')]"));
        elementMap.put("avatar upload module", By.xpath("//*[@id='avatarInput']"));
        elementMap.put("save button of the avatar change window", By.xpath("//*[@id='avatar-modal']//button[text()='Save']"));
        elementMap.put("user-avatar", By.xpath("//div[@id='account-avatar']//img[contains(@src, 'user-avatar')]"));
        elementMap.put("first name field on settings", By.xpath("//*[@id='first_name']"));
        elementMap.put("last name field on settings", By.xpath("//*[@id='last_name']"));
        elementMap.put("phone field on settings", By.xpath("//*[@id='phone']"));
        elementMap.put("short description field on settings", By.xpath("//*[@id='description']"));
        elementMap.put("year of birth", By.xpath("//select[@id='year']"));
        elementMap.put("month of birth", By.xpath("//select[@id='month']"));
        elementMap.put("day of birth", By.xpath("//select[@id='day']"));
        elementMap.put("save button of the form", By.xpath("//form[@id='setting-form']/button"));
        elementMap.put("update profile successfully! message", By.xpath("(//div[contains(normalize-space(.), 'Update profile successfully!')])[2]"));

        //Dashboard/Security Elements
        elementMap.put("new password field on security section", By.id("password"));
        elementMap.put("confirmation password field on security section", By.id("password_confirmation"));

        //Dashboard/Buy credits Elements
        elementMap.put("credit amount", By.xpath("//p[contains(text(),'Your Credits:')]"));
        elementMap.put("single post purchase option", By.xpath("//div//*[contains(text(),'Single Post')]"));
        elementMap.put("5 posts purchase option", By.xpath("//div//*[contains(text(),'5 Posts')]"));
        elementMap.put("single post purchase button", By.xpath("(//button[text()='Purchase'])[1]"));
        elementMap.put("5 posts purchase button", By.xpath("(//button[text()='Purchase'])[2]"));
        elementMap.put("card number field", By.id("stripe-number"));
        elementMap.put("card expiration date field", By.id("stripe-exp"));
        elementMap.put("card full name field", By.id("stripe-name"));
        elementMap.put("card cvc field", By.id("stripe-cvc"));

        //Dashboard/Properties Elements
        elementMap.put("first row of the properties list", By.xpath("//tbody/tr[1]"));
        elementMap.put("edit button of the first item on the properties list", By.xpath("//tbody/tr[1]//a[@data-bs-original-title='Edit']"));
        elementMap.put("delete button of the first item on the properties list", By.xpath("//tbody/tr[1]//a[@data-bs-original-title='Delete']"));
        elementMap.put("cancel button on the popup window", By.xpath("(//button[normalize-space(text()) = 'Cancel'])[1]"));
        elementMap.put("delete button on the popup window", By.xpath("(//button[normalize-space(text()) = 'Delete'])[1]"));

        //Dashboard/Write a Property Elements
        elementMap.put("title field of the properties page", By.id("name"));
        elementMap.put("content field of the properties page", By.xpath("//div[@role='textbox']"));
        elementMap.put("property Location field of the properties page", By.id("location"));
        elementMap.put("property type", By.id("type_id"));

        //Listing Page Elements
        elementMap.put("properties text", By.xpath("//h1[text()='Properties']"));
        elementMap.put("property cards", By.cssSelector(".property-listing"));
            //The first property card on the listing page
            elementMap.put("the first property card on the page", By.xpath("(//div[@class='property-listing property-2 '])[1]"));
            elementMap.put("Listing Name", By.xpath("(//div[@class='property-listing property-2 '])[1]//h4[@class='listing-name']"));
            elementMap.put("Listing Price", By.xpath("(//div[@class='property-listing property-2 '])[1]//h6[@class='listing-card-info-price']"));
            elementMap.put("Bedroom count", By.xpath("(//div[@class='property-listing property-2 '])[1]//div[@class='listing-card-info-icon'][contains(normalize-space(.), 'Beds')]"));
            elementMap.put("Bathroom count", By.xpath("(//div[@class='property-listing property-2 '])[1]//div[@class='listing-card-info-icon'][contains(normalize-space(.), 'Bath')]"));
            elementMap.put("Square meter", By.xpath("(//div[@class='property-listing property-2 '])[1]//div[@class='listing-card-info-icon'][contains(normalize-space(.), 'm²')]"));
            elementMap.put("Wishlist button", By.xpath("(//div[@class='property-listing property-2 '])[1]//div[@class='icon-actions-wrapper']"));
            elementMap.put("Location", By.xpath("(//div[@class='property-listing property-2 '])[1]//div[@class='foot-location d-flex']"));
            elementMap.put("View button", By.xpath("(//div[@class='property-listing property-2 '])[1]//div[@class='footer-flex']"));

        // Contact Page Elements
        elementMap.put("name field", By.id("name"));
        elementMap.put("contact email field", By.id("email"));
        elementMap.put("message field", By.id("message"));
        elementMap.put("success message", By.id("successMessage"));
        elementMap.put("contact text",By.xpath("(//*[text()='Contact'])[2]"));

        // Home Page Elements
        elementMap.put("search button", By.id("searchBtn"));
        elementMap.put("search results", By.id("resultsPage"));
        elementMap.put("find accessible homes to rent", By.xpath("//h1[text()='Find accessible homes to rent']"));

        // Common Elements
        elementMap.put("search field", By.name("search"));
        elementMap.put("submit button", By.cssSelector("button[type='submit']"));
        elementMap.put("popup message", By.cssSelector(".alert-dismissible"));
        elementMap.put("modal content", By.xpath("//div[@class='modal-content']"));

        // Projects Page Elements
        elementMap.put("all projects text", By.xpath("//*[@class='ipt-title']"));

        // Agents Page Elements
        elementMap.put("all agents text",By.xpath("//div[@class='col-lg-12 col-md-12']"));

        //Blog Page Elements
        elementMap.put("blog text",By.xpath("//*[@class='col-lg-12 col-md-12']"));

        //Sign Up Page Elements
        elementMap.put("activity logs text",By.xpath("//*[@class='col-lg-12 col-md-12']"));

        //Add Property Page Elements
        elementMap.put("write a property button",By.xpath("//*[@class='active']"));

        //Homepage Body Elements
        elementMap.put("how it works section",By.xpath("(//*[@class='sec-heading center'])[1]"));
        elementMap.put("explore good places",By.xpath("(//*[@class='sec-heading center'])[2]"));
        elementMap.put("find by locations section",By.xpath("(//*[@class='sec-heading center'])[3]"));
        elementMap.put("good reviews by customers",By.xpath("(//*[@class='sec-heading center'])[4]"));
        elementMap.put("see our packages section",By.xpath("(//*[@class='sec-heading center'])[5]"));
        elementMap.put("recently viewed properties header",By.xpath("((//div[@class='sec-heading center'])[6]"));
        elementMap.put("hero banner",By.xpath("//div[@class='image-cover hero-banner']"));
        elementMap.put("first center icon",By.xpath("(//div[@class='middle-icon-features-content'])[1]"));
        elementMap.put("second center icon",By.xpath("(//div[@class='middle-icon-features-content'])[2]"));
        elementMap.put("third center icon",By.xpath("(//div[@class='middle-icon-features-content'])[3]"));
        elementMap.put("first item box",By.xpath("(//div[@class='item-box'])[1]"));
        elementMap.put("fourth item box",By.xpath("(//div[@class='item-box'])[4]"));
        elementMap.put("fifth item box",By.xpath("(//div[@class='item-box'])[5]"));
        elementMap.put("sixth item box",By.xpath("(//div[@class='item-box'])[6]"));



        // Add more elements as needed

    }

    public static By getLocator(String elementName) {
        String normalizedName = elementName.toLowerCase();
        if (!elementMap.containsKey(normalizedName)) {
            throw new IllegalArgumentException("Element not found: " + elementName);
        }
        return elementMap.get(normalizedName);
    }

    // Optional: Method to add elements at runtime
    public static void addElement(String elementName, By locator) {
        elementMap.put(elementName.toLowerCase(), locator);
    }
}
