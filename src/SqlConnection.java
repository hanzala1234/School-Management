/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import javax.swing.*;
import java.text.*;
/**
 *
 * @author Hanzala
 */
public class SqlConnection {
    Connection connect;
     SqlConnection(){
        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/school_management","root","hanzala12");
        } catch (SQLException ex) {
            Logger.getLogger(SqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
     
     }
     public int InsertData(String name ,String F_Name,String Gender,String phone,String f_Phone,java.sql.Date birth,String Class,String Sec,String adress){
    PreparedStatement ps;
        try {
            
            boolean done = false;
            ps = connect.prepareStatement("Insert into student_data value(null,?,?,?,?,?,?,?,?,?,now())",Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setString(2, F_Name); 
            ps.setString(3, Gender);
            ps.setString(4, phone);
            ps.setString(5, f_Phone);
            ps.setDate(6, birth);
            ps.setString(7, Class);
            ps.setString(8, Sec);
            ps.setString(9, adress);
            int r =ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            while(rs.next()){
            return rs.getInt(1);
            }
                    
               
                    
                    } catch (SQLException ex) {
            Logger.getLogger(SqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
     
     
     return -1;
     
     }
     
public String[] getData(int grNo){
    int found_rows=-1;    
    String[] info = new String[0];
    String name = "",F_Name="",Gender="",phone="",f_Phone="",Class="",Section="",adress="";
    try {
        
            java.util.Date today  = new java.util.Date();
            PreparedStatement ps = connect.prepareStatement("Select * from student_data where Gr_No=?");
            ps.setInt(1, grNo);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                info = new String[11];
                found_rows++;
            info[0]= rs.getString(2);
            info[1]= rs.getString(3);
            info[2]= rs.getString(4);
            info[3]= rs.getString(5);
            info[4]= rs.getString(6);
            info[5]= rs.getDate(7).toString();
            info[6]= rs.getString(8);
            info[7]= rs.getString(9);
            info[8]= rs.getString(10);
         java.sql.Date feeDate  = rs.getDate(11);
         int months = (today.getMonth()-feeDate.getMonth())+(today.getYear()-feeDate.getYear())*12;
         info[9]=String.valueOf(months);
         feeDate.setMonth(feeDate.getMonth()+1);
        info[10] = (new DateFormatSymbols()).getMonths()[feeDate.getMonth()]+"-"+String.valueOf((feeDate.getYear())+1900);
         
            
            return info;
            }
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(SqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

return info;
}  
public int UpdateData(int Gr_No,String name ,String F_Name,String Gender,String phone,String f_Phone,java.sql.Date birth,String Class,String Sec,String adress) {
    int d =0;    
    try {
            PreparedStatement ps = connect.prepareStatement("Update student_data set Name=?,F_Name = ? ,Gender=?,HomePh_No=?,Father_No=?,Birth_Date=?,Class=?,Section=?,Adress=? Where Gr_No=?");
            ps.setString(1, name);
            ps.setString(2, F_Name);
       ps.setString(3, Gender);
       ps.setString(4, phone);
       ps.setString(5, f_Phone);
       ps.setDate(6, birth);
       ps.setString(7, Class);
       ps.setString(8, Sec);
       ps.setString(9, adress);
       ps.setInt(10, Gr_No);
        d =ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
return d;
}
public int getFee(String Class){
    int amount = 0;
        try {
            
       PreparedStatement ps = connect.prepareStatement("Select Amount from fee_structure Where Class =?");
       ps.setString(1, Class);
       ResultSet rs = ps.executeQuery();
       while(rs.next()){
       amount=rs.getInt(1);
       }
        } catch (SQLException ex) {
       
        }

return amount;
}

public boolean feePay(int GrNo){
    boolean paid = false;    
    try {
            
            java.sql.Date feeDate =null;
            PreparedStatement ps = connect.prepareStatement("Select Fee_Paid from student_data where Gr_No = "+GrNo);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            feeDate = rs.getDate("Fee_paid");
            }
            feeDate.setMonth(feeDate.getMonth()+1);
           PreparedStatement s = connect.prepareStatement("Update student_data set fee_paid = ? Where Gr_No ="+GrNo);
           s.setDate(1, feeDate);
           int rows = s.executeUpdate();
           paid = rows>0?true:false;
            
        } catch (SQLException ex) {
            Logger.getLogger(SqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

return paid;
}
public boolean Remove_Student(int grno) {
        try {
            Statement e  = connect.createStatement();
            int l=e.executeUpdate("Delete from student_data where Gr_No ="+grno);
            if(l>0)return true;
        } catch (SQLException ex) {
            Logger.getLogger(SqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
return false;
}
public int Fee_Payment(int grno){
PreparedStatement ps;
        try {
            java.sql.Date paiddate =null;
            String Class="";
            ps=connect.prepareStatement("Select Fee_paid,Class from student_data where Gr_No="+grno);
            ResultSet rs ;
            rs= ps.executeQuery();
         
            while(rs.next()){
            paiddate = rs.getDate("Fee_paid");
            Class=rs.getString("Class");
            }
            if(paiddate!=null){
paiddate.setMonth(paiddate.getMonth()+1);//  to pay fee  of the next month
                ps = connect.prepareStatement("Select * from fee_schedule Where Start_From Between ? AND ?");
                
                paiddate.setYear(paiddate.getYear());
                ps.setDate(2, paiddate);
                paiddate.setYear(paiddate.getYear()-1);
                ps.setDate(1, paiddate);
                rs=ps.executeQuery();
                int amount =0;
                while(rs.next()){
                amount = rs.getInt(Class);
                }
                return amount;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

return 0;
}


}
