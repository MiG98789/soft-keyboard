package testing;

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

//successfully add the image with label
public class P3{
	private JFrame fra;
	private JPanel pan;
	private JButton 
	//b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,
	//b14,b15,b16,b17,b18,b19,b20,b21,b22,b23,b24,b25,b26,
					space,enter,delete,flip,
	//				shift,caps,sharp,and,dollar,dquote,squote,sbra1,sbra2,
	//				p1,p2,p3,p4,p5,p6,p7,
					ec1,ec2,
	//				fr1,fr2,fr3,fr4,sr1,sr2,sr3,sr4,sr5,sr6,sr7,sr8,
	//				tr1,tr2,tr3,tr4,tr5,tr6,tr7,tr8,tr9,tr10,tr11,tr12,tr13,tr14,tr15,tr16,
	//				ex1,ex2,ex3,ex4,ex5,
	//				no1,no2,no3,no4,no5,no6,no7,no8,no9,no0,
	//				d1,d2,d3,d4,d5,d6,d7,d8,d9,d10,mbra1,mbra2,lbra1,lbra2,
	//				fw1,fw2,fw3,fw4,fw5,fw6,fw7,fw8,fw9,fw10,
					reset;
	JButton[] letterbuttons = new JButton[26];
	JButton[] phase1buttons = new JButton[16];
	JButton[] phase2buttons = new JButton[29];
	JButton[] predictbuttons = new JButton[28];
	JButton[] wordbuttons = new JButton[10];

	private JLabel lab;
	ImageIcon image;
	ImageIcon image1;    
	private JTextArea txt;
    private JTextPane tp1;
    private JTextArea ta = new JTextArea();
    private JScrollPane sp;
    private JScrollPane sp1;
    private JScrollPane sp2;
    String filelist = null;
    String content = " ";
    String words = null;
    String fullw = null;
    String file = "C:\\Users\\djing\\Desktop\\123.txt";
    String fullwordfile = "C:\\Users\\djing\\Desktop\\fwfile.txt"; //open it in the same folder!!!!
    StringWriter sw = null;
    BufferedWriter bw = null;
    StringBuffer sbf = new StringBuffer();
    int typedletters = 0;
    String error = null;
    int charValue = 0;
    String next = null;
    String last = null;
    int ecposition1x = 0;
    int ecposition1y = 0;
    int ecposition2x = 0;
    int ecposition2y = 0;
    int click = 0;
    int shiftclick = 0;
    int capsclick = 0;
	String lastWord1 = null; 
	String compareword = null;
	int lastWord1length;
	int lastWord2length;
	String contenttp1;
	int waitingtime;
	String longlist = null;
	String longlist0 = null;
	double mouseX = 0;
	double mouseY = 0;
	int pX,pY;
	int relafrasizeX;
	int relafrasizeY;
//	int BUTTON_SIZE_X;      // size height
//	int BUTTON_SIZE_Y;       // size width
	float xaxis;
	float yaxis;
	double ratio1;
	java.net.URL path2 = P3.class.getResource("123.txt");
	File fileread = new File(path2.getFile());
	BufferedReader reader2 = null;

	int xValue; int yValue; int xValue1; int yValue1;
    int BUTTON_SIZE_X = 38;      // size height
	int BUTTON_SIZE_Y = 38;       // size width
	int midX;
	int midY; 
	int radius;
	double degreeBetween = 9.5;
	double radianBetween = Math.toRadians(degreeBetween);
	Image iflip;ImageIcon flipicon; int flipwidth; int flipheight;
	Image ishift; ImageIcon shifticon; int shiftwidth; int shiftheight; 
	Image icaps; ImageIcon capsicon; int capswidth; int capsheight;
	Image ispace; ImageIcon spaceicon; int spacewidth; int spaceheight;
	Image idelete; ImageIcon deleteicon; int deletewidth; int deleteheight;
	Image ienter; ImageIcon entericon; int enterwidth; int enterheight;
	
	private ActionListener excellis 
	= new ActionListener(){
		public void actionPerformed(ActionEvent actionlis){
			String actionCommand = actionlis.getActionCommand();
			txt.setText(txt.getText() + actionCommand);
			tp1.setText(tp1.getText() + actionCommand);
			content = content + actionCommand;
//			int temp2; String temp = null;
			try {		             
	            Robot robot = new Robot();	
			if(actionCommand == "/"){robot.keyPress(KeyEvent.VK_SLASH);robot.keyRelease(KeyEvent.VK_SLASH);}
            if(actionCommand == "\\"){robot.keyPress(KeyEvent.VK_BACK_SLASH);robot.keyRelease(KeyEvent.VK_BACK_SLASH);}
            if(actionCommand == "|"){
            	robot.keyPress(KeyEvent.VK_SHIFT);
            	robot.keyPress(KeyEvent.VK_BACK_SLASH);robot.keyRelease(KeyEvent.VK_BACK_SLASH);
            	robot.keyRelease(KeyEvent.VK_SHIFT);}
            if(actionCommand == "<"){
            	robot.keyPress(KeyEvent.VK_SHIFT);
            	robot.keyPress(KeyEvent.VK_COMMA);robot.keyRelease(KeyEvent.VK_COMMA);
            	robot.keyRelease(KeyEvent.VK_SHIFT);}
            if(actionCommand == ">"){
            	robot.keyPress(KeyEvent.VK_SHIFT);
            	robot.keyPress(KeyEvent.VK_PERIOD);robot.keyRelease(KeyEvent.VK_PERIOD);
            	robot.keyRelease(KeyEvent.VK_SHIFT);}
            if(actionCommand == "%"){
            	robot.keyPress(KeyEvent.VK_SHIFT);
            	robot.keyPress(KeyEvent.VK_5);robot.keyRelease(KeyEvent.VK_5);
            	robot.keyRelease(KeyEvent.VK_SHIFT);}
            if(actionCommand == "^"){
            	robot.keyPress(KeyEvent.VK_SHIFT);
            	robot.keyPress(KeyEvent.VK_6);robot.keyRelease(KeyEvent.VK_6);
            	robot.keyRelease(KeyEvent.VK_SHIFT);}
            if(actionCommand == "~"){
            	robot.keyPress(KeyEvent.VK_SHIFT);
            	robot.keyPress(KeyEvent.VK_BACK_QUOTE);robot.keyRelease(KeyEvent.VK_BACK_QUOTE);
            	robot.keyRelease(KeyEvent.VK_SHIFT);}
            if(actionCommand == "`"){
            	robot.keyPress(KeyEvent.VK_BACK_QUOTE);robot.keyRelease(KeyEvent.VK_BACK_QUOTE);}
            if(actionCommand == "_"){
            	robot.keyPress(KeyEvent.VK_SHIFT);
            	robot.keyPress(KeyEvent.VK_MINUS);robot.keyRelease(KeyEvent.VK_MINUS);
            	robot.keyRelease(KeyEvent.VK_SHIFT);}
            if(actionCommand == "["){robot.keyPress(KeyEvent.VK_OPEN_BRACKET);robot.keyRelease(KeyEvent.VK_OPEN_BRACKET);}
            if(actionCommand == "]"){robot.keyPress(KeyEvent.VK_CLOSE_BRACKET);robot.keyRelease(KeyEvent.VK_CLOSE_BRACKET);}
            if(actionCommand == "{"){
            	robot.keyPress(KeyEvent.VK_SHIFT);
            	robot.keyPress(KeyEvent.VK_OPEN_BRACKET);robot.keyRelease(KeyEvent.VK_OPEN_BRACKET);
            	robot.keyRelease(KeyEvent.VK_SHIFT);}
            if(actionCommand == "}"){
            	robot.keyPress(KeyEvent.VK_SHIFT);
            	robot.keyPress(KeyEvent.VK_CLOSE_BRACKET);robot.keyRelease(KeyEvent.VK_CLOSE_BRACKET);
            	robot.keyRelease(KeyEvent.VK_SHIFT);}
            if(actionCommand == "+"){
            	robot.keyPress(KeyEvent.VK_SHIFT);
            	robot.keyPress(KeyEvent.VK_EQUALS);robot.keyRelease(KeyEvent.VK_EQUALS);
            	robot.keyRelease(KeyEvent.VK_SHIFT);}
            if(actionCommand == "-"){robot.keyPress(KeyEvent.VK_MINUS);robot.keyRelease(KeyEvent.VK_MINUS);}
            if(actionCommand == "="){robot.keyPress(KeyEvent.VK_EQUALS);robot.keyRelease(KeyEvent.VK_EQUALS);}
            if(actionCommand == "*"){
            	robot.keyPress(KeyEvent.VK_SHIFT);
            	robot.keyPress(KeyEvent.VK_8);robot.keyRelease(KeyEvent.VK_8);
            	robot.keyRelease(KeyEvent.VK_SHIFT);}
            if(actionCommand == "0"){robot.keyPress(KeyEvent.VK_0);robot.keyRelease(KeyEvent.VK_0);}
            if(actionCommand == "1"){robot.keyPress(KeyEvent.VK_1);robot.keyRelease(KeyEvent.VK_1);}
            if(actionCommand == "2"){robot.keyPress(KeyEvent.VK_2);robot.keyRelease(KeyEvent.VK_2);}
            if(actionCommand == "3"){robot.keyPress(KeyEvent.VK_3);robot.keyRelease(KeyEvent.VK_3);}
            if(actionCommand == "4"){robot.keyPress(KeyEvent.VK_4);robot.keyRelease(KeyEvent.VK_4);}
            if(actionCommand == "5"){robot.keyPress(KeyEvent.VK_5);robot.keyRelease(KeyEvent.VK_5);}
            if(actionCommand == "6"){robot.keyPress(KeyEvent.VK_6);robot.keyRelease(KeyEvent.VK_6);}
            if(actionCommand == "7"){robot.keyPress(KeyEvent.VK_7);robot.keyRelease(KeyEvent.VK_7);}
            if(actionCommand == "8"){robot.keyPress(KeyEvent.VK_8);robot.keyRelease(KeyEvent.VK_8);}
            if(actionCommand == "9"){robot.keyPress(KeyEvent.VK_9);robot.keyRelease(KeyEvent.VK_9);}
			} catch (AWTException e) {
				e.printStackTrace();
			}
			
/*			try {
	        	//int temp2; String temp;
	        	temp = actionCommand;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	} */
			//write();
			}
	};
			
    private ActionListener abcd
	= new ActionListener(){
		public void actionPerformed(ActionEvent action0){
			String actionCommand = action0.getActionCommand();
			String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
			if((shiftclick == 0) && ((capsclick % 2) == 0)){
			txt.setText(txt.getText() + actionCommand);
			tp1.setText(contenttp1 + actionCommand);
			content = content + actionCommand;}else{
			actionCommand = actionCommand.toUpperCase();
			txt.setText(txt.getText() + actionCommand);
			tp1.setText(contenttp1 + actionCommand);
			content = content + actionCommand;	
			}
			//write();
			int temp2; String temp = null;
			try {
	        	//int temp2; String temp;
	        	temp = actionCommand;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	} 
			shiftclick = 0;
			generatelist();
		//	txt.requestFocusInWindow();
			System.out.println(actionCommand);
			fullword();
		}			
	};
	String fristring1;
	private ActionListener abcd11
	= new ActionListener(){
		public void actionPerformed(ActionEvent action1){
			String actionCommand = action1.getActionCommand();
			String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
			if((shiftclick == 0) && ((capsclick % 2) == 0)){
			txt.setText(txt.getText() + fristring1 + actionCommand);
			tp1.setText(contenttp1 + fristring1 + actionCommand);
			content = content + fristring1 + actionCommand;
			int temp2; String temp = null;
			try {
	        	//int temp2; String temp;
	        	temp = fristring1;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	} 
			try {
	        	//int temp2; String temp;
	        	temp = actionCommand;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	}
			
			}else{
			fristring1 = fristring1.toUpperCase();
			actionCommand = actionCommand.toUpperCase();
			txt.setText(txt.getText() + fristring1 + actionCommand);
			tp1.setText(contenttp1 + fristring1 + actionCommand);
			content = content + fristring1 + actionCommand;	
			int temp2; String temp = null;
			try {
	        	//int temp2; String temp;
	        	temp = fristring1;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	} 
			try {
	        	//int temp2; String temp;
	        	temp = actionCommand;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	}
			}
			//write();
			shiftclick = 0;
			generatelist();
		//	txt.requestFocusInWindow();
			System.out.println(actionCommand);
		}			
	};
	String fristring2;
	private ActionListener abcd12
	= new ActionListener(){
		public void actionPerformed(ActionEvent action2){
			String actionCommand = action2.getActionCommand();
			String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
			if((shiftclick == 0) && ((capsclick % 2) == 0)){
			txt.setText(txt.getText() + fristring2 + actionCommand);
			tp1.setText(contenttp1 + fristring2 + actionCommand);
			content = content + fristring2 + actionCommand;
			int temp2; String temp = null;
			try {
	        	//int temp2; String temp;
	        	temp = fristring2;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	} 
			try {
	        	//int temp2; String temp;
	        	temp = actionCommand;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	}
			}else{
			fristring2 = fristring2.toUpperCase();
			actionCommand = actionCommand.toUpperCase();
			txt.setText(txt.getText() + fristring2 + actionCommand);
			tp1.setText(contenttp1 + fristring2 + actionCommand);
			content = content + fristring2 + actionCommand;
			int temp2; String temp = null;
			try {
	        	//int temp2; String temp;
	        	temp = fristring2;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	} 
			try {
	        	//int temp2; String temp;
	        	temp = actionCommand;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	}
			}
		//	write();
			shiftclick = 0;
			generatelist();
	//		txt.requestFocusInWindow();
			System.out.println(actionCommand);
		}			
	};
	String fristring3;
	private ActionListener abcd13
	= new ActionListener(){
		public void actionPerformed(ActionEvent action3){
			String actionCommand = action3.getActionCommand();
			String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
			if((shiftclick == 0) && ((capsclick % 2) == 0)){
			txt.setText(txt.getText() + fristring3 + actionCommand);			
			tp1.setText(contenttp1 + fristring3 + actionCommand);
			content = content + fristring3 + actionCommand;
			int temp2; String temp = null;
			try {
	        	//int temp2; String temp;
	        	temp = fristring3;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	} 
			try {
	        	//int temp2; String temp;
	        	temp = actionCommand;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	}
			}else{
			fristring3 = fristring3.toUpperCase();
			actionCommand = actionCommand.toUpperCase();
			txt.setText(txt.getText() + fristring3 + actionCommand);
			tp1.setText(contenttp1 + fristring3 + actionCommand);
			content = content + fristring3 + actionCommand;
			int temp2; String temp = null;
			try {
	        	//int temp2; String temp;
	        	temp = fristring3;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	} 
			try {
	        	//int temp2; String temp;
	        	temp = actionCommand;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	}
			}
		//	write();
			shiftclick = 0;
			generatelist();
	//		txt.requestFocusInWindow();
			System.out.println(actionCommand);
		}			
	};
	String fristring4;
	private ActionListener abcd14
	= new ActionListener(){
		public void actionPerformed(ActionEvent action4){
			String actionCommand = action4.getActionCommand();
			String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
			if((shiftclick == 0) && ((capsclick % 2) == 0)){
			txt.setText(txt.getText() + fristring4 + actionCommand);
			tp1.setText(contenttp1 + fristring4 + actionCommand);
			content = content + fristring4 + actionCommand;
			int temp2; String temp = null;
			try {
	        	//int temp2; String temp;
	        	temp = fristring4;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	} 
			try {
	        	//int temp2; String temp;
	        	temp = actionCommand;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	}
			}else{
			fristring4 = fristring4.toUpperCase();
			actionCommand = actionCommand.toUpperCase();
			txt.setText(txt.getText() + fristring4 + actionCommand);
			tp1.setText(contenttp1 + fristring4 + actionCommand);
			content = content + fristring4 + actionCommand;	
			int temp2; String temp = null;
			try {
	        	//int temp2; String temp;
	        	temp = fristring4;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	} 
			try {
	        	//int temp2; String temp;
	        	temp = actionCommand;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	}
			}
		//	write();
			shiftclick = 0;
			generatelist();
	//		txt.requestFocusInWindow();
			System.out.println(actionCommand);
		}			
	};
	
	String sristring1;
	private ActionListener abcd21
	= new ActionListener(){
		public void actionPerformed(ActionEvent action1){
			String actionCommand = action1.getActionCommand();
			String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
			if((shiftclick == 0) && ((capsclick % 2) == 0)){
			txt.setText(txt.getText() + fristring1 + sristring1 + actionCommand);
			tp1.setText(contenttp1 + fristring1 + sristring1 + actionCommand);
			content = content + fristring1 + sristring1 + actionCommand;
			int temp2; String temp = null;
			try {
	        	//int temp2; String temp;
	        	temp = fristring1;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	} 
			try {
	        	//int temp2; String temp;
	        	temp = sristring1;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	} 
			try {
	        	//int temp2; String temp;
	        	temp = actionCommand;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	}
			}else{
			fristring1 = fristring1.toUpperCase();
			sristring1 = sristring1.toUpperCase();
			actionCommand = actionCommand.toUpperCase();
			txt.setText(txt.getText() + fristring1 + sristring1 + actionCommand);
			tp1.setText(contenttp1 + fristring1 + sristring1 + actionCommand);
			content = content + fristring1 + sristring1 + actionCommand;	
			int temp2; String temp = null;
			try {
	        	//int temp2; String temp;
	        	temp = fristring1;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	} 
			try {
	        	//int temp2; String temp;
	        	temp = sristring1;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	} 
			try {
	        	//int temp2; String temp;
	        	temp = actionCommand;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	}
			}
		//	write();
			shiftclick = 0;
			generatelist();
	//		txt.requestFocusInWindow();
			System.out.println(actionCommand);
		}			
	};
	String sristring2;
	private ActionListener abcd22
	= new ActionListener(){
		public void actionPerformed(ActionEvent action2){
			String actionCommand = action2.getActionCommand();
			String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
			if((shiftclick == 0) && ((capsclick % 2) == 0)){
			txt.setText(txt.getText() + fristring1 + sristring2 + actionCommand);
			tp1.setText(contenttp1 + fristring1 + sristring2 + actionCommand);
			content = content + fristring1 + sristring2 + actionCommand;
			int temp2; String temp = null;
			try {
	        	//int temp2; String temp;
	        	temp = fristring1;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	} 
			try {
	        	//int temp2; String temp;
	        	temp = sristring2;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	} 
			try {
	        	//int temp2; String temp;
	        	temp = actionCommand;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	}
			}else{
			fristring1 = fristring1.toUpperCase();
			sristring2 = sristring2.toUpperCase();
			actionCommand = actionCommand.toUpperCase();
			txt.setText(txt.getText() + fristring1 + sristring2 + actionCommand);
			tp1.setText(contenttp1 + fristring1 + sristring2 + actionCommand);
			content = content + fristring1 + sristring2 + actionCommand;	
			int temp2; String temp = null;
			try {
	        	//int temp2; String temp;
	        	temp = fristring1;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	} 
			try {
	        	//int temp2; String temp;
	        	temp = sristring2;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	} 
			try {
	        	//int temp2; String temp;
	        	temp = actionCommand;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	}
			}
		//	write();
			shiftclick = 0;
			generatelist();
		//	txt.requestFocusInWindow();
			System.out.println(actionCommand);
		}			
	};
	String sristring3;
	private ActionListener abcd23
	= new ActionListener(){
		public void actionPerformed(ActionEvent action3){
			String actionCommand = action3.getActionCommand();
			String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
			if((shiftclick == 0) && ((capsclick % 2) == 0)){
			txt.setText(txt.getText() + fristring2 + sristring3 + actionCommand);
			tp1.setText(contenttp1 + fristring2 + sristring3 + actionCommand);
			content = content + fristring2 + sristring3 + actionCommand;
			int temp2; String temp = null;
			try {
	        	//int temp2; String temp;
	        	temp = fristring2;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	} 
			try {
	        	//int temp2; String temp;
	        	temp = sristring3;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	} 
			try {
	        	//int temp2; String temp;
	        	temp = actionCommand;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	}
			}else{
			fristring2 = fristring2.toUpperCase();
			sristring3 = sristring3.toUpperCase();
			actionCommand = actionCommand.toUpperCase();	
			txt.setText(txt.getText() + fristring2 + sristring3 + actionCommand);
			tp1.setText(contenttp1 + fristring2 + sristring3 + actionCommand);
			content = content + fristring2 + sristring3 + actionCommand;
			int temp2; String temp = null;
			try {
	        	//int temp2; String temp;
	        	temp = fristring2;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	} 
			try {
	        	//int temp2; String temp;
	        	temp = sristring3;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	} 
			try {
	        	//int temp2; String temp;
	        	temp = actionCommand;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	}
			}
		//	write();
			shiftclick = 0;
			generatelist();
		//	txt.requestFocusInWindow();
			System.out.println(actionCommand);
		}			
	};
	String sristring4;
	private ActionListener abcd24
	= new ActionListener(){
		public void actionPerformed(ActionEvent action3){
			String actionCommand = action3.getActionCommand();
			String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
			if((shiftclick == 0) && ((capsclick % 2) == 0)){
			txt.setText(txt.getText() + fristring2 + sristring4 + actionCommand);
			tp1.setText(contenttp1 + fristring2 + sristring4 + actionCommand);
			content = content + fristring2 + sristring4 + actionCommand;
			int temp2; String temp = null;
			try {
	        	//int temp2; String temp;
	        	temp = fristring2;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	} 
			try {
	        	//int temp2; String temp;
	        	temp = sristring4;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	} 
			try {
	        	//int temp2; String temp;
	        	temp = actionCommand;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	}
			}else{
			fristring2 = fristring2.toUpperCase();
			sristring4 = sristring4.toUpperCase();
			actionCommand = actionCommand.toUpperCase();
			txt.setText(txt.getText() + fristring2 + sristring4 + actionCommand);
			tp1.setText(contenttp1 + fristring2 + sristring4 + actionCommand);
			content = content + fristring2 + sristring4 + actionCommand;
			int temp2; String temp = null;
			try {
	        	//int temp2; String temp;
	        	temp = fristring2;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	} 
			try {
	        	//int temp2; String temp;
	        	temp = sristring4;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	} 
			try {
	        	//int temp2; String temp;
	        	temp = actionCommand;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	}
			}
		//	write();
			shiftclick = 0;
			generatelist();
	//		txt.requestFocusInWindow();
			System.out.println(actionCommand);
		}			
	};
	String sristring5;
	private ActionListener abcd25
	= new ActionListener(){
		public void actionPerformed(ActionEvent action3){
			String actionCommand = action3.getActionCommand();
			String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
			if((shiftclick == 0) && ((capsclick % 2) == 0)){
			txt.setText(txt.getText() + fristring3 + sristring5 + actionCommand);
			tp1.setText(contenttp1 + fristring3 + sristring5 + actionCommand);
			content = content + fristring3 + sristring5 + actionCommand;
			int temp2; String temp = null;
			try {
	        	//int temp2; String temp;
	        	temp = fristring3;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	} 
			try {
	        	//int temp2; String temp;
	        	temp = sristring5;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	} 
			try {
	        	//int temp2; String temp;
	        	temp = actionCommand;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	}}else{
			fristring3 = fristring3.toUpperCase();
			sristring5 = sristring5.toUpperCase();
			actionCommand = actionCommand.toUpperCase();
			txt.setText(txt.getText() + fristring3 + sristring5 + actionCommand);
			tp1.setText(contenttp1 + fristring3 + sristring5 + actionCommand);
			content = content + fristring3 + sristring5 + actionCommand;	
			int temp2; String temp = null;
			try {
	        	//int temp2; String temp;
	        	temp = fristring3;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	} 
			try {
	        	//int temp2; String temp;
	        	temp = sristring5;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	} 
			try {
	        	//int temp2; String temp;
	        	temp = actionCommand;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	}
			}
		//	write();
			shiftclick = 0;
			generatelist();
	//		txt.requestFocusInWindow();
			System.out.println(actionCommand);
		}			
	};
	String sristring6;
	private ActionListener abcd26
	= new ActionListener(){
		public void actionPerformed(ActionEvent action3){
			String actionCommand = action3.getActionCommand();
			String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
			if((shiftclick == 0) && ((capsclick % 2) == 0)){
			txt.setText(txt.getText() + fristring3 + sristring6 + actionCommand);
			tp1.setText(contenttp1 + fristring3 + sristring6 + actionCommand);
			content = content + fristring3 + sristring6 + actionCommand;
			int temp2; String temp = null;
			try {
	        	//int temp2; String temp;
	        	temp = fristring3;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	} 
			try {
	        	//int temp2; String temp;
	        	temp = sristring6;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	} 
			try {
	        	//int temp2; String temp;
	        	temp = actionCommand;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	}}else{
			fristring3 = fristring3.toUpperCase();
			sristring6 = sristring6.toUpperCase();
			actionCommand = actionCommand.toUpperCase();	
			txt.setText(txt.getText() + fristring3 + sristring6 + actionCommand);
			tp1.setText(contenttp1 + fristring3 + sristring6 + actionCommand);
			content = content + fristring3 + sristring6 + actionCommand;
			int temp2; String temp = null;
			try {
	        	//int temp2; String temp;
	        	temp = fristring3;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	} 
			try {
	        	//int temp2; String temp;
	        	temp = sristring6;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	} 
			try {
	        	//int temp2; String temp;
	        	temp = actionCommand;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	}
	        	}
	//		write();
			shiftclick = 0;
			generatelist();
		//	txt.requestFocusInWindow();
			System.out.println(actionCommand);
		}			
	};
	String sristring7;
	private ActionListener abcd27
	= new ActionListener(){
		public void actionPerformed(ActionEvent action3){
			String actionCommand = action3.getActionCommand();
			String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
			if((shiftclick == 0) && ((capsclick % 2) == 0)){
			txt.setText(txt.getText() + fristring4 + sristring7 + actionCommand);
			tp1.setText(contenttp1 + fristring4 + sristring7 + actionCommand);
			content = content + fristring4 + sristring7 + actionCommand;
			int temp2; String temp = null;
			try {
	        	//int temp2; String temp;
	        	temp = fristring4;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	} 
			try {
	        	//int temp2; String temp;
	        	temp = sristring7;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	} 
			try {
	        	//int temp2; String temp;
	        	temp = actionCommand;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	}
			}else{
			fristring4 = fristring4.toUpperCase();
			sristring7 = sristring7.toUpperCase();
			actionCommand = actionCommand.toUpperCase();
			txt.setText(txt.getText() + fristring4 + sristring7 + actionCommand);
			tp1.setText(contenttp1 + fristring4 + sristring7 + actionCommand);
			content = content + fristring4 + sristring7 + actionCommand;
			int temp2; String temp = null;
			try {
	        	//int temp2; String temp;
	        	temp = fristring4;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	} 
			try {
	        	//int temp2; String temp;
	        	temp = sristring7;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	} 
			try {
	        	//int temp2; String temp;
	        	temp = actionCommand;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	}
			}
	   //  	write();
			shiftclick = 0;
			generatelist();
	//		txt.requestFocusInWindow();
			System.out.println(actionCommand);
		}			
	};
	String sristring8;
	private ActionListener abcd28
	= new ActionListener(){
		public void actionPerformed(ActionEvent action3){
			String actionCommand = action3.getActionCommand();
			String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
			if((shiftclick == 0) && ((capsclick % 2) == 0)){
			txt.setText(txt.getText() + fristring4 + sristring8 + actionCommand);
			tp1.setText(contenttp1 + fristring4 + sristring8 + actionCommand);
			content = content + fristring4 + sristring8 + actionCommand;
			int temp2; String temp = null;
			try {
	        	//int temp2; String temp;
	        	temp = fristring4;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	} 
			try {
	        	//int temp2; String temp;
	        	temp = sristring8;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	} 
			try {
	        	//int temp2; String temp;
	        	temp = actionCommand;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	}
			}else{
			fristring4 = fristring4.toUpperCase();
			sristring8 = sristring8.toUpperCase();
			actionCommand = actionCommand.toUpperCase();	
			txt.setText(txt.getText() + fristring4 + sristring8 + actionCommand);
			tp1.setText(contenttp1 + fristring4 + sristring8 + actionCommand);
			content = content + fristring4 + sristring8 + actionCommand;
			int temp2; String temp = null;
			try {
	        	//int temp2; String temp;
	        	temp = fristring4;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	} 
			try {
	        	//int temp2; String temp;
	        	temp = sristring8;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	} 
			try {
	        	//int temp2; String temp;
	        	temp = actionCommand;
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	            Robot robot = new Robot();

	            robot.keyPress(keyCode);       
	        	} catch (AWTException e) {
	        		e.printStackTrace();
	        	}
			}
		//	write();
			shiftclick = 0;
			generatelist();
	//		txt.requestFocusInWindow();
			System.out.println(actionCommand);
		}			
	};
	
	public P3() {
		gui();
	}

