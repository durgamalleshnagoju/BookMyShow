package com.example.BookMyShow.Service;

import com.example.BookMyShow.Converter.UserConverter;
import com.example.BookMyShow.Entities.TicketEntity;
import com.example.BookMyShow.Entities.UserEntity;
import com.example.BookMyShow.EntryDto.UserDeleteDto;
import com.example.BookMyShow.EntryDto.UserEntryDto;
import com.example.BookMyShow.EntryDto.UserUpdateDto;
import com.example.BookMyShow.Repository.TicketRepository;
import com.example.BookMyShow.Repository.UserRepository;
import com.example.BookMyShow.ResponseEntity.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TicketRepository ticketRepository;

    public void addUser(UserEntryDto userEntryDto) throws Exception{
        UserEntity user = UserConverter.convertDtoToEntity(userEntryDto);
        userRepository.save(user);
    }
    public void deleteUser(UserDeleteDto dto) throws Exception{
        String email = dto.getEmail();

        UserEntity user = userRepository.findByEmail(email);
        List<TicketEntity> tickets = user.getBookedTickets();
        for(TicketEntity ticket : tickets) ticketRepository.delete(ticket);
        userRepository.delete(user);
    }
    public void updateUser(UserUpdateDto dto) throws Exception{
        String email = dto.getEmail();
        String mobNo = dto.getMobNo();

        UserEntity user = userRepository.findByEmail(email);
        user.setMobNo(mobNo);
    }
    public UserResponseDto getUser(int id){
        UserEntity user = userRepository.findById(id).get();
        UserResponseDto responseDto = UserResponseDto.builder()
                .name(user.getName()).age(user.getAge())
                .mobNo(user.getMobNo()).email(user.getEmail())
                .address(user.getAddress()).build();
        return responseDto;
    }
}
