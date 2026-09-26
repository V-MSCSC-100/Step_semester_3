//Question 1
abstract class PaymentMethod {
    private static int counter = 1001;
    private final String transactionId;

    PaymentMethod() {
        transactionId = "TXN-" + counter++;
    }

    public abstract String processPayment(double amount);

    public String processPayment(double amount, String note) {
        return processPayment(amount) + " (" + note + ")";
    }

    public String getTransactionId() {
        return transactionId;
    }
}

class CreditCardPayment extends PaymentMethod {
    private String cardNumberLastFour;

    public CreditCardPayment(String cardNumberLastFour) {
        this.cardNumberLastFour = cardNumberLastFour;
    }

    @Override
    public String processPayment(double amount) {
        return "Charged $" + amount +
               " to card ending " + cardNumberLastFour +
               " - Txn " + getTransactionId();
    }
}

class CashPayment extends PaymentMethod {

    public CashPayment() {
        super();
    }

    @Override
    public String processPayment(double amount) {
        return "Received $" + amount +
               " in cash - Txn " + getTransactionId();
    }
}

public class Main {
    static void printConfirmation(PaymentMethod payment, double amount) {
        System.out.println(payment.processPayment(amount));
    }

    public static void main(String[] args) {
        CreditCardPayment cc =
                new CreditCardPayment("4471");

        System.out.println(cc.processPayment(250.0));

        System.out.println(
            cc.processPayment(250.0, "Birthday gift")
        );

        CashPayment cash = new CashPayment();
        System.out.println(cash.processPayment(40.0));

        PaymentMethod ref = cc;
        printConfirmation(ref, 250.0);


    }
}

//Question 2
interface Alertable {
    String sendAlert(String message);
}

class SecuritySensor {
    protected String zoneName;

    public SecuritySensor(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getZoneName() {
        return zoneName;
    }
}

class MotionSensor extends SecuritySensor
        implements Alertable {

    public MotionSensor(String zoneName) {
        super(zoneName);
    }

    @Override
    public String sendAlert(String message) {
        return "[" + zoneName + "] " + message;
    }
}

class DualZoneMotionSensor extends MotionSensor {
    private String secondZoneName;

    public DualZoneMotionSensor(
            String zoneName,
            String secondZoneName) {

        super(zoneName);
        this.secondZoneName = secondZoneName;
    }

    @Override
    public String sendAlert(String message) {
        return super.sendAlert(message)
                + "\n[also covering " + secondZoneName + "]";
    }
}

class SmokeDetector implements Alertable {
    private String deviceId;

    public SmokeDetector(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String sendAlert(String message) {
        return "[" + deviceId + "] " + message;
    }
}

public class Main {

    static void broadcastAll(
            Alertable[] devices,
            String message) {

        for (Alertable device : devices) {
            System.out.println(device.sendAlert(message));
        }
    }

    static String getZoneIfMotionSensor(Alertable a) {
        if (a instanceof MotionSensor) {
            MotionSensor m = (MotionSensor) a;
            return m.getZoneName();
        }

        return "Not a motion sensor";
    }

    public static void main(String[] args) {
        MotionSensor m =
                new MotionSensor("Living Room");

        System.out.println(
            m.sendAlert("Motion detected")
        );

        DualZoneMotionSensor d =
                new DualZoneMotionSensor(
                    "Hallway",
                    "Stairwell"
                );

        System.out.println(
            d.sendAlert("Motion detected")
        );

        SmokeDetector s =
                new SmokeDetector("SD-01");

        System.out.println(
            s.sendAlert("Smoke detected")
        );

        System.out.println(getZoneIfMotionSensor(m));
        System.out.println(getZoneIfMotionSensor(s));

        broadcastAll(
            new Alertable[]{m, d, s},
            "Alert!"
        );
    }
}
//Question 3

abstract class StaffMember {
    private double baseSalary;
    protected double bonusRate;

    public StaffMember(double baseSalary) {
        this(baseSalary, 0.10);
    }

    public StaffMember(
            double baseSalary,
            double bonusRate) {

        this.baseSalary = baseSalary;
        this.bonusRate = bonusRate;
    }

    public abstract double calculateBonus();

    public double getSalary() {
        return baseSalary;
    }

    public void setSalary(double baseSalary) {
        if (baseSalary >= 0) {
            this.baseSalary = baseSalary;
        }
    }
}

interface Auditable {
    String auditRecord();
}

class TeamLead extends StaffMember
        implements Auditable {

    private int teamSize;

    public TeamLead(
            double baseSalary,
            int teamSize) {

        super(baseSalary);
        this.teamSize = teamSize;
    }

    public TeamLead(
            double baseSalary,
            double bonusRate,
            int teamSize) {

        super(baseSalary, bonusRate);
        this.teamSize = teamSize;
    }

    @Override
    public double calculateBonus() {
        return getSalary() * bonusRate;
    }

    @Override
    public String auditRecord() {
        return "TeamLead audit: " + teamSize +
               " team members, salary $" +
               getSalary();
    }
}

public class Main {

