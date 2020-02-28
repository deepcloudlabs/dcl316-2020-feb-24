package com.example.hr;

import com.example.hr.entity.Department;
import com.example.hr.entity.Employee;
import com.example.hr.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
   webEnvironment = SpringBootTest.WebEnvironment.MOCK,
   classes = HrMicroserviceApplication.class)
@AutoConfigureMockMvc
class HrMicroserviceApplicationTests {
    @Autowired private MockMvc mvc;
    @Autowired private ObjectMapper mapper;
    @MockBean private EmployeeRepository empRepo;

    @Test
    void createEmployee_isOk() throws Exception {
        Employee jack = new Employee();
        jack.setIdentity("10231002100");
        jack.setFullname("Jack Bauer");
        jack.setIban("TR282238840087568690127692");
        jack.setSalary(100000);
        jack.setPhoto(null);
        jack.setBirthYear(1976);
        jack.setFulltime(true);
        jack.setDepartment(Department.IT);
        Mockito.when(empRepo.findOneByIdentity("10231002100"))
                .thenReturn(Optional.empty());
        Mockito.when(empRepo.save(jack))
                .thenReturn(jack);
        mvc.perform(post("/employees")
                   .content(mapper.writeValueAsString(jack))
                   .contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(jsonPath("identity", CoreMatchers.is("10231002100")));
    }

}
