package com.main;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.dao.ContactDAO;
import com.model.Contact;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtNumber;
	private JTextField txtId;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			UpdateDialog dialog = new UpdateDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public UpdateDialog() {
		setTitle("Update Contact Details");
		setBounds(100, 100, 350, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setEnabled(false);
		lblName.setBounds(10, 42, 46, 14);
		contentPanel.add(lblName);
		
		txtName = new JTextField();
		txtName.setEnabled(false);
		txtName.setColumns(10);
		txtName.setBounds(85, 39, 86, 20);
		contentPanel.add(txtName);
		
		JLabel lblNumber = new JLabel("Number");
		lblNumber.setEnabled(false);
		lblNumber.setBounds(10, 72, 46, 14);
		contentPanel.add(lblNumber);
		
		txtNumber = new JTextField();
		txtNumber.setEnabled(false);
		txtNumber.setColumns(10);
		txtNumber.setBounds(85, 69, 86, 20);
		contentPanel.add(txtNumber);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(10, 14, 46, 14);
		contentPanel.add(lblId);
		
		txtId = new JTextField();
		txtId.setColumns(10);
		txtId.setBounds(85, 11, 86, 20);
		contentPanel.add(txtId);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ContactDAO cDAO=new ContactDAO();
				int id=Integer.parseInt(txtId.getText());
				Contact contact=cDAO.searchContact(id);
				if(contact!=null){
					txtName.setText(contact.getName());
					txtNumber.setText(contact.getNumber());
					txtName.setEnabled(true);
					txtNumber.setEnabled(true);
					lblName.setEnabled(true);
					lblNumber.setEnabled(false);
					txtId.setEnabled(false);
				}else{
					JOptionPane.showMessageDialog(null, "Record Not Found For Given ID!!!");
				}
			}
		});
		btnSearch.setBounds(181, 10, 89, 23);
		contentPanel.add(btnSearch);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Contact contact=new Contact();
						contact.setId(Integer.parseInt(txtId.getText()));
						contact.setName(txtName.getText());
						contact.setNumber(txtNumber.getText());
						ContactDAO cDAO=new ContactDAO();
						int count=cDAO.updateContact(contact);
						if(count>0)
						{
							JOptionPane.showMessageDialog(null, "Record Updated Successfully!!!");
						}else{
							JOptionPane.showMessageDialog(null, "Record Can't Updated !!!");	
						}
						//System.out.println("Result : "+count);
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
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
