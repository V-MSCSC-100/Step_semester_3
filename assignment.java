//Question 1
abstract class Shape {
    private static int counter = 1001;
    private final String shapeId;

    Shape() {
        shapeId = "SH-" + counter++;
    }

    public abstract double calculateArea();

    public void scale(double factor) {
        scale(factor, factor);
    }

    public void scale(double xFactor, double yFactor) {

    }

    public String getShapeId() {
        return shapeId;
    }
}

class CircleShape extends Shape {
    private double radius;

    public CircleShape(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void scale(double xFactor, double yFactor) {
        radius *= xFactor;
        radius *= yFactor;
    }
}

class SquareShape extends Shape {
    private double side;

    public SquareShape(double side) {
        this.side = side;
    }

    @Override
    public double calculateArea() {
        return side * side;
    }

    @Override
    public void scale(double xFactor, double yFactor) {
        side *= xFactor;
        side *= yFactor;
    }
}

public class Main {
    static void printArea(Shape s) {
        System.out.println(s.calculateArea());
    }

    public static void main(String[] args) {
        CircleShape c = new CircleShape(5.0);
        System.out.println(c.calculateArea());

        SquareShape sq = new SquareShape(4.0);
        System.out.println(sq.calculateArea());

        sq.scale(2.0);
        System.out.println(sq.calculateArea());

        printArea(c);

        System.out.println(c.getShapeId());
        System.out.println(sq.getShapeId());

    }
}

//Question 2
interface Exportable {
    String exportData();
}

class ExportCounter {
    private static int totalExports = 0;

    static void increment() {
        totalExports++;
    }

    static int getTotalExports() {
        return totalExports;
    }
}

class ReportGenerator implements Exportable {
    private String reportName;

    public ReportGenerator(String reportName) {
        this.reportName = reportName;
    }

    @Override
    public String exportData() {
        ExportCounter.increment();
        return "Exported report: " + reportName;
    }
}

class UserProfile implements Exportable {
    private String username;

    public UserProfile(String username) {
        this.username = username;
    }

    @Override
    public String exportData() {
        ExportCounter.increment();
        return "Exported profile: " + username;
    }
}

public class Main {
    static void exportAll(Exportable[] items) {
        for (Exportable item : items) {
            System.out.println(item.exportData());
        }
    }

    static int getTotalExports() {
        return ExportCounter.getTotalExports();
    }

    public static void main(String[] args) {
        ReportGenerator r = new ReportGenerator("Sales Q1");
        UserProfile u = new UserProfile("jane_doe");

        System.out.println(r.exportData());
        System.out.println(u.exportData());

        Exportable ref = r;

        exportAll(new Exportable[]{ref, u});

        System.out.println(getTotalExports());
    }
}

//Question 3
abstract class ServiceableVehicle {
    private double mileage;

    public abstract String performMaintenance();

    public double getMileage() {
        return mileage;
    }

    public void addMileage(double km) {
        if (km < 0) {
            return;
        }

        mileage += km;
    }
}

interface Insurable {
    String getInsuranceInfo();
}

class Forklift extends ServiceableVehicle implements Insurable {
    protected String assetTag;

    public Forklift(String assetTag) {
        this.assetTag = assetTag;
    }

    @Override
    public String performMaintenance() {
        return "Forklift " + assetTag +
               ": hydraulic and fork inspection complete";
    }

    @Override
    public String getInsuranceInfo() {
        return "Insured under fleet policy - Asset " + assetTag;
    }
}

class HeavyDutyForklift extends Forklift {

    public HeavyDutyForklift(String assetTag) {
        super(assetTag);
    }

    @Override
    public String performMaintenance() {
        return super.performMaintenance()
                + " | high pressure hydraulic check complete";
    }
}

public class Main {
    static String getInsuranceIfApplicable(ServiceableVehicle v) {
        if (v instanceof Insurable) {
            Insurable i = (Insurable) v;
            return i.getInsuranceInfo();
        }

        return "No insurance record exists";
    }

    public static void main(String[] args) {
        Forklift f = new Forklift("FL-22");

        f.addMileage(120);
        System.out.println(f.getMileage());

        System.out.println(f.performMaintenance());

        HeavyDutyForklift hd = new HeavyDutyForklift("HD-9");
        System.out.println(hd.performMaintenance());

        System.out.println(getInsuranceIfApplicable(f));
    }
}

//Question 4

interface Attackable {
    String attack();
    String attack(String weaponName);
}

interface Defendable {
    String defend();
}

abstract class GameCharacter {
    private static int counter = 1001;
    private final String characterId;

