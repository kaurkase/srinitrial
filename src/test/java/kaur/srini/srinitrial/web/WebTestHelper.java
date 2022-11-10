package kaur.srini.srinitrial.web;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.http.HttpHeaders;

public class WebTestHelper {

	public static String extractCSRF(HttpHeaders headers) {
		List<String> cookies = headers.get(HttpHeaders.SET_COOKIE);
		String token = null;
		for (String cookie: cookies) {
			if (cookie.contains("XSRF-TOKEN=")) {
				token= Pattern.compile("XSRF-TOKEN=(.*);").matcher(cookie).results().findFirst().get().group(1);
			}
		}
		return token;
	}

	public static String extractSessionCookie(HttpHeaders headers) {
		List<String> cookies = headers.get(HttpHeaders.SET_COOKIE);
		for (String cookie: cookies) {
			if (cookie.contains("JSESSIONID")) {
				return cookie;
			}
		}
		return null;
	}
}
