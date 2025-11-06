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
        this.patientName = patientName;
        this.patientPhone = patientPhone;
        this.timeSlot = timeSlot;
        this.doctor = doctor;
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