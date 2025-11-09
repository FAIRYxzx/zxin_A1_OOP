/**
 * Student Name : Zexu Xin
 * Student ID :24832928 / 202300408101
 */

import java.util.HashSet;
import java.util.Set;
/**
* Basic category of Health Professionals
* Encapsulate the common attributes and behaviors of all Health Professionals
*/
public class HealthProfessional {
    public static final String STATUS_IN_CONSULTATION = "Receiving patients";
    public static final String STATUS_RESTING = "Stop receiving patients";
    //Doctor status constant: seeing patients and stopping seeing patients
    private static final Set<Integer> existingIds = new HashSet<>();
    // Store the existing doctor ids to ensure the uniqueness of the ids (static collections are shared among all instances)
    private final int id;
    //Doctor's unique ID (unmodifiable)
    private String name;
    //Doctor's name
    private String specialization;
    //Professional fields (such as General Practitioner, Paediatrician)
    private String status;
    //Doctor's current status (Receiving patients/Stopped receiving patients)
    private int currentAppointments;
    // Current number of reservations
    private int maxAppointments;
    // Maximum number of appointments available

    /**
     * Parameterless constructor (initialized by default)
     * Set default values for all attributes to ensure the integrity of object creation
     */

    public HealthProfessional()
    {
        this.id = 0;
        this.name = "Unknown";
        this.specialization = "Unknown";
        this.status = STATUS_RESTING;
        this.currentAppointments = 0;
        this.maxAppointments = 50;
    }

    /**
     * Parameterized constructor (initializing all properties)
     * It includes parameter verification logic to ensure the legitimacy of the data
     * @param id Doctor ID (Non-negative and unique)
     * @param name Doctor 's name (not empty)
     * @param specialization  specialization Field (Non-Empty)
     * @param status Work Status (Only supports Receiving patients/Stopped receiving patients)
     * @param currentAppointments Current appointments (non-negative)
     * @param maxAppointments Maximum appointments (positive integer)
     */
    public HealthProfessional(int id, String name, String specialization, String status, int currentAppointments, int maxAppointments) {
        // ID legitimacy check: Negative numbers are converted to 0
        int actualId = id < 0 ? 0 : id;
        // Verify the uniqueness of the ID. If it is duplicated, the initialization fails
        if (existingIds.contains(actualId)) {
            System.out.println("Warning: ID " + actualId + " is duplicate, cannot create HealthProfessional.");
            this.id = -1;
            this.name = "Unknown";
            this.specialization = "Unknown";
            this.status = STATUS_RESTING;
            this.currentAppointments = 0;
            this.maxAppointments = 50;
            return;
        }
        this.id = actualId;
        existingIds.add(actualId); // Legal ids are added to the global collection
        // Doctor's name non-blank verification
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Warning: Name cannot be empty, set to 'Unknown' by default");
            this.name = "Unknown";
        } else {
            this.name = name;
        }
        // Non-empty verification in professional fields
        if (specialization == null || specialization.trim().isEmpty()) {
            System.out.println("Warning: Specialization cannot be empty, set to 'Unknown' by default");
            this.specialization = "Unknown";
        } else {
            this.specialization = specialization;
        }
        // Status legitimacy Verification (Only two preset states are supported)
        if (status.equals(STATUS_IN_CONSULTATION) || status.equals(STATUS_RESTING))
        {
            this.status = status;
        }
        else
        {
            this.status = STATUS_RESTING;
            System.out.println("Warning: The status is illegal and has been set by default" + STATUS_RESTING);
        }
        // The current reservation number is non-negative check
        if (currentAppointments < 0){
            System.out.println("Warning: The current reservation number cannot be negative and defaults to 0");
        }else {
            this.currentAppointments = currentAppointments;
        }
        // Maximum reservation number positive integer check
        if (maxAppointments <= 0) {
            System.out.println("Warning: Max appointments cannot be <=0, set to 50 by default");
            this.maxAppointments = 50;
        } else {
            this.maxAppointments = maxAppointments;
        }
    }

    /**
     * Print the basic information of doctors (general methods for basic classes)
     * Available for subclasses to call
     */
    public void printBaseInfo()
    {
        System.out.println("ID：" + id);
        System.out.println("Name：" + name);
        System.out.println("Professional field：" + specialization);
        System.out.println("Current status：" + status);
        System.out.println("Appointment status：" + currentAppointments + "/" + maxAppointments);
    }

    /**
     * Check if there are any remaining reservation quotas
     * @return true: Available quota; false: The contract has been fully booked
     */
    public boolean hasAvailableQuota()
    {
        return currentAppointments < maxAppointments;
    }

    /**
     * After making a new reservation, the current reservation number will increase by 1.
     * Only effective when there is a quota available
     */
    public void incrementAppointments()
    {
        if (hasAvailableQuota())
        {
            currentAppointments++;
        }
    }

    /**
     * Current number of appointments after cancellation -1
     * Only valid when there is an appointment
     */
    public void decrementAppointments()
    {
        if (currentAppointments > 0)
        {
            currentAppointments--;
        }
    }
    // The following are property getter/setter methods (providing controllable access)
    public int getId()
    {
        return id;
    }
    public String getName()
    {
        return name;
    }
    public String getSpecialization()
    {
        return specialization;
    }
    public String getStatus()
    {
        return status;
    }

    /**
     * Set the doctor's status (only valid status modification is supported)
     * @param status Target Status (Receiving patients/Stopped receiving patients)
     */
    public void setStatus(String status) {
        if (status.equals(STATUS_IN_CONSULTATION) || status.equals(STATUS_RESTING))
        {
            this.status = status;
        } else {
            System.out.println("Warning: The status is illegal and has not been modified");
        }
    }
    public int getMaxAppointments()
    {
        return maxAppointments;
    }
    public int getCurrentAppointments()
    {
        return currentAppointments;
    }
}
