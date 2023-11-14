package com.example.homeservice1.service;

import com.example.homeservice1.entity.Home;
import com.example.homeservice1.request.HomeRequest;

import java.util.List;
import java.util.Optional;

public interface HomeService {

    Home createHome(HomeRequest homeRequest);
    Home putHome(Home home);
    List<Home> getHomes();
    Optional<Home> getHome(Long id);
    void deleteHome(Long id);


}
