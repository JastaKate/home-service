package com.example.homeservice1.repository;

import com.example.homeservice1.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long> {
    void deleteAllByHomeId(Long homeId);
}
