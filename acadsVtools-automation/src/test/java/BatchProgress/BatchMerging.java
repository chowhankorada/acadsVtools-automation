package BatchProgress;

import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BatchMergePage;
import pages.BatchPage;
import pages.LandingPages;
import pages.VtoolsSignUp;

public class BatchMerging extends BaseTest {

    @Test
    public void batchCreation() throws InterruptedException {


        LandingPages landingPage = new LandingPages(driver);
        VtoolsSignUp adminSignin = landingPage.vtoolsSigninURL();
        adminSignin.signIn("manish.singh@vedantu.com","vedantu123");
        Thread.sleep(2000);
        BatchPage batchCreate = landingPage.batchCreationURL("63bd88dd5931861672553bd1");
        Thread.sleep(3000);
        String randomNum = random5Digit();
        batchCreate.batchCreation("10","100","60","parent+"+randomNum,"REGULAR","2023-2024","ENGLISH","STANDARD","LONG TERM","10",1,2);
        batchCreate.batchCreation("11","50","70","child+"+randomNum,"REGULAR","2023-2024","HINDI","STANDARD","LONG TERM","10",2,3);
        BatchMergePage mergePage = new BatchMergePage(driver);
        mergePage.batchMergePageOpening();
    }

}
