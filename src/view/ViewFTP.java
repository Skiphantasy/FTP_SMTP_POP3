/**
 * @author 
 * <ul>
 * 		<li>Tania López Martín</li>
 * 		<li>Mario Olivera Castañeda</li>
 * 		<li>Ezequiel Villalobos Reyes</li>
 * </ul>
 * @date 29 dic. 2018
 * @version 1.0
 * @description View for FTP
 * 
 */


package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

import controller.ControllerClientFTP;
import controller.ControllerServerFTP;
import model.ModelFTP;


/**
 * Class ViewFTP
 */
public class ViewFTP extends JFrame{
	/**
	 * @variable_name listDirectory
	 * @type JList
	 */
	private JList listDirectory;
	/**
	 * @variable_name content
	 * @type Container
	 */
	private Container content;
	/**
	 * @variable_name labels
	 * @type ArrayList<JLabel>
	 */
	private ArrayList <JLabel> labels;
	/**
	 * @variable_name buttons
	 * @type ArrayList<JButton>
	 */
	private ArrayList <JButton> buttons;
	/**
	 * @variable_name scroll
	 * @type JScrollPane
	 */
	private JScrollPane scroll;
	/**
	 * @variable_name model
	 * @type ModelFTP
	 */
	private ModelFTP model;
	
	/**
	 * Class ViewFTP Constructor
	 */
	public ViewFTP() {
		super("EMT FTP");
		content = getContentPane();
		listDirectory = new JList();
		model = new ModelFTP();
		labels = new ArrayList <>();
		buttons = new ArrayList<>();
		propertiesLabels();
		propertiesList();
		propertiesButtons();
		content.setBackground(UIManager.getColor("TextField.selectionBackground"));
		content.setFont(new Font("Tahoma", Font.BOLD, 13));
		listDirectory.setFont(new Font("Tahoma", Font.BOLD, 13));
		content.setFont(new Font("Tahoma", Font.BOLD, 13));
		setSize(621, 600);
		setVisible(true);
		
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	try {
					ControllerClientFTP.getClient().disconnect();
					ControllerServerFTP.getServer().stop();
				} catch (IOException el) {
					System.err.println(model.getErrors().get(6));
				}
				
				dispose();
		    }
		});
	}
	/**
	 * Method that sets labels properties
	 * @name propertiesLabels 
	 */
	public void propertiesLabels(){
		for(int i = 0; i < model.getLabelsNames().size(); i++) {
			labels.add(new JLabel(model.getLabelsNames().get(i)));
			content.add(labels.get(i));
		}
		
		labels.get(0).setBounds(392, 13, 188, 22);
		labels.get(1).setBounds(12, 13, 177, 22);
		labels.get(2).setBounds(12, 40, 177, 22);
		labels.get(3).setBounds(15, 490, 325, 22);
		labels.get(4).setBounds(12, 525, 328, 22);
	}
	
	/**
	 * Method that sets list properties
	 * @name propertiesList 
	 */
	public void propertiesList() {
		listDirectory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scroll = new JScrollPane(listDirectory);
		scroll.setPreferredSize (new Dimension(335, 420));
		scroll.setBounds(new Rectangle (5, 65, 335, 420));
		content.add(scroll);
		content.setLayout(null);
	}
	
	/**
	 * Method that sets buttons properties
	 * @name propertiesButtons 
	 */
	public void propertiesButtons(){
		for(int i = 0; i < model.getButtonsNames().size(); i++) {
			buttons.add(new JButton(model.getButtonsNames().get(i)));
			content.add(buttons.get(i));
		}
		
		buttons.get(0).setBounds(368, 82, 172, 25);
		buttons.get(1).setBounds(368, 148, 172, 25);
		buttons.get(2).setBounds(368, 210, 172, 25);
		buttons.get(3).setBounds(368, 288, 172, 25);
		buttons.get(4).setBounds(368, 357, 172, 25);
		buttons.get(5).setBounds(368, 417, 172, 25);
	}
	
	/**
	 * Method getter for listdirectory
	 * @name getListDirectory
	 * @return 
	 */
	public JList getListDirectory() {
		return listDirectory;
	}
	
	/**
	 * Method setter for listdirectory
	 * @name setListDirectory
	 * @param listDirectory 
	 */
	public void setListDirectory(JList listDirectory) {
		this.listDirectory = listDirectory;
	}
	
	/**
	 * Method getter for content
	 * @name getContent
	 * @return 
	 */
	public Container getContent() {
		return content;
	}
	
	/**
	 * Method setter for content
	 * @name setContent
	 * @param content 
	 */
	public void setContent(Container content) {
		this.content = content;
	}
	
	/**
	 * Method getter for labels
	 * @name getLabels
	 * @return 
	 */
	public ArrayList<JLabel> getLabels() {
		return labels;
	}
	
	/**
	 * Method setter for labels
	 * @name setLabels
	 * @param labels 
	 */
	public void setLabels(ArrayList<JLabel> labels) {
		this.labels = labels;
	}
	
	/**
	 * Method getter for buttons
	 * @name getButtons
	 * @return 
	 */
	public ArrayList<JButton> getButtons() {
		return buttons;
	}
	
	/**
	 * Method setter for buttons
	 * @name setButtons
	 * @param buttons 
	 */
	public void setButtons(ArrayList<JButton> buttons) {
		this.buttons = buttons;
	}	
}
