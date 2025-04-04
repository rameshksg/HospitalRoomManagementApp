package com.abchosp.entities;

import java.util.HashSet;
import java.util.Set;

public class Doctor {

	private int doctorId;
	private String doctonName;
	private Set<Room> setOfRoomsAssigned;
	
	
	public Doctor(int doctorId, String doctonName) {
		this.doctorId = doctorId;
		this.doctonName = doctonName;
	}
	
	public Doctor(int doctorId, String doctonName, Set<Room> setOfRoomsAssigned) {
		this.doctorId = doctorId;
		this.doctonName = doctonName;
		this.setOfRoomsAssigned = setOfRoomsAssigned;
	}
	
	public int getDoctorId() {
		return doctorId;
	}
	
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	
	public String getDoctonName() {
		return doctonName;
	}
	
	public void setDoctonName(String doctonName) {
		this.doctonName = doctonName;
	}
	
	public Set<Room> getSetOfRoomsAssigned() {
		return setOfRoomsAssigned;
	}
	
	public void setSetOfRoomsAssigned(Set<Room> setOfRoomsAssigned) {
		this.setOfRoomsAssigned = setOfRoomsAssigned;
	}
	
	public void addRoom(Room roomObj) { 
		if(this.setOfRoomsAssigned == null) 
			this.setOfRoomsAssigned = new HashSet<>();
		
		this.setOfRoomsAssigned.add(roomObj); 
	}

}
