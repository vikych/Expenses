
public class Expenses {

    private int serviceAmount;
    private String service;


    public Expenses() {
        this.serviceAmount = 0;
        this.service = "service";
    }

    public int getServiceAmount() {
        return serviceAmount;
    }

    public void setServiceAmount(int serviceAmount) {
        this.serviceAmount = serviceAmount;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }


    @Override
    public String toString() {
        return "Service he/she has paid for: " + '"' + this.getService() + '"' +
                "\n" + "Amount of it: " + this.getServiceAmount() + "\n";
    }
}
