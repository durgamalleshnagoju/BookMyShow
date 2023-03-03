package com.example.BookMyShow.Converter;

import com.example.BookMyShow.Entities.TheaterEntity;
import com.example.BookMyShow.EntryDto.TheaterEntryDto;

public class TheaterConverter {
    public static TheaterEntity ConvertDtoToEntity(TheaterEntryDto theaterEntryDto){
        TheaterEntity theater = TheaterEntity.builder()
                .name(theaterEntryDto.getName())
                .location(theaterEntryDto.getLocation())
                .build();
        return theater;
    }
}
