//Question 1
class SrmStudent {
    private String name;
    private String regNo;
    private int attendance;


    SrmStudent(String name, String regNo, int attendance) {
        this.name = name;
        this.regNo = regNo;
        this.attendance = attendance;
    }


    void addAttendanceUpdate(int newAttendance) {
        attendance = newAttendance;
    }


    boolean isEligible() {
        return attendance >= 75;
    }


    static double classAverage(SrmStudent[] students) {
        int total = 0;


        for (SrmStudent student : students) {
            total += student.attendance;
        }


        return (double) total / students.length;
    }


    void display() {
        System.out.println(name + " - " + attendance + "% - " +
                (isEligible() ? "Eligible" : "Detained"));
    }
}


public class Main {
    public static void main(String[] args) {
        SrmStudent[] students = {
            new SrmStudent("JohnSmith", "RA251100301011", 82),
            new SrmStudent("JaneDoe", "RA241100301012", 68),
            new SrmStudent("JohnBon", "RA261100301013", 91),
            new SrmStudent("JohnBob", "RA251100301014", 74),
            new SrmStudent("JohnDoe", "RA291100301015", 60)
        };


        for (SrmStudent student : students) {
            student.display();
        }


        System.out.println("Class average: " +
                SrmStudent.classAverage(students) + "%");
    }
}
//Question 2
class FeeAccount {
    private String regNo;
    private double totalFee;
    private double amountPaid;


    FeeAccount(String regNo, double totalFee, double amountPaid) {
        this.regNo = regNo;
        this.totalFee = totalFee;
        this.amountPaid = amountPaid;
    }


    void pay(double amount) {
        if (amount > 0) {
            amountPaid += amount;


            if (amountPaid > totalFee) {
                amountPaid = totalFee;
            }
        }
    }


    double getDue() {
        return totalFee - amountPaid;
    }
}


class HostelFeeAccount extends FeeAccount {
    HostelFeeAccount(String regNo, double totalFee, double amountPaid) {
        super(regNo, totalFee, amountPaid);
    }


    void payInTwoInstallments(double amount) {
        double installment = amount / 2;
        pay(installment);
        pay(installment);
    }
}


class ScholarshipFeeAccount extends FeeAccount {
    private double scholarshipPercent;


    ScholarshipFeeAccount(String regNo, double totalFee, double amountPaid,
                          double scholarshipPercent) {
        super(regNo, totalFee, amountPaid);
        this.scholarshipPercent = scholarshipPercent;
    }


    double effectiveDue() {
        return getDue() - (getDue() * scholarshipPercent / 100);
    }
}


public class Main {
    public static void main(String[] args) {
        FeeAccount plain = new FeeAccount("RA550", 150000, 150000);
        HostelFeeAccount hostel = new HostelFeeAccount("RA551", 200000, 0);
        ScholarshipFeeAccount scholarship =
                new ScholarshipFeeAccount("RA670", 180000, 0, 20);


        FeeAccount[] accounts = {plain, hostel, scholarship};


        plain.pay(150000);
        hostel.payInTwoInstallments(60000);
        scholarship.pay(-5000);


        for (FeeAccount account : accounts) {
            if (account instanceof HostelFeeAccount) {
                System.out.println("Hostel account due: Rs " +
                        account.getDue());
            } else if (account instanceof ScholarshipFeeAccount) {
                ScholarshipFeeAccount s =
                        (ScholarshipFeeAccount) account;
                System.out.println("Scholarship account effective due: Rs " +
                        s.effectiveDue());
            } else {
                System.out.println("Plain account due: Rs " +
                        account.getDue());
            }
        }
    }
}
//Question 3
class HostelRoom {
    private String roomNo;
    private int beds;
    private int occupied;


    HostelRoom(String roomNo, int beds, int occupied) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = occupied;
    }


    void allot(String name) {
        if (occupied < beds) {
            occupied++;
            System.out.println(name + " allotted to room " + roomNo);
        }
    }


    static HostelRoom findAvailableRoom(HostelRoom[] rooms) {
        for (HostelRoom room : rooms) {
            if (room.occupied < room.beds) {
                return room;
            }
        }


        return null;
    }


    static void safeAllot(HostelRoom[] rooms, String studentName) {
        HostelRoom room = findAvailableRoom(rooms);


        if (room != null) {
            room.allot(studentName);
        } else {
            System.out.println("No rooms available for " + studentName);
        }
    }
}


public class Main {
    public static void main(String[] args) {
        HostelRoom[] rooms = {
            new HostelRoom("C-214", 3, 2),
            new HostelRoom("C-507", 2, 2)
        };


        HostelRoom.safeAllot(rooms, "JohnDane");


        HostelRoom[] fullRooms = {
            new HostelRoom("C-214", 3, 3),
            new HostelRoom("C-507", 2, 2)
        };


        HostelRoom.safeAllot(fullRooms, "JohnDane");
    }
}
//Question 4
class BrokenSrmStudent {
    static String name;
    static String regNo;
    static int attendance;


