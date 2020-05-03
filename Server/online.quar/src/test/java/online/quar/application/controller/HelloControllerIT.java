/*TODO:
This is supposed to be a full stack test

taken from:
https://spring.io/guides/gs/spring-boot/
 */

//package online.quar.application.controller;
//
//import static org.assertj.core.api.Assertions.*;
//
//import java.net.URL;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.ResponseEntity;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class HelloControllerIT {
//
//    @org.springframework.boot.web.server.LocalServerPort
//    private int port;
//
//    private URL base;
//
//    @org.springframework.beans.factory.annotation.Autowired
//    private TestRestTemplate template;
//
//    @org.junit.jupiter.api.BeforeEach
//    public void setUp() throws Exception {
//        this.base = new URL("https://localhost:" + port + "/controllerTest");
//    }
//
//    @org.junit.jupiter.api.Test
//    public void getHello() throws Exception {
//        ResponseEntity<String> response = template.getForEntity(base.toString(),
//                String.class);
//        assertThat(response.getBody()).isEqualTo("Hello World, We are TeamNull!");
//    }
//}