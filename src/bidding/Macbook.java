package bidding;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class Macbook {
	
	private WebDriver driver;
	private String baseUrl;
	private JavascriptExecutor js;

	@BeforeClass
	public void beforeClass() {
		
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		baseUrl = "https://www.ebay.com/";
		driver.get(baseUrl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void test1() throws Exception {
		WebElement searchBox = driver.findElement(By.id("gh-ac"));
		searchBox.sendKeys("macbook");
		
		WebElement searchBtn = driver.findElement(By.id("gh-btn"));
		searchBtn.click();
		
		// Close Pop Up
		WebElement closePop = driver.findElement(By.xpath("//span[contains(@class, 'close')]"));
		WebDriverWait wait = new WebDriverWait(driver, 60);// 1 minute 
		wait.until(ExpectedConditions.visibilityOf(closePop));
		closePop.click();
		
		//js.executeScript("arguments[0].click", closePop);
		
		Thread.sleep(3000);
		
		// Select Auction 
		WebElement auctionBtn = driver.findElement(By.xpath("(//a[contains(@href,'Auction')])[2]"));
		auctionBtn.click();
		//auctionBtn.click();
		
		// Sort to Ending Soonest
		WebElement sortBox = driver.findElement(By.xpath("(//div[contains(@class, 'x-flyout')])[1]"));
		WebDriverWait sortWait = new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.visibilityOf(sortBox));
		
		Actions action = new Actions(driver);
		action.moveToElement(sortBox).perform();
		
		WebElement endingSoon = driver.findElement(By.xpath("//div[contains(@id, 'V2-w0-w1') and contains(@class, 'x-flyout')]//span[text()='Time: ending soonest']"));
		action.moveToElement(endingSoon).click().perform();
		
		// Scrolling down
		js.executeScript("window.scrollBy(0, 1800);");
		
		WebElement product = driver.findElement(By.xpath("//li[contains(@id, 'listing18')]//a[contains(@class,'link')]"));
		product.click();
		
		WebElement startingBid = driver.findElement(By.id("prcIsum_bidPrice"));
		System.out.println(startingBid.getText());
		
//		List<WebElement> allSortTypes = sortBox.findElements(By.tagName("li"));
//		
//		for(WebElement sort: allSortTypes) {
//			if(sort.getText().equals("Time: ending soonest")) {
//				sort.click();
//				break;
//			}
//		}
		
		
	}

	@AfterClass
	public void afterClass() throws Exception {
		Thread.sleep(7000);
		//driver.quit();
		
	}

}
