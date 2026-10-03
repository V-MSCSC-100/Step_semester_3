//Question 1
import java.util.*;

abstract class Question {
    String questionText;
    String correctAnswer;

    Question(String questionText, String correctAnswer) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
    }

    abstract boolean evaluate(String answer);
}

class MultipleChoiceQuestion extends Question {
    MultipleChoiceQuestion(String questionText, String correctAnswer) {
        super(questionText, correctAnswer);
    }

    boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class TrueFalseQuestion extends Question {
    TrueFalseQuestion(String questionText, String correctAnswer) {
        super(questionText, correctAnswer);
    }

    boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class Examination {
    String name;
    List<Question> questions = new ArrayList<>();

    Examination(String name) {
        this.name = name;
    }

    void addQuestion(Question question) {
        questions.add(question);
    }
}

class Student {
    String name;

    Student(String name) {
        this.name = name;
    }
}

class Attempt {
    Student student;
    Examination examination;
    Map<Question, String> answers = new LinkedHashMap<>();
    boolean submitted;

    Attempt(Student student, Examination examination) {
        this.student = student;
        this.examination = examination;
    }

    void answerQuestion(Question question, String answer) {
        if (submitted) {
            System.out.println("Cannot change answer after submission.");
            return;
        }

        answers.put(question, answer);
        System.out.println("Question answered with '" + answer + "'.");
    }

    void submit() {
        if (submitted) {
            System.out.println("Attempt already submitted.");
            return;
        }

        submitted = true;

        int correct = 0;

        for (Question question : examination.questions) {
            String answer = answers.get(question);

            if (answer != null && question.evaluate(answer))
                correct++;
        }

        System.out.println("Examination '" + examination.name +
                "' submitted successfully.");

        System.out.println("Result for '" + examination.name +
                "' attempt: " + correct + "/" +
                examination.questions.size() + " correct.");
    }
}

class ExaminationService {
    Set<String> submittedAttempts = new HashSet<>();

    Attempt start(Student student, Examination examination) {
        String key = student.name + ":" + examination.name;

        if (submittedAttempts.contains(key)) {
            System.out.println("Student already has a submitted attempt.");
            return null;
        }

        System.out.println("Examination '" + examination.name +
                "' started by " + student.name + ".");

        return new Attempt(student, examination);
    }

    void submit(Attempt attempt) {
        if (attempt == null)
            return;

        attempt.submit();

        if (attempt.submitted)
            submittedAttempts.add(
                    attempt.student.name + ":" + attempt.examination.name
            );
    }
}

public class Main {
    public static void main(String[] args) {
        Student student = new Student("Student");

        Examination exam = new Examination("Math Quiz");

        Question q1 = new MultipleChoiceQuestion(
                "Question 1", "A");

        Question q2 = new MultipleChoiceQuestion(
                "Question 2", "B");

        exam.addQuestion(q1);
        exam.addQuestion(q2);

        ExaminationService service = new ExaminationService();

        Attempt attempt = service.start(student, exam);

        attempt.answerQuestion(q1, "A");
        attempt.answerQuestion(q2, "C");

        service.submit(attempt);
    }
}

//Question 2

import java.util.*;

abstract class Vehicle {
    String name;
    boolean available = true;

    Vehicle(String name) {
        this.name = name;
    }

    abstract double calculateCharge(int days);
}

class StandardCar extends Vehicle {
    StandardCar(String name) {
        super(name);
    }

    double calculateCharge(int days) {
        return days * 50;
    }
}

class LuxuryCar extends Vehicle {
    LuxuryCar(String name) {
        super(name);
    }

    double calculateCharge(int days) {
        return days * 100;
    }
}

class SUV extends Vehicle {
    SUV(String name) {
        super(name);
    }

    double calculateCharge(int days) {
        return days * 80;
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Rental {
    Customer customer;
    Vehicle vehicle;
    int days;
    boolean active;

    Rental(Customer customer, Vehicle vehicle, int days) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.days = days;
        this.active = true;
    }

    double getTotal() {
        return vehicle.calculateCharge(days);
    }

    void returnVehicle() {
        active = false;
        vehicle.available = true;
    }
}

class RentalService {
    List<Rental> rentals = new ArrayList<>();

    Rental rent(Customer customer, Vehicle vehicle, int days) {
        if (!vehicle.available) {
            System.out.println("Rental failed: " + vehicle.name +
                    " is not available.");
            return null;
        }

        vehicle.available = false;

        Rental rental = new Rental(customer, vehicle, days);
        rentals.add(rental);

        System.out.printf("%s rented for %d days. Total charge: $%.2f.%n",
                vehicle.name, days, rental.getTotal());

        return rental;
    }

    void returnVehicle(Rental rental) {
        if (rental != null && rental.active) {
            rental.returnVehicle();
            System.out.println(rental.vehicle.name +
                    " returned. Now available.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer("Customer");

        Vehicle luxury = new LuxuryCar("Luxury Car A");
        Vehicle standard = new StandardCar("Standard Car B");

        RentalService service = new RentalService();

        Rental r1 = service.rent(customer, luxury, 3);
        Rental r2 = service.rent(customer, standard, 5);

        service.returnVehicle(r1);
    }
}

//Question 3

import java.time.*;
import java.util.*;

interface RoomPricing {
    double calculatePrice(int nights);
}

class StandardPricing implements RoomPricing {
    public double calculatePrice(int nights) {
        return nights * 150;
    }
}

class DeluxePricing implements RoomPricing {
    public double calculatePrice(int nights) {
        return nights * 200;
    }
}

class SuitePricing implements RoomPricing {
    public double calculatePrice(int nights) {
        return nights * 300;
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Room {
    String name;
    RoomPricing pricing;

    Room(String name, RoomPricing pricing) {
        this.name = name;
        this.pricing = pricing;
    }
}

class Reservation {
    Room room;
    Customer customer;
    LocalDate start;
    LocalDate end;
    boolean active = true;

    Reservation(Room room, Customer customer,
                LocalDate start, LocalDate end) {
        this.room = room;
        this.customer = customer;
        this.start = start;
        this.end = end;
    }

    boolean overlaps(LocalDate otherStart, LocalDate otherEnd) {
        return start.isBefore(otherEnd) && otherStart.isBefore(end);
    }

    double getPrice() {
        return room.pricing.calculatePrice(
                (int) (end.toEpochDay() - start.toEpochDay())
        );
    }

    void cancel() {
        active = false;
    }
}

class Hotel {
    List<Room> rooms = new ArrayList<>();
    List<Reservation> reservations = new ArrayList<>();

    void addRoom(Room room) {
        rooms.add(room);
    }

    boolean isAvailable(Room room, LocalDate start, LocalDate end) {
        for (Reservation reservation : reservations) {
            if (reservation.room == room &&
                    reservation.active &&
                    reservation.overlaps(start, end)) {
                return false;
            }
        }

        return true;
    }

    Reservation book(Room room, Customer customer,
                     LocalDate start, LocalDate end) {

        if (!isAvailable(room, start, end)) {
            System.out.println("Booking failed: " + room.name +
                    " is not available for " + start + " to " + end + ".");
            return null;
        }

        Reservation reservation =
                new Reservation(room, customer, start, end);

        reservations.add(reservation);

        System.out.printf("%s booked from %s to %s. Total price: $%.2f.%n",
                room.name, start, end, reservation.getPrice());

        return reservation;
    }

    void cancel(Reservation reservation) {
        if (reservation != null && reservation.active) {
            reservation.cancel();
            System.out.println("Reservation for " +
                    reservation.room.name +
                    " cancelled successfully.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();

        Room deluxe = new Room("Deluxe Room 101",
                new DeluxePricing());

        Room standard = new Room("Standard Room 205",
                new StandardPricing());

        hotel.addRoom(deluxe);
        hotel.addRoom(standard);

        Customer customer = new Customer("Customer");

        Reservation r1 = hotel.book(
                deluxe,
                customer,
                LocalDate.of(2024, 12, 1),
                LocalDate.of(2024, 12, 5)
        );

        hotel.book(
                standard,
                customer,
                LocalDate.of(2024, 12, 3),
                LocalDate.of(2024, 12, 7)
        );

        hotel.book(
                deluxe,
                customer,
                LocalDate.of(2024, 12, 3),
                LocalDate.of(2024, 12, 7)
        );

        hotel.cancel(r1);
    }
}

//Question 4

import java.time.*;

interface LeavePolicy {
    boolean isValid(LocalDate start, LocalDate end);
}

class FullTimePolicy implements LeavePolicy {
    public boolean isValid(LocalDate start, LocalDate end) {
        return !end.isBefore(start);
    }
}

class PartTimePolicy implements LeavePolicy {
    public boolean isValid(LocalDate start, LocalDate end) {
        return !end.isBefore(start);
    }
}

class ContractPolicy implements LeavePolicy {
    public boolean isValid(LocalDate start, LocalDate end) {
        return !end.isBefore(start);
    }
}

class Employee {
    String name;
    LeavePolicy policy;

    Employee(String name, LeavePolicy policy) {
        this.name = name;
        this.policy = policy;
    }
}

class LeaveRequest {
    enum Status {
        PENDING, APPROVED, REJECTED
    }

    Employee employee;
    LocalDate start;
    LocalDate end;
    Status status;

    LeaveRequest(Employee employee,
                 LocalDate start,
                 LocalDate end) {

        if (!employee.policy.isValid(start, end))
            throw new IllegalArgumentException("Invalid leave dates.");

        this.employee = employee;
        this.start = start;
        this.end = end;
        this.status = Status.PENDING;
    }

    void approve() {
        if (status != Status.PENDING) {
            System.out.println("Cannot approve: Request already processed.");
            return;
        }

        status = Status.APPROVED;

        System.out.println("Leave request for " +
                employee.name + " approved. Status: " + status + ".");
    }

    void reject() {
        if (status != Status.PENDING) {
            System.out.println("Cannot reject: Request already processed.");
            return;
        }

        status = Status.REJECTED;

        System.out.println("Leave request for " +
                employee.name + " rejected. Status: " + status + ".");
    }

    void setPending() {
        if (status != Status.PENDING) {
            System.out.println("Cannot change status: " +
                    status + " request cannot revert to Pending.");
            return;
        }

        status = Status.PENDING;
    }
}

class LeaveManager {
    LeaveRequest submit(Employee employee,
                        LocalDate start,
                        LocalDate end) {

        LeaveRequest request =
                new LeaveRequest(employee, start, end);

        System.out.println("Leave request submitted by " +
                employee.name + " for " + start + " to " +
                end + ". Status: " + request.status + ".");

        return request;
    }
}

public class Main {
    public static void main(String[] args) {
        LeaveManager manager = new LeaveManager();

        Employee john =
                new Employee("John Doe", new FullTimePolicy());

        Employee jane =
                new Employee("Jane Smith", new PartTimePolicy());

        LeaveRequest johnRequest = manager.submit(
                john,
                LocalDate.of(2024, 10, 10),
                LocalDate.of(2024, 10, 12)
        );

        johnRequest.approve();

        LeaveRequest janeRequest = manager.submit(
                jane,
                LocalDate.of(2024, 11, 1),
                LocalDate.of(2024, 11, 5)
        );

        johnRequest.setPending();
    }
}

//Question 5

import java.util.*;

interface IPaymentMethod {
    boolean pay(double amount);
    String getName();
}

class CreditCardPayment implements IPaymentMethod {
    public boolean pay(double amount) {
        return true;
    }

    public String getName() {
        return "Credit Card";
    }
}

class DigitalWalletPayment implements IPaymentMethod {
    public boolean pay(double amount) {
        return false;
    }

    public String getName() {
        return "Digital Wallet";
    }
}

class CashOnDelivery implements IPaymentMethod {
    public boolean pay(double amount) {
        return true;
    }

    public String getName() {
        return "Cash on Delivery";
    }
}

class FoodItem {
    String name;
    double price;

    FoodItem(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class LineItem {
    FoodItem item;
    int quantity;

    LineItem(FoodItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    double getTotal() {
        return item.price * quantity;
    }
}

class Restaurant {
    String name;
    List<FoodItem> menu = new ArrayList<>();

    Restaurant(String name) {
        this.name = name;
    }

    void addItem(FoodItem item) {
        menu.add(item);
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Order {
    enum Status {
        CREATED, PENDING_PAYMENT, PAID
    }

    int id;
    Customer customer;
    Restaurant restaurant;
    List<LineItem> items = new ArrayList<>();
    Status status = Status.CREATED;

    Order(int id, Customer customer, Restaurant restaurant) {
        this.id = id;
        this.customer = customer;
        this.restaurant = restaurant;
    }

    void addItem(FoodItem item, int quantity) {
        if (quantity <= 0)
            return;

        items.add(new LineItem(item, quantity));

        System.out.println("Added " + item.name +
                " (Qty " + quantity + ")");
    }

    double getTotal() {
        double total = 0;

        for (LineItem item : items)
            total += item.getTotal();

        return total;
    }

    boolean place(IPaymentMethod paymentMethod) {
        if (items.isEmpty()) {
            System.out.println("Cannot place order: Order must contain at least one item.");
            return false;
        }

        System.out.println("Order placed successfully.");

        boolean success = paymentMethod.pay(getTotal());

        if (success) {
            status = Status.PAID;

            System.out.println("Payment via " +
                    paymentMethod.getName() + " successful.");

            System.out.println("Order status: " + status + ".");

            System.out.println("Notification: Order #" +
                    id + " placed and paid.");
        } else {
            status = Status.PENDING_PAYMENT;

            System.out.println("Payment via " +
                    paymentMethod.getName() + " failed.");

            System.out.println("Order status: " +
                    status + ".");

            System.out.println("Notification: Order #" +
                    id + " placed, awaiting payment.");
        }

        return success;
    }
}

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer("Customer");

        Restaurant restaurant =
                new Restaurant("Campus Restaurant");

        FoodItem pizza =
                new FoodItem("Pizza", 200);

        FoodItem soda =
                new FoodItem("Soda", 50);

        FoodItem burger =
                new FoodItem("Burger", 150);

        restaurant.addItem(pizza);
        restaurant.addItem(soda);
        restaurant.addItem(burger);

        Order order1 =
                new Order(123, customer, restaurant);

        System.out.println("Order created.");

        order1.addItem(pizza, 2);
        order1.addItem(soda, 1);

        Order emptyOrder =
                new Order(122, customer, restaurant);

        emptyOrder.place(new CreditCardPayment());

        order1.place(new CreditCardPayment());

        Order order2 =
                new Order(124, customer, restaurant);

        order2.addItem(burger, 1);
        order2.place(new DigitalWalletPayment());
    }
}
