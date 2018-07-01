package com.Map;

import javax.swing.*;
import javax.swing.UIManager;

public class Starter extends JFrame {

    public Starter() throws Exception {

        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        setBounds(32, 32, 1024, 720);

        this.add(new Map());
       // this.add(new Controls());

        setVisible(true);
    }

}
