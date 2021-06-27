package com.courseapi.Course.API.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class StudentDTO {
    private int id;
    private String firstName;
    private String lastName;
    private Timestamp createdAt;
    private String address;
    private long phoneNumber;
    private Set<TeacherInfo> teachers ;
    private Set<CourseInfo> courses ;

    public StudentDTO(Student student){
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.createdAt = student.getCreatedAt();
        this.address = student.getAddress();
        this.phoneNumber = student.getPhoneNumber();
        this.teachers = student.getTeachers().stream().map(teacher -> new TeacherInfo(teacher)).collect(Collectors.toSet());
        this.courses = student.getCourses().stream().map(course ->{
            CourseInfo courseInfo = new CourseInfo(course);
            return courseInfo;
        } ).collect(Collectors.toSet());
    }
}
