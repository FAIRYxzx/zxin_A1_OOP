/**
 * Student Name : Zexu Xin
 * Student ID :24832928 / 202300408101
 */

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Main class (Program entry)
 * Integrate all functional modules to demonstrate the usage of classes, object creation, and reservation management processes
 */
public class AssignmentOne {
    // Reservation List (stores all valid reservations, managed using ArrayList)
    private static ArrayList<Appointment> appointmentList = new ArrayList<>();

    /**
     * Main method (Program entry
     * Module demonstration: Object creation, reservation management, custom function testing
     */
    public static void main(String[] args) {
        // Part 3: Using Classes and Objects
        System.out.println("=== Part 3: Using classes and objects  ===");
        // Create three General Practitioner objects (parameters: ID, name, professional field, status, current number of appointments, maximum number of appointments, whether home visit service is available)
        GeneralPractitioner gp1 = new GeneralPractitioner(1001, "Bob", "General Practitioner",
                HealthProfessional.STATUS_IN_CONSULTATION, 0, 10, true);
        GeneralPractitioner gp2 = new GeneralPractitioner(1002, "Paul", "General Practitioner",
                HealthProfessional.STATUS_IN_CONSULTATION, 0, 20, false);
        GeneralPractitioner gp3 = new GeneralPractitioner(1003, "Zoe", "General Practitioner",
                HealthProfessional.STATUS_RESTING, 0, 5, true);
        // Create two pediatrician objects  (parameters: ID, name, professional field, status, current number of appointments, maximum number of appointments, whether vaccine services are provided)
        Paediatrician paed1 = new Paediatrician(2001, "Olivia", "Paediatrician",
                HealthProfessional.STATUS_IN_CONSULTATION, 0, 10, false);
        Paediatrician paed2 = new Paediatrician(2002, "Amelia", "Paediatrician",
                HealthProfessional.STATUS_RESTING, 0, 20, true);
        // Print all doctor information (call the subclass printDetails method)
        gp1.printDetails();
        System.out.println("----------------------------------------");
        gp2.printDetails();
        System.out.println("----------------------------------------");
        gp3.printDetails();
        System.out.println("----------------------------------------");
        paed1.printDetails();
        System.out.println("----------------------------------------");
        paed2.printDetails();
        System.out.println("------------------------------");
        // Part 5 – Collection of appointments
        System.out.println("\n=== Part 5: Collection of appointments  ===");
        // Create 4 appointments (2 for general practitioners +2 for pediatrics)
        createAppointment("Leo", "13800138001", "08:30", gp1);
        createAppointment("Ethan", "13900139002", "10:15", gp2);
        createAppointment("David", "13700137003", "14:00", paed1);
        createAppointment("Robert", "13600136004", "16:30", paed2);
        // Print all appointments
        System.out.println("\n1. All Appointments After Creation:");
        printExistingAppointments();
        // Cancel the reservation with the designated mobile phone number
        System.out.println("\n2. Cancelling Appointment for Mobile: 13900139002...");
        cancelBooking("13900139002");
        // Print the list of cancelled appointments
        System.out.println("\n3. All Appointments After Cancellation:");
        printExistingAppointments();
        System.out.println("------------------------------");
        // Custom Function demonstration
        System.out.println("=== Custom content demonstration ===");
        //Create two test doctor objects
        GeneralPractitioner gp = new GeneralPractitioner(1004, "Daniel", "General Practitioner",
                HealthProfessional.STATUS_RESTING, 0, 2, true);
        Paediatrician paed = new Paediatrician(2003, "Linda", "Paediatrician",
                HealthProfessional.STATUS_IN_CONSULTATION, 0, 1, false);
        // Doctor status modification test
        System.out.println("\n=== Doctor status change test ===");
        System.out.println("The initial state of a General Practitioner: " + gp.getStatus());
        gp.setStatus(HealthProfessional.STATUS_IN_CONSULTATION);
        System.out.println("The modified status of the general practitioner: " + gp.getStatus());
        // General Practitioner Home Visit Service Change Test
        System.out.println("\n=== General Practitioner family service test ===");
        System.out.println("Whether home visit services are provided initially: " + gp.isProvidesHouseCalls());
        gp.setProvidesHouseCalls(false);
        System.out.println("Whether to provide home  visit services after modification: " + gp.isProvidesHouseCalls());
        // Paediatrician vaccine Service Change test
        System.out.println("\n=== Paediatrician vaccine service testing ===");
        System.out.println("Whether vaccine services are initially provided: " + paed.isCanDoVaccination());
        paed.setCanDoVaccination(true);
        System.out.println("Whether to provide vaccine services after modification: " + paed.isCanDoVaccination());
        // Appointment boundary value test (empty name, illegal time slot, empty doctor)
        System.out.println("\n=== Appointment boundary test ===");
        createAppointment("Jessica", "13800138001", "09:30", gp); // Legal appointment
        createAppointment("", "13900139002", "10:00", gp); // Empty name
        createAppointment("Lisa", "13700137003", "19:00", paed); //The illegal time slot (19:00 to 17:00) is changed to 8:00 by default
        createAppointment("Thomas", "13600136004", "14:00", paed); // Appointments exceeding the quota
        createAppointment("Mark", "13400134006", "16:00", null); // Empty doctor
        // Print the final valid reservation list
        System.out.println("\n=== Final valid reservation list ===");
        printExistingAppointments();
    }