    GameCharacter() {
        characterId = "CHAR-" + counter++;
    }

    public abstract String getSpecialMove();

    public String getCharacterId() {
        return characterId;
    }
}

class Warrior extends GameCharacter implements Attackable, Defendable {
    private String name;

    public Warrior(String name) {
        this.name = name;
    }

    @Override
    public String attack() {
        return name + " strikes with a blade";
    }

    @Override
    public String attack(String weaponName) {
        return name + " strikes with an " + weaponName;
    }

    @Override
    public String defend() {
        return name + " raises a shield";
    }

    @Override
    public String getSpecialMove() {
        return name + " unleashes Whirlwind Slash";
    }
}

class Trap implements Defendable {
    private String trapType;

    public Trap(String trapType) {
        this.trapType = trapType;
    }

    @Override
    public String defend() {
        return trapType + " triggers automatically";
    }
}

public class Main {
    static void resolveDefense(Defendable[] combatants) {
        for (Defendable combatant : combatants) {
            System.out.println(combatant.defend());
        }
    }

    public static void main(String[] args) {
        Warrior w = new Warrior("Kael");

        System.out.println(w.attack());
        System.out.println(w.attack("Iron Sword"));
        System.out.println(w.defend());
        System.out.println(w.getSpecialMove());

        Trap t = new Trap("Spike Pit");
        System.out.println(t.defend());

        resolveDefense(new Defendable[]{w, t});
    }
}

//Question 5

abstract class HomeDevice {
    private static int counter = 1001;
    private final String serialNumber;

    HomeDevice() {
        serialNumber = "HD-" + counter++;
    }

    public abstract String activate();

    public String getSerialNumber() {
        return serialNumber;
    }
}

interface RemoteControllable {
    String connect(String appId);
}

interface EnergyTrackable {
    double getConsumptionWatts();
}

class WashingMachine extends HomeDevice
        implements RemoteControllable, EnergyTrackable {

    private double consumptionWatts;

    public WashingMachine(double consumptionWatts) {
        this.consumptionWatts = consumptionWatts;
    }

    @Override
    public String activate() {
        return "Washing machine " + getSerialNumber() +
               " started a cycle";
    }

    @Override
    public String connect(String appId) {
        return getSerialNumber() + " connected to " + appId;
    }

    @Override
    public double getConsumptionWatts() {
        return consumptionWatts;
    }
}

class Refrigerator extends HomeDevice implements EnergyTrackable {
    private double consumptionWatts;

    public Refrigerator(double consumptionWatts) {
        this.consumptionWatts = consumptionWatts;
    }

    @Override
    public String activate() {
        return "Refrigerator " + getSerialNumber() + " activated";
    }

    @Override
    public double getConsumptionWatts() {
        return consumptionWatts;
    }
}

class MobileApp implements RemoteControllable {
    private String appName;

    public MobileApp(String appName) {
        this.appName = appName;
    }

    @Override
    public String connect(String appId) {
        return appName + " connected to " + appId;
    }
}

public class Main {
    static void connectAll(RemoteControllable[] items, String appId) {
        for (RemoteControllable item : items) {
            System.out.println(item.connect(appId));
        }
    }

    static double getConsumptionIfTrackable(HomeDevice d) {
        if (d instanceof EnergyTrackable) {
            EnergyTrackable e = (EnergyTrackable) d;
            return e.getConsumptionWatts();
        }

        return 0.0;
    }

    public static void main(String[] args) {
        WashingMachine wm = new WashingMachine(500.0);

        System.out.println(wm.activate());
        System.out.println(wm.connect("HomeConnect"));

        Refrigerator fridge = new Refrigerator(150.0);
        System.out.println(getConsumptionIfTrackable(fridge));

        MobileApp app = new MobileApp("HomeConnect App");
        System.out.println(app.connect("HomeConnect"));

        HomeDevice ref = wm; 
        System.out.println(getConsumptionIfTrackable(ref));

        connectAll(
            new RemoteControllable[]{wm, app},
            "HomeConnect"
        );
    }
}
