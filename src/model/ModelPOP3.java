/**
 * @author 
 * <ul>
 * 		<li>Tania López Martín</li>
 * 		<li>Mario Olivera Castañeda</li>
 * 		<li>Ezequiel Villalobos Reyes</li>
 * </ul>
 * @date 29 dic. 2018
 * @version 1.0
 * @description Model that contains all the strings related to the program
 * 
 */


package model;


/**
 * Class ModelPOP3
 */
public class ModelPOP3 {
	/**
	 * @variable_name inbox
	 * @type String
	 */
	private String inbox;
	/**
	 * @variable_name reply
	 * @type String
	 */
	private String reply;
	/**
	 * @variable_name login
	 * @type String[]
	 */
	private String[] login = {"*****@hotmail.com", "******", "pop3", "pop3.live.com"};
	/**
	 * @variable_name properties
	 * @type String[]
	 */
	private String[] properties = {"mail.pop3.host", "mail.pop3.port", "995", "mail.pop3.starttls.enable",
			"true"};
	/**
	 * @variable_name stores
	 * @type String[]
	 */
	private String[] stores = {"pop3s", "INBOX"};
	/**
	 * @variable_name errors
	 * @type String[]
	 */
	private String[] errors = {"No se han podido recuperar los mensajes", "Error al recuperar los mensajes"};
	/**
	 * @variable_name infos
	 * @type String[]
	 */
	private String[] infos = {"Número de mensajes: ", "---------------------------------",
			"Email número ", "Asunto: ", "De: ", "multipart", "text/plain", "text/html", "Mensaje: ",
			". De: ", " -  Asunto: ", "  Para mí"};
	
	/**
	 * Class ModelPOP3 Constructor
	 */
	public ModelPOP3() {
		inbox = "Bandeja de Entrada";
		reply = "Responder";
	}
		
	/**
	 * Method getter for reply
	 * @name getReply
	 * @return 
	 */
	public String getReply() {
		return reply;
	}

	/**
	 * Method getter for properties
	 * @name getProperties
	 * @return 
	 */
	public String[] getProperties() {
		return properties;
	}
	
	/**
	 * Method getter for stores
	 * @name getStores
	 * @return 
	 */
	public String[] getStores() {
		return stores;
	}
	
	/**
	 * Method getter for errors
	 * @name getErrors
	 * @return 
	 */
	public String[] getErrors() {
		return errors;
	}
	
	/**
	 * Method getter for infos
	 * @name getInfos
	 * @return 
	 */
	public String[] getInfos() {
		return infos;
	}

	/**
	 * Method getter for inbox
	 * @name getInbox
	 * @return 
	 */
	public String getInbox() {
		return inbox;
	}

	/**
	 * Method getter for login
	 * @name getLogin
	 * @return 
	 */
	public String[] getLogin() {
		return login;
	}	
}
