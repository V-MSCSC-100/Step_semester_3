//Question 1
import java.util.*;

interface ScoringRule {
    double calculate(double idea, double execution, double presentation);
}

class InnovationScoring implements ScoringRule {
    public double calculate(double idea, double execution, double presentation) {
        return idea * 0.5 + execution * 0.3 + presentation * 0.2;
    }
}

class OpenScoring implements ScoringRule {
    public double calculate(double idea, double execution, double presentation) {
        return (idea + execution + presentation) / 3.0;
    }
}

class Student {
    String name;

    Student(String name) {
        this.name = name;
    }
}

class Score {
    double idea, execution, presentation;

    Score(double idea, double execution, double presentation) {
        this.idea = idea;
        this.execution = execution;
        this.presentation = presentation;
    }

    double finalScore(ScoringRule rule) {
        return rule.calculate(idea, execution, presentation);
    }
}

class Project {
    String name;
    Score score;

    Project(String name) {
        this.name = name;
    }

    void addScore(Score score) {
        this.score = score;
    }
}

class Team {
    String name;
    List<Student> members;
    String track;
    Project project;

    Team(String name, List<Student> members, String track) {
        this.name = name;
        this.members = members;
        this.track = track;
    }

    boolean submitProject(String projectName) {
        if (project != null)
            return false;

        project = new Project(projectName);
        return true;
    }
}

class Hackathon {
    enum State {
        OPEN, JUDGING, PUBLISHED
    }

    String name;
    List<Team> teams = new ArrayList<>();
    Set<Student> registeredStudents = new HashSet<>();
    Map<String, ScoringRule> rules = new HashMap<>();
    State state = State.OPEN;

    Hackathon(String name) {
        this.name = name;
        rules.put("Innovation", new InnovationScoring());
        rules.put("Open", new OpenScoring());
    }

    void registerTeam(Team team) {
        if (state != State.OPEN) {
            System.out.println("Registration failed: Registration is closed.");
            return;
        }

        if (team.members.size() < 2 || team.members.size() > 4) {
            System.out.println("Registration failed: A team must have 2 to 4 members.");
            return;
        }

        for (Student student : team.members) {
            if (registeredStudents.contains(student)) {
                System.out.println("Registration failed: Student already belongs to a team.");
                return;
            }
        }

        if (!rules.containsKey(team.track)) {
            System.out.println("Registration failed: Invalid track.");
            return;
        }

        teams.add(team);
        registeredStudents.addAll(team.members);

        System.out.println("Team " + team.name + " registered (" +
                team.members.size() + " members, " + team.track + " track).");
    }

    void submitProject(String teamName, String projectName) {
        for (Team team : teams) {
            if (team.name.equals(teamName)) {
                if (team.submitProject(projectName))
                    System.out.println("Project '" + projectName + "' submitted by " + teamName + ".");
                else
                    System.out.println("Submission failed: Team already has a project.");
                return;
            }
        }
    }

    void startJudging() {
        if (state == State.OPEN)
            state = State.JUDGING;
    }

    void scoreProject(String teamName, double idea, double execution, double presentation) {
        if (state == State.PUBLISHED) {
            System.out.println("Rescore rejected: Results have already been published.");
            return;
        }

        for (Team team : teams) {
            if (team.name.equals(teamName) && team.project != null) {
                team.project.addScore(new Score(idea, execution, presentation));
                System.out.println("Score recorded for '" + team.project.name + "'.");
                return;
            }
        }
    }

    void publishResults() {
        if (state != State.PUBLISHED)
            state = State.PUBLISHED;

        for (Team team : teams) {
            if (team.project != null && team.project.score != null) {
                double result = team.project.score.finalScore(rules.get(team.track));
                System.out.printf("Final score: %.2f%n", result);
            }
        }

        System.out.println("Results published.");
    }
}

