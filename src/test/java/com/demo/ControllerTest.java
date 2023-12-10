package com.demo;

import com.demo.entity.Socks;
import com.demo.repository.SocksRepository;
import com.demo.service.Operation;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Testcontainers
public class ControllerTest {
    @Container
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest")
            .withUsername("postgres")
            .withPassword("postgres");

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private DataSource dataSource;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    SocksRepository socksRepository;
    @Autowired
    private InitialData initialData;

    @BeforeEach
    void cleanTables() {
        socksRepository.deleteAll();
    }

    @Test
    void testPostgresql() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            assertThat(conn).isNotNull();
        }
    }

    @Test
    public void addSocksToDB_Successful() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("color", "red");
        jsonObject.put("cottonPart", 50);
        jsonObject.put("quantity", 10);

        mockMvc.perform(post("/api/socks/income")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpectAll(status().isOk(),
                        jsonPath("$.color").value("red"),
                        jsonPath("$.cottonPart").value(50),
                        jsonPath("$.quantity").value(10));
    }

    @Test
    public void addSocksForSocks_Successful() throws Exception {
        Socks socks = initialData.addSocks();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("color", socks.getColor());
        jsonObject.put("cottonPart", socks.getCottonPart());
        jsonObject.put("quantity", 5);

        mockMvc.perform(post("/api/socks/income")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpectAll(status().isOk(),
                        jsonPath("$.color").value(socks.getColor()),
                        jsonPath("$.cottonPart").value(socks.getCottonPart()),
                        jsonPath("$.quantity").value(socks.getQuantity() + 5));
    }

    @Test
    public void outComeSocks_Successful() throws Exception {
        Socks socks = initialData.addSocks();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("color", socks.getColor());
        jsonObject.put("cottonPart", socks.getCottonPart());
        jsonObject.put("quantity", socks.getQuantity());

        mockMvc.perform(post("/api/socks/outcome")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpectAll(status().isOk(),
                        jsonPath("$.color").value(socks.getColor()),
                        jsonPath("$.cottonPart").value(socks.getCottonPart()),
                        jsonPath("$.quantity").value(0));
    }

    @Test
    public void outComeSocks_NotQuantity() throws Exception {
        Socks socks = initialData.addSocks();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("color", socks.getColor());
        jsonObject.put("cottonPart", socks.getCottonPart());
        jsonObject.put("quantity", socks.getQuantity() + 1);

        mockMvc.perform(post("/api/socks/outcome")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void outComeSocks_NotFound() throws Exception {
        Socks socks = initialData.addSocks();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("color", "black");
        jsonObject.put("cottonPart", socks.getCottonPart());
        jsonObject.put("quantity", socks.getQuantity() + 1);

        mockMvc.perform(post("/api/socks/outcome")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getSocks_Successful() throws Exception {
        Socks socks = initialData.addSocks();
        Operation operation = Operation.equal;

        mockMvc.perform(get("/api/socks?color=red&operation=equal&cottonPart=50")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(status().isOk(),
                        jsonPath("$.color").value(socks.getColor()),
                        jsonPath("$.cottonPart").value(socks.getCottonPart()));
    }
    @Test
    public void getSocks_NotFound() throws Exception {
        Socks socks = initialData.addSocks();
        Operation operation = Operation.equal;

        mockMvc.perform(get("/api/socks?color=black&operation=equal&cottonPart=50")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(status().isNotFound());
    }

}
