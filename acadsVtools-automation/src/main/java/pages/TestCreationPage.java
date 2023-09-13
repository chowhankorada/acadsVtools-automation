package pages;

import library.BaseLibrary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class TestCreationPage extends BaseLibrary {
    WebDriver driver;
    public TestCreationPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void createTest(){

    }

}
