package presentationlayer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import bussineslayer.*;
import domainmodel.*;
import javax.swing.JPasswordField;

public class LoginPannel {

	private JFrame frame;
	private JTextField tfUsername;
	private JPasswordField tfPassword;
	private JButton btnPassRecover;
	private UserProcessing up = new UserProcessing();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPannel window = new LoginPannel();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginPannel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 325, 198);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Login pannel");
		
		tfUsername = new JTextField();
		tfUsername.setBounds(96, 35, 171, 20);
		frame.getContentPane().add(tfUsername);
		tfUsername.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(26, 38, 69, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(26, 69, 83, 14);
		frame.getContentPane().add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (notEmptyFiles())
				{
					UserProcessing up = new UserProcessing();
					@SuppressWarnings("deprecation")
					User u = up.login(tfUsername.getText(),tfPassword.getText());
					if (u instanceof Administrator)
					{
						AdminPannel window = new AdminPannel();
						window.setFrameVisible();
						frame.setVisible(false); //you can't see me!
						frame.dispose(); 
					} else if (u instanceof Employee)
					{
						EmployeePannel window = new EmployeePannel();
						window.setFrameVisible();
						frame.setVisible(false); //you can't see me!
						frame.dispose(); 
					} else
					{
						JOptionPane.showMessageDialog(frame,"Not a user!","Warning",JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		btnLogin.setBounds(26, 125, 102, 23);
		frame.getContentPane().add(btnLogin);
		
		tfPassword = new JPasswordField();
		tfPassword.setBounds(96, 66, 171, 20);
		frame.getContentPane().add(tfPassword);
		
		btnPassRecover = new JButton("Forgot password");
		btnPassRecover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = JOptionPane.showInputDialog("Username: ");
				if (!username.equals(""))
				{
					String newPass = up.generatePassword(username);
					if (!newPass.equals(""))
						JOptionPane.showMessageDialog(null, newPass, "New password", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(frame,"Not a user!","Warning",JOptionPane.WARNING_MESSAGE);
				}
					
			}
		});
		btnPassRecover.setBounds(152, 125, 132, 23);
		frame.getContentPane().add(btnPassRecover);
	}
	
    @SuppressWarnings("deprecation")
	private boolean notEmptyFiles()
	{
		boolean notEmpty = true;
		
		if (tfUsername.getText() == "") notEmpty = false;
			else if (tfPassword.getText() == "") notEmpty = false;
		
		return notEmpty;
	}
}
