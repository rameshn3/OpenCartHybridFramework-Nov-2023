package com.qa.opencart.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.qa.opencart.utils.WebDriverUtils;

public class HomePage extends WebDriverUtils{
	
	private static Logger log=LogManager.getLogger(HomePage.class.getName());
	
	public HomePage(WebDriver driver) {
		super(driver);
		
	}

@FindBy(css="#logo > a > img")
private WebElement openCartLogo;

@FindBy(xpath="//div[@id='top-links']/ul/li[2]/a")
private WebElement myAccountMenu;

@FindBy(linkText="Register")
private WebElement registerLink;

@FindBy(linkText="Login")
private WebElement loginLink;

@FindBy(xpath="//div[@id='content']/div[2]/div")
private List<WebElement>featuredSectionCardsList;

public boolean isOpenCartLogoExists() {
	log.info("Verifying opencart logo is present or not");
	return openCartLogo.isDisplayed();
}

public void openMyAccountMenu() throws InterruptedException {
	log.info("Opening my account menu");
	click(myAccountMenu);
}

public void navigateToregisterPage() throws InterruptedException {
	openMyAccountMenu();
	log.info("clcik on registerLink under my account menu");
	click(registerLink);
}

public void navigateToLoginPage() throws InterruptedException {
	openMyAccountMenu();
	log.info("clcik on loginLink under my account menu");
	click(loginLink);
}

public int getFeaturedSectionCardsCount() {
	log.info("fetching Featured section cards count");
	return featuredSectionCardsList.size();
}

}
