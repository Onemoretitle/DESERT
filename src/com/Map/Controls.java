package com.Map;

import javax.swing.*;
import javax.swing.JComponent.AccessibleJComponent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controls extends JFrame  {

    private JTextField textField;

    public Controls() {
        super("Controls");
        Create();
    }

    public void Create() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout());

        JButton button1 = new JButton("Button 1");
        button1.setActionCommand("Button 1 was pressed!");
        panel.add(button1, BorderLayout.EAST);

        JButton button2 = new JButton("Button 2");
        button2.setActionCommand("Button 2 was pressed!");
        panel.add(button2, BorderLayout.EAST);

        JButton button3 = new JButton("Button 3");
        button3.setActionCommand("Button 3 was pressed!");
        panel.add(button3, BorderLayout.SOUTH);

        textField = new JTextField();
        textField.setColumns(23);
        panel.add(textField);

        ActionListener actionListener = new TestActionListener();

        button1.addActionListener(actionListener);
        button2.addActionListener(actionListener);

        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setText(e.getActionCommand());
            }
        });

        getContentPane().add(panel);
        setPreferredSize(new Dimension(320, 100));
    }

    public class TestActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            textField.setText(e.getActionCommand());
        }
    }

}
