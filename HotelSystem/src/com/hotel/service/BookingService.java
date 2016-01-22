package com.hotel.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hotel.dao.ReservationDao;
import com.hotel.dao.RoomDao;
import com.hotel.model.Direction;
import com.hotel.model.Reservation;
import com.hotel.model.ReservationStatus;

@Controller
public class BookingService {

	private static ReservationDao reservationDao = new ReservationDao();
	private static RoomDao roomDao = new RoomDao();

	@RequestMapping("/createbooking")
	public String createBooking(HttpSession session,
			HttpServletRequest request,
			@RequestParam(value = "startDate") Date startDate,
			@RequestParam(value = "endDate") Date endDate,
			@RequestParam(value = "direction") String direction,
			@RequestParam(value = "roomtype") String roomType) {

		String user = (String) session.getAttribute("username");
		switch (direction) {

		case "east":
			reservationDao.create(user, Direction.EAST, startDate, endDate,
					roomType);
			break;
		case "west":
			reservationDao.create(user, Direction.WEST, startDate, endDate,
					roomType);
			break;
		case "north":
			reservationDao.create(user, Direction.NORTH, startDate, endDate,
					roomType);
			break;
		case "south":
			reservationDao.create(user, Direction.SOUTH, startDate, endDate,
					roomType);
			break;
		}
		request.setAttribute("message", "Reservation created successfully");
		return "createreservation";
	}

	@RequestMapping("/searchreservations")
	public String searchReservations(HttpSession session) {
		String user = (String) session.getAttribute("username");
		session.setAttribute("reservations",
				reservationDao.findReservations(user, null));
		return "searchreservation";
	}

	@RequestMapping("/cancelreservations")
	public String cancelReservations(HttpSession session) {
		String user = (String) session.getAttribute("username");
		session.setAttribute("reservations", reservationDao.findReservations(
				user, ReservationStatus.CREATED));
		return "cancelreservation";
	}

	@RequestMapping("/confirmreservations")
	public String confirmReservations(HttpSession session,
			HttpServletRequest request) {
		String user = (String) session.getAttribute("username");
		session.setAttribute("reservations", reservationDao.findReservations(
				user, ReservationStatus.CREATED));
		return "confirmreservation";
	}

	@RequestMapping("/confirmbooking/{reference}")
	public synchronized String confirmBooking(HttpServletRequest request,
			@PathVariable(value = "reference") long reference) {
		Reservation reservation = reservationDao.find(reference);
		int roomNumber = roomDao.getFreeRoom(reservation.getStartDate(),
				reservation.getEndDate(), reservation.getDirection(),
				reservation.getRoomType());
		if (roomNumber != -1) {
			reservation.setRoomNumber(roomNumber);
			reservationDao.confirm(reservation);
			request.setAttribute("message", "Reservation with reference: "
					+ reference + " for the room number: " + roomNumber
					+ "  was confirmed successfully");
		} else {
			request.setAttribute("message", "No room is available !");

		}
		return "confirmreservation";
	}

	@RequestMapping("/cancelbooking/{reference}")
	public String cancelBooking(HttpServletRequest request,
			@PathVariable(value = "reference") long reference) {
		Reservation reservation = reservationDao.find(reference);
		reservationDao.cancel(reservation);
		request.setAttribute("message", "Reservation with reference: <b>"
				+ reference + " </b> was cancelled successfully");
		return "cancelreservation";
	}

}
