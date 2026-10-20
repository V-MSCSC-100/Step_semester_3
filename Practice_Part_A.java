//Problem 1
interface Dimmable {
    void dim(int level);
}

interface Schedulable {
    void schedule(String time);
}

interface EnergyMonitor {
    void energy();
}

class Device {
    String id;
    boolean on;

    Device(String id) {
        this.id = id;
    }

    void turnOn() {
        on = true;
        System.out.println(id + " is ON");
    }

    void turnOff() {
        on = false;
        System.out.println(id + " is OFF");
    }
}

class Light extends Device implements Dimmable, Schedulable {
    Light(String id) {
        super(id);
    }

    public void dim(int level) {
        System.out.println(id + " dimmed to " + level);
    }

    public void schedule(String time) {
        System.out.println(id + " scheduled " + time);
    }
}

class Fan extends Device implements Schedulable {
    Fan(String id) {
        super(id);
    }

    public void schedule(String time) {
        System.out.println(id + " scheduled " + time);
    }
}

class Plug extends Device implements EnergyMonitor {
    int kwh;

    Plug(String id, int kwh) {
        super(id);
        this.kwh = kwh;
    }

    public void energy() {
        System.out.println(id + " energy " + kwh + " kWh");
    }
}

public class Main {
    public static void main(String[] args) {
        Light l = new Light("L1");
        Fan f = new Fan("F1");
        Plug p = new Plug("P1", 12);

        l.turnOn();
        l.dim(40);

        if (f instanceof Dimmable) {
            f.toString();
        } else {
            System.out.println("F1 rejected: DIM unsupported");
        }

        f.schedule("22:00");
        p.energy();

        if (l instanceof EnergyMonitor) {
            ((EnergyMonitor) l).energy();
        } else {
            System.out.println("L1 rejected: ENERGY unsupported");
        }
    }
}
//Problem 2
import java.util.HashSet;
import java.util.Objects;

class Customer {
    int id;
    String name;

    Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Customer)) return false;
        Customer c = (Customer) obj;
        return id == c.id && name.equals(c.name);
    }

    public int hashCode() {
        return Objects.hash(id, name);
    }
}

public class Main {
    public static void main(String[] args) {
        HashSet<Customer> customers = new HashSet<>();

        System.out.println(customers.add(new Customer(101, "Asha")));
        System.out.println(customers.add(new Customer(101, "Asha")));
        System.out.println(customers.add(new Customer(102, "Ravi")));

        System.out.println("contains: " +
            customers.contains(new Customer(101, "Asha")));
        System.out.println("unique count " + customers.size());
    }
}
//Problem 3
public class Main {
    public static void main(String[] args) {
        String[] students = {"Anu", "Ravi", "Meena"};
        String[] subjects = {"Math", "Sci", "Eng"};

        int[][] marks = {
            {80, 90, 70},
            {60, 85, -1},
            {95, 75, 88}
        };

        for (int i = 0; i < students.length; i++) {
            int total = 0;

            for (int j = 0; j < subjects.length; j++) {
                if (marks[i][j] != -1) {
                    total += marks[i][j];
                }
            }

            System.out.println("Total " + students[i] + " " + total);
        }

        for (int j = 0; j < subjects.length; j++) {
            int max = -1;
            int topper = -1;

            for (int i = 0; i < students.length; i++) {
                if (marks[i][j] > max) {
                    max = marks[i][j];
                    topper = i;
                }
            }

            if (topper != -1) {
                System.out.println("Topper " + subjects[j] + " " +
                    students[topper] + " " + max);
            }
        }
    }
}
//Problem 4
public class Main {
    static int lowerBound(int[] prices, int target) {
        int left = 0;
        int right = prices.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (prices[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    static int upperBound(int[] prices, int target) {
        int left = 0;
        int right = prices.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (prices[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        int[] prices = {100, 150, 150, 200, 300, 450};

        int low = lowerBound(prices, 150);
        int high = upperBound(prices, 300);

        System.out.println("Lower bound index " + low);
        System.out.println("Upper bound index " + high);
        System.out.println("Count " + (high - low));
    }
}
//Problem 5
interface Reservable {
    void reserve(String member);
}

interface Downloadable {
    void download();
}

abstract class Resource {
    String id;

    Resource(String id) {
        this.id = id;
    }
}

class Book extends Resource implements Reservable {
    Book(String id) {
        super(id);
    }

    public void reserve(String member) {
        System.out.println(id + " reserved for " + member);
    }
}

class EBook extends Resource implements Downloadable {
    EBook(String id) {
        super(id);
    }

    public void download() {
        System.out.println(id + " downloaded");
    }
}

public class Main {
    static Resource[] resources = new Resource[100];
    static int size = 0;

    static void add(Resource r) {
        int i = 0;

        while (i < size && resources[i].id.compareTo(r.id) < 0) {
            i++;
        }

        if (i < size && resources[i].id.equals(r.id)) {
            System.out.println("duplicate rejected");
            return;
        }

        for (int j = size; j > i; j--) {
            resources[j] = resources[j - 1];
        }

        resources[i] = r;
        size++;
        System.out.println(r.id + " added");
    }

    static int find(String id) {
        int left = 0;
        int right = size - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = resources[mid].id.compareTo(id);

            if (cmp == 0) return mid;
            if (cmp < 0) left = mid + 1;
            else right = mid - 1;
        }

        return -1;
    }

    static void reserve(String id, String member) {
        int i = find(id);

        if (i == -1) {
            System.out.println(id + " not found");
        } else if (resources[i] instanceof Reservable) {
            ((Reservable) resources[i]).reserve(member);
        } else {
            System.out.println(id + " rejected: reserve unsupported");
        }
    }

    static void download(String id) {
        int i = find(id);

        if (i == -1) {
            System.out.println(id + " not found");
        } else if (resources[i] instanceof Downloadable) {
            ((Downloadable) resources[i]).download();
        } else {
            System.out.println(id + " rejected: download unsupported");
        }
    }

    public static void main(String[] args) {
        add(new Book("B1"));
        add(new Book("B1"));
        add(new EBook("E1"));

        reserve("B1", "M1");
        download("B1");
        download("E1");

        int index = find("E1");

        if (index != -1) {
            System.out.println("E1 found at index " + index);
        }
    }
}
