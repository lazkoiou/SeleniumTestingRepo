package com.herokuappwebsite.tests;

import com.herokuappwebsite.pages.AddRemoveElementsPage;
import com.herokuappwebsite.pages.BasicAuthPage;
import com.herokuappwebsite.pages.CommonUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;

import static com.herokuappwebsite.pages.AddRemoveElementsPage.*;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Add Remove Elements Tests")
public class HerokuAppTests {

    public static WebDriver driver = null;

    @BeforeAll
    static void setup() {
        System.out.println("\nStarting HerokuAppTests...");
        System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver2.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterAll
    static void tearDown() {
        driver.close();
        driver.quit();
        System.out.println("\nFinished HerokuAppTests ...");
    }

    @BeforeEach
    void beforeEach() {
        // go to the start page
        driver.navigate().to("http://the-internet.herokuapp.com/");
        String title = driver.getTitle();
        assertEquals("The Internet", title);
    }

    @Test
    @DisplayName("AddRemoveElementTest")
    void addRemoveElementTest() {
        // open to the desired link
        CommonUtils.openLink(driver, "Add/Remove Elements");

        // create element and assert created
        AddRemoveElementsPage addRemoveElementsPage = new AddRemoveElementsPage(driver);
        addRemoveElementsPage.createElement();
        assertEquals("Delete", addRemoveElementsPage.checkIfExistingElements());

        // delete element and assert deleted
        addRemoveElementsPage.deleteElement();
        assertEquals("NoSuchElementException", checkIfDeleted(driver));
    }

    @Test
    @DisplayName("BasicAuthTest")
    void basicAuthTest() throws InterruptedException, AWTException {
        // send the username and password in the header to access the page
        driver.navigate().to("http://admin:admin@the-internet.herokuapp.com/basic_auth");
        BasicAuthPage basicAuthPage = new BasicAuthPage(driver);
        assertTrue(basicAuthPage.getPageText().contains(
                "Congratulations! You must have the proper credentials"
        ));

    }
}
