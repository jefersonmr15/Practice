package com.project.pom;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class Base {
	
	private WebDriver driver;
	
	public Base(WebDriver driver) {
		this.driver=driver;
	}
	
	public WebDriver chromeDriverConnection() {
		
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver=new ChromeDriver();
		return driver;
	}
	
	public WebElement findElement(By locator) {
		return driver.findElement(locator);	
	}
	
	public List<WebElement> findElements(By locator) {
		return driver.findElements(locator);	
	}
	
	public String getText(WebElement element) {
		return element.getText();	
	}
	
	public String getText(By locator) {
		return driver.findElement(locator).getText();
	}
	
	public void type(String inputText, By locator) {
		driver.findElement(locator).sendKeys(inputText);
	}
	
	public void click(By locator) {
		driver.findElement(locator).click();
	}
	
	public Boolean isDisplayed(By locator) {
		try {
			return driver.findElement(locator).isDisplayed();
		}catch(org.openqa.selenium.NoSuchElementException e){
			return false;
		}
	}
	
	public void visit(String url) {
		driver.get(url);
		driver.manage().window().maximize();
	}
	
	public String textAlert() {
		Alert alert = driver.switchTo().alert();
		return alert.getText();
	}
	
	public void confirmAlert() {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	
	public void waitElementVisible(By locator) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void waitElementNotVisible(By locator) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	public void waitAlertPresent() {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void elementToBeClickable(By locator) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public void scrollToElement(By locator) throws InterruptedException {
		WebElement element = driver.findElement(locator);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500); 
	}
	
	public String[][] getFileData() throws IOException{
		String users[][];

		FileInputStream pathFile = new FileInputStream("./src/test/resources/File/users.xlsx");
		XSSFWorkbook book=new XSSFWorkbook(pathFile);
		XSSFSheet sheet=book.getSheetAt(0);
		Iterator<Row> rows=sheet.iterator();
		Iterator<Cell> cells;
		Row row;
		Cell cell;
		int rowCount=0,columnCount=0;
		users=new String[2][2];
		while(rows.hasNext()) {
			row=rows.next();
			cells=row.cellIterator();
			while(cells.hasNext()) {
				cell=cells.next();
				users[rowCount][columnCount]=cell.getStringCellValue();
				columnCount++;
			}
			columnCount=0;
			rowCount++;
		}
		book.close();
		
		return users;
	}

}
