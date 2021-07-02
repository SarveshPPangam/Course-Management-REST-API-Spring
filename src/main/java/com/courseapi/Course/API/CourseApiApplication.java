package com.courseapi.Course.API;

import com.courseapi.Course.API.models.Course;
import com.courseapi.Course.API.models.Student;
import com.courseapi.Course.API.models.Teacher;
import com.courseapi.Course.API.repositories.CourseRepository;
import com.courseapi.Course.API.repositories.StudentRepository;
import com.courseapi.Course.API.repositories.TeacherRepository;
import com.courseapi.Course.API.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	StudentService studentService;
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	TeacherRepository teacherRepository;

	public static void main(String[] args) {
		SpringApplication.run(CourseApiApplication.class, args);
	}


}
