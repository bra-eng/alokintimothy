public class RepairJob extends Job {
    private int items;

    public RepairJob(String id, String customerName, boolean loyal, int items) {
        super(id, customerName, loyal);
        setItems(items);
    }

    public int getItems() {
        return items;
    }

    public final void setItems(int items) {
        if (items <= 0) {
            throw new InvalidJobException("Number of repair items must be greater than zero.");
        }
        this.items = items;
    }

    @Override
    protected double calculateBaseCharge() {
        return items * 10000.0;
    }

    @Override
    public double calculateCharge() {
        double base = calculateBaseCharge();
        return base - (isLoyal() ? base * 0.09 : 0.0);
    }

    @Override
    public String toString() {
        return String.format(
            "%s | %-15s | %-16s | items=%d | loyal=%s | charge=UGX %,.2f",
            getId(), getCustomerName(), getJobType(), items, isLoyal(), calculateCharge()
        );
    }
}
