package com.example.BookMyShow.Service;

import com.example.BookMyShow.Converter.TicketConverter;
import com.example.BookMyShow.Entities.ShowEntity;
import com.example.BookMyShow.Entities.ShowSeatEntity;
import com.example.BookMyShow.Entities.TicketEntity;
import com.example.BookMyShow.Entities.UserEntity;
import com.example.BookMyShow.EntryDto.TicketEntryDto;
import com.example.BookMyShow.Repository.ShowRepository;
import com.example.BookMyShow.Repository.TicketRepository;
import com.example.BookMyShow.Repository.UserRepository;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class    TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    JavaMailSender javaMailSender;


    public String bookTicket(TicketEntryDto ticketEntryDto)throws Exception{
        // first check the required seat availability
        if(!isSeatsAvailable(ticketEntryDto)){
            throw new Exception("Sorry Tickets Not available");
        }

        // set all the possible attributes; --> convert Dto to entity
        TicketEntity ticket = TicketConverter.convertDtoToTicketEntity(ticketEntryDto);

        ShowEntity show = showRepository.findById(ticketEntryDto.getShowId()).get();
        UserEntity user = userRepository.findById(ticketEntryDto.getUserId()).get();
        // updating user and show attributes
        ticket.setUserEntity(user);
        ticket.setShowEntity(show);
        //updating booked seats
        String ticketsBooked = ticketEntryDto.getTicketsRequired().toString();
        ticketsBooked = ticketsBooked.substring(1,ticketsBooked.length()-1);
        ticket.setBookedSeats(ticketsBooked);
        //updating total amount
        ticket.setTotalAmount(calculateTotalAmount(ticketsBooked, show));

        // update the ticket in parent Entity
        // update the parents then ticked will be saved by cascade effect;
        List<TicketEntity> ticketEntityList = user.getBookedTickets();
        ticketEntityList.add(ticket);
        user.setBookedTickets(ticketEntityList);

        userRepository.save(user);

        List<TicketEntity> ticketEntityList1  = show.getListOfBookedTickets();
        ticketEntityList1.add(ticket);
        show.setListOfBookedTickets(ticketEntityList1);

        showRepository.save(show);
        String body = "Hi this is to confirm your booking for seat No "+
                ticketsBooked +"for the movie : " +
                ticket.getMovieName() + "\n" +"Thank you for booking tickets in show_seat"+
                "\n"+ "Have a nice day :) ";


        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setFrom("showseat.durgamallesh@gmail.com");
        mimeMessageHelper.setTo(user.getEmail());
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject("Confirming your booked Ticket");

        javaMailSender.send(mimeMessage);

        return "Ticket Booked successfully !";
    }

    private boolean isSeatsAvailable(TicketEntryDto ticketEntryDto)throws Exception{
        ShowEntity show = showRepository.findById(ticketEntryDto.getShowId()).get();
        List<ShowSeatEntity> seatEntityList = show.getListOfShowSeats();
        List<String> seatsRequired = ticketEntryDto.getTicketsRequired();
        for(String RequiredSeat : seatsRequired){
            for(ShowSeatEntity seat : seatEntityList){
                if(RequiredSeat.equals(seat.getSeatNo()) && seat.isBooked()){
                    return false;
                }
            }
        }
        return true;
    }
    private int calculateTotalAmount(String ticketsBooked, ShowEntity show){
        String [] tickets = ticketsBooked.split(", ");
        List<ShowSeatEntity> showSeatEntityList = show.getListOfShowSeats();
        int totalAmount = 0;
        for(String seat : tickets){
            for(ShowSeatEntity showSeat : showSeatEntityList){
                if(seat.equals(showSeat.getSeatNo())){
                    totalAmount += showSeat.getPrice();
                }
            }
        }
        return totalAmount;
    }
}
