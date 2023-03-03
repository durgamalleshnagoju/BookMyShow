package com.example.BookMyShow.Service;

import com.example.BookMyShow.Converter.MovieConverter;
import com.example.BookMyShow.Entities.MovieEntity;
import com.example.BookMyShow.EntryDto.MovieEntryDto;
import com.example.BookMyShow.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public void addMovie(MovieEntryDto movieEntryDto) throws Exception{
        MovieEntity movie = MovieConverter.convertDtoToEntity(movieEntryDto);
        movieRepository.save(movie);
    }
}
