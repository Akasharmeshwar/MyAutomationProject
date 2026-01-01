package dataProviders;

import org.testng.annotations.DataProvider;

public class ComposeDataProvider {
	@DataProvider(name = "attachments")
	public static Object[][] attachments() {
		return new Object[][] { { "src/test/resources/data/sample.pdf", true },

				{ "src/test/resources/data/large.zip", false },

				{ "src/test/resources/data/eicar.txt", false } };
	}
}
