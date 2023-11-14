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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Getter
@Setter
@RequiredArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class HomeController {


    private final HomeServiceimpl homeService;

    @PostMapping("/homes")
    public ResponseEntity<Home> createPet(@RequestBody @Valid HomeRequest homeRequest) {
        return new ResponseEntity<>(homeService.createHome(homeRequest), HttpStatus.OK);
    }

    @PutMapping("/homes")
    public ResponseEntity<Home> putHome(@RequestBody @Valid Home home) {
        return new ResponseEntity<>(homeService.putHome(home), HttpStatus.OK);
    }

    @GetMapping("/homes")
    public ResponseEntity<List<Home>> getHomes() {
        return new ResponseEntity<>(homeService.getHomes(), HttpStatus.OK);
    }

    @GetMapping("/homes/{id}")
    public Optional<Home> getHome(@PathVariable Long id) {
        return homeService.getHome(id);
    }

    @DeleteMapping("/homes/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        homeService.deleteHome(id);
        return HttpStatus.OK;
    }


}
