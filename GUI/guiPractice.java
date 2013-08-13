package GUI;

import java.io.IOException;

import javax.swing.JFrame;

public class guiPractice {
	public static void main(String[] args) throws IOException {
		ImagePanel p = new ImagePanel();
		p.setResizable(true);	                
		p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p.pack();
		p.setLocationRelativeTo(null);
		p.setVisible(true);
	}
}