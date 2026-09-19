//Question 1

public class Main {

    static class RaceEntry {
        protected String bibNumber;
        protected double entryFee;
        protected double amountPaid;

        public RaceEntry(String bibNumber, double entryFee) {
            if (bibNumber == null || bibNumber.trim().length() < 4) {
                throw new IllegalArgumentException("Invalid bib number");
            }

            this.bibNumber = bibNumber;
            this.entryFee = entryFee;
            this.amountPaid = 0;
        }

        public void pay(double amount) {
            amountPaid += amount;
        }

        public double getBalanceDue() {
            return entryFee - amountPaid;
        }
    }

    static class RunnerEntry extends RaceEntry {
        private String category;

        public RunnerEntry(String bibNumber, double entryFee, String category) {
            super(bibNumber, entryFee);
            this.category = category;
        }
    }

    static String registerBatch(String[] bibNumbers, double entryFee) {
        int registered = 0;
        int rejected = 0;

        for (String bib : bibNumbers) {
            try {
                new RaceEntry(bib, entryFee);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: " + registered + " | Rejected: " + rejected;
    }

    public static void main(String[] args) {

        try {
            RaceEntry r1 = new RaceEntry("B1", 50);
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        RunnerEntry r = new RunnerEntry("BIB2001", 80, "Open 10K");
        r.pay(30);

        System.out.println(r.getBalanceDue());

        String[] bibNumbers = {"BIB1", "B1", "BIB2"};

        System.out.println(registerBatch(bibNumbers, 80));
    }
}

//Question 2

public class Main {

    static class RaceEntry {
        protected String bibNumber;
        protected double entryFee;
        protected double amountPaid;

        public RaceEntry(String bibNumber, double entryFee) {
            if (bibNumber == null || bibNumber.trim().length() < 4) {
                throw new IllegalArgumentException("Invalid bib number");
            }

            this.bibNumber = bibNumber;
            this.entryFee = entryFee;
            this.amountPaid = 0;
        }

        public void pay(double amount) {
            amountPaid += amount;
        }

        public double getBalanceDue() {
            return entryFee - amountPaid;
        }

        public void announce() {
            System.out.println(
                "Race Entry | Bib: " + bibNumber +
                " | Balance: " + getBalanceDue()
            );
        }
    }

    static class RunnerEntry extends RaceEntry {
        protected String category;

        public RunnerEntry(
            String bibNumber,
            double entryFee,
            String category
        ) {
            super(bibNumber, entryFee);
            this.category = category;
        }

        @Override
        public void announce() {
            System.out.println(
                "Runner Entry | Bib: " + bibNumber +
                " | Category: " + category +
                " | Balance: " + getBalanceDue()
            );
        }
    }

    static class EliteRunnerEntry extends RunnerEntry {
        private double sponsorBonus;

        public EliteRunnerEntry(
            String bibNumber,
            double entryFee,
            String category,
            double sponsorBonus
        ) {
            super(bibNumber, entryFee, category);
            this.sponsorBonus = sponsorBonus;
        }

        @Override
        public void announce() {
            System.out.println(
                "Elite Runner | Bib: " + bibNumber +
                " | Category: " + category +
                " | Sponsor Bonus: " + sponsorBonus +
                " | Balance: " + getBalanceDue()
            );
        }
    }

    static class RelayTeamEntry extends RaceEntry {
        private int teamSize;

        public RelayTeamEntry(
            String bibNumber,
            double entryFee,
            int teamSize
        ) {
            super(bibNumber, entryFee);
            this.teamSize = teamSize;
        }

        public int getTeamSize() {
            return teamSize;
        }

        @Override
        public void announce() {
            System.out.println(
                "Relay Team | Bib: " + bibNumber +
                " | Team Size: " + teamSize +
                " | Balance: " + getBalanceDue()
            );
        }
    }

    static String classifyGeneration(RaceEntry entry) {

        if (entry instanceof EliteRunnerEntry) {
            return "Multilevel descendant (3 generations deep)";
        }

        if (entry instanceof RelayTeamEntry) {
            return "Hierarchical sibling (independent branch)";
        }

        if (entry instanceof RunnerEntry) {
            return "Intermediate descendant";
        }

        return "Standard Race Entry";
    }

    static double getTotalBalanceDue(RaceEntry[] entries) {

        double total = 0;

        for (RaceEntry entry : entries) {
            total += entry.getBalanceDue();
        }

        return total;
    }

    public static void main(String[] args) {

        RunnerEntry runnerEntry =
            new RunnerEntry("BIB2001", 80, "Open 10K");

        EliteRunnerEntry eliteEntry =
            new EliteRunnerEntry(
                "BIB3001",
                150,
                "Elite Full Marathon",
                500
            );

        RelayTeamEntry relayEntry =
            new RelayTeamEntry(
                "BIB4001",
                300,
                4
            );

        runnerEntry.announce();
        eliteEntry.announce();
        relayEntry.announce();

        System.out.println(
            classifyGeneration(eliteEntry)
        );

        System.out.println(
            classifyGeneration(relayEntry)
        );

        RaceEntry[] entries = {
            runnerEntry,
            eliteEntry,
            relayEntry
        };

        System.out.println(
            getTotalBalanceDue(entries)
        );
    }
}

//Question 3

import java.util.Arrays;

public class Main {

    static class RaceEntry {

        protected double entryFee;
        protected double amountPaid;

        private double[] lateFeeHistory = new double[10];
        private int feeCount = 0;

        public RaceEntry(double entryFee) {
            this.entryFee = entryFee;
            this.amountPaid = 0;
        }

        public void pay(double amount) {
            amountPaid += amount;
        }

        public double getBalanceDue() {
            return entryFee - amountPaid;
        }

        protected void applyLateFee(double amount) {

            amountPaid -= amount;

            lateFeeHistory[feeCount] = amount;
            feeCount++;
        }

        public double[] getLateFeeHistory() {
            return Arrays.copyOf(lateFeeHistory, feeCount);
        }
    }

    static class RunnerEntry extends RaceEntry {

        public RunnerEntry(double entryFee) {
            super(entryFee);
        }

        @Override
        protected void applyLateFee(double amount) {
            super.applyLateFee(amount * 2);
        }
    }

    public static void main(String[] args) {

        RunnerEntry r = new RunnerEntry(80);

        r.pay(30);

        r.applyLateFee(20);

        System.out.println(r.getBalanceDue());

        double[] history = r.getLateFeeHistory();

        history[0] = 999;

        System.out.println(Arrays.toString(r.getLateFeeHistory()));
    }
}

//Question 4

public class Main {

    static class RaceEntry {

        protected String bibNumber;
        protected double entryFee;
        protected double amountPaid;

        public RaceEntry(String bibNumber, double entryFee) {
            this.bibNumber = bibNumber;
            this.entryFee = entryFee;
            this.amountPaid = 0;
        }

        public double getBalanceDue() {
            return entryFee - amountPaid;
        }

        public void announce() {
            System.out.print(
                "Race Entry | Bib: " + bibNumber +
                " | Balance: " + getBalanceDue() +
                " | "
            );
        }
    }

    static class RunnerEntry extends RaceEntry {

        private String category;

        public RunnerEntry(
            String bibNumber,
            double entryFee,
            String category
        ) {
            super(bibNumber, entryFee);
            this.category = category;
        }

        @Override
        public void announce() {
            System.out.print(
                "Runner Entry | Bib: " + bibNumber +
                " | Category: " + category +
                " | Balance: " + getBalanceDue() +
                " | "
            );
        }
    }

    static class RelayTeamEntry extends RaceEntry {

        private int teamSize;

        public RelayTeamEntry(
            String bibNumber,
            double entryFee,
            int teamSize
        ) {
            super(bibNumber, entryFee);
            this.teamSize = teamSize;
        }

        public int getTeamSize() {
            return teamSize;
        }

        @Override
        public void announce() {
            System.out.print(
                "Relay Team | Bib: " + bibNumber +
                " | Team Size: " + teamSize +
                " | Balance: " + getBalanceDue() +
                " | "
            );
        }
    }

    static String announceAll(RaceEntry[] entries) {

        StringBuilder sb = new StringBuilder();

        for (RaceEntry entry : entries) {

            // Polymorphism
            entry.announce();

            // Safe downcasting
            if (entry instanceof RelayTeamEntry) {

                RelayTeamEntry relay =
                    (RelayTeamEntry) entry;

                sb.append(
                    "[Team size via downcast: "
                );

                sb.append(relay.getTeamSize());

                sb.append("] | ");
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {

        RunnerEntry runnerEntry =
            new RunnerEntry(
                "BIB2001",
                80,
                "Open 10K"
            );

        RelayTeamEntry relayEntry =
            new RelayTeamEntry(
                "BIB4001",
                300,
                4
            );

        RaceEntry[] fleet = {
            runnerEntry,
            relayEntry
        };

        String result = announceAll(fleet);

        System.out.println();
        System.out.println(result);
    }
}

//Question 5

public class Main {

    static class RaceEntry {

        private static int counter = 0;

        final String entryCode;

        protected double entryFee;
        protected double amountPaid;

        public RaceEntry(String bibNumber, double entryFee) {

            counter++;

            entryCode = "ENT-" + counter;

            this.entryFee = entryFee;
            this.amountPaid = 0;
        }

        public void pay(double amount) {
            amountPaid += amount;
        }

        public void pay(double amount, String mode) {

            System.out.println("Paying via " + mode);

            pay(amount);
        }

        public double getBalanceDue() {
            return entryFee - amountPaid;
        }

        public static boolean isValidDiscountCode(
            String code
        ) {

            if (code == null || code.length() != 5) {
                return false;
            }

            if (code.charAt(0) != 'M') {
                return false;
            }

            if (!Character.isDigit(code.charAt(1))) {
                return false;
            }

            if (!Character.isDigit(code.charAt(2))) {
                return false;
            }

            if (!Character.isDigit(code.charAt(3))) {
                return false;
            }

            if (!Character.isUpperCase(code.charAt(4))) {
                return false;
            }

            return true;
        }

        public static int getBibCounter() {
            return counter;
        }
    }

    static class RelayTeamEntry extends RaceEntry {

        private int teamSize;

        public RelayTeamEntry(
            String bibNumber,
            double entryFee,
            int teamSize
        ) {
            super(bibNumber, entryFee);
            this.teamSize = teamSize;
        }

        public int getTeamSize() {
            return teamSize;
        }
    }

    static String settleNight(RaceEntry[] entries) {

        int processed = 0;
        int nullSkipped = 0;
        int relay = 0;
        int individual = 0;

        for (RaceEntry entry : entries) {

            if (entry == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (entry instanceof RelayTeamEntry) {
                relay++;
            } else {
                individual++;
            }
        }

        return processed + " processed | " +
               nullSkipped + " null skipped | " +
               relay + " relay | " +
               individual + " individual";
    }

    public static void main(String[] args) {

        RaceEntry r1 =
            new RaceEntry("BIB1001", 100);

        RelayTeamEntry relay =
            new RelayTeamEntry(
                "BIB2001",
                300,
                4
            );

        System.out.println(r1.entryCode);

        System.out.println(
            RaceEntry.getBibCounter()
        );

        System.out.println(
            RaceEntry.isValidDiscountCode("M123A")
        );

        System.out.println(
            RaceEntry.isValidDiscountCode("M12A")
        );

        System.out.println(
            RaceEntry.isValidDiscountCode("X123A")
        );

        r1.pay(10, "UPI");

        RaceEntry[] entries = {
            r1,
            null,
            relay
        };

        System.out.println(
            settleNight(entries)
        );
    }
}
