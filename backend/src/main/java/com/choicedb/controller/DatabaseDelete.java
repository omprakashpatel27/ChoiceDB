package com.choicedb.controller;

import com.choicedb.service.DatabaseCheck;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/db")
public class DatabaseDelete {

    DatabaseCheck databaseCheck;

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody Map<String, Object> JSON){
        if(databaseCheck.isDatabaseExists(JSON) && databaseCheck.isTableExists(JSON)){
            // TODO :: delete data from database
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid database or table", HttpStatus.NOT_FOUND);
    }
}
