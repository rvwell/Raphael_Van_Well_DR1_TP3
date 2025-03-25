package Contact;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test
    public void testContactFormSubmission() throws InterruptedException {
        driver.get("https://demo.prestashop.com/#/en/front");

        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("framelive")));
        driver.switchTo().frame(iframe);

        WebElement contactUsLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#contact-link a")));
        contactUsLink.click();

        WebElement emailAddress = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailAddress.sendKeys("teste@dominio.com");

        WebElement message = driver.findElement(By.id("contactform-message"));
        message.sendKeys("Este é um teste automatizado de envio de formulário.");

        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit'][name='submitMessage']"));
        submitButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert.alert-success")));

        WebElement confirmationMessage = driver.findElement(By.cssSelector(".alert.alert-success"));
        assertTrue(confirmationMessage.isDisplayed(), "A mensagem de confirmação não foi exibida.");

        String expectedMessage = "Your message has been successfully sent to our team.";
        assertTrue(confirmationMessage.getText().contains(expectedMessage), "A mensagem de confirmação não foi exibida corretamente.");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