	public void gui(){
		//open a word document
		/*
		try{
			Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+"C:\\Users\\djing\\Desktop\\123.txt");
		} catch (IOException ioe) {
		  ioe.printStackTrace(); }
		*/
		//frame
		fra = new JFrame("Good");
		fra.pack();
		fra.setVisible(true);
		fra.setSize(450,600);
		fra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fra.setLocationRelativeTo(null);
		fra.setLayout(new BorderLayout());
        fra.setAlwaysOnTop( true );
        
        fra.addMouseListener(new java.awt.event.MouseAdapter(){
			public void mouseEntered(MouseEvent e) {
				fra.setFocusableWindowState(false);    	
		    }		
		});
		
		//panel
		pan = new JPanel();
		pan.setBackground(Color.WHITE);
		pan.setPreferredSize(null);
		pan.setLayout(new BorderLayout());

		//set the layout
		/* p.setLayout(new FlowLayout(FlowLayout.RIGHT, 50,50));
		 * p.setLayout(new BorderLayout());
		 * Box b = Box.createHorizontalBox();*/
		
	//	read();
		ratio1 = (double)(fra.getWidth()/450.0);
		//image
		lab = new JLabel();	
	//	lab.setLayout(new BorderLayout());
		lab.setBounds(50, 50, fra.getWidth()-100, (int)((fra.getWidth()-100)*1.18));
	//	lab.setBounds(70, 0, 500, 500);
		lab.setBackground(Color.DARK_GRAY);
		lab.repaint();
		lab.revalidate();
		image = new ImageIcon(getClass().getResource("bg1.jpg"));
		image1 = new ImageIcon(getClass().getResource("bg.jpg"));
	
		Image imagey = image.getImage();
		Image imagea = imagey.getScaledInstance(fra.getWidth()-100,(int)((fra.getWidth()-100)*1.18) , java.awt.Image.SCALE_SMOOTH);
		image = new ImageIcon(imagea);

	//	image = new ImageIcon(new ImageIcon("bg.jpg").getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
		image1 = new ImageIcon(image1.getImage().getScaledInstance(fra.getWidth()-150, (int)((fra.getWidth()-150)*1.18), Image.SCALE_SMOOTH));

		//ImageDrawer.drawScaledImage(image.getImage(), lab, );
	//	lab.setLayout(new BorderLayout());
	//	lab.setIcon(imageIcon);
		lab.setIcon(image);
		lab.repaint();
		lab.revalidate(); 
		
		reset = new JButton("reset");
		reset.setBounds(0,0, 70,40); reset.setFont(new Font("Arial", Font.PLAIN, 15));

	/*	b1.setBorder(null);
	    b1.setBorderPainted(false);
	    b1.setContentAreaFilled(false);
	    b1.setOpaque(false); */

	//	changecolor();
         
		//scrollpane for the full predicted words
		//sp = new JScrollPane(ta);
		//sp.setBounds(70, 178, 80, 149);
		ta.setBounds(70, 178, 80, 149);
		//full words buttons

/*		fw1.setBounds(60, 155, 75, 20);fw2.setBounds(60, 175, 80, 20);
		fw3.setBounds(60, 195, 80, 20);fw4.setBounds(60, 215, 80, 20);
		fw5.setBounds(60, 235, 80, 20);fw6.setBounds(60, 255, 80, 20);
		fw7.setBounds(60, 275, 80, 20);fw8.setBounds(60, 295, 80, 20);
		fw9.setBounds(60, 315, 80, 20);fw10.setBounds(60, 335, 75, 20);
*/
		for (int u=0; u<10; u++){
			wordbuttons[u] = new JButton("");
			wordbuttons[u].setBorder(null);wordbuttons[u].setBorderPainted(false);wordbuttons[u].setContentAreaFilled(false);wordbuttons[u].setOpaque(false); 	
			wordbuttons[u].addActionListener(fullwordl);
			wordbuttons[u].setBounds((int)(30*ratio1), (int)((160+20*u)*ratio1), (int)(75*ratio1), (int)(20*ratio1));
			wordbuttons[u].setFont(new Font("Courier", Font.PLAIN, (int)(12*ratio1)));
			final Integer c = new Integer(u);
			wordbuttons[u].addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					// saySomething("Mouse entered", e);
					wordbuttons[c].setBackground(Color.LIGHT_GRAY);
					wordbuttons[c].setContentAreaFilled(true);
					
				}
				public void mouseExited(MouseEvent e) {
					wordbuttons[c].setBackground(null);
					wordbuttons[c].setContentAreaFilled(false);
				}
			});	
		}

		lastWord1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1"); 
		lastWord1length = lastWord1.length();
		//text box
		ratio1 = (double)(fra.getWidth()/500.0);
		Font font1 = new Font("Courier", Font.PLAIN,(int)(30*ratio1));
		Font font2 = new Font("Courier",Font.CENTER_BASELINE,(int)(14*ratio1));
		midX = (int)((fra.getWidth()-100)/2.8+50);
		midY = (int) ((fra.getWidth()-100)/2.8*1.6+42); 
		
		txt = new JTextArea(){
            public void addNotify() {
                super.addNotify();
                requestFocus();
            }
        };
        txt.setFont(font1);
       
		sp1 = new JScrollPane(txt);
		sp1.setBounds((int)(fra.getWidth()*0.05),(int)(fra.getHeight()* 0.8),(int)(400*ratio1),(int)(50*ratio1));

        //text box in the middle
  /*      tp1.setOpaque(false);
        tp1.setBorder(null); 
        sp2.setBounds(190,229,50,45);
		sp2.getViewport().setOpaque(false);
		sp2.setOpaque(false);
		sp2.setBorder(null);  */
		tp1 = new JTextPane();
		tp1.setBounds(midX,midY,(int)(50*ratio1),(int)(30*ratio1));
		tp1.setOpaque(false);
		tp1.setBorder(null);
		StyledDocument doc = tp1.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		tp1.setFont(font2);
		//doc.setParagraphAttributes(0, doc.get, center, false);
		//tp1.setComponentOrientation(ComponentOrientation.getOrientation(Center));

		phase2buttons[0] = new JButton("+");phase2buttons[1] = new JButton("-");
		phase2buttons[2] = new JButton("=");phase2buttons[3] = new JButton("*");
		phase2buttons[4] = new JButton("/");
		phase2buttons[5] = new JButton("0");phase2buttons[6] = new JButton("1");
		phase2buttons[7] = new JButton("2");phase2buttons[8] = new JButton("3");
		phase2buttons[9] = new JButton("4");phase2buttons[10] = new JButton("5");
		phase2buttons[11] = new JButton("6");phase2buttons[12] = new JButton("7");
		phase2buttons[13] = new JButton("8");phase2buttons[14] = new JButton("9");		
		phase2buttons[15] = new JButton("/");phase2buttons[16] = new JButton("\\");
		phase2buttons[17] = new JButton("|"); phase2buttons[18] = new JButton("<");
		phase2buttons[19] = new JButton(">");phase2buttons[20] = new JButton("%");
		phase2buttons[21] = new JButton("^");phase2buttons[22] = new JButton("~"); 
		phase2buttons[23] = new JButton("`");phase2buttons[24] = new JButton("_");
		phase2buttons[25] = new JButton("[");phase2buttons[26] = new JButton("]");
		phase2buttons[27] = new JButton("{");phase2buttons[28] = new JButton("}");	
		
		for (int w=0;w<29;w++){
			
			phase2buttons[w].setBorder(null);				
			phase2buttons[w].setBorderPainted(false);
			phase2buttons[w].setContentAreaFilled(false);
			phase2buttons[w].setOpaque(false);

			midX = (int)((fra.getWidth()-100)/2.8+50);
			midY = (int) ((fra.getWidth()-100)/2.8*1.6+42); 
			
			if (w<5){
				radius = (int)((fra.getWidth()-100)/2.8*0.4);
				degreeBetween = 55;
				radianBetween = Math.toRadians(degreeBetween);
				xValue = (int)(radius*(Math.sin(radianBetween*(w-0.5))));
				yValue = (int)(Math.sqrt(Math.pow(radius,2)-Math.pow(xValue, 2)));
				if(w<3){
					yValue = -1*yValue;
				}
			}else if (w<15){
				radius = (int)((fra.getWidth()-100)/2.8*0.67);
				degreeBetween = 25;
				radianBetween = Math.toRadians(degreeBetween);
				xValue = (int)(radius*(Math.sin(radianBetween*(w-6))));
				yValue = (int)(Math.sqrt(Math.pow(radius,2)-Math.pow(xValue, 2)));
				if(w<10){
					yValue = -1*yValue;
				}
			}else{
				radius = (int)((fra.getWidth()-100)/2.8*0.95);
				degreeBetween = 18;
				radianBetween = Math.toRadians(degreeBetween);
				xValue = (int)(radius*(Math.sin(radianBetween*(w-17))));
				yValue = (int)(Math.sqrt(Math.pow(radius,2)-Math.pow(xValue, 2)));
				if(w<22){
					yValue = -1*yValue;
				}
			}

			xValue +=midX;
			yValue +=midY; 						
			phase2buttons[w].setBounds(xValue, yValue, (int)(BUTTON_SIZE_X*0.8),(int)(BUTTON_SIZE_Y*0.8));
			ratio1 = (double)(fra.getWidth()/500.0);
			phase2buttons[w].setFont(new Font("Arial", Font.PLAIN, (int)(25*ratio1)));	
				
	/*		phase2buttons[w].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ep2){
				String actionCommand = ep2.getActionCommand();
				txt.setText(txt.getText() + actionCommand);
				tp1.setText(tp1.getText() + actionCommand);
				content = content + actionCommand;
				write();
				}			
				});		*/
						
			final Integer x = new Integer(w);
			phase2buttons[x].addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(MouseEvent e) {
			        // saySomething("Mouse entered", e);
			    	phase2buttons[x].setBackground(Color.PINK);
			    	phase2buttons[x].setContentAreaFilled(true);
			     	
			    }
			    public void mouseExited(MouseEvent e) {
			    	phase2buttons[x].setBackground(null);
			    	phase2buttons[x].setContentAreaFilled(false);
			    }
			});
			
		}

//		System.out.println("xxxxxxxxx");
/*		ex1 = new JButton("+");ex2 = new JButton("-");ex3 = new JButton("*");ex4 = new JButton("/");ex5 = new JButton("=");
		no0 = new JButton("0");no1 = new JButton("1");no2 = new JButton("2");no3 = new JButton("3");no4 = new JButton("4");no5 = new JButton("5");no6 = new JButton("6");no7 = new JButton("7");no8 = new JButton("8");no9 = new JButton("9");		
		d1 = new JButton("/");d2 = new JButton("\\");d3 = new JButton("|"); d4 = new JButton("<");d5 = new JButton(">");d6 = new JButton("%");d7 = new JButton("^");d8 = new JButton("~"); d9 = new JButton("`");d10 = new JButton("_");
		mbra1 = new JButton("[");mbra2 = new JButton("]");lbra1 = new JButton("{");lbra2 = new JButton("}");
*/		

		
//		flipicon = new ImageIcon("C:\\Users\\djing\\Desktop\\jde\\flipimage.png");
		java.net.URL flipi = P3.class.getResource("flipimage.png");
		flipicon = new ImageIcon(flipi);
		iflip = flipicon.getImage();
		flipwidth = iflip.getWidth(null); flipheight = iflip.getHeight(null);
		iflip = iflip.getScaledInstance((int)(flipwidth/6*ratio1), (int)(flipheight/4*ratio1), Image.SCALE_SMOOTH);
		flipicon.setImage(iflip);
		flip = new JButton(flipicon);
		midX = (int)((fra.getWidth()-100)/2.8+50);
		midY = (int) ((fra.getWidth()-100)/2.8*1.6+45); 
		radius = (int)((fra.getWidth()-100)/2.8*1.2);
		ratio1 = (double)(fra.getWidth()/450.0);
		
		flip.setBounds(midX+radius, midY, (int)(BUTTON_SIZE_X*ratio1),(int)(BUTTON_SIZE_Y*ratio1));//flip.setFont(new Font("Arial", Font.PLAIN, 30));
		flip.setBorder(null);flip.setBorderPainted(false);flip.setContentAreaFilled(false);flip.setOpaque(false);
		flip.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent eflip){
			//change all the buttons
				click++;
				if((click % 2) == 1){
					excel();
					} else{
					lab.setIcon(image);
					button();
					generatelist();	
					
					for(int l=0; l<28; l++ ){
						pan.add(predictbuttons[l]);
					}
			/*		pan.add(fr1);pan.add(fr2);pan.add(fr3);pan.add(fr4);pan.add(ec1);pan.add(ec2);
					pan.add(sr1);pan.add(sr2);pan.add(sr3);pan.add(sr4);pan.add(sr5);pan.add(sr6);pan.add(sr7);pan.add(sr8);
					pan.add(tr1);pan.add(tr2);pan.add(tr3);pan.add(tr4);pan.add(tr5);pan.add(tr6);pan.add(tr7);pan.add(tr8);pan.add(tr9);pan.add(tr10);pan.add(tr11);pan.add(tr12);pan.add(tr13);pan.add(tr14);pan.add(tr15);pan.add(tr16);
			*/
					pan.add(tp1);
			//		pan.add(fw1);pan.add(fw2);pan.add(fw3);pan.add(fw4);pan.add(fw5);pan.add(fw6);pan.add(fw7);pan.add(fw8);pan.add(fw9);pan.add(fw10);
					for (int e=0; e<10; e++){
						pan.add(wordbuttons[e]);
					}
					
					pan.add(reset);pan.add(lab);
					//pan.add(lab,BorderLayout.CENTER);
					fra.add(pan);
					pan.revalidate();
					pan.repaint();
					fra.revalidate();
					fra.repaint();
					}
				System.out.println(click);
			}	
		});	
		flip.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		        // saySomething("Mouse entered", e);
		    	flip.setBackground(Color.LIGHT_GRAY);
		    	flip.setContentAreaFilled(true);
		     	
		    }
		    public void mouseExited(MouseEvent e) {
		    	flip.setBackground(null);
		    	flip.setContentAreaFilled(false);
		    }
		});
		
		button();

		reset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent res){
				Image imagey = image.getImage();
				Image imagea = imagey.getScaledInstance(fra.getWidth()-100,(int)((fra.getWidth()-100)*1.18), java.awt.Image.SCALE_SMOOTH);
				image = new ImageIcon(imagea);				
//				image = new ImageIcon(image.getImage().getScaledInstance(lab.getWidth(), lab.getHeight(), Image.SCALE_SMOOTH));
				image1 = new ImageIcon(image1.getImage().getScaledInstance(fra.getWidth()-100, (int)((fra.getWidth()-100)*1.18), Image.SCALE_SMOOTH));

				lab.setBounds(50, 50, fra.getWidth()-100, (int)((fra.getWidth()-100)*1.18));		
			  
				BUTTON_SIZE_X = 38;      // size height
				BUTTON_SIZE_Y = 38;       // size width 
				ratio1 = (double)(fra.getWidth()/450.0);
				System.out.println("ratio1"+ratio1);
				for(int i=0; i<26; i++){
					midX = (int)((fra.getWidth()-100)/2.8+50);
					midY = (int) ((fra.getWidth()-100)/2.8*1.5+50); 
					radius = (int)((fra.getWidth()-100)/2.8*1.5);
					degreeBetween = 9.5;
					radianBetween = Math.toRadians(degreeBetween);
					
					xValue = (int)(radius*(Math.sin(radianBetween*(i-3))));	
					yValue = (int)(Math.sqrt(Math.pow(radius,2)-Math.pow(xValue, 2)));
					if(i<13){
						yValue = -1*yValue;
					}
					
					xValue +=midX;
					yValue +=midY; 
					
					letterbuttons[i].setBounds(xValue, yValue,(int) (BUTTON_SIZE_X*ratio1), (int) (BUTTON_SIZE_Y*ratio1));
					letterbuttons[i].setFont(new Font("Arial", Font.PLAIN, (int)(30*ratio1)));		
				}
				

				for(int q=0; q<16; q++){
					midX = (int)((fra.getWidth()-100)/2.8+50);
					midY = (int) ((fra.getWidth()-100)/2.8*1.6+45); 
					radius = (int)((fra.getWidth()-100)/2.8*1.2);
					degreeBetween = 15;
					radianBetween = Math.toRadians(degreeBetween);
					
					xValue = (int)(radius*(Math.sin(radianBetween*(q-2))));
					yValue = (int)(Math.sqrt(Math.pow(radius,2)-Math.pow(xValue, 2)));
					if(q<8){
						yValue = -1*yValue;
					}
					if (q >=8){
						xValue = (int)(radius*(Math.sin(radianBetween*(q-1))));
						yValue = (int)(Math.sqrt(Math.pow(radius,2)-Math.pow(xValue, 2)));	
					}
				
					xValue +=midX;
					yValue +=midY; 						
					phase1buttons[q].setBounds(xValue, yValue, (int)(BUTTON_SIZE_X*0.8*ratio1),(int)(BUTTON_SIZE_Y*0.8*ratio1));
					phase1buttons[q].setFont(new Font("Arial", Font.PLAIN, (int)(25*ratio1)));		
					
				}
				ratio1 = (double)(fra.getWidth()/450.0);
				ishift = shifticon.getImage();	
				ishift = ishift.getScaledInstance((int)(shiftwidth/15*ratio1), (int)(shiftheight/14*ratio1), Image.SCALE_SMOOTH);
				shifticon.setImage(ishift);
				icaps = capsicon.getImage();
				icaps = icaps.getScaledInstance((int)(capswidth/4*ratio1), (int)(capsheight/4*ratio1), Image.SCALE_SMOOTH);
				capsicon.setImage(icaps);
				for (int w=0;w<29;w++){

					midX = (int)((fra.getWidth()-100)/2.8+50);
					midY = (int) ((fra.getWidth()-100)/2.8*1.6+42); 
					
					if (w<5){
						radius = (int)((fra.getWidth()-100)/2.8*0.4);
						degreeBetween = 55;
						radianBetween = Math.toRadians(degreeBetween);
						xValue = (int)(radius*(Math.sin(radianBetween*(w-0.5))));
						yValue = (int)(Math.sqrt(Math.pow(radius,2)-Math.pow(xValue, 2)));
						if(w<3){
							yValue = -1*yValue;
						}
					}else if (w<15){
						radius = (int)((fra.getWidth()-100)/2.8*0.67);
						degreeBetween = 25;
						radianBetween = Math.toRadians(degreeBetween);
						xValue = (int)(radius*(Math.sin(radianBetween*(w-6))));
						yValue = (int)(Math.sqrt(Math.pow(radius,2)-Math.pow(xValue, 2)));
						if(w<10){
							yValue = -1*yValue;
						}
					}else{
						radius = (int)((fra.getWidth()-100)/2.8*0.95);
						degreeBetween = 18;
						radianBetween = Math.toRadians(degreeBetween);
						xValue = (int)(radius*(Math.sin(radianBetween*(w-17))));
						yValue = (int)(Math.sqrt(Math.pow(radius,2)-Math.pow(xValue, 2)));
						if(w<22){
							yValue = -1*yValue;
						}
					}

					xValue +=midX;
					yValue +=midY; 						
					phase2buttons[w].setBounds(xValue, yValue, (int)(BUTTON_SIZE_X*0.8),(int)(BUTTON_SIZE_Y*0.8));
					
					phase2buttons[w].setFont(new Font("Arial", Font.PLAIN, (int)(25*ratio1)));
				}	
					
				for(int m=0; m<28; m++){
					midX = (int)((fra.getWidth()-100)/2.8+50);
					midY = (int) ((fra.getWidth()-100)/2.8*1.6+42); 
					
					if (m<4){
						radius = (int)((fra.getWidth()-100)/2.8*0.4);
						degreeBetween = 70;
						radianBetween = Math.toRadians(degreeBetween);
						xValue = (int)(radius*(Math.sin(radianBetween*(m-0.3))));
						yValue = (int)(Math.sqrt(Math.pow(radius,2)-Math.pow(xValue, 2)));
						if(m<2){
							yValue = -1*yValue;
						}
					}else if (m<12){
						radius = (int)((fra.getWidth()-100)/2.8*0.67);
						degreeBetween = 33;
						radianBetween = Math.toRadians(degreeBetween);
						xValue = (int)(radius*(Math.sin(radianBetween*(m-4.9))));
						yValue = (int)(Math.sqrt(Math.pow(radius,2)-Math.pow(xValue, 2)));
						if(m<8){
							yValue = -1*yValue;
						}
					}else{
						radius = (int)((fra.getWidth()-100)/2.8*0.95);
						degreeBetween = 16.5;
						radianBetween = Math.toRadians(degreeBetween);
						xValue = (int)(radius*(Math.sin(radianBetween*(m-14))));
						yValue = (int)(Math.sqrt(Math.pow(radius,2)-Math.pow(xValue, 2)));
						if(m<20){
							yValue = -1*yValue;
						}
					}

					xValue +=midX;
					yValue +=midY; 						
					predictbuttons[m].setBounds(xValue, yValue, (int)(BUTTON_SIZE_X*0.8),(int)(BUTTON_SIZE_Y*0.8));
					
					predictbuttons[m].setFont(new Font("Arial", Font.PLAIN, (int)(25*ratio1)));
				}
				
				for (int u=0; u<10; u++){
					wordbuttons[u].setBounds((int)(30*ratio1), (int)((160+20*u)*ratio1), (int)(75*ratio1), (int)(20*ratio1));
					wordbuttons[u].setFont(new Font("Courier", Font.PLAIN, (int)(12*ratio1)));

				}

				midX = (int)((fra.getWidth()-100)/2.8+50);
				midY = (int) ((fra.getWidth()-100)/2.8*1.6+45); 							
				radius = (int)((fra.getWidth()-100)/2.8*1.2);
				ratio1 = (double)(fra.getWidth()/450.0);

				iflip = iflip.getScaledInstance((int)(flipwidth/6*ratio1), (int)(flipheight/4*ratio1), Image.SCALE_SMOOTH);
				flipicon.setImage(iflip);
				flip.setIcon(flipicon);
				flip.setBounds(midX+radius, midY, (int)(BUTTON_SIZE_X*ratio1),(int)(BUTTON_SIZE_Y*ratio1));//flip.setFont(new Font("Arial", Font.PLAIN, 30));

				ispace = ispace.getScaledInstance((int)(spacewidth/5*ratio1), (int)(spaceheight/4*ratio1), Image.SCALE_SMOOTH);
				spaceicon.setImage(ispace);
				space.setIcon(spaceicon);
				space.setBounds((int)(midX-60*ratio1),(int)(midY-10*ratio1), (int)(50*ratio1), (int)(40*ratio1)); 
				
				ienter = ienter.getScaledInstance((int)(enterwidth/12*ratio1), (int)(enterheight/10*ratio1), Image.SCALE_SMOOTH);
				entericon.setImage(ienter);
				enter.setIcon(entericon);	
				
				idelete = idelete.getScaledInstance((int)(deletewidth/4*ratio1), (int)(deleteheight/4*ratio1), Image.SCALE_SMOOTH);
				deleteicon.setImage(idelete);
				delete.setIcon(deleteicon);
				
				delete.setBounds((int)(midX-60*ratio1), (int)(midY-10*ratio1-30*ratio1), (int)(40*ratio1), (int)(30*ratio1)); 
				enter.setBounds((int)(midX-60*ratio1), (int)(midY-10*ratio1+40*ratio1), (int)(40*ratio1), (int)(30*ratio1));
			
				sp1.setBounds((int)(fra.getWidth()*0.05),(int)(fra.getHeight()* 0.8),(int)(400*ratio1),(int)(50*ratio1));
		 
				tp1.setBounds(midX,midY,(int)(50*ratio1),(int)(30*ratio1));
				
				lab.setIcon(image);lab.repaint();lab.revalidate(); 
			}
			}); 
	   // sbutton();
	  //  punctuation();
	    
		/*b.add(b1);
		 * b.add(Box.createHorizontalStrut(40));
		 * p.add(lab,BorderLayout.EAST);
		 * p.add(b1, BorderLayout.EAST);
		 /* p.add(b2, BorderLayout.EAST);*/
		//p.setLayout(new BorderLayout());
		for(int i=0; i<26; i++){
			pan.add(letterbuttons[i]);
		}
		for(int i=0; i<16; i++){
			pan.add(phase1buttons[i]);
		}
/*		pan.add(b1);pan.add(b2);pan.add(b3);pan.add(b4);pan.add(b5);pan.add(b6);pan.add(b7);pan.add(b8);
		pan.add(b9);pan.add(b10);pan.add(b11);pan.add(b12);pan.add(b13);pan.add(b14);pan.add(b15);pan.add(b16);
		pan.add(b17);pan.add(b18);pan.add(b19);pan.add(b20);pan.add(b21);pan.add(b22);pan.add(b23);pan.add(b24);
		pan.add(b25);pan.add(b26); 
		pan.add(p1);pan.add(p2);pan.add(p3);pan.add(p4);pan.add(p5);pan.add(p6);pan.add(p7);
		pan.add(sharp);pan.add(and);pan.add(dollar);pan.add(dquote);pan.add(squote);pan.add(sbra1);pan.add(sbra2);
		pan.add(shift);pan.add(caps); */
		pan.add(space);pan.add(enter);pan.add(delete);pan.add(flip);
        pan.add(tp1);
        for (int u=0; u<10; u++){
			pan.add(wordbuttons[u]);
		}
		
//        pan.add(fw1);pan.add(fw2);pan.add(fw3);pan.add(fw4);pan.add(fw5);pan.add(fw6);pan.add(fw7);pan.add(fw8);pan.add(fw9);pan.add(fw10);
		fra.add(sp1);
		pan.add(reset);pan.add(lab);
		//fra.add(ta);
		fra.add(pan);
//		fra.setContentPane(lab);
		
/*		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  */
		
		for(int m=0; m<28; m++){
			predictbuttons[m] = new JButton();
		
			predictbuttons[m].setBorder(null);
			predictbuttons[m].setBorderPainted(false);
			predictbuttons[m].setContentAreaFilled(false);
			predictbuttons[m].setOpaque(false);
		
			//Location
			midX = (int)((fra.getWidth()-100)/2.8+50);
			midY = (int) ((fra.getWidth()-100)/2.8*1.6+42); 
			
			if (m<4){
				radius = (int)((fra.getWidth()-100)/2.8*0.4);
				degreeBetween = 70;
				radianBetween = Math.toRadians(degreeBetween);
				xValue = (int)(radius*(Math.sin(radianBetween*(m-0.3))));
				yValue = (int)(Math.sqrt(Math.pow(radius,2)-Math.pow(xValue, 2)));
				if(m<2){
					yValue = -1*yValue;
				}
			}else if (m<12){
				radius = (int)((fra.getWidth()-100)/2.8*0.67);
				degreeBetween = 33;
				radianBetween = Math.toRadians(degreeBetween);
				xValue = (int)(radius*(Math.sin(radianBetween*(m-4.9))));
				yValue = (int)(Math.sqrt(Math.pow(radius,2)-Math.pow(xValue, 2)));
				if(m<8){
					yValue = -1*yValue;
				}
			}else{
				radius = (int)((fra.getWidth()-100)/2.8*0.95);
				degreeBetween = 16.5;
				radianBetween = Math.toRadians(degreeBetween);
				xValue = (int)(radius*(Math.sin(radianBetween*(m-14))));
				yValue = (int)(Math.sqrt(Math.pow(radius,2)-Math.pow(xValue, 2)));
				if(m<20){
					yValue = -1*yValue;
				}
			}

			xValue +=midX;
			yValue +=midY; 						
			predictbuttons[m].setBounds(xValue, yValue, (int)(BUTTON_SIZE_X*0.8),(int)(BUTTON_SIZE_Y*0.8));
			ratio1 = (double)(fra.getWidth()/450.0);
			predictbuttons[m].setFont(new Font("Arial", Font.PLAIN, (int)(25*ratio1)));
		
			//Actionlistener?
		
			final Integer c = new Integer(m);
			predictbuttons[c].addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					// saySomething("Mouse entered", e);
					predictbuttons[c].setBackground(Color.PINK);
					predictbuttons[c].setContentAreaFilled(true);
					
				}
				public void mouseExited(MouseEvent e) {
					predictbuttons[c].setBackground(null);
					predictbuttons[c].setContentAreaFilled(false);
				}
			});
		
		}
		//buttons in first ring
//        fr1 = new JButton();fr2 = new JButton();fr3 = new JButton();fr4 = new JButton();

//		fr1.setBounds(180, 180, BUTTON_SIZE_X, BUTTON_SIZE_Y); fr2.setBounds(230, 195, BUTTON_SIZE_X, BUTTON_SIZE_Y); fr3.setBounds(240, 250, BUTTON_SIZE_X, BUTTON_SIZE_Y); fr4.setBounds(180, 280, BUTTON_SIZE_X, BUTTON_SIZE_Y); 
/*		fr1.setBorder(null);fr1.setBorderPainted(false);fr1.setContentAreaFilled(false);fr1.setOpaque(false);
        fr2.setBorder(null);fr2.setBorderPainted(false);fr2.setContentAreaFilled(false);fr2.setOpaque(false);
        fr3.setBorder(null);fr3.setBorderPainted(false);fr3.setContentAreaFilled(false);fr3.setOpaque(false);
        fr4.setBorder(null);fr4.setBorderPainted(false);fr4.setContentAreaFilled(false);fr4.setOpaque(false);
*/
		//buttons in second ring
//		sr1 = new JButton();sr2 = new JButton();sr3 = new JButton();sr4 = new JButton();sr5 = new JButton();sr6 = new JButton();sr7 = new JButton();sr8 = new JButton();		

