package kaur.srini.srinitrial.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;

public class WebTestHelperTest {

	private static HttpHeaders headers;

	@BeforeAll
	static void beforeAll() {
		headers = new HttpHeaders();
		List<String> cookies = List.of("XSRF-TOKEN=f914df1c-a9b4-47f0-8e09-fec443159ada; Path=/", "JSESSIONID=F06406DE23408108B2BB2EE519ACD720; Path=/; HttpOnly");
		headers.put(HttpHeaders.SET_COOKIE, cookies);
	}

	@Test
	void testCSRFTokenExtraction() {
		assertEquals("f914df1c-a9b4-47f0-8e09-fec443159ada", WebTestHelper.extractCSRF(headers));
	}

	@Test
	void testSessionExtraction() {
		assertEquals("JSESSIONID=F06406DE23408108B2BB2EE519ACD720; Path=/; HttpOnly", WebTestHelper.extractSessionCookie(headers));
	}

}
