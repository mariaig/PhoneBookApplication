package PBA.view;

import java.awt.*;

import javax.swing.*;
import PBA.db.JDBCUtils;
import PBA.service.UserService;
import java.awt.event.*;
import java.sql.*;
import javax.swing.border.EmptyBorder;


/**
 * Clasa pt implementarea interfetei grafice precum si executarea
 * actiunilor pe butoane
 * @author Maria
 */
public class ContactDetails extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection con =JDBCUtils.getConnection();
	
	
	JTable table;
	JPanel p1=new JPanel();
	JPanel p2=new JPanel();
	JButton new_cont=new JButton("Add contact_det");
	JButton edit_cont=new JButton("Edit contact_det");
	JButton delete_cont=new JButton("Delete contact_det");
	JButton cancel=new JButton("Cancel");
	long contact_id;
	boolean valid=false;
	

	
	public ContactDetails(long contact_id) throws Exception{
		this.contact_id=contact_id;

		 

		new_cont.addActionListener(this);
		new_cont.setActionCommand("New");

		edit_cont.addActionListener(this);
		edit_cont.setActionCommand("Edit");

		delete_cont.addActionListener(this);
		delete_cont.setActionCommand("Delete");

		cancel.addActionListener(this);
		cancel.setActionCommand("Cancel");
		p2.setBorder(new EmptyBorder(20, 0, 30, 0));


		getContentPane().add(p2,BorderLayout.NORTH);
		p2.add(new_cont);
		p2.add(edit_cont);
		p2.add(delete_cont);
		p2.add(cancel);
		p1.setBorder(new EmptyBorder(20, 0, 30, 0));
		
		
		getContentPane().add(p1,BorderLayout.CENTER);
		
		ResultSet resultSet=UserService.getRSContactDetails(contact_id);
		table = TableView.getTable (resultSet);
		if(table.getRowCount()!=0){
			
			p1.add(table);
			JScrollPane sp=new JScrollPane(table);
			p1.add(sp);
			table.setAutoCreateRowSorter(true);
			table.setVisible(true);	
		}
		else{
			p1.add(new JLabel("Your person doesn't have contact details yet."));
			valid=true;
			
			
		}


		setResizable(true);
		setSize(542,409);
		setVisible (true);
		
		
	}


	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("New")){
			try {
				new EditContactDetails(contact_id);
				setVisible(false);
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getActionCommand().equals("Cancel"))
		{
			try {
			dispose();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		if(e.getActionCommand().equals("Edit")){
			try{
				int[] row = table.getSelectedRows();
				if(row.length>1){
					JOptionPane.showMessageDialog(null, "You can edit just one contact. \n You have selected "+row.length+" contacts! ", "alert", JOptionPane.ERROR_MESSAGE);
				}
				else{
					int rowi=row[0];
					int cont_id=Integer.parseInt((String)table.getValueAt(rowi, 1));
					int cont_detail_id=Integer.parseInt((String)table.getValueAt(rowi, 0));
					
					setVisible(false);
					
					try{
					new EditContactDetails_Edit(cont_id,cont_detail_id);
					}catch(Exception ex){
						ex.getStackTrace();
					}
					
				}
			}catch(Exception e1){
				JOptionPane.showMessageDialog(null, "You have to select a row first", "alert", JOptionPane.ERROR_MESSAGE);
			} 

		}
		if(e.getActionCommand().equals("Delete"))
		{
			Object[] options = { "Yes", "CANCEL" };
			if(JOptionPane.showOptionDialog(null, "Are you sure you want to delete this contact?", "Question",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
					null, options, options[0])==0)
			{
				try{
					int[] row = table.getSelectedRows();
					if(row.length>1){
						JOptionPane.showMessageDialog(null, "You can delete just one row. \n You have selected "+row.length+" rows! ", "alert", JOptionPane.ERROR_MESSAGE);
					}else{
						int rowi=row[0];
						int cont_id=Integer.parseInt((String) table.getValueAt(rowi, 0));
						UserService.deleteContactDetails(cont_id);
						setVisible(false);
						new ContactDetails(contact_id);


					}
				}
				catch(Exception e1){
					JOptionPane.showMessageDialog(null, "You have to select a row first", "alert", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}


	
}