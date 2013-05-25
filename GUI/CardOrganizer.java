package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import MTGApplication.Card;
import MTGApplication.CollectionMethods;

@SuppressWarnings("serial")
public class CardOrganizer extends JFrame {
	protected ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	protected JTextArea text = new JTextArea();
	protected JScrollPane scrollBox = new JScrollPane(text);
	protected JButton addCard = new JButton("Add Card");
	protected JButton removeCard = new JButton("Remove Card");
	private JButton viewDatabase = new JButton("All Cards");
	protected JComboBox queryColor = new JComboBox(new Object[] {"Color", "Red", "White", "Blue", "Black", "Green", "Multi", "Colorless"});
	protected JComboBox queryPower = new JComboBox(new Object[] {"Power", 1, 2, 3, 4, 5, 6, 7, 8, 9});
	protected JComboBox queryToughness = new JComboBox(new Object[] {"Toughness", 1, 2, 3, 4, 5, 6, 7, 8, 9});
	protected JComboBox queryOwned = new JComboBox(new Object[] {"Owned", 1, 2, 3, 4, 5, 6, 7, 8, 9});
	protected JComboBox queryType1 = new JComboBox(new Object[] {"Type", "Permanent", "Non-Permanent"});
	protected JComboBox queryType2 = new JComboBox(new Object[] {"Subtype", "Enchantment", "Creature", "Artifact", "Instant", "Sorcery"});
	protected JComboBox queryRarity = new JComboBox(new Object[] {"Rarity", "Common", "Uncommon", "Rare", "Mythic"});
	protected JComboBox set = new JComboBox(new Object[] {"Choose Set", "Return to Ravnica", "Gatecrash", "Dragon's Maze", "Innistrad", "Dark Ascension", "Avacyn Restored"});
	protected JButton goQuery = new JButton("Query!");
	protected JButton viewAll = new JButton("My Cards");
	private JTextField textBox = new JTextField("Enter Here");
	private String selected = "no";
	private JList list;
	private JList list2; 
	private String color = "n";
	private int power = -1;
	private int toughness = -1;
	private int owned = -1;
	private String type1 = "n";
	private String type2 = "n";
	private String rarityC = "n";
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem, menuItem2;
	private String[][] data; 
	private String[] queryList;
	private boolean isQuery = false;
	private boolean isText = false;
	private DefaultTableModel dataModel;
	private JTable table;
	private boolean isRarityQuery = false;
	private ArrayList<String[]> rarityArrays = new ArrayList<String[]>();
	private CollectionMethods organizer = new CollectionMethods();
	private boolean isRemoved;
	private JScrollPane scrollList;
	private String[] queryL;


