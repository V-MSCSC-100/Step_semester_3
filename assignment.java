//Question 1
class LibraryMember {
    private String membershipId;
    protected String branchCode;
    private double finesOwed;
    public String displayName;

    public LibraryMember(String membershipId, String branchCode, double finesOwed, String displayName) {
        if (membershipId == null || membershipId.trim().length() < 4) {
            throw new IllegalArgumentException("construction rejected");
        }

        this.membershipId = membershipId.trim();
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }
}

public class Main {
    static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        if (fieldModifier.equals("private")) {
            return accessorContext.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";
        }

        if (fieldModifier.equals("default")) {
            return accessorContext.equals("SAME_CLASS") ||
                   accessorContext.equals("SAME_PACKAGE") ? "ALLOWED" : "DENIED";
        }

        if (fieldModifier.equals("protected")) {
            return accessorContext.equals("SAME_CLASS") ||
                   accessorContext.equals("SAME_PACKAGE") ? "ALLOWED" : "DENIED";
        }

        return "DENIED";
    }

    static String summarizeByModifier(String[][] attempts) {
        String[] modifiers = {"private", "default", "protected", "public"};
        int[][] counts = new int[4][2];

        for (String[] attempt : attempts) {
            String modifier = attempt[0];
            String context = attempt[1];

            int index = -1;

            for (int i = 0; i < modifiers.length; i++) {
                if (modifiers[i].equals(modifier)) {
                    index = i;
                    break;
                }
            }

            if (index != -1) {
                if (classifyAccess(modifier, context).equals("ALLOWED")) {
                    counts[index][0]++;
                } else {
                    counts[index][1]++;
                }
            }
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < modifiers.length; i++) {
            if (counts[i][0] + counts[i][1] > 0) {
                if (result.length() > 0) {
                    result.append(" | ");
                }

                result.append(modifiers[i])
                      .append(": ")
                      .append(counts[i][0])
                      .append(" allowed / ")
                      .append(counts[i][1])
                      .append(" denied");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String[][] attempts = {
            {"private", "SAME_CLASS"},
            {"private", "SAME_PACKAGE"},
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"protected", "SAME_PACKAGE"},
            {"protected", "SAME_CLASS"},
            {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(classifyAccess("private", "SAME_CLASS"));
        System.out.println(classifyAccess("protected", "DIFFERENT_PACKAGE"));
        System.out.println(summarizeByModifier(attempts));

        try {
            new LibraryMember("LB9", "BR1", 0, "Priya Nair");
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        LibraryMember member =
            new LibraryMember("LB94", "BR1", 0, "Priya Nair");

        System.out.println(member.displayName);
    }
}

//Question 2

class AccessChecker {
    static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        if (fieldModifier.equals("private")) {
            return accessorContext.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";
        }

        if (fieldModifier.equals("default")) {
            return accessorContext.equals("SAME_CLASS") ||
                   accessorContext.equals("SAME_PACKAGE") ? "ALLOWED" : "DENIED";
        }

        if (fieldModifier.equals("protected")) {
            if (accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE") ||
                accessorContext.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        return "DENIED";
    }

    static String describeContext(String accessorContext) {
        String[] parts = accessorContext.split("_");
        StringBuilder result = new StringBuilder();

        for (String part : parts) {
            if (part.length() > 0) {
                if (result.length() > 0) {
                    result.append(" ");
                }

                result.append(part.substring(0, 1).toUpperCase());
                result.append(part.substring(1).toLowerCase());
            }
        }

        return result.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println(
            AccessChecker.classifyAccess(
                "protected",
                "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"
            )
        );

        System.out.println(
            AccessChecker.classifyAccess(
                "protected",
                "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"
            )
        );

        System.out.println(
            AccessChecker.describeContext(
                "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"
            )
        );
    }
}

//Question 3

class BookInventory {
    private int copiesTotal;
    private int copiesAvailable;

    public BookInventory(int copiesTotal) {
        if (copiesTotal <= 0) {
            throw new IllegalArgumentException("construction rejected");
        }

        this.copiesTotal = copiesTotal;
        this.copiesAvailable = copiesTotal;
    }

    public void checkOut() {
        if (copiesAvailable > 0) {
            copiesAvailable--;
        }
    }

    public void checkIn() {
        if (copiesAvailable < copiesTotal) {
            copiesAvailable++;
        }
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }
}

public class Main {
    public static void main(String[] args) {
        try {
            new BookInventory(0);
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        BookInventory b = new BookInventory(3);

        b.checkOut();
        b.checkOut();
        b.checkOut();
        b.checkOut();

        System.out.println(b.getCopiesAvailable());

        b.checkIn();
        b.checkIn();
        b.checkIn();
        b.checkIn();

        System.out.println(b.getCopiesAvailable());
    }
}

//Question 4

class LibraryMember {
    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswerHash;

    public LibraryMember() {
        this(null, null);
    }

    public LibraryMember(String name) {
        this(null, name);
    }

    public LibraryMember(String membershipId, String name) {
        this.membershipId = membershipId;
        this.name = name;
        this.premiumMember = false;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String id) {
        if (membershipId == null) {
            membershipId = id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premium) {
        this.premiumMember = premium;
    }

    public void setSecurityAnswer(String answer) {
        if (answer != null) {
            securityAnswerHash = Integer.toHexString(answer.hashCode());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        LibraryMember m1 = new LibraryMember("Priya Nair");
        System.out.println(m1.getMembershipId());

        LibraryMember m2 =
            new LibraryMember("LIB-8841", "Priya Nair");

        System.out.println(m2.getMembershipId());

        LibraryMember m3 = new LibraryMember();

        m3.setMembershipId("LIB-8841");
        m3.setMembershipId("FAKE-0000");

        System.out.println(m3.getMembershipId());

        m3.setPremiumMember(true);
        System.out.println(m3.isPremiumMember());

        m3.setSecurityAnswer("Blue");
    }
}

//Question 5

class LoanReceipt {
    private final String memberId;
    private final String[] bookIds;

    static {
        System.setProperty("loan.receipt.initialized", "true");
    }

    public LoanReceipt(String memberId, String[] bookIds) {
        if (bookIds == null) {
            throw new IllegalArgumentException("construction rejected");
        }

        for (String id : bookIds) {
            if (id == null || !id.matches("BK-\\d{3}")) {
                throw new IllegalArgumentException("construction rejected");
            }
        }

        this.memberId = memberId;
        this.bookIds = bookIds.clone();
    }

    public String[] getBookIds() {
        return bookIds.clone();
    }

    public LoanReceipt withCorrectedBookId(int index, String newId) {
        if (index < 0 || index >= bookIds.length ||
            newId == null || !newId.matches("BK-\\d{3}")) {
            throw new IllegalArgumentException("construction rejected");
        }

        String[] corrected = bookIds.clone();
        corrected[index] = newId;

        return new LoanReceipt(memberId, corrected);
    }

    public static String processNightlyCirculation(LoanReceipt[] receipts) {
        int processed = 0;
        int skipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (LoanReceipt receipt : receipts) {
            if (receipt == null) {
                skipped++;
            } else {
                processed++;

                if (receipt instanceof ReferenceOnlyLoanReceipt) {
                    referenceOnly++;
                } else {
                    regular++;
                }
            }
        }

        return processed + " processed | " +
               skipped + " null skipped | " +
               referenceOnly + " reference-only | " +
               regular + " regular";
    }
}

class ReferenceOnlyLoanReceipt extends LoanReceipt {
    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(
        String memberId,
        String[] bookIds,
        String roomNumber
    ) {
        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
}

public class Main {
    public static void main(String[] args) {
        try {
            new LoanReceipt(
                "LIB-8841",
                new String[]{"BK-100", "bad"}
            );
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        LoanReceipt r = new LoanReceipt(
            "LIB-8841",
            new String[]{"BK-100", "BK-101"}
        );

        String[] ids = r.getBookIds();
        ids[0] = "HACKED";

        System.out.println(r.getBookIds()[0]);

        LoanReceipt corrected =
            r.withCorrectedBookId(0, "BK-999");

        System.out.println(corrected.getBookIds()[0]);

        LoanReceipt[] receipts = {
            new ReferenceOnlyLoanReceipt(
                "LIB-001",
                new String[]{"BK-200"},
                "Reading Room 3"
            ),
            null,
            new LoanReceipt(
                "LIB-002",
                new String[]{"BK-201"}
            )
        };

        System.out.println(
            LoanReceipt.processNightlyCirculation(receipts)
        );
    }
}
