package com.main;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.dao.ContactDAO;
import com.model.Contact;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class InsertDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtNumber;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			InsertDialog dialog = new InsertDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public InsertDialog() {
		setTitle("Insert Contact Details");
		setBounds(100, 100, 350, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			txtNumber = new JTextField();
			txtNumber.setBounds(85, 49, 86, 20);
			contentPanel.add(txtNumber);
			txtNumber.setColumns(10);
		}
		{
			txtName = new JTextField();
			txtName.setBounds(85, 11, 86, 20);
			contentPanel.add(txtName);
			txtName.setColumns(10);
		}
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 14, 46, 14);
		contentPanel.add(lblName);
		
		JLabel lblNumber = new JLabel("Number");
		lblNumber.setBounds(10, 52, 46, 14);
		contentPanel.add(lblNumber);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Contact contact=new Contact();
						contact.setName(txtName.getText());
						contact.setNumber(txtNumber.getText());
						ContactDAO cDAO=new ContactDAO();
						int count=cDAO.addContact(contact);
						System.out.println("Result : "+count);
						if(count>0)
						{
							JOptionPane.showMessageDialog(null, "Record Inserted Successfully!!!");
						}else{
							JOptionPane.showMessageDialog(null, "Record Can't Inserted !!!");	
						}
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
