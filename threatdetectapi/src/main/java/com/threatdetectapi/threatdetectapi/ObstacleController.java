package com.threatdetectapi.threatdetectapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ObstacleController {

    @PostMapping("/obstacle")
    public ResponseEntity<String> handleObstacleData(@RequestBody ObstacleData obstacleData) {
        // Process the obstacleData received from Arduino
        // Implement your business logic here

        // Example response
        String response = "Obstacle data received successfully";
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
