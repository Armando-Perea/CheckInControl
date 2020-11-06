package com.java.project.checkin.client;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

public class ClientResponseHandler {

	public static HttpStatus getHttpResponse(ResponseEntity<String> response) {
		response.getBody();
		HttpStatus statusCode = response.getStatusCode();
		if (statusCode.is2xxSuccessful()) {
			return HttpStatus.ACCEPTED;
		} else if (statusCode.is4xxClientError()) {
			throw new HttpClientErrorException(statusCode);
		} else if (statusCode.is5xxServerError()) {
			throw new HttpServerErrorException(statusCode);
		} else {
			return statusCode;
		}

	}

}
