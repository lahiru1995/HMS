/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Log;

/**
 *
 * @author lenovo
 */
 import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import static java.awt.Color.red;
import static java.awt.Color.white;
import java.awt.Font;
import java.awt.Image;
import static java.awt.Image.SCALE_SMOOTH;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import static java.util.Locale.filter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
//import javax.swing.table.DefaultTableModel;

public class main extends javax.swing.JFrame {
    JFileChooser fileChooser = null;
      FileNameExtensionFilter filter = null;
     private DefaultTableModel defaultTableModel;
    private static Statement st;
    // private static Statement st1;
    // Statement st;
     
     //private static Statement st1;
     BufferedImage im;
    // String filename = null; 
    // byte[] stuimage = null;
     ByteArrayOutputStream img = new ByteArrayOutputStream();
    // byte[] pic;
    PreparedStatement pst=null;
    
   PreparedStatement pst1=null;

//String ee;

    /**
     * Creates new form main
     */
    public main() {
        //this.ee = (String) jComboBox3.getSelectedItem();
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/images/hotel.png")).getImage());
      
        setExtendedState(MAXIMIZED_BOTH);
        initComponents();
         jPanel1.setVisible(true);
         jPanel2.setVisible(true);
            jPanel4.setVisible(false);
            jPanel5.setVisible(false);
             jPanel12.setVisible(false);
             jPanel17.setVisible(false);
              jPanel21.setVisible(false);
               jPanel22.setVisible(false);
            // st=connect.connection();
              st=connect.connection();
             // st1=connect.connection();
            String ee = (String) jComboBox3.getSelectedItem();
             String d = java.time.LocalDate.now().toString();
        date.setText(d);
            // showUser2();
            
              fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        filter = new FileNameExtensionFilter("*.IMAGE", "jpg","gif","png");
        
        
       
         //jPanel2.setVisible(true);
    }
   
    
    ///////////////////////////////////////////////////////////////////////////////
     public ArrayList<kuruvita> kuruvitaList(){
        
        ArrayList<kuruvita> kuruvitaList = new ArrayList<>();
        try{
          //Class.forName("org.sqlite.JDBC");
          //String url = "jdbc:sqlite:student.db";
          // Connection con1 = DriverManager.getConnection(url);
           String query11 = "SELECT * FROM "+gethos()+" WHERE roomnumber = (SELECT MAX(roomnumber) FROM "+gethos()+")";
          //st =  con1.createStatement();
           ResultSet rs1 = st.executeQuery(query11);
           kuruvita user1;
           while(rs1.next()){
               user1 = new kuruvita(rs1.getInt("roomnumber"),rs1.getInt("bed1"),rs1.getInt("bed2"),rs1.getInt("bed3"),rs1.getInt("bed4"));
               kuruvitaList.add(user1);
           }
           
        }catch(Exception ex){  
            JOptionPane.showMessageDialog(null, "error1");
        }finally
{
       try
       {
         st.close();
        
       }
       catch(Exception e)
       {
          JOptionPane.showMessageDialog(null,e );
       }
} 
        return kuruvitaList;
    }
     
//method for show max filled room number
public void showUser2(){
       ins.setEnabled(true);
       //String x = null;
        ArrayList<kuruvita> List = kuruvitaList();
        DefaultTableModel model = (DefaultTableModel) table2.getModel();
        model.setRowCount(0);
        Object[] row = new Object[5];
        for(int i=0;i<List.size();i++){
            row[0] = List.get(i).getroom();
            row[1] = List.get(i).getbed1();
            row[2] = List.get(i).getbed2();
            row[3] = List.get(i).getbed3();
            row[4] = List.get(i).getbed4();
            model.addRow(row);
            
           
        }
        
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public ArrayList<student> detailsList(){
     ArrayList<student> detailsList = new ArrayList<>();
    try{
        
    //Class.forName("org.sqlite.JDBC");
    //String url = "jdbc:sqlite:student.db";
    //Connection con2 = DriverManager.getConnection(url);
           String query111 = "SELECT * FROM details WHERE hostel = '"+gethos1()+"'";
    //st =  con2.createStatement();
           ResultSet rs1 = st.executeQuery(query111);
           student user1;
           while(rs1.next()){
               user1 = new student(rs1.getInt("indexNo"),rs1.getString("name"),rs1.getString("address"),rs1.getString("phone"),rs1.getString("email"),rs1.getString("parent_name"),rs1.getString("parent_phone"),rs1.getString("gender"),rs1.getString("religion"),rs1.getString("faculty"),rs1.getInt("year"),rs1.getBytes("pic"),rs1.getInt("room"),rs1.getInt("bed"),rs1.getString("regdate"),rs1.getString("vmodel"),rs1.getString("vnumber"));
               detailsList.add(user1);
              
           }
            }catch(Exception ex){  
            JOptionPane.showMessageDialog(null, "error2");
        }
    finally
{
       try
       {
         st.close();
       }
       catch(Exception e)
       {
          JOptionPane.showMessageDialog(null,e );
       } finally
{
       try
       {
         st.close();
        
       }
       catch(Exception e)
       {
          JOptionPane.showMessageDialog(null,e );
       }
} 
} 
    return detailsList;
}


//indexNo,name,address,phone,email,parent_name,parent_phone,gender,religion,faculty,year,room,bed,regdate,vmodel,vnumber,hostel
//show hostel details
public void showUser3(){
      // ins.setEnabled(true);
       //String x = null;
        ArrayList<student> List = detailsList();
        DefaultTableModel model1 = (DefaultTableModel) tablem.getModel();
        model1.setRowCount(0);
        Object[] row = new Object[16];
        for(int i=0;i<List.size();i++){
            row[0] = List.get(i).getindexNo();
            row[1] = List.get(i).getname();
            row[2] = List.get(i).getaddress();
             row[3] = List.get(i).getphone();
            row[4] = List.get(i).getemail();
            row[5] = List.get(i).getparent_name();
            row[6] = List.get(i).getparent_phone();
            row[7] = List.get(i).getgender();
            row[8] = List.get(i).getreligion();
            row[9] = List.get(i).getfaculty();
            row[10] = List.get(i).getyear();
           // row[10] = List.get(i).getpic();
             row[11] = List.get(i).getroom();
            row[12] = List.get(i).getbed();
            row[13] = List.get(i).getdate();
            row[14] = List.get(i).getvmodel();
             row[15] = List.get(i).getvnum();
           // row[16] = List.get(i).gethostel();
            //row[17] = List.get(i).getdate();
            
            model1.addRow(row);
            
           
        }
        
    }

public ArrayList<student> vehiclelList(){
     ArrayList<student> vehiclelList = new ArrayList<>();
      String query12 = "SELECT * FROM details WHERE hostel = '"+gethos3()+"' AND vnumber != ''";
        try {
            //st =  con1.createStatement();
            ResultSet rs1 = st.executeQuery(query12);
             student user1;
           while(rs1.next()){
                user1 = new student(rs1.getInt("indexNo"),rs1.getString("name"),rs1.getString("address"),rs1.getString("phone"),rs1.getString("email"),rs1.getString("parent_name"),rs1.getString("parent_phone"),rs1.getString("gender"),rs1.getString("religion"),rs1.getString("faculty"),rs1.getInt("year"),rs1.getBytes("pic"),rs1.getInt("room"),rs1.getInt("bed"),rs1.getString("regdate"),rs1.getString("vmodel"),rs1.getString("vnumber"));
              vehiclelList.add(user1);
           }
        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vehiclelList;
}
public void showvehicle(){
      // ins.setEnabled(true);
       //String x = null;
        ArrayList<student> List = vehiclelList();
        DefaultTableModel model1 = (DefaultTableModel) jTable1.getModel();
        model1.setRowCount(0);
        Object[] row = new Object[7];
        for(int i=0;i<List.size();i++){
            row[0] = List.get(i).getvnum();
            row[1] = List.get(i).getvmodel();
            row[2] = List.get(i).getindexNo();
            row[3] = List.get(i).getname();
            row[4] = List.get(i).getfaculty();
             row[5] = List.get(i).getyear();
            
             row[6] = List.get(i).getphone();
             
           
            
            model1.addRow(row);
            
           
        }
        
    }
//search vehicle from number
public ArrayList<student> autovehiclelList(){
     ArrayList<student> autovehiclelList = new ArrayList<>();
      String query112 = "SELECT * FROM details WHERE hostel = '"+gethos3()+"' AND vnumber != '' AND vnumber LIKE '"+veh.getText()+"%'";
        try {
            //st =  con1.createStatement();
            ResultSet rs1 = st.executeQuery(query112);
             student user1;
           while(rs1.next()){
                user1 = new student(rs1.getInt("indexNo"),rs1.getString("name"),rs1.getString("address"),rs1.getString("phone"),rs1.getString("email"),rs1.getString("parent_name"),rs1.getString("parent_phone"),rs1.getString("gender"),rs1.getString("religion"),rs1.getString("faculty"),rs1.getInt("year"),rs1.getBytes("pic"),rs1.getInt("room"),rs1.getInt("bed"),rs1.getString("regdate"),rs1.getString("vmodel"),rs1.getString("vnumber"));
              autovehiclelList.add(user1);
           }
        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return autovehiclelList;
}
public void autoshowvehicle(){
      // ins.setEnabled(true);
       //String x = null;
        ArrayList<student> List = autovehiclelList();
        DefaultTableModel model1 = (DefaultTableModel) jTable1.getModel();
        model1.setRowCount(0);
        Object[] row = new Object[7];
        for(int i=0;i<List.size();i++){
            row[0] = List.get(i).getvnum();
            row[1] = List.get(i).getvmodel();
            row[2] = List.get(i).getindexNo();
            row[3] = List.get(i).getname();
            row[4] = List.get(i).getfaculty();
             row[5] = List.get(i).getyear();
            
             row[6] = List.get(i).getphone();
             
           
            
            model1.addRow(row);
            
           
        }
        
    }

public ArrayList<student> autodetailsList(){
     ArrayList<student> autodetailsList = new ArrayList<>();
     try{
      //Class.forName("org.sqlite.JDBC");
          // String url = "jdbc:sqlite:student.db";
          // Connection con2 = DriverManager.getConnection(url);
           String query121 = "SELECT * FROM details WHERE indexNo LIKE '"+search.getText()+"%' AND hostel = '"+gethos1()+"'";
           // st =  con2.createStatement();
           ResultSet rs1 = st.executeQuery(query121);
           student user1;
           while(rs1.next()){
               user1 = new student(rs1.getInt("indexNo"),rs1.getString("name"),rs1.getString("address"),rs1.getString("phone"),rs1.getString("email"),rs1.getString("parent_name"),rs1.getString("parent_phone"),rs1.getString("gender"),rs1.getString("religion"),rs1.getString("faculty"),rs1.getInt("year"),rs1.getBytes("pic"),rs1.getInt("room"),rs1.getInt("bed"),rs1.getString("regdate"),rs1.getString("vmodel"),rs1.getString("vnumber"));
               autodetailsList.add(user1);
           }
           }catch(Exception ex){  
            JOptionPane.showMessageDialog(null, "error auto");
        }
     finally
{
       try
       {
         st.close();
        
       }
       catch(Exception e)
       {
          JOptionPane.showMessageDialog(null,e );
       }
} 
     return autodetailsList;
}
public void autoshowUser3(){
     ArrayList<student> List = autodetailsList();
        DefaultTableModel model1 = (DefaultTableModel) tablem.getModel();
        model1.setRowCount(0);
         Object[] row = new Object[16];
        for(int i=0;i<List.size();i++){
            row[0] = List.get(i).getindexNo();
            row[1] = List.get(i).getname();
            row[2] = List.get(i).getaddress();
             row[3] = List.get(i).getphone();
            row[4] = List.get(i).getemail();
            row[5] = List.get(i).getparent_name();
            row[6] = List.get(i).getparent_phone();
            row[7] = List.get(i).getgender();
            row[8] = List.get(i).getreligion();
            row[9] = List.get(i).getfaculty();
            row[10] = List.get(i).getyear();
           // row[10] = List.get(i).getpic();
             row[11] = List.get(i).getroom();
            row[12] = List.get(i).getbed();
            row[13] = List.get(i).getdate();
            row[14] = List.get(i).getvmodel();
             row[15] = List.get(i).getvnum();
           // row[16] = List.get(i).gethostel();
            //row[17] = List.get(i).getdate();
            
            model1.addRow(row);
        }
}


public void search(){
    try{
    //Class.forName("org.sqlite.JDBC");
          // String url = "jdbc:sqlite:student.db";
          // Connection con2 = DriverManager.getConnection(url);
   String query151 = "SELECT * FROM details WHERE indexNo = '"+search.getText()+"'";
   // st =  con2.createStatement();
     ResultSet rs1 = st.executeQuery(query151);
     if(rs1.next()){
         sname.setText(rs1.getString("name"));
         ind.setText(""+rs1.getInt("indexNo")+"");
         add.setText(rs1.getString("address"));
         pho.setText(""+rs1.getString("phone")+"");
         ema.setText(rs1.getString("email"));
         pname.setText(rs1.getString("parent_name"));
         ppho.setText(""+rs1.getString("parent_phone")+"");
         gen.setText(rs1.getString("gender"));
         rel.setText(rs1.getString("religion"));
         fac.setText(rs1.getString("faculty"));
         yea.setText(""+rs1.getInt("year")+"");
         regd.setText(rs1.getString("regdate"));
         rnum.setText(""+rs1.getInt("room")+"");
         bl.setText(""+rs1.getInt("bed")+"");
         vm.setText(rs1.getString("vmodel"));
         vn.setText(rs1.getString("vnumber"));
         hname.setText(rs1.getString("hostel"));
           
       
    /////////////////////////////////////////////////////////////////////////
         byte[] img1 = rs1.getBytes("pic");
         if(img1 == null){
            pi.setIcon(new ImageIcon("G:\\Hostel Management project\\HOSTEL MANAGEMENT SYSTEM\\src\\images\\blank.png"));
         }else{
          ImageIcon icon = new ImageIcon(img1);
          Image  ima = icon.getImage();
          Image myim = ima.getScaledInstance(pi.getWidth(), pi.getHeight(), SCALE_SMOOTH);
          ImageIcon newicon = new ImageIcon(myim);
          pi.setIcon(newicon);
         }
         // yy.setText(img1.toString());
           
           // pi.setIcon(new ImageIcon(i.getScaledInstance(pic.getWidth(), pic.getHeight(), 1)));
       //pi.setIcon(new ImageIcon(i));
       
     }else{
         JOptionPane.showMessageDialog(null, "Invalid index number or unregistered index number, Please check!");
     }
    }catch(Exception ex){  
            JOptionPane.showMessageDialog(null, "error3");
        }finally
{
       try
       {
         st.close();
        
       }
       catch(Exception e)
       {
          JOptionPane.showMessageDialog(null,e );
       }
} 
}

public ArrayList<kuruvita> hosteldetail(){
    ArrayList<kuruvita> hostelList = new ArrayList<>();
    try{ //Class.forName("org.sqlite.JDBC");
           // String url = "jdbc:sqlite:student.db";
          // Connection con2 = DriverManager.getConnection(url);
           String query171 = "SELECT * FROM "+gethos2()+"";
           // st =  con2.createStatement();
           ResultSet rs1 = st.executeQuery(query171);
           kuruvita user1;
           while(rs1.next()){
               user1 = new kuruvita(rs1.getInt("roomnumber"),rs1.getInt("bed1"),rs1.getInt("bed2"),rs1.getInt("bed3"),rs1.getInt("bed4"));
               hostelList.add(user1);
           }
            }catch(Exception ex){  
            JOptionPane.showMessageDialog(null, "error1");
        }
     return hostelList;
}
  /*public ArrayList<kuruvita> hosteldetail1(){
        
        ArrayList<kuruvita> hostelList = new ArrayList<>();
        try{
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:student.db";
           Connection con1 = DriverManager.getConnection(url);
           String query11 = "SELECT * FROM "+gethos2()+"";
           st =  con1.createStatement();
           ResultSet rs1 = st.executeQuery(query11);
           kuruvita user1;
           while(rs1.next()){
               user1 = new kuruvita(rs1.getInt("roomnumber"),rs1.getInt("bed1"),rs1.getInt("bed2"),rs1.getInt("bed3"),rs1.getInt("bed4"));
               hostelList.add(user1);
           }
           
        }catch(Exception ex){  
            JOptionPane.showMessageDialog(null, "error1");
        }
        return hostelList;
    }*/
  public void showroom(){
      // ins.setEnabled(true);
       //String x = null;
        ArrayList<kuruvita> List = hosteldetail();
        DefaultTableModel model = (DefaultTableModel) tableroom.getModel();
        model.setRowCount(0);
        Object[] row = new Object[5];
        for(int i=0;i<List.size();i++){
            row[0] = List.get(i).getroom();
            row[1] = List.get(i).getbed1();
            row[2] = List.get(i).getbed2();
            row[3] = List.get(i).getbed3();
            row[4] = List.get(i).getbed4();
            model.addRow(row);
           
           
        }
        
    }
  
  public ArrayList<visiters> visiterlList(){
     ArrayList<visiters> visiterlList = new ArrayList<>();
      String query122 = "SELECT * FROM visiters";
        try {
            //st =  con1.createStatement();
            ResultSet rs1 = st.executeQuery(query122);
             visiters user1;
           while(rs1.next()){
                user1 = new visiters(rs1.getInt("vid"),rs1.getString("name"),rs1.getString("nic"),rs1.getString("resp"),rs1.getString("indate"),rs1.getString("outdate"),rs1.getString("hostel"),rs1.getInt("roomno"),rs1.getString("discrip"));
              visiterlList.add(user1);
           }
        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return visiterlList;
}
   public void showvisiters(){
      // ins.setEnabled(true);
       //String x = null;
        ArrayList<visiters> List = visiterlList();
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);
        Object[] row = new Object[9];
        for(int i=0;i<List.size();i++){
            row[0] = List.get(i).getvid();
            row[1] = List.get(i).getvisitername();
            row[2] = List.get(i).getnic();
            row[3] = List.get(i).getresperson();
            row[4] = List.get(i).getindate();
            row[5] = List.get(i).getoutdate();
             row[6] = List.get(i).getvisiterhos();
            row[7] = List.get(i).getvisiterroom();
            row[8] = List.get(i).getdiscrip();
            
            model.addRow(row);
           
           
        }
        
    }
   
   
   public ArrayList<fines> finesList(){
     ArrayList<fines> fineList = new ArrayList<>();
      String queryf122 = "SELECT * FROM fines";
        try {
            //st =  con1.createStatement();
            ResultSet frs1 = st.executeQuery(queryf122);
             fines user1;
           while(frs1.next()){
                user1 = new fines(frs1.getInt("iin"),frs1.getInt("roomN"),frs1.getInt("fine"),frs1.getString("fdate"),frs1.getString("fac"),frs1.getString("hostel"),frs1.getInt("year"),frs1.getString("discrip"));
              fineList.add(user1);
           }
        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fineList;
}
   
    public void showfines(){
      // ins.setEnabled(true);
       //String x = null;
        ArrayList<fines> List = finesList();
        DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
        model.setRowCount(0);
        Object[] row = new Object[8];
        for(int i=0;i<List.size();i++){
            row[0] = List.get(i).getind();
            row[1] = List.get(i).getroomN();
            row[2] = List.get(i).getfine();
            row[3] = List.get(i).getfdate();
            row[4] = List.get(i).getfac();
            row[5] = List.get(i).getfhos();
             row[6] = List.get(i).getfyear();
            row[7] = List.get(i).getdiscrip();
           
            
            model.addRow(row);
           
           
        }
        
    }
    
     public ArrayList<fines> autofinesList(){
     ArrayList<fines> fineList1 = new ArrayList<>();
      String queryff122 = "SELECT * FROM fines WHERE iin LIKE '"+jTextField1.getText()+"%' ";
        try {
            //st =  con1.createStatement();
            ResultSet frsf1 = st.executeQuery(queryff122);
             fines user1;
           while(frsf1.next()){
                user1 = new fines(frsf1.getInt("iin"),frsf1.getInt("roomN"),frsf1.getInt("fine"),frsf1.getString("fdate"),frsf1.getString("fac"),frsf1.getString("hostel"),frsf1.getInt("year"),frsf1.getString("discrip"));
              fineList1.add(user1);
           }
        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fineList1;
}
     public void autoshowfines(){
      // ins.setEnabled(true);
       //String x = null;
        ArrayList<fines> List = autofinesList();
        DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
        model.setRowCount(0);
        Object[] row = new Object[8];
        for(int i=0;i<List.size();i++){
            row[0] = List.get(i).getind();
            row[1] = List.get(i).getroomN();
            row[2] = List.get(i).getfine();
            row[3] = List.get(i).getfdate();
            row[4] = List.get(i).getfac();
            row[5] = List.get(i).getfhos();
             row[6] = List.get(i).getfyear();
            row[7] = List.get(i).getdiscrip();
           
            
            model.addRow(row);
           
           
        }
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        jButton38 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        name = new javax.swing.JTextField();
        index = new javax.swing.JTextField();
        address = new javax.swing.JTextField();
        phone = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        parentname = new javax.swing.JTextField();
        parentphone = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        faculty = new javax.swing.JComboBox<>();
        gender = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        year = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        religon = new javax.swing.JComboBox<>();
        pic1 = new javax.swing.JLabel();
        jButton16 = new javax.swing.JButton();
        jLabel57 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        table2 = new javax.swing.JTable();
        date = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        bed = new javax.swing.JTextField();
        room = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel10 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        vmodel = new javax.swing.JTextField();
        vnumber = new javax.swing.JTextField();
        ins = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        msg = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        search = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        tablemain = new javax.swing.JScrollPane();
        tablem = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        sname = new javax.swing.JTextField();
        ind = new javax.swing.JTextField();
        add = new javax.swing.JTextField();
        ema = new javax.swing.JTextField();
        pname = new javax.swing.JTextField();
        ppho = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        gen = new javax.swing.JTextField();
        rel = new javax.swing.JTextField();
        fac = new javax.swing.JTextField();
        yea = new javax.swing.JTextField();
        pho = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        pi = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        rnum = new javax.swing.JTextField();
        bl = new javax.swing.JTextField();
        vm = new javax.swing.JTextField();
        vn = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        regd = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        hname = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jLabel47 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        avi2 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        avi = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        avi1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableroom = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        searchrn = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jComboBox13 = new javax.swing.JComboBox<>();
        r = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        b1 = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        b2 = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        b3 = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        b4 = new javax.swing.JTextField();
        up1 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        vnu = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        vmo = new javax.swing.JTextField();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel15 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        veh = new javax.swing.JTextField();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel53 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel19 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        vroom = new javax.swing.JTextField();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel60 = new javax.swing.JLabel();
        visnam = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        nic = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        resp = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        dis = new javax.swing.JTextArea();
        jButton13 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jLabel56 = new javax.swing.JLabel();
        vid = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jButton40 = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jButton31 = new javax.swing.JButton();
        in1 = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        in2 = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jButton32 = new javax.swing.JButton();
        faculty1 = new javax.swing.JComboBox<>();
        jComboBox6 = new javax.swing.JComboBox<>();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel71 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jButton33 = new javax.swing.JButton();
        jComboBox7 = new javax.swing.JComboBox<>();
        jLabel77 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jButton34 = new javax.swing.JButton();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jDateChooser4 = new com.toedter.calendar.JDateChooser();
        jLabel78 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jLabel75 = new javax.swing.JLabel();
        jButton35 = new javax.swing.JButton();
        in3 = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        in4 = new javax.swing.JTextField();
        jComboBox8 = new javax.swing.JComboBox<>();
        jLabel76 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jButton39 = new javax.swing.JButton();
        jLabel83 = new javax.swing.JLabel();
        jComboBox10 = new javax.swing.JComboBox<>();
        jPanel22 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel28 = new javax.swing.JPanel();
        jLabel73 = new javax.swing.JLabel();
        jComboBox9 = new javax.swing.JComboBox<>();
        jLabel74 = new javax.swing.JLabel();
        visnam1 = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        roomN = new javax.swing.JTextField();
        jLabel80 = new javax.swing.JLabel();
        fine = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        dis1 = new javax.swing.JTextArea();
        jButton27 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        vid1 = new javax.swing.JLabel();
        jDateChooser5 = new com.toedter.calendar.JDateChooser();
        faculty2 = new javax.swing.JComboBox<>();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        year1 = new javax.swing.JComboBox<>();
        jPanel29 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel82 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(1360, 730));
        setSize(new java.awt.Dimension(0, 0));

        jPanel1.setBackground(new java.awt.Color(0, 102, 0));

        jButton1.setBackground(new java.awt.Color(0, 0, 153));
        jButton1.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 51, 153));
        jButton1.setText("Register Student");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 0, 153));
        jButton2.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 153));
        jButton2.setText("View Student Details");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 0, 153));
        jButton3.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 0, 153));
        jButton3.setText("View Hostel Details");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 0, 153));
        jButton4.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 0, 153));
        jButton4.setText("View Vehicle Details");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(0, 0, 153));
        jButton9.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jButton9.setForeground(new java.awt.Color(0, 0, 153));
        jButton9.setText("Visiters");
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton9MouseClicked(evt);
            }
        });
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton26.setBackground(new java.awt.Color(0, 0, 102));
        jButton26.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jButton26.setForeground(new java.awt.Color(0, 0, 102));
        jButton26.setText("Reports");
        jButton26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton26MouseClicked(evt);
            }
        });
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        jButton37.setBackground(new java.awt.Color(0, 0, 153));
        jButton37.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jButton37.setForeground(new java.awt.Color(0, 0, 102));
        jButton37.setText("Fines");
        jButton37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton37MouseClicked(evt);
            }
        });
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });

        jButton38.setBackground(new java.awt.Color(0, 0, 102));
        jButton38.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jButton38.setForeground(new java.awt.Color(0, 0, 102));
        jButton38.setText("Admins");
        jButton38.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton38MouseClicked(evt);
            }
        });
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton26, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton37, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton38, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton9)
                    .addComponent(jButton26)
                    .addComponent(jButton37)
                    .addComponent(jButton38))
                .addGap(5, 5, 5))
        );

        jPanel2.setBackground(new java.awt.Color(213, 255, 251));
        jPanel2.setAutoscrolls(true);
        jPanel2.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Personal Details"));

        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });
        name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nameKeyPressed(evt);
            }
        });

        index.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                indexActionPerformed(evt);
            }
        });
        index.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                indexKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                indexKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                indexKeyTyped(evt);
            }
        });

        address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addressActionPerformed(evt);
            }
        });

        phone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneActionPerformed(evt);
            }
        });
        phone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                phoneKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                phoneKeyTyped(evt);
            }
        });

        email.setAutoscrolls(false);
        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });

        parentname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parentnameActionPerformed(evt);
            }
        });
        parentname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                parentnameKeyPressed(evt);
            }
        });

        parentphone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parentphoneActionPerformed(evt);
            }
        });
        parentphone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                parentphoneKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                parentphoneKeyTyped(evt);
            }
        });

        jLabel3.setText("Student Name");

        jLabel8.setText("Index Number");

        jLabel4.setText("Address");

        jLabel5.setText("Phone Number");

        jLabel6.setText("Email");

        jLabel7.setText("Parent Name");

        jLabel9.setText("Parent Phone ");

        jLabel10.setText("Faculty");

        jLabel19.setText("Gender");

        faculty.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FAS", "BSF", "FOT", " " }));

        gender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "male", "female" }));

        jLabel20.setText("Year");

        year.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 st yaer", "2 nd year", "3 rd year", "4 th year", " " }));

        jLabel21.setText("Religon");

        religon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sinhala", "Hindusm", "Muslim" }));

        pic1.setBackground(new java.awt.Color(0, 51, 153));
        pic1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b1.jpg"))); // NOI18N
        pic1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton16.setText("Uplord photo");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jLabel57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/v.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(parentname, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(email, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(phone, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(address, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(index, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(parentphone)
                            .addComponent(name, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pic1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(religon, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(gender, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(faculty, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(2, 2, 2)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(name)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(index, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(parentname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(pic1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton16))
                        .addGap(11, 11, 11))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(parentphone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(gender)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(religon)
                            .addComponent(jLabel21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(faculty)
                            .addComponent(jLabel10))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(year)
                            .addComponent(jLabel20)))
                    .addComponent(jLabel57, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        jPanel9.setBackground(new java.awt.Color(204, 255, 204));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Enter Room Details"));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select hostel", "kuruvita", "panduwasdew1", "chithradevi" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jLabel24.setText("Add student to a room, Locker & Bed Number :");

        jLabel25.setText("Register Date :");

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        table2.setBackground(new java.awt.Color(204, 255, 255));
        table2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "room No", "  bed 1", "  bed 2", "  bed 3", "  bed 4"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        table2.setGridColor(new java.awt.Color(255, 255, 255));
        table2.setRowSelectionAllowed(false);
        jScrollPane1.setViewportView(table2);
        if (table2.getColumnModel().getColumnCount() > 0) {
            table2.getColumnModel().getColumn(0).setMaxWidth(60);
            table2.getColumnModel().getColumn(1).setMaxWidth(60);
            table2.getColumnModel().getColumn(2).setMaxWidth(60);
            table2.getColumnModel().getColumn(3).setMaxWidth(60);
            table2.getColumnModel().getColumn(4).setMaxWidth(60);
        }

        jScrollPane3.setViewportView(jScrollPane1);

        date.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateActionPerformed(evt);
            }
        });

        jButton6.setText("next room");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton17.setText("2");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setText("3");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton19.setText("4");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton20.setText("1");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jLabel23.setForeground(new java.awt.Color(51, 51, 255));
        jLabel23.setText("This table shows available room and bed");

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 51, 204));
        jLabel34.setText("First Select a Hostel :");

        jLabel1.setText("Room No :");

        jLabel35.setText("Bed & Locker No :");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel23)
                        .addGap(124, 124, 124))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addGap(30, 30, 30)
                                .addComponent(date))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addGap(18, 18, 18)
                                .addComponent(bed))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(53, 53, 53)
                                .addComponent(room))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jButton20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel9Layout.createSequentialGroup()
                                                .addComponent(jLabel34)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox3)
                    .addComponent(jLabel34))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(room, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bed)
                    .addComponent(jLabel35))
                .addGap(24, 24, 24))
        );

        jPanel10.setBackground(new java.awt.Color(204, 255, 204));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Register Student Vehicles"));

        jLabel26.setText("Vehicle Model  :");

        jLabel27.setBackground(new java.awt.Color(204, 255, 204));
        jLabel27.setText("Number   :");

        vmodel.setText(" ");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vmodel)
                    .addComponent(vnumber))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vmodel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vnumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        ins.setBackground(new java.awt.Color(0, 0, 204));
        ins.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ins.setForeground(new java.awt.Color(0, 0, 204));
        ins.setText("-----Submit All Details-----");
        ins.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insActionPerformed(evt);
            }
        });

        jButton10.setText("Reset");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        msg.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        msg.setForeground(new java.awt.Color(0, 102, 0));
        msg.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel59.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/WEL_1.png"))); // NOI18N
        jLabel59.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel85.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(0, 204, 153));
        jLabel85.setText("HOSTEL MANAGEMENT SYSTEM,   WAYAMBA UNIVERSITY OF SRI LANKA,   KULIYAPITIYA PREMISES");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel59, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(msg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ins, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4))
                            .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jSeparator3)
                        .addGap(3, 3, 3)
                        .addComponent(jLabel85)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)))
                .addGap(10, 10, 10))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ins, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(msg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(2, 2, 2)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel85)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(204, 255, 255));

        jLabel2.setText("Search form All Hostels by Index No :");

        search.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                searchInputMethodTextChanged(evt);
            }
        });
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchKeyTyped(evt);
            }
        });

        jButton7.setText("Search");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        tablemain.setBackground(new java.awt.Color(255, 255, 255));
        tablemain.setMinimumSize(new java.awt.Dimension(650, 550));
        tablemain.setName(""); // NOI18N

        tablem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IndexNo", "Name", "Address", "Phone No", "Email", "Parent Name", "Parent Phone", "Gender", "Religion", "Faculty", "Year", "Room No", "Bed No", "Reg date", "Vehicle Model", "Vihicle No"
            }
        ));
        tablem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablemMouseClicked(evt);
            }
        });
        tablemain.setViewportView(tablem);

        jPanel6.setBackground(new java.awt.Color(204, 255, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        sname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                snameActionPerformed(evt);
            }
        });
        sname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                snameKeyPressed(evt);
            }
        });

        ind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                indActionPerformed(evt);
            }
        });
        ind.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                indKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                indKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                indKeyTyped(evt);
            }
        });

        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        ema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emaActionPerformed(evt);
            }
        });

        pname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pnameActionPerformed(evt);
            }
        });

        ppho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pphoActionPerformed(evt);
            }
        });
        ppho.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pphoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pphoKeyTyped(evt);
            }
        });

        jLabel11.setText("Student Name");

        jLabel12.setText("Index Number");

        jLabel13.setText("Address");

        jLabel14.setText("Phone Number");

        jLabel15.setText("Email");

        jLabel16.setText("Parent Name");

        jLabel17.setText("Parent Phone ");

        jLabel18.setText("Faculty");

        jLabel28.setText("Gender");

        jLabel29.setText("Religon");

        jLabel30.setText("Year");

        gen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genActionPerformed(evt);
            }
        });

        yea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                yeaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                yeaKeyTyped(evt);
            }
        });

        pho.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                phoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                phoKeyTyped(evt);
            }
        });

        jLabel36.setText("Reg Date");

        jLabel38.setText("Room No");

        jLabel39.setText("Bed & Locker");

        jLabel40.setText("Vehicle Model");

        pi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabel37.setText("Hostel");

        rnum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rnumKeyPressed(evt);
            }
        });

        bl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                blKeyPressed(evt);
            }
        });

        vm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vmActionPerformed(evt);
            }
        });

        jLabel41.setText("Vehicle No");

        regd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jButton11.setBorder(null);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        hname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hnameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(24, 24, 24)
                        .addComponent(pho, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ind, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sname, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel16)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(gen)
                            .addComponent(ppho)
                            .addComponent(pname)
                            .addComponent(ema, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel28)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(18, 18, 18)
                        .addComponent(rel, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel30)
                            .addComponent(jLabel36))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(yea, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(fac)
                            .addComponent(regd, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel41)
                    .addComponent(jLabel40)
                    .addComponent(jLabel37)
                    .addComponent(jLabel38))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(rnum, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel39)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bl, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                    .addComponent(vn)
                    .addComponent(vm)
                    .addComponent(hname))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(pi, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(ema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(pname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(ppho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(gen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(rel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(fac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(yea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel36)
                            .addComponent(regd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(vn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel40)
                            .addComponent(vm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel37)
                                .addGap(6, 6, 6))
                            .addComponent(hname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(bl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel39)
                                    .addComponent(rnum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel38))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(sname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(ind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pi, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jButton8.setText("Update");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton5.setText("Delete");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton15.setBackground(new java.awt.Color(255, 255, 255));
        jButton15.setText("Print");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jLabel47.setText("Select Hostel :");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "kuruvita", "panduwasdew1", "chithradevi" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(930, 930, 930)
                        .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel47)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 711, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(tablemain, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(10, 10, 10))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7)
                    .addComponent(jLabel47)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tablemain, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8)
                    .addComponent(jButton5)
                    .addComponent(jButton15))
                .addGap(56, 56, 56))
        );

        jPanel5.setBackground(new java.awt.Color(204, 255, 255));

        jPanel11.setBackground(new java.awt.Color(204, 255, 204));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel7.setBackground(new java.awt.Color(0, 102, 102));

        jLabel49.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(153, 255, 255));
        jLabel49.setText("Chithradevi Hostel >>>>>");

        jLabel50.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(153, 255, 255));
        jLabel50.setText("available rooms");

        avi2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        avi2.setForeground(new java.awt.Color(255, 255, 255));
        avi2.setText("jLabel49");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel49)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel50)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(avi2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(jLabel50)
                    .addComponent(avi2))
                .addContainerGap())
        );

        jPanel18.setBackground(new java.awt.Color(0, 102, 102));

        jLabel67.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(153, 255, 255));
        jLabel67.setText("Kuruvita Hostel >>>>>");

        jLabel68.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(153, 255, 255));
        jLabel68.setText("available rooms");

        avi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        avi.setForeground(new java.awt.Color(255, 255, 255));
        avi.setText("jLabel49");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel67)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel68)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(avi)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel67)
                    .addComponent(jLabel68)
                    .addComponent(avi))
                .addContainerGap())
        );

        jPanel20.setBackground(new java.awt.Color(0, 102, 102));

        jLabel69.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(153, 255, 255));
        jLabel69.setText("Panduwasdew I Hostel >>>>>");

        jLabel70.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(153, 255, 255));
        jLabel70.setText("available rooms");

        avi1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        avi1.setForeground(new java.awt.Color(255, 255, 255));
        avi1.setText("jLabel49");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel69)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel70)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(avi1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel69)
                    .addComponent(jLabel70)
                    .addComponent(avi1))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(46, 46, 46)
                .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(38, 38, 38)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(40, 40, 40))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        tableroom.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Room Number", "Bed 1", "Bed 2", "Bed 3", "Bed 4"
            }
        ));
        tableroom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableroomMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableroom);

        jPanel14.setBackground(new java.awt.Color(204, 255, 204));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel33.setText("Select Hostel");

        jButton14.setText("Ok");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        searchrn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchrnKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchrnKeyReleased(evt);
            }
        });

        jLabel22.setText("Enter Room number");

        jComboBox13.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "kuruvita", "panduwasdew1", "chithradevi" }));
        jComboBox13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox13ActionPerformed(evt);
            }
        });

        r.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel33)
                .addGap(18, 18, 18)
                .addComponent(jComboBox13, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(r, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchrn, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(r, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(searchrn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22)
                            .addComponent(jComboBox13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton14))
                        .addGap(18, 18, 18))))
        );

        jPanel8.setBackground(new java.awt.Color(204, 255, 204));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel42.setText("Bed 1 :");

        b1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                b1KeyPressed(evt);
            }
        });

        jLabel43.setText("Bed 2 :");

        b2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                b2KeyPressed(evt);
            }
        });

        jLabel44.setText("Bed 3 :");

        b3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                b3KeyPressed(evt);
            }
        });

        jLabel45.setText("Bed 4 :");

        b4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                b4KeyPressed(evt);
            }
        });

        up1.setText("Update");
        up1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                up1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83)
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(b2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85)
                .addComponent(jLabel44)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(b3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92)
                .addComponent(jLabel45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(b4, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98)
                .addComponent(up1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43)
                    .addComponent(b2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45)
                    .addComponent(b4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44)
                    .addComponent(b3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(up1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        jPanel12.setBackground(new java.awt.Color(204, 255, 255));

        jPanel13.setBackground(new java.awt.Color(204, 255, 204));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel54.setText("Vehicle Number :");

        jLabel55.setText("Vehicle Model :");

        jButton24.setText("Update");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jButton25.setText("Delete");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel54)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vnu, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel55)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vmo, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jButton24, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                .addGap(39, 39, 39)
                .addComponent(jButton25, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addGap(58, 58, 58))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(vnu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel55)
                    .addComponent(vmo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton24)
                    .addComponent(jButton25))
                .addGap(27, 27, 27))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Vehicle Number", "Model", "Owner IndexNo", "Name", "Faculty", "Year", "Contact No"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTable1);

        jPanel15.setBackground(new java.awt.Color(204, 255, 204));
        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel32.setText("Search Vehicle Number");

        veh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vehActionPerformed(evt);
            }
        });
        veh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                vehKeyReleased(evt);
            }
        });

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "kuruvita", "panduwasdew1", "chithradevi" }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        jLabel53.setText("Enter vehicle number :");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(108, 108, 108)
                .addComponent(jLabel53)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(veh, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(veh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox4)
                    .addComponent(jLabel53))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1344, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel17.setBackground(new java.awt.Color(204, 255, 255));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "NIC", "res: person", "In Date", "Out Date", "Hostel", "Room", "Discription"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(40);
            jTable2.getColumnModel().getColumn(0).setMaxWidth(40);
        }

        jPanel19.setBackground(new java.awt.Color(204, 255, 204));
        jPanel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel58.setText("Hostel");

        vroom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                vroomKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                vroomKeyReleased(evt);
            }
        });

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select hostel", "kuruvita", "panduwasdew1", "chithradevi" }));
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });

        jLabel60.setText("Name Of The Visitor ");

        jLabel61.setText("NIC no");

        jLabel62.setText("Responsible Person");

        jLabel63.setText("In Date");

        jLabel64.setText("Out Date");

        jLabel65.setText("Room Number");

        jLabel66.setText("Discreption");

        dis.setColumns(20);
        dis.setRows(5);
        jScrollPane4.setViewportView(dis);

        jButton13.setText("Save");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton28.setText("Update");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        jButton29.setText("Delete");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jLabel56.setText("visiter ID >>");

        jButton40.setText("Clear");
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel58)
                    .addComponent(jLabel60)
                    .addComponent(jLabel61)
                    .addComponent(jLabel62)
                    .addComponent(jLabel63)
                    .addComponent(jLabel64))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel65)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(vroom, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel56)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(vid, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
                            .addComponent(visnam)
                            .addComponent(nic)
                            .addComponent(resp))
                        .addGap(34, 34, 34))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel66)
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel19Layout.createSequentialGroup()
                            .addComponent(jButton40)
                            .addGap(18, 18, 18)
                            .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(visnam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel66))
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel61))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(resp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel62))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel63)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel64)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jScrollPane4)))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel58)
                        .addComponent(jComboBox5)
                        .addComponent(jLabel65)
                        .addComponent(vroom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel56)
                        .addComponent(jButton40)))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane7)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                .addGap(28, 28, 28))
        );

        jPanel21.setBackground(new java.awt.Color(204, 255, 255));

        jPanel23.setBackground(new java.awt.Color(204, 255, 204));
        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton31.setText("Create Report");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        in1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                in1KeyPressed(evt);
            }
        });

        jLabel48.setText("TO");

        in2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                in2KeyPressed(evt);
            }
        });

        jLabel72.setText("CREATE STUDENTS REPORT BETWEEN INDEX NO ");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(in1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel48)
                .addGap(18, 18, 18)
                .addComponent(in2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton31)
                    .addComponent(in1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48)
                    .addComponent(in2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel72))
                .addGap(20, 20, 20))
        );

        jPanel24.setBackground(new java.awt.Color(204, 255, 204));
        jPanel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton32.setText("Create Report");
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        faculty1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FAS", "BSF", "FOT", " " }));

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "kuruvita", "panduwasdew1", "chithradevi" }));
        jComboBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox6ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 st yaer", "2 nd year", "3 rd year", "4 th year", " " }));

        jLabel71.setText("CREATE STUDENT'S REPORT ");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel71, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(faculty1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(148, 148, 148)
                .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton32)
                    .addComponent(faculty1)
                    .addComponent(jComboBox6)
                    .addComponent(jComboBox1)
                    .addComponent(jLabel71))
                .addGap(20, 20, 20))
        );

        jPanel25.setBackground(new java.awt.Color(204, 255, 204));
        jPanel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton33.setText("Create Report");
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "kuruvita", "panduwasdew1", "chithradevi" }));
        jComboBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox7ActionPerformed(evt);
            }
        });

        jLabel77.setText("CREATE STUDENTS VEHICLE DETAILS REPORT ");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 479, Short.MAX_VALUE)
                .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton33)
                    .addComponent(jComboBox7)
                    .addComponent(jLabel77))
                .addGap(20, 20, 20))
        );

        jPanel26.setBackground(new java.awt.Color(204, 255, 204));
        jPanel26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton34.setText("Create Report");
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });

        jLabel78.setText("CREATE VISITERS DETAILS REPORT ");

        jLabel52.setText("TO");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel52)
                .addGap(29, 29, 29)
                .addComponent(jDateChooser4, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooser3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton34)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(jLabel78)
                        .addGap(6, 6, 6))
                    .addComponent(jLabel52))
                .addGap(20, 20, 20))
        );

        jPanel27.setBackground(new java.awt.Color(204, 255, 204));
        jPanel27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel75.setText("ROOM NO ");

        jButton35.setText("Create Report");
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });

        in3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                in3KeyPressed(evt);
            }
        });

        jLabel51.setText("TO");

        in4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                in4KeyPressed(evt);
            }
        });

        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "kuruvita", "panduwasdew1", "chithradevi" }));
        jComboBox8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox8ActionPerformed(evt);
            }
        });

        jLabel76.setText("CREATE STUDENTS REPORT BETWEEN ROOM NO ");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel76, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel75)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(in3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel51)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(in4, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(148, 148, 148)
                .addComponent(jButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton35)
                    .addComponent(jLabel75)
                    .addComponent(in3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel51)
                    .addComponent(in4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox8)
                    .addComponent(jLabel76))
                .addGap(20, 20, 20))
        );

        jPanel30.setBackground(new java.awt.Color(204, 255, 204));
        jPanel30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton39.setText("Create Report");
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });

        jLabel83.setText("CREATE FINES DETAILS REPORT ");

        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "kuruvita", "panduwasdew1", "chithradevi" }));
        jComboBox10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton39, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton39)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel83)
                            .addComponent(jComboBox10))
                        .addGap(3, 3, 3)))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        jPanel22.setBackground(new java.awt.Color(204, 255, 255));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Index", "Room", "Fine Value", " Date", "Faculty", "Hostel", "Year", "Discription"
            }
        ));
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(jTable3);
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(0).setPreferredWidth(40);
            jTable3.getColumnModel().getColumn(0).setMaxWidth(40);
        }

        jPanel28.setBackground(new java.awt.Color(204, 255, 204));
        jPanel28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel73.setText("Hostel");

        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select hostel", "kuruvita", "panduwasdew1", "chithradevi" }));
        jComboBox9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox9ActionPerformed(evt);
            }
        });

        jLabel74.setText("Index No ");

        visnam1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                visnam1KeyPressed(evt);
            }
        });

        jLabel79.setText("Room No");

        roomN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                roomNKeyPressed(evt);
            }
        });

        jLabel80.setText("Fine Value");

        fine.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fineKeyPressed(evt);
            }
        });

        jLabel81.setText("In Date");

        jLabel84.setText("Discreption");

        dis1.setColumns(20);
        dis1.setRows(5);
        jScrollPane5.setViewportView(dis1);

        jButton27.setText("Add Fine");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jButton30.setText("Update");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        jButton36.setText("Delete");
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });

        faculty2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FAS", "BSF", "FOT", " " }));

        jLabel86.setText("Faculty");

        jLabel87.setText("Year");

        year1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 st year", "2 nd year", "3 rd year", "4 th year", " " }));

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel73)
                    .addComponent(jLabel74)
                    .addComponent(jLabel79)
                    .addComponent(jLabel80)
                    .addComponent(jLabel81))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel28Layout.createSequentialGroup()
                                .addGap(455, 455, 455)
                                .addComponent(vid1, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
                            .addComponent(visnam1)
                            .addComponent(roomN)
                            .addComponent(fine))
                        .addGap(34, 34, 34))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateChooser5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox9, 0, 189, Short.MAX_VALUE))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(year1, 0, 178, Short.MAX_VALUE)
                            .addComponent(faculty2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel84)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel74)
                    .addComponent(visnam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel84))
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(roomN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel79))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel80))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel28Layout.createSequentialGroup()
                                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel81)
                                    .addComponent(jDateChooser5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, Short.MAX_VALUE))
                            .addGroup(jPanel28Layout.createSequentialGroup()
                                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(faculty2)
                                    .addComponent(jLabel86))
                                .addGap(18, 18, 18))))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(year1)
                        .addComponent(jLabel87)
                        .addComponent(jButton27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton36))
                    .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox9)
                        .addComponent(jLabel73)))
                .addGap(18, 18, 18)
                .addComponent(vid1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jLabel82.setText("Search From Index No >>");

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel82)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 1164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel82))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, 1330, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 1330, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(20, 20, 20)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(9, 9, 9)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(10, 10, 10)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(29, 29, 29)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(30, 30, 30)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(24, 24, 24)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 31, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(16, 16, 16)))
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void parentphoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_parentphoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_parentphoneActionPerformed

    private void parentnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_parentnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_parentnameActionPerformed

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void phoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneActionPerformed

    private void addressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addressActionPerformed

    private void indexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_indexActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_indexActionPerformed

    private void nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jPanel1.setVisible(true);
         jPanel2.setVisible(true);
            jPanel4.setVisible(false);
            jPanel5.setVisible(false);
             jPanel12.setVisible(false);
             jPanel17.setVisible(false);
             jPanel21.setVisible(false);
              jPanel22.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
          jPanel1.setVisible(true);
         jPanel2.setVisible(false);
            jPanel4.setVisible(true);
            jPanel5.setVisible(false);
             jPanel12.setVisible(false);
             jPanel17.setVisible(false);
             jPanel21.setVisible(false);
              jPanel22.setVisible(false);
               showUser3();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
          jPanel1.setVisible(true);
         jPanel2.setVisible(false);
            jPanel4.setVisible(false);
            jPanel5.setVisible(true);
             jPanel12.setVisible(false);
             jPanel17.setVisible(false);
             jPanel21.setVisible(false);
              jPanel22.setVisible(false);
               showroom();
             int bb = fillroom("kuruvita");
             avi.setText(""+bb+" - 100 ");
             avi1.setText(""+fillroom("panduwasdew1")+" - 100 ");
              avi2.setText(""+fillroom("chithradevi")+" - 100 ");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void insActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insActionPerformed
        // TODO add your handling code here:
       
 
       // try (Connection conn = connect();Class.forName("org.sqlite.JDBC");
           try{String url = "jdbc:sqlite:student.db";
           Connection con2 = DriverManager.getConnection(url);
            String sql="insert into details"
                +"(name,indexNo,address,phone,email,parent_name,parent_phone,gender,religion,faculty,year,pic,room,bed,regdate,vmodel,vnumber,hostel) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
               pst=con2.prepareStatement(sql);
  pst.setString(1, name.getText()); 
  pst.setString(2, index.getText()); 
  pst.setString(3, address.getText()); 
  pst.setString(4, phone.getText()); 
  pst.setString(5, email.getText()); 
  pst.setString(6, parentname.getText()); 
  pst.setString(7, parentphone.getText()); 
  pst.setString(8, (String) gender.getSelectedItem()); 
  pst.setString(9, (String) religon.getSelectedItem()); 
  pst.setString(10, (String) faculty.getSelectedItem()); 
  pst.setString(11, (String) year.getSelectedItem()); 
  pst.setBytes(12, stuimage); 
  pst.setString(13, room.getText()); 
  pst.setString(14, bed.getText()); 
  pst.setString(15, date.getText()); 
  pst.setString(16, vmodel.getText()); 
       
  pst.setString(17, vnumber.getText()); 
  pst.setString(18, gethos()); 
            // set parameters  pst.setString(3, (String) txt_gender.getSelectedItem());
           
        pst.execute();
         reset();
            msg.setText("Successfully inserted Data");
            // JOptionPane.showMessageDialog(null, "Successfully inserted Data");
           // pst.executeUpdate();
            //System.out.println("Stored the file in the BLOB column.");
 
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "Filled data types are wrong! please check & fill details correctly");
        }
        /* try {
           String querry = "insert into details (name,indexNo,address,phone,email,parent_name,parent_phone,gender,religion,faculty,year,pic,room,bed,regdate,vmodel,vnumber,hostel) values('"+name.getText()+"','"+index.getText()+"','"+address.getText()+"','"+phone.getText()+"','"+email.getText()+"','"+parentname.getText()+"','"+parentphone.getText()+"','"+gender.getSelectedItem().toString()+"','"+religon.getSelectedItem().toString()+"','"+faculty.getSelectedItem().toString()+"','"+year.getSelectedItem().toString()+"','"+stuimage.toString()+"','"+room.getText()+"','"+bed.getText()+"','"+date.getText()+"','"+vmodel.getText()+"','"+vnumber.getText()+"','"+gethos()+"')";
            
            st.executeUpdate(querry);
            reset();
            msg.setText("Successfully inserted Data");
           showUser3();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Filled data types are wrong! please check & fill details correctly");
           // Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
           // 
        }*/
        showUser3();
         showroom();
    }//GEN-LAST:event_insActionPerformed
