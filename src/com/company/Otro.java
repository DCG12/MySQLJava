package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by 46406163y on 19/01/17.
 */
public class Otro {

    public static void main(String[] args) throws SQLException {
        Connection conn = null;

        final String url = "jdbc:mysql://172.31.73.192:3306/";
        final String dbName = "world";
        final String driver = "com.mysql.jdbc.Driver";
        final String userName = "root" ;
        final String password = "theking12" ;


        try{
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url + dbName, userName, password);

            if(!conn.isClosed()){
                System.out.println("Database connection working using TCP/IP... ");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (conn != null)
                conn.close();
        }
    }
}
