package com.example.BookMyShow.Controller;

import com.example.BookMyShow.EntryDto.TicketEntryDto;
import com.example.BookMyShow.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping("/bookTicket")
    public ResponseEntity bookTicket(TicketEntryDto ticketEntryDto){
        try{
            String response = ticketService.bookTicket(ticketEntryDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>("Invalid attributes", HttpStatus.BAD_REQUEST);
        }
    }

}