/*		sr1.setBounds(155, 160, BUTTON_SIZE_X2, BUTTON_SIZE_Y2); 
		sr2.setBounds(190, 150, BUTTON_SIZE_X2, BUTTON_SIZE_Y2); 
		sr3.setBounds(235, 165, BUTTON_SIZE_X2, BUTTON_SIZE_Y2); 
		sr4.setBounds(270, 190, BUTTON_SIZE_X2, BUTTON_SIZE_Y2); 
		sr5.setBounds(285, 240, BUTTON_SIZE_X2, BUTTON_SIZE_Y2); 
		sr6.setBounds(270, 280, BUTTON_SIZE_X2, BUTTON_SIZE_Y2); 
		sr7.setBounds(200, 320, BUTTON_SIZE_X2, BUTTON_SIZE_Y2); 
		sr8.setBounds(160, 310, BUTTON_SIZE_X2, BUTTON_SIZE_Y2); 		
*/
/*		sr1.setBorder(null);sr1.setBorderPainted(false);sr1.setContentAreaFilled(false);sr1.setOpaque(false);
        sr2.setBorder(null);sr2.setBorderPainted(false);sr2.setContentAreaFilled(false);sr2.setOpaque(false);
        sr3.setBorder(null);sr3.setBorderPainted(false);sr3.setContentAreaFilled(false);sr3.setOpaque(false);
        sr4.setBorder(null);sr4.setBorderPainted(false);sr4.setContentAreaFilled(false);sr4.setOpaque(false);
		sr5.setBorder(null);sr5.setBorderPainted(false);sr5.setContentAreaFilled(false);sr5.setOpaque(false);
        sr6.setBorder(null);sr6.setBorderPainted(false);sr6.setContentAreaFilled(false);sr6.setOpaque(false);
        sr7.setBorder(null);sr7.setBorderPainted(false);sr7.setContentAreaFilled(false);sr7.setOpaque(false);
        sr8.setBorder(null);sr8.setBorderPainted(false);sr8.setContentAreaFilled(false);sr8.setOpaque(false);
*/
		//buttons in third ring
//        tr1 = new JButton();tr2 = new JButton();tr3 = new JButton();tr4 = new JButton();tr5 = new JButton();tr6 = new JButton();tr7 = new JButton();tr8 = new JButton();tr9 = new JButton();tr10 = new JButton();tr11 = new JButton();tr12 = new JButton();tr13 = new JButton();tr14 = new JButton();tr15 = new JButton();tr16 = new JButton();		
/*		tr1.setBounds(135, 135, BUTTON_SIZE_X2, BUTTON_SIZE_Y2); 
		tr2.setBounds(155, 125, BUTTON_SIZE_X2, BUTTON_SIZE_Y2); 
		tr3.setBounds(175, 120, BUTTON_SIZE_X2, BUTTON_SIZE_Y2); 
		tr4.setBounds(200, 115, BUTTON_SIZE_X2, BUTTON_SIZE_Y2); 
		tr5.setBounds(230, 125, BUTTON_SIZE_X2, BUTTON_SIZE_Y2); 
		tr6.setBounds(255, 135, BUTTON_SIZE_X2, BUTTON_SIZE_Y2); 
		tr7.setBounds(290, 160, BUTTON_SIZE_X2, BUTTON_SIZE_Y2); 
		tr8.setBounds(310, 185, BUTTON_SIZE_X2, BUTTON_SIZE_Y2); 
		tr9.setBounds(320, 220, BUTTON_SIZE_X2, BUTTON_SIZE_Y2); 
		tr10.setBounds(320, 250, BUTTON_SIZE_X2, BUTTON_SIZE_Y2); 
		tr11.setBounds(310, 280, BUTTON_SIZE_X2, BUTTON_SIZE_Y2); 
		tr12.setBounds(290, 310, BUTTON_SIZE_X2, BUTTON_SIZE_Y2); 
		tr13.setBounds(230, 350, BUTTON_SIZE_X2, BUTTON_SIZE_Y2); 
		tr14.setBounds(200, 355, BUTTON_SIZE_X2, BUTTON_SIZE_Y2); 
		tr15.setBounds(160, 350, BUTTON_SIZE_X2, BUTTON_SIZE_Y2); 
		tr16.setBounds(135, 340, BUTTON_SIZE_X2, BUTTON_SIZE_Y2); 				
*/
/*		tr1.setBorder(null);tr1.setBorderPainted(false);tr1.setContentAreaFilled(false);tr1.setOpaque(false);
		tr2.setBorder(null);tr2.setBorderPainted(false);tr2.setContentAreaFilled(false);tr2.setOpaque(false);
		tr3.setBorder(null);tr3.setBorderPainted(false);tr3.setContentAreaFilled(false);tr3.setOpaque(false);
		tr4.setBorder(null);tr4.setBorderPainted(false);tr4.setContentAreaFilled(false);tr4.setOpaque(false);
		tr5.setBorder(null);tr5.setBorderPainted(false);tr5.setContentAreaFilled(false);tr5.setOpaque(false);
		tr6.setBorder(null);tr6.setBorderPainted(false);tr6.setContentAreaFilled(false);tr6.setOpaque(false);
		tr7.setBorder(null);tr7.setBorderPainted(false);tr7.setContentAreaFilled(false);tr7.setOpaque(false);
		tr8.setBorder(null);tr8.setBorderPainted(false);tr8.setContentAreaFilled(false);tr8.setOpaque(false);
		tr9.setBorder(null);tr9.setBorderPainted(false);tr9.setContentAreaFilled(false);tr9.setOpaque(false);
		tr10.setBorder(null);tr10.setBorderPainted(false);tr10.setContentAreaFilled(false);tr10.setOpaque(false);
		tr11.setBorder(null);tr11.setBorderPainted(false);tr11.setContentAreaFilled(false);tr11.setOpaque(false);
		tr12.setBorder(null);tr12.setBorderPainted(false);tr12.setContentAreaFilled(false);tr12.setOpaque(false);
		tr13.setBorder(null);tr13.setBorderPainted(false);tr13.setContentAreaFilled(false);tr13.setOpaque(false);
		tr14.setBorder(null);tr14.setBorderPainted(false);tr14.setContentAreaFilled(false);tr14.setOpaque(false);
		tr15.setBorder(null);tr15.setBorderPainted(false);tr15.setContentAreaFilled(false);tr15.setOpaque(false);
		tr16.setBorder(null);tr16.setBorderPainted(false);tr16.setContentAreaFilled(false);tr16.setOpaque(false);
*/
		
		fullword();
		//JOptionPane.showMessageDialog(null, "My Goodness, this is so concise");
		generatelist();
	    ec1 = new JButton();
	    ec2 = new JButton();
		for(int l=0; l<28; l++ ){
			pan.add(predictbuttons[l]);
		}
/*	    pan.add(fr1);pan.add(fr2);pan.add(fr3);pan.add(fr4);pan.add(ec1);pan.add(ec2);
		pan.add(sr1);pan.add(sr2);pan.add(sr3);pan.add(sr4);pan.add(sr5);pan.add(sr6);pan.add(sr7);pan.add(sr8);
		pan.add(tr1);pan.add(tr2);pan.add(tr3);pan.add(tr4);pan.add(tr5);pan.add(tr6);pan.add(tr7);pan.add(tr8);pan.add(tr9);pan.add(tr10);pan.add(tr11);pan.add(tr12);pan.add(tr13);pan.add(tr14);pan.add(tr15);pan.add(tr16);
*/	
		pan.add(ec1);pan.add(ec2);
		pan.add(reset);pan.add(lab);
	//	fra.add(pan);
		//fra.add(pan, BorderLayout.CENTER);
		//fra.setContentPane(lab);
		pan.revalidate();
		pan.repaint();
		fra.revalidate();
		fra.repaint();
	} 
	
	public void button() {
		//buttons
		for(int i =0; i<26; i++){
			
			char temp = (char)('a'+i); 			
			letterbuttons[i] = new JButton(Character.toString(temp));
			char smallAlpha = (char)('a'+i); 
			String smallTemp = Character.toString(smallAlpha);
			char bigAlpha = (char)('A'+i); 
			String bigTemp = Character.toString(bigAlpha);
			
			letterbuttons[i].setBorder(null);
			letterbuttons[i].setBorderPainted(false);
			letterbuttons[i].setContentAreaFilled(false);
			letterbuttons[i].setOpaque(false);
				
			
		//    BUTTON_SIZE_X = 38;      // size height
		//	BUTTON_SIZE_Y = 38;       // size width

			degreeBetween = 9.5;
			radianBetween = Math.toRadians(degreeBetween);
			midX = (int)((fra.getWidth()-100)/2.8+50);
			midY = (int) ((fra.getWidth()-100)/2.8*1.5+50); 
			radius = (int)((fra.getWidth()-100)/2.8*1.5);
			
			xValue = (int)(radius*(Math.sin(radianBetween*(i-3))));
			yValue = (int)(Math.sqrt(Math.pow(radius,2)-Math.pow(xValue, 2)));
			if(i<13){
				yValue = -1*yValue;
			}	
			xValue +=midX;
			yValue +=midY; 						
			letterbuttons[i].setBounds(xValue, yValue, BUTTON_SIZE_X, BUTTON_SIZE_Y);
			letterbuttons[i].setFont(new Font("Arial", Font.PLAIN, 30));	
			
			final Integer pp = new Integer(i);

			letterbuttons[i].addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent e1){
					if ((shiftclick == 0) && ((capsclick % 2) == 0)){
						txt.setText(txt.getText() + smallTemp);
						String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
						tp1.setText(contenttp1 + smallTemp);
						error = smallTemp;
						content = content + smallTemp;
						} else {
						txt.setText(txt.getText() + bigTemp);
						String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
						tp1.setText(contenttp1 + bigTemp);
						error = bigTemp;
						content = content + bigTemp;
						}
		//		write();
				phase1buttons[7].setBorder(null);
				phase1buttons[7].setBorderPainted(false);

				
		/*		ecposition1x = 230;
				ecposition1y = 15;
				ecposition2x = 70;
				ecposition2y = 50;
				errorcorrection();
				ec1.setBorder(null);
		        ec1.setBorderPainted(false);
		        ec1.setContentAreaFilled(false);
		        ec1.setOpaque(false); */
				errorcorrection();
				int temp2; String temp = null;
	        	if ((shiftclick == 0) && ((capsclick % 2) == 0)){
	        		temp = smallTemp;
		        	temp2 = (int)temp.charAt(0);
	        		//int temp2; String temp;
	        		try {
		        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);
		        	System.out.println("temptemp" +keyCode);

		            Robot robot = new Robot();
		            // Creates the delay of 5 sec so that you can open notepad before
		            // Robot start writting
		            //robot.delay(5000);
		            robot.keyPress(keyCode);
		        	} catch (AWTException e) {
		        		e.printStackTrace();
		        	}	
	        	} else {
	        		temp = bigTemp;
		        	temp2 = (int)temp.charAt(0);
	        		try {
			        	//int temp2; String temp;
						
			        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);
			        	System.out.println("temptemp" +keyCode);

			            Robot robot = new Robot();
			            // Creates the delay of 5 sec so that you can open notepad before
			            // Robot start writting
			            //robot.delay(5000);
			            robot.keyPress(KeyEvent.VK_CAPS_LOCK);
			            robot.keyRelease(KeyEvent.VK_CAPS_LOCK);
			            robot.keyPress(keyCode);
			            robot.keyPress(KeyEvent.VK_CAPS_LOCK);
			            robot.keyRelease(KeyEvent.VK_CAPS_LOCK);
			        	} catch (AWTException e) {
			        		e.printStackTrace();
			        	}
	        	}
				
//	            txt.requestFocusInWindow();
				shiftclick = 0;
	            generatelist();
	            fullword();
				}
				}); 

			final Integer o = new Integer(i);
			letterbuttons[o].addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(MouseEvent e) {
			        // saySomething("Mouse entered", e);
			    	letterbuttons[o].setBackground(Color.LIGHT_GRAY);
			    	letterbuttons[o].setContentAreaFilled(true);
			     	
			      }
			     public void mouseExited(MouseEvent e) {
			    	letterbuttons[o].setBackground(null);
			    	letterbuttons[o].setContentAreaFilled(false);
			     }
			});			
		}
	


/*		b1 = new JButton("a");
		//b1.setFocusable(false);
		b2 = new JButton("b");
		//b2.setFocusable(false);
		b3 = new JButton("c");
		b4 = new JButton("d");
		b5 = new JButton("e");
		b6 = new JButton("f");
		b7 = new JButton("g");
		b8 = new JButton("h");
		b9 = new JButton("i");
		b10 = new JButton("j");
		b11 = new JButton("k");
		b12 = new JButton("l");
		b13 = new JButton("m");
		b14 = new JButton("n");
		b15 = new JButton("o");
		b16 = new JButton("p");
		b17 = new JButton("q");
		b18 = new JButton("r");
		b19 = new JButton("s");
		b20 = new JButton("t");
		b21 = new JButton("u");
		b22 = new JButton("v");
		b23 = new JButton("w");
		b24 = new JButton("x");
		b25 = new JButton("y");
		b26 = new JButton("z");  */
		
		//button location
		pan.setLayout(null);
		//final int blx = 30;  // location x 
		//final int bly = 30;   // location y 
//	    BUTTON_SIZE_X = 38;      // size height
//		BUTTON_SIZE_Y = 38;       // size width
//		relafrasizeX = (int) MouseInfo.getPointerInfo().getLocation().getX();
//		relafrasizeY = (int) MouseInfo.getPointerInfo().getLocation().getY();	
//		xaxis = (float) (fra.getWidth()/600.0); yaxis = (float) (fra.getHeight()/600.0);
//		BUTTON_SIZE_X = (int) Math.round(38*xaxis); BUTTON_SIZE_Y = (int) Math.round(38*yaxis);

/*		b1.setBounds(fra.getWidth()/20+40, fra.getHeight()/12+30, BUTTON_SIZE_X,BUTTON_SIZE_Y); b1.setFont(new Font("Arial", Font.PLAIN, 30));
		b2.setBounds(fra.getWidth()/9+40, fra.getHeight()/19+30, BUTTON_SIZE_X, BUTTON_SIZE_Y); b2.setFont(new Font("Arial", Font.PLAIN, 30));
		b3.setBounds(fra.getWidth()/6+42, fra.getHeight()/28+25, BUTTON_SIZE_X, BUTTON_SIZE_Y); b3.setFont(new Font("Arial", Font.PLAIN, 30));
		b4.setBounds(fra.getWidth()*3/13+40, fra.getHeight()/36+25, BUTTON_SIZE_X, BUTTON_SIZE_Y); b4.setFont(new Font("Arial", Font.PLAIN, 30));
		b5.setBounds(fra.getWidth()*3/10+35, fra.getHeight()/40+25, BUTTON_SIZE_X, BUTTON_SIZE_Y); b5.setFont(new Font("Arial", Font.PLAIN, 30));
		b6.setBounds(fra.getWidth()*5/14+40, fra.getHeight()/40+30, BUTTON_SIZE_X, BUTTON_SIZE_Y); b6.setFont(new Font("Arial", Font.PLAIN, 30));
		b7.setBounds(fra.getWidth()*5/12+40, fra.getHeight()/26+30, BUTTON_SIZE_X, BUTTON_SIZE_Y); b7.setFont(new Font("Arial", Font.PLAIN, 30));
		b8.setBounds(fra.getWidth()/2+28, fra.getHeight()/15+28, BUTTON_SIZE_X, BUTTON_SIZE_Y); b8.setFont(new Font("Arial", Font.PLAIN, 30));
		b9.setBounds(fra.getWidth()*5/9+30, fra.getHeight()/12+45, BUTTON_SIZE_X,BUTTON_SIZE_Y); b9.setFont(new Font("Arial", Font.PLAIN, 30));
		b10.setBounds(fra.getWidth()*5/8+25, fra.getHeight()/9+55, BUTTON_SIZE_X, BUTTON_SIZE_Y); b10.setFont(new Font("Arial", Font.PLAIN, 30));
		b11.setBounds(fra.getWidth()*3/4-20, fra.getHeight()*3/14+30, BUTTON_SIZE_X, BUTTON_SIZE_Y);b11.setFont(new Font("Arial", Font.PLAIN, 30));
		b12.setBounds(fra.getWidth()*4/5-35, fra.getHeight()/4+40, BUTTON_SIZE_X, BUTTON_SIZE_Y); b12.setFont(new Font("Arial", Font.PLAIN, 30));
		b13.setBounds(fra.getWidth()*4/5-27, fra.getHeight()/3+30, BUTTON_SIZE_X, BUTTON_SIZE_Y); 	b13.setFont(new Font("Arial", Font.PLAIN, 30));	
		b14.setBounds(fra.getWidth()*4/5-27, (fra.getHeight()-140)*5/9+20, BUTTON_SIZE_X, BUTTON_SIZE_Y); b14.setFont(new Font("Arial", Font.PLAIN, 30));
		b15.setBounds(fra.getWidth()*4/5-35, (fra.getHeight()-140)*2/3+10, BUTTON_SIZE_X, BUTTON_SIZE_Y); b15.setFont(new Font("Arial", Font.PLAIN, 30));
		b16.setBounds(fra.getWidth()*3/4-25, (fra.getHeight()-140)*7/9-5, BUTTON_SIZE_X, BUTTON_SIZE_Y); b16.setFont(new Font("Arial", Font.PLAIN, 30));
		b17.setBounds(fra.getWidth()*5/8+15, (fra.getHeight()-140)*13/16+5, BUTTON_SIZE_X, BUTTON_SIZE_Y); b17.setFont(new Font("Arial", Font.PLAIN, 30));
		b18.setBounds(fra.getWidth()*5/9+25, (fra.getHeight()-140)*8/9, BUTTON_SIZE_X, BUTTON_SIZE_Y); 	b18.setFont(new Font("Arial", Font.PLAIN, 30));			
		b19.setBounds(fra.getWidth()/2+28, (fra.getHeight()-140)*17/18-10, BUTTON_SIZE_X, BUTTON_SIZE_Y); 	b19.setFont(new Font("Arial", Font.PLAIN, 30));			
		b20.setBounds(fra.getWidth()*5/12+40, (fra.getHeight()-140)*19/20, BUTTON_SIZE_X, BUTTON_SIZE_Y); b20.setFont(new Font("Arial", Font.PLAIN, 30));			
		b21.setBounds(fra.getWidth()*5/14+38, (fra.getHeight()-140)*20/21+5, BUTTON_SIZE_X, BUTTON_SIZE_Y); b21.setFont(new Font("Arial", Font.PLAIN, 30));		
		b22.setBounds(fra.getWidth()*3/10+35,(fra.getHeight()-140)*22/23+5, BUTTON_SIZE_X, BUTTON_SIZE_Y); b22.setFont(new Font("Arial", Font.PLAIN, 30));
		b23.setBounds(fra.getWidth()*3/13+40, (fra.getHeight()-140)*20/21+5, BUTTON_SIZE_X, BUTTON_SIZE_Y); b23.setFont(new Font("Arial", Font.PLAIN, 30));
		b24.setBounds(fra.getWidth()/6+42, (fra.getHeight()-140)*17/18+5, BUTTON_SIZE_X, BUTTON_SIZE_Y); b24.setFont(new Font("Arial", Font.PLAIN, 30));				
		b25.setBounds(fra.getWidth()/9+40, (fra.getHeight()-140)*13/14, BUTTON_SIZE_X, BUTTON_SIZE_Y); b25.setFont(new Font("Arial", Font.PLAIN, 30));
		b26.setBounds(fra.getWidth()/20+40, (fra.getHeight()-140)*8/9-5, BUTTON_SIZE_X, BUTTON_SIZE_Y); b26.setFont(new Font("Arial", Font.PLAIN, 30));
*/		
		//hidden buttons
/*		b1.setBorder(null);
        b1.setBorderPainted(false);
        b1.setContentAreaFilled(false);
        b1.setOpaque(false); 
		b2.setBorder(null);
        b2.setBorderPainted(false);
        b2.setContentAreaFilled(false);
        b2.setOpaque(false); 
		b3.setBorder(null);
        b3.setBorderPainted(false);
        b3.setContentAreaFilled(false);
        b3.setOpaque(false);
		b4.setBorder(null);
        b4.setBorderPainted(false);
        b4.setContentAreaFilled(false);
        b4.setOpaque(false);
		b5.setBorder(null);
        b5.setBorderPainted(false);
        b5.setContentAreaFilled(false);
        b5.setOpaque(false);
		b6.setBorder(null);
        b6.setBorderPainted(false);
        b6.setContentAreaFilled(false);
        b6.setOpaque(false);
		b7.setBorder(null);
        b7.setBorderPainted(false);
        b7.setContentAreaFilled(false);
        b7.setOpaque(false);
		b8.setBorder(null);
        b8.setBorderPainted(false);
        b8.setContentAreaFilled(false);
        b8.setOpaque(false);
		b9.setBorder(null);
        b9.setBorderPainted(false);
        b9.setContentAreaFilled(false);
        b9.setOpaque(false);
		b10.setBorder(null);
        b10.setBorderPainted(false);
        b10.setContentAreaFilled(false);
        b10.setOpaque(false);
		b11.setBorder(null);
        b11.setBorderPainted(false);
        b11.setContentAreaFilled(false);
        b11.setOpaque(false);
		b12.setBorder(null);
        b12.setBorderPainted(false);
        b12.setContentAreaFilled(false);
        b12.setOpaque(false);
		b13.setBorder(null);
        b13.setBorderPainted(false);
        b13.setContentAreaFilled(false);
        b13.setOpaque(false);
		b14.setBorder(null);
        b14.setBorderPainted(false);
        b14.setContentAreaFilled(false);
        b14.setOpaque(false);
		b15.setBorder(null);
        b15.setBorderPainted(false);
        b15.setContentAreaFilled(false);
        b15.setOpaque(false);
		b16.setBorder(null);
        b16.setBorderPainted(false);
        b16.setContentAreaFilled(false);
        b16.setOpaque(false);
		b17.setBorder(null);
        b17.setBorderPainted(false);
        b17.setContentAreaFilled(false);
        b17.setOpaque(false);
		b18.setBorder(null);
        b18.setBorderPainted(false);
        b18.setContentAreaFilled(false);
        b18.setOpaque(false);		
        b19.setBorder(null);
        b19.setBorderPainted(false);
        b19.setContentAreaFilled(false);
        b19.setOpaque(false);		
        b20.setBorder(null);
        b20.setBorderPainted(false);
        b20.setContentAreaFilled(false);
        b20.setOpaque(false);		
        b21.setBorder(null);
        b21.setBorderPainted(false);
        b21.setContentAreaFilled(false);
        b21.setOpaque(false);		
        b22.setBorder(null);
        b22.setBorderPainted(false);
        b22.setContentAreaFilled(false);
        b22.setOpaque(false);		
        b23.setBorder(null);
        b23.setBorderPainted(false);
        b23.setContentAreaFilled(false);
        b23.setOpaque(false);		
        b24.setBorder(null);
        b24.setBorderPainted(false);
        b24.setContentAreaFilled(false);
        b24.setOpaque(false);
		b25.setBorder(null);
        b25.setBorderPainted(false);
        b25.setContentAreaFilled(false);
        b25.setOpaque(false);
		b26.setBorder(null);
        b26.setBorderPainted(false);
        b26.setContentAreaFilled(false);
        b26.setOpaque(false);  	*/
		
