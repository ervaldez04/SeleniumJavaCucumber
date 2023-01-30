package stepDefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Alert;
import java.util.concurrent.TimeUnit;
import dataProvider.ConfigFileReader;

import java.time.Duration;

public class BankPage {

    WebDriver driver;
    ConfigFileReader config;

    @Given("User navigates to Bank site")
    public void user_navigates_to_bank_site() {
        config = new ConfigFileReader();
        System.setProperty("webdriver.chrome.driver", config.getDriverPath());
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(config.getImplicitlyWait(), TimeUnit.SECONDS);
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
        driver.manage().window().maximize();
    }
    @When("User clicks Customer Login")
    public void user_clicks_customer_login() {
        driver.findElement(By.xpath("//button[@ng-click='customer()']")).click();
    }
    @When("User logins using {string}")
    public void user_logins_using(String userOption) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='userSelect']")));
        WebElement element = driver.findElement(By.xpath("//select[@id='userSelect']"));
        Select option = new Select(element);
        option.selectByVisibleText(userOption);
        driver.findElement(By.xpath("//button[contains(@ng-show,'custId')]")).click();
    }

    @Given("User navigates backward")
    public void user_navigates_backward() {
        config = new ConfigFileReader();
        System.setProperty("webdriver.chrome.driver", config.getDriverPath());
        driver = new ChromeDriver();
        driver.navigate().back();
    }
    @When("User clicks Open Account")
    public void user_clicks_open_account() {
        driver.findElement(By.xpath("//button[@ng-click='home()']")).click();
        driver.findElement(By.xpath("//button[@ng-click='manager()']")).click();
        driver.findElement(By.xpath("//button[@ng-click='openAccount()']")).click();
    }
    @When("User inputs account details {string}, {string}, {string}")
    public void user_inputs_account_details(String firstName, String lastName, String currency) {
        Select customerDropdown = new Select(driver.findElement(By.xpath("//select[@id='userSelect']")));
        customerDropdown.selectByVisibleText(firstName+" "+lastName);
        Select currencyDropdown = new Select(driver.findElement(By.xpath("//select[@id='currency']")));
        currencyDropdown.selectByVisibleText(currency);
        driver.findElement(By.xpath("//button[contains(text(), 'Process') and (@type='submit')]")).click();
    }

    @Then("User will be redirected to {string} Homepage")
    public void user_will_be_redirected_to_homepage(String user) {
        String loginUser = driver.findElement(By.xpath("//span[@class='fontBig ng-binding']")).getText();
        Assert.assertEquals(user, loginUser);
        driver.quit();
    }

    @Given("User clicks Add Customer")
    public void user_clicks_add_customer() {
        driver.findElement(By.xpath("//button[@ng-click='manager()']")).click();
        driver.findElement(By.xpath("//button[@ng-click='addCust()']")).click();
    }
    @When("User inputs required fields {string}, {string}, {string}")
    public void user_inputs_required_fields(String firstName, String lastName, String postCode) {
        driver.findElement(By.xpath("//input[@placeholder ='First Name']")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@placeholder ='Last Name']")).sendKeys(lastName);
        driver.findElement(By.xpath("//input[@placeholder ='Post Code']")).sendKeys(postCode);
        driver.findElement(By.xpath("//button[contains(text(), 'Add Customer') and (@type='submit')]")).click();
    }

    @When("User clicks Customer")
    public void user_clicks_customer() {
        driver.findElement(By.xpath("//button[@ng-click='home()']")).click();
        driver.findElement(By.xpath("//button[@ng-click='manager()']")).click();
        driver.findElement(By.xpath("//button[@ng-click='showCust()']")).click();
    }
    @When("User deletes {string}")
    public void user_deletes(String user) {
        driver.findElement(By.xpath("//input[@ng-model='searchCustomer']")).sendKeys(user);
        driver.findElement(By.xpath("//tr[@class='ng-scope']//button[@ng-click='deleteCust(cust)']")).click();
    }

    @Then("Details is be added successfully {string}")
    public void details_is_be_added_successfully(String message) {
        Alert alert = driver.switchTo().alert();
        String alertMessage= driver.switchTo().alert().getText();
        Assert.assertTrue("Not added", alertMessage.contains(message));
        alert.accept();
        driver.quit();
    }

    @Given("User creates new customer {string}, {string}, {string}")
    public void user_creates_new_customer(String firstName, String lastName, String postCode) {
        user_clicks_add_customer();
        user_inputs_required_fields(firstName, lastName, postCode);
        user_closes_alert_message();
        user_clicks_open_account();
    }

    @Given("User logins via Customer {string}")
    public void user_logins_via_customer(String userOption) {
//        driver.findElement(By.xpath("//button[@ng-click='customer()']")).click();
        driver.findElement(By.xpath("//button[@ng-click='customer()']")).click();
//        driver.findElement(By.xpath("//button[@ng-click='openAccount()']")).click();
        WebElement element = driver.findElement(By.xpath("//select[@id='userSelect']"));
        Select option = new Select(element);
        option.selectByVisibleText(userOption);
        driver.findElement(By.xpath("//button[contains(@ng-show,'custId')]")).click();
    }

    @Given("User selects Deposit")
    public void user_selects_deposit() {
        driver.findElement(By.xpath("//button[@ng-click='deposit()']")).click();
    }

    @Given("User selects Withdrawl")
    public void user_selects_withdrawl() {
        driver.findElement(By.xpath("//button[@ng-click='withdrawl()']")).click();
    }
    @When("User enters deposit of {string}")
    public void user_enter_amount_of(String amount) {
        driver.findElement(By.xpath("//input[@ng-model='amount']")).sendKeys(amount);
        driver.findElement(By.xpath("//button[contains(text(), 'Deposit') and (@type='submit')]")).click();
    }

    @When("User enters withdrawal amount of {string}")
    public void user_enter_withdrawl_amount_of(String amount) {
        driver.findElement(By.xpath("//input[@ng-model='amount']")).sendKeys(amount);
        driver.findElement(By.xpath("//button[contains(text(), 'Withdraw') and (@type='submit')]")).click();
    }
    @Then("Transaction Message of {string} is Displayed")
    public void transaction_message(String message) {
        String actualMessage = driver.findElement(By.xpath("//span[@ng-show='message']")).getText();
        Assert.assertEquals(message, actualMessage);
        driver.quit();
    }

    @When("User clicks Logout")
    public void user_clicks_logout() {
        driver.findElement(By.xpath("//button[@ng-click='byebye()']")).click();
    }

    @Then("User is redirected to login page")
    public void user_redirected_to_login() {
        String url = driver.getCurrentUrl();
        Assert.assertEquals("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/customer", url);
        driver.quit();
    }

    public void user_closes_alert_message() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

}
