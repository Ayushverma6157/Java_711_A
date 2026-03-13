package com.hospital.service;
import com.hospital.patient.Patient;
import com.hospital.exception.DuplicatePatientException;
import com.hospital.exception.PatientNotFoundException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
public class HospitalService {
    private ArrayList<Patient> list = new ArrayList<>();
    private String file = "patients.txt";
    public HospitalService() {
        try {
            File f = new File(file);
            if (f.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(f));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] arr = line.split(",");
                    if (arr.length == 4) {
                        list.add(new Patient(Integer.parseInt(arr[0]), arr[1], Integer.parseInt(arr[2]), arr[3]));
                    }
                }
                br.close();
            }
        } catch (Exception e) {
            System.out.println("File read error");
        }
    }
    public void addPatient(Patient p) throws DuplicatePatientException {
        for (Patient obj : list) {
            if (obj.getPatientId() == p.getPatientId()) {
                throw new DuplicatePatientException("Duplicate Patient Check: Patient ID already exists");
            }
        }
        list.add(p);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            bw.write(p.getPatientId() + "," + p.getPatientName() + "," + p.getAge() + "," + p.getDisease());
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            System.out.println("File write error");
        }
    }
    public void searchPatient(int id) throws PatientNotFoundException {
        for (Patient p : list) {
            if (p.getPatientId() == id) {
                p.displayPatient();
                return;
            }
        }
        throw new PatientNotFoundException("Patient not found");
    }
    public void displayPatients() {
        if (list.size() == 0) {
            System.out.println("No records");
            return;
        }
        for (Patient p : list) {
            p.displayPatient();
        }
    }
}
