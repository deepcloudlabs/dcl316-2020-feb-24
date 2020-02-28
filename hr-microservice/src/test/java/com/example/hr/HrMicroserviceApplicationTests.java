package com.example.hr;

import com.example.hr.entity.Department;
import com.example.hr.entity.Employee;
import com.example.hr.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = HrMicroserviceApplication.class)
@AutoConfigureMockMvc
class HrMicroserviceApplicationTests {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private EmployeeRepository empRepo;

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
                .andExpect(jsonPath("identity", is("10231002100")));
    }

    @Test
    public void getTwoEmployees() throws Exception{
        Employee jack = new Employee();
        jack.setIdentity("10231002100");
        jack.setFullname("Jack Bauer");
        jack.setIban("TR282238840087568690127692");
        jack.setSalary(100000);
        jack.setPhoto(null);
        jack.setBirthYear(1976);
        jack.setFulltime(true);
        jack.setDepartment(Department.IT);
        Employee kate = new Employee();
        kate.setIdentity("14171091712");
        kate.setFullname("Kate Austen");
        kate.setIban("TR881879375711450419301043");
        kate.setSalary(200000);
        kate.setPhoto(null);
        kate.setBirthYear(1986);
        kate.setFulltime(true);
        kate.setDepartment(Department.FINANCE);
        Page page = Mockito.mock(Page.class);
        Mockito.when(page.getContent())
                .thenReturn(Arrays.asList(jack,kate));
        Mockito.when(empRepo.findAll(PageRequest.of(0,25)))
                .thenReturn(page);

        mvc.perform(get("/employees?page=0&size=25"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].identity",Matchers.is("10231002100")))
                .andExpect(jsonPath("$[1].identity",Matchers.is("14171091712")));


    }
}
