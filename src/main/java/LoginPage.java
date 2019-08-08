import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) { this.driver = driver; }

    //MainPage
    private By enterString = By.xpath("//*[@id=\"auth-container\"]/div/div[2]/div/div[1]");
//    @FindBy(xpath = "//div[@class=\"auth-form\"]//div[@class=\"auth-form__title auth-form__title_big auth-form__title_condensed-default\"]")
//    private WebElement enterString;
    @FindBy (xpath = "(//button[@type=\"submit\"])[2]")
    private WebElement enterButton;


    //MainPage metods
    public String textLogIn(){
        return driver.findElement(enterString).getText();
    }
    public LoginPage enterClick(){
        enterButton.click();
        return this;
    }





}