/*		b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e1){
				if ((shiftclick == 0) && ((capsclick % 2) == 0)){
					txt.setText(txt.getText() + "a");
					String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
					tp1.setText(contenttp1 + "a");
					content = content + "a";
					} else {
					txt.setText(txt.getText() + "A");
					String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
					tp1.setText(contenttp1 + "A");
					content = content + "A";}
			write();
			shiftclick = 0;
			shift.setBorder(null);
			shift.setBorderPainted(false);
			error = "a";
			ecposition2x = 70;
			ecposition2y = 50;
			errorcorrection();
			ec1.setBorder(null);
	        ec1.setBorderPainted(false);
	        ec1.setContentAreaFilled(false);
	        ec1.setOpaque(false);
            sbf.append(file);
            txt.requestFocusInWindow();
            if((click % 2) == 1){
            generatelist();
            fullword();}
			}
			}); 
		b1.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		        // saySomething("Mouse entered", e);
		     	b1.setBackground(Color.LIGHT_GRAY);
		     	b1.setContentAreaFilled(true);
		     	
		      }
		     public void mouseExited(MouseEvent e) {
		     	b1.setBackground(null);
		     	b1.setContentAreaFilled(false);
		     }
		 	
		});
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e2){
				if((shiftclick == 0) && ((capsclick % 2) == 0)){
				String actionCommand = e2.getActionCommand();
				txt.setText(txt.getText() + actionCommand);
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + actionCommand);
				content = content + "b";}else{
				txt.setText(txt.getText() + "B");
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + "B");
				content = content + "B";	
				}
			write();
			shiftclick = 0;
			shift.setBorder(null);
			shift.setBorderPainted(false);
			error = "b";
			ecposition1x = 90;
			ecposition1y = 35;
			ecposition2x = 120;
			ecposition2y = 25;
			errorcorrection();
			generatelist();
	        txt.requestFocusInWindow();
	        fullword();
			}			
			});
		b3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e3){
				if((shiftclick == 0) && ((capsclick % 2) == 0)){
				String actionCommand = e3.getActionCommand();
				txt.setText(txt.getText() + actionCommand);
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + actionCommand);
				content = content + "c";}else{
				txt.setText(txt.getText() + "C");
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + "C");
				content = content + "C";
				}
			write();
			shiftclick = 0;
			shift.setBorder(null);
			shift.setBorderPainted(false);
			error = "c";
			ecposition1x = 120;
			ecposition1y = 25;
			ecposition2x = 150;
			ecposition2y = 15;
			errorcorrection();
			generatelist();
			txt.requestFocusInWindow();
			fullword();
			}
			});
		b4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e4){
				if((shiftclick == 0) && ((capsclick % 2) == 0)){
				String actionCommand = e4.getActionCommand();
				txt.setText(txt.getText() + actionCommand);
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + actionCommand);
				content = content + "d";}else{
				txt.setText(txt.getText() + "D");
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + "D");
				content = content + "D";
				}
			write();
			shiftclick = 0;
			shift.setBorder(null);
			shift.setBorderPainted(false);
			error = "d";
			ecposition1x = 155;
			ecposition1y = 15;
			ecposition2x = 185;
			ecposition2y = 10;
			errorcorrection();
			generatelist();
			txt.requestFocusInWindow();
			fullword();
			}			
			});
		b5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e5){
				if((shiftclick == 0) && ((capsclick % 2) == 0)){
				String actionCommand = e5.getActionCommand();
				txt.setText(txt.getText() + actionCommand);
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + actionCommand);
				content = content + "e";}else{
				txt.setText(txt.getText() + "E");
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + "E");
				content = content + "E";
				}
			write();
			shiftclick = 0;
			shift.setBorder(null);
			shift.setBorderPainted(false);
			error = "e";
			ecposition1x = 190;
			ecposition1y = 10;
			ecposition2x = 220;
			ecposition2y = 10;
			errorcorrection();
			generatelist();
			txt.requestFocusInWindow();
			fullword();
			}
			});
		b6.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e6){
			if((shiftclick == 0) && ((capsclick % 2) == 0)){
			String actionCommand = e6.getActionCommand();
			txt.setText(txt.getText() + actionCommand);
			String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
			tp1.setText(contenttp1 + actionCommand);
			System.out.println(tp1.getText());
			content = content + "f";}else{
			txt.setText(txt.getText() + "F");
			String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
			tp1.setText(contenttp1 + "F");
			content = content + "F";	
			}
			write();
			shiftclick = 0;
			shift.setBorder(null);
			shift.setBorderPainted(false);
			error = "f";
			ecposition1x = 230;
			ecposition1y = 15;
			ecposition2x = 260;
			ecposition2y = 20;
			errorcorrection();
			generatelist();
			txt.requestFocusInWindow();
			fullword();
			}			
			});
		b7.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e7){
				if((shiftclick == 0) && ((capsclick % 2) == 0)){
				String actionCommand = e7.getActionCommand();
				txt.setText(txt.getText() + actionCommand);
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + actionCommand);
				content = content + "g";}else{
				txt.setText(txt.getText() + "G");
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + "G");
				content = content + "G";				
				}
			write();
			shiftclick = 0;
			shift.setBorder(null);
			shift.setBorderPainted(false);
			error = "g";
			ecposition1x = 270;
			ecposition1y = 25;
			ecposition2x = 300;
			ecposition2y = 35;
			errorcorrection();
			generatelist();
			txt.requestFocusInWindow();
			fullword();
			}
			});
		b8.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e8){
				if((shiftclick == 0) && ((capsclick % 2) == 0)){
				String actionCommand = e8.getActionCommand();
				txt.setText(txt.getText() + actionCommand);
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + actionCommand);
				content = content + "h";}else{
				txt.setText(txt.getText() + "H");
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + "H");
				content = content + "H";	
				}
			write();
			shiftclick = 0;
			shift.setBorder(null);
			shift.setBorderPainted(false);
			error = "h";
			ecposition1x = 305;
			ecposition1y = 35;
			ecposition2x = 335;
			ecposition2y = 50;
			errorcorrection();
			generatelist();
			txt.requestFocusInWindow();
			fullword();
			}			
			});
		b9.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e9){
				if((shiftclick == 0) && ((capsclick % 2) == 0)){
				String actionCommand = e9.getActionCommand();
				txt.setText(txt.getText() + actionCommand);
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + actionCommand);
				content = content + "i";}else{
				txt.setText(txt.getText() + "I");
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + "I");
				content = content + "I";
				}
			write();
			shiftclick = 0;
			shift.setBorder(null);
			shift.setBorderPainted(false);
			error = "i";
			ecposition1x = 340;
			ecposition1y = 55;
			ecposition2x = 370;
			ecposition2y = 75;
			errorcorrection();
			generatelist();
			txt.requestFocusInWindow();
			fullword();
			}
			});
		b10.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e10){
				if((shiftclick == 0) && ((capsclick % 2) == 0)){
				String actionCommand = e10.getActionCommand();
				txt.setText(txt.getText() + actionCommand);
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + actionCommand);
				content = content + "j";}else{
				txt.setText(txt.getText() + "J");
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + "J");
				content = content + "J";	
				}
			write();
			shiftclick = 0;
			shift.setBorder(null);
			shift.setBorderPainted(false);
			error = "j";
			ecposition1x = 365;
			ecposition1y = 80;
			ecposition2x = 395;
			ecposition2y = 105;
			errorcorrection();
			generatelist();
			txt.requestFocusInWindow();
			fullword();
			}			
			});
		b11.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e11){
				if((shiftclick == 0) && ((capsclick % 2) == 0)){
				String actionCommand = e11.getActionCommand();
				txt.setText(txt.getText() + actionCommand);
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + actionCommand);
				content = content + "k";}else{
				txt.setText(txt.getText() + "K");
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + "K");
				content = content + "K";
				}
			write();
			shiftclick = 0;
			shift.setBorder(null);
			shift.setBorderPainted(false);
			error = "k";
			ecposition1x = 390;
			ecposition1y = 120;
			ecposition2x = 410;
			ecposition2y = 150;
			errorcorrection();
			generatelist();
			txt.requestFocusInWindow();
			fullword();
			}
			});
		b12.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e12){
				if((shiftclick == 0) && ((capsclick % 2) == 0)){
				String actionCommand = e12.getActionCommand();
				txt.setText(txt.getText() + actionCommand);
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + actionCommand);
				content = content + "l";}else{
				txt.setText(txt.getText() + "L");
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + "L");
				content = content + "L";
				}
			write();
			shiftclick = 0;
			shift.setBorder(null);
			shift.setBorderPainted(false);
			error = "l";
			ecposition1x = 410;
			ecposition1y = 150;
			ecposition2x = 425;
			ecposition2y = 180;
			errorcorrection();
			generatelist();
			txt.requestFocusInWindow();
			fullword();
			}			
			});
		b13.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e13){
				if((shiftclick == 0) && ((capsclick % 2) == 0)){
				String actionCommand = e13.getActionCommand();
				txt.setText(txt.getText() + actionCommand);
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + actionCommand);
				content = content + "m";}else{
				txt.setText(txt.getText() + "M");
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + "M");
				content = content + "M";	
				}
			write();
			shiftclick = 0;
			shift.setBorder(null);
			shift.setBorderPainted(false);
			error = "m";
			ecposition1x = 425;
			ecposition1y = 195;
			ecposition2x = 425;
			ecposition2y = 225;
			errorcorrection();
			generatelist();
			txt.requestFocusInWindow();
			fullword();
			}
			});
		b14.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e14){
				if((shiftclick == 0) && ((capsclick % 2) == 0)){
				String actionCommand = e14.getActionCommand();
				txt.setText(txt.getText() + actionCommand);
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + actionCommand);
				content = content + "n";}else{
				txt.setText(txt.getText() + "N");
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + "N");
				content = content + "N";
				}
			write();
			shiftclick = 0;
			shift.setBorder(null);
			shift.setBorderPainted(false);
			error = "n";
			ecposition1x = 425;
			ecposition1y = 230;
			ecposition2x = 425;
			ecposition2y = 260;
			errorcorrection();
			generatelist();
			txt.requestFocusInWindow();
			fullword();
			}			
			});
		b15.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e15){
				if((shiftclick == 0) && ((capsclick % 2) == 0)){
				String actionCommand = e15.getActionCommand();
				txt.setText(txt.getText() + actionCommand);
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + actionCommand);
				content = content + "o";}else{
				txt.setText(txt.getText() + "O");
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + "O");
				content = content + "O";
				}
			write();
			shiftclick = 0;
			shift.setBorder(null);
			shift.setBorderPainted(false);
			error = "o";
			ecposition1x = 420;
			ecposition1y = 265;
			ecposition2x = 415;
			ecposition2y = 295;
			errorcorrection();
			generatelist();
			txt.requestFocusInWindow();
			fullword();
			}
			});
		b16.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e16){
				if((shiftclick == 0) && ((capsclick % 2) == 0)){
				String actionCommand = e16.getActionCommand();
				txt.setText(txt.getText() + actionCommand);
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + actionCommand);
				content = content + "p";}else{
				txt.setText(txt.getText() + "P");
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + "P");
				content = content + "P";
				}
			write();
			shiftclick = 0;
			shift.setBorder(null);
			shift.setBorderPainted(false);
			error = "p";
			ecposition1x = 410;
			ecposition1y = 305;
			ecposition2x = 400;
			ecposition2y = 335;
			errorcorrection();
			generatelist();
			txt.requestFocusInWindow();
			fullword();
			}			
			});
		b17.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e17){
				if((shiftclick == 0) && ((capsclick % 2) == 0)){
				String actionCommand = e17.getActionCommand();					
				txt.setText(txt.getText() + actionCommand);
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + actionCommand);
				content = content + "q";}else{
				txt.setText(txt.getText() + "Q");
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + "Q");
				content = content + "Q";	
				}
			write();
			shiftclick = 0;
			shift.setBorder(null);
			shift.setBorderPainted(false);
			error = "q";
			ecposition1x = 395;
			ecposition1y = 345;
			ecposition2x = 375;
			ecposition2y = 375;
			errorcorrection();
			generatelist();
			txt.requestFocusInWindow();
			fullword();
			}
			});
		b18.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e18){
				if((shiftclick == 0) && ((capsclick % 2) == 0)){
				String actionCommand = e18.getActionCommand();
				txt.setText(txt.getText() + actionCommand);
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + actionCommand);
				content = content + "r";}else{
				txt.setText(txt.getText() + "R");
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + "R");
				content = content + "R";
				}
			write();
			shiftclick = 0;
			shift.setBorder(null);
			shift.setBorderPainted(false);
			error = "r";
			ecposition1x = 370;
			ecposition1y = 380;
			ecposition2x = 350;
			ecposition2y = 410;
			errorcorrection();
			generatelist();
			txt.requestFocusInWindow();
			fullword();
			}			
			});
		b19.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e19){
				if((shiftclick == 0) && ((capsclick % 2) == 0)){
				String actionCommand = e19.getActionCommand();
				txt.setText(txt.getText() + actionCommand);
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + actionCommand);
				content = content + "s";}else{
				txt.setText(txt.getText() + "S");
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + "S");
				content = content + "S";
				}
			write();
			shiftclick = 0;
			shift.setBorder(null);
			shift.setBorderPainted(false);
			error = "s";
			ecposition1x = 340;
			ecposition1y = 415;
			ecposition2x = 310;
			ecposition2y = 435;
			errorcorrection();
			generatelist();
			txt.requestFocusInWindow();
			fullword();
			}
			});
		b20.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e20){
				if((shiftclick == 0) && ((capsclick % 2) == 0)){
				String actionCommand = e20.getActionCommand();
				txt.setText(txt.getText() + actionCommand);
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + actionCommand);
				content = content + "t";}else{
				txt.setText(txt.getText() + "T");
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + "T");
				content = content + "T";
				}
			write();
			shiftclick = 0;
			shift.setBorder(null);
			shift.setBorderPainted(false);
			error = "t";
			ecposition1x = 295;
			ecposition1y = 440;
			ecposition2x = 265;
			ecposition2y = 450;
			errorcorrection();
			generatelist();
			txt.requestFocusInWindow();
			fullword();
			}			
			});
		b21.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e21){
				if((shiftclick == 0) && ((capsclick % 2) == 0)){
				String actionCommand = e21.getActionCommand();
				txt.setText(txt.getText() + actionCommand);
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + actionCommand);
				content = content + "u";}else{
				txt.setText(txt.getText() + "U");
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + "U");
				content = content + "U";			
				}
			write();
			shiftclick = 0;
			shift.setBorder(null);
			shift.setBorderPainted(false);
			error = "u";
			ecposition1x = 265;
			ecposition1y = 450;
			ecposition2x = 235;
			ecposition2y = 455;
			errorcorrection();
			generatelist();
			txt.requestFocusInWindow();
			fullword();
			}
			});
		b22.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e22){
				if((shiftclick == 0) && ((capsclick % 2) == 0)){
				String actionCommand = e22.getActionCommand();
				txt.setText(txt.getText() + actionCommand);
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + actionCommand);
				content = content + "v";}else{
				txt.setText(txt.getText() + "V");
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + "V");
				content = content + "V";
				}
			write();
			shiftclick = 0;
			shift.setBorder(null);
			shift.setBorderPainted(false);
			error = "v";
			ecposition1x = 230;
			ecposition1y = 460;
			ecposition2x = 200;
			ecposition2y = 465;
			errorcorrection();
			generatelist();
			txt.requestFocusInWindow();
			fullword();
			}			
			});
		b23.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e23){
				if((shiftclick == 0) && ((capsclick % 2) == 0)){
				String actionCommand = e23.getActionCommand();
				txt.setText(txt.getText() + actionCommand);
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + actionCommand);
				content = content + "w";}else{
				txt.setText(txt.getText() + "W");
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + "W");
				content = content + "W";
				}
			write();
			shiftclick = 0;
			shift.setBorder(null);
			shift.setBorderPainted(false);
			error = "w";
			ecposition1x = 195;
			ecposition1y = 460;
			ecposition2x = 165;
			ecposition2y = 460;
			errorcorrection();
			generatelist();
			txt.requestFocusInWindow();
			fullword();
			}
			});
		b24.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e24){
				if((shiftclick == 0) && ((capsclick % 2) == 0)){
				String actionCommand = e24.getActionCommand();
				txt.setText(txt.getText() + actionCommand);
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + actionCommand);
				content = content + "x";}else{
				txt.setText(txt.getText() + "X");
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + "X");
				content = content + "X";
				}
			write();
			shiftclick = 0;
			shift.setBorder(null);
			shift.setBorderPainted(false);
			error = "x";
			ecposition1x = 160;
			ecposition1y = 455;
			ecposition2x = 130;
			ecposition2y = 450;
			errorcorrection();
			generatelist();
			txt.requestFocusInWindow();
			fullword();
			}			
			});
		b25.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e25){
				if((shiftclick == 0) && ((capsclick % 2) == 0)){
				String actionCommand = e25.getActionCommand();
				txt.setText(txt.getText() + actionCommand);
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + actionCommand);
				content = content + "y";}else{
				txt.setText(txt.getText() + "Y");
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + "Y");
				content = content + "Y";
				}
			write();
			shiftclick = 0;
			shift.setBorder(null);
			shift.setBorderPainted(false);
			error = "y";
			ecposition1x = 130;
			ecposition1y = 450;
			ecposition2x = 100;
			ecposition2y = 440;
			errorcorrection();
			generatelist();
			txt.requestFocusInWindow();
			fullword();
			}
			});
		b26.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e26){
				if((shiftclick == 0) && ((capsclick % 2) == 0)){
				String actionCommand = e26.getActionCommand();
				txt.setText(txt.getText() + actionCommand);
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + actionCommand);
				content = content + "z";}else{
				txt.setText(txt.getText() + "Z");
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + "Z");
				content = content + "Z";
				}
			write();
			shiftclick = 0;
			shift.setBorder(null);
			shift.setBorderPainted(false);
			error = "z";
			ecposition1x = 75;
			ecposition1y = 430;
			errorcorrection();
			ec2.setBorder(null);
	        ec2.setBorderPainted(false);
	        ec2.setContentAreaFilled(false);
	        ec2.setOpaque(false);
			generatelist();
			txt.requestFocusInWindow();
			fullword();
			}			
			}); */
		ratio1 = (double)(fra.getWidth()/450.0);
		
		space = new JButton();
//		spaceicon = new ImageIcon("C:\\Users\\djing\\Desktop\\jde\\space.png");
		java.net.URL spacei = P3.class.getResource("space.png");
		spaceicon = new ImageIcon(spacei);

		ispace = spaceicon.getImage();
		spacewidth = ispace.getWidth(null); spaceheight = ispace.getHeight(null);
		ispace = ispace.getScaledInstance((int)(spacewidth/5*ratio1), (int)(spaceheight/4*ratio1), Image.SCALE_SMOOTH);
		spaceicon.setImage(ispace);
		space.setIcon(spaceicon);
		space.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent spa){
			//String actionCommand = e26.getActionCommand();
			txt.setText(txt.getText() + " ");
			String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
			tp1.setText(contenttp1 + " ");
			content = content + " ";
			 try {
		        	String temp = " ";
		        	int temp2 = (int)temp.charAt(0);
		        	
		        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);
		             
		            Robot robot = new Robot();
		            // Creates the delay of 5 sec so that you can open notepad before
		            // Robot start writting
		            //robot.delay(5000);
		            robot.keyPress(keyCode);       
		        	} catch (AWTException e) {
		        		e.printStackTrace();
		        	}
		//	write();
			txt.requestFocusInWindow();
			}			
			});
		space.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		        // saySomething("Mouse entered", e);
		    	space.setBackground(Color.LIGHT_GRAY);
		    	space.setContentAreaFilled(true);
		     	
		    }
		    public void mouseExited(MouseEvent e) {
		    	space.setBackground(null);
		    	space.setContentAreaFilled(false);
		    }
		});
		midX = (int)((fra.getWidth()-100)/2.8+50);
		midY = (int) ((fra.getWidth()-100)/2.8*1.6+45); 
	//	radius = (int)((fra.getWidth()-150)/2.8*1.2);

		space.setBounds((int)(midX-60*ratio1),(int)(midY-10*ratio1), (int)(50*ratio1), (int)(40*ratio1)); 
		space.setHorizontalAlignment(SwingConstants.CENTER);
		space.setBorder(null);space.setBorderPainted(false); space.setContentAreaFilled(false); space.setOpaque(false);
        
	//	shifticon = new ImageIcon("C:\\Users\\djing\\Desktop\\jde\\shift.png");
		java.net.URL shifti = P3.class.getResource("shift.png");
		shifticon = new ImageIcon(shifti);
		ishift = shifticon.getImage();	
		shiftwidth = ishift.getWidth(null);shiftheight = ishift.getHeight(null);
		ishift = ishift.getScaledInstance((int)(shiftwidth/15*ratio1), (int)(shiftheight/14*ratio1), Image.SCALE_SMOOTH);
		shifticon.setImage(ishift);
	//	shift = new JButton(shifticon);
	//	capsicon = new ImageIcon("C:\\Users\\djing\\Desktop\\jde\\caps.png");
		java.net.URL capsi = P3.class.getResource("caps.png");
		capsicon = new ImageIcon(capsi);
		icaps = capsicon.getImage();
		capswidth = icaps.getWidth(null); capsheight = icaps.getHeight(null);
		icaps = icaps.getScaledInstance((int)(capswidth/4*ratio1), (int)(capsheight/4*ratio1), Image.SCALE_SMOOTH);
		capsicon.setImage(icaps);
	//	caps = new JButton(capsicon);
		phase1buttons[0] = new JButton(",");phase1buttons[1] = new JButton(".");
		phase1buttons[2] = new JButton(";");phase1buttons[3] = new JButton(":");
		phase1buttons[4] = new JButton("?");phase1buttons[5] = new JButton("!");
		phase1buttons[6] = new JButton("@");phase1buttons[7] = new JButton(shifticon);
		phase1buttons[8] = new JButton(capsicon);phase1buttons[9] = new JButton("$");
		phase1buttons[10] = new JButton("#");phase1buttons[11] = new JButton("&");
		phase1buttons[12] = new JButton("\"");phase1buttons[13] = new JButton("'");
		phase1buttons[14] = new JButton(")");phase1buttons[15] = new JButton("(");


		for(int q =0; q<16; q++){
			final Integer pp = new Integer(q);
				
			phase1buttons[q].setBorder(null);				
			phase1buttons[q].setBorderPainted(false);
			phase1buttons[q].setContentAreaFilled(false);
			phase1buttons[q].setOpaque(false);
			
			midX = (int)((fra.getWidth()-100)/2.8+50);
			midY = (int) ((fra.getWidth()-100)/2.8*1.6+45); 
			radius = (int)((fra.getWidth()-100)/2.8*1.2);
			degreeBetween = 15;
			radianBetween = Math.toRadians(degreeBetween);
			
			xValue = (int)(radius*(Math.sin(radianBetween*(q-2))));
			yValue = (int)(Math.sqrt(Math.pow(radius,2)-Math.pow(xValue, 2)));
			if(q<8){
				yValue = -1*yValue;
			}
			if (q >=8){
				xValue = (int)(radius*(Math.sin(radianBetween*(q-1))));
				yValue = (int)(Math.sqrt(Math.pow(radius,2)-Math.pow(xValue, 2)));	
			}
		
			xValue +=midX;
			yValue +=midY; 						
			phase1buttons[q].setBounds(xValue, yValue, (int)(BUTTON_SIZE_X*0.8),(int)(BUTTON_SIZE_Y*0.8));
			
			phase1buttons[q].setFont(new Font("Arial", Font.PLAIN, 25));	
			
			Border border = new LineBorder(Color.BLACK, 1);
			if (q == 7){
				phase1buttons[7].addActionListener(new ActionListener(){			
					public void actionPerformed(ActionEvent ep5){
						shiftclick = 1; 
						if(shiftclick == 1){
						phase1buttons[7].setBorder(border);
						phase1buttons[7].setBorderPainted(true);}
						}			
					});
			}else if (q == 8){
				phase1buttons[8].addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ep5){
						capsclick ++;
						if((capsclick % 2) == 1){
							phase1buttons[8].setBorder(border);
							phase1buttons[8].setBorderPainted(true);
						}else{
							phase1buttons[8].setBorder(null);
							phase1buttons[8].setBorderPainted(false);	
						}
					}			
					});
			}else{
			phase1buttons[q].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ep2){
				String actionCommand = ep2.getActionCommand();
				txt.setText(txt.getText() + actionCommand);
				tp1.setText(tp1.getText() + actionCommand);
				content = content + actionCommand;
				int temp2; String temp = null;

				try {		             
		            Robot robot = new Robot();	
		            
		            if(pp == 0){robot.keyPress(KeyEvent.VK_COMMA);robot.keyRelease(KeyEvent.VK_COMMA);}
		            if(pp == 1){robot.keyPress(KeyEvent.VK_PERIOD);robot.keyRelease(KeyEvent.VK_PERIOD);}
		            if(pp == 2){robot.keyPress(KeyEvent.VK_SEMICOLON );robot.keyRelease(KeyEvent.VK_SEMICOLON );}
		            if(pp == 3){
		            	robot.keyPress(KeyEvent.VK_SHIFT);
		            	robot.keyPress(KeyEvent.VK_SEMICOLON);robot.keyRelease(KeyEvent.VK_SEMICOLON);
		            	robot.keyRelease(KeyEvent.VK_SHIFT);}
		            if(pp == 4){
		            	robot.keyPress(KeyEvent.VK_SHIFT);
		            	robot.keyPress(KeyEvent.VK_SLASH);robot.keyRelease(KeyEvent.VK_SLASH);
		            	robot.keyRelease(KeyEvent.VK_SHIFT);}
		            if(pp == 5){
		            	robot.keyPress(KeyEvent.VK_SHIFT);
		            	robot.keyPress(KeyEvent.VK_1);robot.keyRelease(KeyEvent.VK_1);
		            	robot.keyRelease(KeyEvent.VK_SHIFT);}
		            if(pp == 6){
		            	robot.keyPress(KeyEvent.VK_SHIFT);
		            	robot.keyPress(KeyEvent.VK_2);robot.keyRelease(KeyEvent.VK_2);
		            	robot.keyRelease(KeyEvent.VK_SHIFT);}
		            if(pp == 9){
		            	robot.keyPress(KeyEvent.VK_SHIFT);
		            	robot.keyPress(KeyEvent.VK_4);robot.keyRelease(KeyEvent.VK_4);
		            	robot.keyRelease(KeyEvent.VK_SHIFT);}
		            if(pp == 10){
		            	robot.keyPress(KeyEvent.VK_SHIFT);
		            	robot.keyPress(KeyEvent.VK_3);robot.keyRelease(KeyEvent.VK_3);
		            	robot.keyRelease(KeyEvent.VK_SHIFT);}
		            if(pp == 11){
		            	robot.keyPress(KeyEvent.VK_SHIFT);
		            	robot.keyPress(KeyEvent.VK_7);robot.keyRelease(KeyEvent.VK_7);
		            	robot.keyRelease(KeyEvent.VK_SHIFT);}
		            if(pp == 12){
		            	robot.keyPress(KeyEvent.VK_SHIFT);
		            	robot.keyPress(KeyEvent.VK_QUOTE);robot.keyRelease(KeyEvent.VK_QUOTE);
		            	robot.keyRelease(KeyEvent.VK_SHIFT);}
		            if(pp == 13){robot.keyPress(KeyEvent.VK_QUOTE);robot.keyRelease(KeyEvent.VK_QUOTE);}
		            if(pp == 14){
		            	robot.keyPress(KeyEvent.VK_SHIFT);
		            	robot.keyPress(KeyEvent.VK_0);robot.keyRelease(KeyEvent.VK_0);
		            	robot.keyRelease(KeyEvent.VK_SHIFT);}
		            if(pp == 15){
		            	robot.keyPress(KeyEvent.VK_SHIFT);
		            	robot.keyPress(KeyEvent.VK_9);robot.keyRelease(KeyEvent.VK_9);
		            	robot.keyRelease(KeyEvent.VK_SHIFT);}

		        } catch (AWTException e) {
		            e.printStackTrace();
		        }
				
/*				try {
		        	//int temp2; String temp;
		        	temp = phase1buttons[pp].getText();
		        	temp2 = (int)temp.charAt(0);
		        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);
 
		            Robot robot = new Robot();
		            // Creates the delay of 5 sec so that you can open notepad before
		            // Robot start writting
		            //robot.delay(5000);
		            robot.keyPress(keyCode);       
		        	} catch (AWTException e) {
		        		e.printStackTrace();
		        	} */
				//write();
				}			
				});
			}
						
			final Integer p = new Integer(q);
			phase1buttons[p].addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(MouseEvent e) {
			        // saySomething("Mouse entered", e);
			    	phase1buttons[p].setBackground(Color.LIGHT_GRAY);
			    	phase1buttons[p].setContentAreaFilled(true);
			     	
			    }
			    public void mouseExited(MouseEvent e) {
			    	phase1buttons[p].setBackground(null);
			    	phase1buttons[p].setContentAreaFilled(false);
			    }
			});
			
			
		}
		//create punctuation button
/*		p1 = new JButton(",");
		p2 = new JButton(".");
		p3 = new JButton(";");
		p4 = new JButton(":");
		p5 = new JButton("?");
		p6 = new JButton("!");
		p7 = new JButton("@");
		sharp = new JButton("#");
		and = new JButton("&");
		dollar = new JButton("$");
		dquote = new JButton("\"");
		squote = new JButton("'");
		sbra1 = new JButton(")");
		sbra2 = new JButton("(");		
*/		
		
		// enter and delete
		enter = new JButton();
//	    entericon = new ImageIcon("C:\\Users\\djing\\Desktop\\jde\\enter.png");
		java.net.URL enteri = P3.class.getResource("enter.png");
		entericon = new ImageIcon(enteri);
		ienter = entericon.getImage();
		enterwidth = ienter.getWidth(null); enterheight = ienter.getHeight(null);
		ienter = ienter.getScaledInstance((int)(enterwidth/12*ratio1), (int)(enterheight/10*ratio1), Image.SCALE_SMOOTH);
		entericon.setImage(ienter);
		enter.setIcon(entericon);	
		
		delete = new JButton();
//		deleteicon = new ImageIcon("C:\\Users\\djing\\Desktop\\jde\\delete.png");
		java.net.URL deletei = P3.class.getResource("delete.png");
		deleteicon = new ImageIcon(deletei);
		idelete = deleteicon.getImage();
		deletewidth = idelete.getWidth(null); deleteheight = idelete.getHeight(null);
		idelete = idelete.getScaledInstance((int)(deletewidth/4*ratio1), (int)(deleteheight/4*ratio1), Image.SCALE_SMOOTH);
		deleteicon.setImage(idelete);
		delete.setIcon(deleteicon);
		
		delete.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		        // saySomething("Mouse entered", e);
		    	delete.setBackground(Color.LIGHT_GRAY);
		    	delete.setContentAreaFilled(true);
		     	
		    }
		    public void mouseExited(MouseEvent e) {
		    	delete.setBackground(null);
		    	delete.setContentAreaFilled(false);
		    }
		});
		enter.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		        // saySomething("Mouse entered", e);
		    	enter.setBackground(Color.LIGHT_GRAY);
		    	enter.setContentAreaFilled(true);
		     	
		    }
		    public void mouseExited(MouseEvent e) {
		    	enter.setBackground(null);
		    	enter.setContentAreaFilled(false);
		    }
		});
		
		//set the location
/*		p1.setBounds(fra.getWidth()/10+40, fra.getHeight()/6+10, BUTTON_SIZE_X-3,BUTTON_SIZE_Y-3); p1.setFont(new Font("Arial", Font.BOLD, 25));
		p2.setBounds(139, 88, 35, 35); p2.setFont(new Font("Arial", Font.BOLD, 25));
		p3.setBounds(171, 80, 35, 35); p3.setFont(new Font("Arial", Font.BOLD, 25));
		p4.setBounds(203, 82, 35, 35); p4.setFont(new Font("Arial", Font.BOLD, 25));
		p5.setBounds(235, 86, 35, 35); p5.setFont(new Font("Arial", Font.BOLD, 25));
		p6.setBounds(267, 99, 35, 35); p6.setFont(new Font("Arial", Font.BOLD, 25));
		dquote.setBounds(300, 112, 35, 35); dquote.setFont(new Font("Arial", Font.BOLD, 25));
		squote.setBounds(325, 145, 35, 35); squote.setFont(new Font("Arial", Font.BOLD, 25));
		shift.setBounds(343, 178, 35, 40);shift.setFont(new Font("Arial", Font.PLAIN, 18));
	    caps.setBounds(344, 288, 35, 40);caps.setFont(new Font("Arial", Font.PLAIN, 18));
		p7.setBounds(325, 327, 35, 35); p7.setFont(new Font("Arial", Font.BOLD, 25));
		sharp.setBounds(293, 360, 35, 35); sharp.setFont(new Font("Arial", Font.BOLD, 25));
		and.setBounds(255, 380, 35, 35); and.setFont(new Font("Arial", Font.BOLD, 25));
		dollar.setBounds(218, 390, 35, 35); dollar.setFont(new Font("Arial", Font.BOLD, 25));
		sbra1.setBounds(180, 390, 35, 35); sbra1.setFont(new Font("Arial", Font.BOLD, 25));
		sbra2.setBounds(140, 380, 35, 35); sbra2.setFont(new Font("Arial", Font.BOLD, 25));		
*/		
		midX = (int)((fra.getWidth()-100)/2.8+50);
		midY = (int) ((fra.getWidth()-100)/2.8*1.6+45); 
		
		delete.setBounds((int)(midX-60*ratio1), (int)(midY-10*ratio1-30*ratio1), (int)(40*ratio1), (int)(30*ratio1)); 
		enter.setBounds((int)(midX-60*ratio1), (int)(midY-10*ratio1+40*ratio1), (int)(40*ratio1), (int)(30*ratio1));
		
		delete.setHorizontalAlignment(SwingConstants.CENTER);
		enter.setHorizontalAlignment(SwingConstants.CENTER);

		//hide buttons
/*		dquote.setBorder(null);dquote.setBorderPainted(false);dquote.setContentAreaFilled(false);dquote.setOpaque(false); 
		squote.setBorder(null);squote.setBorderPainted(false);squote.setContentAreaFilled(false);squote.setOpaque(false); 
		sharp.setBorder(null);sharp.setBorderPainted(false);sharp.setContentAreaFilled(false);sharp.setOpaque(false); 
		and.setBorder(null);and.setBorderPainted(false);and.setContentAreaFilled(false);and.setOpaque(false); 
		dollar.setBorder(null);dollar.setBorderPainted(false);dollar.setContentAreaFilled(false);dollar.setOpaque(false); 
		sbra1.setBorder(null);sbra1.setBorderPainted(false);sbra1.setContentAreaFilled(false);sbra1.setOpaque(false); 
		sbra2.setBorder(null);sbra2.setBorderPainted(false);sbra2.setContentAreaFilled(false);sbra2.setOpaque(false); 
		shift.setBorder(null);shift.setBorderPainted(false);shift.setContentAreaFilled(false);shift.setOpaque(false);
		caps.setBorder(null);caps.setBorderPainted(false);caps.setContentAreaFilled(false);caps.setOpaque(false);	

		p1.setBorder(null);p1.setBorderPainted(false);p1.setContentAreaFilled(false);p1.setOpaque(false); 
		p2.setBorder(null);p2.setBorderPainted(false);p2.setContentAreaFilled(false);p2.setOpaque(false); 
		p3.setBorder(null);p3.setBorderPainted(false);p3.setContentAreaFilled(false);p3.setOpaque(false); 
		p4.setBorder(null);p4.setBorderPainted(false);p4.setContentAreaFilled(false);p4.setOpaque(false); 
		p5.setBorder(null);p5.setBorderPainted(false);p5.setContentAreaFilled(false);p5.setOpaque(false); 
		p6.setBorder(null);p6.setBorderPainted(false);p6.setContentAreaFilled(false);p6.setOpaque(false); 
		p7.setBorder(null);p7.setBorderPainted(false);p7.setContentAreaFilled(false);p7.setOpaque(false); 
	*/	
		
		delete.setBorder(null);delete.setBorderPainted(false);delete.setContentAreaFilled(false);delete.setOpaque(false); 
		enter.setBorder(null);enter.setBorderPainted(false);enter.setContentAreaFilled(false);enter.setOpaque(false); 
	
		//action
