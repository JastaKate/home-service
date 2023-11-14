package com.example.homeservice1.controllers;

import com.example.homeservice1.entity.Room;
import com.example.homeservice1.request.RoomRequest;
import com.example.homeservice1.service.RoomServiceimpl;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Getter
@Setter
@RequiredArgsConstructor
@RequestMapping("/api")
public class RoomController {

    private final RoomServiceimpl roomService;

    @PostMapping("/rooms")
    public ResponseEntity<Room> createHome(@RequestParam Long homeId, @RequestBody @Valid RoomRequest roomRequest) {
        return new ResponseEntity<>(roomService.createRoom(homeId ,roomRequest), HttpStatus.OK);
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity<Room> putHome(@PathVariable Long id, @RequestBody @Valid RoomRequest roomRequest) {
        return new ResponseEntity<>(roomService.putRoom(id, roomRequest), HttpStatus.OK);
    }

    @DeleteMapping("/rooms/{id}")
    public HttpStatus deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return HttpStatus.OK;
    }

}
