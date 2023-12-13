package com.example.homeservice1.service;

import com.example.homeservice1.entity.Home;
import com.example.homeservice1.entity.Room;
import com.example.homeservice1.repository.HomeRepo;
import com.example.homeservice1.repository.RoomRepo;
import com.example.homeservice1.request.HomeRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

@SpringBootTest
class HomeServiceimplTest {

    @Mock
    private HomeRepo homeRepo;
    @Mock
    private RoomRepo roomRepo;
    @Autowired
    private HomeServiceimpl service;

    @BeforeEach
    void setUp() {
        service = new HomeServiceimpl(homeRepo, roomRepo);
    }

    @Test
    void getHomes() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoicHVwdXB1IiwiaWF0IjoxNzAyNDk1ODg1LCJleHAiOjE3MDI1ODIyODV9.npx2Fpvy9aoKYgiU6E7EURkKZXf_KW-fSbvMn5CU4wk";
        String apiUrl = "http://localhost:8081/api/token";
        String responseBody = "{\"id\":1, \"name\":\"Home1\", \"address\":\"Address1\", \"ownerid\":\"1\"}";
        HttpHeaders expectedHeaders = new HttpHeaders();
        expectedHeaders.add("token", token);
        ResponseEntity<String> mockResponse = new ResponseEntity<>(responseBody, HttpStatus.OK);

        RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);
        Mockito.when(restTemplateMock.exchange(apiUrl, HttpMethod.GET, new HttpEntity<>(expectedHeaders), String.class))
                .thenReturn(mockResponse);

        List<Home> expectedHomes = new ArrayList<>();
        Mockito.when(homeRepo.findByAllHouse(responseBody)).thenReturn(expectedHomes);

        List<Home> actualHomes = service.getHomes(token);
        assertEquals(expectedHomes, actualHomes);

    }


    @Test
    void createHome() {

        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoicHVwdXB1IiwiaWF0IjoxNzAyNDk1ODg1LCJleHAiOjE3MDI1ODIyODV9.npx2Fpvy9aoKYgiU6E7EURkKZXf_KW-fSbvMn5CU4wk";
        String apiUrl = "http://localhost:8081/api/token";
        String responseBody = "{\"id\":1, \"name\":\"Home1\", \"address\":\"Address1\", \"ownerid\":\"1\"}";
        HttpHeaders expectedHeaders = new HttpHeaders();
        expectedHeaders.add("token", token);
        ResponseEntity<String> mockResponse = new ResponseEntity<>(responseBody, HttpStatus.OK);

        RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);
        Mockito.when(restTemplateMock.exchange(apiUrl, HttpMethod.GET, new HttpEntity<>(expectedHeaders), String.class))
                .thenReturn(mockResponse);

        HomeRequest homeRequest = new HomeRequest();
        homeRequest.setName("Home1");
        homeRequest.setAddress("Address1");

        Home expectedHome = new Home();
        expectedHome.setId(1L);
        expectedHome.setName(homeRequest.getName());
        expectedHome.setAddress(homeRequest.getAddress());
        expectedHome.setOwnerid(responseBody);

        Mockito.when(homeRepo.save(Mockito.any(Home.class))).thenReturn(expectedHome);

        Home actualHome = service.createHome(token, homeRequest);
        Assertions.assertEquals(expectedHome, actualHome);

    }

    @Test
    void getSingleHome() {
        long id = 1L;
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoicHVwdXB1IiwiaWF0IjoxNzAyNDk1ODg1LCJleHAiOjE3MDI1ODIyODV9.npx2Fpvy9aoKYgiU6E7EURkKZXf_KW-fSbvMn5CU4wk";
        String apiUrl = "http://localhost:8081/api/token";
        String responseBody = "{\"id\":1, \"name\":\"Home1\", \"address\":\"Address1\", \"ownerid\":\"1\"}";
        HttpHeaders expectedHeaders = new HttpHeaders();
        expectedHeaders.add("token", token);
        ResponseEntity<String> mockResponse = new ResponseEntity<>(responseBody, HttpStatus.OK);

        RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);
        Mockito.when(restTemplateMock.exchange(apiUrl, HttpMethod.GET, new HttpEntity<>(expectedHeaders), String.class))
                .thenReturn(mockResponse);

        HomeRequest homeRequest = new HomeRequest();
        homeRequest.setName("Home1");
        homeRequest.setAddress("Address1");

        Home expectedHome = new Home();
        expectedHome.setId(id);
        expectedHome.setName(homeRequest.getName());
        expectedHome.setAddress(homeRequest.getAddress());
        expectedHome.setOwnerid(responseBody);
        Mockito.when(homeRepo.findByHouseId(Mockito.anyLong(), Mockito.anyString())).thenReturn(Optional.of(expectedHome));

        Optional<Home> actualHome = service.getHome(token, id);
        Assertions.assertEquals(expectedHome, actualHome.orElse(null));
    }

    @Test
    void deleteHomes() {
        long id = 1;
        Mockito.doReturn(Optional.of(new Room())).when(roomRepo).findById(id);

        service.deleteHomes(id);

        Mockito.verify(roomRepo, Mockito.times(1)).findById(id);
        Mockito.verify(homeRepo, Mockito.times(1)).deleteById(id);

    }
}