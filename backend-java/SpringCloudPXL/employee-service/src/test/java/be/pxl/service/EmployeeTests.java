package be.pxl.service;


import be.pxl.services.domain.Employee;
import be.pxl.services.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import be.pxl.services.EmployeeServiceApplication;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = EmployeeServiceApplication.class)
@Testcontainers
@AutoConfigureMockMvc
public class EmployeeTests {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Container
    private static MySQLContainer<?> sqlContainer = new MySQLContainer<>("mysql:8.0.36");
    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void cleanDatabase() {
        employeeRepository.deleteAll();
    }

    @DynamicPropertySource
    static void registerMySqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", sqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", sqlContainer::getUsername);
        registry.add("spring.datasource.password", sqlContainer::getPassword);
    }


    @Test
    public void testCreateEmployee() throws Exception {
        Employee employee = Employee.builder()
                .age(21)
                .name("Dries")
                .position("Student")
                .build();

        String employeeString = objectMapper.writeValueAsString(employee);

    mockMvc.perform(MockMvcRequestBuilders.post("/api/employee")
        .contentType(MediaType.APPLICATION_JSON)
        .content(employeeString))
        .andExpect(status().isCreated());

    assertEquals(1, employeeRepository.findAll().size());

    
    }

    @Test
    public void testGetAllEmployees() throws Exception {
    employeeRepository.deleteAll();

    Employee employee = Employee.builder()
        .age(30)
        .name("Alice")
        .position("Developer")
        .departmentId(2L)
        .organizationId(3L)
        .build();

    String employeeString = objectMapper.writeValueAsString(employee);

    mockMvc.perform(MockMvcRequestBuilders.post("/api/employee")
        .contentType(MediaType.APPLICATION_JSON)
        .content(employeeString))
        .andExpect(status().isCreated());

    mockMvc.perform(MockMvcRequestBuilders.get("/api/employee")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(result -> {
            String content = result.getResponse().getContentAsString();
            Employee[] employees = objectMapper.readValue(content, Employee[].class);
            assertEquals(1, employees.length);
            assertEquals("Alice", employees[0].getName());
        });
    }

    @Test
    public void testGetEmployeeById() throws Exception {
    employeeRepository.deleteAll();

    Employee employee = Employee.builder()
        .age(28)
        .name("Bob")
        .position("Analyst")
        .departmentId(5L)
        .organizationId(7L)
        .build();

    String employeeString = objectMapper.writeValueAsString(employee);

    String response = mockMvc.perform(MockMvcRequestBuilders.post("/api/employee")
        .contentType(MediaType.APPLICATION_JSON)
        .content(employeeString))
        .andExpect(status().isCreated())
        .andReturn().getResponse().getContentAsString();

    Employee saved = objectMapper.readValue(response, Employee.class);
    Long id = saved.getId();

    mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/" + id)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(result -> {
            Employee fetched = objectMapper.readValue(result.getResponse().getContentAsString(), Employee.class);
            assertEquals("Bob", fetched.getName());
            assertEquals(id, fetched.getId());
        });
    }

    @Test
    public void testGetEmployeesByDepartment() throws Exception {
    employeeRepository.deleteAll();

    Employee employee = Employee.builder()
        .age(35)
        .name("Carol")
        .position("Manager")
        .departmentId(10L)
        .organizationId(20L)
        .build();

    String employeeString = objectMapper.writeValueAsString(employee);

    mockMvc.perform(MockMvcRequestBuilders.post("/api/employee")
        .contentType(MediaType.APPLICATION_JSON)
        .content(employeeString))
        .andExpect(status().isCreated());

    mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/department/10")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(result -> {
            String content = result.getResponse().getContentAsString();
            Employee[] employees = objectMapper.readValue(content, Employee[].class);
            assertEquals(1, employees.length);
            assertEquals("Carol", employees[0].getName());
        });
    }

    @Test
    public void testGetEmployeesByOrganization() throws Exception {
    employeeRepository.deleteAll();

    Employee employee = Employee.builder()
        .age(40)
        .name("Dave")
        .position("Director")
        .departmentId(15L)
        .organizationId(30L)
        .build();

    String employeeString = objectMapper.writeValueAsString(employee);

    mockMvc.perform(MockMvcRequestBuilders.post("/api/employee")
        .contentType(MediaType.APPLICATION_JSON)
        .content(employeeString))
        .andExpect(status().isCreated());

    mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/organization/30")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(result -> {
            String content = result.getResponse().getContentAsString();
            Employee[] employees = objectMapper.readValue(content, Employee[].class);
            assertEquals(1, employees.length);
            assertEquals("Dave", employees[0].getName());
        });
    }

}
