/**
 * @author 
 * <ul>
 * 		<li>Tania López Martín</li>
 * 		<li>Mario Olivera Castañeda</li>
 * 		<li>Ezequiel Villalobos Reyes</li>
 * </ul>
 * @date 29 dic. 2018
 * @version 1.0
 * @description View that shows all the emails with senders and subjects 
 * in which you can click to see the content.
 * 
 */


package view;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.ModelPOP3;

import javax.swing.UIManager;
import javax.swing.border.BevelBorder;


/**
 * Class MainView
 */
public class MailListViewPOP3 extends JFrame{
	/**
	 * @variable_name panel
	 * @type JPanel
	 */
	private JPanel panel;
	/**
	 * @variable_name textfields
	 * @type ArrayList<JTextField>
	 */
	private ArrayList<JTextField> textfields;
	/**
	 * @variable_name contents
	 * @type ArrayList<String>
	 */
	private ArrayList<String> contents;
	/**
	 * @variable_name scrollPanel
	 * @type JScrollPane
	 */
	private JScrollPane scrollPanel;
	/**
	 * @variable_name headPanel
	 * @type JPanel
	 */
	private JPanel headPanel;
	/**
	 * @variable_name inbox
	 * @type JLabel
	 */
	private JLabel inbox;
	/**
	 * @variable_name model
	 * @type ModelPOP3
	 */
	ModelPOP3 model;

	/**
	 * Class MailListViewPOP3 Constructor
	 */
	public MailListViewPOP3() {
		model = new ModelPOP3();
		headPanel = new JPanel();			
		inbox = new JLabel(model.getInbox());			
		panel = new JPanel();
		textfields = new ArrayList<>();
		contents = new ArrayList<>();		
		scrollPanel = new JScrollPane(panel);
		setViewProperties();
	}
	
	/**
	 * Method that set properties to the view
	 * @name setViewProperties 
	 */
	public void setViewProperties() {
		setResizable(false);
		setTitle("E-mails recibidos");
		getContentPane().setBackground(UIManager.getColor("TextField.selectionBackground"));
		setSize(700, 500);
		headPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		headPanel.setBounds(10, 7, 664, 34);
		getContentPane().setLayout(null);
		getContentPane().add(headPanel);	
		inbox.setBorder(new EmptyBorder(5, 16, 5, 16));				
		headPanel.add(inbox);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		scrollPanel.setLocation(10, 52);
		scrollPanel.setSize(664, 398);
		getContentPane().add(scrollPanel);
	}

	/**
	 * Method
	 * @name addText
	 * @param infos 
	 */
	public void addText(ArrayList<String> infos) {
		for (int i = 0; i < infos.size(); i++) {
			textfields.add(new JTextField("  " + infos.get(i)));	
		}
	}
	
	/**
	 * Method
	 * @name addContent
	 * @param content 
	 */
	public void addContent(String content) {
		contents.add(content);
	}
	
	/**
	 * Method
	 * @name manageTextFields 
	 */
	public void manageTextFields() {
		//Collections.reverse(textfields);
		
		for (int i = 0; i < textfields.size(); i++) {
			panel.add(textfields.get(i));
			textfields.get(i).setEditable(false);
			textfields.get(i).setPreferredSize(new Dimension(900, 40));
			textfields.get(i).setMaximumSize(new Dimension(900, 40));		
			textfields.get(i).setMinimumSize(new Dimension(900, 40));
		}
		
		setVisible(true);
	}

	/**
	 * Method getter for textfields
	 * @name getTextFields
	 * @return 
	 */
	public ArrayList<JTextField> getTextFields() {
		return textfields;
	}

	/**
	 * Method setter for textfields
	 * @name setTextFields
	 * @param textfields 
	 */
	public void setTextFields(ArrayList<JTextField> textfields) {
		this.textfields = textfields;
	}
	
	/**
	 * Method getter for contents
	 * @name getContents
	 * @return 
	 */
	public ArrayList<String> getContents() {
		return contents;
	}

	/**
	 * Method setter for contents
	 * @name setContents
	 * @param contents 
	 */
	public void setContents(ArrayList<String> contents) {
		this.contents = contents;
	}
}
