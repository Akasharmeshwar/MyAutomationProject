package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotHelper {

	public static String captureScreenshot(String testName) {
		WebDriver driver = DriverFactory.getDriver();

		String projectRoot = System.getProperty("user.dir");
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

		String folderPath = projectRoot + File.separator + "Screenshot";
		String fileName = testName + "_" + timestamp + ".png";
		String fullPath = folderPath + File.separator + fileName;

		File folder = new File(folderPath);
		if (!folder.exists()) {
			boolean created = folder.mkdirs();
			System.out.println("Screenshot folder created: " + created);
		}

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(fullPath);
		try {
			Files.copy(src.toPath(), dest.toPath());
			System.out.println("Screenshot saved at: " + fullPath);
		} catch (IOException e) {
			System.err.println("Screenshot failed: " + e.getMessage());
		}

		return fullPath;
	}
}
