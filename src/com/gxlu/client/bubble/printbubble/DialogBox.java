package com.gxlu.client.bubble.printbubble;

import java.awt.Component;
import java.awt.Frame;

import javax.swing.JOptionPane;

public class DialogBox {
	
	private Component parent;
	private String message;
	private String title;
	private int optionType;
	private int messageType;
	private Object[] options;
	
	public DialogBox() {
		
	}
	
	public DialogBox(Component parent,String message,String title,int optionType,
			int messageType,Object[] options) {
		this.parent = parent;
		this.message = message;
		this.title = title;
		this.optionType = optionType;
		this.messageType = messageType;
		this.options = options;
		JOptionPane.setRootFrame((Frame) parent);
	}
	
	public void CreateDialogBox() {
		JOptionPane.setRootFrame((Frame) this.parent);
		JOptionPane.showOptionDialog(this.parent, this.message, this.title, 
				this.optionType, this.messageType,
				null, this.options, this.options[0]);
	}
	
	public static void CreateInfoDialog(Component parent,String message,String title) {
		Object[] options = { "OK" };
		JOptionPane.setRootFrame((Frame) parent);
		JOptionPane.showOptionDialog(parent, message, title, 
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
				null, options, options[0]);
	}
	
	public static int CreateWarningDialog(Component parent,String message,String title) {
		Object[] options = { "OK","Cancel" };
		JOptionPane.setRootFrame((Frame) parent);
		return JOptionPane.showOptionDialog(parent, message, title, 
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
				null, options, options[0]);
	}
	
	public static void CreateErrorDialog(Component parent,String message,String title) {
		Object[] options = { "OK","Cancel" };
		JOptionPane.setRootFrame((Frame) parent);
		JOptionPane.showOptionDialog(parent, message, title, 
				JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE,
				null, options, options[0]);
	}
	
	public static String CreateInputDialog(String message) {
		String inputValue = JOptionPane.showInputDialog(message);
		return inputValue;
	}
}
