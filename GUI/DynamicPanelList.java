package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;

public class DynamicPanelList {
	private static JToggleButton red = new JToggleButton("R");
	private static JToggleButton white = new JToggleButton("W");
	private static JToggleButton blue = new JToggleButton("B");
	private static JToggleButton green = new JToggleButton("G");
	private static JToggleButton black = new JToggleButton("B");
	private static JToggleButton colorless = new JToggleButton("CL");
	private static JToggleButton multi = new JToggleButton("MU");

    public static void main(String[] args) {
        new DynamicPanelList();
    }

    public DynamicPanelList() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception ex) {
                }

                JFrame frame = new JFrame("Test");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new TestPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class TestPane extends JPanel {

        private JPanel mainList;

        public TestPane() {
            setLayout(new BorderLayout());

            mainList = new JPanel(new GridBagLayout());
            GridBagConstraints d = new GridBagConstraints();
            d.gridwidth = GridBagConstraints.REMAINDER;
            d.weightx = 1;
            d.weighty = 1;
            mainList.add(new JPanel(), d);

            add(new JScrollPane(mainList));

            JButton add = new JButton("Add");
        
            JPanel tR = new JPanel(new GridBagLayout());
            tR.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));


        	JTextField t = new JTextField();
        	d.fill = GridBagConstraints.HORIZONTAL;
        	d.weightx = 0.0;
        	//d.weighty = 1.0;
        	d.gridwidth = 2;
        	d.gridx = 0;
        	d.gridy = 0;
        	d.anchor = GridBagConstraints.PAGE_START;
        	tR.add(t, d);

        	JButton button = new JButton("+");
        	d.fill = GridBagConstraints.HORIZONTAL;
        	//d.weighty = 0.0;
        	d.ipady = 10;      //make this component tall
        	d.weightx = 0.5;
        	d.gridwidth = 1;
        	d.gridx = 0;
        	d.gridy = 1;
        	tR.add(button, d);

        	
        	button = new JButton("-");
        	d.fill = GridBagConstraints.HORIZONTAL;
        	d.weighty = 0.0;
        	d.ipady = 10;   	//make this component tall
        	d.weightx = 0.5;
        	d.gridwidth = 1;
        	d.gridx = 1;
        	d.gridy = 1;
        	//d.anchor = GridBagConstraints.PAGE_END;
        	tR.add(button, d);
        	
        	d.gridx = 0;
        	d.gridy = 0;
            mainList.add(tR, d);

            JPanel tR2 = new JPanel(new GridBagLayout());
            //tR2.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
        	JLabel colors = new JLabel("Color Query");
        	d.fill = GridBagConstraints.HORIZONTAL;
        	d.weighty = 0.0;
        	d.ipady = 10;   	//make this component tall
        	d.gridx = 0;
        	d.gridy = 0;
        	tR2.add(colors, d);
        		
//        	power JToggle buttons
        	JPanel colorBox = new JPanel(new GridLayout(1,7));
        	colorBox.add(red);
        	colorBox.add(blue);
        	colorBox.add(white);
        	colorBox.add(black);
        	colorBox.add(green);
        	colorBox.add(multi);
        	colorBox.add(colorless);
        	colorBox.setPreferredSize(new Dimension(90,50));
        	d.gridx = 0;
        	d.gridy = 1;
        	tR2.add(colorBox,d);
         	
        	d.gridx = 0;
        	d.gridy = 1;
        	d.weighty = 1.0;
        	mainList.add(tR2,d);
        	
//                    JPanel panel = new JPanel();
//                    panel.add(new JLabel("Hello"));
//                    panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
//                    gbc.gridwidth = GridBagConstraints.REMAINDER;
//                    gbc.weightx = 1;
//                    gbc.fill = GridBagConstraints.HORIZONTAL;
//
//                    validate();
//                    repaint();
   

            //add(add, BorderLayout.SOUTH);

        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(200, 200);
        }
    }
}
