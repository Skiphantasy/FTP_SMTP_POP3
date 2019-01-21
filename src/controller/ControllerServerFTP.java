/**
 * @author 
 * <ul>
 * 		<li>Tania López Martín</li>
 * 		<li>Mario Olivera Castañeda</li>
 * 		<li>Ezequiel Villalobos Reyes</li>
 * </ul>
 * @date 29 dic. 2018
 * @version 1.0
 * @description Controller that contains methods for the server for FTP
 * 
 */

package controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.PasswordEncryptor;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;
import org.apache.log4j.BasicConfigurator;

import model.ModelFTP;

/**
 *@ClassName Server.java
 */
public class ControllerServerFTP {
	/**
	 * @variable serverFactory
	 * @type FtpServerFactory
	 */
	private FtpServerFactory serverFactory;
	/**
	 * @variable factory
	 * @type ListenerFactory
	 */
	private ListenerFactory factory;
	/**
	 * @variable userManagerFactory
	 * @type PropertiesUserManagerFactory
	 */
	private PropertiesUserManagerFactory userManagerFactory;
	/**
	 * @variable user
	 * @type BaseUser
	 */
	private BaseUser user;
	/**
	 * @variable authorities
	 * @type List<Authority>
	 */
	private List<Authority> authorities;
	/**
	 * @variable um
	 * @type UserManager
	 */
	private UserManager um;
	/**
	 * @variable server
	 * @type FtpServer
	 */
	private static FtpServer server;
	/**
	 * @variable model
	 * @type ModelFTP
	 */
	private ModelFTP model;

	/**
	 * Class ControllerServerFTP Constructor
	 */
	public ControllerServerFTP() {
        try
        {
        	model = new ModelFTP();
        	serverFactory = new FtpServerFactory();
		    factory = new ListenerFactory();
		    factory.setPort(21);
		    serverFactory.addListener("default", factory.createListener());
		    //BasicConfigurator.configure();
		    userManagerFactory = new PropertiesUserManagerFactory();
		    
		    userManagerFactory.setPasswordEncryptor(new PasswordEncryptor()
		    {
	            public String encrypt(String password) {
	                return password;
	            }

	            public boolean matches(String passwordToCheck, String storedPassword) {
	                return passwordToCheck.equals(storedPassword);
	            }
	        });
		    
	        user = new BaseUser();
	        user.setHomeDirectory(model.getPropertiesServer().get(0));
	        user.setName(model.getPropertiesServer().get(1));
	        user.setPassword(model.getPropertiesServer().get(2));
	        authorities = new ArrayList<Authority>();
	        authorities.add(new WritePermission());
	        user.setAuthorities(authorities);
	        um = userManagerFactory.createUserManager();
            um.save(user);
	        serverFactory.setUserManager(um);
	        server = serverFactory.createServer();
            server.start();  
        }
        catch (FtpException ex)
        {
           System.out.println(model.getErrors().get(11)); 
        }
	}

	/**
	 * Method getter for server
	 * @name getServer
	 * @return 
	 */
	public static FtpServer getServer() {
		return server;
	}

	/**
	 * Method setter for server
	 * @name setServer
	 * @param server 
	 */
	public void setServer(FtpServer server) {
		this.server = server;
	}
	
}
