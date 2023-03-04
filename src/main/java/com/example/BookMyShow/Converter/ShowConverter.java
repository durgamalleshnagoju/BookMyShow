package com.example.BookMyShow.Converter;


import com.example.BookMyShow.Entities.ShowEntity;
import com.example.BookMyShow.EntryDto.ShowEntryDto;

public class ShowConverter {
    public static ShowEntity convertDtoToEntity(ShowEntryDto showEntryDto){
        ShowEntity show = ShowEntity.builder()
                .showDate(showEntryDto.getShowDate())
                .showTime(showEntryDto.getShowTime())
                .showType(showEntryDto.getShowType())
                .build();

        return show;
    }
}
