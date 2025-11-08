public class HealthProfessional {
    public static final String STATUS_ON_CALL = "On call";
    public static final String STATUS_IN_CONSULTATION = "Receiving patients";
    public static final String STATUS_RESTING = "At rest";

    private int id;
    private String name;
    private String specialization;
    private String status;
    private int currentAppointments;
    private int maxAppointments;

    public HealthProfessional()
    {
        this.id = 0;
        this.name = "Unknown";
        this.specialization = "Unknown";
        this.status = STATUS_RESTING;
        this.currentAppointments = 0;
        this.maxAppointments = 50;
    }

    public HealthProfessional(int id, String name, String specialization, String status, int currentAppointments, int maxAppointments) {
        if (id < 0) {
            System.out.println("Warning: ID cannot be negative, set to 0 by default");
            this.id = 0;
        } else {
            this.id = id;
        }
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Warning: Name cannot be empty, set to 'Unknown' by default");
            this.name = "Unknown";
        } else {
            this.name = name;
        }
        if (specialization == null || specialization.trim().isEmpty()) {
            System.out.println("Warning: Specialization cannot be empty, set to 'Unknown' by default");
            this.specialization = "Unknown";
        } else {
            this.specialization = specialization;
        }
        if (status.equals(STATUS_ON_CALL) || status.equals(STATUS_IN_CONSULTATION) || status.equals(STATUS_RESTING))
        {
            this.status = status;
        }
        else
        {
            this.status = STATUS_RESTING;
            System.out.println("Warning: The status is illegal and has been set by default" + STATUS_RESTING);
        }
        this.currentAppointments = 0;
        if (currentAppointments < 0){
            System.out.println("Warning: The current reservation number cannot be negative and defaults to 0");
        }else {
            this.currentAppointments = currentAppointments;
        }
        if (maxAppointments <= 0) {
            System.out.println("Warning: Max appointments cannot be <=0, set to 50 by default");
            this.maxAppointments = 50;
        } else {
            this.maxAppointments = maxAppointments;
        }
    }

    public void printBaseInfo()
    {
        System.out.println("ID：" + id);
        System.out.println("Name：" + name);
        System.out.println("Professional field：" + specialization);
        System.out.println("Current status：" + status);
        System.out.println("Appointment status：" + currentAppointments + "/" + maxAppointments);
    }
    public boolean hasAvailableQuota()
    {
        return currentAppointments < maxAppointments;
    }


    public void incrementAppointments()
    {
        if (hasAvailableQuota())
        {
            currentAppointments++;
        }
    }

    public void decrementAppointments()
    {
        if (currentAppointments > 0)
        {
            currentAppointments--;
        }
    }
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
    public void setStatus(String status) {
        if (status.equals(STATUS_ON_CALL) || status.equals(STATUS_IN_CONSULTATION) || status.equals(STATUS_RESTING))
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
