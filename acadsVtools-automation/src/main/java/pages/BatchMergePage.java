package pages;

import library.BaseLibrary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BatchMergePage extends BaseLibrary {

    WebDriver driver;

    public BatchMergePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//a[normalize-space()='Batch Merge']")
    WebElement batchMergeCTA;
    @FindBy(xpath = "//a[contains(text(),'One To Few')]")
    WebElement ontToFewTab;
    @FindBy(xpath = "//input[@id='course-fetch']")
    WebElement reqCourseId;
    @FindBy(xpath = "//button[normalize-space()='Fetch Batches']")
    WebElement fetchBatches;


    public void batchMergePageOpening(){
        mouseHover(ontToFewTab);
        batchMergeCTA.click();
    }

    public  void batchsSearchWithCourseId(String courseId){
        reqCourseId.sendKeys(courseId);
        fetchBatches.click();

    }
}
