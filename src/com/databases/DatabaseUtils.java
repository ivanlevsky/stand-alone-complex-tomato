package com.databases;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class DatabaseUtils {

    public static String executeSql(Connection con, String sql, boolean getResult, ArrayList<String>... data){
        PreparedStatement pstmt;
        ResultSet rs;
        StringBuilder sqlResult = new StringBuilder();
        HashMap<Integer,String> tableColumnType = new HashMap<>();
        String splitText = "S_P_L_I_T";
        try {

            if(data.length > 0){
                String tableName = sql.toLowerCase();
                tableName = tableName.substring(tableName.indexOf("into")+4,tableName.indexOf("(")).trim();
                pstmt = con.prepareStatement("select * from "+ tableName +" limit 1");
                pstmt.executeQuery();
                int rsNum = pstmt.getMetaData().getColumnCount();
                for (int i = 1; i <= rsNum; i++) {
                    tableColumnType.put(i,pstmt.getMetaData().getColumnTypeName(i));
                }
                pstmt = con.prepareStatement(sql);
                for (String s : data[0]) {
                    String[] splitData = s.split(splitText);
                    for (int i = 1; i <= splitData.length; i++) {
                        //mysql:INTEGER, pgsql:int4
                        //mysql:VARCHAR, pgsql:varchar
                        if(tableColumnType.get(i).toLowerCase().startsWith("int")){
                            pstmt.setInt(i, Integer.parseInt(splitData[i-1]));
                        }else if(tableColumnType.get(i).equalsIgnoreCase("varchar")){
                            pstmt.setString(i, splitData[i-1]);
                        }
                    }
                    pstmt.addBatch();
                }
                pstmt.executeBatch();
            }else {
                pstmt = con.prepareStatement(sql);
                pstmt.execute();
            }

            rs = pstmt.getResultSet();
            con.commit();
            if(getResult) {
                int rsNum = rs.getMetaData().getColumnCount();
                StringBuilder fields = new StringBuilder();
                for (int l = 1; l <= rsNum; l++) {
                    fields.append(rs.getMetaData().getColumnName(l));
                    if (l < rsNum) {
                        fields.append(splitText);
                    }
                }
                fields.append(System.lineSeparator());
                sqlResult.append(fields);
                while (rs.next()) {
                    for (int i = 1; i <= rsNum; i++) {
                        if (rs.getObject(i) != null) {
                            sqlResult.append(rs.getObject(i).toString());
                        }

                        if (i < rsNum) {
                            sqlResult.append(splitText);
                        }
                    }
                    sqlResult.append(System.lineSeparator());
                }
            }
            if (rs != null) {
                rs.close();
            }
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sqlResult.toString();
    }

    public static Connection connectToDatabases(String url, String user, String password){
        Connection connection = null;
        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);
//        props.setProperty("gssEncMode","disable");
        try {
            connection = DriverManager.getConnection(url, props);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
