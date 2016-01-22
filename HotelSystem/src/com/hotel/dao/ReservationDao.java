package com.hotel.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.hotel.model.Direction;
import com.hotel.model.Reservation;
import com.hotel.model.ReservationStatus;

public class ReservationDao extends AbstractDao {

	public void create(String username, Direction direction, Date startDate,
			Date endDate, String roomType) {
		Reservation reservation = new Reservation();
		reservation.setDirection(direction);
		reservation.setRoomType(roomType);
		reservation.setStartDate(startDate);
		reservation.setEndDate(endDate);
		reservation.setClientName(username);
		reservation.setStatus(ReservationStatus.CREATED);
		saveOrUpdate(reservation);
	}

	public void cancel(Reservation reservation) {
		reservation.setStatus(ReservationStatus.CANCELLED);
		saveOrUpdate(reservation);
	}

	public Reservation find(long reference) {
		Session session = getSessionFactory().openSession();
		Reservation result = null;
		try {
			result = (Reservation) session.get(Reservation.class, reference);

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	public void confirm(Reservation reservation) {
		reservation.setStatus(ReservationStatus.CONFIRMED);
		saveOrUpdate(reservation);
	}

	@SuppressWarnings("unchecked")
	public List<Reservation> findReservations(String userName,
			ReservationStatus status) {
		Session session = getSessionFactory().openSession();
		String sql1 = "From Reservation R where R.clientName =:username";
		if (status != null) {
			sql1 += " and R.status =:status";
		}
		Query query = session.createQuery(sql1);
		query.setParameter("username", userName);
		if (status != null) {
			query.setParameter("status", status);
		}
		return query.list();
	}

}
