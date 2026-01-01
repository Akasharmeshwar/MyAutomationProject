package dataProviders;

import org.testng.annotations.DataProvider;

public class LoginDataProvider {
	@DataProvider(name = "invalidLogins")
	public static Object[][] invalidLogins() {
		return new Object[][] { { "user@rediffmail.com", "wrongpass" }, { "", "somepass" },
				{ "user@rediffmail.com", "" }, { "invalid@@mail.com", "pass123" } };
	}
}
