package com.databases;

import java.sql.*;
import java.util.Properties;

public class DatabaseUtils {

    public static StringBuilder executeSql(Connection con, String sql, boolean getResult){
        PreparedStatement pstmt;
        ResultSet rs;
        StringBuilder sqlResult = new StringBuilder();
        try {
            pstmt = con.prepareStatement(sql);
//            pstmt.setFetchSize(8000);
            rs = pstmt.executeQuery();
            if(getResult) {
                int rsNum = rs.getMetaData().getColumnCount();
                StringBuilder fields = new StringBuilder();
                for (int l = 1; l <= rsNum; l++) {
                    fields.append(rs.getMetaData().getColumnName(l));
                    if (l < rsNum) {
                        fields.append(", ");
                    }
                }
                fields.append(System.lineSeparator());
                sqlResult.append(fields);
                while (rs.next()) {
                    for (int i = 1; i <= rsNum; i++) {
                        if (rs.getObject(i) != null) {
                            sqlResult.append(rs.getObject(i).toString());
                        } else {
                            sqlResult.append("NULL_OBJECT");
                        }

                        if (i < rsNum) {
                            sqlResult.append(", ");
                        }
                    }
                    sqlResult.append(System.lineSeparator());
                }
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
//        props.setProperty("gssEncMode","disable");
        try {
            con = DriverManager.getConnection(url, props);
            con.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }

}
