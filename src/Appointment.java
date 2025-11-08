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
            System.out.println("Warning: Time slot cannot be empty, set to 'Unknown' by default");
            this.timeSlot = "Unknown";
        } else {
            this.timeSlot = timeSlot;
        }
        if (doctor == null) {
            System.out.println("Warning: Doctor cannot be null, create default doctor by default");
            this.doctor = new HealthProfessional();
        } else {
            this.doctor = doctor;
        }
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