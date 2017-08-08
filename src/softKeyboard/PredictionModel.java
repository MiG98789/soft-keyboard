package softKeyboard;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;
import javax.swing.*;

public class PredictionModel extends JFrame implements MouseListener {
	private int FRAME_WIDTH = 400;
	private int FRAME_HEIGHT = 210;
	private int NUM_OF_PREDICTIONS = 5;
	private float FONT_SIZE = 22.0f;
	private JPanel panel;
	private DefaultListModel dlm = new DefaultListModel();
	private JScrollPane scrollPane;
	
	private Vector<String> mathSymbols = new Vector<String>();
	
	@Override
	public void mouseEntered(MouseEvent e) {
		setFocusableWindowState(false);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	
	
	/**
	 * Loads special math symbols from a text file
	 * and stores them in the String Vector mathSymbols
	 */
	private void loadSymbols() {
		try {
			InputStream is = getClass().getResourceAsStream("/symbols.txt");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
			String line;
			
			while ((line = bufferedReader.readLine()) != null) {
				mathSymbols.add(line); 
			}
			
			System.out.println("Contents of file:");
			for (int i = 0; i < mathSymbols.size(); i++) {
				System.out.println(mathSymbols.elementAt(i).toString());
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void predictSymbol(String str) {
		System.out.println("Prediction input: " + str);
		
		// Clear all items
		dlm.clear();
		if (str.isEmpty()) {
			return;
		}

		// Loop through each symbol, and add matching predictions to list
		for (int i = 0; i < mathSymbols.size(); i++) {
			if (str.length() <= mathSymbols.elementAt(i).length()) {
				if (str.equals(mathSymbols.elementAt(i).substring(0, str.length()))) {
					dlm.addElement(mathSymbols.elementAt(i));
					System.out.println("Predicted: " + mathSymbols.elementAt(i));
				}
			}	
		}
		
		// Update frame
		final JList predictionList = new JList(dlm);
	    predictionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    predictionList.setSelectedIndex(0);
	    predictionList.setVisibleRowCount(NUM_OF_PREDICTIONS);
	    predictionList.setFont(predictionList.getFont().deriveFont(FONT_SIZE));
	    
	    // Automatically type out selected item
	    predictionList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
		        if (SwingUtilities.isLeftMouseButton(evt) && evt.getClickCount() == 1) {
		            if (predictionList.getSelectedIndex() != -1) {
		                int index = predictionList.locationToIndex(evt.getPoint());
		                String selection = (String)predictionList.getModel().getElementAt(index);
		    			System.out.println("You selected: " + selection);
		    			    			
		    			try {
							Robot robot = new Robot();
	
							// Type out selection	    				
							for (int i = str.length(); i < selection.length(); i++) {
								char temp = selection.charAt(i);
								if (Character.isUpperCase(temp)) {
									robot.keyPress(KeyEvent.VK_SHIFT);
								}
								int keyCode = KeyEvent.getExtendedKeyCodeForChar((int)temp);
		    					robot.keyPress(keyCode); robot.keyRelease(keyCode);
								if (Character.isUpperCase(temp)) {
									robot.keyRelease(KeyEvent.VK_SHIFT);
								}
							}
							robot.keyPress(KeyEvent.VK_SPACE);

							dlm.clear();
						} catch (AWTException e) {
							e.printStackTrace();
		    			}
		            }
		        }
			}
		});
	    
	    // After an item is selected, clear the frame
		scrollPane = new JScrollPane(predictionList);
		panel.removeAll();
		panel.add(scrollPane);
		
		panel.revalidate();
		panel.repaint();
		this.revalidate();
		this.repaint();
	}
	
	public PredictionModel(String title) {
		// Frame
		super(title);
		this.pack();
		this.setVisible(true);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);

//		this.addMouseListener(new java.awt.event.MouseAdapter() {
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				setFocusableWindowState(false);
//			}
//		});
		
		// Panel
		panel = new JPanel();
		
		// List
		final JList predictionList = new JList(dlm);
	    predictionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    predictionList.setSelectedIndex(0);
	    predictionList.setVisibleRowCount(NUM_OF_PREDICTIONS);
	    predictionList.setFont(predictionList.getFont().deriveFont(FONT_SIZE));
	    
	    // Scroll pane
		scrollPane = new JScrollPane(predictionList);

		// Display the GUI
		panel.add(scrollPane);
		this.add(panel);

		panel.revalidate();
		panel.repaint();
		this.revalidate();
		this.repaint();
		
		// Symbols
		loadSymbols();
	}
}