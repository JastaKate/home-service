package com.example.homeservice1.repository;

import com.example.homeservice1.entity.Home;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface HomeRepo extends JpaRepository<Home, Long> {
}