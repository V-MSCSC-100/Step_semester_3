//Question 1
class FoodOrder {
    private String studentName;
    private String dishName;
    private boolean delivered;

    public FoodOrder(String studentName, String dishName) {
        if (studentName == null || studentName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid student name");
        }

        if (dishName == null || dishName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid dish name");
        }

        this.studentName = studentName;
        this.dishName = dishName;
        this.delivered = false;
    }

    void markDelivered() {
        if (!delivered) {
            delivered = true;
            System.out.println("Order delivered");
        } else {
            System.out.println("Order was already delivered");
        }
    }

    static void processBatch(String[][] rawOrders) {
        int valid = 0;
        int rejected = 0;

        for (String[] order : rawOrders) {
            try {
                new FoodOrder(order[0], order[1]);
                valid++;
            } catch (Exception e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid + " | Rejected: " + rejected);
    }
}

public class Main {
    public static void main(String[] args) {
        String[][] rawOrders = {
            {"Ravi", "Paneer Butter Masala"},
            {"", "Chole Bhature"},
            {"Meera", " "},
            {"Divya", "Veg Biryani"}
        };

        FoodOrder.processBatch(rawOrders);

        FoodOrder order = new FoodOrder("Ravi", "Paneer Butter Masala");
        order.markDelivered();
        order.markDelivered();
    }
}

//Question 2

class DeliverySlot {
    private String orderId;
    private String timeSlot;

    public DeliverySlot(String orderId, String timeSlot) {
        this.orderId = orderId;
        this.timeSlot = timeSlot;
    }

    public DeliverySlot(String orderId) {
        this(orderId, "ASAP");
    }

    boolean isPeakHour() {
        return timeSlot.equals("12:00-13:00") ||
               timeSlot.equals("13:00-14:00") ||
               timeSlot.equals("19:00-20:00") ||
               timeSlot.equals("20:00-21:00");
    }
}

public class Main {
    public static void main(String[] args) {
        DeliverySlot slot1 =
                new DeliverySlot("ORD101", "13:00-14:00");

        DeliverySlot slot2 =
                new DeliverySlot("ORD102");

        System.out.println(slot1.isPeakHour());
        System.out.println(slot2.isPeakHour());
    }
}

//Question 3

class Canteen {
    private String canteenCode;
    private String canteenName;
    private int trustScore;

    public Canteen(String canteenCode, String canteenName, int trustScore) {
        this.canteenCode = canteenCode;
        this.canteenName = canteenName;
        this.trustScore = trustScore;
    }

    public Canteen(String canteenCode, String canteenName) {
        this(canteenCode, canteenName, 3);
    }

    int compareTo(Canteen other) {
        if (this.trustScore != other.trustScore) {
            return other.trustScore - this.trustScore;
        }

        int codeComparison =
                this.canteenCode.compareToIgnoreCase(other.canteenCode);

        if (codeComparison != 0) {
            return codeComparison;
        }

        return this.canteenName.length() - other.canteenName.length();
    }

    static Canteen[] rankCanteens(Canteen[] canteens) {
        Canteen[] result = new Canteen[canteens.length];

        for (int i = 0; i < canteens.length; i++) {
            result[i] = canteens[i];
        }

        for (int i = 0; i < result.length - 1; i++) {
            for (int j = 0; j < result.length - i - 1; j++) {
                if (result[j].compareTo(result[j + 1]) > 0) {
                    Canteen temp = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = temp;
                }
            }
        }

        return result;
    }

    String getCanteenCode() {
        return canteenCode;
    }
}

public class Main {
    public static void main(String[] args) {
        Canteen[] canteens = {
            new Canteen("HB3-C", "Spice Junction", 3),
            new Canteen("hb1-c", "Grand Mess", 5),
            new Canteen("HB2-C", "Southern Treats")
        };

        Canteen[] ranked = Canteen.rankCanteens(canteens);

        for (Canteen canteen : ranked) {
            System.out.println(canteen.getCanteenCode());
        }
    }
}

//Question 4

final class SurgeFeeCalculator {
    private final double minimumSurgePercent;

    public SurgeFeeCalculator(double minimumSurgePercent) {
        this.minimumSurgePercent = minimumSurgePercent;
    }

