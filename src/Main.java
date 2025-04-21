import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class Main {
    public static void main(String[] args) {
        try {
            // Set look and feel to system default
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            
            // Set global UI properties
            UIManager.put("Button.arc", 15);
            UIManager.put("Component.arc", 10);
            UIManager.put("ProgressBar.arc", 10);
            UIManager.put("TextComponent.arc", 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            // Start with the login screen
            new LoginScreen();
        });
    }
}

class LoginScreen extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> userTypeComboBox;
    
    public LoginScreen() {
        setTitle("Hotel Management System - Login");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Create main panel with a nice background color
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(UIUtils.BACKGROUND_COLOR);
        
        // Create header panel with gradient
        JPanel headerPanel = UIUtils.createGradientPanel(UIUtils.PRIMARY_COLOR, UIUtils.SECONDARY_COLOR);
        headerPanel.setPreferredSize(new Dimension(450, 70));
        headerPanel.setLayout(new BorderLayout());
        
        JLabel titleLabel = new JLabel("  Hotel Management System");
        titleLabel.setFont(UIUtils.TITLE_FONT);
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        
        // Create login panel
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null); // Using absolute positioning for login components
        loginPanel.setBackground(UIUtils.BACKGROUND_COLOR);
        
        // Add a decorative element
        JPanel decorPanel = new JPanel();
        decorPanel.setBackground(UIUtils.ACCENT_COLOR);
        decorPanel.setBounds(0, 0, 5, 280);
        loginPanel.add(decorPanel);
        
        JLabel userTypeLabel = new JLabel("Login as:");
        userTypeLabel.setFont(UIUtils.REGULAR_FONT);
        userTypeLabel.setBounds(100, 30, 80, 25);
        
        String[] userTypes = {"Hotel Staff", "Customer"};
        userTypeComboBox = new JComboBox<>(userTypes);
        userTypeComboBox.setBounds(180, 30, 150, 30);
        UIUtils.styleComboBox(userTypeComboBox);
        
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(UIUtils.REGULAR_FONT);
        usernameLabel.setBounds(100, 80, 80, 25);
        
        usernameField = new JTextField();
        usernameField.setBounds(180, 80, 150, 30);
        UIUtils.styleTextField(usernameField);
        
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(UIUtils.REGULAR_FONT);
        passwordLabel.setBounds(100, 130, 80, 25);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(180, 130, 150, 30);
        UIUtils.stylePasswordField(passwordField);
        
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(180, 190, 150, 40);
        UIUtils.stylePrimaryButton(loginButton);
        
        loginButton.addActionListener(e -> authenticateUser());
        
        loginPanel.add(userTypeLabel);
        loginPanel.add(userTypeComboBox);
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(loginPanel, BorderLayout.CENTER);
        
        // Make window slightly rounded
        setUndecorated(true);
        setShape(new RoundRectangle2D.Double(0, 0, 450, 350, 15, 15));
        
        add(mainPanel);
        setVisible(true);
    }
    
    private void authenticateUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String userType = (String) userTypeComboBox.getSelectedItem();
        
        // Simple authentication for demonstration
        if (userType.equals("Hotel Staff") && username.equals("admin") && password.equals("admin123")) {
            JOptionPane.showMessageDialog(this, "Staff login successful!");
            dispose(); // Close login window
            new StaffDashboard(); // Open staff dashboard
        } else if (userType.equals("Customer") && username.equals("user") && password.equals("user123")) {
            JOptionPane.showMessageDialog(this, "Customer login successful!");
            dispose(); // Close login window
            new CustomerDashboard(); // Open customer dashboard
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials!", "Login Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

class StaffDashboard extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JPanel cardsPanel;
    
    public StaffDashboard() {
        setTitle("Hotel Management System - Staff Dashboard");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(UIUtils.BACKGROUND_COLOR);
        
        // Create header panel with gradient
        JPanel headerPanel = UIUtils.createGradientPanel(UIUtils.PRIMARY_COLOR, UIUtils.SECONDARY_COLOR);
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(900, 70));
        
        JLabel titleLabel = new JLabel("  Staff Dashboard");
        titleLabel.setFont(UIUtils.TITLE_FONT);
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.WEST);
        
        JButton logoutButton = new JButton("Logout");
        UIUtils.styleSecondaryButton(logoutButton);
        logoutButton.addActionListener(e -> logout());
        JPanel logoutPanel = new JPanel();
        logoutPanel.setOpaque(false);
        logoutPanel.add(logoutButton);
        headerPanel.add(logoutPanel, BorderLayout.EAST);
        
        // Create sidebar panel
        JPanel sidebarPanel = UIUtils.createSidebarPanel();
        sidebarPanel.setPreferredSize(new Dimension(220, 650));
        
        // Add logo or icon at the top of sidebar
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(UIUtils.DARK_BACKGROUND);
        logoPanel.setMaximumSize(new Dimension(220, 80));
        logoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel logoLabel = new JLabel("HMS");
        logoLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        logoLabel.setForeground(Color.WHITE);
        logoPanel.add(logoLabel);
        sidebarPanel.add(logoPanel);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Add menu buttons
        String[] menuItems = {"Dashboard", "Room Management", "Bookings", "Check-in/Check-out", "Customers", "Billing", "Reports"};
        for (String menuItem : menuItems) {
            JButton menuButton = UIUtils.createSidebarButton("  " + menuItem);
            menuButton.setMaximumSize(new Dimension(220, 45));
            menuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            menuButton.addActionListener(e -> switchPanel(menuItem));
            
            sidebarPanel.add(menuButton);
            sidebarPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        }
        
        // Create cards panel for different sections
        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);
        
        // Add different panels for each section
        cardsPanel.add(createDashboardPanel(), "Dashboard");
        cardsPanel.add(createRoomManagementPanel(), "Room Management");
        cardsPanel.add(createBookingsPanel(), "Bookings");
        cardsPanel.add(createCheckInOutPanel(), "Check-in/Check-out");
        cardsPanel.add(createCustomersPanel(), "Customers");
        cardsPanel.add(createBillingPanel(), "Billing");
        cardsPanel.add(createReportsPanel(), "Reports");
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(sidebarPanel, BorderLayout.WEST);
        mainPanel.add(cardsPanel, BorderLayout.CENTER);
        
        add(mainPanel);
        setVisible(true);
    }
    
    private void switchPanel(String panelName) {
        cardLayout.show(cardsPanel, panelName);
    }
    
    private void logout() {
        dispose();
        new LoginScreen();
    }
    
    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(UIUtils.BACKGROUND_COLOR);
        
        JLabel overviewLabel = new JLabel("  Dashboard Overview");
        overviewLabel.setFont(UIUtils.SUBTITLE_FONT);
        overviewLabel.setForeground(UIUtils.TEXT_COLOR);
        overviewLabel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JPanel statsPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        statsPanel.setBackground(UIUtils.BACKGROUND_COLOR);
        
        // Add stat cards using UIUtils
        statsPanel.add(UIUtils.createStatCard("Available Rooms", "15", UIUtils.SUCCESS_COLOR));
        statsPanel.add(UIUtils.createStatCard("Occupied Rooms", "25", UIUtils.SECONDARY_COLOR));
        statsPanel.add(UIUtils.createStatCard("Reservations Today", "8", UIUtils.ACCENT_COLOR));
        statsPanel.add(UIUtils.createStatCard("Pending Checkouts", "5", UIUtils.WARNING_COLOR));
        
        // Add a welcome message panel
        JPanel welcomePanel = new JPanel(new BorderLayout());
        welcomePanel.setBackground(Color.WHITE);
        welcomePanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        JLabel welcomeLabel = new JLabel("Welcome back, Admin!");
        welcomeLabel.setFont(UIUtils.HEADING_FONT);
        welcomePanel.add(welcomeLabel, BorderLayout.WEST);
        
        JLabel dateLabel = new JLabel(new java.text.SimpleDateFormat("EEEE, MMMM d, yyyy").format(new java.util.Date()));
        dateLabel.setFont(UIUtils.REGULAR_FONT);
        dateLabel.setForeground(new Color(150, 150, 150));
        welcomePanel.add(dateLabel, BorderLayout.EAST);
        
        panel.add(welcomePanel, BorderLayout.NORTH);
        panel.add(statsPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createRoomManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(UIUtils.BACKGROUND_COLOR);
        
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        JLabel titleLabel = new JLabel("Room Management");
        titleLabel.setFont(UIUtils.SUBTITLE_FONT);
        titleLabel.setForeground(UIUtils.TEXT_COLOR);
        headerPanel.add(titleLabel, BorderLayout.WEST);
        
        // Create search field
        JTextField searchField = new JTextField(15);
        UIUtils.styleTextField(searchField);
        searchField.putClientProperty("JTextField.placeholderText", "Search rooms...");
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.setOpaque(false);
        searchPanel.add(searchField);
        
        // Add search button with functionality
        JButton searchButton = new JButton("Search");
        UIUtils.styleSecondaryButton(searchButton);
        searchButton.addActionListener(e -> {
            String searchTerm = searchField.getText().trim().toLowerCase();
            JOptionPane.showMessageDialog(null, "Searching for: " + searchTerm, "Search", JOptionPane.INFORMATION_MESSAGE);
            // In a real application, this would filter the table data
        });
        searchPanel.add(searchButton);
        headerPanel.add(searchPanel, BorderLayout.EAST);
        
        // Create table for room list
        String[] columnNames = {"Room No", "Type", "Price", "Status", "Actions"};
        Object[][] data = {
            {"101", "Standard", "$100", "Available", "Edit/Delete"},
            {"102", "Standard", "$100", "Occupied", "Edit/Delete"},
            {"201", "Deluxe", "$150", "Available", "Edit/Delete"},
            {"202", "Deluxe", "$150", "Maintenance", "Edit/Delete"},
            {"301", "Suite", "$250", "Available", "Edit/Delete"}
        };
        
        JTable table = new JTable(data, columnNames);
        UIUtils.styleTable(table);
        
        // Custom renderer for status column
        table.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                if (value != null) {
                    String status = value.toString();
                    if (status.equals("Available")) {
                        c.setForeground(UIUtils.SUCCESS_COLOR);
                    } else if (status.equals("Occupied")) {
                        c.setForeground(UIUtils.SECONDARY_COLOR);
                    } else if (status.equals("Maintenance")) {
                        c.setForeground(UIUtils.WARNING_COLOR);
                    }
                }
                
                return c;
            }
        });
        
        // Add action renderer for the Actions column
        table.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
                panel.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
                
                JButton editButton = new JButton("Edit");
                editButton.setFont(UIUtils.SMALL_FONT);
                editButton.setMargin(new Insets(2, 5, 2, 5));
                
                JButton deleteButton = new JButton("Delete");
                deleteButton.setFont(UIUtils.SMALL_FONT);
                deleteButton.setMargin(new Insets(2, 5, 2, 5));
                
                panel.add(editButton);
                panel.add(deleteButton);
                return panel;
            }
        });
        
        // Add mouse listener to handle button clicks in the table
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());
                
                if (col == 4) { // Actions column
                    // Get the room number from the first column
                    String roomNumber = table.getValueAt(row, 0).toString();
                    String roomType = table.getValueAt(row, 1).toString();
                    String roomPrice = table.getValueAt(row, 2).toString();
                    String roomStatus = table.getValueAt(row, 3).toString();
                    
                    // Calculate which button was clicked (Edit or Delete)
                    int buttonWidth = 50; // Approximate width of each button
                    int x = e.getX() - table.getCellRect(row, col, false).x;
                    
                    if (x < buttonWidth) {
                        // Edit button clicked
                        showRoomEditDialog(roomNumber, roomType, roomPrice, roomStatus);
                    } else {
                        // Delete button clicked
                        int confirm = JOptionPane.showConfirmDialog(
                            null,
                            "Are you sure you want to delete Room " + roomNumber + "?",
                            "Confirm Deletion",
                            JOptionPane.YES_NO_OPTION
                        );
                        
                        if (confirm == JOptionPane.YES_OPTION) {
                            JOptionPane.showMessageDialog(null, "Room " + roomNumber + " has been deleted.");
                            // Here you would actually remove the room from the database
                        }
                    }
                }
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(table);
        UIUtils.styleScrollPane(scrollPane);
        
        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JButton addButton = new JButton("Add New Room");
        UIUtils.stylePrimaryButton(addButton);
        addButton.addActionListener(e -> showAddRoomDialog());
        buttonPanel.add(addButton);
        
        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    // Dialog to edit a room
    private void showRoomEditDialog(String roomNumber, String roomType, String roomPrice, String roomStatus) {
        JDialog dialog = new JDialog(this, "Edit Room", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Room number (disabled as it's the primary key)
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Room Number:"), gbc);
        
        gbc.gridx = 1;
        JTextField roomNumberField = new JTextField(roomNumber);
        roomNumberField.setEditable(false);
        panel.add(roomNumberField, gbc);
        
        // Room type
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Room Type:"), gbc);
        
        gbc.gridx = 1;
        String[] types = {"Standard", "Deluxe", "Suite"};
        JComboBox<String> typeCombo = new JComboBox<>(types);
        typeCombo.setSelectedItem(roomType);
        panel.add(typeCombo, gbc);
        
        // Room price
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Price:"), gbc);
        
        gbc.gridx = 1;
        JTextField priceField = new JTextField(roomPrice.replace("$", ""));
        panel.add(priceField, gbc);
        
        // Room status
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Status:"), gbc);
        
        gbc.gridx = 1;
        String[] statuses = {"Available", "Occupied", "Maintenance"};
        JComboBox<String> statusCombo = new JComboBox<>(statuses);
        statusCombo.setSelectedItem(roomStatus);
        panel.add(statusCombo, gbc);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("Save");
        UIUtils.stylePrimaryButton(saveButton);
        saveButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(dialog, "Room " + roomNumber + " updated successfully!");
            dialog.dispose();
        });
        
        JButton cancelButton = new JButton("Cancel");
        UIUtils.styleSecondaryButton(cancelButton);
        cancelButton.addActionListener(e -> dialog.dispose());
        
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        
        dialog.setLayout(new BorderLayout());
        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
    
    // Dialog to add a new room
    private void showAddRoomDialog() {
        JDialog dialog = new JDialog(this, "Add New Room", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Room number
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Room Number:"), gbc);
        
        gbc.gridx = 1;
        JTextField roomNumberField = new JTextField();
        panel.add(roomNumberField, gbc);
        
        // Room type
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Room Type:"), gbc);
        
        gbc.gridx = 1;
        String[] types = {"Standard", "Deluxe", "Suite"};
        JComboBox<String> typeCombo = new JComboBox<>(types);
        panel.add(typeCombo, gbc);
        
        // Room price
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Price:"), gbc);
        
        gbc.gridx = 1;
        JTextField priceField = new JTextField();
        panel.add(priceField, gbc);
        
        // Room status
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Status:"), gbc);
        
        gbc.gridx = 1;
        String[] statuses = {"Available", "Occupied", "Maintenance"};
        JComboBox<String> statusCombo = new JComboBox<>(statuses);
        panel.add(statusCombo, gbc);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("Save");
        UIUtils.stylePrimaryButton(saveButton);
        saveButton.addActionListener(e -> {
            String roomNumber = roomNumberField.getText();
            if (roomNumber.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Room number cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(dialog, "Room " + roomNumber + " added successfully!");
            dialog.dispose();
        });
        
        JButton cancelButton = new JButton("Cancel");
        UIUtils.styleSecondaryButton(cancelButton);
        cancelButton.addActionListener(e -> dialog.dispose());
        
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        
        dialog.setLayout(new BorderLayout());
        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
    
    private JPanel createBookingsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel("  Bookings Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(titleLabel, BorderLayout.NORTH);
        
        // Create table for bookings
        String[] columnNames = {"Booking ID", "Customer", "Room", "Check-in", "Check-out", "Status", "Actions"};
        Object[][] data = {
            {"B001", "John Doe", "101", "2023-06-10", "2023-06-15", "Confirmed", "View/Edit"},
            {"B002", "Jane Smith", "202", "2023-06-12", "2023-06-14", "Checked-in", "View/Edit"},
            {"B003", "Mike Johnson", "301", "2023-06-15", "2023-06-20", "Pending", "View/Edit"}
        };
        
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        
        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton addButton = new JButton("New Booking");
        buttonPanel.add(addButton);
        
        panel.add(buttonPanel, BorderLayout.SOUTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createCheckInOutPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel("  Check-in / Check-out Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(titleLabel, BorderLayout.NORTH);
        
        // Create tabs for check-in and check-out
        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Check-in panel
        JPanel checkInPanel = new JPanel(new BorderLayout());
        String[] checkInColumns = {"Booking ID", "Customer", "Room", "Check-in Date", "Status", "Actions"};
        Object[][] checkInData = {
            {"B001", "John Doe", "101", "2023-06-10", "Confirmed", "Check-in"},
            {"B003", "Mike Johnson", "301", "2023-06-15", "Pending", "Check-in"}
        };
        JTable checkInTable = new JTable(checkInData, checkInColumns);
        checkInPanel.add(new JScrollPane(checkInTable));
        
        // Check-out panel
        JPanel checkOutPanel = new JPanel(new BorderLayout());
        String[] checkOutColumns = {"Booking ID", "Customer", "Room", "Check-out Date", "Status", "Actions"};
        Object[][] checkOutData = {
            {"B002", "Jane Smith", "202", "2023-06-14", "Checked-in", "Check-out"}
        };
        JTable checkOutTable = new JTable(checkOutData, checkOutColumns);
        checkOutPanel.add(new JScrollPane(checkOutTable));
        
        tabbedPane.addTab("Check-in", checkInPanel);
        tabbedPane.addTab("Check-out", checkOutPanel);
        
        panel.add(tabbedPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createCustomersPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel("  Customer Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(titleLabel, BorderLayout.NORTH);
        
        // Create table for customers
        String[] columnNames = {"ID", "Name", "Email", "Phone", "Address", "Actions"};
        Object[][] data = {
            {"C001", "John Doe", "john@example.com", "555-1234", "123 Main St", "View/Edit"},
            {"C002", "Jane Smith", "jane@example.com", "555-5678", "456 Oak Ave", "View/Edit"},
            {"C003", "Mike Johnson", "mike@example.com", "555-9012", "789 Pine Rd", "View/Edit"}
        };
        
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        
        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton addButton = new JButton("Add New Customer");
        buttonPanel.add(addButton);
        
        panel.add(buttonPanel, BorderLayout.SOUTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createBillingPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel("  Billing and Invoices");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(titleLabel, BorderLayout.NORTH);
        
        // Create table for invoices
        String[] columnNames = {"Invoice ID", "Customer", "Room", "Amount", "Status", "Date", "Actions"};
        Object[][] data = {
            {"INV001", "John Doe", "101", "$500", "Paid", "2023-06-15", "View/Print"},
            {"INV002", "Jane Smith", "202", "$300", "Pending", "2023-06-14", "View/Print"},
            {"INV003", "Mike Johnson", "301", "$1250", "Paid", "2023-06-20", "View/Print"}
        };
        
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        
        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton addButton = new JButton("Create New Invoice");
        buttonPanel.add(addButton);
        
        panel.add(buttonPanel, BorderLayout.SOUTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createReportsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel("  Reports");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(titleLabel, BorderLayout.NORTH);
        
        // Create reports options
        JPanel reportsPanel = new JPanel(new GridLayout(3, 2, 20, 20));
        reportsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        reportsPanel.setBackground(Color.WHITE);
        
        String[] reportTypes = {
            "Occupancy Report", "Revenue Report", "Booking Statistics", 
            "Customer Statistics", "Inventory Report", "Staff Performance"
        };
        
        for (String reportType : reportTypes) {
            JPanel reportCard = new JPanel(new BorderLayout());
            reportCard.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            reportCard.setBackground(Color.WHITE);
            
            JLabel reportLabel = new JLabel(reportType, JLabel.CENTER);
            reportLabel.setFont(new Font("Arial", Font.BOLD, 14));
            
            JButton generateButton = new JButton("Generate");
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(generateButton);
            buttonPanel.setBackground(Color.WHITE);
            
            reportCard.add(reportLabel, BorderLayout.CENTER);
            reportCard.add(buttonPanel, BorderLayout.SOUTH);
            
            reportsPanel.add(reportCard);
        }
        
        panel.add(reportsPanel, BorderLayout.CENTER);
        
        return panel;
    }
}

class CustomerDashboard extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JPanel cardsPanel;
    
    public CustomerDashboard() {
        setTitle("Hotel Management System - Customer Dashboard");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(UIUtils.BACKGROUND_COLOR);
        
        // Create header panel with gradient
        JPanel headerPanel = UIUtils.createGradientPanel(UIUtils.ACCENT_COLOR, UIUtils.SECONDARY_COLOR);
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(900, 70));
        
        JLabel titleLabel = new JLabel("  Customer Dashboard");
        titleLabel.setFont(UIUtils.TITLE_FONT);
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.WEST);
        
        JButton logoutButton = new JButton("Logout");
        UIUtils.styleSecondaryButton(logoutButton);
        logoutButton.addActionListener(e -> logout());
        JPanel logoutPanel = new JPanel();
        logoutPanel.setOpaque(false);
        logoutPanel.add(logoutButton);
        headerPanel.add(logoutPanel, BorderLayout.EAST);
        
        // Create sidebar panel
        JPanel sidebarPanel = UIUtils.createSidebarPanel();
        sidebarPanel.setPreferredSize(new Dimension(220, 650));
        
        // Add logo or icon at the top of sidebar
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(UIUtils.DARK_BACKGROUND);
        logoPanel.setMaximumSize(new Dimension(220, 80));
        logoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel logoLabel = new JLabel("HMS");
        logoLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        logoLabel.setForeground(Color.WHITE);
        logoPanel.add(logoLabel);
        sidebarPanel.add(logoPanel);
        sidebarPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Add menu buttons
        String[] menuItems = {"Dashboard", "Browse Rooms", "My Bookings", "My Profile", "Feedback"};
        for (String menuItem : menuItems) {
            JButton menuButton = UIUtils.createSidebarButton("  " + menuItem);
            menuButton.setMaximumSize(new Dimension(220, 45));
            menuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            menuButton.addActionListener(e -> switchPanel(menuItem));
            
            sidebarPanel.add(menuButton);
            sidebarPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        }
        
        // Create cards panel for different sections
        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);
        
        // Add different panels for each section
        cardsPanel.add(createCustomerDashboardPanel(), "Dashboard");
        cardsPanel.add(createBrowseRoomsPanel(), "Browse Rooms");
        cardsPanel.add(createMyBookingsPanel(), "My Bookings");
        cardsPanel.add(createMyProfilePanel(), "My Profile");
        cardsPanel.add(createFeedbackPanel(), "Feedback");
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(sidebarPanel, BorderLayout.WEST);
        mainPanel.add(cardsPanel, BorderLayout.CENTER);
        
        add(mainPanel);
        setVisible(true);
    }
    
    private void switchPanel(String panelName) {
        cardLayout.show(cardsPanel, panelName);
    }
    
    private void logout() {
        dispose();
        new LoginScreen();
    }
    
    private JPanel createCustomerDashboardPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        
        JLabel welcomeLabel = new JLabel("  Welcome, User!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(welcomeLabel, BorderLayout.NORTH);
        
        // Create dashboard content
        JPanel contentPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(Color.WHITE);
        
        // Add quick action cards
        contentPanel.add(createActionCard("Book a Room", "Find and book your perfect room", new Color(46, 204, 113)));
        contentPanel.add(createActionCard("Upcoming Stay", "Check-in: June 15, 2023\nRoom: 301 (Suite)", new Color(52, 152, 219)));
        contentPanel.add(createActionCard("Special Offers", "Summer discount: 15% off on all bookings", new Color(155, 89, 182)));
        contentPanel.add(createActionCard("Hotel Services", "Explore our premium services", new Color(230, 126, 34)));
        
        panel.add(contentPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createActionCard(String title, String description, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(color);
        card.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        JLabel descLabel = new JLabel("<html>" + description.replace("\n", "<br>") + "</html>");
        descLabel.setForeground(Color.WHITE);
        
        JButton actionButton = new JButton("View");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(color);
        buttonPanel.add(actionButton);
        
        card.add(titleLabel, BorderLayout.NORTH);
        card.add(descLabel, BorderLayout.CENTER);
        card.add(buttonPanel, BorderLayout.SOUTH);
        
        return card;
    }
    
    private JPanel createBrowseRoomsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(UIUtils.BACKGROUND_COLOR);
        
        // Create header panel with gradient
        JPanel headerPanel = UIUtils.createHeaderPanel("Browse Available Rooms");
        headerPanel.setPreferredSize(new Dimension(900, 60));
        
        // Create search panel
        JPanel searchPanel = UIUtils.createRoundedPanel(Color.WHITE);
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JLabel checkInLabel = new JLabel("Check-in:");
        checkInLabel.setFont(UIUtils.REGULAR_FONT);
        JTextField checkInField = new JTextField(10);
        UIUtils.styleTextField(checkInField);
        
        JLabel checkOutLabel = new JLabel("Check-out:");
        checkOutLabel.setFont(UIUtils.REGULAR_FONT);
        JTextField checkOutField = new JTextField(10);
        UIUtils.styleTextField(checkOutField);
        
        JLabel roomTypeLabel = new JLabel("Room Type:");
        roomTypeLabel.setFont(UIUtils.REGULAR_FONT);
        String[] roomTypes = {"Any", "Standard", "Deluxe", "Suite"};
        JComboBox<String> roomTypeCombo = new JComboBox<>(roomTypes);
        UIUtils.styleComboBox(roomTypeCombo);
        
        JButton searchButton = new JButton("Search");
        UIUtils.stylePrimaryButton(searchButton);
        
        searchPanel.add(checkInLabel);
        searchPanel.add(checkInField);
        searchPanel.add(checkOutLabel);
        searchPanel.add(checkOutField);
        searchPanel.add(roomTypeLabel);
        searchPanel.add(roomTypeCombo);
        searchPanel.add(searchButton);
        
        // Create rooms display panel
        JPanel roomsPanel = new JPanel(new GridLayout(0, 3, 20, 20));
        roomsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        roomsPanel.setBackground(UIUtils.BACKGROUND_COLOR);
        
        // Add room cards with different accent colors
        roomsPanel.add(UIUtils.createModernRoomCard("101", "Standard Room", "$100/night", "Single bed, TV, AC, Free WiFi", UIUtils.PRIMARY_COLOR));
        roomsPanel.add(UIUtils.createModernRoomCard("201", "Deluxe Room", "$150/night", "Queen bed, TV, AC, Mini bar, Free WiFi", UIUtils.SECONDARY_COLOR));
        roomsPanel.add(UIUtils.createModernRoomCard("301", "Suite", "$250/night", "King bed, Separate living area, Jacuzzi, Mini bar, Free WiFi", UIUtils.ACCENT_COLOR));
        roomsPanel.add(UIUtils.createModernRoomCard("102", "Standard Room", "$100/night", "Single bed, TV, AC, Free WiFi", UIUtils.PRIMARY_COLOR));
        roomsPanel.add(UIUtils.createModernRoomCard("202", "Deluxe Room", "$150/night", "Queen bed, TV, AC, Mini bar, Free WiFi", UIUtils.SECONDARY_COLOR));
        
        JScrollPane scrollPane = new JScrollPane(roomsPanel);
        UIUtils.styleScrollPane(scrollPane);
        
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(UIUtils.BACKGROUND_COLOR);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.add(searchPanel, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        
        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(contentPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    // This method is replaced by UIUtils.createModernRoomCard
    private JPanel createRoomCard(String roomNumber, String roomType, String price, String description) {
        // Using the new UIUtils method instead
        return UIUtils.createModernRoomCard(roomNumber, roomType, price, description, UIUtils.PRIMARY_COLOR);
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        card.setBackground(Color.WHITE);
        
        JLabel roomLabel = new JLabel("Room " + roomNumber + " - " + roomType);
        roomLabel.setFont(new Font("Arial", Font.BOLD, 14));
        roomLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        
        JLabel priceLabel = new JLabel(price);
        priceLabel.setFont(new Font("Arial", Font.BOLD, 12));
        priceLabel.setForeground(new Color(46, 134, 193));
        priceLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 10));
        
        JLabel descLabel = new JLabel("<html>" + description + "</html>");
        descLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        
        JButton bookButton = new JButton("Book Now");
        bookButton.setBackground(new Color(46, 134, 193));
        bookButton.setForeground(Color.WHITE);
        
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
        panel.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel("  My Bookings");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(titleLabel, BorderLayout.NORTH);
        
        // Create table for bookings
        String[] columnNames = {"Booking ID", "Room", "Check-in", "Check-out", "Status", "Actions"};
        Object[][] data = {
            {"B003", "301", "2023-06-15", "2023-06-20", "Confirmed", "View/Cancel"},
            {"B005", "202", "2023-07-10", "2023-07-15", "Pending", "View/Cancel"}
        };
        
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createMyProfilePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel("  My Profile");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(titleLabel, BorderLayout.NORTH);
        
        // Create profile form
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Add form fields
        String[] labels = {"Full Name:", "Email:", "Phone:", "Address:", "Password:"};
        String[] values = {"John Doe", "john@example.com", "555-1234", "123 Main St", "********"};
        
        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.gridwidth = 1;
            formPanel.add(new JLabel(labels[i]), gbc);
            
            gbc.gridx = 1;
            gbc.gridwidth = 2;
            JTextField field = new JTextField(values[i]);
            formPanel.add(field, gbc);
        }
        
        // Add update button
        gbc.gridx = 1;
        gbc.gridy = labels.length;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        JButton updateButton = new JButton("Update Profile");
        formPanel.add(updateButton, gbc);
        
        panel.add(formPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createFeedbackPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel("  Provide Feedback");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(titleLabel, BorderLayout.NORTH);
        
        // Create feedback form
        JPanel formPanel = new JPanel(new BorderLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel ratingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ratingPanel.setBackground(Color.WHITE);
        ratingPanel.add(new JLabel("Rating: "));
        String[] ratings = {"5 - Excellent", "4 - Good", "3 - Average", "2 - Poor", "1 - Very Poor"};
        ratingPanel.add(new JComboBox<>(ratings));
        
        JPanel commentPanel = new JPanel(new BorderLayout());
        commentPanel.setBackground(Color.WHITE);
        commentPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        commentPanel.add(new JLabel("Comments:"), BorderLayout.NORTH);
        JTextArea commentArea = new JTextArea(10, 30);
        commentArea.setLineWrap(true);
        commentArea.setWrapStyleWord(true);
        commentPanel.add(new JScrollPane(commentArea), BorderLayout.CENTER);
        
        JButton submitButton = new JButton("Submit Feedback");
        submitButton.setBackground(new Color(46, 134, 193));
        submitButton.setForeground(Color.WHITE);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(submitButton);
        
        formPanel.add(ratingPanel, BorderLayout.NORTH);
        formPanel.add(commentPanel, BorderLayout.CENTER);
        formPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        panel.add(formPanel, BorderLayout.CENTER);
        
        return panel;
    }
}