public class Main {
    public static void main(String[] args) {
        Hackathon hackathon = new Hackathon("Code Sprint");

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");
        Student kiran = new Student("Kiran");

        Team byteBusters = new Team(
                "ByteBusters",
                Arrays.asList(asha, ravi, neha),
                "Innovation"
        );

        Team soloCoder = new Team(
                "SoloCoder",
                Arrays.asList(kiran),
                "Open"
        );

        hackathon.registerTeam(byteBusters);
        hackathon.registerTeam(soloCoder);

        hackathon.submitProject("ByteBusters", "SmartAttend");

        hackathon.startJudging();
        hackathon.scoreProject("ByteBusters", 8, 7, 9);

        hackathon.publishResults();

        hackathon.scoreProject("ByteBusters", 10, 7, 9);
    }
}

//Question 2

import java.util.*;

enum ParcelStatus {
    BOOKED, PICKED_UP, IN_TRANSIT, OUT_FOR_DELIVERY, DELIVERED
}

interface ShippingType {
    double calculateCharge(double weight);
}

class StandardShipping implements ShippingType {
    public double calculateCharge(double weight) {
        return 40 + 10 * weight;
    }
}

class ExpressShipping implements ShippingType {
    public double calculateCharge(double weight) {
        return 80 + 15 * weight;
    }
}

class FragileShipping implements ShippingType {
    public double calculateCharge(double weight) {
        return 40 + 10 * weight + 50;
    }
}

interface NotificationChannel {
    void notify(String parcelId, ParcelStatus status);
}

class SmsChannel implements NotificationChannel {
    public void notify(String parcelId, ParcelStatus status) {
        System.out.println("[SMS] " + parcelId + " is now " + status + ".");
    }
}

class EmailChannel implements NotificationChannel {
    public void notify(String parcelId, ParcelStatus status) {
        System.out.println("[Email] " + parcelId + " is now " + status + ".");
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Parcel {
    String id;
    double weight;
    ShippingType shippingType;
    ParcelStatus status;
    List<NotificationChannel> channels = new ArrayList<>();

    Parcel(String id, double weight, ShippingType shippingType) {
        this.id = id;
        this.weight = weight;
        this.shippingType = shippingType;
        this.status = ParcelStatus.BOOKED;
    }

    double getCharge() {
        return shippingType.calculateCharge(weight);
    }

    void subscribe(NotificationChannel channel) {
        channels.add(channel);
    }

    void notifyChannels() {
        for (NotificationChannel channel : channels)
            channel.notify(id, status);
    }

    void changeStatus(ParcelStatus newStatus) {
        boolean valid = false;

        if (status == ParcelStatus.BOOKED && newStatus == ParcelStatus.PICKED_UP)
            valid = true;
        else if (status == ParcelStatus.PICKED_UP && newStatus == ParcelStatus.IN_TRANSIT)
            valid = true;
        else if (status == ParcelStatus.IN_TRANSIT && newStatus == ParcelStatus.OUT_FOR_DELIVERY)
            valid = true;
        else if (status == ParcelStatus.OUT_FOR_DELIVERY && newStatus == ParcelStatus.DELIVERED)
            valid = true;

        if (!valid) {
            System.out.println("Invalid transition: " + status + " → " + newStatus + " is not allowed.");
            return;
        }

        status = newStatus;
        notifyChannels();
    }

    void cancel() {
        if (status != ParcelStatus.BOOKED) {
            System.out.println("Cancellation failed: " + id +
                    " can be cancelled only while BOOKED.");
            return;
        }

        System.out.println("Parcel " + id + " cancelled.");
    }
}

class ParcelService {
    Parcel bookParcel(Customer customer, String id, double weight, ShippingType type) {
        Parcel parcel = new Parcel(id, weight, type);

        System.out.println("Parcel " + id + " booked. Charge: ₹" +
                String.format("%.2f", parcel.getCharge()));

        return parcel;
    }
}

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer("Customer");

        ParcelService service = new ParcelService();

        Parcel parcel = service.bookParcel(
                customer,
                "P101",
                2,
                new ExpressShipping()
        );

        parcel.subscribe(new SmsChannel());
        parcel.subscribe(new EmailChannel());

        parcel.notifyChannels();

        parcel.changeStatus(ParcelStatus.PICKED_UP);

        parcel.cancel();

        parcel.changeStatus(ParcelStatus.IN_TRANSIT);

        parcel.changeStatus(ParcelStatus.DELIVERED);
    }
}

