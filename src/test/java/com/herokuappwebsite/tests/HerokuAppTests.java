package com.herokuappwebsite.tests;

import com.herokuappwebsite.pages.*;
import com.herokuappwebsite.utils.CommonUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.herokuappwebsite.pages.AddRemoveElementsPage.*;
import static org.junit.jupiter.api.Assertions.*;

// TODO: change chromedriver.exe and delete the other drivers

@DisplayName("HerokuApp Tests")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HerokuAppTests {

    public static WebDriver driver = null;

    @BeforeAll
    static void setup() {
        System.out.println("\nStarting HerokuAppTests...");
        System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver2.exe");

        // chrome options that are necessary to setup for the fileDownloadTest
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.default_directory", System.getProperty("user.dir") + "\\resources\\");
        prefs.put("plugins.always_open_pdf_externally", true);
        options.setExperimentalOption("prefs", prefs);
        // if we need a faster run of the tests we addArguments
        // "--headless", "--disable-gpu"
        // to save resources
        options.addArguments("start-maximized"); //

        driver = new ChromeDriver(options);
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


    /**
     * Level: Beginner
     * Add or Remove elements by clicking on boxes
     */
    @Test
    @DisplayName("Add/Remove Element Test")
    @Order(1)
    @Disabled
    void addRemoveElementTest() {
        System.out.println("addRemoveElementTest...");
        // open to the desired link
        CommonUtils.openLink(driver, "Add/Remove Elements");

        // create element and assert created
        AddRemoveElementsPage addRemoveElementsPage = new AddRemoveElementsPage(driver);
        addRemoveElementsPage.createElement();
        assertEquals("Delete", addRemoveElementsPage.checkIfExistingElements());

        // delete element and assert deleted
        addRemoveElementsPage.deleteElement();
        assertEquals("NoSuchElementException", checkIfDeleted(driver));
        System.out.println("addRemoveElementTest ...");
    }

    /**
     *  Level: Beginner
     *  Basic HTTP Authentication
     *  In order to access you'll need to pass credentials to the site when requesting a page
     *  This can be achieved by specifying the username and password in the URL
     */
    @Test
    @DisplayName("Basic Auth Test")
    @Order(1)
    @Disabled
    void basicAuthTest() {
        System.out.println("basicAuthTest...");
        // send the username and password in the header to access the page
        driver.navigate().to("http://admin:admin@the-internet.herokuapp.com/basic_auth");
        BasicAuthPage basicAuthPage = new BasicAuthPage(driver);
        assertTrue(basicAuthPage.getPageText().contains(
                "Congratulations! You must have the proper credentials"
        ));
        System.out.println("basicAuthTest ...");
    }

    /**
     * Level: Beginner
     * When trying to automate uploading a file you get prompted with a dialog box that is out of reach for Selenium.
     * A work-around for this is to side-step the system dialog box by inserting the full path of the file we want
     * to upload (as text) into the form and then submit the form.
     */
    @Test
    @DisplayName("Upload File Test")
    @Order(1)
    @Disabled
    void uploadFileTest() {
        System.out.println("uploadFileTest...");
        CommonUtils.openLink(driver, "File Upload");
        FileUploaderPage fileUploaderPage = new FileUploaderPage(driver);

        File file = new File(".\\resources\\testfile.txt");
        System.out.println("File to be uploaded: " + file.getName() +
                " from path: " + file.getAbsolutePath());

        // send the file's absolute path
        assertEquals("testfile.txt", fileUploaderPage.uploadAFile(file.getAbsolutePath()));

        System.out.println("uploadFileTest ...");
    }

    /**
     * Level: Beginner
     * Easily work with the elements in a frame by telling Selenium to switch to that frame first
     */
    @Test
    @DisplayName("Nested Frames Test")
    @Order(1)
    @Disabled
    void nestedFramesTest() {
        System.out.println("nestedFramesTest...");
        CommonUtils.openLink(driver, "Nested Frames");
        NestedFramesPage nestedFramesPage = new NestedFramesPage(driver);

        // switch to each frame and verify the name
        assertEquals("LEFT", nestedFramesPage.switchToTopFrames("frame-left"));
        assertEquals("MIDDLE", nestedFramesPage.switchToTopFrames("frame-middle"));
        assertEquals("RIGHT", nestedFramesPage.switchToTopFrames("frame-right"));
        assertEquals("BOTTOM", nestedFramesPage.switchToBottomFrame("frame-bottom"));

        System.out.println("nestedFramesTest ...");
    }

    /**
     * Level: Beginner
     * Grab the list by it's element and select an item within it based on the text you want.
     * While it sounds pretty straightforward, there is a bit more finesse to it.
     */
    @Test
    @DisplayName("Drop Down List Test")
    @Order(1)
    @Disabled
    void dropDownListTest() {
        System.out.println("dropDownListTest...");
        CommonUtils.openLink(driver, "Dropdown");
        DropdownPage dropdownPage = new DropdownPage(driver);

        // 1st way - select option and assert
        assertEquals("Option 1", dropdownPage.selectOptionByClick("Option 1"));
        assertEquals("Option 2", dropdownPage.selectOptionByClick("Option 2"));

        // 2nd way - select option and assert
        assertEquals("Option 1", dropdownPage.selectOptionBySelect("1"));
        assertEquals("Option 2", dropdownPage.selectOptionBySelect("2"));

        System.out.println("dropDownListTest ...");
    }

    /**
     * Level: Intermediate
     * The application of testing opens a new window. In order to work with both the new and originating windows
     * we need to switch between them. It is a straightforward concept, but watch out for that it might work
     * in some browsers and not others.
     */
    @Test
    @DisplayName("Multiple Windows Test")
    @Order(2)
    @Disabled
    void multipleWindowsTest() {
        System.out.println("multipleWindowsTest...");
        CommonUtils.openLink(driver, "Multiple Windows");
        MultipleWindowsPage multipleWindowsPage = new MultipleWindowsPage(driver);

        // save the initial window handle and open a new one
        multipleWindowsPage.saveInitWindowHandle();
        multipleWindowsPage.openWindow();

        // select the new window and assert
        assertEquals("New Window", multipleWindowsPage.selectNewlyOpenedWindow());

        // select the initial window and assert
        assertEquals("The Internet", multipleWindowsPage.selectInitWindow());

        System.out.println("multipleWindowsTest ...");
    }

    /**
     * Level: Intermediate
     * Just like with uploading files we hit the same issue with downloading them. A dialog box out
     * of Selenium's reach. With some configuration when setting up the Selenium driver we can side-step the dialog box.
     * This is done by instructing the browser to download files to a specific location without being prompted.
     */
    @Test
    @DisplayName("Download File Test")
    @Order(2)
    @Disabled
    void downloadFileTest() throws InterruptedException {
        System.out.println("downloadFileTest...");
        CommonUtils.openLink(driver, "File Download");
        FileDownloaderPage fileDownloaderPage = new FileDownloaderPage(driver);

        // download file and assert it exists
        assertTrue(fileDownloaderPage.downloadFile());

        // giving it some time to visually confirm that the download is successful
        Thread.sleep(300);

        // cleanup - delete the downloaded file
        assertTrue(fileDownloaderPage.deleteFile());

        System.out.println("downloadFileTest ...");
    }

    /**
     * Level: Intermediate
     * Split testing is a simple way to experiment with an application's features
     * (how the application looks and behaves or changes that could include changing text
     * or images on the page, element positioning, color of the submit button, etc).
     */
    @Test
    @DisplayName("A/B Testing")
    @Order(2)
    @Disabled
    void abTest() {
        System.out.println("abTest...");
        // open to the desired link
        CommonUtils.openLink(driver, "A/B Testing");
        ABTestingPage abTestingPage = new ABTestingPage(driver);

        // 1st way
        // delete cookies to ensure there are none
        abTestingPage.deleteCookies();
        assertTrue(
                abTestingPage.getPagetitle().contains("A/B Test Control") ||
                abTestingPage.getPagetitle().contains("A/B Test Variation 1"));

        // add cookie and refresh to get another version of the page
        abTestingPage.addOptOutCookie();
        abTestingPage.refresh();
        assertEquals("No A/B Test", abTestingPage.getPagetitle());


        // 2nd way
        // delete cookies to ensure there are none and refresh
        abTestingPage.deleteCookies();
        abTestingPage.refresh();
        assertTrue(
                abTestingPage.getPagetitle().contains("A/B Test Control") ||
                        abTestingPage.getPagetitle().contains("A/B Test Variation 1"));

        // add optOut request to header and close alert opened
        abTestingPage.addOptOutRequest();
        assertEquals("No A/B Test", abTestingPage.getPagetitle());
        System.out.println("abTest ...");
    }

    /**
     * Level: Intermediate
     * Retry Actions
     * Tests might occasionally fail when the application is dependent upon third-party service providers
     * (e.g. payment providers, social networks, etc). Rather than have the tests fail for reasons that
     * don't reflect an issue, we trigger a context specific retry for a specific set of actions (rather
     * than the whole test) that will back-off after a few attempts.
     *
     * If it finds what it needs, the tests passes. If it doesn't, the test fails there is a legitimate problem
     * in the application.
     */
    @Test
    @DisplayName("Retry Actions Test")
    @Order(2)
    @Disabled
    public void retryActionsTest() {
        System.out.println("retryActionsTest...");
        CommonUtils.openLink(driver, "Notification Messages");
        NotificationMessagesPage notificationMessagesPage = new NotificationMessagesPage(driver);

        // retries up until maxTimes to check if the success notification appears
        assertTrue(notificationMessagesPage.retryUntilSuccessOrMaxTimes(3));

        System.out.println("retryActionsTest ...");
    }

    /**
     *  Level: Intermediate
     *  A simple way to gain insight into your test failures is to capture screenshots at the moment of failure.
     */
    @Test
    @DisplayName("Take Screenshot If Failure Test")
    @Order(2)
    @Disabled
    void takeScreenshotIfFailureTest() throws IOException {
        System.out.println("takeScreenshotIfFailureTest...");

        // Perform an operation that will fail in a try-catch block
        try {
            driver.findElement(By.id("I want this to fail!"));
        }
        catch (Exception e) {
            // clean "screenshots" directory
            CommonUtils.cleanDirectory(".\\screenshots");

            // take screenshot and save it in "screenshots" folder
            CommonUtils.getScreenshot(driver, ".\\screenshots\\failure_" + CommonUtils.getDateTime());
        }

        System.out.println("takeScreenshotIfFailureTest ...");
    }

    /**
     *  Level: Intermediate
     *  When a web application loads things dynamically use Explicit Waits
     *  Specify a timeout and an action. Selenium will repeatedly try that action until it can either complete it
     *  successfully or it will throw a timeout exception (causing the test to error)
     */
    @DisplayName("Dynamic Loaded Elements Test")
    @Order(2)
    @ParameterizedTest
    @ValueSource(strings = { "Example 1", "Example 2"})
//    @Disabled
    void dynamicLoadedElementsTest(String exampleNumber) {
        System.out.println("dynamicLoadedElementsTest " + exampleNumber + "...");
        CommonUtils.openLink(driver, "Dynamic Loading");
        DynamicLoadingPage dynamicLoadingPage = new DynamicLoadingPage(driver);

        // click on "Example #" and click on "Start"
        dynamicLoadingPage.startExample(exampleNumber);
        dynamicLoadingPage.clickStartButton();

        // get the text of the element when visible and assert
        assertEquals("Hello World!", dynamicLoadingPage.getTextFromDynamicElement());

        System.out.println("dynamicLoadedElementsTest ...");
    }

}
