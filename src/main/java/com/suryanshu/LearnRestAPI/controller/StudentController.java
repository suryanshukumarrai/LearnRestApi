package com.suryanshu.LearnRestAPI.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suryanshu.LearnRestAPI.DTO.AddStudentRequestDTO;
import com.suryanshu.LearnRestAPI.DTO.StudentDTO;
import com.suryanshu.LearnRestAPI.service.StudentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;


    @GetMapping
    public ResponseEntity<List<StudentDTO>> getStudents() {
        log.info("Fetching all students");
        return ResponseEntity.ok(studentService.getAllStudents());
    }
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentsById(@PathVariable Long id){
        log.info("Fetching student by id: {}", id);
        return ResponseEntity.ok(studentService.getStudentById(id));
    }
    @PostMapping
    public ResponseEntity<StudentDTO> createNewStudent(@RequestBody @Valid AddStudentRequestDTO addStudentRequestDTO) {
        log.info("Creating new student with email: {}", addStudentRequestDTO.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createNewStudent(addStudentRequestDTO));

    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<Void>deleteStudent(@PathVariable Long id){
        log.info("Deleting student by id: {}", id);
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO>updateStudent(@PathVariable Long id,
                                                   @RequestBody @Valid AddStudentRequestDTO addStudentRequestDTO){
        log.info("Updating student by id: {}", id);
        return ResponseEntity.ok(studentService.updateStudent(id,addStudentRequestDTO));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<StudentDTO>updatePartialStudent(@PathVariable Long id,
                                                          @RequestBody Map<String, Object>updates){
        log.info("Partially updating student by id: {}", id);
        return ResponseEntity.ok(studentService.updatePartialStudent(id, updates));
    }
}