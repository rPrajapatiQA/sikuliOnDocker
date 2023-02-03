package com.test.dockerSikuli;

import java.net.URL;

import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class ParallelTest {

	RemoteWebDriver driver;

	@Parameters("browser")
	@BeforeTest
	public void setUp(String browserType) throws Exception {

		DesiredCapabilities cap = new DesiredCapabilities();
		try {

			if (browserType.equalsIgnoreCase("chrome")) {
				//cap.setBrowserName("chrome");
				FirefoxOptions browserOptions = new FirefoxOptions();
				browserOptions.setAcceptInsecureCerts(true);
				driver = new RemoteWebDriver(new URL("http://54.82.21.108:4445/wd/hub"),browserOptions);
				System.out.println("#############--TEST started on " + browserType + "--################");
				//driver = new ChromeDriver();
			} else if (browserType.equalsIgnoreCase("firefox")) {
				System.out.println("#############--TEST started on " + browserType + "--################");
				//driver = new FirefoxDriver();
			}else if (browserType.equalsIgnoreCase("edge")) {
				EdgeOptions browserOptions = new EdgeOptions();
				browserOptions.setAcceptInsecureCerts(true);
				driver = new RemoteWebDriver(new URL("http://54.82.21.108:4445/wd/hub"),browserOptions);
				System.out.println("#############--TEST started on " + browserType + "--################");
				//driver = new EdgeDriver();
			}
			

			
			//driver = new ChromeDriver();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void googleTest() {
		try {
			driver.get("https://google.com");
			String title = driver.getTitle().toString();
			System.out.println(title);
			Assertion a = new Assertion();
			a.assertEquals(driver.getTitle(), "Google", "Wrong page title");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void facebookeTest() {
		try {
			driver.get("https://facebook.com");
			String title = driver.getTitle().toString();
			System.out.println(title);
			Assertion a = new Assertion();
			a.assertEquals(driver.getTitle(), "Facebook", "Wrong page title");

			System.out.println(title);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void youtubeTest() {
		try {
			driver.get("https://youtube.com");
			String title = driver.getTitle().toString();
			System.out.println(title);
			Assertion a = new Assertion();
			a.assertEquals(driver.getTitle(), "Youbtube", "Wrong page title");
			System.out.println(title);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
