package com.suryanshu.LearnRestAPI.controller;

import com.suryanshu.LearnRestAPI.DTO.AddStudentRequestDTO;
import com.suryanshu.LearnRestAPI.DTO.StudentDTO;
import com.suryanshu.LearnRestAPI.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;


    @GetMapping
    public ResponseEntity<List<StudentDTO>> getStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentsById(@PathVariable Long id){
        return ResponseEntity.ok(studentService.getStudentById(id));
    }
    @PostMapping
    public ResponseEntity<StudentDTO> createNewStudent(@RequestBody AddStudentRequestDTO addStudentRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createNewStudent(addStudentRequestDTO));

    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<Void>deleteStudent(@PathVariable Long id){
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO>updateStudent(@PathVariable Long id,
                                                   @RequestBody @Valid AddStudentRequestDTO addStudentRequestDTO){
        return ResponseEntity.ok(studentService.updateStudent(id,addStudentRequestDTO));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<StudentDTO>updatePartialStudent(@PathVariable Long id,
                                                          @RequestBody @Valid Map<String, Object>updates){
        return ResponseEntity.ok(studentService.updatePartialStudent(id, updates));
    }
}