/*		p1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ep1){
			String actionCommand = ep1.getActionCommand();
			txt.setText(txt.getText() + actionCommand);
			tp1.setText(tp1.getText() + actionCommand);
			content = content + ",";
			write();		
			});
		p2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ep2){
			String actionCommand = ep2.getActionCommand();
			txt.setText(txt.getText() + actionCommand);
			tp1.setText(tp1.getText() + actionCommand);
			content = content + ".";
			write();
			}			
			});
		p3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ep3){
			String actionCommand = ep3.getActionCommand();
			txt.setText(txt.getText() + actionCommand);
			tp1.setText(tp1.getText() + actionCommand);
			content = content + ";";
			write();
			}			
			});
		p4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ep4){
			String actionCommand = ep4.getActionCommand();
			txt.setText(txt.getText() + actionCommand);
			tp1.setText(tp1.getText() + actionCommand);
			content = content + ":";
			write();
			}			
			});
		p5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ep5){
			String actionCommand = ep5.getActionCommand();
			txt.setText(txt.getText() + actionCommand);
			tp1.setText(tp1.getText() + actionCommand);
			content = content + "?";
			write();
			}			
			});
		p6.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ep6){
			String actionCommand = ep6.getActionCommand();
			txt.setText(txt.getText() + actionCommand);
			tp1.setText(tp1.getText() + actionCommand);
			content = content + "!";
			write();
			}			
			});
		p7.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ep7){
			String actionCommand = ep7.getActionCommand();
			txt.setText(txt.getText() + actionCommand);
			tp1.setText(tp1.getText() + actionCommand);
			content = content + "@";
			write();
			}			
			});
		
		squote.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ep5){
				String actionCommand = ep5.getActionCommand();
				txt.setText(txt.getText() + actionCommand);
				tp1.setText(tp1.getText() + actionCommand);
				content = content + "'";
				write();
			}			
			});	
		dquote.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ep5){
				String actionCommand = ep5.getActionCommand();
				txt.setText(txt.getText() + actionCommand);
				tp1.setText(tp1.getText() + actionCommand);
				content = content + "\"";
				write();
			}			
			});	
		sharp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ep5){
				String actionCommand = ep5.getActionCommand();
				txt.setText(txt.getText() + actionCommand);
				tp1.setText(tp1.getText() + actionCommand);
				content = content + "#";
				write();
			}			
			});
		and.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ep5){
				String actionCommand = ep5.getActionCommand();
				txt.setText(txt.getText() + actionCommand);
				tp1.setText(tp1.getText() + actionCommand);
				content = content + "&";
				write();
			}			
			});
		dollar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ep5){
				String actionCommand = ep5.getActionCommand();
				txt.setText(txt.getText() + actionCommand);
				tp1.setText(tp1.getText() + actionCommand);
				content = content + "$";
				write();
			}			
			});
		sbra1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ep5){
				String actionCommand = ep5.getActionCommand();
				txt.setText(txt.getText() + actionCommand);
				tp1.setText(tp1.getText() + actionCommand);
				content = content + ")";
				write();
			}			
			});
		sbra2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ep5){
				String actionCommand = ep5.getActionCommand();
				txt.setText(txt.getText() + actionCommand);
				tp1.setText(tp1.getText() + actionCommand);
				content = content + "(";
				write();
			}			
			}); 
		Border border = new LineBorder(Color.BLACK, 1);
		shift.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ep5){
				shiftclick = 1; 
				if(shiftclick == 1){
				shift.setBorder(border);
				shift.setBorderPainted(true);}
			}			
			});
		caps.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ep5){
				capsclick ++;
				if((capsclick % 2) == 1){
				caps.setBorder(border);
				caps.setBorderPainted(true);
				}else{
				caps.setBorder(null);
				caps.setBorderPainted(false);	
				}
			}			
			});
			*/
		
		
		Border border = new LineBorder(Color.BLACK, 1);

		delete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ep5){
				String updatecontent = content.substring(0, content.length()-1);
				txt.setText(updatecontent);
				tp1.setText(updatecontent);
				content = updatecontent;
			//	write();
				try {
		             
		            Robot robot = new Robot();		
		            robot.keyPress(KeyEvent.VK_SHIFT);
		            robot.keyPress(KeyEvent.VK_BACK_SPACE);
		            robot.keyRelease(KeyEvent.VK_BACK_SPACE);
		            robot.keyRelease(KeyEvent.VK_SHIFT);		             

		        } catch (AWTException e) {
		            e.printStackTrace();
		        }
			}						
			});	
	
		enter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent eenter){
				txt.setText(txt.getText() + "\n");
				String contenttp1 = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
				tp1.setText(contenttp1 + "\n");
				content = content + " ";
			//	write();
				try {
		        	String temp = "\n";
		        	int temp2 = (int)temp.charAt(0);	        	
		        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);		             
		            Robot robot = new Robot();		
		            robot.keyPress(keyCode);		             
		        } catch (AWTException e) {
		            e.printStackTrace();
		        }
			}			
		});	
	
	}

	public void errorcorrection(){
		if (error != null){
		charValue = error.charAt(0);
		System.out.println(charValue);
		next = String.valueOf( (char) (charValue + 1));
		System.out.println(next);
		last = String.valueOf( (char) (charValue - 1));		
		System.out.println(last);
		ec1.setText(last);ec2.setText(next);
/*	    ec1.setBorder(null);ec1.setBorderPainted(false);ec1.setContentAreaFilled(false);ec1.setOpaque(false); 
	    ec2.setBorder(null);ec2.setBorderPainted(false);ec2.setContentAreaFilled(false);ec2.setOpaque(false); 
	    ec1.setBounds(ecposition1x, ecposition1y, 30, 30); 
		ec2.setBounds(ecposition2x, ecposition2y, 30, 30); 
*/
		for (int i=0; i<26; i++){
		String value = String.valueOf( (char) (charValue));
		String comparevalue = letterbuttons[i].getText();
			if(value.equalsIgnoreCase(comparevalue)){
				degreeBetween = 9.5;
				radianBetween = Math.toRadians(degreeBetween);
				midX = (int)((fra.getWidth()-100)/2.8+50);
				midY = (int) ((fra.getWidth()-100)/2.8*1.5+50); 
				radius = (int)((fra.getWidth()-100)/2.8*1.8);
				
				xValue = (int)(radius*(Math.sin(radianBetween*(i-3.5))));
				yValue = (int)(Math.sqrt(Math.pow(radius,2)-Math.pow(xValue, 2)));
				
				xValue1 = (int)(radius*(Math.sin(radianBetween*(i-2.5))));
				yValue1 = (int)(Math.sqrt(Math.pow(radius,2)-Math.pow(xValue1, 2)));
				
				if(i<13){
					yValue = -1*yValue;
					yValue1 = -1*yValue1;
				}	
				xValue +=midX; xValue1 +=midX;
				yValue +=midY; yValue1 +=midY;	
				ec1.setBounds(xValue, yValue, (int)(BUTTON_SIZE_X*ratio1), (int)(BUTTON_SIZE_Y*ratio1));
				ec2.setBounds(xValue1, yValue1, (int)(BUTTON_SIZE_X*ratio1), (int)(BUTTON_SIZE_Y*ratio1));
				ec1.setFont(new Font("Arial", Font.PLAIN, (int)(20*ratio1)));	
				ec2.setFont(new Font("Arial", Font.PLAIN, (int)(20*ratio1)));	

				ec1.setBorder(null);ec1.setBorderPainted(false); 
				ec1.setContentAreaFilled(false); ec1.setOpaque(false);
		        ec2.setBorder(null); ec2.setBorderPainted(false);
		        ec2.setContentAreaFilled(false); ec2.setOpaque(false);
			}
		
		}
		ec1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e1){
			txt.setText(txt.getText().substring(0, txt.getText().length()-1) + last);
			tp1.setText(tp1.getText().substring(0, txt.getText().length()-1) + last);
			content = content.substring(0, content.length()-1) + last;
		//	write();
			try {		
				int temp2; String temp = null;
				temp = last.toString();
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	        	Robot robot = new Robot();	
	            robot.keyPress(KeyEvent.VK_SHIFT);
	            robot.keyPress(KeyEvent.VK_BACK_SPACE);
	            robot.keyRelease(KeyEvent.VK_BACK_SPACE);
	            robot.keyRelease(KeyEvent.VK_SHIFT);	            
	            robot.keyPress(keyCode); 
			
				} catch (AWTException e) {
					e.printStackTrace();
				}
			generatelist();
			}			
			});
		ec2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e1){
			txt.setText(txt.getText().substring(0, txt.getText().length()-1) + next);
			tp1.setText(tp1.getText().substring(0, txt.getText().length()-1) + next);
			content = content.substring(0, content.length()-1) + next;
		//	write();
			try {		
				int temp2; String temp = null;
				temp = next.toString();
	        	temp2 = (int)temp.charAt(0);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);

	        	Robot robot = new Robot();	
	            robot.keyPress(KeyEvent.VK_SHIFT);
	            robot.keyPress(KeyEvent.VK_BACK_SPACE);
	            robot.keyRelease(KeyEvent.VK_BACK_SPACE);
	            robot.keyRelease(KeyEvent.VK_SHIFT);
	            robot.keyPress(keyCode); 
			
				} catch (AWTException e) {
					e.printStackTrace();
				}
			generatelist();
			}			
			});
		}
		ec1.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		        // saySomething("Mouse entered", e);
		    	ec1.setBackground(Color.LIGHT_GRAY);
		    	ec1.setContentAreaFilled(true);
		     	
		    }
		    public void mouseExited(MouseEvent e) {
		    	ec1.setBackground(null);
		    	ec1.setContentAreaFilled(false);
		    }
		});
		ec2.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		        // saySomething("Mouse entered", e);
		    	ec2.setBackground(Color.LIGHT_GRAY);
		    	ec2.setContentAreaFilled(true);
		     	
		    }
		    public void mouseExited(MouseEvent e) {
		    	ec2.setBackground(null);
		    	ec2.setContentAreaFilled(false);
		    }
		});
	}
	/*	space.paintComponents(Graphics);
		int xPoints[] = {200, 140, 140};
		int yPoints[] = {232, 150, 310};
		int nPoints = 3;
		Graphics.fillPolygon(xPoints, yPoints, nPoints);
		//Graphics.fillPolygon(x3Points, y3Points, xPoints.length);
		protected void paintBorder(Graphics g) {
		     g.setColor(getForeground());
		     int xPoints[] = {getSize().width/2, 0, getSize().width};
		     int yPoints[] = {0, getSize().height, getSize().height};
		     g.drawPolygon(xPoints, yPoints, xPoints.length);
		}
		Polygon polygon;
		public boolean contains(int x, int y) {
		     if (polygon == null || !polygon.getBounds().equals(getBounds())) {
		          int xPoints[] = {getSize().width/2, 0, getSize().width};
		          int yPoints[] = {0, getSize().height, getSize().height};
		          polygon = new Polygon(xPoints,yPoints, xPoints.length);
		     }
		     return polygon.contains(x, y);
		}*/
		
	public void read(){
	   // String content = null;	
/*		try {
			reader2 = new BufferedReader(new FileReader(fileread));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    StringBuilder sb1 = new StringBuilder();
	    String line1 = null;
		try {
			line1 = reader2.readLine();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

	    while (line1 != null) {
	        sb1.append(line1);
	        sb1.append(System.lineSeparator());
	        try {
				line1 = reader2.readLine();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    }
	    content = sb1.toString();
	*/
	    try {
			content = new String(Files.readAllBytes(Paths.get(file)));
		} catch (IOException er1) {
		// TODO Auto-generated catch block
			er1.printStackTrace();
		} 
	    System.out.println("content"+content);

		//ct = content;
	}
	public void write(){
/*	    System.out.println("content1"+content);

		FileWriter fw = null;
		try {
			fw = new FileWriter(fileread);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bw = new BufferedWriter(fw);
		try {
			bw.append(content);
		//	bw.write(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		try {
	    	//Files.delete(Paths.get(file));
			Files.write(Paths.get(file), content.getBytes(), StandardOpenOption.CREATE);
	        } catch (IOException ew1) {
		// TODO Auto-generated catch block
			ew1.printStackTrace();
		}  
	    System.out.println("content2"+content);
	} 
/*	public void refresh() throws IOException{
		sw = new StringWriter();
        bw = new BufferedWriter(sw);
        //bw.append(content);
        //bw.write(content);
        bw.append(file);
        bw.flush(); 
        //bw.close();
        
	} */
	
	public void generatelist(){	
		for (int z=0;z<29;z++){
			pan.remove(phase2buttons[z]);
		}
/*		pan.remove(ex1);pan.remove(ex2);pan.remove(ex3);pan.remove(ex4);pan.remove(ex5);
		pan.remove(no0);pan.remove(no1);pan.remove(no2);pan.remove(no3);pan.remove(no4);pan.remove(no5);pan.remove(no6);pan.remove(no7);pan.remove(no8);pan.remove(no9);
		pan.remove(d1);pan.remove(d2);pan.remove(d3);pan.remove(d4);pan.remove(d5);pan.remove(d6);pan.remove(d7);pan.remove(d8);pan.remove(d9);pan.remove(d10);
		pan.remove(mbra1);pan.remove(mbra2);pan.remove(lbra1);pan.remove(lbra2);
*/		
		String lastWord = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
		typedletters = lastWord.length();
		System.out.println("lastword"+lastWord);

/*		if (typedletters == 1){
			waitingtime = 2500;
			}else if(typedletters == 2){
				waitingtime = 1000;
			}else if(typedletters == 3){
				waitingtime = 500;
			}else if(typedletters == 4){
				waitingtime = 250;
			}else if(typedletters == 5){
				waitingtime = 100;
			}else if(typedletters == 6){
				waitingtime = 100;
			}
	
		try {
			TimeUnit.MILLISECONDS.sleep(waitingtime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		*/
		
	/*	File fileabc = new File("C:\\Users\\djing\\Desktop\\abc.txt");
		Boolean checkopenabc = fileabc.canWrite( );
		System.out.println(checkopenabc);
		File file123 = new File("C:\\Users\\djing\\Desktop\\123.txt");
		Boolean checkopen123 = file123.canWrite( );
		System.out.println(checkopen123); 
		
		if(fileabc.exists())
	    {filelist = "C:\\Users\\djing\\Desktop\\abc.txt";

		try {
			words = new String(Files.readAllBytes(Paths.get(filelist)));
		} catch (IOException wl1) {
			// TODO Auto-generated catch block
			wl1.printStackTrace();
		}
		System.out.println(words); */
	    
/*		String[] wordlist1= new String[2000];
		Arrays.fill(wordlist1, null);

		ArrayList<String> wordlist1 = new ArrayList<String>(); 
		wordlist1.clear();
		wordlist1.add(words);  */
		if (typedletters == 0){
			return;
		}
		
/*		File listfile = new File("C:\\Users\\djing\\Desktop\\prediction\\list\\Source\\Wordlist.txt");
		longlist = "C:\\Users\\djing\\Desktop\\prediction\\list\\Source\\Wordlist.txt";
		try {
			longlist0 = new String(Files.readAllBytes(Paths.get(longlist)));
		} catch (IOException wl1) {
			// TODO Auto-generated catch block
			wl1.printStackTrace();
		} */
		
		java.net.URL path1 = P3.class.getResource("Wordlist.txt");
		File filewl = new File(path1.getFile());

		BufferedReader reader1 = null;		
		try {
			reader1 = new BufferedReader(new FileReader(filewl));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    StringBuilder sb = new StringBuilder();
	    String line = null;
		try {
			line = reader1.readLine();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

	    while (line != null) {
	        sb.append(line);
	        sb.append(System.lineSeparator());
	        try {
				line = reader1.readLine();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    }
	    longlist0 = sb.toString();
		
		//System.out.println("longlist0" + longlist0);
		longlist0 = longlist0.replace("\n", " ");
		//System.out.println(longlist0);
		String [] longlist1 = new String[110000];
		Arrays.fill(longlist1, null);
		longlist1 = longlist0.split(" "); 
		//System.out.println(longlist1);
		//String [] firstlist = new String[5000];

		System.out.print("check" + content +"\n");

		ArrayList<String> firstlist = new ArrayList<>(); firstlist.clear();
		for (int first = 0; first < longlist1.length; first++){
			String firstletter = null;
	//		System.out.println("tp"+typedletters);
	//		System.out.println("lw"+lastWord);
			if (typedletters == 1){
			firstletter = longlist1[first].substring(0,1);
			}else if(typedletters == 2){
			firstletter = longlist1[first].substring(0,2);
			}else if((typedletters == 3)&(longlist1[first].length()>2)){
			firstletter = longlist1[first].substring(0,3);
			}else if(typedletters == 4){
			//System.out.println("1234");
				if(longlist1[first].length()>3){
				firstletter = longlist1[first].substring(0,4);
				}else{
				firstletter="a";}
			}else if(typedletters == 5){
				if(longlist1[first].length()>4){
				firstletter = longlist1[first].substring(0,5);}else{
				firstletter="a";}
			}else if(typedletters == 6){
				if(longlist1[first].length()>5){
				firstletter = longlist1[first].substring(0,6);}else{
				firstletter="a";}
			}else if(typedletters == 7){
				if(longlist1[first].length()>6){
				firstletter = longlist1[first].substring(0,7);}else{
				firstletter="a";}
			}else if(typedletters == 8){
				if(longlist1[first].length()>8){
				firstletter = longlist1[first].substring(0,9);}else{
				firstletter="a";}
			}
			if (firstletter.equalsIgnoreCase(lastWord)){
				firstlist.add(longlist1[first]);
		}
		}
		System.out.println("size"+ firstlist.size());
		System.out.println(typedletters);

//		System.out.println(firstlist);
		//System.out.println(firstlist);

		//String [] wordlist1 = words.split(" ");
		//String [] wordlist1 = new String[5000];
		//Arrays.fill(wordlist1, null);
		//wordlist1 = words.split(" ");
		String [] wordlist1 = firstlist.toArray(new String[firstlist.size()]);
		System.out.println(wordlist1);
		//ArrayList<String> separatewordList = new ArrayList<String>(Arrays.asList(wordlist1));
		//System.out.println(separatewordList);
		//grouping
		ArrayList<ArrayList<String>> letters = new ArrayList<ArrayList<String>>();	
		letters.clear();
		ArrayList<String> a = new ArrayList<>(); a.clear();
		ArrayList<String> b = new ArrayList<>(); b.clear();
		ArrayList<String> c = new ArrayList<>(); c.clear();
		ArrayList<String> d = new ArrayList<>(); d.clear();
		ArrayList<String> e = new ArrayList<>(); e.clear();
		ArrayList<String> f = new ArrayList<>(); f.clear();
		ArrayList<String> g = new ArrayList<>(); g.clear();
		ArrayList<String> h = new ArrayList<>(); h.clear();
		ArrayList<String> i = new ArrayList<>(); i.clear();
		ArrayList<String> j = new ArrayList<>(); j.clear();
		ArrayList<String> k = new ArrayList<>(); k.clear();
		ArrayList<String> l = new ArrayList<>(); l.clear();
		ArrayList<String> m = new ArrayList<>(); m.clear();
		ArrayList<String> n = new ArrayList<>(); n.clear();
		ArrayList<String> o = new ArrayList<>(); o.clear();
		ArrayList<String> p = new ArrayList<>(); p.clear();
		ArrayList<String> q = new ArrayList<>(); q.clear();
		ArrayList<String> r = new ArrayList<>(); r.clear();
		ArrayList<String> s = new ArrayList<>(); s.clear();
		ArrayList<String> t = new ArrayList<>(); t.clear();
		ArrayList<String> u = new ArrayList<>(); u.clear();
		ArrayList<String> v = new ArrayList<>(); v.clear();
		ArrayList<String> w = new ArrayList<>(); w.clear();
		ArrayList<String> x = new ArrayList<>(); x.clear();
		ArrayList<String> y = new ArrayList<>(); y.clear();
		ArrayList<String> z = new ArrayList<>(); z.clear();
		
		//get the last word in the content
		//String lastWord = content.replaceAll("^.*?(\\w+)\\W*$", "$1");
		//typedletters = lastWord.length();
		//System.out.println(typedletters);

		for (int abc = 0; abc < wordlist1.length; abc++){
			String compareletter = "a";	
			String secondletter = null;
			
			if (wordlist1[abc].length() > typedletters){
			//secondletter = wordlist1[abc].substring(1,2);
			if (typedletters == 1){
			secondletter = wordlist1[abc].substring(1,2);
			}else if((typedletters == 2)&(wordlist1[abc].length()>2)){
			secondletter = wordlist1[abc].substring(2,3);
			}else if((typedletters == 3)&(wordlist1[abc].length()>3)){
			secondletter = wordlist1[abc].substring(3,4);
			}else if(typedletters == 4){
				if(wordlist1[abc].length()>4){
					//System.out.println("abcde");
				secondletter = wordlist1[abc].substring(4,5);}else{
					//System.out.println("abcdef");
					secondletter = "0";
			}
			}else if(typedletters == 5){
				if(wordlist1[abc].length()>5){
				secondletter = wordlist1[abc].substring(5,6);}else{
				secondletter = "0";}			
			}else if(typedletters == 6){
				if(wordlist1[abc].length()>6){
				secondletter = wordlist1[abc].substring(6,7);}else{
				secondletter = "0";}			
			}else if(typedletters == 7){
				if(wordlist1[abc].length()>7){
				secondletter = wordlist1[abc].substring(7,8);}else{
				secondletter = "0";}			
			}else if(typedletters == 8){
				if(wordlist1[abc].length()>8){
				secondletter = wordlist1[abc].substring(8,9);}else{
				secondletter = "0";}			
			}
			//System.out.println(secondletter);
			
			if (secondletter.equalsIgnoreCase(compareletter)){
				a.add(wordlist1[abc]);
			} else {
				compareletter = "b";
				if(secondletter.equalsIgnoreCase(compareletter)){
					b.add(wordlist1[abc]);
				}else{
					compareletter = "c";
					if(secondletter.equalsIgnoreCase(compareletter)){
						c.add(wordlist1[abc]);
					}else{
						compareletter = "d";
						if(secondletter.equalsIgnoreCase(compareletter)){
							d.add(wordlist1[abc]);
						}else{
							compareletter = "e";
							if(secondletter.equalsIgnoreCase(compareletter)){
								e.add(wordlist1[abc]);
							}else{
								compareletter = "f";
								if(secondletter.equalsIgnoreCase(compareletter)){
									f.add(wordlist1[abc]);
								}else{
									compareletter = "g";
									if(secondletter.equalsIgnoreCase(compareletter)){
										g.add(wordlist1[abc]);
									}else{
										compareletter = "h";
										if(secondletter.equalsIgnoreCase(compareletter)){
											h.add(wordlist1[abc]);
										}else{
											compareletter = "i";
											if(secondletter.equalsIgnoreCase(compareletter)){
												i.add(wordlist1[abc]);
											}else{
												compareletter = "j";
												if(secondletter.equalsIgnoreCase(compareletter)){
													j.add(wordlist1[abc]);
												}else{
													compareletter = "k";
													if(secondletter.equalsIgnoreCase(compareletter)){
														k.add(wordlist1[abc]);
													}else{
														compareletter = "l";
														if(secondletter.equalsIgnoreCase(compareletter)){
															l.add(wordlist1[abc]);
														}else{
															compareletter = "m";
															if(secondletter.equalsIgnoreCase(compareletter)){
																m.add(wordlist1[abc]);
															}else{
																compareletter = "n";
																if(secondletter.equalsIgnoreCase(compareletter)){
																	n.add(wordlist1[abc]);
																}else{
																	compareletter = "o";
																	if(secondletter.equalsIgnoreCase(compareletter)){
																		o.add(wordlist1[abc]);
																	}else{
																		compareletter = "p";
																		if(secondletter.equalsIgnoreCase(compareletter)){
																			p.add(wordlist1[abc]);
																		}else{
																			compareletter = "q";
																			if(secondletter.equalsIgnoreCase(compareletter)){
																				q.add(wordlist1[abc]);
																			}else{
																				compareletter = "r";
																				if(secondletter.equalsIgnoreCase(compareletter)){
																					r.add(wordlist1[abc]);
																				}else{
																					compareletter = "s";
																					if(secondletter.equalsIgnoreCase(compareletter)){
																						s.add(wordlist1[abc]);
																					}else{
																						compareletter = "t";
																						if(secondletter.equalsIgnoreCase(compareletter)){
																							t.add(wordlist1[abc]);
																						}else{
																							compareletter = "u";
																							if(secondletter.equalsIgnoreCase(compareletter)){
																								u.add(wordlist1[abc]);
																							}else{
																								compareletter = "v";
																								if(secondletter.equalsIgnoreCase(compareletter)){
																									v.add(wordlist1[abc]);
																								}else{
																									compareletter = "w";
																									if(secondletter.equalsIgnoreCase(compareletter)){
																										w.add(wordlist1[abc]);
																									}else{
																										compareletter = "x";
																										if(secondletter.equalsIgnoreCase(compareletter)){
																											x.add(wordlist1[abc]);
																										}else{
																											compareletter = "y";
																											if(secondletter.equalsIgnoreCase(compareletter)){
																												y.add(wordlist1[abc]);
																											}else{
																												compareletter = "z";
																												if(secondletter.equalsIgnoreCase(compareletter)){
																													z.add(wordlist1[abc]);
																												}	
																											}		
																										}		
																									}		
																								}		
																							}		
																						}		
																					}		
																				}		
																			}		
																		}		
																	}		
																}		
															}		
														}		
													}		
												}		
											}		
										}		
									}		
								}		
							}		
						}		
					}		
				}				
			}
			}
		}
		letters.add(a);
		letters.add(b);
		letters.add(c);
		letters.add(d);
		letters.add(e);
		letters.add(f);
		letters.add(g);
		letters.add(h);
		letters.add(i);
		letters.add(j);
		letters.add(k);
		letters.add(l);
		letters.add(m);
		letters.add(n);
		letters.add(o);
		letters.add(p);
		letters.add(q);
		letters.add(r);
		letters.add(s);
		letters.add(t);
		letters.add(u);
		letters.add(v);
		letters.add(w);
		letters.add(x);
		letters.add(y);
		letters.add(z);
		
		int sizea = a.size();
		int sizeb = b.size();	
		int sizec = c.size();
		int sized = d.size();
		int sizee = e.size();
		int sizef = f.size();
		int sizeg = g.size();
		int sizeh = h.size();
		int sizei = i.size();
		int sizej = j.size();
		int sizek = k.size();
		int sizel = l.size();
		int sizem = m.size();
		int sizen = n.size();
		int sizeo = o.size();
		int sizep = p.size();
		int sizeq = q.size();
		int sizer = r.size();
		int sizes = s.size();
		int sizet = t.size();
		int sizeu = u.size();
		int sizev = v.size();
		int sizew = w.size();
		int sizex = x.size();
		int sizey = y.size();
		int sizez = z.size();

		
		//Print the first element of first arraylist in letters
		//System.out.println(letters.get(0).get(0));
		
		//get the most frequent 2nd letters
		int[] size = {sizea,sizeb,sizec,sized,sizee,sizef,sizeg,sizeh,sizei,sizej,sizek,sizel,sizem,sizen,sizeo,sizep,sizeq,sizer,sizes,sizet,sizeu,sizev,sizew,sizex,sizey,sizez};
		String[] name = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		System.out.println(size[17]);System.out.println(size[0]);System.out.println(size[4]);System.out.println(size[14]);
		
		int largest = 25;
		int slargest = 25;
		int tlargest = 25;
		int flargest = 25;
		int max = size[25];
		int smax = size[25];
		int tmax = size[25];
		int fmax = size[25];
		String largestn =  null;
		String slargestn =  null;
		String tlargestn =  null;
		String flargestn =  null;

		for (int ab=0; ab<=25; ab++){
			if (size[ab] >= max){
				largest = ab;
				max = size[ab];
			}
			largestn = name[largest];
			//ArrayList<String> test1 = letters.get(largest);
			//sringlargest = test1.toArray(new String[0]);
		}
		for (int ab=0; ab<=25; ab++){
			if ((ab != largest )&&(size[ab] >= smax)){
				slargest = ab;
				smax = size[ab];
			}
			slargestn = name[slargest];
			//ArrayList<String> test1 = letters.get(slargest);
			//sringslargest = test1.toArray(new String[0]);
		}
		for (int ab=0; ab<=25; ab++){
			if ((ab != largest )&&(ab != slargest )&&(size[ab] >= tmax)){
				tlargest = ab;
				tmax = size[ab];
			}
			tlargestn = name[tlargest];
			//ArrayList<String> test1 = letters.get(tlargest);
			//sringtlargest = test1.toArray(new String[0]);
		}
		for (int ab=0; ab<=25; ab++){
			if ((ab != largest )&&(ab != slargest )&&(ab != tlargest )&&(size[ab] >= fmax)){
				flargest = ab;
				fmax = size[ab];
			}
			flargestn = name[flargest];
			//ArrayList<String> test1 = letters.get(flargest);
			//sringflargest = test1.toArray(new String[0]);
		}	
		System.out.println(largestn);
		System.out.println(slargestn);
		System.out.println(tlargestn);
		System.out.println(flargestn);
		
		//The second ring
		ArrayList<ArrayList<String>> comparelist = new ArrayList<ArrayList<String>>();	
		comparelist.clear();
		comparelist.add(letters.get(largest));
		comparelist.add(letters.get(slargest));
		comparelist.add(letters.get(tlargest));
		comparelist.add(letters.get(flargest));
		//System.out.println(comparelist);
		
		ArrayList<ArrayList<String>> largestlist = new ArrayList<ArrayList<String>>(26); largestlist.clear();
		ArrayList<ArrayList<String>> slargestlist = new ArrayList<ArrayList<String>>(26); slargestlist.clear();
		ArrayList<ArrayList<String>> tlargestlist = new ArrayList<ArrayList<String>>(26); tlargestlist.clear();
		ArrayList<ArrayList<String>> flargestlist = new ArrayList<ArrayList<String>>(26); flargestlist.clear();
		ArrayList<String> list1 = new ArrayList<String>();ArrayList<String> list2 = new ArrayList<String>();ArrayList<String> list3 = new ArrayList<String>();ArrayList<String> list4 = new ArrayList<String>();ArrayList<String> list5 = new ArrayList<String>();ArrayList<String> list6 = new ArrayList<String>();ArrayList<String> list7 = new ArrayList<String>();ArrayList<String> list8 = new ArrayList<String>();ArrayList<String> list9 = new ArrayList<String>();ArrayList<String> list10 = new ArrayList<String>();ArrayList<String> list11 = new ArrayList<String>();ArrayList<String> list12 = new ArrayList<String>();ArrayList<String> list13 = new ArrayList<String>();ArrayList<String> list14 = new ArrayList<String>();ArrayList<String> list15 = new ArrayList<String>();ArrayList<String> list16 = new ArrayList<String>();ArrayList<String> list17 = new ArrayList<String>();ArrayList<String> list18 = new ArrayList<String>();ArrayList<String> list19 = new ArrayList<String>();ArrayList<String> list20 = new ArrayList<String>();ArrayList<String> list21 = new ArrayList<String>();ArrayList<String> list22 = new ArrayList<String>();ArrayList<String> list23 = new ArrayList<String>();ArrayList<String> list24 = new ArrayList<String>();ArrayList<String> list25 = new ArrayList<String>();ArrayList<String> list26 = new ArrayList<String>();
		ArrayList<String> slist1 = new ArrayList<String>();ArrayList<String> slist2 = new ArrayList<String>();ArrayList<String> slist3 = new ArrayList<String>();ArrayList<String> slist4 = new ArrayList<String>();ArrayList<String> slist5 = new ArrayList<String>();ArrayList<String> slist6 = new ArrayList<String>();ArrayList<String> slist7 = new ArrayList<String>();ArrayList<String> slist8 = new ArrayList<String>();ArrayList<String> slist9 = new ArrayList<String>();ArrayList<String> slist10 = new ArrayList<String>();ArrayList<String> slist11 = new ArrayList<String>();ArrayList<String> slist12 = new ArrayList<String>();ArrayList<String> slist13 = new ArrayList<String>();ArrayList<String> slist14 = new ArrayList<String>();ArrayList<String> slist15 = new ArrayList<String>();ArrayList<String> slist16 = new ArrayList<String>();ArrayList<String> slist17 = new ArrayList<String>();ArrayList<String> slist18 = new ArrayList<String>();ArrayList<String> slist19 = new ArrayList<String>();ArrayList<String> slist20 = new ArrayList<String>();ArrayList<String> slist21 = new ArrayList<String>();ArrayList<String> slist22 = new ArrayList<String>();ArrayList<String> slist23 = new ArrayList<String>();ArrayList<String> slist24 = new ArrayList<String>();ArrayList<String> slist25 = new ArrayList<String>();ArrayList<String> slist26 = new ArrayList<String>();
        ArrayList<String> tlist1 = new ArrayList<String>();ArrayList<String> tlist2 = new ArrayList<String>();ArrayList<String> tlist3 = new ArrayList<String>();ArrayList<String> tlist4 = new ArrayList<String>();ArrayList<String> tlist5 = new ArrayList<String>();ArrayList<String> tlist6 = new ArrayList<String>();ArrayList<String> tlist7 = new ArrayList<String>();ArrayList<String> tlist8 = new ArrayList<String>();ArrayList<String> tlist9 = new ArrayList<String>();ArrayList<String> tlist10 = new ArrayList<String>();ArrayList<String> tlist11 = new ArrayList<String>();ArrayList<String> tlist12 = new ArrayList<String>();ArrayList<String> tlist13 = new ArrayList<String>();ArrayList<String> tlist14 = new ArrayList<String>();ArrayList<String> tlist15 = new ArrayList<String>();ArrayList<String> tlist16 = new ArrayList<String>();ArrayList<String> tlist17 = new ArrayList<String>();ArrayList<String> tlist18 = new ArrayList<String>();ArrayList<String> tlist19 = new ArrayList<String>();ArrayList<String> tlist20 = new ArrayList<String>();ArrayList<String> tlist21 = new ArrayList<String>();ArrayList<String> tlist22 = new ArrayList<String>();ArrayList<String> tlist23 = new ArrayList<String>();ArrayList<String> tlist24 = new ArrayList<String>();ArrayList<String> tlist25 = new ArrayList<String>();ArrayList<String> tlist26 = new ArrayList<String>();
		ArrayList<String> flist1 = new ArrayList<String>();ArrayList<String> flist2 = new ArrayList<String>();ArrayList<String> flist3 = new ArrayList<String>();ArrayList<String> flist4 = new ArrayList<String>();ArrayList<String> flist5 = new ArrayList<String>();ArrayList<String> flist6 = new ArrayList<String>();ArrayList<String> flist7 = new ArrayList<String>();ArrayList<String> flist8 = new ArrayList<String>();ArrayList<String> flist9 = new ArrayList<String>();ArrayList<String> flist10 = new ArrayList<String>();ArrayList<String> flist11 = new ArrayList<String>();ArrayList<String> flist12 = new ArrayList<String>();ArrayList<String> flist13 = new ArrayList<String>();ArrayList<String> flist14 = new ArrayList<String>();ArrayList<String> flist15 = new ArrayList<String>();ArrayList<String> flist16 = new ArrayList<String>();ArrayList<String> flist17 = new ArrayList<String>();ArrayList<String> flist18 = new ArrayList<String>();ArrayList<String> flist19 = new ArrayList<String>();ArrayList<String> flist20 = new ArrayList<String>();ArrayList<String> flist21 = new ArrayList<String>();ArrayList<String> flist22 = new ArrayList<String>();ArrayList<String> flist23 = new ArrayList<String>();ArrayList<String> flist24 = new ArrayList<String>();ArrayList<String> flist25 = new ArrayList<String>();ArrayList<String> flist26 = new ArrayList<String>();
		list1.clear(); list2.clear(); list3.clear(); list4.clear(); list5.clear();list6.clear();list7.clear();list8.clear();list9.clear();list10.clear(); list11.clear();list12.clear(); list13.clear(); list14.clear(); list15.clear();list16.clear();list17.clear();list18.clear();list19.clear();list20.clear();list21.clear();list22.clear();list23.clear();list24.clear();list25.clear();list26.clear();
		slist1.clear(); slist2.clear(); slist3.clear(); slist4.clear(); slist5.clear();slist6.clear();slist7.clear();slist8.clear();slist9.clear();slist10.clear(); slist11.clear();slist12.clear(); slist13.clear(); slist14.clear(); slist15.clear();slist16.clear();slist17.clear();slist18.clear();slist19.clear();slist20.clear();slist21.clear();slist22.clear();slist23.clear();slist24.clear();slist25.clear();slist26.clear();
		tlist1.clear(); tlist2.clear(); tlist3.clear(); tlist4.clear(); tlist5.clear();tlist6.clear();tlist7.clear();tlist8.clear();tlist9.clear();tlist10.clear(); tlist11.clear();tlist12.clear(); tlist13.clear(); tlist14.clear(); tlist15.clear();tlist16.clear();tlist17.clear();tlist18.clear();tlist19.clear();tlist20.clear();tlist21.clear();tlist22.clear();tlist23.clear();tlist24.clear();tlist25.clear();tlist26.clear();
		flist1.clear(); flist2.clear(); flist3.clear(); flist4.clear(); flist5.clear();flist6.clear();flist7.clear();flist8.clear();flist9.clear();flist10.clear(); flist11.clear();flist12.clear(); flist13.clear(); flist14.clear(); flist15.clear();flist16.clear();flist17.clear();flist18.clear();flist19.clear();flist20.clear();flist21.clear();flist22.clear();flist23.clear();flist24.clear();flist25.clear();flist26.clear();
		largestlist.add(list1); largestlist.add(list2); largestlist.add(list3); largestlist.add(list4);
		largestlist.add(list5); largestlist.add(list6); largestlist.add(list7); largestlist.add(list8);
		largestlist.add(list9); largestlist.add(list10); largestlist.add(list11); largestlist.add(list12);
		largestlist.add(list13); largestlist.add(list14); largestlist.add(list15); largestlist.add(list16);
		largestlist.add(list17); largestlist.add(list18); largestlist.add(list19); largestlist.add(list20);
		largestlist.add(list21); largestlist.add(list22); largestlist.add(list23); largestlist.add(list24);
		largestlist.add(list25); largestlist.add(list26);
		slargestlist.add(slist1); slargestlist.add(slist2); slargestlist.add(slist3); slargestlist.add(slist4);
		slargestlist.add(slist5); slargestlist.add(slist6); slargestlist.add(slist7); slargestlist.add(slist8);
		slargestlist.add(slist9); slargestlist.add(slist10); slargestlist.add(slist11); slargestlist.add(slist12);
		slargestlist.add(slist13); slargestlist.add(slist14); slargestlist.add(slist15); slargestlist.add(slist16);
		slargestlist.add(slist17); slargestlist.add(slist18); slargestlist.add(slist19); slargestlist.add(slist20);
		slargestlist.add(slist21); slargestlist.add(slist22); slargestlist.add(slist23); slargestlist.add(slist24);
		slargestlist.add(slist25); slargestlist.add(slist26);
		tlargestlist.add(tlist1); tlargestlist.add(tlist2); tlargestlist.add(tlist3); tlargestlist.add(tlist4);
		tlargestlist.add(tlist5); tlargestlist.add(tlist6); tlargestlist.add(tlist7); tlargestlist.add(tlist8);
		tlargestlist.add(tlist9); tlargestlist.add(tlist10); tlargestlist.add(tlist11); tlargestlist.add(tlist12);
		tlargestlist.add(tlist13); tlargestlist.add(tlist14); tlargestlist.add(tlist15); tlargestlist.add(tlist16);
		tlargestlist.add(tlist17); tlargestlist.add(tlist18); tlargestlist.add(tlist19); tlargestlist.add(tlist20);
		tlargestlist.add(tlist21); tlargestlist.add(tlist22); tlargestlist.add(tlist23); tlargestlist.add(tlist24);
		tlargestlist.add(tlist25); tlargestlist.add(tlist26);
		flargestlist.add(flist1); flargestlist.add(flist2); flargestlist.add(flist3); flargestlist.add(flist4);
		flargestlist.add(flist5); flargestlist.add(flist6); flargestlist.add(flist7); flargestlist.add(flist8);
		flargestlist.add(flist9); flargestlist.add(flist10); flargestlist.add(flist11); flargestlist.add(flist12);
		flargestlist.add(flist13); flargestlist.add(flist14); flargestlist.add(flist15); flargestlist.add(flist16);
		flargestlist.add(flist17); flargestlist.add(flist18); flargestlist.add(flist19); flargestlist.add(flist20);
		flargestlist.add(flist21); flargestlist.add(flist22); flargestlist.add(flist23); flargestlist.add(flist24);
		flargestlist.add(flist25); flargestlist.add(flist26);
		ArrayList<ArrayList<ArrayList<String>>> separatelist2 = new ArrayList<ArrayList<ArrayList<String>>>(4);	
		separatelist2.add(largestlist); separatelist2.add(slargestlist); separatelist2.add(tlargestlist); separatelist2.add(flargestlist);
		
	for (int indexa = 0; indexa < comparelist.size(); indexa++){

		for (int abc = 0; abc < comparelist.get(indexa).size(); abc++){	
			if( comparelist.get(indexa).get(abc).length() > (typedletters + 1)){
				String compareletter = "a";	
				String secondletter = null;
				if (typedletters == 1){
					secondletter = comparelist.get(indexa).get(abc).substring(2, 3);
					}else if((typedletters == 2)&(comparelist.get(indexa).get(abc).length()>3)){
					secondletter = comparelist.get(indexa).get(abc).substring(3, 4);
					}else if((typedletters == 3)&(comparelist.get(indexa).get(abc).length()>4)){
					secondletter = comparelist.get(indexa).get(abc).substring(4, 5);
					}else if(typedletters == 4){
						if(comparelist.get(indexa).get(abc).length()>5){
						//System.out.println("qwer");
						secondletter = comparelist.get(indexa).get(abc).substring(5, 6);}else{
						//	System.out.println(comparelist.get(indexa).get(abc));
						secondletter = "0";}
					}else if(typedletters == 5){
						if(comparelist.get(indexa).get(abc).length()>6){
							secondletter = comparelist.get(indexa).get(abc).substring(6, 7);}else{
							secondletter = "0";}					
					}else if(typedletters == 6){
						if(comparelist.get(indexa).get(abc).length()>7){
							secondletter = comparelist.get(indexa).get(abc).substring(7, 8);}else{
							secondletter = "0";}					
					}else if(typedletters == 7){
						if(comparelist.get(indexa).get(abc).length()>8){
						secondletter = comparelist.get(indexa).get(abc).substring(8, 9);}else{
						secondletter = "0";}					
					}else if(typedletters == 8){
						if(comparelist.get(indexa).get(abc).length()>9){
							secondletter = comparelist.get(indexa).get(abc).substring(9, 10);}else{
							secondletter = "0";}					
					}

			//secondletter = comparelist.get(indexa).get(abc).substring(2, 3);
			if (secondletter.equalsIgnoreCase(compareletter)){
				separatelist2.get(indexa).get(0).add(comparelist.get(indexa).get(abc));
				//System.out.println(separatelist2.get(indexa).get(0).get(0));
			} else {
				compareletter = "b";
				if(secondletter.equalsIgnoreCase(compareletter)){
					separatelist2.get(indexa).get(1).add(comparelist.get(indexa).get(abc));
				}else{
					compareletter = "c";
					if(secondletter.equalsIgnoreCase(compareletter)){
						separatelist2.get(indexa).get(2).add(comparelist.get(indexa).get(abc));
					}else{
						compareletter = "d";
						if(secondletter.equalsIgnoreCase(compareletter)){
							separatelist2.get(indexa).get(3).add(comparelist.get(indexa).get(abc));
						}else{
							compareletter = "e";
							if(secondletter.equalsIgnoreCase(compareletter)){
								separatelist2.get(indexa).get(4).add(comparelist.get(indexa).get(abc));
							}else{
								compareletter = "f";
								if(secondletter.equalsIgnoreCase(compareletter)){
									separatelist2.get(indexa).get(5).add(comparelist.get(indexa).get(abc));
								}else{
									compareletter = "g";
									if(secondletter.equalsIgnoreCase(compareletter)){
										separatelist2.get(indexa).get(6).add(comparelist.get(indexa).get(abc));
									}else{
										compareletter = "h";
										if(secondletter.equalsIgnoreCase(compareletter)){
											separatelist2.get(indexa).get(7).add(comparelist.get(indexa).get(abc));
										}else{
											compareletter = "i";
											if(secondletter.equalsIgnoreCase(compareletter)){
												separatelist2.get(indexa).get(8).add(comparelist.get(indexa).get(abc));
											}else{
												compareletter = "j";
												if(secondletter.equalsIgnoreCase(compareletter)){
													separatelist2.get(indexa).get(9).add(comparelist.get(indexa).get(abc));
												}else{
													compareletter = "k";
													if(secondletter.equalsIgnoreCase(compareletter)){
														separatelist2.get(indexa).get(10).add(comparelist.get(indexa).get(abc));
													}else{
														compareletter = "l";
														if(secondletter.equalsIgnoreCase(compareletter)){
															separatelist2.get(indexa).get(11).add(comparelist.get(indexa).get(abc));
														}else{
															compareletter = "m";
															if(secondletter.equalsIgnoreCase(compareletter)){
																separatelist2.get(indexa).get(12).add(comparelist.get(indexa).get(abc));
															}else{
																compareletter = "n";
																if(secondletter.equalsIgnoreCase(compareletter)){
																	separatelist2.get(indexa).get(13).add(comparelist.get(indexa).get(abc));
																}else{
																	compareletter = "o";
																	if(secondletter.equalsIgnoreCase(compareletter)){
																		separatelist2.get(indexa).get(14).add(comparelist.get(indexa).get(abc));
																	}else{
																		compareletter = "p";
																		if(secondletter.equalsIgnoreCase(compareletter)){
																			separatelist2.get(indexa).get(15).add(comparelist.get(indexa).get(abc));
																		}else{
																			compareletter = "q";
																			if(secondletter.equalsIgnoreCase(compareletter)){
																				separatelist2.get(indexa).get(16).add(comparelist.get(indexa).get(abc));
																			}else{
																				compareletter = "r";
																				if(secondletter.equalsIgnoreCase(compareletter)){
																					separatelist2.get(indexa).get(17).add(comparelist.get(indexa).get(abc));
																				}else{
																					compareletter = "s";
																					if(secondletter.equalsIgnoreCase(compareletter)){
																						separatelist2.get(indexa).get(18).add(comparelist.get(indexa).get(abc));
																					}else{
																						compareletter = "t";
																						if(secondletter.equalsIgnoreCase(compareletter)){
																							separatelist2.get(indexa).get(19).add(comparelist.get(indexa).get(abc));
																						}else{
																							compareletter = "u";
																							if(secondletter.equalsIgnoreCase(compareletter)){
																								separatelist2.get(indexa).get(20).add(comparelist.get(indexa).get(abc));
																							}else{
																								compareletter = "v";
																								if(secondletter.equalsIgnoreCase(compareletter)){
																									separatelist2.get(indexa).get(21).add(comparelist.get(indexa).get(abc));
																								}else{
																									compareletter = "w";
																									if(secondletter.equalsIgnoreCase(compareletter)){
																										separatelist2.get(indexa).get(22).add(comparelist.get(indexa).get(abc));
																									}else{
																										compareletter = "x";
																										if(secondletter.equalsIgnoreCase(compareletter)){
																											separatelist2.get(indexa).get(23).add(comparelist.get(indexa).get(abc));
																										}else{
																											compareletter = "y";
																											if(secondletter.equalsIgnoreCase(compareletter)){
																												separatelist2.get(indexa).get(24).add(comparelist.get(indexa).get(abc));
																											}else{
																												compareletter = "z";
																												if(secondletter.equalsIgnoreCase(compareletter)){
																													separatelist2.get(indexa).get(25).add(comparelist.get(indexa).get(abc));
																												}	
																											}		
																										}		
																									}		
																								}		
																							}		
																						}		
																					}		
																				}		
																			}		
																		}		
																	}		
																}		
															}		
														}		
													}		
												}		
											}		
										}		
									}		
								}		
							}		
						}		
					}		
				}				
			}
			}
		}
	}
		//System.out.println(separatelist2.get(2).get(1));


	//Store the sizes of 26 third letters of four most frequent 2nd letter
	int[] ssize1 = new int[26];
	//int[] ssize0 = {separatelist2.get(0).get(0).size()};
	for (int qw=0; qw<=25; qw++){
		int as = separatelist2.get(0).get(qw).size();
		ssize1[qw] = as;
	}
	int[] ssize2 = new int[26];
	//int[] ssize0 = {separatelist2.get(0).get(0).size()};
	for (int qw=0; qw<=25; qw++){
		int as = separatelist2.get(1).get(qw).size();
		ssize2[qw] = as;
	}
	int[] ssize3 = new int[26];
	//int[] ssize0 = {separatelist2.get(0).get(0).size()};
	for (int qw=0; qw<=25; qw++){
		int as = separatelist2.get(2).get(qw).size();
		ssize3[qw] = as;
	}
	int[] ssize4 = new int[26];
	//int[] ssize0 = {separatelist2.get(0).get(0).size()};
	for (int qw=0; qw<=25; qw++){
		int as = separatelist2.get(3).get(qw).size();
		ssize4[qw] = as;
	}
	
	//Find the most frequent 3rd letters
	int max21 = ssize1[25]; int srlargest1 = 25;
	int smax21 = ssize1[25]; int srslargest1 = 25;
	int max22 = ssize2[25]; int srlargest2 = 25;
	int smax22 = ssize2[25]; int srslargest2 = 25;
	int max23 = ssize3[25]; int srlargest3 = 25;
	int smax23 = ssize3[25]; int srslargest3 = 25;
	int max24 = ssize4[25]; int srlargest4 = 25;
	int smax24 = ssize4[25]; int srslargest4 = 25;
	String largestn21 =  null;
	String slargestn21 =  null;
	String largestn22 =  null;
	String slargestn22 =  null;
	String largestn23 =  null;
	String slargestn23 =  null;
	String largestn24 =  null;
	String slargestn24 =  null;

	for (int ab=0; ab<=25; ab++){
		if (ssize1[ab] >= max21){
			srlargest1 = ab;
			max21 = ssize1[ab];
		}
		largestn21 = name[srlargest1];
	}
	for (int ab=0; ab<=25; ab++){
		if ((ab != srlargest1 )&&(ssize1[ab] >= smax21)){
			srslargest1 = ab;
			smax21 = ssize1[ab];
		}
		slargestn21 = name[srslargest1];
	}
	for (int ab=0; ab<=25; ab++){
		if (ssize2[ab] >= max22){
			srlargest2 = ab;
			max22 = ssize2[ab];
		}
		largestn22 = name[srlargest2];
	}
	for (int ab=0; ab<=25; ab++){
		if ((ab != srlargest2 )&&(ssize2[ab] >= smax22)){
			srslargest2 = ab;
			smax22 = ssize2[ab];
		}
		slargestn22 = name[srslargest2];
	}
	for (int ab=0; ab<=25; ab++){
		if (ssize3[ab] >= max23){
			srlargest3 = ab;
			max23 = ssize3[ab];
		}
		largestn23 = name[srlargest3];
	}
	for (int ab=0; ab<=25; ab++){
		if ((ab != srlargest3 )&&(ssize3[ab] >= smax23)){
			srslargest3 = ab;
			smax23 = ssize3[ab];
		}
		slargestn23 = name[srslargest3];
	}
	for (int ab=0; ab<=25; ab++){
		if (ssize4[ab] >= max24){
			srlargest4 = ab;
			max24 = ssize4[ab];
		}
		largestn24 = name[srlargest4];
	}
	for (int ab=0; ab<=25; ab++){
		if ((ab != srlargest4 )&&(ssize4[ab] >= smax24)){
			srslargest4 = ab;
			smax24 = ssize4[ab];
		}
		slargestn24 = name[srslargest4];
	}
	
		
		//The third ring
		//Variable list for the loop
		ArrayList<ArrayList<String>> comparelist3 = new ArrayList<ArrayList<String>>(8);
		comparelist3.clear();
		comparelist3.add(separatelist2.get(0).get(srlargest1));		
		comparelist3.add(separatelist2.get(0).get(srslargest1));
		comparelist3.add(separatelist2.get(1).get(srlargest2));
		comparelist3.add(separatelist2.get(1).get(srslargest2));
		comparelist3.add(separatelist2.get(2).get(srlargest3));
		comparelist3.add(separatelist2.get(2).get(srslargest3));
		comparelist3.add(separatelist2.get(3).get(srlargest4));
		comparelist3.add(separatelist2.get(3).get(srslargest4));
		
		ArrayList<ArrayList<String>> trlargestlist11 = new ArrayList<ArrayList<String>>(26); trlargestlist11.clear();//first ring most frequent letter with second ring most frequent one (foa)
		ArrayList<ArrayList<String>> trlargestlist12 = new ArrayList<ArrayList<String>>(26); trlargestlist12.clear();//(fob) assume a, b are the most and second most frequent after fo
		ArrayList<ArrayList<String>> trlargestlist21 = new ArrayList<ArrayList<String>>(26); trlargestlist21.clear();//(fla)
		ArrayList<ArrayList<String>> trlargestlist22 = new ArrayList<ArrayList<String>>(26); trlargestlist22.clear();//(flb)
		ArrayList<ArrayList<String>> trlargestlist31 = new ArrayList<ArrayList<String>>(26); trlargestlist31.clear();
		ArrayList<ArrayList<String>> trlargestlist32 = new ArrayList<ArrayList<String>>(26); trlargestlist32.clear();
		ArrayList<ArrayList<String>> trlargestlist41 = new ArrayList<ArrayList<String>>(26); trlargestlist41.clear();
		ArrayList<ArrayList<String>> trlargestlist42 = new ArrayList<ArrayList<String>>(26); trlargestlist42.clear();
		ArrayList<String> t1list1 = new ArrayList<String>();ArrayList<String> t1list2 = new ArrayList<String>(); ArrayList<String> t1list3 = new ArrayList<String>();ArrayList<String> t1list4 = new ArrayList<String>();ArrayList<String> t1list5 = new ArrayList<String>();ArrayList<String> t1list6 = new ArrayList<String>();ArrayList<String> t1list7 = new ArrayList<String>();ArrayList<String> t1list8 = new ArrayList<String>();ArrayList<String> t1list9 = new ArrayList<String>();ArrayList<String> t1list10 = new ArrayList<String>();ArrayList<String> t1list11 = new ArrayList<String>();ArrayList<String> t1list12 = new ArrayList<String>();ArrayList<String> t1list13 = new ArrayList<String>();ArrayList<String> t1list14 = new ArrayList<String>();ArrayList<String> t1list15 = new ArrayList<String>();ArrayList<String> t1list16 = new ArrayList<String>();ArrayList<String> t1list17 = new ArrayList<String>();ArrayList<String> t1list18 = new ArrayList<String>();ArrayList<String> t1list19 = new ArrayList<String>();ArrayList<String> t1list20 = new ArrayList<String>();ArrayList<String> t1list21 = new ArrayList<String>();ArrayList<String> t1list22 = new ArrayList<String>();ArrayList<String> t1list23 = new ArrayList<String>();ArrayList<String> t1list24 = new ArrayList<String>();ArrayList<String> t1list25 = new ArrayList<String>();ArrayList<String> t1list26 = new ArrayList<String>();
		ArrayList<String> tslist1 = new ArrayList<String>();ArrayList<String> tslist2 = new ArrayList<String>();ArrayList<String> tslist3 = new ArrayList<String>();ArrayList<String> tslist4 = new ArrayList<String>();ArrayList<String> tslist5 = new ArrayList<String>();ArrayList<String> tslist6 = new ArrayList<String>();ArrayList<String> tslist7 = new ArrayList<String>();ArrayList<String> tslist8 = new ArrayList<String>();ArrayList<String> tslist9 = new ArrayList<String>();ArrayList<String> tslist10 = new ArrayList<String>();ArrayList<String> tslist11 = new ArrayList<String>();ArrayList<String> tslist12 = new ArrayList<String>();ArrayList<String> tslist13 = new ArrayList<String>();ArrayList<String> tslist14 = new ArrayList<String>();ArrayList<String> tslist15 = new ArrayList<String>();ArrayList<String> tslist16 = new ArrayList<String>();ArrayList<String> tslist17 = new ArrayList<String>();ArrayList<String> tslist18 = new ArrayList<String>();ArrayList<String> tslist19 = new ArrayList<String>();ArrayList<String> tslist20 = new ArrayList<String>();ArrayList<String> tslist21 = new ArrayList<String>();ArrayList<String> tslist22 = new ArrayList<String>();ArrayList<String> tslist23 = new ArrayList<String>();ArrayList<String> tslist24 = new ArrayList<String>();ArrayList<String> tslist25 = new ArrayList<String>();ArrayList<String> tslist26 = new ArrayList<String>();
        ArrayList<String> ttlist1 = new ArrayList<String>();ArrayList<String> ttlist2 = new ArrayList<String>();ArrayList<String> ttlist3 = new ArrayList<String>();ArrayList<String> ttlist4 = new ArrayList<String>();ArrayList<String> ttlist5 = new ArrayList<String>();ArrayList<String> ttlist6 = new ArrayList<String>();ArrayList<String> ttlist7 = new ArrayList<String>();ArrayList<String> ttlist8 = new ArrayList<String>();ArrayList<String> ttlist9 = new ArrayList<String>();ArrayList<String> ttlist10 = new ArrayList<String>();ArrayList<String> ttlist11 = new ArrayList<String>();ArrayList<String> ttlist12 = new ArrayList<String>();ArrayList<String> ttlist13 = new ArrayList<String>();ArrayList<String> ttlist14 = new ArrayList<String>();ArrayList<String> ttlist15 = new ArrayList<String>();ArrayList<String> ttlist16 = new ArrayList<String>();ArrayList<String> ttlist17 = new ArrayList<String>();ArrayList<String> ttlist18 = new ArrayList<String>();ArrayList<String> ttlist19 = new ArrayList<String>();ArrayList<String> ttlist20 = new ArrayList<String>();ArrayList<String> ttlist21 = new ArrayList<String>();ArrayList<String> ttlist22 = new ArrayList<String>();ArrayList<String> ttlist23 = new ArrayList<String>();ArrayList<String> ttlist24 = new ArrayList<String>();ArrayList<String> ttlist25 = new ArrayList<String>();ArrayList<String> ttlist26 = new ArrayList<String>();
		ArrayList<String> tflist1 = new ArrayList<String>();ArrayList<String> tflist2 = new ArrayList<String>();ArrayList<String> tflist3 = new ArrayList<String>();ArrayList<String> tflist4 = new ArrayList<String>();ArrayList<String> tflist5 = new ArrayList<String>();ArrayList<String> tflist6 = new ArrayList<String>();ArrayList<String> tflist7 = new ArrayList<String>();ArrayList<String> tflist8 = new ArrayList<String>();ArrayList<String> tflist9 = new ArrayList<String>();ArrayList<String> tflist10 = new ArrayList<String>();ArrayList<String> tflist11 = new ArrayList<String>();ArrayList<String> tflist12 = new ArrayList<String>();ArrayList<String> tflist13 = new ArrayList<String>();ArrayList<String> tflist14 = new ArrayList<String>();ArrayList<String> tflist15 = new ArrayList<String>();ArrayList<String> tflist16 = new ArrayList<String>();ArrayList<String> tflist17 = new ArrayList<String>();ArrayList<String> tflist18 = new ArrayList<String>();ArrayList<String> tflist19 = new ArrayList<String>();ArrayList<String> tflist20 = new ArrayList<String>();ArrayList<String> tflist21 = new ArrayList<String>();ArrayList<String> tflist22 = new ArrayList<String>();ArrayList<String> tflist23 = new ArrayList<String>();ArrayList<String> tflist24 = new ArrayList<String>();ArrayList<String> tflist25 = new ArrayList<String>();ArrayList<String> tflist26 = new ArrayList<String>();
		ArrayList<String> t5list1 = new ArrayList<String>();ArrayList<String> t5list2 = new ArrayList<String>(); ArrayList<String> t5list3 = new ArrayList<String>();ArrayList<String> t5list4 = new ArrayList<String>();ArrayList<String> t5list5 = new ArrayList<String>();ArrayList<String> t5list6 = new ArrayList<String>();ArrayList<String> t5list7 = new ArrayList<String>();ArrayList<String> t5list8 = new ArrayList<String>();ArrayList<String> t5list9 = new ArrayList<String>();ArrayList<String> t5list10 = new ArrayList<String>();ArrayList<String> t5list11 = new ArrayList<String>();ArrayList<String> t5list12 = new ArrayList<String>();ArrayList<String> t5list13 = new ArrayList<String>();ArrayList<String> t5list14 = new ArrayList<String>();ArrayList<String> t5list15 = new ArrayList<String>();ArrayList<String> t5list16 = new ArrayList<String>();ArrayList<String> t5list17 = new ArrayList<String>();ArrayList<String> t5list18 = new ArrayList<String>();ArrayList<String> t5list19 = new ArrayList<String>();ArrayList<String> t5list20 = new ArrayList<String>();ArrayList<String> t5list21 = new ArrayList<String>();ArrayList<String> t5list22 = new ArrayList<String>();ArrayList<String> t5list23 = new ArrayList<String>();ArrayList<String> t5list24 = new ArrayList<String>();ArrayList<String> t5list25 = new ArrayList<String>();ArrayList<String> t5list26 = new ArrayList<String>();
		ArrayList<String> t6list1 = new ArrayList<String>();ArrayList<String> t6list2 = new ArrayList<String>();ArrayList<String> t6list3 = new ArrayList<String>();ArrayList<String> t6list4 = new ArrayList<String>();ArrayList<String> t6list5 = new ArrayList<String>();ArrayList<String> t6list6 = new ArrayList<String>();ArrayList<String> t6list7 = new ArrayList<String>();ArrayList<String> t6list8 = new ArrayList<String>();ArrayList<String> t6list9 = new ArrayList<String>();ArrayList<String> t6list10 = new ArrayList<String>();ArrayList<String> t6list11 = new ArrayList<String>();ArrayList<String> t6list12 = new ArrayList<String>();ArrayList<String> t6list13 = new ArrayList<String>();ArrayList<String> t6list14 = new ArrayList<String>();ArrayList<String> t6list15 = new ArrayList<String>();ArrayList<String> t6list16 = new ArrayList<String>();ArrayList<String> t6list17 = new ArrayList<String>();ArrayList<String> t6list18 = new ArrayList<String>();ArrayList<String> t6list19 = new ArrayList<String>();ArrayList<String> t6list20 = new ArrayList<String>();ArrayList<String> t6list21 = new ArrayList<String>();ArrayList<String> t6list22 = new ArrayList<String>();ArrayList<String> t6list23 = new ArrayList<String>();ArrayList<String> t6list24 = new ArrayList<String>();ArrayList<String> t6list25 = new ArrayList<String>();ArrayList<String> t6list26 = new ArrayList<String>();
        ArrayList<String> t7list1 = new ArrayList<String>();ArrayList<String> t7list2 = new ArrayList<String>();ArrayList<String> t7list3 = new ArrayList<String>();ArrayList<String> t7list4 = new ArrayList<String>();ArrayList<String> t7list5 = new ArrayList<String>();ArrayList<String> t7list6 = new ArrayList<String>();ArrayList<String> t7list7 = new ArrayList<String>();ArrayList<String> t7list8 = new ArrayList<String>();ArrayList<String> t7list9 = new ArrayList<String>();ArrayList<String> t7list10 = new ArrayList<String>();ArrayList<String> t7list11 = new ArrayList<String>();ArrayList<String> t7list12 = new ArrayList<String>();ArrayList<String> t7list13 = new ArrayList<String>();ArrayList<String> t7list14 = new ArrayList<String>();ArrayList<String> t7list15 = new ArrayList<String>();ArrayList<String> t7list16 = new ArrayList<String>();ArrayList<String> t7list17 = new ArrayList<String>();ArrayList<String> t7list18 = new ArrayList<String>();ArrayList<String> t7list19 = new ArrayList<String>();ArrayList<String> t7list20 = new ArrayList<String>();ArrayList<String> t7list21 = new ArrayList<String>();ArrayList<String> t7list22 = new ArrayList<String>();ArrayList<String> t7list23 = new ArrayList<String>();ArrayList<String> t7list24 = new ArrayList<String>();ArrayList<String> t7list25 = new ArrayList<String>();ArrayList<String> t7list26 = new ArrayList<String>();
		ArrayList<String> t8list1 = new ArrayList<String>();ArrayList<String> t8list2 = new ArrayList<String>();ArrayList<String> t8list3 = new ArrayList<String>();ArrayList<String> t8list4 = new ArrayList<String>();ArrayList<String> t8list5 = new ArrayList<String>();ArrayList<String> t8list6 = new ArrayList<String>();ArrayList<String> t8list7 = new ArrayList<String>();ArrayList<String> t8list8 = new ArrayList<String>();ArrayList<String> t8list9 = new ArrayList<String>();ArrayList<String> t8list10 = new ArrayList<String>();ArrayList<String> t8list11 = new ArrayList<String>();ArrayList<String> t8list12 = new ArrayList<String>();ArrayList<String> t8list13 = new ArrayList<String>();ArrayList<String> t8list14 = new ArrayList<String>();ArrayList<String> t8list15 = new ArrayList<String>();ArrayList<String> t8list16 = new ArrayList<String>();ArrayList<String> t8list17 = new ArrayList<String>();ArrayList<String> t8list18 = new ArrayList<String>();ArrayList<String> t8list19 = new ArrayList<String>();ArrayList<String> t8list20 = new ArrayList<String>();ArrayList<String> t8list21 = new ArrayList<String>();ArrayList<String> t8list22 = new ArrayList<String>();ArrayList<String> t8list23 = new ArrayList<String>();ArrayList<String> t8list24 = new ArrayList<String>();ArrayList<String> t8list25 = new ArrayList<String>();ArrayList<String> t8list26 = new ArrayList<String>();
		t1list1.clear(); t1list2.clear(); t1list3.clear(); t1list4.clear(); t1list5.clear();t1list6.clear();t1list7.clear();t1list8.clear();t1list9.clear();t1list10.clear(); t1list11.clear();t1list12.clear(); t1list13.clear(); t1list14.clear(); t1list15.clear();t1list16.clear();t1list17.clear();t1list18.clear();t1list19.clear();t1list20.clear();t1list21.clear();t1list22.clear();t1list23.clear();t1list24.clear();t1list25.clear();t1list26.clear();
		tslist1.clear(); tslist2.clear(); tslist3.clear(); tslist4.clear(); tslist5.clear();tslist6.clear();tslist7.clear();tslist8.clear();tslist9.clear();tslist10.clear(); tslist11.clear();tslist12.clear(); tslist13.clear(); tslist14.clear(); tslist15.clear();tslist16.clear();tslist17.clear();tslist18.clear();tslist19.clear();tslist20.clear();tslist21.clear();tslist22.clear();tslist23.clear();tslist24.clear();tslist25.clear();tslist26.clear();
		ttlist1.clear(); ttlist2.clear(); ttlist3.clear(); ttlist4.clear(); ttlist5.clear();ttlist6.clear();ttlist7.clear();ttlist8.clear();ttlist9.clear();ttlist10.clear(); ttlist11.clear();ttlist12.clear(); ttlist13.clear(); ttlist14.clear(); ttlist15.clear();ttlist16.clear();ttlist17.clear();ttlist18.clear();ttlist19.clear();ttlist20.clear();ttlist21.clear();ttlist22.clear();ttlist23.clear();ttlist24.clear();ttlist25.clear();ttlist26.clear();
		tflist1.clear(); tflist2.clear(); tflist3.clear(); tflist4.clear(); tflist5.clear();tflist6.clear();tflist7.clear();tflist8.clear();tflist9.clear();tflist10.clear(); tflist11.clear();tflist12.clear(); tflist13.clear(); tflist14.clear(); tflist15.clear();tflist16.clear();tflist17.clear();tflist18.clear();tflist19.clear();tflist20.clear();tflist21.clear();tflist22.clear();tflist23.clear();tflist24.clear();tflist25.clear();tflist26.clear();
		t5list1.clear(); t5list2.clear(); t5list3.clear(); t5list4.clear(); t5list5.clear();t5list6.clear();t5list7.clear();t5list8.clear();t5list9.clear();t5list10.clear(); t5list11.clear();t5list12.clear(); t5list13.clear(); t5list14.clear(); t5list15.clear();t5list16.clear();t5list17.clear();t5list18.clear();t5list19.clear();t5list20.clear();t5list21.clear();t5list22.clear();t5list23.clear();t5list24.clear();t5list25.clear();t5list26.clear();
		t6list1.clear(); t6list2.clear(); t6list3.clear(); t6list4.clear(); t6list5.clear();t6list6.clear();t6list7.clear();t6list8.clear();t6list9.clear();t6list10.clear(); t6list11.clear();t6list12.clear(); t6list13.clear(); t6list14.clear(); t6list15.clear();t6list16.clear();t6list17.clear();t6list18.clear();t6list19.clear();t6list20.clear();t6list21.clear();t6list22.clear();t6list23.clear();t6list24.clear();t6list25.clear();t6list26.clear();
		t7list1.clear(); t7list2.clear(); t7list3.clear(); t7list4.clear(); t7list5.clear();t7list6.clear();t7list7.clear();t7list8.clear();t7list9.clear();t7list10.clear(); t7list11.clear();t7list12.clear(); t7list13.clear(); t7list14.clear(); t7list15.clear();t7list16.clear();t7list17.clear();t7list18.clear();t7list19.clear();t7list20.clear();t7list21.clear();t7list22.clear();t7list23.clear();t7list24.clear();t7list25.clear();t7list26.clear();
		t8list1.clear(); t8list2.clear(); t8list3.clear(); t8list4.clear(); t8list5.clear();t8list6.clear();t8list7.clear();t8list8.clear();t8list9.clear();t8list10.clear(); t8list11.clear();t8list12.clear(); t8list13.clear(); t8list14.clear(); t8list15.clear();t8list16.clear();t8list17.clear();t8list18.clear();t8list19.clear();t8list20.clear();t8list21.clear();t8list22.clear();t8list23.clear();t8list24.clear();t8list25.clear();t8list26.clear();

		
		trlargestlist11.add(t1list1); trlargestlist11.add(t1list2); trlargestlist11.add(t1list3); trlargestlist11.add(t1list4);
		trlargestlist11.add(t1list5); trlargestlist11.add(t1list6); trlargestlist11.add(t1list7); trlargestlist11.add(t1list8);
		trlargestlist11.add(t1list9); trlargestlist11.add(t1list10); trlargestlist11.add(t1list11); trlargestlist11.add(t1list12);
		trlargestlist11.add(t1list13); trlargestlist11.add(t1list14); trlargestlist11.add(t1list15); trlargestlist11.add(t1list16);
		trlargestlist11.add(t1list17); trlargestlist11.add(t1list18); trlargestlist11.add(t1list19); trlargestlist11.add(t1list20);
		trlargestlist11.add(t1list21); trlargestlist11.add(t1list22); trlargestlist11.add(t1list23); trlargestlist11.add(t1list24);
		trlargestlist11.add(t1list25); trlargestlist11.add(t1list26);
		trlargestlist12.add(tslist1); trlargestlist12.add(tslist2); trlargestlist12.add(tslist3); trlargestlist12.add(tslist4);
		trlargestlist12.add(tslist5); trlargestlist12.add(tslist6); trlargestlist12.add(tslist7); trlargestlist12.add(tslist8);
		trlargestlist12.add(tslist9); trlargestlist12.add(tslist10); trlargestlist12.add(tslist11); trlargestlist12.add(tslist12);
		trlargestlist12.add(tslist13); trlargestlist12.add(tslist14); trlargestlist12.add(tslist15); trlargestlist12.add(tslist16);
		trlargestlist12.add(tslist17); trlargestlist12.add(tslist18); trlargestlist12.add(tslist19); trlargestlist12.add(tslist20);
		trlargestlist12.add(tslist21); trlargestlist12.add(tslist22); trlargestlist12.add(tslist23); trlargestlist12.add(tslist24);
		trlargestlist12.add(tslist25); trlargestlist12.add(tslist26);
		trlargestlist21.add(ttlist1); trlargestlist21.add(ttlist2); trlargestlist21.add(ttlist3); trlargestlist21.add(ttlist4);
		trlargestlist21.add(ttlist5); trlargestlist21.add(ttlist6); trlargestlist21.add(ttlist7); trlargestlist21.add(ttlist8);
		trlargestlist21.add(ttlist9); trlargestlist21.add(ttlist10); trlargestlist21.add(ttlist11); trlargestlist21.add(ttlist12);
		trlargestlist21.add(ttlist13); trlargestlist21.add(ttlist14); trlargestlist21.add(ttlist15); trlargestlist21.add(ttlist16);
		trlargestlist21.add(ttlist17); trlargestlist21.add(ttlist18); trlargestlist21.add(ttlist19); trlargestlist21.add(ttlist20);
		trlargestlist21.add(ttlist21); trlargestlist21.add(ttlist22); trlargestlist21.add(ttlist23); trlargestlist21.add(ttlist24);
		trlargestlist21.add(ttlist25); trlargestlist21.add(ttlist26);
		trlargestlist22.add(tflist1); trlargestlist22.add(tflist2); trlargestlist22.add(tflist3); trlargestlist22.add(tflist4);
		trlargestlist22.add(tflist5); trlargestlist22.add(tflist6); trlargestlist22.add(tflist7); trlargestlist22.add(tflist8);
		trlargestlist22.add(tflist9); trlargestlist22.add(tflist10); trlargestlist22.add(tflist11); trlargestlist22.add(tflist12);
		trlargestlist22.add(tflist13); trlargestlist22.add(tflist14); trlargestlist22.add(tflist15); trlargestlist22.add(tflist16);
		trlargestlist22.add(tflist17); trlargestlist22.add(tflist18); trlargestlist22.add(tflist19); trlargestlist22.add(tflist20);
		trlargestlist22.add(tflist21); trlargestlist22.add(tflist22); trlargestlist22.add(tflist23); trlargestlist22.add(tflist24);
		trlargestlist22.add(tflist25); trlargestlist22.add(tflist26);
		trlargestlist31.add(t5list1); trlargestlist31.add(t5list2); trlargestlist31.add(t5list3); trlargestlist31.add(t5list4);
		trlargestlist31.add(t5list5); trlargestlist31.add(t5list6); trlargestlist31.add(t5list7); trlargestlist31.add(t5list8);
		trlargestlist31.add(t5list9); trlargestlist31.add(t5list10); trlargestlist31.add(t5list11); trlargestlist31.add(t5list12);
		trlargestlist31.add(t5list13); trlargestlist31.add(t5list14); trlargestlist31.add(t5list15); trlargestlist31.add(t5list16);
		trlargestlist31.add(t5list17); trlargestlist31.add(t5list18); trlargestlist31.add(t5list19); trlargestlist31.add(t5list20);
		trlargestlist31.add(t5list21); trlargestlist31.add(t5list22); trlargestlist31.add(t5list23); trlargestlist31.add(t5list24);
		trlargestlist31.add(t5list25); trlargestlist31.add(t5list26);
		trlargestlist32.add(t6list1); trlargestlist32.add(t6list2); trlargestlist32.add(t6list3); trlargestlist32.add(t6list4);
		trlargestlist32.add(t6list5); trlargestlist32.add(t6list6); trlargestlist32.add(t6list7); trlargestlist32.add(t6list8);
		trlargestlist32.add(t6list9); trlargestlist32.add(t6list10); trlargestlist32.add(t6list11); trlargestlist32.add(t6list12);
		trlargestlist32.add(t6list13); trlargestlist32.add(t6list14); trlargestlist32.add(t6list15); trlargestlist32.add(t6list16);
		trlargestlist32.add(t6list17); trlargestlist32.add(t6list18); trlargestlist32.add(t6list19); trlargestlist32.add(t6list20);
		trlargestlist32.add(t6list21); trlargestlist32.add(t6list22); trlargestlist32.add(t6list23); trlargestlist32.add(t6list24);
		trlargestlist32.add(t6list25); trlargestlist32.add(t6list26);
		trlargestlist41.add(t7list1); trlargestlist41.add(t7list2); trlargestlist41.add(t7list3); trlargestlist41.add(t7list4);
		trlargestlist41.add(t7list5); trlargestlist41.add(t7list6); trlargestlist41.add(t7list7); trlargestlist41.add(t7list8);
		trlargestlist41.add(t7list9); trlargestlist41.add(t7list10); trlargestlist41.add(t7list11); trlargestlist41.add(t7list12);
		trlargestlist41.add(t7list13); trlargestlist41.add(t7list14); trlargestlist41.add(t7list15); trlargestlist41.add(t7list16);
		trlargestlist41.add(t7list17); trlargestlist41.add(t7list18); trlargestlist41.add(t7list19); trlargestlist41.add(t7list20);
		trlargestlist41.add(t7list21); trlargestlist41.add(t7list22); trlargestlist41.add(t7list23); trlargestlist41.add(t7list24);
		trlargestlist41.add(t7list25); trlargestlist41.add(t7list26);
		trlargestlist42.add(t8list1); trlargestlist42.add(t8list2); trlargestlist42.add(t8list3); trlargestlist42.add(t8list4);
		trlargestlist42.add(t8list5); trlargestlist42.add(t8list6); trlargestlist42.add(t8list7); trlargestlist42.add(t8list8);
		trlargestlist42.add(t8list9); trlargestlist42.add(t8list10); trlargestlist42.add(t8list11); trlargestlist42.add(t8list12);
		trlargestlist42.add(t8list13); trlargestlist42.add(t8list14); trlargestlist42.add(t8list15); trlargestlist42.add(t8list16);
		trlargestlist42.add(t8list17); trlargestlist42.add(t8list18); trlargestlist42.add(t8list19); trlargestlist42.add(t8list20);
		trlargestlist42.add(t8list21); trlargestlist42.add(t8list22); trlargestlist42.add(t8list23); trlargestlist42.add(t8list24);
		trlargestlist42.add(t8list25); trlargestlist42.add(t8list26);
		ArrayList<ArrayList<ArrayList<String>>> separatelist3 = new ArrayList<ArrayList<ArrayList<String>>>(8);	
		separatelist3.add(trlargestlist11); separatelist3.add(trlargestlist12); separatelist3.add(trlargestlist21); separatelist3.add(trlargestlist22);separatelist3.add(trlargestlist31); separatelist3.add(trlargestlist32); separatelist3.add(trlargestlist41); separatelist3.add(trlargestlist42);
		//System.out.println(separatelist3);
	
		//Store to the separate list
		for (int indexa = 0; indexa < comparelist3.size(); indexa++){

			for (int abc = 0; abc < comparelist3.get(indexa).size(); abc++){	
				if( comparelist3.get(indexa).get(abc).length() > (typedletters+2)){
				String compareletter = "a";	
				String secondletter = null;
				
				if (typedletters == 1){
					secondletter = comparelist3.get(indexa).get(abc).substring(3, 4);
					}else if((typedletters == 2)&(comparelist3.get(indexa).get(abc).length()>4)){
					secondletter = comparelist3.get(indexa).get(abc).substring(4, 5);
					}else if((typedletters == 3)&(comparelist3.get(indexa).get(abc).length()>5)){
					secondletter = comparelist3.get(indexa).get(abc).substring(5, 6);
					}else if(typedletters == 4){
						if(comparelist3.get(indexa).get(abc).length()>6){
						//System.out.println("asdf");
						secondletter = comparelist3.get(indexa).get(abc).substring(6, 7);}else{
						//System.out.println("lkjh");secondletter = "0";
						}
					}else if(typedletters == 5){
						if(comparelist3.get(indexa).get(abc).length()>7){
						secondletter = comparelist3.get(indexa).get(abc).substring(7, 8);}else{
						}					
					}else if(typedletters == 6){
						if(comparelist3.get(indexa).get(abc).length()>8){
						secondletter = comparelist3.get(indexa).get(abc).substring(8, 9);}else{
						}					
					}else if(typedletters == 7){
						if(comparelist3.get(indexa).get(abc).length()>9){
						secondletter = comparelist3.get(indexa).get(abc).substring(9, 10);}else{
						}	
					}else if(typedletters == 8){
						if(comparelist3.get(indexa).get(abc).length()>10){
						secondletter = comparelist3.get(indexa).get(abc).substring(10, 11);}else{
						}	
					}
				//secondletter = comparelist3.get(indexa).get(abc).substring(3, 4);
				System.out.println("checkkk");
				if (secondletter.equalsIgnoreCase(compareletter)){
					separatelist3.get(indexa).get(0).add(comparelist3.get(indexa).get(abc));
					//System.out.println(separatelist2.get(indexa).get(0).get(0));
				} else {
					compareletter = "b";
					if(secondletter.equalsIgnoreCase(compareletter)){
						separatelist3.get(indexa).get(1).add(comparelist3.get(indexa).get(abc));
					}else{
						compareletter = "c";
						if(secondletter.equalsIgnoreCase(compareletter)){
							separatelist3.get(indexa).get(2).add(comparelist3.get(indexa).get(abc));
						}else{
							compareletter = "d";
							if(secondletter.equalsIgnoreCase(compareletter)){
								separatelist3.get(indexa).get(3).add(comparelist3.get(indexa).get(abc));
							}else{
								compareletter = "e";
								if(secondletter.equalsIgnoreCase(compareletter)){
									separatelist3.get(indexa).get(4).add(comparelist3.get(indexa).get(abc));
								}else{
									compareletter = "f";
									if(secondletter.equalsIgnoreCase(compareletter)){
										separatelist3.get(indexa).get(5).add(comparelist3.get(indexa).get(abc));
									}else{
										compareletter = "g";
										if(secondletter.equalsIgnoreCase(compareletter)){
											separatelist3.get(indexa).get(6).add(comparelist3.get(indexa).get(abc));
										}else{
											compareletter = "h";
											if(secondletter.equalsIgnoreCase(compareletter)){
												separatelist3.get(indexa).get(7).add(comparelist3.get(indexa).get(abc));
											}else{
												compareletter = "i";
												if(secondletter.equalsIgnoreCase(compareletter)){
													separatelist3.get(indexa).get(8).add(comparelist3.get(indexa).get(abc));
												}else{
													compareletter = "j";
													if(secondletter.equalsIgnoreCase(compareletter)){
														separatelist3.get(indexa).get(9).add(comparelist3.get(indexa).get(abc));
													}else{
														compareletter = "k";
														if(secondletter.equalsIgnoreCase(compareletter)){
															separatelist3.get(indexa).get(10).add(comparelist3.get(indexa).get(abc));
														}else{
															compareletter = "l";
															if(secondletter.equalsIgnoreCase(compareletter)){
																separatelist3.get(indexa).get(11).add(comparelist3.get(indexa).get(abc));
															}else{
																compareletter = "m";
																if(secondletter.equalsIgnoreCase(compareletter)){
																	separatelist3.get(indexa).get(12).add(comparelist3.get(indexa).get(abc));
																}else{
																	compareletter = "n";
																	if(secondletter.equalsIgnoreCase(compareletter)){
																		separatelist3.get(indexa).get(13).add(comparelist3.get(indexa).get(abc));
																	}else{
																		compareletter = "o";
																		if(secondletter.equalsIgnoreCase(compareletter)){
																			separatelist3.get(indexa).get(14).add(comparelist3.get(indexa).get(abc));
																		}else{
																			compareletter = "p";
																			if(secondletter.equalsIgnoreCase(compareletter)){
																				separatelist3.get(indexa).get(15).add(comparelist3.get(indexa).get(abc));
																			}else{
																				compareletter = "q";
																				if(secondletter.equalsIgnoreCase(compareletter)){
																					separatelist3.get(indexa).get(16).add(comparelist3.get(indexa).get(abc));
																				}else{
																					compareletter = "r";
																					if(secondletter.equalsIgnoreCase(compareletter)){
																						separatelist3.get(indexa).get(17).add(comparelist3.get(indexa).get(abc));
																					}else{
																						compareletter = "s";
																						if(secondletter.equalsIgnoreCase(compareletter)){
																							separatelist3.get(indexa).get(18).add(comparelist3.get(indexa).get(abc));
																						}else{
																							compareletter = "t";
																							if(secondletter.equalsIgnoreCase(compareletter)){
																								separatelist3.get(indexa).get(19).add(comparelist3.get(indexa).get(abc));
																							}else{
																								compareletter = "u";
																								if(secondletter.equalsIgnoreCase(compareletter)){
																									separatelist3.get(indexa).get(20).add(comparelist3.get(indexa).get(abc));
																								}else{
																									compareletter = "v";
																									if(secondletter.equalsIgnoreCase(compareletter)){
																										separatelist3.get(indexa).get(21).add(comparelist3.get(indexa).get(abc));
																									}else{
																										compareletter = "w";
																										if(secondletter.equalsIgnoreCase(compareletter)){
																											separatelist3.get(indexa).get(22).add(comparelist3.get(indexa).get(abc));
																										}else{
																											compareletter = "x";
																											if(secondletter.equalsIgnoreCase(compareletter)){
																												separatelist3.get(indexa).get(23).add(comparelist3.get(indexa).get(abc));
																											}else{
																												compareletter = "y";
																												if(secondletter.equalsIgnoreCase(compareletter)){
																													separatelist3.get(indexa).get(24).add(comparelist3.get(indexa).get(abc));
																												}else{
																													compareletter = "z";
																													if(secondletter.equalsIgnoreCase(compareletter)){
																														separatelist3.get(indexa).get(25).add(comparelist3.get(indexa).get(abc));
																													}	
																												}		
																											}		
																										}		
																									}		
																								}		
																							}		
																						}		
																					}		
																				}		
																			}		
																		}		
																	}		
																}		
															}		
														}		
													}		
												}		
											}		
										}		
									}		
								}		
							}		
						}		
					}				
				}
				}
			}
		}
		//System.out.println(separatelist3.get(0).get(1));
		
		//Store the sizes of 26 third letters of 8 most frequent 3rd letter
		int[] tsize1 = new int[26];
		//int[] ssize0 = {separatelist2.get(0).get(0).size()};
		for (int qw=0; qw<=25; qw++){
			int as = separatelist3.get(0).get(qw).size();
			tsize1[qw] = as;
		}
		int[] tsize2 = new int[26];
		//int[] ssize0 = {separatelist2.get(0).get(0).size()};
		for (int qw=0; qw<=25; qw++){
			int as = separatelist3.get(1).get(qw).size();
			tsize2[qw] = as;
		}
		int[] tsize3 = new int[26];
		//int[] ssize0 = {separatelist2.get(0).get(0).size()};
		for (int qw=0; qw<=25; qw++){
			int as = separatelist3.get(2).get(qw).size();
			tsize3[qw] = as;
		}
		int[] tsize4 = new int[26];
		//int[] ssize0 = {separatelist2.get(0).get(0).size()};
		for (int qw=0; qw<=25; qw++){
			int as = separatelist3.get(3).get(qw).size();
			tsize4[qw] = as;
		}
		int[] tsize5 = new int[26];
		//int[] ssize0 = {separatelist2.get(0).get(0).size()};
		for (int qw=0; qw<=25; qw++){
			int as = separatelist3.get(4).get(qw).size();
			tsize5[qw] = as;
		}
		int[] tsize6 = new int[26];
		//int[] ssize0 = {separatelist2.get(0).get(0).size()};
		for (int qw=0; qw<=25; qw++){
			int as = separatelist3.get(5).get(qw).size();
			tsize6[qw] = as;
		}
		int[] tsize7 = new int[26];
		//int[] ssize0 = {separatelist2.get(0).get(0).size()};
		for (int qw=0; qw<=25; qw++){
			int as = separatelist3.get(6).get(qw).size();
			tsize7[qw] = as;
		}
		int[] tsize8 = new int[26];
		//int[] ssize0 = {separatelist2.get(0).get(0).size()};
		for (int qw=0; qw<=25; qw++){
			int as = separatelist3.get(7).get(qw).size();
			tsize8[qw] = as;
		}
		
		//Find the most frequent 4th letters
		int max311 = tsize1[25]; int trlargest1 = 25;
		int smax311 = tsize1[25]; int trslargest1 = 25;
		int max312 = tsize2[25]; int trlargest2 = 25;
		int smax312 = tsize2[25]; int trslargest2 = 25;
		int max321 = tsize3[25]; int trlargest3 = 25;
		int smax321 = tsize3[25]; int trslargest3 = 25;
		int max322 = tsize4[25]; int trlargest4 = 25;
		int smax322 = tsize4[25]; int trslargest4 = 25;
		int max331 = tsize5[25]; int trlargest5 = 25;
		int smax331 = tsize5[25]; int trslargest5 = 25;
		int max332 = tsize6[25]; int trlargest6 = 25;
		int smax332 = tsize6[25]; int trslargest6 = 25;
		int max341 = tsize7[25]; int trlargest7 = 25;
		int smax341 = tsize7[25]; int trslargest7 = 25;
		int max342 = tsize8[25]; int trlargest8 = 25;
		int smax342 = tsize8[25]; int trslargest8 = 25;
		String largestn311 =  null;String slargestn311 =  null;
		String largestn312 =  null;String slargestn312 =  null;
		String largestn321 =  null;String slargestn321 =  null;
		String largestn322 =  null;String slargestn322 =  null;
		String largestn331 =  null;String slargestn331 =  null;
		String largestn332 =  null;String slargestn332 =  null;
		String largestn341 =  null;String slargestn341 =  null;
		String largestn342 =  null;String slargestn342 =  null;

		for (int ab=0; ab<=25; ab++){
			if (tsize1[ab] >= max311){
				trlargest1 = ab;
				max311 = tsize1[ab];
			}
			largestn311 = name[trlargest1];
		}
		for (int ab=0; ab<=25; ab++){
			if ((ab != trlargest1 )&&(tsize1[ab] >= smax311)){
				trslargest1 = ab;
				smax311 = tsize1[ab];
			}
			slargestn311 = name[trslargest1];
		}  //for
		for (int ab=0; ab<=25; ab++){
			if (tsize2[ab] >= max312){
				trlargest2 = ab;
				max312 = tsize2[ab];
			}
			largestn312 = name[trlargest2];
		}
		for (int ab=0; ab<=25; ab++){
			if ((ab != trlargest2 )&&(tsize2[ab] >= smax312)){
				trslargest2 = ab;
				smax312 = tsize2[ab];
			}
			slargestn312 = name[trslargest2];
		} //foo		
		for (int ab=0; ab<=25; ab++){
			if (tsize3[ab] >= max321){
				trlargest3 = ab;
				max321 = tsize3[ab];
			}
			largestn321 = name[trlargest3];
		}
		for (int ab=0; ab<=25; ab++){
			if ((ab != trlargest3 )&&(tsize3[ab] >= smax321)){
				trslargest3 = ab;
				smax321 = tsize3[ab];
			}
			slargestn321 = name[trslargest3];
		}//3 + 321
		for (int ab=0; ab<=25; ab++){
			if (tsize4[ab] >= max322){
				trlargest4 = ab;
				max322 = tsize4[ab];
			}
			largestn322 = name[trlargest4];
		}
		for (int ab=0; ab<=25; ab++){
			if ((ab != trlargest4 )&&(tsize4[ab] >= smax322)){
				trslargest4 = ab;
				smax322 = tsize4[ab];
			}
			slargestn322 = name[trslargest4];
		}//4 + 322
		for (int ab=0; ab<=25; ab++){
			if (tsize5[ab] >= max331){
				trlargest5 = ab;
				max331 = tsize5[ab];
			}
			largestn331 = name[trlargest5];
		}
		for (int ab=0; ab<=25; ab++){
			if ((ab != trlargest5 )&&(tsize5[ab] >= smax331)){
				trslargest5 = ab;
				smax331 = tsize5[ab];
			}
			slargestn331 = name[trslargest5];
		}//5 + 331
		for (int ab=0; ab<=25; ab++){
			if (tsize6[ab] >= max332){
				trlargest6 = ab;
				max332 = tsize6[ab];
			}
			largestn332 = name[trlargest6];
		}
		for (int ab=0; ab<=25; ab++){
			if ((ab != trlargest6 )&&(tsize6[ab] >= smax332)){
				trslargest6 = ab;
				smax332 = tsize6[ab];
			}
			slargestn332 = name[trslargest6];
		}  //6 + 332
		for (int ab=0; ab<=25; ab++){
			if (tsize7[ab] >= max341){
				trlargest7 = ab;
				max341 = tsize7[ab];
			}
			largestn341 = name[trlargest7];
		}
		for (int ab=0; ab<=25; ab++){
			if ((ab != trlargest7 )&&(tsize7[ab] >= smax341)){
				trslargest7 = ab;
				smax341 = tsize7[ab];
			}
			slargestn341 = name[trslargest7];
		} //7 + 341		
		for (int ab=0; ab<=25; ab++){
			if (tsize8[ab] >= max342){
				trlargest8 = ab;
				max342 = tsize8[ab];
			}
			largestn342 = name[trlargest8];
		}
		for (int ab=0; ab<=25; ab++){
			if ((ab != trlargest8 )&&(tsize8[ab] >= smax342)){
				trslargest8 = ab;
				smax342 = tsize8[ab];
			}
			slargestn342 = name[trslargest8];
		}//8 + 342
		
		//buttons in the first ring
		predictbuttons[0].setText(largestn); //fr1.setFont(new Font("Arial", Font.PLAIN, 30));	
		predictbuttons[1].setText(slargestn); //fr2.setFont(new Font("Arial", Font.PLAIN, 30));
		predictbuttons[2].setText(tlargestn); //fr3.setFont(new Font("Arial", Font.PLAIN, 30));
		predictbuttons[3].setText(flargestn); //fr4.setFont(new Font("Arial", Font.PLAIN, 30));
		
		
/*		ActionListener abcd;
		abcd = new ActionListener(){
			public void actionPerformed(ActionEvent action1){
				String actionCommand = action1.getActionCommand();
				txt.setText(txt.getText() + actionCommand);
				tp1.setText(tp1.getText() + actionCommand);
				content = content + actionCommand;
				write();
				txt.requestFocusInWindow();
				System.out.println(actionCommand);
			}			
		};  */
		
		predictbuttons[0].removeActionListener(abcd);
		predictbuttons[0].addActionListener(abcd);		
		predictbuttons[1].removeActionListener(abcd);
		predictbuttons[1].addActionListener(abcd);
		predictbuttons[2].removeActionListener(abcd);
		predictbuttons[2].addActionListener(abcd);
		predictbuttons[3].removeActionListener(abcd);
		predictbuttons[3].addActionListener(abcd);

		
		//buttons in second ring
		predictbuttons[4].setText(largestn21); //sr1.setFont(new Font("Arial", Font.PLAIN, 30));
		predictbuttons[5].setText(slargestn21); //sr2.setFont(new Font("Arial", Font.PLAIN, 30));
		predictbuttons[6].setText(largestn22); //sr3.setFont(new Font("Arial", Font.PLAIN, 30));
		predictbuttons[7].setText(slargestn22); //sr4.setFont(new Font("Arial", Font.PLAIN, 30));
		predictbuttons[8].setText(largestn23); //sr5.setFont(new Font("Arial", Font.PLAIN, 30));
		predictbuttons[9].setText(slargestn23); //sr6.setFont(new Font("Arial", Font.PLAIN, 30));
		predictbuttons[10].setText(largestn24); //sr7.setFont(new Font("Arial", Font.PLAIN, 30));
		predictbuttons[11].setText(slargestn24); //sr8.setFont(new Font("Arial", Font.PLAIN, 30));

	
		fristring1 = largestn;
		predictbuttons[4].removeActionListener(abcd11);
		predictbuttons[4].addActionListener(abcd11);
		predictbuttons[5].removeActionListener(abcd11);
		predictbuttons[5].addActionListener(abcd11);		
		fristring2 = slargestn;
		predictbuttons[6].removeActionListener(abcd12);
		predictbuttons[6].addActionListener(abcd12);
		predictbuttons[7].removeActionListener(abcd12);
		predictbuttons[7].addActionListener(abcd12);	
		fristring3 = tlargestn;
		predictbuttons[8].removeActionListener(abcd13);
		predictbuttons[8].addActionListener(abcd13);
		predictbuttons[9].removeActionListener(abcd13);
		predictbuttons[9].addActionListener(abcd13);
		fristring4 = flargestn;
		predictbuttons[10].removeActionListener(abcd14);
		predictbuttons[10].addActionListener(abcd14);
		predictbuttons[11].removeActionListener(abcd14);
		predictbuttons[11].addActionListener(abcd14);
		
		//buttons in third ring
		predictbuttons[12].setText(largestn311);predictbuttons[13].setText(slargestn311);
		predictbuttons[14].setText(largestn312);predictbuttons[15].setText(slargestn312);
		predictbuttons[16].setText(largestn321);predictbuttons[17].setText(slargestn321);
		predictbuttons[18].setText(largestn322);predictbuttons[19].setText(slargestn322);
		predictbuttons[20].setText(largestn331);predictbuttons[21].setText(slargestn331);
		predictbuttons[22].setText(largestn332);predictbuttons[23].setText(slargestn332);
		predictbuttons[24].setText(largestn341);predictbuttons[25].setText(slargestn341);
		predictbuttons[26].setText(largestn342);predictbuttons[27].setText(slargestn342);
//		tr1.setFont(new Font("Arial", Font.PLAIN, 25));tr2.setFont(new Font("Arial", Font.PLAIN, 25));tr3.setFont(new Font("Arial", Font.PLAIN, 25));tr4.setFont(new Font("Arial", Font.PLAIN, 25));tr5.setFont(new Font("Arial", Font.PLAIN, 25));tr6.setFont(new Font("Arial", Font.PLAIN, 25));tr7.setFont(new Font("Arial", Font.PLAIN, 25));tr8.setFont(new Font("Arial", Font.PLAIN, 25));
//		tr9.setFont(new Font("Arial", Font.PLAIN, 25));tr10.setFont(new Font("Arial", Font.PLAIN, 25));tr11.setFont(new Font("Arial", Font.PLAIN, 25));tr12.setFont(new Font("Arial", Font.PLAIN, 25));tr13.setFont(new Font("Arial", Font.PLAIN, 25));tr14.setFont(new Font("Arial", Font.PLAIN, 25));tr15.setFont(new Font("Arial", Font.PLAIN, 25));tr16.setFont(new Font("Arial", Font.PLAIN, 25));
		sristring1 = largestn21;
		predictbuttons[12].removeActionListener(abcd21);
		predictbuttons[12].addActionListener(abcd21);
		predictbuttons[13].removeActionListener(abcd21);
		predictbuttons[13].addActionListener(abcd21);		
		sristring2 = slargestn21;
		predictbuttons[14].removeActionListener(abcd22);
		predictbuttons[14].addActionListener(abcd22);
		predictbuttons[15].removeActionListener(abcd22);
		predictbuttons[15].addActionListener(abcd22);	
		sristring3 = largestn22;
		predictbuttons[16].removeActionListener(abcd23);
		predictbuttons[16].addActionListener(abcd23);
		predictbuttons[17].removeActionListener(abcd23);
		predictbuttons[17].addActionListener(abcd23);
		sristring4 = slargestn22;
		predictbuttons[18].removeActionListener(abcd24);
		predictbuttons[18].addActionListener(abcd24);
		predictbuttons[19].removeActionListener(abcd24);
		predictbuttons[19].addActionListener(abcd24);
		sristring5 = largestn23;
		predictbuttons[20].removeActionListener(abcd25);
		predictbuttons[20].addActionListener(abcd25);
		predictbuttons[21].removeActionListener(abcd25);
		predictbuttons[21].addActionListener(abcd25);		
		sristring6 = slargestn23;
		predictbuttons[22].removeActionListener(abcd26);
		predictbuttons[22].addActionListener(abcd26);
		predictbuttons[23].removeActionListener(abcd26);
		predictbuttons[23].addActionListener(abcd26);	
		sristring7 = largestn24;
		predictbuttons[24].removeActionListener(abcd27);
		predictbuttons[24].addActionListener(abcd27);
		predictbuttons[25].removeActionListener(abcd27);
		predictbuttons[25].addActionListener(abcd27);
		sristring8 = slargestn24;
		predictbuttons[26].removeActionListener(abcd28);
		predictbuttons[26].addActionListener(abcd28);
		predictbuttons[27].removeActionListener(abcd28);
		predictbuttons[27].addActionListener(abcd28);
	    }

	
	public void excel(){
		lab.setBounds(50, 50, fra.getWidth()-100, (int)((fra.getWidth()-100)*1.18));
		image1 = new ImageIcon(image1.getImage().getScaledInstance(fra.getWidth()-100, (int)((fra.getWidth()-100)*1.18), Image.SCALE_SMOOTH));

		lab.setIcon(image1);
		lab.repaint();
		lab.revalidate(); 
		//remove the buttons in first, second and third ring
		for (int u =0; u<28; u++){
			pan.remove(predictbuttons[u]);
		}
/*		pan.remove(fr1);pan.remove(fr2);pan.remove(fr3);pan.remove(fr4);
		pan.remove(sr1);pan.remove(sr2);pan.remove(sr3);pan.remove(sr4);pan.remove(sr5);pan.remove(sr6);pan.remove(sr7);pan.remove(sr8);
		pan.remove(tr1);pan.remove(tr2);pan.remove(tr3);pan.remove(tr4);pan.remove(tr5);pan.remove(tr6);pan.remove(tr7);pan.remove(tr8);pan.remove(tr9);pan.remove(tr10);pan.remove(tr11);pan.remove(tr12);pan.remove(tr13);pan.remove(tr14);pan.remove(tr15);pan.remove(tr16);
*/
		//pan.revalidate();
		//pan.repaint();

		//Add new buttons: +-*/= numbers and others
		//ex1 = new JButton("+");ex2 = new JButton("-");ex3 = new JButton("*");ex4 = new JButton("/");ex5 = new JButton("=");
/*		ex1.setBounds(182, 183, 33, 33); ex1.setFont(new Font("Arial", Font.PLAIN, 25));
		ex2.setBounds(222, 190, 33, 33); ex2.setFont(new Font("Arial", Font.PLAIN, 25));
		ex5.setBounds(250, 233, 33, 33); ex5.setFont(new Font("Arial", Font.PLAIN, 25));
		ex3.setBounds(224, 280, 33, 33); ex3.setFont(new Font("Arial", Font.PLAIN, 25));
		ex4.setBounds(182, 283, 33, 33); ex4.setFont(new Font("Arial", Font.PLAIN, 25));
		ex1.setBorder(null);ex1.setBorderPainted(false);ex1.setContentAreaFilled(false);ex1.setOpaque(false);
        ex2.setBorder(null);ex2.setBorderPainted(false);ex2.setContentAreaFilled(false);ex2.setOpaque(false);
        ex3.setBorder(null);ex3.setBorderPainted(false);ex3.setContentAreaFilled(false);ex3.setOpaque(false);
        ex4.setBorder(null);ex4.setBorderPainted(false);ex4.setContentAreaFilled(false);ex4.setOpaque(false);
        ex5.setBorder(null);ex5.setBorderPainted(false);ex5.setContentAreaFilled(false);ex5.setOpaque(false);
    */    
		for(int a=0; a<29; a++){
		phase2buttons[a].removeActionListener(excellis);
		phase2buttons[a].addActionListener(excellis);
		}
/*		ex1.removeActionListener(excellis);ex1.addActionListener(excellis);
		ex2.removeActionListener(excellis);ex2.addActionListener(excellis);
		ex3.removeActionListener(excellis);ex3.addActionListener(excellis);
		ex4.removeActionListener(excellis);ex4.addActionListener(excellis);
		ex5.removeActionListener(excellis);ex5.addActionListener(excellis);
*/
        pan.revalidate();
		pan.repaint();
		
		//no1,no2,no3,no4,no5,no6,no7,no8,no9,no0;
		
/*		no0.setBounds(155, 160, 30, 30);//no0.setBorder(null);no0.setBorderPainted(false);no0.setContentAreaFilled(false);no0.setOpaque(false);
		no1.setBounds(190, 150, 30, 30);//no1.setBorder(null);no1.setBorderPainted(false);no1.setContentAreaFilled(false);no0.setOpaque(false);
		no2.setBounds(225, 155, 30, 30);//no2.setBorder(null);no2.setBorderPainted(false);no2.setContentAreaFilled(false);no0.setOpaque(false);
		no3.setBounds(260, 180, 30, 30);//no3.setBorder(null);no3.setBorderPainted(false);no3.setContentAreaFilled(false);no0.setOpaque(false);
		no4.setBounds(283, 215, 30, 30);//no4.setBorder(null);no4.setBorderPainted(false);no4.setContentAreaFilled(false);no0.setOpaque(false);
		no5.setBounds(285, 255, 30, 30);//no5.setBorder(null);no5.setBorderPainted(false);no5.setContentAreaFilled(false);no0.setOpaque(false);
		no6.setBounds(270, 290, 30, 30);//no6.setBorder(null);no6.setBorderPainted(false);no6.setContentAreaFilled(false);no0.setOpaque(false);
		no7.setBounds(240, 315, 30, 30);//no7.setBorder(null);no7.setBorderPainted(false);no7.setContentAreaFilled(false);no0.setOpaque(false);
		no8.setBounds(200, 320, 30, 30);//no8.setBorder(null);no8.setBorderPainted(false);no8.setContentAreaFilled(false);no0.setOpaque(false);
		no9.setBounds(160, 312, 30, 30);//no9.setBorder(null);no9.setBorderPainted(false);no9.setContentAreaFilled(false);no0.setOpaque(false);
		no0.setFont(new Font("Arial", Font.PLAIN, 25));no1.setFont(new Font("Arial", Font.PLAIN, 25));no2.setFont(new Font("Arial", Font.PLAIN, 25));no3.setFont(new Font("Arial", Font.PLAIN, 25));no4.setFont(new Font("Arial", Font.PLAIN, 25));
		no5.setFont(new Font("Arial", Font.PLAIN, 25));no6.setFont(new Font("Arial", Font.PLAIN, 25));no7.setFont(new Font("Arial", Font.PLAIN, 25));no8.setFont(new Font("Arial", Font.PLAIN, 25));no9.setFont(new Font("Arial", Font.PLAIN, 25));
*/		
/*		no0.removeActionListener(excellis);no0.addActionListener(excellis);
		no1.removeActionListener(excellis);no1.addActionListener(excellis);
		no2.removeActionListener(excellis);no2.addActionListener(excellis);
		no3.removeActionListener(excellis);no3.addActionListener(excellis);
		no4.removeActionListener(excellis);no4.addActionListener(excellis);
		no5.removeActionListener(excellis);no5.addActionListener(excellis);
		no6.removeActionListener(excellis);no6.addActionListener(excellis);
		no7.removeActionListener(excellis);no7.addActionListener(excellis);
		no8.removeActionListener(excellis);no8.addActionListener(excellis);
		no9.removeActionListener(excellis);no9.addActionListener(excellis);
*/		
		//d1,d2,d3,d4,d5,d6,d7,d8,d9,d10,mbra1,mbra2,lbra1,lbra2;
	/*	tr1.setBounds(135, 135, BUTTON_SIZE_X2, BUTTON_SIZE_Y2); 
		tr2.setBounds(155, 125, BUTTON_SIZE_X2, BUTTON_SIZE_Y2); 
		tr3.setBounds(175, 120, BUTTON_SIZE_X2, BUTTON_SIZE_Y2); 
		tr4.setBounds(200, 115, BUTTON_SIZE_X2, BUTTON_SIZE_Y2); 
		tr5.setBounds(230, 125, BUTTON_SIZE_X2, BUTTON_SIZE_Y2); 
		tr6.setBounds(255, 135, BUTTON_SIZE_X2, BUTTON_SIZE_Y2); 
		tr7.setBounds(290, 160, BUTTON_SIZE_X2, BUTTON_SIZE_Y2); 
		tr8.setBounds(310, 185, BUTTON_SIZE_X2, BUTTON_SIZE_Y2); 
		tr9.setBounds(320, 220, BUTTON_SIZE_X2, BUTTON_SIZE_Y2); 
		tr10.setBounds(320, 250, BUTTON_SIZE_X2, BUTTON_SIZE_Y2); 
		tr11.setBounds(310, 280, BUTTON_SIZE_X2, BUTTON_SIZE_Y2); 
		tr12.setBounds(290, 310, BUTTON_SIZE_X2, BUTTON_SIZE_Y2); 
		tr13.setBounds(230, 350, BUTTON_SIZE_X2, BUTTON_SIZE_Y2); 
		tr14.setBounds(200, 355, BUTTON_SIZE_X2, BUTTON_SIZE_Y2); 
		tr15.setBounds(160, 350, BUTTON_SIZE_X2, BUTTON_SIZE_Y2); 
		tr16.setBounds(135, 340, BUTTON_SIZE_X2, BUTTON_SIZE_Y2); */

/*		d1.setBounds(285, 325,33, 33); d1.setFont(new Font("Arial", Font.PLAIN, 25));
		d2.setBounds(305, 290, 33, 33); d2.setFont(new Font("Arial", Font.PLAIN, 25));
		d3.setBounds(212, 116, 33, 33); d3.setFont(new Font("Arial", Font.PLAIN, 25));
		d4.setBounds(250, 130, 33, 33); d4.setFont(new Font("Arial", Font.PLAIN, 25));
		d5.setBounds(285, 150, 33, 33); d5.setFont(new Font("Arial", Font.PLAIN, 25));
		d6.setBounds(309, 185, 33, 33); d6.setFont(new Font("Arial", Font.PLAIN, 25));
		d7.setBounds(318, 220, 33, 33); d7.setFont(new Font("Arial", Font.PLAIN, 25));
		d8.setBounds(317, 255, 33, 33); d8.setFont(new Font("Arial", Font.PLAIN, 25));
		d9.setBounds(245, 345,33, 33); d9.setFont(new Font("Arial", Font.PLAIN, 25));
		d10.setBounds(208, 353, 33, 33); d10.setFont(new Font("Arial", Font.PLAIN, 25));
		mbra1.setBounds(138, 130, 33, 33); mbra1.setFont(new Font("Arial", Font.PLAIN, 25));
		mbra2.setBounds(175, 117, 33, 33); mbra2.setFont(new Font("Arial", Font.PLAIN, 25));
		lbra1.setBounds(135, 338, 33, 33); lbra1.setFont(new Font("Arial", Font.PLAIN, 25));
		lbra2.setBounds(172, 352, 33, 33); lbra2.setFont(new Font("Arial", Font.PLAIN, 25));
*/
/*		d1.setBorder(null);d1.setBorderPainted(false);d1.setContentAreaFilled(false);d1.setOpaque(false);
        d2.setBorder(null);d2.setBorderPainted(false);d2.setContentAreaFilled(false);d2.setOpaque(false);
        d3.setBorder(null);d3.setBorderPainted(false);d3.setContentAreaFilled(false);d3.setOpaque(false);
        d4.setBorder(null);d4.setBorderPainted(false);d4.setContentAreaFilled(false);d4.setOpaque(false);
        d5.setBorder(null);d5.setBorderPainted(false);d5.setContentAreaFilled(false);d5.setOpaque(false);
		d6.setBorder(null);d6.setBorderPainted(false);d6.setContentAreaFilled(false);d6.setOpaque(false);
        d7.setBorder(null);d7.setBorderPainted(false);d7.setContentAreaFilled(false);d7.setOpaque(false);
        d8.setBorder(null);d8.setBorderPainted(false);d8.setContentAreaFilled(false);d8.setOpaque(false);
        d9.setBorder(null);d9.setBorderPainted(false);d9.setContentAreaFilled(false);d9.setOpaque(false);
        d10.setBorder(null);d10.setBorderPainted(false);d10.setContentAreaFilled(false);d10.setOpaque(false);
        mbra1.setBorder(null);mbra1.setBorderPainted(false);mbra1.setContentAreaFilled(false);mbra1.setOpaque(false);
        mbra2.setBorder(null);mbra2.setBorderPainted(false);mbra2.setContentAreaFilled(false);mbra2.setOpaque(false);
        lbra1.setBorder(null);lbra1.setBorderPainted(false);lbra1.setContentAreaFilled(false);lbra1.setOpaque(false);
        lbra2.setBorder(null);lbra2.setBorderPainted(false);lbra2.setContentAreaFilled(false);lbra2.setOpaque(false);
*/
/*		d1.removeActionListener(excellis);d1.addActionListener(excellis);
		d2.removeActionListener(excellis);d2.addActionListener(excellis);
		d3.removeActionListener(excellis);d3.addActionListener(excellis);
		d4.removeActionListener(excellis);d4.addActionListener(excellis);
		d5.removeActionListener(excellis);d5.addActionListener(excellis);
		d6.removeActionListener(excellis);d6.addActionListener(excellis);
		d7.removeActionListener(excellis);d7.addActionListener(excellis);
		d8.removeActionListener(excellis);d8.addActionListener(excellis);
		d9.removeActionListener(excellis);d9.addActionListener(excellis);
		d10.removeActionListener(excellis);d10.addActionListener(excellis);
		mbra1.removeActionListener(excellis);mbra1.addActionListener(excellis);
		mbra2.removeActionListener(excellis);mbra2.addActionListener(excellis);
		lbra1.removeActionListener(excellis);lbra1.addActionListener(excellis);
		lbra2.removeActionListener(excellis);lbra2.addActionListener(excellis);
*/		
		for (int a =0; a<29; a++){
			pan.add(phase2buttons[a]);
		}
/*		pan.add(ex1);pan.add(ex2);pan.add(ex3);pan.add(ex4);pan.add(ex5);
		pan.add(no1);pan.add(no2);pan.add(no3);pan.add(no4);pan.add(no5);pan.add(no6);pan.add(no7);pan.add(no8);pan.add(no9);pan.add(no0);
        pan.add(d1);pan.add(d2);pan.add(d3);pan.add(d4);pan.add(d5);pan.add(d6);pan.add(d7);pan.add(d8);pan.add(d9);pan.add(d10);
        pan.add(mbra1);pan.add(mbra2);pan.add(lbra1);pan.add(lbra2);
*/
		pan.add(lab);
		pan.revalidate();
		pan.repaint();
		fra.add(pan);
		fra.revalidate();
		fra.repaint();
		fra.setVisible(true);
	}
	
	//actionlistener for full words
	private ActionListener fullwordl = new ActionListener(){
		public void actionPerformed(ActionEvent ep2){
		System.out.println(ep2.getActionCommand());
	//	String actionCommand = ep2.getActionCommand().substring(2, ep2.getActionCommand().length());
		lastWord1length = lastWord2length;
		String actionCommand = ep2.getActionCommand().substring(lastWord1length, ep2.getActionCommand().length());
		System.out.println(actionCommand);
		txt.setText(txt.getText() + actionCommand);
		tp1.setText(tp1.getText() + actionCommand);
		content = content + actionCommand;
		//write();
//		StringSelection stringSelection = new StringSelection(content);
//		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
//		clipboard.setContents(stringSelection, stringSelection);
//		Robot robot = new Robot();      
//        robot.keyPress();
		for(int i = 0; i < actionCommand.length(); i++){
		try {
			 	int temp2 = (int)actionCommand.charAt(i);
	        	int keyCode = KeyEvent.getExtendedKeyCodeForChar(temp2);	             
	            Robot robot = new Robot();	            
	            robot.keyPress(keyCode);
	        	
	        } catch (AWTException e) {
	            e.printStackTrace();
	        } }
		}			
		};
		
	public void fullword(){
		//Store the full-length word into arraylist separatefullwordList
/*		try {
			fullw = new String(Files.readAllBytes(Paths.get(fullwordfile)));
		} catch (IOException fw1) {
			// TODO Auto-generated catch block
			fw1.printStackTrace();
		}*/
		java.net.URL path = P3.class.getResource("fwfile.txt");
		File filefw = new File(path.getFile());
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(filefw));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fullw = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(fullw);

		//Compare the typed letters with the words in separatefullwordList
		String [] fullwordlist1 = new String[2000];
		Arrays.fill(fullwordlist1, null);
		fullwordlist1 = fullw.split(",");
		ArrayList<String> separatefullwordList = new ArrayList<String>(Arrays.asList(fullwordlist1));
		//System.out.println(separatefullwordList);
		String lastWord2 = content.replaceAll("^.*?(\\w+)\\W*$", "$1"); 
		String compareword1 = null;
		lastWord2length = lastWord2.length();
		System.out.println(lastWord2);
		System.out.println("length" + lastWord2length);		
		ArrayList<String> wordonbuttonList = new ArrayList<String>();
		System.out.println(wordonbuttonList);
		
		if(lastWord2length>5){
			return;
		}
		
		for (int ino = 0; ino < separatefullwordList.size(); ino ++){
			compareword1 = separatefullwordList.get(ino);
			if ((lastWord2length >1)&&(lastWord2.equalsIgnoreCase(compareword1.substring(0,lastWord2length)))){
				wordonbuttonList.add(separatefullwordList.get(ino));
				//ta.setText(ta.getText() + separatefullwordList.get(ino) + "\n");
			}
		}
		System.out.println(wordonbuttonList);
		while (wordonbuttonList.size() < 10 ){
			wordonbuttonList.add(wordonbuttonList.size(), null);
		}
		System.out.println(wordonbuttonList);
	/*	try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
		for (int u=0; u<10; u++){
		wordbuttons[u].setText(wordonbuttonList.get(u));
		}
/*		fw1.setText(wordonbuttonList.get(0));fw2.setText(wordonbuttonList.get(1));
		fw3.setText(wordonbuttonList.get(2));fw4.setText(wordonbuttonList.get(3));
		fw5.setText(wordonbuttonList.get(4));fw6.setText(wordonbuttonList.get(5));
		fw7.setText(wordonbuttonList.get(6));fw8.setText(wordonbuttonList.get(7));
		fw9.setText(wordonbuttonList.get(8));fw10.setText(wordonbuttonList.get(9));
		*/
		fra.repaint();
		fra.revalidate();
		pan.repaint();
		pan.revalidate();
	}
	
	public static void main(String[] args) {
		P3 p = new P3();
	}

	
}

