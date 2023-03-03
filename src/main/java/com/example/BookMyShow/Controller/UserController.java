package com.example.BookMyShow.Controller;

import com.example.BookMyShow.EntryDto.UserEntryDto;
import com.example.BookMyShow.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("/addUser")
    public ResponseEntity addUser(@RequestBody UserEntryDto userEntryDto){
        try{
            userService.addUser(userEntryDto);
            return new ResponseEntity<>("User added Successfully!", HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>("Invalid User!", HttpStatus.BAD_REQUEST);
        }
    }
}
