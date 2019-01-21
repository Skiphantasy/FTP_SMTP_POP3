/**
 * @author 
 * <ul>
 * 		<li>Tania López Martín</li>
 * 		<li>Mario Olivera Castañeda</li>
 * 		<li>Ezequiel Villalobos Reyes</li>
 * </ul>
 * @date 29 dic. 2018
 * @version 1.0
 * @description Model that contains all the strings related to the FTP 
 * 
 */


package model;

import java.util.ArrayList;


/**
 * Class ModelFTP
 */
public class ModelFTP {
	/**
	 * @variable_name propertiesServer
	 * @type ArrayList<String>
	 */
	private ArrayList<String> propertiesServer;
	/**
	 * @variable_name labelsNames
	 * @type ArrayList<String>
	 */
	private ArrayList<String> labelsNames;
	/**
	 * @variable_name buttonsNames
	 * @type ArrayList<String>
	 */
	private ArrayList<String> buttonsNames;
	/**
	 * @variable_name deleteDirectory
	 * @type ArrayList<String>
	 */
	private ArrayList<String> deleteDirectory;
	/**
	 * @variable_name createDirectory
	 * @type ArrayList<String>
	 */
	private ArrayList<String> createDirectory;
	/**
	 * @variable_name uploadFile
	 * @type ArrayList<String>
	 */
	private ArrayList<String> uploadFile;
	/**
	 * @variable_name list
	 * @type ArrayList<String>
	 */
	private ArrayList<String> list;
	/**
	 * @variable_name uploadFileMethod
	 * @type ArrayList<String>
	 */
	private ArrayList<String> uploadFileMethod;
	/**
	 * @variable_name downloadFileMethod
	 * @type ArrayList<String>
	 */
	private ArrayList<String> downloadFileMethod;
	/**
	 * @variable_name deleteFileMethod
	 * @type ArrayList<String>
	 */
	private ArrayList<String> deleteFileMethod;
	/**
	 * @variable_name errors
	 * @type ArrayList<String>
	 */
	private ArrayList<String> errors;
	/**
	 * @variable_name fillList
	 * @type String
	 */
	private String fillList;
	/**
	 * @variable_name rootDirectory
	 * @type String
	 */
	private String rootDirectory;
	/**
	 * @variable_name folder
	 * @type String
	 */
	private String folder;
	/**
	 * Class ModelFTP Constructor
	 */
	
	public ModelFTP() {
		propertiesServer = new ArrayList<>();
		labelsNames = new ArrayList<>();
		buttonsNames = new ArrayList<>();
		deleteDirectory = new ArrayList<>();
		createDirectory = new ArrayList<>();
		uploadFile = new ArrayList<>();
		list = new ArrayList<>();
		uploadFileMethod = new ArrayList<>();
		downloadFileMethod = new ArrayList<>();
		deleteFileMethod = new ArrayList<>();
		errors = new ArrayList<>();
		fillList = "[DIR] ";
		rootDirectory = "/";
		folder = "carpeta";
		fillPropertiesServer();
		fillButtonsNames();
		fillDeleteDirectory();
		fillLabelsName();
		fillCreateDirectory();
		fillUploadFile();
		fillList();
		fillUploadFileMethod();
		fillDownloadFileMethod();
		fillDeleteFileMethod();
		fillErrors();
	}
	
	/**
	 * Method that fills labels names
	 * @name fillLabelsName 
	 */
	public void fillLabelsName() {
		labelsNames.add("Servidor FTP: " + propertiesServer.get(3));
		labelsNames.add("Usuario: " + propertiesServer.get(1));
		labelsNames.add("Directorio Raíz: " + rootDirectory);
		labelsNames.add("« ARBOL DE DIRECTORIOS CONSTRUIDO »");
		labelsNames.add("");
	}
	
	/**
	 * Method that fills server properties
	 * @name fillPropertiesServer 
	 */
	public void fillPropertiesServer() {
		propertiesServer.add("C:\\CarpetaFTP");
		propertiesServer.add("emtPeval3");
		propertiesServer.add("emt1234");
		propertiesServer.add("127.0.0.1");
	}
	
	/**
	 * Method that fills buttons names
	 * @name fillButtonsNames 
	 */
	public void fillButtonsNames() {
		buttonsNames.add("Subir Fichero");
		buttonsNames.add("Descargar Fichero");
		buttonsNames.add("Eliminar Fichero");
		buttonsNames.add("Crear Carpeta");
		buttonsNames.add("Eliminar Carpeta");
		buttonsNames.add("Salir");
	}
	
