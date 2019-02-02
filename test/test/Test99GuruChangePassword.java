/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.Guru99BalanceEnquiry;
import java.util.Random;
import pages.Guru99ChangePassword;

import pages.Guru99HomePage;
import pages.Guru99Login;
import pages.Guru99FundTransfer;
import pages.Guru99FundTransferDetail;
import static test.Test99GuruFundTransfer.driver;

public class Test99GuruChangePassword {
    
    static WebDriver driver;
    
    static String actualPassword = "colombia2020+";   // ingresar la contraseña actual
    static String newPassword =  "colombia2019+";     //ingresar nueva contraseña
    static String confirmedPassword =  "colombia2019+"; //confirmar nueva contraseña
    
    Guru99Login objLogin;
    Guru99HomePage objHomePage;
    Guru99ChangePassword objChangePassword;
    
    @BeforeClass
    public static void setUpClass() {
        System.setProperty("webdriver.chrome.driver", "Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Before
    public void setUp() {
        driver.get("http://demo.guru99.com/V4/");
    }

    @After
    public void tearDown() {
        driver.quit();
    }
    
    @Test
    public void test_validate_change_pasword() {
        //Create Login Page object
        objLogin = new Guru99Login(driver);
        //login to application valido por 20 dias desde 27/01/2019
        objLogin.loginToGuru99("mngr175690", actualPassword);
        // go the next page
        objHomePage = new Guru99HomePage(driver);
        //Verify home page
        Assert.assertTrue(objHomePage.getHomePageDashboardUserName().toLowerCase().contains("manger id : mngr175690"));
        //Ir a ventana cambiar contraseña     
        objHomePage.goToChangePassword();
        objChangePassword = new Guru99ChangePassword(driver);
        objChangePassword.SubmitChangePassword(actualPassword, newPassword, confirmedPassword);
        String messageAlert = objChangePassword.getMessageAlert();
        
        System.out.println("--------------------MENSAJE DE CONFIRMACION-----------------------------");
        System.out.println(messageAlert);
        System.out.println("-------------------------------------------------------------------------");
        driver.switchTo().alert().accept();
        Assert.assertTrue(messageAlert.toLowerCase().contains("password is changed"));
        //Se valida nuevamente el login y el home
        objLogin.loginToGuru99("mngr175690", newPassword);
        Assert.assertTrue(objHomePage.getHomePageDashboardUserName().toLowerCase().contains("manger id : mngr175690"));    
    }
    
}
