package Authenticator;

import java.sql.*;
import java.util.Scanner;

public class authenticateLogInUsers {

    public static void checkInUserOfDatabase() throws Exception {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Please enter your passcode: ");
        int passcode = scanner.nextInt();

        System.out.println("Checking your entry information....\n");

        if (authenticateUser(username, passcode)) {
            System.out.println("Welcome back " + username + "\nLogged in successfully");

            System.out.println(username.toUpperCase()+ " Please rate our product and our service 1 TO 10 ");
            int rate = scanner.nextInt();
            if (rate > 10 || rate < 1) {
                throw new Exception("Please enter valid rate between 1 and 10");
            }
            System.out.println("Please write your DESCRIPTION about our service and etc : ");
            String description = scanner.next();

            insertRateToDataBase(rate, description);

            System.out.println("your rate and description added successfully! ");
        }
        else {
            throw new Exception("Invalid username or password \nPlease SignUp or Login again");
        }
    }

    private static void insertRateToDataBase( int rate , String description) throws Exception {

        String myDriver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/rating";

        try {
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(url, "root", "Poria1382");
            String sql = "UPDATE user_rated_information SET rate = ?, description = ? WHERE rate IS NULL AND description IS NULL";
            PreparedStatement preparedStmt = conn.prepareStatement(sql);

            preparedStmt.setInt(1, rate);
            preparedStmt.setString(2, description);

            preparedStmt.executeUpdate();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

        public static boolean authenticateUser(String username, int passcode) throws SQLException {

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rating", "root", "Poria1382")) {

            String query = "SELECT userName, passCode FROM user_rated_information WHERE userName = ? AND passCode = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setInt(2, passcode);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    return resultSet.next();
                }
            }
        }
    }
}

