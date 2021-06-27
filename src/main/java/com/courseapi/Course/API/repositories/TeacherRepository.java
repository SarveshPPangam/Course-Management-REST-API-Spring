package com.courseapi.Course.API.repositories;

import com.courseapi.Course.API.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    @Transactional
    @Modifying
    @Query(value="delete from Teachers where id = ?1",
    nativeQuery = true)
    public void deleteByIdCustom(int id);

}
