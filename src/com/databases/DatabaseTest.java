package com.databases;

import com.java.common.GlobalParams;

import java.sql.Connection;

public class DatabaseTest {
    public static void main(String[] args) {

        String sql = "select * from student";
        Connection con = DatabaseUtils.connectToDatabases(GlobalParams.mariaDbUrl,
                GlobalParams.mariaDbUser,GlobalParams.mariaDbPassword);
        StringBuilder result = DatabaseUtils.executeSql(con, sql, true);
        System.out.println(result.toString());

        con = DatabaseUtils.connectToDatabases(GlobalParams.pgsqlUrl,
                GlobalParams.pgsqlUser,GlobalParams.pgsqlPassword);
        result = DatabaseUtils.executeSql(con, sql,true);
        System.out.println(result.toString());

    }
}
