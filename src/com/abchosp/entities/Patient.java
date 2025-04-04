package com.abchosp.entities;

public class Patient {

	private int patientId;
	private String patientName;
	private Room roomAssigned;
	
	
	public Patient(int patientId, String patientName) {
		this.patientId = patientId;
		this.patientName = patientName;
	}
	
	public Patient(int patientId, String patientName, Room roomAssigned) {
		this.patientId = patientId;
		this.patientName = patientName;
		this.roomAssigned = roomAssigned;
	}


	public int getPatientId() {
		return patientId;
	}


	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}


	public String getPatientName() {
		return patientName;
	}


	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}


	public Room getRoomAssigned() {
		return roomAssigned;
	}


	public void setRoomAssigned(Room roomAssigned) {
		this.roomAssigned = roomAssigned;
	}
	
	
	
	
}
