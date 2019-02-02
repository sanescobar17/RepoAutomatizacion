/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Guru99FundTransferDetail {

    WebDriver driver;
    By balanceEnquiryBtn = By.xpath("/html/body/div[3]/div/ul/li[12]/a");
    By amount = By.xpath("/html/body/table/tbody/tr[2]/td/table/tbody/tr[3]/td[2]");

    public Guru99FundTransferDetail(WebDriver driver) {
        this.driver = driver;
    }
    
    public String getAmount(){
     return driver.findElement(amount).getText();
    }
    
    public void goTobalanceEnquiry() {
        driver.findElement(balanceEnquiryBtn).click();
    }
}
