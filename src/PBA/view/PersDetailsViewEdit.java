package PBA.view;


import java.util.*;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import PBA.model.Contact;
import PBA.service.UserService;
import PBA.utils.LanguageUtils;
/**
 * Clasa pt implementarea interfetei grafice precum si executarea
 * actiunilor pe butoane
 * @author Maria
 */
public class PersDetailsViewEdit  extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Contact cont=new Contact();
	

	JTextField edit_text_first;
	JTextField edit_text_last;
	JTextField edit_text_birth;
	JTextField edit_text_street;
	JTextField edit_text_no;
	JTextField edit_text_city;
	JTextField edit_text_country;





	JButton edit_save =new JButton ("Save");
	JButton edit_cancel=new JButton("Cancel");
	JButton edit_cont_det=new JButton("->(Contact Details)");
	
	long contact_id;


	public PersDetailsViewEdit(long contact_id) throws Exception{
		setBorder(new EmptyBorder(20, 10, 30, 10));
		this.contact_id=contact_id;
		
		

		edit_save.setFont(new Font("Tahoma", Font.PLAIN, 12));
		edit_save.addActionListener(this);
		edit_save.setActionCommand("Save");

		edit_cancel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		edit_cancel.addActionListener(this);
		edit_cancel.setActionCommand("Cancel");

		edit_cont_det.setFont(new Font("Tahoma", Font.PLAIN, 12));
		edit_cont_det.addActionListener(this);
		edit_cont_det.setActionCommand("Go");
		

		
		
		//TEXTFIELDS:


				cont=UserService.getContactactById(contact_id);
				edit_text_first=new JTextField(cont.getFirstName(),25);
				edit_text_last=new JTextField(cont.getLastName(),25);
				edit_text_birth=new JTextField(cont.getBirthdate(),25);
				edit_text_street=new JTextField(cont.getStreet(),25);
				if(cont.getNumber()==0){
					edit_text_no=new JTextField("",25);
				}
				else {
					edit_text_no=new JTextField(Integer.toString(cont.getNumber()),25);
				}
				edit_text_city=new JTextField(cont.getCity(),25);
				edit_text_country=new JTextField(cont.getCountry(),25);
				
				

		GridLayout gridLayout = new GridLayout(9,3);
		gridLayout.setVgap(10);
		gridLayout.setHgap(13);
		setLayout(gridLayout);
		JLabel label_1 = new JLabel(LanguageUtils.getI18NString("label.contact.firstname")+": ");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(label_1);
		add(edit_text_first);
		add(new JLabel());
		
		JLabel label_2 = new JLabel(LanguageUtils.getI18NString("label.contact.lastname")+": ");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(label_2);
		add(edit_text_last);
		add(new JLabel());
		
		JLabel label_3 = new JLabel(LanguageUtils.getI18NString("label.contact.birthdate")+": ");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(label_3);
		add(edit_text_birth);
		add(new JLabel());

		JLabel label = new JLabel(LanguageUtils.getI18NString("label.contact.adress")+": ");
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		add(label);
		add(new JLabel());
		add(new JLabel());

		JLabel label_4 = new JLabel(LanguageUtils.getI18NString("label.contact.street")+": ");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(label_4);
		add(edit_text_street);
		add(new JLabel());
		
		JLabel label_5 = new JLabel(LanguageUtils.getI18NString("label.contact.number")+": ");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(label_5);
		add(edit_text_no);
		add(new JLabel());
		
		JLabel label_6 = new JLabel(LanguageUtils.getI18NString("label.contact.city")+": ");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(label_6);
		add(edit_text_city);
		add(new JLabel());
		
		JLabel label_7 = new JLabel(LanguageUtils.getI18NString("label.contact.country")+": ");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(label_7);
		add(edit_text_country);
		add(new JLabel());
		
		add(edit_save);
		add(edit_cancel);
		add(edit_cont_det);
	
		

	}

	public void actionPerformed(ActionEvent e) {
		
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
		final String firstname=edit_text_first.getText();
		final String lastname=edit_text_last.getText();
		final String birthdate=edit_text_birth.getText();
		final String street=edit_text_street.getText();
		final String no=edit_text_no.getText();
		final String city=edit_text_city.getText();
		final String country=edit_text_country.getText();


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
				user.setContactId(contact_id);
				try{
					UserService.UpdateUser(user);
				}catch(ClassNotFoundException | SQLException e1){
					e1.getStackTrace();
				}
				if(e.getActionCommand().equals("Save")){
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
				if(e.getActionCommand().equals("Go")){

						try {
							new ContactDetails(contact_id);
							
							
							
						} catch (Exception e1) {
							
							e1.printStackTrace();
						}
						

				}


			}


		}

	}
//	public static void main(String[] args) throws Exception {
//		JFrame frame=new JFrame();
//		frame.getContentPane().add(new PersDetailsViewEdit(2));
//		frame.pack();
//		frame.setVisible(true);}
		


}
