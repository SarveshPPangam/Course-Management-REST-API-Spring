package com.courseapi.Course.API.models;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class TeacherDTO {
    private int id;
    private String firstName;
    private String lastName;
    private Timestamp createdAt;
    private String address;
    private long phoneNumber;
    private Set<StudentInfo> students ;
    private Set<CourseInfo> courses ;

    public TeacherDTO(Teacher teacher){
        this.id = teacher.getId();
        this.firstName = teacher.getFirstName();
        this.lastName = teacher.getLastName();
        this.createdAt = teacher.getCreatedAt();
        this.address = teacher.getAddress();
        this.phoneNumber = teacher.getPhoneNumber();
        this.students = teacher.getStudents().stream().map(student1 -> {
            return new StudentInfo(student1 );
        }).collect(Collectors.toSet());
        this.courses = teacher.getCourses().stream().map(course -> {
            return new CourseInfo(course);
        }).collect(Collectors.toSet());
    }
}
