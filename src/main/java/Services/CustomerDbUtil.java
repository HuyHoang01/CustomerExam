/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import JDBC.DatabaseConnectionPoolManager;
import Models.Customer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author PC
 */
public class CustomerDbUtil {
    private DataSource dataSource;
    private DatabaseConnectionPoolManager dbcpm;
   
    public CustomerDbUtil(DatabaseConnectionPoolManager dbcpm){
//        this.dataSource=dataSource;
        this.dbcpm=dbcpm;
    }
    
    private void close(Connection conn, PreparedStatement pstm, ResultSet rs){
        if(rs!=null)try{
                rs.close();
            }catch(SQLException e){
                //ignore
            }
            
            if(pstm!=null)try{
                pstm.close();
            }catch(SQLException e){
                //ignore
            }
            
            if(conn!=null)try{
                conn.close();
            }catch(SQLException e){
                //ignore
            }
    }
    
    public List<Customer> getCustomersByName(String name) throws Exception{
        List<Customer> customers=new ArrayList<Customer>();
        
        Connection conn=null;
        PreparedStatement pstm=null;
        ResultSet rs=null;
        
        try{
            conn=dbcpm.getConnection();
            
//            conn=dataSource.getConnection();
            
            String sql="SELECT * FROM customer WHERE name LIKE ?";
            pstm=conn.prepareStatement(sql);
            pstm.setString(1, "%"+name+"%");
            
            rs=pstm.executeQuery();
            
            while(rs.next()){
                int tempId=rs.getInt("id");
                String tempName=rs.getString("name");
                int tempAge=rs.getInt("age");
                String tempAddress=rs.getString("address");
                
                Customer tempCus=new Customer(tempId, tempName, tempAge, tempAddress);
                customers.add(tempCus);
            }
            return customers;
        }finally{
            close(conn, pstm, rs);
        }
    }
}