//public byte [] getPic(){
  //  return pic.;
   // ps.setBytes(10, pi1.getPic());
//}
    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
       
       try {
       fileChooser.addChoosableFileFilter(filter);
       fileChooser.showOpenDialog(null);
       
             im = ImageIO.read(fileChooser.getSelectedFile());
       
          pic1.setIcon(new ImageIcon(im.getScaledInstance(pic1.getWidth(), pic1.getHeight(), 1)));
          File f = fileChooser.getSelectedFile();
          filename = f.getAbsolutePath();
       
            File image = new File(""+filename+"");
            FileInputStream fis = new FileInputStream(image);
            byte[] buf = new byte[1024];
            bos = new ByteArrayOutputStream();
            
            for(int readNum; (readNum=fis.read(buf))!=-1;){
                bos.write(buf, 0, readNum);
        }
            stuimage = bos.toByteArray(); 
           
        } catch (Exception ex) {
          
            JOptionPane.showMessageDialog(null, "No File Selected");
            // filename="G:\\Hostel Management project\\HOSTEL MANAGEMENT SYSTEM\\src\\images\\blank.png";
             
        }
         
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        
        reset();
    }//GEN-LAST:event_jButton10ActionPerformed

    public void reset(){
        name.setText(null);
        index.setText(null);
        address.setText(null);
        phone.setText(null);
        email.setText(null);
        parentname.setText(null);
        parentphone.setText(null);
        msg.setText(null);
        pic1.setIcon(new ImageIcon("E:\\Hostel Management project1\\HOSTEL MANAGEMENT SYSTEM\\images\\b1.jpg"));
        room.setText(null);
         bed.setText(null);
          //date.setText(null);
           vmodel.setText(null);
            vnumber.setText(null);
    }
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
         jPanel12.setVisible(true);
          jPanel1.setVisible(true);
         jPanel2.setVisible(false);
            jPanel4.setVisible(false);
            jPanel5.setVisible(false);
            jPanel17.setVisible(false);
            jPanel21.setVisible(false);
             jPanel22.setVisible(false);
             showvehicle();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        
         try {
            if(full() == 100){
               JOptionPane.showMessageDialog(null, "All rooms are filled"); 
            }else{
             // TODO add your handling code here:
              
             String qr = "insert into "+gethos()+" (bed1,bed2,bed3,bed4) values(0,0,0,0)";
             // String querry = "UPDATE kuruvita SET bed2 = 10 WHERE roomnumber = (SELECT MAX(roomnumber) FROM kuruvita)";
             st.executeUpdate(qr);
            
              
             msg.setText("OK, Now please enter deta to first bed");
            }
         } catch (SQLException ex) {
             Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
         }
         
         showUser2();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
     // String g =   main.ee;
         if(insert("bed2")==0){
              bed.setText("2");
            room.setText(""+insert("roomnumber")+"");
            bed("bed2");
        }else{
            JOptionPane.showMessageDialog(null, "Please enter empty room number");
        }
      showUser2();
         //showUser1();
       //  view();
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
         if(insert("bed3")==0){
              bed.setText("3");
            room.setText(""+insert("roomnumber")+"");
            bed("bed3");
        }else{
            JOptionPane.showMessageDialog(null, "Please enter empty room number");
        }
       showUser2();
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
          if(insert("bed4")==0){
               bed.setText("4");
            room.setText(""+insert("roomnumber")+"");
            bed("bed4");
        }else{
            JOptionPane.showMessageDialog(null, "This room is full! Please enter new room");
        }
        showUser2();
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
     
       if(insert("bed1")==0){
            bed.setText("1");
            room.setText(""+insert("roomnumber")+"");
            bed("bed1");
        }else{
            JOptionPane.showMessageDialog(null, "Please enter empty room number");
            // bed.setText("");
        }
     showUser2();
      
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
         jPanel12.setVisible(false);
          jPanel1.setVisible(true);
         jPanel2.setVisible(false);
            jPanel4.setVisible(false);
            jPanel5.setVisible(false);
            jPanel17.setVisible(true);
            jPanel21.setVisible(false);
             jPanel22.setVisible(false);
             showvisiters();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
      
        if(gethos() == "select hostel"){
           DefaultTableModel model = (DefaultTableModel) table2.getModel();
        model.setRowCount(0);
        }else{
         gethos();
       showUser2();
        }
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void dateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        
        try{
           // Class.forName("org.sqlite.JDBC");
           // String url = "jdbc:sqlite:student.db";
           // Connection con2 = DriverManager.getConnection(url);
            String query211 = "DELETE FROM details WHERE indexNo = '"+search.getText()+"'";
            // String query12 = "DELETE FROM "+hname.getText()+" WHERE indexNo = '"+search.getText()+"'";
            //st =  con2.createStatement();

            ///////////--
             String q12125 = "UPDATE "+gethos1()+" SET bed"+bl.getText()+" = '0' WHERE roomnumber = "+rnum.getText()+"";
             
            
            // JOptionPane.showMessageDialog(null, "deleted successfully");
           
            
            
            
            ///rs1.next();
            int option = JOptionPane.showConfirmDialog(this, "Are you sure to delete this Search Student?", "Confirm search Deletion", JOptionPane.ERROR_MESSAGE);
            if (option == 0) {
                st.executeUpdate(q12125);
                st.executeQuery(query211);
                  // st.executeQuery(q12125);
               // st.executeUpdate(q12125);
               
// msg.setText("Successfully delete "+search.getText()+"");
            }
        }catch(Exception ex){
           // JOptionPane.showMessageDialog(null, "error33");
        }
         search.setText("");
        ind.setText("");
        sname.setText("");
        add.setText("");
        pho.setText("");
        ema.setText("");
        pname.setText("");
        ppho.setText("");
        gen.setText("");
         rel.setText("");
        fac.setText("");
        yea.setText("");
         rnum.setText("");
        bl.setText("");
        regd.setText("");
         vm.setText("");
        vn.setText("");
       hname.setText(""); 
       pi.setIcon(null);
        JOptionPane.showMessageDialog(null, "Successfully deleted!");
        showUser3();
    /////////////////////////////////////////////////////////room hostel databases 
        /* try {
            
             // b1.setText("0");
            // msg.setText("Successfully inserted Data to "+c+"");
         } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null, "update error");
         }*/
         showroom();
        

    }//GEN-LAST:event_jButton5ActionPerformed

    private void vmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vmActionPerformed

    private void genActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_genActionPerformed

    private void pphoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pphoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pphoActionPerformed

    private void pnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pnameActionPerformed

    private void emaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emaActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addActionPerformed

    private void indActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_indActionPerformed
        // TODO add your handling code here:jc.
        
    }//GEN-LAST:event_indActionPerformed

    private void snameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_snameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_snameActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
         /*try {
             // TODO add your handling code here:
             ema.print();
         } catch (PrinterException ex) {
             Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        ///
        
         }*/
          String value1=sname.getText();
            //JFileChooser dialog = new JFileChooser();
        fileChooser.setSelectedFile(new File(value1 +""+".pdf"));
        int dialogResult = fileChooser.showSaveDialog(null);
        if (dialogResult==JFileChooser.APPROVE_OPTION){
            String filePath = fileChooser.getSelectedFile().getPath();
            //Document myDocument = new Document();
             Document myDocument = new Document();
              try {
                try {
                    PdfWriter myWriter = PdfWriter.getInstance(myDocument, new FileOutputStream(filePath));
                } catch (DocumentException ex) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                }
                 myDocument.open();
                try {
                    myDocument.add(new Paragraph("STUDENT'S HOSTEL REGISTRATION DETAILS",FontFactory.getFont(FontFactory.TIMES_BOLD,20,Font.BOLD )));
                     myDocument.add((new Paragraph("Wayamba university of Sri Lanka",FontFactory.getFont(FontFactory.TIMES_ROMAN,13,Font.BOLD)))); 
                    myDocument.add(new Paragraph(new Date().toString()));
                       myDocument.add(new Paragraph("-------------------------------------------------------------------------------------------"));
                //myDocument.add((new Paragraph("Meal Details",FontFactory.getFont(FontFactory.TIMES_ROMAN,15,Font.BOLD))));
                myDocument.add((new Paragraph("Student Name  : "+sname.getText()+ " ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                myDocument.add((new Paragraph("Index No      : "+ind.getText()+ " ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                myDocument.add((new Paragraph("Address       : "+add.getText()+" ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                myDocument.add((new Paragraph("Phone No      : "+pho.getText()+" ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                
                myDocument.add((new Paragraph("Email         : "+ema.getText()+ " ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                myDocument.add((new Paragraph("Parent Name   : "+pname.getText()+ " ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                myDocument.add((new Paragraph("Parent Phone  : "+ppho.getText()+" ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                myDocument.add((new Paragraph("Gender        : "+gen.getText()+" ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                
                myDocument.add((new Paragraph("Religion      : "+rel.getText()+ " ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                myDocument.add((new Paragraph("Faculty       : "+fac.getText()+ " ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                myDocument.add((new Paragraph("Year          : "+yea.getText()+" ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                myDocument.add((new Paragraph("Register Date : "+regd.getText()+" ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                
                myDocument.add((new Paragraph("Vehicle No    : "+vn.getText()+ " ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                myDocument.add((new Paragraph("Vehicle Model : "+vm.getText()+ " ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                myDocument.add((new Paragraph("Hostel        : "+hname.getText()+" ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                myDocument.add((new Paragraph("Room No       : "+rnum.getText()+" ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                myDocument.add((new Paragraph("Bed No        : "+bl.getText()+" ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                //myDocument.add((new Paragraph("Total Amount:  "+total,FontFactory.getFont(FontFactory.TIMES_ROMAN,10,Font.PLAIN))));
                 myDocument.add((new Paragraph("\n ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                 myDocument.add((new Paragraph("\n ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                 myDocument.add((new Paragraph("Sinature ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                myDocument.newPage();
                myDocument.close();
                JOptionPane.showMessageDialog(null,"Report was successfully generated");
                      
                } catch (DocumentException ex) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                }
              
               
                 
              } catch (FileNotFoundException ex) {
                  Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
              } 
                
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        try{String url = "jdbc:sqlite:student.db";
           Connection con2 = DriverManager.getConnection(url);
            String sql1="UPDATE details SET name =?,indexNo = ?,address = ?,phone  = ?,email = ?,parent_name = ?,parent_phone = ?,gender = ?,religion = ?,faculty = ?,year = ?,pic = ?,room = ?,bed = ?,vmodel = ?,vnumber = ? WHERE indexNo = '"+search.getText()+"'";
                //+"name =? WHERE indexNo = "+search.getText()+"";
               // +"name =? ,indexNo = ?,address = ?,phone  = ?,email = ?,parent_name = ?,parent_phone = ?,gender = ?,religion = ?,faculty = ?,year = ?,pic = ?,room = ?,bed = ?,vmodel = ?,vnumber = ? WHERE indexNo = "+search.getText()+"";
            pst=con2.prepareStatement(sql1);
  pst.setString(1, sname.getText()); 
 pst.setString(2, ind.getText()); 
  pst.setString(3, add.getText()); 
  pst.setString(4, pho.getText()); 
  pst.setString(5, ema.getText()); 
  pst.setString(6, pname.getText()); 
  pst.setString(7, ppho.getText()); 
  pst.setString(8, gen.getText()); 
  pst.setString(9, rel.getText()); 
  pst.setString(10, fac.getText()); 
  pst.setString(11, yea.getText()); 
  pst.setBytes(12, stuimage1); 
  pst.setString(13, rnum.getText()); 
  pst.setString(14, bl.getText()); 
 // pst.setString(15, date.getText()); 
  pst.setString(15, vm.getText()); 
       
  pst.setString(16, vn.getText()); 
  //pst.setString(18, gethos()); */
            // set parameters  pst.setString(3, (String) txt_gender.getSelectedItem());
           
        pst.execute();
         //reset();
           //msg.setText("Successfully inserted Data");
           // pst.executeUpdate();
            //System.out.println("Stored the file in the BLOB column.");
  JOptionPane.showMessageDialog(null, "Successfully updated data");
  
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "Filled data types are wrong! please check & fill details correctly");
        }
         showUser3();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void tablemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablemMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tablem.getModel();
        int selectedRowIndex = tablem.getSelectedRow();
        search.setText(model.getValueAt(selectedRowIndex, 0).toString());
        ind.setText(model.getValueAt(selectedRowIndex, 0).toString());
        sname.setText(model.getValueAt(selectedRowIndex, 1).toString());
        add.setText(model.getValueAt(selectedRowIndex, 2).toString());
        pho.setText(model.getValueAt(selectedRowIndex, 3).toString());
        ema.setText(model.getValueAt(selectedRowIndex, 4).toString());
        pname.setText(model.getValueAt(selectedRowIndex, 5).toString());
        ppho.setText(model.getValueAt(selectedRowIndex, 6).toString());
        gen.setText(model.getValueAt(selectedRowIndex, 7).toString());
         rel.setText(model.getValueAt(selectedRowIndex, 8).toString());
        fac.setText(model.getValueAt(selectedRowIndex, 9).toString());
        yea.setText(model.getValueAt(selectedRowIndex, 10).toString());
         rnum.setText(model.getValueAt(selectedRowIndex, 11).toString());
        bl.setText(model.getValueAt(selectedRowIndex, 12).toString());
        regd.setText(model.getValueAt(selectedRowIndex, 13).toString());
         vm.setText(model.getValueAt(selectedRowIndex, 14).toString());
        vn.setText(model.getValueAt(selectedRowIndex, 15).toString());
       //add.setText(model.getValueAt(selectedRowIndex, 2).toString());
       
       try{//Class.forName("org.sqlite.JDBC");
          /// String url = "jdbc:sqlite:student.db";
          // Connection con2 = DriverManager.getConnection(url);
   String query118 = "SELECT * FROM details WHERE indexNo = '"+search.getText()+"'";
    //st =  con2.createStatement();
    
     ResultSet rs1 = st.executeQuery(query118);
     if(rs1.next()){
      hname.setText(rs1.getString("hostel")); 
       byte[] img1 = rs1.getBytes("pic");
       if(img1==null){
           pi.setIcon(new ImageIcon("G:\\Hostel Management project\\HOSTEL MANAGEMENT SYSTEM\\src\\images\\blank.png"));
       }else{
          ImageIcon icon = new ImageIcon(img1);
          Image  ima = icon.getImage();
          Image myim = ima.getScaledInstance(pi.getWidth(), pi.getHeight(), SCALE_SMOOTH);
          ImageIcon newicon = new ImageIcon(myim);
          pi.setIcon(newicon);
     }
     }
     } catch(Exception ex){  
            JOptionPane.showMessageDialog(null, "error3");
        }finally
     {
       try
       {
         st.close();
       }
       catch(Exception e)
       {
          JOptionPane.showMessageDialog(null,e );
       }
} 
    }//GEN-LAST:event_tablemMouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
         try {
       fileChooser.addChoosableFileFilter(filter);
       fileChooser.showOpenDialog(null);
       
             im = ImageIO.read(fileChooser.getSelectedFile());
       
          pi.setIcon(new ImageIcon(im.getScaledInstance(pi.getWidth(), pi.getHeight(), 1)));
          File f = fileChooser.getSelectedFile();
          filename = f.getAbsolutePath();
       
            File image = new File(""+filename+"");
            FileInputStream fis = new FileInputStream(image);
            byte[] buf = new byte[1024];
            bos = new ByteArrayOutputStream();
            
            for(int readNum; (readNum=fis.read(buf))!=-1;){
                bos.write(buf, 0, readNum);
        }
            stuimage1 = bos.toByteArray(); 
           
        } catch (Exception ex) {
          
            JOptionPane.showMessageDialog(null, "No File Selected");
            // filename="G:\\Hostel Management project\\HOSTEL MANAGEMENT SYSTEM\\src\\images\\blank.png";
             
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jComboBox13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox13ActionPerformed
        // TODO add your handling code here:
          if(gethos2() == "select hostel"){
            DefaultTableModel model = (DefaultTableModel) tableroom.getModel();
            model.setRowCount(0);
        }else{
            gethos2();
            showroom();
        }
    }//GEN-LAST:event_jComboBox13ActionPerformed

    private void up1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_up1ActionPerformed
        // TODO add your handling code here:
        //System.out.println(""+gethos2()+"");
         try {
             String qc = "UPDATE "+gethos2()+" SET bed1 = '"+b1.getText()+"',bed2 = '"+b2.getText()+"',bed3 = '"+b3.getText()+"',bed4 = '"+b4.getText()+"' WHERE roomnumber = "+searchrn.getText()+"";
             // st.execute(qc);
           st.executeUpdate(qc);
             JOptionPane.showMessageDialog(null, "update successfully");
             showroom();
            // msg.setText("Successfully inserted Data to "+c+"");
         } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null, "update error");
         }
//showroom();
    }//GEN-LAST:event_up1ActionPerformed

    private void tableroomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableroomMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tableroom.getModel();
        int selectedRowIndex = tableroom.getSelectedRow();
        searchrn.setText(model.getValueAt(selectedRowIndex, 0).toString());
        b1.setText(model.getValueAt(selectedRowIndex, 1).toString());
         b2.setText(model.getValueAt(selectedRowIndex, 2).toString());
          b3.setText(model.getValueAt(selectedRowIndex, 3).toString());
           b4.setText(model.getValueAt(selectedRowIndex, 4).toString());
    }//GEN-LAST:event_tableroomMouseClicked

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        
       try{ //Class.forName("org.sqlite.JDBC");
           //String url = "jdbc:sqlite:student.db";
          // Connection con2 = DriverManager.getConnection(url);
   String query178 = "SELECT * FROM "+gethos2()+" WHERE roomnumber = '"+searchrn.getText()+"'";
    //st =  con2.createStatement();
     ResultSet rs1 = st.executeQuery(query178);
     if(rs1.next()){
         b1.setText(rs1.getString("bed1"));
          b2.setText(rs1.getString("bed2"));
           b3.setText(rs1.getString("bed3"));
            b4.setText(rs1.getString("bed4"));
         
     }else{
         JOptionPane.showMessageDialog(null, "inncrect room number, Please check!");
     }
       }
        catch(Exception e)
       {
          JOptionPane.showMessageDialog(null," inncrect room number" );
       }finally
{
       try
       {
         st.close();
        
       }
       catch(Exception e)
       {
          JOptionPane.showMessageDialog(null,e );
       }
} 
        
    }//GEN-LAST:event_jButton14ActionPerformed

    private void indexKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_indexKeyPressed
        // TODO add your handling code here:
         char c = evt.getKeyChar();
        if(Character.isLetter(c) ){
            index.setEditable(false);
            //JOptionPane.showMessageDialog(null,"Plaese Enter Number");
        }else{
             index.setEditable(true);
        }
       
    }//GEN-LAST:event_indexKeyPressed

    private void phoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phoneKeyPressed
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(Character.isLetter(c) ){
            phone.setEditable(false);
            //JOptionPane.showMessageDialog(null,"Plaese Enter Number");
        }else{
             phone.setEditable(true);
        }
    }//GEN-LAST:event_phoneKeyPressed

    private void parentphoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_parentphoneKeyPressed
        // TODO add your handling code here:
         char c = evt.getKeyChar();
        if(Character.isLetter(c) ){
            parentphone.setEditable(false);
            //JOptionPane.showMessageDialog(null,"Plaese Enter Number");
        }else{
             parentphone.setEditable(true);
        }
      
    }//GEN-LAST:event_parentphoneKeyPressed

    private void indKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_indKeyPressed
        // TODO add your handling code here:
       char c = evt.getKeyChar();
        if(Character.isLetter(c) ){
            ind.setEditable(false);
            //JOptionPane.showMessageDialog(null,"Plaese Enter Number");
        }else{
            ind.setEditable(true);
        }
        
    }//GEN-LAST:event_indKeyPressed

    private void phoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phoKeyPressed
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(Character.isLetter(c) ){
           pho.setEditable(false);
            //JOptionPane.showMessageDialog(null,"Plaese Enter Number");
        }else{
            pho.setEditable(true);
        }
    }//GEN-LAST:event_phoKeyPressed

    private void pphoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pphoKeyPressed
        // TODO add your handling code here:
         char c = evt.getKeyChar();
        if(Character.isLetter(c) ){
            ppho.setEditable(false);
            //JOptionPane.showMessageDialog(null,"Plaese Enter Number");
        }else{
            ppho.setEditable(true);
        }
    }//GEN-LAST:event_pphoKeyPressed

    private void yeaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_yeaKeyPressed
        // TODO add your handling code here:
          char c = evt.getKeyChar();
        if(Character.isLetter(c) ){
            yea.setEditable(false);
            //JOptionPane.showMessageDialog(null,"Plaese Enter Number");
        }else{
             yea.setEditable(true);
        }
    }//GEN-LAST:event_yeaKeyPressed

    private void searchInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_searchInputMethodTextChanged
        // TODO add your handling code here:
        //autoshowUser3();
    }//GEN-LAST:event_searchInputMethodTextChanged

    private void searchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyTyped
        // TODO add your handling code here:
        //autoshowUser3();
    }//GEN-LAST:event_searchKeyTyped

    private void searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyPressed
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(Character.isLetter(c)){
            search.setEditable(false);
           // JOptionPane.showMessageDialog(null,"Plaese Enter Number");
        }else{
           search.setEditable(true);
        }
        //autoshowUser3();
    }//GEN-LAST:event_searchKeyPressed

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
        // TODO add your handling code here:
        autoshowUser3();
    }//GEN-LAST:event_searchKeyReleased

    private void indKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_indKeyReleased
        // TODO add your handling code here:
       
         
    }//GEN-LAST:event_indKeyReleased

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
          search.setText("");
        if(gethos1() == "select hostel"){
            DefaultTableModel model1 = (DefaultTableModel) tablem.getModel();
            model1.setRowCount(0);
        }else{
            gethos1();
            showUser3();
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void nameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameKeyPressed
        // TODO add your handling code here:
        char c= evt.getKeyChar();
if(Character.isLetter(c)||Character.isWhitespace(c)||Character.isISOControl(c)){
name.setEditable(true);}else{
   name.setEditable(false); 
   // JOptionPane.showMessageDialog(null,"please enter characters" );
}
    }//GEN-LAST:event_nameKeyPressed

    private void snameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_snameKeyPressed
        // TODO add your handling code here:
         char c= evt.getKeyChar();
if(Character.isLetter(c)||Character.isWhitespace(c)||Character.isISOControl(c)){
sname.setEditable(true);
//sname.setBackground(white);
}
else{
 //sname.setBackground(red);
   sname.setEditable(false); 
   // JOptionPane.showMessageDialog(null,"please enter characters" );
}
    }//GEN-LAST:event_snameKeyPressed

    private void indKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_indKeyTyped
        // TODO add your handling code here:
       char c = evt.getKeyChar();
        if(ind.getText().length()>=6 || Character.isLetter(c)){
            ind.setEditable(false);
            //JOptionPane.showMessageDialog(null,"Plaese Enter Number");
        }else{
            ind.setEditable(true);
        }   
        
    }//GEN-LAST:event_indKeyTyped

    private void indexKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_indexKeyReleased
        // TODO add your handling code here:
         
    }//GEN-LAST:event_indexKeyReleased

    private void indexKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_indexKeyTyped
        // TODO add your handling code here:
         char c = evt.getKeyChar();
        if(index.getText().length()>=6 || Character.isLetter(c)){
            index.setEditable(false);
            //JOptionPane.showMessageDialog(null,"Plaese Enter Number");
        }else{
             index.setEditable(true);
        }
    }//GEN-LAST:event_indexKeyTyped

    private void phoneKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phoneKeyTyped
        // TODO add your handling code here:
         char c = evt.getKeyChar();
        if(phone.getText().length()>=10 || Character.isLetter(c)){
            phone.setEditable(false);
            //JOptionPane.showMessageDialog(null,"Plaese Enter Number");
        }else{
             phone.setEditable(true);
        }
    }//GEN-LAST:event_phoneKeyTyped

    private void parentphoneKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_parentphoneKeyTyped
        // TODO add your handling code here:
         char c = evt.getKeyChar();
        if(parentphone.getText().length()>=10 || Character.isLetter(c)){
            parentphone.setEditable(false);
            //JOptionPane.showMessageDialog(null,"Plaese Enter Number");
        }else{
             parentphone.setEditable(true);
        }
    }//GEN-LAST:event_parentphoneKeyTyped

    private void phoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phoKeyTyped
        // TODO add your handling code here:
          char c = evt.getKeyChar();
        if(pho.getText().length()>=10 || Character.isLetter(c)){
            pho.setEditable(false);
            //JOptionPane.showMessageDialog(null,"Plaese Enter Number");
        }else{
             pho.setEditable(true);
        }
    }//GEN-LAST:event_phoKeyTyped

    private void pphoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pphoKeyTyped
        // TODO add your handling code here:
          char c = evt.getKeyChar();
        if(ppho.getText().length()>=10 || Character.isLetter(c)){
            ppho.setEditable(false);
            //JOptionPane.showMessageDialog(null,"Plaese Enter Number");
        }else{
             ppho.setEditable(true);
        }
    }//GEN-LAST:event_pphoKeyTyped

    private void yeaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_yeaKeyTyped
        // TODO add your handling code here:
       char c = evt.getKeyChar();
        if(yea.getText().length()>=1 || Character.isLetter(c)){
            yea.setEditable(false);
            //JOptionPane.showMessageDialog(null,"Plaese Enter Number");
        }else{
             yea.setEditable(true);
        }
    }//GEN-LAST:event_yeaKeyTyped

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        int selectedRowIndex = jTable2.getSelectedRow();
       
        vid.setText(model.getValueAt(selectedRowIndex, 0).toString());
        visnam.setText(model.getValueAt(selectedRowIndex, 1).toString());
      
        nic.setText(model.getValueAt(selectedRowIndex, 2).toString());
        resp.setText(model.getValueAt(selectedRowIndex, 3).toString());
        try {
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse( model.getValueAt(selectedRowIndex, 4).toString());
            java.util.Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse( model.getValueAt(selectedRowIndex, 5).toString());
              jDateChooser1.setDate(date);
              jDateChooser2.setDate(date1);
        } catch (ParseException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
      
       // jDateChooser2.setDate((Date) model.getValueAt(selectedRowIndex, 5));
     jComboBox5.setActionCommand((String) model.getValueAt(selectedRowIndex, 6));
        vroom.setText(model.getValueAt(selectedRowIndex, 7).toString());
         dis.setText(model.getValueAt(selectedRowIndex, 8).toString());
    }//GEN-LAST:event_jTable2MouseClicked

    private void vroomKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vroomKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_vroomKeyReleased

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox5ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
       // jDateChooser1.setDateFormatString("dd/MM/yyyy");
        //jDateChooser2.setDateFormatString("dd/MM/yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String d = sdf.format( jDateChooser1.getDate());
         String d2 = sdf.format( jDateChooser2.getDate());
        try{String url = "jdbc:sqlite:student.db";
           Connection con2 = DriverManager.getConnection(url);
            String sql8="insert into visiters"
                +"(name,nic,resp,indate,outdate,hostel,roomno,discrip,vid) values(?,?,?,?,?,?,?,?,?)";
             pst=con2.prepareStatement(sql8);
  pst.setString(1, visnam.getText()); 
  pst.setString(2, nic.getText()); 
  pst.setString(3, resp.getText()); 
  pst.setString(4, d); 
  pst.setString(5, d2); 
  pst.setString(6,(String) jComboBox5.getSelectedItem()); 
  pst.setString(7, vroom.getText()); 
  pst.setString(8, dis.getText()); 
  pst.setString(9, vid.getText()); 
  
            // set parameters  pst.setString(3, (String) txt_gender.getSelectedItem());
           
        pst.execute();
         //reset();
           // msg.setText("Successfully inserted Data");
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"error visiters" ); 
          // Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
         }
         showvisiters();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        // TODO add your handling code here:
       // "DELETE FROM details WHERE indexNo = '"+search.getText()+"'";
         //jDateChooser1.setDateFormatString("dd/MM/yyyy");
        //jDateChooser2.setDateFormatString("dd/MM/yyyy");
        try{
            String url = "jdbc:sqlite:student.db";
           Connection con2 = DriverManager.getConnection(url);
            String sql9="UPDATE visiters SET name = ?, nic = ?, resp = ?, indate = ?, outdate = ?, hostel = ?, roomno = ?, discrip = ? WHERE vid = '"+vid.getText()+"'";
               
          
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String d1 = sdf.format( jDateChooser1.getDate());
         String d22 = sdf.format( jDateChooser2.getDate());
               
               pst=con2.prepareStatement(sql9);
             pst.setString(1, visnam.getText()); 
           pst.setString(2, nic.getText()); 
  pst.setString(3, resp.getText()); 
  pst.setString(4, d1); 
 // pst.setDate(1, (java.sql.Date) jDateChooser2.getDate());
  pst.setString(5, d22); 
  pst.setString(6,(String) jComboBox5.getSelectedItem()); 
  pst.setString(7, vroom.getText()); 
  pst.setString(8, dis.getText()); 
              pst.execute();
         JOptionPane.showMessageDialog(null,"successfully updated" );
         // showvisiters();
     
            } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "Filled data types are wrong! please check & fill details correctly");
        }
      
        showvisiters();
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        // TODO add your handling code here: // "DELETE FROM details WHERE indexNo = '"+search.getText()+"'";
        try{
            //String url = "jdbc:sqlite:student.db";
           //Connection con2 = DriverManager.getConnection(url);
            String sql10="DELETE FROM visiters WHERE vid = '"+vid.getText()+"'";
            // st =  con2.createStatement();

            ///rs1.next();
            int option = JOptionPane.showConfirmDialog(this, "Are you sure to delete this Search Student?", "Confirm search Deletion", JOptionPane.ERROR_MESSAGE);
            if (option == 0) {
                st.executeQuery(sql10);
                
        visnam.setText("");
        nic.setText("");
        resp.setText("");
         jDateChooser1.setDate(null);
        jDateChooser2.setDate(null);
        vroom.setText("");
        dis.setText("");
         vid.setText("");
            }
        } catch (SQLException e) {
             //JOptionPane.showMessageDialog(null, "error del visiters");
        }
        showvisiters();
        
    }//GEN-LAST:event_jButton29ActionPerformed

    private void vroomKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vroomKeyPressed
        // TODO add your handling code here:
         char c = evt.getKeyChar();
        if(Character.isLetter(c) ){
            vroom.setEditable(false);
            //JOptionPane.showMessageDialog(null,"Plaese Enter Number");
        }else{
           vroom.setEditable(true);
        }
    }//GEN-LAST:event_vroomKeyPressed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        // TODO add your handling code here:
         jPanel12.setVisible(false);
          jPanel1.setVisible(true);
         jPanel2.setVisible(false);
            jPanel4.setVisible(false);
            jPanel5.setVisible(false);
            jPanel17.setVisible(false);
        jPanel21.setVisible(true);
         jPanel22.setVisible(false);
    }//GEN-LAST:event_jButton26ActionPerformed

    private void searchrnKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchrnKeyReleased
        // TODO add your handling code here:
         try{ //Class.forName("org.sqlite.JDBC");
           //String url = "jdbc:sqlite:student.db";
          // Connection con2 = DriverManager.getConnection(url);
   String query1777 = "SELECT * FROM "+gethos2()+" WHERE roomnumber = '"+searchrn.getText()+"'";
    //st =  con2.createStatement();
     ResultSet rs11 = st.executeQuery(query1777);
     if(rs11.next()){
         b1.setText(rs11.getString("bed1"));
          b2.setText(rs11.getString("bed2"));
           b3.setText(rs11.getString("bed3"));
            b4.setText(rs11.getString("bed4"));
          r.setText("");
     }else{
        // JOptionPane.showMessageDialog(null, "inncrect room number, Please check!");
         b1.setText("");
          b2.setText("");
           b3.setText("");
            b4.setText("");
            r.setText("             Not filled or wrong room number");
     }
       }
        catch(Exception ex)
       {
          JOptionPane.showMessageDialog(null," inncrect room number" );
       }finally
{
       try
       {
         st.close();
        
       }
       catch(Exception e)
       {
          JOptionPane.showMessageDialog(null,e );
       }
} 
    }//GEN-LAST:event_searchrnKeyReleased

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
        showvehicle();
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void vehKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vehKeyReleased
        // TODO add your handling code here:
        gethos3();
        autoshowvehicle();
    }//GEN-LAST:event_vehKeyReleased

    private void vehActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vehActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vehActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int selectedRowIndex = jTable1.getSelectedRow();
        vnu.setText(model.getValueAt(selectedRowIndex, 0).toString());
        vmo.setText(model.getValueAt(selectedRowIndex, 1).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int selectedRowIndex = jTable1.getSelectedRow();
        String t = model.getValueAt(selectedRowIndex, 2).toString();

        try {
            //st =  con1.createStatement();
            String query88 = "UPDATE details SET vnumber = '', vmodel = '' WHERE indexNo = "+t+"";
            st.executeUpdate(query88);
            JOptionPane.showMessageDialog(null,"succssfully deleted" );
            showvehicle();
        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int selectedRowIndex = jTable1.getSelectedRow();
        String t = model.getValueAt(selectedRowIndex, 2).toString();

        try {
            //st =  con1.createStatement();
            String query55 = "UPDATE details SET vnumber = '"+vnu.getText()+"', vmodel = '"+vmo.getText()+"' WHERE indexNo = "+t+"";
            st.executeUpdate(query55);
            JOptionPane.showMessageDialog(null,"succssfully updated" );
            showvehicle();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error updated" );
        }
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        // TODO add your handling code here:
       // String value1=sname.getText();
            //JFileChooser dialog = new JFileChooser();
        fileChooser.setSelectedFile(new File(""+in1.getText()+"-"+in1.getText()+".pdf"));
        int dialogResult = fileChooser.showSaveDialog(null);
        if (dialogResult==JFileChooser.APPROVE_OPTION){
            try {
                String filePath = fileChooser.getSelectedFile().getPath();
                //Document myDocument = new Document();
                Document myDocument = new Document();
                
                
                try {
                    PdfWriter myWriter = PdfWriter.getInstance(myDocument, new FileOutputStream(filePath));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                }
                PdfPTable mytable = new PdfPTable(2);
                
                
                myDocument.open();
                
                String queryre = "SELECT indexNo, name FROM details WHERE indexNo BETWEEN '"+in1.getText()+"' AND '"+in2.getText()+"'";
                
               // PdfPTable mytable = new PdfPTable(2);
                PdfPCell tablecell;
                //st =  con1.createStatement();
               // myDocument.add((new Paragraph("index and names ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                ResultSet re = st.executeQuery(queryre);
                while(re.next()){
                    String index = re.getString("indexNo");
                    tablecell = new PdfPCell(new Phrase(index));
                    mytable.addCell(tablecell);
                    String name = re.getString("name");
                    tablecell = new PdfPCell(new Phrase(name));
                    mytable.addCell(tablecell);
                }
                
                
                
                // myDocument.add((new Paragraph("\n ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                // myDocument.add((new Paragraph("\n ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                // myDocument.add((new Paragraph("Sinature ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                
                myDocument.newPage();
                 myDocument.add(new Paragraph("STUDENT'S HOSTEL REGISTRATION DETAILS",FontFactory.getFont(FontFactory.TIMES_BOLD,20,Font.BOLD )));
                     myDocument.add((new Paragraph("Wayamba university of Sri Lanka",FontFactory.getFont(FontFactory.TIMES_ROMAN,16,Font.BOLD)))); 
                     myDocument.add((new Paragraph("Index No: "+in1.getText()+" "+"to"+" "+""+in1.getText()+"",FontFactory.getFont(FontFactory.TIMES_ROMAN,13,Font.BOLD)))); 
                     myDocument.add(new Paragraph(new Date().toString()));
                       myDocument.add(new Paragraph("------------------------------------------------------------------------------------------------------------------------"));
               // myDocument.add((new Paragraph("ttttttttttttt ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                myDocument.add(mytable);
                myDocument.close();
                JOptionPane.showMessageDialog(null,"Report was successfully generated");
            } catch (DocumentException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
                      
              
              
               
                 
              
                
        }
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        // TODO add your handling code here: String e3 = (String) jComboBox4.getSelectedItem();
       
         String h = (String) jComboBox6.getSelectedItem();
          String f = (String) faculty1.getSelectedItem();
           String y = (String) jComboBox1.getSelectedItem();
         fileChooser.setSelectedFile(new File(""+h+"-"+f+"-"+y+".pdf"));
        int dialogResult = fileChooser.showSaveDialog(null);
        if (dialogResult==JFileChooser.APPROVE_OPTION){
            try {
                String filePath = fileChooser.getSelectedFile().getPath();
                //Document myDocument = new Document();
                Document myDocument = new Document();
                
                
                try {
                    PdfWriter myWriter = PdfWriter.getInstance(myDocument, new FileOutputStream(filePath));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                }
                PdfPTable mytable = new PdfPTable(2);
                
                
                myDocument.open();
                
                String queryr1e = "SELECT indexNo, name FROM details WHERE hostel = '"+h+"' AND faculty = '"+f+"' AND year = '"+y+"'";
                
               // PdfPTable mytable = new PdfPTable(2);
                PdfPCell tablecell;
                //st =  con1.createStatement();
               // myDocument.add((new Paragraph("index and names ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                ResultSet re1 = st.executeQuery(queryr1e);
                while(re1.next()){
                    String index = re1.getString("indexNo");
                    tablecell = new PdfPCell(new Phrase(index));
                    mytable.addCell(tablecell);
                    String name = re1.getString("name");
                    tablecell = new PdfPCell(new Phrase(name));
                    mytable.addCell(tablecell);
                }
                
                
                
                // myDocument.add((new Paragraph("\n ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                // myDocument.add((new Paragraph("\n ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                // myDocument.add((new Paragraph("Sinature ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                
                myDocument.newPage();
                 myDocument.add(new Paragraph("STUDENT'S HOSTEL REGISTRATION DETAILS",FontFactory.getFont(FontFactory.TIMES_BOLD,20,Font.BOLD )));
                    myDocument.add((new Paragraph("Wayamba university of Sri Lanka",FontFactory.getFont(FontFactory.TIMES_ROMAN,16,Font.BOLD)))); 
                    myDocument.add((new Paragraph("Hostel:- "+h+", Faculty:- "+f+", Year:- "+y+"",FontFactory.getFont(FontFactory.TIMES_ROMAN,13,Font.BOLD)))); 
                    myDocument.add(new Paragraph(new Date().toString()));
                    myDocument.add(new Paragraph("------------------------------------------------------------------------------------------------------------------------"));
               // myDocument.add((new Paragraph("ttttttttttttt ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                myDocument.add(mytable);
                myDocument.close();
                JOptionPane.showMessageDialog(null,"Report was successfully generated");
            } catch (DocumentException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
                      
              
              
               
                 
              
                
        }
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jComboBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox6ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        // TODO add your handling code here:
        
         String h = (String) jComboBox7.getSelectedItem();
          
         fileChooser.setSelectedFile(new File("vehicle details"+""+h+".pdf"));
        int dialogResult = fileChooser.showSaveDialog(null);
        if (dialogResult==JFileChooser.APPROVE_OPTION){
            try {
                String filePath = fileChooser.getSelectedFile().getPath();
                //Document myDocument = new Document();
                Document myDocument = new Document();
                
                
                try {
                    PdfWriter myWriter = PdfWriter.getInstance(myDocument, new FileOutputStream(filePath));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                }
                PdfPTable mytable = new PdfPTable(5);
                
                
                myDocument.open();
                
                String queryr11e = "SELECT indexNo, name ,vmodel, vnumber, faculty FROM details WHERE hostel = '"+h+"' AND vnumber != '' ";
                
               // PdfPTable mytable = new PdfPTable(2);
                PdfPCell tablecell;
                //st =  con1.createStatement();
               // myDocument.add((new Paragraph("index and names ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                ResultSet ree1 = st.executeQuery(queryr11e);
                while(ree1.next()){
                    String index = ree1.getString("indexNo");
                    tablecell = new PdfPCell(new Phrase(index));
                    mytable.addCell(tablecell);
                    String name = ree1.getString("name");
                    tablecell = new PdfPCell(new Phrase(name));
                    mytable.addCell(tablecell);
                    String model = ree1.getString("vmodel");
                    tablecell = new PdfPCell(new Phrase(model));
                    mytable.addCell(tablecell);
                    String number = ree1.getString("vnumber");
                    tablecell = new PdfPCell(new Phrase(number));
                    mytable.addCell(tablecell);
                     String fac = ree1.getString("faculty");
                    tablecell = new PdfPCell(new Phrase(fac));
                    mytable.addCell(tablecell);
                }
                
                
                
                // myDocument.add((new Paragraph("\n ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                // myDocument.add((new Paragraph("\n ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                // myDocument.add((new Paragraph("Sinature ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                
                myDocument.newPage();
                 myDocument.add(new Paragraph("STUDENT'S VEHICLES REGISTRATION DETAILS",FontFactory.getFont(FontFactory.TIMES_BOLD,20,Font.BOLD )));
                     myDocument.add((new Paragraph("Wayamba university of Sri Lanka",FontFactory.getFont(FontFactory.TIMES_ROMAN,16,Font.BOLD)))); 
                       myDocument.add((new Paragraph("vehicle details : Hostel :-"+" "+h+"",FontFactory.getFont(FontFactory.TIMES_ROMAN,13,Font.BOLD)))); 
                    myDocument.add(new Paragraph(new Date().toString()));
                       myDocument.add(new Paragraph("------------------------------------------------------------------------------------------------------------------------"));
               // myDocument.add((new Paragraph("ttttttttttttt ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                myDocument.add(mytable);
                myDocument.close();
                JOptionPane.showMessageDialog(null,"Report was successfully generated");
            } catch (DocumentException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
                      
              
              
               
                 
              
                
        }
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jComboBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox7ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        // TODO add your handling code here:
        //String sql8="insert into visiters"
               // +"(name,nic,resp,indate,outdate,hostel,roomno,discrip) values(?,?,?,?,?,?,?,?)";
                 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String d = sdf.format( jDateChooser3.getDate());
          String d1 = sdf.format( jDateChooser4.getDate());
        //  String h = (String) jComboBox7.getSelectedItem();
          
         fileChooser.setSelectedFile(new File("visiter details"+".pdf"));
        int dialogResult = fileChooser.showSaveDialog(null);
        if (dialogResult==JFileChooser.APPROVE_OPTION){
            try {
                String filePath = fileChooser.getSelectedFile().getPath();
                //Document myDocument = new Document();
                Document myDocument = new Document();
                
                
                try {
                    PdfWriter myWriter = PdfWriter.getInstance(myDocument, new FileOutputStream(filePath));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                }
                PdfPTable mytable = new PdfPTable(5);
                
                
                myDocument.open();
                
                String queryr11ev = "SELECT name,nic,indate,outdate,hostel FROM visiters WHERE indate BETWEEN '"+d+"' AND '"+d1+"'";
                
               // PdfPTable mytable = new PdfPTable(2);
                PdfPCell tablecell;
                //st =  con1.createStatement();
               // myDocument.add((new Paragraph("index and names ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                ResultSet ree1v = st.executeQuery(queryr11ev);
                while(ree1v.next()){
                    String name = ree1v.getString("name");
                    tablecell = new PdfPCell(new Phrase(name));
                    mytable.addCell(tablecell);
                    String nic = ree1v.getString("nic");
                    tablecell = new PdfPCell(new Phrase(nic));
                    mytable.addCell(tablecell);
                    String in = ree1v.getString("indate");
                    tablecell = new PdfPCell(new Phrase(in));
                    mytable.addCell(tablecell);
                    String out = ree1v.getString("outdate");
                    tablecell = new PdfPCell(new Phrase(out));
                    mytable.addCell(tablecell);
                     String hos = ree1v.getString("hostel");
                    tablecell = new PdfPCell(new Phrase(hos));
                    mytable.addCell(tablecell);
                }
                
                
                
                // myDocument.add((new Paragraph("\n ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                // myDocument.add((new Paragraph("\n ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                // myDocument.add((new Paragraph("Sinature ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                
                myDocument.newPage();
                 myDocument.add(new Paragraph("VISITER'S HOSTEL REGISTRATION DETAILS",FontFactory.getFont(FontFactory.TIMES_BOLD,20,Font.BOLD )));
                     myDocument.add((new Paragraph("Wayamba university of Sri Lanka",FontFactory.getFont(FontFactory.TIMES_ROMAN,16,Font.BOLD)))); 
                      myDocument.add((new Paragraph("Visiter's Details "+d+": to :"+d1+"",FontFactory.getFont(FontFactory.TIMES_ROMAN,13,Font.BOLD)))); 
                    myDocument.add(new Paragraph(new Date().toString()));
                       myDocument.add(new Paragraph("------------------------------------------------------------------------------------------------------------------------"));
               // myDocument.add((new Paragraph("ttttttttttttt ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                myDocument.add(mytable);
                myDocument.close();
                JOptionPane.showMessageDialog(null,"Report was successfully generated");
            } catch (DocumentException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
                      
              
              
               
                 
              
                
        }
    }//GEN-LAST:event_jButton34ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        // TODO add your handling code here:
         String h = (String) jComboBox8.getSelectedItem();
        fileChooser.setSelectedFile(new File(""+h+""+in3.getText()+"-"+in4.getText()+".pdf"));
        int dialogResult = fileChooser.showSaveDialog(null);
        if (dialogResult==JFileChooser.APPROVE_OPTION){
            try {
                String filePath = fileChooser.getSelectedFile().getPath();
                //Document myDocument = new Document();
                Document myDocument = new Document();
                
                
                try {
                    PdfWriter myWriter = PdfWriter.getInstance(myDocument, new FileOutputStream(filePath));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                }
                PdfPTable mytable = new PdfPTable(3);
                
                
                myDocument.open();
                
                String queryrer = "SELECT indexNo, name, room FROM details WHERE hostel = '"+h+"' AND room BETWEEN '"+in3.getText()+"' AND '"+in4.getText()+"' ORDER BY room ASC";
                
               // PdfPTable mytable = new PdfPTable(2);
                PdfPCell tablecell;
                //st =  con1.createStatement();
               // myDocument.add((new Paragraph("index and names ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                ResultSet rer = st.executeQuery(queryrer);
                while(rer.next()){
                    String index = rer.getString("indexNo");
                    tablecell = new PdfPCell(new Phrase(index));
                    mytable.addCell(tablecell);
                   
                    String name = rer.getString("name");
                    tablecell = new PdfPCell(new Phrase(name));
                    mytable.addCell(tablecell);
                     String room = rer.getString("room");
                    tablecell = new PdfPCell(new Phrase(room));
                    mytable.addCell(tablecell);
                }
                
                
                
                // myDocument.add((new Paragraph("\n ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                // myDocument.add((new Paragraph("\n ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                // myDocument.add((new Paragraph("Sinature ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                
                myDocument.newPage();
                 myDocument.add(new Paragraph("STUDENT'S HOSTEL REGISTRATION DETAILS",FontFactory.getFont(FontFactory.TIMES_BOLD,20,Font.BOLD )));
                     myDocument.add((new Paragraph("Wayamba university of Sri Lanka",FontFactory.getFont(FontFactory.TIMES_ROMAN,16,Font.BOLD)))); 
                         myDocument.add((new Paragraph("Hostel:"+h+""+" "+"room"+" "+in3.getText()+" "+"to"+" "+in4.getText()+"",FontFactory.getFont(FontFactory.TIMES_ROMAN,13,Font.BOLD)))); 
                     myDocument.add(new Paragraph(new Date().toString()));
                       myDocument.add(new Paragraph("------------------------------------------------------------------------------------------------------------------------"));
               // myDocument.add((new Paragraph("ttttttttttttt ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                myDocument.add(mytable);
                myDocument.close();
                JOptionPane.showMessageDialog(null,"Report was successfully generated");
            } catch (DocumentException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
                      
              
              
               
                 
              
                
        }
    }//GEN-LAST:event_jButton35ActionPerformed

    private void jComboBox8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox8ActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
          DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
        int selectedRowIndex = jTable3.getSelectedRow();
       
       visnam1.setText(model.getValueAt(selectedRowIndex, 0).toString());
        roomN.setText(model.getValueAt(selectedRowIndex, 1).toString());
      
       fine.setText(model.getValueAt(selectedRowIndex, 2).toString());
        //resp.setText(model.getValueAt(selectedRowIndex, 3).toString());
        try {
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse( model.getValueAt(selectedRowIndex, 3).toString());
            //java.util.Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse( model.getValueAt(selectedRowIndex, 5).toString());
              jDateChooser5.setDate(date);
              //jDateChooser2.setDate(date1);
        } catch (ParseException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
      
       // jDateChooser2.setDate((Date) model.getValueAt(selectedRowIndex, 5));
     //jComboBox5.setActionCommand((String) model.getValueAt(selectedRowIndex, 4));
        dis1.setText(model.getValueAt(selectedRowIndex, 7).toString());
         //dis.setText(model.getValueAt(selectedRowIndex, 8).toString());
    }//GEN-LAST:event_jTable3MouseClicked

    private void jComboBox9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox9ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        try {
            // TODO add your handling code here:
           SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String df = sdf1.format( jDateChooser5.getDate());
            // String d2 = sdf.format( jDateChooser2.getDate());
            
            String url = "jdbc:sqlite:student.db";
            Connection con2 = DriverManager.getConnection(url);
           
              String sqlf8="insert into fines"
            +"(iin,roomN,fine,fdate,fac,hostel,year,discrip) values(?,?,?,?,?,?,?,?)";
            pst = con2.prepareStatement(sqlf8);
             pst.setString(1, visnam1.getText());
            pst.setString(2, roomN.getText());
           pst.setString(3, fine.getText());
            pst.setString(4, df);
            pst.setString(5, (String) faculty2.getSelectedItem());
           pst.setString(6,(String) jComboBox9.getSelectedItem());
            pst.setString(7, (String) year1.getSelectedItem());
            pst.setString(8, dis1.getText());
             pst.execute();
           
           
        } catch (SQLException ex) {
           // Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error inserting data");
        }
         showfines();
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        // TODO add your handling code here:
              try{
            String url = "jdbc:sqlite:student.db";
           Connection con2 = DriverManager.getConnection(url);
            String sqfl9="UPDATE fines SET roomN = ?, fine = ?, fdate = ?, fac = ?, hostel = ?, year = ?, discrip = ? WHERE iin = '"+visnam1.getText()+"'";
               
          
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String d1 = sdf.format( jDateChooser5.getDate());
        //String d22 = sdf.format( jDateChooser2.getDate());
               
               pst=con2.prepareStatement(sqfl9);
             pst.setString(1, roomN.getText()); 
           pst.setString(2, fine.getText()); 
  //pst.setString(3, resp.getText()); 
  pst.setString(3, d1); 
 // pst.setDate(1, (java.sql.Date) jDateChooser2.getDate());
 // pst.setString(5, d22); 
  pst.setString(4,(String) faculty2.getSelectedItem()); 
   pst.setString(5,(String) jComboBox9.getSelectedItem()); 
    pst.setString(6,(String) year1.getSelectedItem()); 
  pst.setString(7, dis1.getText()); 
 // pst.setString(8, dis.getText()); 
              pst.execute();
         JOptionPane.showMessageDialog(null,"successfully updated" );
         // showvisiters();
     
            } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "Filled data types are wrong! please check & fill details correctly");
        }
      
        showfines();
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        // TODO add your handling code here:
        try{
            //String url = "jdbc:sqlite:student.db";
           //Connection con2 = DriverManager.getConnection(url);
            String fsql10="DELETE FROM fines WHERE iin = '"+visnam1.getText()+"'";
            // st =  con2.createStatement();

            ///rs1.next();
            int option = JOptionPane.showConfirmDialog(this, "Are you sure to delete this Search Student?", "Confirm search Deletion", JOptionPane.ERROR_MESSAGE);
            if (option == 0) {
                st.executeQuery(fsql10);
                
        visnam1.setText("");
        roomN.setText("");
        fine.setText("");
        jDateChooser5.setDate(null);
       // jDateChooser2.setDate(null);
        dis1.setText("");
        
       
            }
        } catch (SQLException e) {
             //JOptionPane.showMessageDialog(null, "error del visiters");
        }
         showfines();
    }//GEN-LAST:event_jButton36ActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        // TODO add your handling code here:
         jPanel12.setVisible(false);
          jPanel1.setVisible(true);
         jPanel2.setVisible(false);
            jPanel4.setVisible(false);
            jPanel5.setVisible(false);
            jPanel17.setVisible(false);
        jPanel21.setVisible(false);
         jPanel22.setVisible(true);
          showfines();
    }//GEN-LAST:event_jButton37ActionPerformed

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        // TODO add your handling code here:
         visnam.setText("");
          nic.setText("");
           resp.setText("");
            vroom.setText("");
             dis.setText("");
              vid.setText(null);
              jDateChooser1.setDate(null);
                jDateChooser2.setDate(null);
    }//GEN-LAST:event_jButton40ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
        autoshowfines();
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
        // TODO add your handling code here:
           String h = (String) jComboBox10.getSelectedItem();
          
         fileChooser.setSelectedFile(new File("Fine report-"+h+".pdf"));
        int dialogResult = fileChooser.showSaveDialog(null);
        if (dialogResult==JFileChooser.APPROVE_OPTION){
            try {
                String filePath = fileChooser.getSelectedFile().getPath();
                //Document myDocument = new Document();
                Document myDocument = new Document();
                
                
                try {
                    PdfWriter myWriter = PdfWriter.getInstance(myDocument, new FileOutputStream(filePath));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                }
                PdfPTable mytable = new PdfPTable(5);
                
                
                myDocument.open();
                
                String queryr11ef = "SELECT iin, roomN ,fine, fac, year FROM fines WHERE hostel = '"+h+"'";
                
               // PdfPTable mytable = new PdfPTable(2);
                PdfPCell tablecell;
                //st =  con1.createStatement();
               // myDocument.add((new Paragraph("index and names ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                ResultSet ree1f = st.executeQuery(queryr11ef);
                while(ree1f.next()){
                    String iin = ree1f.getString("iin");
                    tablecell = new PdfPCell(new Phrase(iin));
                    mytable.addCell(tablecell);
                    String rn = ree1f.getString("roomN");
                    tablecell = new PdfPCell(new Phrase(rn));
                    mytable.addCell(tablecell);
                    String fi = ree1f.getString("fine");
                    tablecell = new PdfPCell(new Phrase(fi));
                    mytable.addCell(tablecell);
                    String fac = ree1f.getString("fac");
                    tablecell = new PdfPCell(new Phrase(fac));
                    mytable.addCell(tablecell);
                     String ye = ree1f.getString("year");
                    tablecell = new PdfPCell(new Phrase(ye));
                    mytable.addCell(tablecell);
                }
                
                
                
                // myDocument.add((new Paragraph("\n ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                // myDocument.add((new Paragraph("\n ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                // myDocument.add((new Paragraph("Sinature ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                
                myDocument.newPage();
                 myDocument.add(new Paragraph("STUDENT'S FINES DETAILS",FontFactory.getFont(FontFactory.TIMES_BOLD,20,Font.BOLD )));
                     myDocument.add((new Paragraph("Wayamba university of Sri Lanka",FontFactory.getFont(FontFactory.TIMES_ROMAN,16,Font.BOLD)))); 
                     myDocument.add((new Paragraph("Fine Report - "+h+"",FontFactory.getFont(FontFactory.TIMES_ROMAN,13,Font.BOLD)))); 
                     myDocument.add(new Paragraph(new Date().toString()));
                       myDocument.add(new Paragraph("------------------------------------------------------------------------------------------------------------------------"));
               // myDocument.add((new Paragraph("ttttttttttttt ",FontFactory.getFont(FontFactory.TIMES_ROMAN,11,Font.PLAIN))));
                myDocument.add(mytable);
                myDocument.close();
                JOptionPane.showMessageDialog(null,"Report was successfully generated");
            } catch (DocumentException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
                      
              
              
               
                 
              
                
        }
    }//GEN-LAST:event_jButton39ActionPerformed

    private void jComboBox10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox10ActionPerformed

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        // TODO add your handling code here:
       Admin infoa = new Admin();
              infoa.setVisible(true);
    }//GEN-LAST:event_jButton38ActionPerformed

    private void searchrnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchrnKeyPressed
        // TODO add your handling code here:
         char c = evt.getKeyChar();
        if(Character.isLetter(c) ){
            searchrn.setEditable(false);
            //JOptionPane.showMessageDialog(null,"Plaese Enter Number");
        }else{
           searchrn.setEditable(true);
        }
    }//GEN-LAST:event_searchrnKeyPressed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
       // jButton1.setBackground(Color.green);
        jButton1.setForeground(Color.blue);
        jButton2.setForeground(Color.black);
        jButton3.setForeground(Color.black);
        jButton4.setForeground(Color.black);
        jButton9.setForeground(Color.black);
        jButton26.setForeground(Color.black);
         jButton37.setForeground(Color.black);
          jButton38.setForeground(Color.black);
        
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        // jButton2.setBackground(Color.green);
        jButton1.setForeground(Color.black);
        jButton2.setForeground(Color.blue);
        jButton3.setForeground(Color.black);
        jButton4.setForeground(Color.black);
        jButton9.setForeground(Color.black);
        jButton26.setForeground(Color.black);
         jButton37.setForeground(Color.black);
          jButton38.setForeground(Color.black);
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // TODO add your handling code here:
        // jButton3.setBackground(Color.green);
        jButton1.setForeground(Color.black);
        jButton2.setForeground(Color.black);
        jButton3.setForeground(Color.blue);
        jButton4.setForeground(Color.black);
        jButton9.setForeground(Color.black);
        jButton26.setForeground(Color.black);
         jButton37.setForeground(Color.black);
          jButton38.setForeground(Color.black);
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        // TODO add your handling code here:
         jButton1.setForeground(Color.black);
        jButton2.setForeground(Color.black);
        jButton3.setForeground(Color.black);
        jButton4.setForeground(Color.blue);
        jButton9.setForeground(Color.black);
        jButton26.setForeground(Color.black);
         jButton37.setForeground(Color.black);
          jButton38.setForeground(Color.black);
    }//GEN-LAST:event_jButton4MouseClicked

    private void jButton9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseClicked
        // TODO add your handling code here:
         jButton1.setForeground(Color.black);
        jButton2.setForeground(Color.black);
        jButton3.setForeground(Color.black);
        jButton4.setForeground(Color.black);
        jButton9.setForeground(Color.blue);
        jButton26.setForeground(Color.black);
         jButton37.setForeground(Color.black);
          jButton38.setForeground(Color.black);
    }//GEN-LAST:event_jButton9MouseClicked

    private void jButton26MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton26MouseClicked
        // TODO add your handling code here:
          jButton1.setForeground(Color.black);
        jButton2.setForeground(Color.black);
        jButton3.setForeground(Color.black);
        jButton4.setForeground(Color.black);
        jButton9.setForeground(Color.black);
        jButton26.setForeground(Color.blue);
         jButton37.setForeground(Color.black);
          jButton38.setForeground(Color.black);
    }//GEN-LAST:event_jButton26MouseClicked

    private void jButton37MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton37MouseClicked
        // TODO add your handling code here:
          jButton1.setForeground(Color.black);
        jButton2.setForeground(Color.black);
        jButton3.setForeground(Color.black);
        jButton4.setForeground(Color.black);
        jButton9.setForeground(Color.black);
        jButton26.setForeground(Color.black);
         jButton37.setForeground(Color.blue);
          jButton38.setForeground(Color.black);
    }//GEN-LAST:event_jButton37MouseClicked

    private void jButton38MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton38MouseClicked
        // TODO add your handling code here:
          jButton1.setForeground(Color.black);
        jButton2.setForeground(Color.black);
        jButton3.setForeground(Color.black);
        jButton4.setForeground(Color.black);
        jButton9.setForeground(Color.black);
        jButton26.setForeground(Color.black);
         jButton37.setForeground(Color.black);
          jButton38.setForeground(Color.blue);
    }//GEN-LAST:event_jButton38MouseClicked

    private void b1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b1KeyPressed
        // TODO add your handling code here:
         char c = evt.getKeyChar();
        if(Character.isLetter(c) ){
            b1.setEditable(false);
            //JOptionPane.showMessageDialog(null,"Plaese Enter Number");
        }else{
            b1.setEditable(true);
        }
    }//GEN-LAST:event_b1KeyPressed

    private void b2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b2KeyPressed
        // TODO add your handling code here:
         char c = evt.getKeyChar();
        if(Character.isLetter(c) ){
            b2.setEditable(false);
            //JOptionPane.showMessageDialog(null,"Plaese Enter Number");
        }else{
           b2.setEditable(true);
        }
    }//GEN-LAST:event_b2KeyPressed

    private void b3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b3KeyPressed
        // TODO add your handling code here:
         char c = evt.getKeyChar();
        if(Character.isLetter(c) ){
            b3.setEditable(false);
            //JOptionPane.showMessageDialog(null,"Plaese Enter Number");
        }else{
           b3.setEditable(true);
        }
    }//GEN-LAST:event_b3KeyPressed

    private void b4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b4KeyPressed
        // TODO add your handling code here:
         char c = evt.getKeyChar();
        if(Character.isLetter(c) ){
           b4.setEditable(false);
            //JOptionPane.showMessageDialog(null,"Plaese Enter Number");
        }else{
            b4.setEditable(true);
        }
    }//GEN-LAST:event_b4KeyPressed

    private void in1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_in1KeyPressed
        // TODO add your handling code here:
         char c = evt.getKeyChar();
        if(Character.isLetter(c) ){
           in1.setEditable(false);
            //JOptionPane.showMessageDialog(null,"Plaese Enter Number");
        }else{
           in1.setEditable(true);
        }
    }//GEN-LAST:event_in1KeyPressed

    private void in2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_in2KeyPressed
        // TODO add your handling code here:
          char c = evt.getKeyChar();
        if(Character.isLetter(c) ){
           in2.setEditable(false);
            //JOptionPane.showMessageDialog(null,"Plaese Enter Number");
        }else{
           in2.setEditable(true);
        }
    }//GEN-LAST:event_in2KeyPressed

    private void in3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_in3KeyPressed
        // TODO add your handling code here:
          char c = evt.getKeyChar();
        if(Character.isLetter(c) ){
           in3.setEditable(false);
            //JOptionPane.showMessageDialog(null,"Plaese Enter Number");
        }else{
           in3.setEditable(true);
        }
    }//GEN-LAST:event_in3KeyPressed

    private void in4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_in4KeyPressed
        // TODO add your handling code here:
          char c = evt.getKeyChar();
        if(Character.isLetter(c) ){
           in4.setEditable(false);
            //JOptionPane.showMessageDialog(null,"Plaese Enter Number");
        }else{
           in4.setEditable(true);
        }
    }//GEN-LAST:event_in4KeyPressed

    private void visnam1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_visnam1KeyPressed
        // TODO add your handling code here:
          char c = evt.getKeyChar();
        if(Character.isLetter(c) ){
          visnam1.setEditable(false);
            //JOptionPane.showMessageDialog(null,"Plaese Enter Number");
        }else{
          visnam1.setEditable(true);
        }
    }//GEN-LAST:event_visnam1KeyPressed

    private void roomNKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_roomNKeyPressed
        // TODO add your handling code here:
          char c = evt.getKeyChar();
        if(Character.isLetter(c) ){
         roomN.setEditable(false);
            //JOptionPane.showMessageDialog(null,"Plaese Enter Number");
        }else{
          roomN.setEditable(true);
        }
    }//GEN-LAST:event_roomNKeyPressed

    private void fineKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fineKeyPressed
        // TODO add your handling code here:
          char c = evt.getKeyChar();
        if(Character.isLetter(c) ){
        fine.setEditable(false);
            //JOptionPane.showMessageDialog(null,"Plaese Enter Number");
        }else{
          fine.setEditable(true);
        }
    }//GEN-LAST:event_fineKeyPressed

    private void hnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hnameActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        // TODO add your handling code here:
            char c = evt.getKeyChar();
        if(Character.isLetter(c) ){
         jTextField1.setEditable(false);
            //JOptionPane.showMessageDialog(null,"Plaese Enter Number");
        }else{
          jTextField1.setEditable(true);
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    private void rnumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rnumKeyPressed
        // TODO add your handling code here:
           char c = evt.getKeyChar();
        if(Character.isLetter(c) ){
           rnum.setEditable(false);
            //JOptionPane.showMessageDialog(null,"Plaese Enter Number");
        }else{
            rnum.setEditable(true);
        }
    }//GEN-LAST:event_rnumKeyPressed

    private void blKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_blKeyPressed
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(Character.isLetter(c) ){
           bl.setEditable(false);
            //JOptionPane.showMessageDialog(null,"Plaese Enter Number");
        }else{
            bl.setEditable(true);
        }
    }//GEN-LAST:event_blKeyPressed

    private void parentnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_parentnameKeyPressed
        // TODO add your handling code here:
        char c= evt.getKeyChar();
