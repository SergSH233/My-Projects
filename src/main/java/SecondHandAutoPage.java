import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SecondHandAutoPage {
    WebDriver driver;

    public SecondHandAutoPage(WebDriver driver) {
        this.driver = driver;
    }
//    @FindBy(xpath = "//h1[@class=\"js-search-title m-title main\"]")
//    private WebElement heading;
    private By heading = By.xpath("//h1[@class=\"js-search-title m-title main\"]");

    public String headingAutoPage(){
        return driver.findElement(heading).getText();
    }
}
