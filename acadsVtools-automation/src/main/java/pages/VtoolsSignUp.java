package pages;

import library.BaseLibrary;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VtoolsSignUp extends BaseLibrary {


    WebDriver driver;
    public VtoolsSignUp(WebDriver driver) {
        super(driver);
        this.driver= driver;
        PageFactory.initElements(driver ,this);
    }

    @FindBy(xpath = "(//button[contains(text(),'Sign in')])[2]")
    WebElement signInButton;
    By signinButtonBy = By.xpath("(//button[contains(text(),'Sign in')])[2]");
    @FindBy(xpath = "//div[@id='login-email-phone']//input[contains(@type,'text')]")
    WebElement emailInput;
    @FindBy(xpath = "//input[@id='login-submit3']")
    WebElement emailNextButoon;
    @FindBy(xpath = "//input[@id='password-input-field']")
    WebElement passwordInput;
    By passwordInputBy = By.xpath("//input[@id='password-input-field']");
    @FindBy(xpath = "//input[@id='login-submit2']")
    WebElement passwordNextButton;



    public void signIn(String adminEmail, String adminPassword){
        waitForElementToBeClickable(signinButtonBy);
        signInButton.click();
        emailInput.sendKeys(adminEmail);
        emailNextButoon.click();
        waitForElementToBeVisible(passwordInputBy);
        passwordInput.sendKeys(adminPassword);
        passwordNextButton.click();

    }

}

