package com.example.BookMyShow.Controller;

import com.example.BookMyShow.Entities.UserEntity;
import com.example.BookMyShow.EntryDto.UserDeleteDto;
import com.example.BookMyShow.EntryDto.UserEntryDto;
import com.example.BookMyShow.EntryDto.UserUpdateDto;
import com.example.BookMyShow.ResponseEntity.UserResponseDto;
import com.example.BookMyShow.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @DeleteMapping("/deleteUser")
    public ResponseEntity deleteUser(@RequestBody UserDeleteDto dto){
        try{
            userService.deleteUser(dto);
            return new ResponseEntity<>("User deleted Successfully", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/updateUser")
    public ResponseEntity updateUser(@RequestBody UserUpdateDto dto){
        try{
            userService.updateUser(dto);
            return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getUser/{id}")
    public ResponseEntity getUser(@PathVariable("id") int id){
        try{
            UserResponseDto result = userService.getUser(id);
            return new ResponseEntity<>(result, HttpStatus.FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
