/**
 * Student Name : Zexu Xin
 * Student ID :24832928 / 202300408101
 */

/**
 * General Practitioner subclass (inherited from HealthProfessional)
 * Extend the base class attribute and add whether the general practitioner provides home visit services
 */
public class GeneralPractitioner extends HealthProfessional {
    // Unique attribute: Whether home visit service is provided
    private boolean providesHouseCalls;

    /**
     * Parameterless constructor
     * Call the parameterless constructor of the parent class to initialize general attributes. By default, no on-site service is provided for specific attributes
     */
    public GeneralPractitioner()
    {
        super(); // Inherit the default initialization logic of the parent class
        this.providesHouseCalls = false;
    }

    /**
     * Parameterized constructor (initializing all properties)
     * Call the parameterized constructor of the parent class to initialize the general attributes, and then initialize the specific attributes of the child class
     * @param id Doctor ID (Non-negative and unique)
     * @param name Doctor 's name (not empty)
     * @param specialization Specialization Field (General Practitioner)
     * @param status Work Status (Only supports Receiving patients/Stopped receiving patients)
     * @param currentAppointments Current appointments (non-negative)
     * @param maxAppointments Maximum appointments (positive integer)
     * @param providesHouseCalls Is home visit service provided
     */
    public GeneralPractitioner(int id, String name, String specialization, String status,int currentAppointments,  int maxAppointments, boolean providesHouseCalls)
    {
        // Call the constructor of the parent class to initialize the base class properties
        super(id, name, specialization, status, currentAppointments, maxAppointments);
        this.providesHouseCalls = providesHouseCalls;
    }

    /**
     * Print the complete information of the general practitioner (rewrite the parent class logic and add unique attributes)
     * First, print the doctor type, then call the parent class method to print the general information, and finally print the information specific to the subclass
     */
    public void printDetails()
    {
        System.out.println("Doctor Type: General Practitioner");
        super.printBaseInfo(); // Reuse the basic information printing logic of the parent class
        // Format the specific attribute description
        String houseCallInfo = providesHouseCalls ? "Home visit service provided" : "Home visit service not provided";
        System.out.println("Home visits：" + houseCallInfo);
    }
    // getter/setter methods for specific properties
    public boolean isProvidesHouseCalls()
    {
        return providesHouseCalls;
    }
    public void setProvidesHouseCalls(boolean providesHouseCalls) {
        this.providesHouseCalls = providesHouseCalls;
    }
}