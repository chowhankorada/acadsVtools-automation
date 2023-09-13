package BatchProgress;

import TestComponents.BaseTest;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.BatchMergePage;
import pages.BatchPage;
import pages.LandingPages;
import pages.VtoolsSignUp;
import utils.dataHelper.ApiService;
import org.hamcrest.Matchers;
import static org.hamcrest.MatcherAssert.assertThat;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

public class BatchMerging extends BaseTest {

    @Test
    public void batchCreation() throws InterruptedException, ParseException {

        String courseId = "63bd88dd5931861672553bd1";
        LandingPages landingPage = new LandingPages(driver);
        VtoolsSignUp adminSignin = landingPage.vtoolsSigninURL();
        adminSignin.signIn("manish.singh@vedantu.com","vedantu123");
        Thread.sleep(5000);
        BatchPage batchCreate = landingPage.batchCreationURL(courseId);
        Thread.sleep(3000);
        String randomNum = random5Digit();
        ApiService apiService = new ApiService();

        // batch creation with sessions and teacher calendar marking
        long currentTimeMillis = System.currentTimeMillis();
        long currentTimeMillisWithExtra2hrs = currentTimeMillis + (2 * 60 * 60 * 1000);
        long currentTimeMillisWithExtra2_30hrs = currentTimeMillis + (2 * 60 * 60 * 1000) + (30 * 60 * 1000);

        batchCreate.batchCreation("10","100","60","parent+"+randomNum,"REGULAR","2023-2024","ENGLISH","STANDARD","LONG TERM","10",1,2,"parentteacher@vedantu.com");
        Map<String, Long> teacher1SlotTimings = apiService.teacher1SlotMarking("parent_teacher", currentTimeMillisWithExtra2hrs, currentTimeMillisWithExtra2_30hrs);
        System.out.println(teacher1SlotTimings);
        String parentBatchId = apiService.batchFetchingWithGroupName(courseId, "parent+" + randomNum);
        landingPage.batchPage(parentBatchId,courseId);
        batchCreate.addingSessionInBatch(teacher1SlotTimings.get("requiredSlotStartTime"),teacher1SlotTimings.get("requiredSlotEndTime"),"1","parentteacher@vedantu.com","REGULAR");

        landingPage.batchCreationURL(courseId);
        batchCreate.batchCreation("11","50","70","child+"+randomNum,"REGULAR","2023-2024","HINDI","STANDARD","LONG TERM","10",2,3,"childteacher@vedantu.com");
        Map<String, Long> teacher2SlotTimings = apiService.teacher1SlotMarking("child_teacher", currentTimeMillisWithExtra2hrs, currentTimeMillisWithExtra2_30hrs);
        String childBatchId = apiService.batchFetchingWithGroupName(courseId,"child+"+randomNum);
        landingPage.batchPage(childBatchId,courseId);
        batchCreate.addingSessionInBatch(teacher2SlotTimings.get("requiredSlotStartTime"),teacher2SlotTimings.get("requiredSlotEndTime"),"1","childteacher@vedantu.com","REGULAR");

        // batch merge flow
        BatchMergePage mergePage = new BatchMergePage(driver);
        mergePage.batchMergePageOpening();
        mergePage.batchsSearchWithCourseId("63bd88dd5931861672553bd1");
        HashMap<String,String> map = mergePage.newParChildBatcheForMerge("parent+"+randomNum,"child+"+randomNum);


        Assert.assertEquals(apiService.batchGroupName(map.get("MergedParentBatchId")),"parent+"+randomNum);

        Assert.assertEquals(apiService.batchGroupName(map.get("MergedChildBatchId")),"child+"+randomNum);
        Assert.assertEquals(apiService.batchWeeklyPlanDay(map.get("MergedChildBatchId")),apiService.batchWeeklyPlanDay(map.get("MergedParentBatchId")));
        Assert.assertEquals(apiService.batchWeeklyPlanHour(map.get("MergedChildBatchId")),apiService.batchWeeklyPlanHour(map.get("MergedParentBatchId")));
        Assert.assertTrue(apiService.batchTeachers(map.get("MergedChildBatchId")).containsAll(apiService.batchTeachers(map.get("MergedParentBatchId"))));



    }

    @Test
    public void teacherCalendarMarking() throws ParseException {
        ApiService apiService = new ApiService();
        long currentTimeMillis = System.currentTimeMillis();
        long currentTimeMillisWithExtra2hrs = currentTimeMillis + (2 * 60 * 60 * 1000);
        long currentTimeMillisWithExtra2_30hrs = currentTimeMillis + (2 * 60 * 60 * 1000) + (30 * 60 * 1000);
        Map<String, Long> requiredTimings = apiService.teacher1SlotMarking("parent_teacher", currentTimeMillisWithExtra2hrs, currentTimeMillisWithExtra2_30hrs);
        System.out.println(requiredTimings);

    }

}
