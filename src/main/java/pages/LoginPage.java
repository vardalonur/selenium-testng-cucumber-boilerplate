package pages;

import config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.LogUtils;

import java.util.List;
import java.util.Map;

public class LoginPage extends BasePage {

    // Constructor
    public LoginPage() {
        super();
    }

    public boolean verifyAllFieldErrors(List<Map<String, String>> expectedErrors) {
        for (Map<String, String> error : expectedErrors) {
            String field = error.get("field");
            String expectedMessage = error.get("message");

            if (expectedMessage == null || expectedMessage.isEmpty()) {
                continue;
            }

            String actualMessage = getFieldError(field);
            if (!expectedMessage.equals(actualMessage)) {
                LogUtils.error(String.format("Error message mismatch for %s field. Expected: %s, Actual: %s",
                        field, expectedMessage, actualMessage));
                return false;
            }
        }
        return true;
    }

    private String getFieldError(String field) {
        String elementName = getErrorElementName(field);
        return getText(elementName);
    }

    private String getErrorElementName(String field) {
        return switch (field) {
            case "username" -> "email error message";
            case "password" -> "password error message";
            case "popup" -> "popup message";
            default -> throw new IllegalArgumentException("Unknown field: " + field);
        };
    }


}
