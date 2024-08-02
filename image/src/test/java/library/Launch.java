package library;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.DateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Launch {
	WebDriver driver;

	@BeforeMethod
	void launching() {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--headless=new");
		driver = new ChromeDriver(option);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

	}

	public static String getDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM, yy");
		LocalDateTime now = LocalDateTime.now();
		return (dtf.format(now));
	}

	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
		long diffInMillies = date2.getTime() - date1.getTime();
		return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}
	public static void writeFile(String filePath,String data){
	    try {
	        FileWriter myWriter = new FileWriter(filePath+"\\visaDates.txt");
	        myWriter.write(" ");
	        myWriter.write(data);
	        myWriter.close();
	        System.out.println("Successfully wrote to the file.");
	      } catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
	    }

	@Test
	void test1() throws InterruptedException {
		try {
			String mail="";
			driver.get("https://checkvisaslots.com/latest-us-visa-availability.html");
			Thread.sleep(10000);
			//// table[@id='table_B1_B2_Regular_']//tbody/tr[@data-index='0']/td
			List<WebElement> elList = driver.findElements(By.xpath("//table[@id='table_B1_B2_Regular_']//tbody/tr"));
			for (int i=0;i<elList.size();i++) {
				
				var element = driver.findElement(
						By.xpath("//table[@id='table_B1_B2_Regular_']//tbody/tr[@data-index='" + i + "']/td"));
				String location = element.getText();
				//System.out.println(location);
				if (location.contains("VAC")) {
					String date = driver
							.findElement(By.xpath(
									"//table[@id='table_B1_B2_Regular_']//tbody/tr[@data-index='" + i + "']/td[3]"))
							.getText();

					long dateLeft = getDateDiff(
							DateUtils.parseDate(getDate(), new String[] { "yyyy-MM-dd HH:mm:ss", "dd MMM, yy" }),
							DateUtils.parseDate(date, new String[] { "yyyy-MM-dd HH:mm:ss", "dd MMM, yy" }),
							TimeUnit.DAYS);
					if (dateLeft < 120) {
						mail += dateLeft +" <= days left :: appointment date=> " + date
								+ " :: Place => " + location+"\n";
						
						writeFile("c:\\",mail);
						
					}

				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@AfterMethod
	void tearDown() {
		driver.quit();
	}

}