//Question 3

import java.util.*;

interface Capability {
    String getName();
    boolean setValue(double value);
}

class PowerCapability implements Capability {
    private boolean on;

    public String getName() {
        return "Power";
    }

    public boolean setValue(double value) {
        if (value != 0 && value != 1)
            return false;

        on = value == 1;
        return true;
    }

    boolean isOn() {
        return on;
    }
}

class BrightnessCapability implements Capability {
    private double brightness;

    public String getName() {
        return "Brightness";
    }

    public boolean setValue(double value) {
        if (value < 0 || value > 100)
            return false;

        brightness = value;
        return true;
    }
}

class TemperatureCapability implements Capability {
    private double temperature;

    public String getName() {
        return "Temperature";
    }

    public boolean setValue(double value) {
        if (value < 16 || value > 30)
            return false;

        temperature = value;
        return true;
    }
}

class Device {
    String name;
    Map<String, Capability> capabilities = new HashMap<>();

    Device(String name) {
        this.name = name;
    }

    void addCapability(Capability capability) {
        capabilities.put(capability.getName(), capability);
        System.out.println(name + ": " + capability.getName() + " capability added.");
    }

    boolean hasCapability(String name) {
        return capabilities.containsKey(name);
    }

    void apply(String capabilityName, double value) {
        Capability capability = capabilities.get(capabilityName);

        if (capability == null)
            return;

        if (!capability.setValue(value)) {
            if (capabilityName.equals("Temperature"))
                System.out.println("Rejected: " + name +
                        " temperature must be between 16°C and 30°C.");
            else if (capabilityName.equals("Brightness"))
                System.out.println("Rejected: " + name +
                        " brightness must be between 0% and 100%.");
            return;
        }

        if (capabilityName.equals("Power"))
            System.out.println(name + ": " + (value == 1 ? "ON" : "OFF") + ".");
        else if (capabilityName.equals("Brightness"))
            System.out.println(name + ": brightness set to " + value + "%.");
        else if (capabilityName.equals("Temperature"))
            System.out.println(name + ": temperature set to " + value + "°C.");
    }
}

class SceneStep {
    String capability;
    double value;

    SceneStep(String capability, double value) {
        this.capability = capability;
        this.value = value;
    }

    int execute(List<Device> devices) {
        int count = 0;

        for (Device device : devices) {
            if (device.hasCapability(capability)) {
                device.apply(capability, value);
                count++;
            }
        }

        return count;
    }
}

class Scene {
    String name;
    List<SceneStep> steps = new ArrayList<>();

    Scene(String name) {
        this.name = name;
    }

    void addStep(SceneStep step) {
        steps.add(step);
    }

    void execute(List<Device> devices) {
        System.out.println("Scene '" + name + "' started.");

        int actions = 0;

        for (SceneStep step : steps)
            actions += step.execute(devices);

        System.out.println("Scene '" + name + "' completed: " +
                actions + " actions applied.");
    }
}

public class Main {
    public static void main(String[] args) {
        Device ac = new Device("Lab AC");
        Device lights = new Device("Ceiling Lights");
        Device projector = new Device("Projector");

        ac.addCapability(new PowerCapability());
        ac.addCapability(new TemperatureCapability());

        lights.addCapability(new PowerCapability());
        lights.addCapability(new BrightnessCapability());

        projector.addCapability(new PowerCapability());

        List<Device> devices = Arrays.asList(ac, lights, projector);

        Scene lectureMode = new Scene("Lecture Mode");

        lectureMode.addStep(new SceneStep("Power", 1));
        lectureMode.addStep(new SceneStep("Brightness", 40));
        lectureMode.addStep(new SceneStep("Temperature", 24));

        lectureMode.execute(devices);

        ac.apply("Temperature", 12);

        projector.addCapability(new BrightnessCapability());
        projector.apply("Brightness", 70);
    }
}

