package com.example.homeservice1.service;

import com.example.homeservice1.entity.Home;
import com.example.homeservice1.entity.Room;
import com.example.homeservice1.repository.HomeRepo;
import com.example.homeservice1.repository.RoomRepo;
import com.example.homeservice1.request.HomeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HomeServiceimpl implements HomeService{

    private final HomeRepo homeRepo;
    private final RoomRepo roomRepo;

    @Override
    public Home createHome(String token, HomeRequest homeRequest) {
        RestTemplate restTemplate = new RestTemplate();
        String ResourceUrl = "http://localhost:8081/api/token";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("token", token);
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(ResourceUrl, HttpMethod.GET, entity, String.class);
        return homeRepo.save(Home.builder()
                .name(homeRequest.getName())
                .address(homeRequest.getAddress())
                .ownerid(response.getBody())
                .build());
    }

    @Override
    public Home putHome(Home home) {
        return homeRepo.save(home);
    }

    @Override
    public List<Home> getHomes(String token) {
        RestTemplate restTemplate = new RestTemplate();
        String ResourceUrl = "http://localhost:8081/api/token";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("token", token);
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(ResourceUrl, HttpMethod.GET, entity, String.class);
        return homeRepo.findByAllHouse(response.getBody());
    }

    @Override
    public Optional<Home> getHome(String token, Long id) {
        RestTemplate restTemplate = new RestTemplate();
        String ResourceUrl = "http://localhost:8081/api/token";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("token", token);
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(ResourceUrl, HttpMethod.GET, entity, String.class);
        return homeRepo.findByHouseId(id, response.getBody());
    }

//    @Override
//    public void deleteHome(Long id){
//        homeRepo.deleteById(id);
//    }

    @Override
    public void deleteHomes(Long id) {
        Room roomToDelete = roomRepo.findById(id).orElse(null);
        if (roomToDelete != null) {
            roomRepo.delete(roomToDelete);
        }
        homeRepo.deleteById(id);
    }

}
