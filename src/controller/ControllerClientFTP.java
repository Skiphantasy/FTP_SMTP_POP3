/**
 * @author 
 * <ul>
 * 		<li>Tania López Martín</li>
 * 		<li>Mario Olivera Castañeda</li>
 * 		<li>Ezequiel Villalobos Reyes</li>
 * </ul>
 * @date 29 dic. 2018
 * @version 1.0
 * @description Controller that contains methods for the client for FTP
 * 
 */

package controller;

import javax.swing.*;
import javax.swing.event.*;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.*;
import org.apache.log4j.BasicConfigurator;

import model.ModelFTP;
import view.ViewFTP;

import java.io.*;
import java.net.SocketException;
import java.awt.event.*;


/**
 * Class ControllerClientFTP
 */
public class ControllerClientFTP {

	/**
	 * @variable model
	 * @type ModelFTP
	 */
	private static ModelFTP model;
	/**
	 * @variable view
	 * @type ViewFTP
	 */
	private static ViewFTP view;
	/**
	 * @variable client
	 * @type FTPClient
	 */
	private static FTPClient client;
	/**
	 * @variable address
	 * @type String
	 */
	private String address;
	/**
	 * @variable user
	 * @type String
	 */
	private String user;
	/**
	 * @variable pasw
	 * @type String
	 */
	private String pasw;
	/**
	 * @variable login
	 * @type boolean
	 */
	private boolean login;
	/**
	 * @variable directorySelected
	 * @type String
	 */
	private static String directorySelected;
	/**
	 * @variable fileSelected
	 * @type String
	 */
	private String fileSelected = "";
	/**
	 * @variable server
	 * @type ControllerServerFTP
	 */
	private ControllerServerFTP server;

	/**
	 * @name ControllerClientFTP
	 */

