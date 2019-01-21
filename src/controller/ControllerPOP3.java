/**
 * @author 
 * <ul>
 * 		<li>Tania López Martín</li>
 * 		<li>Mario Olivera Castañeda</li>
 * 		<li>Ezequiel Villalobos Reyes</li>
 * </ul>
 * @date 29 dic. 2018
 * @version 1.0
 * @description Controller that lets the user to query received mails
 * 
 */

package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.swing.JOptionPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import model.ModelPOP3;
import view.MailContentViewPOP3;
import view.MailListViewPOP3;


/**
 * Class ControllerPOP3
 */
public class ControllerPOP3 implements MouseListener {
	/**
	 * @variable_name model
	 * @type ModelPOP3
	 */
	ModelPOP3 model;
	/**
	 * @variable_name infos
	 * @type ArrayList<String>
	 */
	private ArrayList<String> infos;
	/**
	 * @variable_name sender
	 * @type ArrayList<String>
	 */
	private ArrayList<String> sender;
	/**
	 * @variable_name subject
	 * @type ArrayList<String>
	 */
	private ArrayList<String> subject;
	/**
	 * @variable_name contents
	 * @type ArrayList<String>
	 */
	private ArrayList<String> contents;
	/**
	 * @variable_name mainView
	 * @type MainView
	 */
	private MailListViewPOP3 mailListViewPOP3;
	/**
	 * @variable_name host
	 * @type String
	 */
	private String host;
	/**
	 * @variable_name storeType
	 * @type String
	 */
	private String storeType;
	/**
	 * @variable_name user
	 * @type String
	 */
	private String user;
	/**
	 * @variable_name password
	 * @type String
	 */
	private String password;
	/**
	 * @variable_name mail
	 * @type MailContentViewPOP3
	 */
	private MailContentViewPOP3 mail;
	/**
	 * Class ControllerPOP3 Constructor
	 * @param host
	 * @param storeType
	 * @param user
	 * @param password
	 */
	public ControllerPOP3() {
		model = new ModelPOP3();
		this.host = model.getLogin()[3];
		this.storeType = model.getLogin()[2];
		this.user = model.getLogin()[0];
		this.password = model.getLogin()[1];
		model = new ModelPOP3();
	}

