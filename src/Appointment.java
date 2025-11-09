/**
 * Student Name : Zexu Xin
 * Student ID :24832928 / 202300408101
 */

/**
 * Reservation type (encapsulating the complete information of a single reservation)
 * Associate patient information, appointment time slots and attending doctors
 */
public class Appointment {
    private String patientName;
    // Patient's name
    private String patientPhone;
    // Patient mobile phone number (unique identifier, can be used to cancel appointments)
    private String timeSlot;
    // Reservation time slot (format: HH:mm, such as 08:30)
    private HealthProfessional doctor;
    // Attending physician (can point to subclass objects such as General Practitioners/Paediatricians)

    /**
     * Parameterless constructor (initialized by default)
     * Set default values for all attributes to ensure object integrity
     */
    public Appointment()
    {
        this.patientName = "Unknown";
        this.patientPhone = "Unknown";
        this.timeSlot = "Unknown";
        this.doctor = new HealthProfessional(); // By default, a base class doctor object is created
    }

    /**
     * Parameterized constructor (initializing all properties)
     * It includes parameter verification logic to ensure that the reservation information is legal and valid
     * @param patientName  Patient Name (non-empty)
     * @param patientPhone Patient's mobile phone number (non-empty)
     * @param timeSlot Reservation Time Slot (Legal Format + Time slot)
     * @param doctor Attending Doctor (not available)
     */
    public Appointment(String patientName, String patientPhone, String timeSlot, HealthProfessional doctor)
    {
        // Non-blank verification of patient name
        if (patientName == null || patientName.trim().isEmpty()) {
            System.out.println("Warning: Patient name cannot be empty, set to 'Unknown' by default");
            this.patientName = "Unknown";
        } else {
            this.patientName = patientName;
        }
        // Non-empty verification of the patient's mobile phone number
        if (patientPhone == null || patientPhone.trim().isEmpty()) {
            System.out.println("Warning: Patient phone cannot be empty, set to 'Unknown' by default");
            this.patientPhone = "Unknown";
        } else {
            this.patientPhone = patientPhone;
        }
        // Reservation time slot verification (non-empty + legal format + valid time slot
        if (timeSlot == null || timeSlot.trim().isEmpty()) {
            System.out.println("Warning：Invalid appointment time, set to Unknown'");
            this.timeSlot = "Unknown";
        }
        else if (!isValidTimeSlot(timeSlot)) {
            // Call the internal validation method
            System.out.println("Warning：Invalid time (need 8:00-17:00 HH:mm format, min 00-59), has been set to default '08:00'");
            this.timeSlot = "08:00";
        }
        else {
            this.timeSlot = timeSlot;
        }
        // Non-empty verification by the attending doctor
        if (doctor == null) {
            System.out.println("Warning: Doctor cannot be null, create default doctor by default");
            this.doctor = new HealthProfessional();
        } else {
            this.doctor = doctor;
        }
    }

    /**
     * Verify the legality of the reserved time slot (Private auxiliary method)
     * Rule: Time period 8:00-17:59, format HH:mm (hours 08-17, minutes 00-59)
     * @param timeSlot The string of time periods to be verified
     * @return true: Legal; false: Illegal
     */
    private boolean isValidTimeSlot(String timeSlot) {
        String timeRegex = "^(08|09|1[0-7]):([0-5][0-9])$"; // Regular expression matching rules
        return timeSlot.matches(timeRegex);
    }

    /**
     * Print the complete reservation information
     * Call the printDetails method of the corresponding subclass based on the actual type of the doctor
     */
    public void printInfo()
    {
        System.out.println("Patient's name：" + patientName);
        System.out.println("Phone：" + patientPhone);
        System.out.println("Appointment time：" + timeSlot);
        System.out.println("Doctor information：");
        // Polymorphic application: Determine the actual type of the doctor and call the print method of the corresponding subclass
        if (doctor instanceof GeneralPractitioner) {
            ((GeneralPractitioner) doctor).printDetails();
        } else if (doctor instanceof Paediatrician) {
            ((Paediatrician) doctor).printDetails();
        } else {
            doctor.printBaseInfo(); // The base class doctor calls the base class print method
        }
    }

    // getter method (for external access to necessary information)
    public HealthProfessional getDoctor()
    {
        return doctor;
    }

    public String getPatientPhone()
    {
        return patientPhone;
    }
}