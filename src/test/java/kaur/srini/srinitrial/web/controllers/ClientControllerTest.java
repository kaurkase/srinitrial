package kaur.srini.srinitrial.web.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;

import kaur.srini.srinitrial.data.dbobjects.Client;
import kaur.srini.srinitrial.data.dbobjects.Country;
import kaur.srini.srinitrial.data.dto.ClientList;
import kaur.srini.srinitrial.web.ApiError;
import kaur.srini.srinitrial.web.WebTestHelper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	private HttpHeaders headers;
	private BasicAuthenticationInterceptor bai;

	private static int usernameCount = 0;

	@BeforeEach
	public void beforeEach() {
		usernameCount++;
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		bai = new BasicAuthenticationInterceptor("user1", "password1");
		restTemplate.getRestTemplate().getInterceptors().add(bai);
	}

	@Test
	void postValidClientSucceeds() {
		setSessionAndCSFR(headers);
		Client requestClient = createValidClient();
		HttpEntity<Client> request = new HttpEntity<>(createValidClient(), headers);
		ResponseEntity<Client> responseEntity = restTemplate
			      .postForEntity(getPostClientUrl(), request, Client.class);
		Client result = responseEntity.getBody();
		assertNotNull(result.getId());
		result.setId(null);
		assertEquals(requestClient, result);
	}

	@Test
	void getClientListSucceeds() {
		setSessionAndCSFR(headers);
		Client requestClient = createValidClient();
		HttpEntity<Client> request = new HttpEntity<>(createValidClient(), headers);
		ResponseEntity<Client> responseEntity = restTemplate
			      .postForEntity(getPostClientUrl(), request, Client.class);
		ResponseEntity<ClientList> response = restTemplate.exchange(
				getPostClientUrl() + "/byuser", HttpMethod.GET, new HttpEntity<Object>(headers),
				ClientList.class);
		List<Client> clients = response.getBody().getClients();
		requestClient.setId(responseEntity.getBody().getId());
		assertTrue(clients.contains(requestClient));
	}

	@Test
	void postClientNoEmailSucceeds() {
		setSessionAndCSFR(headers);
		Client requestClient = createValidClient();
		requestClient.setEmail(null);
		HttpEntity<Client> request = new HttpEntity<>(requestClient, headers);
		ResponseEntity<Client> responseEntity = restTemplate
			      .postForEntity(getPostClientUrl(), request, Client.class);
		Client result = responseEntity.getBody();
		result.setId(null);
		assertEquals(requestClient, result);
	}

	@Test
	void postClientInvalidEmailReturnsValidationErrors() {
		setSessionAndCSFR(headers);
		Client requestClient = createValidClient();
		requestClient.setEmail("a");
		HttpEntity<Client> request = new HttpEntity<>(requestClient, headers);
		ResponseEntity<ApiError> responseEntity = restTemplate
			      .postForEntity(getPostClientUrl(), request, ApiError.class);
		ApiError error = responseEntity.getBody();
		assertEquals(error.getValidationErrors().get("email"), "E-mail is not valid");
	}

	@Test
	void postClientInvalidCountryReturnsValidationErrors() {
		setSessionAndCSFR(headers);
		Client requestClient = createValidClient();
		requestClient.getCountry().setCode("xxx");
		HttpEntity<Client> request = new HttpEntity<>(requestClient, headers);
		ResponseEntity<ApiError> responseEntity = restTemplate
			      .postForEntity(getPostClientUrl(), request, ApiError.class);
		ApiError error = responseEntity.getBody();
		assertEquals(error.getValidationErrors().get("country"), "country is not valid");
	}

	@Test
	void postInvalidClientReturnsValidationErrors() {
		setSessionAndCSFR(headers);
		Client requestClient = createValidClient();
		requestClient.setFirstName(null);
		HttpEntity<Client> request = new HttpEntity<>(requestClient, headers);
		ResponseEntity<ApiError> responseEntity = restTemplate
			      .postForEntity(getPostClientUrl(), request, ApiError.class);
		ApiError error = responseEntity.getBody();
		assertEquals(error.getValidationErrors().get("firstName"), "First name is mandatory");
	}

	@Test
	void notLoggedInGetClientsFails() {
		restTemplate.getRestTemplate().getInterceptors().remove(bai);
		ResponseEntity<String> getResponse = restTemplate.getForEntity(getPostClientUrl() + "/byuser", String.class);
		System.out.println(getResponse);
	}
	private void setSessionAndCSFR(HttpHeaders outgoingHeaders) {
		HttpEntity<Map<String, String>> request = new HttpEntity<>(Map.of("username", "user1", "password", "password1"));
		HttpHeaders incomingHeaders = restTemplate.postForEntity(getLoginUrl(), request, String.class).getHeaders();
		String csrfToken = WebTestHelper.extractCSRF(incomingHeaders);
		String session = WebTestHelper.extractSessionCookie(incomingHeaders);
		String cookies = session + ";" + "XSRF-TOKEN=" + csrfToken;
		outgoingHeaders.add(HttpHeaders.COOKIE, cookies);
		outgoingHeaders.add("X-XSRF-TOKEN", csrfToken);
	}

	private String getPostClientUrl() {
		return "http://localhost:" + port + "/clients";
	}

	private String getLoginUrl() {
		return "http://localhost:" + port + "/login";
	}

	private Client createValidClient() {
		Client client = new Client();
		client.setAddress("someAddress");
		Country country = new Country();
		country.setCode("EST");
		country.setName("Estonia");
		client.setCountry(country);
		client.setEmail("first.name.555@gmail.com");
		client.setFirstName("first");
		client.setLastName("name");
		client.setUsername("username" + usernameCount);
		return client;
	}
}
