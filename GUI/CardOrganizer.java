package GUI;

import java.awt.AWTEvent;
import java.awt.AWTException;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.EventQueue;
import java.awt.FlowLayout;
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
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.security.InvalidKeyException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.ButtonGroup;
import javax.swing.ComponentInputMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
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
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.ActionMapUIResource;
import javax.swing.table.DefaultTableModel;

import org.imgscalr.Scalr;

import MTGApplication.Card;
import MTGApplication.CollectionMethods;

@SuppressWarnings("serial")
public class CardOrganizer extends JFrame  {
	protected ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	protected JTextArea notes = new JTextArea();
	protected JTextArea oracleText = new JTextArea();
	protected JButton addCard = new JButton("+");
	protected JButton removeCard = new JButton("-");
	private JButton viewDatabase = new JButton("All Cards");
	protected JComboBox set = new JComboBox(new Object[] {"Set", "Theros", "Return to Ravnica", "Gatecrash", "Dragon's Maze", "Innistrad", "Dark Ascension", "Avacyn Restored", "Magic 2012", "Magic 2013", "Magic 2014"});
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
	private JToggleButton oneC = new JToggleButton("1");
	private JToggleButton twoC = new JToggleButton("2");
	private JToggleButton threeC = new JToggleButton("3");
	private JToggleButton fourC = new JToggleButton("4");
	private JToggleButton fiveC = new JToggleButton("5");
	private JToggleButton sixC = new JToggleButton("6");
	private JToggleButton sevenC = new JToggleButton("7");
	private JToggleButton eightC = new JToggleButton("8");
	private JToggleButton nineC = new JToggleButton("9+");
	private JToggleButton land = new JToggleButton("Land");
	private JToggleButton unowned = new JToggleButton("Missing");
	private JToggleButton ownedC = new JToggleButton("Owned");
	JToggleButton[] buttons;
	private JButton mycards = new JButton("My Cards");
	private JButton allcards = new JButton("All Cards");
	protected JButton goQuery = new JButton("Query!");
	protected JButton viewAll = new JButton("My Cards");
	private JTextField textBox = new JTextField("");
	private String selected = null;
	private JCheckBox deckBuild = new JCheckBox();
	private JCheckBox sideBoard = new JCheckBox();
	DefaultComboBoxModel model = new DefaultComboBoxModel();
	private JComboBox decks = new JComboBox(model);
	private String name = "n";
	private String cardText = "n";
	private String color = "n";
	private int power = -1;
	private int toughness = -1;
	private int owned = -1;
	private int CMC = -1;
	private String type1 = "n";
	private String type2 = "n";
	private String rarityC = "n";
	private String tribal = "n";
	private String combinedColors = "";
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem, menuItem2, menuItem3, menuItem4, menuItem5, menuItemSaveDeck, menuRand, menuClear, menuText;
	private String[] queryList;
	private boolean isAnd = false;
	private boolean isOr = false;
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
	private boolean enterC = false;
	private boolean deckCheck = false;
	private boolean sideboardCheck = false;
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
	private JScrollPane scrollList;
	private JScrollPane scrollQuery;
	private JLabel label = new JLabel();
	private JLabel ownedAndPrice = new JLabel("");
	private JLabel ownedL = new JLabel("");
	private JLabel collectionV = new JLabel("Collection Value: ");
	private JLabel numberInTable = new JLabel("| # in Query:");
	private JLabel numT = new JLabel("3");
	private JLabel collectionValue = new JLabel("0.0");
	private JLabel deckV = new JLabel("");
	private JLabel deckValue = new JLabel("");
	private JLabel deckCount = new JLabel("");
	private JLabel deckNum = new JLabel("");
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
	Boolean table5Sel = false;
	String home = System.getProperty("user.home");
	String currentDeck = "";
	Dimension imgSize = new Dimension(500, 100);
	Dimension boundary = new Dimension(200, 200);
	JPanel cardV = new JPanel(new GridBagLayout());
	JPanel fixedTopBottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
	int oldWidth = 223;
	String[] myCards = organizer.getAllArray();
	String[] allCards = organizer.getCategory("cD");
	JPanel cardPanel = new JPanel(new GridBagLayout());
	JPanel combine = new JPanel(new GridBagLayout());
	JPanel notebox = new JPanel();
	int heightScheme = 0;
	boolean differentTens = false;
	int currentHeight = 0;
	String[] setList;
	JScrollPane noteScroll;
	
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
			mainList.setPreferredSize(new Dimension(250, 640));

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
			
