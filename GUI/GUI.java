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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.util.ArrayList;
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
public class GUI extends JFrame {
	protected ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	protected JTextArea text = new JTextArea();
	protected JScrollPane scrollBox = new JScrollPane(text);
	protected JButton addCard = new JButton("Add Card");
	protected JButton removeCard = new JButton("Remove Card");
	protected JComboBox viewCat = new JComboBox(new Object[] {"View Category", "all", "red", "white", "blue", "black", "green", "multi", "colorless"});
	protected JComboBox queryColor = new JComboBox(new Object[] {"Color", "red", "white", "blue", "black", "green", "multi", "colorless"});
	protected JComboBox queryPower = new JComboBox(new Object[] {"Power", 1, 2, 3, 4, 5, 6, 7, 8, 9});
	protected JComboBox queryToughness = new JComboBox(new Object[] {"Toughness", 1, 2, 3, 4, 5, 6, 7, 8, 9});
	protected JComboBox queryOwned = new JComboBox(new Object[] {"Owned", 1, 2, 3, 4, 5, 6, 7, 8, 9});
	protected JComboBox queryType1 = new JComboBox(new Object[] {"Type", "permanent", "nonPermanent"});
	protected JComboBox queryType2 = new JComboBox(new Object[] {"Subtype", "enchantment", "creature", "artifact", "instant", "sorcery"});
	protected JComboBox queryRarity = new JComboBox(new Object[] {"Rarity", "Common", "Uncommon", "Rare", "Mythic"});
	protected JButton goQuery = new JButton("Query!");
	protected JButton viewAll = new JButton("View All");
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
	private String rarityQ = "";
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem, menuItem2;
	private String[][] data; 
	private String[] queryList;
	private boolean isQuery = false;
	private boolean isText = false;
	private DefaultTableModel dataModel;
	private JTable table;

