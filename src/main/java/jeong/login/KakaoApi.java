package jeong.login;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class KakaoApi {
	
	private static final String API_SERVER_HOST = "https://kapi.kakao.com";
	private static final String AUTH_SERVER_HOST = "https://kauth.kakao.com";
	
	private static final String USER_TOKEN_PATH = "/oauth/token";
	
	private static final String USER_ME_PATH = "/v2/user/me";
	private static final String USER_LOGOUT = "/v1/user/logout";
	
	public String getAccessToken(String authorize_code) {
		String token = "aa721207ecbcf6c84136cc80aeb2a38e";
		String re_uri = "http://localhost:8000/login";
		String access_Token = "";
		String refresh_Token = "";		
		//String reqURL = "https://kauth.kakao.com/oauth/token";
		
		try {
			URL url = new URL(AUTH_SERVER_HOST+USER_TOKEN_PATH);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			// POST 요청을 위해 기본값이 false인 setDoOutput을 true로 
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=" + token);
			sb.append("&redirect_uri=" + re_uri);
			sb.append("&code=" + authorize_code);
			
			bw.write(sb.toString());
			bw.flush();
			
			// 결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode = " + responseCode);
			
			// 요청을 통해 얻은 JSON 타입의 Response 메시지 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";
			
			while((line = br.readLine()) != null) {
				result += line;
			}
			
			System.out.println("response body : " + result);
			
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);
			
			access_Token = element.getAsJsonObject().get("access_token").getAsString();
			refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
			
			System.out.println("access_token : " + access_Token);
			System.out.println("refresh_Token : " + refresh_Token);
			
			br.close();
			bw.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return access_Token;
	}
	
	public HashMap<String, Object> getUserInfo(String access_Token) {
		HashMap<String, Object> userInfo = new HashMap<>();
		
		//String reqURL = "https://kapi.kakao.com/v2/user/me";
		
		try {
			URL url = new URL(API_SERVER_HOST+USER_ME_PATH);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();			
			conn.setRequestMethod("POST");
			
			// 요청에 필요한 Header에 포함될 내용
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);
			
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String line = "";
			String result = "";
			
			while((line = br.readLine()) != null) {
				result += line;
			}
			
			System.out.println("response Body : " + result);
			
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);
			
			JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
			JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
			
			String nickname = properties.getAsJsonObject().get("nickname").getAsString();
			String email = kakao_account.getAsJsonObject().get("email").getAsString();

			userInfo.put("nickname", nickname);
			userInfo.put("email", email);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return userInfo;
	}
	
	public void kakaoLogout(String access_Token) {
		try {
			URL url = new URL(API_SERVER_HOST+USER_LOGOUT);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);
			
			int responseCode = conn.getResponseCode();
			
			System.out.println("responseCode : " + responseCode);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String result = "";
			String line = "";
			
			while((line = br.readLine()) != null) {
				result += line;
			}
			
			System.out.println(result);
					
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
