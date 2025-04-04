package com.abchosp.entities;

public class Room {

	private int roomId;
	private Doctor doctor;
	private int patientId = -1;

	public Room(int roomId) {
		this.roomId = roomId;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public Doctor getDoctor() { 
		return doctor; 
	}
	  
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor; 
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	 

//	zip it 
//	upload to google drive
//	doraa@labcorp.com

}
