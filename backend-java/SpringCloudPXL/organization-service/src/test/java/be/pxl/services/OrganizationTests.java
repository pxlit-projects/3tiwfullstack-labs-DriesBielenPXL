package be.pxl.services;

import be.pxl.services.domain.Organization;
import be.pxl.services.repository.OrganizationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = OrganizationServiceApplication.class)
@Testcontainers
@AutoConfigureMockMvc
public class OrganizationTests {

    @Autowired
    MockMvc mockMvc;
        // ...existing code...

    // Ensure Spring will ignore malformed class files (like macOS dot-underscore files)
    // This needs to be set very early, before Spring's classpath scanning runs.
    static {
        System.setProperty("spring.classformat.ignore", "true");
    }

    @Container
    private static MySQLContainer<?> sqlContainer = new MySQLContainer<>("mysql:8.0.36");
    @Autowired
    private OrganizationRepository organizationRepository;

    @BeforeEach
    public void cleanDatabase() {
        organizationRepository.deleteAll();
    }

    @DynamicPropertySource
    static void registerMySqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", sqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", sqlContainer::getUsername);
        registry.add("spring.datasource.password", sqlContainer::getPassword);
    }

    @Test
    public void testGetOrganizationByIdEndpoints() throws Exception {
        Organization org = Organization.builder()
                .name("Acme")
                .address("Main St")
                .build();

        Organization saved = organizationRepository.save(org);

        mockMvc.perform(get("/api/organization/" + saved.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/organization/" + saved.getId() + "/with-departments")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/organization/" + saved.getId() + "/with-departments-and-employees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/organization/" + saved.getId() + "/with-employees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assertEquals(1, organizationRepository.findAll().size());
    }
}
