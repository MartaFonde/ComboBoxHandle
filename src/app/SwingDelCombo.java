package app;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;

public class SwingDelCombo extends JFrame implements ActionListener, ItemListener {
    private JComboBox<String> cbA;
    private String[] nombreDirector = {"David", "Lars", "Andréi", "Sofia", "Darren"};
    private JComboBox<String> cbB;
    private String[] apellidoDirector = {"Lynch", "Von Trier", "Tarkovski", "Coppola", "Aronofsky"};
    private JButton btnAñadir;
    private JButton btnQuitar;
    private JButton btnTraspasarAB;
    private JButton btnTraspasarBA;
    private JTextField txf1;
    private JTextField txf2;
    private JLabel lblElem;
    private JLabel lblInd;
    private Timer temporizador;
    private JLabel seg;
    private int cont;

    public SwingDelCombo() {
        super("ComboBox");
        setLayout(null);

        cbA = new JComboBox<String>(nombreDirector);
        cbA.setSize(160, 25);
        cbA.setLocation(15, 15);
        cbA.setToolTipText("ComboBox A");
        cbA.addItemListener(this);
        add(cbA);

        cbB = new JComboBox<String>(apellidoDirector);
        cbB.setSize(160, 25);
        cbB.setLocation(230, 15);
        cbB.setToolTipText("Índice seleccionado de ComboBox B: " + cbB.getSelectedIndex());
        cbB.addItemListener(this);
        add(cbB);

        btnTraspasarAB = new JButton("Transpasar A -> B");
        btnTraspasarAB.setSize(btnTraspasarAB.getPreferredSize());
        btnTraspasarAB.setLocation(430, 10);
        btnTraspasarAB.setToolTipText("Traspasa el valor seleccionado de la comboBox A a la B");
        btnTraspasarAB.addActionListener(this);
        add(btnTraspasarAB);

        btnTraspasarBA = new JButton("Transpasar B -> A");
        btnTraspasarBA.setSize(btnTraspasarBA.getPreferredSize());
        btnTraspasarBA.setLocation(600, 10);
        btnTraspasarBA.setToolTipText("Traspasa el valor seleccionado de la comboBox B a la A");
        btnTraspasarBA.addActionListener(this);
        add(btnTraspasarBA);

        txf1 = new JTextField(20);
        txf1.setSize(txf1.getPreferredSize());
        txf1.setLocation(10, 55);
        txf1.setToolTipText(
                "Items a añadir a ComboBox A. Introduce varias entradas separadas ; Pulsa Añadir");
        add(txf1);

        btnAñadir = new JButton("Añadir");
        btnAñadir.setSize(btnAñadir.getPreferredSize());
        btnAñadir.setLocation(250, 50);
        btnAñadir.setToolTipText("Añade a ComboBox A los items introducidos en el campo de texto");
        btnAñadir.addActionListener(this);
        add(btnAñadir);

        txf2 = new JTextField(20);
        txf2.setSize(txf2.getPreferredSize());
        txf2.setLocation(10, 105);
        txf2.setToolTipText("Items a borrar de ComboBox A que comiencen por el texto aquí introducido. Pulsa Quitar");
        add(txf2);

        btnQuitar = new JButton("Quitar");
        btnQuitar.setSize(btnQuitar.getPreferredSize());
        btnQuitar.setLocation(250, 100);
        btnQuitar.setBackground(Color.white);
        btnQuitar.setToolTipText(
                "Borra de ComboBox A el item seleccionado o los que comiencen por lo introducido en el campo de texto");
        btnQuitar.addActionListener(this);
        btnQuitar.addMouseListener(new MouseHandler());
        add(btnQuitar);

        lblElem = new JLabel("Cantidad de elementos en cbA: " + cbA.getItemCount());
        lblElem.setSize(lblElem.getPreferredSize());
        lblElem.setLocation(20, 140);
        lblElem.setToolTipText("Número total de items de ComboBox A");
        add(lblElem);

        lblInd = new JLabel("Índice seleccionado en cbA: " + cbA.getSelectedIndex());
        lblInd.setSize(280, 20);
        lblInd.setLocation(20, 170);
        lblInd.setToolTipText("Índice del item seleccionado en ComboBox A");
        add(lblInd);

        cont = 0;
        seg = new JLabel("Segundos: " + cont);
        seg.setSize(150, 20);
        seg.setLocation(20, 200);
        seg.setToolTipText("Los campos se reinician si la app permanece inactiva más de 1 minuto");
        add(seg);

        addWindowListener(new WindowHandler());

        temporizador = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seg.setText("Segundos: " + cont);
                cont++;
                if (cont == 60) {
                    cbA.removeAllItems();
                    cbB.removeAllItems();
                    txf1.setText("");
                    txf2.setText("");
                    cont = 0;
                    lblElem.setText("Cantidad de elementos en cbA: " + cbA.getItemCount());
                    lblInd.setText("Índice seleccionado en cbA: " + cbA.getSelectedIndex());            
                }
            }
        });
        temporizador.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAñadir) {
            if (txf1.getText().trim().length() > 0) {
                if (txf1.getText().contains(";")) {
                    String[] items = txf1.getText().split(";");
                    for (String item : items) {
                        if (item.trim().length() != 0) {
                            cbA.addItem(item.trim());
                        }
                    }
                } else {
                    cbA.addItem(txf1.getText().trim());
                }
            }
        }

        if (e.getSource() == btnQuitar) {
            if (txf2.getText().length() == 0) {
                cbA.removeItemAt(cbA.getSelectedIndex());
            } else {
                for (int i = cbA.getItemCount() - 1 ; i >= 0; i--) {
                    if (cbA.getItemAt(i).startsWith(txf2.getText())) {
                        cbA.removeItemAt(i);
                    }
                }
            }
        }

        if (e.getSource() == btnTraspasarAB) {
            cbB.addItem(cbA.getSelectedItem().toString());
            cbA.removeItem(cbA.getSelectedItem());
        }

        if (e.getSource() == btnTraspasarBA) {
            cbA.addItem(cbB.getSelectedItem().toString());
            cbB.removeItem(cbB.getSelectedItem());
        }
        lblElem.setText("Cantidad de elementos en cbA: " + cbA.getItemCount());
        lblInd.setText("Índice seleccionado en cbA: " + cbA.getSelectedIndex());
        cont = 0;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            lblInd.setText("Índice seleccionado en cbA: " + cbA.getSelectedIndex());
            cbB.setToolTipText("Índice seleccionado de ComboBox B: " + cbB.getSelectedIndex());
        }
        cont = 0;
    }

    private class MouseHandler extends MouseAdapter {
        @Override
        public void mouseEntered(java.awt.event.MouseEvent e) {
            if (e.getSource() == btnQuitar) {
                btnQuitar.setBackground(Color.red);
            }
            cont = 0;
        }

        @Override
        public void mouseExited(java.awt.event.MouseEvent e) {
            if (e.getSource() == btnQuitar) {
                btnQuitar.setBackground(Color.white);
            }
            cont = 0;
        }
    }

    private class WindowHandler extends WindowAdapter {
        public void windowClosing(java.awt.event.WindowEvent e) {
            int res = JOptionPane.showConfirmDialog(null, "¿Quieres cerrar el programa?", "ComboBox",
                    JOptionPane.YES_NO_OPTION);
            if (res == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

}