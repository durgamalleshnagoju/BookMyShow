package com.example.BookMyShow.Repository;

import com.example.BookMyShow.Entities.TheaterSeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterSeatRepository extends JpaRepository<TheaterSeatEntity, Integer> {
}
