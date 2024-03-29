package gui;

import javax.swing.*;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import controller.PersonController;
import model.Address;
import model.Person;

public class AccountManagerMenu extends JPanel {

	final JTable memberTable;
	final JTable instructorTable;
	final JTable adminTable;
	Object[][] memberData;
	Object[][] instructorData;
	Object[][] adminData;
	final PersonController personController = new PersonController();
	private final JButton btnDeleteMembers;
	private final JButton btnEditMember;
	private final JButton btnChangeRole;
	private final JButton btnSeeInfo;
	/**
	 * Create the panel.
	 * Tab 0 = Member, 1 = Instructor, 2 = Administrator.
	 */
	public AccountManagerMenu(MainMenu mainMenu) throws SQLException {
		setSize(626, 515);
		setName("AccountManagerMenu");
		setLayout(null);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 466, 493);
		add(tabbedPane);

		JPanel panelMembers = new JPanel();
		tabbedPane.addTab("Kursister", null, panelMembers, "Skift til Kursister");
		panelMembers.setLayout(null);

		JScrollPane scrollPaneMembers = new JScrollPane();
		scrollPaneMembers.setBounds(10, 11, 441, 442);
		panelMembers.add(scrollPaneMembers);

		List<Person> members = personController.getAllMembers();
		memberTable = new JTable();
		memberData = refreshTable(memberTable, members);
		scrollPaneMembers.setViewportView(memberTable);
		memberTable.getSelectionModel().addListSelectionListener(event -> setButtonsEnabled(true));

		JPanel panelInstructors = new JPanel();
		tabbedPane.addTab("Instruktører", null, panelInstructors, "Skift til Instruktører");
		panelInstructors.setLayout(null);

		JScrollPane scrollPaneInstructors = new JScrollPane();
		scrollPaneInstructors.setBounds(10, 11, 441, 442);
		panelInstructors.add(scrollPaneInstructors);

		List<Person> instructors = personController.getAllInstructors();
		instructorTable = new JTable();
		instructorData = refreshTable(instructorTable, instructors);
		scrollPaneInstructors.setViewportView(instructorTable);
		instructorTable.getSelectionModel().addListSelectionListener(event -> setButtonsEnabled(true));

		JPanel panelAdminstrator = new JPanel();
		tabbedPane.addTab("Adminstratorer", null, panelAdminstrator, "Skift til Adminstratorer");
		panelAdminstrator.setLayout(null);

		JScrollPane scrollPaneAdmin = new JScrollPane();
		scrollPaneAdmin.setBounds(10, 11, 441, 442);
		panelAdminstrator.add(scrollPaneAdmin);

		List<Person> admins = personController.getAllAdmins();
		adminTable = new JTable();
		adminData = refreshTable(adminTable, admins);
		scrollPaneAdmin.setViewportView(adminTable);
		adminTable.getSelectionModel().addListSelectionListener(event -> setButtonsEnabled(true));

		btnDeleteMembers = new JButton("Slet");
		btnDeleteMembers.setBounds(486, 170, 118, 34);
		add(btnDeleteMembers);

		btnEditMember = new JButton("Rediger");
		btnEditMember.setBounds(486, 125, 118, 34);
		add(btnEditMember);

		btnChangeRole = new JButton("Ændre rolle");
		btnChangeRole.addActionListener(e -> {
			Person person = getSelectedPerson(tabbedPane);

			if (person == null) {
				JOptionPane.showMessageDialog(null, "Vælg en kursist fra tabellen");
				return;
			}

			String[] options = { "Medlem", "Instruktør", "Administrator" };
			int choice = JOptionPane.showOptionDialog(null, "Vælg en rolle", "Rolle", JOptionPane.DEFAULT_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (choice == 0) {
				person.setRole(1);
			} 
			else if (choice == 1) {
				person.setRole(2);
			} 
			else if (choice == 2) {
				person.setRole(3);
			}

			try {
				if(personController.updatePerson(person)) {
					JOptionPane.showMessageDialog(null, "Personen blev opdateret");
				} 
				else {
					JOptionPane.showMessageDialog(null, "Der skete en fejl ved ændring af rolle");
					return;
				}
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, "Der skete en fejl ved opdatering af personen");
				System.out.println(e1.getMessage());
				return;
			}

			memberData = refreshTable(memberTable, personController.getAllMembers());
			instructorData = refreshTable(instructorTable, personController.getAllInstructors());
			adminData = refreshTable(adminTable, personController.getAllAdmins());
		});
		btnChangeRole.setBounds(486, 80, 118, 34);
		add(btnChangeRole);