	public CardOrganizer() throws InvalidKeyException, IOException {
		queryL = organizer.getAllArray();
		String home = System.getProperty("user.home");
		try{
			System.setProperty("apple.laf.useScreenMenuBar", "true");
		} catch (Exception ex) {
			System.out.print("Not available in your OS");
		}

		//Create the menu bar.
		menuBar = new JMenuBar();

		//Build the first menu.
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
		menuBar.add(menu);

		//a group of JMenuItems
		menuItem = new JMenuItem("Save",KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Save");
		menu.add(menuItem);

		setJMenuBar(menuBar);

		//menuItem listener
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					organizer.save();
				} catch (InvalidKeyException e1) {
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
			}

		});
		JPanel p5 = new JPanel(new GridLayout(2,1));
		p5.add(viewDatabase);
		p5.add(set);

		//Category combo box panel
		JPanel p = new JPanel(new GridLayout(2,1));
		p.add(viewAll);
		p.add(p5);
	
		//query combo boxes
		JPanel p0 = new JPanel(new GridLayout(8,1));
		p0.add(queryColor);
		p0.add(queryPower);
		p0.add(queryToughness);
		p0.add(queryOwned);
		p0.add(queryType1);
		p0.add(queryType2);
		p0.add(queryRarity);
		p0.add(goQuery);
	
		//button grid panel
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(2,2));
		p1.add(addCard);
		p1.add(removeCard);
		p1.add(p0);
		p1.add(p);

		//textbox + button / query box
		JPanel p2 = new JPanel(new BorderLayout());
		p2.add(textBox, BorderLayout.NORTH);
		p2.add(p1, BorderLayout.CENTER);
		p2.setPreferredSize(new Dimension(250,433));

		//JTable
		JPanel p4 = new JPanel();
		String[] cardNames = organizer.getAllArray();
		Integer[] myCardsOwned = organizer.getOwned();
		String[] stringOwned = new String[myCardsOwned.length];
		String[] rarity = new String[myCardsOwned.length];
		String[] fullList = new String[cardNames.length];
		for(int i = 0; i < myCardsOwned.length; i++) {
			stringOwned[i] = myCardsOwned[i].toString();
		}
		for(int i = 0; i < myCardsOwned.length; i++) {
			String[] splitR = new String[100];
			if(organizer.getCard(cardNames[i]).getRarity() != null) {
			splitR = organizer.getCard(cardNames[i]).getRarity().split(",");
			String[] splitSet = new String[2];
			splitSet = splitR[0].split("-");
			try {
				if(splitSet[1].equals("C"))
					rarity[i] = "Common";
				if(splitSet[1].equals("U"))
					rarity[i] = "Uncommon";
				if(splitSet[1].equals("R"))
					rarity[i] = "Rare";
				if(splitSet[1].equals("M"))
					rarity[i] = "Mythic";
			}catch (ArrayIndexOutOfBoundsException ex) {
				ex.printStackTrace();
			}
		}
	}

		dataModel = new DefaultTableModel();
		dataModel.setColumnCount(3);
		dataModel.setRowCount(organizer.getAllArray().length);
		dataModel.setColumnIdentifiers(new String[]{"Name", "#Owned", "Rarity"});
		for(int i = 0; i < cardNames.length; i++) {
			dataModel.setValueAt(cardNames[i],i, 0);
		}
		for(int i = 0; i < cardNames.length; i++) {
			dataModel.setValueAt(stringOwned[i],i, 1);
		}
		for(int i = 0; i < cardNames.length; i++) {
			dataModel.setValueAt(rarity[i],i, 2);
		}

		table = new JTable(dataModel) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {                
				return false;  
			};
		};

		scrollList = new JScrollPane(table);
		scrollList.setPreferredSize(new Dimension(550,430));
		p4.add(scrollList);

		//card viewing JLabel
		ImageIcon image;
		final JLabel label = new JLabel();
		try {
			image = new ImageIcon(ImageIO.read(new File(home +"/Desktop/VCO/Pictures Try 2/" + "card_back" + ".jpg")));
			label.setIcon(image);
		} catch (Exception ex) {
			label.setIcon(organizer.getCard("card_back").getImg());
		}
		//final formatting
		JPanel p3 = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		//buttons
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;     
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 0;
		p3.add(p2,c);

		//JTable 
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;     
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 0;
		p3.add(p4, c);

		//label orientation
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,15,0,0);
		c.ipady = 0;     
		c.gridwidth = 1;
		c.gridx = 2;
		c.gridy = 0;
		p3.add(label,c);
		add(p3);

		queryColor.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				color = queryColor.getSelectedItem().toString().toLowerCase();
				if(color.equals("color"))
					color = "n";
			}
		});

		queryPower.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(queryPower.getSelectedItem().equals("Power")) {
					power = -1;
				} else {
					power = (Integer) queryPower.getSelectedItem();
				}
			}
		});

		queryToughness.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(queryToughness.getSelectedItem().equals("Toughness")) {
					toughness = -1;
				} else {
					toughness = (Integer) queryToughness.getSelectedItem();
				}
			}
		});

		queryOwned.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(queryOwned.getSelectedItem().equals("Owned")) {
					owned = -1;
				} else {
					owned = (Integer) queryOwned.getSelectedItem();
				}
			}
		});

		queryType1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				type1 = queryType1.getSelectedItem().toString().toLowerCase();
				if(type1.equals("Type"))
					type1 = "n";
				else if  (type1.equals("non-permanent"))
					type1 = "nonPermanent";
			}
		});

		queryType2.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				type2 = queryType2.getSelectedItem().toString().toLowerCase();
				if(type2.equals("subtype"))
					type2 = "n";
			}
		});
		
		queryRarity.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				rarityC = queryRarity.getSelectedItem().toString().toLowerCase();
				if(rarityC.equals("rarity"))
					rarityC = "n";
			}
		});
		
		set.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				String sel = (String) set.getSelectedItem();
				if(sel.equals("Return to Ravnica"))
					sel = "RTR-";
				if(sel.equals("Gatecrash"))
					sel = "GTC-";
				if(sel.equals("Dragon's Maze"))
					sel = "DGM-";
				if(sel.equals("Innistrad"))
					sel = "ISD-";
				if(sel.equals("Dark Ascension"))
					sel = "DKA-";
				if(sel.equals("Avacyn Restored"))
					sel = "AVR-";
				String[] all = null;
				try {
					all = organizer.getSet(sel);
				} catch (InvalidKeyException e1) {
					e1.printStackTrace();
				}
				queryL = all;
				ArrayList<String[]> values = returnValues(all);
				String[] stringOwned = values.get(0);
				String[] rarity = values.get(1);
				int currentRows = dataModel.getRowCount();
				for(int i = 0; i < all.length - currentRows ; i++) { //add appropriate number of rows
					dataModel.addRow(new Object[]{"","",""});
						}
				refreshTable(all, values);
				int toRemove = dataModel.getRowCount() - all.length;
				for(int i = 0; i < toRemove; i++) { // remove excess rows
					if(dataModel.getRowCount() > 3)
						dataModel.removeRow(dataModel.getRowCount() - 1);
				}
			}
		});
		
		//query button listener and table refresher
		goQuery.addActionListener(new ActionListener() {
			@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					queryList = organizer.query(queryL, color, power,toughness,owned, type1, type2, rarityC);
				} catch (InvalidKeyException e1) {
					e1.printStackTrace();
				}
				java.util.Arrays.sort(queryList);
				isQuery = true;
				ArrayList<Integer> ownedList = new ArrayList();
				if(owned == -1 && color.equals("n")
						&& type1.equals("n") && type2.equals("n") && power == -1 && toughness == -1 && rarityC == "n") { //if no input for query
					//Do nothing. I know this is probably a horrible way to do this...
				}else {
					ArrayList<String[]> values = returnValues(queryList);
					String[] stringOwned = values.get(0);
					String[] rarity = values.get(1);
					int toRemove = dataModel.getRowCount() - queryList.length; //get # of rows to remove which = current rowCount - querylist length
					for(int i = 0; i < toRemove; i++) {
						if(dataModel.getRowCount() > 2)
							dataModel.removeRow(dataModel.getRowCount() - 1);
					}	
					int toAdd = queryList.length - dataModel.getRowCount(); //get # of rows to add if previously within shorter query = qList length - rowCount
					for(int i = 0; i < toAdd; i++)
						dataModel.addRow(new Object[]{"","",""});
					
					refreshTable(queryList, values);
				}
			}
		});

		removeCard.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				isRemoved = false;
				try {
					selected = (String) table.getValueAt(table.getSelectedRow(), 0);
						} catch (ArrayIndexOutOfBoundsException ex) {
							selected = textBox.getText();
								}
				if(isText == true) {
					selected = textBox.getText();
						}
