package de.frostbyteger.start;
import java.awt.EventQueue;

import de.frostbyteger.gui.PasswordGenGUI;

/**
 * 
 */

/**
 * @author Kevin
 * @version 1.00
 * This is the main entrance point for the program.
 */
public class PWGen {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					@SuppressWarnings("unused")
					PasswordGenGUI frame = new PasswordGenGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
