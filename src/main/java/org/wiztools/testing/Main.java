package org.wiztools.testing;

import javax.swing.SwingUtilities;

public class Main {
    public static void main( String[] args ) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MyFrame("WizTools.org - java.text.SimpleDateFormat Tester");
            }
        });
    }
}
