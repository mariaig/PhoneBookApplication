package PBA.view;

import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import PBA.db.JDBCUtils;

import com.mysql.jdbc.ResultSetMetaData;

/**
 * Importarea tabelei din baza de date sub forma unui JTable user-friendly
 * @author Maria-Laptop
 *
 */
public class TableView{

	

    public static JTable getTable(ResultSet resultSet)throws Exception{
    	Connection con=JDBCUtils.getConnection();
        JTable t1=new JTable();
        DefaultTableModel dm=new DefaultTableModel();
      
        ResultSetMetaData rsmd=(ResultSetMetaData) resultSet.getMetaData();
        //Coding to get columns-
        int cols=rsmd.getColumnCount();
        String c[]=new String[cols];
        for(int i=0;i<cols;i++){
            c[i]=rsmd.getColumnName(i+1);
            dm.addColumn(c[i]);
        }


        //get data from rows
        Object row[]=new Object[cols];
        while(resultSet.next()){
             for(int i=0;i<cols;i++){
                    row[i]=resultSet.getString(i+1);
                }
            dm.addRow(row);
        }
        t1.setModel(dm);
        con.close();
        return t1;
    }
}
