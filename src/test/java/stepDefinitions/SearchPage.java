package stepDefinitions;

import dataProvider.ConfigFileReader;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchPage {

    WebDriver driver;
    ConfigFileReader config;

    @Given("User is in Google site")
    public void user_is_in_google_site() {
        config = new ConfigFileReader();
        System.setProperty("webdriver.chrome.driver", config.getDriverPath());
        driver = new ChromeDriver();
        driver.get("https://www.google.com/");
        driver.manage().window().maximize();
    }
    @When("User refreshes page")
    public void user_refreshed_page() {
        driver.navigate().refresh();
    }

    @Then("Page will be redirected to Google site")
    public void google_site_page() {
        String title = driver.getTitle();
        Assert.assertEquals( "Google", title);
    }

    @When("User inputs {string}")
    public void user_inputs(String word) {
        driver.findElement(By.xpath("//input[@title='Search']")).sendKeys(word);
    }
    @When("User clicks Search")
    public void user_clicks_search() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='VlcLAe']//input[@value='Google Search']")));
        driver.findElement(By.xpath("//div[@class='FPdoLc lJ9FBc']//input[@value='Google Search']")).click();
    }

    @When("User hit enter")
    public void user_hit_enter() {
        driver.findElement(By.xpath("//input[@title='Search']")).sendKeys(Keys.RETURN);
    }
    @Then("Page will be redirected to {string} Google Search")
    public void page_will_be_redirected_to_google_search(String searchedText) {
        String searchedtitle = driver.getTitle();
        Assert.assertEquals( searchedText + " - Google Search", searchedtitle);
    }
    @Then("User closes browser")
    public void user_closes_browser() {
        driver.quit();
    }
}
