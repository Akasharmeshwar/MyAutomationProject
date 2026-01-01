package reporting;

import io.qameta.allure.Attachment;

public class Reporter {
	
	@Attachment(value = "Failure Screenshot", type = "image/png")
	
	public static byte[] attachImage(byte[] bytes) {
		
		return bytes;
	}

}
