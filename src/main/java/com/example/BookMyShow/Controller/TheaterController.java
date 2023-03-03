package com.example.BookMyShow.Controller;

import com.example.BookMyShow.EntryDto.TheaterEntryDto;
import com.example.BookMyShow.Service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theater")
public class TheaterController {

    @Autowired
    TheaterService theaterService;
    @PostMapping("/addTheater")
    public ResponseEntity addTheater(@RequestBody TheaterEntryDto theaterEntryDto){
        String response;
        try{
            response = theaterService.addTheater(theaterEntryDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>("Invalid params :(", HttpStatus.NO_CONTENT);
        }
    }
}