//Question 4

import java.util.*;

interface CreditPolicy {
    int getLimit();
}

class RegularPolicy implements CreditPolicy {
    public int getLimit() {
        return 24;
    }
}

class HonorsPolicy implements CreditPolicy {
    public int getLimit() {
        return 28;
    }
}

class ExchangePolicy implements CreditPolicy {
    public int getLimit() {
        return 20;
    }
}

class Student {
    String name;
    int currentCredits;
    CreditPolicy policy;

    Student(String name, int currentCredits, CreditPolicy policy) {
        this.name = name;
        this.currentCredits = currentCredits;
        this.policy = policy;
    }

    boolean canTake(int credits) {
        return currentCredits + credits <= policy.getLimit();
    }

    void addCredits(int credits) {
        currentCredits += credits;
    }

    void removeCredits(int credits) {
        currentCredits -= credits;
    }
}

class Enrollment {
    Student student;
    Elective elective;

    Enrollment(Student student, Elective elective) {
        this.student = student;
        this.elective = elective;
    }
}

class Elective {
    String name;
    int credits;
    int capacity;
    List<Enrollment> enrolled = new ArrayList<>();
    Queue<Student> waitlist = new LinkedList<>();

    Elective(String name, int credits, int capacity) {
        this.name = name;
        this.credits = credits;
        this.capacity = capacity;
    }

    boolean isEnrolled(Student student) {
        for (Enrollment e : enrolled)
            if (e.student == student)
                return true;

        return false;
    }

    boolean isWaiting(Student student) {
        return waitlist.contains(student);
    }

    void enroll(Student student) {
        if (isEnrolled(student) || isWaiting(student)) {
            System.out.println("Enrollment failed: Student already enrolled or waitlisted.");
            return;
        }

        if (!student.canTake(credits)) {
            System.out.println("Enrollment failed: " + student.name +
                    " would exceed the credit limit (" +
                    (student.currentCredits + credits) + "/" +
                    student.policy.getLimit() + ").");
            return;
        }

        if (enrolled.size() >= capacity) {
            waitlist.add(student);
            System.out.println(name + " is full. " + student.name +
                    " added to waitlist (position " + waitlist.size() + ").");
            return;
        }

        enrolled.add(new Enrollment(student, this));
        student.addCredits(credits);

        System.out.println(student.name + " enrolled in " + name +
                " (credits: " + student.currentCredits + "/" +
                student.policy.getLimit() + ").");
    }

    void drop(Student student) {
        Enrollment target = null;

        for (Enrollment e : enrolled) {
            if (e.student == student) {
                target = e;
                break;
            }
        }

        if (target == null)
            return;

        enrolled.remove(target);
        student.removeCredits(credits);

        System.out.println(student.name + " dropped " + name +
                " (credits: " + student.currentCredits + "/" +
                student.policy.getLimit() + ").");

        promote();
    }

    void promote() {
        Iterator<Student> iterator = waitlist.iterator();

        while (iterator.hasNext()) {
            Student student = iterator.next();

            if (student.canTake(credits)) {
                iterator.remove();
                enrolled.add(new Enrollment(student, this));
                student.addCredits(credits);

                System.out.println(student.name +
                        " promoted from waitlist and enrolled in " + name +
                        " (credits: " + student.currentCredits + "/" +
                        student.policy.getLimit() + ").");
                return;
            }
        }
    }
}

class EnrollmentService {
    void enroll(Elective elective, Student student) {
        elective.enroll(student);
    }

    void drop(Elective elective, Student student) {
        elective.drop(student);
    }
}

public class Main {
    public static void main(String[] args) {
        Elective cloud = new Elective("Cloud Computing", 4, 2);

        Student asha = new Student("Asha", 20, new RegularPolicy());
        Student ravi = new Student("Ravi", 22, new HonorsPolicy());
        Student neha = new Student("Neha", 12, new ExchangePolicy());
        Student kiran = new Student("Kiran", 22, new RegularPolicy());

        EnrollmentService service = new EnrollmentService();

        service.enroll(cloud, asha);
        service.enroll(cloud, ravi);
        service.enroll(cloud, neha);
        service.enroll(cloud, kiran);

        service.drop(cloud, asha);
    }
}

