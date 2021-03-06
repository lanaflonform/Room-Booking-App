package com.team.application.services;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import com.team.application.DateTimeConverter;
import com.team.application.models.CheckedIn;
import com.team.application.models.Employee;
import com.team.application.models.Reservation;
import com.team.application.models.keys.ReservationCompositeKey;
import com.team.application.repositories.checkedin.CheckedInRepository;

@Service
public class CheckedInService {
	
	@Autowired
	private CheckedInRepository checkedInRepository;
	
	public List<CheckedIn> getAllCheckIns(){
		return checkedInRepository.findAllCheckIns();
	}
	
	public List<CheckedIn> findCheckInsByRoom(Integer hotel_id,Integer room_number){
		return checkedInRepository.findCheckInsByRoom(hotel_id,room_number);
	}
	
	@SuppressWarnings("deprecation")
	public void reservationCheckIn(Integer hotel_id, Integer room_number,Date start_date,Date end_date,String employee_sin,Double payment, ReservationService reservationService, EmployeeService employeeService) throws ParseException{
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        
		ReservationCompositeKey key = new ReservationCompositeKey();
		key.setHotel_id(hotel_id);
		key.setRoom_number(room_number);
		//WARNING:
		//date.getYear() returns # of years since 1900
		//date.getMonth() returns months index starting from 0 (ie Jan is 0 , March is 2 ..)
		//additions below necessary for date creation through parser
		System.out.println("hotel_id:"+hotel_id+", room_number:"+room_number+", employee_sin:"+employee_sin+", payment:"+payment);
		String s;
		key.setStart_date(format.parse(s=((1900+start_date.getYear())+"-"+(1+start_date.getMonth())+"-"+start_date.getDate()+"T"+start_date.getHours()+":"+start_date.getMinutes()+":"+start_date.getSeconds())));
		System.out.println("start: "+s);
		key.setEnd_date(format.parse(s=((1900+end_date.getYear())+"-"+(1+end_date.getMonth())+"-"+end_date.getDate()+"T"+end_date.getHours()+":"+end_date.getMinutes()+":"+end_date.getSeconds())));
		System.out.println("end: "+s);
		Reservation reservation = reservationService.findReservationsById(key).get(0);	
		Employee employee = employeeService.findEmployeeById(employee_sin).get(0);
		CheckedIn checkin = new CheckedIn();
		checkin.setEmployee(employee);
		checkin.setEmployee_sin(employee.getSin());
		checkin.setHotel_id(reservation.getHotel_id());
		checkin.setRoom_number(reservation.getRoom_number());
		checkin.setPayment(payment);
		checkin.setStart_date(reservation.getStart_date());
		checkin.setEnd_date(reservation.getEnd_date());
		checkin.setReservation(reservation);
		
		checkedInRepository.save(checkin);
	}
}