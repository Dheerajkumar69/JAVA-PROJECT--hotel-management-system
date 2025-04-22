import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    // Shared data models to avoid duplicate handling in different classes
    public static List<Room> rooms = new ArrayList<>();
    public static List<Booking> bookings = new ArrayList<>();
    public static List<Customer> customers = new ArrayList<>();
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Initialize with sample data
        initializeSampleData();
        
        SwingUtilities.invokeLater(() -> new LoginScreen());
    }
    
    private static void initializeSampleData() {
        // Initialize rooms
        rooms.add(new Room("101", "Standard", 100, "Available"));
        rooms.add(new Room("102", "Standard", 100, "Occupied"));
        rooms.add(new Room("201", "Deluxe", 150, "Available"));
        rooms.add(new Room("202", "Deluxe", 150, "Maintenance"));
        rooms.add(new Room("301", "Suite", 250, "Available"));
        
        // Initialize customers
        customers.add(new Customer("C001", "John Doe", "john@example.com", "555-1234", "123 Main St"));
        customers.add(new Customer("C002", "Jane Smith", "jane@example.com", "555-5678", "456 Oak Ave"));
        customers.add(new Customer("C003", "Mike Johnson", "mike@example.com", "555-9012", "789 Pine Rd"));
        customers.add(new Customer("C004", "Sarah Brown", "sarah@example.com", "555-3456", "321 Maple St"));
        
        // Initialize bookings
        bookings.add(new Booking("B001", "C001", "101", "2023-06-10", "2023-06-15", "Completed"));
        bookings.add(new Booking("B002", "C002", "202", "2023-06-12", "2023-06-18", "In Progress"));
        bookings.add(new Booking("B003", "C003", "301", "2023-06-15", "2023-06-20", "Confirmed"));
        bookings.add(new Booking("B004", "C004", "102", "2023-06-18", "2023-06-22", "Confirmed"));
    }
}

// Model classes
class Room {
    private String roomNumber;
    private String type;
    private double price;
    private String status;
    
    public Room(String roomNumber, String type, double price, String status) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.status = status;
    }
    
    public String getRoomNumber() { return roomNumber; }
    public String getType() { return type; }
    public double getPrice() { return price; }
    public String getStatus() { return status; }
    
    public void setType(String type) { this.type = type; }
    public void setPrice(double price) { this.price = price; }
    public void setStatus(String status) { this.status = status; }
}

class Customer {
    private String customerId;
    private String name;
    private String email;
    private String phone;
    private String address;
    
    public Customer(String customerId, String name, String email, String phone, String address) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
    
    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setAddress(String address) { this.address = address; }
}

class Booking {
    private String bookingId;
    private String customerId;
    private String roomNumber;
    private String checkInDate;
    private String checkOutDate;
    private String status;
    
    public Booking(String bookingId, String customerId, String roomNumber, String checkInDate, String checkOutDate, String status) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.roomNumber = roomNumber;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.status = status;
    }
    
    public String getBookingId() { return bookingId; }
    public String getCustomerId() { return customerId; }
    public String getRoomNumber() { return roomNumber; }
    public String getCheckInDate() { return checkInDate; }
    public String getCheckOutDate() { return checkOutDate; }
    public String getStatus() { return status; }
    
    public void setStatus(String status) { this.status = status; }
}

class LoginScreen extends JFrame {
    public LoginScreen() {
        setTitle("Hotel Management System - Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        JLabel titleLabel = new JLabel("Hotel Management System", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        
        JLabel userTypeLabel = new JLabel("Login as:");
        String[] userTypes = {"Staff", "Customer"};
        JComboBox<String> userTypeCombo = new JComboBox<>(userTypes);
        
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            String userType = (String) userTypeCombo.getSelectedItem();
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            
            if (authenticate(userType, username, password)) {
                dispose();
                if (userType.equals("Staff")) {
                    new StaffDashboard();
                } else {
                    new CustomerDashboard(username);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials!");
            }
        });
        
        passwordField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginButton.doClick();
                }
            }
        });
        
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        
        panel.add(userTypeLabel);
        panel.add(userTypeCombo);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel(""));
        panel.add(loginButton);
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(titlePanel, BorderLayout.NORTH);
        getContentPane().add(panel, BorderLayout.CENTER);
        
        setVisible(true);
    }
    
    private boolean authenticate(String userType, String username, String password) {
        if (userType.equals("Staff") && username.equals("admin") && password.equals("admin123")) {
            return true;
        } else if (userType.equals("Customer")) {
            for (Customer customer : Main.customers) {
                if (customer.getCustomerId().equals(username) && password.equals("user123")) {
                    return true;
                }
            }
        }
        return false;
    }
}

class StaffDashboard extends JFrame {
    private JPanel contentPanel;
    
