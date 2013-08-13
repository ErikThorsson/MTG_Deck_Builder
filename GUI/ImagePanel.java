package GUI;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class ImagePanel extends JFrame{
	
public ImagePanel() throws IOException {
	Image img = ImageIO.read(new File("/Users/eorndahl/Desktop/VCO/Pictures Try 2/" + "card_back" + ".jpg"));
	JLabel picL = new JLabel(new ImageIcon(img));
	JPanel pic = new JPanel();
	pic.add(picL);
	JLabel j = new JLabel("test");
	pic.add(j);
	this.add(pic);
	
    this.addComponentListener(new ComponentAdapter() {
        public void componentResized(ComponentEvent e) {
            // This is only called when the user releases the mouse button.
        	System.out.println("resized");
        }
    });
}

// These methods do not appear to be called at all when a JFrame
// is being resized.
@Override
public void setSize(int width, int height) {
    System.out.println("setSize");
}

@Override
public void setBounds(Rectangle r) {
    System.out.println("setBounds A");
}

@Override
public void setBounds(int x, int y, int width, int height) {
    System.out.println("setBounds B");
}

@Override
public void validate() {
}
}
