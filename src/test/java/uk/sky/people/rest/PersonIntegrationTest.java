package uk.sky.people.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import uk.sky.people.dto.PersonDTO;
import uk.sky.people.dto.PersonReqDTO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)// random port will assign its test to a random port to ensure itll run
// above line loads the app context with all your beans
@AutoConfigureMockMvc  // sets up the testing library
@Sql(scripts = {"classpath:person-schema.sql", "classpath:person-date.sql"})
public class PersonIntegrationTest {


    @Autowired // tells Spring to inject the mvc object into my test class
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void testCreate() throws Exception {
//        SETTING UP THE REQUEST

        PersonReqDTO newPerson = new PersonReqDTO("Anthony",33,"Dev", "somechars");
        String newPersonAsJSON = this.mapper.writeValueAsString(newPerson);
//        body, method, url, content-type
        RequestBuilder req = MockMvcRequestBuilders.post("/create").content(newPersonAsJSON).contentType(MediaType.APPLICATION_JSON);
//      CHECK THE RESPONSE STATUS
        ResultMatcher checkStatus = MockMvcResultMatchers.status().isCreated();
        PersonDTO created = new PersonDTO("Anthony", 33, "Dev");
//      CHECK THE RESPONSE BODY
        ResultMatcher checkBody = MockMvcResultMatchers.content().json(this.mapper.writeValueAsString(created));
//      DO THE REQ AND CHECK THE RESPONSE

        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
    }

}
