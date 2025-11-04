public class GeneralPractitioner extends HealthProfessional {
    public GeneralPractitioner()
    {
        super();
    }
    public GeneralPractitioner(int id, String name, String specialization, String status)
    {
        super(id, name, specialization, status);
    }

    public void printDetails()
    {
        System.out.println("Doctor Type: General Practitioner");
        super.printBaseInfo();
    }
}