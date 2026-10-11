public class NewGarmentJob extends Job {
    private double fabricCost;

    public NewGarmentJob(String id, String customerName, boolean loyal, double fabricCost) {
        super(id, customerName, loyal);
        setFabricCost(fabricCost);
    }

    public double getFabricCost() {
        return fabricCost;
    }

    public final void setFabricCost(double fabricCost) {
        if (Double.isNaN(fabricCost) || Double.isInfinite(fabricCost) || fabricCost < 0) {
            throw new InvalidJobException("Fabric cost cannot be negative or invalid.");
        }
        this.fabricCost = fabricCost;
    }

    @Override
    protected double calculateBaseCharge() {
        return fabricCost + 40000.0;
    }

    @Override
    public double calculateCharge() {
        double base = calculateBaseCharge();
        return base - (isLoyal() ? base * 0.09 : 0.0);
    }

    @Override
    public String toString() {
        return String.format(
            "%s | %-15s | %-16s | fabric=UGX %,.2f | loyal=%s | charge=UGX %,.2f",
            getId(), getCustomerName(), getJobType(), fabricCost, isLoyal(), calculateCharge()
        );
    }
}
