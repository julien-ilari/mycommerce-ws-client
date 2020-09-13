package fr.mycommerce.rest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.ws.rs.ProcessingException;

import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import fr.mycommerce.exception.UnknownUriException;
import fr.mycommerce.ext.laposte.RestClientLaPoste;

//@RunWith(JUnitPlatform.class)
public class RestClientTest {

	private final static String baseUrl = "https://api.laposte.fr";

	/**
	 * Service externe pour api Laposte
	 */
	private RestClientLaPoste remoteApi;

	/**
	 * Initilisation
	 * 
	 * @throws MalformedURLException
	 */
	@BeforeEach
	public void init() throws MalformedURLException {
		final URL apiUrl = new URL(baseUrl);

		remoteApi = RestClientBuilder.newBuilder().property("microprofile.rest.client.disable.default.mapper", true)
				.baseUrl(apiUrl).build(RestClientLaPoste.class);

		assertNotNull(remoteApi, "Service indisponible ou mal configurer");
	}

	@Test
	public void testLaPosteApi() throws IOException, ProcessingException, UnknownUriException {
		final String jsonString = remoteApi.find("2 avenue de la libération,Gardanne 13120").toString();
		assertNotNull(jsonString);

		final ObjectMapper mapper = new ObjectMapper();
		// final List<ControlAddressResponse> pp1 = mapper.readValue(jsonString, new
		// TypeReference<List<ControlAddressResponse>>() {});

//		assertNotNull(pp1);
//		assertThat(pp1, Matchers.not(IsEmptyCollection.empty()));
	}
}