if(Character.isLetter(c)||Character.isWhitespace(c)||Character.isISOControl(c)){
parentname.setEditable(true);}else{
   parentname.setEditable(false);
}
    }//GEN-LAST:event_parentnameKeyPressed
public void bed(String c){
         try {
             String q0 = "UPDATE "+gethos()+" SET "+c+" = "+index.getText()+" WHERE roomnumber = (SELECT MAX(roomnumber) FROM "+gethos()+")";
             
             st.executeUpdate(q0);
             msg.setText("Successfully inserted Data to "+c+"");
         } catch (SQLException ex) {
             
         }
}
 public int fillroom(String a){
    int b =0;
        try{
               String querry101 = "SELECT roomnumber FROM "+a+" WHERE roomnumber = (SELECT MAX(roomnumber) FROM "+a+")";
             
             ResultSet rs = st.executeQuery(querry101);
              b = rs.getInt("roomnumber");
               } catch (SQLException ex) {
             //Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
         }
         return b;    
 }
 
    
    public int insert(String a){
    int b =0;
         try {
             
            // String url = "jdbc:sqlite:student.db";
            // Connection con = DriverManager.getConnection(url);
             
             String querry199 = "SELECT "+a+" FROM "+gethos()+" WHERE roomnumber = (SELECT MAX(roomnumber) FROM "+gethos()+")";
            // st =  con.createStatement();
             ResultSet rs = st.executeQuery(querry199);
              b = rs.getInt(a);
              
         } catch (SQLException ex) {
             //Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
         }finally
{
       try
       {
         st.close();
        
       }
       catch(Exception e)
       {
          JOptionPane.showMessageDialog(null,e );
       }
} 
         return b;      
}
    public int full(){
    int b =0;
         try {
             
             //String url = "jdbc:sqlite:student.db";
            // Connection con = DriverManager.getConnection(url);
             
             String querry166 = "SELECT roomnumber FROM "+gethos()+" WHERE roomnumber = (SELECT MAX(roomnumber) FROM "+gethos()+")";
            // st =  con.createStatement();
             ResultSet rs = st.executeQuery(querry166);
              b = rs.getInt("roomnumber");
              
         } catch (SQLException ex) {
             //Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
         }finally
{
       try
       {
         st.close();
        
       }
       catch(Exception e)
       {
          JOptionPane.showMessageDialog(null,e );
       }
} 
         return b;      
}
     public String gethos(){
        String ee = (String) jComboBox3.getSelectedItem();
        return ee;
    }
     public String gethos1(){
        String e1 = (String) jComboBox2.getSelectedItem();
        return e1;
    }
     public String gethos2(){
        String e2 = (String) jComboBox13.getSelectedItem();
        return e2;
    }
      public String gethos3(){
        String e3 = (String) jComboBox4.getSelectedItem();
        return e3;
    }
  
   
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
         
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField add;
    private javax.swing.JTextField address;
    private javax.swing.JLabel avi;
    private javax.swing.JLabel avi1;
    private javax.swing.JLabel avi2;
    private javax.swing.JTextField b1;
    private javax.swing.JTextField b2;
    private javax.swing.JTextField b3;
    private javax.swing.JTextField b4;
    private javax.swing.JTextField bed;
    private javax.swing.JTextField bl;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JTextField date;
    private javax.swing.JTextArea dis;
    private javax.swing.JTextArea dis1;
    private javax.swing.JTextField ema;
    private javax.swing.JTextField email;
    private javax.swing.JTextField fac;
    private javax.swing.JComboBox<String> faculty;
    private javax.swing.JComboBox<String> faculty1;
    private javax.swing.JComboBox<String> faculty2;
    private javax.swing.JTextField fine;
    private javax.swing.JTextField gen;
    private javax.swing.JComboBox<String> gender;
    private javax.swing.JTextField hname;
    private javax.swing.JTextField in1;
    private javax.swing.JTextField in2;
    private javax.swing.JTextField in3;
    private javax.swing.JTextField in4;
    private javax.swing.JTextField ind;
    private javax.swing.JTextField index;
    private javax.swing.JButton ins;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox10;
    private javax.swing.JComboBox<String> jComboBox13;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JComboBox<String> jComboBox9;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private com.toedter.calendar.JDateChooser jDateChooser4;
    private com.toedter.calendar.JDateChooser jDateChooser5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel msg;
    private javax.swing.JTextField name;
    private javax.swing.JTextField nic;
    private javax.swing.JTextField parentname;
    private javax.swing.JTextField parentphone;
    private javax.swing.JTextField pho;
    private javax.swing.JTextField phone;
    private javax.swing.JLabel pi;
    private javax.swing.JLabel pic1;
    private javax.swing.JTextField pname;
    private javax.swing.JTextField ppho;
    private javax.swing.JLabel r;
    private javax.swing.JLabel regd;
    private javax.swing.JTextField rel;
    private javax.swing.JComboBox<String> religon;
    private javax.swing.JTextField resp;
    private javax.swing.JTextField rnum;
    private javax.swing.JTextField room;
    private javax.swing.JTextField roomN;
    private javax.swing.JTextField search;
    private javax.swing.JTextField searchrn;
    private javax.swing.JTextField sname;
    private javax.swing.JTable table2;
    private javax.swing.JTable tablem;
    private javax.swing.JScrollPane tablemain;
    private javax.swing.JTable tableroom;
    private javax.swing.JButton up1;
    private javax.swing.JTextField veh;
    private javax.swing.JLabel vid;
    private javax.swing.JLabel vid1;
    private javax.swing.JTextField visnam;
    private javax.swing.JTextField visnam1;
    private javax.swing.JTextField vm;
    private javax.swing.JTextField vmo;
    private javax.swing.JTextField vmodel;
    private javax.swing.JTextField vn;
    private javax.swing.JTextField vnu;
    private javax.swing.JTextField vnumber;
    private javax.swing.JTextField vroom;
    private javax.swing.JTextField yea;
    private javax.swing.JComboBox<String> year;
    private javax.swing.JComboBox<String> year1;
    // End of variables declaration//GEN-END:variables
String filename = null; 
 byte[] stuimage = null;
 byte[] stuimage1 = null;
 student personalInfo = null;
     byte[] pic;
   ByteArrayOutputStream bos = null;
}

