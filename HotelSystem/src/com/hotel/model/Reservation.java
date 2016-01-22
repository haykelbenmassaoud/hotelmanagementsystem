package com.hotel.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
 
@Entity
public class Reservation {
	
	@Id 
	@GeneratedValue
	private long reference;

	private Date startDate;
	
	private Date endDate;
	
	@Enumerated(EnumType.STRING)
	private Direction direction;
	
	private String roomType;
	
  	private int roomNumber;
  	
  	private String clientName;
	
	@Enumerated(EnumType.STRING)
	private ReservationStatus status;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public ReservationStatus getStatus() {
		return status;
	}

	public void setStatus(ReservationStatus status) {
		this.status = status;
	}

 

	public long getReference() {
		return reference;
	}

	public void setReference(long reference) {
		this.reference = reference;
	}



	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	
	
 
	

}
