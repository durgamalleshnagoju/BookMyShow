package com.example.BookMyShow.ResponseEntity;

import jakarta.persistence.Column;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private String name;

    private int age;

    private String email;

    private String mobNo;

    private String address;
}
