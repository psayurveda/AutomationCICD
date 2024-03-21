package dublinusdpfc.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import dublinusdpfc.AbstractComponents.AbstractComponents;

//import dublinusdpfc.ExcelFileOperations;

public class LandingPage extends AbstractComponents {

	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		//ExcelFileOperations.createFile();
	}
	

	@FindBy(xpath="//div[@class='schools-list']")
	WebElement schools;
	
	
	@FindBy(xpath="//div[@class='schools-list']//li/a[text()='Amador Elementary']")
	WebElement amador;

	public void selectSchool() {
		schools.click();
		amador.click();
	}
	public void goTo() {
		driver.get("https://dublinusd.org/");
	}
}
