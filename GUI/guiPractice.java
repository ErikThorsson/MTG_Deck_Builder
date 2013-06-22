package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import GUI.DynamicPanelList.TestPane;
import MTGApplication.CollectionMethods;

public class guiPractice extends JFrame{
	private static JToggleButton red = new JToggleButton("R");
	private static JToggleButton white = new JToggleButton("W");
	private static JToggleButton blue = new JToggleButton("B");
	private static JToggleButton green = new JToggleButton("G");
	private static JToggleButton black = new JToggleButton("B");
	private static JToggleButton colorless = new JToggleButton("CL");
	private static JToggleButton multi = new JToggleButton("MU");
	protected JComboBox set = new JComboBox(new Object[] {"Choose Set", "Return to Ravnica", "Gatecrash", "Dragon's Maze", "Innistrad", "Dark Ascension", "Avacyn Restored"});
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
	protected JButton addCard = new JButton("+");
	protected JButton removeCard = new JButton("-");
	private JButton viewDatabase = new JButton("All Cards");
	private JRadioButton nameCheck = new JRadioButton();
	private JRadioButton tribalCheck = new JRadioButton();
	private JRadioButton textCheck = new JRadioButton();
	private JRadioButton enterCheck = new JRadioButton();
	protected ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	protected JTextArea text = new JTextArea();
	protected JScrollPane scrollBox = new JScrollPane(text);
	protected JComboBox queryColor = new JComboBox(new Object[] {"Color", "Red", "White", "Blue", "Black", "Green", "Multi", "Colorless"});
	protected JComboBox queryPower = new JComboBox(new Object[] {"Power", 1, 2, 3, 4, 5, 6, 7, 8, 9});
	protected JComboBox queryToughness = new JComboBox(new Object[] {"Toughness", 1, 2, 3, 4, 5, 6, 7, 8, 9});
	protected JComboBox queryOwned = new JComboBox(new Object[] {"Owned", 1, 2, 3, 4, 5, 6, 7, 8, 9});
	protected JComboBox queryType1 = new JComboBox(new Object[] {"Type", "Permanent", "Non-Permanent"});
	protected JComboBox queryType2 = new JComboBox(new Object[] {"Subtype", "Enchantment", "Creature", "Artifact", "Instant", "Sorcery", "Planeswalker", "Land"});
	protected JComboBox queryRarity = new JComboBox(new Object[] {"Rarity", "Common", "Uncommon", "Rare", "Mythic"});
	protected JComboBox queryTribal = new JComboBox(new Object[] {"Tribal", "Yes (Type in text box)"});
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

	
    public static void main(String[] args) throws InvalidKeyException, IOException {
        new guiPractice();
    }

    public guiPractice() throws InvalidKeyException, IOException {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception ex) {
                }

                JFrame frame = new JFrame("Test");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                try {
					frame.add(new TestPane());
				} catch (InvalidKeyException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setResizable(true);	                
                frame.setVisible(true);
            }
        });
    }
    
    public class TestPane extends JPanel {

        private JPanel mainList;

        public TestPane() throws InvalidKeyException, IOException {
            setLayout(new BorderLayout());
	
            mainList = new JPanel(new GridBagLayout());
            
            GridBagConstraints d = new GridBagConstraints();
            d.gridwidth = GridBagConstraints.REMAINDER;
            d.weightx = 1;
            d.weighty = 1;
            mainList.add(new JPanel(), d);
        
            JPanel tR = new JPanel(new GridBagLayout());
            tR.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));

            
    		JPanel p5 = new JPanel(new GridLayout(2,1));
    		p5.add(viewDatabase);
    		p5.add(set);
    		
    	    JPanel panel1 = new JPanel(new GridBagLayout());
            panel1.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
    		
    		JPanel p2 = new JPanel(new GridBagLayout());

    		JTextField t = new JTextField();
    		d.fill = GridBagConstraints.HORIZONTAL;
    		d.weightx = 0.0;
    		d.gridwidth = 2;
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
    		panel1.add(radios,d);
    	        
    		d.fill = GridBagConstraints.HORIZONTAL;
    		d.ipady = 4;      //make this component tall
    		d.weightx = 0.5;
    		d.gridwidth = 1;
    		d.gridx = 0;
    		d.gridy = 2;
    		panel1.add(addCard, d);

    		d.fill = GridBagConstraints.HORIZONTAL;
    		d.ipady = 4;   	//make this component tall
    		d.weightx = 0.5;
    		d.gridwidth = 1;
    		d.gridx = 1;
    		d.gridy = 2;
    		panel1.add(removeCard, d);
    	
    		d.gridx = 0;
    		d.gridy = 3;
    		panel1.add(mycards,d);
    		
    		d.gridx = 1;
    		d.gridy = 3;
    		panel1.add(allcards,d);
    		
    		d.fill = GridBagConstraints.HORIZONTAL;
    		d.weightx = 0.5;
    		d.gridwidth = 1;
    		d.gridx = 0;
    		d.gridy = 4;
    		panel1.add(unowned,d);
    		set.setPreferredSize(new Dimension(0,30));
    		d.gridx = 1;
    		d.gridy = 4;
    		panel1.add(set, d);
    		
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
    		
    		//add panels to mainList
            GridBagConstraints gb = new GridBagConstraints();
    		gb.fill = GridBagConstraints.HORIZONTAL;
    		gb.anchor = GridBagConstraints.PAGE_START;
    		gb.gridx = 0;
    		gb.gridy = 0;
    		mainList.add(panel1, gb);
    		gb.gridy = 1;
    		mainList.add(panel2,gb);
    		gb.gridy = 2;
    		mainList.add(panel3,gb);
    		gb.gridy = 3;
    		mainList.add(panel4, gb);
    		gb.gridy = 4;
    		mainList.add(panel5, gb);
    		gb.gridy = 5;
    		gb.weighty = 1.0;
    		mainList.add(panel6, gb);
    		
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
    		
    		JScrollPane scrollList = new JScrollPane(table);
    		scrollList.setPreferredSize(new Dimension(500,503));
    		p4.add(scrollList);
    		
    	    JScrollPane scroll =  new JScrollPane(mainList);
            //JPanel query = new JPanel();
            scroll.setPreferredSize(new Dimension(250, 503));
           // query.add(scroll);

    		//card viewing JLabel
    		JPanel card = new JPanel(new GridBagLayout());
    		ImageIcon image;
    		label = new JLabel();
    		String home = System.getProperty("user.home");
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
    		//JScrollPane s2 = new JScrollPane(p3);
    		GridBagConstraints c = new GridBagConstraints();
//
//    		//Queries
    		c.insets = new Insets(0,5,0,0);
    		c.ipady = 0;     
    		c.weighty = 1.0;
    		c.gridx = 0;
    		c.gridy = 0;
    		c.weightx = 1;
    		c.weighty = 1;
    		c.fill = GridBagConstraints.BOTH;
    		p3.add(scroll,c); //query list will scroll if too long
//
    		//JTable
    		c.insets = new Insets(0,0,0,0);
    		c.fill = GridBagConstraints.HORIZONTAL;
    		c.ipady = 0;     
    		c.gridx = 1;
    		c.gridy = 0;
    		p3.add(p4, c);
    
    		//label orientation
    		c.fill = GridBagConstraints.HORIZONTAL;
    		c.insets = new Insets(25,15,15,15);
    		c.ipady = 0;     
    		c.gridx = 2;
    		c.gridy = 0;
    		p3.add(card, c);   		
    		add(p3);
        }
    }
}