	public ControllerClientFTP() {
		server = new ControllerServerFTP();
		view = new ViewFTP();
		model = new ModelFTP();
		client = new FTPClient();
		address = model.getPropertiesServer().get(3);
		user = model.getPropertiesServer().get(1);
		pasw = model.getPropertiesServer().get(2);
		directorySelected = model.getRootDirectory();
		// BasicConfigurator.configure();
		client.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));

		try {
			client.connect(address);
			login = client.login(user, pasw);
			client.changeWorkingDirectory(model.getRootDirectory());
			FTPFile[] files = client.listFiles();
			fillList(files, model.getRootDirectory());
		} catch (SocketException e) {
			System.err.println(model.getErrors().get(0));
		} catch (IOException e) {
			System.err.println(model.getErrors().get(1));
		}

		exitListener();
		deleteDirectoryListener();
		createDirectoryListener();
		deleteFileListener();
		downloadFileListener();
		uploadFileListener();
		listListener();
	}

	/**
	 * Method that fill the List
	 * 
	 * @name fillList
	 * @param files
	 * @param direc2
	 */
	private static void fillList(FTPFile[] files, String direc2) {
		if (files == null)
			return;
		DefaultListModel listModel = new DefaultListModel();
		view.getListDirectory().removeAll();

		try {
			client.changeWorkingDirectory(direc2);
		} catch (IOException e) {
			System.err.println(model.getErrors().get(2));
		}

		directorySelected = direc2;
		listModel.addElement(direc2);

		for (int i = 0; i < files.length; i++) {
			if (!(files[i].getName()).equals(".") && !(files[i].getName()).equals("..")) {
				String f = files[i].getName();
				if (files[i].isDirectory())
					f = model.getFillList() + f;
				listModel.addElement(f);
			}
		}

		try {
			view.getListDirectory().setModel(listModel);
		} catch (NullPointerException n) {
			;
		}
	}

	/**
	 * Method that uploads file
	 * 
	 * @name uploadFile
	 * @param file
	 * @param name
	 * @return
	 */
	private boolean uploadFile(String file, String name) {
		boolean ok = false;

		try {
			client.setFileType(FTP.BINARY_FILE_TYPE);
			BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(file));
			client.changeWorkingDirectory(directorySelected);

			if (client.storeFile(name, inStream)) {
				String text = " " + name + model.getUploadFileMethod().get(0);
				view.getLabels().get(3).setText(text);
				view.getLabels().get(4).setText(model.getUploadFileMethod().get(1));
				JOptionPane.showMessageDialog(null, text);
				FTPFile[] ff2 = null;
				ff2 = client.listFiles();
				fillList(ff2, directorySelected);
				ok = true;
			} else
				view.getLabels().get(3).setText(model.getUploadFileMethod().get(2) + name);
		} catch (IOException e) {
			System.err.println(model.getErrors().get(3));
		}

		return ok;
	}

	/**
	 * Method that downloads file
	 * 
	 * @name downloadFile
	 * @param fullName
	 * @param fileName
	 */
	private void downloadFile(String fullName, String fileName) {
		File file;
		String finalDirectoryAndFile = model.getLabelsNames().get(4);
		String finalDirectory = model.getLabelsNames().get(4);
		JFileChooser fileChoosed = new JFileChooser();
		fileChoosed.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChoosed.setDialogTitle(model.getDownloadFileMethod().get(0));
		int returnVal = fileChoosed.showDialog(null, model.getDownloadFileMethod().get(1));

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = fileChoosed.getSelectedFile();
			finalDirectory = (file.getAbsolutePath()).toString();
			finalDirectoryAndFile = finalDirectory + File.separator + fileName;

			try {
				client.setFileType(FTP.BINARY_FILE_TYPE);
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(finalDirectoryAndFile));

				if (client.retrieveFile(fullName, out))
					JOptionPane.showMessageDialog(null, fileName + model.getDownloadFileMethod().get(2));
				else
					JOptionPane.showMessageDialog(null, fileName + model.getDownloadFileMethod().get(3));
				out.close();
			} catch (IOException el) {
				System.err.println(model.getErrors().get(4));
			}
		}
	}

	/**
	 * Method that deletes file
	 * 
	 * @name deleteFile
	 * @param fullName
	 * @param fileName
	 */
	private void deleteFile(String fullName, String fileName) {
		int selection = JOptionPane.showConfirmDialog(null, model.getDeleteFileMethod().get(0));

		if (selection == JOptionPane.OK_OPTION) {
			try {
				if (client.deleteFile(fullName)) {
					String text = fileName + model.getDeleteFileMethod().get(1);
					JOptionPane.showMessageDialog(null, text);
					view.getLabels().get(3).setText(text);
					client.changeWorkingDirectory(directorySelected);
					FTPFile[] ff2 = null;
					ff2 = client.listFiles();
					fillList(ff2, directorySelected);
				} else
					JOptionPane.showMessageDialog(null, fileName + model.getDeleteFileMethod().get(2));
			} catch (IOException el) {
				System.err.println(model.getErrors().get(5));
			}
		}
	}

	/**
	 * Method
	 * 
	 * @name exitListener
	 */
	private void exitListener() {
		view.getButtons().get(5).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					client.disconnect();
					server.getServer().stop();
				} catch (IOException el) {
					System.err.println(model.getErrors().get(6));
				}
				
				view.dispose();
			}
		});
	}

	/**
	 * Method
	 * 
	 * @name deleteDirectoryListener
	 */
	private void deleteDirectoryListener() {
		view.getButtons().get(4).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String folderName = JOptionPane.showInputDialog(null, model.getDeleteDirectory().get(0),
						model.getFolder());

				if (!(folderName == null)) {
					String directory = directorySelected;

					if (!directorySelected.equals(model.getRootDirectory()))
						directory = directory + model.getRootDirectory();

					directory += folderName.trim();

					try {
						if (client.removeDirectory(directory)) {
							String name = folderName.trim() + model.getDeleteDirectory().get(1);
							JOptionPane.showMessageDialog(null, name);
							view.getLabels().get(3).setText(name);
							client.changeWorkingDirectory(directorySelected);
							FTPFile[] ff2 = null;
							ff2 = client.listFiles();
							fillList(ff2, directorySelected);
						} else
							JOptionPane.showMessageDialog(null, folderName.trim() + model.getDeleteDirectory().get(2));
					} catch (IOException el) {
						System.err.println(model.getErrors().get(7));
					}
				}
			}
		});
	}

	/**
	 * Method
	 * 
	 * @name createDirectoryListener
	 */
	private void createDirectoryListener() {
		view.getButtons().get(3).addActionListener(new ActionListener() {
			/**
			 * Method
			 * 
			 * @name actionPerformed
			 * @param e
			 * @overriden @see
			 *            java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				String folderName = JOptionPane.showInputDialog(null, model.getCreateDirectory().get(0),
						model.getFolder());

				if (!(folderName == null)) {
					String directory = directorySelected;
					
					if (!directorySelected.equals(model.getRootDirectory()))
						directory = directory + model.getRootDirectory();
					
					directory += folderName.trim();
					
					try {
						if (client.makeDirectory(directory)) {
							String m = folderName.trim() + model.getCreateDirectory().get(1);
							JOptionPane.showMessageDialog(null, m);
							view.getLabels().get(3).setText(m);
							client.changeWorkingDirectory(directorySelected);
							FTPFile[] ff2 = null;
							ff2 = client.listFiles();
							fillList(ff2, directorySelected);
						} else
							JOptionPane.showMessageDialog(null, folderName.trim() + model.getCreateDirectory().get(2));
					} catch (IOException el) {
						System.err.println(model.getErrors().get(8));
					}
				}
			}
		});
	}

	/**
	 * Method
	 * 
	 * @name deleteFileListener
	 */
	private void deleteFileListener() {
		view.getButtons().get(2).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String directory = directorySelected;
				
				if (!directorySelected.equals(model.getRootDirectory()))
					directory = directory + model.getRootDirectory();
				
				if (!fileSelected.equals(model.getLabelsNames().get(4)))
					deleteFile(directory + fileSelected, fileSelected);
			}
		});
	}

	/**
	 * Method
	 * 
	 * @name downloadFileListener
	 */
	private void downloadFileListener() {
		view.getButtons().get(1).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String directory = directorySelected;
				
				if (!directorySelected.equals(model.getRootDirectory()))
					directory = directory + model.getRootDirectory();
				
				if (!fileSelected.equals(model.getLabelsNames().get(4))) {
					downloadFile(directory + fileSelected, fileSelected);
				}
			}
		});
	}

	/**
	 * Method
	 * 
	 * @name uploadFileListener
	 */
	private void uploadFileListener() {
		view.getButtons().get(0).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChoose;
				File file;
				fileChoose = new JFileChooser();
				fileChoose.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fileChoose.setDialogTitle(model.getUploadFile().get(0));
				int returnVal = fileChoose.showDialog(fileChoose, model.getUploadFile().get(1));
				
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					file = fileChoose.getSelectedFile();
					String file2 = file.getAbsolutePath();
					String fileName = file.getName();
					uploadFile(file2, fileName);
				}
			}
		});
	}

	/**
	 * Method
	 * 
	 * @name listListener
	 */
	private void listListener() {
		view.getListDirectory().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse) {
				if (lse.getValueIsAdjusting()) {
					fileSelected = model.getLabelsNames().get(4);
					String fic = view.getListDirectory().getSelectedValue().toString();
					
					if (view.getListDirectory().getSelectedIndex() == 0) {
						if (!fic.equals(model.getRootDirectory())) {
							try {
								client.changeToParentDirectory();
								directorySelected = client.printWorkingDirectory();
								FTPFile[] ff2 = null;
								client.changeWorkingDirectory(directorySelected);
								ff2 = client.listFiles();
								view.getLabels().get(3).setText(model.getLabelsNames().get(4));
								fillList(ff2, directorySelected);
							} catch (IOException e) {
								System.err.println(model.getErrors().get(9));
							}
						}
					} else {
						if (fic.substring(0, 6).equals(model.getFillList())) {
							try {
								fic = fic.substring(6);
								String direcSelec2 = model.getLabelsNames().get(4);
								
								if (directorySelected.equals(model.getRootDirectory()))
									direcSelec2 = directorySelected + fic;
								else
									direcSelec2 = directorySelected + model.getRootDirectory() + fic;
								
								FTPFile[] ff2 = null;
								client.changeWorkingDirectory(direcSelec2);
								ff2 = client.listFiles();
								view.getLabels().get(3).setText(
										model.getList().get(0) + fic + ", " + ff2.length + model.getList().get(1));
								directorySelected = direcSelec2;
								fillList(ff2, directorySelected);
							} catch (IOException e2) {
								System.err.println(model.getErrors().get(10));
							}
						} else {
							fileSelected = directorySelected;
							
							if (directorySelected.equals(model.getRootDirectory()))
								fileSelected += fic;
							else
								fileSelected += model.getRootDirectory() + fic;
							
							view.getLabels().get(3).setText(model.getList().get(2) + fileSelected);
							fileSelected = fic;
						}
					}
					
					view.getLabels().get(4).setText(model.getList().get(3) + directorySelected);
				}
			}
		});
	}

	/**
	 * Method getter for client
	 * @name getClient
	 * @return 
	 */
	public static FTPClient getClient() {
		return client;
	}	
}