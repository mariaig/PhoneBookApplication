package PBA.view;


import java.util.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.border.EmptyBorder;
import PBA.model.Contact;
import PBA.service.UserService;
import PBA.utils.LanguageUtils;
/**
 * Clasa pt implementarea interfetei grafice precum si executarea
 * actiunilor pe butoane
 * @author Maria
 */
public class PersDetailsView extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	



	JTextField add_text_first=new JTextField(25);
	JTextField add_text_last=new JTextField(25);
	JTextField add_text_birth=new JTextField(25);
	JTextField add_text_street=new JTextField(25);
	JTextField add_text_no=new JTextField(25);
	JTextField add_text_city=new JTextField(25);
	JTextField add_text_country=new JTextField(25);


	JButton add_save =new JButton ("Save");
	JButton add_cancel=new JButton("Cancel");
	JButton add_cont_det=new JButton("->(Contact Details)");
	
	


	public PersDetailsView() throws Exception{
		setBorder(new EmptyBorder(20, 10, 30, 10));
		
		


		add_save.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add_save.addActionListener(this);
		add_save.setActionCommand("Save");

		add_cancel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add_cancel.addActionListener(this);
		add_cancel.setActionCommand("Cancel");

		add_cont_det.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add_cont_det.addActionListener(this);
		add_cont_det.setActionCommand("Go");


		GridLayout gridLayout = new GridLayout(9,3);
		gridLayout.setVgap(10);
		gridLayout.setHgap(13);
		setLayout(gridLayout);
		
		
		JLabel label_1 = new JLabel(LanguageUtils.getI18NString("label.contact.firstname")+": ");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(label_1);
		add(add_text_first);
		add(new JLabel());
		
		JLabel label_2 = new JLabel(LanguageUtils.getI18NString("label.contact.lastname")+": ");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(label_2);
		add(add_text_last);
		add(new JLabel());
		
		JLabel label_3 = new JLabel(LanguageUtils.getI18NString("label.contact.birthdate")+": ");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(label_3);
		add(add_text_birth);
		add(new JLabel());

		JLabel label = new JLabel(LanguageUtils.getI18NString("label.contact.adress")+": ");
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		add(label);
		add(new JLabel());
		add(new JLabel());

		JLabel label_4 = new JLabel(LanguageUtils.getI18NString("label.contact.street")+": ");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(label_4);
		add(add_text_street);
		add(new JLabel());
		
		JLabel label_5 = new JLabel(LanguageUtils.getI18NString("label.contact.number")+": ");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(label_5);
		add(add_text_no);
		add(new JLabel());
		
		JLabel label_6 = new JLabel(LanguageUtils.getI18NString("label.contact.city")+": ");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(label_6);
		add(add_text_city);
		add(new JLabel());
		
		JLabel label_7 = new JLabel(LanguageUtils.getI18NString("label.contact.country")+": ");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(label_7);
		add(add_text_country);
		add(new JLabel());
		
		add(add_save);
		add(add_cancel);
		add(add_cont_det);
	


		
		setVisible (true);
	}

	public void actionPerformed(ActionEvent e){

		if(e.getActionCommand().equals("Cancel"))
		{
			try {
				setVisible(false);
				Vector<JPanel> v=null;
				v=PersonsOverview.parameters();
				v.get(0).setVisible(true);
				v.get(1).setVisible(true);
				v.get(2).setVisible(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		final String firstname=add_text_first.getText();
		final String lastname=add_text_last.getText();
		final String birthdate=add_text_birth.getText();
		final String street=add_text_street.getText();
		final String no=add_text_no.getText();
		final String city=add_text_city.getText();
		final String country=add_text_country.getText();


		if(e.getActionCommand().equals("Save")||e.getActionCommand().equals("Go")){

			if(firstname.equals("")||lastname.equals("")||birthdate.equals(""))
			{
				JOptionPane.showMessageDialog(null, "Firstname, lastname & birthdate are obligatory", "alert", JOptionPane.ERROR_MESSAGE);

			}
			else{

				Contact user=new Contact();
				user.setFirstName(firstname);
				user.setLastName(lastname);
				user.setBirthdate(birthdate);
				user.setStreet(street);
				if(!no.equals("")){
					user.setNumber(Integer.parseInt(no));
				}

				user.setCity(city);
				user.setCountry(country);
				
				if(e.getActionCommand().equals("Save")){
					try {
						UserService.insertContact(user);
						setVisible(false);
						Vector<JPanel> v=null;
						v=PersonsOverview.parameters();
						v.get(0).setVisible(true);
						v.get(1).setVisible(true);
						v.get(2).setVisible(true);
						
						
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}


				}
				if(e.getActionCommand().equals("Go")){

					try {
						new ContactDetails(user.getContactId());

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}


			}


		}

	}
//	public static void main(String[] args) throws Exception {
//		JFrame frame=new JFrame();
//		PersDetailsView add_panel=new PersDetailsView();
//		frame.getContentPane().add(add_panel);
//		frame.setSize(500,500);
//		frame.pack();
//		frame.setVisible(true);
//	}



}
