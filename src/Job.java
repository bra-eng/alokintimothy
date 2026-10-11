public abstract class Job implements Chargeable {
    private String id;
    private String customerName;
    private boolean loyal;

    protected Job(String id, String customerName, boolean loyal) {
        setId(id);
        setCustomerName(customerName);
        this.loyal = loyal;
    }

    public String getId() {
        return id;
    }

    public final void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new InvalidJobException("Job ID cannot be blank.");
        }
        this.id = id.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public final void setCustomerName(String customerName) {
        if (customerName == null || customerName.trim().isEmpty()) {
            throw new InvalidJobException("Customer name cannot be blank.");
        }
        this.customerName = customerName.trim();
    }

    public boolean isLoyal() {
        return loyal;
    }

    public void setLoyal(boolean loyal) {
        this.loyal = loyal;
    }

    protected abstract double calculateBaseCharge();

    public double calculateDiscount() {
        return loyal ? calculateBaseCharge() * 0.09 : 0.0;
    }

    public double getDiscountAmount() {
        return calculateDiscount();
    }

    @Override
    public abstract double calculateCharge();

    public String getJobType() {
        return getClass().getSimpleName();
    }
}
