package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutComplete {
	WebDriver driver;

    @FindBy(className="complete-header")
    WebElement thankYouMsg;

    public CheckoutComplete(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getThankYouMessage() {
        return thankYouMsg.getText();
    }

}
