package presentationlayer;


import javax.swing.JFrame;
import javax.swing.JScrollPane;

import domainmodel.*;
import bussineslayer.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JComboBox;

public class AdminPannel {

	private JFrame frame;
	private	JScrollPane scrollPane;
	private JPanel pNewEvent;
	private JTextField tfneTitle;
	private JTextField tfneDirectedBy;
	private JTextField tfneCast;
	private JLabel lblneTitle;
	private JLabel lblneDirectedBy;
	private JLabel lblneCast;
	private JTextField tfnePremiereDate;
	private JLabel lblnePremiereDate;
	private JLabel lblneTicketsNr;
	private JTextField tfneTicketsNr;
	private JButton btnAddEvent;
	
	
	
	private EventsProcessing ep = new EventsProcessing();
	private UserProcessing up = new UserProcessing();
	
	private Object[][] data;
    private String columnNames[] = { "Name", "Directed By", "Cast","Premiere Date", "Tickets Nr" };
	
	private DefaultTableModel model;
	
	private JTable table;
	
	private JLabel lblUpdateAnEvent;
	@SuppressWarnings("rawtypes")
	private JComboBox cbTitle;
	private JLabel lblueDirectedBy;
	private JTextField tfueDirBy;
	private JLabel lblueCast;
	private JTextField tfueCast;
	private JLabel lbluePremiereDate;
	private JTextField tfuepDate;
	private JLabel lblueTickets;
	private JTextField tfueTnr;
	private JLabel lblueTitle;
	private JButton btnUpdateEvent;
	private JPanel pDeleteEvent;
	private JLabel lblDeleteAnEvent;
	@SuppressWarnings("rawtypes")
	private JComboBox cbdelTitle;
	private JButton btnDel;
	private JTextField tfnempName;
	private JTextField tfnempUser;
	private JTextField tfnempPass;