	public GUI() throws InvalidKeyException, IOException {
		final CollectionMethods organizer = new CollectionMethods();
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

		//Category combo box panel
		JPanel p = new JPanel(new BorderLayout());
		p.add(viewCat, BorderLayout.NORTH);

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
		p1.add(viewAll);

		//textbox + button / query box
		JPanel p2 = new JPanel(new BorderLayout());
		p2.add(textBox, BorderLayout.NORTH);
		p2.add(p1, BorderLayout.CENTER);
		p2.setPreferredSize(new Dimension(300,433));

		//JTable
		JPanel p4 = new JPanel();
		String[] cardNames = organizer.getAllArray();
		Integer[] cardsOwned = organizer.getOwned();
		String[] stringOwned = new String[cardsOwned.length];
		String[] rarity = new String[cardsOwned.length];
		String[] fullList = new String[cardNames.length];
		for(int i = 0; i < cardsOwned.length; i++) {
			stringOwned[i] = cardsOwned[i].toString();
		}
		for(int i = 0; i < cardsOwned.length; i++) {
			String[] splitR = new String[100];
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

		JScrollPane scrollList = new JScrollPane(table);
		scrollList.setPreferredSize(new Dimension(500,430));
		p4.add(scrollList);

		//card viewing JLabel
		URL url = new URL("http://upload.wikimedia.org/wikipedia/en/thumb/a/aa/Magic_the_gathering-card_back.jpg/200px-Magic_the_gathering-card_back.jpg");
		ImageIcon ii = new ImageIcon(url);
		final JLabel label = new JLabel(ii);

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

		//combo listener
		viewCat.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if((String)viewCat.getSelectedItem() == "all") {
					list.setListData(organizer.getAllArray());
					list2.setListData(organizer.getOwned());
				} else {
					list.setListData(organizer.getCategory((String)viewCat.getSelectedItem()));
				}
			}
		});

		queryColor.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				color = (String) queryColor.getSelectedItem();
				if(color.equals("Color"))
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
				type1 = (String) queryType1.getSelectedItem();
				if(type1.equals("Type1"))
					type1 = "n";
			}
		});

		queryType2.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				type2 = (String) queryType2.getSelectedItem();
				if(type2.equals("Type2"))
					type2 = "n";
			}
		});
		
		queryRarity.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				rarityQ = (String) queryRarity.getSelectedItem();
				ArrayList<String> arr = new ArrayList();
				if(rarityQ.equals("Common"))
					rarityQ = "C";
				if(rarityQ.equals("Uncommon"))
					rarityQ = "U";
				if(rarityQ.equals("Rare"))
					rarityQ = "R";
				if(rarityQ.equals("Mythic"))
					rarityQ = "M";
				
				//System.out.println(rarityQ + "\n");
				String rarity = "";
				String[] all = organizer.getAllArray();
				//System.out.println(java.util.Arrays.toString(all));
				ArrayList<Card> list = new ArrayList<Card>();
				for(int i = 0; i < all.length; i++) {
					try {
					list.add(organizer.getCard(all[i]));
					//System.out.println(organizer.getCard(all[i]).name);
					} catch (InvalidKeyException e1) {
						e1.printStackTrace();
					}
				}
				String[] splitR;
				Card card = new Card();
				for(int j = 0; j < list.size(); j++) {
					card = list.get(j);
					if(card != null)
						rarity = card.rarity;
					splitR = rarity.split(",");
					String[] splitSet = new String[2];
					splitSet = splitR[0].split("-");
					rarity = splitSet[1];
					//System.out.println(rarity);
					if(rarity.equals(rarityQ))
						arr.add(card.name);
				}
				String[] arr2= new String[arr.size()];
				arr.toArray(arr2);
				//System.out.println(arr2.length);
				//System.out.println(java.util.Arrays.toString(arr2));

				String[] rarityL = new String[arr2.length];
				for(int i = 0; i < arr2.length; i++) {
					String[] splitR2 = new String[100];
					try {
						splitR2 = organizer.getCard(arr2[i]).getRarity().split(",");
					} catch (InvalidKeyException e1) {
						e1.printStackTrace();
					}
					String[] splitSet = new String[2];
					splitSet = splitR2[0].split("-");
					try {
						if(splitSet[1].equals("C"))
							rarityL[i] = "Common";
						if(splitSet[1].equals("U"))
							rarityL[i] = "Uncommon";
						if(splitSet[1].equals("R"))
							rarityL[i] = "Rare";
						if(splitSet[1].equals("M"))
							rarityL[i] = "Mythic";
					}catch (ArrayIndexOutOfBoundsException ex) {
						ex.printStackTrace();
					}
				}
				ArrayList<Integer> ownedList = new ArrayList();
				for( int i = 0; i < arr2.length; i++) {
					try {
						ownedList.add(organizer.getCard(arr2[i]).getOwned());
						//System.out.println(organizer.getCard(arr2[i]).getOwned());
					} catch (InvalidKeyException e1) {
						e1.printStackTrace();
					}
				}
				//convert ArrayList to Integer[]
				Integer[] arr3= new Integer[ownedList.size()];
				ownedList.toArray(arr3);
				String[] stringOwned = new String[arr3.length];
				for(int i = 0; i < arr2.length; i++) {
					stringOwned[i] = Integer.toString(arr3[i]);
				}
				
				//System.out.println(java.util.Arrays.toString(arr2));
				//System.out.println(java.util.Arrays.toString(stringOwned));
				//System.out.println(java.util.Arrays.toString(rarityL));

				int toRemove = dataModel.getRowCount() - arr2.length;
				for(int i = 0; i < toRemove; i++) {
					if(dataModel.getRowCount() > 3)
						dataModel.removeRow(dataModel.getRowCount() - 1);
				}	
				int toAdd = arr2.length - dataModel.getRowCount();
				for(int i = 0; i < toAdd; i++)
					dataModel.addRow(new Object[]{"","",""});
				for(int i = 0; i < dataModel.getRowCount(); i++) {
					dataModel.setValueAt(null,i, 0);
				}
				for(int i = 0; i < dataModel.getRowCount(); i++) {
					dataModel.setValueAt(null,i, 1);
				}
				for(int i = 0; i < dataModel.getRowCount(); i++) {
					dataModel.setValueAt(null,i, 2);
				}
				for(int i = 0; i < arr2.length; i++) {
					dataModel.setValueAt(arr2[i],i, 0);
				}
				for(int i = 0; i < arr2.length; i++) {
					dataModel.setValueAt(stringOwned[i],i, 1);
				}
				for(int i = 0; i < arr2.length; i++) {
					dataModel.setValueAt(rarityL[i],i, 2);
				}
			table.repaint();
			}
		});
		//query button listener and table refresher
		goQuery.addActionListener(new ActionListener() {
			@SuppressWarnings("rawtypes")
			@Override
			public void actionPerformed(ActionEvent e) {
				queryList = organizer.query(color, power,toughness,owned, type1, type2);
				isQuery = true;
				@SuppressWarnings("unchecked")
				ArrayList<Integer> ownedList = new ArrayList();
				if(owned == -1 && color.equals("n")
						&& type1.equals("n") && type2.equals("n") && power == -1 && toughness == -1) {
					//Do nothing
				} else {
					for( int i = 0; i < queryList.length; i++) {
						try {
							ownedList.add(organizer.getCard(queryList[i]).getOwned());
						} catch (InvalidKeyException e1) {
							e1.printStackTrace();
						}
					}
				}
				//convert ArrayList to Integer[]
				Integer[] arr2= new Integer[ownedList.size()];
				ownedList.toArray(arr2);
				final String[] stringOwned = new String[arr2.length];

				if(owned == -1 && color.equals("n")
						&& type1.equals("n") && type2.equals("n") && power == -1 && toughness == -1) {
					//Do nothing
				}else {
					for(int i = 0; i < queryList.length; i++) {
						stringOwned[i] = Integer.toString(arr2[i]);
					}
					final String[] rarity = new String[queryList.length];
					for(int i = 0; i < queryList.length; i++) {
						String[] splitR = new String[100];
						try {
							splitR = organizer.getCard(queryList[i]).getRarity().split(",");
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
					int toRemove = dataModel.getRowCount() - queryList.length;
					for(int i = 0; i < toRemove; i++) {
						if(dataModel.getRowCount() > 2)
							dataModel.removeRow(dataModel.getRowCount() - 1);
					}	
					int toAdd = queryList.length - dataModel.getRowCount();
					for(int i = 0; i < toAdd; i++)
						dataModel.addRow(new Object[]{"","",""});
					for(int i = 0; i < dataModel.getRowCount(); i++) {
						dataModel.setValueAt(null,i, 0);
					}
					for(int i = 0; i < dataModel.getRowCount(); i++) {
						dataModel.setValueAt(null,i, 1);
					}
					for(int i = 0; i < dataModel.getRowCount(); i++) {
						dataModel.setValueAt(null,i, 2);
					}
					for(int i = 0; i < queryList.length; i++) {
						dataModel.setValueAt(queryList[i],i, 0);
					}
					for(int i = 0; i < queryList.length; i++) {
						dataModel.setValueAt(stringOwned[i],i, 1);
					}
					for(int i = 0; i < queryList.length; i++) {
						dataModel.setValueAt(rarity[i],i, 2);
					}
				}	
				table.repaint(); 
			}
		});

		removeCard.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isRemoved = false;
				try {
					selected = (String) table.getValueAt(table.getSelectedRow(), 0);
				} catch (ArrayIndexOutOfBoundsException ex) {
					selected = textBox.getText();
				}
				if(isText == true) {
					selected = textBox.getText();
				}
				try {
					if (organizer.getCard(selected).owned == 1)
						isRemoved = true;
				} catch (InvalidKeyException e1) {
					e1.printStackTrace();
				}
				try {
					if(selected != null)
						organizer.removeCard(selected);
				} catch (NullPointerException ex) {
					ex.printStackTrace();
				}
				//determine if all view and refresh
				if(isQuery != true) {
					String[] allList = organizer.getAllArray();
					ArrayList<Integer> ownedL = new ArrayList();
					for( int i = 0; i < allList.length; i++) {
						try {
							ownedL.add(organizer.getCard(allList[i]).getOwned());
						} catch (InvalidKeyException e1) {
							e1.printStackTrace();
						}
					}
					//convert ArrayList to Integer[] to String[]
					Integer[] arr2= new Integer[ownedL.size()];
					ownedL.toArray(arr2);
					String[] stringOwned = new String[arr2.length];
					for(int i = 0; i < allList.length; i++) {
						stringOwned[i] = Integer.toString(arr2[i]);
					}
					String[] rarity = new String[allList.length];
					for(int i = 0; i < allList.length; i++) {
						String[] splitR = new String[100];
						try {
							splitR = organizer.getCard(allList[i]).getRarity().split(",");
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
					if(isRemoved == true && dataModel.getRowCount() > 2) {
						dataModel.removeRow(1);
					}
					for(int i = 0; i < dataModel.getRowCount(); i++) {
						dataModel.setValueAt(null,i, 0);
					}
					for(int i = 0; i < dataModel.getRowCount(); i++) {
						dataModel.setValueAt(null,i, 1);
					}
					for(int i = 0; i < dataModel.getRowCount(); i++) {
						dataModel.setValueAt(null,i, 2);
					}	
					for(int i = 0; i < allList.length; i++) {
						dataModel.setValueAt(allList[i],i, 0);
					}
					for(int i = 0; i < allList.length; i++) {
						dataModel.setValueAt(stringOwned[i],i, 1);
					}
					for(int i = 0; i < allList.length; i++) {
						dataModel.setValueAt(rarity[i],i, 2);
					}
					table.repaint(); 
					//determine if queryview and refresh
				} else {
					queryList = organizer.query(color, power,toughness,owned, type1, type2);
					@SuppressWarnings("unchecked")
					ArrayList<Integer> ownedList = new ArrayList();
					for( int i = 0; i < queryList.length; i++) {
						try {
							ownedList.add(organizer.getCard(queryList[i]).getOwned());
						} catch (InvalidKeyException e1) {
							e1.printStackTrace();
						}
					}
					//convert ArrayList to Integer[]
					Integer[] arr2= new Integer[ownedList.size()];
					ownedList.toArray(arr2);
					String[] stringOwned = new String[arr2.length];

					for(int i = 0; i < queryList.length; i++) {
						stringOwned[i] = Integer.toString(arr2[i]);
					}
					String[] rarity = new String[queryList.length];
					for(int i = 0; i < queryList.length; i++) {
						String[] splitR = new String[100];
						try {
							splitR = organizer.getCard(queryList[i]).getRarity().split(",");
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
					if(isRemoved == true && dataModel.getRowCount() > 2)
						dataModel.removeRow(1);
					for(int i = 0; i < dataModel.getRowCount(); i++) {
						dataModel.setValueAt(null,i, 0);
					}
					for(int i = 0; i < dataModel.getRowCount(); i++) {
						dataModel.setValueAt(null,i, 1);
					}
					for(int i = 0; i < dataModel.getRowCount(); i++) {
						dataModel.setValueAt(null,i, 2);
					}
					for(int i = 0; i < queryList.length; i++) {
						dataModel.setValueAt(queryList[i],i, 0);
					}
					for(int i = 0; i < queryList.length; i++) {
						dataModel.setValueAt(stringOwned[i],i, 1);
					}
					for(int i = 0; i < queryList.length; i++) {
						dataModel.setValueAt(rarity[i],i, 2);
					}
					table.repaint(); 
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
				if(isQuery != true) {
					String[] allList = organizer.getAllArray();
					ArrayList<Integer> ownedL = new ArrayList();
					for( int i = 0; i < allList.length; i++) {
						try {
							ownedL.add(organizer.getCard(allList[i]).getOwned());
						} catch (InvalidKeyException e1) {
							e1.printStackTrace();
						}
					}
					//convert ArrayList to Integer[] to String[]
					Integer[] arr2= new Integer[ownedL.size()];
					ownedL.toArray(arr2);
					String[] stringOwned = new String[arr2.length];
					for(int i = 0; i < allList.length; i++) {
						stringOwned[i] = Integer.toString(arr2[i]);
					}
					String[] rarity = new String[allList.length];
					for(int i = 0; i < allList.length; i++) {
						String[] splitR = new String[100];
						try {
							splitR = organizer.getCard(allList[i]).getRarity().split(",");
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
					try {
						if(organizer.getCard(selected).owned == 1)
							dataModel.addRow(new Object[]{"","",""});
					} catch (InvalidKeyException e1) {
						e1.printStackTrace();
					}
					for(int i = 0; i < dataModel.getRowCount(); i++) {
						dataModel.setValueAt(null,i, 0);
					}
					for(int i = 0; i < dataModel.getRowCount(); i++) {
						dataModel.setValueAt(null,i, 1);
					}
					for(int i = 0; i < dataModel.getRowCount(); i++) {
						dataModel.setValueAt(null,i, 2);
					}
					for(int i = 0; i < allList.length; i++) {
						dataModel.setValueAt(allList[i],i, 0);
					}
					for(int i = 0; i < allList.length; i++) {
						dataModel.setValueAt(stringOwned[i],i, 1);
					}
					for(int i = 0; i < allList.length; i++) {
						dataModel.setValueAt(rarity[i],i, 2);
					}
					table.repaint(); 
					//determine if query view and refresh
				} else {
					queryList = organizer.query(color, power,toughness,owned, type1, type2);
					@SuppressWarnings("unchecked")
					ArrayList<Integer> ownedList = new ArrayList();
					for( int i = 0; i < queryList.length; i++) {
						try {
							ownedList.add(organizer.getCard(queryList[i]).getOwned());
						} catch (InvalidKeyException e1) {
							e1.printStackTrace();
						}
					}
					//convert ArrayList to Integer[]
					Integer[] arr2= new Integer[ownedList.size()];
					ownedList.toArray(arr2);
					String[] stringOwned = new String[arr2.length];

					for(int i = 0; i < queryList.length; i++) {
						stringOwned[i] = Integer.toString(arr2[i]);
					}
					String[] rarity = new String[queryList.length];
					for(int i = 0; i < queryList.length; i++) {
						String[] splitR = new String[100];
						try {
							splitR = organizer.getCard(queryList[i]).getRarity().split(",");
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
					try {
						if(organizer.getCard(selected).owned == 1)
							dataModel.addRow(new Object[]{"","",""});
					} catch (InvalidKeyException e1) {
						e1.printStackTrace();
					}						for(int i = 0; i < dataModel.getRowCount(); i++) {
						dataModel.setValueAt(null,i, 0);
					}
					for(int i = 0; i < dataModel.getRowCount(); i++) {
						dataModel.setValueAt(null,i, 1);
					}
					for(int i = 0; i < dataModel.getRowCount(); i++) {
						dataModel.setValueAt(null,i, 2);
					}
					for(int i = 0; i < queryList.length; i++) {
						dataModel.setValueAt(queryList[i],i, 0);
					}
					for(int i = 0; i < queryList.length; i++) {
						dataModel.setValueAt(stringOwned[i],i, 1);
					}
					for(int i = 0; i < queryList.length; i++) {
						dataModel.setValueAt(rarity[i],i, 2);
					}
					table.repaint(); 
				}
			}
		});

		viewAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isQuery = false;
				ArrayList<Integer> ownedList = new ArrayList();
				String[] all = organizer.getAllArray();
				for( int i = 0; i < all.length; i++) {
					try {
						ownedList.add(organizer.getCard(all[i]).getOwned());
					} catch (InvalidKeyException e1) {
						e1.printStackTrace();
					}
				}
				//convert ArrayList to Integer[]
				Integer[] arr2= new Integer[ownedList.size()];
				ownedList.toArray(arr2);
				String[] stringOwned = new String[arr2.length];
				for(int i = 0; i < all.length; i++) {
					stringOwned[i] = Integer.toString(arr2[i]);
				}
				//split the rarity out of each card given the name list
				String[] rarity = new String[all.length];
				for(int i = 0; i < all.length; i++) {
					String[] splitR = new String[100];
					try {
						splitR = organizer.getCard(all[i]).getRarity().split(",");
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
				int currentRows = dataModel.getRowCount();
				for(int i = 0; i < organizer.getAllArray(). length - currentRows ; i++) {
					dataModel.addRow(new Object[]{"","",""});
				}
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
		});

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

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				selected = (String) table.getValueAt(table.getSelectedRow(), 0);
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
					}catch (InvalidKeyException i2) {
						i2.printStackTrace();
					}
				}
			}
		});

	}

	public static void main(String[] args) throws InvalidKeyException, IOException {
		GUI frame = null;
		try {
			frame = new GUI();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		frame.setTitle("Virtual Card Organizer");
		frame.setSize(1060,480);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		//frame.setResizable(false);
	}
}
