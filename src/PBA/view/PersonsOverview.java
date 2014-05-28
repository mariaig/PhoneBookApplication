package PBA.view;

import java.util.*;


import java.awt.*;


import javax.swing.*;
import javax.swing.border.EmptyBorder;




import PBA.db.JDBCUtils;
import PBA.model.Contact;
import PBA.service.UserService;


import java.awt.event.*;
import java.awt.print.PrinterException;
import java.sql.*;
import PBA.utils.*;

/**
 * Clasa pt implementarea interfetei grafice precum si executarea
 * actiunilor pe butoane
 * @author Maria
 */
public class PersonsOverview extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static int contor=0;
	private static final long serialVersionUID = 1L;
	Connection con =JDBCUtils.getConnection(); 


	JTable table;
	static JPanel p1=new JPanel();
	static JPanel p2=new JPanel(new GridLayout (2,0));
	static JPanel p3=new JPanel();
	JPanel add_p1=new JPanel();

	JButton search =new JButton ("Search");;
	JButton new_pers=new JButton("New person");
	JButton edit_pers=new JButton("Edit person");
	JButton delete_pers=new JButton("Delete person");
	JButton print=new JButton ("Print");
	JButton cont_det=new JButton("->(Contact Details)");
	JTextField text_s=new JTextField(20);




	JComboBox<Object> comb;
	private final JLabel labelSearchBy;

	public PersonsOverview() throws Exception{


		p1.setBorder(new EmptyBorder(0, 10, 10, 10));
		p2.setBorder(new EmptyBorder(10, 10, 10, 10));
		p3.setBorder(new EmptyBorder(10, 10, 10, 20));

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().add(p2,BorderLayout.CENTER);
		String[] personAtt = { "Firstname", "Lastname", "Birthdate(yyyy-mm-dd)", "Street", "Street Number", "City", "Country" };

		new_pers.setFont(new Font("Tahoma", Font.PLAIN, 12));
		new_pers.addActionListener(this);
		new_pers.setActionCommand("New");

		edit_pers.setFont(new Font("Tahoma", Font.PLAIN, 12));
		edit_pers.addActionListener(this);
		edit_pers.setActionCommand("Edit");

		delete_pers.setFont(new Font("Tahoma", Font.PLAIN, 12));
		delete_pers.addActionListener(this);
		delete_pers.setActionCommand("Delete");

		print.setFont(new Font("Tahoma", Font.PLAIN, 12));
		print.addActionListener(this);
		print.setActionCommand("Print");

		cont_det.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cont_det.addActionListener(this);
		cont_det.setActionCommand("Go");

		p1.setBorder(new EmptyBorder(20, 10, 10, 0));



		labelSearchBy=new JLabel(LanguageUtils.getI18NString("label.persons.search")+": ");

		getContentPane().add(p1,BorderLayout.NORTH);

		p1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		p1.add(labelSearchBy);
		comb=new JComboBox<Object>(personAtt);
		comb.addActionListener(this);
		p1.add(comb);
		p1.add(text_s);

		search.addActionListener(this);
		search.setActionCommand("Search");
		p1.add(search);
		p3.setBorder(new EmptyBorder(10, 0, 30, 0));

		getContentPane().add(p3,BorderLayout.SOUTH);
		p3.add(new_pers);
		p3.add(edit_pers);
		p3.add(delete_pers);
		p3.add(print);
		p3.add(cont_det);
		setSize(684,518);
		setVisible (true);
	}

	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals("Search")) {
			Contact user=new Contact();
			int indexcomb=comb.getSelectedIndex();

			final String search_item= text_s.getText();

			if(indexcomb==0){
				user.setFirstName(search_item);
			}
			if(indexcomb==1){
				user.setLastName(search_item);
			}
			if(indexcomb==2){
				user.setBirthdate(search_item);
			}
			if(indexcomb==3){
				user.setStreet(search_item);
			}
			if(indexcomb==4){
				int number=0;
				try{
					if(!search_item.equals("")){
						number=Integer.parseInt(search_item);
					}
					user.setNumber(number);
				}
				catch(Exception e2){
					e2.getStackTrace();
				}
			}
			if(indexcomb==5){
				user.setCity(search_item);
			}
			if(indexcomb==6){
				user.setCountry(search_item);
			}
			try {
				contor++;
				if (contor >1) p2.removeAll();
				ResultSet resultSet=UserService.SearchUser(user);
				table = TableView.getTable (resultSet);
				p2.add(table);
				JScrollPane sp=new JScrollPane(table);
				p2.add(sp);
				table.setAutoCreateRowSorter(true);
				setVisible(true);								



			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		}

		if(e.getActionCommand().equals("Delete"))
		{
			try{
				int[] row = table.getSelectedRows();
				if(row.length>1){
					JOptionPane.showMessageDialog(null, "You can delete just one row. \n You have selected "+row.length+" rows! ", "alert", JOptionPane.ERROR_MESSAGE);
				}
				else{
					Object[] options = { "Yes", "CANCEL" };
					if(JOptionPane.showOptionDialog(null, "Are you sure you want to delete this contact?", "Question",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
							null, options, options[0])==0)
					{
						int rowi=row[0];
						int cont_id=Integer.parseInt((String) table.getValueAt(rowi, 0));
						UserService.deleteUser(cont_id);

					}
				}
			}
			catch(Exception e1){
				JOptionPane.showMessageDialog(null, "You have to select a row first", "alert", JOptionPane.ERROR_MESSAGE);
			}

		}


		if(e.getActionCommand().equals("New")){
			try {
				p1.setVisible(false);
				p2.setVisible(false);
				p3.setVisible(false);
				PersDetailsView add_panel=new PersDetailsView();
				getContentPane().add(add_panel);
				add_panel.setVisible(true);
				pack();

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
					p1.setVisible(false);
					p2.setVisible(false);
					p3.setVisible(false);
					int rowi=row[0];
					int cont_id=Integer.parseInt((String) table.getValueAt(rowi, 0));
					PersDetailsViewEdit edit_panel;

					edit_panel = new PersDetailsViewEdit(cont_id);
					getContentPane().add(edit_panel);
					edit_panel.setVisible(true);
					pack();

				}
			}
			catch(Exception e2){
				JOptionPane.showMessageDialog(null, "You have to select a row first", "alert", JOptionPane.ERROR_MESSAGE);
			} 

		}
		if(e.getActionCommand().equals("Print")){


			try {
				table.print();
			} 
			catch (PrinterException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getActionCommand().equals("Go")){
			try{
				int[] row = table.getSelectedRows();
				if(row.length>1){
					JOptionPane.showMessageDialog(null, " You have selected "+row.length+" contacts! ", "alert", JOptionPane.ERROR_MESSAGE);
				}
				else{
					int rowi=row[0];
					int cont_id=Integer.parseInt((String) table.getValueAt(rowi, 0));
					new ContactDetails(cont_id);
					setVisible(false);
				}
			}catch(ArrayIndexOutOfBoundsException|NullPointerException e1){
				JOptionPane.showMessageDialog(null, "You have to select a row first", "alert", JOptionPane.ERROR_MESSAGE);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}



	public static Vector<JPanel> parameters (){
		Vector<JPanel> v=new Vector<JPanel>();
		v.add(p1);
		v.add(p2);
		v.add(p3);
		return v;

	} 






	public static void main  (String [] args) throws Exception {

		new PersonsOverview();
	}


}


