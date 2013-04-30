package GUI;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class tableModel {

public static void main(String[] args) {
	  TableModel dataModel1 = new AbstractTableModel() {
          public int getColumnCount() { return 5; }
          public int getRowCount() { return 5;}
          public Object getValueAt(int row, int col) { return new Integer(row*col); }
      };
      
      JTable table = new JTable(dataModel1);
      JScrollPane scrollpane = new JScrollPane(table);
      JFrame frame = new JFrame();
      frame.add(table);
  	frame.setTitle("Virtual Card Organizer");
	frame.setSize(1060,480);
	frame.setLocationRelativeTo(null);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
	frame.setResizable(false);
}
}