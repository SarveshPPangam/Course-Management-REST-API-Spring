package com.courseapi.Course.API.models;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private Timestamp createdAt;
    private String address;
    private long phoneNumber;


    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "student_courses",
            joinColumns = @JoinColumn(
                    name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "Course_id", referencedColumnName = "id"))
    private Set<Course> courses = new HashSet<Course>();


    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "students", fetch = FetchType.EAGER)
    private Set<Teacher> teachers = new HashSet<Teacher>();


    public String getAddress() {
        return address;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void enroll(Course course){
        this.courses.add(course);
        course.getTeacher().addStudents(Set.of(this));
    }


    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    @JsonIgnore
    public Set<Course> getCourses() {
        return courses;
    }

    @JsonIgnore
    public Set<Teacher> getTeachers() {
        return teachers;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", createdAt=" + createdAt +
                ", address='" + address + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }

    @Override
    public int hashCode() {
        return id;
    }

    public void unEnroll(Course course) {
        this.courses.remove(course);
        course.getTeacher().removeStudent(this);
    }

    public Student setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Student setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Student setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Student setAddress(String address) {
        this.address = address;
        return this;
    }

    public Student setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