//Question 5

import java.util.*;

interface PricingPlan {
    double calculatePrice(double originalPrice);
}

class DayScholarPlan implements PricingPlan {
    public double calculatePrice(double originalPrice) {
        return originalPrice;
    }
}

class HostellerPlan implements PricingPlan {
    public double calculatePrice(double originalPrice) {
        return originalPrice * 0.90;
    }
}

class StaffPlan implements PricingPlan {
    public double calculatePrice(double originalPrice) {
        return originalPrice * 0.80;
    }
}

class Transaction {
    double amount;
    String description;

    Transaction(double amount, String description) {
        this.amount = amount;
        this.description = description;
    }
}

class SmartCard {
    String id;
    PricingPlan plan;
    List<Transaction> transactions = new ArrayList<>();
    boolean blocked;

    SmartCard(String id, PricingPlan plan) {
        this.id = id;
        this.plan = plan;
    }

    double getBalance() {
        double balance = 0;

        for (Transaction transaction : transactions)
            balance += transaction.amount;

        return balance;
    }

    void topUp(double amount) {
        if (blocked) {
            System.out.println("Top-up failed: Card is blocked.");
            return;
        }

        if (amount < 100) {
            System.out.println("Top-up failed: Minimum top-up is ₹100.");
            return;
        }

        if (getBalance() + amount > 5000) {
            System.out.println("Top-up failed: Maximum balance is ₹5000.");
            return;
        }

        transactions.add(new Transaction(amount, "Top-up"));

        System.out.printf("%s topped up with ₹%.2f. Balance: ₹%.2f.%n",
                id, amount, getBalance());
    }

    void purchase(String item, double originalPrice) {
        if (blocked) {
            System.out.println("Purchase failed: Card is blocked.");
            return;
        }

        double price = plan.calculatePrice(originalPrice);

        if (getBalance() < price) {
            System.out.printf(
                    "Purchase failed: Insufficient balance (required ₹%.2f, available ₹%.2f).%n",
                    price, getBalance());
            return;
        }

        transactions.add(new Transaction(-price, item));

        System.out.printf("%s purchased for ₹%.2f. Balance: ₹%.2f.%n",
                item, price, getBalance());
    }

    void refund(String item) {
        for (Transaction transaction : transactions) {
            if (transaction.description.equals(item)) {
                for (Transaction other : transactions) {
                    if (other.description.equals("Refund: " + item)) {
                        System.out.println("Refund rejected: " + item +
                                " has already been refunded.");
                        return;
                    }
                }

                double amount = -transaction.amount;
                transactions.add(new Transaction(amount, "Refund: " + item));

                System.out.printf("Refund of ₹%.2f for %s processed. Balance: ₹%.2f.%n",
                        amount, item, getBalance());
                return;
            }
        }

        System.out.println("Refund rejected: Purchase not found.");
    }

    void block() {
        blocked = true;
    }

    void unblock() {
        blocked = false;
    }

    void miniStatement() {
        System.out.print("Mini-statement for " + id + ": ");

        for (int i = 0; i < transactions.size(); i++) {
            double amount = transactions.get(i).amount;

            if (amount >= 0)
                System.out.printf("+%.2f", amount);
            else
                System.out.printf("%.2f", amount);

            if (i < transactions.size() - 1)
                System.out.print(", ");
        }

        System.out.printf(" = ₹%.2f.%n", getBalance());
    }
}

public class Main {
    public static void main(String[] args) {
        SmartCard card = new SmartCard("C-2045", new HostellerPlan());

        card.topUp(500);

        card.purchase("Veg Thali", 120);
        card.purchase("Cold Coffee", 60);
        card.purchase("Items", 400);

        card.refund("Veg Thali");
        card.refund("Veg Thali");

        card.miniStatement();
    }
}
