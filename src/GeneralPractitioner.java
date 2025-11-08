public class GeneralPractitioner extends HealthProfessional {
    private boolean providesHouseCalls;
    public GeneralPractitioner()
    {
        super();
        this.providesHouseCalls = false;
    }
    public GeneralPractitioner(int id, String name, String specialization, String status,int currentAppointments,  int maxAppointments, boolean providesHouseCalls)
    {
        super(id, name, specialization, status, currentAppointments, maxAppointments);
        this.providesHouseCalls = providesHouseCalls;
    }

    public void printDetails()
    {
        System.out.println("Doctor Type: General Practitioner");
        super.printBaseInfo();
        String houseCallInfo = providesHouseCalls ? "Home visit service provided" : "Home visit service not provided";
        System.out.println("Home visits：" + houseCallInfo);
    }
    public boolean isProvidesHouseCalls()
    {
        return providesHouseCalls;
    }
}