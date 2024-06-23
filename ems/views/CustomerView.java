package ems.views;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

import ems.Models.Customer;

public class CustomerView extends JFrame {

    private JButton homeButton, eventsButton, bookingsButton, profileButton;

    public CustomerView() {
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
        JPanel contentArea = createContentArea();
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

        homeButton = createSidebarButton("Home");
        eventsButton = createSidebarButton("Events");
        bookingsButton = createSidebarButton("Bookings");
        profileButton = createSidebarButton("Profile");

        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(homeButton);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(eventsButton);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(bookingsButton);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(profileButton);

        // Set "Home" as the active button
        setActiveButton(homeButton);

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

        return button;
    }

    private void setActiveButton(JButton activeButton) {
        JButton[] buttons = {homeButton, eventsButton, bookingsButton, profileButton};

        for (JButton button : buttons) {
            if (button == activeButton) {
                button.setBackground(new Color(77, 77, 77));
            } else {
                button.setBackground(new Color(55, 55, 55));
            }
        }
    }

    private JPanel createContentArea() {
        JPanel contentArea = new JPanel();
        contentArea.setLayout(new GridLayout(0, 3, 20, 20)); // 3 columns with 20px gaps
        contentArea.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentArea.setBackground(new Color(240, 240, 240));

        // Add event cards
        for (int i = 1; i <= 15; i++) {
            contentArea.add(createEventCard("Event " + i, "Description for event " + i));
        }

        return contentArea;
    }

    private JPanel createEventCard(String title, String description) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2, true));
        card.setPreferredSize(new Dimension(200, 150));
        card.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        card.add(titleLabel, BorderLayout.NORTH);

        JTextArea descriptionArea = new JTextArea(description);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionArea.setBackground(Color.WHITE);
        descriptionArea.setForeground(new Color(60, 60, 60));
        card.add(new JScrollPane(descriptionArea), BorderLayout.CENTER);

        return card;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CustomerView dashboard = new CustomerView();
            dashboard.setVisible(true);
        });
    }
}
