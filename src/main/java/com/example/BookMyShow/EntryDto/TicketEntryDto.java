package com.example.BookMyShow.EntryDto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
public class TicketEntryDto {

    private String movieName;

    private LocalDate showDate;

    private LocalTime showTime;

    private int totalAmount;

    private String theaterName;

    private String bookedSeats;
}
