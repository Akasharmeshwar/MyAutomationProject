package framework_core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EnvLoader {
	
	public static Properties load(String envName) {
		
		Properties p = new Properties();
		
		String path = "/rediffmail-automation/src/test/resources/env" +envName+ ".properties";
		
		try(FileInputStream fis = new FileInputStream(path)){
			
			p.load(fis);
			
		}catch(IOException e) {
			
			throw new RuntimeException("Failed to laod env : " +envName, e);
		}
		return p;
	}

}
