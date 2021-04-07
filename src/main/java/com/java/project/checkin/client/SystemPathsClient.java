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
import com.java.project.checkin.models.SystemPaths;

public class SystemPathsClient {
	
	public static Logger logger = Logger.getLogger(SystemPathsClient.class.getName());
	
	public static final String SYSTEM_URL = "http://localhost:3001/checkincontrol/system";

	public static SystemPaths[] getAllSystemPaths() {
		HttpHeaders headers = new HttpHeaders();
		SystemPaths[] checkIn = {};
		try {
			headers.setContentType(MediaType.APPLICATION_JSON);
			RestTemplate restTemplate = new RestTemplate();
			String url = SYSTEM_URL + "/systempaths/getAllSystemPaths";
			HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
			ResponseEntity<SystemPaths[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
					SystemPaths[].class);
			checkIn = responseEntity.getBody();
			logger.info("Before Returning all SystemPaths");
			return checkIn;
		} catch (Exception ex) {
			logger.warning("ERROR GET ALL CHECKIN: " + ex);
		}
		return checkIn;
	}
	
	public static SystemPaths getSystemPathById(Integer id) {
		HttpHeaders headers = new HttpHeaders();
		SystemPaths cartSale = new SystemPaths();
		try {
			headers.setContentType(MediaType.APPLICATION_JSON);
			RestTemplate restTemplate = new RestTemplate();
			String url = SYSTEM_URL + "/systempaths/getSystemPathById/{id}";
			HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
			ResponseEntity<SystemPaths> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, SystemPaths.class,
					id);
			cartSale = responseEntity.getBody();
			logger.info("Before Returning getSystemPathById");
			return cartSale;
		} catch (Exception ex) {
			logger.warning("ERROR GET CASH BY ID: " + ex);
		}
		return cartSale;
	}

	public static SystemPaths addSystemPath(final SystemPaths systemPaths) {
		HttpHeaders headers = new HttpHeaders();
		SystemPaths systemPathsResp = new SystemPaths();
		try {
			headers.setContentType(MediaType.APPLICATION_JSON);
			RestTemplate restTemplate = new RestTemplate();
			String url = SYSTEM_URL + "/systempaths/createSystemPath";
			HttpEntity<SystemPaths> requestEntity = new HttpEntity<SystemPaths>(systemPaths, headers);
			ResponseEntity<SystemPaths> result = restTemplate.postForEntity(url, requestEntity, SystemPaths.class);
			systemPathsResp = result.getBody();
			logger.info("Before Returning addSystemPath");
			return systemPathsResp;
		} catch (Exception ex) {
			logger.warning("addSystemPath: " + systemPathsResp.toString());
		}
		return systemPathsResp;
	}

	public static Integer updateSystemPath(final SystemPaths systemPaths) {
		HttpHeaders headers = new HttpHeaders();
		int status = 200;
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = SYSTEM_URL + "/systempaths/updateSystemPath";
		HttpEntity<SystemPaths> requestEntity = new HttpEntity<SystemPaths>(systemPaths, headers);
		try {
			ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
			HttpStatus statusResponse = ClientResponseHandler.getHttpResponse(result);
			status = statusResponse.value();
		} catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerExc) {
			if (HttpStatus.NOT_FOUND.equals(httpClientOrServerExc.getStatusCode())
					|| HttpStatus.INTERNAL_SERVER_ERROR.equals(httpClientOrServerExc.getStatusCode())) {
				status = httpClientOrServerExc.getRawStatusCode();
			}
			logger.warning("Catch Returning updateSystemPath " + systemPaths.toString());
			return status;
		}
		logger.info("Before Returning updateSystemPath");
		return status;
	}

	public static Integer deleteSystemPath(Integer id) {
		HttpHeaders headers = new HttpHeaders();
		int status = 200;
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String url = SYSTEM_URL + "/systempaths/deleteSystemPath/{id}";
		HttpEntity<SystemPaths> requestEntity = new HttpEntity<SystemPaths>(headers);
		try {
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class,
					id);
			HttpStatus statusResponse = ClientResponseHandler.getHttpResponse(response);
			status = statusResponse.value();
		} catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerExc) {
			logger.warning("ERROR deleteSystemPath: " + httpClientOrServerExc);
			status = httpClientOrServerExc.getRawStatusCode();
			return status;
		}
		logger.info("Before Returning deleteCash");
		return status;
	}
	
	public static String truncateSystemPaths() {
		HttpHeaders headers = new HttpHeaders();
		String resp = "Not Processed";
		try {
			headers.setContentType(MediaType.APPLICATION_JSON);
			RestTemplate restTemplate = new RestTemplate();
			String url = SYSTEM_URL + "/systempaths/truncateSystemPaths";
			HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
			ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
					String.class);
			resp = responseEntity.getBody();
			logger.info("Before Returning truncateSystemaPaths");
			return resp;
		} catch (Exception ex) {
			logger.warning("ERROR TRUNCATE PATHS " + ex);
		}
		return resp;
	}

}
