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
    private JButton homeButton, eventsButton, bookingsButton, profileButton;
    private JPanel contentPanel;

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
        contentPanel = new JPanel(new CardLayout());
        add(contentPanel, BorderLayout.CENTER);

        // Show Events area by default
        showEventsArea();
    }

    private JPanel createTopBar() {
        JPanel topBar = new JPanel();
        topBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        topBar.setBackground(new Color(45, 45, 45));

        JLabel title = new JLabel("Welcome " + this.customer.getFirstName() + " " + this.customer.getLastName());
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

        eventsButton = createSidebarButton("Events");
        bookingsButton = createSidebarButton("Bookings");
        profileButton = createSidebarButton("Profile");

        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(eventsButton);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(bookingsButton);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(profileButton);

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

        return button;
    }

    private void showEventsArea() {
        JPanel eventsArea = new JPanel();
        eventsArea.setLayout(new GridLayout(0, 3, 20, 20)); // 3 columns with 20px gaps
        eventsArea.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        eventsArea.setBackground(new Color(240, 240, 240));

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
        JPanel bookingsArea = new JPanel();
        bookingsArea.setLayout(new GridLayout(0, 3, 20, 20)); // 3 columns with 20px gaps
        bookingsArea.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        bookingsArea.setBackground(new Color(240, 240, 240));

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

    private JPanel createEventCard(Event event, boolean includeBookButton) {
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
