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


package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import model.ModelFTP;
import view.MenuView;


/**
 * Class MenuController
 */
public class MenuController implements ActionListener{
	private MenuView view;
	private ControllerPOP3 controllerPOP3;
	private ControllerSMTP controllerSMTP;
	private ModelFTP model;
	/**
	 * Class MenuController Constructor
	 */
	public MenuController() {
		view = new MenuView();
		addEvents();
		model = new ModelFTP();
	}
	
	public void addEvents() {
		view.getFtpButton().addActionListener(this);
		view.getSmtpButton().addActionListener(this);
		view.getPop3Button().addActionListener(this);
	}
	/**
	 * Method
	 * @name actionPerformed
	 * @param e 
	 * @overriden @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(view.getFtpButton())) {
			File directory = new File("C:\\CarpetaFTP"); 
			directory.mkdir();
			File directory1 = new File("C:\\CarpetaFTP\\Ejemplo1");
			directory1.mkdir();
			File directory2 = new File("C:\\CarpetaFTP\\Ejemplo1\\Ejemplo4");
			directory2.mkdir();
			File file = new File("C:\\CarpetaFTP\\ejemplo2.txt");
			
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(file));
				File file1 = new File("C:\\CarpetaFTP\\Ejemplo1\\ejemplo3.txt");
				BufferedWriter bw1 = new BufferedWriter(new FileWriter(file1));
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, model.getErrors().get(12));
				System.err.println(model.getErrors().get(12));
			}
			
			new ControllerClientFTP();
		} else if (e.getSource().equals(view.getSmtpButton())) {
			controllerSMTP = new ControllerSMTP();
		} else {
			controllerPOP3 = new ControllerPOP3();
			controllerPOP3.check();
			controllerPOP3.createInterface();
		}
	}

}
