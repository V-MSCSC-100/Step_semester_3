//Question 1

public class Main {

    static class EventTicket {
        protected String attendeeId;
        protected double basePrice;
        protected double amountPaid;

        public EventTicket(String attendeeId, double basePrice) {

            if (attendeeId == null ||
                attendeeId.trim().length() < 4) {

                throw new IllegalArgumentException(
                    "Invalid attendee ID"
                );
            }

            this.attendeeId = attendeeId;
            this.basePrice = basePrice;
            this.amountPaid = 0;
        }

        public void pay(double amount) {
            amountPaid += amount;
        }

        public double getBalanceDue() {
            return basePrice - amountPaid;
        }
    }

    static class WorkshopTicket extends EventTicket {

        private String track;

        public WorkshopTicket(
            String attendeeId,
            double basePrice,
            String track
        ) {
            super(attendeeId, basePrice);
            this.track = track;
        }

        public String getTrack() {
            return track;
        }
    }

    static String registerBatch(
        String[] attendeeIds,
        double basePrice
    ) {

        int registered = 0;
        int rejected = 0;

        for (String id : attendeeIds) {

            try {
                new EventTicket(id, basePrice);
                registered++;
            }
            catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: " + registered +
               " | Rejected: " + rejected;
    }

    public static void main(String[] args) {

        EventTicket ticket =
            new EventTicket("USER1001", 500);

        ticket.pay(200);

        System.out.println(
            "Balance: " + ticket.getBalanceDue()
        );

        WorkshopTicket workshop =
            new WorkshopTicket(
                "USER2001",
                1000,
                "Java"
            );

        System.out.println(
            "Track: " + workshop.getTrack()
        );

        String[] ids = {
            "USER1",
            "AB",
            "USER2",
            null,
            "USER3"
        };

        System.out.println(
            registerBatch(ids, 500)
        );
    }
}

//Question 2

public class Main {

    static class EventTicket {

        protected String attendeeId;
        protected double basePrice;
        protected double amountPaid;

        public EventTicket(
            String attendeeId,
            double basePrice
        ) {

            if (attendeeId == null ||
                attendeeId.trim().length() < 4) {

                throw new IllegalArgumentException(
                    "Invalid attendee ID"
                );
            }

            this.attendeeId = attendeeId;
            this.basePrice = basePrice;
            this.amountPaid = 0;
        }

        public void pay(double amount) {
            amountPaid += amount;
        }

        public double getBalanceDue() {
            return basePrice - amountPaid;
        }

        public void printTicket() {

            System.out.println(
                "Event Ticket | Attendee: " +
                attendeeId +
                " | Balance: " +
                getBalanceDue()
            );
        }
    }

    static class WorkshopTicket
        extends EventTicket {

        protected String track;

        public WorkshopTicket(
            String attendeeId,
            double basePrice,
            String track
        ) {
            super(attendeeId, basePrice);
            this.track = track;
        }

        @Override
        public void printTicket() {

            System.out.println(
                "Workshop Ticket | Attendee: " +
                attendeeId +
                " | Track: " +
                track +
                " | Balance: " +
                getBalanceDue()
            );
        }
    }

    static class PremiumWorkshopTicket
        extends WorkshopTicket {

        private double kitFee;

        public PremiumWorkshopTicket(
            String attendeeId,
            double basePrice,
            String track,
            double kitFee
        ) {

            super(
                attendeeId,
                basePrice,
                track
            );

            this.kitFee = kitFee;
        }

        @Override
        public void printTicket() {

            System.out.println(
                "Premium Workshop Ticket | " +
                "Attendee: " + attendeeId +
                " | Track: " + track +
                " | Kit Fee: " + kitFee +
                " | Balance: " +
                getBalanceDue()
            );
        }
    }

    static class HackathonTicket
        extends EventTicket {

        private String teamName;

        public HackathonTicket(
            String attendeeId,
            double basePrice,
            String teamName
        ) {

            super(
                attendeeId,
                basePrice
            );

            this.teamName = teamName;
        }

        @Override
        public void printTicket() {

            System.out.println(
                "Hackathon Ticket | " +
                "Attendee: " + attendeeId +
                " | Team: " + teamName +
                " | Balance: " +
                getBalanceDue()
            );
        }
    }

