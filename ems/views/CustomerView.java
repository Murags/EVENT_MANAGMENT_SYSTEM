package ems.views;

import ems.Controller.BookingsController;
import ems.Controller.EventsController;
import ems.Models.Customer;
import ems.Models.Event;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;

public class CustomerView extends JFrame {

    private Customer customer;
    private JButton eventsButton, bookingsButton, settingsButton, exitButton, minimizeButton,maximizeButton ;
    private JPanel TopButtons;
    private JPanel contentPanel;

    public CustomerView(Customer customer) {
        this.customer = customer;
        // Set up the main frame
        setTitle("Event Management Dashboard");
        setSize(1490, 880);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setLayout(new BorderLayout());

        // Add top bar
        JPanel topBar = createTopBar();
        add(topBar, BorderLayout.NORTH);

        // Add sidebar
        JPanel sidebar = createSidebar();
        add(sidebar, BorderLayout.WEST);

        // Add main content area
        contentPanel = new JPanel(new CardLayout());
        contentPanel.setBackground(new Color(221, 218, 238));
        add(contentPanel, BorderLayout.CENTER);

        // Show Events area by default
        showEventsArea();
    }

    //Creation of the TopBar
    private JPanel createTopBar() {
        exitButton = new JButton();
        ImageIcon icon = new ImageIcon("img/RED BUTTON.jpg");
        exitButton.setIcon(icon);
        exitButton.setFocusable(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setBackground(new Color(50, 50, 47, 255));
        exitButton.setBorder(BorderFactory.createEmptyBorder());
        exitButton.addActionListener(e -> System.exit(0));

        maximizeButton = new JButton();
        maximizeButton.setBackground(new Color(50, 50, 47, 255));
        maximizeButton.setContentAreaFilled(false);
        maximizeButton.setPreferredSize(new Dimension(40, 40));
        ImageIcon icon1 = new ImageIcon("img/Orange colour.jpg");
        maximizeButton.setIcon(icon1);
        maximizeButton.setFocusable(false);
        maximizeButton.setBorder(BorderFactory.createEmptyBorder());

        minimizeButton = new JButton();
        minimizeButton.setBackground(new Color(50, 50, 47, 255));
        ImageIcon icon3 = new ImageIcon("img/Minimize Button.jpg");
        minimizeButton.setIcon(icon3);
        minimizeButton.setContentAreaFilled(false);
        minimizeButton.setFocusable(false);
        minimizeButton.setBorder(BorderFactory.createEmptyBorder());
        minimizeButton.addActionListener(ae -> setState(ICONIFIED));

        TopButtons = new JPanel();
        TopButtons.setLayout(new GridLayout(1, 3, 0, 0));
        TopButtons.setPreferredSize(new Dimension(70, 20));
        TopButtons.setBackground(new Color(50, 50, 47, 255));
        TopButtons.setBorder(BorderFactory.createEmptyBorder());
        TopButtons.add(minimizeButton);
        TopButtons.add(maximizeButton);
        TopButtons.add(exitButton);

        JLabel title = new JLabel("Welcome " + this.customer.getFirstName() + " " + this.customer.getLastName());
        JPanel topBar = new JPanel();
        topBar.setPreferredSize(new Dimension(450, 40));
        topBar.setLayout(new BorderLayout(0, 0));
        topBar.setBackground(new Color(50, 50, 47, 255));
        topBar.add(TopButtons, BorderLayout.EAST);
        topBar.add(title);
        return topBar;
    }

   // Creation of the SidePanel
    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(33, 33, 33));
        sidebar.setPreferredSize(new Dimension(200, getHeight()));

        eventsButton = createSidebarButton("Events");
        bookingsButton = createSidebarButton("Bookings");
        settingsButton = createSidebarButton("Settings");

        
        
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(eventsButton);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(bookingsButton);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(settingsButton);

        // Set action listeners
        eventsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showEventsArea();
            }
        });

        bookingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showBookingsArea();
            }
        });

        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSettingsArea();
            }
        });

        return sidebar;
    }


