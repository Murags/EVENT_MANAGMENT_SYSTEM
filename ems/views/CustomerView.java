package ems.views;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import ems.Models.Customer;
import ems.Models.Event;
import ems.Controller.BookingsController;
import ems.Controller.EventsController;

public class CustomerView extends JFrame {

    private Customer customer;
    private JButton  eventsButton, bookingsButton, profileButton, settingsButton;
    private JPanel contentArea;

    public CustomerView(Customer customer) {
        this.customer = customer;
        // Set up the main frame
        setTitle("Event Management Dashboard");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Add top bar
        JPanel topBar = createTopBar();
        add(topBar, BorderLayout.NORTH);

        // Add sidebar
        JPanel sidebar = createSidebar();
        add(sidebar, BorderLayout.WEST);

        // Add main content area
        contentArea = createContentArea();
        add(new JScrollPane(contentArea), BorderLayout.CENTER);
    }

    private JPanel createTopBar() {
        JPanel topBar = new JPanel();
        topBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        topBar.setBackground(new Color(45, 45, 45));

        JLabel title = new JLabel("Dashboard");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Verdana", Font.BOLD, 24));

        topBar.add(title);

        return topBar;
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(33, 33, 33));
        sidebar.setPreferredSize(new Dimension(200, getHeight()));

        //homeButton = createSidebarButton("Home");
        eventsButton = createSidebarButton("Events");
        bookingsButton = createSidebarButton("Bookings");
        profileButton = createSidebarButton("Profile");
        settingsButton = createSidebarButton("Settings");

        //sidebar.add(Box.createVerticalStrut(20));
        //sidebar.add(homeButton);
        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(eventsButton);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(bookingsButton);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(profileButton);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(settingsButton);
        // Set "Home" as the active button
        setActiveButton(eventsButton);

        return sidebar;
    }

    private JButton createSidebarButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(180, 40));
        button.setFont(new Font("Verdana", Font.PLAIN, 16));
        button.setBackground(new Color(55, 55, 55));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Add rounded corners
        button.setBorder(new CompoundBorder(
            new LineBorder(new Color(33, 33, 33), 2, true),
            new EmptyBorder(5, 15, 5, 15)
        ));

        // Add mouse listener to change active button
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setActiveButton(button);
            }
        });

        // Add action listener to display the corresponding panel
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayPanel(text);
            }
        });

        return button;
    }

    private void setActiveButton(JButton activeButton) {
        JButton[] buttons = {eventsButton, bookingsButton, profileButton, settingsButton};

        for (JButton button : buttons) {
            if (button == activeButton) {
                button.setBackground(new Color(77, 77, 77));
            } else {
                button.setBackground(new Color(55, 55, 55));
            }
        }
    }

    private void displayPanel(String panelName) {
        contentArea.removeAll();
        switch (panelName) {
            case "Events":
                List<Event> events = EventsController.allEvents();
                for (Event event : events) {
                    contentArea.add(createEventCard(event));
                }
                break;
            case "Settings":
                contentArea.add(createSettingsPanel());
                break;
            // Add cases for other buttons if needed
        }
        contentArea.revalidate();
        contentArea.repaint();
    }

    private JPanel createContentArea() {
        JPanel contentArea = new JPanel();
        contentArea.setLayout(new GridLayout(0, 3, 20, 20)); // 3 columns with 20px gaps
        contentArea.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentArea.setBackground(new Color(240, 240, 240));
        return contentArea;
    }

    private JPanel createSettingsPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Labels and text fields for customer information
        JLabel nameLabel = new JLabel("Name:");
        gbc.gridx = 3;
        gbc.gridy = 0;
        panel.add(nameLabel, gbc);

        JTextField nameField = new JTextField(customer.getFirstName(), 20);
        gbc.gridx = 5;
        panel.add(nameField, gbc);

        JLabel emailLabel = new JLabel("Email:");
        gbc.gridx = 3;
        gbc.gridy = 1;
        panel.add(emailLabel, gbc);

        JTextField emailField = new JTextField(customer.getEmail(), 20);
        gbc.gridx = 5;
        panel.add(emailField, gbc);

        JLabel passLabel = new JLabel("Password:");
        gbc.gridx = 3;
        gbc.gridy = 2;
        panel.add(passLabel, gbc);

        JTextField passField = new JTextField(customer.getPassword(), 20);
        gbc.gridx = 5;
        panel.add(passField, gbc);

        // Update button
        JButton updateButton = new JButton("Update");
        gbc.gridx = 4;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        updateButton.setMaximumSize(new Dimension(180, 40));
        updateButton.setFont(new Font("Verdana", Font.PLAIN, 16));
        updateButton.setBackground(new Color(55, 55, 55));
        updateButton.setForeground(Color.WHITE);
        panel.add(updateButton, gbc);

        // Delete button
        JButton deleteButton = new JButton("Delete");
        gbc.gridx = 6;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        deleteButton.setMaximumSize(new Dimension(180, 40));
        deleteButton.setFont(new Font("Verdana", Font.PLAIN, 16));
        deleteButton.setBackground(new Color(55, 55, 55));
        deleteButton.setForeground(Color.WHITE);
        panel.add(deleteButton, gbc);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the organizer's details
                String newName = nameField.getText();
                String newEmail = emailField.getText();
                String newPassword = passField.getText();
                customer.updateCustomer(newName , newEmail , newPassword , CustomerView.this);
            
            }
        });
    
        // Action listener for delete button
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform the delete operation
                customer.deleteCustomer(CustomerView.this);
            }
        });
    
        return panel;
    }



    private JPanel createEventCard(Event event) {
        JPanel eventCard = new JPanel();
        eventCard.setLayout(new BoxLayout(eventCard, BoxLayout.Y_AXIS));
        eventCard.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        eventCard.setBackground(Color.WHITE);

        // Image
        JLabel imageLabel = new JLabel();
        if (event.getImage() != null) {
            ImageIcon imageIcon = new ImageIcon(event.getImage());
            Image image = imageIcon.getImage().getScaledInstance(200, 150, Image.SCALE_DEFAULT);
            imageIcon = new ImageIcon(image);
            imageLabel.setIcon(imageIcon);
        }
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        eventCard.add(imageLabel);

        // Title
        JLabel titleLabel = new JLabel(event.getTitle());
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        eventCard.add(titleLabel);

        // Description
        JTextArea descriptionArea = new JTextArea(event.getDescription());
        descriptionArea.setEditable(false);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);
        descriptionScrollPane.setPreferredSize(new Dimension(200, 100));
        eventCard.add(descriptionScrollPane);

        // Price
        JLabel priceLabel = new JLabel("Price: $" + event.getPrice());
        priceLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        priceLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        eventCard.add(priceLabel);

        // Book Button
        JButton bookButton = new JButton("Book");
        bookButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        bookButton.setBackground(new Color(0, 123, 255));
        bookButton.setForeground(Color.WHITE);
        bookButton.setFocusPainted(false);
        bookButton.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        bookButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement booking action here
                boolean success = BookingsController.createBooking(customer, event);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Successfully booked event: " + event.getTitle());
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to book event: " + event.getTitle());
                }
            }
        });
        eventCard.add(bookButton);

        return eventCard;
    }

}