    static String getAuditIfApplicable(StaffMember s) {
        if (s instanceof Auditable) {
            Auditable a = (Auditable) s;
            return a.auditRecord();
        }

        return "No audit required";
    }

    public static void main(String[] args) {
        TeamLead t = new TeamLead(60000, 5);

        System.out.println(t.calculateBonus());

        TeamLead t2 =
                new TeamLead(60000, 0.20, 5);

        System.out.println(t2.calculateBonus());

        t.setSalary(-5000);
        System.out.println(t.getSalary());

        StaffMember ref = t; // Upcasting

        System.out.println(
            getAuditIfApplicable(ref)
        );
    }
}

//Question 4

interface Playable {
    String play();
    String play(int fromSecond);
    String pause();
}

abstract class MediaFile {
    private static int counter = 1001;
    private final String fileId;

    MediaFile() {
        fileId = "MF-" + counter++;
    }

    public abstract String getFormatInfo();

    public String getFileId() {
        return fileId;
    }
}

class AudioFile extends MediaFile
        implements Playable {

    private String title;

    public AudioFile(String title) {
        this.title = title;
    }

    @Override
    public String play() {
        return "Playing audio: " + title;
    }

    @Override
    public String play(int fromSecond) {
        int minutes = fromSecond / 60;
        int seconds = fromSecond % 60;

        return "Playing audio: " + title +
               String.format(
                   " from %d:%02d",
                   minutes,
                   seconds
               );
    }

    @Override
    public String pause() {
        return "Audio paused: " + title;
    }

    @Override
    public String getFormatInfo() {
        return "Audio file, ID: " + getFileId();
    }
}

class Podcast implements Playable {
    private String showName;
    private int episodeNumber;

    public Podcast(
            String showName,
            int episodeNumber) {

        this.showName = showName;
        this.episodeNumber = episodeNumber;
    }

    @Override
    public String play() {
        return "Streaming episode " +
               episodeNumber +
               " of " + showName;
    }

    @Override
    public String play(int fromSecond) {
        return "Streaming episode " +
               episodeNumber +
               " of " + showName +
               " from " + fromSecond + " seconds";
    }

    @Override
    public String pause() {
        return "Podcast paused: " + showName;
    }
}

public class Main {

    static void launchAll(Playable[] items) {
        for (Playable item : items) {
            System.out.println(item.play());
        }
    }

    public static void main(String[] args) {
        AudioFile a =
                new AudioFile("Morning Jazz");

        System.out.println(a.play());
        System.out.println(a.play(30));
        System.out.println(a.getFormatInfo());

        Podcast p =
                new Podcast("Tech Talk", 12);

        System.out.println(p.play());

        Playable ref = a; // Upcasting

        System.out.println(ref.play());

        launchAll(new Playable[]{ref, p});
    }
}

//Question 5
abstract class LibraryItem {
    private static int counter = 1001;
    private final String itemId;

    LibraryItem() {
        itemId = "LIB-" + counter++;
    }

    public abstract int getLoanPeriodDays();

    public String getItemId() {
        return itemId;
    }
}

interface Renewable {
    String renew();
}

interface Reservable {
    String reserve();
}

class Textbook extends LibraryItem
        implements Renewable, Reservable {

    private String title;

    public Textbook(String title) {
        this.title = title;
    }

    @Override
    public int getLoanPeriodDays() {
        return 14;
    }

    @Override
    public String renew() {
        return title + " renewed";
    }

    @Override
    public String reserve() {
        return title + " reserved";
    }
}

class Magazine extends LibraryItem
        implements Renewable {

    private String title;

    public Magazine(String title) {
        this.title = title;
    }

    @Override
    public int getLoanPeriodDays() {
        return 7;
    }

    @Override
    public String renew() {
        return title + " renewed";
    }
}

class DigitalPass implements Renewable {
    private String resourceName;

    public DigitalPass(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public String renew() {
        return resourceName + " renewed";
    }
}

public class Main {

    static void processCheckouts(
            LibraryItem[] items) {

        for (LibraryItem item : items) {
            System.out.println(
                item.getLoanPeriodDays()
            );
        }
    }

    static String reserveIfSupported(Object o) {
        if (o instanceof Reservable) {
            Reservable r = (Reservable) o;
            return r.reserve();
        }

        return "Reservation not supported";
    }

    public static void main(String[] args) {
        Textbook t =
                new Textbook("Java Fundamentals");

        System.out.println(
            t.getLoanPeriodDays()
        );

        System.out.println(t.renew());
        System.out.println(t.reserve());

        Magazine m =
                new Magazine("Tech Monthly");

        System.out.println(
            reserveIfSupported(m)
        );

        DigitalPass d =
                new DigitalPass("E-Journal Access");

        System.out.println(
            reserveIfSupported(d)
        );

        LibraryItem ref = t; // Upcasting

        System.out.println(
            reserveIfSupported(ref)
        );

        processCheckouts(
            new LibraryItem[]{t, m}
        );
    }
}

