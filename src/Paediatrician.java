public class Paediatrician extends HealthProfessional {
    private boolean canDoVaccination;
    public Paediatrician()
    {
        super();
        this.canDoVaccination = false;
    }

    public Paediatrician(int id, String name, String specialization, String status, boolean canDoVaccination)
    {
        super(id, name, specialization, status);
        this.canDoVaccination = canDoVaccination;
    }
    public void printDetails()
    {
        System.out.println("Doctor Type: Paediatrician");
        super.printBaseInfo();
        String vaccinationInfo; 
        if (canDoVaccination) {
            vaccinationInfo = "Children can receive vaccines";
        } else {
            vaccinationInfo = "Vaccination is not available for the time being";
        }
        System.out.println("Vaccination service：" + vaccinationInfo);
    }
    public boolean isCanDoVaccination()
    {
        return canDoVaccination;
    }
}
