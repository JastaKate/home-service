package com.example.homeservice1.service;

import com.example.homeservice1.entity.Home;
import com.example.homeservice1.entity.Room;
import com.example.homeservice1.repository.RoomRepo;
import com.example.homeservice1.request.RoomRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomServiceimpl implements RoomService{

    @PersistenceContext
    private EntityManager entityManager;
    private final RoomRepo roomRepo;


    @Override
    public Room createRoom(Long homeId, RoomRequest roomRequest) {
        Home home = entityManager.getReference(Home.class, homeId);
        return roomRepo.save(Room.builder()
                .home(home)
                .name(roomRequest.getName())
                .build());
    }

    @Override
    public Room putRoom(Long roomId, RoomRequest roomRequest) {
        Room room = roomRepo.findById(roomId).get();
        room.setName(roomRequest.getName());
        return roomRepo.save(room);

    }

    @Override
    public void deleteRoom(Long id) {
        roomRepo.deleteById(id);
    }
}
