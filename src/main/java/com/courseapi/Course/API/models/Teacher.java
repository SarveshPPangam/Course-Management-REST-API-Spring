package com.courseapi.Course.API.models;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private Timestamp createdAt;
    private String address;
    private long phoneNumber;


    @OneToMany(mappedBy = "teacher_id", targetEntity = Course.class, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Course> courses = new HashSet<>();

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "teachers_students",
            joinColumns = @JoinColumn(
                    name = "teacher_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "student_id", referencedColumnName = "id"))
    @JsonIgnore
    private Set<Student> students = new HashSet<>();



    public int getId() {
        return id;
    }



    public String getAddress() {
        return address;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void addCourse(Course course){
        this.courses.add(course);
        course.setTeacher_id(this);
        this.students.addAll(course.getStudents());
    }


    public void addStudents(Set<Student> students){
        this.students.addAll(students);
    }

    public void removeStudent(Student student){
        this.students.remove(student);
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
    public Set<Student> getStudents() {

        return students;
    }

    @Override
    public String toString() {
        return "Teacher{" +
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

    public Teacher setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Teacher setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Teacher setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Teacher setAddress(String address) {
        this.address = address;
        return this;
    }

    public Teacher setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

}
