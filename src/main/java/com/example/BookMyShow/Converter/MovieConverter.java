package com.example.BookMyShow.Converter;

import com.example.BookMyShow.Entities.MovieEntity;
import com.example.BookMyShow.EntryDto.MovieEntryDto;

public class MovieConverter {
    public static MovieEntity convertDtoToEntity(MovieEntryDto movieEntryDto){
        MovieEntity movie = MovieEntity.builder()
                .movieName(movieEntryDto.getMovieName())
                .genre(movieEntryDto.getGenre())
                .duration(movieEntryDto.getDuration())
                .ratings(movieEntryDto.getRatings())
                .language(movieEntryDto.getLanguage())
                .build();
        return movie;
    }
}