	/**
	 * Method that fills delete directory
	 * @name fillDeleteDirectory 
	 */
	public void fillDeleteDirectory() {
		deleteDirectory.add("Introduce el nombre de la carpeta que se va a eliminar");
		deleteDirectory.add(" => Se ha eliminado correctamente");
		deleteDirectory.add("  => No se ha podido eliminar ...");
	}
	
	/**
	 * Method that fills create directory
	 * @name fillCreateDirectory 
	 */
	public void fillCreateDirectory() {
		createDirectory.add("Introduce el nombre del carpeta a crear");
		createDirectory.add(" => Se ha creado correctamente ...");
		createDirectory.add(" => No se ha podido crear ...");
	}
	
	/**
	 * Method that fills upload file
	 * @name fillUploadFile 
	 */
	public void fillUploadFile() {
		uploadFile.add("Selecciona el fichero que se va a subir");
		uploadFile.add("Cargar");
	}
	
	/**
	 * Method that fills list
	 * @name fillList 
	 */
	public void fillList() {
		list.add("DIRECTORIO: ");
		list.add(" elementos");
		list.add("FICHERO seleccionado: ");
		list.add("DIRECTORIO ACTUAL: ");
	}
	
	/**
	 * Method that fills uploadfile method
	 * @name fillUploadFileMethod 
	 */
	public void fillUploadFileMethod() {
		uploadFileMethod.add(" => Subido correctamente... ");
		uploadFileMethod.add("Se va a actualizar el árbol de directorios...");
		uploadFileMethod.add("No se ha podido subir... ");
	}
	
	/**
	 * Method that fills downloadfile method
	 * @name fillDownloadFileMethod 
	 */
	public void fillDownloadFileMethod() {
		downloadFileMethod.add("Selecciona el Directorio donde descargar el fichero");
		downloadFileMethod.add("Descargar");
		downloadFileMethod.add(" => Se ha descargado correctamente ...");
		downloadFileMethod.add(" => No se ha podido descargar ...");
	}
	
	/**
	 * Method that fills deletefile method
	 * @name fillDeleteFileMethod 
	 */
	public void fillDeleteFileMethod() {
		deleteFileMethod.add("¿Desea eliminar el fichero seleccionado?");
		deleteFileMethod.add(" => Eliminado correctamente...");
		deleteFileMethod.add(" => No se ha podido eliminar");
	}
	
	/**
	 * Method that fill erros
	 * @name fillErrors 
	 */
	public void fillErrors() {
		errors.add("Error al conectarse al servidor");
		errors.add("Error al introducir o recibir los datos al conectarse");
		errors.add("Error al introducir la dirección");
		errors.add("Error al introducir o recibir los datos al subir el archivo");
		errors.add("Error al introducir o recibir los datos al descargar el archivo");
		errors.add("Error al introducir o recibir los datos al borrar el archivo");
		errors.add("Error al salir de la aplicación");
		errors.add("Error al pulsar el botón para borrar el archivo");
		errors.add("Error al pulsar el botón para crear un directorio");
		errors.add("Error al pulsar el list");
		errors.add("Error al abrir el directorio");
		errors.add("Error al iniciar el servidor FTP");
		errors.add("Error al abrir o crear el archivo");
	}
	
	/**
	 * Method getter for erros
	 * @name getErrors
	 * @return 
	 */
	public ArrayList<String> getErrors() {
		return errors;
	}
	
	/**
	 * Method setter for errors
	 * @name setErrors
	 * @param errors 
	 */
	public void setErrors(ArrayList<String> errors) {
		this.errors = errors;
	}
	
	/**
	 * Method getter for deletefilemethod
	 * @name getDeleteFileMethod
	 * @return 
	 */
	public ArrayList<String> getDeleteFileMethod() {
		return deleteFileMethod;
	}
	
	/**
	 * Method setter for detelefilemethod
	 * @name setDeleteFileMethod
	 * @param deleteFileMethod 
	 */
	public void setDeleteFileMethod(ArrayList<String> deleteFileMethod) {
		this.deleteFileMethod = deleteFileMethod;
	}
	
	/**
	 * Method getter for downloadfilemethod
	 * @name getDownloadFileMethod
	 * @return 
	 */
	public ArrayList<String> getDownloadFileMethod() {
		return downloadFileMethod;
	}
	
	/**
	 * Method setter for downloadfilemethod
	 * @name setDownloadFileMethod
	 * @param downloadFileMethod 
	 */
	public void setDownloadFileMethod(ArrayList<String> downloadFileMethod) {
		this.downloadFileMethod = downloadFileMethod;
	}
	
