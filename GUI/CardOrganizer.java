package GUI;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Robot;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.openqa.jetty.html.Image;

import MTGApplication.Card;
import MTGApplication.CollectionMethods;

@SuppressWarnings("serial")
public class CardOrganizer extends JFrame {
	protected ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	protected JTextArea text = new JTextArea();
	protected JScrollPane scrollBox = new JScrollPane(text);
	protected JButton addCard = new JButton("+");
	protected JButton removeCard = new JButton("-");
	private JButton viewDatabase = new JButton("All Cards");
	protected JComboBox queryColor = new JComboBox(new Object[] {"Color", "Red", "White", "Blue", "Black", "Green", "Multi", "Colorless"});
	protected JComboBox queryPower = new JComboBox(new Object[] {"Power", 1, 2, 3, 4, 5, 6, 7, 8, 9});
	protected JComboBox queryToughness = new JComboBox(new Object[] {"Toughness", 1, 2, 3, 4, 5, 6, 7, 8, 9});
	protected JComboBox queryOwned = new JComboBox(new Object[] {"Owned", 1, 2, 3, 4, 5, 6, 7, 8, 9});
	protected JComboBox queryType1 = new JComboBox(new Object[] {"Type", "Permanent", "Non-Permanent"});
	protected JComboBox queryType2 = new JComboBox(new Object[] {"Subtype", "Enchantment", "Creature", "Artifact", "Instant", "Sorcery", "Planeswalker", "Land"});
	protected JComboBox queryRarity = new JComboBox(new Object[] {"Rarity", "Common", "Uncommon", "Rare", "Mythic"});
	protected JComboBox queryTribal = new JComboBox(new Object[] {"Tribal", "Yes (Type in text box)"});
	protected JComboBox set = new JComboBox(new Object[] {"Choose Set", "Return to Ravnica", "Gatecrash", "Dragon's Maze", "Innistrad", "Dark Ascension", "Avacyn Restored"});
	private JToggleButton red = new JToggleButton("R");
	private JToggleButton white = new JToggleButton("W");
	private JToggleButton blue = new JToggleButton("U");
	private JToggleButton green = new JToggleButton();
	private JToggleButton black = new JToggleButton("B");
	private JToggleButton colorless = new JToggleButton("Cl");
	private JToggleButton multi = new JToggleButton("Mc");
	private JToggleButton common = new JToggleButton("C");
	private JToggleButton uncommon = new JToggleButton("U");
	private JToggleButton rare = new JToggleButton("R");
	private JToggleButton mythic = new JToggleButton("M");
	private JToggleButton creature = new JToggleButton("Creature");
	private JToggleButton enchantment = new JToggleButton("Enchant");
	private JToggleButton instant = new JToggleButton("Instant");
	private JToggleButton sorcery = new JToggleButton("Sorcery");
	private JToggleButton artifact = new JToggleButton("Artifact");
	private JToggleButton planeswalker = new JToggleButton("Pwalk");
	private JToggleButton oneP = new JToggleButton("1");
	private JToggleButton twoP = new JToggleButton("2");
	private JToggleButton threeP = new JToggleButton("3");
	private JToggleButton fourP = new JToggleButton("4");
	private JToggleButton fiveP = new JToggleButton("5");
	private JToggleButton sixP = new JToggleButton("6");
	private JToggleButton sevenP = new JToggleButton("7");
	private JToggleButton oneT = new JToggleButton("1");
	private JToggleButton twoT = new JToggleButton("2");
	private JToggleButton threeT = new JToggleButton("3");
	private JToggleButton fourT = new JToggleButton("4");
	private JToggleButton fiveT = new JToggleButton("5");
	private JToggleButton sixT = new JToggleButton("6");
	private JToggleButton sevenT = new JToggleButton("7");
	private JToggleButton land = new JToggleButton("Land");
	private JToggleButton unowned = new JToggleButton("Unowned");
	private JButton mycards = new JButton("My Cards");
	private JButton allcards = new JButton("All Cards");
	protected JButton goQuery = new JButton("Query!");
	protected JButton viewAll = new JButton("My Cards");
	private JTextField textBox = new JTextField("Enter Here");
	private String selected = "no";
	private JList list;
	private JList list2; 
	private String name = "n";
	private String cardText = "n";
	private String color = "n";
	private int power = -1;
	private int toughness = -1;
	private int owned = -1;
	private String type1 = "n";
	private String type2 = "n";
	private String rarityC = "n";
	private String tribal = "n";
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem, menuItem2;
	private String[][] data; 
	private String[] queryList;
	private boolean isQuery = false;
	private boolean isText = false;
	private boolean isMyCards = true;
	private boolean isSet = false;
	private boolean redSelected = false;
	private boolean blueSelected = false;
	private boolean whiteSelected = false;
	private boolean greenSelected = false;
	private boolean blackSelected = false;
	private boolean colorlessSelected = false;
	private boolean multiSelected = false;
	private boolean buttonSelected = false;
	private boolean tribalC = false;
	private boolean textC = false;
	private boolean enterC = false;
	private boolean nameC = false;
	private DefaultTableModel dataModel;
	private JTable table;
	private ArrayList<String[]> rarityArrays = new ArrayList<String[]>();
	private CollectionMethods organizer = new CollectionMethods();
	private boolean isRemoved;
	private JScrollPane scrollList;
	private JScrollPane scrollQuery;
	private String[] queryL;
	private JLabel label = new JLabel();
	private JLabel ownedL = new JLabel("Owned: ");
	private JLabel ownedNum = new JLabel("0");
	private JRadioButton nameCheck = new JRadioButton();
	private JRadioButton tribalCheck = new JRadioButton();
	private JRadioButton textCheck = new JRadioButton();
	private JRadioButton enterCheck = new JRadioButton();

