/**
 * @author 
 * <ul>
 * 		<li>Tania López Martín</li>
 * 		<li>Mario Olivera Castañeda</li>
 * 		<li>Ezequiel Villalobos Reyes</li>
 * </ul>
 * @date 29 dic. 2018
 * @version 1.0
 * @description View that shows a window to send messages
 * 
 */


package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.ModelSMTP;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.UIManager;


public class ViewSMTP1 extends JFrame{

	/**
	 * @variable_name serialVersionUID
	 * @type long
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @variable_name model
	 * @type ModelSMTP
	 */
	private ModelSMTP model;
	/**
	 * @variable_name contentPane
	 * @type JPanel
	 */
	private JPanel contentPane;
	/**
	 * @variable_name to
	 * @type JTextField
	 */
	private JTextField to;
	/**
	 * @variable_name subject
	 * @type JTextField
	 */
	private JTextField subject;
	/**
	 * @variable_name scrollPane
	 * @type JScrollPane
	 */
	private JScrollPane scrollPane;
	/**
	 * @variable_name message
	 * @type JTextArea
	 */
	private JTextArea message;
	/**
	 * @variable_name send
	 * @type JButton
	 */
	private JButton send;
	/**
	 * @variable_name closeProgram
	 * @type JButton
	 */
	private JButton closeProgram;
	/**
	 * @variable_name clearBox
	 * @type JButton
	 */
	private JButton clearBox;


	/**
	 * Class ViewSMTP1 Constructor
	 */
	public ViewSMTP1() {
		model = new ModelSMTP();
		elementView();
	}
	
	/**
	 * Method that implements view elements
	 * @name elementView 
	 */
	public void elementView() {
		setTitle("Envío de E-mail");
		setLocation(370, 150);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("TextField.selectionBackground"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel(model.getNameTags().get(0));
		lblNewLabel.setBounds(10, 11, 66, 14);
		contentPane.add(lblNewLabel);
		
		to = new JTextField();
		to.setBounds(86, 8, 488, 20);
		contentPane.add(to);
		to.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel(model.getNameTags().get(1));
		lblNewLabel_1.setBounds(10, 45, 66, 14);
		contentPane.add(lblNewLabel_1);
		
		subject = new JTextField();
		subject.setBounds(86, 42, 488, 20);
		contentPane.add(subject);
		subject.setColumns(10);
		
		send = new JButton(model.getNameButtons().get(0));
		send.setBounds(10, 327, 89, 23);
		contentPane.add(send);
		
		closeProgram = new JButton(model.getNameButtons().get(1));
		closeProgram.setBounds(213, 327, 150, 23);
		contentPane.add(closeProgram);
		
		clearBox = new JButton(model.getNameButtons().get(2));
		clearBox.setBounds(424, 327, 150, 23);
		contentPane.add(clearBox);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 100, 564, 203);
		contentPane.add(scrollPane);
		
		message = new JTextArea();
		scrollPane.setViewportView(message);
	}
	
	/**
	 * Metho getter for to
	 * @return the to
	 */
	public JTextField getTo() {
		return to;
	}

	/**
	 * Method setter for to
	 * @param to the to to set
	 */
	public void setTo(JTextField to) {
		this.to = to;
	}

	/**
	 * Method getter for subject
	 * @return the subject
	 */
	public JTextField getSubject() {
		return subject;
	}

	/**
	 * Metho setter for subject
	 * @param subject the subject to set
	 */
	public void setSubject(JTextField subject) {
		this.subject = subject;
	}
	
	/**
	 * Method getter for send
	 * @return the send
	 */
	public JButton getSend() {
		return send;
	}

	/**
	 * Method getter for closeProgram
	 * @return the closeProgram
	 */
	public JButton getCloseProgram() {
		return closeProgram;
	}

	/**
	 * Method getter for clearbox
	 * @return the clearBox
	 */
	public JButton getClearBox() {
		return clearBox;
	}

	/**
	 * Method getter for message
	 * @return the message
	 */
	public JTextArea getMessage() {
		return message;
	}

	/**
	 * Method setter for message
	 * @name setMessage
	 * @param message 
	 */
	public void setMessage(JTextArea message) {
		this.message = message;
	}
}
