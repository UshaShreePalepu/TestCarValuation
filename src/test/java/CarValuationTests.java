import org.example.FileUtils;
import org.example.pages.DetailsNotFoundPage;
import org.example.pages.DetailsPage;
import org.example.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CarValuationTests {
    WebDriver driver;
    private HomePage homePage;
    private DetailsPage detailsPage;
    private DetailsNotFoundPage detailsNotFoundPage;

    @BeforeMethod
    public void setup() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("https://www.webuyanycar.com/");
        driver.manage().deleteAllCookies();
        Thread.sleep(7000);
        driver.findElement(By.cssSelector("#onetrust-accept-btn-handler")).click();
        Thread.sleep(15000);
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        Thread.sleep(10000);
    }

    @DataProvider(name = "carRegNumbers")
    public Object[] carREgNumberProvider() throws IOException {
        return FileUtils.readCarNumbers();
    }

    @Test(dataProvider = "carRegNumbers")
    public void weBuyAnyCarValuationTest(String number) throws IOException, InterruptedException {
        homePage.enterVehicleRegistrationNumber(number);
        homePage.enterMileage("20000");
        homePage.clickGetMyCarValuationButton();

        detailsPage = new DetailsPage(driver);

        HashMap<String, String[]> outputCarDetails = FileUtils.retrieveExpectedOutput();
        String[] details = outputCarDetails.get(number);
        Thread.sleep(7000);
        if (detailsPage.getPageUrl().contains("details")) {
            String reg = detailsPage.getRegistrationNumber(number);
            String make = detailsPage.getMake();
            String model = detailsPage.getModelDetails();
            String year = detailsPage.getMakeYear();
            assertAll(
                    () -> assertEquals(number, reg),
                    () -> assertEquals(details[0], make),
                    () -> assertEquals(details[1], model),
                    () -> assertEquals(details[2], year)
            );
        } else {
            detailsNotFoundPage = new DetailsNotFoundPage(driver);
            String header = detailsNotFoundPage.getPageHeadingText();
            assertEquals(header, "Sorry, we couldn't find your car");
        }
        System.out.printf("VARIANT_REG: %s, MAKE: %s, MODEL: %s, YEAR: %s%n", number, details[0], details[1], details[2]);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


}
