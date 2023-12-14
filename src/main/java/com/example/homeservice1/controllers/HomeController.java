package com.example.homeservice1.controllers;

import com.example.homeservice1.entity.Home;
import com.example.homeservice1.request.HomeRequest;
import com.example.homeservice1.service.HomeServiceimpl;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@Getter
@Setter
@RequiredArgsConstructor
@RequestMapping(value = "/v2/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class HomeController {


    private final HomeServiceimpl homeService;
    private final KafkaTemplate<String, String> kafkaTemplate;


    @PostMapping("/homes")
    public ResponseEntity<Home> createHome(@RequestHeader String token, @RequestBody @Valid HomeRequest homeRequest) {
        return new ResponseEntity<>(homeService.createHome(token, homeRequest), HttpStatus.OK);
    }

    @PutMapping("/homes/{id}")
    public ResponseEntity<Home> putHome(@RequestParam Long id,@RequestHeader String token, @RequestBody @Valid Home put) {
        homeService.putHome(id, token, put);
        return new ResponseEntity<>(put, HttpStatus.OK);
    }

    @GetMapping("/homes")
    public ResponseEntity<List<Home>> getHomes(@RequestHeader String token) {
        return new ResponseEntity<>(homeService.getHomes(token), HttpStatus.OK);
    }

    @GetMapping("/homes/{id}")
    public Optional<Home> getHome(@RequestHeader String token, @PathVariable Long id) {
        return homeService.getHome(token,id);
    }

    @DeleteMapping("/homes/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        homeService.deleteHomes(id);
        kafkaTemplate.send("home-deleted", "home deleted");
        kafkaTemplate.send("home-deleted", "rooms deleted");
        return HttpStatus.OK;
    }




}
