package popupCheck;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import com.support.files.Utils;

public class ElementProxy implements InvocationHandler {

    private WebElement element = null;
    private WebDriver driver = null;

    public ElementProxy(WebElement element) {
        this.element = element;
    }
    
    public ElementProxy(WebDriver driver) {

		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, 30);
		PageFactory.initElements(finder, this);
	}



    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //before invoking actual method check for the popup
        this.checkForPopupAndKill();
        //at this point, popup would have been closed if it had appeared. element action can be called safely now.
        Object result = method.invoke(element, args);
        return result;
    }

//    @FindAll({@FindBy(css="div[class='intercom-chat-snippet-card']")})
//    List<WebElement> popup;
    
    private void checkForPopupAndKill() throws InterruptedException 
    {   
        Login.checkForPopupAndKill();    
    }

    
}