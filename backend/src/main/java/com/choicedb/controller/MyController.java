package com.choicedb.controller;

import com.choicedb.model.Database;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/db")
public class MyController {
    @PostMapping("/create-database")
    public ResponseEntity<String> createDatabase(@RequestBody Map<String, Object> JSON){
        String fieldValue = (String) JSON.get("database_name");
        if(Database.contains(fieldValue)){

            // TODO :: service call for mysql/mongodb/redis db to insert data

            return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Invalid request , db Name not exists!", HttpStatus.NOT_FOUND);
    }

}
