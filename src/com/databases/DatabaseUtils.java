package com.databases;

import java.sql.*;
import java.util.Properties;

public class DatabaseUtils {
    public static void main(String[] args) {
        Connection con = connectToDatabases("jdbc:mysql://172.21.100.124:3306/mysql",
                "debianmysql","debianmysqlpasswd");
        StringBuilder result = executeQuery(con, "select * from user");
        System.out.println(result.toString());
    }

    public static StringBuilder executeQuery(Connection con, String sql){
        PreparedStatement pstmt;
        ResultSet rs;
        StringBuilder sqlResult = new StringBuilder();
        try {
//            con.setAutoCommit(false);
            pstmt = con.prepareStatement(sql);
//            pstmt.setFetchSize(8000);
            rs = pstmt.executeQuery();
            int rsNum = rs.getMetaData().getColumnCount();
            StringBuilder fields = new StringBuilder();
            for (int l = 1; l <= rsNum ; l++) {
                fields.append(rs.getMetaData().getColumnName(l));
                if(l < rsNum){
                    fields.append(", ");
                }
            }
            fields.append(System.lineSeparator());
            sqlResult.append(fields);
            while (rs.next()){
                for (int i = 1; i <= rsNum ; i++) {
                    sqlResult.append(rs.getObject(i).toString());
                    if(i < rsNum){
                        sqlResult.append(", ");
                    }
                }
                sqlResult.append(System.lineSeparator());
            }
            rs.close();
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sqlResult;
    }

    public static Connection connectToDatabases(String url, String user, String password){
        Connection con = null;
        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);
//        props.setProperty("gssEncMode","disabled");
        try {
            con = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

}