    BrokenSrmStudent(String name, String regNo, int attendance) {
        BrokenSrmStudent.name = name;
        BrokenSrmStudent.regNo = regNo;
        BrokenSrmStudent.attendance = attendance;
    }
}


class SrmStudent {
    private String name;
    private String regNo;
    private int attendance;


    static String university = "SRM";
    static int admissionCount = 0;


    SrmStudent(String name, int attendance) {
        this.name = name;
        this.attendance = attendance;
        admissionCount++;
        this.regNo = "RA2511102601155" +
                String.format("%03d", admissionCount + 10);
    }


    void printIdCard() {
        System.out.println(name + " | " + regNo);
    }


    static void printTotalAdmissions() {
        System.out.println("Students admitted so far: " +
                admissionCount);
    }
}


public class Main {
    public static void main(String[] args) {
        BrokenSrmStudent student1 =
                new BrokenSrmStudent("RandallDoe", "RA550", 82);


        BrokenSrmStudent student2 =
                new BrokenSrmStudent("RhondaDoe", "RA551", 74);


        System.out.println(BrokenSrmStudent.name);
        System.out.println(BrokenSrmStudent.name);


        SrmStudent s1 = new SrmStudent("RandallDoe", 82);
        SrmStudent s2 = new SrmStudent("RhondaDoe", 74);


        s1.printIdCard();
        s2.printIdCard();


        SrmStudent.printTotalAdmissions();
    }
}
//Question 5
class FeeAccount {
    private String regNo;
    private double totalFee;
    private double amountPaid;


    FeeAccount(String regNo, double totalFee, double amountPaid) {
        this.regNo = regNo;
        this.totalFee = totalFee;
        this.amountPaid = amountPaid;
    }


    void pay(double amount) {
        if (amount > 0) {
            amountPaid += amount;


            if (amountPaid > totalFee) {
                amountPaid = totalFee;
            }
        }
    }


    double getDue() {
        return totalFee - amountPaid;
    }
}


class HostelFeeAccount extends FeeAccount {
    HostelFeeAccount(String regNo, double totalFee, double amountPaid) {
        super(regNo, totalFee, amountPaid);
    }


    void payInTwoInstallments(double amount) {
        pay(amount / 2);
        pay(amount / 2);
    }
}


class HostelRoom {
    private String roomNo;
    private int beds;
    private int occupied;


    HostelRoom(String roomNo, int beds, int occupied) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = occupied;
    }


    void allot(String name) {
        if (occupied < beds) {
            occupied++;
        }
    }


    static HostelRoom findAvailableRoom(HostelRoom[] rooms) {
        for (HostelRoom room : rooms) {
            if (room.occupied < room.beds) {
                return room;
            }
        }


        return null;
    }


    String getRoomNo() {
        return roomNo;
    }
}


class SrmStudent {
    String name;
    String regNo;
    HostelFeeAccount feeAccount;
    HostelRoom room;


    static int totalStudents = 0;


    SrmStudent(String name, String regNo,
               HostelFeeAccount feeAccount, HostelRoom room) {
        this.name = name;
        this.regNo = regNo;
        this.feeAccount = feeAccount;
        this.room = room;
        totalStudents++;
    }


    String fullStatus() {
        String roomStatus;


        if (room == null) {
            roomStatus = "unallotted";
        } else {
            roomStatus = room.getRoomNo();
        }


        return name + " | Due: Rs " +
                feeAccount.getDue() +
                " | Room: " + roomStatus;
    }
}


public class Main {
    public static void main(String[] args) {
        HostelRoom room1 = new HostelRoom("C-214", 3, 2);
        HostelRoom room2 = new HostelRoom("C-507", 2, 1);


        HostelFeeAccount fee1 =
                new HostelFeeAccount("RA101", 200000, 0);
        HostelFeeAccount fee2 =
                new HostelFeeAccount("RA102", 180000, 0);
        HostelFeeAccount fee3 =
                new HostelFeeAccount("RA103", 200000, 0);


        fee1.pay(60000);
        fee2.pay(-5000);


        SrmStudent student1 =
                new SrmStudent("Randall", "RA231100301011",
                        fee1, room1);


        SrmStudent student2 =
                new SrmStudent("Rhonda", "RA231100301012",
                        fee2, room2);


        SrmStudent student3 =
                new SrmStudent("Kalainesan", "RA231100301013",
                        fee3, null);


        room1.allot("Randall");
        room2.allot("Rhonda");


        System.out.println(student1.fullStatus());
        System.out.println(student2.fullStatus());
        System.out.println(student3.fullStatus());
        System.out.println("Total students: " +
                SrmStudent.totalStudents);
    }
}
