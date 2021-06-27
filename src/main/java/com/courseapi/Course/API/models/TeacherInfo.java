package com.courseapi.Course.API.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class TeacherInfo {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private long phoneNumber;

    public TeacherInfo(Teacher teacher){
        this.id = teacher.getId();
        this.firstName = teacher.getFirstName();
        this.lastName = teacher.getLastName();
        this.address = teacher.getAddress();
        this.phoneNumber = teacher.getPhoneNumber();
    }
}
