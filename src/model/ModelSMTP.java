/**
 * @author 
 * <ul>
 * 		<li>Tania López Martín</li>
 * 		<li>Mario Olivera Castañeda</li>
 * 		<li>Ezequiel Villalobos Reyes</li>
 * </ul>
 * @date 29 dic. 2018
 * @version 1.0
 * @description Class that contains the strings for the SMTP elements
 * 
 */


package model;

import java.util.ArrayList;


/**
 * Class ModelSMTP
 */
public class ModelSMTP {
	/**
	 * @variable_name nameButtons
	 * @type ArrayList<String>
	 */
	ArrayList <String> nameButtons;
	/**
	 * @variable_name nameTags
	 * @type ArrayList<String>
	 */
	ArrayList <String> nameTags;
	/**
	 * @variable_name login
	 * @type ArrayList<String>
	 */
	ArrayList <String> login;

	/**
	 * @variable_name infos
	 * @type ArrayList<String>
	 */
	ArrayList <String> infos;
	/**
	 * Class ModelSMTP Constructor
	 */
	public ModelSMTP() {
		nameButtons = new ArrayList<>();
		login = new ArrayList<>();
		infos = new ArrayList<>();
		nameTags = new ArrayList<>();
		nameButtons.add("Enviar");
		nameButtons.add("Cerrar Programa");
		nameButtons.add("Limpiar Ventana");
		nameTags.add("Para:");
		nameTags.add("Asunto:");
		nameTags.add("Cuerpo del Mensaje:");
		login.add("*****@hotmail.com");
		login.add("******");
		infos.add("ESTABLECIENDO LAS PROPERTIES");
		infos.add("ENVIANDO...");
		infos.add("MENSAJE ENVIADO");
		infos.add("EL MENSAJE NO SE HA PODIDO ENVIAR YA QUE NO SE ESTABLECIERON LOS DATOS NECESARIOS PARA EL ENVIO:\n");
		infos.add("revisa que el correo sea correcto");
		infos.add("Debe rellenar el campo del correo correctamente");
		infos.add("CERRANDO VENTANA");
		infos.add("LIMPIEZA DE LA VENTANA REALIZADA CON EXITO");
	}

	//Getter's and Setter's
	/**
	 * Method getter for nameButtons
	 * @return the nombreBotones
	 */
	public ArrayList<String> getNameButtons() {
		return nameButtons;
	}

	/**
	 * Method getter for nameTags
	 * @return the nombreEtiquetas
	 */
	public ArrayList<String> getNameTags() {
		return nameTags;
	}
	
	/**
	 * Method getter for login
	 * @name getLogin
	 * @return 
	 */
	public ArrayList<String> getLogin() {
		return login;
	}

	/**
	 * Method getter for infos
	 * @name getInfos
	 * @return 
	 */
	public ArrayList<String> getInfos() {
		return infos;
	}
}
