import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CatalogPage {
    WebDriver driver;

    public CatalogPage(WebDriver driver) {
        this.driver = driver;
    }

    private By heading = By.xpath("//h1[@class=\"catalog-navigation__title\"]");

    private By computerAndWeb = By.xpath("//li[@data-id=\"2\"]");

    private By electronics = By.xpath("//li[@data-id=\"1\"]");

    private By componentParts = By.xpath("(//div[@class=\"catalog-navigation-list__aside-title\"])[12]");

    private By tvAndVideo = By.xpath("//a[@href=\"https://catalog.onliner.by/tv\"]/ancestor::div[@class=\"catalog-navigation-list__aside-item\"]");

    private By videocard = By.xpath("//a[@href=\"https://catalog.onliner.by/videocard\"]");

    private By tv = By.xpath("//a[@href=\"https://catalog.onliner.by/tv\"]");
    //Меню Производитель
    private By manufacturer = By.xpath("(//div[@class=\"schema-filter-control__item\"])[2]");
    //Меню магазины
    private By store = By.xpath("(//div[@class=\"schema-filter-control__item\"])[3]");
    // Шина памяти
    private By memoryInterface = By.xpath("(//div[@class=\"schema-filter-control__item\"])[5]");
    //Производитель(галочки)
    private By manufacturerAMD = By.xpath("(//span[text()=\"AMD\"])[3]");
    private By manufacturerNVIDIA = By.xpath("(//span[text()=\"NVIDIA\"])[2]");
    //Объем Видеопамяти
    private By videoRAM1 = By.xpath("(//select[@class=\"schema-filter-control__item\"])[1]");
    private By videoRAM1_Select4Gb = By.xpath("(//select[@class=\"schema-filter-control__item\"])[1]//option[@value=\"4gb\"]");
    private By videoRAM2 = By.xpath("(//select[@class=\"schema-filter-control__item\"])[2]");
    private By videoRAM2_Select8Gb = By.xpath("(//select[@class=\"schema-filter-control__item\"])[2]//option[@value=\"8gb\"]");
    //Поднятся вверх и нажать для сортировки
    private By sorting = By.xpath("//a[@class=\"schema-order__link\"]");
    //Критерии сортировки
    private By chip = By.xpath("//div[@class=\"schema-order__item\"]//span[text()=\"Дешевые\"]");
    private By feedback = By.xpath("//div[@class=\"schema-order__item schema-order__item_new schema-order__item_active\"]//span[text()=\"С отзывами\"]");
    //Лист элементов
    private By findList = By.xpath("//div[@class=\"schema-product__group\"]//span[@data-bind=\"html: product.extended_name || product.full_name\"]");

    //Скролы при быстрых проверках видекарт
    //Описание
    public By headingCard = By.xpath("//h3");
    //технические характеристики
    public By specifications = By.xpath("(//td[@colspan=\"2\"])[3]");


    //MainPage
    public String getHeanding() {
        return driver.findElement(heading).getText();
    }

    //Instrument
    public void selectRadioButton(String name) {
        String rbXpath = "//span[text()='%s']";
        if (!driver.findElement(By.xpath(String.format(rbXpath, name))).isSelected())
            driver.findElement(By.xpath(String.format(rbXpath, name))).click();
    }

    //Instrument
    public void scrollPage(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    //Instrument
    public void moveCursorToElement(WebElement element) {
        Actions bulder = new Actions(driver);
        bulder.moveToElement(element).build().perform();
    }

    //Instrument
    public void createScreenshot() {
        Date dataNow = new Date();
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd//hh_mm_ss");
        String fileName = format.format(dataNow) + ".png";
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("E:\\Pictures\\Project\\OnlinerProject " + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Instrument
    public void explicitWait(WebElement element) {
        WebElement wait = new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(element));
    }

    //Instrument
    public void waitThreadSleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //#firstOnTheListIsCheaper
    public boolean firstСheaper(WebElement element1, WebElement element2) {
        double x = priceParseDouble(element1);
        double y = priceParseDouble(element2);
        if (x < y) {
            return true;
        } else {
            return false;
        }
    }

    //#firstOnTheListIsCheaper
    public double priceParseDouble(WebElement elementForPrice) {
        String element = elementForPrice.getText();
        double x = Double.parseDouble(element.substring(0, element.length() - 3).replace(",", "."));
        return x;
    }

    //#firstOnTheListIsCheaper
    public WebElement selectElementFromList_price(int numberInList) {
        String element = "(//a[@data-bind=\"attr: {href: $data.prices.html_url}\"]//preceding-sibling::*)[%d]";
        return driver.findElement(By.xpath(String.format(element, numberInList)));
    }

    //#onlyOneManufacturerInTheSearch
    public boolean onlyOneManufacturerInList(List list, String nameCompany) {
        List<WebElement> array = list;
        for (int i = 0; i < array.size(); i++) {
            String element = array.get(i).getText();
            if (element.contains(nameCompany)) {
                return true;
            } else if (!element.contains(nameCompany)) {
                return false;
            }
        }
        return false;
    }

    //#onlyOneManufacturerInTheSearch
    public void findTV() {
        driver.findElement(electronics).click();
        moveCursorToElement(driver.findElement(tvAndVideo));
        driver.findElement(tv).click();
    }


    //#videoсardYear
    public boolean yearVideoCard(WebElement element) {
        String year = element.getText();
        if (year.contains("2018 г.") || year.contains("2019 г.")) {
            return true;
        } else {
            return false;
        }
    }

    //#videoсardYear
    public WebElement selectElementFromList(int numberInList) {
        String element = "(//div[@class=\"schema-product__group\"]//span[@data-bind=\"html: product.extended_name || product.full_name\"])[%d]";
        return driver.findElement(By.xpath(String.format(element, numberInList)));
    }

    //#firstOnTheListIsCheaper; #videoсardYear;
    public void findVideoCard() {
        driver.findElement(computerAndWeb).click();
        driver.findElement(componentParts).click();
        driver.findElement(videocard).click();
        driver.findElement(manufacturer).click();
        selectRadioButton("AMD");
        selectRadioButton("ASUS");
        scrollPage(driver.findElement(store));
        driver.findElement(store).click();
        selectRadioButton("21vek.by");
        selectRadioButton("Sli.by");
//        selectRadioButton("SOCKET.BY");
        selectRadioButton("В наличии на складе");
        selectRadioButton("2018");
        selectRadioButton("2019");
        scrollPage(driver.findElement(manufacturerAMD));
        driver.findElement(manufacturerAMD).click();
        driver.findElement(manufacturerNVIDIA).click();
        driver.findElement(videoRAM1).click();
        driver.findElement(videoRAM1_Select4Gb).click();
        driver.findElement(videoRAM2).click();
        driver.findElement(videoRAM2_Select8Gb).click();
        selectRadioButton("GDDR5");
        selectRadioButton("GDDR6");
        driver.findElement(memoryInterface).click();
        selectRadioButton("192 бит");
        selectRadioButton("256 бит");
        selectRadioButton("активное");
        scrollPage(driver.findElement(sorting));
    }

    //#firstOnTheListIsCheaper
    public void clickSortingByPrice() {
        driver.findElement(sorting).click();
        driver.findElement(chip).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

