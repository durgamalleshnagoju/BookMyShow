package com.example.BookMyShow.Service;

import com.example.BookMyShow.Converter.ShowConverter;
import com.example.BookMyShow.Entities.*;
import com.example.BookMyShow.EntryDto.ShowEntryDto;
import com.example.BookMyShow.Enums.SeatType;
import com.example.BookMyShow.Repository.MovieRepository;
import com.example.BookMyShow.Repository.ShowRepository;
import com.example.BookMyShow.Repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    ShowRepository showRepository;
    public String addShow(ShowEntryDto showEntryDto)throws Exception{
        //Converting Dto to Entity
        ShowEntity showEntity = ShowConverter.convertDtoToEntity(showEntryDto);


        MovieEntity movie = movieRepository.findById(showEntryDto.getMovieId()).get();
        TheaterEntity theater = theaterRepository.findById(showEntryDto.getTheaterId()).get();

        //setting the remaining attributes(foreign keys)
        showEntity.setMovieEntity(movie);
        showEntity.setTheaterEntity(theater);

        //setting showSeats list to the show
        List<ShowSeatEntity> showSeatEntityList = createShowSeats(showEntryDto, showEntity);
        showEntity.setListOfShowSeats(showSeatEntityList);

        // adding showEntity to its parents  --> movie and theater
        List<ShowEntity> showEntityList = movie.getShowEntityList();
        showEntityList.add(showEntity);
        movie.setShowEntityList(showEntityList);

        movieRepository.save(movie);

        List<ShowEntity> showEntityList1 = theater.getShowEntityList();
        showEntityList1.add(showEntity);
        theater.setShowEntityList(showEntityList1);


        theaterRepository.save(theater);

        // saving movie and theater will save the show entity by cascading effect (show entity saves internally)
        // when the showEntity saves internally show seats also saves by the cascade effect
        return "Show added Successfully !";
    }


    //Creating Show seats;
    private List<ShowSeatEntity> createShowSeats(ShowEntryDto showEntryDto,
                                                 ShowEntity showEntity)throws Exception{

        TheaterEntity theater = showEntity.getTheaterEntity();
        List<TheaterSeatEntity> theaterSeatEntities = theater.getTheaterSeatEntityList();

        List<ShowSeatEntity> showSeatEntitiesList = new ArrayList<>();
        // Creating Show Seats from the theater Seats
        for(TheaterSeatEntity theaterSeat : theaterSeatEntities){
            int seatPrice;
            if(theaterSeat.getSeatType().equals(SeatType.CLASSIC)){
                seatPrice = showEntryDto.getClassicSeatsPrice();
            } else {
                seatPrice = showEntryDto.getPremiumSeatsPrice();
            }
            //building the showSeat entity (setting the all possible attributes)
            ShowSeatEntity showSeat = ShowSeatEntity.builder()
                    .seatNo(theaterSeat.getSeatNo())
                    .seatType(theaterSeat.getSeatType())
                    .price(seatPrice)
                    .isBooked(false)
                    .showEntity(showEntity)
                    .build();
            showSeatEntitiesList.add(showSeat);
        }

        return showSeatEntitiesList;
    }

}
