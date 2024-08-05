package com.example.template;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = labshopcontracttest.OrderApplication.class)
@AutoConfigureMockMvc
@AutoConfigureStubRunner(
    stubsMode = StubRunnerProperties.StubsMode.LOCAL,
    ids = "labshopcontracttest:inventory:+:stubs:8090"
)
@ActiveProfiles("test")
public class InventoryContractTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    RestTemplate restTemplate;
    
    @Test
    public void getInventory_stub_test() throws Exception {
        String url = "http://localhost:8090/inventories/search/findByTestInventory/1";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        
        String responseString = response.getBody();
        DocumentContext parsedJson = JsonPath.parse(responseString);
        // and:
        // examples
        Assertions
            .assertThat(parsedJson.read("$.id", Long.class))
            .isGreaterThan(0L);
        Assertions
            .assertThat(parsedJson.read("$.stock", Integer.class))
            .isGreaterThan(0);
    }
}
