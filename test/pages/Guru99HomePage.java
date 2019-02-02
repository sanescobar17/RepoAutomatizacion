package pages;

import javax.xml.xpath.XPath;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

public class Guru99HomePage {

    WebDriver driver;

    By homePageUserName = By.xpath("//table//tr[@class='heading3']");
    By fundTransferBtn =  By.xpath("/html/body/div[3]/div/ul/li[10]/a");
    By balanceEnquiryBtn = By.xpath("/html/body/div[3]/div/ul/li[12]/a");
    By changePasswordBtn = By.xpath("/html/body/div[3]/div/ul/li[11]/a");
    
    
    public Guru99HomePage(WebDriver driver){
        this.driver = driver;
    }

    //Get the User name from Home Page
    public String getHomePageDashboardUserName() {
        return driver.findElement(homePageUserName).getText();
    }

    public void goToFundTransfer() {
        driver.findElement(fundTransferBtn).click();
    }

    public void goTobalanceEnquiry() {
        driver.findElement(balanceEnquiryBtn).click();
    }
    
    public void goToChangePassword() {
        driver.findElement(changePasswordBtn).click();
    }
    
    
}