package PBA.service;

import java.sql.*; 



import PBA.db.*;
import PBA.model.Contact;
import PBA.model.ContactDetail;

/**
 * @author Maria
 */

public class UserService {
	
	/**
	 * Inserarea in baza de date a unui contact
	 * @param user
	 * @return cheia(din baza de date) a user-ului trimis ca parametru
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int insertContact(Contact user) throws ClassNotFoundException, SQLException
	{

		int nr=0;
		String query="INSERT INTO contact (firstname,lastname,birthdate,street,number,city,country) VALUES(?,?,?,?,?,?,?)";
		Connection con=null;
		PreparedStatement preStatement=null;
		ResultSet resultSet=null;

		try{
			con=JDBCUtils.getConnection();
			preStatement=con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			int index=0;
			preStatement.setString(++index,user.getFirstName());
			preStatement.setString(++index, user.getLastName());
			preStatement.setString(++index, user.getBirthdate());
			preStatement.setString(++index, user.getStreet());
			preStatement.setInt(++index, user.getNumber());
			preStatement.setString(++index, user.getCity());
			preStatement.setString(++index, user.getCountry());
			nr=preStatement.executeUpdate();
			resultSet=preStatement.getGeneratedKeys();
			if(resultSet.next()){
				user.setContactId(resultSet.getLong(1));
			}

		}
		
		finally{
			JDBCUtils.closeResult(resultSet);
			JDBCUtils.closePreparedStatement(preStatement);
			JDBCUtils.close(con);


		}

		return nr;
	}

	/**
	 * Inserarea in baza de date a unui contactDetail
	 * @param cd
	 * @return cheia(din baza de date) a contactDetail-ului trimis ca parametru
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int insertContactDetails(ContactDetail cd) throws ClassNotFoundException, SQLException
	{

		int nr=0;
		String query="INSERT INTO contact_details (contact_id, prefix, phone_number, network_name) VALUES(?,?,?,?)";
		Connection con=null;
		PreparedStatement preStatement=null;

		try{
			con=JDBCUtils.getConnection();
			preStatement=con.prepareStatement(query);
			int index=0;
			preStatement.setLong(++index,cd.getContactId());
			preStatement.setString(++index,cd.getPrefix());
			preStatement.setString(++index, cd.getPhoneNumber());
			preStatement.setString(++index, cd.getNetworkName() );
			nr=preStatement.executeUpdate();


		}
		
		finally{

			JDBCUtils.closePreparedStatement(preStatement);
			JDBCUtils.close(con);
		}

		return nr;
	}



	/**
	 * Stergerea unui contactDetail din baza de date in functie de id-ului acestuia
	 * @param id
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int deleteContactDetails(int id) throws ClassNotFoundException, SQLException
	{

		int nr=0;
		String query="DELETE FROM contact_details where contact_detail_id=?";
		Connection con=null;
		PreparedStatement preStatement=null;

		try{
			con=JDBCUtils.getConnection();
			preStatement=con.prepareStatement(query);
			preStatement.setInt(1,id);
			nr=preStatement.executeUpdate();
		}
	
		
		finally{

			JDBCUtils.closePreparedStatement(preStatement);
			JDBCUtils.close(con);


		}
		return nr;
	}


	/**
	 * Stergerea unui user din baza de date in functie de id
	 * @param id
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int deleteUser(int id) throws ClassNotFoundException, SQLException
	{

		int nr=0;
		String query="DELETE FROM contact where contact_id=?";
		Connection con=null;
		PreparedStatement preStatement=null;

		try{
			con=JDBCUtils.getConnection();
			preStatement=con.prepareStatement(query);
			preStatement.setInt(1,id);
			nr=preStatement.executeUpdate();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	
		finally{
			JDBCUtils.closePreparedStatement(preStatement);
			JDBCUtils.close(con);


		}
		return nr;
	}

	/**
	 * Metoda ce face update in baza de date a unui contactDetail
	 * @param cd
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int UpdateContactDetails(ContactDetail cd) throws ClassNotFoundException, SQLException 
	{
		int nr=0;
		String query="UPDATE contact_details SET prefix=?, phone_number=?, network_name=? WHERE contact_detail_id=?";
		Connection con=null;
		PreparedStatement preStatement=null;


		try{
			con=JDBCUtils.getConnection();
			preStatement=con.prepareStatement(query);

			int index=0;

			preStatement.setString(++index, cd.getPrefix());
			preStatement.setString(++index, cd.getPhoneNumber());
			preStatement.setString(++index, cd.getNetworkName() );
			preStatement.setLong(++index, cd.getContactDetailId());
			nr=preStatement.executeUpdate();

		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		finally{
			JDBCUtils.closePreparedStatement(preStatement);
			JDBCUtils.close(con);

		}

		return nr;
	}
	
	
	/**
	 * Metoda ce face update in baza de date a unui user
	 * @param user
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int UpdateUser(Contact user) throws ClassNotFoundException, SQLException
	{
		int nr=0;

		String query="UPDATE contact SET firstname=?, lastname=?, birthdate=?, street=?, number=?, city=?, country=? WHERE contact_id=?"; 
		Connection con=null;
		PreparedStatement preStatement=null;


		try{
			con=JDBCUtils.getConnection();
			preStatement=con.prepareStatement(query);

			int index=0;

			preStatement.setString(++index,user.getFirstName());
			preStatement.setString(++index, user.getLastName());
			preStatement.setString(++index, user.getBirthdate() );
			preStatement.setString(++index, user.getStreet());
			preStatement.setInt(++index, user.getNumber());
			preStatement.setString(++index, user.getCity());
			preStatement.setString(++index, user.getCountry());
			preStatement.setLong(++index, user.getContactId());
			nr=preStatement.executeUpdate();

		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		finally{
			JDBCUtils.closePreparedStatement(preStatement);
			JDBCUtils.close(con);


		}

		return nr;
	}

	/**
	 * Metoda care face select in baza de date in functie de user-ul trimis ca parametru
	 * @param user
	 * @return un resultSet ce contine toate toate datele despre user
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ResultSet SearchUser(Contact user)throws ClassNotFoundException, SQLException 
	{

		String query="SELECT contact_id,firstname, lastname, birthdate, street, number, city, country FROM contact WHERE 1=1";
		Connection con=null;
		PreparedStatement preStatement=null;
		ResultSet resultSet=null;


		try{
			con=JDBCUtils.getConnection();

			if(user.getFirstName()!=null){
				query+=" AND firstname LIKE ? ";
			}else{
				query += " AND ( 1=1 or firstname like ? )";
			}
			
			if(user.getLastName()!=null){
				query+=" AND lastname LIKE ? ";
			}else{
				query += " AND ( 1=1 or lastname like ? ) ";
			}
			
			if(user.getBirthdate()!=null){
				query+=" AND birthdate LIKE ? ";
			}else{
				query += " AND ( 1=1 or birthdate like ? ) ";

			}
			
			if(user.getStreet()!=null){
				query+=" AND street LIKE ? ";
			}else{
				query += " AND ( 1=1 or street like ? ) ";
			}
			
			if(user.getNumber()!=0){
				query+=" AND number like ? ";
			}else{
				query += " AND ( 1=1 or number like ? ) ";
			}
			
			if(user.getCity()!=null){
				query+=" AND city LIKE ? ";
			}else{
				query += " AND ( 1=1 or city like ? )";
			}
			
			if(user.getCountry()!=null){
				query+=" AND country LIKE ? ";
			}else{
				query += " AND ( 1=1 or country like ? )";
			}

			preStatement=con.prepareStatement(query);
			int index=0;
			preStatement.setString(++index, "%"+user.getFirstName()+"%");
			preStatement.setString(++index, "%"+user.getLastName()+"%");
			preStatement.setString(++index, "%"+user.getBirthdate()+"%");
			preStatement.setString(++index, "%"+user.getStreet()+"%");
			preStatement.setString(++index, "%"+user.getNumber()+"%");
			preStatement.setString(++index, "%"+user.getCity()+"%");
			preStatement.setString(++index, "%"+user.getCountry()+"%");


			resultSet=preStatement.executeQuery();


		}
		catch (Exception e){
			e.printStackTrace();
		}
	


		return resultSet;
	}
	

	/**
	 * Metoda care face select in baza de date in fuctie de id pt a lua datele
	 * unui contact detail
	 * @param uId
	 * @return un resultSet ce contine toate datele despre contactDetailul cerut
	 */
	public static ResultSet getRSContactDetails(long uId)
	{
		
		String query="SELECT contact_detail_id, contact_id,  prefix, phone_number, network_name  FROM contact_details WHERE contact_id=?";
		
		Connection con=null;
		ResultSet resultSet=null;
		PreparedStatement preStatement=null;
		try{
			con=JDBCUtils.getConnection();
			preStatement=con.prepareStatement(query);
			preStatement.setLong(1,uId);
			resultSet=preStatement.executeQuery();

		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return resultSet;
	}

	
	/**
	 * Metoda care construieste un resultSet ce contine datele unui ContactId ce corespunde
	 * id-ului (de user) trimis ca parametru
	 * @param uId
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ContactDetail getContactDetailByContactDetailId(long uId)throws ClassNotFoundException, SQLException
	{
		ContactDetail user=null;
		String query="SELECT contact_detail_id, contact_id,  prefix, phone_number, network_name  FROM contact_details WHERE contact_detail_id=?";
		
		Connection con=null;
		ResultSet resultSet=null;
		PreparedStatement preStatement=null;
		try{
			con=JDBCUtils.getConnection();
			preStatement=con.prepareStatement(query);
			preStatement.setLong(1,uId);
			resultSet=preStatement.executeQuery();

			if(resultSet.next()){
				user=new ContactDetail();
				int index=0;
				user.setContactId(resultSet.getLong(++index));
				user.setContactDetailId(resultSet.getInt(++index));
				user.setPrefix(resultSet.getString(++index));
				user.setPhoneNumber(resultSet.getString(++index));
				user.setNetworkName(resultSet.getString(++index));

			}

		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally{
			JDBCUtils.closeResult(resultSet);
			JDBCUtils.closePreparedStatement(preStatement);
			JDBCUtils.close(con);
		}

		return user;
	}
	
	
	/**
	 * Metoda care ia datele unui contact in functie de id-ul trimis ca parametru
	 * @param uId
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Contact getContactactById(long uId) throws ClassNotFoundException, SQLException
	{
		Contact user=null;
		String query="SELECT  contact_id,firstname, lastname, birthdate, street, number, city, country FROM contact WHERE contact_id=?";
		Connection con=null;
		ResultSet resultSet=null;
		PreparedStatement preStatement=null;
		try{
			con=JDBCUtils.getConnection();
			preStatement=con.prepareStatement(query);
			preStatement.setLong(1,uId);
			resultSet=preStatement.executeQuery();

			if(resultSet.next()){
				user=new Contact();
				int index=0;
				
				user.setContactId(resultSet.getLong(++index));
				user.setFirstName(resultSet.getString(++index));
				user.setLastName(resultSet.getString(++index));
				user.setBirthdate(resultSet.getString(++index));
				user.setStreet(resultSet.getString(++index));
				user.setNumber(resultSet.getInt(++index));
				user.setCity(resultSet.getString(++index));
				user.setCountry(resultSet.getString(++index));

			}

		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally{
			JDBCUtils.closeResult(resultSet);
			JDBCUtils.closePreparedStatement(preStatement);
			JDBCUtils.close(con);
		}

		return user;
	}
}
