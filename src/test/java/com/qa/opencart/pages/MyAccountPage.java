package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.WebDriverUtils;

public class MyAccountPage extends WebDriverUtils{

	private Logger log=LogManager.getLogger(MyAccountPage.class.getName());
	private WebDriver driver;
	public MyAccountPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		
	}
	
	@FindBy(xpath="//div[@id='top-links']/ul/li[2]/a")
	private WebElement myAccountMenu;
	
	@FindBy(css="input[placeholder='Search']")
	private WebElement searchEditbox;
	
	@FindBy(css="button[class='btn btn-default btn-lg']")
	private WebElement searchTorchIcon;
	
	@FindBy(linkText="Logout")
	private WebElement logoutLink;
	
	@FindBy(css="body > div:nth-child(4) > ul > li:nth-child(2) > a")
	private WebElement accountBreadcrumb;
	
	@FindBy(xpath="//*[@id='content']/h2")
	private List<WebElement> myAccountHeaderList;
	
	@FindBy(xpath="//*[@id='top-links']/ul/li[2]/ul/li/a")
	private List<WebElement> myAccountMenuOptionList;
	
	@FindBy(xpath="//*[@id='content']/ul[1]/li/a")
	private List<WebElement> myAccountHeaderMenuOptionList;
	
	@FindBy(xpath="//*[@id='content']/ul[2]/li/a")
	private List<WebElement> myOrdersHeaderMenuOptionList;
	
	@FindBy(xpath="//*[@class='fa fa-home']")
	private WebElement homeIcon;
	
	public void clickMyAccountMenuLink() throws InterruptedException {
		log.info("click on My Account menu link");
		click(myAccountMenu);
	}
	
	public String getMyAccountPageUrl() {
		log.info("fetch my account page url");
		return waitForUrlContains(Constants.ACC_PAGE_FRACTION_URL);
	}
	
	public boolean isSearchEditboxExists() {
		return isDisplayed(searchEditbox);
	}
	
	public boolean isLogoutExists() throws InterruptedException {
		clickMyAccountMenuLink();
		return isDisplayed(logoutLink);
	}
	public void clickLogoutLink() throws InterruptedException {
		clickMyAccountMenuLink();
		waitForElementVisible(By.linkText("Logout"));
		log.info("click on logout link");
		click(logoutLink);
	}
	
	
	public List<String> getMyAccountMenuOptionList() throws InterruptedException{
		clickMyAccountMenuLink();
		List<String>myAccMenuOptionTxtList=new ArrayList<String>();
		for(WebElement ele:myAccountMenuOptionList) {
			String txt=ele.getText();
			//add the element text to myAccMenuOptionTxtList
			myAccMenuOptionTxtList.add(txt);
		}
		return myAccMenuOptionTxtList;
	}
	
	public List<String> getMyAccountHeaderList() throws InterruptedException{
		
		List<String>myAccHeaderTxtList=new ArrayList<String>();
		for(WebElement ele:myAccountHeaderList) {
			String txt=ele.getText();
			//add the element text to myAccMenuOptionTxtList
			myAccHeaderTxtList.add(txt);
		}
		return myAccHeaderTxtList;
	}
	
	public List<String> getMyAccountHeaderMenuOptionList() throws InterruptedException{
		
		List<String>myAccHeaderOptionTxtList=new ArrayList<String>();
		for(WebElement ele:myAccountHeaderMenuOptionList) {
			String txt=ele.getText();
			//add the element text to myAccMenuOptionTxtList
			myAccHeaderOptionTxtList.add(txt);
		}
		return myAccHeaderOptionTxtList;
	}
	
	public List<String> getMyOrderHeaderOptionList() throws InterruptedException{
		
		List<String>myOrderHeaderTxtList=new ArrayList<String>();
		for(WebElement ele:myOrdersHeaderMenuOptionList) {
			String txt=ele.getText();
			//add the element text to myAccMenuOptionTxtList
			myOrderHeaderTxtList.add(txt);
		}
		return myOrderHeaderTxtList;
	}
	
	public ResultsPage doProductSearch(String productName) throws InterruptedException {
		log.info("Searching for the product:"+productName);
		if(isSearchEditboxExists()) {
			sendData(searchEditbox, productName);
			click(searchTorchIcon);
			return new ResultsPage(driver);
		}
		return null;
	}
	
}