	public CardOrganizer() throws InvalidKeyException, IOException, AWTException {
		queryL = organizer.getAllArray();
		String home = System.getProperty("user.home");
		try{
			System.setProperty("apple.laf.useScreenMenuBar", "true");
		} catch (Exception ex) {
			System.out.print("Not available in your OS");
		}
		
	    ButtonGroup group = new ButtonGroup();
	    group.add(nameCheck);
	    group.add(tribalCheck);
	    group.add(textCheck);
	    group.add(enterCheck);
	   
		
		//button images
		red = new JToggleButton(new ImageIcon(home + "/Desktop/red.png"));
		
		blue = new JToggleButton(new ImageIcon(home + "/Desktop/blue.png"));

		black = new JToggleButton(new ImageIcon(home + "/Desktop/black.png"));

		white = new JToggleButton(new ImageIcon(home + "/Desktop/white.png"));

		colorless = new JToggleButton(new ImageIcon(home + "/Desktop/colorless.png"));

		multi = new JToggleButton(new ImageIcon(home + "/Desktop/multi.png"));
		
		green = new JToggleButton(new ImageIcon(home + "/Desktop/green.png"));
		

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
		menuItem2 = new JMenuItem("Backup", KeyEvent.VK_T);
		menuItem2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
		menuItem2.getAccessibleContext().setAccessibleDescription("Backup");
		menu.add(menuItem2);
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
		
		//menuItem2 listener
		menuItem2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					organizer.backup();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				} catch (IOException e3) {
					e3.printStackTrace();
				}
			}
		});
		
		GridBagConstraints d = new GridBagConstraints();

		JPanel p5 = new JPanel(new GridLayout(2,1));
		p5.add(viewDatabase);
		p5.add(set);
		
		//TextBox, add, and remove card	
		JPanel tR = new JPanel(new GridBagLayout());
		JPanel p2 = new JPanel(new GridBagLayout());
		scrollQuery = new JScrollPane(p2);

		JTextField t = new JTextField();
		d.fill = GridBagConstraints.HORIZONTAL;
		d.weightx = 0.0;
		d.gridwidth = 2;
		d.gridx = 0;
		d.gridy = 0;
		d.anchor = GridBagConstraints.PAGE_START;
		tR.add(textBox, d);
		
		//TextBox Filter
		JLabel nameL = new JLabel("Name");
		JLabel tribalL = new JLabel("Tribal");
		JLabel textL = new JLabel("Text");
		JLabel enterL = new JLabel("Add");
		nameL.setFont(new Font("Serif", Font.PLAIN, 12));
		textL.setFont(new Font("Serif", Font.PLAIN, 12));
		tribalL.setFont(new Font("Serif", Font.PLAIN, 12));
		enterL.setFont(new Font("Serif", Font.PLAIN, 12));
		JPanel radios = new JPanel(new GridLayout(1,8));
		radios.add(nameL);
		radios.add(nameCheck);
		radios.add(tribalL);
		radios.add(tribalCheck);
		radios.add(textL);
		radios.add(textCheck);
		radios.add(enterL);
		radios.add(enterCheck);
		d.gridy = 1;
		d.gridx = 0;
		tR.add(radios,d);
		radios.setPreferredSize(new Dimension(0,30));
			
		d.fill = GridBagConstraints.HORIZONTAL;
		d.ipady = 4;      //make this component tall
		d.weightx = 0.5;
		d.gridwidth = 1;
		d.gridx = 0;
		d.gridy = 2;
		tR.add(addCard, d);

		d.fill = GridBagConstraints.HORIZONTAL;
		d.weighty = 0.0;
		d.ipady = 4;   	//make this component tall
		d.weightx = 0.5;
		d.gridwidth = 1;
		d.gridx = 1;
		d.gridy = 2;
		tR.add(removeCard, d);
		
		d.gridx = 0;
		d.gridy = 3;
		tR.add(mycards,d);
		
		d.gridx = 1;
		d.gridy = 3;
		tR.add(allcards,d);
		
		// ColorQuery
		JLabel colors = new JLabel("Color:");
		d.fill = GridBagConstraints.HORIZONTAL;
		d.weighty = 0.0;
		d.ipady = 27;   	//make this component tall
		d.weightx = 0.5;
		d.gridwidth = 1;
		d.gridx = 0;
		d.gridy = 4;
		d.ipady = 1;
		tR.add(unowned,d);
		set.setPreferredSize(new Dimension(0,30));
		d.gridx = 1;
		d.gridy = 4;
		d.insets = new Insets(0,0,0,0);
		tR.add(set, d);
		d.insets = new Insets(0,0,0,0);
			
//		color JToggle buttons
		JPanel colorBox = new JPanel(new GridLayout(1,7));
		colorBox.add(red);
		colorBox.add(blue);
		colorBox.add(white);
		colorBox.add(black);
		colorBox.add(green);
		colorBox.add(multi);
		colorBox.add(colorless);
		colorBox.setPreferredSize(new Dimension(0,30));
		
		//adding tR(textBox/+/-) and colorQuery together into p2
		d.ipady = 10;
		d.gridy = 0;
		d.gridx = 0;
		d.weighty = 0.0;
		d.anchor = GridBagConstraints.PAGE_START;
		p2.add(tR, d);
		d.gridy = 1;
		d.gridx = 0;
		d.weighty = 0.0;
		p2.add(colors,d);
		d.gridy = 2;
		p2.add(colorBox, d);
		
		//type Jtoggle buttons
		JPanel typeBox1 = new JPanel(new GridLayout(1,4));
		typeBox1.add(creature);
		typeBox1.add(enchantment);
		typeBox1.add(instant);
		typeBox1.setPreferredSize(new Dimension(0,20));
		JPanel typeBox2 = new JPanel(new GridLayout(1,3));
		typeBox2.add(sorcery);
		typeBox2.add(artifact);
		typeBox2.add(land);
		typeBox2.add(planeswalker);
		typeBox2.setPreferredSize(new Dimension(0,20));
		JPanel typeBox3 = new JPanel(new GridLayout(1,1));