//Creation of the 
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

        return button;
    }

    private void setActiveButton(JButton activeButton) {
        JButton[] buttons = {eventsButton, bookingsButton, settingsButton};

        for (JButton button : buttons) {
            if (button == activeButton) {
                button.setBackground(new Color(77, 77, 77));
            } else {
                button.setBackground(new Color(55, 55, 55));
            }
        }
    }

    private void showEventsArea() {
        JPanel eventsArea = new JPanel();
        eventsArea.setLayout(new GridLayout(0, 3, 20, 20)); // 3 columns with 20px gaps
        eventsArea.setBorder(BorderFactory.createEmptyBorder(50, 10, 100, 10));
        eventsArea.setBackground(new Color(221, 218, 238));

        List<Event> events = EventsController.allEvents();
        if (events.isEmpty()) {
            JLabel noEventsLabel = new JLabel("No Events Available");
            noEventsLabel.setFont(new Font("Verdana", Font.BOLD, 18));
            noEventsLabel.setHorizontalAlignment(SwingConstants.CENTER);
            eventsArea.add(noEventsLabel);
        } else {
            for (Event event : events) {
                eventsArea.add(createEventCard(event, true));
            }
        }

        contentPanel.removeAll();
        contentPanel.add(new JScrollPane(eventsArea));
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showBookingsArea() {
        JPanel bookingsArea = new JPanel(){};
        bookingsArea.setLayout(new GridLayout(0, 3, 20, 20)); // 3 columns with 20px gaps
        bookingsArea.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        bookingsArea.setBackground(new Color(221, 218, 238));

        List<Event> myEvents = customer.getEvents();
        if (myEvents.isEmpty()) {
            JLabel noBookingsLabel = new JLabel("No Bookings Made");
            noBookingsLabel.setFont(new Font("Verdana", Font.BOLD, 18));
            noBookingsLabel.setHorizontalAlignment(SwingConstants.CENTER);
            bookingsArea.add(noBookingsLabel);
        } else {
            for (Event event : myEvents) {
                bookingsArea.add(createEventCard(event, false));
            }
        }

        contentPanel.removeAll();
        contentPanel.add(new JScrollPane(bookingsArea));
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showSettingsArea() {
        JPanel panel = new JPanel(new GridBagLayout()){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load and draw the background image
                ImageIcon backgroundImage = new ImageIcon("img/bg.png");
                // Adjust transparency
            Graphics2D g2d = (Graphics2D) g.create();
            float alpha = 0.1f; // 0.0f is fully transparent, 1.0f is fully opaque
            g2d.setComposite(AlphaComposite.SrcOver.derive(alpha));
            g2d.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);

            g2d.dispose();
            }
        };
        panel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Labels and text fields for customer information
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setForeground(new Color (7,106,171,255));
        gbc.gridx = 3;
        gbc.gridy = 0;
        panel.add(nameLabel, gbc);

        JTextField nameField = new JTextField(customer.getFirstName(), 20);
        gbc.gridx = 5;
        panel.add(nameField, gbc);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(new Color (7,106,171,255));
        gbc.gridx = 3;
        gbc.gridy = 1;
        panel.add(emailLabel, gbc);

        JTextField emailField = new JTextField(customer.getEmail(), 20);
        gbc.gridx = 5;
        panel.add(emailField, gbc);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(new Color (7,106,171,255));
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
        updateButton.setBackground(new Color (7,106,171,255));
        updateButton.setForeground(Color.BLACK);
        panel.add(updateButton, gbc);

        // Delete button
        JButton deleteButton = new JButton("Delete");
        gbc.gridx = 6;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        deleteButton.setMaximumSize(new Dimension(180, 40));
        deleteButton.setFont(new Font("Verdana", Font.PLAIN, 16));
        deleteButton.setBackground(new Color (7,106,171,255));
        deleteButton.setForeground(Color.BLACK);
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
        contentPanel.removeAll();
        contentPanel.add(new JScrollPane(panel));
        contentPanel.revalidate();
        contentPanel.repaint();
    }


    // Method to create event card based on Event object
    private JPanel createEventCard(Event event, boolean includeBookButton) {
        JPanel eventCard = new JPanel(){};
        eventCard.setLayout(new BoxLayout(eventCard, BoxLayout.Y_AXIS));
        eventCard.setBorder(BorderFactory.createEmptyBorder());
        eventCard.setBackground(new Color(221, 218, 238));
        eventCard.setPreferredSize(new Dimension(400, 700)); // Adjust the size as needed
        eventCard.setMaximumSize(new Dimension(Integer.MAX_VALUE,700)); // Set max height

        // Image
        JLabel imageLabel = new JLabel();
        if (event.getImage() != null) {
            ImageIcon imageIcon = new ImageIcon(event.getImage());
            Image image = imageIcon.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT);
            imageIcon = new ImageIcon(image);
            imageLabel.setIcon(imageIcon);
        }
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        eventCard.add(imageLabel);

        // Title
        JLabel titleLabel = new JLabel(event.getTitle());
        titleLabel.setFont(new Font("Calibri",Font.BOLD, 30));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        eventCard.add(titleLabel);

        // Description
        JTextArea descriptionArea = new JTextArea(event.getDescription());
        descriptionArea.setEditable(false);
        descriptionArea.setFont(new Font("Verdana", Font.ITALIC, 15));
        descriptionArea.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        descriptionArea.setBackground(new Color(221, 218, 238));
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);
        descriptionScrollPane.setPreferredSize(new Dimension(400, 50));
        descriptionScrollPane.setBackground(new Color(221, 218, 238));
        descriptionScrollPane.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        eventCard.add(descriptionScrollPane);

        // Price
        JLabel priceLabel = new JLabel("Price: Ksh" + event.getPrice());
        priceLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        priceLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        eventCard.add(priceLabel);


        if (includeBookButton) {
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
        }

        return eventCard;
    }

}
