/**
 * @author 
 * <ul>
 * 		<li>Tania López Martín</li>
 * 		<li>Mario Olivera Castañeda</li>
 * 		<li>Ezequiel Villalobos Reyes</li>
 * </ul>
 * @date 29 dic. 2018
 * @version 1.0
 * @description Controller that contains SMTP methods to send emails
 * 
 */


package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import model.ModelSMTP;
import view.ViewSMTP1;


/**
 * Class ControllerSMTP
 */
public class ControllerSMTP implements ActionListener{
	
	/**
	 * @variable_name viewMessage
	 * @type ViewSMTP1
	 */
	ViewSMTP1 viewMessage;

	/**
	 * @variable_name model
	 * @type ModelSMTP
	 */
	ModelSMTP model;
	/**
	 * @variable_name properties
	 * @type Properties
	 */
	private final static Properties properties = new Properties();
	
	/**
	 * Class ControllerSMTP Constructor
	 */
	public ControllerSMTP() {
		viewMessage = new ViewSMTP1();
		viewMessage.getSend().addActionListener(this);		
		viewMessage.getCloseProgram().addActionListener(this);
		viewMessage.getClearBox().addActionListener(this);
		viewMessage.setVisible(true);	
		model = new ModelSMTP();
	}	
	
	/**
	 * Method that set message properties and send message
	 * 
	 * @param receiver
	 * @param subject
	 * @param bodyMessage
	 */
	private void sendEmail(String receiver, String subject, String bodyMessage) {
		String sender = model.getLogin().get(0);
		String password = model.getLogin().get(1);
		
		Authenticator authenticator = new Authenticator()
	        {
	            @Override
	            protected PasswordAuthentication getPasswordAuthentication()
	            {
	                return new PasswordAuthentication(sender, password);
	            }
	        };
	        
		properties.put("mail.smtp.host", "smtp.live.com");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "25");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.user", sender);
		properties.put("mail.password", password);
		
		Session session = Session.getInstance(properties, authenticator);
		MimeMessage message = new MimeMessage(session);
		
		try {
			message.setFrom(new InternetAddress(sender));
			message.addRecipients(Message.RecipientType.TO, receiver);
			message.setSubject(subject);				
			message.setText(bodyMessage);
			message.setContent(bodyMessage.replaceAll("\n", "<br>"), "text/html; charset=utf-8");
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.live.com", sender, password);
			System.out.println(model.getInfos().get(1));
			transport.sendMessage(message, message.getAllRecipients());
			JOptionPane.showMessageDialog(null, model.getInfos().get(2));
			transport.close();
		}
		catch (MessagingException me) {
			JOptionPane.showMessageDialog(null, model.getInfos().get(3) + "\n" 
					+ model.getInfos().get(4));
			System.err.println(model.getInfos().get(3));
			System.err.println(model.getInfos().get(4));
		}
	}
	
	/**
	 * Method that checks if textfield "to" has the correct format
	 * 
	 * @param mail
	 * @return true/false
	 */
	public boolean emailControl(String mail) {
		Pattern pat = null;
		Matcher mat = null;
		pat = Pattern.compile("^[\\w\\\\\\+]+(\\.[\\w\\\\]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$");
		mat = pat.matcher(mail);
		
		if(mat.find()) {
			return true;
		}else {
			return false;
		}
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	/**
	 * Method
	 * @name actionPerformed
	 * @param e 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(viewMessage.getSend())) {
		    String recipient =  viewMessage.getTo().getText();
		    String subject = viewMessage.getSubject().getText();
		    String message = viewMessage.getMessage().getText();
		    
		    if(emailControl(viewMessage.getTo().getText())) {
		    	sendEmail(recipient, subject, message);
		    }else {
		    	JOptionPane.showMessageDialog(null, model.getInfos().get(5));
		    	viewMessage.getTo().requestFocus();
		    }
		}else if(e.getSource().equals(viewMessage.getCloseProgram())) {
			System.out.println("\n=============================");
			System.out.println(model.getInfos().get(6));
			System.out.println("=============================");
			viewMessage.dispose();
		}else if(e.getSource().equals(viewMessage.getClearBox())) {
			viewMessage.getTo().setText("");
		    viewMessage.getSubject().setText("");
		    viewMessage.getMessage().setText("");		    
		    JOptionPane.showMessageDialog(null, model.getInfos().get(7));
		}
	}
	
	/**
	 * Method setter for viewMessage
	 * @name getViewMessage
	 * @return 
	 */
	public ViewSMTP1 getViewMessage() {
		return viewMessage;
	}
}


