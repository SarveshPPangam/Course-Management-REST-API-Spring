package com.courseapi.Course.API.models;

import ch.qos.logback.core.status.StatusUtil;
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
@Setter
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Timestamp createdAt;



    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id")
    @JsonIgnore
    private Teacher teacher_id;

    public int getId() {
        return id;
    }

    @ManyToMany(cascade = CascadeType.MERGE,mappedBy = "courses", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Student> students;


    @Override
    public int hashCode() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    @JsonIgnore
    public Set<Student> getStudents() {
        return this.students;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    @JsonIgnore
    public Teacher getTeacher() {
        return this.teacher_id;
    }


    public void setTeacher_id(Teacher teacher_id) {
        this.teacher_id = teacher_id;
    }

    public void clearTeacher(){
        this.teacher_id = null;
    }

}
