package com.example.BookMyShow.ResponseEntity;

import com.example.BookMyShow.Entities.ShowEntity;
import com.example.BookMyShow.Enums.Genre;
import com.example.BookMyShow.Enums.Language;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponseDto {

    private String movieName;

    private double ratings;

    private int duration;


    private Language language;


    private Genre genre;

}
