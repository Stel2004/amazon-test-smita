package browserfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

/**
 * Create a New Java Project with the Name amazon-test Create the package ‘browserfactory’ and create the class with the name ‘BaseTest’
 * inside the ‘browserfactory’ package. And write the browser setup code inside the class ‘Base
 * Test’.
 */
public class BaseTest {

    static String browser = "Chrome";
    public static WebDriver driver ;

    public void openBrowser(String baseUrl){
        if(browser.equalsIgnoreCase("Chrome")){
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("Firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("Edge")) {
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("Safari")) {
            driver = new SafariDriver();
        } else {
            System.out.println("Wrong browser Calling");
        }
        driver.get(baseUrl);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));

    }

    public void closeBrowser(){
        driver.quit();
    }
}
