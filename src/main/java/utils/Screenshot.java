package utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {

	public static void capture(WebDriver driver, String name) {

		try {

			TakesScreenshot ts = (TakesScreenshot) driver;

			byte[] data = ts.getScreenshotAs(OutputType.BYTES);

			Path dir = Paths.get("target", "screenshots");

			Files.createDirectories(dir);

			String file = name + "--" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
					+ ".png";
			Files.write(dir.resolve(file), data);

		} catch (Exception ignored) {

		}
	}

}
