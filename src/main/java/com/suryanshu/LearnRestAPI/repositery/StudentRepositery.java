package com.suryanshu.LearnRestAPI.repositery;

import com.suryanshu.LearnRestAPI.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepositery extends JpaRepository<Student,Long> {

}
