/**
 * @author 
 * <ul>
 * 		<li>Tania López Martín</li>
 * 		<li>Mario Olivera Castañeda</li>
 * 		<li>Ezequiel Villalobos Reyes</li>
 * </ul>
 * @date 29 dic. 2018
 * @version 1.0
 * @description View that shows the content of the email you clicked in previous window.
 * 
 */

package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;

import model.ModelPOP3;

/**
 * Class MailView
 */
public class MailContentViewPOP3 extends JFrame {
	/**
	 * @variable_name footerPanel
	 * @type JPanel
	 */
	private JPanel footerPanel;
	/**
	 * @variable_name panel
	 * @type JPanel
	 */
	private JPanel panel;
	/**
	 * @variable_name titlePanel
	 * @type JPanel
	 */
	private JPanel titlePanel;
	/**
	 * @variable_name textPane
	 * @type JTextPane
	 */
	private JTextPane textPane;
	/**
	 * @variable_name scrollPane
	 * @type JScrollPane
	 */
	private JScrollPane scrollPane;
	/**
	 * @variable_name toText
	 * @type JTextField
	 */
	private JTextField toText;
	/**
	 * @variable_name fromText
	 * @type JTextField
	 */
	private JTextField fromText;
	/**
	 * @variable_name title
	 * @type JTextField
	 */
	private JTextField title;
	/**
	 * @variable_name button
	 * @type JButton
	 */
	private JButton button;
	/**
	 * @variable_name from
	 * @type String
	 */
	private String from;
	/**
	 * @variable_name subject
	 * @type String
	 */
	private String subject;
	/**
	 * @variable_name content
	 * @type String
	 */
	private String content;
	/**
	 * @variable_name model
	 * @type ModelPOP3
	 */
	ModelPOP3 model;

	/**
	 * Class MailContentViewPOP3 Constructor
	 * 
	 * @param from
	 * @param subject
	 * @param content
	 */
	public MailContentViewPOP3(String from, String subject, String content) {
		this.from = from;
		this.subject = subject;
		this.content = content;
		model = new ModelPOP3();
		footerPanel = new JPanel();
		titlePanel = new JPanel();
		title = new JTextField("  " + subject);
		fromText = new JTextField(" " + model.getInfos()[4] + from);
		toText = new JTextField();
		panel = new JPanel();
		textPane = new JTextPane();
		scrollPane = new JScrollPane(textPane);
		button = new JButton(model.getReply());
		setViewProperties();
	}
	
	/**
	 * Class MailView Constructor
	 */
	public MailContentViewPOP3() {
	}

	/**
	 * Method that set properties to the view
	 * 
	 * @name setViewProperties
	 */
	public void setViewProperties() {
		Font font;
		setTitle("Lectura de E-mail");
		getContentPane().setBackground(UIManager.getColor("TextField.selectionBackground"));
		setBackground(SystemColor.activeCaption);
		footerPanel.setLocation(550, 447);
		footerPanel.setMaximumSize(new Dimension(32767, 100));
		footerPanel.setSize(134, 24);
		footerPanel.setBackground(UIManager.getColor("FormattedTextField.selectionBackground"));
		setSize(700, 509);
		setResizable(false);
		font = new Font("SansSerif", Font.BOLD, 16);
		getContentPane().setLayout(null);
		titlePanel.setBounds(10, 11, 674, 127);
		getContentPane().add(titlePanel);
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
		titlePanel.add(title);
		title.setFont(font);
		title.setBackground(UIManager.getColor("FormattedTextField.selectionForeground"));
		title.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		title.setEditable(false);
		titlePanel.add(fromText);
		fromText.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		fromText.setEditable(false);
		titlePanel.add(toText);
		toText.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		toText.setText(model.getInfos()[11]);
		toText.setEditable(false);
		panel.setBounds(10, 138, 674, 298);
		getContentPane().add(panel);
		panel.setLayout(null);
		textPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textPane.setEditable(false);
		textPane.setContentType(model.getInfos()[7]);
		textPane.setText("<html>" + content + "</html>");
		textPane.setBorder((new EmptyBorder(10, 10, 10, 10)));
		scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(0, 0, 674, 298);
		panel.add(scrollPane);
		getContentPane().add(footerPanel);
		footerPanel.setLayout(new BorderLayout(0, 0));
		button.setBorder(UIManager.getBorder("Button.border"));
		footerPanel.add(button);
		setVisible(true);
	}

	/**
	 * Method getter for button
	 * 
	 * @name getButton
	 * @return
	 */
	public JButton getButton() {
		return button;
	}

	/**
	 * Method getter for from
	 * 
	 * @name getFrom
	 * @return
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * Method getter for subject
	 * 
	 * @name getSubject
	 * @return
	 */
	public String getSubject() {
		return subject;
	}
}
