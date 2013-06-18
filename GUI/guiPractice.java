package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.net.MalformedURLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class guiPractice extends JFrame{
	private static JToggleButton red = new JToggleButton("R");
	private static JToggleButton white = new JToggleButton("W");
	private static JToggleButton blue = new JToggleButton("B");
	private static JToggleButton green = new JToggleButton("G");
	private static JToggleButton black = new JToggleButton("B");
	private static JToggleButton colorless = new JToggleButton("CL");
	private static JToggleButton multi = new JToggleButton("MU");
	guiPractice() {}
	
	public static void main(String[] args) {
	JButton button;
	guiPractice frame = new guiPractice();
	frame.setLayout(new GridBagLayout());
	GridBagConstraints d = new GridBagConstraints();
	
	
	JPanel tR = new JPanel(new GridBagLayout());
	JPanel p2 = new JPanel(new GridBagLayout());

	JTextField t = new JTextField();
	d.fill = GridBagConstraints.HORIZONTAL;
	d.weightx = 0.0;
	//d.weighty = 1.0;
	d.gridwidth = 2;
	d.gridx = 0;
	d.gridy = 0;
	d.anchor = GridBagConstraints.PAGE_START;
	tR.add(t, d);
	
	button = new JButton("+");
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
	
	JLabel colors = new JLabel("Color Query");
	d.fill = GridBagConstraints.HORIZONTAL;
	d.weighty = 0.0;
	d.ipady = 10;   	//make this component tall
	d.weightx = 0.5;
	d.gridwidth = 1;
	d.gridx = 0;
	d.gridy = 2;
	tR.add(colors, d);
		
//	power JToggle buttons
	JPanel colorBox = new JPanel(new GridLayout(1,7));
	colorBox.add(red);
	colorBox.add(blue);
	colorBox.add(white);
	colorBox.add(black);
	colorBox.add(green);
	colorBox.add(multi);
	colorBox.add(colorless);
	colorBox.setPreferredSize(new Dimension(90,50));

	d.gridy = 0;
	d.gridx = 0;
	d.weighty = 0.0;
	d.anchor = GridBagConstraints.PAGE_START;
	p2.add(tR, d);
	d.gridy = 1;
	d.gridx = 0;
	d.weighty = 1.0;
	p2.add(colorBox, d);
	
	JRadioButton r = new JRadioButton();
	JRadioButton r2 = new JRadioButton();
	d.gridx = 0;
	d.gridy = 2;
	p2.add(r,d);
	d.gridx =1;
	p2.add(r2,d);

	
	frame.add(p2,d);

	frame.setTitle("Virtual Card Organizer");
	frame.setSize(500,500);
	frame.setLocationRelativeTo(null);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);

	}
}
