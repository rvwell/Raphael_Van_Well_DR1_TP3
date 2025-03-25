package Register;

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

public class RegisterUserTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test
    public void testRegisterFormSubmission() throws InterruptedException {
        driver.get("https://demo.prestashop.com/#/en/front");

        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("framelive")));
        driver.switchTo().frame(iframe);

        WebElement signInLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sign in")));
        signInLink.click();

        WebElement registerLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("No account? Create one here")));
        registerLink.click();


        WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("field-firstname")));
        firstNameField.sendKeys("Teste");

        WebElement lastNameField = driver.findElement(By.id("field-lastname"));
        lastNameField.sendKeys("Usuario");

        WebElement emailField = driver.findElement(By.id("field-email"));
        emailField.sendKeys("teste.usuario@email.com");


        WebElement passwordField = driver.findElement(By.id("field-password"));
        passwordField.sendKeys("SenhaForte123!");

        WebElement birthdayField = driver.findElement(By.id("field-birthday"));
        birthdayField.sendKeys("01/01/1991");

        driver.findElement(By.cssSelector("input[name='optin']")).click();
        driver.findElement(By.cssSelector("input[name='psgdpr']")).click();
        driver.findElement(By.cssSelector("input[name='newsletter']")).click();
        driver.findElement(By.cssSelector("input[name='customer_privacy']")).click();

        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();

        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Sign out")));
        assertTrue(successMessage.isDisplayed(), "A mensagem de sucesso não foi exibida.");

    }
}
