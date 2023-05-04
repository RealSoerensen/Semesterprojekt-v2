package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTabbedPane;

public class AccountManagerMenu extends JPanel {

	/**
	 * Create the panel.
	 */
	public AccountManagerMenu() {
		setLayout(null);
		setName("AccountManagerMenu");
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 599, 492);
		add(tabbedPane);
		
		JPanel panelMembers = new JPanel();
		tabbedPane.addTab("Kursister", null, panelMembers, "Skift til Kursister");
		panelMembers.setLayout(null);
		
		JScrollPane scrollPaneMembers = new JScrollPane();
		scrollPaneMembers.setBounds(10, 11, 410, 442);
		panelMembers.add(scrollPaneMembers);
		
		JButton btnMembersInfo = new JButton("Se flere oplysninger");
		btnMembersInfo.setBounds(430, 11, 154, 34);
		panelMembers.add(btnMembersInfo);
		
		JButton btnCreateMember = new JButton("Opret Kursist");
		btnCreateMember.setBounds(430, 56, 154, 34);
		panelMembers.add(btnCreateMember);
		
		JButton btnEditMember = new JButton("Rediger Kursist");
		btnEditMember.setBounds(430, 101, 154, 34);
		panelMembers.add(btnEditMember);
		
		JButton btnDeleteMembers = new JButton("Slet Kursist");
		btnDeleteMembers.setBounds(430, 146, 154, 34);
		panelMembers.add(btnDeleteMembers);
		
		JPanel panelInstructors = new JPanel();
		tabbedPane.addTab("Instruktører", null, panelInstructors, "Skift til Instruktører");
		panelInstructors.setLayout(null);
		
		JScrollPane scrollPaneInstructors = new JScrollPane();
		scrollPaneInstructors.setBounds(10, 11, 410, 442);
		panelInstructors.add(scrollPaneInstructors);
		
		JButton btnInstructorsInfo = new JButton("Se flere oplysninger");
		btnInstructorsInfo.setBounds(430, 11, 154, 34);
		panelInstructors.add(btnInstructorsInfo);
		
		JButton btnCreateInstructor = new JButton("Opret Instruktør");
		btnCreateInstructor.setBounds(430, 56, 154, 34);
		panelInstructors.add(btnCreateInstructor);
		
		JButton btnEditInstructor = new JButton("Rediger Instruktør");
		btnEditInstructor.setBounds(430, 101, 154, 34);
		panelInstructors.add(btnEditInstructor);
		
		JButton btnDeleteInstructor = new JButton("Slet Instruktør");
		btnDeleteInstructor.setBounds(430, 146, 154, 34);
		panelInstructors.add(btnDeleteInstructor);
		
		JPanel panelAdminstrator = new JPanel();
		tabbedPane.addTab("Adminstratorer", null, panelAdminstrator, "Skift til Adminstratorer");
		panelAdminstrator.setLayout(null);
		
		JScrollPane scrollPaneAdmin = new JScrollPane();
		scrollPaneAdmin.setBounds(10, 11, 410, 442);
		panelAdminstrator.add(scrollPaneAdmin);
		
		JButton btnAdminInfo = new JButton("Se flere oplysninger");
		btnAdminInfo.setBounds(430, 11, 154, 34);
		panelAdminstrator.add(btnAdminInfo);
		
		JButton btnCreateAdmin = new JButton("Opret Adminstrator");
		btnCreateAdmin.setBounds(430, 56, 154, 34);
		panelAdminstrator.add(btnCreateAdmin);
		
		JButton btnEditAdmin = new JButton("Rediger Adminstrator");
		btnEditAdmin.setBounds(430, 101, 154, 34);
		panelAdminstrator.add(btnEditAdmin);
		
		JButton btnDeleteAdmin = new JButton("Slet Adminstrator");
		btnDeleteAdmin.setBounds(430, 146, 154, 34);
		panelAdminstrator.add(btnDeleteAdmin);

	}
}
