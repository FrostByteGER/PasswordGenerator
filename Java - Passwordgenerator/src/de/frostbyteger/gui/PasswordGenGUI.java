package de.frostbyteger.gui;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;
import javax.swing.ButtonGroup;
import javax.swing.JFormattedTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import de.frostbyteger.core.PWGenerator;

/**
 * 
 * @author Kevin
 * @version 1.00
 * This is the GUI class. It displays the user interface
 * and is responsible for user input.
 */
public class PasswordGenGUI implements ActionListener{

	private JFrame frame;
	private JPanel contentPane;
	private JPanel panel;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenu mnHelp;
	private JMenuItem mntmAbout;
	private JMenuItem mntmQuit;
	private JButton btnGenerate;
	private JButton btnClear;
	private JLabel lblGenerated;
	private JLabel lblLength;
	private JLabel lblUsed;
	private JLabel lblUnused;
	private JTextField tfUsed;
	private JTextField tfUnused;
	private JRadioButton rdbtnAscii;
	private JRadioButton rdbtnUtf;
	private JRadioButton rdbtnUnicode;
	private ButtonGroup bgp;
	private JTextArea taPw;
	private JCheckBox chckbxUseChars;
	private JSpinner spinner;
	private JCheckBox chckbxUseAlphaNumbers;
	private JButton btnCopyToClipboard;


	/**
	 * Create the frame.
	 */
	public PasswordGenGUI() {
		frame = new JFrame("Password Generator v1.01");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 500, 300);
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmQuit = new JMenuItem("Quit");
		mntmQuit.setActionCommand("quit");
		mntmQuit.addActionListener(this);
		mnFile.add(mntmQuit);
		
		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		mntmAbout = new JMenuItem("About");
		mntmAbout.setActionCommand("about");
		mntmAbout.addActionListener(this);
		mnHelp.add(mntmAbout);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		frame.setContentPane(contentPane);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		btnGenerate = new JButton("Generate");
		btnGenerate.setToolTipText("Click to generate your password with the given settings");
		btnGenerate.setActionCommand("generate");
		btnGenerate.addActionListener(this);
		btnGenerate.setBounds(109, 197, 89, 23);
		panel.add(btnGenerate);
		
		btnClear = new JButton("Clear");
		btnClear.setToolTipText("Click to clear the generated password field");
		btnClear.setActionCommand("clear");
		btnClear.addActionListener(this);
		btnClear.setBounds(208, 197, 89, 23);
		panel.add(btnClear);
		
		lblUsed = new JLabel("Used Characters");
		lblUsed.setBounds(10, 11, 404, 14);
		panel.add(lblUsed);
		
		tfUsed = new JTextField();
		tfUsed.setToolTipText("Enter letters or numbers with which the password should be generated");
		tfUsed.setEditable(false);
		tfUsed.setBounds(10, 25, 437, 20);
		panel.add(tfUsed);
		tfUsed.setColumns(10);
		
		lblUnused = new JLabel("Unused Characters");
		lblUnused.setBounds(10, 47, 404, 14);
		panel.add(lblUnused);
		
		tfUnused = new JTextField();
		tfUnused.setToolTipText("Enter characters or numbers you don't want in your password");
		tfUnused.setColumns(10);
		tfUnused.setBounds(10, 61, 437, 20);
		panel.add(tfUnused);
		
		bgp = new ButtonGroup();
		
		rdbtnAscii = new JRadioButton("ASCII");
		rdbtnAscii.setToolTipText("Check this to use the default ASCII charset");
		rdbtnAscii.setSelected(true);
		rdbtnAscii.setBounds(10, 145, 71, 23);
		
		rdbtnUtf = new JRadioButton("UTF-8");
		rdbtnUtf.setToolTipText("Check this to use the UTF-8 charset(deactivated in v1.00)");
		rdbtnUtf.setBounds(10, 171, 71, 23);
		
		rdbtnUnicode = new JRadioButton("Unicode");
		rdbtnUnicode.setToolTipText("Check this to use the Unicode charset(deactivated in v1.00)");
		rdbtnUnicode.setBounds(10, 197, 71, 23);
		
		bgp.add(rdbtnAscii);
		bgp.add(rdbtnUtf);
		bgp.add(rdbtnUnicode);
		
		panel.add(rdbtnUtf);
		panel.add(rdbtnAscii);
		panel.add(rdbtnUnicode);
		
		lblGenerated = new JLabel("Generated Password");
		lblGenerated.setBounds(10, 107, 404, 14);
		panel.add(lblGenerated);
		
		taPw = new JTextArea();
		taPw.setToolTipText("Your generated password");
		taPw.setEditable(false);
		taPw.setBounds(10, 121, 437, 22);
		panel.add(taPw);
		
