package com.courseapi.Course.API;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.jar.JarOutputStream;

@SpringBootApplication
public class CourseApiApplication {


	public static void main(String[] args) {
		SpringApplication.run(CourseApiApplication.class, args);
	}


}
