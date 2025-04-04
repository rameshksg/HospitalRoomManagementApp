package com.abchosp.entities;

public class SequenceGenerator {
    private static int patientSequence = 1;
    private static int doctorSequence = 1;
    //private static int roomSequence = 1;
    
    // Generate the next Patient ID
    public static int getNextPatientId() {
        return patientSequence++;
    }

    // Generate the next Doctor ID
    public static int getNextDoctorId() {
        return doctorSequence++;
    }
    
    // Generate the next Room ID
    /*public static int getNextRoomId() {
        return roomSequence++;
    }*/

}