//		typeBox3.add(planeswalker);
//		typeBox3.setPreferredSize(new Dimension(0,20));
		
		//type buttons
		JLabel typeL = new JLabel("Type:");
		d.gridy = 3;
		p2.add(typeL, d);
		d.gridy = 4;
		d.ipady = 14;
		p2.add(typeBox1, d);
		d.gridy = 5;
		d.weighty = 0.0;
		p2.add(typeBox2, d);
		//p2.add()
		d.ipady = 10;

		//rarity buttons
		JPanel rarityBox = new JPanel(new GridLayout(1,4));
		rarityBox.add(common);
		rarityBox.add(uncommon);
		rarityBox.add(rare);
		rarityBox.add(mythic);
		rarityBox.setPreferredSize(new Dimension(0,20));
		JLabel rarityL = new JLabel("Rarity:");
		d.gridx = 0;
		d.gridy = 6;
		d.weighty = 0.0;
		p2.add(rarityL, d);
		d.gridx = 0;
		d.gridy = 7;
		d.weighty = 0;
		p2.add(rarityBox, d);
		
		//power buttons
		JPanel powerB = new JPanel(new GridLayout(1,7));
		powerB.add(oneP);
		powerB.add(twoP);
		powerB.add(threeP);
		powerB.add(fourP);
		powerB.add(fiveP);
		powerB.add(sixP);
		powerB.add(sevenP);
		powerB.setPreferredSize(new Dimension(0,23));
		JLabel powerL = new JLabel("Power:");
		d.gridx = 0;
		d.gridy = 8;
		p2.add(powerL,d);
		d.gridy = 9;
		d.weighty = 0;
		p2.add(powerB,d);
		
		//toughness buttons
		JPanel tB = new JPanel(new GridLayout(1,7));
		tB.add(oneT);
		tB.add(twoT);
		tB.add(threeT);
		tB.add(fourT);
		tB.add(fiveT);
		tB.add(sixT);
		tB.add(sevenT);
		tB.setPreferredSize(new Dimension(0,23));
		JLabel tL = new JLabel("Toughness:");
		d.gridx = 0;
		d.gridy = 10;
		p2.add(tL,d);
		d.gridx = 0;
		d.gridy = 11;
		d.weighty = 1;
		p2.add(tB,d);
		
		//size of all panels combined
		p2.setPreferredSize(new Dimension(250,503));

		//JTable
		JPanel p4 = new JPanel();
		String[] cardNames = organizer.getAllArray();
		ArrayList<Integer> myCardsOwned = new ArrayList<Integer>();
		for( int i = 0; i < cardNames.length; i++) {
			try {
				myCardsOwned.add(organizer.getCard(cardNames[i]).getOwned()); //put owned ints into ownedList
			} catch (InvalidKeyException e1) {
				e1.printStackTrace();
			}
		}
		Integer[] cardsOwned = new Integer[myCardsOwned.size()];
		myCardsOwned.toArray(cardsOwned);
		String[] stringOwned = new String[cardsOwned.length];
		String[] stringSet = new String[cardsOwned.length];
		String[] rarity = new String[cardsOwned.length];
		String[] fullList = new String[cardNames.length];
		for(int i = 0; i < cardsOwned.length; i++) {
				if(cardsOwned[i] > 0) 
					stringOwned[i] = "✓";
				else
					stringOwned[i] = "No";
		}
		for(int i = 0; i < cardsOwned.length; i++) {
			String[] splitR = new String[100];
			if(organizer.getCard(cardNames[i]).getRarity() != null) {
			splitR = organizer.getCard(cardNames[i]).getRarity().split(",");
			String[] splitSet = new String[2];
			splitSet = splitR[0].split("-");
			try {
				stringSet[i] = splitSet[0];
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
		dataModel.setColumnCount(4);
		dataModel.setRowCount(organizer.getAllArray().length);
		dataModel.setColumnIdentifiers(new String[]{"Name", "Rarity", "Set", "Owned"});
		for(int i = 0; i < cardNames.length; i++) {
			dataModel.setValueAt(cardNames[i],i, 0);
		}
		for(int i = 0; i < cardNames.length; i++) {
			dataModel.setValueAt(rarity[i],i, 1);
		}
		for(int i = 0; i < cardNames.length; i++) {
			dataModel.setValueAt(stringSet[i],i, 2);
		}
		for(int i = 0; i < cardNames.length; i++) {
			dataModel.setValueAt(stringOwned[i],i, 3);
		}

		table = new JTable(dataModel) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {                
				return false;  
			};
		};

		scrollList = new JScrollPane(table);
		scrollList.setPreferredSize(new Dimension(650,500));
		p4.add(scrollList);

		//card viewing JLabel
		JPanel card = new JPanel(new GridBagLayout());
		ImageIcon image;
		label = new JLabel();
		try {
			image = new ImageIcon(ImageIO.read(new File(home +"/Desktop/VCO/Pictures Try 2/" + "card_back" + ".jpg")));
			label.setIcon(image);
		} catch (Exception ex) {
			label.setIcon(organizer.getCard("card_back").getImg());
		}
		d.fill = GridBagConstraints.HORIZONTAL;
		d.weightx = 0.0;
		d.gridwidth = 2;
		d.gridx = 0;
		d.gridy = 0;
		d.anchor = GridBagConstraints.PAGE_START;
		card.add(label, d);
		d.gridx = 0;
		d.gridy = 1;
		d.weighty = 0;
		d.insets = new Insets(11,0,0,0);
		card.add(ownedL, d);
		d.gridx = 1;
		d.gridy = 1;
		d.weighty = 1;
		d.insets = new Insets(0,50,0,0);
		ownedNum.setFont(new Font("Serif", Font.PLAIN, 36));
		card.add(ownedNum, d);
		
		//final formatting
		JPanel p3 = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		//buttons
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;     
		c.weighty = 1.0;
		c.gridx = 0;
		c.gridy = 0;
		p3.add(scrollQuery,c); //query list will scroll if too long

		//JTable 
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;     
		c.gridx = 1;
		c.gridy = 0;
		p3.add(p4, c);

		//label orientation
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(25,15,15,0);
		c.ipady = 0;     
		c.gridx = 2;
		c.gridy = 0;
		p3.add(card,c);
		add(p3);
		
		//remaps ENTER key in JTable to addCard()
		table.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
	    table.getActionMap().put("Enter", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent ae) {
				try {
					ownedNum.setText(Integer.toString(organizer.getCard(selected).getOwned()));
				} catch (InvalidKeyException e3) {
					e3.printStackTrace();
				}
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
					if(isMyCards == true)
						viewMyCards(); 
					else
						viewAll();
				//determine if query view and refresh
				} else if(isQuery == true) {
					try {
						queryList = organizer.query(queryL, color, power,toughness,owned, type1, type2, rarityC, tribal, cardText, name);
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
	    
	    tribalCheck.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	        	tribalC = true;
	        	nameC = false;
	        	textC = false;
	        	enterC = false;
	        }
	    });
	    
	    nameCheck.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	        	tribalC = false;
	        	nameC = true;
	        	textC = false;
	        	enterC = false;
	        }
	    });
	    
	    textCheck.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	        	tribalC = false;
	        	nameC = false;
	        	textC = true;
	        	enterC = false;
	        }
	    });
	    
	    enterCheck.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	        	tribalC = false;
	        	nameC = false;
	        	textC = false;
	        	enterC = true;
	        }
	    });
	    
	    sevenP.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	          AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	          buttonSelected = abstractButton.getModel().isSelected();
	          power = 7;
	          if(buttonSelected == false)
	        	  power = -1;
	          query();
	          try {
			selected = (String) table.getValueAt(table.getSelectedRow(), 0);
			try {
				label.setIcon(organizer.getCard(selected).getImg());
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				}
	          } catch (Exception ex) {
	        	  //do nothing. Means that no row was selected which is ok.
	          }
	          table.requestFocusInWindow();
	        }
	    });
	    
	    sixP.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	          AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	          buttonSelected = abstractButton.getModel().isSelected();
	          power = 6;
	          if(buttonSelected == false)
	        	  power = -1;
	          query();
	          try {
			selected = (String) table.getValueAt(table.getSelectedRow(), 0);
			try {
				label.setIcon(organizer.getCard(selected).getImg());
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				}
	          } catch (Exception ex) {
	        	  //do nothing. Means that no row was selected which is ok.
	          }
	          table.requestFocusInWindow();
	        }
	    });
	    
	    oneP.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	          AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	          buttonSelected = abstractButton.getModel().isSelected();
	          power = 1;
	          if(buttonSelected == false)
	        	  power = -1;
	          query();
	          try {
			selected = (String) table.getValueAt(table.getSelectedRow(), 0);
			try {
				label.setIcon(organizer.getCard(selected).getImg());
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				}
	          } catch (Exception ex) {
	        	  //do nothing. Means that no row was selected which is ok.
	          }
	          table.requestFocusInWindow();
	        }
	    });
	    
	    twoP.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	          AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	          buttonSelected = abstractButton.getModel().isSelected();
	          power = 2;
	          if(buttonSelected == false)
	        	  power = -1;
	          query();
	          try {
			selected = (String) table.getValueAt(table.getSelectedRow(), 0);
			try {
				label.setIcon(organizer.getCard(selected).getImg());
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				}
	          } catch (Exception ex) {
	        	  //do nothing. Means that no row was selected which is ok.
	          }
	          table.requestFocusInWindow();
	        }
	    });
	    
	    threeP.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	          AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	          buttonSelected = abstractButton.getModel().isSelected();
	          power = 3;
	          if(buttonSelected == false)
	        	  power = -1;
	          query();
	          try {
			selected = (String) table.getValueAt(table.getSelectedRow(), 0);
			try {
				label.setIcon(organizer.getCard(selected).getImg());
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				}
	          } catch (Exception ex) {
	        	  //do nothing. Means that no row was selected which is ok.
	          }
	          table.requestFocusInWindow();
	        }
	    });
	    
	    fourP.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	          AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	          buttonSelected = abstractButton.getModel().isSelected();
	          power = 4;
	          if(buttonSelected == false)
	        	  power = -1;
	          query();
	          try {
			selected = (String) table.getValueAt(table.getSelectedRow(), 0);
			try {
				label.setIcon(organizer.getCard(selected).getImg());
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				}
	          } catch (Exception ex) {
	        	  //do nothing. Means that no row was selected which is ok.
	          }
	          table.requestFocusInWindow();
	        }
	    });
	    
	    fiveP.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	          AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	          buttonSelected = abstractButton.getModel().isSelected();
	          power = 5;
	          if(buttonSelected == false)
	        	  power = -1;
	          query();
	          try {
			selected = (String) table.getValueAt(table.getSelectedRow(), 0);
			try {
				label.setIcon(organizer.getCard(selected).getImg());
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				}
	          } catch (Exception ex) {
	        	  //do nothing. Means that no row was selected which is ok.
	          }
	          table.requestFocusInWindow();
	        }
	    });
	    
	    oneT.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	          AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	          buttonSelected = abstractButton.getModel().isSelected();
	          toughness = 1;
	          if(buttonSelected == false)
	        	  toughness = -1;
	          query();
	          try {
			selected = (String) table.getValueAt(table.getSelectedRow(), 0);
			try {
				label.setIcon(organizer.getCard(selected).getImg());
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				}
	          } catch (Exception ex) {
	        	  //do nothing. Means that no row was selected which is ok.
	          }
	          table.requestFocusInWindow();
	        }
	    });
	    
	    
	    twoT.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	          AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	          buttonSelected = abstractButton.getModel().isSelected();
	          toughness = 2;
	          if(buttonSelected == false)
	        	  toughness = -1;
	          query();
	          try {
			selected = (String) table.getValueAt(table.getSelectedRow(), 0);
			try {
				label.setIcon(organizer.getCard(selected).getImg());
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				}
	          } catch (Exception ex) {
	        	  //do nothing. Means that no row was selected which is ok.
	          }
	          table.requestFocusInWindow();
	        }
	    });
	    
	    
	    threeT.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	          AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	          buttonSelected = abstractButton.getModel().isSelected();
	          toughness = 3;
	          if(buttonSelected == false)
	        	  toughness = -1;
	          query();
	          try {
			selected = (String) table.getValueAt(table.getSelectedRow(), 0);
			try {
				label.setIcon(organizer.getCard(selected).getImg());
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				}
	          } catch (Exception ex) {
	        	  //do nothing. Means that no row was selected which is ok.
	          }
	          table.requestFocusInWindow();
	        }
	    });
	    
	    
	    fourT.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	          AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	          buttonSelected = abstractButton.getModel().isSelected();
	          toughness = 4;
	          if(buttonSelected == false)
	        	  toughness = -1;
	          query();
	          try {
			selected = (String) table.getValueAt(table.getSelectedRow(), 0);
			try {
				label.setIcon(organizer.getCard(selected).getImg());
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				}
	          } catch (Exception ex) {
	        	  //do nothing. Means that no row was selected which is ok.
	          }
	          table.requestFocusInWindow();
	        }
	    });
	    
	    
	    fiveT.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	          AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	          buttonSelected = abstractButton.getModel().isSelected();
	          toughness = 5;
	          if(buttonSelected == false)
	        	  toughness = -1;
	          query();
	          try {
			selected = (String) table.getValueAt(table.getSelectedRow(), 0);
			try {
				label.setIcon(organizer.getCard(selected).getImg());
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				}
	          } catch (Exception ex) {
	        	  //do nothing. Means that no row was selected which is ok.
	          }
	          table.requestFocusInWindow();
	        }
	    });
	    
	    
	    sixT.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	          AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	          buttonSelected = abstractButton.getModel().isSelected();
	          toughness = 6;
	          if(buttonSelected == false)
	        	  toughness = -1;
	          query();
	          try {
			selected = (String) table.getValueAt(table.getSelectedRow(), 0);
			try {
				label.setIcon(organizer.getCard(selected).getImg());
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				}
	          } catch (Exception ex) {
	        	  //do nothing. Means that no row was selected which is ok.
	          }
	          table.requestFocusInWindow();
	        }
	    });
	    
	    
	    sevenT.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	          AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	          buttonSelected = abstractButton.getModel().isSelected();
	          toughness = 7;
	          if(buttonSelected == false)
	        	  toughness = -1;
	          query();
	          try {
			selected = (String) table.getValueAt(table.getSelectedRow(), 0);
			try {
				label.setIcon(organizer.getCard(selected).getImg());
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				}
	          } catch (Exception ex) {
	        	  //do nothing. Means that no row was selected which is ok.
	          }
	          table.requestFocusInWindow();
	        }
	    });
	    
	    creature.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	          AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	          buttonSelected = abstractButton.getModel().isSelected();
	          type2 = "creature";
	          if(buttonSelected == false)
	        	  type2 = "n";
	          query();
	          try {
			selected = (String) table.getValueAt(table.getSelectedRow(), 0);
			try {
				label.setIcon(organizer.getCard(selected).getImg());
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				}
	          } catch (Exception ex) {
	        	  //do nothing. Means that no row was selected which is ok.
	          }
	          table.requestFocusInWindow();
	        }
	    });
	    
	    enchantment.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	          AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	          buttonSelected = abstractButton.getModel().isSelected();
	          type2 = "enchantment";
	          if(buttonSelected == false)
	        	  type2 = "n";
	          query();
	          try {
			selected = (String) table.getValueAt(table.getSelectedRow(), 0);
			try {
				label.setIcon(organizer.getCard(selected).getImg());
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				}
	          } catch (Exception ex) {
	        	  //do nothing. Means that no row was selected which is ok.
	          }
	          table.requestFocusInWindow();
	        }
	    });
	    
	    instant.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	          AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	          buttonSelected = abstractButton.getModel().isSelected();
	          type2 = "instant";
	          if(buttonSelected == false)
	        	  type2 = "n";
	          query();
	          try {
			selected = (String) table.getValueAt(table.getSelectedRow(), 0);
			try {
				label.setIcon(organizer.getCard(selected).getImg());
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				}
	          } catch (Exception ex) {
	        	  //do nothing. Means that no row was selected which is ok.
	          }
	          table.requestFocusInWindow();
	        }
	    });
	    
	    sorcery.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	          AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	          buttonSelected = abstractButton.getModel().isSelected();
	          type2 = "sorcery";
	          if(buttonSelected == false)
	        	  type2 = "n";
	          query();
	          try {
			selected = (String) table.getValueAt(table.getSelectedRow(), 0);
			try {
				label.setIcon(organizer.getCard(selected).getImg());
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				}
	          } catch (Exception ex) {
	        	  //do nothing. Means that no row was selected which is ok.
	          }
	          table.requestFocusInWindow();
	        }
	    });
	    
	    artifact.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	          AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	          buttonSelected = abstractButton.getModel().isSelected();
	          type2 = "artifact";
	          if(buttonSelected == false)
	        	  type2 = "n";
	          query();
	          try {
			selected = (String) table.getValueAt(table.getSelectedRow(), 0);
			try {
				label.setIcon(organizer.getCard(selected).getImg());
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				}
	          } catch (Exception ex) {
	        	  //do nothing. Means that no row was selected which is ok.
	          }
	          table.requestFocusInWindow();
	        }
	    });
	    
	    land.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	          AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	          buttonSelected = abstractButton.getModel().isSelected();
	          type2 = "land";
	          if(buttonSelected == false)
	        	  type2 = "n";
	          query();
	          try {
			selected = (String) table.getValueAt(table.getSelectedRow(), 0);
			try {
				label.setIcon(organizer.getCard(selected).getImg());
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				}
	          } catch (Exception ex) {
	        	  //do nothing. Means that no row was selected which is ok.
	          }
	          table.requestFocusInWindow();
	        }
	    });
	    
	    planeswalker.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	          AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	          buttonSelected = abstractButton.getModel().isSelected();
	          type2 = "planeswalker";
	          if(buttonSelected == false)
	        	  type2 = "n";
	          query();
	          try {
			selected = (String) table.getValueAt(table.getSelectedRow(), 0);
			try {
				label.setIcon(organizer.getCard(selected).getImg());
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				}
	          } catch (Exception ex) {
	        	  //do nothing. Means that no row was selected which is ok.
	          }
	          table.requestFocusInWindow();
	        }
	    });
	    
	    red.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	          AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	          redSelected = abstractButton.getModel().isSelected();
	          color = "red";
	          if(redSelected == false)
	        	  color = "n";
	          query();
	          try {
			selected = (String) table.getValueAt(table.getSelectedRow(), 0);
			try {
				label.setIcon(organizer.getCard(selected).getImg());
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	          } catch (Exception ex) {
	        	  //
	          }
	          table.requestFocusInWindow();
	        }
	    });
	    
	    white.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	          AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	          whiteSelected = abstractButton.getModel().isSelected();
	          color = "white";
	          if(whiteSelected == false)
	        	  color = "n";
	          query();
	          try {
	          selected = (String) table.getValueAt(table.getSelectedRow(), 0);
				try {
					label.setIcon(organizer.getCard(selected).getImg());
				} catch (InvalidKeyException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
	          } catch (Exception ex) {
	    	//
	          }
	          table.requestFocusInWindow();
	        }
	    });
	    
	    blue.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	          AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	          blueSelected = abstractButton.getModel().isSelected();
	          color = "blue";
	          if(blueSelected == false)
	        	  color = "n";
	          query();
	          try {
	          selected = (String) table.getValueAt(table.getSelectedRow(), 0);
				try {
					label.setIcon(organizer.getCard(selected).getImg());
				} catch (InvalidKeyException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
	          } catch (Exception ex) {
	        	  //
	          }
	          table.requestFocusInWindow();
	        }
	    });
	    
	    green.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	          AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	          greenSelected = abstractButton.getModel().isSelected();
	          color = "green";
	          if(greenSelected == false)
	        	  color = "n";
	          query();
	          try {
	          selected = (String) table.getValueAt(table.getSelectedRow(), 0);
				try {
					label.setIcon(organizer.getCard(selected).getImg());
				} catch (InvalidKeyException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
	          } catch (Exception ex) {
	        	  //
	          }
	          table.requestFocusInWindow();
	        }
	    });
	    
	    black.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	          AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	          blackSelected = abstractButton.getModel().isSelected();
	          color = "black";
	          if(blackSelected == false)
	        	  color = "n";
	          query();
	          try {
		          selected = (String) table.getValueAt(table.getSelectedRow(), 0);
					try {
						label.setIcon(organizer.getCard(selected).getImg());
					} catch (InvalidKeyException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
		          } catch (Exception ex) {
		        	  //
		          }
	          table.requestFocusInWindow();
	        }
	    });
	    
	    colorless.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	          AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	          colorlessSelected = abstractButton.getModel().isSelected();
	          color = "colorless";
	          if(colorlessSelected == false)
	        	  color = "n";
	          query();
	          try {
		          selected = (String) table.getValueAt(table.getSelectedRow(), 0);
					try {
						label.setIcon(organizer.getCard(selected).getImg());
					} catch (InvalidKeyException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
		          } catch (Exception ex) {
		        	  //
		          }
	          table.requestFocusInWindow();
	        }
	    });
	    
	    multi.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	          AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	          multiSelected = abstractButton.getModel().isSelected();
	          color = "multi";
	          if(multiSelected == false)
	        	  color = "n";
	          query();
	          try {
		          selected = (String) table.getValueAt(table.getSelectedRow(), 0);
					try {
						label.setIcon(organizer.getCard(selected).getImg());
					} catch (InvalidKeyException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
		          } catch (Exception ex) {
		        	  //
		          }
	          table.requestFocusInWindow();
	        }
	    });
	    
	    mythic.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	          AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	          buttonSelected = abstractButton.getModel().isSelected();
	          rarityC = "mythic";
	          if(buttonSelected == false)
	        	  rarityC = "n";
	          query();
	          try {
		          selected = (String) table.getValueAt(table.getSelectedRow(), 0);
					try {
						label.setIcon(organizer.getCard(selected).getImg());
					} catch (InvalidKeyException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
		          } catch (Exception ex) {
		        	  //
		          }
	          table.requestFocusInWindow();
	        }
	    });
	    
	    rare.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	          AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	          buttonSelected = abstractButton.getModel().isSelected();
	          rarityC = "rare";
	          if(buttonSelected == false)
	        	  rarityC = "n";
	          query();
	          try {
		          selected = (String) table.getValueAt(table.getSelectedRow(), 0);
					try {
						label.setIcon(organizer.getCard(selected).getImg());
					} catch (InvalidKeyException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
		          } catch (Exception ex) {
		        	  //
		          }
	          table.requestFocusInWindow();
	        }
	    });
	    
	    uncommon.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	          AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	          buttonSelected = abstractButton.getModel().isSelected();
	          rarityC = "uncommon";
	          if(buttonSelected == false)
	        	  rarityC = "n";
	          query();
	          try {
		          selected = (String) table.getValueAt(table.getSelectedRow(), 0);
					try {
						label.setIcon(organizer.getCard(selected).getImg());
					} catch (InvalidKeyException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
		          } catch (Exception ex) {
		        	  //
		          }
	          table.requestFocusInWindow();
	        }
	    });
	    
	    common.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	          AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	          buttonSelected = abstractButton.getModel().isSelected();
	          rarityC = "common";
	          if(buttonSelected == false)
	        	  rarityC = "n";
	          query();
	          try {
		          selected = (String) table.getValueAt(table.getSelectedRow(), 0);
					try {
						label.setIcon(organizer.getCard(selected).getImg());
					} catch (InvalidKeyException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
		          } catch (Exception ex) {
		        	  //
		          }
	          table.requestFocusInWindow();
	        }
	    });
	    
		
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
		
		queryTribal.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(queryTribal.getSelectedItem().toString().equals("Yes (Type in text box)")) {
				try {
				tribal = textBox.getText();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				} else
				tribal = "n";
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
				isSet = true;
				String sel = (String) set.getSelectedItem();
				if(sel.equals("Choose Set")) {
					if(isMyCards == true) {
						viewMyCards();
						query();
						return; }
					else {
						viewAll();
						query();
						return; 
					}
				}
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
				if(isMyCards == true) {
					ArrayList<String> arr = new ArrayList<String>();
					String[] mine = organizer.getAllArray();
					for(int i = 0; i < mine.length; i++) {
						try {
							if(organizer.getCard(mine[i]).rarity.contains(sel))
								arr.add(mine[i]);
							} catch (Exception ex) {
								//ex.printStackTrace(); //Not quite sure what is going wrong here buuut its small as far as I can tell
							}
					}
					all = new String[arr.size()];
					arr.toArray(all);
				}
				if(owned == -1 && color.equals("n") && type1.equals("n") && type2.equals("n") 
						&& power == -1 && toughness == -1 && rarityC.equals("n") && tribal.equals("n")) {
					queryL = all;
				} else {		
				try {
					all = organizer.query(all, color, power,toughness,owned, type1, type2, rarityC, tribal, cardText, name);
					queryL = all;
				} catch (InvalidKeyException e1) {
					e1.printStackTrace();
					}
				}
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
				if(queryTribal.getSelectedItem().toString().equals("Yes (Type in text box)"))
					tribal = textBox.getText();
				try {
					queryList = organizer.query(queryL, color, power,toughness,owned, type1, type2, rarityC, tribal, cardText, name);
				} catch (InvalidKeyException e1) {
					e1.printStackTrace();
				}
				java.util.Arrays.sort(queryList);
				isQuery = true;
				ArrayList<Integer> ownedList = new ArrayList();
				if(owned == -1 && color.equals("n") && type1.equals("n") && type2.equals("n") 
						&& power == -1 && toughness == -1 && rarityC.equals("n") && tribal.equals("n")) { //if no input for query
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
/**
 * Removing cards from queries inside of sets, removing cards from queries inside of all cards
 *  and querying after removing inside a query do no work for some reason
 *  
 *  HOWEVER, removing inside my cards, all cards, set cards, and queries inside of my cards do work
 */
		removeCard.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {			
				isRemoved = false;
				//if there is no selected value sets selected to textbox string
				try {
					selected = (String) table.getValueAt(table.getSelectedRow(), 0);
						} catch (ArrayIndexOutOfBoundsException ex) {
							selected = textBox.getText();
								}
				//if textbox is selected sets selected to that
				if(isText == true) {
					selected = textBox.getText();
						}
				try {
					if (isMyCards == true && organizer.getCard(selected).owned == 1) 
						isRemoved = true;
							} catch (InvalidKeyException e1) {
								e1.printStackTrace();
									}
				//deals with complete database keeping cards with zero and my cards deleting the cards
				try {
					if (organizer.getCard(selected).owned == 1)
						organizer.removeCard(selected, "cD");
					if(selected != null && isMyCards == true) {
						organizer.removeCard(selected);
						} else
							organizer.removeCard(selected);
							} catch (NullPointerException ex) {
									ex.printStackTrace();
										} catch (InvalidKeyException e1) {
											e1.printStackTrace();
							}
				//attempt at refreshing #owned
				try {
					ownedNum.setText(Integer.toString(organizer.getCard(selected).getOwned()));
				} catch (InvalidKeyException e2) {
					e2.printStackTrace();
				}
				String[] all2 = organizer.getCategory("cD");
				ArrayList<String[]> values2 = returnValues(all2);
				refreshTable(all2, returnValues(all2));
				
				//give it the right String[] to refresh
					if(isMyCards == true) {
						if(isSet == false && isQuery == false)
						queryL = organizer.getAllArray();
						if (isSet == true && isQuery == false) {
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
							if(isMyCards == true) {
								ArrayList<String> arr = new ArrayList<String>();
								String[] mine = organizer.getAllArray();
								for(int i = 0; i < mine.length; i++) {
									try {
										if(organizer.getCard(mine[i]).rarity.contains(sel))
											arr.add(mine[i]);
										} catch (Exception ex) {
											//ex.printStackTrace(); //Not quite sure what is going wrong here buuut its small as far as I can tell
										}
								}
								all = new String[arr.size()];
								arr.toArray(all);
							}
							queryL = all;
						} else if (isQuery == true) {
							try {
								queryList = organizer.query(queryL, color, power,toughness,owned, type1, type2, rarityC, tribal, cardText, name);
							} catch (InvalidKeyException e1) {
								e1.printStackTrace();
							}
							queryL = queryList;
						}
					} else {
							if(isSet == false && isQuery == false) {
							queryL = organizer.getCategory("cD");
							if (isSet == true && isQuery == false) {
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
								if(isMyCards == true) {
									ArrayList<String> arr = new ArrayList<String>();
									String[] mine = organizer.getAllArray();
									for(int i = 0; i < mine.length; i++) {
										try {
											if(organizer.getCard(mine[i]).rarity.contains(sel))
												arr.add(mine[i]);
											} catch (Exception ex) {
												//ex.printStackTrace(); //Not quite sure what is going wrong here buuut its small as far as I can tell
											}
									}
									all = new String[arr.size()];
									arr.toArray(all);
								}
								queryL = all;
							} else if (isQuery == true) {
								try {
									queryList = organizer.query(queryL, color, power,toughness,owned, type1, type2, rarityC, tribal, cardText, name);
								} catch (InvalidKeyException e1) {
									e1.printStackTrace();
								}
								queryL = queryList;
								} 
							}
						}
					String[] allList = queryL;
					ArrayList<String[]> values = returnValues(allList);
					if(isRemoved == true && dataModel.getRowCount() > 2) { // remove one row if there is no longer a card unless there are only 2 rows
						dataModel.removeRow(1);							   // 2 rows because you need to joggle the selected row to toggle the image...Lazy I know...Will look into it.
					}
					refreshTable(allList, values);
					}
		});


		addCard.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ownedNum.setText(Integer.toString(organizer.getCard(selected).getOwned()));
				} catch (InvalidKeyException e3) {
					e3.printStackTrace();
				}
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
					if(isMyCards == true)
						viewMyCards(); 
					else
						viewAll();
				//determine if query view and refresh
				} else if(isQuery == true) {
					try {
						queryList = organizer.query(queryL, color, power,toughness,owned, type1, type2, rarityC, tribal, cardText, name);
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

	    mycards.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	    		queryL = organizer.getAllArray();
				isQuery = false;
				isMyCards = true;
				isSet = false;
				String[] all = organizer.getAllArray();
				if(owned == -1 && color.equals("n") && type1.equals("n") && type2.equals("n") 
						&& power == -1 && toughness == -1 && rarityC.equals("n") && tribal.equals("n")  && cardText.equals("n") && name.equals("n") && isSet != true) { 
					//do nothing
				} else {
				try {
					all = organizer.query(all, color, power,toughness,owned, type1, type2, rarityC, tribal, cardText, name);
				} catch (InvalidKeyException e) {
					e.printStackTrace();
					}
				}
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
				try {
				selected = (String) table.getValueAt(table.getSelectedRow(), 0);
				}catch (Exception ex) {
					//ex.printStackTrace();
				}
				try {
					label.setIcon(organizer.getCard(selected).getImg());
				} catch (InvalidKeyException e) {
					//e.printStackTrace();
				} catch (IOException e) {
					//e.printStackTrace();
				}
	          table.requestFocusInWindow();
	        }
	    });
	    
	    allcards.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	    		queryL = organizer.getCategory("cD");
				isQuery = false;
				isMyCards = false;
				isSet = false;
				String[] all = organizer.getCategory("cD");
				if(owned == -1 && color.equals("n") && type1.equals("n") && type2.equals("n") 
						&& power == -1 && toughness == -1 && rarityC.equals("n") && tribal.equals("n")  && cardText.equals("n") && name.equals("n") && isSet != true) { 
					//do nothing
				} else {
				try {
					all = organizer.query(all, color, power,toughness,owned, type1, type2, rarityC, tribal, cardText, name);
				} catch (InvalidKeyException e) {
					e.printStackTrace();
					}
				}
				ArrayList<String[]> values = returnValues(all);
				String[] stringOwned = values.get(0);
				String[] rarity = values.get(1);
				int currentRows = dataModel.getRowCount();
				for(int i = 0; i < organizer.getCategory("cD"). length - currentRows ; i++) { //add appropriate number of rows
					dataModel.addRow(new Object[]{"","",""});
						}
				refreshTable(all, values);
				try {
					selected = (String) table.getValueAt(table.getSelectedRow(), 0);
					}catch (Exception ex) {
						//ex.printStackTrace();
					}
				try {
					label.setIcon(organizer.getCard(selected).getImg());
				} catch (InvalidKeyException e) {
					//e.printStackTrace();
				} catch (IOException e) {
					//e.printStackTrace();
				}
	          table.requestFocusInWindow();
	        }
	    });

		//listens for textBox input
		textBox.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				if(textC == true)
					text();
				if(nameC == true)
					name();
				if(tribalC == true)
					tribal();
					}
			public void removeUpdate(DocumentEvent e) {
				if(textC == true)
					text();
				if(nameC == true)
					name();
				if(tribalC == true)
					tribal();
					}
			public void insertUpdate(DocumentEvent e) {
				if(textC == true)
					text();
				if(nameC == true)
					name();
				if(tribalC == true)
					tribal();
					}
			
			public void tribal() {
				isText = true;
				if(!textBox.getText().equals("")) {
					tribal = textBox.getText().toLowerCase();
				} else {
					tribal = "n";
				}
				query();
				if(textBox.getText().equals("")) {
					if(isMyCards == true) 
						viewMyCards();
					else
						viewAll();
				}
			}
			public void name() {
				isText = true;
				if(!textBox.getText().equals("")) {
					name = textBox.getText().toLowerCase();
				} else {
					name = "n";
				}
					query();
					if(textBox.getText().equals("")) {
					if(isMyCards == true) 
						viewMyCards();
					else
						viewAll();
				}
			}
			public void text() {
				isText = true;
				if(!textBox.getText().equals("")) {
					cardText = textBox.getText().toLowerCase();
				} else {
					cardText = "n";
				}
					query();
					if(textBox.getText().equals("")) {
					if(isMyCards == true) 
						viewMyCards();
					else
						viewAll();
				}
			}
		});
		
		//detects selected table row and updates image icon
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
				selected = (String) table.getValueAt(table.getSelectedRow(), 0);
				} catch (ArrayIndexOutOfBoundsException e1) {
					e1.printStackTrace();//Do nothing for now...until I'm clever / motivated enough to do something
				}
				isText = false;
				if(selected != null) {
					try {
						ownedNum.setText(Integer.toString(organizer.getCard(selected).getOwned()));
						} catch (Exception ex) {
							ex.printStackTrace();
						}
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
		String[] stringSet = l.get(0);
		String[] rarity = l.get(1);
		String[] owned = l.get(2);
				
		for(int i = 0; i < dataModel.getRowCount(); i++) {
			dataModel.setValueAt(null,i, 0);
		}
		for(int i = 0; i < dataModel.getRowCount(); i++) {
			dataModel.setValueAt(null,i, 1);
		}
		for(int i = 0; i < dataModel.getRowCount(); i++) {
			dataModel.setValueAt(null,i, 2);
		}
		for(int i = 0; i < dataModel.getRowCount(); i++) {
			dataModel.setValueAt(null,i, 3);
		}
		for(int i = 0; i < all.length; i++) {
			dataModel.setValueAt(all[i],i, 0);
		}
		for(int i = 0; i < all.length; i++) {
			dataModel.setValueAt(rarity[i],i, 1);
		}
		for(int i = 0; i < all.length; i++) {
			dataModel.setValueAt(stringSet[i],i, 2);
		}
		for(int i = 0; i < all.length; i++) {
			dataModel.setValueAt(owned[i],i, 3);
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
				//e1.printStackTrace();
				}
			}
		//convert ownedList<Integer> to String[] stringOwned
		Integer[] arr2= new Integer[ownedList.size()];
		ownedList.toArray(arr2);
		String[] stringOwned = new String[arr2.length];
		for(int i = 0; i < category.length; i++) {
			if(arr2[i] > 0) 
				stringOwned[i] = "✓";
			else
				stringOwned[i] = "No";
		}
		//split the rarity out of each card given the name list
		String[] rarity = new String[category.length];
		String[] stringSet = new String[category.length];
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
					stringSet[i] = splitSet[0];
					if(splitSet[1].equals("C"))
						rarity[i] = "Common";
					if(splitSet[1].equals("U"))
						rarity[i] = "Uncommon";
					if(splitSet[1].equals("R"))
						rarity[i] = "Rare";
					if(splitSet[1].equals("M"))
						rarity[i] = "Mythic";
				}catch (ArrayIndexOutOfBoundsException ex) {
					//ex.printStackTrace(); //probably messed up b/c of cardback which is basically empty
					}
					}
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			}
	} 
		
		ArrayList<String[]> strings = new ArrayList<String[]>();
		strings.add(stringSet);
		strings.add(rarity);
		strings.add(stringOwned);
		return strings;
	}
	
	/**
	 * Refreshes table with query
	 */
	public void query() {
		if(isSet == true)
			viewSet();
		try {
			queryList = organizer.query(queryL, color, power,toughness,owned, type1, type2, rarityC, tribal, cardText, name);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		java.util.Arrays.sort(queryList);
		isQuery = true;
		ArrayList<Integer> ownedList = new ArrayList();
		if(owned == -1 && color.equals("n") && type1.equals("n") && type2.equals("n") 
				&& power == -1 && toughness == -1 && rarityC.equals("n") && tribal.equals("n") && cardText.equals("n") && name.equals("n") && isSet != true) { //if no input for query
			if (isMyCards == true) {
				viewMyCards();
			} else if(isSet == true) {
				viewSet();
			} else {
			viewAll();
			}	
		} else {
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
			try {
			selected = (String) table.getValueAt(table.getSelectedRow(), 0);
			} catch (Exception ex) {
				selected = (String) table.getValueAt(0, 0);
				//ex.printStackTrace();//do nothing since there is no selected row
			}
			try {
				label.setIcon(organizer.getCard(selected).getImg());
			} catch (Exception e1) {
				//e1.printStackTrace(); //nor image file for it
			}	
		}
	}
	public void viewMyCards() {
		queryL = organizer.getAllArray();
		isQuery = false;
		isMyCards = true;
		isSet = false;
		try {
			ownedNum.setText(Integer.toString(organizer.getCard(selected).getOwned()));
		} catch (InvalidKeyException e3) {
			e3.printStackTrace();
		}
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

	public void viewAll() {
		queryL = organizer.getCategory("cD");
		isQuery = false;
		isMyCards = false;
		isSet = false;
		try {
			ownedNum.setText(Integer.toString(organizer.getCard(selected).getOwned()));
		} catch (InvalidKeyException e3) {
			e3.printStackTrace();
		}
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
	
	public void viewSet() {
		isSet = true;
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
		if(isMyCards == true) {
			ArrayList<String> arr = new ArrayList<String>();
			String[] mine = organizer.getAllArray();
			for(int i = 0; i < mine.length; i++) {
				try {
					if(organizer.getCard(mine[i]).rarity.contains(sel))
						arr.add(mine[i]);
					} catch (Exception ex) {
						ex.printStackTrace(); //Not quite sure what is going wrong here buuut its small as far as I can tell
					}
			}
			all = new String[arr.size()];
			arr.toArray(all);
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
	
		public static void main(String[] args) throws InvalidKeyException, IOException, AWTException {
		CardOrganizer frame = null;
		try {
			frame = new CardOrganizer();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		frame.setTitle("Virtual Card Organizer");
		frame.setSize(1185,542);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		//frame.setResizable(false);
	}
}
