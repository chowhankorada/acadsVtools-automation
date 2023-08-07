package pages;

import library.BaseLibrary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LandingPages extends BaseLibrary {

    WebDriver driver;
    public  LandingPages(WebDriver driver ){

        super(driver);
        this.driver= driver;
        PageFactory.initElements(driver ,this);

    }

    public BatchPage batchCreationURL(String courseId){

        driver.navigate().to("https://tools.vedantuint.net/tools#/otf/"+courseId+"/batches/");
        return new BatchPage(driver);

    }

    public VtoolsSignUp vtoolsSigninURL(){
        driver.navigate().to("https://www.vedantuint.net");
        return new VtoolsSignUp(driver);
    }
}
