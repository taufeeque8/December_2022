package com.restfultutorial.restApi.schedule;

import com.restfultutorial.restApi.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	@Autowired
	RestTemplate restTemplate;

	@Scheduled(cron = "*/10 * * * * *")
	public void reportCurrentTime() {
		ResponseEntity<List<User>> response = restTemplate.exchange(
				"http://localhost:8080/users/", HttpMethod.GET,null, new ParameterizedTypeReference<List<User>>() {});
		List<User> users = response.getBody();
		log.info("The time is now {}", dateFormat.format(new Date()));
		for(User user : users){
			System.out.print(user.toString()+" ");
		}
		System.out.println();
	}
}