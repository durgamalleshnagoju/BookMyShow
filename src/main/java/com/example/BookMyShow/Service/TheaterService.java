package com.example.BookMyShow.Service;

import com.example.BookMyShow.Converter.TheaterConverter;
import com.example.BookMyShow.Entities.TheaterEntity;
import com.example.BookMyShow.Entities.TheaterSeatEntity;
import com.example.BookMyShow.EntryDto.TheaterEntryDto;
import com.example.BookMyShow.Enums.SeatType;
import com.example.BookMyShow.Repository.TheaterRepository;
import com.example.BookMyShow.Repository.TheaterSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    TheaterSeatRepository theaterSeatRepository;

    public String addTheater(TheaterEntryDto theaterEntryDto)throws Exception{

        if(theaterEntryDto.getName()==null || theaterEntryDto.getLocation()==null){
            throw new Exception("Valid Parameters Expected");
        }
        //converting EntryDto to Entity
        TheaterEntity theater = TheaterConverter.ConvertDtoToEntity(theaterEntryDto);
        //seats list
        List<TheaterSeatEntity> seatsList = createSeats(theaterEntryDto, theater);

        theater.setTheaterSeatEntityList(seatsList);

        theaterRepository.save(theater);

        return "Theater and their Seats Created Successfully !";
    }
    private List<TheaterSeatEntity> createSeats(TheaterEntryDto theatreEntryDto, TheaterEntity theaterEntity){
        List<TheaterSeatEntity> seatsList = new ArrayList<>();
        int numberOfPremiumSeats = theatreEntryDto.getPremiumSeatCount();
        int numberOfClassicSeats = theatreEntryDto.getClassicSeatCount();

        for(int count = 1;count <= numberOfPremiumSeats; count++){
            TheaterSeatEntity theaterSeatEntity = TheaterSeatEntity.builder()
                    .seatNo(count+"P")
                    .seatType(SeatType.PREMIUM)
                    .theaterEntity(theaterEntity)
                    .build();
            seatsList.add(theaterSeatEntity);
        }
        for(int count = 1; count <= numberOfClassicSeats; count++){
            TheaterSeatEntity theaterSeatEntity = TheaterSeatEntity.builder()
                    .seatNo(count+"C")
                    .seatType(SeatType.CLASSIC)
                    .theaterEntity(theaterEntity)
                    .build();
            seatsList.add(theaterSeatEntity);
        }
        return seatsList;
    }

}
