package library;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class BaseLibrary {

    WebDriver driver;

    public BaseLibrary(WebDriver driver) {

        this.driver = driver;
    }

    public void waitForElementToBeClickable(By findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(findBy));
    }

    public  void waitForElementToBeVisible(By findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForElementToBeInvisibleWithText(By findBy,String message){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementWithText(findBy, message));

    }

    public void waitForElementToBeInvisible(WebElement findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(findBy));
    }

    public  void selectWithVisibleText(WebElement finBy,String visibleText){
        Select value = new Select(finBy);
        value.selectByVisibleText(visibleText);
    }

    public void fullPageScroll(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public void pageRefresh(){
        driver.navigate().refresh();
    }

    public void tabClick(WebElement findBy){
        Actions actions = new Actions(driver);
        actions.sendKeys(findBy, Keys.TAB).build().perform();

    }

    public void mouseHover(WebElement findBy){
        Actions actions = new Actions(driver);
        actions.moveToElement(findBy).perform();
    }
    public LocalDateTime indianTimeZoneDateTime(){
        ZoneId indianTimeZone = ZoneId.of("Asia/Kolkata");
        LocalDateTime currentIndianDate = LocalDateTime.now(indianTimeZone);
        return currentIndianDate;
    }

    public DayOfWeek indianTimeZoneDayOfWeek(){
        ZoneId indianTimeZone = ZoneId.of("Asia/Kolkata");
        LocalDateTime currentIndianTime = LocalDateTime.now(indianTimeZone);
        return currentIndianTime.getDayOfWeek();
    }

}
