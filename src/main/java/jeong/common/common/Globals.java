package jeong.common.common;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Globals {

	public static final String REAL_PATH = Globals.class.getResource("").getPath().substring(0, Globals.class.getResource("").getPath().lastIndexOf("jeong"));
	  public static final String GLOBALS_PROPERTIES_FILE = REAL_PATH + "props/globals.properties";

	  public static final String KAKAO_REST_API = Globals.getProperty(GLOBALS_PROPERTIES_FILE,"Globals.kakaoRestApi");
	  public static final String KAKAO_JS_API = Globals.getProperty(GLOBALS_PROPERTIES_FILE,"Globals.kakaoJsApi");
	  public static final String KAKAO_NATIVE_API = Globals.getProperty(GLOBALS_PROPERTIES_FILE,"Globals.kakaoNativeApi");
	  public static final String KAKAO_ADMIN_API = Globals.getProperty(GLOBALS_PROPERTIES_FILE,"Globals.kakaoAdminApi");
	  public static final String KAKAO_REDIRECT_URL = Globals.getProperty(GLOBALS_PROPERTIES_FILE,"Globals.kakaoRedirectURL");

	  public static String getProperty(String propertiesFile, String key){

			String value = "";
			Properties props = new Properties();
			FileInputStream fis = null;
			try{
				fis = new FileInputStream(propertiesFile);
				props.load(new BufferedInputStream(fis));
				value = props.getProperty(key).trim();
				fis.close();

			}catch(FileNotFoundException fne){
				fne.getMessage();
			}catch(IOException ioe){
				ioe.getMessage();
			}finally{
				try {
					if (fis != null) fis.close();
				} catch (Exception ex) {
				}
			}

			return value;
		  }
}
