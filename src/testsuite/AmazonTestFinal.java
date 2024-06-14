package testsuite;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

/**
 * Automate Following Test.
 * 1. Open the URL https://www.amazon.co.uk/
 * 2. Type "Dell Laptop" in the search box and press enter or click on search
 *    Button.
 * 3. Click on the checkbox brand Dell on the left side.
 * 4. Verify that the  30(maybe different) products are displayed on the page.
 * 5. Print all product names in the console.
 * 6. Click on the product name 'Dell Latitude 5410 14" FHD Business Laptop Computer, Intel Core i5-10310U, 16GB RAM, 512GB SSD, Backlit Keyboard, Windows 10 Pro (Renewed)'
 * 7. Verify the Product name 'Dell Latitude 5410 14" FHD Business Laptop Computer, Intel Core i5-10310U, 16GB RAM, 512GB SSD, Backlit Keyboard, Windows 10 Pro (Renewed)'
 * 5. Close the Browser.
 */
public class AmazonTestFinal extends Utility {
    String baseUrl = "https://www.amazon.co.uk/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void productVerifySuccessfully() throws InterruptedException {
        // Accept cookies policy popup
        acceptCookiesPolicy(By.id("sp-cc-accept"));
        sendTextToElement(By.id("twotabsearchtextbox"), "Dell Laptop");
        clickOnElement(By.id("nav-search-submit-button"));
        sleep(2000);
        clickOnElement(By.xpath("//li[@aria-label='Dell']//i[@class='a-icon a-icon-checkbox']"));
        int page = 1;
        productList(page);
    }

    public void productList(int page) throws InterruptedException {
        List<WebElement> listItems = driver.findElements(By.xpath("//div[@data-cy='title-recipe']/h2"));
        System.out.println("Total number of elements are: " + listItems.size());
        sleep(2000);
         // Loop through the products
        for (WebElement item : listItems) {
            System.out.println(item.getText() + "\n");

            // Check if the product name matches the desired product
            if (item.getText().equals("Dell Latitude 5410 14\" FHD Business Laptop Computer, Intel Core i5-10310U, 16GB RAM, 512GB SSD, Backlit Keyboard, Windows 10 Pro (Renewed)")) {
                System.out.println("**************************************************************************");
                clickOnElement(By.xpath("//span[contains(text(), 'Dell Latitude 5410 14\" FHD Business Laptop Computer, Intel Core i5-10310U, 16GB RAM, 512GB SSD, Backlit Keyboard, Windows 10 Pro (Renewed)')]"));
                sleep(10000);

                // Get the product title on the product details page
                String expectedTitle = "Dell Latitude 5410 14\" FHD Business Laptop Computer, Intel Core i5-10310U, 16GB RAM, 512GB SSD, Backlit Keyboard, Windows 10 Pro (Renewed)";
                // Verify the product title
                verifyElements("Product title does not match!", expectedTitle, By.id("productTitle"));
                return;  // Exit the method
            }
        }
        page++;
        pagination(page);
    }

    public void pagination(int page) throws InterruptedException {
        // Next Button
        clickOnElement(By.xpath("//a[@class='s-pagination-item s-pagination-next s-pagination-button s-pagination-separator']"));
        sleep(3000);
        productList(page);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