			//use mac searchbox
			try {
			textBox.putClientProperty("JTextField.variant", "search");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			addCard.putClientProperty("JButton.buttonType", "roundRect");
			removeCard.putClientProperty("JButton.buttonType", "roundRect");
			and.putClientProperty("JButton.buttonType", "roundRect");
			or.putClientProperty("JButton.buttonType", "roundRect");
			common.putClientProperty("JButton.buttonType", "textured");
			uncommon.putClientProperty("JButton.buttonType", "textured");
			rare.putClientProperty("JButton.buttonType", "textured");
			mythic.putClientProperty("JButton.buttonType", "textured");
			creature.putClientProperty("JButton.buttonType",  "bevel");
			enchantment.putClientProperty("JButton.buttonType",  "bevel");
			instant.putClientProperty("JButton.buttonType",  "bevel");
			sorcery.putClientProperty("JButton.buttonType", "bevel");
			artifact.putClientProperty("JButton.buttonType", "bevel");
			planeswalker.putClientProperty("JButton.buttonType", "bevel");
			land.putClientProperty("JButton.buttonType", "bevel");
//			mycards.putClientProperty("JButton.buttonType", "bevel");
//			allcards.putClientProperty("JButton.buttonType", "bevel");
//			unowned.putClientProperty("JButton.buttonType", "bevel");
			
			//button images
			red = new JToggleButton(new ImageIcon(home + "/Desktop/VCO/Icons/red.png"));
			blue = new JToggleButton(new ImageIcon(home + "/Desktop/VCO/Icons/blue.png"));
			black = new JToggleButton(new ImageIcon(home + "/Desktop/VCO/Icons/black.png"));
			white = new JToggleButton(new ImageIcon(home + "/Desktop/VCO/Icons/white.png"));
			colorless = new JToggleButton(new ImageIcon(home + "/Desktop/VCO/Icons/colorless.png"));
			multi = new JToggleButton(new ImageIcon(home + "/Desktop/VCO/Icons/multi.png"));
			green = new JToggleButton(new ImageIcon(home + "/Desktop/VCO/Icons/green.png"));
			
			//rarity icons and buttons	
			mythicIcon = new ImageIcon(home + "/Desktop/VCO/Icons/mythic.png");
			rareIcon = new ImageIcon(home + "/Desktop/VCO/Icons/rare.png");		  
			uncommonIcon = new ImageIcon(home + "/Desktop/VCO/Icons/uncommon.png");		  
			commonIcon = new ImageIcon(home + "/Desktop/VCO/Icons/common.png");	
			common = new JToggleButton(commonIcon);
			uncommon = new JToggleButton(uncommonIcon);
			rare = new JToggleButton(rareIcon);
			mythic = new JToggleButton(mythicIcon);
			blackIcon = new ImageIcon(home + "/Desktop/VCO/Icons/black_mana.gif");
			greenIcon = new ImageIcon(home + "/Desktop/VCO/Icons/green_mana.gif");
			blueIcon = new ImageIcon(home + "/Desktop/VCO/Icons/blue_mana.gif");
			whiteIcon = new ImageIcon(home + "/Desktop/VCO/Icons/white_mana.gif");
			redIcon = new ImageIcon(home + "/Desktop/VCO/Icons/red_mana.gif");
			oneIcon = new ImageIcon(home + "/Desktop/VCO/Icons/Symbol_1_mana.gif");
			twoIcon = new ImageIcon(home + "/Desktop/VCO/Icons/Symbol_2_mana.gif");
			threeIcon = new ImageIcon(home + "/Desktop/VCO/Icons/Symbol_3_mana.gif");
			fourIcon = new ImageIcon(home + "/Desktop/VCO/Icons/Symbol_4_mana.gif");
			fiveIcon = new ImageIcon(home + "/Desktop/VCO/Icons/Symbol_5_mana.gif");
			sixIcon = new ImageIcon(home + "/Desktop/VCO/Icons/Symbol_6_mana.gif");
			sevenIcon = new ImageIcon(home + "/Desktop/VCO/Icons/Symbol_7_mana.gif");
			eightIcon = new ImageIcon(home + "/Desktop/VCO/Icons/Symbol_8_mana.gif");
			nineIcon = new ImageIcon(home + "/Desktop/VCO/Icons/Symbol_9_mana.gif");
			tenIcon = new ImageIcon(home + "/Desktop/VCO/Icons/Symbol_10_mana.gif");
			elevenIcon = new ImageIcon(home + "/Desktop/VCO/Icons/Symbol_11_mana.gif");
			bgIcon = new ImageIcon(home + "/Desktop/VCO/Icons/Symbol_BG_mana.gif");
			buIcon = new ImageIcon(home + "/Desktop/VCO/Icons/Symbol_UB_mana.gif");
			brIcon = new ImageIcon(home + "/Desktop/VCO/Icons/Symbol_BR_mana.gif");
			bwIcon = new ImageIcon(home + "/Desktop/VCO/Icons/Symbol_WB_mana.gif");
			ugIcon = new ImageIcon(home + "/Desktop/VCO/Icons/Symbol_GU_mana.gif");
			urIcon = new ImageIcon(home + "/Desktop/VCO/Icons/Symbol_UR_mana.gif");
			uwIcon = new ImageIcon(home + "/Desktop/VCO/Icons/Symbol_WU_mana.gif");
			wgIcon = new ImageIcon(home + "/Desktop/VCO/Icons/Symbol_GW_mana.gif");
			wrIcon = new ImageIcon(home + "/Desktop/VCO/Icons/Symbol_RW_mana.gif");
			grIcon = new ImageIcon(home + "/Desktop/VCO/Icons/Symbol_RG_mana.gif");
			checkIcon = new ImageIcon(home + "/Desktop/VCO/Icons/rsz_check2.png");
			xIcon = new ImageIcon(home + "/Desktop/VCO/Icons/cross16.png");
			XIcon = new ImageIcon(home + "/Desktop/VCO/Icons/Symbol_X_mana.gif");
			common.putClientProperty("JButton.buttonType", "roundRect");
			uncommon.putClientProperty("JButton.buttonType", "roundRect");
			rare.putClientProperty("JButton.buttonType", "roundRect");
			mythic.putClientProperty("JButton.buttonType", "roundRect");
			
			//Create the menu bar.
			menuBar = new JMenuBar();

			//Build the first menu.
			menu = new JMenu("File");
			menu.setMnemonic(KeyEvent.VK_A);
			menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
			menuBar.add(menu);

			//a group of JMenuItems
			menuItemSaveDeck = new JMenuItem("Save Deck", KeyEvent.VK_S);
			menuItemSaveDeck.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.META_MASK));
			menu.add(menuItemSaveDeck);
			menuItem3 = new JMenuItem("Save As", KeyEvent.VK_A);
			menuItem3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.META_MASK));
			menu.add(menuItem3);
			menuItem4 = new JMenuItem("Delete Deck", KeyEvent.VK_D);
			menuItem4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.META_MASK));
			menu.add(menuItem4);
			menuItem5 = new JMenuItem("New Deck", KeyEvent.VK_N);
			menuItem5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.META_MASK));
			menu.add(menuItem5);
			menuItem2 = new JMenuItem("Backup Collection", KeyEvent.VK_B);
			menuItem2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.META_MASK));
			menu.add(menuItem2);
			menuRand = new JMenuItem("Random Card", KeyEvent.VK_R);
			menuRand.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.META_MASK));
			menu.add(menuRand);
			menuClear = new JMenuItem("Clear Toggles", KeyEvent.VK_C);
			menuClear.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.META_MASK));
			menu.add(menuClear);
			menuText = new JMenuItem("Extract Deck Text (Tappedout.net format)");
			menu.add(menuText);
			setJMenuBar(menuBar);

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
			
			menuItemSaveDeck.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						saveDeck(false);
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
						saveDeck(true);
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
					deleteDeck();
				}
			});
			
			menuItem5.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					organizer.resetDeck();
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
					try{
						refreshDeckTable(sideboard, dataModel5);
					}catch (Exception e2) {
						e2.printStackTrace();
						}
				}
			});
			
			menuRand.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String[] num = organizer.getCategory("cD");
					Random r = new Random();
					int rand = r.nextInt(num.length);
					String st = num[rand];
					Card card = null;
					try {
						card = organizer.getCard(st);
					} catch (InvalidKeyException e1) {
						e1.printStackTrace();
					}
					BufferedImage img = null;
					String cut = card.name;
					System.out.println(cut);
					if(cut != null && cut.contains("//")) {
						cut = cut.replace("//", "");
					}
					try {
						img = ImageIO.read(new File(home +"/Desktop/VCO/All Cards/" + cut + ".jpg"));
					} catch (IOException e1) {
						try {
							img = ImageIO.read(new File(home +"/Desktop/VCO/All Cards/" + cut + ".full.jpg"));
						} catch( Exception e2) {
							System.out.println("why not work?");
						}
						}
						BufferedImage cardIm =
								Scalr.resize(img, Scalr.Method.QUALITY, Scalr.Mode.FIT_TO_WIDTH,
										cardV.getWidth(), 100, Scalr.OP_ANTIALIAS);
						ImageIcon i = new ImageIcon(cardIm);
						label.setIcon(i);
				}
			});
			
			menuClear.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JToggleButton[] buttons = {ownedC, unowned, land, nineC, eightC, sevenC, sixC, fiveC, fourC, threeC, twoC, oneC, sevenT, sixT, fiveT, fourT, threeT, twoT, oneT, sevenP, sixP, fiveP, fourP, threeP, twoP, oneP, planeswalker, creature, enchantment, instant, artifact, sorcery, mythic, uncommon, common, rare, red, white, blue, green, black, multi, colorless, and, or};
					for (JToggleButton jToggleButton : buttons) {
		                if(jToggleButton.isSelected())
		                	jToggleButton.setSelected(!jToggleButton.isSelected());
		            }
					name = "n";
					cardText = "n";
					color = "n";
					power = -1;
					toughness = -1;
					owned = -1;
					CMC = -1;
					type1 = "n";
					type2 = "n";
					rarityC = "n";
					tribal = "n";
					isAnd = false;
					isOr = false;
					combinedColors = "";
					if (isMyCards == true && isSet == false) {
						viewMyCards();
					} else if(isSet == true) {
						viewSet();
					} else {
						viewAll();
					}
				}
			});
			
			menuText.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					PrintWriter out = null;
					File f;
					String data = "Main Deck:\n\n";
					StringBuilder sFile = new StringBuilder();
					
					try {
						sFile = organizer.readFromFile(home + "/Desktop/VCO/Decks/" + currentDeck + ".txt");
					} catch (IOException e4) {
						e4.printStackTrace();
					}
					
					String[] sBSplit = sFile.toString().split("::");
					String sB[] = null;
					try {
						if(sBSplit[1] != null)
							sB = sBSplit[1].split(":");
					} catch (Exception e2){
						System.out.print("there will be an array out of bounds w/o a sideboard. Tis okay");
					}
					
					String[] line = sBSplit[0].toString().split(":");
					int z = 0;
					String d = "";
					for(int i = 1; i < line.length; i++) {
						if(i % 2 != 0) {
							d = line[i];
						} else {
							z = Integer.parseInt(line[i]);
							data += z +"x " + d + "\n";
						}	
					}
					
					data += "\nSideboard:\n\n";
					if(sB != null) {
					for(int i = 0; i < sB.length; i++) {
						if(i % 2 == 0) {
							d = sB[i];
						} else {
							z = Integer.parseInt(sB[i]);
							data += z +"x " + d + "\n";
							}	
						}
					}
					
					data += "\nNotes:\n\n";
					String[] noteS = sFile.toString().split("///");
					try {
						noteS[1] = noteS[1].replace("%$$%", "\n\n");
						data += noteS[1];
					} catch (Exception e3) {
						System.out.println("No Notes");
					}
					
					
					f = new File(home + "/Desktop/VCO/" + "deckData" + ".txt");
					try {
						out = new PrintWriter(f);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					out.print(data);
					out.close();
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
			d.weightx = 1;
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
			
			//CMC buttons
			JPanel cmc = new JPanel(new GridBagLayout());
			cmc.setBorder(new MatteBorder(0,0,1,0, Color.GRAY));
			JPanel cmcB = new JPanel(new GridLayout(1,9));
			cmcB.add(oneC);
			cmcB.add(twoC);
			cmcB.add(threeC);
			cmcB.add(fourC);
			cmcB.add(fiveC);
			cmcB.add(sixC);
			cmcB.add(sevenC);
			cmcB.add(eightC);
			cmcB.add(nineC);
			cmcB.setPreferredSize(new Dimension(0,23));
			JLabel cmcL = new JLabel("CMC: ");
			d.gridx = 0;
			d.gridy = 0;
			cmc.add(cmcL, d);
			d.gridy = 1;
			cmc.add(cmcB, d);
			
			//combobox model
			getDecks();
			decks = new JComboBox(model);
			    
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
			gb.gridy = 7;
			mainList.add(panel5, gb);
			gb.gridy = 8;
			gb.weighty = 1.0;
			mainList.add(panel6, gb);
			gb.gridy = 9;
			mainList.add(cmc, gb);


			//JTable
			String[] cardNames = organizer.getAllArray();
			queryList = cardNames;	
			dataModel = new DefaultTableModel();
			dataModel.setColumnCount(4);
			dataModel.setRowCount(organizer.getAllArray().length);
			dataModel.setColumnIdentifiers(new String[]{"Name", "CMC", "Type", "Rarity", "Set"});
			table = new JTable(dataModel) {
				//private static final long serialVersionUID = 1L;
				@Override
				public boolean isCellEditable(int row, int column) {                
					return false;  
				};
				@SuppressWarnings({ "rawtypes" })
				@Override
				public Class getColumnClass(int columnIndex) {
				    if(columnIndex == 1 || columnIndex == 3 || columnIndex == 5){
				        return ImageIcon.class;
				    }
				    return Object.class;
				}
			};
			table.getColumnModel().getColumn(0).setPreferredWidth(150);
			refreshTable(cardNames, null);

			JScrollPane scrollList = new JScrollPane(table);
			JScrollPane scroll =  new JScrollPane(mainList);

			ImageIcon image;
			label = new JLabel();
			try {
				image = new ImageIcon(ImageIO.read(new File(home +"/Desktop/VCO/All Cards/" + "card_back" + ".jpg")));
				label.setIcon(image);
			} catch (Exception ex) {
				label.setIcon(organizer.getCard("card_back").getImg());
			}
	
			ownedAndPrice.setFont(new Font("Serif", Font.PLAIN, 30));

			//Final formatting
			JPanel p3 = new JPanel(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();

			//Queries
			c.insets = new Insets(0,5,0,0);
			c.gridx = 0;
			c.gridy = 0;
			c.weightx = 1;
			c.weighty = 1;
			c.fill = GridBagConstraints.BOTH;
			JPanel queryContainer = new JPanel(new GridBagLayout());
			queryContainer.setPreferredSize(new Dimension(300, 498));
			queryContainer.add(scroll, c);
			JPanel queryScroll = new JPanel();
			queryScroll.add(queryContainer);
			c.insets = new Insets(-10,-20, 0, 0);
			p3.add(queryScroll, c);
						
			//JTable
			c.insets = new Insets(3,0,0,0);
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 1;
			c.weighty = 1;
			c.gridx = 1;
			c.gridy = 0;
			JPanel box = new JPanel(new GridBagLayout());
			box.add(scrollList, c);
			scrollList.setPreferredSize(new Dimension(500, 400));
			p3.add(box,c);
			c.insets = new Insets(0,0,0,0);
		
			//make deck builder window
			JPanel deck = new JPanel(new GridBagLayout());
						
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
				//private static final long serialVersionUID = 1L;
				@Override
				public boolean isCellEditable(int row, int column) {                
					return false;  
				};
			};
			table2.getColumnModel().getColumn(0).setPreferredWidth(150);
			
			table3 = new JTable(dataModel3) {
				//private static final long serialVersionUID = 1L;
				@Override
				public boolean isCellEditable(int row, int column) {                
					return false;  
				};
			};
			table3.getColumnModel().getColumn(0).setPreferredWidth(150);

			table4 = new JTable(dataModel4) {
				//private static final long serialVersionUID = 1L;
				@Override
				public boolean isCellEditable(int row, int column) {                
					return false;  
				};
			};
			table4.getColumnModel().getColumn(0).setPreferredWidth(150);

			table5 = new JTable(dataModel5) {
				//private static final long serialVersionUID = 1L;
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
			deck.add(scrollList2, b);
			b.gridx = 1;
			deck.add(scrollList3, b);
			b.gridx = 2;
			deck.add(scrollList4, b);
			b.gridx = 3;
			deck.add(scrollList5, b);

			
			//deckbuild and query Panel
			GridBagConstraints g = new GridBagConstraints();
			g.fill = GridBagConstraints.BOTH;			
			JPanel topBottom = new JPanel(new GridBagLayout());
			g.weightx = 1;
			g.weighty = 1;
			g.gridx = 0;
			g.gridy = 0;
			g.ipady = 0;
			JPanel top = new JPanel();
			top.add(p3);
			g.insets = new Insets(0,0,0,0);
			topBottom.add(p3, g);
			g.ipady = 0;
			g.gridy = 1;
			g.weightx = 1;
			g.weighty = 1;
			JPanel bottom = new JPanel();
			bottom.add(deck);
			deck.setPreferredSize(new Dimension(800, 200));
			topBottom.add(deck,g);
			g.weightx = 0;
			g.weighty = 0;
			g.gridx = 0;
			g.gridy = 0;
						
			//card view panel
			g.gridx = 0;
			g.gridy = 0;
			JPanel collectionVal = new JPanel(new GridBagLayout());
			g.anchor = GridBagConstraints.WEST;
			g.insets = new Insets(0,0,0,0);
			cardV.add(collectionV, g);
			g.insets = new Insets(0,110,0,0);
			priceCollection();
			cardV.add(collectionValue, g);
			g.insets = new Insets(0,170,0,0);
			cardV.add(numberInTable, g);
			g.insets = new Insets(0,250,0,0);
			int i = dataModel.getRowCount();
			String numTable = Integer.toString(i);
			numT.setText(numTable);
			cardV.add(numT, g);
			g.insets = new Insets(0,0,0,0);
			g.gridy = 1;
			cardV.setSize(300, 1);
			cardPanel.add(label);
			resizeL();
			cardPanel.setMinimumSize(new Dimension(0, 430));
			cardV.add(cardPanel, g);
			g.gridx = 0;
			JPanel stats = new JPanel(new GridBagLayout());
			g.gridx = 0;
			g.gridy = 2;
			g.insets = new Insets(0,0,0,0);
			cardV.add(ownedL, g);
			g.insets = new Insets(0,50,0,0);
			cardV.add(ownedAndPrice, g);
			g.insets = new Insets(0,0,0,0);
			g.fill = GridBagConstraints.BOTH;
			g.gridy = 3;
			g.insets = new Insets(0,0,0,0);
			cardV.add(deckV, g);
			g.insets = new Insets(0,75,0,0);
			cardV.add(deckValue, g);
			g.insets = new Insets(0,135,0,0);
			cardV.add(deckCount, g);
			g.insets = new Insets(0,190,0,0);
			deckNum.setText("");
			cardV.add(deckNum, g);
			g.insets = new Insets(0,0,0,0);
			notebox.add(notes);
			JTabbedPane tabbedPane = new JTabbedPane();
			noteScroll = new JScrollPane(notes); 
			JScrollPane oracleScroll = new JScrollPane(oracleText); 
			tabbedPane.addTab("Deck Notes:", noteScroll);
			tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
			tabbedPane.addTab("Oracle Text", oracleScroll);
			tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
			notes.setLineWrap(true);
			notes.setWrapStyleWord(true);
			oracleText.setLineWrap(true);
			oracleText.setWrapStyleWord(true);
			g.gridy = 4;
			g.weightx = 1;
			g.weighty = 1;
			g.fill = GridBagConstraints.BOTH;
			g.ipady = 200;
			cardV.add(tabbedPane,g);
			g.ipady = 0;
			g.weightx = 0;
			g.weighty = 0;

			//combine tB and cV
			g.fill = GridBagConstraints.BOTH;
			g.weightx = 0;
			g.weighty = 0;
			g.gridx = 0;
			g.gridy = 0;
			g.ipadx = 0;
			g.ipady = 0;			
			topBottom.setMinimumSize(new Dimension(800, 700));
			combine.add(topBottom, g);
			//combine.setPreferredSize(new Dimension(1200,665));
			g.gridx = 1;
			g.weightx = 1;
			g.weighty = 1;
			g.ipadx = 0;
			g.ipady = 0;
			combine.add(cardV, g);
			add(combine);
			
			//remaps ENTER key in JTables to addCard()
			table.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "add");
			table.getActionMap().put("add", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					addCard();
				}
			});
			
			table2.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "add");
			table2.getActionMap().put("add", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					addCard();
				}
			});
			
			table3.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "add");
			table3.getActionMap().put("add", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					addCard();
				}
			});
			
			table4.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "add");
			table4.getActionMap().put("add", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					addCard();
				}
			});
			
			table5.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "add");
			table5.getActionMap().put("add", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					addCard();
				}
			});
			//maps backslash to remove card
			
			table.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SLASH, 0), "subtract");
			table.getActionMap().put("subtract", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					removeCard();
				}
			});
			
			table2.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SLASH, 0), "subtract");
			table2.getActionMap().put("subtract", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					removeCard();
				}
			});
			
			table3.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SLASH, 0), "subtract");
			table3.getActionMap().put("subtract", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					removeCard();
				}
			});
			
			table4.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SLASH, 0), "subtract");
			table4.getActionMap().put("subtract", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					removeCard();
				}
			});
			
			table5.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SLASH, 0), "subtract");
			table5.getActionMap().put("subtract", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					removeCard();
				}
			});
			
			//maps subtract to sideboard to ]
			table2.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_CLOSE_BRACKET, 0), "subtractToSB");
			table2.getActionMap().put("subtractToSB", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					sideboardCheck = true;
					addCard();
					sideboardCheck = false;
					removeCard();
				}
			});
			
			table4.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_CLOSE_BRACKET, 0), "subtractToSB");
			table4.getActionMap().put("subtractToSB", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					sideboardCheck = true;
					addCard();
					sideboardCheck = false;
					removeCard();
				}
			});
			
			table3.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_CLOSE_BRACKET, 0), "subtractToSB");
			table3.getActionMap().put("subtractToSB", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					sideboardCheck = true;
					addCard();
					sideboardCheck = false;
					removeCard();
				}
			});
			
			//maps subtract from sideboard to deck to [
			
			table5.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_OPEN_BRACKET, 0), "subtractToDeck");
			table5.getActionMap().put("subtractToDeck", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					sideboardCheck = false;
					addCard();
					sideboardCheck = true;
					removeCard();
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
			
			sideBoard.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					if(sideboardCheck == false) {
						sideboardCheck = true;
					} else {
						sideboardCheck = false;
					}
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
			
			oneC.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					buttonSelected = abstractButton.getModel().isSelected();
					CMC = 1;
					if(buttonSelected == false)
						CMC = -1;
					query();
				}
			});
			
			twoC.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					buttonSelected = abstractButton.getModel().isSelected();
					CMC = 2;
					if(buttonSelected == false)
						CMC = -1;
					query();
				}
			});
			
			threeC.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					buttonSelected = abstractButton.getModel().isSelected();
					CMC = 3;
					if(buttonSelected == false)
						CMC = -1;
					query();
				}
			});
			
			fourC.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					buttonSelected = abstractButton.getModel().isSelected();
					CMC = 4;
					if(buttonSelected == false)
						CMC = -1;
					query();
				}
			});
			
			fiveC.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					buttonSelected = abstractButton.getModel().isSelected();
					CMC = 5;
					if(buttonSelected == false)
						CMC = -1;
					query();
				}
			});
			
			sixC.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					buttonSelected = abstractButton.getModel().isSelected();
					CMC = 6;
					if(buttonSelected == false)
						CMC = -1;
					query();
				}
			});
			
			sevenC.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					buttonSelected = abstractButton.getModel().isSelected();
					CMC = 7;
					if(buttonSelected == false)
						CMC = -1;
					query();
				}
			});
			
			eightC.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					buttonSelected = abstractButton.getModel().isSelected();
					CMC = 8;
					if(buttonSelected == false)
						CMC = -1;
					query();
				}
			});
			
			nineC.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					buttonSelected = abstractButton.getModel().isSelected();
					CMC = 9;
					if(buttonSelected == false)
						CMC = -1;
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
					if(isAnd == true) {
						combinedColors += stringToChar();
						color = "n";
					}
					if(redSelected == false) {
						color = "n";
						if(isAnd == true) {
							combinedColors = combinedColors.replace("R", "");
							}
					}
					query();
				}
			});

			white.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					whiteSelected = abstractButton.getModel().isSelected();
					color = "white";
					if(isAnd == true) {
						combinedColors += stringToChar();
						color = "n";
					}
					if(whiteSelected == false) {
						color = "n";
						if(isAnd == true) {
							combinedColors = combinedColors.replace("W", "");
							}
					}
					query();
				}
			});

			blue.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					blueSelected = abstractButton.getModel().isSelected();
					color = "blue";
					if(isAnd == true) {
						combinedColors += stringToChar();
						color = "n";
					}
					if(blueSelected == false) {
						color = "n";
						if(isAnd == true) {
							combinedColors = combinedColors.replace("U", "");
							}
					}
					query();
				}
			});

			green.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					greenSelected = abstractButton.getModel().isSelected();
					color = "green";
					if(isAnd == true) {
						combinedColors += stringToChar();
						color = "n";
					}
					if(greenSelected == false) {
						color = "n";
						if(isAnd == true) {
							combinedColors = combinedColors.replace("G", "");
							}
					}
					query();
				}
			});

			black.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					blackSelected = abstractButton.getModel().isSelected();
					color = "black";
					if(isAnd == true && blackSelected == true) {
						combinedColors += stringToChar();
						color = "n";
					}
					if(blackSelected == false) {
						color = "n";
						if(isAnd == true) {
							combinedColors = combinedColors.replace("B", "");
							}
					}
					query();
				}
			});

			colorless.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					colorlessSelected = abstractButton.getModel().isSelected();
					color = "colorless";
					if(colorlessSelected == false) {
						color = "n";
					}	
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
			
			unowned.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					buttonSelected = abstractButton.getModel().isSelected();
					unowned();
					if(buttonSelected == false) {
						if (isMyCards == true) {
							viewMyCards();
						} else if(isSet == true) {
							viewSet();
						} else {
							viewAll();
						}
					}
				}
			});
			
			and.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
					buttonSelected = abstractButton.getModel().isSelected();
					char color = stringToChar();
					isAnd = true;
					if(color != 97)
						combinedColors = Character.toString(color);
					if(buttonSelected == false) {
						combinedColors = "";
						isAnd = false;
					}
				}
			});
			
			or.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					AbstractButton abstractButton = (AbstractButton) e.getSource();
					buttonSelected = abstractButton.getModel().isSelected();
					char color = stringToChar();
					isAnd = true;
					isOr = true;
					if(color != 97)
						combinedColors = Character.toString(color);
					if(buttonSelected == false) {
						combinedColors = "";
						isAnd = false;
						isOr = false;
					}
				}
			});
			
			removeCard.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {			
				removeCard();
				}
			});


			addCard.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					addCard();
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
				currentDeck = s;
				if (s != null) {
				try {
					loadDeck(s);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				}
				}
			});
			
		  	cardPanel.addComponentListener(new ComponentAdapter() {
		        public void componentResized(ComponentEvent e) {
		        	resizeL();
		        }
		    });

		  	//would be ideal for real time updating as your type (like Google) but far too inefficient right now
		  	
