package com.example.BookMyShow.Controller;

import com.example.BookMyShow.EntryDto.ShowEntryDto;
import com.example.BookMyShow.Repository.ShowRepository;
import com.example.BookMyShow.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    ShowService showService;

    @PostMapping("/addShow")
    public ResponseEntity addShow(@RequestBody ShowEntryDto showEntryDto){
        try{
            String response = showService.addShow(showEntryDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>("Invalid attributes", HttpStatus.BAD_REQUEST);
        }
    }

}
