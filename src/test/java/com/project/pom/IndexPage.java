package com.project.pom;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IndexPage extends Base {
	
	public String chargedata[][];
	
	By logInLink=By.xpath("//*[@id='login2']");
	By userInput=By.xpath("//*[@id='loginusername']");
	By passwordInput=By.xpath("//*[@id='loginpassword']");
	By LogInButton=By.xpath("//*[@onclick='logIn()']");
	By userAuthenticade=By.xpath("//*[@id='nameofuser' and contains(text(),'Welcome')]");
	By phoneCateogriesLink=By.xpath("//*[@id='itemc' and text()='Phones']");
	By phoneLink=By.xpath("//*[@class='card-title']/a[text()='HTC One M9']");
	
	By addCartButton=By.xpath("//*[@onclick='addToCart(7)']");
	By cartLink=By.xpath("//*[@id='cartur']");
	
	By productsphoneTable=By.xpath("//*[@id='tbodyid']/tr[1]/td[2]");
	
	By LogOutLink=By.xpath("//*[@onclick='logOut()']");

	public IndexPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public void loginUser(String user,String password) throws InterruptedException{
		click(logInLink);
		waitElementVisible(userInput);
		type(user, userInput);
		type(password, passwordInput);
		click(LogInButton);
		waitElementNotVisible(userInput);
	}
	
	public void addCart() throws InterruptedException{
		waitElementVisible(userAuthenticade);
		click(phoneCateogriesLink);
		scrollToElement(phoneLink);
		click(phoneLink);
		waitElementVisible(addCartButton);
		click(addCartButton);
		waitAlertPresent();
		assertEquals("Product added.", textAlert());
		confirmAlert();
	}
	
	public void verifyProducts() {
		elementToBeClickable(addCartButton);
		click(cartLink);
		waitElementVisible(productsphoneTable);
		assertEquals("HTC One M9", getText(productsphoneTable));
	}
	
	public void logOut() {
		click(LogOutLink);
	}
	
	public String[][] getchargedata() throws IOException {
		this.chargedata=getFileData();
		return chargedata;
	}
	
	
	
}
