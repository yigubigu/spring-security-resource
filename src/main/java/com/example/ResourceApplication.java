package com.example;

import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
class ResourceApplication extends WebSecurityConfigurerAdapter {

	@Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http.cors().and().authorizeRequests()
	      .anyRequest().authenticated();
	  }
	
	@RequestMapping("/")
	@CrossOrigin(origins="*", maxAge=3600, allowedHeaders={"x-auth-token", "x-requested-with"})
	public Message home() {
		return new Message("Hello World");
	}

	public static void main(String[] args) {
		SpringApplication.run(ResourceApplication.class, args);
	}
	
	 @Bean
	  HeaderHttpSessionStrategy sessionStrategy() {
	    return new HeaderHttpSessionStrategy();
	  }


}

class Message {
	private String id = UUID.randomUUID().toString();
	private String content;

	public Message(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}