		chckbxUseChars = new JCheckBox("Use entered characters only");
		chckbxUseChars.setToolTipText("Check this to generate a password with the given input");
		chckbxUseChars.setActionCommand("useEntered");
		chckbxUseChars.addActionListener(this);
		chckbxUseChars.setBounds(101, 145, 196, 23);
		panel.add(chckbxUseChars);
		
		lblLength = new JLabel("Password length");
		lblLength.setBounds(303, 149, 111, 14);
		panel.add(lblLength);
		
		spinner = new JSpinner(new SpinnerNumberModel(new Integer(16), new Integer(0), null, new Integer(1)));
		spinner.setToolTipText("The length of your password");
		spinner.setBounds(303, 166, 144, 20);
		///Prevents that the user can input non-integers.
		JFormattedTextField txt = ((JSpinner.NumberEditor) spinner.getEditor()).getTextField();
		((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);
		///
		panel.add(spinner);
		
		chckbxUseAlphaNumbers = new JCheckBox("Use A-Z a-z and 0-9");
		chckbxUseAlphaNumbers.addActionListener(this);
		chckbxUseAlphaNumbers.setToolTipText("Check this to generate a password with standard A-Z, a-z and 0-9 characters");
		chckbxUseAlphaNumbers.setActionCommand("uselatinnumbers");
		chckbxUseAlphaNumbers.setBounds(101, 171, 196, 23);
		panel.add(chckbxUseAlphaNumbers);
		
		btnCopyToClipboard = new JButton("Copy to Clipboard");
		btnCopyToClipboard.setEnabled(false);
		btnCopyToClipboard.setActionCommand("copy");
		btnCopyToClipboard.addActionListener(this);
		btnCopyToClipboard.setBounds(307, 197, 140, 23);
		panel.add(btnCopyToClipboard);
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getActionCommand().equals("useEntered")){
			if(!chckbxUseChars.isSelected()){
				chckbxUseAlphaNumbers.setEnabled(true);
				tfUsed.setEditable(false);
				tfUnused.setEditable(true);
				rdbtnAscii.setEnabled(true);
				rdbtnUtf.setEnabled(true);
				rdbtnUnicode.setEnabled(true);
			}else{
				chckbxUseAlphaNumbers.setEnabled(false);
				tfUsed.setEditable(true);
				tfUnused.setEditable(false);
				rdbtnAscii.setEnabled(false);
				rdbtnUtf.setEnabled(false);
				rdbtnUnicode.setEnabled(false);
			}
		}else if(ae.getActionCommand().equals("uselatinnumbers")){
			if(!chckbxUseAlphaNumbers.isSelected()){
				chckbxUseChars.setEnabled(true);
				tfUsed.setText("");
				tfUsed.setEditable(false);
				tfUnused.setEditable(true);
				rdbtnAscii.setEnabled(true);
				rdbtnUtf.setEnabled(true);
				rdbtnUnicode.setEnabled(true);
			}else{
				chckbxUseChars.setEnabled(false);
				tfUsed.setText("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789");
				tfUsed.setEditable(false);
				tfUnused.setEditable(false);
				rdbtnAscii.setEnabled(false);
				rdbtnUtf.setEnabled(false);
				rdbtnUnicode.setEnabled(false);
			}
		}else if(ae.getActionCommand().equals("generate")){
			String pw = null;
			PWGenerator pwg = new PWGenerator();
			if(!chckbxUseChars.isSelected() && !chckbxUseAlphaNumbers.isSelected()){
				int state = 0;
				if(rdbtnAscii.isSelected()){
					state = 1;
				}else if(rdbtnUtf.isSelected()){
					state = 1;
					//state = 2;
				}else if(rdbtnUnicode.isSelected()){
					state = 1;
					//state = 3;
				}
			pw = pwg.generatePW(state, ((Number)spinner.getValue()).intValue(), tfUnused.getText().toCharArray());
			}else{
				pw = pwg.generatePW(((Number)spinner.getValue()).intValue(), tfUsed.getText().toCharArray());
			}
			taPw.setText(pw);
			taPw.requestFocus();
			taPw.selectAll();
			if(!btnCopyToClipboard.isEnabled()){
				btnCopyToClipboard.setEnabled(true);
			}
		}else if(ae.getActionCommand().equals("clear")){
			taPw.setText("");
			btnCopyToClipboard.setEnabled(false);
		}else if(ae.getActionCommand().equals("about")){
			JOptionPane.showMessageDialog(frame,"Password Generator v1.01 \nMade by Kevin Kügler");
		}else if(ae.getActionCommand().equals("quit")){
			Frame[] frames = Frame.getFrames();  
	        for (Frame f:frames){
	        	f.dispose();
	        }  
		}else if(ae.getActionCommand().equals("copy")){
			StringSelection stringSelection = new StringSelection(taPw.getText());
			Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
			clpbrd.setContents(stringSelection, null);
		}
		
	}
}
