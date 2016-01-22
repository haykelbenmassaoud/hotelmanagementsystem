package com.hotel.dao;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.hotel.model.Direction;
import com.hotel.model.Reservation;
import com.hotel.model.ReservationStatus;
import com.hotel.model.Room;

public class RoomDao extends AbstractDao {

	public Room find(int number) {
		Session session = getSessionFactory().openSession();
		Room result = null;
		try {
			result = (Room) session.get(Room.class, number);

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public int getFreeRoom(Date startDate, Date endDate, Direction direction,
			String roomType) {

		Session session = getSessionFactory().openSession();

		Criteria cr2 = session.createCriteria(Reservation.class);
		cr2.add(Restrictions.eq("status", ReservationStatus.CONFIRMED));
		cr2.add(Restrictions.eq("direction", direction));
		cr2.add(Restrictions.le("startDate", endDate));
		cr2.add(Restrictions.ge("endDate", startDate));
		cr2.setProjection(Projections.property("roomNumber"));
		List reservations = cr2.list();


		Criteria cr1 = session.createCriteria(Room.class);
		cr1.add(Restrictions.eq("direction", direction));

		switch (roomType) {
		case "standardRoom":
			cr1.add(Restrictions.eq("type", "com.hotel.model.StandardRoom"));
			break;
		case "exclusiveRoom":
			cr1.add(Restrictions.eq("type", "com.hotel.model.ExclusiveRoom"));
			break;
		case "specialRoom":
			cr1.add(Restrictions.eq("type", "com.hotel.model.SpecialRoom"));
			break;
		}
		List<Room> rooms = cr1.list();

		for (Room room : rooms) {
			if (!reservations.contains(room.getNumber()))
				return room.getNumber();
		}
		return -1;

	}

}
