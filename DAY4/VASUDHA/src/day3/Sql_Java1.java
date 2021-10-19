package day3;

import java.sql.*;

public class Sql_Java1{

    public void addCustomer(String cId , String cName , String cEmail , String cPh , String cAddress ,int X, int Y  ){

        Connection conn = null;
        try {
            //Class.forName("org.postgresql.Driver"); no need to register driver as we r using latest version of jdbc driver
            System.out.println("Connecting to database!!!...");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/ziggy1","postgres", "Leela@123");
            Statement st = conn.createStatement();
            st.executeQuery("INSERT INTO CUSTOMERS VALUES('C108', 'Arya', 'arya@gmail.com', 9999998888, 'Australia, Sydney d-no: 1/1', -11, -15);");
            System.out.println("Sucessfully Inserted!!!...");

//            ResultSet rs = st.executeQuery("SELECT * FROM CUSTOMERS;");
//            while(rs.next()){
//                System.out.println(rs.getString("cId"));
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Opened database successfully");

    }



    public static void main(String args[]) {




    }
}
