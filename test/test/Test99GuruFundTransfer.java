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

import pages.Guru99HomePage;
import pages.Guru99Login;
import pages.Guru99FundTransfer;
import pages.Guru99FundTransferDetail;

public class Test99GuruFundTransfer {

    static WebDriver driver;
    static String actualPassword = "colombia2020+";
    Guru99Login objLogin;
    Guru99HomePage objHomePage;
    Guru99FundTransfer objFundTransfer;
    Guru99BalanceEnquiry objBalanceEnquiry;
    Guru99FundTransferDetail objFundTransferDetail;

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
    public void test_Fund_Transfer_validate_field() {
        //Create Login Page object
        objLogin = new Guru99Login(driver);
        //login to application valido por 20 dias desde 27/01/2019
        objLogin.loginToGuru99("mngr175690", actualPassword);
        // go the next page
        objHomePage = new Guru99HomePage(driver);
        //Verify home page
        Assert.assertTrue(objHomePage.getHomePageDashboardUserName().toLowerCase().contains("manger id : mngr175690"));
        //Validar campo Payees account no      
        objHomePage.goToFundTransfer(); // Va a la 
        objFundTransfer = new Guru99FundTransfer(driver);
        //Otra cuenta es 55545
        objFundTransfer.submitTransfer("55544", "", "50000", "pago 3");
        //Acept alert
        //MODIFICACION 2
        String mensajeValidacion = "";
        if (isAlertPresent()) {
            driver.switchTo().alert().accept();
            mensajeValidacion = objFundTransfer.getMessage();
            //String mensajeBtn = objFundTransfer.getReponseBtn();
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println(mensajeValidacion);
            //System.out.println(mensajeBtn);
            System.out.println("---------------------------------------------------------------------------------------");
        }
        Assert.assertTrue(!mensajeValidacion.toLowerCase().contains("payees account number must not be blank"));
    }
   
    @Test
    public void test_Fund_Transfer_validate_balance() {

        //Create Login Page object
        objLogin = new Guru99Login(driver);
        //login to application valido por 20 dias desde 27/01/2019
        objLogin.loginToGuru99("mngr175690", actualPassword);
        // go the next page
        objHomePage = new Guru99HomePage(driver);
        //Verify home page
        Assert.assertTrue(objHomePage.getHomePageDashboardUserName().toLowerCase().contains("manger id : mngr175690"));

        objHomePage.goTobalanceEnquiry();
        
        String PayersAccountNo = "55546";
        String PayeesAccountNo = "55547";
        
        objBalanceEnquiry = new Guru99BalanceEnquiry(driver);
        //Validacion de que las cuentas existen y consulta de saldos

        double balancePayeesAccount = 0;
        double balancePayersAccount = 0;
        //PayersAccount
        objBalanceEnquiry.checkBalance(PayersAccountNo);
        Assert.assertTrue(!isAlertPresent());
        if (!isAlertPresent()) {
            balancePayersAccount = Double.parseDouble(objBalanceEnquiry.getBalance());
            System.out.println("Saldo actual Payees Account: " + balancePayersAccount);
        }
        objBalanceEnquiry.goTobalanceEnquiry();
        //PayeesAccount
        objBalanceEnquiry.checkBalance(PayeesAccountNo);
        Assert.assertTrue(!isAlertPresent());
        if (!isAlertPresent()) {
            balancePayeesAccount = Double.parseDouble(objBalanceEnquiry.getBalance());
            System.out.println("Saldo actual Payees Account: " + balancePayeesAccount);
        }

        //View Fund Fransfer
        objBalanceEnquiry.goToFundTransfer();
        objFundTransfer = new Guru99FundTransfer(driver);
        //Transferencia
        Random rnd = new Random();
        objFundTransfer.submitTransfer(PayersAccountNo, PayeesAccountNo, "50000", "Transfer" + rnd.nextInt());

        Assert.assertTrue(!isAlertPresent());
        if (!isAlertPresent()) {
            
            objFundTransferDetail = new Guru99FundTransferDetail(driver);
            double amount = Double.parseDouble(objFundTransferDetail.getAmount()); 
            //PayersAccount
            objFundTransferDetail.goTobalanceEnquiry();
            objBalanceEnquiry.checkBalance(PayersAccountNo);
            double balancePayersAccountAfter = Double.parseDouble(objBalanceEnquiry.getBalance());
            //Vuelve a la vista principal del modulo          
            objBalanceEnquiry.goTobalanceEnquiry();
            //PayeesAccount
            objBalanceEnquiry.checkBalance(PayeesAccountNo);
            double balancePayeesAccountAfter = Double.parseDouble(objBalanceEnquiry.getBalance());
            //Validadciones por separado
            //Validacion Payers
            Assert.assertTrue(balancePayersAccount-amount==balancePayersAccountAfter);
            //Validacion Payees
            Assert.assertTrue(balancePayeesAccount+amount==balancePayeesAccountAfter);
            //Assert.assertTrue(balancePayersAccount-amount==balancePayersAccountAfter&&balancePayeesAccount+amount==balancePayeesAccountAfter);
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println("balancePayersAccount after transfer: " + balancePayersAccountAfter);  
            System.out.println("balancePayeesAccountAfter after transfer: " + balancePayeesAccountAfter); 
            System.out.println("---------------------------------------------------------------------------------------");
        }
    }

    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } // try 
        catch (NoAlertPresentException Ex) {
            return false;
        }   // catch 
    }
}
