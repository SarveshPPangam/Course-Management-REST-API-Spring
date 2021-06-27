package com.courseapi.Course.API.models;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class CourseDTO {
    private int id;
    private String name;
    private Timestamp createdAt;
    private TeacherInfo teacher;
    private Set<StudentInfo> students;

    public CourseDTO(Course course){
        this.id = course.getId();
        this.name = course.getName();
        this.createdAt = course.getCreatedAt();
        this.teacher = course.getTeacher()!=null ? new TeacherInfo(course.getTeacher()) : null;
        if(course.getStudents()!=null) {
            this.students = course.getStudents().stream().map(student1 -> {
                return new StudentInfo(student1);
            }).collect(Collectors.toSet());
        }else{
            this.students=null;
        }
    }


}
