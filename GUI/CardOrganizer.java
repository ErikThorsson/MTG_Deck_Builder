package GUI;

import java.awt.AWTException;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
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
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import MTGApplication.Card;
import MTGApplication.CollectionMethods;

@SuppressWarnings("serial")
public class CardOrganizer extends JFrame  {
	protected ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	protected JTextArea notes = new JTextArea();
	protected JButton addCard = new JButton("+");
	protected JButton removeCard = new JButton("-");
	private JButton viewDatabase = new JButton("All Cards");
	protected JComboBox set = new JComboBox(new Object[] {"Set", "Return to Ravnica", "Gatecrash", "Dragon's Maze", "Innistrad", "Dark Ascension", "Avacyn Restored"});
	private JToggleButton red = new JToggleButton("R");
	private JToggleButton white = new JToggleButton("W");
	private JToggleButton blue = new JToggleButton("U");
	private JToggleButton green = new JToggleButton();
	private JToggleButton black = new JToggleButton("B");
	private JToggleButton colorless = new JToggleButton("Cl");
	private JToggleButton multi = new JToggleButton("Mc");
	private JToggleButton and = new JToggleButton("And");
	private JToggleButton or  = new JToggleButton("Or");
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
	private JToggleButton unowned = new JToggleButton("Missing");
	private JToggleButton ownedC = new JToggleButton("Owned");
	private JButton mycards = new JButton("My Cards");
	private JButton allcards = new JButton("All Cards");
	protected JButton goQuery = new JButton("Query!");
	protected JButton viewAll = new JButton("My Cards");
	private JTextField textBox = new JTextField("Enter Here");
	private String selected = null;
	private JCheckBox deckBuild = new JCheckBox();
	private JCheckBox sideBoard = new JCheckBox();
	private JComboBox decks = new JComboBox();
	DefaultComboBoxModel model = new DefaultComboBoxModel();
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
	private JMenuItem menuItem, menuItem2, menuItem3, menuItem4;
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
	private boolean deckCheck = false;
	private DefaultTableModel dataModel;
	private DefaultTableModel dataModel2;
	private DefaultTableModel dataModel3;
	private DefaultTableModel dataModel4;
	private DefaultTableModel dataModel5;
	private JTable table;
	private JTable table2;
	private JTable table3;
	private JTable table4;
	private JTable table5;
	private CollectionMethods organizer = new CollectionMethods();
	private boolean isRemoved;
	private JScrollPane scrollList;
	private JScrollPane scrollQuery;
	private JLabel label = new JLabel();
	private JLabel ownedL = new JLabel("Owned: ");
	private JLabel ownedNum = new JLabel("0");
	private JLabel priceL = new JLabel("$");
	private JLabel price = new JLabel("0.0");
	private JLabel collectionV = new JLabel("Collection Value: ");
	private JLabel mSign = new JLabel("$");
	private JLabel collectionValue = new JLabel("0.0");
	private JRadioButton nameCheck = new JRadioButton();
	private JRadioButton tribalCheck = new JRadioButton();
	private JRadioButton textCheck = new JRadioButton();
	private JRadioButton enterCheck = new JRadioButton();
	private ImageIcon mythicIcon = new ImageIcon();
	private ImageIcon rareIcon = new ImageIcon();		  
	private ImageIcon uncommonIcon = new ImageIcon();		  
	private ImageIcon commonIcon = new ImageIcon();	
	private ImageIcon blackIcon = new ImageIcon();
	private ImageIcon greenIcon = new ImageIcon();	
	private ImageIcon blueIcon = new ImageIcon();
	private ImageIcon redIcon = new ImageIcon();
	private ImageIcon whiteIcon = new ImageIcon();
	private ImageIcon oneIcon = new ImageIcon();
	private ImageIcon twoIcon = new ImageIcon();
	private ImageIcon threeIcon = new ImageIcon();
	private ImageIcon fourIcon = new ImageIcon();
	private ImageIcon fiveIcon = new ImageIcon();
	private ImageIcon sixIcon = new ImageIcon();
	private ImageIcon sevenIcon = new ImageIcon();
	private ImageIcon eightIcon = new ImageIcon();
	private ImageIcon nineIcon = new ImageIcon();
	private ImageIcon tenIcon = new ImageIcon();
	private ImageIcon elevenIcon = new ImageIcon();
	private ImageIcon bgIcon = new ImageIcon();
	private ImageIcon buIcon = new ImageIcon();
	private ImageIcon brIcon = new ImageIcon();
	private ImageIcon bwIcon = new ImageIcon();
	private ImageIcon ugIcon = new ImageIcon();
	private ImageIcon urIcon = new ImageIcon();
	private ImageIcon uwIcon = new ImageIcon();
	private ImageIcon wgIcon = new ImageIcon();
	private ImageIcon wrIcon = new ImageIcon();
	private ImageIcon grIcon = new ImageIcon();
	private ImageIcon checkIcon = new ImageIcon();
	private ImageIcon xIcon = new ImageIcon();
	private ImageIcon XIcon = new ImageIcon();
	String[][] deckList = new String[1][1];
	ArrayList<Card> creatures = new ArrayList<Card>();
	ArrayList<Card> spells = new ArrayList<Card>();
	ArrayList<Card> lands = new ArrayList<Card>();
	ArrayList<Card> sideboard = new ArrayList<Card>();
	Boolean tableSel = false;
	Boolean table2Sel = false;
	Boolean table3Sel = false;
	Boolean table4Sel = false;

	String home = System.getProperty("user.home");

	
	public static void main(String[] args) throws InvalidKeyException, IOException, AWTException {
		new CardOrganizer();
	}

