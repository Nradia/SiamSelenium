package com.peopleNtech.selenium;

import java.util.concurrent.TimeUnit;
import org.testng.Assert;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


public class DriverLaunch {

	static WebDriver driver;
	//public String url;
	public static void main(String[] args) throws InterruptedException {
		
		
		
		Logger logger=Logger.getLogger(DriverLaunch.class); 
		System.setProperty("webdriver.chrome.driver","Drivers\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.get("https://ebay.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		
		// validate url
		System.out.println(driver.getCurrentUrl());
		String actURL=driver.getCurrentUrl();
		String expURL="https://www.ebay.com/";

		Assert.assertEquals(actURL, expURL);
		//logger.info("Url  validated");
		
		WebElement lego=driver.findElement(By.id("gh-l-h1"));
		boolean result=lego.isDisplayed();
		Assert.assertEquals(result,true,"Lego displayed");
		driver.findElement(By.id("gh-ac")).sendKeys("computers");
		//logger.info("Computers typed on search bar");
	
		driver.findElement(By.id("gh-btn")).click();
		
		WebElement results=driver.findElement(By.xpath("//h1[@class='srp-controls__count-heading']"));
		System.out.println(results.getText());
		logger.info("Search result displayed successfuly");
		driver.navigate().back();
		// select from drop down menu
		
		WebElement drop=driver.findElement(By.id("gh-cat"));
		drop.click();
	
		Select select=new Select(drop);
		
		select.selectByVisibleText("Travel");
		//search button
		driver.findElement(By.id("gh-btn")).click();
		WebElement travelText=driver.findElement(By.xpath("//span[text()='Travel & Luggage']"));
		System.out.println(travelText.getText());
		logger.info("dropdown selected ");
		driver.navigate().back();
		
		//Move to element
		
		WebElement motors=driver.findElement(By.linkText("Motors"));
		Actions action=new Actions(driver);
		action.moveToElement(motors).build().perform();
		
	WebElement classics=driver.findElement(By.linkText("Classics"));
	System.out.println(classics.getText());
	logger.info("classics page displayed");
		
		
		
		
		
		
		
		
	}

	
	
	
	
	
	
	public static void setUpBrowser(String browser, String url) {
		if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver","Drivers\\chromedriver.exe");
			WebDriver driver= new ChromeDriver();
			driver.get(url);	}
		else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.geckodriver.driver","Drivers\\geckodriver.exe");
			WebDriver driver= new ChromeDriver();
			driver.get(url);
		}
		
		
	}
}
