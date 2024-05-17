package framework.utils;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.function.Predicate;

public class WebListUtils extends WebPage {

    public WebListUtils(WebDriver driver) {
        super(driver);
    }

    public static WebElement getElementFromListByTextContains(List<WebElement> list, String text) {
        return getElementFromListByPredicate(list, getElementFromListByTextContains(text));
    }

    public static WebElement getElementFromListByAttributeEquals(List<WebElement> list, String attribute, String value) {
        return getElementFromListByPredicate(list, getPredicateElementFromListByAttributeEquals(attribute, value));
    }

    public static WebElement getElementFromListByAttributeContains(List<WebElement> list, String attribute, String value) {
        return getElementFromListByPredicate(list, getPredicateElementByAttributeContains(attribute, value));
    }

    private static WebElement getElementFromListByPredicate(List<WebElement> list, Predicate<WebElement> predicate) {
        return list
                .stream()
                .filter(predicate)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Element not found!"));
    }

    private static Predicate<WebElement> getElementFromListByTextContains(String text) {
        return webElement -> webElement.getText().contains(text);
    }

    private static Predicate<WebElement> getPredicateElementFromListByAttributeEquals(String attribute, String value) {
        return webElement -> webElement.getAttribute(attribute).equals(value);
    }

    private static Predicate<WebElement> getPredicateElementByAttributeContains(String attribute, String value) {
        return webElement -> webElement.getAttribute(attribute).contains(value);
    }
}
