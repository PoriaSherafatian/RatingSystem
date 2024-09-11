import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        System.out.println("Hi welcome back to ' PoriBootik ', to add your ' RATE ' and ' DESCRIPTION ' you should add your PhoneNumber and UserName");

        System.out.println("Please enter the PHONE NUMBER you would like to add : ");
        Double phoneNumber = scanner.nextDouble();

        if (phoneNumber.longValue() < 11) {
            throw new Exception("INVALID PHONE NUMBER - PLEASE ENTER A VALID PHONE NUMBER");
        } else {
            Rating(phoneNumber);
        }
    }

    public static void Rating(Double phoneNumber) throws Exception {

        System.out.println("Please enter the USER NAME you would like to add : ");
        String userName = scanner.next();

        System.out.println(userName.toUpperCase() + " Please rate our product and our service 1 TO 10 ");
        int rate = scanner.nextInt();

        if (rate > 10 || rate < 0) {
            throw new Exception("PLEASE ENTER A VALID RATE BETWEEN 0 AND 10");
        }

        System.out.println("Add DESCRIPTION you would like : ");
        String description = scanner.next();

        System.out.println("Thank you for adding rate and description for our service and our product\n" + userName + " added: " + description);

        String url = "jdbc:mysql://localhost:3306/rating";
        String user = "root";
        String password = "Poria1382";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            String sql = "INSERT INTO userinfo (phone_number, user_name, rate, description) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, phoneNumber);
            statement.setString(2, userName);
            statement.setInt(3, rate);
            statement.setString(4, description);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new rating was inserted successfully!");
            }

            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

