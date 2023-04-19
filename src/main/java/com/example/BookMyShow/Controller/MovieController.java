package com.example.BookMyShow.Controller;

import com.example.BookMyShow.Entities.MovieEntity;
import com.example.BookMyShow.EntryDto.MovieEntryDto;
import com.example.BookMyShow.ResponseEntity.MovieResponseDto;
import com.example.BookMyShow.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @DeleteMapping("/deleteMovie/{id}")
    public ResponseEntity deleteMovie(@PathVariable("id") int id){
        try{
            movieService.deleteMovie(id);
            return new ResponseEntity<>("Movie deleted successfully", HttpStatus.FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getMovie/{id}")
    public ResponseEntity getMovie(@PathVariable("id") int id){
        try{
            MovieResponseDto result = movieService.getMovie(id);
            return new ResponseEntity<>(result, HttpStatus.FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
