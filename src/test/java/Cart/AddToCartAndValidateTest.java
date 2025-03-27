package Cart;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddToCartAndValidateTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testAddToCartAndValidate() {
        driver.get("https://demo.prestashop.com/#/en/front");

        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("framelive")));
        driver.switchTo().frame(iframe);

        WebElement popularProductsTitle = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("All products")));

        new Actions(driver)
                .scrollToElement(popularProductsTitle)
                .pause(Duration.ofSeconds(1))
                .perform();

        WebElement product = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Hummingbird printed t-shirt")));
        product.click();

        WebElement productPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Add to cart")));
        productPage.click();

    }
}
