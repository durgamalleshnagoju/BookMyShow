package com.example.BookMyShow.EntryDto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class TicketEntryDto {
    private int userId;
    private int showId;
    private List<String> ticketsRequired = new ArrayList<>();

}
