package stepDefinitions;

import dataProvider.ConfigFileReader;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dashboard {

    WebDriver driver;
    ConfigFileReader config;

    @Given("User is login to site")
    public void user_is_login_to_site() {
        System.setProperty("webdriver.chrome.driver", "/Users/evaldez/Documents/driver/chromedriver_mac_arm64/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//*[@id='login-button']")).click();
    }

    @When("User add item in cart")
    public void user_add_item_in_cart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='title']")));
        driver.findElement(By.xpath("//div[@class='inventory_item']//div[contains(text(),'Sauce Labs Backpack')]//..//..//..//button[contains(@class,'btn_inventory')]")).click();
    }

    @When("User select sort via {string}")
    public void user_select_sort_via(String sortOption) {
        WebElement element = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
        Select option = new Select(element);
//        option.selectByVisibleText(sortOption);
        option.selectByValue(sortOption);
    }

    @Then("Items will be sorted in ascending order")
    public void items_will_be_sorted_in_ascending_order() {
        ArrayList<String> products = new ArrayList<>();
        List<WebElement> elementList = driver.findElements(By.xpath("//div[@class='inventory_item_name']"));
        for (WebElement we : elementList) {
            products.add(we.getText());
        }
        ArrayList<String> sortedList = new ArrayList<>();
        for (String s : products) {
            sortedList.add(s);
        }
        Collections.sort(sortedList);
        Assert.assertTrue(sortedList.equals(products));

        driver.quit();
    }

    @Then("Items will be sorted in descending order")
    public void items_will_be_sorted_in_descending_order() {
        ArrayList<String> products = new ArrayList<>();
        List<WebElement> elementList = driver.findElements(By.xpath("//div[@class='inventory_item_name']"));
        for (WebElement we : elementList) {
            products.add(we.getText());
        }
        ArrayList<String> sortedList = new ArrayList<>();
        for (String s : products) {
            sortedList.add(s);
        }
        Collections.sort(sortedList);
        Collections.reverse(sortedList);
        Assert.assertTrue(sortedList.equals(products));

        driver.quit();
    }

    @Then("Item is displayed in minicart")
    public void item_is_displayed_in_minicart() {
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class,'checkout_button')]")));
        boolean status = driver.findElement(By.xpath("//div[@class='cart_item_label']//div[contains(text(),'Sauce Labs Backpack')]")).isDisplayed();
        Assert.assertTrue(status);

    }

    @And("User remove item in minicart")
    public void user_remove_item_in_cart() {
        driver.findElement(By.xpath("//div[@class='cart_item_label']//div[contains(text(),'Sauce Labs Backpack')]//..//..//button[contains(@class,'btn_secondary')]")).click();
        driver.quit();
    }
}