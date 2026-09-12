//Question 1
class BookIssue {
    private String title;
    private String borrowerName;
    private int daysOverdue;


    BookIssue(String title, String borrowerName, int daysOverdue) {
        this.title = title;
        this.borrowerName = borrowerName;
        this.daysOverdue = daysOverdue;
    }


    double fineAmount() {
        if (daysOverdue > 0) {
            return daysOverdue * 5;
        }


        return 0;
    }


    boolean isSeverelyOverdue() {
        return daysOverdue > 14;
    }


    static double totalFineCollected(BookIssue[] issues) {
        double total = 0;


        for (BookIssue issue : issues) {
            total += issue.fineAmount();
        }


        return total;
    }


    void display() {
        System.out.println(title + " - " + daysOverdue +
                " days - " +
                (isSeverelyOverdue() ? "Severely overdue" : "OK"));
    }
}


public class Main {
    public static void main(String[] args) {
        BookIssue[] issues = {
            new BookIssue("Clean Code", "Vignesh", 18),
            new BookIssue("Effective Java", "John Doe", 5),
            new BookIssue("Refactoring", "Vignesh", 0),
            new BookIssue("DSA Handbook", "John Doe", 21),
            new BookIssue("Design Patterns", "Vignesh", 9)
        };


        for (BookIssue issue : issues) {
            issue.display();
        }


        System.out.println("Total fine collected: Rs " +
                BookIssue.totalFineCollected(issues));
    }
}

//Question 2

class Employee {
    private int empId;
    private String empName;
    private double salary;


    Employee(int empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }


    double getSalary() {
        return salary;
    }


    String getEmpName() {
        return empName;
    }
}


class ManagerEmployee extends Employee {
    private double teamBonus;


    ManagerEmployee(int empId, String empName,
                    double salary, double teamBonus) {
        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }


    double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}


class InternEmployee extends Employee {
    private double stipendCap;


    InternEmployee(int empId, String empName,
                   double salary, double stipendCap) {
        super(empId, empName, salary);
        this.stipendCap = stipendCap;
    }


    double effectiveSalary() {
        return Math.min(getSalary(), stipendCap);
    }
}


public class Main {
    public static void main(String[] args) {
        Employee plain =
                new Employee(101, "Vignesh", 40000);


        ManagerEmployee manager =
                new ManagerEmployee(102, "John Doe",
                        70000, 8000);


        InternEmployee intern =
                new InternEmployee(103, "Vignesh",
                        12000, 10000);


        Employee[] employees = {
            plain, manager, intern
        };


        for (Employee employee : employees) {
            if (employee instanceof ManagerEmployee) {
                ManagerEmployee managerEmployee =
                        (ManagerEmployee) employee;


                System.out.println("Manager effective pay: Rs " +
                        managerEmployee.effectiveSalary());
            } else if (employee instanceof InternEmployee) {
                InternEmployee internEmployee =
                        (InternEmployee) employee;


                System.out.println("Intern effective pay: Rs " +
                        internEmployee.effectiveSalary());
            } else {
                System.out.println("Plain employee pay: Rs " +
                        employee.getSalary());
            }
        }
    }
}

//Question 3

class ParkingSlot {
    private String slotNo;
    private int capacity;
    private int occupiedCount;


    ParkingSlot(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }


    void allot(String vehicleNo) {
        if (occupiedCount < capacity) {
            occupiedCount++;
            System.out.println(vehicleNo +
                    " allotted to slot " + slotNo);
        }
    }


    static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {
        for (ParkingSlot slot : slots) {
            if (slot.occupiedCount < slot.capacity) {
                return slot;
            }
        }


        return null;
    }


    static void safeAllot(ParkingSlot[] slots, String vehicleNo) {
        ParkingSlot slot = findAvailableSlot(slots);


        if (slot != null) {
            slot.allot(vehicleNo);
        } else {
            System.out.println("No slots available for " +
                    vehicleNo);
        }
    }
}


public class Main {
    public static void main(String[] args) {
        ParkingSlot[] slots = {
            new ParkingSlot("A1", 4, 3),
            new ParkingSlot("A2", 5, 5)
        };


        ParkingSlot.safeAllot(slots, "TN67AB1234");


        ParkingSlot[] fullSlots = {
            new ParkingSlot("A1", 4, 4),
            new ParkingSlot("A2", 5, 5)
        };


        ParkingSlot.safeAllot(fullSlots, "TN67AB1234");
    }
}

//Question 4

class BrokenLibraryMember {
    static String name;
    static String memberId;
    static int booksIssued;


    BrokenLibraryMember(String name, String memberId,
                        int booksIssued) {
        BrokenLibraryMember.name = name;
        BrokenLibraryMember.memberId = memberId;
        BrokenLibraryMember.booksIssued = booksIssued;
    }
}