//			textBox.getDocument().addDocumentListener(new DocumentListener() {
//				@Override
//				public void changedUpdate(DocumentEvent e) {
//				text();
//				}
//				@Override
//				public void removeUpdate(DocumentEvent e) {
//				text();
//				}
//				@Override
//				public void insertUpdate(DocumentEvent e) {
//				text();
//				}
		  	
		  	textBox.addKeyListener(new KeyAdapter() {
	            public void keyReleased(KeyEvent e) {
	            	if(e.getKeyCode() == KeyEvent.VK_ENTER){
					isText = true;
					if(!textBox.getText().equals("")) {
						cardText = textBox.getText().toLowerCase();
					} else {
						cardText = "n";
					}
					query();
				}
	            }
			});

			//detects selected table row and updates image icon
			table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					try {
					selected = (String) table.getValueAt(table.getSelectedRow(), 0);
					}catch (Exception e2) {
						e2.printStackTrace();
					}
						table3.getSelectionModel().clearSelection();
						table2.getSelectionModel().clearSelection();
						table4.getSelectionModel().clearSelection();
						table5.getSelectionModel().clearSelection();					
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
						}catch (Exception e2) {
							e2.printStackTrace();
						}
						table.getSelectionModel().clearSelection();
						table3.getSelectionModel().clearSelection();
						table4.getSelectionModel().clearSelection();
						table5.getSelectionModel().clearSelection();
					try {
						selected = (String) table.getValueAt(table.getSelectedRow(), 0);
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
					selected = (String) table3.getValueAt(table3.getSelectedRow(), 0);
					}catch (Exception e2) {
						e2.printStackTrace();
					}
					table.getSelectionModel().clearSelection();
					table2.getSelectionModel().clearSelection();
					table4.getSelectionModel().clearSelection();
					table5.getSelectionModel().clearSelection();
					try {
						selected = (String) table3.getValueAt(table3.getSelectedRow(), 0);
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
					}catch (Exception e2) {
						e2.printStackTrace();
					}
					table2.getSelectionModel().clearSelection();
					table3.getSelectionModel().clearSelection();
					table.getSelectionModel().clearSelection();
					table5.getSelectionModel().clearSelection();
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
			table5.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					try {
					selected = (String) table5.getValueAt(table5.getSelectedRow(), 0);
					}catch (Exception e2) {
						e2.printStackTrace();
					}
					table.getSelectionModel().clearSelection();
					table3.getSelectionModel().clearSelection();
					table4.getSelectionModel().clearSelection();
					table2.getSelectionModel().clearSelection();
					try {
						selected = (String) table5.getValueAt(table5.getSelectedRow(), 0);
					} catch (ArrayIndexOutOfBoundsException e1) {
						e1.printStackTrace();
					}
					isText = false;
					if(selected != null) {
						refreshCardValues(table5);
					}
				}
			});
		}
	}
	
	/**
	 * String color to char abbreviation
	 */
	public char stringToChar() {
	char x = 97;
		if(color.equals("blue"))
			x = 85;
		if(color.equals("white"))
			x = 87;
		if(color.equals("red"))
			x = 82;
		if(color.equals("black"))
			x = 66;
		if(color.equals("green"))
			x = 71;
	return x;
	}
	
	/**
	 * Unowned filter
	 */
	public void unowned() {
		ArrayList<String> unownedCards = new ArrayList<String>();
		for(int i = 0; i < queryList.length; i++) {
			Card card = null;
			try {
				card = organizer.getCard(queryList[i]);
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			}
			if(card.owned == 0)
				unownedCards.add(card.name);
		}
		String[] unowned = new String[unownedCards.size()];
		unownedCards.toArray(unowned);
		queryList = unowned;
		ArrayList<String[]> values = returnValues(queryList);
		refreshTable(queryList, values);
		refreshCardValues(table);	
	}
	
	/**
	 * Set JCombo list to current saved decks
	 */
	@SuppressWarnings("unchecked")
	public void getDecks() {
	File directory = new File("/Users/eorndahl/Desktop/VCO/Decks");
	String[] files = directory.list();
	model.removeAllElements();
	model.addElement("Load Deck");
	for (int i = 0; i < files.length; i++){
		//System.out.println(files[i]);
		String[] splits = new String[2];
		splits = files[i].split("\\.");
		if(!splits[0].equals("")) {
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
	 * resize label in proper aspect ratio
	 */
	
	public void resizeL() {
	BufferedImage img = null;
	String cut = selected;
	if(selected != null && selected.contains("//")) {
		cut = selected.replace("//", "");
	}
	try {
		img = ImageIO.read(new File(home +"/Desktop/VCO/All Cards/" + cut + ".jpg"));
	} catch (IOException e1) {
		try {
			img = ImageIO.read(new File(home +"/Desktop/VCO/All Cards/" + cut + ".full.jpg"));
		} catch( Exception e) {
			try {
				img = ImageIO.read(new File(home +"/Desktop/VCO/All Cards/" + "card_back" + ".jpg"));
			} catch (IOException e2) {
				e2.printStackTrace();
				}
		}
		}
		BufferedImage card =
				Scalr.resize(img, Scalr.Method.QUALITY, Scalr.Mode.FIT_TO_WIDTH,
						cardV.getWidth(), 100, Scalr.OP_ANTIALIAS);
		ImageIcon i = new ImageIcon(card);
		label.setIcon(i);
		//System.out.println(label.getHeight());
		
		int height = label.getHeight();
		int newHS =  (int) height/10;
		String num = Integer.toString(newHS);
		for(int p = 0; p < num.length(); p++) {
			if(p == num.length() - 1) {
				newHS = Character.getNumericValue(num.charAt(p)); //get the tens place of the pixel height
			}
		}
		
		if(heightScheme - newHS <= -2 || heightScheme - newHS >= 2) { //if the pixel tenth height is a 2 tenths difference, restart the method
			differentTens = true;
			heightScheme = newHS;
		}
		
		if(differentTens == true) {
		differentTens = false;
		//Now add pixels to the end of each so each image is uniform height.
		//First, get the three types of images and render for this window width. This solution might be very resource intensive unfortunately.
		int aGHeight = 0;
		int aCHeight = 0;
		int aSHeight = 0;
		try {
			BufferedImage ajaniGoldmane = ImageIO.read(new File(home +"/Desktop/VCO/All Cards/" + "Ajani Goldmane" + ".full.jpg"));
			BufferedImage aGResize =
					Scalr.resize(ajaniGoldmane, Scalr.Method.SPEED, Scalr.Mode.FIT_TO_WIDTH,
							cardV.getWidth(), 100, Scalr.OP_GRAYSCALE);
			aGHeight = aGResize.getHeight();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			BufferedImage ajaniCaller = ImageIO.read(new File(home +"/Desktop/VCO/All Cards/" + "Ajani, Caller of the Pride" + ".jpg"));
			BufferedImage aCResize =
					Scalr.resize(ajaniCaller, Scalr.Method.SPEED, Scalr.Mode.FIT_TO_WIDTH,
							cardV.getWidth(), 100, Scalr.OP_GRAYSCALE);
			aCHeight = aCResize.getHeight();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			BufferedImage aquusSteed = ImageIO.read(new File(home +"/Desktop/VCO/All Cards/" + "Aquus Steed" + ".jpg"));
			BufferedImage aSResize =
					Scalr.resize(aquusSteed, Scalr.Method.SPEED, Scalr.Mode.FIT_TO_WIDTH,
							cardV.getWidth(), 100, Scalr.OP_GRAYSCALE);
			aSHeight = aSResize.getHeight();
		} catch (IOException e) {
			e.printStackTrace();
		}
		currentHeight = Collections.max(Arrays.asList(aGHeight, aCHeight, aSHeight));
		}
		cardPanel.setMinimumSize(new Dimension(1, label.getHeight() + (currentHeight - label.getHeight()))); // add the difference in pixels between the desired height and actual
	}

	/**
	 * Refresh card values (price/owned/icon)
	 */
	public void refreshCardValues(JTable j) {
		try { //set selected to the new row upon query
		selected = (String) table.getValueAt(table.getSelectedRow(), 0);
			} catch (Exception e) {
				e.printStackTrace();
		}
		
		if(selected != null) 
		{
			if(isText!= true)
			{
				j.requestFocusInWindow();
			}
			
		resizeL();
		try {
			NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
			double pric = Double.parseDouble(organizer.getCard(selected).price);
			String owned = Integer.toString(organizer.getCard(selected).getOwned());
			ownedL.setText("Owned:");
			ownedAndPrice.setText(String.format(owned + "  " + "%-5s", currencyFormatter.format(pric)));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Card card;
		try {
			card = organizer.getCard(selected);
			String text = "";
			text = card.text;
			oracleText.setText(text);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		int i = dataModel.getRowCount();
		String numTable = Integer.toString(i);
		numT.setText(numTable);
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
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
		collectionValue.setText(currencyFormatter.format(sum));
	}
	
	/**
	 * Price deck
	 */
	public void priceDeck(){
		double sum = 0;
		ArrayList<Card> temp = organizer.entries("deck");
		for(int i = 0; i < temp.size(); i++) {
			Card card = temp.get(i);
			double value = 0;
			if(card != null) {
				value = Double.parseDouble(card.price);
			if(card.owned > 0) {
				sum+= value * card.cardsInDeck + value * card.numSB;
			} else 
				sum += value;
			}
		}
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
		deckV.setText("Deck Value: ");
		deckValue.setText(currencyFormatter.format(sum));
	}
	
	/**
	 * Count cards in deck
	 */
	
	public void countDeck() {
		int sum = 0;
		ArrayList<Card> temp = organizer.entries("deck");
		for(int i = 0; i < temp.size(); i++) {
			Card card = temp.get(i);
			if(card != null) {
			sum += card.cardsInDeck;
			}
		}
		deckCount.setText("# Cards: ");
		deckNum.setText(Integer.toString(sum));		
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
		ArrayList<Card> cards = new ArrayList<Card>();	

		int toRemove = dataModel.getRowCount() - s.length; //get # of rows to remove which = current rowCount - querylist length	
		for(int i = 0; i < toRemove; i++) {
			if(dataModel.getRowCount() > 2)
				dataModel.removeRow(dataModel.getRowCount() - 1);
		}	
		int toAdd = 0;
		toAdd = s.length - dataModel.getRowCount(); //get # of rows to add if previously within shorter query = qList length - rowCount
		for(int i = 0; i < toAdd; i++)
			dataModel.addRow(new Object[]{"","",""});
		
		for(int i = 0; i < s.length; i++) {
			try {
				try {
					cards.add(organizer.getCard(all[i]));
				} catch (InvalidKeyException e) {
					e.printStackTrace();
				}
				if(!cards.get(i).name.equals("card_back")) {
				if(!isMyCards) 
					dataModel.setValueAt(null,i, 5);
				dataModel.setValueAt(cards.get(i).name,i, 0);
				dataModel.setValueAt(iconCombine(cards.get(i).CMC),i, 1);
				dataModel.setValueAt(cards.get(i).type2,i, 2);
				if(!cards.get(i).type3.equals("null")) {
					dataModel.setValueAt(cards.get(i).type2 + " - " + cards.get(i).type3,i, 2);
				}
				if(cards.get(i).rarity.contains("-M")) {
					dataModel.setValueAt(mythicIcon,i, 3);
				} else if(cards.get(i).rarity.contains("-R")){
				dataModel.setValueAt(rareIcon,i, 3); 
				} else if(cards.get(i).rarity.contains("-U")){
					dataModel.setValueAt(uncommonIcon,i, 3); 
				} else if(cards.get(i).rarity.contains("-C")){
					dataModel.setValueAt(commonIcon,i, 3); 
				}
				//set
				String set = "";
				String[] splitR = new String[2];
					splitR = cards.get(i).rarity.split(",");
				String[] splitSet = new String[2];
				splitSet = splitR[0].split("-");
					set = splitSet[0];
				dataModel.setValueAt(set,i, 4);
				if(!isMyCards) {
					if(cards.get(i).owned > 0) {
						dataModel.setValueAt(checkIcon,i, 5);
					} else
					dataModel.setValueAt(xIcon,i, 5);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(170);
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
			if(!m.equals(dataModel5))
				m.setValueAt(al.get(i).cardsInDeck,i, 1);
			else 
				m.setValueAt(al.get(i).numSB,i, 1);
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
						ex.printStackTrace(); //probably messed up b/c of cardback which is basically empty
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
	
	public void saveDeck(boolean b) throws IOException {
		ArrayList<String> deck = new ArrayList<String>();
		if(creatures.size() > 0) {
		for(int i = 0; i < creatures.size(); i++) {
			deck.add(":" + creatures.get(i).name + ":" + creatures.get(i).cardsInDeck);
			}
		}
		if(spells.size() > 0) {
		for(int i = 0; i < spells.size(); i++) {
			deck.add(":" + spells.get(i).name + ":" + spells.get(i).cardsInDeck);
			}
		}
		if(lands.size() > 0) {
		for(int i = 0; i < lands.size(); i++) {
			deck.add(":" + lands.get(i).name + ":" + lands.get(i).cardsInDeck);
			}
		}
		if(sideboard.size() > 0) {
		for(int i = 0; i < sideboard.size(); i++) {
			if(i == 0)
				deck.add("::" + sideboard.get(i).name + ":" + sideboard.get(i).numSB);
			else
				deck.add(":" + sideboard.get(i).name + ":" + sideboard.get(i).numSB);
			}
		}
		if(notes.getText() != null)
			deck.add("::///" + notes.getText());
		String s = "";
		for(int i = 0; i < deck.size(); i++){
			s += deck.get(i) + "\n";
		}
		Scanner scan = new Scanner(s);
		String fixSpacing = "";
		while(scan.hasNext()!= false) {
			String line = scan.nextLine();
			if(line.equals(""))
				line = "%$$%";
			fixSpacing += line;
		}
		PrintWriter out;
		if(b == true) {
		String inputValue = JOptionPane.showInputDialog(null, "Enter name", "Save Deck", JOptionPane.PLAIN_MESSAGE);
		File f = new File(home + "/Desktop/VCO/Decks/" + inputValue+ ".txt");
		f.createNewFile();
		out = new PrintWriter(home + "/Desktop/VCO/Decks/"+ inputValue +".txt");
		} else {
			File f = new File(home + "/Desktop/VCO/Decks/" + currentDeck + ".txt");
			f.createNewFile();
			out = new PrintWriter(home + "/Desktop/VCO/Decks/"+ currentDeck +".txt");
		}
		out.print(fixSpacing);
		out.close();
	}
	
	/**
	 * Delete deck
	 */
	
	public void deleteDeck() {
		int reply = JOptionPane.showConfirmDialog(
	            null,
	            "Are you sure you want to delete this deck?",
	            "",
	            JOptionPane.YES_NO_OPTION);
		if(reply == JOptionPane.YES_NO_OPTION) {
			File file = new File(home + "/Desktop/VCO/Decks/"+ currentDeck +".txt");
			file.delete();
			getDecks();
		}
	}
	
	/**
	 * Refresh ArrayList values
	 */
	
	public void refreshALs() {
		creatures = new ArrayList<Card>();
		spells = new ArrayList<Card>();
		lands = new ArrayList<Card>();
		sideboard = new ArrayList<Card>();
		ArrayList<Card> temp = organizer.entries("deck");
		for(int i = 0; i < temp.size(); i++) {
			Card card = temp.get(i);
			if (card!= null && card.cardsInDeck > 0) {
			if(card.type2.equals("creature") && !creatures.contains(card)) {
				creatures.add(card);
			} else if(card.type2.equals("instant") && !spells.contains(card)
					|| card.type2.equals("sorcery") && !spells.contains(card)
					||card.type2.equals("enchantment")&& !spells.contains(card)
					||card.type2.equals("planeswalker")&& !spells.contains(card)
					||card.type2.equals("artifact")&& !spells.contains(card)) {
				spells.add(card);
			} else if(card.type2.equals("land") && !lands.contains(card)) {
				lands.add(card);
				}
			}
			if(card!= null && !sideboard.contains(card) && card.numSB > 0) {
				sideboard.add(card);
			}
		}
	}
	
	/**
	 * Load deck
	 * @throws IOException 
	 */
	@SuppressWarnings("static-access")
	public void loadDeck(String s) throws IOException {
		if(!s.equals("Load Deck")) {
		priceDeck();
		countDeck();
		StringBuilder sFile = new StringBuilder();
		sFile = organizer.readFromFile(home + "/Desktop/VCO/Decks/" + s + ".txt");
		organizer.resetDeck();
		String[] sBSplit = sFile.toString().split("::");
		String sB[] = null;
		try {
		if(sBSplit[1] != null)
			sB = sBSplit[1].split(":");
		} catch (Exception e){
		System.out.print("there will be an array out of bounds w/o a sideboard. Tis okay");
		}
		String[] line = sBSplit[0].toString().split(":");
		int z = 0;
		String d = "";
		for(int i = 1; i < line.length; i++) {
			if(i % 2 != 0) {
				d = line[i];
			} else {
				z = Integer.parseInt(line[i]);
				for(int j = 0; j < z; j++)
					organizer.addCardToDeck(d, false);
			}	
		}
		if(sB != null) {
		for(int i = 0; i < sB.length; i++) {
			if(i % 2 == 0) {
				d = sB[i];
			} else {
				z = Integer.parseInt(sB[i]);
				for(int j = 0; j < z; j++)
					organizer.addCardToDeck(d, true);
				}	
			}
		}
		String[] noteS = sFile.toString().split("///");
		try {
			noteS[1] = noteS[1].replace("%$$%", "\n\n");
			notes.setText(noteS[1]);
			notes.setCaretPosition(0);
		} catch (Exception e) {
			notes.setText("");
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
		try{
			refreshDeckTable(sideboard, dataModel5);
		}catch (Exception e2) {
			e2.printStackTrace();
			}
		}
	}

	/**
	 * Refreshes table with query
	 */
	public void query() {	
		String[] myCards = organizer.getAllArray();
		String[] allCards = organizer.getCategory("cD");
		isQuery = true;
		String[] querySet = null;
		if(isSet == true) {
			querySet = setList;
		} else if(isMyCards) {
			querySet = myCards;
		} else 
			querySet = allCards;
		try {
			queryList = organizer.query(querySet, color, power,toughness,owned, type1, type2, rarityC, tribal, cardText, name, CMC, combinedColors, isAnd, isOr);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		java.util.Arrays.sort(queryList);
		if(owned == -1 && color.equals("n") && type1.equals("n") && type2.equals("n") 
				&& power == -1 && toughness == -1 && rarityC.equals("n") && tribal.equals("n") 
				&& cardText.equals("n") && name.equals("n") && CMC == -1 && combinedColors.equals("")) { //if no input for query
			isQuery = false;
			if (isMyCards == true && isSet == false) {
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
			my = organizer.query(organizer.getCategory("cD"), color, power,toughness,
					owned, type1, type2, rarityC, tribal, cardText, name, CMC, combinedColors, isAnd, isOr);
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
				all = organizer.query(organizer.getCategory("cD"), color, power,toughness,owned, 
						type1, type2, rarityC, tribal, cardText, name, CMC, combinedColors, isAnd, isOr);
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			}
		}
		queryList = all;
		ArrayList<String[]> values = null;
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
		if(sel.equals("Magic 2012"))
			sel = "M12-";
		if(sel.equals("Magic 2013"))
			sel = "M13-";
		if(sel.equals("Magic 2014"))
			sel = "M14-";
		if(sel.equals("Theros"))
			sel = "THS-";
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
				try {
					if(organizer.getCard(mine[i]).rarity != null)
						if(organizer.getCard(mine[i]).rarity.contains(sel))
							arr.add(mine[i]);
				} catch (InvalidKeyException e) {
					e.printStackTrace();
				}
			}
			all = new String[arr.size()];
			arr.toArray(all);
		}
		setList = all;
		ArrayList<String[]> values = returnValues(all);
		refreshTable(all, values);
	}
	
	public void addCard() {
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
		try { //price collection on add
			priceCollection();
		} catch (InvalidKeyException e1) {
			e1.printStackTrace();
		}
		try {
			organizer.save();
		} catch (InvalidKeyException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		} else {
			organizer.addCardToDeck(selected, sideboardCheck);
			countDeck();
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
			try{
				refreshDeckTable(sideboard, dataModel5);
			}catch (Exception e2) {
				e2.printStackTrace();
				}
		}
	}
	
	public void removeCard() {
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
			}
		viewMyCards();
		}
		if(isMyCards != true) {
			if(selected != null)
				organizer.removeCard(selected, "cD");
			if(isQuery == true) {
				try {
					queryList = organizer.query(queryList, color, power,toughness,owned, 
							type1, type2, rarityC, tribal, cardText, name,	CMC, combinedColors, isAnd, isOr);
				} catch (InvalidKeyException e2) {
					e2.printStackTrace();
				}
				ArrayList<String[]> values = returnValues(queryList);
				refreshTable(queryList, values);
				//return;
				}
			viewAll(); 
			}
		try { //price collection on remove
			priceCollection();
		} catch (InvalidKeyException e1) {
			e1.printStackTrace();
		}
		try {
			organizer.save();
		} catch (InvalidKeyException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		} else {
			try{
			organizer.removeCardFromDeck(selected, sideboardCheck);
			countDeck();
			priceDeck();
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
			try{
				refreshDeckTable(sideboard, dataModel5);
			}catch (Exception e2) {
				e2.printStackTrace();
				}
		}
	}
}
