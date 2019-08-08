import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class CatalogPageTest {
    WebDriver driver;
    CatalogPage catalogPage;
    private static final Logger log = Logger.getLogger(String.valueOf(CatalogPageTest.class));
    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Java\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://catalog.onliner.by/");
        catalogPage = new CatalogPage(driver);
    }
    @Test
    public void firstOnTheListIsCheaper(){
        catalogPage.findVideoCard();
        catalogPage.clickSortingByPrice();
        WebElement firstInList = catalogPage.selectElementFromList_price(1);
        WebElement secondInList = catalogPage.selectElementFromList_price(2);
        Assert.assertTrue(catalogPage.firstСheaper(firstInList,secondInList));
        log.info("Test First On The List Is Cheaper completed");
    }
    @Test
    public void onlyOneManufacturerInTheSearch(){
        catalogPage.findTV();
        catalogPage.selectRadioButton("LG");
        catalogPage.waitThreadSleep(1500);
        List<WebElement> list = driver.findElements(By.xpath("//div[@class=\"schema-product__group\"]//span[@data-bind=\"html: product.extended_name || product.full_name\"]"));
        Assert.assertTrue(catalogPage.onlyOneManufacturerInList(list,"LG"));
        log.info("Test Only One Manufacturer In The Search completed");
    }
    @Test
    public void videoсardYear(){
        catalogPage.findVideoCard();
        catalogPage.waitThreadSleep(2000);
        catalogPage.selectElementFromList(2).click();
        WebElement heading = driver.findElement(By.xpath("//h3"));
        catalogPage.scrollPage(heading);
        WebElement productionDate = driver.findElement(By.xpath("(//tr[2]//td[2])[1]"));
        Assert.assertTrue(catalogPage.yearVideoCard(productionDate));
        log.info("Test Videoсard Year completed");

    }

    @After
    public void tearDown() {
        catalogPage.createScreenshot();
        driver.quit();
    }
}
