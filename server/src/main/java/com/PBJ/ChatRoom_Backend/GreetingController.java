package com.PBJ.ChatRoom_Backend;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public ApiResponse<String> greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        String message = String.format("Hello, %s!", name);

        // Dynamically create the fields map
        Map<String, Object> fields = new HashMap<>();
        fields.put("executed", true);
        fields.put("result", 0);
        fields.put("message", message);  // The dynamic field data

        return new ApiResponse<>("success", "greeting", fields);
    }

}
