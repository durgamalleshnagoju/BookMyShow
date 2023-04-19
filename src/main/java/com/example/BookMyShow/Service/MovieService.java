package com.example.BookMyShow.Service;

import com.example.BookMyShow.Converter.MovieConverter;
import com.example.BookMyShow.Entities.MovieEntity;
import com.example.BookMyShow.Entities.ShowEntity;
import com.example.BookMyShow.Entities.ShowSeatEntity;
import com.example.BookMyShow.Entities.TicketEntity;
import com.example.BookMyShow.EntryDto.MovieEntryDto;
import com.example.BookMyShow.Repository.MovieRepository;
import com.example.BookMyShow.Repository.ShowRepository;
import com.example.BookMyShow.Repository.TicketRepository;
import com.example.BookMyShow.ResponseEntity.MovieResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Timer;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    TicketRepository ticketRepository;

    public void addMovie(MovieEntryDto movieEntryDto) throws Exception{
        MovieEntity movie = MovieConverter.convertDtoToEntity(movieEntryDto);
        movieRepository.save(movie);
    }
    public void deleteMovie(int id) throws Exception{
        MovieEntity movie = movieRepository.findById(id).get();
        List<ShowEntity> showEntityList = movie.getShowEntityList();

        for(ShowEntity show : showEntityList){
            List<TicketEntity> ticketEntities = show.getListOfBookedTickets();
            for(TicketEntity ticket: ticketEntities) ticketRepository.delete(ticket);
            showRepository.delete(show);
        }

    }
    public MovieResponseDto getMovie(int id) throws Exception{
        MovieEntity movie = movieRepository.findById(id).get();
        MovieResponseDto responseDto = MovieResponseDto.builder()
                .movieName(movie.getMovieName()).genre(movie.getGenre())
                .language(movie.getLanguage()).ratings(movie.getRatings())
                .duration(movie.getDuration()).build();
        return responseDto;
    }
}