class LibraryMember {
    private String name;
    private String memberId;
    private int booksIssued;


    static String libraryName = "Central Library";
    static int memberCount = 0;


    LibraryMember(String name, int booksIssued) {
        this.name = name;
        this.booksIssued = booksIssued;


        memberCount++;
        this.memberId = "LM-" + (1000 + memberCount);
    }


    void printMemberCard() {
        System.out.println(name + " | " + memberId);
    }


    static void printTotalMembers() {
        System.out.println("Total members: " +
                memberCount);
    }
}


public class Main {
    public static void main(String[] args) {
        BrokenLibraryMember member1 =
                new BrokenLibraryMember("Kalaiarasan", "LM-1001", 2);


        BrokenLibraryMember member2 =
                new BrokenLibraryMember("Vignesh", "LM-1002", 3);


        System.out.println(BrokenLibraryMember.name);
        System.out.println(BrokenLibraryMember.name);


        LibraryMember m1 =
                new LibraryMember("Kalaiarasan", 2);


        LibraryMember m2 =
                new LibraryMember("Vignesh", 3);


        m1.printMemberCard();
        m2.printMemberCard();


        LibraryMember.printTotalMembers();
    }
}

//Question 5

class Employee {
    private int empId;
    private String empName;
    private double salary;


    Employee(int empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }


    double getSalary() {
        return salary;
    }
}


class ManagerEmployee extends Employee {
    private double teamBonus;


    ManagerEmployee(int empId, String empName,
                    double salary, double teamBonus) {
        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }


    double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}


class InternEmployee extends Employee {
    private double stipendCap;


    InternEmployee(int empId, String empName,
                   double salary, double stipendCap) {
        super(empId, empName, salary);
        this.stipendCap = stipendCap;
    }


    double effectiveSalary() {
        return Math.min(getSalary(), stipendCap);
    }
}


class ParkingSlot {
    private String slotNo;
    private int capacity;
    private int occupiedCount;


    ParkingSlot(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }


    void allot(String vehicleNo) {
        if (occupiedCount < capacity) {
            occupiedCount++;
        }
    }


    static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {
        for (ParkingSlot slot : slots) {
            if (slot.occupiedCount < slot.capacity) {
                return slot;
            }
        }


        return null;
    }


    String getSlotNo() {
        return slotNo;
    }
}


class CompanyEmployeeRecord {
    String name;
    String empId;
    Employee employee;
    ParkingSlot slot;


    static int totalRecords = 0;


    CompanyEmployeeRecord(String name, String empId,
                           Employee employee, ParkingSlot slot) {
        this.name = name;
        this.empId = empId;
        this.employee = employee;
        this.slot = slot;
        totalRecords++;
    }


    String fullProfile() {
        double pay;


        if (employee instanceof ManagerEmployee) {
            pay = ((ManagerEmployee) employee).effectiveSalary();
        } else if (employee instanceof InternEmployee) {
            pay = ((InternEmployee) employee).effectiveSalary();
        } else {
            pay = employee.getSalary();
        }


        String slotStatus;


        if (slot == null) {
            slotStatus = "no parking assigned";
        } else {
            slotStatus = slot.getSlotNo();
        }


        return name + " | Pay: Rs " + pay +
                " | Slot: " + slotStatus;
    }
}


public class Main {
    public static void main(String[] args) {
        ParkingSlot slot1 =
                new ParkingSlot("A1", 1, 0);


        ParkingSlot slot2 =
                new ParkingSlot("A2", 1, 0);


        ManagerEmployee manager =
                new ManagerEmployee(101, "Vignesh",
                        70000, 8000);


        Employee employee =
                new Employee(102, "Kalainesan",
                        40000);


        InternEmployee intern =
                new InternEmployee(103, "Fignet",
                        12000, 10000);


        ParkingSlot[] slots = {
            slot1, slot2
        };


        ParkingSlot first =
                ParkingSlot.findAvailableSlot(slots);


        if (first != null) {
            first.allot("Vignesh");
        }


        ParkingSlot second =
                ParkingSlot.findAvailableSlot(slots);


        if (second != null) {
            second.allot("Kalainesan");
        }


        CompanyEmployeeRecord record1 =
                new CompanyEmployeeRecord(
                        "Vignesh", "E101",
                        manager, first);


        CompanyEmployeeRecord record2 =
                new CompanyEmployeeRecord(
                        "Kalainesan", "E102",
                        employee, second);


        CompanyEmployeeRecord record3 =
                new CompanyEmployeeRecord(
                        "Fignet", "E103",
                        intern, null);


        System.out.println(record1.fullProfile());
        System.out.println(record2.fullProfile());
        System.out.println(record3.fullProfile());


        System.out.println("Total records: " +
                CompanyEmployeeRecord.totalRecords);
    }
}


