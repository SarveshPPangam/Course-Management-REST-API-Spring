package com.courseapi.Course.API.repositories;

import com.courseapi.Course.API.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Query(value = "SELECT * FROM courses WHERE teacher_id=?1",
            nativeQuery = true)
    public Course findByTeacherId(int id);
}
