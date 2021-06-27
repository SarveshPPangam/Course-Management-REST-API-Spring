package com.courseapi.Course.API.services;

import com.courseapi.Course.API.models.Course;
import com.courseapi.Course.API.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public List<Course> getAll(){
        return courseRepository.findAll((Sort.by(Sort.Direction.ASC, "name")));
    }

    public Course findById(int id){
        Optional<Course> course = courseRepository.findById(id);
        if(course.isPresent()) return course.get();
        else throw new RuntimeException("Course not found for id: "+id);
    }

    public Optional<Course> findByTeacherId(int id){
        Optional<Course> course = Optional.ofNullable(courseRepository.findByTeacherId(id));
        return course;
    }

    public void deleteById(int id){
        courseRepository.deleteById(id);
    }

    public Course save(Course course){
        return courseRepository.save(course);
    }
}
