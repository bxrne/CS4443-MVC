package com.ise.RMIS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration;

@SpringBootApplication(exclude = WebSocketServletAutoConfiguration.class)
public class RmisApplication {

	public static void main(String[] args) {
		SpringApplication.run(RmisApplication.class, args);
	}

}
