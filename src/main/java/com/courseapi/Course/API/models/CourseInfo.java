package com.courseapi.Course.API.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class CourseInfo {
    private int id;
    private String courseName;
    private Timestamp createdAt;

    public CourseInfo(Course course){
        this.id = course.getId();
        this.courseName = course.getName();
        this.createdAt = course.getCreatedAt();
    }
}