    static String classifyGeneration(
        EventTicket ticket
    ) {

        if (ticket instanceof
            PremiumWorkshopTicket) {

            return "Multilevel descendant " +
                   "(3 generations deep)";
        }

        if (ticket instanceof
            HackathonTicket) {

            return "Hierarchical sibling " +
                   "(independent branch)";
        }

        if (ticket instanceof
            WorkshopTicket) {

            return "Intermediate descendant";
        }

        return "Standard Event Ticket";
    }

    static double getTotalBalanceDue(
        EventTicket[] tickets
    ) {

        double total = 0;

        for (EventTicket ticket : tickets) {

            total += ticket.getBalanceDue();
        }

        return total;
    }

    public static void main(String[] args) {

        EventTicket event =
            new EventTicket(
                "USER1001",
                500
            );

        WorkshopTicket workshop =
            new WorkshopTicket(
                "USER2001",
                1000,
                "Java"
            );

        PremiumWorkshopTicket premium =
            new PremiumWorkshopTicket(
                "USER3001",
                1500,
                "AI",
                300
            );

        HackathonTicket hackathon =
            new HackathonTicket(
                "USER4001",
                800,
                "Code Warriors"
            );

        event.printTicket();
        workshop.printTicket();
        premium.printTicket();
        hackathon.printTicket();

        System.out.println(
            classifyGeneration(premium)
        );

        System.out.println(
            classifyGeneration(hackathon)
        );

        EventTicket[] tickets = {
            event,
            workshop,
            premium,
            hackathon
        };

        System.out.println(
            "Total Balance Due: " +
            getTotalBalanceDue(tickets)
        );
    }
}

//Question 3

import java.util.Arrays;

public class Main {

    static class EventTicket {

        protected double basePrice;
        protected double amountPaid;

        private double[] lateFeeHistory =
            new double[10];

        private int feeCount = 0;

        public EventTicket(double basePrice) {

            this.basePrice = basePrice;
            this.amountPaid = 0;
        }

        public void pay(double amount) {

            amountPaid += amount;
        }

        public double getBalanceDue() {

            return basePrice - amountPaid;
        }

        protected void applyLateFee(double amount) {

            amountPaid -= amount;

            lateFeeHistory[feeCount] = amount;

            feeCount++;
        }

        public double[] getLateFeeHistory() {

            return Arrays.copyOf(
                lateFeeHistory,
                feeCount
            );
        }
    }

    static class WorkshopTicket
        extends EventTicket {

        public WorkshopTicket(
            double basePrice
        ) {
            super(basePrice);
        }

        @Override
        protected void applyLateFee(
            double amount
        ) {

            super.applyLateFee(
                amount * 2
            );
        }
    }

    public static void main(String[] args) {

        WorkshopTicket ticket =
            new WorkshopTicket(1000);

        ticket.pay(1000);

        System.out.println(
            "Before late fee: " +
            ticket.getBalanceDue()
        );

        ticket.applyLateFee(100);

        System.out.println(
            "After late fee: " +
            ticket.getBalanceDue()
        );

        double[] history =
            ticket.getLateFeeHistory();

        System.out.println(
            "Original history: " +
            Arrays.toString(history)
        );

        // Try to modify the returned array
        history[0] = 9999;

        System.out.println(
            "Internal history: " +
            Arrays.toString(
                ticket.getLateFeeHistory()
            )
        );
    }
}

//Question 4

public class Main {

    static class EventTicket {

        protected String attendeeId;
        protected double basePrice;
        protected double amountPaid;

        public EventTicket(
            String attendeeId,
            double basePrice
        ) {

            this.attendeeId = attendeeId;
            this.basePrice = basePrice;
            this.amountPaid = 0;
        }

        public void pay(double amount) {
            amountPaid += amount;
        }

        public double getBalanceDue() {
            return basePrice - amountPaid;
        }

        public void printTicket() {

            System.out.print(
                "Event Ticket | Attendee: " +
                attendeeId +
                " | Balance: " +
                getBalanceDue() +
                " | "
            );
        }
    }

    static class WorkshopTicket
        extends EventTicket {

        private String track;

        public WorkshopTicket(
            String attendeeId,
            double basePrice,
            String track
        ) {

            super(
                attendeeId,
                basePrice
            );

            this.track = track;
        }

        public String getTrack() {
            return track;
        }

        @Override
        public void printTicket() {

            System.out.print(
                "Workshop Ticket | Attendee: " +
                attendeeId +
                " | Track: " +
                track +
                " | Balance: " +
                getBalanceDue() +
                " | "
            );
        }
    }

