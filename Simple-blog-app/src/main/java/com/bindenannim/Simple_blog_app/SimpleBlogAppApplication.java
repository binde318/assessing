package com.bindenannim.Simple_blog_app;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SimpleBlogAppApplication {
		@Bean
		public ModelMapper modelMapper() {
			return new ModelMapper();
		}

	public static void main(String[] args) {
		SpringApplication.run(SimpleBlogAppApplication.class, args);
	}

}
