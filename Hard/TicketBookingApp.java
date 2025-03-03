import java.util.*;

class TicketBookingSystem {
    private int availableSeats;
    
    public TicketBookingSystem(int seats) {
        this.availableSeats = seats;
    }

    // Synchronized method to prevent double booking
    public synchronized boolean bookTicket(String customerType, String customerName) {
        if (availableSeats > 0) {
            System.out.println(customerType + " " + customerName + " booked a seat. Seats left: " + (--availableSeats));
            return true;
        } else {
            System.out.println(customerType + " " + customerName + " failed to book. No seats left.");
            return false;
        }
    }
}

// Thread class for booking tickets
class Customer extends Thread {
    private TicketBookingSystem system;
    private String customerType;
    private String customerName;

    public Customer(TicketBookingSystem system, String customerType, String customerName, int priority) {
        this.system = system;
        this.customerType = customerType;
        this.customerName = customerName;
        setPriority(priority);  // Setting thread priority
    }

    @Override
    public void run() {
        system.bookTicket(customerType, customerName);
    }
}

public class TicketBookingApp {
    public static void main(String[] args) {
        int totalSeats = 5;  // Change this to increase/decrease available seats
        TicketBookingSystem bookingSystem = new TicketBookingSystem(totalSeats);

        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(bookingSystem, "VIP", "Alice", Thread.MAX_PRIORITY)); // High Priority
        customers.add(new Customer(bookingSystem, "Regular", "Bob", Thread.NORM_PRIORITY));
        customers.add(new Customer(bookingSystem, "VIP", "Charlie", Thread.MAX_PRIORITY));
        customers.add(new Customer(bookingSystem, "Regular", "David", Thread.NORM_PRIORITY));
        customers.add(new Customer(bookingSystem, "VIP", "Eve", Thread.MAX_PRIORITY));
        customers.add(new Customer(bookingSystem, "Regular", "Frank", Thread.NORM_PRIORITY));

        // Shuffle to simulate random booking attempts
        Collections.shuffle(customers);

        // Start all threads
        for (Customer customer : customers) {
            customer.start();
        }

        // Wait for all threads to finish
        for (Customer customer : customers) {
            try {
                customer.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("All bookings processed.");
    }
}
