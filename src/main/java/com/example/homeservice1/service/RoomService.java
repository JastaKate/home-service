package com.example.homeservice1.service;

import com.example.homeservice1.entity.Room;
import com.example.homeservice1.request.RoomRequest;

public interface RoomService {
    Room createRoom(Long homeId, RoomRequest roomRequest);

    Room putRoom(Long roomId, RoomRequest roomRequest);

    void deleteRoom(Long id);
}
