package com.choicedb.controller;

import com.choicedb.service.DatabaseCheck;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/db")
public class DatabaseRead {

    DatabaseCheck databaseCheck;

    @GetMapping("/read")
    public ResponseEntity<String> read(@RequestBody Map<String, Object> JSON){
        if(databaseCheck.isDatabaseExists(JSON) && databaseCheck.isTableExists(JSON)){
            // TODO :: fetch information from database
            // search for redis is provide key
            // search for sql will be the field or fields
            // search for mongodb -> ?
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid database or table", HttpStatus.NOT_FOUND);
    }
}