    final double calculateSurgeFee(double orderValue, int delayMinutes) {
        if (orderValue < 0 || delayMinutes < 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        if (delayMinutes == 0) {
            return 0.0;
        }

        double tieredFee = 0.0;

        int firstTier = Math.min(delayMinutes, 5);
        tieredFee += firstTier * orderValue * 0.005;

        if (delayMinutes > 5) {
            int secondTier = Math.min(delayMinutes - 5, 10);
            tieredFee += secondTier * orderValue * 0.01;
        }

        if (delayMinutes > 15) {
            int thirdTier = delayMinutes - 15;
            tieredFee += thirdTier * orderValue * 0.02;
        }

        double minimumFee =
                orderValue * minimumSurgePercent / 100.0;

        return Math.max(tieredFee, minimumFee);
    }
}

public class Main {
    public static void main(String[] args) {
        SurgeFeeCalculator calculator =
                new SurgeFeeCalculator(1.0);

        System.out.println("Rs " +
                calculator.calculateSurgeFee(500, 0));

        System.out.println("Rs " +
                calculator.calculateSurgeFee(500, 1));

        System.out.println("Rs " +
                calculator.calculateSurgeFee(500, 16));
    }
}

//Question 5

class DeliveryAccount {
    protected String studentId;
    protected double orderValue;

    static String systemName;

    static {
        systemName = "Campus Delivery Reconciliation";
    }

    public DeliveryAccount(String studentId, double orderValue) {
        this.studentId = studentId;
        this.orderValue = orderValue;
    }

    public DeliveryAccount(String studentId) {
        this(studentId, 0.0);
    }

    final double calculateSurgeFee(int delayMinutes) {
        if (delayMinutes < 0) {
            throw new IllegalArgumentException("Invalid delay");
        }

        if (delayMinutes == 0) {
            return 0.0;
        }

        double fee = 0.0;

        int first = Math.min(delayMinutes, 5);
        fee += first * orderValue * 0.005;

        if (delayMinutes > 5) {
            int second = Math.min(delayMinutes - 5, 10);
            fee += second * orderValue * 0.01;
        }

        if (delayMinutes > 15) {
            int third = delayMinutes - 15;
            fee += third * orderValue * 0.02;
        }

        double minimum = orderValue * 0.01;

        return Math.max(fee, minimum);
    }

    void processAccount(DeliveryAccount account,
                        double amount,
                        int delayMinutes) {
        double fee = account.calculateSurgeFee(delayMinutes);
        System.out.println("Student: " + account.studentId +
                " | Surge fee: Rs " + fee);
    }

    static void processBatch(DeliveryAccount[] accounts,
                             double[] amounts,
                             int[] delayMinutesArray) {

        if (accounts.length != amounts.length ||
            accounts.length != delayMinutesArray.length) {
            throw new IllegalArgumentException(
                    "Arrays must have matching lengths");
        }

        int processed = 0;
        int nullSkipped = 0;
        int premium = 0;
        int regular = 0;
        double grandTotal = 0.0;

        for (int i = 0; i < accounts.length; i++) {
            DeliveryAccount account = accounts[i];

            if (account == null) {
                nullSkipped++;
                continue;
            }

            double fee;

            if (account instanceof PremiumDeliveryAccount) {
                premium++;
                fee = account.calculateSurgeFee(delayMinutesArray[i]) * 0.5;
            } else {
                regular++;
                fee = account.calculateSurgeFee(delayMinutesArray[i]);
            }

            grandTotal += fee;
            processed++;
        }

        System.out.println(processed + " processed | " +
                nullSkipped + " null skipped | " +
                premium + " premium | " +
                regular + " regular | " +
                "grand total surge fees = Rs " + grandTotal);
    }
}

class PremiumDeliveryAccount extends DeliveryAccount {
    public PremiumDeliveryAccount(String studentId, double orderValue) {
        super(studentId, orderValue);
    }

    public PremiumDeliveryAccount(String studentId) {
        super(studentId);
    }
}

public class Main {
    public static void main(String[] args) {
        DeliveryAccount[] accounts = {
            new PremiumDeliveryAccount("STU001", 500),
            null,
            new DeliveryAccount("STU002", 300)
        };

        double[] amounts = {
            500, 400, 300
        };

        int[] delayMinutesArray = {
            10, 5, 0
        };

        DeliveryAccount.processBatch(
                accounts,
                amounts,
                delayMinutesArray
        );
    }
}

