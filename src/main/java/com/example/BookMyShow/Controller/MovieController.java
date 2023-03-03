package com.example.BookMyShow.Controller;

import com.example.BookMyShow.EntryDto.MovieEntryDto;
import com.example.BookMyShow.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/addMovie")
    public ResponseEntity addMovie(@RequestBody MovieEntryDto movieEntryDto){
        try{
            movieService.addMovie(movieEntryDto);
            return new ResponseEntity<>("Movie added Successfully!", HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>("Invalid attributes", HttpStatus.BAD_REQUEST);
        }
    }
}
