/**
 * @author 
 * <ul>
 * 		<li>Tania López Martín</li>
 * 		<li>Mario Olivera Castañeda</li>
 * 		<li>Ezequiel Villalobos Reyes</li>
 * </ul>
 * @date 29 dic. 2018
 * @version 1.0
 * @description View that shows main Menu
 * 
 */


package view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.UIManager;


/**
 * Class MenuView
 */
public class MenuView extends JFrame {

	/**
	 * @variable_name contentPane
	 * @type JPanel
	 */
	private JPanel contentPane;
    /**
     * @variable_name ftpButton
     * @type JButton
     */
    private JButton ftpButton;
	/**
     * @variable_name smtpButton
     * @type JButton
     */
    private JButton smtpButton;
    /**
     * @variable_name pop3Button
     * @type JButton
     */
    private JButton pop3Button;

	/**
	 * Class MenuView Constructor
	 */
	public MenuView() {
		contentPane = new JPanel();
		ftpButton = new JButton();
		smtpButton = new JButton();
		pop3Button = new JButton();
		setViewProperties();
	}
	
	/**
	 * Method
	 * @name setViewProperties 
	 */
	public void setViewProperties() {
		setTitle("Peval3 PSP");
		setBackground(UIManager.getColor("Label.disabledForeground"));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 242, 288);
		contentPane.setBackground(new Color(70, 130, 180));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);	
		ftpButton.setOpaque(false);
		ftpButton.setBorderPainted(false);
		ftpButton.setFocusPainted(false);
		ftpButton.setBorder(null);
		ftpButton.setBackground(new Color(0, 0, 51));
		ftpButton.setIcon(new ImageIcon("ftpbutton.png"));
		ftpButton.setBounds(36, 30, 159, 65);
		contentPane.add(ftpButton);	
		smtpButton.setIcon(new ImageIcon("smtpbutton.png"));
		smtpButton.setOpaque(false);
		smtpButton.setFocusPainted(false);
		smtpButton.setBorderPainted(false);
		smtpButton.setBorder(null);
		smtpButton.setBackground(new Color(0, 0, 51));
		smtpButton.setBounds(36, 93, 159, 65);
		contentPane.add(smtpButton);		
		pop3Button.setIcon(new ImageIcon("pop3button.png"));
		pop3Button.setOpaque(false);
		pop3Button.setFocusPainted(false);
		pop3Button.setBorderPainted(false);
		pop3Button.setBorder(null);
		pop3Button.setBackground(new Color(0, 0, 51));
		pop3Button.setBounds(36, 156, 159, 65);
		contentPane.add(pop3Button);
		setVisible(true);
	}
	
    /**
     * Method getter for ftpButton
     * @name getFtpButton
     * @return 
     */
    public JButton getFtpButton() {
		return ftpButton;
	}

	/**
	 * Method getter for smtpButton
	 * @name getSmtpButton
	 * @return 
	 */
	public JButton getSmtpButton() {
		return smtpButton;
	}

	/**
	 * Method getter for pop3Button
	 * @name getPop3Button
	 * @return 
	 */
	public JButton getPop3Button() {
		return pop3Button;
	}
}
