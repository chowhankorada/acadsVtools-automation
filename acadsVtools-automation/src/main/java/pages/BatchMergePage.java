package pages;

import library.BaseLibrary;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BatchMergePage extends BaseLibrary {

    WebDriver driver;

    public BatchMergePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[normalize-space()='Batch Merge']")
    WebElement batchMergeCTA;
    By batchMergeCTABy = By.xpath("//a[normalize-space()='Batch Merge']");
    @FindBy(xpath = "//a[contains(text(),'One To Few')]")
    WebElement ontToFewTab;
    @FindBy(xpath = "//input[@id='course-fetch']")
    WebElement reqCourseId;
    @FindBy(xpath = "//button[normalize-space()='Fetch Batches']")
    WebElement fetchBatches;
    //    @FindBy(xpath = "//thead/tr/th")
//    List<WebElement> groupNameHeading;
//    @FindBy(xpath = "//tbody/tr/td")
//    List<WebElement> allGroupNames;
    @FindBy(xpath = "//label[contains(text(),'New-Parent')]")
    WebElement newParent;
    @FindBy(xpath = "//th[contains(text(),'Master Batch')]")
    WebElement masterBatch;
    By masterBatchBy = By.xpath("//th[contains(text(),'Master Batch')]");
    @FindBy(xpath = "//button[contains(text(),'Merged Batches')]")
    WebElement mergedBatchButton;
    @FindBy(xpath = "//button[contains(@class,'btn')] [contains(text(),'Yes')]")
    WebElement batchMergeConfirmButton;


    public void batchMergePageOpening() {
        mouseHover(ontToFewTab);
        waitForElementToBeVisible(batchMergeCTABy);
        batchMergeCTA.click();
    }

    public void batchsSearchWithCourseId(String courseId) {
        reqCourseId.sendKeys(courseId);
        fetchBatches.click();

    }



    public void newParChildBatcheForMerge(String ParentBatchName, String ChildBatchName) {
        newParent.click();
        waitForElementToBeVisible(masterBatchBy);
        List<WebElement> allHeadingOfTable = driver.findElements(By.xpath("//thead/tr/th"));
//        int reqGroupParentNameRow = 0;
//        int reqGroupChildNameRow = 0;
        List<Integer> reqRows = new ArrayList<>();
        for (int i = 0; i < allHeadingOfTable.size(); i++) {
            String eachTabName = allHeadingOfTable.get(i).getText();
            int k = i + 1;
            if (eachTabName.equals("groupName")) {
                List<WebElement> allBatchNames = driver.findElements(By.xpath("//tbody/tr/td[" + k + "]"));
                for (int batchNameColumnIndex = 0; batchNameColumnIndex < allBatchNames.size(); batchNameColumnIndex++) {
                    String allName = allBatchNames.get(batchNameColumnIndex).getText();
                    if (allName.equals(ParentBatchName)) {
                        reqRows.add(batchNameColumnIndex);

                        for (int masterBatchRowIndex = 0; masterBatchRowIndex < allHeadingOfTable.size(); masterBatchRowIndex ++) {
                            int masterBatchRow = masterBatchRowIndex + 1;
                            int reqParentColumn = batchNameColumnIndex + 1;
                            if (allHeadingOfTable.get(masterBatchRowIndex).getText().equals("Master Batch")) {
                                driver.findElement(By.xpath("//tbody/tr["+reqParentColumn+"]/td["+masterBatchRow+"]/input")).click();
                                break;
                            }
                        }

                    }
                    if (allName.equals(ChildBatchName)) {
                        reqRows.add(batchNameColumnIndex);
                    }
                }
            }
        }

        for (int actionRowIndex = 0; actionRowIndex < allHeadingOfTable.size(); actionRowIndex++) {
            String eachTab = allHeadingOfTable.get(actionRowIndex).getText();

            if (eachTab.equals("Action")) {
                int reqActionRow = actionRowIndex + 1;

                List<WebElement> allActionButtons = driver.findElements(By.xpath("//tbody/tr/td[" + reqActionRow + "]/a"));
                // WebElement actionReq = driver.findElement(By.xpath("//tbody/tr["+reqGroupParentNameRow+"]/td["+n+"]"));


                for (int actionButtonIndex = allActionButtons.size() - 1; actionButtonIndex >= 0; actionButtonIndex--) {
                    if (!reqRows.contains(actionButtonIndex)) {
                        System.out.println(actionButtonIndex);
                        try {
                            System.out.println(allActionButtons.get(actionButtonIndex));
                            allActionButtons.get(actionButtonIndex).click();
                        } catch (Exception e) {

                        }
                        //
                    }
                }

            }
        }

        mergedBatchButton.click();
        batchMergeConfirmButton.click();
    }

}
