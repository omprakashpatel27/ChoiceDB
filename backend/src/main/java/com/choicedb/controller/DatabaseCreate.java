package com.choicedb.controller;

import com.choicedb.service.CreateService;
import com.choicedb.service.DatabaseCheck;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/db")
public class DatabaseCreate {

    DatabaseCheck databaseCheck;
    CreateService createService;
    private Map<String, Object> JSON;

    @PostMapping("/create-database")
    public ResponseEntity<String> createDatabase(@RequestBody Map<String, Object> JSON){
        if(databaseCheck.isDatabaseExists(JSON)){

            createService.createDatabase((String) JSON.get("db_name"), JSON);

            return new ResponseEntity<>("SUCCESS" , HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Invalid request , db Name not exists!", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-table")
    public ResponseEntity<String> createTable(@RequestBody Map<String, Object> JSON){
        if(databaseCheck.isDatabaseExists(JSON) && databaseCheck.isTableExists(JSON)){

            createService.createTable((String) JSON.get("db_name"), JSON);

            return new ResponseEntity<>("SUCCESS" , HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Invalid request , db Name not exists!", HttpStatus.NOT_FOUND);
    }
}
