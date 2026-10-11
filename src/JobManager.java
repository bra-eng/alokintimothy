import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class JobManager {
    private final ArrayList<Job> jobs = new ArrayList<>();

    public void addJob(Job job) {
        if (job == null) {
            throw new InvalidJobException("Job cannot be null.");
        }
        if (findJobOrNull(job.getId()) != null) {
            throw new InvalidJobException("A job with ID " + job.getId() + " already exists.");
        }
        jobs.add(job);
    }

    public Job findJob(String id) {
        Job job = findJobOrNull(id);
        if (job == null) {
            throw new JobNotFoundException("No job was found with ID " + id + ".");
        }
        return job;
    }

    private Job findJobOrNull(String id) {
        if (id == null) return null;
        for (Job job : jobs) {
            if (job.getId().equalsIgnoreCase(id.trim())) {
                return job;
            }
        }
        return null;
    }

    public boolean removeJob(String id) {
        Job job = findJob(id);
        return jobs.remove(job);
    }

    public double getTotalCharges() {
        double total = 0.0;
        for (Chargeable record : jobs) {
            total += record.calculateCharge();
        }
        return total;
    }

    public double getTotalDiscount() {
        double total = 0.0;
        for (Job job : jobs) {
            total += job.getDiscountAmount();
        }
        return total;
    }

    public List<Job> getSortedJobs() {
        ArrayList<Job> sorted = new ArrayList<>(jobs);
        sorted.sort(Comparator.comparing(Job::getCustomerName, String.CASE_INSENSITIVE_ORDER));
        return sorted;
    }

    public int size() {
        return jobs.size();
    }

    public boolean isEmpty() {
        return jobs.isEmpty();
    }
}
