import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class MainPageTest {
    WebDriver driver;
    MainPage mainPage;
    CatalogPage catalogPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Java\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.onliner.by/");
        mainPage = PageFactory.initElements(driver, MainPage.class);
        catalogPage = new CatalogPage(driver);

    }

    @Test
    public void catalogEnter() {
        CatalogPage catalogPage = mainPage.catalogEnter();
        String heading = catalogPage.getHeanding();
        Assert.assertEquals("Каталог", heading);
    }

    @Test
    public void secondHandAutoPageEnter() {
        SecondHandAutoPage secondHandAutoPage = mainPage.secondHandAutoEnter();
        String heading = secondHandAutoPage.headingAutoPage();
        Assert.assertEquals("Автобарахолка", heading);
    }

    @Test
    public void searchBar() {
        String productName = "Ryzen";
        mainPage.search(productName);
        String searchElement = mainPage.textFirstElementsInSearch();
        Assert.assertTrue(searchElement.contains(productName));
    }

    @Test
    public void homeAndFlatEnter() {
        mainPage.homeAndFlatEnter();
        Assert.assertTrue(driver.getTitle().contains("Купить квартиру в Минске"));
    }

    @Test
    public void servicesPageEnter() {
        mainPage.servicesEnter();
        Assert.assertTrue(driver.getTitle().contains("Заказы на услуги"));
    }

    @Test
    public void fleaMarketPageEnter() {
        mainPage.fleaMarketEnter();
        Assert.assertTrue(driver.getTitle().contains("Барахолка onliner.by - Главная страница"));
    }

    @Test
    public void forumEnter() {
        mainPage.forumEnter();
        Assert.assertTrue(driver.getTitle().contains("Форум onliner.by - Главная страница"));
    }

    @Test
    public void exchangeRatesEnter() {
        mainPage.exchangeRatesEnter();
        Assert.assertTrue(driver.getTitle().contains("Лучшие курсы валют onliner.by"));
    }

    @Test
    public void weatherEnter() {
        mainPage.weatherEnter();
        Assert.assertTrue(driver.getTitle().contains("Погода onliner.by"));
    }


    @After
    public void tearDown() {
        catalogPage.createScreenshot();
        driver.quit();
    }
}
