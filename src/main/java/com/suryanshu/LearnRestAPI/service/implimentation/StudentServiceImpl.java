package com.suryanshu.LearnRestAPI.service.implimentation;

import com.suryanshu.LearnRestAPI.DTO.AddStudentRequestDTO;
import com.suryanshu.LearnRestAPI.DTO.StudentDTO;
import com.suryanshu.LearnRestAPI.entity.Student;
import com.suryanshu.LearnRestAPI.repositery.StudentRepositery;
import com.suryanshu.LearnRestAPI.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepositery studentRepositery;
    private final ModelMapper modelMapper;

    @Override
    public List<StudentDTO> getAllStudents() {
        return studentRepositery.findAll()
                .stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .toList();
    }

    @Override
    public StudentDTO getStudentById(Long id) {
        Student student = studentRepositery.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public StudentDTO createNewStudent(AddStudentRequestDTO addStudentRequestDTO) {
        Student newStudent = modelMapper.map(addStudentRequestDTO, Student.class);
        Student savedStudent = studentRepositery.save(newStudent);
        return modelMapper.map(savedStudent, StudentDTO.class);
    }
    @Override
    public void deleteStudentById(Long id) {
        if (!studentRepositery.existsById(id)) {
            throw new IllegalArgumentException("Student not found");
        }
        studentRepositery.deleteById(id);
    }

    @Override
    public StudentDTO updateStudent(Long id, AddStudentRequestDTO addStudentRequestDTO) {
        Student student = studentRepositery.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));
        modelMapper.map(addStudentRequestDTO,student);
        student=studentRepositery.save(student);
        return modelMapper.map(student,StudentDTO.class);

    }

    @Override
    public StudentDTO updatePartialStudent(Long id, Map<String, Object> updates) {

        Student student = studentRepositery.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        updates.forEach((field, value) -> {
            switch (field) {

                case "name":
                    student.setName((String) value);
                    break;

                case "email":
                    student.setEmail((String) value);
                    break;

                default:
                    throw new IllegalArgumentException("Field not supported: " + field);
            }
        });

        Student savedStudent = studentRepositery.save(student);
        return modelMapper.map(savedStudent, StudentDTO.class);
    }



}
