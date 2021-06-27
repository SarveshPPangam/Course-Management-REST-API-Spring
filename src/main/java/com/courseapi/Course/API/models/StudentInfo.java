package com.courseapi.Course.API.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class StudentInfo {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private long phoneNumber;

    public StudentInfo(Student student){
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.address = student.getAddress();
        this.phoneNumber = student.getPhoneNumber();
    }
}
