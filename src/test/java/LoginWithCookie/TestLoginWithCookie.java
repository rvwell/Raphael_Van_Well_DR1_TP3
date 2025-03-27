package LoginWithCookie;

import Register.RegisterUserTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestLoginWithCookie {

    private WebDriver driver;
    private WebDriverWait wait;
    private final String BASE_URL = "https://demo.prestashop.com/#/en/front";

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
    public void testPlaceOrderLoginPersist() {
        RegisterUserTest registerTest = new RegisterUserTest();
        registerTest.setUp();
        registerTest.testRegisterFormSubmission();

        Set<Cookie> cookies = registerTest.getLoginCookies();
        Cookie prestaShopCookie = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().startsWith("PrestaShop-")) {
                prestaShopCookie = cookie;
                break;
            }
        }

        driver.get(BASE_URL);

        driver.manage().addCookie(prestaShopCookie);

        driver.navigate().refresh();

        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("framelive")));
        driver.switchTo().frame(iframe);

        WebElement signOutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Sign out")));
        assertTrue(signOutLink.isDisplayed(), "Login persistente falhou.");
    }
}
