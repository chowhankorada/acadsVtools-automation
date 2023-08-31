package pages;

import library.BaseLibrary;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.HashMap;
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


    public HashMap<String, String> newParChildBatcheForMerge(String ParentBatchName, String ChildBatchName) {
        newParent.click();
        waitForElementToBeVisible(masterBatchBy);
        List<WebElement> allHeadingOfTable = driver.findElements(By.xpath("//thead/tr/th"));

        // Below list stores the required row of parent and child
        List<Integer> reqRows = new ArrayList<>();
        // Below Map stores the parent and child child batch ids
        HashMap<String, String> mergedBatchIds = new HashMap<>();

        // Loop for fetching the required table heading
        for (int i = 0; i < allHeadingOfTable.size(); i++) {
            String eachTabName = allHeadingOfTable.get(i).getText();
            int k = i + 1;
            if (eachTabName.equals("groupName")) {
                List<WebElement> allBatchNames = driver.findElements(By.xpath("//tbody/tr/td[" + k + "]"));

                //loop for fetching batch names both parent and child
                for (int batchNameColumnIndex = 0; batchNameColumnIndex < allBatchNames.size(); batchNameColumnIndex++) {
                    String allName = allBatchNames.get(batchNameColumnIndex).getText();
                    //Fetching required parent batch row
                    if (allName.equals(ParentBatchName)) {
                        reqRows.add(batchNameColumnIndex);

                        // loop for selecting parent batch for merge
                        for (int masterBatchRowIndex = 0; masterBatchRowIndex < allHeadingOfTable.size(); masterBatchRowIndex++) {
                            int masterBatchRow = masterBatchRowIndex + 1;
                            int reqParentColumn = batchNameColumnIndex + 1;
                            if (allHeadingOfTable.get(masterBatchRowIndex).getText().equals("Master Batch")) {
                                driver.findElement(By.xpath("//tbody/tr[" + reqParentColumn + "]/td[" + masterBatchRow + "]/input")).click();
                                break;
                            }
                        }

                        //loop for fetching parent batch id in the hashmap
                        for (int parentBatchIdRowIndex = 0; parentBatchIdRowIndex < allHeadingOfTable.size(); parentBatchIdRowIndex++) {
                            int parentBatchIdRow = parentBatchIdRowIndex + 1;
                            int reqParentBatchIdColumn = batchNameColumnIndex + 1;
                            if (allHeadingOfTable.get(parentBatchIdRowIndex).getText().equals("Batch Id")) {
                                String parentBatchId = driver.findElement(By.xpath("//tbody/tr[" + reqParentBatchIdColumn + "]/td[" + parentBatchIdRow + "]")).getText();
                                mergedBatchIds.put("MergedParentBatchId", parentBatchId);
                                break;
                            }
                        }

                    }
                    //Fetching required child batch row
                    if (allName.equals(ChildBatchName)) {
                        reqRows.add(batchNameColumnIndex);

                        //loop for fetching child batch id in the hashmap
                        for (int childBatchIdRowIndex = 0; childBatchIdRowIndex < allHeadingOfTable.size(); childBatchIdRowIndex++) {
                            int childBatchIdRow = childBatchIdRowIndex + 1;
                            int reqChildBatchIdColumn = batchNameColumnIndex + 1;
                            if (allHeadingOfTable.get(childBatchIdRowIndex).getText().equals("Batch Id")) {
                                String childBatchId = driver.findElement(By.xpath("//tbody/tr[" + reqChildBatchIdColumn + "]/td[" + childBatchIdRow + "]")).getText();
                                mergedBatchIds.put("MergedChildBatchId", childBatchId);
                                break;
                            }

                        }
                    }
                }
            }
        }

        //loop for Action coloumn from the table
        for (int actionRowIndex = 0; actionRowIndex < allHeadingOfTable.size(); actionRowIndex++) {
            String eachTab = allHeadingOfTable.get(actionRowIndex).getText();

            if (eachTab.equals("Action")) {
                int reqActionRow = actionRowIndex + 1;

                List<WebElement> allActionButtons = driver.findElements(By.xpath("//tbody/tr/td[" + reqActionRow + "]/a"));
                // WebElement actionReq = driver.findElement(By.xpath("//tbody/tr["+reqGroupParentNameRow+"]/td["+n+"]"));


                //loop for removing the all batches except parent and child
                for (int actionButtonIndex = allActionButtons.size() - 1; actionButtonIndex >= 0; actionButtonIndex--) {
                    if (!reqRows.contains(actionButtonIndex)) {
                        try {
                            allActionButtons.get(actionButtonIndex).click();
                        } catch (Exception e) {

                        }

                    }
                }

            }
        }

        mergedBatchButton.click();
        batchMergeConfirmButton.click();
        return mergedBatchIds;

    }

    public void fetchingBatchData(){
        HashMap<String , String > batch = new HashMap<>();

    }

}
