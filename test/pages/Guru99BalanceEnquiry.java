
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Guru99BalanceEnquiry {
    
    WebDriver driver;

    By accountNo = By.name("accountno");
    By btnSumit = By.name("AccSubmit");
    By balance = By.xpath("//*[@id=\"balenquiry\"]/tbody/tr[16]/td[2]");
    By fundTransferBtn =  By.xpath("/html/body/div[3]/div/ul/li[10]/a");
    By balanceEnquiryBtn = By.xpath("/html/body/div[3]/div/ul/li[12]/a");
    
    public Guru99BalanceEnquiry(WebDriver driver) {
        this.driver = driver;
    }
        
    public void clickSubit(){
        driver.findElement(btnSumit).click();
    }

    public void setAccountNo(String strAccountNo) {
         driver.findElement(accountNo).sendKeys(strAccountNo);   
    }
  
    public String getBalance(){
     return driver.findElement(balance).getText();
    }
    
    public void checkBalance(String strAccountNo){   
        this.setAccountNo(strAccountNo);
        this.clickSubit();   
    }

    public void goToFundTransfer() {
        driver.findElement(fundTransferBtn).click();
    }
    
    public void goTobalanceEnquiry() {
        driver.findElement(balanceEnquiryBtn).click();
    }
}