    static String batchPrint(
        EventTicket[] tickets
    ) {

        StringBuilder result =
            new StringBuilder();

        for (EventTicket ticket : tickets) {

            // Runtime polymorphism
            ticket.printTicket();

            // Safe downcasting
            if (ticket instanceof
                WorkshopTicket) {

                WorkshopTicket workshop =
                    (WorkshopTicket) ticket;

                result.append(
                    "[Track via downcast: "
                );

                result.append(
                    workshop.getTrack()
                );

                result.append("] | ");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {

        EventTicket event =
            new EventTicket(
                "USER1001",
                500
            );

        WorkshopTicket workshop =
            new WorkshopTicket(
                "USER2001",
                1000,
                "Java"
            );

        EventTicket[] tickets = {
            event,
            workshop
        };

        String result =
            batchPrint(tickets);

        System.out.println();
        System.out.println(result);
    }
}

//Question 5

public class Main {

    static class EventTicket {

        private static int counter = 1000;

        final String ticketId;

        protected double basePrice;
        protected double amountPaid;

        public EventTicket(
            double basePrice
        ) {

            counter++;

            ticketId =
                "TCK-" + counter;

            this.basePrice =
                basePrice;

            this.amountPaid = 0;
        }

        public void pay(double amount) {

            amountPaid += amount;
        }

        // Method overloading
        public void pay(
            double amount,
            String mode
        ) {

            System.out.println(
                "Payment mode: " + mode
            );

            pay(amount);
        }

        public double getBalanceDue() {

            return basePrice -
                   amountPaid;
        }

        public static boolean
        isValidPromoCode(String code) {

            if (code == null ||
                code.length() != 5) {

                return false;
            }

            // First character must be F
            if (code.charAt(0) != 'F') {
                return false;
            }

            // Characters 1, 2 and 3
            // must be digits
            if (!Character.isDigit(
                    code.charAt(1))) {

                return false;
            }

            if (!Character.isDigit(
                    code.charAt(2))) {

                return false;
            }

            if (!Character.isDigit(
                    code.charAt(3))) {

                return false;
            }

            // Last character must be uppercase
            if (!Character.isUpperCase(
                    code.charAt(4))) {

                return false;
            }

            return true;
        }

        public static int
        getTicketsIssued() {

            return counter - 1000;
        }
    }

    static class GroupTicket
        extends EventTicket {

        private int groupSize;

        public GroupTicket(
            double basePrice,
            int groupSize
        ) {

            super(basePrice);

            this.groupSize =
                groupSize;
        }

        public int getGroupSize() {
            return groupSize;
        }
    }

    static String processNightlySettlement(
        EventTicket[] tickets
    ) {

        int processed = 0;
        int nullSkipped = 0;
        int group = 0;
        int individual = 0;

        for (EventTicket ticket : tickets) {

            if (ticket == null) {

                nullSkipped++;

                continue;
            }

            processed++;

            if (ticket instanceof
                GroupTicket) {

                group++;

            } else {

                individual++;
            }
        }

        return processed +
               " processed | " +
               nullSkipped +
               " null skipped | " +
               group +
               " group | " +
               individual +
               " individual";
    }

    public static void main(String[] args) {

        EventTicket ticket1 =
            new EventTicket(1000);

        EventTicket ticket2 =
            new EventTicket(1500);

        GroupTicket groupTicket =
            new GroupTicket(
                3000,
                5
            );

        System.out.println(
            "Ticket 1: " +
            ticket1.ticketId
        );

        System.out.println(
            "Ticket 2: " +
            ticket2.ticketId
        );

        System.out.println(
            "Group Ticket: " +
            groupTicket.ticketId
        );

        System.out.println(
            "Tickets issued: " +
            EventTicket.getTicketsIssued()
        );

        // Overloaded pay()
        ticket1.pay(500);

        ticket2.pay(
            700,
            "UPI"
        );

        System.out.println(
            "Ticket 1 balance: " +
            ticket1.getBalanceDue()
        );

        System.out.println(
            "Ticket 2 balance: " +
            ticket2.getBalanceDue()
        );

        // Promo code validation
        System.out.println(
            EventTicket.isValidPromoCode(
                "F123A"
            )
        );

        System.out.println(
            EventTicket.isValidPromoCode(
                "F12AB"
            )
        );

        System.out.println(
            EventTicket.isValidPromoCode(
                "X123A"
            )
        );

        // Nightly settlement
        EventTicket[] tickets = {
            ticket1,
            ticket2,
            groupTicket,
            null
        };

        System.out.println(
            processNightlySettlement(
                tickets
            )
        );
    }
}
