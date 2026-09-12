//Question 1
class BusTicket {
    private String passengerName;
    private String destination;
    private boolean checkedIn;

    public BusTicket(String passengerName, String destination) {
        if (passengerName == null ||
            passengerName.trim().isEmpty() ||
            !passengerName.matches("[A-Za-z ]+")) {
            throw new IllegalArgumentException("Invalid passenger name");
        }

        if (destination == null ||
            destination.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid destination");
        }

        this.passengerName = passengerName;
        this.destination = destination;
        this.checkedIn = false;
    }

    void markCheckedIn() {
        if (!checkedIn) {
            checkedIn = true;
            System.out.println("Passenger checked in");
        } else {
            System.out.println("Passenger was already checked in");
        }
    }

    static void processBatch(String[][] rawBookings) {
        int valid = 0;
        int rejected = 0;
        int duplicates = 0;

        String[][] accepted = new String[rawBookings.length][2];
        int acceptedCount = 0;

        for (String[] booking : rawBookings) {
            try {
                BusTicket ticket =
                        new BusTicket(booking[0], booking[1]);

                boolean duplicate = false;

                for (int i = 0; i < acceptedCount; i++) {
                    if (accepted[i][0].equals(ticket.passengerName) &&
                        accepted[i][1].equals(ticket.destination)) {
                        duplicate = true;
                        break;
                    }
                }

                if (duplicate) {
                    duplicates++;
                } else {
                    accepted[acceptedCount][0] = ticket.passengerName;
                    accepted[acceptedCount][1] = ticket.destination;
                    acceptedCount++;
                    valid++;
                }

            } catch (Exception e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid +
                " | Rejected: " + rejected +
                " | Duplicates skipped: " + duplicates);
    }
}

public class Main {
    public static void main(String[] args) {
        String[][] rawBookings = {
            {"Divya", "Chennai"},
            {"", "Bangalore"},
            {"Ravi123", "Pune"},
            {"Divya", "Chennai"},
            {" ", " "}
        };

        BusTicket.processBatch(rawBookings);

        BusTicket ticket =
                new BusTicket("Divya", "Chennai");

        ticket.markCheckedIn();
        ticket.markCheckedIn();
    }
}
//Question 2

class FareSplitter {
    private String tripId;
    private double totalFare;
    private int passengerCount;

    public FareSplitter(String tripId,
                        double totalFare,
                        int passengerCount) {

        if (totalFare < 0) {
            throw new IllegalArgumentException("Fare cannot be negative");
        }

        if (passengerCount <= 0) {
            throw new IllegalArgumentException(
                    "Passenger count must be positive");
        }

        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    public FareSplitter(String tripId, double totalFare) {
        this(tripId, totalFare, 2);
    }

    public FareSplitter(String tripId) {
        this(tripId, 0.0, 2);
    }

    double[] fareBreakdown() {
        long totalCents = Math.round(totalFare * 100);
        long baseCents = totalCents / passengerCount;
        long remainder = totalCents % passengerCount;

        double[] result = new double[passengerCount];

        for (int i = 0; i < passengerCount; i++) {
            long share = baseCents;

            if (i == passengerCount - 1) {
                share += remainder;
            }

            result[i] = share / 100.0;
        }

        return result;
    }

    boolean isConfirmationOverdue(int confirmed, int expected) {
        if (confirmed < 0 || expected < 0) {
            throw new IllegalArgumentException("Invalid confirmation values");
        }

        return confirmed < expected;
    }
}

public class Main {
    public static void main(String[] args) {
        FareSplitter splitter =
                new FareSplitter("TRIP001", 100000, 3);

        double[] breakdown = splitter.fareBreakdown();

        for (double value : breakdown) {
            System.out.printf("%.2f ", value);
        }

        System.out.println();

        FareSplitter provisional =
                new FareSplitter("TRIP003");

        double[] provisionalBreakdown =
                provisional.fareBreakdown();

        for (double value : provisionalBreakdown) {
            System.out.printf("%.1f ", value);
        }

        System.out.println();

        System.out.println(
                splitter.isConfirmationOverdue(2, 3));
    }
}

//Question 3
class BusRoute {
    private String routeCode;
    private String routeName;
    private int priority;

    public BusRoute(String routeCode,
                    String routeName,
                    int priority) {
        this.routeCode = routeCode;
        this.routeName = routeName;
        this.priority = priority;
    }

    public BusRoute(String routeCode, String routeName) {
        this(routeCode, routeName, 3);
    }

    int compareTo(BusRoute other) {
        if (this.priority != other.priority) {
            return other.priority - this.priority;
        }

        int codeComparison =
                this.routeCode.compareToIgnoreCase(other.routeCode);

        if (codeComparison != 0) {
            return codeComparison;
        }

        return this.routeName.length() -
               other.routeName.length();
    }

    static BusRoute[] rankRoutes(BusRoute[] routes) {
        BusRoute[] result = new BusRoute[routes.length];

        for (int i = 0; i < routes.length; i++) {
            result[i] = routes[i];
        }

        for (int i = 0; i < result.length - 1; i++) {
            for (int j = 0; j < result.length - i - 1; j++) {
                if (result[j].compareTo(result[j + 1]) > 0) {
                    BusRoute temp = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = temp;
                }
            }
        }

        return result;
    }

