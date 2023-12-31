package pages;


import library.BaseLibrary;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class BatchPage extends BaseLibrary {

    WebDriver driver;

    public BatchPage(WebDriver driver) {

        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//a[contains(text(),'Create Batch')]")
    WebElement createBatch;
    By createBatchBy = By.xpath("//a[contains(text(),'Create Batch')]");
    @FindBy(xpath = "//button[contains(text(),'Proceed To Interface')]")
    WebElement batchInterface;
    @FindBy(xpath = ("//div[contains(text(),'Capacity*')]/following-sibling::div/input"))
    WebElement capacity;
    @FindBy(xpath = "//input[@ng-model='batch.fillRate']")
    WebElement fillrate;
    @FindBy(xpath = "//input[@checklist-model='batch.packageTypes']/following-sibling::label")
    List<WebElement> enrolmentTypes;
    @FindBy(xpath = "//select[@ng-model='batch.language']")
    WebElement language;
    @FindBy(xpath = "//select[@ng-model='batch.sessionYear']")
    WebElement sessionYear;
    @FindBy(xpath = "//input[contains(@ng-model,'duration')]")
    WebElement duration;
    @FindBy(xpath = "//input[contains(@ng-model,'groupName')]")
    WebElement groupName;
    @FindBy(xpath = "(//input[@checklist-model='batch.levels']/following-sibling::label)")
    List<WebElement> levels;
    @FindBy(xpath = "//div[contains(@ng-repeat,'searchTerm')]/input/following-sibling::label")
    List<WebElement> searchTerms;

    @FindBy(xpath = "//tr[contains(@ng-repeat,'weeklySchedule')]")
    List<WebElement> weeklyCal;
    @FindBy(xpath = "//tr[contains(@ng-repeat,'weeklySchedule')]/td[1]/input")
    List<WebElement> weeklyCheckBoxs;
    @FindBy(xpath = "//tr[contains(@ng-repeat,'weeklySchedule')]/td[2]")
    List<WebElement> weeklyDays;
    @FindBy(xpath = "//tr[contains(@ng-repeat,'weeklySchedule')]/td[4]/a")
    List<WebElement> weeklyAddTimings;
    //	@FindBy(xpath = "//tr[contains(@ng-repeat,'weeklySchedule')]/td[3]/table/tbody/tr/td/div/div[1]/div[2]")
//	WebElement WeeklyStartTimeSelector;
//	By WeeklyStartTimeSelectorBy = By.xpath("//tr[contains(@ng-repeat,'weeklySchedule')]/td[3]/table/tbody/tr/td/div/div[1]/div[2]");
    @FindBy(xpath = "(//div[contains(@class,'slot1')])[1]/input")
    WebElement WeeklyStartTimeSelector;
    By WeeklyStartTimeSelectorBy = By.xpath("(//div[contains(@class,'slot1')])[1]/input");
    @FindBy(xpath = "(//div[contains(@class,'slot2')])[1]/input")
    WebElement WeeklyEndTimeSelector;
    @FindBy(xpath = "//input[contains(@ng-model,'purchasePrice')]")
    WebElement batchBulkPrice;
    @FindBy(xpath = "//button[normalize-space()='Save Batch']")
    WebElement batchSave;
    @FindBy(xpath = "//input[contains(@ng-model,'startDateTime')]")
    WebElement batchStartDate;
    @FindBy(xpath = "//input[contains(@ng-model,'endDateTime')]")
    WebElement batchEndDate;
    @FindBy(xpath = "//input[contains(@ng-model,'lastPurchaseDateTime')]")
    WebElement batchLastPurchaseDate;
    @FindBy(xpath = "//button[normalize-space()='Save Batch']")
    WebElement savingBatch;
    @FindBy(xpath = "//button[normalize-space()='Yes']")
    WebElement batchSaveConfirmation;
    @FindBy(xpath = "//button[contains(text(),'Ok')]")
    WebElement batchOk;
    @FindBy(xpath = "//input[@placeholder='Enter Min 2 characters']")
    WebElement teacherInput;
    By suggestedTeacherBy = By.xpath("//div[contains(@class,'user-sugg')]");
    @FindBy(xpath = "//div[contains(@class,'user-sugg')]")
    WebElement suggestedTeacher;
    @FindBy(xpath = "//input[contains(@name,'teacher-board')]/following-sibling::label")
    WebElement teacherSubject;
    @FindBy(xpath = "//select[@name='presenterRole']")
    WebElement teacherRole;
    @FindBy(xpath = "//input[contains(@ng-model,'maxSessions')]")
    WebElement maxSessionCount;
    By maxSessionCountBy = By.xpath("//input[contains(@ng-model,'maxSessions')]");
    @FindBy(xpath = "//input[@ng-model='sessionPlan.startDateTime']")
    WebElement sessionStartDateInput;
    @FindBy(xpath = "//input[@ng-model='sessionPlan.endDateTime']")
    WebElement sessionEndDateInput;
    @FindBy(xpath = "//label[contains(@for,'session_day')]")
    List<WebElement> sessionDayOfWeek;
    @FindBy(xpath = "//input[@ng-model='session.startTime']")
    WebElement sessionStartTimeInput;
    @FindBy(xpath = "//input[@ng-model='session.endTime']")
    WebElement sessionEndTimeInput;
    @FindBy(xpath = "//select[@ng-model='session.teacher']")
    WebElement sessionTeacherElement;
    @FindBy(xpath = "(//button[contains(text(),'Generate')])[2]")
    WebElement sessionGenButton;
    @FindBy(xpath = "//textarea[@placeholder='Agenda title']")
    WebElement sessionTitle;
    @FindBy(xpath = "//select[@ng-model='agenda.otmSessionType']")
    WebElement sessionSourceTypes;
    @FindBy(xpath = "//input[@value='Save Sessions']")
    WebElement sessionSaving;
    @FindBy(xpath = "//div[contains(text(),'curricullumTopics not present')]")
    WebElement topicSelectionError;
    @FindBy(xpath = "//button[contains(@class,'closeVMessagePopup')]")
    WebElement topicSelectionErrorAck;
    @FindBy(xpath = "//a[contains(text(),'Add topics *')]")
    WebElement addingTopic;
    @FindBy(xpath = "//li[@ng-repeat= 'topic in chapterCurriculum.nodes']/input")
    WebElement firstTopic;
    @FindBy(xpath = "//span[contains(text(),'Add')]")
    WebElement topicAddButton;
    @FindBy(xpath = "//div[contains(text(),'Sessions Added to Batch successfully')]")
    WebElement successSessionCreation;
    By successSessionCreationBy = By.xpath("//div[contains(text(),'Sessions Added to Batch successfully')]");
    @FindBy(xpath = "//button[normalize-space()='Ok']")
    WebElement sessionOk;
    By sessionOkBy = By.xpath("//button[normalize-space()='Ok']");




    public void batchCreation(String batchCapacity, String batchFillrate, String batchDuration, String batchGroupName, String enrolType, String uiSessionYear, String uiLangType, String uiLevelType, String uiSearchTerm, String batchPrice, long slotStartInNext, long slotEndInNext, String teacherEmail) throws InterruptedException {
        waitForElementToBeVisible(createBatchBy);
        waitForElementToBeClickable(createBatchBy);
        createBatch.click();
        batchInterface.click();
        capacity.sendKeys(batchCapacity);
        fillrate.sendKeys(batchFillrate);

        //Enrolment type
        for (int i = 0; i < enrolmentTypes.size(); i++) {
            WebElement enrolmentElement = enrolmentTypes.get(i);
            String enrolment = enrolmentElement.getText();
            if (enrolment.equals(enrolType)) {
                enrolmentElement.click();
            }
        }

        // Language selection

        Select lang = new Select(language);
        List<WebElement> allLangTypes = lang.getOptions();
        for (WebElement langType : allLangTypes) {
            if (langType.getText().equals(uiLangType)) {
                langType.click();
            }
            ;
        }

        // Session Year*
        Select session = new Select(sessionYear);
        List<WebElement> allTypesSessions = session.getOptions();
        for (WebElement sessionType : allTypesSessions) {
            if (sessionType.getText().equals(uiSessionYear)) {
                sessionType.click();
            }
            ;
        }

        duration.sendKeys(batchDuration);
        groupName.sendKeys(batchGroupName);

        // Course Level*
        for (int j = 0; j < levels.size(); j++) {
            WebElement level = levels.get(j);
            if (level.getText().equals(uiLevelType)) {
                level.click();

            }
        }

        // for Search Terms*
        for (int k = 0; k < searchTerms.size(); k++) {
            WebElement searchTerm = searchTerms.get(k);
            if (searchTerm.getText().equals(uiSearchTerm)) {
                searchTerm.click();
            }
        }

        // Batch weekly selection Indian Time Zone

        //getting week of the day in formatted way
        Object currentDay = indianTimeZoneDayOfWeek();
        String currentDayShort = currentDay.toString().substring(0, 3);

        //getting current and adding 1hr to start and 2hr of end
        LocalDateTime currentIndianTime = indianTimeZoneDateTime();
        LocalDateTime updatedStartDateTimeBy = currentIndianTime.plusHours(slotStartInNext);
        LocalDateTime updatedEndDateTimeBy = currentIndianTime.plusHours(slotEndInNext);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hhmma", Locale.ENGLISH);

        for (int l = 1; l < weeklyDays.size(); l++) {
            String weeklyDay = weeklyDays.get(l).getText();
            if (Objects.equals(weeklyDay, currentDayShort)) {
                weeklyCheckBoxs.get(l).click();
                weeklyAddTimings.get(l).click();
                Thread.sleep(2000);
                String slotStartTime = updatedStartDateTimeBy.format(formatter);
                fullPageScroll();
                waitForElementToBeVisible(WeeklyStartTimeSelectorBy);
                waitForElementToBeClickable(WeeklyStartTimeSelectorBy);
                WeeklyStartTimeSelector.sendKeys(slotStartTime);
                String slotEndTime = updatedEndDateTimeBy.format(formatter);
                WeeklyEndTimeSelector.sendKeys(slotEndTime);

            }
        }
        batchBulkPrice.clear();
        batchBulkPrice.sendKeys(batchPrice);
        batchSave.click();

        //Batch Start date time
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("ddMMyyyy,hhmma", Locale.ENGLISH);
        String batchStartTimeDate = currentIndianTime.format(formatter1);
        String[] startSplittedDateTime = batchStartTimeDate.split(",");
        String startSplittedDate = startSplittedDateTime[0].trim();
        String startSplittedTime = startSplittedDateTime[1].trim();
        String combinedStartDateTime = startSplittedDate + "\t" + startSplittedTime;
        batchStartDate.sendKeys(combinedStartDateTime);


        //Batch End date time & Last purchase date time
        LocalDateTime endDateTime = currentIndianTime.plusMonths(5);
        String batchEndTimeDate = endDateTime.format(formatter1);
        String[] endSplittedDateTime = batchEndTimeDate.split(",");
        String endSplittedDate = endSplittedDateTime[0].trim();
        String endSplittedTime = endSplittedDateTime[1].trim();
        String combinedEndDateTime = endSplittedDate + "\t" + endSplittedTime;
        batchEndDate.sendKeys(combinedEndDateTime);


        //Batch last purchase date and time giving same as end date
        batchLastPurchaseDate.sendKeys(combinedEndDateTime);

        //Adding teacher to batch
        teacherInput.sendKeys(teacherEmail);
        waitForElementToBeClickable(suggestedTeacherBy);
        suggestedTeacher.click();
        teacherSubject.click();
        selectWithVisibleText(teacherRole,"TEACHER");

        savingBatch.click();
        batchSaveConfirmation.click();
        batchOk.click();

    }

    public void addingSessionInBatch(long startTime,long endTime,String noOfSessions,String teacherEmailId, String sessionSourceTypeInput) {

        waitForElementToBeClickable(maxSessionCountBy);
        String sessionStartTime = milliesToDateTimeFormat(startTime);
        long oneDayMillis = 24 * 60 * 60 * 1000L;
        String sessionEndTime = milliesToDateTimeFormat(endTime+oneDayMillis);
        maxSessionCount.clear();
        maxSessionCount.sendKeys(noOfSessions);

        String  yearStart = sessionStartTime.substring(0,4);
        String monthStart = sessionStartTime.substring(5,7);
        String dayStart = sessionStartTime.substring(8,10);
        sessionStartDateInput.sendKeys(dayStart+monthStart+yearStart);

        String  yearEnd = sessionEndTime.substring(0,4);
        String monthEnd = sessionEndTime.substring(5,7);
        String dayEnd = sessionEndTime.substring(8,10);
        sessionEndDateInput.sendKeys(dayEnd+monthEnd+yearEnd);

        for (WebElement dayOfWeek : sessionDayOfWeek){
            if (dayOfWeek.getText().equalsIgnoreCase(millistoDayOfWeek(startTime))){
                dayOfWeek.click();
            }
        }

        String startHour = sessionStartTime.substring(11,13);
        String startMin = sessionStartTime.substring(14,16);
        String startAMPM = sessionStartTime.substring(20,22);
        sessionStartTimeInput.sendKeys(startHour+startMin+startAMPM);

        String endHour = sessionEndTime.substring(11,13);
        String endMin = sessionEndTime.substring(14,16);
        String endAMPM = sessionEndTime.substring(20,22);
        sessionEndTimeInput.sendKeys(endHour+endMin+endAMPM);

        //session details adding
        Select sessionTeacher = new Select(sessionTeacherElement);
        sessionTeacher.selectByValue(teacherEmailId);
        sessionGenButton.click();

        sessionTitle.sendKeys("Automation session");
        Select sessionSourceType = new Select(sessionSourceTypes);
        sessionSourceType.selectByVisibleText(sessionSourceTypeInput);
        sessionSaving.click();

        if(topicSelectionError.isDisplayed()){
            topicSelectionErrorAck.click();
            addingTopic.click();
            firstTopic.click();
            topicAddButton.click();
            sessionSaving.click();
        }
        waitForElementToBeVisible(successSessionCreationBy);
        waitForElementToBeClickable(sessionOkBy);
        sessionOk.click();

    }

}


