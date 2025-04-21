import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 * Utility class for custom UI styling to make the interface more beautiful and stylish.
 */
public class UIUtils {
    // Modern color palette
    public static final Color PRIMARY_COLOR = new Color(41, 128, 185);    // Blue
    public static final Color SECONDARY_COLOR = new Color(52, 152, 219); // Lighter blue
    public static final Color ACCENT_COLOR = new Color(26, 188, 156);    // Teal
    public static final Color WARNING_COLOR = new Color(243, 156, 18);   // Orange
    public static final Color DANGER_COLOR = new Color(231, 76, 60);     // Red
    public static final Color SUCCESS_COLOR = new Color(46, 204, 113);   // Green
    public static final Color BACKGROUND_COLOR = new Color(245, 247, 250); // Light gray
    public static final Color DARK_BACKGROUND = new Color(52, 73, 94);   // Dark blue-gray
    public static final Color TEXT_COLOR = new Color(44, 62, 80);        // Dark blue-gray
    public static final Color LIGHT_TEXT = new Color(236, 240, 241);     // Off-white
    
    // Fonts
    public static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 22);
    public static final Font SUBTITLE_FONT = new Font("Segoe UI", Font.BOLD, 18);
    public static final Font HEADING_FONT = new Font("Segoe UI", Font.BOLD, 16);
    public static final Font REGULAR_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font SMALL_FONT = new Font("Segoe UI", Font.PLAIN, 12);
    
    // Border radius
    public static final int BORDER_RADIUS = 10;
    
    /**
     * Styles a button with modern look and feel
     */
    public static void styleButton(JButton button, Color bgColor, Color textColor) {
        button.setBackground(bgColor);
        button.setForeground(textColor);
        button.setFont(REGULAR_FONT);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }
            
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
            
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker().darker());
            }
            
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }
        });
    }
    
    /**
     * Styles a primary action button
     */
    public static void stylePrimaryButton(JButton button) {
        styleButton(button, PRIMARY_COLOR, Color.WHITE);
    }
    
    /**
     * Styles a secondary action button
     */
    public static void styleSecondaryButton(JButton button) {
        styleButton(button, SECONDARY_COLOR, Color.WHITE);
    }
    
    /**
     * Styles a danger/warning button
     */
    public static void styleDangerButton(JButton button) {
        styleButton(button, DANGER_COLOR, Color.WHITE);
    }
    
    /**
     * Styles a success button
     */
    public static void styleSuccessButton(JButton button) {
        styleButton(button, SUCCESS_COLOR, Color.WHITE);
    }
    
    /**
     * Styles a text field with modern look
     */
    public static void styleTextField(JTextField textField) {
        textField.setFont(REGULAR_FONT);
        textField.setBackground(Color.WHITE);
        textField.setForeground(TEXT_COLOR);
        textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 2, 0, PRIMARY_COLOR),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)));
        
        // Add focus effect
        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 0, 2, 0, ACCENT_COLOR),
                    BorderFactory.createEmptyBorder(8, 10, 8, 10)));
            }
            
            public void focusLost(java.awt.event.FocusEvent evt) {
                textField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 0, 2, 0, PRIMARY_COLOR),
                    BorderFactory.createEmptyBorder(8, 10, 8, 10)));
            }
        });
    }
    
    /**
     * Styles a password field with modern look
     */
    public static void stylePasswordField(JPasswordField passwordField) {
        styleTextField(passwordField);
    }
    
    /**
     * Styles a combo box with modern look
     */
    public static void styleComboBox(JComboBox<?> comboBox) {
        comboBox.setFont(REGULAR_FONT);
        comboBox.setBackground(Color.WHITE);
        comboBox.setForeground(TEXT_COLOR);
        comboBox.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 2, 0, PRIMARY_COLOR),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        
        // Style the dropdown
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (isSelected) {
                    c.setBackground(PRIMARY_COLOR);
                    c.setForeground(Color.WHITE);
                } else {
                    c.setBackground(Color.WHITE);
                    c.setForeground(TEXT_COLOR);
                }
                setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                return c;
            }
        });
    }
    
    /**
     * Creates a panel with a gradient background
     */
    public static JPanel createGradientPanel(Color color1, Color color2) {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int w = getWidth();
                int h = getHeight();
                GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
    }
    
    /**
     * Creates a header panel with gradient background
     */
    public static JPanel createHeaderPanel(String title) {
        JPanel headerPanel = createGradientPanel(PRIMARY_COLOR, SECONDARY_COLOR);
        headerPanel.setLayout(new BorderLayout());
        
        JLabel titleLabel = new JLabel("  " + title);
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.WEST);
        
        return headerPanel;
    }
    
    /**
     * Creates a styled sidebar panel
     */
    public static JPanel createSidebarPanel() {
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(DARK_BACKGROUND);
        return sidebarPanel;
    }
    
    /**
     * Styles a sidebar menu button
     */
    public static JButton createSidebarButton(String text) {
        JButton button = new JButton(text);
        button.setFont(REGULAR_FONT);
        button.setForeground(LIGHT_TEXT);
        button.setBackground(DARK_BACKGROUND);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(44, 62, 80));
                button.setForeground(Color.WHITE);
                button.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, ACCENT_COLOR));
            }
            
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(DARK_BACKGROUND);
                button.setForeground(LIGHT_TEXT);
                button.setBorder(null);
            }
        });
        
        return button;
    }
    
    /**
     * Creates a card panel for dashboard statistics
     */
    public static JPanel createStatCard(String title, String value, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setFont(REGULAR_FONT);
        
        JLabel valueLabel = new JLabel(value);
        valueLabel.setForeground(color);
        valueLabel.setFont(TITLE_FONT);
        
        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);
        
        // Add shadow effect
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 5, 0, 0, color),
            BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15),
                BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
                    BorderFactory.createEmptyBorder(0, 0, 0, 0)
                )
            )
        ));
        
        // Add hover effect
        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 5, 0, 0, color.darker()),
                    BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(15, 15, 15, 15),
                        BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                            BorderFactory.createEmptyBorder(0, 0, 0, 0)
                        )
                    )
                ));
            }
            
            public void mouseExited(java.awt.event.MouseEvent evt) {
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 5, 0, 0, color),
                    BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(15, 15, 15, 15),
                        BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
                            BorderFactory.createEmptyBorder(0, 0, 0, 0)
                        )
                    )
                ));
            }
        });
        
        return card;
    }
    
    /**
     * Styles a table with modern look
     */
    public static void styleTable(JTable table) {
        table.setFont(REGULAR_FONT);
        table.setRowHeight(35);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setSelectionBackground(PRIMARY_COLOR.brighter());
        table.setSelectionForeground(Color.WHITE);
        
        // Style header
        table.getTableHeader().setFont(HEADING_FONT);
        table.getTableHeader().setBackground(PRIMARY_COLOR);
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBorder(BorderFactory.createEmptyBorder());
        table.getTableHeader().setPreferredSize(new Dimension(0, 40));
        
        // Zebra striping
        table.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                if (isSelected) {
                    c.setBackground(PRIMARY_COLOR.brighter());
                    c.setForeground(Color.WHITE);
                } else {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(245, 245, 245));
                    c.setForeground(TEXT_COLOR);
                }
                
                setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
                return c;
            }
        });
    }
    
    /**
     * Styles a scroll pane with modern look
     */
    public static void styleScrollPane(JScrollPane scrollPane) {
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(Color.WHITE);
        
        // Style the scrollbars
        scrollPane.getVerticalScrollBar().setUI(new ModernScrollBarUI());
        scrollPane.getHorizontalScrollBar().setUI(new ModernScrollBarUI());
    }
    
    /**
     * Creates a rounded panel with shadow effect
     */
    public static JPanel createRoundedPanel(Color bgColor) {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(bgColor);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), BORDER_RADIUS, BORDER_RADIUS);
                g2.dispose();
            }
        };
        panel.setOpaque(false);
        return panel;
    }
    
    /**
     * Creates a modern room card for room browsing
     */
    public static JPanel createModernRoomCard(String roomNumber, String roomType, String price, String description, Color accentColor) {
        JPanel card = createRoundedPanel(Color.WHITE);
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Room type header with accent color
        JPanel headerPanel = createRoundedPanel(accentColor);
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        
        JLabel roomTypeLabel = new JLabel(roomType);
        roomTypeLabel.setFont(HEADING_FONT);
        roomTypeLabel.setForeground(Color.WHITE);
        headerPanel.add(roomTypeLabel, BorderLayout.WEST);
        
        JLabel roomNumberLabel = new JLabel("Room " + roomNumber);
        roomNumberLabel.setFont(REGULAR_FONT);
        roomNumberLabel.setForeground(Color.WHITE);
        headerPanel.add(roomNumberLabel, BorderLayout.EAST);
        
        // Content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        
        JLabel priceLabel = new JLabel(price);
        priceLabel.setFont(SUBTITLE_FONT);
        priceLabel.setForeground(accentColor);
        priceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel descLabel = new JLabel("<html>" + description + "</html>");
        descLabel.setFont(REGULAR_FONT);
        descLabel.setForeground(TEXT_COLOR);
        descLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        descLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        contentPanel.add(priceLabel);
        contentPanel.add(descLabel);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);
        
        JButton bookButton = new JButton("Book Now");
        styleButton(bookButton, accentColor, Color.WHITE);
        buttonPanel.add(bookButton);
        
        // Add all components to card
        card.add(headerPanel, BorderLayout.NORTH);
        card.add(contentPanel, BorderLayout.CENTER);
        card.add(buttonPanel, BorderLayout.SOUTH);
        
        // Add shadow and hover effects
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(5, 5, 5, 5),
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
            )
        ));
        
        return card;
    }
    
    /**
     * Custom modern scroll bar UI
     */
    private static class ModernScrollBarUI extends BasicScrollBarUI {
        private final int THUMB_SIZE = 8;
        
        @Override
        protected JButton createDecreaseButton(int orientation) {
            return createZeroButton();
        }
        
        @Override
        protected JButton createIncreaseButton(int orientation) {
            return createZeroButton();
        }
        
        private JButton createZeroButton() {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(0, 0));
            button.setMinimumSize(new Dimension(0, 0));
            button.setMaximumSize(new Dimension(0, 0));
            return button;
        }
        
        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
            g.setColor(new Color(240, 240, 240));
            g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
        }
        
        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
            if (thumbBounds.isEmpty() || !scrollbar.isEnabled()) {
                return;
            }
            
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            // Draw rounded thumb
            g2.setColor(scrollbar.isEnabled() ? new Color(180, 180, 180) : new Color(220, 220, 220));
            
            if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
                int newWidth = THUMB_SIZE;
                int newX = thumbBounds.x + (thumbBounds.width - newWidth) / 2;
                g2.fillRoundRect(newX, thumbBounds.y, newWidth, thumbBounds.height, 5, 5);
            } else {
                int newHeight = THUMB_SIZE;
                int newY = thumbBounds.y + (thumbBounds.height - newHeight) / 2;
                g2.fillRoundRect(thumbBounds.x, newY, thumbBounds.width, newHeight, 5, 5);
            }
            
            g2.dispose();
        }
        
        @Override
        protected void setThumbBounds(int x, int y, int width, int height) {
            super.setThumbBounds(x, y, width, height);
            scrollbar.repaint();
        }
    }
}