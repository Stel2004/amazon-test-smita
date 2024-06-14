package utilities;

import browserfactory.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

/**
 * Create the package utilities and create the class with the name ‘Utility’ inside the ‘utilities’
 * package. And write the all the utility methods in it’.
 */
public class Utility extends BaseTest {

    // Accept or declined cookies policy
    public void acceptCookiesPolicy(By by){
        driver.findElement(by).click();
    }

    // Send text to element
    public void sendTextToElement(By by, String text){
        driver.findElement(by).sendKeys(text);
    }

    // Click on element
    public void clickOnElement(By by){
        driver.findElement(by).click();
    }

    // Clear element
    public void clearElement(By by){
        driver.findElement(by).clear();
    }
    public void clearText(By by){
        driver.findElement(by).clear();
    }

    // sleep
    public void sleep(int duration) throws InterruptedException {
        Thread.sleep(duration);
    }

    // Get text from element
    public String getTextFromElement(By by){
        return (driver.findElement(by).getText());
    }

    // Verify the actual and expected text
    public void verifyElements(String displayMessage, String expectedText, By by) {
        String actualText = getTextFromElement(by).trim();
        Assert.assertEquals(displayMessage, expectedText, actualText);
    }


    //************************* Alert Methods *****************************************************

    /**
     * This method will switch to alert
     */
    public void switchToAlert() {
        driver.switchTo().alert();
    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    public void getTextAlert() {
        driver.switchTo().alert().getText();
    }

    public void sendTextToAlert(By by, String text) {
        driver.switchTo().alert().sendKeys(text);
    }

    //************************* Dropdown Methods *****************************************************

    public void selectByValueFromDropDown(By by, String value) {
        WebElement dropDown = driver.findElement(by);
        // Create the object of Select Class
        Select select = new Select(dropDown);
        select.selectByValue(value);
    }

    public void selectByVisibleTextFromDropDown(By by, String text) {
        WebElement dropDown = driver.findElement(by);
        Select select = new Select(dropDown);
        select.selectByVisibleText(text);
    }

    public void selectByIndex(By by, int num) {
        WebElement index = driver.findElement(by);
        Select select = new Select(index);
        select.selectByIndex(num);
    }

    //*************************** Action Methods ***************************************//
    //Mouse hover
    public WebElement mouseHoverToElement(By by) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(by)).build().perform();
        return null;
    }

    //Mouse hover and click
    public WebElement mouseHoverAndClickOnElement(By by) {
        Actions actions = new Actions(driver);
        WebElement text1 = driver.findElement(by);
        WebElement text2 = driver.findElement(by);
        actions.moveToElement(text1).moveToElement(text2).click().build().perform();
        return text1;
    }

    /***************** Random Email ***************************/
    public static String getRandomEmail() { // Method generating Random email everytime
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder random = new StringBuilder();
        Random rnd = new Random();
        while (random.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * chars.length());
            random.append(chars.charAt(index));
        }
        String email = random.toString() + "@gmail.com";
        return email;
    }

    public void waitForElementToBeVisible(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementsToBeVisible(List<WebElement> elements, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void waitForPageToLoad(int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(webDriver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }
}