//				try {
//					if (queryHashtable.equals("root") && organizer.getCard(selected).owned == 1)
//						isRemoved = true;
//							} catch (InvalidKeyException e1) {
//								e1.printStackTrace();
//									}
//				try {
//					if(queryHashtable.equals("cD") && organizer.getCard(selected).owned == 1)
//						organizer.removeCard(selected, "cD");
//				} catch (InvalidKeyException e1) {
//					e1.printStackTrace();
//				}
//				try {
//					if(selected != null)
//						organizer.removeCard(selected);
//					if(isRemoved == true)
//						organizer.removeCard(selected, "cD");
//							} catch (NullPointerException ex) {
//									ex.printStackTrace();
//										}
				//determine if all view / my view and refresh
				if(isQuery != true && isRarityQuery != true) {
					String[] allList = queryL;
					ArrayList<String[]> values = returnValues(allList);
					String[] stringOwned = values.get(0);
					String[] rarity = values.get(1);
					if(isRemoved == true && dataModel.getRowCount() > 2) { // remove one row if there is no longer a card unless there are only 2 rows
						dataModel.removeRow(1);							   // 2 rows because you need to joggle the selected row to toggle the image...Lazy I know...Will look into it.
					}
					refreshTable(allList, values);
				//determine if query view and refresh
				} else if (isQuery == true) {
					try {
						queryList = organizer.query(queryL, color, power,toughness,owned, type1, type2, rarityC);
					} catch (InvalidKeyException e1) {
						e1.printStackTrace();
					}
					@SuppressWarnings("unchecked")
					String[] all = organizer.getAllArray();
					ArrayList<String[]> values = returnValues(queryList);
					String[] stringOwned = values.get(0);
					String[] rarity = values.get(1);
					if(isRemoved == true && dataModel.getRowCount() > 2)
						dataModel.removeRow(1);
					refreshTable(queryList, values);
						}
				}
		});


		addCard.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					selected = (String) table.getValueAt(table.getSelectedRow(), 0);
						} catch (ArrayIndexOutOfBoundsException ex) {
							selected = textBox.getText();
								}
				if(isText == true) {
					selected = textBox.getText();
							}
				try {
					if(selected != null)
						organizer.addCard(selected);
							} catch (InvalidKeyException e1) {
									e1.printStackTrace();
										}
				//determine if all view and refresh
				if(isQuery != true && isRarityQuery != true) {
					String[] allList = queryL;
					ArrayList<String[]> values = returnValues(allList);
					String[] stringOwned = values.get(0);
					String[] rarity = values.get(1);
					try {
						if(organizer.getCard(selected).owned == 1)
							dataModel.addRow(new Object[]{"","",""}); //only add row if card doesn't exist yet
								} catch (InvalidKeyException e1) {
									e1.printStackTrace();
										}
					refreshTable(allList, values);
				//determine if query view and refresh
				} else if(isQuery == true) {
					try {
						queryList = organizer.query(queryL, color, power,toughness,owned, type1, type2, rarityC);
					} catch (InvalidKeyException e2) {
						e2.printStackTrace();
					}
					@SuppressWarnings("unchecked")
					ArrayList<String[]> values = returnValues(queryList);
					String[] stringOwned = values.get(0);
					String[] rarity = values.get(1);
					try {
						if(organizer.getCard(selected).owned == 1)
							dataModel.addRow(new Object[]{"","",""});
								} catch (InvalidKeyException e1) {
									e1.printStackTrace();
										}
					refreshTable(queryList, values);
						}
				}
		});

		viewAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				queryL = organizer.getAllArray();
				isQuery = false;
				isRarityQuery = false;
				String[] all = organizer.getAllArray();
				ArrayList<String[]> values = returnValues(all);
				String[] stringOwned = values.get(0);
				String[] rarity = values.get(1);
				int currentRows = dataModel.getRowCount();
				for(int i = 0; i < organizer.getAllArray(). length - currentRows ; i++) { //add appropriate number of rows
					dataModel.addRow(new Object[]{"","",""});
						}
				refreshTable(all, values);
				int toRemove = dataModel.getRowCount() - all.length;
				for(int i = 0; i < toRemove; i++) {
					if(dataModel.getRowCount() > 3)
						dataModel.removeRow(dataModel.getRowCount() - 1);
				}
			}
		});
		
		viewDatabase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				queryL = organizer.getCategory("cD");
				isQuery = false;
				isRarityQuery = false;
				String[] all = organizer.getCategory("cD");
				ArrayList<String[]> values = returnValues(all);
				String[] stringOwned = values.get(0);
				String[] rarity = values.get(1);
				int currentRows = dataModel.getRowCount();
				for(int i = 0; i < organizer.getCategory("cD"). length - currentRows ; i++) { //add appropriate number of rows
					dataModel.addRow(new Object[]{"","",""});
						}
				refreshTable(all, values);
			}
		});

		//listens for textBox input
		textBox.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				warn();
					}
			public void removeUpdate(DocumentEvent e) {
				warn();
					}
			public void insertUpdate(DocumentEvent e) {
				warn();
					}
			public void warn() {
				isText = true;
			}
		});
		
		//detects selected table row and updates image icon
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
				selected = (String) table.getValueAt(table.getSelectedRow(), 0);
				} catch (ArrayIndexOutOfBoundsException e1) {
					//Do nothing for now...until I'm clever / motivated enough to do something
				}
				isText = false;
				if(selected != null) {
					try{
						try {
							label.setIcon(organizer.getCard(selected).getImg());
								} catch (MalformedURLException e1) {
									e1.printStackTrace();
								} catch (IOException e2) {
									e2.printStackTrace();
										}
									} catch (InvalidKeyException i2) {
										i2.printStackTrace();
											}
				}
			}
		});

	}
	/**
	 * This method takes as a parameter the String[] of card names and then an ArrayList<String[]>
	 * of the number owned and rarity in their appropriate String[] and refreshes the JTable with this
	 * data. There are two parameters vs just one due to the structure upstream but mostly pure laziness :D! 
	 * @param s
	 * @param l
	 */
	public void refreshTable(String[] s, ArrayList<String[]> l) {
		String[] all = s;
		String[] stringOwned = l.get(0);
		String[] rarity = l.get(1);
		
		for(int i = 0; i < dataModel.getRowCount(); i++) {
			dataModel.setValueAt(null,i, 0);
		}
		for(int i = 0; i < dataModel.getRowCount(); i++) {
			dataModel.setValueAt(null,i, 1);
		}
		for(int i = 0; i < dataModel.getRowCount(); i++) {
			dataModel.setValueAt(null,i, 2);
		}
		for(int i = 0; i < all.length; i++) {
			dataModel.setValueAt(all[i],i, 0);
		}
		for(int i = 0; i < all.length; i++) {
			dataModel.setValueAt(stringOwned[i],i, 1);
		}
		for(int i = 0; i < all.length; i++) {
			dataModel.setValueAt(rarity[i],i, 2);
		}
		table.repaint(); 
	}
	/**
	 * This method, given a String[] containing a list of names, returns an ArrayList<String[]> containing
	 * the String[]s for the number of each card owned and its rarity.
	 * @param s
	 * @return
	 */
	public ArrayList<String[]> returnValues(String[] s) {
		ArrayList<Integer> ownedList = new ArrayList();
		String[] category = s;
		for( int i = 0; i < category.length; i++) {
			try {
				ownedList.add(organizer.getCard(category[i]).getOwned()); //put owned ints into ownedList
			} catch (InvalidKeyException e1) {
				e1.printStackTrace();
			}
		}
		//convert ownedList<Integer> to String[] stringOwned
		Integer[] arr2= new Integer[ownedList.size()];
		ownedList.toArray(arr2);
		String[] stringOwned = new String[arr2.length];
		for(int i = 0; i < category.length; i++) {
			stringOwned[i] = Integer.toString(arr2[i]);
		}
		//split the rarity out of each card given the name list
		String[] rarity = new String[category.length];
		for(int i = 0; i < category.length; i++) {
			try {
				if(organizer.getCard(category[i]).getRarity() != null) {
				String[] splitR = new String[100];
				try {
					splitR = organizer.getCard(category[i]).getRarity().split(",");
				} catch (InvalidKeyException e1) {
					e1.printStackTrace();
				}
				String[] splitSet = new String[2];
				splitSet = splitR[0].split("-");
				try {
					if(splitSet[1].equals("C"))
						rarity[i] = "Common";
					if(splitSet[1].equals("U"))
						rarity[i] = "Uncommon";
					if(splitSet[1].equals("R"))
						rarity[i] = "Rare";
					if(splitSet[1].equals("M"))
						rarity[i] = "Mythic";
				}catch (ArrayIndexOutOfBoundsException ex) {
					ex.printStackTrace();
				}
}
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			}
	}
		ArrayList<String[]> strings = new ArrayList<String[]>();
		strings.add(stringOwned);
		strings.add(rarity);
		return strings;
	}
	
		public static void main(String[] args) throws InvalidKeyException, IOException {
		CardOrganizer frame = null;
		try {
			frame = new CardOrganizer();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		frame.setTitle("Virtual Card Organizer");
		frame.setSize(1085,480);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
	}
}
