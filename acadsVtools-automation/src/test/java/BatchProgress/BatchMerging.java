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

public class BatchMerging extends BaseTest {

    @Test
    public void batchCreation() throws InterruptedException, ParseException {


        LandingPages landingPage = new LandingPages(driver);
        VtoolsSignUp adminSignin = landingPage.vtoolsSigninURL();
        adminSignin.signIn("manish.singh@vedantu.com","vedantu123");
        Thread.sleep(5000);
        BatchPage batchCreate = landingPage.batchCreationURL("63bd88dd5931861672553bd1");
        Thread.sleep(3000);
        String randomNum = random5Digit();
        batchCreate.batchCreation("10","100","60","parent+"+randomNum,"REGULAR","2023-2024","ENGLISH","STANDARD","LONG TERM","10",1,2,"parentteacher@vedantu.com");
        batchCreate.batchCreation("11","50","70","child+"+randomNum,"REGULAR","2023-2024","HINDI","STANDARD","LONG TERM","10",2,3,"childteacher@vedantu.com");
        BatchMergePage mergePage = new BatchMergePage(driver);
        mergePage.batchMergePageOpening();
        mergePage.batchsSearchWithCourseId("63bd88dd5931861672553bd1");
        HashMap<String,String> map = mergePage.newParChildBatcheForMerge("parent+"+randomNum,"child+"+randomNum);

        ApiService batchMatching = new ApiService();
        Assert.assertEquals(batchMatching.batchGroupName(map.get("MergedParentBatchId")),"parent+"+randomNum);

        Assert.assertEquals(batchMatching.batchGroupName(map.get("MergedChildBatchId")),"child+"+randomNum);
        Assert.assertEquals(batchMatching.batchWeeklyPlanDay(map.get("MergedChildBatchId")),batchMatching.batchWeeklyPlanDay(map.get("MergedParentBatchId")));
        Assert.assertEquals(batchMatching.batchWeeklyPlanHour(map.get("MergedChildBatchId")),batchMatching.batchWeeklyPlanHour(map.get("MergedParentBatchId")));
        Assert.assertTrue(batchMatching.batchTeachers(map.get("MergedChildBatchId")).containsAll(batchMatching.batchTeachers(map.get("MergedParentBatchId"))));


    }

}
