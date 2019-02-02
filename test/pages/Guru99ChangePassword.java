/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Guru99ChangePassword {
    
    WebDriver driver;
    
    By oldPassword = By.name("oldpassword");
    By newPassword = By.name("newpassword");
    By confirmPassword = By.name("confirmpassword");
    By btnSubmit = By.name("sub");
    
    public Guru99ChangePassword(WebDriver driver) {
        this.driver = driver;
    }
    
    public void setOldPassword(String strOldPassword) {
        driver.findElement(oldPassword).sendKeys(strOldPassword);        
    }
    
    public void setNewPassword(String strNewPassword) {
        driver.findElement(newPassword).sendKeys(strNewPassword);        
    }
    
    public void setConfirmPassword(String strConfirmPassword) {
        driver.findElement(confirmPassword).sendKeys(strConfirmPassword);        
    }
    
    public void clickSubmit() {
        driver.findElement(btnSubmit).click();
    }
    
    public String getMessageAlert(){
        return driver.switchTo().alert().getText();	  
    }
    
    
    public void SubmitChangePassword(String strOldPassword, String strNewPassword, String strConfirmPassword) {
        
        this.setOldPassword(strOldPassword);
        this.setNewPassword(strNewPassword);
        this.setConfirmPassword(strConfirmPassword);
        this.clickSubmit();
    }
    
}
