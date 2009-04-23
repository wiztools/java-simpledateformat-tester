package org.wiztools.testing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 *
 * @author subwiz
 */
public class MyFrame extends JFrame{

    private static final String NO_RESULT_TEXT = "<no result>";

    private JTextField jtf_in = new JTextField(30);
    private JLabel jl_out = new JLabel(NO_RESULT_TEXT);
    private JComboBox jcb_locale = new JComboBox(Locale.getAvailableLocales());

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
                SimpleDateFormat sdf = new SimpleDateFormat(jtf_in.getText(),
                        (Locale)jcb_locale.getSelectedItem());
                String out = sdf.format(new Date());
                if("".equals(out.trim())){
                    out = NO_RESULT_TEXT;
                }
                jl_out.setText(out);
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
