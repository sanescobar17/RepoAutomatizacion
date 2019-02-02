package test;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.Guru99HomePage;
import pages.Guru99Login;
import pages.Guru99FundTransfer;

public class Test99GuruLogin {

    static WebDriver driver;
    
    static String actualPassword = "colombia2020+";
    Guru99Login objLogin;
    Guru99HomePage objHomePage;
    Guru99FundTransfer objFundTransfer;

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

    /**
     *
     * This test case will login in http://demo.guru99.com/V4/
     *
     * Verify login page title as guru99 bank
     *
     * Login to application
     *
     * Verify the home page using Dashboard message
     *
     */

    @Test
    public void test_Home_Page_Appear_Correct() {

     //Create Login Page object
        objLogin = new Guru99Login(driver);

    //Verify login page title
        String loginPageTitle = objLogin.getLoginTitle();
        Assert.assertTrue(loginPageTitle.toLowerCase().contains("guru99 bank"));

    //login to application
        objLogin.loginToGuru99("mngr175690", actualPassword);

    // go the next page
        objHomePage = new Guru99HomePage(driver);

    //Verify home page
        Assert.assertTrue(objHomePage.getHomePageDashboardUserName().toLowerCase().contains("manger id : mngr175690"));
     
    }
}