		btnSeeInfo = new JButton("Se oplysninger");
		btnSeeInfo.setBounds(486, 35, 118, 34);
		add(btnSeeInfo);

		JButton btnCreatePerson = new JButton("Opret");
		btnCreatePerson.addActionListener(e -> {
			CreateAccountMenu createUserPanel = new CreateAccountMenu();
			createUserPanel.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					memberData = refreshTable(memberTable, personController.getAllMembers());
					instructorData = refreshTable(instructorTable, personController.getAllInstructors());
					adminData = refreshTable(adminTable, personController.getAllAdmins());
				}
			});
			createUserPanel.run(false);
		});

		btnCreatePerson.setBounds(486, 215, 118, 34);
		add(btnCreatePerson);

		btnSeeInfo.addActionListener(e -> {
			Person person = getSelectedPerson(tabbedPane);

			if (person == null) {
				JOptionPane.showMessageDialog(null, "Vælg en kursist fra tabellen");
				return;
			}

			displayPersonInfo(person);

		});

		btnEditMember.addActionListener(e -> {
			Person person = getSelectedPerson(tabbedPane);
			if (person == null) {
				JOptionPane.showMessageDialog(null, "Vælg en bruger fra tabellen");
				return;
			}
			EditAccountMenu editMember;
			editMember = new EditAccountMenu(mainMenu, person, false);
			mainMenu.mainPanel.add(editMember, "EditMember");
			mainMenu.cardLayout.show(mainMenu.mainPanel, "EditMember");
		});

		btnDeleteMembers.addActionListener(e -> {
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			Person person = getSelectedPerson(tabbedPane);
			if (person == null) {
				JOptionPane.showMessageDialog(null, "Vælg en bruger fra tabellen");
				return;
			}
			int result = JOptionPane.showConfirmDialog(null,
					"Er du sikker på at du vil slette " + person.getFirstName() + " ?", "Slet Kursus",
					JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				try {
					if (!personController.deletePerson(person)) {
						JOptionPane.showMessageDialog(null, "Bruger kunne ikke slettes");
					}
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Fejl: Der skete en fejl under sletning af bruger");
				}
				memberData = refreshTable(memberTable, personController.getAllMembers());
				instructorData = refreshTable(instructorTable, personController.getAllInstructors());
				adminData = refreshTable(adminTable, personController.getAllAdmins());
			}
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		});
		setButtonsEnabled(false);
		tabbedPane.addChangeListener(e -> {
			memberTable.changeSelection(memberTable.getSelectedRow(), memberTable.getSelectedColumn(), true, false);
			instructorTable.changeSelection(instructorTable.getSelectedRow(), instructorTable.getSelectedColumn(), true, false);
			adminTable.changeSelection(adminTable.getSelectedRow(), adminTable.getSelectedColumn(), true, false);
			setButtonsEnabled(false);
		});
	
		adminTable.addMouseListener(new MouseAdapter() {
	         public void mouseClicked(MouseEvent me) {
	        	 if (me.getClickCount() == 2) {
	               	 Person person = null;
	               	 try {
	               		 person = (Person) adminData[adminTable.getSelectedRow()][0];
	               	 } catch(Exception e) {
	               		 e.printStackTrace();
	               	 }
	               	 if(person != null) {
	               		 displayPersonInfo(person);
	               	 }
	             }
	         }
	    });
		instructorTable.addMouseListener(new MouseAdapter() {
	         public void mouseClicked(MouseEvent me) {
	        	 if (me.getClickCount() == 2) {
	               	 Person person = null;
	               	 try {
	               		 person = (Person) instructorData[instructorTable.getSelectedRow()][0];
	               	 } catch(Exception e) {
	               		 e.printStackTrace();
	               	 }
	               	 if(person != null) {
	               		 displayPersonInfo(person);
	               	 }
	             }
	         }
	    });
		memberTable.addMouseListener(new MouseAdapter() {
	         public void mouseClicked(MouseEvent me) {
	        	 if (me.getClickCount() == 2) {
	               	 Person person = null;
	               	 try {
	               		 person = (Person) memberData[memberTable.getSelectedRow()][0];
	               	 } catch(Exception e) {
	               		 e.printStackTrace();
	               	 }
	               	 if(person != null) {
	               		 displayPersonInfo(person);
	               	 }
	             }
	         }
	    });
	}
	
	private void displayPersonInfo(Person person) {
		Address address = person.getAddress();
		JOptionPane optionPane = new JOptionPane();
		optionPane.setMessage("Navn: " + person.getFirstName() + "\n" +
				"Efternavn: " + person.getLastName() + "\n" +
				"Email: " + person.getEmail() + "\n" +
				"Telefon: " + person.getPhoneNumber() + "\n" +
				"CPR: " + person.getSsn() + "\n" +
				"Rolle: " + getRole(person.getRole()) + "\n" +
				"Adresse: " + address.getStreet() + " " + address.getHouseNumber() + "\n" +
				"Postnummer: " + address.getZipCode() + "\n" +
				"By: " + address.getCity());
		optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);

		JDialog dialog = optionPane.createDialog(null, "Kursist oplysninger");
		dialog.setVisible(true);
	}

	private Person getSelectedPerson(JTabbedPane tabbedPane) {
		Person person = null;
		int activeTabIndex = tabbedPane.getSelectedIndex();
		JPanel panel = (JPanel) tabbedPane.getComponentAt(activeTabIndex);
		JScrollPane scrollPane = (JScrollPane) panel.getComponent(0);
		JTable table = (JTable) scrollPane.getViewport().getView();
		int selectedRow = table.getSelectedRow();
		if (selectedRow != -1) {
			if (activeTabIndex == 0) {
				person = (Person) memberData[selectedRow][0];
			} 
			else if (activeTabIndex == 1) {
				person = (Person) instructorData[selectedRow][0];
			} 
			else if (activeTabIndex == 2) {
				person = (Person) adminData[selectedRow][0];
			}
		}

		return person;
	}

	private Object[][] refreshTable(JTable table, List<Person> persons) {
		Object[][] data = new Object[persons.size()][7];
		for (int i = 0; i < persons.size(); i++) {
			Person person = persons.get(i);
			data[i][0] = person;
			data[i][1] = person.getFirstName();
			data[i][2] = person.getLastName();
			data[i][3] = person.getPhoneNumber();
			data[i][4] = person.getEmail();
			data[i][5] = person.getAddress().getStreet() + " " + person.getAddress().getHouseNumber() + ", "
					+ person.getAddress().getZipCode() + " " + person.getAddress().getCity();
			data[i][6] = person.getSsn();
		}

		String[] columnNames = {
				"PersonObject", "Fornavn", "Efternavn", "Tlf. Nr.", "Email", "Adresse", "CPR"
		};

		DefaultTableModel model = new DefaultTableModel(data, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table.setModel(model);

		// Get the column model
		TableColumnModel columnModel = table.getColumnModel();
		// Hide the first column
		TableColumn column = columnModel.getColumn(0);
		columnModel.removeColumn(column);
		return data;
	}
	
	private void setButtonsEnabled(boolean enabled) {
		btnDeleteMembers.setEnabled(enabled);
		btnChangeRole.setEnabled(enabled);
		btnEditMember.setEnabled(enabled);
		btnSeeInfo.setEnabled(enabled);
	}
	
	private String getRole(int role) {
		String stringRole = null;
		if(role == 1) {
			stringRole = "Kursist";
		}
		if(role == 2) {
			stringRole = "Instruktør";
		}
		if(role == 3) {
			stringRole = "Administrator";
		}
		return stringRole;
	}
}
