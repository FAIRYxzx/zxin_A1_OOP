import java.util.ArrayList;
import java.util.Iterator;

public class AssignmentOne {
    private static ArrayList<Appointment> appointmentList = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println("=== Part 3: Using classes and objects  ===");
        GeneralPractitioner gp1 = new GeneralPractitioner(1001, "Bob", "General Practitioner",
                HealthProfessional.STATUS_IN_CONSULTATION, 0, 10, true);
        GeneralPractitioner gp2 = new GeneralPractitioner(1002, "Paul", "General Practitioner",
                HealthProfessional.STATUS_IN_CONSULTATION, 0, 20, false);
        GeneralPractitioner gp3 = new GeneralPractitioner(1003, "Zoe", "General Practitioner",
                HealthProfessional.STATUS_RESTING, 0, 5, true);

        Paediatrician paed1 = new Paediatrician(2001, "Olivia", "Paediatrician",
                HealthProfessional.STATUS_IN_CONSULTATION, 0, 10, false);
        Paediatrician paed2 = new Paediatrician(2002, "Amelia", "Paediatrician",
                HealthProfessional.STATUS_RESTING, 0, 20, true);

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

        System.out.println("\n=== Part 5: Collection of appointments  ===");
        createAppointment("Leo", "13800138001", "08:30", gp1);
        createAppointment("Ethan", "13900139002", "10:15", gp2);
        createAppointment("David", "13700137003", "14:00", paed1);
        createAppointment("Robert", "13600136004", "16:30", paed2);

        System.out.println("\n1. All Appointments After Creation:");
        printExistingAppointments();

        System.out.println("\n2. Cancelling Appointment for Mobile: 13900139002...");
        cancelBooking("13900139002");

        System.out.println("\n3. All Appointments After Cancellation:");
        printExistingAppointments();
        System.out.println("------------------------------");
        System.out.println("=== Custom content demonstration ===");
        GeneralPractitioner gp = new GeneralPractitioner(1004, "Daniel", "General Practitioner",
                HealthProfessional.STATUS_RESTING, 0, 2, true);
        Paediatrician paed = new Paediatrician(2003, "Linda", "Paediatrician",
                HealthProfessional.STATUS_IN_CONSULTATION, 0, 1, false);

        System.out.println("\n=== Doctor status change test ===");
        System.out.println("The initial state of a General Practitioner: " + gp.getStatus());
        gp.setStatus(HealthProfessional.STATUS_IN_CONSULTATION);
        System.out.println("The modified status of the general practitioner: " + gp.getStatus());


        System.out.println("\n=== General Practitioner family service test ===");
        System.out.println("Whether home visit services are provided initially: " + gp.isProvidesHouseCalls());
        gp.setProvidesHouseCalls(false);
        System.out.println("Whether to provide home  visit services after modification: " + gp.isProvidesHouseCalls());


        System.out.println("\n=== Paediatrician vaccine service testing ===");
        System.out.println("Whether vaccine services are initially provided: " + paed.isCanDoVaccination());
        paed.setCanDoVaccination(true);
        System.out.println("Whether to provide vaccine services after modification: " + paed.isCanDoVaccination());


        System.out.println("\n=== Schedule a boundary test ===");

        createAppointment("Jessica", "13800138001", "09:30", gp);

        createAppointment("", "13900139002", "10:00", gp);

        createAppointment("Lisa", "13700137003", "19:00", paed);

        createAppointment("Thomas", "13600136004", "14:00", paed); // 第1个预约

        createAppointment("Mark", "13400134006", "16:00", null);


        System.out.println("\n=== Final valid reservation list ===");
        printExistingAppointments();
    }


    public static void createAppointment(String patientName, String patientMobile,
                                         String timeSlot, HealthProfessional doctor) {

        if (patientName == null || patientName.trim().isEmpty() ||
                patientMobile == null || patientMobile.trim().isEmpty() ||
                timeSlot == null || timeSlot.trim().isEmpty() ||
                doctor == null) {
            System.out.println("Failed to create appointment: Missing required information!");
            return;
        }


        if (!doctor.hasAvailableQuota()) {
            System.out.println("Failed to create appointment for " + patientName +
                    ": Doctor " + doctor.getName() + " has no available quota!");
            return;
        }


        Appointment newAppointment = new Appointment(patientName, patientMobile, timeSlot, doctor);
        appointmentList.add(newAppointment);
        doctor.incrementAppointments();
        System.out.println("Appointment created successfully for " + patientName +
                " (Time: " + timeSlot + ", Doctor: " + doctor.getName() + ")");
    }


    public static void printExistingAppointments() {
        if (appointmentList.isEmpty()) {
            System.out.println("No existing appointments.");
            return;
        }

        for (int i = 0; i < appointmentList.size(); i++) {
            System.out.println("\nAppointment " + (i + 1) + ":");
            appointmentList.get(i).printInfo();
            System.out.println("----------------------------------------");
        }
    }


    public static void cancelBooking(String patientMobile) {
        Iterator<Appointment> iterator = appointmentList.iterator();
        boolean found = false;

        while (iterator.hasNext()) {
            Appointment appointment = iterator.next();
            if (appointment.getPatientPhone().equals(patientMobile)) {

                HealthProfessional doctor = appointment.getDoctor();
                doctor.decrementAppointments();

                iterator.remove();
                System.out.println("Successfully cancelled appointment for Mobile: " + patientMobile);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Error: No appointment found for Mobile: " + patientMobile);
        }
    }
}