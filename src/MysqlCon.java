import java.sql.*;
import java.util.Scanner;

public class MysqlCon{
    String path = "test";
    String table = "persons";

    void method(int choice){
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + path, "root", "dipu1234");

            Statement stmt = con.createStatement();

            String query = query(choice);

            if (choice == 1){
                ResultSet rs = stmt.executeQuery("select * from " + table);
                while (rs.next())
                    System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
                con.close();
            }
            else if(choice == 2 || choice == 3){
                stmt.executeUpdate(query);
                System.out.println("Successfully Updated---\n");
            }
            else if (choice == 4){
                stmt.executeUpdate(query);
                stmt.executeUpdate("DELETE FROM Persons WHERE 1;");
                System.out.println("Successfully Deleted---\n");
            }
            else if(choice == 6){
                ResultSet r = stmt.executeQuery(query);
                while(r.next()){
                    System.out.println(r.getInt(1) +" "+r.getString(2)+" "+r.getString(3));
                }
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    String query(int choice){
        String query = null;

        switch (choice){
            case 1: {
                query = "select * from " + table;
                break;
            }
            case 2: {
                Scanner sc2 = new Scanner(System.in);
                System.out.println("Enter ID---");
                int id = sc2.nextInt();
                System.out.println("Enter Name---");
                String name = sc2.next();
                System.out.println("Enter Salary---");
                int salary = sc2.nextInt();

                query = "insert into "+table+" values ("+id+", \'"+name+"\' ,"+salary+")";
                break;
            }
            case 3: {
                query = "update persons set name = 'Dipendra Bhardwaj' where id = 51";
                break;
            }
            case 4: {
                query = "SET SQL_SAFE_UPDATES=0;";
                break;
            }
            case 5: {
                System.out.println("\n\n\nSuccessfully Exited...");
                System.exit(0);
            }
            case 6: {
                query = "SELECT * FROM Persons ORDER BY id";
                break;
            }
            default:{
                System.out.println("Wrong Choice---");
            }
        }
        return query;
    }

    public static void main(String args[]) {

        while (true) {
            System.out.println("\nPress 1 to Retrieve Table---");
            System.out.println("Press 2 to Insert Into  Table---");
            System.out.println("Press 3 to Update from Table---");
            System.out.println("Press 4 to Drop from Table---");
            System.out.println("Press 5 to Exit from Table---");
            System.out.println("Press 6 to Order By ID from Table---");

            Scanner sc = new Scanner(System.in);
            System.out.println("\nEnter Your Choice---");
            int choice = sc.nextInt();

            MysqlCon obj = new MysqlCon();
            obj.method(choice);
        }
    }
}