public class Appointment {
    private String patientName;
    private String patientPhone;
    private String timeSlot;
    private HealthProfessional doctor;

    public Appointment()
    {
        this.patientName = "Unknown";
        this.patientPhone = "Unknown";
        this.timeSlot = "Unknown";
        this.doctor = new HealthProfessional();
    }

    public Appointment(String patientName, String patientPhone, String timeSlot, HealthProfessional doctor)
    {
        if (patientName == null || patientName.trim().isEmpty()) {
            System.out.println("Warning: Patient name cannot be empty, set to 'Unknown' by default");
            this.patientName = "Unknown";
        } else {
            this.patientName = patientName;
        }
        if (patientPhone == null || patientPhone.trim().isEmpty()) {
            System.out.println("Warning: Patient phone cannot be empty, set to 'Unknown' by default");
            this.patientPhone = "Unknown";
        } else {
            this.patientPhone = patientPhone;
        }
        if (timeSlot == null || timeSlot.trim().isEmpty()) {
            System.out.println("Warning：Invalid appointment time, set to Unknown'");
            this.timeSlot = "Unknown";
        }
        else if (!isValidTimeSlot(timeSlot)) {
            System.out.println("Warning：Invalid time (need 8:00-17:00 HH:mm format, min 00-59), has been set to default '08:00'");
            this.timeSlot = "08:00";
        }
        else {
            this.timeSlot = timeSlot;
        }
        if (doctor == null) {
            System.out.println("Warning: Doctor cannot be null, create default doctor by default");
            this.doctor = new HealthProfessional();
        } else {
            this.doctor = doctor;
        }
    }
    private boolean isValidTimeSlot(String timeSlot) {
        String timeRegex = "^(08|09|1[0-7]):([0-5][0-9])$";
        return timeSlot.matches(timeRegex);
    }
    public void printInfo()
    {
        System.out.println("Patient's name：" + patientName);
        System.out.println("Phone：" + patientPhone);
        System.out.println("Appointment time：" + timeSlot);
        System.out.println("Doctor information：");
        if (doctor instanceof GeneralPractitioner) {
            ((GeneralPractitioner) doctor).printDetails();
        } else if (doctor instanceof Paediatrician) {
            ((Paediatrician) doctor).printDetails();
        } else {
            doctor.printBaseInfo();
        }
    }


    public HealthProfessional getDoctor()
    {
        return doctor;
    }

    public String getPatientPhone()
    {
        return patientPhone;
    }
}