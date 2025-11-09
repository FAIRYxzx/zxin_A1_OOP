/**
 * Student Name : Zexu Xin
 * Student ID :24832928 / 202300408101
 */

/**
 * Paediatrician subclass (inherited from HealthProfessional)
 * Expand the base class attributes and add vaccine service functions specific to paediatrician
 */
public class Paediatrician extends HealthProfessional {
    // Unique attributes: Whether children's vaccination services are provided
    private boolean canDoVaccination;

    /**
     * Parameterless constructor
     * Call the parameterless constructor of the parent class to initialize the general attributes. The specific attributes do not provide vaccine services by default
     */
    public Paediatrician()
    {
        super(); // Inherit the default initialization logic of the parent class
        this.canDoVaccination = false;
    }
    /**
     * Parameterized constructor (initializing all properties)
     * Call the parameterized constructor of the parent class to initialize the general attributes, and then initialize the specific attributes of the child class
     * @param id Doctor ID (Non-negative and unique)
     * @param name Doctor 's name (not empty)
     * @param specialization Specialization Field (Paediatrician)
     * @param status Work Status (Only supports Receiving patients/Stopped receiving patients)
     * @param currentAppointments Current appointments (non-negative)
     * @param maxAppointments Maximum appointments (positive integer)
     * @param canDoVaccination Whether vaccination services are provided
     */
    public Paediatrician(int id, String name, String specialization, String status, int currentAppointments,  int maxAppointments, boolean canDoVaccination) {
        // Call the constructor of the parent class to initialize the base class properties
        super(id, name, specialization, status, currentAppointments, maxAppointments);
        this.canDoVaccination = canDoVaccination;
    }

    /**
     * Print the complete information of the pediatrician (rewrite the parent class logic and add unique attributes)
     * First, print the doctor type, then call the parent class method to print the general information, and finally print the information specific to the subclass
     */
    public void printDetails()
    {
        System.out.println("Doctor Type: Paediatrician");
        super.printBaseInfo(); // Reuse the basic information printing logic of the parent class
        // Format the specific attribute description
        String vaccinationInfo;
        if (canDoVaccination) {
            vaccinationInfo = "Children can receive vaccines";
        } else {
            vaccinationInfo = "Vaccination is not available for the time being";
        }
        System.out.println("Vaccination service：" + vaccinationInfo);
    }
    // getter/setter methods for specific properties
    public boolean isCanDoVaccination()
    {
        return canDoVaccination;
    }
    public void setCanDoVaccination(boolean canDoVaccination) {
        this.canDoVaccination = canDoVaccination;
    }
}
