package ems.main;

import javax.swing.SwingUtilities;

import ems.views.Home;

/**
 * The Main class is the entry point of the application.
 */
public class Main {
    /**
     * The main method is the entry point of the application.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->{
            Home Home = new Home();
            Home.setVisible(true);

        });
    }
}
