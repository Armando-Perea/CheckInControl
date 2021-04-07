package com.java.project.checkin.client;

import java.util.logging.Logger;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import com.java.project.checkin.models.CheckIn;

public class CheckInClient {

	public static Logger logger = Logger.getLogger(CheckInClient.class.getName());
	
	public static final String SYSTEM_URL = "http://localhost:3001/checkincontrol/system";

	public static CheckIn[] getAllCheckIn() {
		HttpHeaders headers = new HttpHeaders();
		CheckIn[] checkIn = {};
		try {
			headers.setContentType(MediaType.APPLICATION_JSON);
			RestTemplate restTemplate = new RestTemplate();
			String url = SYSTEM_URL + "/checkin/getAllCheckIn";
			HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
			ResponseEntity<CheckIn[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
					CheckIn[].class);
			checkIn = responseEntity.getBody();
			logger.info("Before Returning all checkIn");
			return checkIn;
		} catch (Exception ex) {
			logger.warning("ERROR GET ALL CHECKIN: " + ex);
		}
		return checkIn;
	}

	public static CheckIn getCheckInById(Integer id) {
		HttpHeaders headers = new HttpHeaders();
		CheckIn checkIn = new CheckIn();
		try {
			headers.setContentType(MediaType.APPLICATION_JSON);
			RestTemplate restTemplate = new RestTemplate();
			String url = SYSTEM_URL + "/checkin/getCheckInById/{id}";
			HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
			ResponseEntity<CheckIn> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
					CheckIn.class, id);
			checkIn = responseEntity.getBody();
			logger.info("Before Returning getCheckInById");
			return checkIn;
		} catch (Exception ex) {
			logger.warning("ERROR GET CHECKIN BY ID: " + ex);
		}
		return checkIn;
	}

	public static CheckIn[] getCheckInByName(String name) {
		HttpHeaders headers = new HttpHeaders();
		CheckIn[] checkIn = {};
		try {
			headers.setContentType(MediaType.APPLICATION_JSON);
			RestTemplate restTemplate = new RestTemplate();
			String url = SYSTEM_URL + "/checkin/getCheckInByName/{name}";
			HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
			ResponseEntity<CheckIn[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
					CheckIn[].class, name);
			checkIn = responseEntity.getBody();
			logger.info("Before Returning getCheckInByName");
			return checkIn;
		} catch (Exception ex) {
			logger.warning("ERROR GET CHECKIN NAME: " + ex);
		}
		return checkIn;
	}

	public static CheckIn addCheckIn(final CheckIn checkIn) {
		HttpHeaders headers = new HttpHeaders();
		CheckIn checkInResponse = new CheckIn();
		try {
			headers.setContentType(MediaType.APPLICATION_JSON);
			RestTemplate restTemplate = new RestTemplate();
			String url = SYSTEM_URL + "/checkin/createCheckIn";
			HttpEntity<CheckIn> requestEntity = new HttpEntity<CheckIn>(checkIn, headers);
			ResponseEntity<CheckIn> result = restTemplate.postForEntity(url, requestEntity, CheckIn.class);
			checkInResponse = result.getBody();
			logger.info("Before Returning addCheckIn");
			return checkInResponse;
		} catch (Exception ex) {
			logger.warning("addCheckIn: " + checkInResponse.toString());
		}
		return checkInResponse;
	}

	public static Integer updateCheckIn(final CheckIn checkIn) {
		HttpHeaders headers = new HttpHeaders();
		int status = 200;
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = SYSTEM_URL + "/checkin/updateCheckIn";
		HttpEntity<CheckIn> requestEntity = new HttpEntity<CheckIn>(checkIn, headers);
		try {
			ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
			HttpStatus statusResponse = ClientResponseHandler.getHttpResponse(result);
			status = statusResponse.value();
		} catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerExc) {
			if (HttpStatus.NOT_FOUND.equals(httpClientOrServerExc.getStatusCode())
					|| HttpStatus.INTERNAL_SERVER_ERROR.equals(httpClientOrServerExc.getStatusCode())) {
				status = httpClientOrServerExc.getRawStatusCode();
			}
			logger.warning("Catch Returning updateCheckIn " + checkIn.toString());
			return status;
		}
		logger.info("Before Returning updateCheckIn");
		return status;
	}

	public static Integer deleteCheckIn(Integer id) {
		HttpHeaders headers = new HttpHeaders();
		int status = 200;
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = SYSTEM_URL + "/checkin/deleteCheckIn/{id}";
		HttpEntity<CheckIn> requestEntity = new HttpEntity<CheckIn>(headers);
		try {
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class,
					id);
			HttpStatus statusResponse = ClientResponseHandler.getHttpResponse(response);
			status = statusResponse.value();
		} catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerExc) {
			logger.warning("ERROR deleteCheckIn: " + httpClientOrServerExc);
			status = httpClientOrServerExc.getRawStatusCode();
			return status;
		}
		logger.info("Before Returning deleteCheckIn");
		return status;
	}
	
	public static String truncateCheckin() {
		HttpHeaders headers = new HttpHeaders();
		String resp = "Not Processed";
		try {
			headers.setContentType(MediaType.APPLICATION_JSON);
			RestTemplate restTemplate = new RestTemplate();
			String url = SYSTEM_URL + "/checkin/truncateCheckin";
			HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
			ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
					String.class);
			resp = responseEntity.getBody();
			logger.info("Before Returning truncateCheckin");
			return resp;
		} catch (Exception ex) {
			logger.warning("ERROR TRUNCATE CHECKIN: " + ex);
		}
		return resp;
	}

}