    /**
     * Create a reservation (for the main method to call)
     * It includes complete verification logic to ensure the successful creation of the reservation
     * Polymorphic application: Supports appointments with various types of doctors such as general practitioners and pediatricians
     * @param patientName Patient's name
     * @param patientMobile patient mobile phone number
     * @param timeSlot Reservation time slot
     * @param doctor Attending Doctor (Polymorphic parameters)
     */
    public static void createAppointment(String patientName, String patientMobile,
                                         String timeSlot, HealthProfessional doctor) {
        // Verify required parameters (non-empty)
        if (patientName == null || patientName.trim().isEmpty() ||
                patientMobile == null || patientMobile.trim().isEmpty() ||
                timeSlot == null || timeSlot.trim().isEmpty() ||
                doctor == null) {
            System.out.println("Failed to create appointment: Missing required information!");
            return;
        }
        // Check if the doctor still has an appointment quota
        if (!doctor.hasAvailableQuota()) {
            System.out.println("Failed to create appointment for " + patientName +
                    ": Doctor " + doctor.getName() + " has no available quota!");
            return;
        }
        // Create an appointment object and add it to the list, and the number of doctor appointments will increase by 1
        Appointment newAppointment = new Appointment(patientName, patientMobile, timeSlot, doctor);
        appointmentList.add(newAppointment);
        doctor.incrementAppointments();
        System.out.println("Appointment created successfully for " + patientName +
                " (Time: " + timeSlot + ", Doctor: " + doctor.getName() + ")");
    }

    /**
     * Print all existing appointments
     * If the list is empty, it will prompt that there is no appointment. Otherwise, traverse and print each reservation detail
     */
    public static void printExistingAppointments() {
        if (appointmentList.isEmpty()) {
            System.out.println("No existing appointments.");
            return;
        }
        //Traverse the reservation list and print the complete information of each reservation
        for (int i = 0; i < appointmentList.size(); i++) {
            System.out.println("\nAppointment " + (i + 1) + ":");
            appointmentList.get(i).printInfo();
            System.out.println("----------------------------------------");
        }
    }

    /**
     * Cancel the appointment (by patient's mobile phone number)
     * Use an iterator to traverse the list to avoid concurrent modification of exceptions
     * After successful cancellation, the number of doctor appointments will be -1
     * @param patientMobile patient mobile phone number
     */
    public static void cancelBooking(String patientMobile) {
        Iterator<Appointment> iterator = appointmentList.iterator();
        boolean found = false;
        // Traverse the reservation list to find reservations that match the mobile phone number
        while (iterator.hasNext()) {
            Appointment appointment = iterator.next();
            if (appointment.getPatientPhone().equals(patientMobile)) {
                HealthProfessional doctor = appointment.getDoctor();
                doctor.decrementAppointments(); // The number of doctor appointments has decreased by 1
                iterator.remove(); // Safely Delete the appointment (Iterator Method)
                System.out.println("Successfully cancelled appointment for Mobile: " + patientMobile);
                found = true;
                break; //The mobile phone number is unique. Once found, exit directly
            }
        }
        // An error is prompted when the corresponding reservation is not found
        if (!found) {
            System.out.println("Error: No appointment found for Mobile: " + patientMobile);
        }
    }
}