	/**
	 * Method getter for uploadfilemethod
	 * @name getUploadFileMethod
	 * @return 
	 */
	public ArrayList<String> getUploadFileMethod() {
		return uploadFileMethod;
	}
	
	/**
	 * Method setter for uploadfilemethod
	 * @name setUploadFileMethod
	 * @param uploadFileMethod 
	 */
	public void setUploadFileMethod(ArrayList<String> uploadFileMethod) {
		this.uploadFileMethod = uploadFileMethod;
	}
	
	/**
	 * Method getter for filllist
	 * @name getFillList
	 * @return 
	 */
	public String getFillList() {
		return fillList;
	}
	
	/**
	 * Method setter for filllist
	 * @name setFillList
	 * @param fillList 
	 */
	public void setFillList(String fillList) {
		this.fillList = fillList;
	}
	
	/**
	 * Method getter for list
	 * @name getList
	 * @return 
	 */
	public ArrayList<String> getList() {
		return list;
	}
	
	/**
	 * Method setter for list
	 * @name setList
	 * @param list 
	 */
	public void setList(ArrayList<String> list) {
		this.list = list;
	}
	
	/**
	 * Method getter for uploadfile
	 * @name getUploadFile
	 * @return 
	 */
	public ArrayList<String> getUploadFile() {
		return uploadFile;
	}
	
	/**
	 * Method setter for uploadfile
	 * @name setUploadFile
	 * @param uploadFile 
	 */
	public void setUploadFile(ArrayList<String> uploadFile) {
		this.uploadFile = uploadFile;
	}
	
	/**
	 * Method getter for createdirectory
	 * @name getCreateDirectory
	 * @return 
	 */
	public ArrayList<String> getCreateDirectory() {
		return createDirectory;
	}
	
	/**
	 * Method setter cor createdirectory
	 * @name setCreateDirectory
	 * @param createDirectory 
	 */
	public void setCreateDirectory(ArrayList<String> createDirectory) {
		this.createDirectory = createDirectory;
	}
	
	/**
	 * Method getter for deletedirectory
	 * @name getDeleteDirectory
	 * @return 
	 */
	public ArrayList<String> getDeleteDirectory() {
		return deleteDirectory;
	}
	
	/**
	 * Method setter for deletedirectory
	 * @name setDeleteDirectory
	 * @param deleteDirectory 
	 */
	public void setDeleteDirectory(ArrayList<String> deleteDirectory) {
		this.deleteDirectory = deleteDirectory;
	}
	
	/**
	 * Method getter for folder
	 * @name getFolder
	 * @return 
	 */
	public String getFolder() {
		return folder;
	}
	
	/**
	 * Method setter for folder
	 * @name setFolder
	 * @param folder 
	 */
	public void setFolder(String folder) {
		this.folder = folder;
	}
	
	/**
	 * Method getter for rootdirectory
	 * @name getRootDirectory
	 * @return 
	 */
	public String getRootDirectory() {
		return rootDirectory;
	}
	
	/**
	 * Method setter for rootdirectory
	 * @name setRootDirectory
	 * @param rootDirectory 
	 */
	public void setRootDirectory(String rootDirectory) {
		this.rootDirectory = rootDirectory;
	}
	
	/**
	 * Method getter for buttonsnames
	 * @name getButtonsNames
	 * @return 
	 */
	public ArrayList<String> getButtonsNames() {
		return buttonsNames;
	}
	
	/**
	 * Method setter for buttonsnames
	 * @name setButtonsNames
	 * @param buttonsNames 
	 */
	public void setButtonsNames(ArrayList<String> buttonsNames) {
		this.buttonsNames = buttonsNames;
	}
	
	/**
	 * Method getter for propertiesserver
	 * @name getPropertiesServer
	 * @return 
	 */
	public ArrayList<String> getPropertiesServer() {
		return propertiesServer;
	}
	
	/**
	 * Method setter for propertiesserver
	 * @name setPropertiesServer
	 * @param propertiesServer 
	 */
	public void setPropertiesServer(ArrayList<String> propertiesServer) {
		this.propertiesServer = propertiesServer;
	}
	
	/**
	 * Method getter for labelsnames
	 * @name getLabelsNames
	 * @return 
	 */
	public ArrayList<String> getLabelsNames() {
		return labelsNames;
	}
	
	/**
	 * Method setter for labelsnames
	 * @name setLabelsNames
	 * @param labelsNames 
	 */
	public void setLabelsNames(ArrayList<String> labelsNames) {
		this.labelsNames = labelsNames;
	}
}
