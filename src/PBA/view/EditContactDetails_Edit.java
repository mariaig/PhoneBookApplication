package PBA.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import PBA.model.ContactDetail;
import PBA.service.UserService;
import PBA.utils.LanguageUtils;
/**
 * Clasa pt implementarea interfetei grafice precum si executarea
 * actiunilor pe butoane
 * @author Maria
 */
public class EditContactDetails_Edit extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JTextField text_prefix;
	JTextField text_no;
	JTextField text_network;

	JPanel p1=new JPanel();
	JPanel p2=new JPanel();
	JPanel p3=new JPanel();

	JButton save =new JButton ("Save");
	JButton cancel=new JButton("Cancel");
	long contact_detail_id;
	long contact_id;



	public   EditContactDetails_Edit(long contact_id,long contact_detail_id) throws Exception,NullPointerException{
		
		this.contact_id=contact_id;
		this.contact_detail_id=contact_detail_id;
		
		BorderLayout borderLayout = (BorderLayout) getContentPane().getLayout();
		borderLayout.setVgap(40);
		borderLayout.setHgap(20);

		save.setFont(new Font("Tahoma", Font.PLAIN, 12));
		save.addActionListener(this);
		save.setActionCommand("Save");

		cancel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cancel.addActionListener(this);
		cancel.setActionCommand("Cancel");


		p1.setBorder(new EmptyBorder(20, 20, 0, 0));

		getContentPane().add(p1,BorderLayout.WEST);
		p1.setLayout(new GridLayout(0,1));
		
		JLabel label_1 = new JLabel(LanguageUtils.getI18NString("label.contact.detail.prefix")+": ");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		p1.add(label_1);
		
		JLabel label_2 = new JLabel(LanguageUtils.getI18NString("label.contact.detail.phone.number")+": ");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		p1.add(label_2);
		
		JLabel label_3 = new JLabel(LanguageUtils.getI18NString("label.contact.detail.network.name")+": ");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		p1.add(label_3);


		p2.setBorder(new EmptyBorder(20, 10, 0, 50));


		//TEXTFIELDS
		getContentPane().add(p2,BorderLayout.CENTER);

		ContactDetail contDetail=new ContactDetail();
		contDetail=UserService.getContactDetailByContactDetailId(contact_detail_id);
		text_prefix=new JTextField(contDetail.getPrefix(),25);
		text_no=new JTextField(contDetail.getPhoneNumber(),25);
		text_network=new JTextField(contDetail.getNetworkName(),25);
		
		GridLayout gl_p2 = new GridLayout(0,1);
		gl_p2.setVgap(10);
		gl_p2.setHgap(20);
		p2.setLayout(gl_p2);
		p2.add(text_prefix);
		p2.add(text_no);
		p2.add(text_network);

		p3.setBorder(new EmptyBorder(0, 0, 30, 0));


		getContentPane().add(p3,BorderLayout.SOUTH);
		FlowLayout fl_p3 = new FlowLayout();
		fl_p3.setHgap(10);
		p3.setLayout(fl_p3);
		p3.add(save);
		p3.add(cancel);

		setResizable(true);
		setSize(396,253);
		setVisible (true);

	}

	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("Save")){
			
			ContactDetail cd=new ContactDetail();
			cd.setContactDetailId(contact_detail_id);
			cd.setPrefix(text_prefix.getText());
			cd.setPhoneNumber(text_no.getText());
			cd.setNetworkName(text_network.getText());
			try{
				UserService.UpdateContactDetails(cd);

				new ContactDetails(contact_id);
				setVisible(false);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		if(e.getActionCommand().equals("Cancel")){
			try {
				new ContactDetails(contact_id);
				setVisible(false);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}

}
