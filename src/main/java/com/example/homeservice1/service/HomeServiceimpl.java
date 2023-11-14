package com.example.homeservice1.service;

import com.example.homeservice1.entity.Home;
import com.example.homeservice1.repository.HomeRepo;
import com.example.homeservice1.request.HomeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HomeServiceimpl implements HomeService{

    private final HomeRepo homeRepo;

    @Override
    public Home createHome(HomeRequest homeRequest) {
        return homeRepo.save(Home.builder()
                .name(homeRequest.getName())
                .address(homeRequest.getAddress())
                .build());
    }

    @Override
    public Home putHome(Home home) {
        return homeRepo.save(home);
    }

    @Override
    public List<Home> getHomes() {
        return homeRepo.findAll();
    }

    @Override
    public Optional<Home> getHome(Long id){
        return homeRepo.findById(id);
    }

    @Override
    public void deleteHome(Long id){
        homeRepo.deleteById(id);
    }

}
