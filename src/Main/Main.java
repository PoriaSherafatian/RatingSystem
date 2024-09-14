package Main;

import Authenticator.authenticateLogInUsers;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        String myDriver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/rating";

        System.out.println("Hi dear if you are new here you probably should to signup for do it please enter 'SIGNUP' \nAnd if you are our costumer so you already have an account please enter 'LOGIN' then enter your username and password");
        String authentication = scanner.next();

        switch (authentication) {
            case "signup":
                authentication.toLowerCase().equals("signup");

                System.out.println("Please enter your USER NAME you wanna to add in our site : ");
                String userName = scanner.next();

                System.out.println("Please enter your PASSCODE you wanna to use for '" + userName.toUpperCase() + "' account");
                int passCode = scanner.nextInt();

                System.out.println("Please enter your PHONE NUMBER you wanna to add in our site : ");
                double phoneNumber = scanner.nextDouble();


                System.out.println("Your account has been created successfully \nUserName is : " + userName + " \nPasscode is : " + passCode + " \nPhoneNumber is : " + phoneNumber);

                try {
                    Class.forName(myDriver);
                    Connection conn = DriverManager.getConnection(url, "root", "Poria1382");
                    String sql = "INSERT INTO user_rated_information (userName, passCode, phoneNumber) VALUES (?, ?, ?)";
                    PreparedStatement preparedStmt = conn.prepareStatement(sql);
                    preparedStmt.setString(1, userName);
                    preparedStmt.setInt(2, passCode);
                    preparedStmt.setDouble(3, phoneNumber);
                    preparedStmt.execute();
                    conn.close();
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
                break;

            case "login":
                authentication.toLowerCase().equals("login");
                authenticateLogInUsers ALIN = new authenticateLogInUsers();
                ALIN.checkInUserOfDatabase();
                break;

            default:
                throw new Exception("Invalid entry please signup or login");
        }
    }
}

