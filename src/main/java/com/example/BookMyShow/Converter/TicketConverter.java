package com.example.BookMyShow.Converter;

import com.example.BookMyShow.Entities.*;
import com.example.BookMyShow.EntryDto.TicketEntryDto;
import com.example.BookMyShow.Repository.MovieRepository;
import com.example.BookMyShow.Repository.ShowRepository;
import com.example.BookMyShow.Repository.TheaterRepository;
import com.example.BookMyShow.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TicketConverter {

    @Autowired
    static ShowRepository showRepository;

    @Autowired
    static MovieRepository movieRepository;

    @Autowired
    static TheaterRepository theaterRepository;

    @Autowired
    static UserRepository userRepository;


    public static TicketEntity convertDtoToTicketEntity(TicketEntryDto ticketEntryDto)throws Exception{
        ShowEntity show = showRepository.findById(ticketEntryDto.getShowId()).get();
        UserEntity user = userRepository.findById(ticketEntryDto.getUserId()).get();
        MovieEntity movie = show.getMovieEntity();
        TheaterEntity theater = show.getTheaterEntity();
        TicketEntity ticket = TicketEntity.builder()
                .movieName(movie.getMovieName())
                .showTime(show.getShowTime())
                .showDate(show.getShowDate())
                .theaterName(theater.getName())
                .build();

        return ticket;
    }
}
