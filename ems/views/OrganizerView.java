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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import ems.Controller.EventsController;
import ems.Models.Organizer;
import ems.db.DBConnection;
import ems.Models.Event;
import ems.views.Login;

public class OrganizerView extends JFrame {

    private JButton dashboardButton, eventsButton, bookingsButton, createEventButton, settingsButton,signOutButton;
    private JPanel contentArea;
    private Organizer organizer;

    public OrganizerView(Organizer organizer) {
        this.organizer = organizer;
        System.out.println(organizer.getEmail());
        // Set up the main frame
        setTitle("Organizer Dashboard");
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
        //contentArea.add(new JScrollPane(createsignOutPanel()),"SignOut");
        add(contentArea, BorderLayout.CENTER);

        setActiveButton(dashboardButton); // Set initial active button and content
        showPanel("Dashboard");
        //setActiveButton(signOutButton);
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
        signOutButton = createSidebarButton("SignOut");

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
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(signOutButton);

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
            if (text.equals("SignOut")) {
                dispose(); // Close the current Organizer dashboard window
                SwingUtilities.invokeLater(() ->  new Login().setVisible(true)
                );
            }
        }
    });
        return button;
    }

    private void setActiveButton(JButton activeButton) {
        JButton[] buttons = {dashboardButton, eventsButton, bookingsButton, createEventButton, settingsButton,signOutButton};

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

        List<Event> events = organizer.getEvents();
        // Add event cards
        for (Event event : events) {
            panel.add(createEventCard(event));
        }

        return panel;
    }

    private JPanel createBookingsPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 240, 240));
        panel.add(new JLabel("Bookings will be shown here."));
        return panel;
    }

   /*  private JPanel createSettingsPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 240, 240));
        panel.add(new JLabel("Settings will be configured here."));
        return panel;
    } */
    private JPanel createSettingsPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Labels and text fields for organizer information
        JLabel nameLabel = new JLabel("Name:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(nameLabel, gbc);

        JTextField nameField = new JTextField(organizer.getFirstName(), 20);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        JLabel emailLabel = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(emailLabel, gbc);

        JTextField emailField = new JTextField(organizer.getEmail(), 20);
        gbc.gridx = 1;
        panel.add(emailField, gbc);

        JLabel passLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = -1;
        panel.add(passLabel, gbc);

        JTextField passField = new JTextField(organizer.getPassword(),20);
        gbc.gridx = 1;
        panel.add(passField, gbc);
        // Add more fields if needed
        // Update button
        JButton updateButton = new JButton("Update");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(updateButton, gbc);

        // Delete button
        JButton deleteButton = new JButton("Delete");
        gbc.gridx = 1;
        panel.add(deleteButton, gbc);

        // Action listener for update button
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the organizer's details
                String newName = nameField.getText();
                String newEmail = emailField.getText();
                String newPassword = passField.getText();
              
                //code to update the database or model here

                 try{
                        DBConnection db = new DBConnection();
                        Connection con = db.getConnection();

                        String query = "UPDATE organizers SET First_Name = ? , email = ? , password = ? WHERE id = ?";
                        
                        PreparedStatement ps = con.prepareStatement(query);
                        ps.setString(1,newName);
                        ps.setString(2,newEmail);
                        ps.setString(3,newPassword);
                        ps.setInt(4,organizer.getId());
                        
                        int rowsAffectected = ps.executeUpdate();
                        if(rowsAffectected > 0){
                            organizer.setFirstName(newName);
                            organizer.setEmail(newEmail);
                            organizer.setPassword(newPassword);
                            JOptionPane.showMessageDialog(panel, "Organizer details updated successfully!");
                            dispose();
                            new Login().setVisible(true);
                        }
                        else{
                            JOptionPane.showMessageDialog(panel, "Organizer details update failed. Please Try Again!");
                        }
                    }catch(SQLException | ClassNotFoundException em){
                    em.printStackTrace();
                    JOptionPane.showMessageDialog(panel, "Database Connection erro"+em.getMessage());
                } 
                
            }
        });

        // Action listener for delete button
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform the delete operation
                // code to delete the organizer from the database or model here

                int confirmation = JOptionPane.showConfirmDialog(panel, "Are you sure you want to delete this organizer?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if(confirmation == JOptionPane.YES_OPTION){
                      try{
                            DBConnection db = new DBConnection();
                            Connection con = db.getConnection();

                            String query = "DELETE FROM organizers WHERE id = ?";
                            PreparedStatement pmt = con.prepareStatement(query);
                            pmt.setInt(1, organizer.getId());
                            int rowsAffectected = pmt.executeUpdate();

                            if(rowsAffectected > 0){
                                JOptionPane.showMessageDialog(panel, "Organizer deleted successfully!");
                                dispose();
                                new Login().setVisible(true);
                            }
                            else{
                                JOptionPane.showMessageDialog(panel, "Organizer deletetion failed. Please Try Again!");

                            }
                      }catch(ClassNotFoundException | SQLException emp){
                          emp.printStackTrace();
                      }
                }
                // Redirect to login screen or close the application
                
            }
        });

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
        descriptionScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);// vertical scroll bar

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

                    // Refresh events panel
                    refreshEventsPanel();
                    showPanel("Events");
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

    private void refreshEventsPanel() {
        JPanel newEventsPanel = createEventsPanel(); // Create a new panel with updated events
        contentArea.add(new JScrollPane(newEventsPanel), "Events");
        showPanel("Events");
    }

    // Method to create event card based on Event object
    private JPanel createEventCard(Event event) {
        JPanel eventCard = new JPanel();
        eventCard.setLayout(new BoxLayout(eventCard, BoxLayout.Y_AXIS));
        eventCard.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        eventCard.setBackground(Color.WHITE);
        eventCard.setPreferredSize(new Dimension(250, 390)); // Adjust the size as needed
        eventCard.setMaximumSize(new Dimension(Integer.MAX_VALUE, 390)); // Set max height

        // Image
        JLabel imageLabel = new JLabel();
        if (event.getImage() != null) {
            ImageIcon imageIcon = new ImageIcon(event.getImage());
            Image image = imageIcon.getImage().getScaledInstance(350, 250, Image.SCALE_DEFAULT);
            imageIcon = new ImageIcon(image);
            imageLabel.setIcon(imageIcon);
        }
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        eventCard.add(imageLabel);

        // Title
        JLabel titleLabel = new JLabel(event.getTitle());
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        eventCard.add(titleLabel);

        // Description
        JTextArea descriptionArea = new JTextArea(event.getDescription());
        descriptionArea.setEditable(false);
        descriptionArea.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);
        descriptionScrollPane.setPreferredSize(new Dimension(250, 10));
        eventCard.add(descriptionScrollPane);

        // Price
        JLabel priceLabel = new JLabel("Price: Ksh" + event.getPrice());
        priceLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        priceLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        eventCard.add(priceLabel);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE);

        // Update Button
        JButton updateButton = new JButton("Update");
        updateButton.setBackground(new Color(255, 193, 7));
        updateButton.setForeground(Color.WHITE);
        updateButton.setFocusPainted(false);
        updateButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        updateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        updateButton.addActionListener(e -> showUpdateDialog(event));
        buttonPanel.add(updateButton);

        // Delete Button
        JButton deleteButton = new JButton("Delete");
        deleteButton.setBackground(new Color(220, 53, 69));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);
        deleteButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this event?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                boolean success = EventsController.deleteEvent(event.getId());
                if (success) {
                    JOptionPane.showMessageDialog(null, "Event deleted successfully!");
                    refreshEventsPanel();
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete event. Please try again.");
                }
            }
        });
        buttonPanel.add(deleteButton);

        eventCard.add(buttonPanel);

        return eventCard;
    }

    // Method to show update dialog with current event details
    private void showUpdateDialog(Event event) {
        JTextField titleField = new JTextField(event.getTitle());
        JTextArea descriptionArea = new JTextArea(event.getDescription());
        JTextField priceField = new JTextField(String.valueOf(event.getPrice()));

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Title:"));
        panel.add(titleField);
        panel.add(new JLabel("Description:"));
        panel.add(new JScrollPane(descriptionArea));
        panel.add(new JLabel("Price:"));
        panel.add(priceField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Edit Event", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String updatedTitle = titleField.getText();
            String updatedDescription = descriptionArea.getText();
            double updatedPrice;
            try {
                updatedPrice = Double.parseDouble(priceField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid price value. Please enter a valid number.");
                return;
            }

            // Update event details
            event.setTitle(updatedTitle);
            event.setDescription(updatedDescription);
            event.setPrice(updatedPrice);

            // Save updated event
            boolean success = EventsController.updateEvent(event);
            if (success) {
                JOptionPane.showMessageDialog(null, "Event updated successfully!");
                refreshEventsPanel();
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update event. Please try again.");
            }
        }
    }

}
