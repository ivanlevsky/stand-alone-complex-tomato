package com.databases;

import com.java.common.GlobalParams;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseTest {
    public static void main(String[] args) {

      createTableTest();
      readExceltoDatabase();
      queryDatabaseToExcel();
    }

    private static void queryDatabaseToExcel() {
        String querySql = "select * from movie";
        Connection con = DatabaseUtils.connectToDatabases(GlobalParams.mariaDbUrl,
                GlobalParams.mariaDbUser,GlobalParams.mariaDbPassword);
        String mariaDBRowValues = DatabaseUtils.executeSql(con, querySql, true, true);
        DatasetsUtils.writeExcel(GlobalParams.excelDatasets, "movieMaria", mariaDBRowValues, false);
        DatasetsUtils.writeCSV(GlobalParams.csvDatasets,mariaDBRowValues,true);

        con = DatabaseUtils.connectToDatabases(GlobalParams.pgsqlUrl,
                GlobalParams.pgsqlUser,GlobalParams.pgsqlPassword);
        String pgsqlRowValues = DatabaseUtils.executeSql(con, querySql, true, true);
        DatasetsUtils.writeExcel(GlobalParams.excelDatasets, "moviePg", pgsqlRowValues, true);
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void readExceltoDatabase() {
        String insertManySql = "INSERT INTO movie " +
                "(id, name, chnname, main_cast, year, region, type, viewed, want_to_review) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        ArrayList<String> data = DatasetsUtils.readExcel(GlobalParams.excelDatasets, "movie", true, null);
        Connection con = DatabaseUtils.connectToDatabases(GlobalParams.mariaDbUrl,
                GlobalParams.mariaDbUser,GlobalParams.mariaDbPassword);
        DatabaseUtils.executeSql(con, insertManySql, false, true, data);

        con = DatabaseUtils.connectToDatabases(GlobalParams.pgsqlUrl,
                GlobalParams.pgsqlUser,GlobalParams.pgsqlPassword);
        DatabaseUtils.executeSql(con, insertManySql, false, true, data);

        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void createTableTest(){
        String createTableSql =
                "CREATE TABLE movie"
             +" ("
             +"          id INT PRIMARY KEY  NOT NULL,"
             +"          name        VARCHAR(100) NOT NULL,"
             +"          chnname     VARCHAR(50),"
             +"          main_cast        VARCHAR(50),"
             +"          year         VARCHAR(10) NOT NULL,"
             +"          region      VARCHAR(20) NOT NULL,"
             +"          type        VARCHAR(20),"
             +"          viewed     VARCHAR(5) NOT NULL,"
             +"          want_to_review   VARCHAR(5) NOT NULL"
             +"  )";


        Connection con = DatabaseUtils.connectToDatabases(GlobalParams.mariaDbUrl,
                GlobalParams.mariaDbUser,GlobalParams.mariaDbPassword);
        DatabaseUtils.executeSql(con, createTableSql, false, true);

        con = DatabaseUtils.connectToDatabases(GlobalParams.pgsqlUrl,
                GlobalParams.pgsqlUser,GlobalParams.pgsqlPassword);
        DatabaseUtils.executeSql(con, createTableSql,false, true);
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
