package presentationlayer;


import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import bussineslayer.*;
import domainmodel.*;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class EmployeePannel {

	
	private JFrame frame;
	private	JScrollPane scrollPane;
	private JTextField tfadRow;
	private JTextField tfadNr;
	@SuppressWarnings("rawtypes")
	private JComboBox cbShowTickets;
	@SuppressWarnings("rawtypes")
	private JComboBox cbatEvents;
	
	private Object[][] data;
    private String columnNames[] = { "Event name", "Row", "Nr" };
	
	private DefaultTableModel model;
	
	private JTable table;
	
	private EventsProcessing ep = new EventsProcessing();
	private TicketsProcessing tp = new TicketsProcessing();
	String[] events;
	private JLabel lblAddANew;
	private JLabel lblShowTicketsFor;
	

	/**
	 * Create the application.
	 */
	public EmployeePannel() {
		events = ep.getEventsList2();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 396);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Employee pannel");
		
		data = tp.getTickets(events[0]);
		model = new DefaultTableModel(data,columnNames);
		table = new JTable(model);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 424, 189);
		frame.getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);
		
		JPanel pAddTicket = new JPanel();
		pAddTicket.setBorder(new LineBorder(Color.GRAY));
		pAddTicket.setBounds(10, 211, 207, 145);
		frame.getContentPane().add(pAddTicket);
		pAddTicket.setLayout(null);
		
		cbatEvents = new JComboBox(events);
		cbatEvents.setBounds(10, 35, 187, 20);
		pAddTicket.add(cbatEvents);
		
		tfadRow = new JTextField();
		tfadRow.setBounds(84, 62, 113, 20);
		pAddTicket.add(tfadRow);
		tfadRow.setColumns(10);
		
		tfadNr = new JTextField();
		tfadNr.setBounds(84, 87, 113, 20);
		pAddTicket.add(tfadNr);
		tfadNr.setColumns(10);
		
		JButton btnAddTicket = new JButton("Add Ticket");
		btnAddTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textFieldsOk()){
				try{
					int nr = Integer.parseInt(tfadNr.getText());
					int row = Integer.parseInt(tfadRow.getText());
					String event = cbatEvents.getSelectedItem().toString();
					
					if (tp.addTicket(new Ticket(event,row,nr)) == 2)
					{
						JOptionPane.showMessageDialog(frame,"That row number is not free!","Warning",JOptionPane.WARNING_MESSAGE);
					}
					else if (tp.addTicket(new Ticket(event,row,nr)) == 1)
					{
						JOptionPane.showMessageDialog(frame,"No more tickets for this event!","Warning",JOptionPane.WARNING_MESSAGE);
					}
					else
					{
						refreshTable(cbatEvents.getSelectedIndex());
					}
				} catch(Exception e)
				{
					e.printStackTrace();
				}
				}
				else
				{
					JOptionPane.showMessageDialog(frame,"Text fields can not be empty!","Warning",JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		btnAddTicket.setBounds(46, 113, 113, 23);
		pAddTicket.add(btnAddTicket);
		
		JLabel lblNewLabel = new JLabel("Row:");
		lblNewLabel.setBounds(10, 65, 73, 14);
		pAddTicket.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Number:");
		lblNewLabel_1.setBounds(10, 90, 73, 14);
		pAddTicket.add(lblNewLabel_1);
		
		lblAddANew = new JLabel("Add a new Ticket");
		lblAddANew.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAddANew.setBounds(10, 11, 187, 14);
		pAddTicket.add(lblAddANew);
		
		JPanel pShowTickets = new JPanel();
		pShowTickets.setBorder(new LineBorder(Color.GRAY));
		pShowTickets.setBounds(227, 211, 207, 145);
		frame.getContentPane().add(pShowTickets);
		pShowTickets.setLayout(null);
		
		cbShowTickets = new JComboBox(events);
		cbShowTickets.setSelectedIndex(0);
		cbShowTickets.setBounds(10, 35, 187, 20);
		pShowTickets.add(cbShowTickets);
		
		JButton btnShowTickets = new JButton("Show Tickets");
		btnShowTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshTable(cbShowTickets.getSelectedIndex());
			}
		});
		btnShowTickets.setBounds(47, 77, 114, 42);
		pShowTickets.add(btnShowTickets);
		
		lblShowTicketsFor = new JLabel("Show tickets for an event");
		lblShowTicketsFor.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblShowTicketsFor.setBounds(10, 11, 187, 14);
		pShowTickets.add(lblShowTicketsFor);
	}
	
	private void refreshTable(int i)
	{
		data = tp.getTickets(events[i]);
		model = new DefaultTableModel(data,columnNames);
		table.setModel(model);
		table.repaint();
	}
	
	private boolean textFieldsOk()
	{
		boolean ok = true;
		if (tfadRow.getText().equals("") || tfadNr.getText().equals(""))
			ok = false;
		return ok;
	}
	
	public void setFrameVisible()
	{
		frame.setVisible(true);
	}
}
