package steps;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import selenium.driver.WebDriverUtility; 
import selenium.driver.Browser;

public class RequestADemo {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = WebDriverUtility.getWebDriver(Browser.FIREFOX); 
        driver.manage().timeouts();
    }

    @Test
    @DisplayName("Request a demo test")
    public void testRequestADemo() {
        // Search for AODocs in Google
        driver.get("https://www.google.fr");

        // Refuse Google cookies
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement refuseCookiesButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("W0wltc")));
        refuseCookiesButton.click();
        
        // Enter AODocs on a Google search bar 
        WebElement searchbarElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("APjFqb")));
        searchbarElement.sendKeys("AODocs");
        searchbarElement.sendKeys(Keys.ENTER);
        
        // Click the AODocs website link
        WebElement aodocsResult = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[contains(text(), 'AODocs | Cloud Document Managemen')]")));
        aodocsResult.click();

        // Click request demo 
        WebElement requestDemoButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-id='47706e2a']")));
        requestDemoButton.click();

        // Enter the first name
        WebElement firstNameField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@placeholder, 'First Name*')]")));
        firstNameField.sendKeys("Seldy");

        // Leave the last name field empty
        WebElement lastNameField = driver.findElement(By.xpath("//input[contains(@placeholder, 'Last Name*')]"));
        lastNameField.clear();

        // Enter the email
        WebElement emailField = driver.findElement(By.xpath("//input[contains(@placeholder, 'Your Email*')]"));
        emailField.sendKeys("seldylonguy@yopmail.fr");

        // Select a company size
        WebElement companySizeButton = driver.findElement(By.xpath("//input[contains(@placeholder, 'Company Size*')]"));
        Select selectCompanySize = new Select(companySizeButton);
        selectCompanySize.selectByVisibleText("0-4 employees");

        // Display the last name error message 
        WebElement lastNameErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("hs-error-msg")));
        String expectedLastNameErrorMessage = "Please complete this required field."; 
        assertEquals(expectedLastNameErrorMessage, lastNameErrorMessage.getText(), "Please complete this required field.");

        // Display the email error message 
        WebElement emailErrorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("hs-error-msg")));
        String expectedEmailErrorMessage = "Please enter a valid email address."; 
        assertEquals(expectedEmailErrorMessage, emailErrorMessage.getText(), "Please enter a valid email address.");
    }
    @AfterEach
    public void tearDown() {
        WebDriverUtility.closeWebDriver(driver);
    }
}