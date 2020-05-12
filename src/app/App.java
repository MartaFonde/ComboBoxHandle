package app;

import javax.swing.JFrame;

public class App {
    public static void main(String[] args) throws Exception {
        Prog p = new Prog();
        p.setSize(800,600);
        p.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        p.setVisible(true);
    }
}