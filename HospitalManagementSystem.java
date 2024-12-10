import java.util.ArrayList;
import java.util.Scanner;

// Main Class
public class HospitalManagementSystem {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Doctor> doctors = new ArrayList<>();
    static ArrayList<Patient> patients = new ArrayList<>();
    static ArrayList<Appointment> appointments = new ArrayList<>();

    public static void main(String[] args) {
        // Sample Data
        doctors.add(new Doctor(1, "Dr. Smith", "Cardiologist"));
        doctors.add(new Doctor(2, "Dr. Taylor", "Dermatologist"));

        while (true) {
            System.out.println("\n--- Hospital Management System ---");
            System.out.println("1. Add Patient");
            System.out.println("2. View Patients");
            System.out.println("3. Add Appointment");
            System.out.println("4. View Appointments");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addPatient();
                    break;
                case 2:
                    viewPatients();
                    break;
                case 3:
                    addAppointment();
                    break;
                case 4:
                    viewAppointments();
                    break;
                case 5:
                    System.out.println("Exiting... Thank you!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Add a new patient
    private static void addPatient() {
        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Patient Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Patient Illness: ");
        String illness = scanner.nextLine();

        int id = patients.size() + 1;
        patients.add(new Patient(id, name, age, illness));
        System.out.println("Patient added successfully!");
    }

    // View all patients
    private static void viewPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }

        System.out.println("\n--- Patient List ---");
        for (Patient patient : patients) {
            System.out.println(patient);
        }
    }

    // Add an appointment
    private static void addAppointment() {
        if (doctors.isEmpty() || patients.isEmpty()) {
            System.out.println("Make sure doctors and patients are available.");
            return;
        }

        System.out.println("\n--- Available Doctors ---");
        for (Doctor doctor : doctors) {
            System.out.println(doctor);
        }

        System.out.print("Enter Doctor ID: ");
        int doctorId = scanner.nextInt();
        Doctor doctor = findDoctorById(doctorId);

        if (doctor == null) {
            System.out.println("Doctor not found.");
            return;
        }

        System.out.println("\n--- Registered Patients ---");
        for (Patient patient : patients) {
            System.out.println(patient);
        }

        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        Patient patient = findPatientById(patientId);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        appointments.add(new Appointment(patient, doctor));
        System.out.println("Appointment booked successfully!");
    }

    // View all appointments
    private static void viewAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }

        System.out.println("\n--- Appointments List ---");
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }

    // Helper to find a doctor by ID
    private static Doctor findDoctorById(int id) {
        for (Doctor doctor : doctors) {
            if (doctor.getId() == id) return doctor;
        }
        return null;
    }

    // Helper to find a patient by ID
    private static Patient findPatientById(int id) {
        for (Patient patient : patients) {
            if (patient.getId() == id) return patient;
        }
        return null;
    }
}

// Doctor Class
class Doctor {
    private int id;
    private String name;
    private String specialization;

    public Doctor(int id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Specialization: " + specialization;
    }
}

// Patient Class
class Patient {
    private int id;
    private String name;
    private int age;
    private String illness;

    public Patient(int id, String name, int age, String illness) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.illness = illness;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Illness: " + illness;
    }
}

// Appointment Class
class Appointment {
    private Patient patient;
    private Doctor doctor;

    public Appointment(Patient patient, Doctor doctor) {
        this.patient = patient;
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "Patient: " + patient + ", Doctor: " + doctor;
    }
}
