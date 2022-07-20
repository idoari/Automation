package utilities;

import io.qameta.allure.Step;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ManageDB extends  CommonOps{

/*
    @Step("Start new db connection session")
    public static void StartConnection(String dbURL ,  String username , String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(dbURL,username,password);
            stat = con.createStatement();

        } catch (Exception e) {
            System.out.println("Error Occur While Connecting To Database. see details : " + e);
        }

    }

    @Step("close db session")
    public static void CloseConnection() {
        try {
            con.close();
        } catch (Exception e) {
            System.out.println("Error Occur While Closing the Database. see details : " + e);
        }
    }
    */

}