	/**
	 * Method that checks if the data to log in and read emails is correct
	 * @name check 
	 */
	public void check() {
		Properties properties;
		Session emailSession;
		Store store;
		Folder emailFolder;
		Message[] messages;
		infos = new ArrayList<>();
		contents = new ArrayList<>();
		sender = new ArrayList<>();
		subject = new ArrayList<>();

		try {
			properties = new Properties();
			properties.put(model.getProperties()[0], host);
			properties.put(model.getProperties()[1], model.getProperties()[2]);
			properties.put(model.getProperties()[3], model.getProperties()[4]);
			
			emailSession = Session.getDefaultInstance(properties);
			store = emailSession.getStore(model.getStores()[0]);
			store.connect(host, user, password);
			emailFolder = store.getFolder(model.getStores()[1]);
			emailFolder.open(Folder.READ_ONLY);
			messages = emailFolder.getMessages();
			readMessages(messages);
			emailFolder.close(false);
			store.close();
		} catch (NoSuchProviderException e) {
			JOptionPane.showMessageDialog(null, model.getErrors()[0]);
			System.err.println(model.getErrors()[0]);
		} catch (MessagingException e) {
			JOptionPane.showMessageDialog(null, model.getErrors()[0]);
			System.err.println(model.getErrors()[0]);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, model.getErrors()[1]);
			System.err.println(model.getErrors()[1]);
		}
	}

	/**
	 * Method to read messages
	 * 
	 * @name readMessages
	 * @param messages
	 */
	public void readMessages(Message[] messages) {
		String info;
		
		System.out.println(model.getInfos()[0] + messages.length);
		int n = 0;
		
		for (int i = (messages.length - 1); i >= n; i--) {
			Message message = messages[i];
			System.out.println(model.getInfos()[1]);
			System.out.println(model.getInfos()[2] + (i + 1));
			
			try {
				String contentType;
				String messageContent;
				String text;
				Address[] froms = message.getFrom();
				String email = froms == null ? null
						: ((InternetAddress) froms[0]).getPersonal() + " (" + ((InternetAddress) froms[0]).getAddress()
								+ ") ";
				Document doc;
				System.out.println(model.getInfos()[3] + message.getSubject());
				System.out.println(model.getInfos()[4] + email);
				contentType = message.getContentType();
				messageContent = "";

				if (contentType.contains(model.getInfos()[5])) {
					Multipart multiPart = (Multipart) message.getContent();
					int numberOfParts = multiPart.getCount();

					for (int j = 0; j < numberOfParts; j++) {
						MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(j);
						messageContent = part.getContent().toString();
					}
				} else if (contentType.contains(model.getInfos()[6]) || contentType.contains(model.getInfos()[7])) {
					Object content = message.getContent();

					if (content != null) {
						messageContent = content.toString();
					}
				}

				doc = Jsoup.parse(messageContent);
				text = doc.body().text();
				System.out.println(model.getInfos()[8] + text);
				contents.add(messageContent);
				info = (i + 1) + model.getInfos()[9] + email + model.getInfos()[10] + message.getSubject();
				subject.add(message.getSubject());
				sender.add(email);
				infos.add(info);
			} catch (MessagingException e) {
				JOptionPane.showMessageDialog(null, model.getErrors()[0]);
				System.err.println(model.getErrors()[0]);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, model.getErrors()[0]);
				System.err.println(model.getErrors()[0]);
			}
		}
	}

	/**
	 * Method to create interface
	 * 
	 * @name createInterface
	 */
	public void createInterface() {
		mailListViewPOP3 = new MailListViewPOP3();
		mailListViewPOP3.addText(infos);
		mailListViewPOP3.setContents(contents);
		mailListViewPOP3.manageTextFields();
		addEvents(mailListViewPOP3);
	}

	/**
	 * Method to add events
	 * 
	 * @name addEvents
	 * @param mailListViewPOP3
	 */
	public void addEvents(MailListViewPOP3 mailListViewPOP3) {
		for (int i = 0; i < mailListViewPOP3.getTextFields().size(); i++) {
			mailListViewPOP3.getTextFields().get(i).addMouseListener(this);
		}
	}
	
	/**
	 * Method to review message and delete css tag
	 * @name reviewContent
	 * @param content
	 * @return 
	 */
	public String reviewContent(String content) {
		String[] cssParse = content.split("<style");
		String[] cssParse2;
		String allInfo = "";

		for (int i = 0; i < cssParse.length; i++) {
			if (cssParse[i].contains("</style>")) {
				cssParse2 = cssParse[i].split("</style>");

				for (int j = 0; j < cssParse2.length; j++) {
					if (j != 0) {
						allInfo += cssParse2[j];
					}
				}
			} else {
				allInfo += cssParse[i];
			}
		}
		
		return allInfo;
	}

	/**
	 * Method
	 * 
	 * @name mouseClicked
	 * @param arg0
	 * @overriden @see
	 *            java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		mail = new MailContentViewPOP3();
		
		for (int i = 0; i < mailListViewPOP3.getTextFields().size(); i++) {
			String content;
			
			if (e.getSource().equals(mailListViewPOP3.getTextFields().get(i))) {
				content = reviewContent(contents.get(i));
				mail = new MailContentViewPOP3(sender.get(i), subject.get(i), content);
				mail.setVisible(true);
				mail.getButton().addActionListener((new ActionListener() {

					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						String[] sender = mail.getFrom().split("\\(");
						ControllerSMTP smtpController = new ControllerSMTP();
						smtpController.getViewMessage().getTo().setText(sender[1].replaceAll("\\)", ""));
						smtpController.getViewMessage().getSubject().setText("RE: " + mail.getSubject());
						smtpController.getViewMessage().setVisible(true);
					}
		        }));
			}
		}
		
		if(e.getSource().equals(mail.getButton())) {
			
		}
	}

	/**
	 * Method
	 * 
	 * @name mouseEntered
	 * @param arg0
	 * @overriden @see
	 *            java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		for (int i = 0; i < mailListViewPOP3.getTextFields().size(); i++) {
			if (e.getSource().equals(mailListViewPOP3.getTextFields().get(i))) {
				mailListViewPOP3.getTextFields().get(i).setBackground(Color.BLUE);
				mailListViewPOP3.getTextFields().get(i).setForeground(Color.WHITE);
				mailListViewPOP3.getTextFields().get(i).setOpaque(true);
				mailListViewPOP3.repaint();
			}
		}
	}

	/**
	 * Method
	 * 
	 * @name mouseExited
	 * @param arg0
	 * @overriden @see
	 *            java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		for (int i = 0; i < mailListViewPOP3.getTextFields().size(); i++) {
			if (e.getSource().equals(mailListViewPOP3.getTextFields().get(i))) {
				mailListViewPOP3.getTextFields().get(i).setOpaque(false);
				mailListViewPOP3.getTextFields().get(i).setForeground(Color.BLACK);
				mailListViewPOP3.repaint();
			}
		}
	}

	/**
	 * Method
	 * 
	 * @name mousePressed
	 * @param arg0
	 * @overriden @see
	 *            java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	/**
	 * Method
	 * 
	 * @name mouseReleased
	 * @param arg0
	 * @overriden @see
	 *            java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
}
