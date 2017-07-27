import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowFocusListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URI;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Keyboard {
	private JFrame frame;
	private JPanel panel;
	private JLabel label;
	private ImageIcon image;
	ImageIcon background;
	
	private JButton space, enter, delete;
	JButton[] letterButtons = new JButton[26];
	JButton[] numberButtons = new JButton[10];
	
	boolean shiftClick = false;
	boolean capsClick = false;
	
    int BUTTON_SIZE_X = 38;      // size height
	int BUTTON_SIZE_Y = 38;       // size width
	
	public void button() {
		for(int i = 0; i < 26; i++) {
			char temp = (char)('a' + i);
			letterButtons[i] = new JButton(Character.toString(temp));
			char lowercase = (char)('a' + i);
			String lowerTemp = Character.toString(lowercase);
			char uppercase = (char)('A' + i);
			String upperTemp = Character.toString(uppercase);
			
			letterButtons[i].setBorder(null);
			letterButtons[i].setBorderPainted(false);
			letterButtons[i].setContentAreaFilled(false);
			letterButtons[i].setOpaque(false);
			
			double radian = Math.toRadians(9.5);
			int midX = (int)((frame.getWidth() - 100) / 2.8 + 50);
			int midY = (int)((frame.getWidth() - 100) / 2.8 * 1.5 + 50);
			int radius = (int)((frame.getWidth() - 100) / 2.8 * 1.5);
			
			int xValue = (int)(radius * (Math.sin(radian * (i - 3))));
			int yValue = (int)(Math.sqrt(Math.pow(radius,  2) - Math.pow(xValue,  2)));
			if(i < 13) {
				yValue = -1 * yValue;
			}
			xValue += midX;
			yValue += midY;
			letterButtons[i].setBounds(xValue, yValue, BUTTON_SIZE_X, BUTTON_SIZE_Y);
			letterButtons[i].setFont(new Font("Arial", Font.PLAIN, 30));
			
			letterButtons[i].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae) {
					if(!shiftClick && !capsClick) {
						
					}
				}
				
			});
		}
	}
	
	public void gui() {
		// Frame
		frame = new JFrame("Soft Keyboard");
		frame.pack();
		frame.setVisible(true);
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());
		frame.setAlwaysOnTop(true);
		
		frame.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				frame.setFocusableWindowState(false);
			}
		});
		
		// Panel
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setPreferredSize(null);
		panel.setLayout(new BorderLayout());
		
		// Label
		label = new JLabel();
		label.setBounds(50,  50, frame.getWidth() - 100,  (int)((frame.getWidth() - 100) * 1.18));
		label.setBackground(Color.DARK_GRAY);
		label.repaint();
		label.revalidate();
		
		// Background image
		background = new ImageIcon(getClass().getResource("/bg.png"));
		/*Image imagey = background.getImage();
		Image imagea = imagey.getScaledInstance(frame.getWidth() - 100,  
				(int)((frame.getWidth() - 100) * 1.18), java.awt.Image.SCALE_SMOOTH);
		background = new ImageIcon(imagea);*/
		
		// Numbers
		numberButtons[0] = new JButton("0");
		numberButtons[1] = new JButton("1");
		numberButtons[2] = new JButton("2");
		numberButtons[3] = new JButton("3");
		numberButtons[4] = new JButton("4");
		numberButtons[5] = new JButton("5");
		numberButtons[6] = new JButton("6");
		numberButtons[7] = new JButton("7");
		numberButtons[8] = new JButton("8");
		numberButtons[9] = new JButton("9");
		
		double radian;
		int midX, midY, radius, xValue, yValue;
		
		for(int i = 0; i < 10; i++) {
			numberButtons[i].setBorder(null);
			numberButtons[i].setBorderPainted(false);
			numberButtons[i].setContentAreaFilled(false);
			numberButtons[i].setOpaque(false);
			
			radian = Math.toRadians(25);
			midX = (int)((frame.getWidth() - 100) / 2.8 + 50);
			midY = (int)((frame.getWidth() - 100) / 2.8 * 1.6 + 42);
			radius = (int)((frame.getWidth() - 100) / 2.8 * 0.67);
			xValue = (int)(radius * (Math.sin(radian * (i - 6))));
			yValue = (int)(Math.sqrt(Math.pow(radius, 2) - Math.pow(xValue, 2)));
			if(i < 5) {
				yValue *= -1;
			}
			
			xValue += midX;
			yValue += midY;
			numberButtons[i].setBounds(xValue, yValue, (int)(BUTTON_SIZE_X * 0.8), (int)(BUTTON_SIZE_Y * 0.8));
			numberButtons[i].setFont(new Font("Arial", Font.PLAIN, (int)(25 * (double)(frame.getWidth() / 500.0))));
			numberButtons[i].setForeground(Color.WHITE);
			
			final Integer x = new Integer(i);
			numberButtons[x].addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					numberButtons[x].setBackground(Color.PINK);
					numberButtons[x].setContentAreaFilled(true);
				}
				
				public void mouseExited(MouseEvent e) {
					numberButtons[x].setBackground(null);
					numberButtons[x].setContentAreaFilled(false);
				}
			});
		}
		
		// Display
		label.setIcon(background);
		label.revalidate();
		label.repaint();
		
		for(int i = 0; i < 10; i++) {
			panel.add(numberButtons[i]);
		}
		panel.add(label);
		
		frame.add(panel);
		
		panel.revalidate();
		panel.repaint();
		frame.revalidate();
		frame.repaint();
	}
	
	public Keyboard() {
		gui();
	}
	
	public static void main(String[] args) {
		Keyboard k = new Keyboard();
	}
}
