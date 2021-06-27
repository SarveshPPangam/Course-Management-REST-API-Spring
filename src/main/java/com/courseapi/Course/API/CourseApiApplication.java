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

	@Bean
	CommandLineRunner commandLineRunner(){
		return args -> {
//			Course course = new Course();
//			course.setName("Machine Learning");
//			course.setCreatedAt(new Timestamp(System.currentTimeMillis()));
//			Student student = new Student();
			Optional<Course> course = courseRepository.findById(2);
//			student.setCreatedAt(new Timestamp(System.currentTimeMillis()));
//			student.setFirstName("f");
//			student.setLastName("l");
//			student.addCourse(course.get());
			Teacher teacher = new Teacher();
//			teacher.setFirstName("tf");
//			teacher.setLastName("tl");
//			teacher.addCourse(course.get());
//			teacher.addStudents(course.get().getStudents());
//
//			Optional<Teacher> teacher1 = teacherRepository.findById(2);
//
//			Student student = studentService.findById(4);
//			System.out.println(student.getCourses());
//			student.unEnroll(course.get());
//			studentService.save(student);
//			courseRepository.save(course.get());
//			teacherRepository.save(teacher1.get());
//			//student.enroll(course.get());
//			System.out.println("stu"+student.getCourses());
//			System.out.println("stu"+student.getTeachers());
//			System.out.println("cour"+course.get().getStudents());
//			System.out.println("tea"+teacher1.get().getStudents());
			//teacherRepository.save(teacher);
			//studentService.save(student);
		};
	}

}
