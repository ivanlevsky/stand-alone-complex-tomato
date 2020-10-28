package com.databases;

import com.java.common.GlobalParams;

import java.sql.Connection;

public class DatabaseTest {
    public static void main(String[] args) {

        String sql = "select * from student";
        Connection con = DatabaseUtils.connectToDatabases(GlobalParams.mariaDbUrl,
                GlobalParams.mariaDbUser,GlobalParams.mariaDbPassword);
        StringBuilder result = DatabaseUtils.executeQuery(con, sql);
        System.out.println(result.toString());

        con = DatabaseUtils.connectToDatabases(GlobalParams.pgsqlUrl,
                GlobalParams.pgsqlUser,GlobalParams.pgsqlPassword);
        result = DatabaseUtils.executeQuery(con, sql);
        System.out.println(result.toString());


    }
}
