package maven.rjit;

import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class SecontTestClass extends ParallelTest {

	@Test
	public void googleTest() {
		try {
			driver.get("https://google.com");
			String title = driver.getTitle().toString();
			System.out.println(title+" SecontTestClass");
			//Assertion a = new Assertion();
			//a.assertEquals(driver.getTitle(), "Google", "Wrong page title");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void facebookeTest() {
		try {
			driver.get("https://facebook.com");
			String title = driver.getTitle().toString();
			System.out.println(title+" SecontTestClass");
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
			System.out.println(title+" SecontTestClass");
			//Assertion a = new Assertion();
			//a.assertEquals(driver.getTitle(), "Youbtube", "Wrong page title");
			System.out.println(title);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
