package GUI;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
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
import java.security.InvalidKeyException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
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
	private JComboBox decks = new JComboBox(new Object[] {"Example1", "Example2"});
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
	private boolean deckCheck = false;
	private DefaultTableModel dataModel;
	private DefaultTableModel dataModel2;
	private JTable table;
	private JTable table2;
	private ArrayList<String[]> rarityArrays = new ArrayList<String[]>();
	private CollectionMethods organizer = new CollectionMethods();
	private boolean isRemoved;
	private JScrollPane scrollList;
	private JScrollPane scrollQuery;
	private String[] queryL;
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
	private String textString;

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

		public Interface() throws InvalidKeyException, IOException {
			setLayout(new BorderLayout());

			mainList = new JPanel(new GridBagLayout());
			mainList.setPreferredSize(new Dimension(250, 553));

			GridBagConstraints d = new GridBagConstraints();
			d.gridwidth = GridBagConstraints.REMAINDER;
			d.weightx = 1;
			d.weighty = 1;
			mainList.add(new JPanel(), d);

			queryL = organizer.getAllArray();
			String home = System.getProperty("user.home");

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

			JPanel p5 = new JPanel(new GridLayout(2,1));
			p5.add(viewDatabase);
			p5.add(set);

			JPanel panel1 = new JPanel(new GridBagLayout());
			panel1.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));

			//TextBox, add, and remove card	
			JPanel p2 = new JPanel(new GridBagLayout());
			scrollQuery = new JScrollPane(p2);

			JTextField t = new JTextField();
			d.fill = GridBagConstraints.HORIZONTAL;
			d.weightx = 1.0;
			d.gridwidth = 1;
			d.gridx = 0;
			d.gridy = 0;
			d.anchor = GridBagConstraints.PAGE_START;
			panel1.add(textBox, d);


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
			//d.insets = new Insets(0,0,0,0);
			panel1.add(radios,d);
			//d.insets = new Insets(0,0,0,0);

			//ADD/REMOVE/MY/ALL
			JPanel panel1Quarter = new JPanel(new GridBagLayout());
			panel1Quarter.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY)); 

			d.fill = GridBagConstraints.HORIZONTAL;
			d.ipady = 4;      //make this component tall
			d.weightx = 0.5;
			d.gridwidth = 1;
			d.gridx = 0;
			d.gridy = 0;
			panel1Quarter.add(addCard, d);
			d.ipady = 4;   	//make this component tall
			d.weightx = 0.5;
			d.gridwidth = 1;
			d.gridx = 1;
			d.gridy = 0;
			panel1Quarter.add(removeCard, d);
			d.gridx = 0;
			d.gridy = 1;
			panel1Quarter.add(mycards,d);
			d.gridx = 1;
			d.gridy = 1;
			panel1Quarter.add(allcards,d);

			//SORT
			JPanel panel1Half = new JPanel(new GridBagLayout());
			panel1Half.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY)); 

			JLabel sort = new JLabel("Sort:");
			d.fill = GridBagConstraints.HORIZONTAL;
			d.gridx = 0;
			d.gridy = 0;
			panel1Half.add(sort,d);
			d.gridx = 0;
			d.gridy = 1;
			panel1Half.add(unowned,d);
			d.gridx = 1;
			panel1Half.add(ownedC, d);
			set.setPreferredSize(new Dimension(68, 30));
			d.gridx = 2;
			panel1Half.add(set, d);
			d.ipady = 4;   	//make this component tall

			//ColorQuery
			JPanel panel2 = new JPanel(new GridBagLayout());
			panel2.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
			JLabel colors = new JLabel("Color:");
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
			JPanel typeBox3 = new JPanel(new GridLayout(1,1));

			//type buttons
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
			
			//deckBuild
			JPanel deckB = new JPanel(new GridBagLayout());
			deckB.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
			JPanel dB = new JPanel(new GridBagLayout());
			JLabel on = new JLabel("Deck Building Mode:");
			d.gridx = 0;
			d.gridy=0;
			dB.add(on,d);
			d.gridx = 1;
			d.insets = new Insets(-3,-10,0,0);
			dB.add(deckBuild,d);
			d.insets = new Insets(0,0,0,0);
			d.gridx =2;
			//decks.setPreferredSize(new Dimension(0,70));
			dB.add(decks,d);
			JLabel dL = new JLabel("Deck Building:");
			d.gridx = 0;
			d.gridy = 0;
			//deckB.add(dL, d);
			d.gridy = 0;
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
			mainList.add(panel1Half, gb);
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
			dataModel.setColumnIdentifiers(new String[]{"Name", "Rarity", "Set", "Owned"});
			table = new JTable(dataModel) {
				private static final long serialVersionUID = 1L;
				@Override
				public boolean isCellEditable(int row, int column) {                
					return false;  
				};
				@Override
				public Class getColumnClass(int columnIndex) {
				    if(columnIndex == 1){
				        return ImageIcon.class;
				    }
				    return Object.class;
				}
			};
			
			refreshTable(cardNames, values);

			JScrollPane scrollList = new JScrollPane(table);
			p4.add(scrollList);

			JScrollPane scroll =  new JScrollPane(mainList);
			//JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

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
			queryContainer.setPreferredSize(new Dimension(320, 380));
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
			//scrollList.setPreferredSize(new Dimension(680,384));
			JPanel box = new JPanel(new GridBagLayout());
			//box.setPreferredSize(new Dimension(660,384));
			c.weightx = 10;
			c.weighty = 10;
			c.anchor = GridBagConstraints.PAGE_START;
			box.add(scrollList, c);
			p3.add(box,c);
			
			//make deck builder window
			JPanel deck = new JPanel(new GridBagLayout());
			//deck.setPreferredSize(new Dimension(1400, 410));
			
			dataModel2 = new DefaultTableModel();
			dataModel2.setColumnCount(2);
			dataModel2.setRowCount(organizer.getAllArray().length);
			dataModel2.setColumnIdentifiers(new String[]{"Creatures", "#"});
			dataModel2.setValueAt("Goblin Electromancer", 0, 0);
			dataModel2.setValueAt("Dreg Mangler", 1, 0);
			dataModel2.setValueAt("Taunting Elf", 2, 0);
			DefaultTableModel dataModel3 = new DefaultTableModel();
			dataModel3.setColumnCount(2);
			dataModel3.setRowCount(organizer.getAllArray().length);
			dataModel3.setColumnIdentifiers(new String[]{"Spells", "#"});
			dataModel3.setValueAt("Giant Growth", 0, 0);
			dataModel3.setValueAt("Shock", 1, 0);
			dataModel3.setValueAt("Glorious Anthem", 2, 0);
			DefaultTableModel dataModel4 = new DefaultTableModel();
			dataModel4.setColumnCount(2);
			dataModel4.setRowCount(organizer.getAllArray().length);
			dataModel4.setColumnIdentifiers(new String[]{"Land", "#"});
			dataModel4.setValueAt("Vault of the Archangel", 0, 0);
			dataModel4.setValueAt("Dragonskull Summit", 1, 0);
			dataModel4.setValueAt("Drowning Pool", 2, 0);
			DefaultTableModel dataModel5 = new DefaultTableModel();
			dataModel4.setColumnCount(2);
			dataModel5.setRowCount(organizer.getAllArray().length);
			dataModel5.setColumnIdentifiers(new String[]{"Side Board", "#"});
			dataModel5.setValueAt("Bllllllaarrrrrrg", 0, 0);
			dataModel5.setValueAt("I'm quite bad", 1, 0);
			dataModel5.setValueAt("Suntail Hooplah", 2, 0);

			table2 = new JTable(dataModel2) {
				private static final long serialVersionUID = 1L;
				@Override
				public boolean isCellEditable(int row, int column) {                
					return false;  
				};
			};
			
			JTable table3 = new JTable(dataModel3) {
				private static final long serialVersionUID = 1L;
				@Override
				public boolean isCellEditable(int row, int column) {                
					return false;  
				};
			};
			
			JTable table4 = new JTable(dataModel4) {
				private static final long serialVersionUID = 1L;
				@Override
				public boolean isCellEditable(int row, int column) {                
					return false;  
				};
			};
			
			JTable table5 = new JTable(dataModel5) {
				private static final long serialVersionUID = 1L;
				@Override
				public boolean isCellEditable(int row, int column) {                
					return false;  
				};
			};

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
			
			//frame and add top and bottom
			JPanel test = new JPanel();
			test.setPreferredSize(new Dimension(1400, 400));
			JPanel frame = new JPanel(new GridBagLayout());
			frame.setPreferredSize(new Dimension(1400, 800));
			GridBagConstraints g = new GridBagConstraints();
			g.anchor = GridBagConstraints.PAGE_START;
			g.fill = GridBagConstraints.BOTH;
			g.weightx = 1;
			g.weighty = 1;
			g.gridx = 0;
			g.gridy = 0;
			g.ipady = -275;      //make this component tall
			frame.add(p3,g);
			g.weightx = 0;
			g.weighty = 0;
			g.gridx = 1;
			frame.add(label,g);
			g.gridx = 0;
			g.ipady = 0;      //make this component tall
			g.weightx = 1;
			g.gridwidth =3;
			g.weighty = 1;
			g.gridy = 1;
			g.ipady =1;
			g.fill = GridBagConstraints.BOTH;
			frame.add(deck, g);
			
			//deckbuild and query Panel
			JPanel topBottom = new JPanel(new GridBagLayout());
			topBottom.setPreferredSize(new Dimension(1300, 800));
			g.gridx = 0;
			g.gridy = 0;
			g.ipady = 60;      
			topBottom.add(p3, g);
			g.gridy = 1;
			g.ipady = 0; 
			topBottom.add(deck,g);
			g.ipady = 0; 
			g.gridx = 0;
			g.gridy = 0;
			g.weightx = 1;
			g.weighty = 1;
			//g.fill = GrigBagConstraints.BOTH;
			//frame.add(topBottom, g);
			
			//card view panel
			JPanel cardV = new JPanel(new GridBagLayout());
			g.insets = new Insets(0,35,30,-50);
			g.gridx = 0;
			g.gridy = 0;
			g.weightx = 1;
			g.weighty = 1;
			cardV.add(label, g);
			g.insets = new Insets(0,0,0,0);
			notes.setPreferredSize(new Dimension(0, 280));
			g.gridy = 1;
			cardV.add(notes,g);
			JLabel notesL = new JLabel("Notes:");
			g.insets = new Insets(380,0,0,0);
			g.gridx = 2;
			g.gridy = 0;
			cardV.add(notesL, g);
			g.insets = new Insets(0,0,0,0);
			
			//combine tB and cV
			JPanel combine = new JPanel(new GridBagLayout());
			g.fill = GridBagConstraints.BOTH;
			g.gridx = 0;
			g.gridy = 0;
			g.gridwidth = 1;
			g.weightx = 8;
			combine.add(topBottom, g);
			g.gridx = 1;
			g.weightx = 1;
			combine.add(cardV, g);
			
			add(combine);
			//add(frame);
			
			//owned and price
			d.gridx = 2;
			d.gridy = 0;
			d.insets = new Insets(347,0,0,0);
			cardV.add(ownedL, d);
			d.gridx = 2;
			d.insets = new Insets(340,55,0,0);
			ownedNum.setFont(new Font("Serif", Font.PLAIN, 36));
			cardV.add(ownedNum, d);
			d.gridx = 2;
			d.insets = new Insets(340,90,0,0);
			cardV.add(priceL, d);
			d.gridx = 2;
			d.insets = new Insets(340,105,0,0);
			cardV.add(price, d);
			d.gridx = 2;
			d.insets = new Insets(0,0,0,0);
			cardV.add(collectionV, d);
			d.insets = new Insets(0,110,0,0);
			cardV.add(mSign,d);
			d.ipady = 1;
			d.insets = new Insets(5,120,0,0);
			priceCollection();
			cardV.add(collectionValue, d);
			p3.setPreferredSize(new Dimension(1400, 395));
			p3.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));

			
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
					refreshCardValues();
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
					refreshCardValues();
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
					refreshCardValues();
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
					refreshCardValues();
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
					refreshCardValues();
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
					refreshCardValues();
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
					refreshCardValues();
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
					refreshCardValues();
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
					refreshCardValues();
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
					refreshCardValues();
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
					refreshCardValues();
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
					refreshCardValues();
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
					refreshCardValues();
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
					refreshCardValues();
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
					refreshCardValues();
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
					refreshCardValues();
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
					refreshCardValues();
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
					refreshCardValues();
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
					refreshCardValues();
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
					refreshCardValues();
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
					refreshCardValues();
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
					refreshCardValues();
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
					refreshCardValues();
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
					refreshCardValues();
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
					refreshCardValues();
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
					refreshCardValues();
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
					refreshCardValues();
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
					refreshCardValues();
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
					refreshCardValues();
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
					refreshCardValues();
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
					refreshCardValues();
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
					refreshCardValues();
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
					if( enterC != true) {
						try { //set new owned number to JLabel
							int owned = (organizer.getCard(selected).getOwned()) - 1;
							ownedNum.setText(Integer.toString(owned));
						} catch (InvalidKeyException e3) {
							e3.printStackTrace();
						}}
					if(enterC == true) { 
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
					if(enterC == true) { //use textbox string if checked
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
						String[] allList = organizer.getAllArray(); //replaced queryL
						ArrayList<String[]> values = returnValues(allList);
						String[] stringOwned = values.get(0);
						String[] rarity = values.get(1);
						if(isMyCards == true)
							viewMyCards(); 
						else
							viewAll();
					} else if(isQuery == true) { 	//determine if query view and refresh
						try {
							queryList = organizer.query(queryL, color, power,toughness,owned, type1, type2, rarityC, tribal, cardText, name);
						} catch (InvalidKeyException e2) {
							e2.printStackTrace();
						}
						@SuppressWarnings("unchecked")
						ArrayList<String[]> values = returnValues(queryList);
						String[] stringOwned = values.get(0);
						String[] rarity = values.get(1);
						refreshTable(queryList, values);
					}
				}
			});
			
			mycards.addActionListener(new ActionListener() { //refreshes table to your collection
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					viewMyCards();
				}
			});

			allcards.addActionListener(new ActionListener() { //refreshes table to complete database
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					viewAll();
				}
			});

			set.addItemListener(new ItemListener() { //refreshes table to query the current table by set
				@Override
				public void itemStateChanged(ItemEvent e) {
					viewSet();
				}
			});

			//listens for textBox input
			textBox.getDocument().addDocumentListener(new DocumentListener() {
				@Override
				public void changedUpdate(DocumentEvent e) {
					if(textC == true)
						text();
					if(nameC == true)
						name();
					if(tribalC == true)
						tribal();
				}
				@Override
				public void removeUpdate(DocumentEvent e) {
					if(textC == true)
						text();
					if(nameC == true)
						name();
					if(tribalC == true)
						tribal();
				}
				@Override
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
						e1.printStackTrace();
					}
					isText = false;
					if(selected != null) {
						try {
							ownedNum.setText(Integer.toString(organizer.getCard(selected).getOwned()));
							price.setText(organizer.getCard(selected).price);
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
						try {
							ownedNum.setText(Integer.toString(organizer.getCard(selected).getOwned()));
							price.setText(organizer.getCard(selected).price);
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
	}
	
	/**
	 * Refresh card values (price/owned/icon)
	 */
	public void refreshCardValues() {
		table.requestFocusInWindow();
		if(selected != null) {
		try {
			selected = (String) table.getValueAt(table.getSelectedRow(), 0);
		} catch (ArrayIndexOutOfBoundsException e1) {
			e1.printStackTrace();
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
		
		int toRemove = dataModel.getRowCount() - queryList.length; //get # of rows to remove which = current rowCount - querylist length
		for(int i = 0; i < toRemove; i++) {
			if(dataModel.getRowCount() > 2)
				dataModel.removeRow(dataModel.getRowCount() - 1);
		}	
		int toAdd = queryList.length - dataModel.getRowCount(); //get # of rows to add if previously within shorter query = qList length - rowCount
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
		for(int i = 0; i < all.length; i++) {
			dataModel.setValueAt(all[i],i, 0);
		}
		for(int i = 0; i < all.length; i++) {
			if(rarity[i] == "Mythic") {
				dataModel.setValueAt(mythicIcon,i, 1);
			} else if(rarity[i] == "Rare"){
			dataModel.setValueAt(rareIcon,i, 1); 
			} else if(rarity[i] == "Uncommon"){
				dataModel.setValueAt(uncommonIcon,i, 1); 
			} else if(rarity[i] == "Common"){
				dataModel.setValueAt(commonIcon,i, 1); 
			}
		}
		for(int i = 0; i < all.length; i++) {
			dataModel.setValueAt(stringSet[i],i, 2);
		}
		for(int i = 0; i < all.length; i++) {
			dataModel.setValueAt(owned[i],i, 3);
		}
		table.repaint(); 
		refreshCardValues();
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
				&& power == -1 && toughness == -1 && rarityC.equals("n") && tribal.equals("n") 
				&& cardText.equals("n") && name.equals("n") && isSet != true) { //if no input for query
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
			refreshCardValues();	
		}
	}
	
	/**
	 * Refresh table to your collection
	 */
	public void viewMyCards() {
		queryL = organizer.getAllArray();
		isQuery = false;
		isMyCards = true;
		isSet = false;
		String[] my = organizer.getAllArray();
		queryList = my;
		ArrayList<String[]> values = returnValues(my);
		String[] stringOwned = values.get(0);
		String[] rarity = values.get(1);
		refreshTable(my, values);
	}

	/**
	 * Refresh table to entire database
	 */
	public void viewAll() {
		queryL = organizer.getCategory("cD");
		isQuery = false;
		isMyCards = false;
		isSet = false;
		String[] all = organizer.getCategory("cD");
		queryList = all;
		ArrayList<String[]> values = returnValues(all);
		String[] stringOwned = values.get(0);
		String[] rarity = values.get(1);
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
		refreshCardValues();
	}
}