    String getRouteCode() {
        return routeCode;
    }
}

public class Main {
    public static void main(String[] args) {
        BusRoute[] routes = {
            new BusRoute("RT205L", "Airport Express", 3),
            new BusRoute("rt201j", "City Central", 4),
            new BusRoute("RT299T", "Night Service")
        };

        BusRoute[] ranked =
                BusRoute.rankRoutes(routes);

        for (BusRoute route : ranked) {
            System.out.println(route.getRouteCode());
        }
    }
}

//Question 4

final class BoardingPenaltyCalculator {
    private final double minimumPenaltyPercent;

    public BoardingPenaltyCalculator(
            double minimumPenaltyPercent) {
        this.minimumPenaltyPercent =
                minimumPenaltyPercent;
    }

    final double calculatePenalty(
            double ticketFare,
            int minutesLate) {

        if (ticketFare < 0 || minutesLate < 0) {
            throw new IllegalArgumentException(
                    "Invalid input");
        }

        if (minutesLate == 0) {
            return 0.0;
        }

        double penalty = 0.0;

        int firstTier = Math.min(minutesLate, 5);
        penalty += firstTier *
                ticketFare * 0.005;

        if (minutesLate > 5) {
            int secondTier =
                    Math.min(minutesLate - 5, 10);

            penalty += secondTier *
                    ticketFare * 0.01;
        }

        if (minutesLate > 15) {
            int thirdTier =
                    minutesLate - 15;

            penalty += thirdTier *
                    ticketFare * 0.02;
        }

        double minimumPenalty =
                ticketFare *
                minimumPenaltyPercent / 100.0;

        return Math.max(penalty, minimumPenalty);
    }
}

public class Main {
    public static void main(String[] args) {
        BoardingPenaltyCalculator calculator =
                new BoardingPenaltyCalculator(1.0);

        System.out.println("Rs " +
                calculator.calculatePenalty(1000, 0));

        System.out.println("Rs " +
                calculator.calculatePenalty(1000, 1));

        System.out.println("Rs " +
                calculator.calculatePenalty(1000, 16));
    }
}

//Question 5

class BusTicketAccount {
    protected String bookingId;
    protected double ticketFare;

    static String depotName;

    static {
        depotName = "Campus Bus Depot";
    }

    public BusTicketAccount(String bookingId,
                             double ticketFare) {
        this.bookingId = bookingId;
        this.ticketFare = ticketFare;
    }

    public BusTicketAccount(String bookingId) {
        this(bookingId, 0.0);
    }

    final double calculatePenalty(int minutesLate) {
        if (minutesLate < 0) {
            throw new IllegalArgumentException(
                    "Invalid delay");
        }

        if (minutesLate == 0) {
            return 0.0;
        }

        double penalty = 0.0;

        int firstTier =
                Math.min(minutesLate, 5);

        penalty += firstTier *
                ticketFare * 0.005;

        if (minutesLate > 5) {
            int secondTier =
                    Math.min(minutesLate - 5, 10);

            penalty += secondTier *
                    ticketFare * 0.01;
        }

        if (minutesLate > 15) {
            int thirdTier =
                    minutesLate - 15;

            penalty += thirdTier *
                    ticketFare * 0.02;
        }

        double minimumPenalty =
                ticketFare * 0.01;

        return Math.max(penalty, minimumPenalty);
    }

    void processAccount(BusTicketAccount account,
                        double amount,
                        int minutesLate) {

        double penalty =
                account.calculatePenalty(minutesLate);

        System.out.println(
                "Booking: " + account.bookingId +
                " | Penalty: Rs " + penalty);
    }

    static void processBatch(
            BusTicketAccount[] accounts,
            double[] amounts,
            int[] minutesLateArray) {

        if (accounts.length != amounts.length ||
            accounts.length != minutesLateArray.length) {

            throw new IllegalArgumentException(
                    "Arrays must have matching lengths");
        }

        int processed = 0;
        int nullSkipped = 0;
        int sleeper = 0;
        int regular = 0;
        double grandTotal = 0.0;

        for (int i = 0; i < accounts.length; i++) {

            BusTicketAccount account =
                    accounts[i];

            if (account == null) {
                nullSkipped++;
                continue;
            }

            double penalty;

            if (account instanceof SleeperBusTicketAccount) {
                sleeper++;

                penalty =
                        account.calculatePenalty(
                                minutesLateArray[i]) * 0.5;
            } else {
                regular++;

                penalty =
                        account.calculatePenalty(
                                minutesLateArray[i]);
            }

            grandTotal += penalty;
            processed++;
        }

        System.out.println(
                processed + " processed | " +
                nullSkipped + " null skipped | " +
                sleeper + " sleeper | " +
                regular + " regular | " +
                "grand total penalties = Rs " +
                grandTotal);
    }
}

class SleeperBusTicketAccount
        extends BusTicketAccount {

    public SleeperBusTicketAccount(
            String bookingId,
            double ticketFare) {

        super(bookingId, ticketFare);
    }

    public SleeperBusTicketAccount(
            String bookingId) {

        super(bookingId);
    }
}

public class Main {
    public static void main(String[] args) {

        BusTicketAccount[] accounts = {
            new SleeperBusTicketAccount("BK001", 2000),
            null,
            new BusTicketAccount("BK002", 1200)
        };

        double[] amounts = {
            1200, 900, 700
        };

        int[] minutesLateArray = {
            10, 5, 0
        };

        BusTicketAccount.processBatch(
                accounts,
                amounts,
                minutesLateArray);
    }
}
