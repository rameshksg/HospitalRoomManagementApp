package com.abchosp.controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import com.abchosp.entities.Doctor;
import com.abchosp.entities.Patient;
import com.abchosp.entities.Room;
import com.abchosp.entities.SequenceGenerator;


/**
 * # Hospital Room Allotment App using Pojos (not database)
 * 1.Register a new patient
 * 2.Assign a room to the patient
 * 3.Assign a doctor to a room
 * 4.Get Room info by using doctorId
 * 5.Get Room info by using patientId
 * 6.Shift Room for a patient
 * zip it 	upload to google drive and send to	doraa@labcorp.com
 * 
*/

public class HospitalOperations {

	public static HashMap<Integer, Patient> mapOfPatients = new HashMap<>();
	public static HashMap<Integer, Doctor> mapOfDoctors = new HashMap<>();
	public static HashMap<Integer, Room> mapOfRooms = new HashMap<>();
	
	
	// Initialize fixed 20 rooms
    static {
        for (int i = 1; i <= 20; i++) {
        	mapOfRooms.put(i, new Room(i));
        }
        System.out.println("20 rooms initialized.");
        System.out.println("--------");
		System.out.println("");
    }

    
	public static boolean registerPatient(Integer id, String name) {
		if(mapOfPatients.containsKey(id)) {
			System.out.println("Patient is already registered");
			return false;
		} 
	
		mapOfPatients.put(id, new Patient(id, name));
		System.out.println("Patient " + name + " registered successfully with ID: " + id);
		return true;
	}
	
	public static int registerPatient(String name) {
        int patientId = SequenceGenerator.getNextPatientId();
        Patient patient = new Patient(patientId, name);
        mapOfPatients.put(patientId, patient);
        System.out.println("Patient " + name + " registered successfully with ID: " + patientId);
        return patientId;
    }
	
	public static int registerDoctor(String name) {
        int doctorId = SequenceGenerator.getNextDoctorId();
        Doctor doctor = new Doctor(doctorId, name);
        mapOfDoctors.put(doctorId, doctor);
        System.out.println("Doctor " + name + " added successfully with ID: " + doctorId);
        return doctorId;
    }


	// Assign a room to a patient
	public static boolean assignPatientToRoom(int patientId, int roomId) {
		Patient patientObj = mapOfPatients.get(patientId);
		Room roomObj = mapOfRooms.get(roomId);
		
		if(patientObj == null ) {
			System.out.println("No Patient exist with the id " + patientId);
			return false;
		} else if(roomObj == null ) {
			System.out.println("No Room exist with the id " + roomId);
			return false;
		} else if(patientObj.getRoomAssigned() != null) {
			System.out.println("Patient (Id: "+patientId+", Name:"+patientObj.getPatientName()+") is already assigned to the Room with id "+ patientObj.getRoomAssigned().getRoomId() + "");
			return false;
		} else if(roomObj.getPatientId() != -1) {
			System.out.println("Room (Id: "+roomId+") is already assigned to the Patient with id " + roomObj.getPatientId() + "");
			return false;
		}
		
		patientObj.setRoomAssigned(roomObj);
		roomObj.setPatientId(patientId);
		System.out.println("Patient (Id: "+patientId+", Name:"+patientObj.getPatientName()+") is assigned to the Room with id "+ roomId + " succesffully");
						
		return true;
	}
	
	
	// Assign a doctor to a room
    public static boolean assignDoctorToRoom(int doctorId, int roomId) {
		Doctor doctor = mapOfDoctors.get(doctorId);
		Room room = mapOfRooms.get(roomId);
		
		if(doctor == null ) {
			System.out.println("No Doctor exist with the id " + doctorId);
			return false;
		} else if(room == null ) {
			System.out.println("No Room exist with the id " + roomId);
			return false;
		} else if(doctor.getSetOfRoomsAssigned() != null && doctor.getSetOfRoomsAssigned().contains(room)) {
			System.out.println("Failure. Doctor (Id: "+doctorId+", Name:"+doctor.getDoctonName()+") is already assigned to the Room with id "+ roomId);
			return false;
		}
		
		room.setDoctor(doctor);
		doctor.addRoom(room);
		System.out.println("Doctor (Id: "+doctorId+", Name:"+doctor.getDoctonName()+") is assigned to the Room with id "+ roomId + " succesffully");
						
		return true;
	}
    
    //Get ALl Rooms and their statuses
    public static void printAllRoomsAndTheirStatuses() {
    	//Get all rooms
    	System.out.println("");
    	System.out.println("--------");
        System.out.println("Total Rooms:");
        for(Integer roomId : mapOfRooms.keySet()) {
        	System.out.print(roomId.intValue() + " ");
        }
        System.out.println();
        
        //Get all available/unassigned rooms
        System.out.println("Rooms' Status:");
        for(Entry<Integer, Room> entryOfRoom : mapOfRooms.entrySet()) {
        	if(entryOfRoom.getKey() != null && entryOfRoom.getValue() != null)
        		if(entryOfRoom.getValue().getPatientId() != -1)
        			System.out.println(entryOfRoom.getKey() + " - Occupied by patient id : " + entryOfRoom.getValue().getPatientId());
        		else
        			System.out.println(entryOfRoom.getKey() + " - Not Occupied i.e., Available ");
        }
        System.out.println("--------");
        System.out.println("");
    }
    
