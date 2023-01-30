package stepDefinitions;

import dataProvider.ConfigFileReader;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    ConfigFileReader config;

    @Given("User navigate to Swag Labs site")
    public void user_navigate_to_swag_labs_site() {
        config = new ConfigFileReader();
        System.setProperty("webdriver.chrome.driver", config.getDriverPath());
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
    }

    @When("User inputs {string} and {string}")
    public void user_inputs_username_and_password(String username, String password) {
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
        driver.findElement(By.xpath("//*[@id='login-button']")).click();
    }

    @Then("User will be able to go to dashboard page")
    public void user_will_be_able_to_go_to_dashboard_page() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='title']")));
        String title = driver.findElement(By.xpath("//span[@class='title']")).getText();
        Assert.assertEquals("PRODUCTS", title);
    }

    @Then("Page will display {string}")
    public void page_will_display_error_message(String errorMessage) {
        String message = driver.findElement(By.xpath("//*[@class='error-message-container error']/h3[@data-test='error']")).getText();
        Assert.assertEquals(errorMessage, message);
    }

    @And("User closes window")
    public void user_closes_window() {
        driver.quit();
    }

}
