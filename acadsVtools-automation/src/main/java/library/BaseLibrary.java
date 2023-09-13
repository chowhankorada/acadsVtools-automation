package library;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

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

    public long dateTimeInMillis(){
        long currentTimeMillis = System.currentTimeMillis();
        return currentTimeMillis;
    }

    public String milliesToDateTimeFormat(long timestampMillis){
        Date date = new Date(timestampMillis);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        String formattedDate = dateFormat.format(date);
        return formattedDate;
    }



    public String millistoDayOfWeek(long currentTimeMillis){
        // Create an Instant object from the current time
        Instant currentInstant = Instant.ofEpochMilli(currentTimeMillis);
        // Define a time zone (e.g., UTC, or your desired zone)
        ZoneId zoneId = TimeZone.getDefault().toZoneId();
        // Get the weekday from the Instant object
        String weekday = currentInstant.atZone(zoneId)
                .getDayOfWeek()
                .getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        return weekday;

    }

}
