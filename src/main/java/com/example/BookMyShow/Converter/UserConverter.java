package com.example.BookMyShow.Converter;

import com.example.BookMyShow.Entities.UserEntity;
import com.example.BookMyShow.EntryDto.UserEntryDto;

public class UserConverter {

    public static UserEntity convertDtoToEntity(UserEntryDto userEntryDto){
        return UserEntity.builder()
                .name(userEntryDto.getName())
                .age(userEntryDto.getAge())
                .mobNo(userEntryDto.getMobNo())
                .email(userEntryDto.getEmail())
                .address(userEntryDto.getAddress())
                .build();
    }
}
