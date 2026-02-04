package com.suryanshu.LearnRestAPI.service;

import com.suryanshu.LearnRestAPI.DTO.AddStudentRequestDTO;
import com.suryanshu.LearnRestAPI.DTO.StudentDTO;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface StudentService {

    List<StudentDTO> getAllStudents();

    StudentDTO getStudentById(Long id);

    StudentDTO createNewStudent(AddStudentRequestDTO addStudentRequestDTO);
    void deleteStudentById(Long id);


    StudentDTO updateStudent(Long id, AddStudentRequestDTO addStudentRequestDTO);

     StudentDTO updatePartialStudent(Long id, Map<String, Object> updates);
}
