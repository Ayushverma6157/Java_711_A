package com.hospital.main;
import com.hospital.patient.Patient;
import com.hospital.service.HospitalService;
import com.hospital.exception.InvalidAgeException;
import com.hospital.exception.DuplicatePatientException;
import com.hospital.exception.PatientNotFoundException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        HospitalService hs = new HospitalService();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. add patient");
            System.out.println("2. display patients");
            System.out.println("3. search patient");
            System.out.println("4. exit");
            System.out.print("choice: ");
            int choice = sc.nextInt();
            if (choice == 1) {
                try {
                    System.out.print("enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("enter name: ");
                    String name = sc.nextLine();
                    System.out.print("enter age: ");
                    int age = sc.nextInt();
                    if (age < 0 || age > 120) {
                        throw new InvalidAgeException("Invalid Age");
                    }
                    sc.nextLine();
                    System.out.print("enter disease: ");
                    String disease = sc.nextLine();
                    Patient p = new Patient(id, name, age, disease);
                    hs.addPatient(p);
                    System.out.println("Patient added successfully");
                    if (age > 60 && disease.equals("Heart Problem")) {
                        System.out.println("Priority Patient – Immediate Attention Required");
                    }
                } catch (InvalidAgeException e) {
                    System.out.println(e.getMessage());
                } catch (DuplicatePatientException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.out.println("Invalid input");
                    sc.nextLine();
                }
            } else if (choice == 2) {
                hs.displayPatients();
            } else if (choice == 3) {
                System.out.print("Enter ID: ");
                int id = sc.nextInt();
                try {
                    hs.searchPatient(id);
                } catch (PatientNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            } else if (choice == 4) {
                break;
            }
        }
        sc.close();
    }
}
