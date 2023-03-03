package com.example.BookMyShow.Service;

import com.example.BookMyShow.Converter.UserConverter;
import com.example.BookMyShow.Entities.UserEntity;
import com.example.BookMyShow.EntryDto.UserEntryDto;
import com.example.BookMyShow.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    public void addUser(UserEntryDto userEntryDto) throws Exception{
        UserEntity user = UserConverter.convertDtoToEntity(userEntryDto);
        userRepository.save(user);
    }
}
