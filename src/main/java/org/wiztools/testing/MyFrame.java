package org.wiztools.testing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 *
 * @author subwiz
 */
public class MyFrame extends JFrame{

    private static final String DEFAULT_PATTERN = "EEE, dd MMM yyyy HH:mm:ss z";
    private static final String NO_RESULT_TEXT = "<no result>";

    private JTextField jtf_in = new JTextField(DEFAULT_PATTERN, 30);
    private JLabel jl_out = new JLabel(NO_RESULT_TEXT);
    
    private final JComboBox jcb_locale;
    {
        // Locale does not implement Comparable :-(
        // So Arrays.sort() does not work on it
        Locale[] l = Locale.getAvailableLocales();
        MyLocale[] myLocales = new MyLocale[l.length];
        for(int i=0; i<l.length; i++) {
            myLocales[i] = new MyLocale(l[i]);
        }
        Arrays.sort(myLocales);
        
        // This is what will be populated in Combo box:
        Locale[] locales = new Locale[l.length];
        for(int i=0; i<l.length; i++) {
            locales[i] = myLocales[i].getLocale();
        }
        jcb_locale = new JComboBox(locales);
    }

    private static final int FONT_SIZE = 26;
    

    public MyFrame(String title){
        super(title);
        Container c = super.getContentPane();
        c.setLayout(new BorderLayout());

        JPanel jp_north = new JPanel();
        jp_north.setLayout(new FlowLayout());
        jp_north.add(new JLabel("Enter Simple Date Format: "));
        jp_north.add(jtf_in);
        jcb_locale.setSelectedItem(Locale.getDefault());
        jp_north.add(jcb_locale);
        JButton jb = new JButton("Go!");
        jb.setMnemonic('g');
        jb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    SimpleDateFormat sdf = new SimpleDateFormat(jtf_in.getText(),
                            (Locale)jcb_locale.getSelectedItem());
                    String out = sdf.format(new Date());
                    if("".equals(out.trim())){
                        out = NO_RESULT_TEXT;
                    }
                    jl_out.setText(out);
                }
                catch(IllegalArgumentException ex){
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage(),
                            "Error parsing!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        jp_north.add(jb);
        c.add(jp_north, BorderLayout.NORTH);

        JPanel jp_center = new JPanel();
        jp_center.setLayout(new FlowLayout(FlowLayout.CENTER));
        jl_out.setFont(new Font(Font.DIALOG, Font.BOLD, FONT_SIZE));
        jl_out.setForeground(Color.BLUE);
        jp_center.add(jl_out);
        c.add(jp_center, BorderLayout.CENTER);

        super.pack();
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        super.setVisible(true);
    }
}