    public StaffDashboard() {
        setTitle("Hotel Management System - Staff Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Create header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(41, 128, 185));
        headerPanel.setPreferredSize(new Dimension(800, 50));
        
        JLabel titleLabel = new JLabel("Staff Dashboard");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> logout());
        
        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(logoutButton, BorderLayout.EAST);
        
        // Create sidebar
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(new Color(52, 73, 94));
        sidebarPanel.setPreferredSize(new Dimension(180, 600));
        
        // Add menu buttons
        String[] menuItems = {"Dashboard", "Rooms", "Bookings", "Customers", "Reports"};
        for (String item : menuItems) {
            JButton menuButton = new JButton(item);
            menuButton.setMaximumSize(new Dimension(180, 40));
            menuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            menuButton.addActionListener(e -> switchPanel(item));
            sidebarPanel.add(menuButton);
            sidebarPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        }
        
        // Create content panel
        contentPanel = new JPanel(new CardLayout());
        contentPanel.add(createDashboardPanel(), "Dashboard");
        contentPanel.add(createRoomsPanel(), "Rooms");
        contentPanel.add(createBookingsPanel(), "Bookings");
        contentPanel.add(createCustomersPanel(), "Customers");
        contentPanel.add(createReportsPanel(), "Reports");
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(sidebarPanel, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        add(mainPanel);
        setVisible(true);
    }
    
    private void switchPanel(String panelName) {
        CardLayout cl = (CardLayout) contentPanel.getLayout();
        cl.show(contentPanel, panelName);
    }
    
    private void logout() {
        dispose();
        new LoginScreen();
    }
    
    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JLabel titleLabel = new JLabel("Dashboard Overview");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel statsPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Count available and occupied rooms
        int availableRooms = 0;
        int occupiedRooms = 0;
        for (Room room : Main.rooms) {
            if (room.getStatus().equals("Available")) {
                availableRooms++;
            } else if (room.getStatus().equals("Occupied")) {
                occupiedRooms++;
            }
        }
        
        // Count today's bookings and pending checkouts (for simplicity, just use the bookings list)
        int todayBookings = Main.bookings.size() > 2 ? 2 : Main.bookings.size();
        int pendingCheckouts = 1; // Just for demonstration
        
        statsPanel.add(createStatCard("Available Rooms", String.valueOf(availableRooms)));
        statsPanel.add(createStatCard("Occupied Rooms", String.valueOf(occupiedRooms)));
        statsPanel.add(createStatCard("Bookings Today", String.valueOf(todayBookings)));
        statsPanel.add(createStatCard("Pending Checkouts", String.valueOf(pendingCheckouts)));
        
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(statsPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createStatCard(String title, String value) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        card.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        
        JLabel valueLabel = new JLabel(value, JLabel.CENTER);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 32));
        
        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);
        
        return card;
    }
    
    private JPanel createRoomsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JLabel titleLabel = new JLabel("Room Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Create table model from rooms list
        String[] columnNames = {"Room No", "Type", "Price", "Status"};
        Object[][] data = new Object[Main.rooms.size()][4];
        
        for (int i = 0; i < Main.rooms.size(); i++) {
            Room room = Main.rooms.get(i);
            data[i][0] = room.getRoomNumber();
            data[i][1] = room.getType();
            data[i][2] = "$" + room.getPrice();
            data[i][3] = room.getStatus();
        }
        
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        
        JButton addButton = new JButton("Add Room");
        JButton editButton = new JButton("Edit Room");
        JButton deleteButton = new JButton("Delete Room");
        JButton allocateButton = new JButton("Allocate Room"); // New button for room allocation
        
        // Implement Add Room functionality
        addButton.addActionListener(e -> {
            JTextField roomNumberField = new JTextField();
            JComboBox<String> roomTypeCombo = new JComboBox<>(new String[]{"Standard", "Deluxe", "Suite"});
            JTextField priceField = new JTextField();
            JComboBox<String> statusCombo = new JComboBox<>(new String[]{"Available", "Occupied", "Maintenance"});
            
            JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
            inputPanel.add(new JLabel("Room Number:"));
            inputPanel.add(roomNumberField);
            inputPanel.add(new JLabel("Room Type:"));
            inputPanel.add(roomTypeCombo);
            inputPanel.add(new JLabel("Price:"));
            inputPanel.add(priceField);
            inputPanel.add(new JLabel("Status:"));
            inputPanel.add(statusCombo);
            
            int result = JOptionPane.showConfirmDialog(panel, inputPanel, "Add New Room", 
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            
            if (result == JOptionPane.OK_OPTION) {
                try {
                    String roomNumber = roomNumberField.getText();
                    String roomType = (String) roomTypeCombo.getSelectedItem();
                    double price = Double.parseDouble(priceField.getText());
                    String status = (String) statusCombo.getSelectedItem();
                    
                    // Check if room number already exists
                    boolean roomExists = false;
                    for (Room r : Main.rooms) {
                        if (r.getRoomNumber().equals(roomNumber)) {
                            roomExists = true;
                            break;
                        }
                    }
                    
                    if (roomExists) {
                        JOptionPane.showMessageDialog(panel, "Room number already exists!");
                    } else {
                        Main.rooms.add(new Room(roomNumber, roomType, price, status));
                        JOptionPane.showMessageDialog(panel, "Room added successfully!");
                        
                        // Refresh the panel
                        contentPanel.remove(panel);
                        contentPanel.add(createRoomsPanel(), "Rooms");
                        switchPanel("Rooms");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel, "Invalid price format!");
                }
            }
        });
        
        // Implement Edit Room functionality
        editButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(panel, "Please select a room to edit!");
                return;
            }
            
            Room selectedRoom = Main.rooms.get(selectedRow);
            
            JTextField roomNumberField = new JTextField(selectedRoom.getRoomNumber());
            roomNumberField.setEditable(false); // Room number can't be changed
            
            JComboBox<String> roomTypeCombo = new JComboBox<>(new String[]{"Standard", "Deluxe", "Suite"});
            roomTypeCombo.setSelectedItem(selectedRoom.getType());
            
            JTextField priceField = new JTextField(String.valueOf(selectedRoom.getPrice()));
            
            JComboBox<String> statusCombo = new JComboBox<>(new String[]{"Available", "Occupied", "Maintenance"});
            statusCombo.setSelectedItem(selectedRoom.getStatus());
            
            JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
            inputPanel.add(new JLabel("Room Number:"));
            inputPanel.add(roomNumberField);
            inputPanel.add(new JLabel("Room Type:"));
            inputPanel.add(roomTypeCombo);
            inputPanel.add(new JLabel("Price:"));
            inputPanel.add(priceField);
            inputPanel.add(new JLabel("Status:"));
            inputPanel.add(statusCombo);
            
            int result = JOptionPane.showConfirmDialog(panel, inputPanel, "Edit Room", 
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            
            if (result == JOptionPane.OK_OPTION) {
                try {
                    String roomType = (String) roomTypeCombo.getSelectedItem();
                    double price = Double.parseDouble(priceField.getText());
                    String status = (String) statusCombo.getSelectedItem();
                    
                    selectedRoom.setType(roomType);
                    selectedRoom.setPrice(price);
                    selectedRoom.setStatus(status);
                    
                    JOptionPane.showMessageDialog(panel, "Room updated successfully!");
                    
                    // Refresh the panel
                    contentPanel.remove(panel);
                    contentPanel.add(createRoomsPanel(), "Rooms");
                    switchPanel("Rooms");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel, "Invalid price format!");
                }
            }
        });
        
        // Implement Delete Room functionality
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(panel, "Please select a room to delete!");
                return;
            }
            
            Room selectedRoom = Main.rooms.get(selectedRow);
            
            // Check if room is in any bookings
            boolean roomInBookings = false;
            for (Booking booking : Main.bookings) {
                if (booking.getRoomNumber().equals(selectedRoom.getRoomNumber())) {
                    roomInBookings = true;
                    break;
                }
            }
            
            if (roomInBookings) {
                JOptionPane.showMessageDialog(panel, "Cannot delete room as it has bookings associated with it!");
            } else {
                int confirm = JOptionPane.showConfirmDialog(panel, 
                        "Are you sure you want to delete Room " + selectedRoom.getRoomNumber() + "?", 
                        "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                
                if (confirm == JOptionPane.YES_OPTION) {
                    Main.rooms.remove(selectedRow);
                    JOptionPane.showMessageDialog(panel, "Room deleted successfully!");
                    
                    // Refresh the panel
                    contentPanel.remove(panel);
                    contentPanel.add(createRoomsPanel(), "Rooms");
                    switchPanel("Rooms");
                }
            }
        });
        
        // Implement Room Allocation functionality
        allocateButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(panel, "Please select a room to allocate!");
                return;
            }
            
            Room selectedRoom = Main.rooms.get(selectedRow);
            
            if (!selectedRoom.getStatus().equals("Available")) {
                JOptionPane.showMessageDialog(panel, "This room is not available for allocation!");
                return;
            }
            
            // Create a combo box with customer names
            JComboBox<String> customerCombo = new JComboBox<>();
            Map<String, String> customerMap = new HashMap<>(); // To store displayed name -> customer ID mapping
            
            for (Customer customer : Main.customers) {
                String displayName = customer.getName() + " (" + customer.getCustomerId() + ")";
                customerCombo.addItem(displayName);
                customerMap.put(displayName, customer.getCustomerId());
            }
            
            JTextField checkInField = new JTextField(java.time.LocalDate.now().toString());
            JTextField checkOutField = new JTextField(java.time.LocalDate.now().plusDays(1).toString());
            
            JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
            inputPanel.add(new JLabel("Customer:"));
            inputPanel.add(customerCombo);
            inputPanel.add(new JLabel("Check-in Date:"));
            inputPanel.add(checkInField);
            inputPanel.add(new JLabel("Check-out Date:"));
            inputPanel.add(checkOutField);
            
            int result = JOptionPane.showConfirmDialog(panel, inputPanel, "Allocate Room", 
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            
            if (result == JOptionPane.OK_OPTION) {
                String selectedCustomerDisplay = (String) customerCombo.getSelectedItem();
                String customerId = customerMap.get(selectedCustomerDisplay);
                String checkIn = checkInField.getText();
                String checkOut = checkOutField.getText();
                
                // Generate a new booking ID
                String bookingId = "B" + String.format("%03d", Main.bookings.size() + 1);
                
                // Create new booking
                Main.bookings.add(new Booking(bookingId, customerId, selectedRoom.getRoomNumber(), checkIn, checkOut, "Confirmed"));
                
                // Update room status
                selectedRoom.setStatus("Occupied");
                
                JOptionPane.showMessageDialog(panel, "Room allocated successfully!\nBooking ID: " + bookingId);
                
                // Refresh the panel
                contentPanel.remove(panel);
                contentPanel.add(createRoomsPanel(), "Rooms");
                switchPanel("Rooms");
            }
        });
        
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(allocateButton);
        
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createBookingsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JLabel titleLabel = new JLabel("Bookings");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Create table model from bookings list
        String[] columnNames = {"Booking ID", "Guest", "Room", "Check-in", "Check-out", "Status"};
        Object[][] data = new Object[Main.bookings.size()][6];
        
        for (int i = 0; i < Main.bookings.size(); i++) {
            Booking booking = Main.bookings.get(i);
            data[i][0] = booking.getBookingId();
            
            // Find customer name
            String guestName = "Unknown";
            for (Customer customer : Main.customers) {
                if (customer.getCustomerId().equals(booking.getCustomerId())) {
                    guestName = customer.getName();
                    break;
                }
            }
            
            data[i][1] = guestName;
            data[i][2] = booking.getRoomNumber();
            data[i][3] = booking.getCheckInDate();
            data[i][4] = booking.getCheckOutDate();
            data[i][5] = booking.getStatus();
        }
        
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        
        JButton viewButton = new JButton("View Details");
        JButton checkInButton = new JButton("Check In");
        JButton checkOutButton = new JButton("Check Out");
        JButton cancelButton = new JButton("Cancel Booking");
        
        // Implement View Details functionality
        viewButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(panel, "Please select a booking to view!");
                return;
            }
            
            Booking selectedBooking = Main.bookings.get(selectedRow);
            
            // Find customer
            Customer bookingCustomer = null;
            for (Customer customer : Main.customers) {
                if (customer.getCustomerId().equals(selectedBooking.getCustomerId())) {
                    bookingCustomer = customer;
                    break;
                }
            }
            
            // Find room
            Room bookingRoom = null;
            for (Room room : Main.rooms) {
                if (room.getRoomNumber().equals(selectedBooking.getRoomNumber())) {
                    bookingRoom = room;
                    break;
                }
            }
            
            StringBuilder details = new StringBuilder();
            details.append("Booking ID: ").append(selectedBooking.getBookingId()).append("\n");
            details.append("Status: ").append(selectedBooking.getStatus()).append("\n\n");
            
            details.append("Guest Information:\n");
            if (bookingCustomer != null) {
                details.append("Name: ").append(bookingCustomer.getName()).append("\n");
                details.append("Email: ").append(bookingCustomer.getEmail()).append("\n");
                details.append("Phone: ").append(bookingCustomer.getPhone()).append("\n");
                details.append("Address: ").append(bookingCustomer.getAddress()).append("\n\n");
            } else {
                details.append("Guest information not found.\n\n");
            }
            
            details.append("Room Information:\n");
            if (bookingRoom != null) {
                details.append("Room Number: ").append(bookingRoom.getRoomNumber()).append("\n");
                details.append("Type: ").append(bookingRoom.getType()).append("\n");
                details.append("Price: $").append(bookingRoom.getPrice()).append(" per night\n\n");
            } else {
                details.append("Room information not found.\n\n");
            }
            
            details.append("Dates:\n");
            details.append("Check-in: ").append(selectedBooking.getCheckInDate()).append("\n");
            details.append("Check-out: ").append(selectedBooking.getCheckOutDate()).append("\n");
            
            JOptionPane.showMessageDialog(panel, details.toString(), "Booking Details", JOptionPane.INFORMATION_MESSAGE);
        });
        
        // Implement Check In functionality
        checkInButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(panel, "Please select a booking to check in!");
                return;
            }
            
            Booking selectedBooking = Main.bookings.get(selectedRow);
            
            if (!selectedBooking.getStatus().equals("Confirmed")) {
                JOptionPane.showMessageDialog(panel, "This booking cannot be checked in. It must be in 'Confirmed' status.");
                return;
            }
            
            // Update booking status
            selectedBooking.setStatus("In Progress");
            
            // Update room status
            for (Room room : Main.rooms) {
                if (room.getRoomNumber().equals(selectedBooking.getRoomNumber())) {
                    room.setStatus("Occupied");
                    break;
                }
            }
            
            JOptionPane.showMessageDialog(panel, "Guest has been checked in successfully!");
            
            // Refresh the panel
            contentPanel.remove(panel);
            contentPanel.add(createBookingsPanel(), "Bookings");
            switchPanel("Bookings");
        });
        
        // Implement Check Out functionality
        checkOutButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(panel, "Please select a booking to check out!");
                return;
            }
            
            Booking selectedBooking = Main.bookings.get(selectedRow);
            
            if (!selectedBooking.getStatus().equals("In Progress")) {
                JOptionPane.showMessageDialog(panel, "This booking cannot be checked out. It must be in 'In Progress' status.");
                return;
            }
            
            // Update booking status
            selectedBooking.setStatus("Completed");
            
            // Update room status
            for (Room room : Main.rooms) {
                if (room.getRoomNumber().equals(selectedBooking.getRoomNumber())) {
                    room.setStatus("Available");
                    break;
                }
            }
            
            JOptionPane.showMessageDialog(panel, "Guest has been checked out successfully!");
            
            // Refresh the panel
            contentPanel.remove(panel);
            contentPanel.add(createBookingsPanel(), "Bookings");
            switchPanel("Bookings");
        });
        
        // Implement Cancel Booking functionality
        cancelButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(panel, "Please select a booking to cancel!");
                return;
            }
            
            Booking selectedBooking = Main.bookings.get(selectedRow);
            
            if (selectedBooking.getStatus().equals("Completed")) {
                JOptionPane.showMessageDialog(panel, "Cannot cancel a completed booking!");
                return;
            }
            
            int confirm = JOptionPane.showConfirmDialog(panel, 
                    "Are you sure you want to cancel this booking?", 
                    "Confirm Cancellation", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                // Update booking status
                selectedBooking.setStatus("Cancelled");
                
                // Update room status if it was occupied by this booking
                for (Room room : Main.rooms) {
                    if (room.getRoomNumber().equals(selectedBooking.getRoomNumber()) && 
                            room.getStatus().equals("Occupied")) {
                        room.setStatus("Available");
                        break;
                    }
                }
                
                JOptionPane.showMessageDialog(panel, "Booking has been cancelled successfully!");
                
                // Refresh the panel
                contentPanel.remove(panel);
                contentPanel.add(createBookingsPanel(), "Bookings");
                switchPanel("Bookings");
            }
        });
        
        buttonPanel.add(viewButton);
        buttonPanel.add(checkInButton);
        buttonPanel.add(checkOutButton);
        buttonPanel.add(cancelButton);
        
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createCustomersPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JLabel titleLabel = new JLabel("Customers");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Create table model from customers list
        String[] columnNames = {"Customer ID", "Name", "Email", "Phone", "Bookings"};
        Object[][] data = new Object[Main.customers.size()][5];
        
        for (int i = 0; i < Main.customers.size(); i++) {
            Customer customer = Main.customers.get(i);
            data[i][0] = customer.getCustomerId();
            data[i][1] = customer.getName();
            data[i][2] = customer.getEmail();
            data[i][3] = customer.getPhone();
            
            // Count bookings for this customer
            int bookingCount = 0;
            for (Booking booking : Main.bookings) {
                if (booking.getCustomerId().equals(customer.getCustomerId())) {
                    bookingCount++;
                }
            }
            
            data[i][4] = bookingCount;
        }
        
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        
        JButton addButton = new JButton("Add Customer");
        JButton editButton = new JButton("Edit Customer");
        JButton viewButton = new JButton("View Bookings");
        
        // Implement Add Customer functionality
        addButton.addActionListener(e -> {
            JTextField customerIdField = new JTextField();
            JTextField nameField = new JTextField();
            JTextField emailField = new JTextField();
            JTextField phoneField = new JTextField();
            JTextField addressField = new JTextField();
            
            JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
            inputPanel.add(new JLabel("Customer ID:"));
            inputPanel.add(customerIdField);
            inputPanel.add(new JLabel("Name:"));
            inputPanel.add(nameField);
            inputPanel.add(new JLabel("Email:"));
            inputPanel.add(emailField);
            inputPanel.add(new JLabel("Phone:"));
            inputPanel.add(phoneField);
            inputPanel.add(new JLabel("Address:"));
            inputPanel.add(addressField);
            
            int result = JOptionPane.showConfirmDialog(panel, inputPanel, "Add New Customer", 
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            
            if (result == JOptionPane.OK_OPTION) {
                String customerId = customerIdField.getText();
                String name = nameField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                String address = addressField.getText();
                
                // Validate fields
                if (customerId.isEmpty() || name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "All fields except address are required!");
                    return;
                }
                
                // Check if customer ID already exists
                boolean customerExists = false;
                for (Customer c : Main.customers) {
                    if (c.getCustomerId().equals(customerId)) {
                        customerExists = true;
                        break;
                    }
                }
                
                if (customerExists) {
                    JOptionPane.showMessageDialog(panel, "Customer ID already exists!");
                } else {
                    Main.customers.add(new Customer(customerId, name, email, phone, address));
                    JOptionPane.showMessageDialog(panel, "Customer added successfully!");
                    
                    // Refresh the panel
                    contentPanel.remove(panel);
                    contentPanel.add(createCustomersPanel(), "Customers");
                    switchPanel("Customers");
                }
            }
        });
        
        // Implement Edit Customer functionality
        editButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(panel, "Please select a customer to edit!");
                return;
            }
            
            Customer selectedCustomer = Main.customers.get(selectedRow);
            
            JTextField customerIdField = new JTextField(selectedCustomer.getCustomerId());
            customerIdField.setEditable(false); // Customer ID can't be changed
            
            JTextField nameField = new JTextField(selectedCustomer.getName());
            JTextField emailField = new JTextField(selectedCustomer.getEmail());
            JTextField phoneField = new JTextField(selectedCustomer.getPhone());
            JTextField addressField = new JTextField(selectedCustomer.getAddress());
            
            JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
            inputPanel.add(new JLabel("Customer ID:"));
            inputPanel.add(customerIdField);
            inputPanel.add(new JLabel("Name:"));
            inputPanel.add(nameField);
            inputPanel.add(new JLabel("Email:"));
            inputPanel.add(emailField);
            inputPanel.add(new JLabel("Phone:"));
            inputPanel.add(phoneField);
            inputPanel.add(new JLabel("Address:"));
            inputPanel.add(addressField);
            
            int result = JOptionPane.showConfirmDialog(panel, inputPanel, "Edit Customer", 
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            
            if (result == JOptionPane.OK_OPTION) {
                String name = nameField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                String address = addressField.getText();
                
                // Validate fields
                if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "All fields except address are required!");
                    return;
                }
                
                selectedCustomer.setName(name);
                selectedCustomer.setEmail(email);
                selectedCustomer.setPhone(phone);
                selectedCustomer.setAddress(address);
                
                JOptionPane.showMessageDialog(panel, "Customer updated successfully!");
                
                // Refresh the panel
                contentPanel.remove(panel);
                contentPanel.add(createCustomersPanel(), "Customers");
                switchPanel("Customers");
            }
        });
        
        // Implement View Bookings functionality
        viewButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(panel, "Please select a customer to view bookings!");
                return;
            }
            
            Customer selectedCustomer = Main.customers.get(selectedRow);
            
            // Find all bookings for this customer
            List<Booking> customerBookings = new ArrayList<>();
            for (Booking booking : Main.bookings) {
                if (booking.getCustomerId().equals(selectedCustomer.getCustomerId())) {
                    customerBookings.add(booking);
                }
            }
            
            if (customerBookings.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "This customer has no bookings!");
                return;
            }
            
            // Create a dialog with a table of bookings
            JDialog bookingsDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(panel), 
                    "Bookings for " + selectedCustomer.getName(), true);
            bookingsDialog.setSize(600, 400);
            bookingsDialog.setLocationRelativeTo(panel);
            
            JPanel dialogPanel = new JPanel(new BorderLayout());
            
            String[] bookingColumns = {"Booking ID", "Room", "Check-in", "Check-out", "Status"};
            Object[][] bookingData = new Object[customerBookings.size()][5];
            
            for (int i = 0; i < customerBookings.size(); i++) {
                Booking booking = customerBookings.get(i);
                bookingData[i][0] = booking.getBookingId();
                bookingData[i][1] = booking.getRoomNumber();
                bookingData[i][2] = booking.getCheckInDate();
                bookingData[i][3] = booking.getCheckOutDate();
                bookingData[i][4] = booking.getStatus();
            }
            
            JTable bookingsTable = new JTable(bookingData, bookingColumns);
            JScrollPane bookingsScrollPane = new JScrollPane(bookingsTable);
            bookingsScrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            
            dialogPanel.add(bookingsScrollPane, BorderLayout.CENTER);
            
            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(evt -> bookingsDialog.dispose());
            
            JPanel dialogButtonPanel = new JPanel();
            dialogButtonPanel.add(closeButton);
            dialogButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
            
            dialogPanel.add(dialogButtonPanel, BorderLayout.SOUTH);
            
            bookingsDialog.add(dialogPanel);
            bookingsDialog.setVisible(true);
        });
        
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(viewButton);
        
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createReportsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JLabel titleLabel = new JLabel("Reports");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel reportsPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        reportsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        String[] reportTypes = {"Occupancy Report", "Revenue Report", "Booking Statistics", "Customer Statistics"};
        
        for (String report : reportTypes) {
            JPanel reportCard = new JPanel(new BorderLayout());
            reportCard.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            reportCard.setBackground(Color.WHITE);
            
            JLabel reportLabel = new JLabel(report, JLabel.CENTER);
            reportLabel.setFont(new Font("Arial", Font.BOLD, 14));
            
            JButton generateButton = new JButton("Generate");
            generateButton.addActionListener(e -> {
                // Create a report dialog
                JDialog reportDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(panel), 
                        report, true);
                reportDialog.setSize(600, 400);
                reportDialog.setLocationRelativeTo(panel);
                
                JPanel dialogPanel = new JPanel(new BorderLayout());
                dialogPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                
                JLabel reportTitleLabel = new JLabel(report, JLabel.CENTER);
                reportTitleLabel.setFont(new Font("Arial", Font.BOLD, 16));
                
                JPanel reportContentPanel = new JPanel();
                
                // Generate report based on type
                switch (report) {
                    case "Occupancy Report":
                        reportContentPanel = generateOccupancyReport();
                        break;
                    case "Revenue Report":
                        reportContentPanel = generateRevenueReport();
                        break;
                    case "Booking Statistics":
                        reportContentPanel = generateBookingStatistics();
                        break;
                    case "Customer Statistics":
                        reportContentPanel = generateCustomerStatistics();
                        break;
                }
                
                JButton closeButton = new JButton("Close");
                closeButton.addActionListener(evt -> reportDialog.dispose());
                
                JPanel buttonPanel = new JPanel();
                buttonPanel.add(closeButton);
                
                dialogPanel.add(reportTitleLabel, BorderLayout.NORTH);
                dialogPanel.add(reportContentPanel, BorderLayout.CENTER);
                dialogPanel.add(buttonPanel, BorderLayout.SOUTH);
                
                reportDialog.add(dialogPanel);
                reportDialog.setVisible(true);
            });
            
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(generateButton);
            buttonPanel.setBackground(Color.WHITE);
            
            reportCard.add(reportLabel, BorderLayout.CENTER);
            reportCard.add(buttonPanel, BorderLayout.SOUTH);
            reportsPanel.add(reportCard);
        }
        
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(reportsPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    // Methods for generating different reports
    private JPanel generateOccupancyReport() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Count rooms by status
        int available = 0;
        int occupied = 0;
        int maintenance = 0;
        
        for (Room room : Main.rooms) {
            switch (room.getStatus()) {
                case "Available":
                    available++;
                    break;
                case "Occupied":
                    occupied++;
                    break;
                case "Maintenance":
                    maintenance++;
                    break;
            }
        }
        
        // Create a table with occupancy data
        String[] columnNames = {"Status", "Count", "Percentage"};
        Object[][] data = {
            {"Available", available, String.format("%.1f%%", (double) available / Main.rooms.size() * 100)},
            {"Occupied", occupied, String.format("%.1f%%", (double) occupied / Main.rooms.size() * 100)},
            {"Maintenance", maintenance, String.format("%.1f%%", (double) maintenance / Main.rooms.size() * 100)},
            {"Total", Main.rooms.size(), "100.0%"}
        };
        
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        
        // Add a summary
        JLabel summaryLabel = new JLabel(String.format(
                "<html>Current Occupancy Rate: <b>%.1f%%</b><br>" +
                "Available Rooms: <b>%d</b><br>" +
                "Occupied Rooms: <b>%d</b><br>" +
                "Rooms in Maintenance: <b>%d</b></html>",
                (double) occupied / Main.rooms.size() * 100,
                available, occupied, maintenance));
        summaryLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        panel.add(summaryLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel generateRevenueReport() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Calculate revenue by room type
        double standardRevenue = 0;
        double deluxeRevenue = 0;
        double suiteRevenue = 0;
        
        // For simplicity, just calculate based on room prices and status
        for (Room room : Main.rooms) {
            if (room.getStatus().equals("Occupied")) {
                switch (room.getType()) {
                    case "Standard":
                        standardRevenue += room.getPrice();
                        break;
                    case "Deluxe":
                        deluxeRevenue += room.getPrice();
                        break;
                    case "Suite":
                        suiteRevenue += room.getPrice();
                        break;
                }
            }
        }
        
        double totalRevenue = standardRevenue + deluxeRevenue + suiteRevenue;
        
        // Create a table with revenue data
        String[] columnNames = {"Room Type", "Daily Revenue", "Percentage"};
        Object[][] data = {
            {"Standard", "$" + standardRevenue, totalRevenue > 0 ? String.format("%.1f%%", standardRevenue / totalRevenue * 100) : "0.0%"},
            {"Deluxe", "$" + deluxeRevenue, totalRevenue > 0 ? String.format("%.1f%%", deluxeRevenue / totalRevenue * 100) : "0.0%"},
            {"Suite", "$" + suiteRevenue, totalRevenue > 0 ? String.format("%.1f%%", suiteRevenue / totalRevenue * 100) : "0.0%"},
            {"Total", "$" + totalRevenue, "100.0%"}
        };
        
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        
        // Add a summary
        JLabel summaryLabel = new JLabel(String.format(
                "<html>Daily Revenue: <b>$%.2f</b><br>" +
                "Projected Monthly Revenue: <b>$%.2f</b><br>" +
                "Projected Annual Revenue: <b>$%.2f</b></html>",
                totalRevenue, totalRevenue * 30, totalRevenue * 365));
        summaryLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        panel.add(summaryLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel generateBookingStatistics() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Count bookings by status
        int confirmed = 0;
        int inProgress = 0;
        int completed = 0;
        int cancelled = 0;
        
        for (Booking booking : Main.bookings) {
            switch (booking.getStatus()) {
                case "Confirmed":
                    confirmed++;
                    break;
                case "In Progress":
                    inProgress++;
                    break;
                case "Completed":
                    completed++;
                    break;
                case "Cancelled":
                    cancelled++;
                    break;
            }
        }
        
        int totalBookings = Main.bookings.size();
        
        // Create a table with booking data
        String[] columnNames = {"Status", "Count", "Percentage"};
        Object[][] data = {
            {"Confirmed", confirmed, totalBookings > 0 ? String.format("%.1f%%", (double) confirmed / totalBookings * 100) : "0.0%"},
            {"In Progress", inProgress, totalBookings > 0 ? String.format("%.1f%%", (double) inProgress / totalBookings * 100) : "0.0%"},
            {"Completed", completed, totalBookings > 0 ? String.format("%.1f%%", (double) completed / totalBookings * 100) : "0.0%"},
            {"Cancelled", cancelled, totalBookings > 0 ? String.format("%.1f%%", (double) cancelled / totalBookings * 100) : "0.0%"},
            {"Total", totalBookings, "100.0%"}
        };
        
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        
        // Add a summary
        JLabel summaryLabel = new JLabel(String.format(
                "<html>Total Bookings: <b>%d</b><br>" +
                "Active Bookings: <b>%d</b><br>" +
                "Completion Rate: <b>%.1f%%</b><br>" +
                "Cancellation Rate: <b>%.1f%%</b></html>",
                totalBookings, confirmed + inProgress,
                totalBookings > 0 ? (double) completed / totalBookings * 100 : 0.0,
                totalBookings > 0 ? (double) cancelled / totalBookings * 100 : 0.0));
        summaryLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        panel.add(summaryLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel generateCustomerStatistics() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Calculate customer statistics
        Map<String, Integer> bookingsPerCustomer = new HashMap<>();
        
        for (Booking booking : Main.bookings) {
            String customerId = booking.getCustomerId();
            bookingsPerCustomer.put(customerId, bookingsPerCustomer.getOrDefault(customerId, 0) + 1);
        }
        
        // Find top customers by number of bookings
        List<Map.Entry<String, Integer>> sortedCustomers = new ArrayList<>(bookingsPerCustomer.entrySet());
        sortedCustomers.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        
        // Create a table with customer data
        String[] columnNames = {"Customer ID", "Name", "Bookings"};
        Object[][] data = new Object[Math.min(sortedCustomers.size(), 5)][3];
        
        for (int i = 0; i < data.length; i++) {
            Map.Entry<String, Integer> entry = sortedCustomers.get(i);
            String customerId = entry.getKey();
            int bookingCount = entry.getValue();
            
            // Find customer name
            String customerName = "Unknown";
            for (Customer customer : Main.customers) {
                if (customer.getCustomerId().equals(customerId)) {
                    customerName = customer.getName();
                    break;
                }
            }
            
            data[i][0] = customerId;
            data[i][1] = customerName;
            data[i][2] = bookingCount;
        }
        
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        
        // Add a summary
        double avgBookingsPerCustomer = (double) Main.bookings.size() / Main.customers.size();
        
        JLabel summaryLabel = new JLabel(String.format(
                "<html>Total Customers: <b>%d</b><br>" +
                "Total Bookings: <b>%d</b><br>" +
                "Average Bookings per Customer: <b>%.2f</b><br>" +
                "Top Customers:</html>",
                Main.customers.size(), Main.bookings.size(), avgBookingsPerCustomer));
        summaryLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        panel.add(summaryLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
}

class CustomerDashboard extends JFrame {
    private JPanel contentPanel;
    private String customerId;
    private Customer customer;
    
    public CustomerDashboard(String customerId) {
        this.customerId = customerId;
        
        // Find the customer object
        for (Customer c : Main.customers) {
            if (c.getCustomerId().equals(customerId)) {
                this.customer = c;
                break;
            }
        }
        
        setTitle("Hotel Management System - Customer Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Create header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(26, 188, 156));
        headerPanel.setPreferredSize(new Dimension(800, 50));
        
        JLabel titleLabel = new JLabel("Customer Dashboard");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> logout());
        
        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(logoutButton, BorderLayout.EAST);
        
        // Create sidebar
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(new Color(52, 73, 94));
        sidebarPanel.setPreferredSize(new Dimension(180, 600));
        
        // Add menu buttons
        String[] menuItems = {"Dashboard", "Browse Rooms", "My Bookings", "My Profile"};
        for (String item : menuItems) {
            JButton menuButton = new JButton(item);
            menuButton.setMaximumSize(new Dimension(180, 40));
            menuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            menuButton.addActionListener(e -> switchPanel(item));
            sidebarPanel.add(menuButton);
            sidebarPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        }
        
        // Create content panel
        contentPanel = new JPanel(new CardLayout());
        contentPanel.add(createDashboardPanel(), "Dashboard");
        contentPanel.add(createBrowseRoomsPanel(), "Browse Rooms");
        contentPanel.add(createMyBookingsPanel(), "My Bookings");
        contentPanel.add(createMyProfilePanel(), "My Profile");
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(sidebarPanel, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        add(mainPanel);
        setVisible(true);
    }
    
    private void switchPanel(String panelName) {
        CardLayout cl = (CardLayout) contentPanel.getLayout();
        cl.show(contentPanel, panelName);
    }
    
    private void logout() {
        dispose();
        new LoginScreen();
    }
    
    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        String greeting = "Welcome, " + (customer != null ? customer.getName() : "Guest") + "!";
        JLabel welcomeLabel = new JLabel(greeting);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel cardsPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        cardsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        cardsPanel.add(createActionCard("Book a Room", "Find and book your perfect room"));
        cardsPanel.add(createActionCard("My Bookings", "View and manage your bookings"));
        cardsPanel.add(createActionCard("Special Offers", "Check out our latest deals"));
        cardsPanel.add(createActionCard("Contact Us", "Need help? Reach out to us"));
        
        panel.add(welcomeLabel, BorderLayout.NORTH);
        panel.add(cardsPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createActionCard(String title, String description) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        card.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        
        JLabel descLabel = new JLabel("<html>" + description + "</html>");
        descLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        
        JButton actionButton = new JButton("View");
        actionButton.addActionListener(e -> {
            if (title.equals("Book a Room")) {
                switchPanel("Browse Rooms");
            } else if (title.equals("My Bookings")) {
                switchPanel("My Bookings");
            } else {
                JOptionPane.showMessageDialog(card, "This feature will be available soon!");
            }
        });
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(actionButton);
        
        card.add(titleLabel, BorderLayout.NORTH);
        card.add(descLabel, BorderLayout.CENTER);
        card.add(buttonPanel, BorderLayout.SOUTH);
        
        return card;
    }
    
    private JPanel createBrowseRoomsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JLabel titleLabel = new JLabel("Browse Rooms");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        
        JTextField checkInField = new JTextField(java.time.LocalDate.now().toString(), 10);
        JTextField checkOutField = new JTextField(java.time.LocalDate.now().plusDays(1).toString(), 10);
        JComboBox<String> roomTypeCombo = new JComboBox<>(new String[] {"Any", "Standard", "Deluxe", "Suite"});
        
        searchPanel.add(new JLabel("Check-in:"));
        searchPanel.add(checkInField);
        searchPanel.add(new JLabel("Check-out:"));
        searchPanel.add(checkOutField);
        searchPanel.add(new JLabel("Room Type:"));
        searchPanel.add(roomTypeCombo);
        
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> {
            // In real app, this would filter the rooms based on search criteria
            JOptionPane.showMessageDialog(panel, "Search results updated");
        });
        searchPanel.add(searchButton);
        
        JPanel roomsPanel = new JPanel(new GridLayout(0, 2, 15, 15));
        roomsPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        
        // Add room cards for available rooms
        for (Room room : Main.rooms) {
            if (room.getStatus().equals("Available")) {
                roomsPanel.add(createRoomCard(room));
            }
        }
        
        JScrollPane scrollPane = new JScrollPane(roomsPanel);
        
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(searchPanel, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(contentPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createRoomCard(Room room) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        card.setBackground(Color.WHITE);
        
        String description;
        switch (room.getType()) {
            case "Standard":
                description = "Single bed, TV, AC, WiFi";
                break;
            case "Deluxe":
                description = "Queen bed, TV, AC, Mini bar, WiFi";
                break;
            case "Suite":
                description = "King bed, Living area, Jacuzzi, Mini bar, WiFi";
                break;
            default:
                description = "Comfortable accommodation with basic amenities";
        }
        
        JLabel roomLabel = new JLabel("Room " + room.getRoomNumber() + " - " + room.getType());
        roomLabel.setFont(new Font("Arial", Font.BOLD, 14));
        roomLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        
        JLabel priceLabel = new JLabel("$" + room.getPrice() + "/night");
        priceLabel.setFont(new Font("Arial", Font.BOLD, 12));
        priceLabel.setForeground(new Color(46, 134, 193));
        priceLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 10));
        
        JLabel descLabel = new JLabel("<html>" + description + "</html>");
        descLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        
        JButton bookButton = new JButton("Book Now");
        bookButton.addActionListener(e -> {
            // Create booking dialog
            JDialog bookingDialog = new JDialog(this, "Book Room " + room.getRoomNumber(), true);
            bookingDialog.setSize(400, 300);
            bookingDialog.setLocationRelativeTo(this);
            
            JPanel bookingPanel = new JPanel(new GridLayout(4, 2, 10, 10));
            bookingPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            
            JTextField checkInField = new JTextField(java.time.LocalDate.now().toString());
            JTextField checkOutField = new JTextField(java.time.LocalDate.now().plusDays(1).toString());
            
            bookingPanel.add(new JLabel("Room:"));
            bookingPanel.add(new JLabel(room.getRoomNumber() + " - " + room.getType()));
            
            bookingPanel.add(new JLabel("Price:"));
            bookingPanel.add(new JLabel("$" + room.getPrice() + "/night"));
            
            bookingPanel.add(new JLabel("Check-in Date:"));
            bookingPanel.add(checkInField);
            
            bookingPanel.add(new JLabel("Check-out Date:"));
            bookingPanel.add(checkOutField);
            
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton confirmButton = new JButton("Confirm Booking");
            JButton cancelButton = new JButton("Cancel");
            
            confirmButton.addActionListener(evt -> {
                String checkIn = checkInField.getText();
                String checkOut = checkOutField.getText();
                
                // Generate a new booking ID
                String bookingId = "B" + String.format("%03d", Main.bookings.size() + 1);
                
                // Create new booking
                Main.bookings.add(new Booking(bookingId, customerId, room.getRoomNumber(), checkIn, checkOut, "Confirmed"));
                
                // Update room status
                room.setStatus("Occupied");
                
                JOptionPane.showMessageDialog(bookingDialog, 
                        "Booking confirmed!\nBooking ID: " + bookingId);
                
                bookingDialog.dispose();
                
                // Refresh the rooms panel
                contentPanel.remove(contentPanel.getComponent(1)); // Remove "Browse Rooms" panel
                contentPanel.add(createBrowseRoomsPanel(), "Browse Rooms");
                switchPanel("Browse Rooms");
            });
            
            cancelButton.addActionListener(evt -> bookingDialog.dispose());
            
            buttonPanel.add(confirmButton);
            buttonPanel.add(cancelButton);
            
            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.add(bookingPanel, BorderLayout.CENTER);
            mainPanel.add(buttonPanel, BorderLayout.SOUTH);
            
            bookingDialog.add(mainPanel);
            bookingDialog.setVisible(true);
        });
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(bookButton);
        
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBackground(Color.WHITE);
        infoPanel.add(roomLabel, BorderLayout.NORTH);
        infoPanel.add(priceLabel, BorderLayout.CENTER);
        infoPanel.add(descLabel, BorderLayout.SOUTH);
        
        card.add(infoPanel, BorderLayout.CENTER);
        card.add(buttonPanel, BorderLayout.SOUTH);
        
        return card;
    }
    
    private JPanel createMyBookingsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JLabel titleLabel = new JLabel("My Bookings");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Find bookings for this customer
        List<Booking> myBookings = new ArrayList<>();
        for (Booking booking : Main.bookings) {
            if (booking.getCustomerId().equals(customerId)) {
                myBookings.add(booking);
            }
        }
        
        String[] columnNames = {"Booking ID", "Room", "Check-in", "Check-out", "Status"};
        Object[][] data = new Object[myBookings.size()][5];
        
        for (int i = 0; i < myBookings.size(); i++) {
            Booking booking = myBookings.get(i);
            data[i][0] = booking.getBookingId();
            data[i][1] = booking.getRoomNumber();
            data[i][2] = booking.getCheckInDate();
            data[i][3] = booking.getCheckOutDate();
            data[i][4] = booking.getStatus();
        }
        
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        
        JButton viewButton = new JButton("View Details");
        JButton cancelButton = new JButton("Cancel Booking");
        
        viewButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                Booking selectedBooking = myBookings.get(row);
                
                // Find room details
                Room room = null;
                for (Room r : Main.rooms) {
                    if (r.getRoomNumber().equals(selectedBooking.getRoomNumber())) {
                        room = r;
                        break;
                    }
                }
                
                StringBuilder details = new StringBuilder();
                details.append("Booking ID: ").append(selectedBooking.getBookingId()).append("\n");
                details.append("Status: ").append(selectedBooking.getStatus()).append("\n\n");
                
                details.append("Room Information:\n");
                if (room != null) {
                    details.append("Room Number: ").append(room.getRoomNumber()).append("\n");
                    details.append("Type: ").append(room.getType()).append("\n");
                    details.append("Price: $").append(room.getPrice()).append(" per night\n\n");
                } else {
                    details.append("Room information not found.\n\n");
                }
                
                details.append("Dates:\n");
                details.append("Check-in: ").append(selectedBooking.getCheckInDate()).append("\n");
                details.append("Check-out: ").append(selectedBooking.getCheckOutDate()).append("\n");
                
                JOptionPane.showMessageDialog(panel, details.toString(), "Booking Details", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(panel, "Please select a booking first");
            }
        });
        
        cancelButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                Booking selectedBooking = myBookings.get(row);
                
                // Check if booking can be cancelled
                if (selectedBooking.getStatus().equals("Completed")) {
                    JOptionPane.showMessageDialog(panel, "Cannot cancel a completed booking!");
                    return;
                }
                
                int option = JOptionPane.showConfirmDialog(panel, 
                    "Are you sure you want to cancel booking " + selectedBooking.getBookingId() + "?");
                
                if (option == JOptionPane.YES_OPTION) {
                    // Update booking status
                    selectedBooking.setStatus("Cancelled");
                    
                    // Update room status
                    for (Room room : Main.rooms) {
                        if (room.getRoomNumber().equals(selectedBooking.getRoomNumber())) {
                            if (room.getStatus().equals("Occupied")) {
                                room.setStatus("Available");
                            }
                            break;
                        }
                    }
                    
                    JOptionPane.showMessageDialog(panel, "Booking " + selectedBooking.getBookingId() + " has been cancelled");
                    
                    // Refresh panel
                    contentPanel.remove(panel);
                    contentPanel.add(createMyBookingsPanel(), "My Bookings");
                    switchPanel("My Bookings");
                }
            } else {
                JOptionPane.showMessageDialog(panel, "Please select a booking first");
            }
        });
        
        buttonPanel.add(viewButton);
        buttonPanel.add(cancelButton);
        
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createMyProfilePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JLabel titleLabel = new JLabel("My Profile");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        String[] labels = {"Customer ID:", "Full Name:", "Email:", "Phone:", "Address:", "Password:"};
        String[] values = {
            customer != null ? customer.getCustomerId() : "",
            customer != null ? customer.getName() : "",
            customer != null ? customer.getEmail() : "",
            customer != null ? customer.getPhone() : "",
            customer != null ? customer.getAddress() : "",
            "********"
        };
        
        JTextField[] fields = new JTextField[values.length];
        
        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.gridwidth = 1;
            formPanel.add(new JLabel(labels[i]), gbc);
            
            gbc.gridx = 1;
            gbc.gridwidth = 2;
            fields[i] = new JTextField(values[i]);
            
            // Customer ID can't be changed
            if (i == 0) {
                fields[i].setEditable(false);
            }
            
            // Password is masked and handled separately
            if (i == 5) {
                JPasswordField passwordField = new JPasswordField(values[i]);
                formPanel.add(passwordField, gbc);
            } else {
                formPanel.add(fields[i], gbc);
            }
        }
        
        JButton updateButton = new JButton("Update Profile");
        updateButton.addActionListener(e -> {
            if (customer != null) {
                customer.setName(fields[1].getText());
                customer.setEmail(fields[2].getText());
                customer.setPhone(fields[3].getText());
                customer.setAddress(fields[4].getText());
                
                JOptionPane.showMessageDialog(panel, "Profile updated successfully!");
            } else {
                JOptionPane.showMessageDialog(panel, "Error: Customer information not found!");
            }
        });
        
        gbc.gridx = 1;
        gbc.gridy = labels.length;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(updateButton, gbc);
        
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);
        
        return panel;
    }
}