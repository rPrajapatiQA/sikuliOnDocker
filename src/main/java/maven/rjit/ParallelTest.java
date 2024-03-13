package maven.rjit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.sikuli.script.ScreenImage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class ParallelTest {

	WebDriver driver;

	@Parameters("browser")
	@BeforeMethod
	public void setUp(String browser) throws Exception {

		//DesiredCapabilities cap = new DesiredCapabilities();
		try {

			if (browser.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-dev-shm-usage");
				//options.addArguments("--headless");
				driver = new ChromeDriver(options);
				//driver = new RemoteWebDriver(new URL("http://54.82.21.108:4445/wd/hub"),browserOptions);
				System.out.println("#############--TEST started on " + browser + "--################");
				//driver = new ChromeDriver();
			} else if (browser.equalsIgnoreCase("firefox")) {
				System.out.println("#############--TEST started on " + browser + "--################");
				driver = new FirefoxDriver();
			}else if (browser.equalsIgnoreCase("edge")) {
				//EdgeOptions browserOptions = new EdgeOptions();
				//browserOptions.setAcceptInsecureCerts(true);
				driver=new EdgeDriver();
				//driver = new RemoteWebDriver(new URL("http://54.82.21.108:4445/wd/hub"),browserOptions);
				System.out.println("#############--TEST started on " + browser+ "--################");
				//driver = new EdgeDriver();
			}
			

			
			//driver = new ChromeDriver();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	@AfterMethod
	void tearDown(){
		if(driver!=null) {
			driver.quit();
		}
	}

	@Test
	public void googleTest() {
		try {
			driver.get("https://google.com");
			String title = driver.getTitle().toString();
			System.out.println(title);
			//new Assert().assertEquals(driver.getTitle(), "Google", "Wrong page title");
			
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
			//Assertion a = new Assertion();
			//a.assertEquals(driver.getTitle(), "Facebook", "Wrong page title");

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
			
			Screen scr = new Screen();
			Pattern p = new Pattern(System.getProperty("user.dir")+"/image/youtube.png");
			
			ScreenImage img = scr.findBest(p).highlight(5).getScreen().capture();
			img.save(System.getProperty("user.dir"));
			
			//Assertion a = new Assertion();
			//a.assertEquals(driver.getTitle(), "Youbtube", "Wrong page title");
			System.out.println(title);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	
	
}
