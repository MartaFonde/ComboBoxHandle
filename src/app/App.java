package app;

import javax.swing.JFrame;

public class App {
    
    public static void main(String[] args) throws Exception {
        SwingDelCombo p = new SwingDelCombo();
        p.setSize(800,450);
        p.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        p.setVisible(true);
    }
}