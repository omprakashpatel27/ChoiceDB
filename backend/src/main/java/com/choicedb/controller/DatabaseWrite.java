package com.choicedb.controller;

import com.choicedb.service.DatabaseCheck;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/db")
public class DatabaseWrite {

    DatabaseCheck databaseCheck;

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody Map<String, Object> JSON){
        if(databaseCheck.isDatabaseExists(JSON) && databaseCheck.isTableExists(JSON)){
            // TODO :: Update the database
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid database or table", HttpStatus.NOT_FOUND);
    }
}
