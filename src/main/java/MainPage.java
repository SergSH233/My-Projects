import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    //Верхняя панель
    @FindBy(xpath = "//div[@class=\"auth-bar__item auth-bar__item--text\"]")
    private WebElement logInButton;
    @FindBy(xpath = ".//span[text()=\"Корзина\"]")
    private WebElement cart;
    @FindBy(xpath = "//input[@class=\"fast-search__input\"]")
    private WebElement search;
    //Желтый бар сверху
    @FindBy(xpath = "//a[@class=\"b-main-navigation__link\"]//span[text()=\"Каталог\"]")
    private WebElement catalog;
    @FindBy(xpath = "//a[@class=\"b-main-navigation__link\"]//span[text()=\"Новости\"]")
    private WebElement news;
    @FindBy(xpath = "//a[@class=\"b-main-navigation__link\"]//span[text()=\"Автобарахолка\"]")
    private WebElement secondHandAuto;
    @FindBy(xpath = "//a[@class=\"b-main-navigation__link\"]//span[text()=\"Дома и квартиры\"]")
    private WebElement homeAndFlat;
    @FindBy(xpath = "//a[@class=\"b-main-navigation__link\"]//span[text()=\"Услуги\"]")
    private WebElement services;
    @FindBy(xpath = "//a[@class=\"b-main-navigation__link\"]//span[text()=\"Барахолка\"]")
    private WebElement fleaMarket;
    @FindBy(xpath = "//a[@class=\"b-main-navigation__link\"]//span[text()=\"Форум\"]")
    private WebElement forum;
    //Погода и курсы валют
    @FindBy(xpath = "//li[@id=\"currency-informer\"]//span")
    private WebElement exchangeRates;
    @FindBy(xpath = "//li[@id=\"weather-informer\"]//span")
    private WebElement weather;
    //Мини-бар каталога



    //Работа с поиском
    @FindBy(xpath = "(//div[@class=\"product__title\"])[1]")
    private WebElement firstElementInSearch;
;
    //Методы для поиска
    public MainPage search(String wordForSearch){
        search.sendKeys(wordForSearch);
        driver.switchTo().frame(0);
        return this;
    }
    public String textFirstElementsInSearch(){
        return firstElementInSearch.getText();
    }
    public LoginPage logInEnter(){
        logInButton.click();
        return new LoginPage(driver);
    }

    public CatalogPage catalogEnter(){
        catalog.click();
        return new CatalogPage(driver);
    }
    public SecondHandAutoPage secondHandAutoEnter(){
        secondHandAuto.click();
        return new SecondHandAutoPage(driver);
    }
    public HomeAndFlatPage homeAndFlatEnter() {
        homeAndFlat.click();
        return new HomeAndFlatPage(driver);
    }
    public ServicesPage servicesEnter(){
        services.click();
        return new ServicesPage(driver);
    }
    public FleaMarketPage fleaMarketEnter(){
        fleaMarket.click();
        return new FleaMarketPage(driver);
    }
    public ForumPage forumEnter(){
        forum.click();
        return new ForumPage(driver);
    }
    public ExchangeRatesPage exchangeRatesEnter(){
        exchangeRates.click();
        return new ExchangeRatesPage(driver);
    }
    public WeatherPage weatherEnter(){
        weather.click();
        return new WeatherPage(driver);
    }
    public MainPage news(){
        news.click();
        return this;
    }






}
