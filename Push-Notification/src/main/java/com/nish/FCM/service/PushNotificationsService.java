package com.nish.FCM.service;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PushNotificationsService {
	private static final String FIREBASE_SERVER_KEY = "AAAAcRj7Xow:APA91bHfjoBxVJ9_sHSWa5rp4sDlVLtx6rf9T0bmC2OxBYwth9Hogs6X_7a6d_ufUc0_6q9yGcnAXbHtmFUYbi4kmuIYrOsyAnO3oT5bQd0c_-UeklxCXrWmF6ZWrEl_XLc_4lUhgtUM";
	private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";
	
	 @Async
	  public CompletableFuture<String> send(HttpEntity<String> entity) {
	 
	    RestTemplate restTemplate = new RestTemplate();
	    ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
	    interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
	    interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
	    restTemplate.setInterceptors(interceptors);
	 
	    String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, entity, String.class);
	 
	    return CompletableFuture.completedFuture(firebaseResponse);
	 }
}
