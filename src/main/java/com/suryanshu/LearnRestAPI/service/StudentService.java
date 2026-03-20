package com.suryanshu.LearnRestAPI.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.suryanshu.LearnRestAPI.DTO.AddStudentRequestDTO;
import com.suryanshu.LearnRestAPI.DTO.StudentDTO;
import com.suryanshu.LearnRestAPI.entity.Student;
import com.suryanshu.LearnRestAPI.exception.ResourceNotFoundException;
import com.suryanshu.LearnRestAPI.repositery.StudentRepositery;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final Set<String> ALLOWED_PATCH_FIELDS = Set.of("name", "email", "courses");
    private static final Set<String> PREDEFINED_COURSES = Set.of("English", "Math", "Science", "History", "Biology", "Chemistry", "Physics", "Geography", "Computer Science", "Sports");

    private final StudentRepositery studentRepositery;
    private final ModelMapper modelMapper;

    public List<StudentDTO> getAllStudents(){
        log.debug("Fetching all student records");
        return studentRepositery.findAll()
                .stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toList());
    }

    public StudentDTO getStudentById(Long id){
        log.debug("Fetching student by id: {}", id);
        Student student = studentRepositery.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));

        return modelMapper.map(student, StudentDTO.class);
    }

    public StudentDTO createNewStudent(AddStudentRequestDTO dto){
        log.debug("Creating student with email: {}", dto.getEmail());
        if (dto.getCourses() != null && !dto.getCourses().isBlank()) {
            validateCourses(dto.getCourses());
        }
        Student student = modelMapper.map(dto, Student.class);
        Student saved = studentRepositery.save(student);
        return modelMapper.map(saved, StudentDTO.class);
    }

    public void deleteStudentById(Long id){
        log.debug("Deleting student by id: {}", id);
        if(!studentRepositery.existsById(id)){
            throw new ResourceNotFoundException("Student not found with id " + id);
        }
        studentRepositery.deleteById(id);
    }

    public StudentDTO updateStudent(Long id, AddStudentRequestDTO dto){
        log.debug("Updating student by id: {}", id);
        Student student = studentRepositery.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));

        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        if (dto.getCourses() != null && !dto.getCourses().isBlank()) {
            validateCourses(dto.getCourses());
            student.setCourses(dto.getCourses());
        }

        Student updated = studentRepositery.save(student);
        return modelMapper.map(updated, StudentDTO.class);
    }

    public StudentDTO updatePartialStudent(Long id, Map<String,Object> updates){
        log.debug("Partially updating student by id: {}", id);
        if (updates == null || updates.isEmpty()) {
            throw new IllegalArgumentException("At least one field must be provided for patch update");
        }

        for (String key : updates.keySet()) {
            if (!ALLOWED_PATCH_FIELDS.contains(key)) {
                throw new IllegalArgumentException("Unsupported patch field: " + key);
            }
        }

        Student student = studentRepositery.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));

        if(updates.containsKey("name")){
            Object nameObj = updates.get("name");
            if (!(nameObj instanceof String name) || name.isBlank() || name.length() < 2 || name.length() > 30) {
                throw new IllegalArgumentException("name should be of 2 to 30 char and should not be blank");
            }
            student.setName(name);
        }

        if(updates.containsKey("email")){
            Object emailObj = updates.get("email");
            if (!(emailObj instanceof String email) || email.isBlank() || !EMAIL_PATTERN.matcher(email).matches()) {
                throw new IllegalArgumentException("put your email here and it should be vailid");
            }
            student.setEmail(email);
        }

        if(updates.containsKey("courses")){
            Object coursesObj = updates.get("courses");
            if (!(coursesObj instanceof String courses) || courses.isBlank() || courses.length() > 500) {
                throw new IllegalArgumentException("courses should not be blank and not exceed 500 characters");
            }
            validateCourses(courses);
            student.setCourses(courses);
        }

        Student updated = studentRepositery.save(student);
        return modelMapper.map(updated, StudentDTO.class);
    }

    private void validateCourses(String courses) {
        if (courses == null || courses.isBlank()) {
            return;
        }
        String[] courseArray = courses.split(",");
        for (String course : courseArray) {
            String trimmedCourse = course.trim();
            if (!PREDEFINED_COURSES.contains(trimmedCourse)) {
                throw new IllegalArgumentException("Invalid course: " + trimmedCourse + ". Allowed courses: " + PREDEFINED_COURSES);
            }
        }
    }
}