    public static Set<Room> getAllAvailableRooms() {
    	//Get all available/unassigned rooms
    	Set<Room> setOfRoomsAvailable = new HashSet<Room>();
    	for(Entry<Integer, Room> entryOfRoom : mapOfRooms.entrySet()) {
        	if(entryOfRoom.getKey() != null && entryOfRoom.getValue() != null)
        		if(entryOfRoom.getValue().getPatientId() == -1)
        			setOfRoomsAvailable.add(entryOfRoom.getValue());
        }
    	System.out.println("Number of Rooms available: "+setOfRoomsAvailable.size());
        
        return setOfRoomsAvailable;
    }
	
    // Get Room info by using doctorId
    public static void getRoomInfoByDoctor(int doctorId) {
        Doctor doctor = mapOfDoctors.get(doctorId);
        if (doctor != null && doctor.getSetOfRoomsAssigned() != null) {
            System.out.print("Doctor " + doctor.getDoctonName() + " manages rooms: ");
            for (Room room : doctor.getSetOfRoomsAssigned()) {
                System.out.print(room.getRoomId() + " ");
            }
            System.out.println();
        } else {
            System.out.println("Doctor not found!");
        }
    }
    
    // Get Room info by using patientId
    public static void getRoomInfoByPatient(int patientId) {
        Patient patient = mapOfPatients.get(patientId);
        if (patient != null && patient.getRoomAssigned() != null) {
            System.out.println("Patient " + patient.getPatientName() + " is in Room " + patient.getRoomAssigned().getRoomId());
        } else {
            System.out.println("Patient not found or not assigned to a room!");
        }
    }

    
    // Shift Room for a patient
    public static boolean shiftPatientRoom(int patientId, int newRoomId) {
        Patient patient = mapOfPatients.get(patientId);
        Room newRoom = mapOfRooms.get(newRoomId);

        if (patient == null) {
            System.out.println("Patient not found!");
            return false;
        } else if (newRoom == null) {
            System.out.println("Invalid Room ID!");
            return false;
        } 
        
        Room oldRoom = patient.getRoomAssigned();
        patient.setRoomAssigned(newRoom);
        newRoom.setPatientId(patientId);
        System.out.println("Patient " + patient.getPatientName() + " shifted from Room " + 
            (oldRoom != null ? oldRoom.getRoomId() : "None") + " to Room " + newRoomId);
        
        return true;
    }

    
	public static void main(String[] args) {
		// Add doctors
		int doctorId1 = registerDoctor("Dr. Ramesh");
		int doctorId2 = registerDoctor("Dr. Doraa");
		System.out.println("--------");
		System.out.println("");
    	
		// Register patients
		int patientId1 = registerPatient("Kiran");
		int patientId2 = registerPatient("Sachin");
		System.out.println("--------");
		System.out.println("");
    	
        // Assign patients to rooms
        //assignPatientToRoom(1, 1);
        //assignPatientToRoom(2, 2);
                
        // Assign doctors to rooms
        //assignDoctorToRoom(1, 1);
        //assignDoctorToRoom(2, 2);
				
        printAllRoomsAndTheirStatuses();
        
        Set<Room> allAvailableRooms = getAllAvailableRooms();
        Room arrAllAvailableRooms[] = new Room[allAvailableRooms.size()]; 
        allAvailableRooms.toArray(arrAllAvailableRooms); 
        if(arrAllAvailableRooms.length >= 2 && arrAllAvailableRooms[1] != null){
        	assignPatientToRoom(patientId2, arrAllAvailableRooms[1].getRoomId());
        	assignDoctorToRoom(doctorId2, arrAllAvailableRooms[1].getRoomId());
        	System.out.println("--------");
    		System.out.println("");
        } 
        if(arrAllAvailableRooms.length >= 1 &&  arrAllAvailableRooms[0] != null){
        	assignPatientToRoom(patientId1, arrAllAvailableRooms[0].getRoomId());
        	assignDoctorToRoom(doctorId1, arrAllAvailableRooms[0].getRoomId());
        	System.out.println("--------");
    		System.out.println("");
        } else {
        	System.out.println("No Rooms available");
        }
        
        // Get info
        getRoomInfoByPatient(patientId1);
        getRoomInfoByPatient(patientId2);
        System.out.println("--------");
		System.out.println("");

        getRoomInfoByDoctor(doctorId1);
        getRoomInfoByDoctor(doctorId2);
        System.out.println("--------");
		System.out.println("");
        
        // Shift patient to new room, and assign doctor to new room
        if(arrAllAvailableRooms.length >= 3 && arrAllAvailableRooms[2] != null){
        	shiftPatientRoom(patientId2, arrAllAvailableRooms[2].getRoomId());
        	assignDoctorToRoom(doctorId2, arrAllAvailableRooms[2].getRoomId());
        }
        System.out.println("--------");
		System.out.println("");


	}
}
