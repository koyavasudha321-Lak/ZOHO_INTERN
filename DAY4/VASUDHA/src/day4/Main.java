package day4;

import java.sql.*;
import java.util.*;

public class Main{
    static int K;


    //Adding Customers
    public static void addCustomer() {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter customer Id to insert!!!! : ");
        String cId = sc.nextLine();

        System.out.print("Enter customer Name to insert!!!! : ");
        String cName = sc.nextLine();

        System.out.print("Enter customer Email to insert!!!! : ");
        String cEmail = sc.nextLine();

        System.out.print("Enter customer Phone Number to insert!!!! : ");
        int cPh = sc.nextInt();

        System.out.print("Enter customer Address to insert!!!! : ");
        String cAddress = sc.next();


        System.out.print("Enter customer X_Co-Ordinate to insert!!!! : ");
        int X = sc.nextInt();

        System.out.print("Enter customer Y_Co-Ordinate to insert!!!! : ");
        int Y = sc.nextInt();

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
            st.setInt(4, cPh);
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
    public static void addRestaurant() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Restaurant Id to insert!!!! : ");
        String rId = sc.nextLine();

        System.out.print("Enter dish Name to insert!!!! : ");
        String dName = sc.nextLine();

        System.out.print("Enter dish Price to insert!!!! : ");
        int dPrice = sc.nextInt();

        System.out.print("Enter Restaurant X_Co-Ordinate to insert!!!! : ");
        int X = sc.nextInt();

        System.out.print("Enter Restaurant Y_Co-Ordinate to insert!!!! : ");
        int Y = sc.nextInt();

        System.out.print("Enter Restaurant Name to insert!!!! : ");
        String rName = sc.next();

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
    public static void addDrivers() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter DRIVERS Id to insert!!!! : ");
        String dId = sc.nextLine();

        System.out.print("Enter  DRIVERS Name to insert!!!! : ");
        String dName = sc.nextLine();

        System.out.print("Enter enter 1 if driver is available to insert!!!! : ");
        boolean dAvailable = sc.nextBoolean();

        System.out.print("Enter customer X_Co-Ordinate to insert!!!! : ");
        int X = sc.nextInt();

        System.out.print("Enter customer Y_Co-Ordinate to insert!!!! : ");
        int Y = sc.nextInt();


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
    public static void addOrders() {

        Scanner sc = new Scanner(System.in);

//        System.out.print("Enter Order Id to insert!!!! : ");
//        String oId = sc.nextLine();

        System.out.print("Enter Restaurant's ID to insert!!!! : ");
        String rId = sc.nextLine();

        System.out.print("Enter Customer's ID to insert!!!! : ");
        String cId = sc.nextLine();

        System.out.println("current timestamp is processing to insert!!!! : ");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long timeOrder = timestamp.getTime();
//
//        System.out.print("Enter Drivers ID to insert!!!! : ");
//        String dId = sc.nextLine();

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
    public static void displayOptimal_Drivers() {

        Scanner sc = new Scanner(System.in);
        Connection conn = null;

        try {
            //Class.forName("org.postgresql.Driver"); no need to register driver as we r using latest version of jdbc driver

            System.out.println("Connecting to database!!!...");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/ziggy1","postgres", "Leela@123");

            System.out.print("Enter Range(distance) from customer to consider: ");
            K = sc.nextInt();

            System.out.print("Enter Customer id to check nearest Restaurants and Drivers: ");
            String id = sc.next();

            PreparedStatement ps = conn.prepareStatement("SELECT c.cId, r.rId ,d.did, dish, ( sqrt(( (d.x_co_ordinate - r.x_co_ordinate) * (d.x_co_ordinate - r.x_co_ordinate)) + ((d.y_co_ordinate - r.y_co_ordinate) * (d.y_co_ordinate - r.y_co_ordinate)))) as dist_d_r, ( sqrt( ((c.x_co_ordinate - d.x_co_ordinate) * (c.x_co_ordinate - d.x_co_ordinate)) + ((c.y_co_ordinate - d.y_co_ordinate) * (c.y_co_ordinate - d.y_co_ordinate)) )) as dist_c_d FROM customers c, Restaurant r, Drivers d, Orders o WHERE o.cId = c.cId AND o.rId = r.rId AND  ( sqrt(( (c.x_co_ordinate - d.x_co_ordinate) * (c.x_co_ordinate - d.x_co_ordinate)) + ((c.y_co_ordinate - d.y_co_ordinate) * (c.y_co_ordinate - d.y_co_ordinate))) <= ?) AND  ( sqrt(( (d.x_co_ordinate - r.x_co_ordinate) * (d.x_co_ordinate - r.x_co_ordinate)) + ((d.y_co_ordinate - r.y_co_ordinate) * (d.y_co_ordinate - r.y_co_ordinate))) <= ? ) AND c.cId = ? AND d.available = true ORDER BY dist_d_r, dist_c_d;");
            ResultSet rs;

            ps.setInt(1, K);
            ps.setInt(2,K);
            ps.setString(3, id);

            rs = ps.executeQuery();
            System.out.println("The Restaurants and Drivers within given Range are: ");
            System.out.println("cID\t\trID\t\tdID\t\tdish\t Restaurant_To_Driver\t\tCustomer_to_Driver");
            System.out.println("==================================================================================");
            while ( rs.next() ) {
                System.out.println(rs.getString("cId") + "\t" + rs.getString("rId")+ "\t\t" + rs.getString("dId") + "\t"+ rs.getString("dish") +"\t" +rs.getString("dist_d_r") +"\t\t\t"+rs.getString("dist_c_d"));
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
    public static void displayAvailable_Restaurants() {

        Scanner sc = new Scanner(System.in);
        Connection conn = null;

        try {
            //Class.forName("org.postgresql.Driver"); no need to register driver as we r using latest version of jdbc driver

            System.out.println("Connecting to database!!!...");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/ziggy1","postgres", "Leela@123");

            System.out.print("Enter Range(distance) from customer to consider: ");
            K = sc.nextInt();

            System.out.print("Enter Customer id to check nearest Restaurants and Drivers: ");
            String id = sc.next();

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

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        Main m = new Main();


        while(true){

            System.out.println(" 1. ADD CUSTOMERS\n 2. ADD Restaurant\n 3. ADD DRIVERS\n 4. ADD ORDERS\n 5. Display_Optimal_Drivers\n 6. Display_Available_Restaurants\n 7. exit()\n");
            System.out.print("Enter Option : ");
            int ch = sc.nextInt();
            switch(ch) {

                case 1: System.out.println("Adding Customers into Customer table");
                        m.addCustomer();
                        break;
                case 2: System.out.println("Adding Restaurants into Restaurant table....");
                        m.addRestaurant();
                        break;
                case 3: System.out.println("Adding Drivers into Driver table.....");
                        m.addDrivers();
                        break;
                case 4: System.out.println("Adding customers into customer table....");
                        m.addOrders();
                        break;
                case 5: System.out.println("Displaying display Optimal Drivers....");
                        m.displayOptimal_Drivers();
                        break;
                case 6: System.out.println("Displaying all Available Restaurants....");
                        m.displayAvailable_Restaurants();
                        break;
                case 7:System.out.println("Ending The Program!!!!.....");
                        return;

                default:System.out.println("Enter Valid Input!!!!...");
                        break;


            }
        }

    }
}
