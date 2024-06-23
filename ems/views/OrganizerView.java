package ems.views;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import ems.Controller.EventsController;
import ems.Models.Organizer;

public class OrganizerView extends JFrame {

    private JButton dashboardButton, eventsButton, bookingsButton, createEventButton, settingsButton;
    private JPanel contentArea;
    private Organizer organizer;

    public OrganizerView(Organizer organizer) {
        this.organizer = organizer;
        System.out.println(organizer.getEmail());
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
        contentArea = new JPanel(new CardLayout());
        contentArea.add(new JScrollPane(createDashboardPanel()), "Dashboard");
        contentArea.add(new JScrollPane(createEventsPanel()), "Events");
        contentArea.add(new JScrollPane(createBookingsPanel()), "Bookings");
        contentArea.add(new JScrollPane(createSettingsPanel()), "Settings");
        contentArea.add(new JScrollPane(createCreateEventPanel()), "Create Event");
        add(contentArea, BorderLayout.CENTER);

        setActiveButton(dashboardButton); // Set initial active button and content
        showPanel("Dashboard");
    }

    private JPanel createTopBar() {
        JPanel topBar = new JPanel();
        topBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        topBar.setBackground(new Color(45, 45, 45));

        JLabel title = new JLabel("Event Management Dashboard");
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

        dashboardButton = createSidebarButton("Dashboard");
        eventsButton = createSidebarButton("Events");
        bookingsButton = createSidebarButton("Bookings");
        createEventButton = createSidebarButton("Create Event");
        settingsButton = createSidebarButton("Settings");

        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(dashboardButton);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(eventsButton);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(bookingsButton);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(createEventButton);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(settingsButton);

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

        // Add action listener to change active button and content
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setActiveButton(button);
                showPanel(text);
            }
        });

        return button;
    }

    private void setActiveButton(JButton activeButton) {
        JButton[] buttons = {dashboardButton, eventsButton, bookingsButton, createEventButton, settingsButton};

        for (JButton button : buttons) {
            if (button == activeButton) {
                button.setBackground(new Color(77, 77, 77));
            } else {
                button.setBackground(new Color(55, 55, 55));
            }
        }
    }

    private void showPanel(String panelName) {
        CardLayout cl = (CardLayout) (contentArea.getLayout());
        cl.show(contentArea, panelName);
    }

    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 240, 240));
        panel.add(new JLabel("Welcome to the Dashboard!"));
        return panel;
    }

    private JPanel createEventsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 3, 20, 20)); // 3 columns with 20px gaps
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(240, 240, 240));

        // Add event cards (example content)
        for (int i = 1; i <= 15; i++) {
            panel.add(createEventCard("Event " + i, "Description for event " + i));
        }

        return panel;
    }

    private JPanel createBookingsPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 240, 240));
        panel.add(new JLabel("Bookings will be shown here."));
        return panel;
    }

    private JPanel createSettingsPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 240, 240));
        panel.add(new JLabel("Settings will be configured here."));
        return panel;
    }

    private JPanel createCreateEventPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(240, 240, 240));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        JLabel titleLabel = new JLabel("Create New Event");
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel("Title:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JTextField titleField = new JTextField(20);
        panel.add(titleField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel("Description:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JTextArea descriptionArea = new JTextArea(5, 20);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea); // Wrap JTextArea in JScrollPane
        panel.add(descriptionScrollPane, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel("Price:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JTextField priceField = new JTextField(20);
        panel.add(priceField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(new JLabel("Image:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JPanel imagePanel = new JPanel(new BorderLayout());
        JLabel imageLabel = new JLabel("No file chosen");
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        JButton uploadImageButton = new JButton("Upload");
        uploadImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png"));
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String fileName = selectedFile.getName();
                    String directoryPath = "Uploads/"; // Set your desired directory path
                    File destinationDirectory = new File(directoryPath);
                    if (!destinationDirectory.exists()) {
                        destinationDirectory.mkdirs();
                    }
                    File destinationFile = new File(directoryPath + fileName);
                    try {
                        Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        // imageLabel.setText(destinationFile.getAbsolutePath());
                        imageLabel.setText("Uploads/" + fileName);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Failed to upload image.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        imagePanel.add(uploadImageButton, BorderLayout.EAST);
        panel.add(imagePanel, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton createEventButton = new JButton("Create Event");
        panel.add(createEventButton, gbc);

        createEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve data from fields
                String title = titleField.getText();
                String description = descriptionArea.getText();
                String priceText = priceField.getText();
                double price = 0.0;

                try {
                    price = Double.parseDouble(priceText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid price.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Perform validation
                if (title.isEmpty() || description.isEmpty() || priceText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Handle image upload
                String imagePath = imageLabel.getText();
                System.out.println(imagePath);
                if (imageLabel.getText().equals("No file chosen")) {
                    JOptionPane.showMessageDialog(null, "Please upload an image.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int organizer_id = organizer.getId();
                boolean success = EventsController.createEvent(organizer_id, title, description, price, imagePath);

                if (success) {
                    JOptionPane.showMessageDialog(null, "Event created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    // Reset fields
                    titleField.setText("");
                    descriptionArea.setText("");
                    priceField.setText("");
                    imageLabel.setText("No file chosen");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to create event.", "Error", JOptionPane.ERROR_MESSAGE);
                    // Optionally reset fields even on failure
                    titleField.setText("");
                    descriptionArea.setText("");
                    priceField.setText("");
                    imageLabel.setText("No file chosen");
                }
            }
        });


        // Wrap the panel in a JScrollPane to make it scrollable
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        return panel; // Return the JScrollPane instead of the JPanel
    }

    private JPanel createEventCard(String title, String description) {
        JPanel eventCard = new JPanel();
        eventCard.setLayout(new BorderLayout());
        eventCard.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        eventCard.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        eventCard.add(titleLabel, BorderLayout.NORTH);

        JTextArea descriptionArea = new JTextArea(description);
        descriptionArea.setEditable(false);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        eventCard.add(new JScrollPane(descriptionArea), BorderLayout.CENTER);

        return eventCard;
    }
}
