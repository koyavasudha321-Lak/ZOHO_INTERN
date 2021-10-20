package day4;

import java.sql.*;
import java.util.*;

public class Main{
    static int K;


    //Adding Customers
    public static void addCustomer(String cId, String cName, String cEmail, long cPh, String cAddress, int X, int Y) {


        Connection conn = null;

        try {
            //Class.forName("org.postgresql.Driver"); no need to register driver as we r using latest version of jdbc driver
            System.out.println("Connecting to database!!!...");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/ziggy1","postgres", "Leela@123");
            // String sql = ;

            PreparedStatement st = conn.prepareStatement("INSERT INTO CUSTOMERS VALUES(?, ?, ?, ?, ?, ?, ?);");


            st.setString(1, cId);
            st.setString(2, cName);
            st.setString(3, cEmail);
            st.setLong(4, cPh);
            st.setString(5, cAddress);
            st.setInt(6, X);
            st.setInt(7, Y);

            st.executeUpdate();



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

    //Adding Restaurants
    public static void addRestaurant(String rId, String dName, int dPrice, int X, int Y, String rName) {


        Connection conn = null;

        try {
            //Class.forName("org.postgresql.Driver"); no need to register driver as we r using latest version of jdbc driver
            System.out.println("Connecting to database!!!...");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/ziggy1","postgres", "Leela@123");
            // String sql = ;

            PreparedStatement st = conn.prepareStatement("INSERT INTO RESTAURANT VALUES(?, ?, ?, ?, ?, ?);");


            st.setString(1, rId);
            st.setString(2, dName);
            st.setInt(3, dPrice);
            st.setInt(4, X);
            st.setInt(5, Y);
            st.setString(6, rName);

            st.executeUpdate();

            System.out.println("Sucessfully Restaurant details Inserted!!!...");

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Opened database successfully");

    }

   //Adding Drivers
    public static void addDrivers(String dId, String dName, boolean dAvailable, int X, int Y) {


        Connection conn = null;

        try {



            //Class.forName("org.postgresql.Driver"); no need to register driver as we r using latest version of jdbc driver
            System.out.println("Connecting to database!!!...");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/ziggy1","postgres", "Leela@123");
            // String sql = ;

            PreparedStatement st = conn.prepareStatement("INSERT INTO DRIVERS VALUES(?, ?, ?, ?, ?);");


            st.setString(1, dId);
            st.setString(2, dName);
            st.setBoolean(3, dAvailable);
            st.setInt(4, X);
            st.setInt(5, Y);


            st.executeUpdate();

            System.out.println("Sucessfully Restaurant details Inserted!!!...");

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Opened database successfully");

    }


    //Adding Orders
    public static void addOrders(String rId, String cId, long timeOrder) {


        Connection conn = null;
        timeOrder /= 1000;//converting time in milliseconds into time in seconds

        try {
            //Class.forName("org.postgresql.Driver"); no need to register driver as we r using latest version of jdbc driver
            System.out.println("Connecting to database!!!...");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/ziggy1","postgres", "Leela@123");
            // String sql = ;

            PreparedStatement st = conn.prepareStatement("INSERT INTO ORDERS(rId, cId, TimeOrder) VALUES(?, ?, ?);");


            //st.setString(1, oId);
            st.setString(1, rId);
            st.setString(2, cId);
            st.setLong(3, timeOrder);
            //st.setString(5, null);


            st.executeUpdate();

            System.out.println("Sucessfully Order details Inserted!!!...");

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Opened database successfully");

    }


    //Display Optimal drivers
    public static void displayOptimal_Drivers(int K, String cId, String rId) {


        Connection conn = null;

        try {
            //Class.forName("org.postgresql.Driver"); no need to register driver as we r using latest version of jdbc driver

            System.out.println("Connecting to database!!!...");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/ziggy1","postgres", "Leela@123");



            PreparedStatement ps = conn.prepareStatement("SELECT c.cid, r.rid, d.did, d.dname,sqrt(((c.x_co_ordinate - r.x_co_ordinate) * (c.x_co_ordinate - r.x_co_ordinate)) + ((c.y_co_ordinate - r.y_co_ordinate) * (c.y_co_ordinate - r.y_co_ordinate))) as dist_C_R,sqrt(( (c.x_co_ordinate -d.x_co_ordinate) * (c.x_co_ordinate - d.x_co_ordinate)) + ((c.y_co_ordinate - d.y_co_ordinate) * (c.y_co_ordinate - d.y_co_ordinate))) as dist_C_D from drivers d, customers c, restaurant r WHERE (sqrt(( (c.x_co_ordinate - r.x_co_ordinate) * (c.x_co_ordinate - r.x_co_ordinate)) + ((c.y_co_ordinate - r.y_co_ordinate) * (c.y_co_ordinate - r.y_co_ordinate))) <= ?) and (sqrt(( (c.x_co_ordinate - d.x_co_ordinate) * (c.x_co_ordinate - d.x_co_ordinate)) + ((c.y_co_ordinate - d.y_co_ordinate) * (c.y_co_ordinate - d.y_co_ordinate))) <= ?) AND c.cid = ? AND r.rId = ? AND available = true order by dist_C_D, dist_C_R ;");
            ResultSet rs;

            ps.setInt(1, K);
            ps.setInt(2,K);
            ps.setString(3, cId);
            ps.setString(4,rId);

            rs = ps.executeQuery();
            System.out.println("The Restaurants and Drivers within given Range are: ");
            System.out.println("cID\t\trID\t\tdID\t\t Customer_To_Driver\t\tCustomer_to_Restaurant");
            System.out.println("==================================================================================");
            while ( rs.next() ) {
                System.out.println(rs.getString("cId") + "\t" + rs.getString("rId")+ "\t\t" + rs.getString("dId") + "\t"+ rs.getString("dist_C_D") +"\t\t\t"+rs.getString("dist_C_R"));
                System.out.println("------------------------------------------------------------------------------");
            }
            System.out.println("Sucessfully Displayed!!!...");

        }
        catch (Exception e) {
            e.printStackTrace();

        }
        System.out.println("Opened database successfully");

    }



    //Display available Restaurants for Given cId
    public static void displayAvailable_Restaurants(int K, String id) {


        Connection conn = null;

        try {
            //Class.forName("org.postgresql.Driver"); no need to register driver as we r using latest version of jdbc driver

            System.out.println("Connecting to database!!!...");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/ziggy1","postgres", "Leela@123");



            PreparedStatement ps = conn.prepareStatement("SELECT c.cId, d.did, r.rId, dish,o.oId,( sqrt( ((c.x_co_ordinate - r.x_co_ordinate) * (c.x_co_ordinate - r.x_co_ordinate)) + ((c.y_co_ordinate - r.y_co_ordinate) * (c.y_co_ordinate - r.y_co_ordinate)) ))as dist_c_r FROM customers c, Restaurant r, Orders o, drivers d WHERE o.cId = c.cId AND o.rId = r.rId AND ( sqrt(( (c.x_co_ordinate - r.x_co_ordinate) * (c.x_co_ordinate - r.x_co_ordinate)) + ((c.y_co_ordinate - r.y_co_ordinate) * (c.y_co_ordinate - r.y_co_ordinate))) <= ? )AND available = true AND c.cId = ? order by dist_c_r;");
            ResultSet rs;

            ps.setInt(1, K);
            ps.setString(2,id);


            rs = ps.executeQuery();
            System.out.println("The Restaurants and Drivers within given Range are: ");
            System.out.println("cID\t\trID\t\tdID\t\tdish\t Restaurant_To_Customer");
            System.out.println("==================================================================================");
            while ( rs.next() ) {
                System.out.println(rs.getString("cId") + "\t" + rs.getString("rId")+ "\t\t" + rs.getString("dId") + "\t"+ rs.getString("dish") +"\t" +rs.getString("dist_c_r"));
                System.out.println("------------------------------------------------------------------------------");
            }
            System.out.println("Sucessfully Displayed!!!...");

        }
        catch (Exception e) {
            e.printStackTrace();

        }
        System.out.println("Opened database successfully");

    }
    
    //Display All Customers
    public static void displayCustomers() {


        Connection conn = null;

        try {
            //Class.forName("org.postgresql.Driver"); no need to register driver as we r using latest version of jdbc driver

            System.out.println("Connecting to database!!!...");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/ziggy1","postgres", "Leela@123");



            PreparedStatement ps = conn.prepareStatement("SELECT * FROM CUSTOMERS;");
            ResultSet rs;

            
            rs = ps.executeQuery();
            System.out.println("The Restaurants and Drivers within given Range are: ");
            System.out.println("cID\t\tcName\t\tcEmail\t\t\tcPhone\t\tcAddress\t\tX_Co_ordinate\tY_Co_Ordinate");
            System.out.println("====================================================================================================");
            while ( rs.next() ) {
                System.out.println(rs.getString("cId") + "\t\t" + rs.getString("cName")+ "\t" + rs.getString("cEmail")+"\t" + rs.getLong("cPh") + "\t"+ rs.getString("cAddress") +"\t\t\t" +rs.getInt("x_co_ordinate") +"\t"+rs.getInt("y_co_ordinate"));
                System.out.println("-------------------------------------------------------------------------------------------------");
            }
            System.out.println("Sucessfully Displayed!!!...");

        }
        catch (Exception e) {
            e.printStackTrace();

        }
        System.out.println("Opened database successfully");

    }

    //Display All Restaurants
    public static void displayRestaurants() {


        Connection conn = null;

        try {
            //Class.forName("org.postgresql.Driver"); no need to register driver as we r using latest version of jdbc driver

            System.out.println("Connecting to database!!!...");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/ziggy1","postgres", "Leela@123");



            PreparedStatement ps = conn.prepareStatement("SELECT * FROM RESTAURANT;");
            ResultSet rs;


            rs = ps.executeQuery();
            System.out.println("The Restaurants and Drivers within given Range are: ");
            System.out.println("rId\t\tDish\t\tPrice\t\tX_Co_ordinate\tY_Co_Ordinate\trName");
            System.out.println("====================================================================================================");
            while ( rs.next() ) {
                System.out.println(rs.getString("rId") + "\t\t" + rs.getString("dish")+ "\t" + rs.getInt("price")+"\t"+ rs.getInt("x_co_ordinate") +"\t"+rs.getInt("y_co_ordinate") +" "+ rs.getString("rname") );
                System.out.println("-------------------------------------------------------------------------------------------------");
            }
            System.out.println("Sucessfully Displayed!!!...");

        }
        catch (Exception e) {
            e.printStackTrace();

        }
        System.out.println("Opened database successfully");

    }

    //Display All Orders
    public static void displayOrders() {


        Connection conn = null;

        try {
            //Class.forName("org.postgresql.Driver"); no need to register driver as we r using latest version of jdbc driver

            System.out.println("Connecting to database!!!...");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/ziggy1","postgres", "Leela@123");



            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ORDERS;");
            ResultSet rs;


            rs = ps.executeQuery();
            System.out.println("The ORDERS ARE: ");
            System.out.println("oID\t\trId\t\tcId\t\tTimeOfOrder\t\tdId");
            System.out.println("====================================================================================================");
            while ( rs.next() ) {
                System.out.println(rs.getString("oId") + "\t\t" + rs.getString("rId")+ "\t" + rs.getString("cId")+"\t" + rs.getLong("TimeOrder") + "\t"+ rs.getString("dId"));
                System.out.println("-------------------------------------------------------------------------------------------------");
            }
            System.out.println("Sucessfully Displayed!!!...");

        }
        catch (Exception e) {
            e.printStackTrace();

        }
        System.out.println("Opened database successfully");

    }

    //Display All Drivers
    public static void displayDrivers() {


        Connection conn = null;

        try {
            //Class.forName("org.postgresql.Driver"); no need to register driver as we r using latest version of jdbc driver

            System.out.println("Connecting to database!!!...");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/ziggy1","postgres", "Leela@123");



            PreparedStatement ps = conn.prepareStatement("SELECT * FROM DRIVERS;");
            ResultSet rs;


            rs = ps.executeQuery();
            System.out.println("The Drivers are: ");
            System.out.println("dID\t\tdName\t\tAvailable\t\tX_Co_ordinate\tY_Co_Ordinate");
            System.out.println("====================================================================================================");
            while ( rs.next() ) {
                System.out.println(rs.getString("dId") + "\t\t" + rs.getString("dName")+ "\t\t" + rs.getString("Available")+ "\t\t" +rs.getInt("x_co_ordinate") +"\t"+rs.getInt("y_co_ordinate"));
                System.out.println("-------------------------------------------------------------------------------------------------");
            }
            System.out.println("Sucessfully Displayed!!!...");

        }
        catch (Exception e) {
            e.printStackTrace();

        }
        System.out.println("Opened database successfully");

    }


    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        Main m = new Main();

        int X, Y, dPrice;
        long cPh;
        String rId, cId, dId, dName, rName, cName, cAddress, cEmail;
        boolean dAvailable;
        while(true){

            System.out.println(" 1. ADD CUSTOMERS\n 2. ADD Restaurant\n 3. ADD DRIVERS\n 4. ADD ORDERS\n 5. Display_Optimal_Drivers\n 6. Display_Available_Restaurants\n 7. Display all Customers\n 8. Display all Restaurants\n 9. Display all Orders\n 10. Display all Drivers\n 11. exit()\n");
            System.out.print("Enter Option : ");
            int ch = sc.nextInt();
            switch(ch) {

                case 1:  System.out.print("Enter customer Id to insert!!!! : ");
                        cId = sc.next();

                        System.out.print("Enter customer Name to insert!!!! : ");
                        cName = sc.next();

                        System.out.print("Enter customer Email to insert!!!! : ");
                        cEmail = sc.next();

                        System.out.print("Enter customer Phone Number to insert!!!! : ");
                        cPh = sc.nextLong();

                        System.out.print("Enter customer Address to insert!!!! : ");
                        cAddress = sc.next();

                        System.out.print("Enter customer X_Co-Ordinate to insert!!!! : ");
                        X = sc.nextInt();

                        System.out.print("Enter customer Y_Co-Ordinate to insert!!!! : ");
                        Y = sc.nextInt();

                        System.out.println("Adding Customers into Customer table");

                        m.addCustomer(cId, cName, cEmail, cPh,cAddress,X, Y);
                        break;
                case 2:
                        System.out.print("Enter Restaurant Id to insert!!!! : ");
                        rId = sc.next();

                        System.out.print("Enter dish Name to insert!!!! : ");
                        dName = sc.next();

                        System.out.print("Enter dish Price to insert!!!! : ");
                        dPrice = sc.nextInt();

                        System.out.print("Enter Restaurant X_Co-Ordinate to insert!!!! : ");
                        X = sc.nextInt();

                        System.out.print("Enter Restaurant Y_Co-Ordinate to insert!!!! : ");
                        Y = sc.nextInt();

                        System.out.print("Enter Restaurant Name to insert!!!! : ");
                        rName = sc.next();
                        System.out.println("Adding Restaurants into Restaurant table....");
                        m.addRestaurant(rId, dName, dPrice, X, Y, rName);
                        break;
                case 3: System.out.print("Enter DRIVERS Id to insert!!!! : ");
                        dId = sc.next();

                        System.out.print("Enter  DRIVERS Name to insert!!!! : ");
                        dName = sc.next();

                        System.out.print("Enter enter 1 if driver is available to insert!!!! : ");
                        dAvailable = sc.nextBoolean();

                        System.out.print("Enter customer X_Co-Ordinate to insert!!!! : ");
                        X = sc.nextInt();

                        System.out.print("Enter customer Y_Co-Ordinate to insert!!!! : ");
                        Y = sc.nextInt();
                        System.out.println("Adding Drivers into Driver table.....");
                        m.addDrivers(dId, dName, dAvailable, X, Y);
                        break;
                case 4:
                        System.out.print("Enter Restaurant's ID to insert!!!! : ");
                        rId = sc.next();

                        System.out.print("Enter Customer's ID to insert!!!! : ");
                        cId = sc.next();

                        System.out.println("current timestamp is processing to insert!!!! : ");
                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                        long timeOrder = timestamp.getTime();
                        System.out.println("Adding customers into customer table....");

                        m.addOrders(rId, cId, timeOrder);
                        break;
                case 5: System.out.print("Enter Range(distance) from customer to consider: ");
                        K = sc.nextInt();

                        System.out.print("Enter Customer id to check nearest Drivers: ");
                        cId = sc.next();

                        System.out.print("Enter Restaurant id to check nearest Drivers: ");
                        rId = sc.next();


                        System.out.println("Displaying display Optimal Drivers....");
                        m.displayOptimal_Drivers(K, cId, rId);
                        break;
                case 6: System.out.print("Enter Range(distance) from customer to consider: ");
                        K = sc.nextInt();

                        System.out.print("Enter Customer id to check nearest Restaurants and Drivers: ");
                        cId = sc.next();

                        System.out.println("Displaying all Available Restaurants....");
                        m.displayAvailable_Restaurants(K, cId);
                        break;
                case 7: System.out.println("Displaying Customer Details....");
                        m.displayCustomers();
                        break;
                case 8: System.out.println("Displaying Customer Details....");
                        m.displayRestaurants();
                        break;
                case 9: System.out.println("Displaying Customer Details....");
                        m.displayOrders();
                        break;
                case 10: System.out.println("Displaying Customer Details....");
                        m.displayDrivers();
                        break;
                case 11:System.out.println("Ending The Program!!!!.....");
                        return;

                default:System.out.println("Enter Valid Input!!!!...");
                        break;


            }
        }

    }
}
