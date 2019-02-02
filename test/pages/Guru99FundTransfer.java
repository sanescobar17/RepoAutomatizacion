
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Guru99FundTransfer {
    
    WebDriver driver;

    By payersAccountNoOrigen = By.name("payersaccount");
    By payeesAccountNodestino = By.name("payeeaccount");
    By amount = By.name("ammount");
    By description = By.name("desc");  
    By btnSumit = By.name("AccSubmit");
    By message = By.id("message11");//By.xpath("//*[@id=\"message11\"]");
    By balanceEnquiryBtn = By.xpath("/html/body/div[3]/div/ul/li[12]/a");
    
    
   
    public Guru99FundTransfer(WebDriver driver) {
        this.driver = driver;
    }

    public void setPayersAccountNoOrigen(String strPayersAccountNoOrigen) {
        driver.findElement(payersAccountNoOrigen).sendKeys(strPayersAccountNoOrigen);    
    }

    public void setPayeesAccountNodestino(String strPayeesAccountNodestino) {
        driver.findElement(payeesAccountNodestino).sendKeys(strPayeesAccountNodestino);   
    }

    public void setAmount(String strAmount) {
        driver.findElement(amount).sendKeys(strAmount); 
    }

    public void setDescription(String strDescription) {
        driver.findElement(description).sendKeys(strDescription); 
    }

    public void clickSumit() {
         driver.findElement(btnSumit).click();
    }
    
    public String getMessage(){
     return driver.findElement(message).getText();
    }
    
    public String getReponseBtn(){
     return driver.findElement(btnSumit).getText();
    }
    
    public void goTobalanceEnquiry() {
        driver.findElement(balanceEnquiryBtn).click();
    }
    
     public void submitTransfer(String strPayersAccountNoOrigen,String strPayeesAccountNodestino, String strAmount, String strDescription){
        this.setPayersAccountNoOrigen(strPayersAccountNoOrigen);
        this.setPayeesAccountNodestino(strPayeesAccountNodestino);
        this.setAmount(strAmount);
        this.setDescription(strDescription);
        this.clickSumit();        
    }    
}