	public CardOrganizer() throws InvalidKeyException, IOException, AWTException {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame("Virtual Card Organizer");
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception ex) {
				}
				try {
					frame.add(new Interface());
				} catch (InvalidKeyException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				frame.setJMenuBar(menuBar);		
				frame.setResizable(true);	                
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

	public class Interface extends JPanel {
		private JPanel mainList;

		@SuppressWarnings("unchecked")
		public Interface() throws InvalidKeyException, IOException {
			setLayout(new BorderLayout());

			mainList = new JPanel(new GridBagLayout());
			mainList.setPreferredSize(new Dimension(250, 580));

			GridBagConstraints d = new GridBagConstraints();
			d.gridwidth = GridBagConstraints.REMAINDER;
			d.weightx = 1;
			d.weighty = 1;
			mainList.add(new JPanel(), d);

			//use mac menu bar
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
			
			
			
			//rarity icons and buttons	private JToggleButton common = new JToggleButton("C");
			mythicIcon = new ImageIcon(home + "/Desktop/mythic.png");
			rareIcon = new ImageIcon(home + "/Desktop/rare.png");		  
			uncommonIcon = new ImageIcon(home + "/Desktop/uncommon.png");		  
			commonIcon = new ImageIcon(home + "/Desktop/common.png");	
			common = new JToggleButton(commonIcon);
			uncommon = new JToggleButton(uncommonIcon);
			rare = new JToggleButton(rareIcon);
			mythic = new JToggleButton(mythicIcon);
			blackIcon = new ImageIcon(home + "/Desktop/Icons/black_mana.gif");
			greenIcon = new ImageIcon(home + "/Desktop/Icons/green_mana.gif");
			blueIcon = new ImageIcon(home + "/Desktop/Icons/blue_mana.gif");
			whiteIcon = new ImageIcon(home + "/Desktop/Icons/white_mana.gif");
			redIcon = new ImageIcon(home + "/Desktop/Icons/red_mana.gif");
			oneIcon = new ImageIcon(home + "/Desktop/Icons/Symbol_1_mana.gif");
			twoIcon = new ImageIcon(home + "/Desktop/Icons/Symbol_2_mana.gif");
			threeIcon = new ImageIcon(home + "/Desktop/Icons/Symbol_3_mana.gif");
			fourIcon = new ImageIcon(home + "/Desktop/Icons/Symbol_4_mana.gif");
			fiveIcon = new ImageIcon(home + "/Desktop/Icons/Symbol_5_mana.gif");
			sixIcon = new ImageIcon(home + "/Desktop/Icons/Symbol_6_mana.gif");
			sevenIcon = new ImageIcon(home + "/Desktop/Icons/Symbol_7_mana.gif");
			eightIcon = new ImageIcon(home + "/Desktop/Icons/Symbol_8_mana.gif");
			nineIcon = new ImageIcon(home + "/Desktop/Icons/Symbol_9_mana.gif");
			tenIcon = new ImageIcon(home + "/Desktop/Icons/Symbol_10_mana.gif");
			elevenIcon = new ImageIcon(home + "/Desktop/Icons/Symbol_11_mana.gif");
			bgIcon = new ImageIcon(home + "/Desktop/Icons/Symbol_BG_mana.gif");
			buIcon = new ImageIcon(home + "/Desktop/Icons/Symbol_UB_mana.gif");
			brIcon = new ImageIcon(home + "/Desktop/Icons/Symbol_BR_mana.gif");
			bwIcon = new ImageIcon(home + "/Desktop/Icons/Symbol_WB_mana.gif");
			ugIcon = new ImageIcon(home + "/Desktop/Icons/Symbol_GU_mana.gif");
			urIcon = new ImageIcon(home + "/Desktop/Icons/Symbol_UR_mana.gif");
			uwIcon = new ImageIcon(home + "/Desktop/Icons/Symbol_WU_mana.gif");
			wgIcon = new ImageIcon(home + "/Desktop/Icons/Symbol_GW_mana.gif");
			wrIcon = new ImageIcon(home + "/Desktop/Icons/Symbol_RW_mana.gif");
			grIcon = new ImageIcon(home + "/Desktop/Icons/Symbol_RG_mana.gif");
			checkIcon = new ImageIcon(home + "/Desktop/Icons/rsz_check2.png");
			xIcon = new ImageIcon(home + "/Desktop/Icons/cross16.png");
			XIcon = new ImageIcon(home + "/Desktop/Icons/Symbol_X_mana.gif");
			
			//Create the menu bar.
			menuBar = new JMenuBar();

			//Build the first menu.
			menu = new JMenu("File");
			menu.setMnemonic(KeyEvent.VK_A);
			menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
			menuBar.add(menu);

			//a group of JMenuItems
			menuItem = new JMenuItem("Save Collection",KeyEvent.VK_T);
			menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
			menuItem.getAccessibleContext().setAccessibleDescription("Save");
			menu.add(menuItem);
			menuItem2 = new JMenuItem("Backup Collection", KeyEvent.VK_T);
			menuItem2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
			menuItem2.getAccessibleContext().setAccessibleDescription("Backup");
			menu.add(menuItem2);
			menuItem3 = new JMenuItem("Save Deck", KeyEvent.VK_T);
			menu.add(menuItem3);
			menuItem4 = new JMenuItem("Delete Deck", KeyEvent.VK_T);
			menu.add(menuItem4);
			

			setJMenuBar(menuBar);

			//menuItem listener
			menuItem.addActionListener(new ActionListener() {
				@Override
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
				@Override
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
			
			menuItem3.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						saveDeck();
						getDecks();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (MalformedURLException e1) {
						e1.printStackTrace();
					} catch (IOException e3) {
						e3.printStackTrace();
					}
				}
			});
			menuItem4.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

				}
			});

			JPanel p5 = new JPanel(new GridLayout(2,1));
			p5.add(viewDatabase);
			p5.add(set);

			JPanel panel1 = new JPanel(new GridBagLayout());
			panel1.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));

			//TextBox, add, and remove card	
			JPanel p2 = new JPanel(new GridBagLayout());
			scrollQuery = new JScrollPane(p2);

			d.fill = GridBagConstraints.HORIZONTAL;
			d.weightx = 1.0;
			d.gridwidth = 1;
			d.gridx = 0;
			d.gridy = 0;
			d.anchor = GridBagConstraints.PAGE_START;
			panel1.add(textBox, d);
			d.gridy = 1;
			d.ipady = 4;      //make this component tall
			JPanel addRemove = new JPanel(new GridLayout(1, 2));
			addRemove.add(addCard);
			addRemove.add(removeCard);
			panel1.add(addRemove,d);

			//SORT
			JLabel sort = new JLabel("Sort:");
			JPanel panel1Quarter = new JPanel(new GridBagLayout());
			panel1Quarter.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY)); 
			JPanel filter = new JPanel(new GridLayout(1,2));
			filter.add(mycards, d);
			filter.add(allcards,d);
			JPanel unownedSet = new JPanel(new GridLayout(1,2));
			unownedSet.add(unowned);
			set.setPreferredSize(new Dimension(88, 30));
			unownedSet.add(set);
			d.fill = GridBagConstraints.HORIZONTAL;
			d.gridx = 0;
			d.gridy = 0;
			panel1Quarter.add(sort, d);
			d.gridy = 1;
			panel1Quarter.add(filter,d);
			d.gridy =2;
			panel1Quarter.add(unownedSet,d);

			//ColorQuery
			JPanel panel2 = new JPanel(new GridBagLayout());
			panel2.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
			JLabel colors = new JLabel("Color:");
			JPanel andOr = new JPanel(new GridLayout(1,2));
			andOr.add(and);
			andOr.add(or);
			JPanel colorBox = new JPanel(new GridLayout(1,7));
			colorBox.add(red);
			colorBox.add(blue);
			colorBox.add(white);
			colorBox.add(black);
			colorBox.add(green);
			colorBox.add(multi);
			colorBox.add(colorless);
			colorBox.setPreferredSize(new Dimension(0,33));
			d.gridx = 0;
			d.gridy = 0;
			panel2.add(colors, d);
			d.gridx = 0;
			d.gridy = 1;
			panel2.add(colorBox,d);
			d.gridx = 0;
			d.gridy = 2;
			panel2.add(andOr, d);

			//type Jtoggle buttons
			JPanel panel3 = new JPanel(new GridBagLayout());
			panel3.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
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
			JLabel typeL = new JLabel("Type:");
			d.gridy = 3;
			panel3.add(typeL, d);
			d.gridy = 4;
			d.ipady = 14;
			panel3.add(typeBox1, d);
			d.gridy = 5;
			d.weighty = 0.0;
			panel3.add(typeBox2, d);
			d.ipady = 10;

			//rarity buttons
			JPanel panel4 = new JPanel(new GridBagLayout());
			panel4.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
			JPanel rarityBox = new JPanel(new GridLayout(1,4));
			rarityBox.add(common);
			rarityBox.add(uncommon);
			rarityBox.add(rare);
			rarityBox.add(mythic);
			rarityBox.setPreferredSize(new Dimension(0,20));
			JLabel rarityL = new JLabel("Rarity:");
			d.gridx = 0;
			d.gridy = 0;
			panel4.add(rarityL, d);
			d.gridx = 0;
			d.gridy = 1;
			panel4.add(rarityBox, d);

			//power buttons
			JPanel panel5 = new JPanel(new GridBagLayout());
			panel5.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
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
			d.gridy = 0;
			panel5.add(powerL,d);
			d.gridy = 1;
			panel5.add(powerB,d);

			//toughness buttons
			JPanel panel6 = new JPanel(new GridBagLayout());
			panel6.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
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
			d.gridy = 0;
			panel6.add(tL,d);
			d.gridx = 0;
			d.gridy = 1;
			panel6.add(tB,d);
			
			//combobox model
			@SuppressWarnings("rawtypes")
			Vector comboBoxItems=new Vector();
		    comboBoxItems.add("Load Deck");
			model = new DefaultComboBoxModel(comboBoxItems);
			decks = new JComboBox(model);
			getDecks();
			    
			//deckBuild
			JPanel deckB = new JPanel(new GridBagLayout());
			deckB.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
			JPanel dB = new JPanel(new GridBagLayout());
			JLabel on = new JLabel("Deck Build Mode:");
			JLabel sb = new JLabel("Add to Sideboard:");
			JLabel loadD = new JLabel("Load Deck:");
			d.gridx = 0;
			d.gridy=0;
			dB.add(on,d);
			d.insets = new Insets(0,134,0,0);
			dB.add(sb, d);
			d.gridy = 0;
			d.insets = new Insets(-4,105,0,0);
			dB.add(deckBuild,d);
			d.gridx = 1;
			d.insets = new Insets(-4,-5,0,0);
			dB.add(sideBoard,d);
			d.gridx = 0;
			d.insets = new Insets(0,0,0,0);
			d.gridy = 1;
			dB.add(loadD, d);
			d.insets = new Insets(-2,70,0,0);
			decks.setPreferredSize(new Dimension(100,20));
			dB.add(decks,d);
			d.insets = new Insets(0,0,0,0);
			deckB.add(dB, d);

			//add panels to mainList
			GridBagConstraints gb = new GridBagConstraints();
			gb.fill = GridBagConstraints.HORIZONTAL;
			gb.anchor = GridBagConstraints.PAGE_START;
			gb.gridx = 0;
			gb.gridy = 0;
			mainList.add(panel1, gb);
			gb.gridy = 1;
			mainList.add(panel1Quarter, gb);
			gb.gridy = 2;
			mainList.add(deckB, gb);
			gb.gridy = 3;
			mainList.add(panel2,gb);
			gb.gridy = 4;
			mainList.add(panel3,gb);
			gb.gridy = 5;
			mainList.add(panel4, gb);
			gb.gridy = 6;
			//mainList.add(panel1Half, gb);
			gb.gridy = 7;
			mainList.add(panel5, gb);
			gb.gridy = 8;
			gb.weighty = 1.0;
			mainList.add(panel6, gb);


			//JTable
			JPanel p4 = new JPanel();
			String[] cardNames = organizer.getAllArray();
			ArrayList<String[]> values = returnValues(cardNames);
			queryList = cardNames;	
			dataModel = new DefaultTableModel();
			dataModel.setColumnCount(4);
			dataModel.setRowCount(organizer.getAllArray().length);
			dataModel.setColumnIdentifiers(new String[]{"Name", "CMC", "Type", "Rarity", "Set"});
			table = new JTable(dataModel) {
				private static final long serialVersionUID = 1L;
				@Override
				public boolean isCellEditable(int row, int column) {                
					return false;  
				};
				@SuppressWarnings({ "unchecked", "rawtypes" })
				@Override
				public Class getColumnClass(int columnIndex) {
				    if(columnIndex == 1 || columnIndex == 3 || columnIndex == 5){
				        return ImageIcon.class;
				    }
				    return Object.class;
				}
			};
			table.getColumnModel().getColumn(0).setPreferredWidth(150);
			refreshTable(cardNames, values);

			JScrollPane scrollList = new JScrollPane(table);
			p4.add(scrollList);
			JScrollPane scroll =  new JScrollPane(mainList);

			ImageIcon image;
			label = new JLabel();
			try {
				image = new ImageIcon(ImageIO.read(new File(home +"/Desktop/VCO/Pictures Try 2/" + "card_back" + ".jpg")));
				label.setIcon(image);
			} catch (Exception ex) {
				label.setIcon(organizer.getCard("card_back").getImg());
			}
	
			ownedNum.setFont(new Font("Serif", Font.PLAIN, 36));
			price.setFont(new Font("Serif", Font.PLAIN, 36));
			priceL.setFont(new Font("Serif", Font.PLAIN, 26));

			//final formatting
			JPanel p3 = new JPanel(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();

			//Queries
			c.insets = new Insets(0,5,0,0);
			c.gridx = 0;
			c.gridy = 0;
			c.weightx = 5;
			c.weighty = 5;
			c.fill = GridBagConstraints.BOTH;
			scroll.setPreferredSize(new Dimension(300, 503));
			JPanel queryContainer = new JPanel(new GridBagLayout());
			queryContainer.setPreferredSize(new Dimension(300, 470));
			queryContainer.add(scroll, c);
			JPanel try2 = new JPanel();
			try2.add(queryContainer);
			add(try2);
			c.insets = new Insets(0,-30,0,0);
			c.anchor = GridBagConstraints.WEST;	
			p3.add(try2, c);
			c.weightx = 1;
			c.weighty = 1;

			//JTable
			c.insets = new Insets(0,0,0,0);
			c.fill = GridBagConstraints.BOTH;
			c.gridx = 1;
			c.gridy = 0;
			JPanel box = new JPanel(new GridBagLayout());
			c.weightx = 10;
			c.weighty = 10;
			c.anchor = GridBagConstraints.PAGE_START;
			box.add(scrollList, c);
			p3.add(box,c);
			
			//make deck builder window
			JPanel deck = new JPanel(new GridBagLayout());
			deck.setPreferredSize(new Dimension(1400, 310));
			p3.setPreferredSize(new Dimension(1400, 490));
			
			dataModel2 = new DefaultTableModel();
			dataModel2.setColumnCount(2);
			dataModel2.setRowCount(0);
			dataModel2.setColumnIdentifiers(new String[]{"Creatures", "#"});
			dataModel3 = new DefaultTableModel();
			dataModel3.setColumnCount(2);
			dataModel3.setRowCount(0);
			dataModel3.setColumnIdentifiers(new String[]{"Spells", "#"});
			dataModel4 = new DefaultTableModel();
			dataModel4.setColumnCount(2);
			dataModel4.setRowCount(0);
			dataModel4.setColumnIdentifiers(new String[]{"Land", "#"});
			dataModel4.setColumnCount(2);
			dataModel5 = new DefaultTableModel();
			dataModel5.setRowCount(0);
			dataModel5.setColumnIdentifiers(new String[]{"Side Board", "#"});

			table2 = new JTable(dataModel2) {
				private static final long serialVersionUID = 1L;
				@Override
				public boolean isCellEditable(int row, int column) {                
					return false;  
				};
			};
			table2.getColumnModel().getColumn(0).setPreferredWidth(150);
			
			table3 = new JTable(dataModel3) {
				private static final long serialVersionUID = 1L;
				@Override
				public boolean isCellEditable(int row, int column) {                
					return false;  
				};
			};
			table3.getColumnModel().getColumn(0).setPreferredWidth(150);

			table4 = new JTable(dataModel4) {
				private static final long serialVersionUID = 1L;
				@Override
				public boolean isCellEditable(int row, int column) {                
					return false;  
				};
			};
			table4.getColumnModel().getColumn(0).setPreferredWidth(150);

			table5 = new JTable(dataModel5) {
				private static final long serialVersionUID = 1L;
				@Override
				public boolean isCellEditable(int row, int column) {                
					return false;  
				};
			};
			table5.getColumnModel().getColumn(0).setPreferredWidth(150);

			JScrollPane scrollList2 = new JScrollPane(table2);
			JScrollPane scrollList3 = new JScrollPane(table3);
			JScrollPane scrollList4 = new JScrollPane(table4);
			JScrollPane scrollList5 = new JScrollPane(table5);

			GridBagConstraints b = new GridBagConstraints();
			b.gridx = 0;
			b.gridy = 0;
			b.fill = GridBagConstraints.BOTH;
			b.weightx = 1;
			b.weighty = 1;
			b.anchor = GridBagConstraints.PAGE_START;
			deck.add(scrollList2, b);
			b.gridx = 1;
			deck.add(scrollList3, b);
			b.gridx = 2;
			deck.add(scrollList4, b);
			b.gridx = 3;
			deck.add(scrollList5, b);
			
			//deckbuild and query Panel
			GridBagConstraints g = new GridBagConstraints();
			g.anchor = GridBagConstraints.PAGE_START;
			g.fill = GridBagConstraints.BOTH;			
			JPanel topBottom = new JPanel(new GridBagLayout());
			g.weightx = 1;
			g.weighty = 1;
			g.gridwidth =3;
			g.gridx = 0;
			g.gridy = 0;
			g.ipady = 140;      
			topBottom.add(p3, g);
			g.gridy = 1;
			g.ipady = 0; 
			topBottom.add(deck,g);
			g.ipady = 0; 
			g.gridx = 0;
			g.gridy = 0;
			
			//card view panel
			JPanel cardV = new JPanel(new GridBagLayout());
			g.gridx = 0;
			g.gridy = 0;
			g.anchor = GridBagConstraints.PAGE_START;
			JPanel collectionVal = new JPanel(new GridBagLayout());
			g.insets = new Insets(0,0,0,0);
			collectionVal.add(collectionV, g);
			g.insets = new Insets(0,110,0,0);
			collectionVal.add(mSign,g);
			g.insets = new Insets(0,120,0,0);
			priceCollection();
			collectionVal.add(collectionValue, g);
			g.insets = new Insets(0,0,0,0);
			cardV.add(collectionVal, g);
			g.gridy = 1;
			cardV.add(label, g);
			JPanel ownedBox = new JPanel(new GridBagLayout());
			g.gridy = 0;
			ownedBox.add(ownedL, g);
			g.insets = new Insets(0,55,0,0);
			ownedNum.setFont(new Font("Serif", Font.PLAIN, 36));
			ownedBox.add(ownedNum, g);
			g.insets = new Insets(0,90,0,0);
			ownedBox.add(priceL, g);
			g.insets = new Insets(0,105,0,0);
			ownedBox.add(price, g);
			g.insets = new Insets(0,0,0,0);
			g.gridy = 2;
			cardV.add(ownedBox, g);
			JLabel notesL = new JLabel("Notes:");
			g.gridx = 0;
			g.gridy = 3;
			cardV.add(notesL, g);
			notes.setPreferredSize(new Dimension(0, 280));
			g.gridy = 4;
			cardV.add(notes,g);
		
			//combine tB and cV
			JPanel combine = new JPanel(new GridBagLayout());
			g.fill = GridBagConstraints.BOTH;
			g.weightx = 1;
			g.weighty = 1;
			g.gridx = 0;
			g.gridy = 0;
			g.gridwidth = 1;
			topBottom.setPreferredSize(new Dimension(880, 800));
			JPanel try3 = new JPanel();
			try3.add(topBottom);
			add(try3);
			g.anchor = GridBagConstraints.FIRST_LINE_START;
			combine.add(try3, g);
			g.gridx = 1;
			g.weightx = 0;
			g.weighty = 0;
			g.ipadx = 1;
			combine.add(cardV, g);
			add(combine);
			
			//remaps ENTER key in JTable to addCard()
			table.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
			table.getActionMap().put("Enter", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(enterC == true) { //use textbox string if checked
						selected = textBox.getText();
					}
					try {
						if(selected != null)
							organizer.addCard(selected);
					} catch (InvalidKeyException e1) {
						e1.printStackTrace();
					}
					//determine view and refresh
					if(isQuery != true) {
						if(isMyCards == true)
							viewMyCards(); 
						else
							viewAll();
					} else if(isQuery == true) { 	//determine if query view and refresh
						ArrayList<String[]> values = returnValues(queryList);
						refreshTable(queryList, values);
					}
				}
			});
			
			deckBuild.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					if(deckCheck == false) {
						deckCheck = true;
					} else {
						deckCheck = false;
					}
				}
			});

			tribalCheck.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					tribalC = true;
					nameC = false;
					textC = false;
					enterC = false;
				}
			});

			nameCheck.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					tribalC = false;
					nameC = true;
					textC = false;
					enterC = false;
				}
			});

			textCheck.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					tribalC = false;
					nameC = false;
					textC = true;
					enterC = false;
				}
			});

			enterCheck.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					tribalC = false;
					nameC = false;
					textC = false;
					enterC = true;
				}
			});

			sevenP.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					buttonSelected = abstractButton.getModel().isSelected();
					power = 7;
					if(buttonSelected == false)
						power = -1;
					query();
				}
			});

			sixP.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					buttonSelected = abstractButton.getModel().isSelected();
					power = 6;
					if(buttonSelected == false)
						power = -1;
					query();
				}
			});

			oneP.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					buttonSelected = abstractButton.getModel().isSelected();
					power = 1;
					if(buttonSelected == false)
						power = -1;
					query();
				}
			});

			twoP.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					buttonSelected = abstractButton.getModel().isSelected();
					power = 2;
					if(buttonSelected == false)
						power = -1;
					query();
				}
			});

			threeP.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					buttonSelected = abstractButton.getModel().isSelected();
					power = 3;
					if(buttonSelected == false)
						power = -1;
					query();
				}
			});

			fourP.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					buttonSelected = abstractButton.getModel().isSelected();
					power = 4;
					if(buttonSelected == false)
						power = -1;
					query();
				}
			});

			fiveP.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					buttonSelected = abstractButton.getModel().isSelected();
					power = 5;
					if(buttonSelected == false)
						power = -1;
					query();
				}
			});

			oneT.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					buttonSelected = abstractButton.getModel().isSelected();
					toughness = 1;
					if(buttonSelected == false)
						toughness = -1;
					query();
				}
			});


			twoT.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					buttonSelected = abstractButton.getModel().isSelected();
					toughness = 2;
					if(buttonSelected == false)
						toughness = -1;
					query();
				}
			});


			threeT.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					buttonSelected = abstractButton.getModel().isSelected();
					toughness = 3;
					if(buttonSelected == false)
						toughness = -1;
					query();
				}
			});


			fourT.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					buttonSelected = abstractButton.getModel().isSelected();
					toughness = 4;
					if(buttonSelected == false)
						toughness = -1;
					query();
				}
			});


			fiveT.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					buttonSelected = abstractButton.getModel().isSelected();
					toughness = 5;
					if(buttonSelected == false)
						toughness = -1;
					query();
				}
			});


			sixT.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					buttonSelected = abstractButton.getModel().isSelected();
					toughness = 6;
					if(buttonSelected == false)
						toughness = -1;
					query();
				}
			});


			sevenT.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					buttonSelected = abstractButton.getModel().isSelected();
					toughness = 7;
					if(buttonSelected == false)
						toughness = -1;
					query();
				}
			});

			creature.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					buttonSelected = abstractButton.getModel().isSelected();
					type2 = "creature";
					if(buttonSelected == false)
						type2 = "n";
					query();
				}
			});

			enchantment.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					buttonSelected = abstractButton.getModel().isSelected();
					type2 = "enchantment";
					if(buttonSelected == false)
						type2 = "n";
					query();
				}
			});

			instant.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					buttonSelected = abstractButton.getModel().isSelected();
					type2 = "instant";
					if(buttonSelected == false)
						type2 = "n";
					query();
				}
			});

			sorcery.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					buttonSelected = abstractButton.getModel().isSelected();
					type2 = "sorcery";
					if(buttonSelected == false)
						type2 = "n";
					query();
				}
			});

			artifact.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					buttonSelected = abstractButton.getModel().isSelected();
					type2 = "artifact";
					if(buttonSelected == false)
						type2 = "n";
					query();
				}
			});

			land.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					buttonSelected = abstractButton.getModel().isSelected();
					type2 = "land";
					if(buttonSelected == false)
						type2 = "n";
					query();
				}
			});

			planeswalker.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					buttonSelected = abstractButton.getModel().isSelected();
					type2 = "planeswalker";
					if(buttonSelected == false)
						type2 = "n";
					query();
				}
			});

			red.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					redSelected = abstractButton.getModel().isSelected();
					color = "red";
					if(redSelected == false)
						color = "n";
					query();
				}
			});

			white.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					whiteSelected = abstractButton.getModel().isSelected();
					color = "white";
					if(whiteSelected == false)
						color = "n";
					query();
				}
			});

			blue.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					blueSelected = abstractButton.getModel().isSelected();
					color = "blue";
					if(blueSelected == false)
						color = "n";
					query();
				}
			});

			green.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					greenSelected = abstractButton.getModel().isSelected();
					color = "green";
					if(greenSelected == false)
						color = "n";
					query();
				}
			});

			black.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					blackSelected = abstractButton.getModel().isSelected();
					color = "black";
					if(blackSelected == false)
						color = "n";
					query();
				}
			});

			colorless.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					colorlessSelected = abstractButton.getModel().isSelected();
					color = "colorless";
					if(colorlessSelected == false)
						color = "n";
					query();
				}
			});

			multi.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					multiSelected = abstractButton.getModel().isSelected();
					color = "multi";
					if(multiSelected == false)
						color = "n";
					query();
				}
			});

			mythic.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					buttonSelected = abstractButton.getModel().isSelected();
					rarityC = "mythic";
					if(buttonSelected == false)
						rarityC = "n";
					query();
				}
			});

			rare.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					buttonSelected = abstractButton.getModel().isSelected();
					rarityC = "rare";
					if(buttonSelected == false)
						rarityC = "n";
					query();
				}
			});

			uncommon.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					buttonSelected = abstractButton.getModel().isSelected();
					rarityC = "uncommon";
					if(buttonSelected == false)
						rarityC = "n";
					query();
				}
			});

			common.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					buttonSelected = abstractButton.getModel().isSelected();
					rarityC = "common";
					if(buttonSelected == false)
						rarityC = "n";
					query();
				}
			});
			
			removeCard.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {			
					isRemoved = false;
					if(enterC == true) { 
						selected = textBox.getText();
					}
					if(deckCheck!= true){
					if(isMyCards == true) {
						if(selected != null)
							organizer.removeCard(selected, "root");
						if(isQuery == true) {
							ArrayList<String> filterOwned = new ArrayList<String>(); //filters out owned = 0 cards 
							for (int i = 0; i < queryList.length; i++) {
								Card card = null;
								try {
									card = organizer.getCard(queryList[i]);
								} catch (InvalidKeyException e2) {
									e2.printStackTrace();
								}
								if(card.owned != 0)
									filterOwned.add(queryList[i]);
							}
							String[] myCards = new String[filterOwned.size()];
							filterOwned.toArray(myCards);
							queryList = myCards;
							ArrayList<String[]> values = returnValues(queryList);
							refreshTable(queryList, values);
							return;
						}
					viewMyCards();
					}
					if(isMyCards != true) {
						if(selected != null)
							organizer.removeCard(selected, "cD");
						if(isQuery == true) {
							try {
								queryList = organizer.query(queryList, color, power,toughness,owned, type1, type2, rarityC, tribal, cardText, name);
							} catch (InvalidKeyException e2) {
								e2.printStackTrace();
							}
							ArrayList<String[]> values = returnValues(queryList);
							refreshTable(queryList, values);
							return;
							}
						viewAll(); 
						}
					} else {
						try{
						organizer.removeCardFromDeck(selected);
						refreshALs();
						} catch (Exception ex) {
							//tis fine
						}
						try{
							refreshDeckTable(creatures, dataModel2);
						}catch (Exception e2) {
							e2.printStackTrace();
							}
						try{
							refreshDeckTable(spells, dataModel3);
						}catch (Exception e2) {
							e2.printStackTrace();
							}
						try{
							refreshDeckTable(lands, dataModel4);
						}catch (Exception e2) {
							e2.printStackTrace();
							}
					}
				}
			});


			addCard.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(enterC == true) { //use textbox string if checked
						selected = textBox.getText();
					}
					if(deckCheck!= true){
					try {
						if(selected != null)
							organizer.addCard(selected);
					} catch (InvalidKeyException e1) {
						e1.printStackTrace();
					}
					//determine view and refresh
					if(isQuery != true) {
						if(isMyCards == true)
							viewMyCards(); 
						else
							viewAll();
					} else if(isQuery == true) { 	//determine if query view and refresh
						ArrayList<String[]> values = returnValues(queryList);
						refreshTable(queryList, values);
					}
					} else {
						organizer.addCardToDeck(selected);
						refreshALs();
						try{
							refreshDeckTable(creatures, dataModel2);
						}catch (Exception e2) {
							e2.printStackTrace();
							}
						try{
							refreshDeckTable(spells, dataModel3);
						}catch (Exception e2) {
							e2.printStackTrace();
							}
						try{
							refreshDeckTable(lands, dataModel4);
						}catch (Exception e2) {
							e2.printStackTrace();
							}
						}
					}
			});
			
			mycards.addActionListener(new ActionListener() { //refreshes table to your collection
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					dataModel.setColumnCount(4);
					dataModel.setColumnIdentifiers(new String[]{"Name", "CMC", "Type", "Rarity", "Set"});
					viewMyCards();
				}
			});

			allcards.addActionListener(new ActionListener() { //refreshes table to complete database
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					dataModel.setColumnCount(5);
					dataModel.setColumnIdentifiers(new String[]{"Name", "CMC", "Type", "Rarity", "Set", "Owned"});
					viewAll();
				}
			});

			set.addItemListener(new ItemListener() { //refreshes table to query the current table by set
				@Override
				public void itemStateChanged(ItemEvent e) {
					if(isMyCards == true) {
						dataModel.setColumnCount(4);
						dataModel.setColumnIdentifiers(new String[]{"Name", "CMC", "Type", "Rarity", "Set"});
					}
					viewSet();
				}
			});
			
			decks.addItemListener(new ItemListener() { //refreshes table to query the current table by set
				@Override
				public void itemStateChanged(ItemEvent e) {
				String s = (String) decks.getSelectedItem();
				try {
					loadDeck(s);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				}
			});

			//listens for textBox input
			textBox.getDocument().addDocumentListener(new DocumentListener() {
				@Override
				public void changedUpdate(DocumentEvent e) {
				text();
				}
				@Override
				public void removeUpdate(DocumentEvent e) {
				text();
				}
				@Override
				public void insertUpdate(DocumentEvent e) {
				text();
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
						e1.printStackTrace();
					}
					isText = false;
					if(selected != null) {
						refreshCardValues(table);
					}
				}
			});
			table2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					try {
						selected = (String) table2.getValueAt(table2.getSelectedRow(), 0);
					} catch (ArrayIndexOutOfBoundsException e1) {
						e1.printStackTrace();
					}
					isText = false;
					if(selected != null) {
						refreshCardValues(table2);
					}
				}
			});
			table3.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					try {
					} catch (ArrayIndexOutOfBoundsException e1) {
						e1.printStackTrace();
					}
					isText = false;
					if(selected != null) {
						refreshCardValues(table3);
					}
				}
			});
			table4.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					try {
						selected = (String) table4.getValueAt(table4.getSelectedRow(), 0);
					} catch (ArrayIndexOutOfBoundsException e1) {
						e1.printStackTrace();
					}
					isText = false;
					if(selected != null) {
						refreshCardValues(table4);
					}
				}
			});
		}
	}
	
	/**
	 * Set JCombo list to current saved decks
	 */
	public void getDecks() {
	String filepath = "/Users/eorndahl/Desktop/VCO/Decks";
	File directory = new File(filepath);
	String[] files = directory.list();
	model.removeAllElements();
	for (int i = 0; i < files.length; i++){
		String[] splits = new String[2];
		splits = files[i].split("\\.");
		if(i != 0) {
			model.addElement(splits[0]);
		}
		}
	}
	
	/**
	 * Return a combined icon 
	 */
	
	public Icon iconCombine(String s) {
		ArrayList<Icon> CMC = new ArrayList<Icon>();
		if(s != null) {
		for(int i = 0; i< s.length(); i++) {
			if(String.valueOf(s.charAt(i)).equals("X"))
				CMC.add(XIcon);
			if(String.valueOf(s.charAt(i)).equals("1"))
				CMC.add(oneIcon);
			if(String.valueOf(s.charAt(i)).equals("2"))
				CMC.add(twoIcon);
			if(String.valueOf(s.charAt(i)).equals("3"))
				CMC.add(threeIcon);
			if(String.valueOf(s.charAt(i)).equals("4"))
				CMC.add(fourIcon);
			if(String.valueOf(s.charAt(i)).equals("5"))
				CMC.add(fiveIcon);
			if(String.valueOf(s.charAt(i)).equals("6"))
				CMC.add(sixIcon);
			if(String.valueOf(s.charAt(i)).equals("7"))
				CMC.add(sevenIcon);
			if(String.valueOf(s.charAt(i)).equals("8"))
				CMC.add(eightIcon);
			if(String.valueOf(s.charAt(i)).equals("9"))
				CMC.add(nineIcon);
			if(String.valueOf(s.charAt(i)).equals("10"))
				CMC.add(tenIcon);
			if(String.valueOf(s.charAt(i)).equals("11"))
				CMC.add(elevenIcon);
			if(String.valueOf(s.charAt(i)).equals("R"))
				CMC.add(redIcon);
			if(String.valueOf(s.charAt(i)).equals("W"))
				CMC.add(whiteIcon);
			if(String.valueOf(s.charAt(i)).equals("U"))
				CMC.add(blueIcon);
			if(String.valueOf(s.charAt(i)).equals("B"))
				CMC.add(blackIcon);
			if(String.valueOf(s.charAt(i)).equals("G"))
				CMC.add(greenIcon);
			if(String.valueOf(s.charAt(i)).equals("(")) {
				String multi = "(";
				for(int j = 1; j < 5; j++) {
					multi+=String.valueOf(s.charAt(i +j));
				}
				if(multi.equals("(r/w)"))
					CMC.add(wrIcon);
				if(multi.equals("(r/g)"))
					CMC.add(grIcon);
				if(multi.equals("(w/b)"))
					CMC.add(bwIcon);
				if(multi.equals("(w/u)"))
					CMC.add(uwIcon);
				if(multi.equals("(b/r)"))
					CMC.add(brIcon);
				if(multi.equals("(b/g)"))
					CMC.add(bgIcon);
				if(multi.equals("(u/r)"))
					CMC.add(urIcon);
				if(multi.equals("(u/b)"))
					CMC.add(buIcon);
				if(multi.equals("(g/w)"))
					CMC.add(wgIcon);
				if(multi.equals("(g/u)"))
					CMC.add(ugIcon);
				i+=4;
				}
			}
		}
		Icon[] icons = new Icon[CMC.size()];
		CMC.toArray(icons);
		Icon icon = new CompoundIcon(icons);
		return icon;
	}
	/**
	 * Icon to image
	 * @param icon
	 * @return
	 */
	static Image iconToImage(Icon icon) {
		   if (icon instanceof ImageIcon) {
		      return ((ImageIcon)icon).getImage();
		   } 
		   else {
		      int w = icon.getIconWidth();
		      int h = icon.getIconHeight();
		      GraphicsEnvironment ge = 
		        GraphicsEnvironment.getLocalGraphicsEnvironment();
		      GraphicsDevice gd = ge.getDefaultScreenDevice();
		      GraphicsConfiguration gc = gd.getDefaultConfiguration();
		      BufferedImage image = gc.createCompatibleImage(w, h);
		      Graphics2D g = image.createGraphics();
		      icon.paintIcon(null, g, 0, 0);
		      g.dispose();
		      return image;
		   }
		 }
	
	/**
	 * we want the x and o to be resized when the JFrame is resized
	 */
	public void resizeImage(int biggerWidth, int biggerHeight) throws InvalidKeyException, IOException {
	    int type = BufferedImage.TYPE_INT_ARGB;

	    BufferedImage resizedImage = new BufferedImage(biggerWidth, biggerHeight, type);
	    Graphics2D g = resizedImage.createGraphics();

	    g.setComposite(AlphaComposite.Src);
	    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    
	    ImageIcon icon = new ImageIcon(ImageIO.read(new File(home +"/Desktop/VCO/Pictures Try 2/" + "card_back" + ".jpg")));
	    Image card = iconToImage(icon);
	    g.drawImage(card, 0, 0, biggerWidth, biggerHeight, this);
	    g.dispose();
	    Image image = (Image)resizedImage;
		try {
			label.setIcon((Icon) image);
		} catch (Exception ex) {
			label.setIcon(organizer.getCard("card_back").getImg());
		}
	}
	
	/**
	 * Refresh card values (price/owned/icon)
	 */
	public void refreshCardValues(JTable j) {
		if(selected != null) {
			if(isText!= true) {
					j.requestFocusInWindow();
				}
		try {
			selected = (String) j.getValueAt(j.getSelectedRow(), 0);
		} catch (ArrayIndexOutOfBoundsException e1) {
			//e1.printStackTrace(); //this will not be selected at times which is ok
		}
		try {
			label.setIcon(organizer.getCard(selected).getImg());
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			ownedNum.setText(Integer.toString(organizer.getCard(selected).getOwned()));
			price.setText(organizer.getCard(selected).price);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		}
	}
	/**
	 * Price collection
	 * @throws InvalidKeyException 
	 */  
	public void priceCollection() throws InvalidKeyException {
		String[] names = organizer.getAllArray();
		double sum = 0;
		for (int i = 0; i < names.length; i++) {
			Card card2 = new Card();
			card2 = organizer.getCard(names[i]);
			String value = (card2.price);
			double value2 = Double.parseDouble(value);
			if(card2.owned > 0) {
				sum += value2 * card2.owned;
			} else { 
				sum += value2;
			}
		}
		sum = (double)((int)(sum * 100))/100;
		String total = Double.toString(sum);
		collectionValue.setText(total);
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
		ArrayList<Card> cards = new ArrayList<Card>();
		for(int i = 0; i < all.length; i++) {
			try {
				cards.add(organizer.getCard(all[i]));
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			}
		}	
		int toRemove = dataModel.getRowCount() - s.length; //get # of rows to remove which = current rowCount - querylist length
		for(int i = 0; i < toRemove; i++) {
			if(dataModel.getRowCount() > 2)
				dataModel.removeRow(dataModel.getRowCount() - 1);
		}	
		int toAdd = s.length - dataModel.getRowCount(); //get # of rows to add if previously within shorter query = qList length - rowCount
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
		for(int i = 0; i < dataModel.getRowCount(); i++) {
			dataModel.setValueAt(null,i, 3);
		}
		for(int i = 0; i < dataModel.getRowCount(); i++) {
			dataModel.setValueAt(null,i, 4);
		}
		if(!isMyCards) {
		for(int i = 0; i < dataModel.getRowCount(); i++) {
			dataModel.setValueAt(null,i, 5);
		}
		}
		for(int i = 0; i < all.length; i++) {
			dataModel.setValueAt(all[i],i, 0);
		}
		for(int i = 0; i < all.length; i++) {
			dataModel.setValueAt(iconCombine(cards.get(i).CMC),i, 1);
		}
		for(int i = 0; i < all.length; i++) {
			dataModel.setValueAt(cards.get(i).type2,i, 2);
		}
		for(int i = 0; i < all.length; i++) {
			if(rarity[i] == "Mythic") {
				dataModel.setValueAt(mythicIcon,i, 3);
			} else if(rarity[i] == "Rare"){
			dataModel.setValueAt(rareIcon,i, 3); 
			} else if(rarity[i] == "Uncommon"){
				dataModel.setValueAt(uncommonIcon,i, 3); 
			} else if(rarity[i] == "Common"){
				dataModel.setValueAt(commonIcon,i, 3); 
			}
		}
		for(int i = 0; i < all.length; i++) {
			dataModel.setValueAt(stringSet[i],i, 4);
		}
		if(!isMyCards) {
		for(int i = 0; i < all.length; i++) {
			if(owned[i].equals("✓")) {
				dataModel.setValueAt(checkIcon,i, 5);
			} else
			dataModel.setValueAt(xIcon,i, 5);
		}
		}
		table.repaint(); 
		refreshCardValues(table);
	}
	
	public void refreshDeckTable(ArrayList<Card> al, DefaultTableModel m) {
		String[] names = new String[al.size()];			
		int toRemove = m.getRowCount() - al.size(); //get # of rows to remove which = current rowCount - querylist length
		for(int i = 0; i < toRemove; i++) {
			if(m.getRowCount() > 2)
				m.removeRow(m.getRowCount() - 1);
		}	
		int toAdd = al.size() - m.getRowCount(); //get # of rows to add if previously within shorter query = qList length - rowCount
		for(int i = 0; i < toAdd; i++)
			m.addRow(new Object[]{"","",""});
		
		for(int i = 0; i < m.getRowCount(); i++) {
			m.setValueAt(null,i, 0);
		}
		for(int i = 0; i < m.getRowCount(); i++) {
			m.setValueAt(null,i, 1);
		}
		for(int i = 0; i < al.size(); i++) {
			m.setValueAt(al.get(i).name,i, 0);
		}
		for(int i = 0; i < names.length; i++) {
			m.setValueAt(al.get(i).cardsInDeck,i, 1);
		}
	}

	/**
	 * This method, given a String[] containing a list of names, returns an ArrayList<String[]> containing
	 * the String[]s for the number of each card owned and its rarity.
	 * @param s
	 * @return
	 */
	public ArrayList<String[]> returnValues(String[] s) {
		ArrayList<Integer> ownedList = new ArrayList<Integer>();
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
	 * Save Deck
	 * @throws IOException 
	 */
	
	public void saveDeck() throws IOException {
		ArrayList<String> deck = new ArrayList<String>();
		for(int i = 0; i < creatures.size(); i++) {
			deck.add(":" + creatures.get(i).name + ":" + creatures.get(i).cardsInDeck);
		}
		for(int i = 0; i < spells.size(); i++) {
			deck.add(":" + spells.get(i).name + ":" + spells.get(i).cardsInDeck);
		}
		for(int i = 0; i < lands.size(); i++) {
			deck.add(":" + lands.get(i).name + ":" + lands.get(i).cardsInDeck);
		}
		String s = "";
		for(int i = 0; i < deck.size(); i++){
			s += deck.get(i) + "\n";
		}
		String inputValue = JOptionPane.showInputDialog(null, "Enter name", "Save Deck", JOptionPane.PLAIN_MESSAGE);
		File f = new File(home + "/Desktop/VCO/Decks/" + inputValue+ ".txt");
		f.createNewFile();
		PrintWriter out = new PrintWriter(home + "/Desktop/VCO/Decks/"+ inputValue +".txt");
		out.print(s);
		out.close();
	}
	
	/**
	 * Delete deck
	 */
	
	public void deleteDeck() {
		
	}
	
	/**
	 * Refresh ArrayList values
	 */
	
	public void refreshALs() {
		creatures = new ArrayList<Card>();
		spells = new ArrayList<Card>();
		lands = new ArrayList<Card>();
		ArrayList<Card> temp = organizer.entries("deck");
		for(int i = 0; i < temp.size(); i++) {
			Card card = temp.get(i);
			if (card!= null) {
			if(card.type2.equals("creature") && !creatures.contains(card)) {
				creatures.add(card);
			}
			else if(card.type2.equals("instant") && !spells.contains(card)
					|| card.type2.equals("sorcery") && !spells.contains(card)
					||card.type2.equals("enchantment")&& !spells.contains(card)
					||card.type2.equals("planeswalker")&& !spells.contains(card)) {
				spells.add(card);
			}
			else if(card.type2.equals("land") && !lands.contains(card)) {
				lands.add(card);
			}
		}
		}
	}
	
	/**
	 * Load deck
	 * @throws IOException 
	 */
	@SuppressWarnings("static-access")
	public void loadDeck(String s) throws IOException {
		StringBuilder sFile = new StringBuilder();
		sFile = organizer.readFromFile(home + "/Desktop/VCO/Decks/" + s + ".txt");
		organizer.resetDeck();
		creatures = new ArrayList<Card>();
		spells = new ArrayList<Card>();
		lands = new ArrayList<Card>();
		String[] line = sFile.toString().split(":");
		int z = 0;
		String d = "";
		for(int i = 1; i < line.length; i++) {
			if(i % 2 != 0) {
				d = line[i];
			} else {
				z = Integer.parseInt(line[i]);
				for(int j = 0; j < z; j++)
					organizer.addCardToDeck(d);
			}	
		}
		refreshALs();
		try{
			refreshDeckTable(creatures, dataModel2);
		}catch (Exception e2) {
			e2.printStackTrace();
			}
		try{
			refreshDeckTable(spells, dataModel3);
		}catch (Exception e2) {
			e2.printStackTrace();
			}
		try{
			refreshDeckTable(lands, dataModel4);
		}catch (Exception e2) {
			e2.printStackTrace();
			}
	}

	/**
	 * Refreshes table with query
	 */
	public void query() {		
		isQuery = true;
		String[] querySet = null;
		String[] myCards = organizer.getAllArray();
		String[] allCards = organizer.getCategory("cD");
		if(isSet == true) {
			querySet = queryList;
		} else if(isMyCards) {
			querySet = myCards;
		} else 
			querySet = allCards;
		try {
			queryList = organizer.query(querySet, color, power,toughness,owned, type1, type2, rarityC, tribal, cardText, name);
		} catch (Exception e1) {
			e1.printStackTrace();
			}
		java.util.Arrays.sort(queryList);
		if(owned == -1 && color.equals("n") && type1.equals("n") && type2.equals("n") 
				&& power == -1 && toughness == -1 && rarityC.equals("n") && tribal.equals("n") 
				&& cardText.equals("n") && name.equals("n")) { //if no input for query
			isQuery = false;
			if (isMyCards == true) {
				viewMyCards();
			} else if(isSet == true) {
				viewSet();
			} else {
				viewAll();
			}
		} else {
			ArrayList<String[]> values = returnValues(queryList);
			refreshTable(queryList, values);
			refreshCardValues(table);	
		}
	}
	
	/**
	 * Refresh table to your collection
	 * @throws InvalidKeyException 
	 */
	public void viewMyCards() {
		isMyCards = true;
		isSet = false;
		String[] my = null;
		if(isQuery != true) {
			my = organizer.getAllArray();
		} else {
		try {
			my = organizer.query(organizer.getCategory("cD"), color, power,toughness,owned, type1, type2, rarityC, tribal, cardText, name);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			}
		}
		ArrayList<String> filterOwned = new ArrayList<String>();
		for (int i = 0; i < my.length; i++) {
			Card card = null;
			try {
				card = organizer.getCard(my[i]);
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			}
			if(card.owned != 0)
				filterOwned.add(my[i]);
		}
		String[] myCards = new String[filterOwned.size()];
		filterOwned.toArray(myCards);
		queryList = myCards;
		ArrayList<String[]> values = returnValues(myCards);
		refreshTable(myCards, values);
	}

	/**
	 * Refresh table to entire database
	 */
	public void viewAll() {
		isMyCards = false;
		isSet = false;
		String[] all = null;
		if(isQuery != true) {
			all = organizer.getCategory("cD");
		} else {
			try {
				all = organizer.query(organizer.getCategory("cD"), color, power,toughness,owned, type1, type2, rarityC, tribal, cardText, name);
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			}
		}
		queryList = all;
		ArrayList<String[]> values = returnValues(all);
		refreshTable(all, values);
	}

	/**
	 * Query current table by set
	 */
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
		if(isMyCards == true) { //skims your collection
			ArrayList<String> arr = new ArrayList<String>();
			String[] mine = organizer.getAllArray();
			for(int i = 0; i < mine.length; i++) {
//				try {
				try {
					if(organizer.getCard(mine[i]).rarity != null)
						if(organizer.getCard(mine[i]).rarity.contains(sel))
							arr.add(mine[i]);
				} catch (InvalidKeyException e) {
					e.printStackTrace();
				}
//				} catch (Exception ex) {
//					ex.printStackTrace(); //Not quite sure what is going wrong here buuut its small as far as I can tell
//				}
			}
			all = new String[arr.size()];
			arr.toArray(all);
		}
		queryList = all;
		ArrayList<String[]> values = returnValues(all);
		refreshTable(all, values);
	}
}
