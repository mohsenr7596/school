package com.example.school.controller;

import com.example.school.dto.StudentDTO;
import com.example.school.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    private StudentDTO studentDTO;

    @BeforeEach
    void setUp() {
        studentDTO = new StudentDTO();
        studentDTO.setName("Ali Alavi");
        studentDTO.setEmail("ali.alavi@example.com");
    }

    @Test
    void testCreateStudent() throws Exception {
        when(studentService.saveStudent(any(StudentDTO.class))).thenReturn(studentDTO);

        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Ali Alavi"))
                .andExpect(jsonPath("$.email").value("ali.alavi@example.com"));
    }

    @Test
    void testGetAllStudents() throws Exception {
        Page<StudentDTO> studentPage = new PageImpl<>(List.of(studentDTO));
        when(studentService.getAllStudents(anyInt(), anyInt())).thenReturn(studentPage);

        mockMvc.perform(get("/students")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Ali Alavi"))
                .andExpect(jsonPath("$.content[0].email").value("ali.alavi@example.com"));
    }

    @Test
    void testGetStudentById() throws Exception {
        when(studentService.getStudentById(anyLong())).thenReturn(studentDTO);

        mockMvc.perform(get("/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ali Alavi"))
                .andExpect(jsonPath("$.email").value("ali.alavi@example.com"));
    }

    @Test
    void testDeleteStudent() throws Exception {
        doNothing().when(studentService).deleteStudent(anyLong());

        mockMvc.perform(delete("/students/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testUpdateStudent() throws Exception {
        when(studentService.updateStudent(anyLong(), any(StudentDTO.class))).thenReturn(studentDTO);

        mockMvc.perform(put("/students/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ali Alavi"))
                .andExpect(jsonPath("$.email").value("ali.alavi@example.com"));
    }

    @Test
    void testCalculateGPA() throws Exception {
        when(studentService.calculateGPA(anyLong())).thenReturn(3.5);

        mockMvc.perform(get("/students/1/gpa"))
                .andExpect(status().isOk())
                .andExpect(content().string("3.5"));
    }

    @Test
    void testSearchStudents() throws Exception {
        Page<StudentDTO> studentPage = new PageImpl<>(List.of(studentDTO));
        when(studentService.searchStudents(anyString(), anyInt(), anyInt())).thenReturn(studentPage);

        mockMvc.perform(get("/students/search")
                        .param("keyword", "Ali")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Ali Alavi"))
                .andExpect(jsonPath("$.content[0].email").value("ali.alavi@example.com"));
    }
}