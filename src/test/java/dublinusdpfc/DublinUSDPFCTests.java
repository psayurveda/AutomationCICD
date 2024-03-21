package dublinusdpfc;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dublinusdpfc.pageobjects.LandingPage;

//import com.FramesAILabs.DublinUSDPFCSeleniumFrameWork.ExcelFileOperations;


public class DublinUSDPFCTests {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		//driver.get("https://dublinusd.org/");
		//ExcelFileOperations.createFile();
		LandingPage landingpage = new LandingPage(driver);
		landingpage.goTo();
		
		
	}
	public static void getAmadorEleParentFaculty(WebDriver driver, WebDriverWait wait) {
		WebElement schools = driver.findElement(By.xpath("//div[@class='schools-list']"));
		schools.click();
		
		WebElement amador = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='schools-list']//li/a[text()='Amador Elementary']")));
		amador.click();
		WebElement about = driver.findElement(By.xpath("//a[@id='nmP2']"));
		about.click();
		WebElement organization = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='nmP7']")));
		organization.click();
		
		driver.findElement(By.xpath("//a[@id='nmL10']")).click();
		List<WebElement> list = driver.findElements(By.xpath("//div[@class='en-editable-block-wrapper']//div[4]//div//div//div"));
		List<String> amadorclub = new ArrayList<String>();
		System.out.println("List size "+ list.size());
		String[] amadorArr=null;
		int count = 1;
		String aPost;
		String aName;
		String aEmail;
		String locator = "//div[@class='en-editable-block-wrapper']//div[4]//div//div//div";
		
		for ( WebElement name : list)
		{
		    System.out.println("Name "+name.getText()+ " Count: "+count );
		    
		    if ((name.getText().isBlank()) || (name.getText().isEmpty())) {
		    	count= count+1;
		    	continue;
		    }
		    
			aPost = locator+"["+count+"]//strong";
			aName =locator+"["+count+"]";
			aEmail =locator+"["+count+"]//a";
			
			String ePost = driver.findElement(By.xpath(aPost)).getText();
			String eName = driver.findElement(By.xpath(aName)).getText();
			String eEmail = driver.findElement(By.xpath(aEmail)).getText();
			System.out.println("ePost =" +ePost+","+"eName =" +eName+","+"eMail="+eEmail);
			amadorArr = eName.split("-");
			amadorclub.add(amadorArr[1]+","+amadorArr[0]+","+amadorArr[2]);
		     
			count= count+1;
			
		}
		
		//ExcelFileOperations.createFile();
		ExcelFileOperations.fileWrite(amadorclub, "Amador", ",");
	}
}
