package com.project.pom;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class Login_Test {
	
	private WebDriver driver;
	IndexPage login;
	String list[][];

	@Before
	public void setUp() throws Exception {
		login=new IndexPage(driver);
		driver=login.chromeDriverConnection();
		login.visit("https://demoblaze.com/index.html");
	}
	
	@After
	public void tearDown() throws Exception {
		Thread.sleep(10000);
		driver.quit();
	}

	@Test
	public void test() throws InterruptedException, IOException {
		list=login.getchargedata();
		for(int i=0;i<list.length;i++) {
			login.loginUser(list[i][0],list[i][1]);
			login.addCart();
			login.verifyProducts();
			login.logOut();
		}
	}

}