	/**
	 * Create the application.
	 */
	public AdminPannel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 970, 504);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Admin pannel");
		
		data = ep.readEvents();
		model = new DefaultTableModel(data,columnNames);
		table = new JTable(model);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 945, 231);
		frame.getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);
		
		pNewEvent = new JPanel();
		pNewEvent.setBorder(new LineBorder(Color.GRAY));
		pNewEvent.setBounds(10, 255, 238, 210);
		frame.getContentPane().add(pNewEvent);
		pNewEvent.setLayout(null);
		
		lblneTitle = new JLabel("Title:");
		lblneTitle.setBounds(10, 41, 102, 14);
		pNewEvent.add(lblneTitle);
		
		tfneTitle = new JTextField();
		tfneTitle.setBounds(122, 41, 107, 20);
		pNewEvent.add(tfneTitle);
		tfneTitle.setColumns(15);
		
		lblneDirectedBy = new JLabel("DirectedBy:");
		lblneDirectedBy.setBounds(10, 66, 102, 14);
		pNewEvent.add(lblneDirectedBy);
		
		tfneDirectedBy = new JTextField();
		tfneDirectedBy.setBounds(122, 66, 107, 20);
		pNewEvent.add(tfneDirectedBy);
		tfneDirectedBy.setColumns(15);
		
		lblneCast = new JLabel("Cast:");
		lblneCast.setBounds(10, 91, 102, 14);
		pNewEvent.add(lblneCast);
		
		tfneCast = new JTextField();
		tfneCast.setBounds(122, 91, 107, 20);
		pNewEvent.add(tfneCast);
		tfneCast.setColumns(15);
		
		lblnePremiereDate = new JLabel("Premiere Date: ");
		lblnePremiereDate.setBounds(10, 116, 102, 14);
		pNewEvent.add(lblnePremiereDate);
		
		tfnePremiereDate = new JTextField();
		tfnePremiereDate.setBounds(122, 116, 107, 20);
		pNewEvent.add(tfnePremiereDate);
		tfnePremiereDate.setColumns(13);
		
		lblneTicketsNr = new JLabel("Tickets Nr. :");
		lblneTicketsNr.setBounds(10, 141, 102, 14);
		pNewEvent.add(lblneTicketsNr);
		
		tfneTicketsNr = new JTextField();
		tfneTicketsNr.setBounds(122, 141, 71, 20);
		pNewEvent.add(tfneTicketsNr);
		tfneTicketsNr.setColumns(10);
		
		btnAddEvent = new JButton("Add Event");
		btnAddEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (verifyAddEventFields())
				{
					addEvent();
					refreshTable();
					ep.refreshEvents();
				} else
				{
					JOptionPane.showMessageDialog(frame,"Text fields can not be empty!","Warning",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnAddEvent.setBounds(59, 176, 110, 23);
		pNewEvent.add(btnAddEvent);
		
		JLabel lblAddANew = new JLabel("Add a new Event");
		lblAddANew.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAddANew.setBounds(59, 11, 132, 19);
		pNewEvent.add(lblAddANew);
		
		JPanel pUpdateEvent = new JPanel();
		pUpdateEvent.setBorder(new LineBorder(Color.GRAY));
		pUpdateEvent.setBounds(258, 255, 238, 210);
		frame.getContentPane().add(pUpdateEvent);
		pUpdateEvent.setLayout(null);
		
		lblUpdateAnEvent = new JLabel("Update an Event");
		lblUpdateAnEvent.setBounds(51, 11, 102, 22);
		pUpdateEvent.add(lblUpdateAnEvent);
		lblUpdateAnEvent.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		cbTitle = new JComboBox(ep.getEventsList());
		cbTitle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (cbTitle.getSelectedIndex() == 0)
				{
					tfueDirBy.setText("");
					tfueCast.setText("");
					tfuepDate.setText("");
					tfueTnr.setText("");
				} else
				{
					Event e = ep.getEvent(cbTitle.getSelectedItem().toString());
					tfueDirBy.setText(e.getDirectedBy());
					tfueCast.setText(e.getCast());
					tfuepDate.setText(e.getPremiereDate());
					tfueTnr.setText(e.getTicketsNr()+"");
				}
			}
		});
		cbTitle.setBounds(100, 44, 128, 22);
		pUpdateEvent.add(cbTitle);
		
		lblueDirectedBy = new JLabel("DirectedBy:");
		lblueDirectedBy.setBounds(9, 71, 102, 14);
		pUpdateEvent.add(lblueDirectedBy);
		
		tfueDirBy = new JTextField();
		tfueDirBy.setColumns(15);
		tfueDirBy.setBounds(99, 71, 129, 20);
		pUpdateEvent.add(tfueDirBy);
		
		lblueCast = new JLabel("Cast:");
		lblueCast.setBounds(9, 96, 102, 14);
		pUpdateEvent.add(lblueCast);
		
		tfueCast = new JTextField();
		tfueCast.setColumns(15);
		tfueCast.setBounds(99, 96, 129, 20);
		pUpdateEvent.add(tfueCast);
		
		lbluePremiereDate = new JLabel("Premiere Date: ");
		lbluePremiereDate.setBounds(9, 121, 102, 14);
		pUpdateEvent.add(lbluePremiereDate);
		
		tfuepDate = new JTextField();
		tfuepDate.setColumns(13);
		tfuepDate.setBounds(99, 121, 129, 20);
		pUpdateEvent.add(tfuepDate);
		
		lblueTickets = new JLabel("Tickets Nr. :");
		lblueTickets.setBounds(9, 146, 102, 14);
		pUpdateEvent.add(lblueTickets);
		
		tfueTnr = new JTextField();
		tfueTnr.setColumns(10);
		tfueTnr.setBounds(99, 146, 93, 20);
		pUpdateEvent.add(tfueTnr);
		
		lblueTitle = new JLabel("Title:");
		lblueTitle.setBounds(10, 48, 102, 14);
		pUpdateEvent.add(lblueTitle);
		
		btnUpdateEvent = new JButton("Update event");
		btnUpdateEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (verifyUpdateEventFields())
				{
					String title = cbTitle.getSelectedItem().toString();
					String directedBy = tfueDirBy.getText();
					String cast = tfueCast.getText();
					String pdate  = tfuepDate.getText();
					try
					{
						int ticketsNr = Integer.parseInt(tfueTnr.getText());
						Event ev = new Event(title, directedBy,cast,pdate,ticketsNr);
						ep.updateEvent(ev);
					}
					catch(Exception er)
					{
						er.printStackTrace();
					}
					refreshTable();
				} else
				{
					JOptionPane.showMessageDialog(frame,"Text fields can not be empty!","Warning",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnUpdateEvent.setBounds(61, 177, 110, 23);
		pUpdateEvent.add(btnUpdateEvent);
		
		pDeleteEvent = new JPanel();
		pDeleteEvent.setBorder(new LineBorder(Color.GRAY));
		pDeleteEvent.setBounds(506, 255, 215, 210);
		frame.getContentPane().add(pDeleteEvent);
		pDeleteEvent.setLayout(null);
		
		lblDeleteAnEvent = new JLabel("Delete an Event");
		lblDeleteAnEvent.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDeleteAnEvent.setBounds(55, 11, 107, 22);
		pDeleteEvent.add(lblDeleteAnEvent);
		
		cbdelTitle = new JComboBox(ep.getEventsList());
		cbdelTitle.setBounds(35, 56, 139, 30);
		pDeleteEvent.add(cbdelTitle);
		
		btnDel = new JButton("Delete");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbdelTitle.getSelectedIndex() > 0)
				{
					Event ev = ep.getEvent(cbdelTitle.getSelectedItem().toString());
					ep.deleteEvent(ev);
					refreshTable();
				} else
				{
					JOptionPane.showMessageDialog(frame,"Select an event!","Warning",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnDel.setBounds(55, 139, 89, 42);
		pDeleteEvent.add(btnDel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.RED));
		panel.setBounds(731, 255, 224, 210);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblAddNewEmployee = new JLabel("Add new Employee");
		lblAddNewEmployee.setForeground(Color.RED);
		lblAddNewEmployee.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAddNewEmployee.setBounds(52, 11, 138, 23);
		panel.add(lblAddNewEmployee);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setBounds(10, 48, 86, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setBounds(10, 73, 86, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setBounds(10, 98, 86, 14);
		panel.add(lblNewLabel_2);
		
		tfnempName = new JTextField();
		tfnempName.setBounds(97, 45, 117, 20);
		panel.add(tfnempName);
		tfnempName.setColumns(10);
		
		tfnempUser = new JTextField();
		tfnempUser.setBounds(97, 70, 117, 20);
		panel.add(tfnempUser);
		tfnempUser.setColumns(10);
		
		tfnempPass = new JTextField();
		tfnempPass.setBounds(97, 95, 117, 20);
		panel.add(tfnempPass);
		tfnempPass.setColumns(10);
		
		JButton btnAddEmp = new JButton("Add");
		btnAddEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (verifyAddEmployeeFields())
				{
					addEmployee();
					JOptionPane.showMessageDialog(frame, "Employee added.");
				} else
				{
					JOptionPane.showMessageDialog(frame,"Text fields can not be empty!","Warning",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnAddEmp.setBounds(66, 159, 89, 23);
		panel.add(btnAddEmp);
	}
	
	private boolean verifyAddEventFields()
	{
		boolean notEmpty = true;
		
		if ((tfneTitle.getText().equals("")) || (tfneDirectedBy.equals("")) || (tfneCast.getText().equals("")) || (tfnePremiereDate.getText().equals("")) ||
				(tfneTicketsNr.getText().equals(""))) notEmpty = false;
		return notEmpty;
	}
	
	private boolean verifyUpdateEventFields()
	{
		boolean notEmpty = true;
		
		if ((tfueDirBy.getText().equals("")) || (tfueCast.getText().equals("")) || (tfuepDate.getText().equals("")) ||
				(tfueTnr.getText().equals(""))) notEmpty = false;
		return notEmpty;
	}
	
	private boolean verifyAddEmployeeFields()
	{
		boolean notEmpty = true;
		
		if ((tfnempName.getText().equals("")) || (tfnempUser.getText().equals("")) || (tfnempPass.getText().equals(""))) notEmpty = false;
		return notEmpty;
	}
	
	private void refreshTable()
	{
		data = ep.readEvents();
		model = new DefaultTableModel(data,columnNames);
		table.setModel(model);
		table.repaint();
	}
	
	private void addEmployee()
	{
		String name = tfnempName.getText();
		String username = tfnempUser.getText();
		String password = tfnempPass.getText();
		
		up.createEmployeeAccount(name,username, password);
	}
	
	private void addEvent()
	{		
		String title = tfneTitle.getText();
		String directedBy = tfneDirectedBy.getText();
		String cast = tfneCast.getText();
		String pdate  = tfnePremiereDate.getText();
		try
		{
			int ticketsNr = Integer.parseInt(tfneTicketsNr.getText());
			Event e = new Event(title, directedBy,cast,pdate,ticketsNr);
			ep.addEvent(e);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void setFrameVisible()
	{
		frame.setVisible(true);
	}
}
